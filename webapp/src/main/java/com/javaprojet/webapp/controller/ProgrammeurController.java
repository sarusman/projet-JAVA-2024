package com.javaprojet.webapp.controller;

import com.javaprojet.webapp.repository.ProgrammeurRepository;
import com.javaprojet.webapp.service.ProgrammeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.javaprojet.webapp.model.Programmeur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProgrammeurController {

    private final ProgrammeurService programmeurService;
    public ProgrammeurController(ProgrammeurService programmeurService) {
        this.programmeurService = programmeurService;
    }

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Programmeur> listProgrammeur = programmeurService.afficherProgrammeurs();
        model.addAttribute("programmeurs", listProgrammeur);
        return "home";
    }

    @GetMapping("/addProgrammeur")
    public String afficherFormAjoutProgrammeur(Model model) {
        return "addProgrammeurForm";
    }

    // Traiter le formulaire
    @PostMapping("/ajouter")
    public String ajouterProgrammeur(
            @RequestParam String prenom,
            @RequestParam String nom,
            @RequestParam String adresse,
            @RequestParam String pseudo,
            @RequestParam String responsable,
            @RequestParam String hobby,
            @RequestParam Integer annee,
            @RequestParam Double salaire,
            @RequestParam Double prime
    ) {
        // Créer un nouvel objet Programmeur
        Programmeur programmeur = new Programmeur();
        programmeur.setPrenom(prenom);
        programmeur.setNom(nom);
        programmeur.setAdresse(adresse);
        programmeur.setPseudo(pseudo);
        programmeur.setResponsable(responsable);
        programmeur.setHobby(hobby);
        programmeur.setAnnee(annee);
        programmeur.setSalaire(salaire);
        programmeur.setPrime(prime);

        // Enregistrer le programmeur dans la base
        programmeurService.ajouterProgrammeur(programmeur);

        // Redirection après soumission
        return "redirect:/";
    }
}
