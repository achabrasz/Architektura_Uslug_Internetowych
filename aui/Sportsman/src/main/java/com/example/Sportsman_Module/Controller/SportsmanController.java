package com.example.Sportsman_Module.Controller;

import com.example.Sportsman_Module.Service.SportsmanService;
import com.example.Sportsman_Module.Sportsman.Dto.SportsmanDto;
import com.example.Sportsman_Module.Sportsman.Sportsman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/sportsmen")
public class SportsmanController {

    private final SportsmanService sportsmanService;
    private final RestTemplate restTemplate;

    @Autowired
    public SportsmanController(SportsmanService sportsmanService, RestTemplate restTemplate) {
        this.sportsmanService = sportsmanService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sportsman> getSportsmanById(@PathVariable UUID id) {
        Sportsman sportsman = sportsmanService.findById(id);
        if (sportsman == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sportsman);
    }

    @GetMapping("/sport/{sportId}")
    public ResponseEntity<List<Sportsman>> getSportsmenBySport(@PathVariable UUID sportId) {
        List <Sportsman> sportsmen = sportsmanService.findBySportId(sportId);
        return ResponseEntity.ok(
                new ArrayList<>(sportsmen)
        );
    }

    @PostMapping("/{sportId}")
    public ResponseEntity<SportsmanDto> addSportsmanToSport(
            @PathVariable UUID sportId,
            @RequestBody SportsmanDto SportsmanDTO) {
        System.out.println("Trying to add sportsmen to a sport");
        String sport = restTemplate.getForObject("http://localhost:8083/sports/" + sportId, String.class);
        if (sport == null) {
            System.out.println("Sport not found");
            return ResponseEntity.badRequest().build(); // Category does not exist
        }
        Sportsman sportsman = new Sportsman(UUID.randomUUID(), SportsmanDTO.getName(), SportsmanDTO.getRating(), sportId);
        sportsmanService.save(sportsman);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SportsmanDto(sportsman.getName(), sportsman.getRating(), sportsman.getSportName(restTemplate)));
    }

    @GetMapping
    public List<SportsmanDto> getAllSportsmen() {
        return sportsmanService.findAll().stream()
                .map(sportsman -> new SportsmanDto(sportsman.getName(), sportsman.getRating(), sportsman.getSportName(restTemplate)))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sportsman> updateSportsman(@PathVariable UUID id, @RequestBody SportsmanDto SportsmanDTO) {
        Sportsman sportsman = sportsmanService.findById(id);
        if (sportsman == null) {
            return ResponseEntity.notFound().build();
        }
        sportsman.setName(SportsmanDTO.getName());
        sportsman.setRating(SportsmanDTO.getRating());
        sportsmanService.save(sportsman);
        return ResponseEntity.ok(sportsman);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSportsman(@PathVariable UUID id) {
        Sportsman sportsman = sportsmanService.findById(id);
        sportsmanService.delete(sportsman);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllSportsmen() {
        sportsmanService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/sport/{sportId}")
    public ResponseEntity<Void> deleteSportsmenBySport(@PathVariable UUID sportId) {
        System.out.println("Deleting sportsmen for sport " + sportId);
        sportsmanService.deleteAllBySportId(sportId);
        return ResponseEntity.noContent().build();
    }
}

