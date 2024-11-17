package com.example.aui.repositories;

import com.example.aui.entities.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SportRepository extends JpaRepository<Sport, UUID> {
    Sport findByName(String name);

    void deleteByName(String name);
}
