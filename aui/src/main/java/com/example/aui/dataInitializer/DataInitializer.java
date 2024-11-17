package com.example.aui.dataInitializer;

import com.example.aui.entities.Sport;
import com.example.aui.entities.Sportsman;
import com.example.aui.services.SportService;
import com.example.aui.services.SportsmanService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class DataInitializer {
    private final SportService sportService;
    private final SportsmanService sportsmanService;

    @Autowired
    public DataInitializer(SportService sportService, SportsmanService sportsmanService) {
        this.sportService = sportService;
        this.sportsmanService = sportsmanService;
    }

    @PostConstruct
    public void init() {
        // Football
        Sport football = new Sport(UUID.randomUUID(), "Football", new ArrayList<>());
        sportService.save(football);

        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Cristiano Ronaldo", 95, football));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Robert Lewandowski", 94, football));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Leo Messi", 94, football));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Neymar", 91, football));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Kylian Mbappe", 92, football));

        // Basketball
        Sport basketball = new Sport(UUID.randomUUID(), "Basketball", new ArrayList<>());
        sportService.save(basketball);

        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Lebron James", 95, basketball));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Steph Curry", 94, basketball));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Michael Jordan", 94, basketball));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Kobe Bryant", 93, basketball));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Shaquille O'Neal", 91, basketball));

        // Tennis
        Sport tennis = new Sport(UUID.randomUUID(), "Tennis", new ArrayList<>());
        sportService.save(tennis);

        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Roger Federer", 96, tennis));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Rafael Nadal", 95, tennis));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Novak Djokovic", 97, tennis));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Andy Murray", 90, tennis));

        // Ice Hockey
        Sport iceHockey = new Sport(UUID.randomUUID(), "Ice Hockey", new ArrayList<>());
        sportService.save(iceHockey);

        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Wayne Gretzky", 99, iceHockey));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Sidney Crosby", 96, iceHockey));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Alexander Ovechkin", 95, iceHockey));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Mario Lemieux", 97, iceHockey));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Patrick Roy", 93, iceHockey));

        // Swimming
        Sport swimming = new Sport(UUID.randomUUID(), "Swimming", new ArrayList<>());
        sportService.save(swimming);

        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Michael Phelps", 99, swimming));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Ryan Lochte", 93, swimming));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Caeleb Dressel", 94, swimming));
        sportsmanService.save(new Sportsman(UUID.randomUUID(), "Katie Ledecky", 95, swimming));
    }
}
