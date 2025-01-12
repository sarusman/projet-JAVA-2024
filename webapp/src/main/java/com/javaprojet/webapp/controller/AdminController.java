package com.javaprojet.webapp.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur pour gérer les actions d'administration liées à la connexion et à la déconnexion des utilisateurs.
 *
 * Ce contrôleur permet d'afficher la page de connexion, de gérer la soumission du formulaire de connexion et de gérer la déconnexion des utilisateurs.
 *
 * @author Sarusman
 */
@Controller
public class AdminController {

    /**
     * Affiche la page de connexion.
     *
     * Cette méthode est appelée lors de la requête GET sur la page de connexion. Elle affiche le formulaire de connexion.
     *
     * @return Le nom de la vue à afficher (login).
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    /**
     * Gère la soumission du formulaire de connexion.
     *
     * Cette méthode est appelée lors de la soumission du formulaire de connexion. Elle vérifie si le nom d'utilisateur
     * et le mot de passe sont corrects. Si la connexion est réussie, l'utilisateur est redirigé vers la page des responsables.
     * Si la connexion échoue, un message d'erreur est affiché sur la page de connexion.
     *
     * @param username Le nom d'utilisateur saisi par l'utilisateur.
     * @param password Le mot de passe saisi par l'utilisateur.
     * @param model Le modèle pour ajouter des attributs à la vue.
     * @return La redirection vers la page des responsables si la connexion est réussie, sinon la page de connexion avec un message d'erreur.
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if ("toto".equals(username) && "tutu".equals(password)) {
            return "redirect:/responsables";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

    /**
     * Gère la déconnexion de l'utilisateur.
     *
     * Cette méthode est appelée lors de la requête GET sur la page de déconnexion. Elle redirige l'utilisateur vers la page de connexion.
     *
     * @return La redirection vers la page de connexion.
     */
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}