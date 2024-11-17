package com.example.aui.services;

import com.example.aui.entities.Sportsman;
import com.example.aui.repositories.SportsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportsmanService {
    private final SportsmanRepository sportsmanRepository;

    @Autowired
    public SportsmanService(SportsmanRepository sportsmanRepository) {
        this.sportsmanRepository = sportsmanRepository;
    }

    public List<Sportsman> findAll() {
        return sportsmanRepository.findAll();
    }

    public Sportsman save(Sportsman sportsman) {
        return sportsmanRepository.save(sportsman);
    }

    public void deleteAll() {
        sportsmanRepository.deleteAll();
    }

    public void deleteByName(String name) {
        sportsmanRepository.deleteByName(name);
    }

    public void deleteBySportName(String sportName) {
        sportsmanRepository.deleteBySport_Name(sportName);
    }
}

