package com.javaprojet.webapp.model;

import jakarta.persistence.*;

/**
 * Représente un responsable dans le système.
 *
 * Cette classe est une entité JPA mappée à la table "responsables" dans la base de données. Elle contient des informations
 * sur un responsable, telles que son prénom et son nom. Les annotations JPA sont utilisées pour la persistance des données.
 *
 * @author Sarusman
 */
@Entity
@Table(name = "responsables")
public class Responsable {

    /**
     * L'identifiant unique du responsable.
     *
     * Cet identifiant est généré automatiquement par la base de données grâce à la stratégie de génération d'identifiant
     * spécifiée par l'annotation {@link GeneratedValue}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Le prénom du responsable.
     */
    private String prenom;

    /**
     * Le nom du responsable.
     */
    private String nom;

    /**
     * Constructeur pour créer un responsable avec un prénom et un nom.
     *
     * @param prenom Le prénom du responsable.
     * @param nom Le nom du responsable.
     */
    public Responsable(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    /**
     * Constructeur par défaut.
     *
     * Ce constructeur est utilisé par JPA pour créer une instance de l'entité sans initialiser ses propriétés.
     */
    public Responsable() {
    }

    /**
     * Retourne l'identifiant du responsable.
     *
     * @return L'identifiant du responsable.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du responsable.
     *
     * @param id L'identifiant à définir.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le prénom du responsable.
     *
     * @return Le prénom du responsable.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom du responsable.
     *
     * @param prenom Le prénom à définir.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne le nom du responsable.
     *
     * @return Le nom du responsable.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du responsable.
     *
     * @param nom Le nom à définir.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
