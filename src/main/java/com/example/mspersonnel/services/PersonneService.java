package com.example.mspersonnel.services;

import com.example.mspersonnel.entites.Personne;

import java.util.List;

public interface PersonneService {
    Personne ajouter(Personne personne);
    Personne persById(Long id);
    List<Personne> allPerson();
    void supprimer(Long id);
}
