package menu;

import actions.ActionsBDDImpl;
import services.ProgrammeurService;

import java.util.Scanner;

/**
 * Classe principale pour afficher et gérer le menu interactif du programme.
 * @author Sarusman
 */
public class Menu {
    private static ActionsBDDImpl actionsBDD = new ActionsBDDImpl();
    private static Scanner sc = new Scanner(System.in);

    /**
     * Affiche le menu principal et gère les interactions avec l'utilisateur.
     */
    public static void afficherMenu() {
        boolean afficher = true;

        while (afficher) {
            afficherOptions();
            String choix = sc.nextLine();

            switch (choix) {
                case "1":
                    ProgrammeurService.afficherProgrammeurs(actionsBDD);
                    break;
                case "2":
                    ProgrammeurService.afficherProgrammeur(actionsBDD, sc);
                    break;
                case "3":
                    ProgrammeurService.supprimerProgrammeur(actionsBDD, sc);
                    break;
                case "4":
                    ProgrammeurService.ajouterProgrammeur(actionsBDD, sc);
                    break;
                case "5":
                    ProgrammeurService.modifierSalaire(actionsBDD, sc);
                    break;
                case "6":
                    System.out.println("Bonne journée !");
                    afficher = false;
                    break;
                default:
                    System.out.println("ERREUR! Veuillez saisir un nombre entre 1 et 6.");
                    break;
            }
        }
        sc.close();
    }

    /**
     * Affiche les options disponibles dans le menu principal.
     */
    private static void afficherOptions() {
        System.out.println("\n<<<<<<<<<<<<<<<<<< MENU >>>>>>>>>>>>>>>>");
        System.out.println("1. Afficher tous les programmeurs");
        System.out.println("2. Afficher un programmeur");
        System.out.println("3. Supprimer un programmeur");
        System.out.println("4. Ajouter un programmeur");
        System.out.println("5. Modifier le salaire");
        System.out.println("6. Quitter le programme");
        System.out.print("\nQuel est votre choix ? : ");
    }
}