package com.javaprojet.webapp.controller;

import com.javaprojet.webapp.service.ResponsableService;
import com.javaprojet.webapp.model.Responsable;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour gérer les opérations liées aux responsables.
 *
 * Ce contrôleur permet d'afficher, ajouter et supprimer des responsables. Il interagit avec le service {@link ResponsableService}
 * pour effectuer les actions nécessaires sur les données des responsables. Les méthodes définies ici sont responsables de
 * la gestion des requêtes HTTP pour afficher et manipuler les responsables dans l'application.
 *
 * @author Sahkana
 */
@Controller
public class ResponsableController {

    /**
     * Le service pour gérer les responsables.
     *
     * Ce service est utilisé pour interagir avec la base de données et effectuer des opérations CRUD sur les responsables.
     */
    private final ResponsableService responsableService;

    /**
     * Constructeur de la classe {@link ResponsableController}.
     *
     * @param responsableService Le service pour gérer les responsables.
     */
    public ResponsableController(ResponsableService responsableService) {
        this.responsableService = responsableService;
    }

    /**
     * Affiche la liste des responsables.
     *
     * Cette méthode est appelée lorsque l'utilisateur accède à la page des responsables. Elle récupère tous les responsables
     * de la base de données via le service {@link ResponsableService} et les ajoute au modèle pour être affichés dans la vue.
     *
     * @param model Le modèle pour ajouter des attributs à la vue.
     * @return Le nom de la vue à afficher (responsables).
     */
    @GetMapping("/responsables")
    public String afficherResponsables(Model model) {
        List<Responsable> responsables = responsableService.afficherResponsables();
        model.addAttribute("responsables", responsables);
        return "responsables";
    }

    /**
     * Ajoute un nouveau responsable.
     *
     * Cette méthode est appelée lors de la soumission du formulaire pour ajouter un responsable. Elle vérifie si un responsable
     * avec le même prénom et nom existe déjà dans la base de données. Si c'est le cas, un message d'erreur est affiché.
     * Sinon, un nouveau responsable est créé et ajouté à la base de données.
     *
     * @param prenom Le prénom du responsable à ajouter.
     * @param nom Le nom du responsable à ajouter.
     * @param model Le modèle pour ajouter des attributs à la vue.
     * @return La redirection vers la page des responsables.
     */
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

    /**
     * Supprime un responsable par son identifiant.
     *
     * Cette méthode est appelée lorsqu'un responsable doit être supprimé. Elle supprime le responsable de la base de données
     * en fonction de son identifiant.
     *
     * @param id L'identifiant du responsable à supprimer.
     * @return La redirection vers la page des responsables.
     */
    @PostMapping("/deleteResponsable")
    public String deleteResponsable(@RequestParam Long id) {
        responsableService.deleteById(id);
        return "redirect:/responsables";
    }
}