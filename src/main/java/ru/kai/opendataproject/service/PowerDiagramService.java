package ru.kai.opendataproject.service;

import jakarta.annotation.PostConstruct;
import kn.uni.voronoitreemap.datastructure.OpenList;
import kn.uni.voronoitreemap.diagram.PowerDiagram;
import kn.uni.voronoitreemap.j2d.PolygonSimple;
import kn.uni.voronoitreemap.j2d.Site;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kai.opendataproject.dto.LightPoint;
import ru.kai.opendataproject.model.Point;
import ru.kai.opendataproject.model.Polygon;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PowerDiagramService {
    private final PointService pointService;
    private final BoundsService boundsService;
    @Getter
    private List<Polygon> polygons = new ArrayList<>();

    @PostConstruct
    private void init() {
        computeDiagram();
    }

    private boolean computeDiagram() {
        try {
            List<Point> points = pointService.getAllPoints();
            PowerDiagram diagram = new PowerDiagram();
            OpenList sites = new OpenList();
            PolygonSimple rootPolygon = boundsService.getBounds();

            for (Point point: points) {
                Site site = new Site(point.getYCoord()*1000000D, point.getXCoord()*1000000D);
                site.setData(point.getClusterId()+";"+point.getUuid()+";"+point.getMarkLevel());
                sites.add(site);
            }
            diagram.setSites(sites);
            diagram.setClipPoly(rootPolygon);
            diagram.computeDiagram();
            List<Polygon> list = new ArrayList<>();
            for (int i=0;i<sites.size;i++){
                Site site=sites.array[i];
                PolygonSimple polygon=site.getPolygon();
                if(polygon == null)
                    continue;
                String[] data = ((String) site.getData()).split(";");
                Polygon tempPoly = new Polygon(data[1], data[0], Integer.parseInt(data[2]));
                for(int j=0; j<polygon.getNumPoints(); j++) {
                    tempPoly.add(new LightPoint(polygon.getXPoints()[j]/1000000D, polygon.getYPoints()[j]/1000000D));
                }
                list.add(tempPoly);
            }
            polygons = list;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
