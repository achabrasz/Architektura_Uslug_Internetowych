package com.example.Sport_Module.DataInitalizer;

import com.example.Sport_Module.Service.SportService;
import com.example.Sport_Module.Sport.Sport;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer {
    private final SportService sportService;

    @Autowired
    public DataInitializer(SportService sportService) {
        this.sportService = sportService;
    }

    @PostConstruct
    public void init() {
        // Football
        UUID footballId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID basketballId = UUID.fromString("123e4567-e89b-12d3-a456-426614174001");
        UUID tennisId = UUID.fromString("123e4567-e89b-12d3-a456-426614174002");
        UUID iceHockeyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174003");
        UUID swimmingId = UUID.fromString("123e4567-e89b-12d3-a456-426614174004");
        Sport football = new Sport(footballId, "Football");
        sportService.save(football);

        // Basketball
        Sport basketball = new Sport(basketballId, "Basketball");
        sportService.save(basketball);

        // Tennis
        Sport tennis = new Sport(tennisId, "Tennis");
        sportService.save(tennis);

        // Ice Hockey
        Sport iceHockey = new Sport(iceHockeyId, "Ice Hockey");
        sportService.save(iceHockey);

        // Swimming
        Sport swimming = new Sport(swimmingId, "Swimming");
        sportService.save(swimming);
    }
}
