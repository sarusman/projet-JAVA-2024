import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    private static ActionsBDDImpl actionsBDD = new ActionsBDDImpl();
    private static Scanner sc = new Scanner(System.in);

    public static void afficherMenu() {
        boolean afficher = true;

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
                    afficherProgrammeur();
                    break;
                case "3":
                    supprimerProgrammeur();
                    break;
                case "4":
                    ajouterProgrammeur();
                    break;
                case "5":
                    modifierSalaire();
                    break;
                case "6":
                    System.out.println("Bonne journée !");
                    afficher = false;
                    break;
                default:
                    System.out.println("Veuillez saisir un chiffre entre 1 et 6.");
                    break;
            }
        }
        sc.close();
    }

    private static void afficherProgrammeurs() {
        if (actionsBDD.getProgrammeurs()) {
            actionsBDD.afficherProgrammeurs();
        } else {
            System.out.println("\u001B[31mAUCUN PROGRAMMEUR DANS LA BASE DE DONNÉES\u001B[0m");
        }
    }

    private static void afficherProgrammeur() {
        int id = getIdFromUser("Id du programmeur à afficher : ");
        if (actionsBDD.afficherProgrammeur(id)) {
            return;
        }
        System.out.println("\u001B[31mProgrammeur non trouvé.\u001B[0m");
    }

    private static void supprimerProgrammeur() {
        int id = getIdFromUser("Id du programmeur à supprimer : ");
        if (actionsBDD.supprimerProgrammeur(id)) {
            System.out.println("SUPPRESSION REUSSIE !");
        } else {
            System.out.println("\u001B[31mProgrammeur non trouvé.\u001B[0m");
        }
    }

    private static void ajouterProgrammeur() {
        System.out.println("\nAjout d'un nouveau programmeur :");

        String nom = getValidNom("Nom : ");

        String prenom = getValidPrenom("Prénom : ");
        if (actionsBDD.checkDoublonProgrammeur(prenom, nom, null)) {
            System.out.println("\u001B[31mUn programmeur avec ce nom/prénom existe déjà.\u001B[0m");
            return;
        }

        String adresse = getValidAdresse("Adresse : ");

        String pseudo = getValidPseudo("Pseudo : ");
        if (actionsBDD.checkDoublonProgrammeur(null, null, pseudo)) {
            System.out.println("\u001B[31mUn programmeur avec ce pseudo existe déjà.\u001B[0m");
            return;
        }

        String responsable = getValidResponsable("Responsable : ");
        String hobby = getValidHobby("Hobby : ");

        int annee = getIntInput("Année de naissance : ", 1900, 2024);

        double salaire = getDoubleInput("Salaire : ", 400, Double.MAX_VALUE);
        double prime = getDoubleInput("Prime : ", 20, Double.MAX_VALUE);

        Programmeur programmeur = new Programmeur(nom, prenom, adresse, pseudo, responsable, hobby, annee, salaire, prime);
        actionsBDD.ajouterProgrammeur(programmeur);
    }


    private static void modifierSalaire() {
        int id = getIdFromUser("Id du programmeur à modifier le salaire : ");
        double salaire = getDoubleInput("Nouveau salaire : ", 0, Double.MAX_VALUE);
        actionsBDD.modifierSalaire(id, salaire);
    }

    private static int getIdFromUser(String prompt) {
        int id;
        while (true) {
            System.out.print(prompt);
            try {
                id = Integer.parseInt(sc.nextLine());
                return id;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mErreur : L'ID doit être un nombre entier.\u001B[0m");
            }
        }
    }

    private static String getValidNom(String prompt) {
        String nom;
        while (true) {
            nom = getStringInput(prompt);
            if (nom.matches("^[A-Za-zÀ-ÿ]+([\\s-][A-Za-zÀ-ÿ]+)*$")) {
                return nom;
            } else {
                System.out.println("\u001B[31mErreur : Le nom doit être composé de lettres, avec des espaces ou des tirets autorisés.\u001B[0m");
            }
        }
    }

    private static String getValidPrenom(String prompt) {
        String prenom;
        while (true) {
            prenom = getStringInput(prompt);
            if (prenom.matches("^[A-Za-zÀ-ÿ]+([\\s-][A-Za-zÀ-ÿ]+)*$")) {
                return prenom;
            } else {
                System.out.println("\u001B[31mErreur : Le prénom doit être composé de lettres, avec des espaces ou des tirets autorisés.\u001B[0m");
            }
        }
    }

    private static String getValidAdresse(String prompt) {
        String adresse;
        while (true) {
            adresse = getStringInput(prompt);
            if (adresse.matches("^[0-9]+(?:[A-Za-zÀ-ÿ\\s-]+)*$")) {
                return adresse;
            } else {
                System.out.println("\u001B[31mErreur : L'adresse doit commencer par un numéro, suivi de la rue (sans chiffres au début de la rue).\u001B[0m");
            }
        }
    }

    private static String getValidPseudo(String prompt) {
        String pseudo;
        while (true) {
            pseudo = getStringInput(prompt);
            if (pseudo.matches("^[A-Za-zÀ-ÿ][A-Za-zÀ-ÿ0-9_-]*$") && !pseudo.matches("^[0-9]+$")) {
                return pseudo;
            } else {
                System.out.println("\u001B[31mErreur : Le pseudo doit commencer par une lettre .\u001B[0m");
            }
        }
    }

    private static String getValidResponsable(String prompt) {
        String responsable;
        while (true) {
            responsable = getStringInput(prompt);
            if (responsable.length() > 0 && responsable.length() <= 50) {
                return responsable;
            } else {
                System.out.println("\u001B[31mErreur : Le responsable doit être une chaîne de caractères raisonnablement courte.\u001B[0m");
            }
        }
    }

    private static String getValidHobby(String prompt) {
        String hobby;
        while (true) {
            hobby = getStringInput(prompt);
            if (hobby.length() > 0 && hobby.length() <= 50) {
                return hobby;
            } else {
                System.out.println("\u001B[31mErreur : L'hobby doit être une chaîne de caractères raisonnablement courte.\u001B[0m");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    private static int getIntInput(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(sc.nextLine());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("\u001B[31mErreur : La valeur doit être entre " + min + " et " + max + ".\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mErreur : Veuillez saisir un nombre entier.\u001B[0m");
            }
        }
    }

    private static double getDoubleInput(String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(sc.nextLine());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("\u001B[31mErreur : La valeur doit être entre " + min + " et " + max + ".\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mErreur : Veuillez saisir un nombre valide.\u001B[0m");
            }
        }
    }
}
