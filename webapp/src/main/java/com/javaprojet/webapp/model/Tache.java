package com.javaprojet.webapp.model;

import jakarta.persistence.*;

@Entity
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "programmeurs_id", nullable = false)
    private Programmeur programmeur;

    @ManyToOne
    @JoinColumn(name = "responsables_id", nullable = false)
    private Responsable responsable;

    public Tache() {
    }

    public Tache(String description, Programmeur programmeur, Responsable responsable) {
        this.description = description;
        this.programmeur = programmeur;
        this.responsable = responsable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Programmeur getProgrammeur() {
        return programmeur;
    }

    public void setProgrammeur(Programmeur programmeur) {
        this.programmeur = programmeur;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
}
