package com.gis_team.mushroom_locations.repository;

import com.gis_team.mushroom_locations.model.MushroomLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MushroomLocationRepository extends JpaRepository <MushroomLocation, Integer> {

    List<MushroomLocation> findByName(String name);

}
