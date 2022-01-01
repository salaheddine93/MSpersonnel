package com.example.mspersonnel.entites;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Etudiant extends Personne{
    private String cin;
}
