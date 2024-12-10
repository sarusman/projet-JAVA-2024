package com.javaprojet.webapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Programmeurs")
@Data
public class Programmeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String nom;
    public String prenom;
    public String adresse;
    public String pseudo;
    public String responsable;
    public String hobby;
    @Column(nullable = false, columnDefinition = "integer default 1930")
    public int annee;
    public double salaire;
    public double prime;
}
