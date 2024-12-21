package com.javaprojet.webapp.service;

import com.javaprojet.webapp.model.Programmeur;
import com.javaprojet.webapp.repository.ProgrammeurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.text.DecimalFormat;

@Service
@Transactional
public class ProgrammeurService {
    private final ProgrammeurRepository programmeurRepository;

    @Autowired
    public ProgrammeurService(ProgrammeurRepository programmeurRepository) {
        this.programmeurRepository = programmeurRepository;
    }

    // récupérer tous les programmeurs
    public List<Programmeur> afficherProgrammeurs() {
        return programmeurRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }


    // sauvegarde un programmeur
    public void saveProgrammeur(Programmeur programmeur) {
        programmeurRepository.save(programmeur);
    }

    // trouver le programmeur à partir de l'id
    public Programmeur findProgrammeurById(int id) {
        return programmeurRepository.findById(id).orElse(null);
    }

    // supprimer le programmeur par son id
    public void deleteProgrammeurById(int id) {
        programmeurRepository.deleteById(id);
    }

    // calculs des statistiques
    public Map<String, Object> getStatistiques() {
        List<Programmeur> programmeurs = programmeurRepository.findAll();

        // tous les programmeurs
        long totalProgrammeurs = programmeurs.size();

        // salaire moyen, minimum et maximum
        DecimalFormat df = new DecimalFormat("#.##");
        double salaireMoyen = programmeurs.stream().mapToDouble(Programmeur::getSalaire).average().orElse(0);
        double salaireMin = programmeurs.stream().mapToDouble(Programmeur::getSalaire).min().orElse(0);
        double salaireMax = programmeurs.stream().mapToDouble(Programmeur::getSalaire).max().orElse(0);

        // Formatage des salaires
        String salaireMoyenStr = df.format(salaireMoyen);
        String salaireMinStr = df.format(salaireMin);
        String salaireMaxStr = df.format(salaireMax);

        // répartition des programmeurs par année de naissance
        Map<Integer, Long> repartitionAnnee = programmeurs.stream()
                .collect(Collectors.groupingBy(Programmeur::getAnnee, Collectors.counting()));

        // résultat dans une Map
        return Map.of(
                "totalProgrammeurs", totalProgrammeurs,
                "salaireMoyen", salaireMoyenStr,
                "salaireMin", salaireMinStr,
                "salaireMax", salaireMaxStr,
                "repartitionAnnee", repartitionAnnee
        );
    }

    public Programmeur findByPrenomAndNom(String prenom, String nom) {
        return programmeurRepository.findByPrenomAndNom(prenom, nom);
    }


    public List<Programmeur> searchProgrammeurs(String nom, String prenom, Double salaireMin, String responsable, String adresse) {
        List<Programmeur> programmeurs = programmeurRepository.findAll();

        // recherche à partir du nom
        if (nom != null && !nom.isEmpty()) {
            programmeurs = programmeurs.stream()
                    .filter(programmeur -> programmeur.getNom().equalsIgnoreCase(nom))
                    .collect(Collectors.toList());
        }

        // recherche à partir du prénom
        if (prenom != null && !prenom.isEmpty()) {
            programmeurs = programmeurs.stream()
                    .filter(programmeur -> programmeur.getPrenom().equalsIgnoreCase(prenom))
                    .collect(Collectors.toList());
        }

        // recherche à partir du salaire
        if (salaireMin != null) {
            programmeurs = programmeurs.stream()
                    .filter(programmeur -> programmeur.getSalaire() >= salaireMin)
                    .collect(Collectors.toList());
        }

        // recherche à partir responsable
        if (responsable != null && !responsable.isEmpty()) {
            programmeurs = programmeurs.stream()
                    .filter(programmeur -> programmeur.getResponsable().equalsIgnoreCase(responsable))
                    .collect(Collectors.toList());
        }

        // recherche à partir de l'adresse
        if (adresse != null && !adresse.isEmpty()) {
            programmeurs = programmeurs.stream()
                    .filter(programmeur -> programmeur.getAdresse().equalsIgnoreCase(adresse))
                    .collect(Collectors.toList());
        }

        return programmeurs;
    }


}
