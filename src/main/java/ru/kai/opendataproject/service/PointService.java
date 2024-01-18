package ru.kai.opendataproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kai.opendataproject.model.Point;
import ru.kai.opendataproject.repository.PointRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    public List<Point> getAllPoints() {
        return (List<Point>) pointRepository.findAll();
    }

    public void addPoint(Point point) {
        pointRepository.save(point);
    }
}
