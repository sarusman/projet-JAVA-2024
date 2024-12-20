package com.javaprojet.webapp.service;

import com.javaprojet.webapp.model.Programmeur;
import com.javaprojet.webapp.repository.ProgrammeurRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProgrammeurService {
    private final ProgrammeurRepository programmeurRepository;

    public ProgrammeurService( ProgrammeurRepository programmeurRepository) {
        this.programmeurRepository = programmeurRepository;
    }

    // récupérer tous les programmeurs
    public List<Programmeur> afficherProgrammeurs() {
        return programmeurRepository.findAll();
    }

    // sauvegarder les programmeurs
    public void saveProgrammeur(Programmeur programmeur) {
        programmeurRepository.save(programmeur);
    }

    // trouver le programmeur à partir de l'id
    public Programmeur findProgrammeurById(int id) {
        return programmeurRepository.findById(id).get();
    }


    // supprimer le programmeur par son id
    public void deleteProgrammeurById(int id) {
        programmeurRepository.deleteById(id);
    }
}
