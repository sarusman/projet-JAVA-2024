import javax.management.Query;
import java.sql.*;

public class ActionsBDDImpl implements ActionBDD {

    private static final String URL = "jdbc:postgresql://aws-0-eu-west-3.pooler.supabase.com:6543/postgres?user=postgres.fcckpwkbrkquitlmuxon&password=Prysasha2024!";
    private static final ActionsBDDImpl actionsBD = new ActionsBDDImpl();

    public Connection ouvrirConnexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    @Override
    public void afficherProgrammeurs() {
        String query = "SELECT * FROM Programmeurs";
        try ( Connection co = actionsBD.ouvrirConnexion(); Statement stmt = co.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
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

    @Override
    public boolean afficherProgrammeur(int id) {
        String query = "SELECT * FROM Programmeurs WHERE id = " + id;
        try (Connection co = actionsBD.ouvrirConnexion(); Statement stmt = co.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
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

    @Override
    public boolean supprimerProgrammeur(int id) {
        String query = "DELETE FROM Programmeurs WHERE id = " + id;
        try (Connection co = actionsBD.ouvrirConnexion(); Statement stmt = co.createStatement(); ) {
            int rs = stmt.executeUpdate(query);
            if(rs > 0){
                System.out.println("SUPRESSION REUSSI !");
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des programmeurs.");
        }
        return false;
    }

    @Override
    public void ajouterProgrammeur(Programmeur programmeur) {
        String query = "INSERT INTO Programmeurs (nom, prenom, adresse, pseudo, responsable, hobby, annee, salaire, prime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection co = actionsBD.ouvrirConnexion(); PreparedStatement stmt = co.prepareStatement(query); ) {
                stmt.setString(1, programmeur.getNom());
                stmt.setString(2, programmeur.getPrenom());
                stmt.setString(3, programmeur.getAdresse());
                stmt.setString(4, programmeur.getPseudo());
                stmt.setString(5, programmeur.getResponsable());
                stmt.setString(6, programmeur.getHobby());
                stmt.setInt(7, programmeur.getAnneeNaissance());
                stmt.setDouble(8, programmeur.getSalaire());
                stmt.setDouble(9, programmeur.getPrime());
                int rows = stmt.executeUpdate();
                if(rows > 0) {
                    System.out.println("AJOUT REUSSI !");
                }else{
                    System.out.println("ERREUR lors de l'ajout de programmeur");
                }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des programmeurs.");
        }
    }

    @Override
    public void modifierSalaire(int id, double salaire) {
        String queryUpdate = "UPDATE Programmeurs SET salaire = ? WHERE id = " + id;
        try (Connection co = actionsBD.ouvrirConnexion(); PreparedStatement stmt = co.prepareStatement(queryUpdate); ) {
            stmt.setDouble(1, salaire);
            int rows = stmt.executeUpdate();
            if(rows > 0) {
                System.out.println("MODIFICATION REUSSIE !");
            }else{
                System.out.println("ERREUR lors de la modification du programmeur");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des programmeurs.");
        }
    }


    public boolean getProgrammeur(int id) {
        String querySelect = "SELECT * FROM Programmeurs WHERE id = " + id;
        try (Connection co = actionsBD.ouvrirConnexion(); PreparedStatement stmt = co.prepareStatement(querySelect); ) {
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des programmeurs.");
        }
        return false;
    }

    public boolean getProgrammeurs() {
        String querySelect = "SELECT * FROM Programmeurs";
        try (Connection co = actionsBD.ouvrirConnexion(); PreparedStatement stmt = co.prepareStatement(querySelect); ) {
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des programmeurs.");
        }
        return false;
    }

}
