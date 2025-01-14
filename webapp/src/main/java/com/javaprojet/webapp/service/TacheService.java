package com.javaprojet.webapp.service;

import com.javaprojet.webapp.model.Tache;
import com.javaprojet.webapp.repository.TacheRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheService {

    private final TacheRepository tacheRepository;

    public TacheService(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    /**
     * Sauvegarde une nouvelle tâche.
     *
     * @param tache La tâche à sauvegarder.
     * @return La tâche sauvegardée.
     */
    public Tache save(Tache tache) {
        return tacheRepository.save(tache);
    }

    /**
     * Récupère toutes les tâches.
     *
     * @return La liste de toutes les tâches.
     */
    public List<Tache> findAll() {
        return tacheRepository.findAll();
    }

    /**
     * Récupère une tâche par son ID.
     *
     * @param id L'identifiant de la tâche.
     * @return Une tâche si elle existe, sinon null.
     */
    public Tache findById(int id) {
        Optional<Tache> tache = tacheRepository.findById(id);
        return tache.orElse(null);
    }

    /**
     * Supprime une tâche par son ID.
     *
     * @param id L'identifiant de la tâche à supprimer.
     */
    public void deleteById(int id) {
        tacheRepository.deleteById(id);
    }
}
