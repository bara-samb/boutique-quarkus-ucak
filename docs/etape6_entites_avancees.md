# Étape 6 : Entités JPA Avancées et Relations complexes

Ce guide explique comment nous avons adapté les entités complexes et leurs relations du projet `MonProjetJPAByBousso` dans votre projet Quarkus.

---

## 1. Intégration de Lombok dans Quarkus

Votre projet d'origine utilise **Lombok** (`@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`) pour générer automatiquement le code répétitif (getters, setters, constructeurs).

Pour que cela fonctionne dans Quarkus avec Maven, nous avons :
1.  Ajouté la dépendance Lombok dans [pom.xml](../pom.xml) :
    ```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.46</version>
        <scope>provided</scope>
    </dependency>
    ```
2.  Configuré le compilateur Java (`maven-compiler-plugin`) pour utiliser le processeur d'annotations Lombok au moment de la compilation :
    ```xml
    <annotationProcessorPaths>
        <path>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.46</version>
        </path>
    </annotationProcessorPaths>
    ```

---

## 2. Les Entités et Leurs Relations

Nous avons recréé les 7 entités dans le package `en.edu.ucak.entites` sous `src/main/java/` :

### A. Relation Un-à-Un (One-to-One) : `Client` <-> `Carte`
*   [Client.java](../src/main/java/sn/edu/ucak/dar/entities/Client.java) représente le client.
*   [Carte.java](../src/main/java/sn/edu/ucak/dar/entities/Carte.java) possède une relation un-à-un avec le client :
    ```java
    @OneToOne
    @JoinColumn(nullable = false)
    private Client proprietaire;
    ```

### B. Relation Un-à-Plusieurs (One-to-Many) : `Marque` <-> `Produit`
*   Une [Marque.java](../src/main/java/sn/edu/ucak/dar/entities/Marque.java) possède plusieurs produits :
    ```java
    @OneToMany(mappedBy = "marque")
    private List<Produit> produits = new ArrayList<>();
    ```
*   Chaque [Produit.java](../src/main/java/sn/edu/ucak/dar/entities/Produit.java) fait référence à une seule marque obligatoire :
    ```java
    @ManyToOne(optional = false)
    @JoinColumn(name = "marque_id", nullable = false)
    private Marque marque;
    ```

### C. Relation Un-à-Un : `Produit` <-> `Stock`
*   Un [Stock.java](../src/main/java/sn/edu/ucak/dar/entities/Stock.java) est lié à un unique produit :
    ```java
    @OneToOne
    @JoinColumn(name = "produit_id", nullable = false, unique = true)
    private Produit produit;
    ```

### D. Relations de Facturation : `Facture` <-> `LigneArticle` <-> `Produit`
*   Une [Facture.java](../src/main/java/sn/edu/ucak/dar/entities/Facture.java) possède plusieurs lignes d'articles :
    ```java
    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
    private Set<LigneArticle> ligneArticles;
    ```
*   Chaque [LigneArticle.java](../src/main/java/sn/edu/ucak/dar/entities/LigneArticle.java) fait le lien entre une facture et un produit :
    ```java
    @ManyToOne
    @JoinColumn(name = "facture_id", nullable = false)
    private Facture facture;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;
    ```

---

## 3. Script d'initialisation mis à jour

Comme les tables ont désormais des clés étrangères (par exemple, un produit doit avoir une marque valide existante), nous avons complètement réécrit le script [import.sql](../src/main/resources/import.sql) :
1.  Il insère d'abord les **Marques** (`Asus`, `Logitech`, `Bose`).
2.  Il insère ensuite les **Produits** en pointant sur les identifiants des marques.
3.  Il insère les **Stocks**, les **Clients** et les **Cartes** dans le bon ordre.

---

## 4. Adaptation du Contrôleur REST
La ressource [ProduitResource.java](../src/main/java/sn/edu/ucak/dar/ProduitResource.java) a été adaptée pour utiliser les attributs de la nouvelle classe `Produit` (champs `nom`, `code`, `description`, `prix`, relation avec la marque, et identifiants de type `Integer`).

---

### Passer à l'étape suivante :
*   Retourner à l'index des guides : [README_GUIDE.md](../README_GUIDE.md)
