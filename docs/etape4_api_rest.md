# Étape 4 : Création d'une API REST avec JAX-RS

Cette étape explique comment exposer des points de terminaison (endpoints) HTTP pour effectuer des opérations de création, lecture, mise à jour et suppression (CRUD) sur vos données en utilisant l'entité JPA [Produit](../src/main/java/sn/edu/ucak/dar/Produit.java).

---

## 1. Qu'est-ce que JAX-RS / Jakarta REST ?

Dans Quarkus, les API REST sont construites avec les standards **Jakarta RESTful Web Services** (anciennement JAX-RS). Les annotations principales sont :
*   `@Path("/chemin")` : Spécifie le chemin d'accès URL de la ressource.
*   `@GET`, `@POST`, `@PUT`, `@DELETE` : Indiquent les méthodes HTTP acceptées par chaque méthode Java.
*   `@Produces` et `@Consumes` : Spécifient le format de données échangé (généralement `application/json`).

---

## 2. Analyse de notre classe `ProduitResource`

Regardons en détail le code de la classe [ProduitResource.java](../src/main/java/sn/edu/ucak/dar/ProduitResource.java) :

### L'Injection de Dépendance (`@Inject`)
```java
@Inject
EntityManager em;
```
*   `EntityManager` est l'interface principale de JPA qui permet de dialoguer avec la base de données.
*   L'annotation `@Inject` demande à Quarkus (via son conteneur CDI nommé ArC) de nous fournir automatiquement une instance valide d'EntityManager configurée avec notre base de données `boutiqueucak`. Pas besoin de l'instancier manuellement avec un `new` !

### Les requêtes de Lecture (GET)
*   **Récupérer tous les produits** :
    ```java
    @GET
    public List<Produit> listAll() {
        return em.createQuery("FROM Produit", Produit.class).getResultList();
    }
    ```
    Ici, nous écrivons une requête JPQL (`FROM Produit`) qui récupère toutes les lignes de la table `produit` et les mappe automatiquement dans une liste d'objets Java. Quarkus transforme ensuite cette liste en format JSON automatiquement.

*   **Récupérer un produit spécifique** :
    ```java
    Produit produit = em.find(Produit.class, id);
    ```
    La méthode `em.find()` recherche directement l'enregistrement par sa clé primaire. Si l'enregistrement n'existe pas, nous retournons un statut HTTP `404 Not Found`.

### Les requêtes d'Écriture (POST, PUT, DELETE) et `@Transactional`
Toute opération qui modifie la base de données (ajout, modification, suppression) **doit s'exécuter dans le contexte d'une transaction active**.

Dans Quarkus, il suffit d'ajouter l'annotation `@Transactional` sur vos méthodes d'écriture :
*   **Création (POST)** :
    ```java
    @POST
    @Transactional
    public Response create(Produit produit) {
        em.persist(produit);
        return Response.status(Response.Status.CREATED).entity(produit).build();
    }
    ```
    La méthode `em.persist(produit)` indique à Hibernate d'insérer un nouvel enregistrement dans la base de données.
*   **Mise à jour (PUT)** :
    ```java
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(...) {
        // Recherche du produit existant...
        produit.setLibelle(updateData.getLibelle()); // Modification des valeurs
        // Hibernate détecte automatiquement les changements lors du commit de la transaction !
    }
    ```
*   **Suppression (DELETE)** :
    ```java
    em.remove(produit);
    ```
    La méthode `em.remove()` supprime la ligne correspondante de la base de données.

---

### Passer à l'étape suivante :
*   [Étape 5 : Lancement et tests de l'application](../docs/etape5_lancement_dev_mode.md)
