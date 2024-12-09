import java.util.Scanner;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class Menu {
    private static ActionsBDDImpl actionsBDD = new ActionsBDDImpl();

    public static void afficherMenu() {
        boolean afficher = true;
        Scanner sc = new Scanner(System.in);

        while (afficher) {
            System.out.println("\n<<<<<<<<<<<<<<<<<< MENU >>>>>>>>>>>>>>>>");
            System.out.println("1. Afficher tous les programmeurs");
            System.out.println("2. Afficher un programmeur");
            System.out.println("3. Supprimer un programmeur");
            System.out.println("4. Ajouter un programmeur");
            System.out.println("5. Modifier le salaire");
            System.out.println("6. Quitter le programme");
            System.out.print("\nQuel est votre choix ? : ");

            String choix = sc.nextLine();

            switch (choix) {
                case "1":
                    afficherProgrammeurs();
                    break;
                case "2":
                    afficherProgrammeur(sc);
                    break;
                case "3":
                    supprimerProgrammeur(sc);
                    break;
                case "4":
                    ajouterProgrammeur(sc);
                    break;
                case "5":
                    modifierSalaire(sc);
                    break;
                case "6":
                    System.out.println("Bonne journée !");
                    afficher = false;
                    break;
                default:
                    System.out.println("Veuillez choisir un chiffre entre 1 et 6.");
                    break;
            }
        }
        sc.close();
    }

    //afficher tous les programmeurs
    public static void afficherProgrammeurs() {
        //requête bdd
        actionsBDD.afficherProgrammeurs();
    }

    //afficher un programmeur avec son id
    public static void afficherProgrammeur(Scanner sc) {
        System.out.println("Id du programmeur à afficher : ");
        int id = 0;
        try {
            id = parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31mErreur : L'ID doit être un nombre entier\u001B[0m");
        }
        boolean programmeurTrouver = actionsBDD.afficherProgrammeur(id);
        //requête depuis la bdd
        while (!programmeurTrouver){
            System.out.print("Recherche KO. Saisissez à nouveau l'id : ");
            try {
                id = parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mErreur : L'ID doit être un nombre entier\u001B[0m");
            }
            programmeurTrouver = actionsBDD.afficherProgrammeur(id);
        }
    }

    //supprimer un programmeur
    public static void supprimerProgrammeur(Scanner sc) {
        System.out.print("\nEntrez l'ID du programmeur que vous voulez supprimer : ");
        String id = sc.nextLine();
        System.out.println("Le programmeur n° " + id + " a été supprimé.");
        //requête bdd
        actionsBDD.supprimerProgrammeur();
    }

    //ajouter un programmeur
    public static void ajouterProgrammeur(Scanner sc) {
        System.out.println("\nAjout d'un nouveau programmeur :");
        System.out.print("Nom : ");
        String nom = sc.nextLine();
        System.out.print("Prénom : ");
        String prenom = sc.nextLine();
        System.out.print("Adresse : ");
        String adresse = sc.nextLine();
        System.out.print("Pseudo : ");
        String pseudo = sc.nextLine();
        System.out.print("Responsable : ");
        String responsable = sc.nextLine();
        System.out.print("Hobby : ");
        String hobby = sc.nextLine();
        System.out.print("Année de naissance : ");
        String annee = sc.nextLine();
        System.out.print("Salaire : ");
        String salaire = sc.nextLine();
        System.out.print("Prime : ");
        String prime = sc.nextLine();

        Programmeur programmeur = new Programmeur(nom, prenom, adresse, pseudo, responsable, hobby, parseInt(annee), parseFloat(salaire), parseFloat(prime));

        System.out.println("\nLe programmeur " + nom + " " + prenom + " a bien été ajouté !");
        //requête bdd
        actionsBDD.ajouterProgrammeur(programmeur);
    }

    //modifier le salaire d'un programmeur
    public static void modifierSalaire(Scanner sc) {
        System.out.print("\nEntrez l'ID du programmeur pour modifier le salaire : ");
        String id = sc.nextLine();
        System.out.print("Entrez le nouveau salaire : ");
        String salaire = sc.nextLine();
        System.out.println("Le salaire du programmeur n° " + id + " a été modifié à " + salaire + " €.");
        //requête bdd
        actionsBDD.modifierSalaire(parseInt(id), parseFloat(salaire));
    }
}
