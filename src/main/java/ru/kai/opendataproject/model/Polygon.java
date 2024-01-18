package ru.kai.opendataproject.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.kai.opendataproject.dto.LightPoint;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class Polygon {
    private final String uuid;
    private final String clusterId;
    private final Integer level;
    private ArrayList<LightPoint> points = new ArrayList<>();
    public void add(Point point) {
        points.add(new LightPoint(point.getXCoord(), point.getYCoord()));
    }
    public void add(LightPoint point) {
        points.add(point);
    }
}
