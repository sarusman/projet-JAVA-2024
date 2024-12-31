package utils;

import java.util.Scanner;

/**
 * Classe utilitaire pour valider les entrées utilisateur.
 * @author Sahkana
 */
public class InputValidator {

    /**
     * Lit une entrée entière valide dans une plage spécifiée.
     *
     * @param sc      Scanner utilisé pour lire l'entrée.
     * @param prompt  Message affiché pour demander l'entrée.
     * @param min     Valeur minimale acceptée.
     * @param max     Valeur maximale acceptée.
     * @return Une valeur entière valide.
     */
    public static int getIntInput(Scanner sc, String prompt, int min, int max) {
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

    /**
     * Lit une entrée décimale valide dans une plage spécifiée.
     *
     * @param sc      Scanner utilisé pour lire l'entrée.
     * @param prompt  Message affiché pour demander l'entrée.
     * @param min     Valeur minimale acceptée.
     * @param max     Valeur maximale acceptée.
     * @return Une valeur décimale valide.
     */
    public static double getDoubleInput(Scanner sc, String prompt, double min, double max) {
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

    /**
     * Valide et lit un nom en utilisant une expression régulière.
     *
     * @param sc      Scanner utilisé pour lire l'entrée.
     * @param prompt  Message affiché pour demander l'entrée.
     * @return Un nom valide.
     */
    public static String getValidNom(Scanner sc, String prompt) {
        return getValidatedInput(sc, prompt, "^[A-Za-zÀ-ÿ]+([\\s-][A-Za-zÀ-ÿ]+)*$", "Erreur : Le nom doit être composé de lettres.");
    }

    /**
     * Valide et lit un prénom en utilisant une expression régulière.
     *
     * @param sc      Scanner utilisé pour lire l'entrée.
     * @param prompt  Message affiché pour demander l'entrée.
     * @return Un prénom valide.
     */
    public static String getValidPrenom(Scanner sc, String prompt) {
        return getValidatedInput(sc, prompt, "^[A-Za-zÀ-ÿ]+([\\s-][A-Za-zÀ-ÿ]+)*$", "Erreur : Le prénom doit être composé de lettres.");
    }

    /**
     * Demande à l'utilisateur d'entrer une adresse, puis valide que l'entrée
     * commence par un numéro suivi de deux mots, qui peuvent contenir des espaces et des '+'.
     *
     * Cette méthode continue à demander une entrée tant que l'utilisateur n'entre pas
     * une chaîne valide. Elle utilise une expression régulière pour vérifier que l'adresse
     * commence par un numéro suivi de mots (qui peuvent contenir des espaces ou des caractères '+' entre les mots).
     *
     * @param sc     Le scanner utilisé pour lire l'entrée de l'utilisateur.
     * @param prompt Le message à afficher pour inviter l'utilisateur à entrer une adresse.
     * @return       Une chaîne de caractères contenant l'adresse valide.
     */
    public static String getValidAdresse(Scanner sc, String prompt) {
        return getValidatedInput(sc, prompt, "^[0-9]+(?:[A-Za-zÀ-ÿ\\s+\\-]+)+$", "Erreur : L'adresse doit commencer par un numéro et contenir au moins un mot.");
    }

    /**
     * Demande à l'utilisateur d'entrer un pseudo, puis valide que l'entrée
     * commence par une lettre et peut contenir des lettres, des chiffres, des tirets,
     * des underscores et des '+'.
     *
     * Cette méthode continue à demander une entrée tant que l'utilisateur n'entre pas
     * une chaîne valide.
     *
     * @param sc     Le scanner utilisé pour lire l'entrée de l'utilisateur.
     * @param prompt Le message à afficher pour inviter l'utilisateur à entrer un pseudo.
     * @return       Une chaîne de caractères contenant le pseudo valide.
     */
    public static String getValidPseudo(Scanner sc, String prompt) {
        return getValidatedInput(sc, prompt, "^[A-Za-zÀ-ÿ][A-Za-zÀ-ÿ0-9_\\-\\+]*$", "Erreur : Le pseudo doit commencer par une lettre et peut contenir des lettres, des chiffres, des tirets, des underscores et des '+'.");
    }

    /**
     * Demande à l'utilisateur d'entrer un prénom et un nom, puis valide que l'entrée
     * correspond à un format correct (prénom et nom séparés par un espace, uniquement des lettres).
     *
     * Cette méthode continue à demander une entrée tant que l'utilisateur n'entre pas
     * une chaîne valide. Elle utilise une expression régulière pour vérifier que l'entrée
     * contient un prénom et un nom composés uniquement de lettres (y compris les caractères accentués).
     *
     * @param sc     Le scanner utilisé pour lire l'entrée de l'utilisateur.
     * @param prompt Le message à afficher pour inviter l'utilisateur à entrer un prénom et un nom.
     * @return       Une chaîne de caractères contenant le prénom et le nom valides, séparés par un espace.
     */
    public static String getValidResponsable(Scanner sc, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.matches("^[A-Za-zÀ-ÖØ-öø-ÿ]+ [A-Za-zÀ-ÖØ-öø-ÿ]+$")) {
                return input;
            } else {
                System.out.println("Erreur : Veuillez entrer un prénom et un nom séparés par un espace.");
            }
        }
    }

    /**
     * Valide et lit un hobby en utilisant une expression régulière.
     *
     * @param sc      Scanner utilisé pour lire l'entrée.
     * @param prompt  Message affiché pour demander l'entrée.
     * @return Un hobby valide.
     */
    public static String getValidHobby(Scanner sc, String prompt) {
        return getValidatedInput(sc, prompt, ".{1,50}", "Erreur : L'hobby doit être court.");
    }

    /**
     * Valide une entrée utilisateur générique en utilisant une expression régulière.
     *
     * @param sc            Scanner utilisé pour lire l'entrée.
     * @param prompt        Message affiché pour demander l'entrée.
     * @param regex         Expression régulière pour valider l'entrée.
     * @param errorMessage  Message d'erreur affiché si la validation échoue.
     * @return Une entrée valide.
     */
    private static String getValidatedInput(Scanner sc, String prompt, String regex, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (input.matches(regex)) {
                return input;
            } else {
                System.out.println("\u001B[31m" + errorMessage + "\u001B[0m");
            }
        }
    }
}