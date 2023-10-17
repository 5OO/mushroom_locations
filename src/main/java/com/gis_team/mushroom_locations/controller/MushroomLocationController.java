package com.gis_team.mushroom_locations.controller;

import com.gis_team.mushroom_locations.model.MushroomLocation;
import com.gis_team.mushroom_locations.service.MushroomLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mushroom-locations")
public class MushroomLocationController {

    @Autowired
    private MushroomLocationService locationService;

    @GetMapping
    public ResponseEntity<List<MushroomLocation>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MushroomLocation> getLocationById(@PathVariable Integer id) {
        Optional<MushroomLocation> mushroomLocationOptional = locationService.getLocationById(id);
        if (!mushroomLocationOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mushroomLocationOptional.get());
    }

}
