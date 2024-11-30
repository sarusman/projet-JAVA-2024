import java.sql.*;

public class ActionsBDDImpl implements ActionBDD {

    private static final String URL = "jdbc:postgresql://aws-0-eu-west-3.pooler.supabase.com:6543/postgres?user=postgres.fcckpwkbrkquitlmuxon&password=Prysasha2024!";


    public Connection ouvrirConnexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    @Override
    public void afficherProgrammeurs() {
        ActionsBDDImpl actionsBD = new ActionsBDDImpl();
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

    @Override
    public void afficherProgrammeur(int id) {

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
}
