# Étape 5 : Lancement et Tests de l'application

Dans cette dernière étape, nous allons lancer notre application et tester ses différentes routes API en direct.

---

## 1. Démarrage de Quarkus (Dev Mode)

Assurez-vous que votre serveur MySQL est démarré sur votre machine (ou Docker) et que les identifiants saisis dans le fichier [application.properties](../src/main/resources/application.properties) sont corrects.

Ouvrez un terminal dans le dossier du projet [boutique-quarkus](..) et exécutez la commande suivante :
```bash
./mvnw quarkus:dev
```

*Note : La première fois, Maven téléchargera peut-être des dépendances. Ensuite, Quarkus démarrera très rapidement.*

---

## 2. Tester les Points de Terminaison (Endpoints)

Une fois l'application lancée, elle écoute sur le port **8080** par défaut. Vous pouvez tester les routes à l'aide de votre navigateur ou d'outils comme `curl`, Postman, Insomnia ou VS Code REST Client.

### A. Lister tous les produits (GET)
**Action** : Ouvrez votre navigateur et allez sur `http://localhost:8080/produits` ou tapez dans votre terminal :
```bash
curl -X GET http://localhost:8080/produits
```
**Réponse attendue** : Une liste JSON contenant les produits insérés automatiquement par notre script [import.sql](../src/main/resources/import.sql) :
```json
[
  {"id":1,"libelle":"Ordinateur Portable Asus","prix":750000.0,"quantite":10},
  {"id":2,"libelle":"Souris Optique Sans Fil","prix":15000.0,"quantite":50},
  {"id":3,"libelle":"Clavier Mecanique RVB","prix":45000.0,"quantite":30},
  {"id":4,"libelle":"Ecran PC 24 pouces Full HD","prix":120000.0,"quantite":15}
]
```

### B. Ajouter un nouveau produit (POST)
**Action** : Envoyez une requête POST avec le produit en JSON :
```bash
curl -X POST http://localhost:8080/produits \
     -H "Content-Type: application/json" \
     -d '{"libelle":"Casque Audio Anti-bruit","prix":85000.0,"quantite":8}'
```
**Réponse attendue** : Le produit créé avec son ID généré automatiquement par MySQL (ex: 5).

### C. Mettre à jour un produit (PUT)
**Action** : Modifiez les informations du produit créé (ex: ID 5) :
```bash
curl -X PUT http://localhost:8080/produits/5 \
     -H "Content-Type: application/json" \
     -d '{"libelle":"Casque Audio Anti-bruit Bose","prix":90000.0,"quantite":6}'
```

### D. Supprimer un produit (DELETE)
**Action** : Supprimez le produit créé (ex: ID 5) :
```bash
curl -X DELETE http://localhost:8080/produits/5
```
**Réponse attendue** : Code HTTP `204 No Content` (indique que la suppression a réussi).

---

## 3. Explorer l'interface Dev UI de Quarkus

Pendant que Quarkus tourne en mode dev, ouvrez la **Dev UI** dans votre navigateur :
👉 **`http://localhost:8080/q/dev/`**

Cette interface très riche vous permet de :
*   Visualiser vos extensions installées.
*   Consulter la structure de votre base de données et de vos entités Hibernate.
*   Gérer les configurations dynamiques.
*   Lancer et visualiser vos tests unitaires directement en ligne.

---

## 4. Compiler pour la Production

Lorsque votre développement est terminé et prêt à être déployé, vous pouvez packager l'application en exécutant :
```bash
./mvnw package
```
Cela produit un fichier jar exécutable performant dans le dossier `target/quarkus-app/`.
Si vous souhaitez compiler l'application en **code natif** (exécutable binaire autonome sans JVM requise grâce à GraalVM), utilisez :
```bash
./mvnw package -Dnative
```

Félicitations, vous maîtrisez maintenant les bases de Quarkus avec MySQL et JPA !
