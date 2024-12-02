package com.example.Sportsman_Module.DataInitalizer;

import com.example.Sportsman_Module.Service.SportsmanService;
import com.example.Sportsman_Module.Sportsman.Sportsman;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer {
    private final SportsmanService sportsmanService;

    @Autowired
    public DataInitializer(SportsmanService sportsmanService) {
        this.sportsmanService = sportsmanService;
    }

    @PostConstruct
    public void init() {
        // Football
        UUID footballId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID basketballId = UUID.fromString("123e4567-e89b-12d3-a456-426614174001");
        UUID tennisId = UUID.fromString("123e4567-e89b-12d3-a456-426614174002");
        UUID iceHockeyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174003");
        UUID swimmingId = UUID.fromString("123e4567-e89b-12d3-a456-426614174004");

        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Cristiano Ronaldo", 95, footballId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Robert Lewandowski", 94, footballId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Leo Messi", 94, footballId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Neymar", 91, footballId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Kylian Mbappe", 92, footballId));

        // Basketball

        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Lebron James", 95, basketballId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Steph Curry", 94, basketballId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Michael Jordan", 94, basketballId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Kobe Bryant", 93, basketballId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Shaquille O'Neal", 91, basketballId));

        // Tennis

        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Roger Federer", 96, tennisId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Rafael Nadal", 95, tennisId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Novak Djokovic", 97, tennisId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Andy Murray", 90, tennisId));

        // Ice Hockey

        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Wayne Gretzky", 99, iceHockeyId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Sidney Crosby", 96, iceHockeyId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Alexander Ovechkin", 95, iceHockeyId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Mario Lemieux", 97, iceHockeyId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Patrick Roy", 93, iceHockeyId));

        // Swimming

        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Michael Phelps", 99, swimmingId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Ryan Lochte", 93, swimmingId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Caeleb Dressel", 94, swimmingId));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Katie Ledecky", 95, swimmingId));
    }
}
