package com.javaprojet.webapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Programmeurs")
public class Programmeur {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    @Column(nullable = false, columnDefinition = "integer default 1930")
    private int annee_naissance;
    @Getter
    @Setter
    private double salaire;
    @Getter
    @Setter
    private double prime;
}
