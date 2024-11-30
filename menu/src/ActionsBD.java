import java.sql.*;

//Classe test à supprimer après
public class ActionsBD {
    private static final String URL = "jdbc:postgresql://aws-0-eu-west-3.pooler.supabase.com:6543/postgres?user=postgres.fcckpwkbrkquitlmuxon&password=Prysasha2024!";


    public Connection ouvrirConnexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void insertionProgrammeur(String nom, String prenom, String adresse, String pseudo, String responsable, String hobby, int annee, float salaire, float prime){
        ActionsBD actionsBD = new ActionsBD();
        String query = "INSERT INTO Programmeurs (nom, prenom, adresse, pseudo, responsable, hobby, annee, salaire, prime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection co = actionsBD.ouvrirConnexion()) {
            System.out.println(co + " connecté.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection co = actionsBD.ouvrirConnexion();
             PreparedStatement stmt = co.prepareStatement(query)) {

            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, adresse);
            stmt.setString(4, pseudo);
            stmt.setString(5, responsable);
            stmt.setString(6, hobby);
            stmt.setInt(7, annee);
            stmt.setFloat(8, salaire);
            stmt.setFloat(9, prime);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " programmeur(s) inséré(s)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void afficherProgrammeurs() {
        ActionsBD actionsBD = new ActionsBD();
        String query = "SELECT * FROM Programmeurs";

        try (Connection co = actionsBD.ouvrirConnexion();
             Statement stmt = co.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\nListe des programmeurs :");
            System.out.println("-------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String pseudo = rs.getString("pseudo");
                String responsable = rs.getString("responsable");
                String hobby = rs.getString("hobby");
                int annee = rs.getInt("annee");
                float salaire = rs.getFloat("salaire");
                float prime = rs.getFloat("prime");

                System.out.printf("ID: %d | Nom: %s | Prénom: %s | Adresse: %s | Pseudo: %s | Responsable: %s | Hobby: %s | Année: %d | Salaire: %.2f | Prime: %.2f%n",
                        id, nom, prenom, adresse, pseudo, responsable, hobby, annee, salaire, prime);
            }

            System.out.println("-------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des programmeurs.");
        }
    }


    public static void main(String[] args) {
        afficherProgrammeurs();
    }


}
