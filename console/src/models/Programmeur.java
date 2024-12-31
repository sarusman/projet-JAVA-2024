package models;

/**
 * La classe models.Programmeur représente un programmeur avec ses informations personnelles et professionnelles.
 * @author Sarusman
 */

public class Programmeur {

    /** ATTRIBUTS */
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String pseudo;
    private Responsable responsable;
    private String hobby;
    private int anneeNaissance;
    private double salaire;
    private double prime;

    /** CONSTRUCTEURS */
    /**
     * Constructeur de la classe models.Programmeur avec tous les attributs y compris l'identifiant.
     *
     * @param id            identifiant unique du programmeur
     * @param nom           nom du programmeur
     * @param prenom        prénom du programmeur
     * @param adresse       adresse du programmeur
     * @param pseudo        pseudonyme du programmeur
     * @param responsable   responsable hiérarchique du programmeur
     * @param hobby         hobby ou loisir préféré du programmeur
     * @param anneeNaissance année de naissance du programmeur
     * @param salaire       salaire annuel du programmeur
     * @param prime         prime annuelle du programmeur
     */
    public Programmeur(int id, String nom, String prenom, String adresse, String pseudo, Responsable responsable, String hobby, int anneeNaissance, double salaire, double prime) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.pseudo = pseudo;
        this.responsable = responsable;
        this.hobby = hobby;
        this.anneeNaissance = anneeNaissance;
        this.salaire = salaire;
        this.prime = prime;
    }

    /**
     * Constructeur de la classe models.Programmeur sans identifiant
     *
     * @param nom           nom du programmeur
     * @param prenom        prénom du programmeur
     * @param adresse       adresse du programmeur
     * @param pseudo        pseudonyme du programmeur
     * @param responsable   responsable hiérarchique du programmeur
     * @param hobby         hobby ou loisir préféré du programmeur
     * @param anneeNaissance année de naissance du programmeur
     * @param salaire       salaire annuel du programmeur
     * @param prime         prime annuelle du programmeur
     */
    public Programmeur(String nom, String prenom, String adresse, String pseudo, Responsable responsable, String hobby, int anneeNaissance, double salaire, double prime) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.pseudo = pseudo;
        this.responsable = responsable;
        this.hobby = hobby;
        this.anneeNaissance = anneeNaissance;
        this.salaire = salaire;
        this.prime = prime;
    }

    /** GETTERS & SETTERS */

    /**
     * Retourne l'identifiant unique du programmeur.
     * @return l'identifiant du programmeur
     */
    public int getId() {
        return id;
    }

    /**
     * Modifie l'identifiant du programmeur.
     * @param id le nouvel identifiant du programmeur
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public int getAnneeNaissance() {
        return anneeNaissance;
    }

    public void setAnneeNaissance(int anneeNaissance) {
        this.anneeNaissance = anneeNaissance;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public double getPrime() {
        return prime;
    }

    public void setPrime(double prime) {
        this.prime = prime;
    }


    /**
     * Méthode retournant une représentation plus lisible de l'objet models.Programmeur.
     * @return une chaîne de caractères contenant les informations du programmeur
     */
    @Override
    public String toString() {
        return
                "Id\t:\t" + id + '\n' +
                        "Nom\t:\t" + nom + '\n' +
                        "Prenom\t:\t" + prenom + '\n' +
                        "Adresse\t:\t" + adresse + '\n' +
                        "Pseudo\t:\t" + pseudo + '\n' +
                        "models.Responsable\t:\t" + responsable + '\n' +
                        "Hobby\t:\t" + hobby + '\n' +
                        "Annee de naissance\t:\t" + anneeNaissance + '\n' +
                        "Salaire\t:\t" + salaire + '\n' +
                        "Prime\t:\t" + prime;
    }
}
