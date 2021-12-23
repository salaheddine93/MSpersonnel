package com.example.mspersonnel;

import com.example.mspersonnel.dao.PersonneRepo;
import com.example.mspersonnel.entites.Admin;
import com.example.mspersonnel.entites.Personne;
import com.example.mspersonnel.entites.Professeur;
import com.example.mspersonnel.entites.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class MSpersonnelApplication implements CommandLineRunner {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    String ddl;

    @Autowired private PersonneRepo personneRepo;

    public static void main(String[] args) {
        SpringApplication.run(MSpersonnelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Admin admin = new Admin();
        admin.setId(null);
        admin.setNom("ADMIN");
        admin.setPrenom("Admin");
        admin.setEmail("admin@gmail.com");
        admin.setNumTel("0000000000");
        admin.setStatusCompte(true);
        admin.setNomUtilisateur("admin");
        admin.setMotDePasse("admin");
        admin.setDateInscription(new Date());
        admin.setRole(Role.ADMIN);
        personneRepo.save(admin);

        Professeur professeur = new Professeur();
        professeur.setId(null);
        professeur.setNom("PROFESSEUR");
        professeur.setPrenom("Professeur");
        professeur.setEmail("professeur@gmail.com");
        professeur.setNumTel("1111111111");
        professeur.setStatusCompte(true);
        professeur.setNomUtilisateur("professeur");
        professeur.setMotDePasse("professeur");
        professeur.setDateInscription(new Date());
        professeur.setRole(Role.PROFESSEUR);
        personneRepo.save(professeur);
    }
}
