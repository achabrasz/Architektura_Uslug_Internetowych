package com.example.Sportsman_Module.Sportsman;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Entity
@Table(name = "sportsmen")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"id", "sportId"})
public class Sportsman {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private int rating;

    @Column(name = "sportId")
    private UUID sportId;

    public String getSportName(RestTemplate restTemplate) {
        /*
        var sport = restTemplate.getForObject("http://localhost:8083/sports/" + sportId, Sport.class);
        if (sport == null) {
            System.out.println("Sport with id " + sportId + " not found");
            restTemplate.delete("http://localhost:8082/sportsmen/sport/" + sportId);
            return null;
        }*/
        return restTemplate.getForObject("http://localhost:8083/sports/" + sportId, String.class);
    }
}

