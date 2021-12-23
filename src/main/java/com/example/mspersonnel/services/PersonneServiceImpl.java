package com.example.mspersonnel.services;

import com.example.mspersonnel.dao.PersonneRepo;
import com.example.mspersonnel.entites.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonneServiceImpl implements PersonneService{

    @Autowired
    private PersonneRepo personneRepo;

    @Override
    public Personne ajouter(Personne personne) {
        return personneRepo.save(personne);
    }

    @Override
    public Personne persById(Long id) {
        return personneRepo.findById(id).get();
    }

    @Override
    public List<Personne> allPerson() {
        return personneRepo.findAll();
    }

    @Override
    public void supprimer(Long id) {
        personneRepo.deleteById(id
        );
    }
}
