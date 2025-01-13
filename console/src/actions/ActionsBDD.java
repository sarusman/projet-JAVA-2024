package actions;

import models.Programmeur;

import java.sql.SQLException;

/**
 * Interface pour les actions de base concernant la gestion des programmeurs dans la base de données.
 */
public interface ActionsBDD {

    /**
     * Affiche la liste complète des programmeurs présents dans la base de données.
     *
     * @throws SQLException Si une erreur se produit lors de l'accès à la base de données.
     */
    void afficherProgrammeurs() throws SQLException;

    /**
     * Affiche les informations d'un programmeur en particulier.
     *
     * @param id L'identifiant unique du programmeur.
     * @return {@code true} si le programmeur existe, sinon {@code false}.
     */
    boolean afficherProgrammeur(int id);

    /**
     * Supprime un programmeur de la base de données à l'aide de son identifiant.
     *
     * @param id L'identifiant du programmeur à supprimer.
     * @return {@code true} si la suppression a réussi, sinon {@code false}.
     */
    boolean supprimerProgrammeur(int id);

    /**
     * Ajoute un nouveau programmeur à la base de données.
     *
     * @param programmeur Une instance de {@link Programmeur} contenant les informations du programmeur.
     */
    void ajouterProgrammeur(Programmeur programmeur);

    /**
     * Modifie le salaire d'un programmeur dans la base de données.
     *
     * @param id      L'identifiant unique du programmeur.
     * @param salaire Le nouveau salaire à attribuer.
     */
    void modifierSalaire(int id, double salaire);

    /**
     * Vérifie si un programmeur est un doublon dans la base de données avec son prénom, nom ou pseudo.
     *
     * @param prenom Le prénom du programmeur.
     * @param nom    Le nom du programmeur.
     * @param pseudo Le pseudo du programmeur.
     * @return {@code true} si un doublon existe, sinon {@code false}.
     */
    boolean checkDoublonProgrammeur(String prenom, String nom, String pseudo);
}