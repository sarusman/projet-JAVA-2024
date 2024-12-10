package com.javaprojet.webapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Programmeurs")
public class Programmeur {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String nom;
    @Getter
    @Setter
    private String prenom;
    @Getter
    @Setter
    private String adresse;
    @Getter
    @Setter
    private String pseudo;
    @Getter
    @Setter
    private String responsable;
    @Getter
    @Setter
    private String hobby;
    @Getter
    @Setter
    private int anneeNaissance;
    @Getter
    @Setter
    private double salaire;
    @Getter
    @Setter
    private double prime;

}
