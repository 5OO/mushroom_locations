package com.gis_team.mushroom_locations.repository;

import com.gis_team.mushroom_locations.model.MushroomLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MushroomLocationRepository extends JpaRepository <MushroomLocation, Long> {

}
