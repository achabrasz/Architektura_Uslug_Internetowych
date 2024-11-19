package com.example.Sportsman_Module.Sportsman;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "sportsmen")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"id", "sport_id"})
public class Sportsman {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private int rating;

    @Column(name = "sport_id")
    private UUID sport_id;
}

