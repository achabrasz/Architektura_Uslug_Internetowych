package com.example.aui.services;

import com.example.aui.entities.Sport;
import com.example.aui.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SportService {
    private final SportRepository sportRepository;
    private final SportsmanService sportsmanService;

    @Autowired
    public SportService(SportRepository sportRepository, SportsmanService sportsmanService) {
        this.sportRepository = sportRepository;
        this.sportsmanService = sportsmanService;
    }

    public List<Sport> findAll() {
        return sportRepository.findAll();
    }

    public Sport findByName(String name) {
        return sportRepository.findByName(name);
    }

    public void deleteAll() {
        sportRepository.deleteAll();
    }

    public Sport save(Sport sport) {
        return sportRepository.save(sport);
    }

    public void delete(Sport sport) {
        sportRepository.delete(sport);
    }

    public Sport findById(UUID id) {
        return sportRepository.findById(id).orElse(null);
    }
}

