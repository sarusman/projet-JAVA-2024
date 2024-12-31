package models;

/**
 * Représente un responsable avec un prénom et un nom.
 * Cette classe est utilisée pour stocker les informations d'un responsable
 * dans le cadre de la gestion des programmeurs.
 *
 * @author Sahkana
 */
public class Responsable {
    private String prenom;
    private String nom;

    /**
     * Constructeur pour créer un responsable avec son prénom et son nom.
     *
     * @param prenom Le prénom du responsable.
     * @param nom Le nom du responsable.
     */
    public Responsable(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
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
     * Retourne le nom du responsable.
     *
     * @return Le nom du responsable.
     */
    public String getNom() {
        return nom;
    }
}