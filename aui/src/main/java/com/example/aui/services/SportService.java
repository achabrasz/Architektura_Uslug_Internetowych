package com.example.aui.services;

import com.example.aui.entities.Sport;
import com.example.aui.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteByName(String name) {
        sportsmanService.deleteBySportName(name);
        sportRepository.deleteByName(name);
    }

    public void deleteAll() {
        sportRepository.deleteAll();
    }

    public Sport save(Sport sport) {
        return sportRepository.save(sport);
    }
}
