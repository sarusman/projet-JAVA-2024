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

            switch(choix){
                case "1":
                    //affiche tous les programmeurs
                    System.out.println("Voici tous les programmeurs : \n");
                    break;
                case "2":
                    // affiche programmeur
                    System.out.println("Entrez l'id du programmeur que vous voulez afficher : \n");
                    String idProgrammeur = sc.nextLine();
                    System.out.println("Voici le programmeur n° :" + idProgrammeur + "\n");
                    break;
                case "3":
                    // supprime programmeur
                    System.out.println("Entrez l'id du programmeur que vous voulez supprimer : \n");
                    String idSupp = sc.nextLine();
                    System.out.println("Le programmeur n° :" + idSupp + " a bien été supprimé.\n");
                    break;
                case "4":
                    // ajoute programmeur
                    ajouterProgrammeur();
                    break;
                case "5":
                    // modifier le salaire
                    break;
                case "0":
                    //quitter programme
                    afficher = false;
                    break;
            }
        }
    }

    public static void ajouterProgrammeur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nom du programmeur : \n");
        String nom = sc.nextLine();
        System.out.println("Prénom du programmeur : \n");
        String prenom = sc.nextLine();
        System.out.println("Adresse du programmeur : \n");
        String adresse = sc.nextLine();
        System.out.println("Pseudo du programmeur : \n");
        String pseudo = sc.nextLine();
        System.out.println("Responsable du programmeur : \n");
        String responsable = sc.nextLine();
        System.out.println("Hobby du programmeur : \n");
        String hobby = sc.nextLine();
        System.out.println("Année de naissance du programmeur : \n");
        String annee = sc.nextLine();
        System.out.println("Salaire du programmeur : \n");
        String salaire = sc.nextLine();
        System.out.println("Prime du programmeur : \n");
        String prime = sc.nextLine();
        System.out.println("Nom du programmeur : \n");

        System.out.println(nom + " " + prenom + " a bien été ajouté !\n");
    }
}
