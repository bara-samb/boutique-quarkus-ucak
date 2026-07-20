# Étape 1 : Introduction à Quarkus (Supersonic Subatomic Java)

Bienvenue dans l'univers de **Quarkus** ! Ce guide vous explique les concepts fondamentaux de ce framework moderne.

---

## 1. Qu'est-ce que Quarkus ?

**Quarkus** est un framework Java natif pour Kubernetes, conçu pour les machines virtuelles Java (JVM) et la compilation native (GraalVM). Il optimise Java spécifiquement pour les conteneurs et les architectures cloud / microservices.

### Ses points forts :
1. **Temps de démarrage ultra-rapide** (quelques millisecondes au lieu de plusieurs secondes avec d'autres frameworks).
2. **Faible consommation de mémoire** (très utile pour économiser les coûts de serveurs ou de cloud).
3. **Le Mode Dev en temps réel (Hot Reload)** : Toute modification apportée au code source est prise en compte instantanément lors de la prochaine requête HTTP, sans redémarrer le serveur !
4. **Dev UI** : Une interface d'administration accessible dans le navigateur pour surveiller et configurer l'application.

---

## 2. Structure d'un projet Quarkus

Voici comment est organisé notre projet [boutique-quarkus](..) :

*   [pom.xml](../pom.xml) : Le fichier de configuration Maven contenant toutes les dépendances (Quarkus, Hibernate ORM, MySQL JDBC, JUnit, etc.).
*   `src/main/java/` : Le dossier contenant tout votre code Java.
    *   [Produit.java](../src/main/java/sn/edu/ucak/dar/Produit.java) : L'entité JPA représentant les données de notre base de données.
    *   [ProduitResource.java](../src/main/java/sn/edu/ucak/dar/ProduitResource.java) : Notre point d'accès API REST (contrôleur).
*   `src/main/resources/` : Les ressources non-Java de l'application.
    *   [application.properties](../src/main/resources/application.properties) : Le fichier de configuration principal de l'application (base de données, logs, sécurité).
    *   [import.sql](../src/main/resources/import.sql) : Un script SQL qui s'exécute automatiquement au démarrage pour insérer des données de test.

---

## 3. Le cycle de vie du Dev Mode

Avec Quarkus, vous lancez l'application en mode développement en utilisant la commande :
```bash
./mvnw quarkus:dev
```
Dès que vous modifiez un fichier et que vous rafraîchissez votre navigateur ou que vous envoyez une requête HTTP, Quarkus compile uniquement les classes modifiées en tâche de fond. C'est le **Live Reload**.

---

### Passer à l'étape suivante :
*   [Étape 2 : Configuration de MySQL](../docs/etape2_configuration_mysql.md)
