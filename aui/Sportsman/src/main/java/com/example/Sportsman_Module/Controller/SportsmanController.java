package com.example.Sportsman_Module.Controller;

import com.example.Sportsman_Module.Service.SportsmanService;
import com.example.Sportsman_Module.Sportsman.Dto.SportsmanDto;
import com.example.Sportsman_Module.Sportsman.Sport.Sport;
import com.example.Sportsman_Module.Sportsman.Sportsman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sportsmen")
public class SportsmanController {

    private final SportsmanService sportsmanService;
    private final RestTemplate restTemplate;

    @Autowired
    public SportsmanController(SportsmanService sportsmanService, RestTemplate restTemplate) {
        this.sportsmanService = sportsmanService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{sportId}")
    public ResponseEntity<List<SportsmanDto>> getSportsmenBySport(@PathVariable UUID sportId) {
        List <Sportsman> sportsmen = sportsmanService.findBySportId(sportId);
        return ResponseEntity.ok(
                sportsmen.stream()
                        .map(sportsman -> new SportsmanDto(sportsman.getName(), sportsman.getRating(), sportsman.getSportName(restTemplate)))
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/{sportId}")
    public ResponseEntity<SportsmanDto> addSportsmanToSport(
            @PathVariable UUID sportId,
            @RequestBody SportsmanDto SportsmanDTO) {
        var sport = restTemplate.getForObject("http://localhost:8083/sports/" + sportId, Sport.class);
        if (sport == null) {
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

