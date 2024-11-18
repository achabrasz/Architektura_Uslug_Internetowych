package com.example.aui.repositories;

import com.example.aui.entities.Sportsman;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SportsmanRepository extends JpaRepository<Sportsman, UUID> {
    Sportsman findByName(String name);
}
