package com.javaprojet.webapp.service;

import com.javaprojet.webapp.model.Responsable;
import com.javaprojet.webapp.model.Programmeur;
import com.javaprojet.webapp.repository.ResponsableRepository;
import com.javaprojet.webapp.repository.ProgrammeurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * La classe ProgrammeurService permet de gérer les opérations liées aux programmeurs.
 *
 * Cette classe contient des méthodes permettant de réaliser les actions créer, afficher, mettre à jour et supprimer
 * sur les programmeurs ainsi que des statistiques sur les programmeurs. Elle est annotée avec {@link Service} pour
 * indiquer qu'il s'agit d'un service Spring géré par le conteneur Spring.
 *
 * @author Priyank
 */
@Service
@Transactional
public class ProgrammeurService {

    private final ProgrammeurRepository programmeurRepository;
    private final ResponsableRepository responsableRepository;

    /**
     * Constructeur pour les repositories nécessaires dans le service.
     *
     * @param programmeurRepository Le repository utilisé pour accéder aux données des programmeurs.
     * @param responsableRepository Le repository utilisé pour accéder aux données des responsables.
     */
    @Autowired
    public ProgrammeurService(ProgrammeurRepository programmeurRepository, ResponsableRepository responsableRepository) {
        this.programmeurRepository = programmeurRepository;
        this.responsableRepository = responsableRepository;
    }

    /**
     * Récupère la liste de tous les programmeurs triée par identifiant.
     *
     * Cette méthode appelle la méthode {@link ProgrammeurRepository#findAll(Sort)} pour obtenir tous les programmeurs
     * enregistrés dans la base de données triés par leur identifiant.
     *
     * @return Une liste de programmeurs triée par identifiant.
     */
    public List<Programmeur> afficherProgrammeurs() {
        return programmeurRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    /**
     * Sauvegarde un programmeur dans la base de données.
     *
     * Cette méthode appelle la méthode save(Programmeur) pour enregistrer un programmeur.
     *
     * @param programmeur Le programmeur à sauvegarder.
     */
    public void saveProgrammeur(Programmeur programmeur) {
        programmeurRepository.save(programmeur);
    }

    /**
     * Trouve un programmeur par son identifiant.
     *
     * Cette méthode appelle la méthode ProgrammeurRepository.findById(int) pour trouver un programmeur
     * correspondant à l'identifiant fourni.
     *
     * @param id L'identifiant du programmeur à rechercher.
     * @return Le programmeur correspondant à l'identifiant, ou null si aucun programmeur n'est trouvé.
     */
    public Programmeur findProgrammeurById(int id) {
        return programmeurRepository.findById(id).orElse(null);
    }

    /**
     * Supprime un programmeur par son identifiant.
     *
     * Cette méthode appelle la méthode ProgrammeurRepository.deleteById(int) pour supprimer un programmeur
     * de la base de données en utilisant son identifiant.
     *
     * @param id L'identifiant du programmeur à supprimer.
     */
    public void deleteProgrammeurById(int id) {
        programmeurRepository.deleteById(id);
    }

    /**
     * Calcule les statistiques sur les programmeurs.
     *
     * Cette méthode calcule des statistiques telles que le nombre total de programmeurs, le salaire moyen, minimum
     * et maximum ainsi que la répartition des programmeurs par année de naissance.
     *
     * @return Une Map contenant les statistiques : total des programmeurs, salaire moyen, salaire minimum,
     *         salaire maximum et répartition par année de naissance.
     */
    public Map<String, Object> getStatistiques() {
        List<Programmeur> programmeurs = programmeurRepository.findAll();

        // calcul du nombre total de programmeurs
        long totalProgrammeurs = programmeurs.size();

        // calcul des salaires moyen, minimum et maximum
        DecimalFormat df = new DecimalFormat("#.##");
        double salaireMoyen = programmeurs.stream().mapToDouble(Programmeur::getSalaire).average().orElse(0);
        double salaireMin = programmeurs.stream().mapToDouble(Programmeur::getSalaire).min().orElse(0);
        double salaireMax = programmeurs.stream().mapToDouble(Programmeur::getSalaire).max().orElse(0);

        // formatage des salaires
        String salaireMoyenStr = df.format(salaireMoyen);
        String salaireMinStr = df.format(salaireMin);
        String salaireMaxStr = df.format(salaireMax);

        // répartition des programmeurs
        Map<Integer, Long> repartitionAnnee = new TreeMap<>(
                programmeurs.stream()
                        .collect(Collectors.groupingBy(Programmeur::getAnnee, Collectors.counting()))
        );

        // retourne les statistiques dans une map
        return Map.of(
                "totalProgrammeurs", totalProgrammeurs,
                "salaireMoyen", salaireMoyenStr,
                "salaireMin", salaireMinStr,
                "salaireMax", salaireMaxStr,
                "repartitionAnnee", repartitionAnnee
        );
    }

    /**
     * Recherche un programmeur par son prénom et son nom.
     *
     * Cette méthode utilise {@link ProgrammeurRepository#findByPrenomAndNom(String, String)} pour rechercher un
     * programmeur dans la base de données en fonction de son prénom et de son nom.
     *
     * @param prenom Le prénom du programmeur à rechercher.
     * @param nom Le nom du programmeur à rechercher.
     * @return Le programmeur correspondant, ou null si aucun programmeur n'est trouvé.
     */
    public Programmeur findByPrenomAndNom(String prenom, String nom) {
        return programmeurRepository.findByPrenomAndNom(prenom, nom);
    }

    /**
     * Recherche des programmeurs en fonction de plusieurs critères.
     *
     * Cette méthode permet de filtrer les programmeurs en fonction de différents critères tels que le nom, le prénom,
     * le salaire minimum, le responsable et l'adresse.
     *
     * @param nom Le nom du programmeur à rechercher.
     * @param prenom Le prénom du programmeur à rechercher.
     * @param salaireMin Le salaire minimum des programmeurs à rechercher.
     * @param responsable Le responsable des programmeurs à rechercher.
     * @param adresse L'adresse des programmeurs à rechercher.
     * @return Une liste de programmeurs correspondant aux critères de recherche.
     */
    public List<Programmeur> searchProgrammeurs(String nom, String prenom, Double salaireMin, String responsable, String adresse) {
        List<Programmeur> programmeurs = programmeurRepository.findAll();

        // recherche par nom
        if (nom != null && !nom.isEmpty()) {
            programmeurs = programmeurs.stream()
                    .filter(programmeur -> programmeur.getNom().equalsIgnoreCase(nom))
                    .collect(Collectors.toList());
        }

        // recherche par prénom
        if (prenom != null && !prenom.isEmpty()) {
            programmeurs = programmeurs.stream()
                    .filter(programmeur -> programmeur.getPrenom().equalsIgnoreCase(prenom))
                    .collect(Collectors.toList());
        }

        // recherche par salaire minimum
        if (salaireMin != null) {
            programmeurs = programmeurs.stream()
                    .filter(programmeur -> programmeur.getSalaire() >= salaireMin)
                    .collect(Collectors.toList());
        }

        // recherche par responsable
        if (responsable != null && !responsable.isEmpty()) {
            programmeurs = programmeurs.stream()
                    .filter(programmeur -> programmeur.getResponsable().equalsIgnoreCase(responsable))
                    .collect(Collectors.toList());
        }

        // recherche par adresse
        if (adresse != null && !adresse.isEmpty()) {
            programmeurs = programmeurs.stream()
                    .filter(programmeur -> programmeur.getAdresse().equalsIgnoreCase(adresse))
                    .collect(Collectors.toList());
        }

        return programmeurs;
    }
}