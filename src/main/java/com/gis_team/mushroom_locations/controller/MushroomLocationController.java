package com.gis_team.mushroom_locations.controller;

import com.gis_team.mushroom_locations.model.MushroomLocation;
import com.gis_team.mushroom_locations.service.MushroomLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<MushroomLocation> addLocation(@RequestBody MushroomLocation location) {
        return ResponseEntity.ok(locationService.addLocation(location));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MushroomLocation> updateLocation(@PathVariable Integer id, @RequestBody MushroomLocation location) {
        location.setId(id);
        return ResponseEntity.ok(locationService.updateLocation(location));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Integer id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
