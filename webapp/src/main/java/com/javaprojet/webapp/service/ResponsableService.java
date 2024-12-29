package com.javaprojet.webapp.service;

import com.javaprojet.webapp.model.Responsable;
import com.javaprojet.webapp.repository.ResponsableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsableService {

    private final ResponsableRepository responsableRepository;

    public ResponsableService(ResponsableRepository responsableRepository) {
        this.responsableRepository = responsableRepository;
    }

    public List<Responsable> afficherResponsables() {
        return responsableRepository.findAll();
    }
}