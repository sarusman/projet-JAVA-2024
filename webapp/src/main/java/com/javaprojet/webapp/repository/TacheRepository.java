package com.javaprojet.webapp.repository;

import com.javaprojet.webapp.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepository extends JpaRepository<Tache, Integer> {
}
