package com.example.aui.services;

import com.example.aui.entities.Sportsman;
import com.example.aui.repositories.SportsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public Sportsman findByName(String name) {
        return sportsmanRepository.findByName(name);
    }

    public void delete(Sportsman sportsman) {
        sportsmanRepository.delete(sportsman);
    }

    public Sportsman findById(UUID id) {
        return sportsmanRepository.findById(id).orElse(null);
    }

    public void delete(List<Sportsman> sportsmen) {
        sportsmanRepository.deleteAll(sportsmen);
    }
}

