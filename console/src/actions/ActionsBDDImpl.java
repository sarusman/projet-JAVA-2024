package actions;

import models.Programmeur;
import models.Responsable;

import java.sql.*;

/**
 * Implémentation de l'interface actions.ActionsBDD pour gérer les interactions avec la base de données.
 */
public class ActionsBDDImpl implements ActionsBDD {

    private static final String URL = "jdbc:postgresql://aws-0-eu-west-3.pooler.supabase.com:6543/postgres?user=postgres.fcckpwkbrkquitlmuxon&password=Prysasha2024!";
    private static final ActionsBDDImpl actionsBD = new ActionsBDDImpl();

    private static final String SELECT_ALL_PROGRAMMEURS = "SELECT * FROM Programmeurs ORDER BY id ASC";
    private static final String SELECT_PROGRAMMEUR_BY_ID = "SELECT * FROM Programmeurs WHERE id = ?";
    private static final String DELETE_PROGRAMMEUR = "DELETE FROM Programmeurs WHERE id = ?";
    private static final String INSERT_PROGRAMMEUR = "INSERT INTO Programmeurs (nom, prenom, adresse, pseudo, responsable, hobby, annee, salaire, prime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SALAIRE = "UPDATE Programmeurs SET salaire = ? WHERE id = ?";
    private static final String CHECK_DOUBlON_PROGRAMMEUR = "SELECT * FROM Programmeurs WHERE (LOWER(prenom) = LOWER(?) AND LOWER(nom) = LOWER(?)) OR LOWER(pseudo) = LOWER(?)";
    private static final String GET_RESPONSABLE_ID = "SELECT id FROM responsables WHERE LOWER(nom) = LOWER(?) AND LOWER(prenom) = LOWER(?)";
    private static final String CHECK_RESPONSABLE_EXISTS = "SELECT id FROM Responsables WHERE LOWER(prenom) = LOWER(?) AND LOWER(nom) = LOWER(?)";
    private static final String INSERT_RESPONSABLE = "INSERT INTO Responsables (prenom, nom) VALUES (?, ?) RETURNING id";

    /**
     * Ouvre une connexion à la base de données.
     *
     * @return Une instance de {@link Connection}.
     * @throws SQLException Si une erreur se produit lors de la connexion.
     * @author Sahkana
     */
    public Connection ouvrirConnexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Affiche tous les programmeurs enregistrés dans la base de données.
     * @author Priyank
     */
    @Override
    public void afficherProgrammeurs() {
        try (Connection co = actionsBD.ouvrirConnexion(); Statement stmt = co.createStatement(); ResultSet rs = stmt.executeQuery(SELECT_ALL_PROGRAMMEURS)) {
            System.out.println("\nListe des programmeurs :");
            System.out.println("-------------------------------------------------");
            while (rs.next()) {
                System.out.printf("Id           : %d%n", rs.getInt("id"));
                System.out.printf("Nom          : %s%n", rs.getString("nom"));
                System.out.printf("Prénom       : %s%n", rs.getString("prenom"));
                System.out.printf("Adresse      : %s%n", rs.getString("adresse"));
                System.out.printf("Pseudo       : %s%n", rs.getString("pseudo"));
                System.out.printf("Responsable  : %s%n", rs.getString("responsable"));
                System.out.printf("Hobby        : %s%n", rs.getString("hobby"));
                System.out.printf("Naissance    : %d%n", rs.getInt("annee"));
                System.out.printf("Salaire      : %.1f%n", rs.getFloat("salaire"));
                System.out.printf("Prime        : %.1f%n", rs.getFloat("prime"));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des programmeurs.");
        }
    }

    /**
     * Affiche les informations d'un programmeur en particulier.
     *
     * @param id L'identifiant du programmeur.
     * @return {@code true} si le programmeur existe, sinon {@code false}.
     * @author Priyank
     */
    @Override
    public boolean afficherProgrammeur(int id) {
        try (Connection co = actionsBD.ouvrirConnexion(); PreparedStatement stmt = co.prepareStatement(SELECT_PROGRAMMEUR_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.printf("Id           : %d%n", id);
                System.out.printf("Nom          : %s%n", rs.getString("nom"));
                System.out.printf("Prénom       : %s%n", rs.getString("prenom"));
                System.out.printf("Adresse      : %s%n", rs.getString("adresse"));
                System.out.printf("Pseudo       : %s%n", rs.getString("pseudo"));
                System.out.printf("Responsable  : %s%n", rs.getString("responsable"));
                System.out.printf("Hobby        : %s%n", rs.getString("hobby"));
                System.out.printf("Naissance    : %d%n", rs.getInt("annee"));
                System.out.printf("Salaire      : %.1f%n", rs.getFloat("salaire"));
                System.out.printf("Prime        : %.1f%n", rs.getFloat("prime"));
                System.out.println("-------------------------------------------------");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des programmeurs.");
        }
        return false;
    }

    /**
     * Supprime un programmeur de la base de données.
     *
     * @param id L'identifiant du programmeur.
     * @return {@code true} si la suppression a réussi, sinon {@code false}.
     * @author Sarusman
     */
    @Override
    public boolean supprimerProgrammeur(int id) {
        try (Connection co = actionsBD.ouvrirConnexion(); PreparedStatement stmt = co.prepareStatement(DELETE_PROGRAMMEUR)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la suppression du programmeur.");
        }
        return false;
    }

    /**
     * Ajoute un nouveau programmeur à la base de données.
     *
     * @param programmeur Une instance de {@link Programmeur} contenant les informations du programmeur.
     * @author Sahkana
     */
    @Override
    public void ajouterProgrammeur(Programmeur programmeur) {
        Responsable responsable = programmeur.getResponsable();
        String responsableComplet = responsable.getPrenom() + " " + responsable.getNom();

        try (Connection co = actionsBD.ouvrirConnexion(); PreparedStatement stmt = co.prepareStatement(INSERT_PROGRAMMEUR)) {
            stmt.setString(1, programmeur.getNom());
            stmt.setString(2, programmeur.getPrenom());
            stmt.setString(3, programmeur.getAdresse());
            stmt.setString(4, programmeur.getPseudo());
            stmt.setString(5, responsableComplet);
            stmt.setString(6, programmeur.getHobby());
            stmt.setInt(7, programmeur.getAnneeNaissance());
            stmt.setDouble(8, programmeur.getSalaire());
            stmt.setDouble(9, programmeur.getPrime());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("\u001B[32mAJOUT REUSSI !\u001B[0m");
            } else {
                System.out.println("Erreur lors de l'ajout du programmeur.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du programmeur.");
        }
        ajouterResponsable(responsable);
    }

    /**
     * Modifie le salaire d'un programmeur.
     *
     * @param id      L'identifiant du programmeur.
     * @param salaire Le nouveau salaire.
     * @author Priyank
     */
    @Override
    public void modifierSalaire(int id, double salaire) {
        try (Connection co = actionsBD.ouvrirConnexion(); PreparedStatement stmt = co.prepareStatement(UPDATE_SALAIRE)) {
            stmt.setDouble(1, salaire);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("MODIFICATION REUSSIE !");
            } else {
                System.out.println("Erreur lors de la modification du programmeur.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la modification du salaire.");
        }
    }

    /**
     * Vérifie l'existence d'un programmeur spécifique.
     *
     * @param id L'identifiant du programmeur.
     * @return {@code true} si le programmeur existe, sinon {@code false}.
     * @author Priyank
     */
    public boolean getProgrammeur(int id) {
        try (Connection co = actionsBD.ouvrirConnexion(); PreparedStatement stmt = co.prepareStatement(SELECT_PROGRAMMEUR_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération du programmeur.");
        }
        return false;
    }

    /**
     * Vérifie l'existence de programmeurs dans la base de données.
     *
     * @return {@code true} si des programmeurs existent, sinon {@code false}.
     * @author Sarusman
     */
    public boolean getProgrammeurs() {
        try (Connection co = actionsBD.ouvrirConnexion(); PreparedStatement stmt = co.prepareStatement(SELECT_ALL_PROGRAMMEURS)) {
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des programmeurs.");
        }
        return false;
    }

    /**
     * Vérifie si un programmeur est un doublon en fonction du prénom, nom et pseudo.
     *
     * @param prenom Le prénom du programmeur.
     * @param nom    Le nom du programmeur.
     * @param pseudo Le pseudo du programmeur.
     * @return {@code true} si un doublon existe, sinon {@code false}.
     * @author Sahkana
     */
    public boolean checkDoublonProgrammeur(String prenom, String nom, String pseudo) {
        try (Connection co = actionsBD.ouvrirConnexion(); PreparedStatement stmt = co.prepareStatement(CHECK_DOUBlON_PROGRAMMEUR)) {
            stmt.setString(1, prenom);
            stmt.setString(2, nom);
            stmt.setString(3, pseudo);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Récupère l'ID du responsable à partir de son nom et prénom.
     *
     * @param nom    le nom du responsable
     * @param prenom le prénom du responsable
     * @return l'id du responsable s'il existe, -1 sinon
     * @author Sarusman
     */
    public int getResponsableId(String nom, String prenom) {
        try (Connection co = actionsBD.ouvrirConnexion(); PreparedStatement stmt = co.prepareStatement(GET_RESPONSABLE_ID)) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Ajoute un responsable si nécessaire et retourne son id.
     *
     * @param responsable L'objet Responsable.
     * @return L'ID du responsable.
     * @author Sahkana
     */
    public int ajouterResponsable(Responsable responsable) {
        Connection co = null;
        try {
            co = actionsBD.ouvrirConnexion();
            co.setAutoCommit(false); // Au cas où il y a une erreur lors de l'ajout d'un responsable, on doit annuler l'ajout et revenir à la bdd de départ
            try (PreparedStatement checkStmt = co.prepareStatement(CHECK_RESPONSABLE_EXISTS)) {
                checkStmt.setString(1, responsable.getPrenom());
                checkStmt.setString(2, responsable.getNom());
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    co.commit();
                    return rs.getInt("id");
                }
            }

            try (PreparedStatement insertStmt = co.prepareStatement(INSERT_RESPONSABLE)) {
                insertStmt.setString(1, responsable.getPrenom());
                insertStmt.setString(2, responsable.getNom());
                ResultSet inserted = insertStmt.executeQuery();
                if (inserted.next()) {
                    int newId = inserted.getInt("id");
                    co.commit();
                    return newId;
                }
            }

        } catch (SQLException e) {
            if (co != null) {
                try {
                    co.rollback();
                } catch (SQLException ex) {
                    System.err.println("Erreur lors du rollback");
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }
}
    