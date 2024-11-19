package com.example.aui.entities.DTO;

public class SportsmanDto {
    private String name;
    private int rating;

    private String sportName;

    public SportsmanDto(String name, int rating, String sportName) {
        this.name = name;
        this.rating = rating;
        this.sportName = sportName;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getSportName() {
        return sportName;
    }
}
