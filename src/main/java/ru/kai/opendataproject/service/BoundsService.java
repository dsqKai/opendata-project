package ru.kai.opendataproject.service;

import kn.uni.voronoitreemap.j2d.PolygonSimple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kai.opendataproject.model.ConvexPoint;
import ru.kai.opendataproject.repository.BoundsRepository;

@Service
@RequiredArgsConstructor
public class BoundsService {
    private final BoundsRepository repository;
    public PolygonSimple getBounds() {
        PolygonSimple polygonSimple = new PolygonSimple();
        for(ConvexPoint point: repository.findAll()) {
            polygonSimple.add(point.getX()*1000000D, point.getY()*1000000D);
        }
        return polygonSimple;
    }
}
