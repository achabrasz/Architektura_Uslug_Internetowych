package com.example.aui.controllers;

import com.example.aui.entities.DTO.SportDTO;
import com.example.aui.entities.Sport;
import com.example.aui.services.SportService;
import com.example.aui.services.SportsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sports")
public class SportController {

    private final SportService sportService;
    private final SportsmanService sportsmanService;

    @Autowired
    public SportController(SportService sportService, SportsmanService sportsmanService) {
        this.sportService = sportService;
        this.sportsmanService = sportsmanService;
    }

    @GetMapping
    public List<SportDTO> getAllSports() {
        return sportService.findAll().stream()
                .map(sport -> new SportDTO(sport.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportDTO> getSportById(@PathVariable UUID id) {
        Sport sport = sportService.findById(id);
        if (sport == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new SportDTO(sport.getName()));
    }

    @PostMapping
    public ResponseEntity<SportDTO> createSport(@RequestBody SportDTO SportDTO) {
        Sport sport = sportService.save(new Sport(UUID.randomUUID(), SportDTO.getName(), new ArrayList<>()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new SportDTO(sport.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SportDTO> updateSport(@PathVariable UUID id, @RequestBody SportDTO SportDTO) {
        Sport sport = sportService.findById(id);
        if (sport == null) {
            return ResponseEntity.notFound().build();
        }
        sport.setName(SportDTO.getName());
        sportService.save(sport);
        return ResponseEntity.ok(new SportDTO(sport.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSport(@PathVariable UUID id) {
        Sport sport = sportService.findById(id);
        if (sport == null) {
            return ResponseEntity.notFound().build();
        }
        sportService.delete(sport); // Ensure cascade delete for sportsmen
        return ResponseEntity.noContent().build();
    }
}

