package com.javaprojet.webapp.service;

import com.javaprojet.webapp.model.Programmeur;
import com.javaprojet.webapp.repository.ProgrammeurRepository;

import java.util.List;

public class ProgrammeurService {
    private final ProgrammeurRepository programmeurRepository;

    public ProgrammeurService(ProgrammeurRepository programmeurRepository) {
        this.programmeurRepository = programmeurRepository;
    }

    public List<Programmeur> afficherProduits() {
        return programmeurRepository.findAll();
    }
}
