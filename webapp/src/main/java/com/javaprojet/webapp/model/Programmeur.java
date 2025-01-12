package com.javaprojet.webapp.model;

import jakarta.persistence.*;

/**
 * Représente un programmeur dans le système.
 *
 * Cette classe est une entité JPA mappée à la table "Programmeurs" dans la base de données. Elle contient des informations
 * détaillées sur un programmeur, telles que son nom, prénom, adresse, pseudo, responsable, hobby, année d'entrée, salaire,
 * et prime. Elle est utilisée pour stocker et récupérer les données relatives aux programmeurs dans la base de données.
 *
 * @author Sarusman
 */
@Entity
@Table(name = "Programmeurs")
public class Programmeur {

    /**
     * Identifiant unique du programmeur.
     *
     * Cet identifiant est généré automatiquement par la base de données grâce à la stratégie de génération d'identifiant
     * spécifiée par l'annotation {@link GeneratedValue}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    /**
     * Le nom du programmeur.
     */
    public String nom;

    /**
     * Le prénom du programmeur.
     */
    public String prenom;

    /**
     * L'adresse du programmeur.
     */
    public String adresse;

    /**
     * Le pseudo du programmeur.
     */
    public String pseudo;

    /**
     * Le responsable du programmeur.
     */
    public String responsable;

    /**
     * Les hobbies du programmeur.
     */
    public String hobby;

    /**
     * L'année d'entrée du programmeur dans l'entreprise.
     */
    public int annee;

    /**
     * Le salaire du programmeur.
     */
    public double salaire;

    /**
     * La prime du programmeur.
     */
    public double prime;

    /**
     * Constructeur par défaut pour JPA.
     *
     * Ce constructeur est utilisé par JPA pour créer une instance de l'entité sans initialiser ses propriétés.
     */
    public Programmeur() {}

    /**
     * Retourne l'identifiant du programmeur.
     *
     * @return L'identifiant du programmeur.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant du programmeur.
     *
     * @param id L'identifiant à définir.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne le nom du programmeur.
     *
     * @return Le nom du programmeur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du programmeur.
     *
     * @param nom Le nom à définir.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom du programmeur.
     *
     * @return Le prénom du programmeur.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom du programmeur.
     *
     * @param prenom Le prénom à définir.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne l'adresse du programmeur.
     *
     * @return L'adresse du programmeur.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit l'adresse du programmeur.
     *
     * @param adresse L'adresse à définir.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Retourne le pseudo du programmeur.
     *
     * @return Le pseudo du programmeur.
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Définit le pseudo du programmeur.
     *
     * @param pseudo Le pseudo à définir.
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Retourne le responsable du programmeur.
     *
     * @return Le responsable du programmeur.
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * Définit le responsable du programmeur.
     *
     * @param responsable Le responsable à définir.
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * Retourne les hobbies du programmeur.
     *
     * @return Les hobbies du programmeur.
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * Définit les hobbies du programmeur.
     *
     * @param hobby Les hobbies à définir.
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /**
     * Retourne l'année d'entrée du programmeur dans l'entreprise.
     *
     * @return L'année d'entrée du programmeur.
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Définit l'année d'entrée du programmeur dans l'entreprise.
     *
     * @param annee L'année d'entrée à définir.
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * Retourne le salaire du programmeur.
     *
     * @return Le salaire du programmeur.
     */
    public double getSalaire() {
        return salaire;
    }

    /**
     * Définit le salaire du programmeur.
     *
     * @param salaire Le salaire à définir.
     */
    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    /**
     * Retourne la prime du programmeur.
     *
     * @return La prime du programmeur.
     */
    public double getPrime() {
        return prime;
    }

    /**
     * Définit la prime du programmeur.
     *
     * @param prime La prime à définir.
     */
    public void setPrime(double prime) {
        this.prime = prime;
    }
}