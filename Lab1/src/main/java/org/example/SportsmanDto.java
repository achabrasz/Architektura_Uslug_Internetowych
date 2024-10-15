package org.example;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@Builder
class SportsmanDto implements Serializable {
    private String name;
    private int rating;
    private String sport;

    @Override
    public String toString() {
        return "DTO Sportsman: " + name + ", Rating: " + rating + ", Sport: " + sport;
    }
}
