package com.javaprojet.webapp.repository;

import com.javaprojet.webapp.model.Programmeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgrammeurRepository extends JpaRepository<Programmeur, Integer> {

    public Programmeur findByPrenomAndNom(String prenom, String nom);

}
