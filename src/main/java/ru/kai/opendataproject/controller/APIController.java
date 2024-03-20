package ru.kai.opendataproject.controller;

import kn.uni.voronoitreemap.j2d.PolygonSimple;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kai.opendataproject.model.Point;
import ru.kai.opendataproject.model.Polygon;
import ru.kai.opendataproject.service.PointService;
import ru.kai.opendataproject.service.PowerDiagramService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class APIController {
    private final PointService pointService;
    private final PowerDiagramService diagramService;
    @GetMapping("/getAllPoints")
    public List<Point> getAllPoints() {
        return pointService.getAllPoints();
    }

    @GetMapping("/getPolygons")
    public List<Polygon> getPolygons() {
        HashMap<String, Polygon> polygons = new HashMap<>();
        List<Point> points = pointService.getAllPoints();
        for(Point point: points) {
            if(!polygons.containsKey(point.getClusterId())) {
                polygons.put(point.getClusterId(), new Polygon(point.getUuid(), point.getClusterId(), point.getMarkLevel()));
            }
            polygons.get(point.getClusterId()).add(point);
        }
        return polygons.values().stream().toList();
    }

    @GetMapping("/getVoronoiPolygons")
    public List<Polygon> getVoronoiPolygons() {
        return diagramService.getPolygons();
    }
}
