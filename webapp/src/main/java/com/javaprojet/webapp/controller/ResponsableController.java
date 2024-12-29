package com.javaprojet.webapp.controller;

import com.javaprojet.webapp.service.ResponsableService;
import com.javaprojet.webapp.model.Responsable;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ResponsableController {

    private final ResponsableService responsableService;

    public ResponsableController(ResponsableService responsableService) {
        this.responsableService = responsableService;
    }

    @GetMapping("/responsables")
    public String afficherResponsables(Model model) {
        List<Responsable> responsables = responsableService.afficherResponsables();
        model.addAttribute("responsables", responsables);
        return "responsables";
    }

    @RequestMapping(value = "/addResponsable", method = RequestMethod.POST)
    public String addResponsable(@RequestParam String prenom, @RequestParam String nom, Model model) {
        Responsable existingResponsable = responsableService.findByPrenomAndNom(prenom, nom);

        if (existingResponsable != null) {
            model.addAttribute("errorMessage", "Un responsable avec ce prénom et nom existe déjà.");
            return "redirect:/responsables";
        }

        // Ajouter le responsable à la base de données
        Responsable newResponsable = new Responsable(prenom, nom);
        responsableService.save(newResponsable);

        return "redirect:/responsables";
    }


    @PostMapping("/deleteResponsable")
    public String deleteResponsable(@RequestParam Long id) {
        responsableService.deleteById(id);
        return "redirect:/responsables";
    }


}
