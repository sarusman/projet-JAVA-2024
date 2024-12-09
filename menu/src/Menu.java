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
                    if(actionsBDD.getProgrammeurs()){
                        afficherProgrammeurs();
                    }else{
                        System.out.println("\u001B[31mAUCUN PROGRAMMEURS DANS LA BASE DE DONNÉES\u001B[0m");
                    }
                    break;
                case "2":
                    if(actionsBDD.getProgrammeurs()){
                        afficherProgrammeur(sc);
                    }else{
                        System.out.println("\u001B[31mAUCUN PROGRAMMEURS DANS LA BASE DE DONNÉES\u001B[0m");
                    }
                    break;
                case "3":
                    if(actionsBDD.getProgrammeurs()){
                        supprimerProgrammeur(sc);
                    }else{
                        System.out.println("\u001B[31mAUCUN PROGRAMMEURS DANS LA BASE DE DONNÉES\u001B[0m");
                    }
                    break;
                case "4":
                    ajouterProgrammeur(sc);
                    break;
                case "5":
                    if(actionsBDD.getProgrammeurs()){
                        modifierSalaire(sc);
                    }else{
                        System.out.println("\u001B[31mAUCUN PROGRAMMEURS DANS LA BASE DE DONNÉES\u001B[0m");
                    }
                    break;
                case "6":
                    System.out.println("Bonne journée !");
                    afficher = false;
                    break;
                default:
                    System.out.println("ERREUR ! Veuillez saisir un chiffre entre 1 et 6.");
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
        System.out.println("Id du programmeur à afficher :");
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
        System.out.println("Id du programmeur à supprimer : ");
        int id = 0;
        try {
            id = parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31mErreur : L'ID doit être un nombre entier\u001B[0m");
        }
        boolean programmeurTrouver = actionsBDD.supprimerProgrammeur(id);
        //requête depuis la bdd
        while (!programmeurTrouver){
            System.out.print("Recherche KO. Saisissez à nouveau l'id : ");
            try {
                id = parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mErreur : L'ID doit être un nombre entier\u001B[0m");
            }
            programmeurTrouver = actionsBDD.supprimerProgrammeur(id);
        }
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
        int annee = -1;
        while (annee < 0){
            try {
                System.out.print("Année de naissance : ");
                annee = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mErreur : L'année de naissance doit être un nombre entier positif\u001B[0m");
            }
        }
        double salaire = -1;
        while (salaire < 0 ){
            try {
                System.out.print("Salaire : ");
                salaire = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mErreur : Le salaire doit être un nombre à virgule positif\u001B[0m");
            }
        }
        double prime = -1;
        while (prime < 0 ){
            try {
                System.out.print("Prime : ");
                prime = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mErreur : La prime doit être un nombre positif\u001B[0m");
            }
        }
        Programmeur programmeur = new Programmeur(nom, prenom, adresse, pseudo, responsable, hobby, annee, salaire, prime);
        //requête bdd
        actionsBDD.ajouterProgrammeur(programmeur);
    }

    //modifier le salaire d'un programmeur
    public static void modifierSalaire(Scanner sc) {
        System.out.println("Id du programmeur : ");
        int id = 0;
        try {
            id = parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31mErreur : L'ID doit être un nombre entier\u001B[0m");
        }
        // verification de l'existence du programmeur
        boolean programmeurTrouver = actionsBDD.getProgrammeur(id);
        //requête depuis la bdd
        while (!programmeurTrouver){
            System.out.print("Recherche KO. Saisissez à nouveau l'id : ");
            try {
                id = parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mErreur : L'ID doit être un nombre entier\u001B[0m");
            }
            programmeurTrouver = actionsBDD.getProgrammeur(id);
        }
        //update programmeur
        double salaire = -1;
        while (salaire < 0 ){
            try {
                System.out.println("Nouveau salaire de ce programmeur : ");
                salaire = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mErreur : Le salaire doit être un nombre entier ou à virgule positif\u001B[0m");
            }
        }
        actionsBDD.modifierSalaire(id, salaire);
    }
}
