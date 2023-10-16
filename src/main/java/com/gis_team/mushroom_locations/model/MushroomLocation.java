package com.gis_team.mushroom_locations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geolatte.geom.Geometry;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MushroomLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "coordinates", columnDefinition = "geometry")
    private Geometry coordinates;
    private String description;
}
