import java.sql.*;

public class ActionsBDDImpl implements ActionBDD {

    private static final String URL = "jdbc:postgresql://aws-0-eu-west-3.pooler.supabase.com:6543/postgres?user=postgres.fcckpwkbrkquitlmuxon&password=Prysasha2024!";
    private static ActionsBDDImpl actionsBD = new ActionsBDDImpl();

    public Connection ouvrirConnexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    @Override
    public void afficherProgrammeurs() {

        String query = "SELECT * FROM Programmeurs";

        try (Connection co = actionsBD.ouvrirConnexion(); Statement stmt = co.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

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

                System.out.printf("Id           : %d%n", id);
                System.out.printf("Nom          : %s%n", nom);
                System.out.printf("Prénom       : %s%n", prenom);
                System.out.printf("Adresse      : %s%n", adresse);
                System.out.printf("Pseudo       : %s%n", pseudo);
                System.out.printf("Responsable  : %s%n", responsable);
                System.out.printf("Hobby        : %s%n", hobby);
                System.out.printf("Naissance    : %d%n", annee);
                System.out.printf("Salaire      : %.1f%n", salaire);
                System.out.printf("Prime        : %.1f%n", prime);
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
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String pseudo = rs.getString("pseudo");
                String responsable = rs.getString("responsable");
                String hobby = rs.getString("hobby");
                int annee = rs.getInt("annee");
                float salaire = rs.getFloat("salaire");
                float prime = rs.getFloat("prime");
                System.out.printf("Id           : %d%n", id);
                System.out.printf("Nom          : %s%n", nom);
                System.out.printf("Prénom       : %s%n", prenom);
                System.out.printf("Adresse      : %s%n", adresse);
                System.out.printf("Pseudo       : %s%n", pseudo);
                System.out.printf("Responsable  : %s%n", responsable);
                System.out.printf("Hobby        : %s%n", hobby);
                System.out.printf("Naissance    : %d%n", annee);
                System.out.printf("Salaire      : %.1f%n", salaire);
                System.out.printf("Prime        : %.1f%n", prime);
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
    public void supprimerProgrammeur() {
        //TODO
    }

    @Override
    public void ajouterProgrammeur(Programmeur programmeur) {
        //TODO
    }

    @Override
    public void modifierSalaire(int id, float salaire) {
        //TODO
    }

    //remplacer par ajouterProgrammeur
    public static void insertionProgrammeur(String nom, String prenom, String adresse, String pseudo, String responsable, String hobby, int annee, float salaire, float prime){
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
}
