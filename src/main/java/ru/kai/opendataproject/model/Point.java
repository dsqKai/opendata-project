package ru.kai.opendataproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Point {
    @Id
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "name")
    private String address;
    @Column(name="x_coord")
    private Double xCoord;
    @Column(name="y_coord")
    private Double yCoord;
    @Column(name="cluster_id")
    private String clusterId;
    @Column(name="mark_level")
    private Integer markLevel;
}
