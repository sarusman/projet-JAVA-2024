package com.javaprojet.webapp.controller;

import com.javaprojet.webapp.service.ProgrammeurService;
import org.springframework.ui.Model;
import com.javaprojet.webapp.model.Programmeur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur pour gérer les opérations liées aux programmeurs.
 *
 * Ce contrôleur permet d'afficher, ajouter, modifier, supprimer, rechercher et afficher les statistiques des programmeurs.
 * Il interagit avec le service {@link ProgrammeurService} pour effectuer les actions nécessaires sur les données des programmeurs.
 *
 * @author Priyank
 */
@Controller
public class ProgrammeurController {

    /**
     * Le service pour gérer les programmeurs.
     *
     * Ce service est utilisé pour interagir avec la base de données et effectuer des opérations de base sur les programmeurs.
     */
    private final ProgrammeurService programmeurService;

    /**
     * Constructeur de la classe {@link ProgrammeurController}.
     *
     * @param programmeurService Le service pour gérer les programmeurs.
     */
    public ProgrammeurController(ProgrammeurService programmeurService) {
        this.programmeurService = programmeurService;
    }

    /**
     * Affiche la page d'accueil avec la liste des programmeurs.
     *
     * Cette méthode est appelée lors de la requête GET sur la page d'accueil. Elle récupère la liste des programmeurs
     * et l'ajoute au modèle pour être affichée dans la vue.
     *
     * @param model Le modèle pour ajouter des attributs à la vue.
     * @return Le nom de la vue à afficher (home).
     */
    @GetMapping("/")
    public String home(Model model) {
        Iterable<Programmeur> listProgrammeur = programmeurService.afficherProgrammeurs();
        model.addAttribute("programmeurs", listProgrammeur);
        return "home";
    }

    /**
     * Affiche le formulaire d'ajout d'un programmeur.
     *
     * Cette méthode est appelée lors de la requête GET sur la page d'ajout d'un programmeur.
     *
     * @return Le nom de la vue à afficher (addProgrammeurForm).
     */
    @GetMapping("/addProgrammeur")
    public String afficherFormAjoutProgrammeur() {
        return "addProgrammeurForm";
    }

    /**
     * Affiche le formulaire de modification d'un programmeur.
     *
     * Cette méthode est appelée lors de la requête GET sur la page de modification d'un programmeur.
     * Elle récupère les informations du programmeur à modifier et les ajoute au modèle.
     *
     * @param model Le modèle pour ajouter des attributs à la vue.
     * @param id L'identifiant du programmeur à modifier.
     * @return Le nom de la vue à afficher (updateProgrammeurForm).
     */
    @GetMapping("/updateProgrammeur")
    public String afficherFormModifierProgrammeur(Model model, @RequestParam("id") int id) {
        Programmeur programmeur = programmeurService.findProgrammeurById(id);
        model.addAttribute("programmeur", programmeur);
        return "updateProgrammeurForm";
    }

    /**
     * Affiche le formulaire de suppression d'un programmeur.
     *
     * Cette méthode est appelée lors de la requête GET sur la page de suppression d'un programmeur.
     * Elle récupère les informations du programmeur à supprimer et les ajoute au modèle.
     *
     * @param model Le modèle pour ajouter des attributs à la vue.
     * @param id L'identifiant du programmeur à supprimer.
     * @return Le nom de la vue à afficher (deleteProgrammeurPage).
     */
    @GetMapping("/deleteProgrammeur")
    public String deleteFormProgrammeur(Model model, @RequestParam("id") int id) {
        Programmeur programmeur = programmeurService.findProgrammeurById(id);
        model.addAttribute("programmeur", programmeur);
        return "deleteProgrammeurPage";
    }

    // POST

    /**
     * Ajoute un nouveau programmeur.
     *
     * Cette méthode est appelée lors de la soumission du formulaire pour ajouter un programmeur. Elle vérifie si un programmeur
     * avec le même prénom et nom existe déjà. Si ce n'est pas le cas, un nouveau programmeur est ajouté à la base de données.
     *
     * @param prenom Le prénom du programmeur à ajouter.
     * @param nom Le nom du programmeur à ajouter.
     * @param adresse L'adresse du programmeur à ajouter.
     * @param pseudo Le pseudo du programmeur à ajouter.
     * @param responsable Le responsable du programmeur à ajouter.
     * @param hobby Le hobby du programmeur à ajouter.
     * @param annee L'année du programmeur à ajouter.
     * @param salaire Le salaire du programmeur à ajouter.
     * @param prime La prime du programmeur à ajouter.
     * @param model Le modèle pour ajouter des attributs à la vue.
     * @return La redirection vers la page d'accueil.
     */
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

    /**
     * Modifie un programmeur existant.
     *
     * Cette méthode est appelée lors de la soumission du formulaire pour modifier un programmeur. Elle met à jour les informations
     * du programmeur dans la base de données.
     *
     * @param id L'identifiant du programmeur à modifier.
     * @param prenom Le nouveau prénom du programmeur.
     * @param nom Le nouveau nom du programmeur.
     * @param adresse La nouvelle adresse du programmeur.
     * @param pseudo Le nouveau pseudo du programmeur.
     * @param responsable Le nouveau responsable du programmeur.
     * @param hobby Le nouveau hobby du programmeur.
     * @param annee La nouvelle année du programmeur.
     * @param salaire Le nouveau salaire du programmeur.
     * @param prime La nouvelle prime du programmeur.
     * @return La redirection vers la page d'accueil.
     */
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

    /**
     * Supprime un programmeur.
     *
     * Cette méthode est appelée pour supprimer un programmeur de la base de données en fonction de son identifiant.
     *
     * @param id L'identifiant du programmeur à supprimer.
     * @return La redirection vers la page d'accueil.
     */
    @PostMapping("/supprimer")
    public String supprimerProgrammeur(@RequestParam String id) {
        programmeurService.deleteProgrammeurById(Integer.parseInt(id));
        return "redirect:/";
    }

    /**
     * Affiche les statistiques des programmeurs.
     *
     * Cette méthode est appelée lors de la requête GET sur la page des statistiques. Elle récupère les statistiques des programmeurs
     * via le service {@link ProgrammeurService} et les ajoute au modèle pour être affichées dans la vue.
     *
     * @param model Le modèle pour ajouter des attributs à la vue.
     * @return Le nom de la vue à afficher (statistiques).
     */
    @GetMapping("/statistiques")
    public String afficherStatistiques(Model model) {
        var statistiques = programmeurService.getStatistiques();
        model.addAttribute("statistiques", statistiques);
        return "statistiques";
    }

    /**
     * Recherche des programmeurs en fonction de critères.
     *
     * Cette méthode est appelée lors de la soumission d'un formulaire de recherche. Elle filtre les programmeurs en fonction
     * des critères fournis (nom, prénom, salaire, responsable, adresse).
     *
     * @param nom Le nom du programmeur à rechercher.
     * @param prenom Le prénom du programmeur à rechercher.
     * @param salaireMin Le salaire minimum du programmeur à rechercher.
     * @param responsable Le responsable du programmeur à rechercher.
     * @param adresse L'adresse du programmeur à rechercher.
     * @param model Le modèle pour ajouter des attributs à la vue.
     * @return Le nom de la vue à afficher (home).
     */
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

    /**
     * Affiche la page de contact.
     *
     * Cette méthode est appelée lors de la requête GET sur la page de contact.
     *
     * @return Le nom de la vue à afficher (contact).
     */
    @GetMapping("/contact")
    public String afficherContact() {
        return "contact";
    }

    /**
     * Affiche la page des mentions légales.
     *
     * @return Le nom de la vue à afficher (mentionsLegales).
     */
    @GetMapping("/mentions-legales")
    public String afficherMentionsLegales() {
        return "legal";
    }

    /**
     * Affiche la page de la politique de confidentialité.
     *
     * @return Le nom de la vue à afficher (politiqueConfidentialite).
     */
    @GetMapping("/politique-confidentialite")
    public String afficherPolitiqueConfidentialite() {
        return "confidentiality";
    }

    /**
     * Récupère les programmeurs par année.
     *
     * Cette méthode est appelée via une requête GET pour récupérer les programmeurs d'une année spécifique.
     *
     * @param year L'année des programmeurs à récupérer.
     * @return La liste des programmeurs pour l'année spécifiée.
     */
    @GetMapping("/programmeursByYear")
    @ResponseBody
    public List<Programmeur> getProgrammeursByYear(@RequestParam Integer year) {
        return programmeurService.afficherProgrammeurs()
                .stream()
                .filter(p -> Integer.valueOf(p.getAnnee()).equals(year))
                .collect(Collectors.toList());
    }
}