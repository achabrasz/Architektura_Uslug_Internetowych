package com.example.Sport_Module.Controller;

import com.example.Sport_Module.Service.SportService;
import com.example.Sport_Module.Sport.Dto.SportDto;
import com.example.Sport_Module.Sport.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sports")
public class SportController {

    private final SportService sportService;

    @Autowired
    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping
    public List<SportDto> getAllSports() {
        return sportService.findAll().stream()
                .map(sport -> new SportDto(sport.getName()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<SportDto> createSport(@RequestBody SportDto SportDTO) {
        Sport sport = sportService.save(new Sport(UUID.randomUUID(), SportDTO.getName()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new SportDto(sport.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportDto> getSportById(@PathVariable UUID id) {
        Sport sport = sportService.findById(id);
        if (sport == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new SportDto(sport.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SportDto> updateSport(@PathVariable UUID id, @RequestBody SportDto SportDTO) {
        Sport sport = sportService.findById(id);
        if (sport == null) {
            return ResponseEntity.notFound().build();
        }
        sport.setName(SportDTO.getName());
        sportService.save(sport);
        return ResponseEntity.ok(new SportDto(sport.getName()));
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
