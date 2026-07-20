# Étape 7 : Implémentation des Contrôleurs REST CRUD

Cette étape présente les nouveaux contrôleurs REST JAX-RS créés pour gérer l'ensemble des entités de la boutique (`Client`, `Marque`, `Stock`, `Facture`).

---

## 1. Les Nouveaux Contrôleurs REST implémentés

Afin de pouvoir administrer toute la base de données, nous avons créé les ressources suivantes dans le package `sn.edu.ucak.dev` :

### A. Gestion des Marques : [MarqueResource.java](../src/main/java/sn/edu/ucak/dar/MarqueResource.java)
*   **Point d'accès** : `/marques`
*   **Méthodes** :
    *   `GET /marques` : Liste toutes les marques.
    *   `GET /marques/{id}` : Récupère une marque spécifique.
    *   `POST /marques` : Crée une nouvelle marque.
    *   `PUT /marques/{id}` : Modifie une marque existante.
    *   `DELETE /marques/{id}` : Supprime une marque (si elle n'est pas liée à des produits).

### B. Gestion des Clients : [ClientResource.java](../src/main/java/sn/edu/ucak/dar/ClientResource.java)
*   **Point d'accès** : `/clients`
*   **Méthodes** :
    *   `GET /clients` : Liste tous les clients.
    *   `POST /clients` : Crée un client et lui **associe automatiquement une carte de fidélité** (`Carte`) persistée en base.
    *   `DELETE /clients/{id}` : Supprime un client ainsi que sa carte associée en cascade.

### C. Gestion des Stocks : [StockResource.java](../src/main/java/sn/edu/ucak/dar/StockResource.java)
*   **Point d'accès** : `/stocks`
*   **Méthodes** :
    *   `GET /stocks` : Liste les stocks de tous les produits.
    *   `GET /stocks/produit/{produitId}` : Récupère le stock pour un produit spécifique.
    *   `PUT /stocks/{id}` : Met à jour la quantité en stock.

### D. Gestion des Factures : [FactureResource.java](../src/main/java/sn/edu/ucak/dar/FactureResource.java)
*   **Point d'accès** : `/factures`
*   **Méthodes** :
    *   `GET /factures` : Liste toutes les factures avec leurs lignes d'articles respectives.
    *   `POST /factures` : Enregistre une facture. La ressource valide le client, rattache les produits, calcule le prix unitaire automatique et sauvegarde en cascade (`CascadeType.ALL`) les lignes d'articles.

---

## 2. Comment tester graphiquement avec Swagger UI ?

Grâce au module OpenAPI configuré précédemment, vous n'avez pas besoin d'écrire de requêtes `curl` complexes.
1. Lancez l'application en mode dev : `./mvnw quarkus:dev`
2. Ouvrez votre navigateur sur : **`http://localhost:8080/q/swagger-ui/`**
3. Vous y trouverez toutes les routes triées par entité. Vous pouvez cliquer sur **`Try it out`** pour tester la création de clients ou l'émission de factures interactives.

---

### Liens utiles :
*   Retourner à l'index des guides : [README_GUIDE.md](../README_GUIDE.md)
