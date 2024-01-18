package ru.kai.opendataproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kai.opendataproject.model.ConvexPoint;

@Repository
public interface BoundsRepository extends CrudRepository<ConvexPoint, Long> {
}
