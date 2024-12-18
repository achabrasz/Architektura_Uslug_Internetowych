package com.example.aui.controllers;

import com.example.aui.entities.DTO.SportsmanDTO;
import com.example.aui.entities.Sport;
import com.example.aui.entities.Sportsman;
import com.example.aui.services.SportService;
import com.example.aui.services.SportsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sports")
public class SportsmanController {

    private final SportsmanService sportsmanService;
    private final SportService sportService;

    @Autowired
    public SportsmanController(SportsmanService sportsmanService, SportService sportService) {
        this.sportsmanService = sportsmanService;
        this.sportService = sportService;
    }

    @GetMapping("/{sportId}/sportsmen")
    public ResponseEntity<List<SportsmanDTO>> getSportsmenBySport(@PathVariable UUID sportId) {
        Sport sport = sportService.findById(sportId);
        if (sport == null) {
            return ResponseEntity.notFound().build();
        }
        if (sport.getSportsmen().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(
                sport.getSportsmen().stream()
                        .map(sportsman -> new SportsmanDTO(sportsman.getName(), sportsman.getRating(), sport.getName()))
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/{sportId}/sportsmen")
    public ResponseEntity<SportsmanDTO> addSportsmanToSport(
            @PathVariable UUID sportId,
            @RequestBody SportsmanDTO SportsmanDTO) {
        Sport sport = sportService.findById(sportId);
        if (sport == null) {
            return ResponseEntity.badRequest().build(); // Category does not exist
        }
        Sportsman sportsman = new Sportsman(UUID.randomUUID(), SportsmanDTO.getName(), SportsmanDTO.getRating(), sport);
        sport.getSportsmen().add(sportsman);
        sportsmanService.save(sportsman);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SportsmanDTO(sportsman.getName(), sportsman.getRating(), sport.getName()));
    }

    @GetMapping("/sportsmen")
    public List<SportsmanDTO> getAllSportsmen() {
        return sportsmanService.findAll().stream()
                .map(sportsman -> new SportsmanDTO(sportsman.getName(), sportsman.getRating(), sportsman.getSport().getName()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/sportsmen/{id}")
    public ResponseEntity<Void> deleteSportsman(@PathVariable UUID id) {
        Sportsman sportsman = sportsmanService.findById(id);
        sportsmanService.delete(sportsman);
        return ResponseEntity.noContent().build();
    }
}

