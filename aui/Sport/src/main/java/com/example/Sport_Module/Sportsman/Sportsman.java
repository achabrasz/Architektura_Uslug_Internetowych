package com.example.Sport_Module.Sportsman;

import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class Sportsman {
    private UUID id;
    private String name;
    private int rating;
    private UUID sportId;

    public Sportsman(UUID id, String name, int rating, UUID sportId) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.sportId = sportId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public UUID getSportId() {
        return sportId;
    }

    public String getSportName(RestTemplate restTemplate) {
        return restTemplate.getForObject("http://localhost:8083/sports/" + sportId, String.class);
    }
}
