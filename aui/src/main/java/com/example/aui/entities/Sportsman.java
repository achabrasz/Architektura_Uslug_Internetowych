package com.example.aui.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "sportsmen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sportsman {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @Override
    public String toString() {
        return name + " (" + rating + ")" + " " + sport.getName();
    }
}

