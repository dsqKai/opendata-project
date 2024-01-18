package ru.kai.opendataproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="bounds")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConvexPoint {
    private double x;
    private double y;
    @Id
    @GeneratedValue
    private Long id;
}
