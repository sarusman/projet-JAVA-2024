# DevDirectory - Projet Java 2024

## Description
DevDirectory est une application conçue pour la gestion de programmeurs avec une base de données. Elle est divisée en deux parties :
1. **Console** : Programme de base pour les opérations en ligne de commande.
2. **Webapp** : Bonus : Interface graphique avec de nouvelles fonctionnalités.

## Structure du projet
Le projet est organisé comme suit :
```
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
├── readme.txt
└── README.md
```

---

## Prérequis
- **IDE recommandé** : IntelliJ IDEA Community Edition (CE)
- **Java JDK** : Version 11 ou supérieure

---

## Comment lancer le projet

### Étape 1 : Ouvrir le projet
1. Décompressez le dossier `DevDirectory.zip` à l'emplacement de votre choix.
2. Lancez IntelliJ IDEA CE.
3. Ouvrez le dossier `DevDirectory` dans IntelliJ IDEA.

<p align="center">
  <img src="img/zip.png" alt="Décompresser le dossier" width="300" style="margin-right: 10px;">
  <img src="img/open.png" alt="Ouvrir dans IntelliJ IDEA" width="300">
</p>

---

### Étape 2 : Ajouter la bibliothèque PostgreSQL JDBC
Si vous rencontrez une erreur liée à JDBC :
1. Rendez-vous dans **File** → **Project Structure**.
2. Cliquez sur **Libraries**.
3. Cliquez sur le bouton **+** puis sélectionnez **Java**.
4. Ajoutez le fichier `.jar` situé dans :
   ```
   DevDirectory/console/Librairies/postgresql-42.7.4.jar
   ```
5. Validez et appliquez les modifications.

---

### Étape 3 : Configurer le répertoire source
Si le fichier `Start.java` (situé dans `console/src/`) ou les fichiers du dossier `webapp` ne sont pas exécutables :
1. Faites un clic droit sur les répertoires `console/src` et `webapp/src`.
2. Cliquez sur **Mark Directory as** → **Sources Root**.

---

### Étape 4 : Exécuter le projet
- Pour la **console** :
  Exécutez le fichier `Start.java` qui se trouve dans `console/src/`.

- Pour la **webapp** :
  Lancer la configuration fait automatiquement par IntelliJ 'WebappApplication'.

### Étape 2 : Ajouter la bibliothèque PostgreSQL JDBC
Si vous rencontrez une erreur liée à JDBC :
1. Rendez-vous dans **File** → **Project Structure**.
![zip.png](img/project.png)
2. Cliquez sur **Libraries**.
   ![zip.png](img/librairies.png)
3. Cliquez sur le bouton **+** puis sélectionnez **Java**.
   ![zip.png](img/java.png)
4. Ajoutez le fichier `.jar` situé dans :
   ```
   DevDirectory/console/Librairies/postgresql-42.7.4.jar
   ```
5. Validez et appliquez les modifications.

---

### Étape 3 : Configurer le répertoire source
Si le fichier `Start.java` (situé dans `console/src/`) ou les fichiers du dossier `webapp` ne sont pas exécutables :
1. Faites un clic droit sur les répertoires `console/src` et `webapp/src`.
2. Cliquez sur **Mark Directory as** → **Sources Root**.

---

### Étape 4 : Exécuter le projet
- Pour la **console** :
  Exécutez le fichier `Start.java` qui se trouve dans `console/src/`.

- Pour la **webapp** :
  Lancer la configuration fait automatiquement par IntelliJ 'WebappApplication'.

--- 

## Choix de SGBD
Le projet utilise PostgreSQL comme système de gestion de base de données (SGBD).

---

## Fonctionnalités supplémentaires
- **Console** :
   - Vérification de l'entrée utilisateur lors de l'ajout et modification des programmeurs.
   - Vérifications des doublons pour les noms et prénoms, numéro de téléphone et création de responsables.

- **Webapp** :
   - Interface graphique pour toutes les fonctionnalités disponibles dans la console.
   - Ajout de pages pour les statistiques, recherche filtrée, connexion admin, page de contact et possibilité d'ajouter des admins.

---

## Étudiants
- **SOLANKI Priyank**
- **SATKUNARAJAH Sarusman**
- **SASIKUMAR Sahkana**

---
