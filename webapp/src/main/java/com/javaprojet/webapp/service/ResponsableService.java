package com.javaprojet.webapp.service;

import com.javaprojet.webapp.model.Responsable;
import com.javaprojet.webapp.repository.ResponsableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour gérer les opérations liées aux responsables.
 *
 * Cette classe contient les méthodes permettant d'effectuer actions (créer, afficher, mettre à jour et supprimer)
 * sur les responsables via le {@link ResponsableRepository}. Elle est annotée avec {@link Service} pour indiquer
 * qu'il s'agit d'un service Spring géré par le conteneur Spring.
 *
 * @author Sahkana
 */
@Service
public class ResponsableService {

    private final ResponsableRepository responsableRepository;

    /**
     * Constructeur pour le {@link ResponsableRepository} dans le service.
     *
     * @param responsableRepository Le repository utilisé pour accéder aux données des responsables.
     */
    public ResponsableService(ResponsableRepository responsableRepository) {
        this.responsableRepository = responsableRepository;
    }

    /**
     * Récupère la liste de tous les responsables.
     *
     * Cette méthode appelle la méthode {@link ResponsableRepository#findAll()} pour obtenir tous les responsables
     * enregistrés dans la base de données.
     *
     * @return Une liste de responsables.
     */
    public List<Responsable> afficherResponsables() {
        return responsableRepository.findAll();
    }

    /**
     * Sauvegarde un responsable dans la base de données.
     *
     * Cette méthode appelle la méthode save(Responsable) pour enregistrer un responsable.
     *
     * @param responsable Le responsable à sauvegarder.
     */
    public void save(Responsable responsable) {
        responsableRepository.save(responsable);
    }

    /**
     * Supprime un responsable par son identifiant.
     *
     * Cette méthode appelle la méthode deleteById(Long) pour supprimer un responsable
     * de la base de données en utilisant son identifiant.
     *
     * @param id L'identifiant du responsable à supprimer.
     */
    public void deleteById(Long id) {
        responsableRepository.deleteById(id);
    }

    /**
     * Recherche un responsable par son prénom et son nom.
     *
     * Cette méthode utilise la méthode {@link ResponsableRepository#findByPrenomAndNom(String, String)} pour trouver
     * un responsable ayant les mêmes prénom et nom dans la base de données.
     *
     * @param prenom Le prénom du responsable à rechercher.
     * @param nom Le nom du responsable à rechercher.
     * @return Le responsable correspondant, ou null si aucun responsable n'est trouvé.
     */
    public Responsable findByPrenomAndNom(String prenom, String nom) {
        return responsableRepository.findByPrenomAndNom(prenom, nom);
    }
}
