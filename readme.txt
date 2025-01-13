============================================================
                  DevDirectory - Projet Java 2024
============================================================

Description
-----------
DevDirectory est une application pour gérer des programmeurs avec une base de données.
Elle est divisée en deux parties :
1. Console : Programme de base pour les opérations en ligne de commande.
2. Webapp : Interface graphique avec des fonctionnalités supplémentaires.

============================================================
Structure du Projet
-------------------
DevDirectory/
├── console/
│   ├── src/
│   │   ├── actions/
│   │   │   ├── ActionsBDD.java
│   │   │   └── ActionsBDDImpl.java
│   │   ├── menu/
│   │   │   └── Menu.java
│   │   ├── models/
│   │   │   ├── Programmeur.java
│   │   │   └── Responsable.java
│   │   ├── services/
│   │   │   └── ProgrammeurService.java
│   │   └── utils/
│   │       └── InputValidator.java
│   └── Librairies/
│       └── postgresql-42.7.4.jar
├── webapp/
│   ├── src/
│   │   ├── actions/
│   │   ├── menu/
│   │   ├── models/
│   │   ├── services/
│   │   └── utils/
├── javaDoc/
├── img/
├── readme.txt
└── README.md

============================================================
Prérequis
---------
- IDE recommandé : IntelliJ IDEA Community Edition (CE)
- Java JDK       : Version 11 ou supérieure

============================================================
Comment Lancer le Projet
-------------------------

1. **Ouvrir le projet**
   - Décompressez le dossier `DevDirectory.zip`.
   - Ouvrez IntelliJ IDEA CE et chargez le dossier `DevDirectory`.

2. **Ajouter la bibliothèque PostgreSQL JDBC**
   - Dans IntelliJ : **File** → **Project Structure** → **Libraries**.
   - Ajoutez le fichier `.jar` :
     `DevDirectory/console/Librairies/postgresql-42.7.4.jar`.

3. **Configurer les répertoires source**
   - Faites un clic droit sur `console/src` et `webapp/src/java`.
   - Sélectionnez **Mark Directory as** → **Sources Root**.

4. **Exécuter le projet**
   - **Console** : Lancez `Start.java` dans `console/src/`.
   - **Webapp**  : Lancez `WebappApplication.java` dans `/webapp/src/main/java/`.
   - Accédez au site web : http://localhost:8080/

============================================================
Choix de SGBD
-------------
- Base de données : PostgreSQL
- Plateforme      : Supabase

Nous avons choisi PostgreSQL pour gérer nos données, car c’est une base de données gratuite et bien documentée.
Nous voulions stocker nos données en ligne pour y avoir accès de partout.
Nous avons donc utilisé Supabase, qui est une plateforme basée sur PostgreSQL. Supabase est pratique pour les développeurs,
car il offre des outils prêts à l’emploi, comme une interface facile pour gérer les données, un système d’authentification
pour les utilisateurs, et même une API automatique pour accéder à la base de données.
Cela nous a permis de gagner du temps et de nous concentrer sur le reste du projet.
PostgreSQL et Supabase nous donnent une solution simple, fiable et efficace pour gérer nos données et les rendre accessibles en ligne. C’est une bonne option pour notre petit projet avec une petite base de données.


============================================================
Fonctionnalités Supplémentaires
-------------------------------
- **Console** :
  - Vérification des données saisies (ex : format des numéros de téléphone).
  - Système anti-doublons pour les noms, numéros, et responsables.

- **Webapp** :
  - Interface graphique intuitive avec :
    - Statistiques sur les programmeurs.
    - Recherche filtrée rapide.
    - Connexion sécurisée pour les administrateurs.
    - Page de contact et gestion des administrateurs.
    - Un easter egg sur la page d'accueil !

============================================================
Étudiants
---------
- SOLANKI Priyank
- SATKUNARAJAH Sarusman
- SASIKUMAR Sahkana
============================================================
