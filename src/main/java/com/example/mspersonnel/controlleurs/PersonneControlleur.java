package com.example.mspersonnel.controlleurs;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.mspersonnel.dao.PersonneRepo;
import com.example.mspersonnel.entites.Personne;
import com.example.mspersonnel.services.PersonneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class PersonneControlleur {

    @Autowired private PersonneService personneService;
    @Autowired private PersonneRepo personneRepo;

    @PostMapping("/personnes")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Personne save(@RequestBody Personne personne) {
        return personneService.ajouter(personne);
    }

    @GetMapping("/personnes/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Personne getPerso(@PathVariable Long id) {
        return personneService.persById(id);
    }

    @GetMapping("/personnes")
    //@PostAuthorize("hasAuthority('PROFESSEUR')")
    @PostAuthorize("hasAuthority('ADMIN')")
    public List<Personne> getAllPersonnel() {
        return personneService.allPerson();
    }

    @DeleteMapping("/personnes/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void deletePersonne(@PathVariable Long id) {
        personneService.supprimer(id);
    }
    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String authToken = request.getHeader("Authorization");
        if ( authToken != null && authToken.startsWith("Bearer ")){
            try {
                String jwt = authToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("mySecret123");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
                Personne personne = personneRepo.findPersonneByNomUtilisateur(username);
                String jwtAccessToken = JWT.create()
                        .withSubject(personne.getNomUtilisateur())
                        .withExpiresAt(new Date(System.currentTimeMillis()+2*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role", Arrays.asList(personne.getRole().toString()))
                        .sign(algorithm);
                Map<String,String> idToken = new HashMap<>();
                idToken.put("access-token",jwtAccessToken);
                idToken.put("refresh-token",jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
                System.out.println("access-token "+jwtAccessToken);
                System.out.println("refresh-token "+jwt);
            }
            catch (Exception e){
                throw e;
            }
        }
        else {
            throw new RuntimeException("Refresh Token Required !!!");
        }
    }
}
