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


    public List<Programmeur> afficherProgrammeurs() {
        return programmeurRepository.findAll();
    }
}
