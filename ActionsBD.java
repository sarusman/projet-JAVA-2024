import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActionsBD {
    private static final String URL = "jdbc:postgresql://aws-0-eu-west-3.pooler.supabase.com:6543/postgres?user=postgres.fcckpwkbrkquitlmuxon&password=Prysasha2024!";


    public Connection ouvrirConnexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void main(String[] args) {
        ActionsBD actionsBD = new ActionsBD();
        String query = "INSERT INTO Programmeurs (id, nom, prenom, adresse, pseudo, responsable, hobby, annee, salaire, prime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection co = actionsBD.ouvrirConnexion()) {
            System.out.println(co + " connecté.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection co = actionsBD.ouvrirConnexion();
             PreparedStatement stmt = co.prepareStatement(query)) {

            stmt.setInt(1, 1);
            stmt.setString(2, "SOLANKI");
            stmt.setString(3, "Priyank");
            stmt.setString(4, "5 Avenue de l'or");
            stmt.setString(5, "xmeller");
            stmt.setString(6, "moi");
            stmt.setString(7, "Chanter");
            stmt.setInt(8, 2003);
            stmt.setFloat(9, 4000);
            stmt.setFloat(10, 50);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " row(s) inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
