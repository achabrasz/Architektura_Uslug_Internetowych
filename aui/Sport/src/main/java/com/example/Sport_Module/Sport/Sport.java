package com.example.Sport_Module.Sport;

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
@Table(name = "sports")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
public class Sport {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;
}

