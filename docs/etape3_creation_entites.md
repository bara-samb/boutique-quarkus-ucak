# Étape 3 : Création d'Entités JPA avec Hibernate ORM

Cette étape vous explique comment créer des entités pour représenter vos tables de base de données sous forme de classes Java.

---

## 1. Qu'est-ce qu'une Entité JPA ?

Une **Entité** est une classe Java simple (POJO - Plain Old Java Object) annotée pour être liée à une table spécifique de la base de données. Grâce à l'API **JPA (Jakarta Persistence)**, chaque instance de cette classe représentera une ligne (un enregistrement) dans la table.

Dans notre projet, nous avons créé l'entité [Produit.java](../src/main/java/sn/edu/ucak/dar/Produit.java).

---

## 2. Analyse de notre classe `Produit`

Voici les annotations clés utilisées dans [Produit.java](../src/main/java/sn/edu/ucak/dar/Produit.java) :

### `@Entity`
Cette annotation indique à Hibernate que cette classe Java est une entité persistante qui doit être gérée par l'EntityManager et mappée à une table SQL.

### `@Table(name = "produit")`
Optionnelle mais recommandée. Elle spécifie le nom exact de la table dans MySQL (ici `produit`). Si elle n'est pas spécifiée, Hibernate utilisera le nom de la classe (qui serait `Produit` avec une majuscule).

### `@Id`
Désigne le champ qui servira de **clé primaire** unique pour identifier chaque ligne de la table.

### `@GeneratedValue(strategy = GenerationType.IDENTITY)`
Définit la stratégie de génération de la clé primaire. `GenerationType.IDENTITY` indique à Hibernate que c'est MySQL qui va générer automatiquement la valeur de l'identifiant (colonne `AUTO_INCREMENT` en SQL).

---

## 3. Les règles importantes d'une Entité JPA

Pour qu'une classe fonctionne correctement en tant qu'entité JPA :
1.  **Constructeur par défaut** : La classe doit obligatoirement avoir un constructeur sans argument (vide) avec une visibilité `public` ou `protected`. C'est ce constructeur qu'Hibernate utilise par réflexion pour instancier l'objet lorsqu'il lit des lignes dans la base de données.
2.  **Champs privés ou publics** : En JPA standard, on utilise généralement des champs privés avec des getters et des setters (comme dans [Produit.java](../src/main/java/sn/edu/ucak/dar/Produit.java)).
    *   *Note : Quarkus propose aussi une extension appelée **Panache** (non configurée ici mais très populaire) qui permet d'utiliser des champs publics et d'éliminer les getters/setters tout en bénéficiant de méthodes magiques comme `Produit.listAll()`, mais nous utilisons ici JPA standard.*

---

### Passer à l'étape suivante :
*   [Étape 4 : Création de la ressource REST API](../docs/etape4_api_rest.md)
