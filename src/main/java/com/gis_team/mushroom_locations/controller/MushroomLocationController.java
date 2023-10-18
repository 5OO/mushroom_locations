package com.gis_team.mushroom_locations.controller;
import com.gis_team.mushroom_locations.model.MushroomLocation;
import com.gis_team.mushroom_locations.service.MushroomLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.locationtech.jts.geom.Geometry;
import com.bedatadriven.jackson.datatype.jts.JtsModule;

@RestController
@RequestMapping("/api/mushroom-locations")
public class MushroomLocationController {

    @Autowired
    private MushroomLocationService locationService;
    private ObjectMapper geoJsonMapper = new ObjectMapper().registerModule(new JtsModule());

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
        Geometry locationCoordinates = location.getCoordinates();
        String geoJsonOutput = convertToGeoJson(locationCoordinates);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/geo+json"))
                .body(geoJsonOutput);
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

    private String convertToGeoJson(Geometry geometry) {
        try {
            String geoJsonString = geoJsonMapper.writeValueAsString(geometry);
            return geoJsonString;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting \"Geometry\" to \"GeoJSON\"", e);
        }
    }

    public Geometry convertFromGeoJson(String geoJsonString) {
        try {
            Geometry coordinates = geoJsonMapper.readValue(geoJsonString, Geometry.class);
            return coordinates;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting \"GeoJSON\" to \"Geometry\"", e);
        }
    }
}
