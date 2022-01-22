package com.example.mspersonnel;

import com.example.mspersonnel.dao.PersonneRepo;
import com.example.mspersonnel.entites.*;
import com.example.mspersonnel.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MSpersonnelApplication implements CommandLineRunner {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    String ddl;

    @Autowired private PersonneRepo personneRepo;
    @Autowired private PersonneService personneService;

    public static void main(String[] args) {
        SpringApplication.run(MSpersonnelApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {

        if ("create".equals(ddl)) {
            Admin admin = new Admin();
            admin.setId(null);
            admin.setNom("ADMIN");
            admin.setPrenom("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setNumTel("0000000000");
            admin.setStatusCompte(true);
            admin.setNomUtilisateur("admin");
            admin.setMotDePasse(passwordEncoder().encode("admin"));
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
            professeur.setMotDePasse(passwordEncoder().encode("professeur"));
            professeur.setDateInscription(new Date());
            professeur.setRole(Role.PROFESSEUR);
            personneRepo.save(professeur);

            Etudiant etudiant = new Etudiant();
            etudiant.setId(null);
            etudiant.setCin("I 706269");
            etudiant.setNom("ETUDIANT");
            etudiant.setPrenom("Etudiant");
            etudiant.setEmail("etudiant@gmail.com");
            etudiant.setNumTel("777777777777");
            etudiant.setStatusCompte(true);
            etudiant.setNomUtilisateur("etudiant");
            etudiant.setMotDePasse(passwordEncoder().encode("etudiant"));
            etudiant.setDateInscription(new Date());
            etudiant.setRole(Role.ETUDIANT);
            personneRepo.save(etudiant);
        }
    }
}
