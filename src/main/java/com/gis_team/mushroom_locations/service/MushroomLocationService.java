package com.gis_team.mushroom_locations.service;

import com.gis_team.mushroom_locations.model.MushroomLocation;
import com.gis_team.mushroom_locations.repository.MushroomLocationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MushroomLocationService {

    @Autowired
    private MushroomLocationRepository mushroomLocationRepository;

    public List<MushroomLocation> getAllLocations() {
        return mushroomLocationRepository.findAll();
    }

    public Optional<MushroomLocation> getLocationById(Integer id) {
       return mushroomLocationRepository.findById(id);
    }

    public List<MushroomLocation> getLocationByName(String name) {
        return mushroomLocationRepository.findByName(name);
    }

    public MushroomLocation addLocation(MushroomLocation location) {
        return mushroomLocationRepository.save(location);
    }

    public MushroomLocation updateLocation(MushroomLocation location) {
        if (mushroomLocationRepository.existsById(location.getId())) {
            return mushroomLocationRepository.save(location);
        } else {
            throw new EntityNotFoundException(" MushroomLocation with ID " + location.getId() + " not found.");
        }
    }

    public void deleteLocation(Integer id) {
        mushroomLocationRepository.deleteById(id);
    }
}
