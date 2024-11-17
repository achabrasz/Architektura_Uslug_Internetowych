package com.example.aui.repositories;

import com.example.aui.entities.Sportsman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SportsmanRepository extends JpaRepository<Sportsman, UUID> {
    List<Sportsman> findBySport_Name(String sportName);
    void deleteBySport_Name(String sportName);

    void deleteByName(String name);
}
