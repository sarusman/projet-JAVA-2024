package com.javaprojet.webapp.controller;

import com.javaprojet.webapp.service.ProgrammeurService;
import org.springframework.ui.Model;
import com.javaprojet.webapp.model.Programmeur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ProgrammeurController {

    private final ProgrammeurService programmeurService;
    public ProgrammeurController(ProgrammeurService programmeurService) {
        this.programmeurService = programmeurService;
    }

    // GET
    @GetMapping("/")
    public String home(Model model) {
        Iterable<Programmeur> listProgrammeur = programmeurService.afficherProgrammeurs();
        model.addAttribute("programmeurs", listProgrammeur);
        return "home";
    }

    @GetMapping("/addProgrammeur")
    public String afficherFormAjoutProgrammeur() {
        return "addProgrammeurForm";
    }

    @GetMapping("/updateProgrammeur")
    public String afficherFormModifierProgrammeur(Model model, @RequestParam("id") int id) {
        Programmeur programmeur = programmeurService.findProgrammeurById(id);
        model.addAttribute("programmeur", programmeur);
        return "updateProgrammeurForm";
    }

    @GetMapping("/deleteProgrammeur")
    public String deleteFormProgrammeur(Model model, @RequestParam("id") int id) {
        Programmeur programmeur = programmeurService.findProgrammeurById(id);
        model.addAttribute("programmeur", programmeur);
        return "deleteProgrammeurPage";
    }

    // POST
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
            @RequestParam Double prime,
            Model model
    ) {
        // Vérification des doublons
        Programmeur existingProgrammeur = programmeurService.findByPrenomAndNom(prenom, nom);
        if (existingProgrammeur != null) {
            model.addAttribute("error", "Un programmeur avec ce prénom et nom existe déjà.");
            return "addProgrammeurForm";
        }

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
        programmeurService.saveProgrammeur(programmeur);
        return "redirect:/";
    }

    @PostMapping("/modifier")
    public String modifierProgrammeur(
          @RequestParam String id,
          @RequestParam String prenom,
          @RequestParam String nom,
          @RequestParam String adresse,
          @RequestParam String pseudo,
          @RequestParam String responsable,
          @RequestParam String hobby,
          @RequestParam Integer annee,
          @RequestParam Double salaire,
          @RequestParam Double prime) {
        Programmeur p = programmeurService.findProgrammeurById(Integer.parseInt(id));
        p.setPrenom(prenom);
        p.setNom(nom);
        p.setAdresse(adresse);
        p.setPseudo(pseudo);
        p.setResponsable(responsable);
        p.setHobby(hobby);
        p.setAnnee(annee);
        p.setSalaire(salaire);
        p.setPrime(prime);
        programmeurService.saveProgrammeur(p);
        return "redirect:/";
    }

    @PostMapping("/supprimer")
    public String supprimerProgrammeur(@RequestParam String id) {
        programmeurService.deleteProgrammeurById(Integer.parseInt(id));
        return "redirect:/";
    }

    // GET pour les statistiques
    @GetMapping("/statistiques")
    public String afficherStatistiques(Model model) {
        var statistiques = programmeurService.getStatistiques();
        model.addAttribute("statistiques", statistiques);
        return "statistiques";
    }

    @GetMapping("/searchProgrammeurs")
    public String searchProgrammeurs(@RequestParam(required = false) String nom,
                                     @RequestParam(required = false) String prenom,
                                     @RequestParam(required = false) Double salaireMin,
                                     @RequestParam(required = false) String responsable,
                                     @RequestParam(required = false) String adresse, Model model) {
        List<Programmeur> filteredProgrammeurs = programmeurService.searchProgrammeurs(nom, prenom, salaireMin, responsable, adresse);
        model.addAttribute("programmeurs", filteredProgrammeurs);
        return "home";
    }

}
