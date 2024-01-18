package ru.kai.opendataproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kai.opendataproject.model.Point;

@Repository
public interface PointRepository extends CrudRepository<Point, String> {

}
