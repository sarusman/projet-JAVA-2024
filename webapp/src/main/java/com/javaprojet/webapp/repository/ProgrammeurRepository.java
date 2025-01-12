package com.javaprojet.webapp.repository;

import com.javaprojet.webapp.model.Programmeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour gérer les opérations CRUD sur les programmeurs.
 *
 * Cette interface étend {@link JpaRepository} et fournit des méthodes pour accéder et manipuler les données des programmeurs.
 * Elle inclut une méthode personnalisée pour rechercher un programmeur par son prénom et son nom.
 *
 * @author Priyank
 */
@Repository
public interface ProgrammeurRepository extends JpaRepository<Programmeur, Integer> {

    /**
     * Recherche un programmeur par son prénom et son nom.
     *
     * Cette méthode permet de trouver un programmeur spécifique en fonction de son prénom et de son nom.
     *
     * @param prenom Le prénom du programmeur à rechercher.
     * @param nom Le nom du programmeur à rechercher.
     * @return Le programmeur correspondant aux critères, ou null si aucun programmeur n'est trouvé.
     */
    public Programmeur findByPrenomAndNom(String prenom, String nom);
}
