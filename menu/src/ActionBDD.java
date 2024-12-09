import java.sql.SQLException;

public interface ActionBDD {
    void afficherProgrammeurs() throws SQLException;
    boolean afficherProgrammeur(int id);
    boolean supprimerProgrammeur(int id);
    void ajouterProgrammeur(Programmeur programmeur);
    void modifierSalaire(int id, double salaire);
}
