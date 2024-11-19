package com.example.Sport_Module.Repository;

import com.example.Sport_Module.Sport.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SportRepository extends JpaRepository<Sport, UUID> {
    Sport findByName(String name);
}
