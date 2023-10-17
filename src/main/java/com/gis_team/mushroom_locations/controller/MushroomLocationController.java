package com.gis_team.mushroom_locations.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gis_team.mushroom_locations.model.MushroomLocation;
import com.gis_team.mushroom_locations.service.MushroomLocationService;
import org.geolatte.geom.json.GeolatteGeomModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public ResponseEntity<String> getLocationById(@PathVariable Integer id) {
        Optional<MushroomLocation> mushroomLocationOptional = locationService.getLocationById(id);
        if (!mushroomLocationOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        MushroomLocation location = mushroomLocationOptional.get();
        String geoJsonOutput = convertToGeoJson(location);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/geo+json")).body(geoJsonOutput);
    }

    private String convertToGeoJson(MushroomLocation location) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new GeolatteGeomModule());

        try {
            return mapper.writeValueAsString(location.getCoordinates());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Geometry to GeoJSON ",e);
        }
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
