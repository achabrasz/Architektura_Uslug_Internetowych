package com.example.Sport_Module.Controller;

import com.example.Sport_Module.Service.SportService;
import com.example.Sport_Module.Sport.Dto.SportDto;
import com.example.Sport_Module.Sport.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/sports")
public class SportController {

    private final SportService sportService;
    private final RestTemplate restTemplate;

    @Autowired
    public SportController(SportService sportService, RestTemplate restTemplate) {
        this.sportService = sportService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<Sport> getAllSports() {
        return new ArrayList<>(sportService.findAll());
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
            System.out.println("Sport not found");
            return ResponseEntity.notFound().build();
        }
        System.out.println("Deleting sportsmen for sport " + id);
        restTemplate.delete("http://localhost:8083/sportsmen/sport/" + id);
        sportService.delete(sport); // Ensure cascade delete for sportsmen
        System.out.println("Deleted sportsmen for sport " + id);
        return ResponseEntity.noContent().build();
    }
}

