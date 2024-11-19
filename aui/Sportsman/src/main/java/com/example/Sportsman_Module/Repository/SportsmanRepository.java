package com.example.Sportsman_Module.Repository;

import com.example.Sportsman_Module.Sportsman.Sportsman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SportsmanRepository extends JpaRepository<Sportsman, UUID> {
    Sportsman findByName(String name);
}
