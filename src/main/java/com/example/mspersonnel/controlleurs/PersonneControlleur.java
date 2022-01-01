package com.example.mspersonnel.controlleurs;

import com.example.mspersonnel.entites.Personne;
import com.example.mspersonnel.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnes")
public class PersonneControlleur {

    @Autowired
    private PersonneService personneService;

    @PostMapping("/")
    public Personne save(@RequestBody Personne personne) {
        return personneService.ajouter(personne);
    }

    @GetMapping("/{id}")
    public Personne getPerso(@PathVariable Long id) {
        return personneService.persById(id);
    }

    @GetMapping("")
    public List<Personne> getAllPersonnel() {
        return personneService.allPerson();
    }

    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable Long id) {
        personneService.supprimer(id);

    }
}
