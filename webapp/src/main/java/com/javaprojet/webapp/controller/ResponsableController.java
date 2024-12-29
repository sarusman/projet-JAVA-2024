package com.javaprojet.webapp.controller;

import com.javaprojet.webapp.service.ResponsableService;
import com.javaprojet.webapp.model.Responsable;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
