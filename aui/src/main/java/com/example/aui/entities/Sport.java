package com.example.aui.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sport {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "sport", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Sportsman> sportsmen;

    @Override
    public String toString() {
        return name + " (" + sportsmen.size() + " sportsmen)";
    }

    public void listSportsmen() {
        System.out.println(name);
        sportsmen.forEach(sportsmen -> System.out.println("- " + sportsmen));
    }
}

