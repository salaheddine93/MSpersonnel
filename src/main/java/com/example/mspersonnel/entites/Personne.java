package com.example.mspersonnel.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
 
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personne {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String numTel;
    private Boolean statusCompte;
    private String motDePasse;
    private String nomUtilisateur;
    private Date dateInscription;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
