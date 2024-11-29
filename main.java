import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        afficherMenu();
    }

    public static void afficherMenu(){
        boolean afficher = true;
        while (afficher){
            System.out.println("<<<<<<<<<<<<<<<<<< MENU >>>>>>>>>>>>>>>>\n");
            System.out.println("1. Afficher tous les programmeurs\n");
            System.out.println("2. Afficher un programmeur\n");
            System.out.println("3. Supprimer un programmeur\n");
            System.out.println("4. Ajouter un programmeur\n");
            System.out.println("5. Modifier le salaire\n");
            System.out.println("Quitter le programme\n");
            System.out.println("Quel est votre choix ? :\n");
            Scanner sc = new Scanner(System.in);
            String choix = sc.nextLine();
            //vérification de la saisie

            switch(choix){
                case "1":
                    //affiche tous les programmeurs
                    afficherProgrammeur();
                    break;
                case "2":
                    // affiche programmeur
                    String idProgrammeur = sc.nextLine();
                    afficherProgrammeur(idProgrammeur);
                    break;
                case "3":
                    // supprime programmeur
                    System.out.println("Entrez l'id du programmeur que vous voulez supprimer : \n");
                    String idSupp = sc.nextLine();
                    supprimerProgrammeur(idSupp);
                    break;
                case "4":
                    // ajoute programmeur
                    ajouterProgrammeur();
                    break;
                case "5":
                    // modifier le salaire
                    System.out.println("Entrez l'id du programmeur :");
                    break;
                case "0":
                    //quitter programme
                    afficher = false;
                    break;
            }
        }
    }

    public static void afficherProgrammeur(){
        // vérification id existante
        System.out.println("Voici tous les programmeurs : \n");
    }

    public static void afficherProgrammeur(String id){
        // vérification id existante
        System.out.println("Entrez l'id du programmeur que vous voulez afficher : \n");

        System.out.println("Voici le programmeur n° :" + id + "\n");
    }

    public static void supprimerProgrammeur(String id){
        System.out.println("Le programmeur n° :" + id + " a bien été supprimé.\n");
    }

    public static void ajouterProgrammeur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nNom du programmeur : ");
        String nom = sc.nextLine();
        System.out.println("\nPrénom du programmeur : ");
        String prenom = sc.nextLine();
        System.out.println("\nAdresse du programmeur : ");
        String adresse = sc.nextLine();
        System.out.println("\nPseudo du programmeur : ");
        String pseudo = sc.nextLine();
        System.out.println("\nResponsable du programmeur : ");
        String responsable = sc.nextLine();
        System.out.println("\nHobby du programmeur : ");
        String hobby = sc.nextLine();
        System.out.println("\nAnnée de naissance du programmeur : ");
        String annee = sc.nextLine();
        System.out.println("\nSalaire du programmeur : ");
        String salaire = sc.nextLine();
        System.out.println("\nPrime du programmeur : ");
        String prime = sc.nextLine();
        System.out.println("\nNom du programmeur : ");

        System.out.println(" \n" + nom + " " + prenom + " a bien été ajouté !");
    }
}
