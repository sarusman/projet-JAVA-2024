public interface ActionBDD {
    public void afficherProgrammeurs();
    public boolean afficherProgrammeur(int id);
    public void supprimerProgrammeur();
    public void ajouterProgrammeur(Programmeur programmeur);
    public void modifierSalaire(int id, float salaire);
}
