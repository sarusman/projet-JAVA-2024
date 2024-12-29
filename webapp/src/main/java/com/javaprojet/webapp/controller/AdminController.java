package com.javaprojet.webapp.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    // Affichage de la page de connexion
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Affiche la page de connexion (login.html)
    }

    // Traitement de la soumission du formulaire de connexion
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Vérification des identifiants simples
        if ("toto".equals(username) && "tutu".equals(password)) {
            // Connexion réussie, rediriger vers la page d'administration
            return "redirect:/responsables";  // Redirige vers la page d'administration
        } else {
            // Identifiants incorrects, afficher un message d'erreur
            model.addAttribute("error", true);
            return "login";  // Affiche à nouveau la page de connexion avec un message d'erreur
        }
    }

    // Affichage de la page d'administration (par exemple, liste des formateurs)
    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        // Vous pouvez récupérer ici la liste des formateurs ou autres informations
        return "admin";  // Affiche la page admin (admin.html)
    }

    @GetMapping("/logout")
    public String logout() {
        // Logique de déconnexion (ici, nous n'avons pas de session, mais vous pouvez ajouter une session si nécessaire)
        return "redirect:/login";  // Redirige vers la page de connexion
    }
}

