# Étape 2 : Configuration de MySQL dans Quarkus

Cette étape explique comment configurer l'accès à une base de données MySQL dans votre projet Quarkus.

---

## 1. Dépendances requises

Pour utiliser MySQL, un ORM (Hibernate) et le format de sérialisation JSON dans Quarkus, nous avons besoin de trois extensions majeures déclarées dans le fichier [pom.xml](file:///../pom.xml) :

1.  `quarkus-rest-jackson` : Permet la sérialisation automatique des entités Java en JSON lors du retour d'une requête HTTP.
2.  `quarkus-hibernate-orm` : Permet d'utiliser JPA/Hibernate pour manipuler la base de données via des objets Java.
3.  `quarkus-jdbc-mysql` : Le pilote (driver) MySQL JDBC pour permettre la connexion réseau.

---

## 2. Configuration des propriétés

Nous avons configuré le fichier de configuration principal [application.properties](../src/main/resources/application.properties) avec les propriétés suivantes :

```properties
# 1. Type de base de données
quarkus.datasource.db-kind=mysql

# 2. Informations d'authentification
# Votre conteneur docker "mysql_boutiqueucak" est configuré avec MYSQL_ALLOW_EMPTY_PASSWORD=yes
# Le mot de passe root doit donc être laissé vide.
quarkus.datasource.username=root
quarkus.datasource.password=

# 3. URL de connexion JDBC
quarkus.datasource.jdbc.url=jdbc:mysql://localhost:3306/boutiqueucak?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
```

### Explications détaillées :
*   `quarkus.datasource.db-kind=mysql` : Indique à Quarkus qu'il doit charger le pilote MySQL.
*   `quarkus.datasource.jdbc.url` :
    *   `jdbc:mysql://localhost:3306/boutiqueucak` : URL de connexion locale pour la base de données `boutiqueucak`.
    *   `createDatabaseIfNotExist=true` : Crée automatiquement la base si elle n'existe pas.
    *   `useSSL=false` & `allowPublicKeyRetrieval=true` : Désactive le SSL pour le dev local et permet le cryptage des mots de passe.

---

## 3. Le super-pouvoir de Quarkus : Les Dev Services (Docker requis)

Si vous n'avez pas de conteneur MySQL lancé manuellement :
*   Si vous supprimez/commentez l'URL, l'utilisateur et le mot de passe dans `application.properties`, Quarkus démarrera automatiquement un conteneur MySQL temporaire dans Docker pour votre application en mode dev, configurera tout seul la connexion, et arrêtera le conteneur à la fermeture du serveur.

---

## 4. Configuration d'Hibernate ORM

```properties
# Génération automatique du schéma (nouveau format non déprécié)
quarkus.hibernate-orm.schema-management.strategy=update

# Afficher les requêtes SQL générées
quarkus.hibernate-orm.log.sql=true

# Script d'initialisation SQL
quarkus.hibernate-orm.sql-load-script=import.sql
```

*   `quarkus.hibernate-orm.schema-management.strategy=update` : Demande à Hibernate de vérifier les classes Java annotées avec `@Entity` et de générer ou mettre à jour les tables MySQL correspondantes (remplace la propriété dépréciée `quarkus.hibernate-orm.database.generation`).

---

### Passer à l'étape suivante :
*   [Étape 3 : Création d'Entités JPA](../docs/etape3_creation_entites.md)
