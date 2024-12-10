package com.javaprojet.webapp.controller;

import com.javaprojet.webapp.repository.ProgrammeurRepository;
import com.javaprojet.webapp.service.ProgrammeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.javaprojet.webapp.model.Programmeur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProgrammeurController {

    private final ProgrammeurService programmeurService;
    public ProgrammeurController(ProgrammeurService programmeurService) {
        this.programmeurService = programmeurService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        Iterable<Programmeur> listProgrammeur = programmeurService.afficherProgrammeurs();
        model.addAttribute("programmeurs", listProgrammeur);
        return "home";
    }

    @GetMapping("/addProgrammeur")
    public String afficherFormAjoutProgrammeur(Model model) {
        model.addAttribute("programmeur", new Programmeur());
        return "addProgrammeurForm";
    }
}
