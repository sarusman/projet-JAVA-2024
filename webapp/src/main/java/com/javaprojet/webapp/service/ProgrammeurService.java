package com.javaprojet.webapp.service;

import com.javaprojet.webapp.model.Programmeur;
import com.javaprojet.webapp.repository.ProgrammeurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return programmeurRepository.findAll();
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
        double salaireMoyen = programmeurs.stream().mapToDouble(Programmeur::getSalaire).average().orElse(0);
        double salaireMin = programmeurs.stream().mapToDouble(Programmeur::getSalaire).min().orElse(0);
        double salaireMax = programmeurs.stream().mapToDouble(Programmeur::getSalaire).max().orElse(0);

        // répartition des programmeurs par année de naissance
        Map<Integer, Long> repartitionAnnee = programmeurs.stream()
                .collect(Collectors.groupingBy(Programmeur::getAnnee, Collectors.counting()));

        // résultat dans une Map
        return Map.of(
                "totalProgrammeurs", totalProgrammeurs,
                "salaireMoyen", salaireMoyen,
                "salaireMin", salaireMin,
                "salaireMax", salaireMax,
                "repartitionAnnee", repartitionAnnee
        );
    }
}
