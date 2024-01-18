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
    public PolygonSimple test1() {
        PolygonSimple polygonSimple = new PolygonSimple();
        polygonSimple.add(37.602287*1000000D,55.566373*1000000D);
        polygonSimple.add(37.598397*1000000D,55.566605*1000000D);
        polygonSimple.add(37.597468*1000000D,55.566872*1000000D);
        polygonSimple.add(37.52724*1000000D,55.589663*1000000D);
        polygonSimple.add(37.511879*1000000D,55.595596*1000000D);
        polygonSimple.add(37.506553*1000000D,55.598249*1000000D);
        polygonSimple.add(37.487842*1000000D,55.60839*1000000D);
        polygonSimple.add(37.455434*1000000D,55.637223*1000000D);
        polygonSimple.add(37.381562*1000000D,55.712603*1000000D);
        polygonSimple.add(37.381352*1000000D,55.713094*1000000D);
        polygonSimple.add(37.373982*1000000D,55.732513*1000000D);
        polygonSimple.add(37.368948*1000000D,55.745982*1000000D);
        polygonSimple.add(37.368577*1000000D,55.747868*1000000D);
        polygonSimple.add(37.365612*1000000D,55.764412*1000000D);
        polygonSimple.add(37.36526*1000000D,55.771728*1000000D);
        polygonSimple.add(37.367267*1000000D,55.790372*1000000D);
        polygonSimple.add(37.394287*1000000D,55.854447*1000000D);
        polygonSimple.add(37.395834*1000000D,55.857395*1000000D);
        polygonSimple.add(37.397371*1000000D,55.860018*1000000D);
        polygonSimple.add(37.39933*1000000D,55.862347*1000000D);
        polygonSimple.add(37.401393*1000000D,55.864514*1000000D);
        polygonSimple.add(37.40481*1000000D,55.867285*1000000D);
        polygonSimple.add(37.406579*1000000D,55.868473*1000000D);
        polygonSimple.add(37.40906*1000000D,55.869931*1000000D);
        polygonSimple.add(37.411159*1000000D,55.871002*1000000D);
        polygonSimple.add(37.41236*1000000D,55.871541*1000000D);
        polygonSimple.add(37.413783*1000000D,55.872149*1000000D);
        polygonSimple.add(37.441535*1000000D,55.883839*1000000D);
        polygonSimple.add(37.442419*1000000D,55.884165*1000000D);
        polygonSimple.add(37.527026*1000000D,55.905796*1000000D);
        polygonSimple.add(37.527938*1000000D,55.906015*1000000D);
        polygonSimple.add(37.531896*1000000D,55.906828*1000000D);
        polygonSimple.add(37.533818*1000000D,55.907172*1000000D);
        polygonSimple.add(37.537191*1000000D,55.907591*1000000D);
        polygonSimple.add(37.590735*1000000D,55.913656*1000000D);
        polygonSimple.add(37.590977*1000000D,55.913624*1000000D);
        polygonSimple.add(37.591202*1000000D,55.913594*1000000D);
        polygonSimple.add(37.69872*1000000D,55.894336*1000000D);
        polygonSimple.add(37.703052*1000000D,55.893412*1000000D);
        polygonSimple.add(37.707139*1000000D,55.892303*1000000D);
        polygonSimple.add(37.731555*1000000D,55.885619*1000000D);
        polygonSimple.add(37.732099*1000000D,55.885316*1000000D);
        polygonSimple.add(37.829744*1000000D,55.830558*1000000D);
        polygonSimple.add(37.830139*1000000D,55.830302*1000000D);
        polygonSimple.add(37.83439*1000000D,55.825961*1000000D);
        polygonSimple.add(37.836049*1000000D,55.824223*1000000D);
        polygonSimple.add(37.843762*1000000D,55.814774*1000000D);
        polygonSimple.add(37.850678*1000000D,55.778563*1000000D);
        polygonSimple.add(37.864093*1000000D,55.693688*1000000D);
        polygonSimple.add(37.864745*1000000D,55.688264*1000000D);
        polygonSimple.add(37.864792*1000000D,55.68735*1000000D);
        polygonSimple.add(37.858984*1000000D,55.677163*1000000D);
        polygonSimple.add(37.841266*1000000D,55.655347*1000000D);
        polygonSimple.add(37.84114*1000000D,55.655192*1000000D);
        polygonSimple.add(37.824012*1000000D,55.640091*1000000D);
        polygonSimple.add(37.823511*1000000D,55.639675*1000000D);
        polygonSimple.add(37.786913*1000000D,55.616602*1000000D);
        polygonSimple.add(37.732173*1000000D,55.588797*1000000D);
        polygonSimple.add(37.690925*1000000D,55.572319*1000000D);
        polygonSimple.add(37.602287*1000000D,55.566373*1000000D);

        return polygonSimple;
    }
}