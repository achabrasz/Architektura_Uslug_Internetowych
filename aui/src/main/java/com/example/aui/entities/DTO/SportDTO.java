package com.example.aui.entities.DTO;

public class SportDTO {
    private String name;
    private int rating;

    public SportDTO(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }
}
