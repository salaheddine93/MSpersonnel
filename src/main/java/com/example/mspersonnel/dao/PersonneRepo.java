package com.example.mspersonnel.dao;

import com.example.mspersonnel.entites.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepo extends JpaRepository<Personne,Long> {
}
