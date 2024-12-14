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

    //get all programmeurs
    public List<Programmeur> afficherProgrammeurs() {
        return programmeurRepository.findAll();
    }

    //save programmeur
    public void saveProgrammeur(Programmeur programmeur) {
        programmeurRepository.save(programmeur);
    }

    //find programmeur by id
    public Programmeur findProgrammeurById(int id) {
        return programmeurRepository.findById(id).get();
    }


    //delete programmeur by id
    public void deleteProgrammeurById(int id) {
        programmeurRepository.deleteById(id);
    }
}
