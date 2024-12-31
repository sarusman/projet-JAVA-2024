package services;

import actions.ActionsBDDImpl;
import models.Programmeur;
import models.Responsable;
import utils.InputValidator;

import java.util.Scanner;

/**
 * Service pour gérer les opérations liées aux programmeurs dans la base de données.
 * @author Priyank
 */
public class ProgrammeurService {

    /**
     * Affiche tous les programmeurs présents dans la base de données.
     *
     * @param actionsBDD l'instance d'actions.ActionsBDDImpl pour interagir avec la base de données
     */
    public static void afficherProgrammeurs(ActionsBDDImpl actionsBDD) {
        if (actionsBDD.getProgrammeurs()) {
            actionsBDD.afficherProgrammeurs();
        } else {
            System.out.println("\u001B[31mAUCUN PROGRAMMEUR DANS LA BASE DE DONNÉES\u001B[0m");
        }
    }

    /**
     * Affiche les détails d'un programmeur spécifique en fonction de son ID.
     *
     * @param actionsBDD l'instance d'actions.ActionsBDDImpl pour interagir avec la base de données
     * @param sc         le scanner utilisé pour lire l'entrée utilisateur
     */
    public static void afficherProgrammeur(ActionsBDDImpl actionsBDD, Scanner sc) {
        int ask;
        while (true) {
            int id = InputValidator.getIntInput(sc, "Id du programmeur à afficher : ", 1, Integer.MAX_VALUE);
            if (actionsBDD.afficherProgrammeur(id)) {
                break;
            } else {
                System.out.println("\u001B[31mRecherche KO. Saisissez à nouveau l'id :\u001B[0m");
            }
        }
    }

    /**
     * Supprime un programmeur de la base de données en fonction de son ID.
     *
     * @param actionsBDD l'instance d'actions.ActionsBDDImpl pour interagir avec la base de données
     * @param sc         le scanner utilisé pour lire l'entrée utilisateur
     */
    public static void supprimerProgrammeur(ActionsBDDImpl actionsBDD, Scanner sc) {
        while (true) {
            int id = InputValidator.getIntInput(sc, "Id du programmeur à supprimer : ", 1, Integer.MAX_VALUE);
            if (actionsBDD.supprimerProgrammeur(id)) {
                System.out.println("SUPPRESSION REUSSIE !");
                break;
            } else {
                System.out.println("\u001B[31mSuppression KO. Saisissez à nouveau l'id :\u001B[0m");
            }
        }
    }

    /**
     * Ajoute un nouveau programmeur à la base de données.
     *
     * @param actionsBDD l'instance d'actions.ActionsBDDImpl pour interagir avec la base de données
     * @param sc         le scanner utilisé pour lire l'entrée utilisateur
     */
    public static void ajouterProgrammeur(ActionsBDDImpl actionsBDD, Scanner sc) {
        String nom, prenom, adresse, pseudo, responsableComplet, responsablePrenom, responsableNom, hobby;
        int responsableId, annee;
        double salaire, prime;

        while (true) {
            nom = InputValidator.getValidNom(sc, "Nom du programmeur : ");
            prenom = InputValidator.getValidPrenom(sc, "Prénom du programmeur : ");

            if (actionsBDD.checkDoublonProgrammeur(prenom, nom, null)) {
                System.out.println("\u001B[31mUn programmeur avec ce nom/prénom existe déjà.\u001B[0m");
            } else {
                break;
            }
        }
        adresse = InputValidator.getValidAdresse(sc, "Adresse du programmeur : ");

        while (true) {
            pseudo = InputValidator.getValidPseudo(sc, "Pseudo du programmeur : ");

            if (actionsBDD.checkDoublonProgrammeur(null, null, pseudo)) {
                System.out.println("\u001B[31mUn programmeur avec ce pseudo existe déjà.\u001B[0m");
            } else {
                break;
            }
        }

        while (true) {
            responsableComplet = InputValidator.getValidResponsable(sc, "models.Responsable du programmeur : ");
            String[] parts = responsableComplet.split(" ", 2);

            if (parts.length < 2) {
                System.out.println("Erreur : Vous devez entrer à la fois un prénom et un nom.");
                continue;
            }
            responsablePrenom = parts[0].toLowerCase(); // Paul = paul = pAuL
            responsableNom = parts[1].toLowerCase();

            Responsable responsable = new Responsable(responsablePrenom, responsableNom);

            responsableId = actionsBDD.getResponsableId(responsableNom, responsablePrenom);

            if (responsableId != -1) {
                break;
            } else {
                System.out.println("\u001B[31mLe responsable est introuvable. Souhaitez-vous l'ajouter ? (o/n)\u001B[0m");
                String reponse = sc.nextLine();

                if (reponse.equalsIgnoreCase("o")) {
                    break;
                } else if (reponse.equalsIgnoreCase("n")) {
                    continue;
                } else {
                    System.out.println("Réponse invalide. Veuillez répondre par 'o' pour ajouter ou 'n' pour entrer un autre responsable.");
                }
            }
        }

        hobby = InputValidator.getValidHobby(sc, "Hobby du programmeur : ");
        annee = InputValidator.getIntInput(sc, "Année de naissance du programmeur : ", 1900, 2024);
        salaire = InputValidator.getDoubleInput(sc, "Salaire du programmeur : ", 400, 100000);
        prime = InputValidator.getDoubleInput(sc, "Prime du programmeur : ", 10, 100000);
        Responsable responsable = new Responsable(responsablePrenom, responsableNom);
        Programmeur programmeur = new Programmeur(nom, prenom, adresse, pseudo, responsable, hobby, annee, salaire, prime);
        actionsBDD.ajouterProgrammeur(programmeur);
    }

    /**
     * Modifie le salaire d'un programmeur dans la base de données.
     *
     * @param actionsBDD l'instance d'actions.ActionsBDDImpl pour interagir avec la base de données
     * @param sc         le scanner utilisé pour lire l'entrée utilisateur
     */
    public static void modifierSalaire(ActionsBDDImpl actionsBDD, Scanner sc) {
        while (true) {
            int id = InputValidator.getIntInput(sc, "Id du programmeur : ", 1, Integer.MAX_VALUE);
            if (actionsBDD.getProgrammeur(id)) {
                double salaire = InputValidator.getDoubleInput(sc, "Nouveau salaire de ce programmeur : ", 0, Double.MAX_VALUE);
                actionsBDD.modifierSalaire(id, salaire);
                break;
            } else {
                System.out.println("\u001B[31mProgrammeur introuvable. Saisissez à nouveau l'id :\u001B[0m");
            }
        }
    }
}