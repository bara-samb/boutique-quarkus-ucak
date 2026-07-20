# Guide de contribution

Ce dépôt est utilisé en groupe pour le projet **boutique-quarkus**. Merci de suivre ce workflow pour éviter les conflits et garder un historique propre.

## 1. Récupérer le projet

Chaque contributeur travaille sur son **propre fork**, pas directement sur ce dépôt.

1. Cliquez sur **Fork** en haut à droite de la page GitHub du dépôt.
2. Clonez **votre fork** en local :
   ```shell script
   git clone https://github.com/<votre-user>/boutique-quarkus-ucak.git
   cd boutique-quarkus-ucak
   ```
3. Ajoutez le dépôt d'origine comme remote `upstream` pour pouvoir récupérer les mises à jour des autres :
   ```shell script
   git remote add upstream https://github.com/makhtar2/boutique-quarkus-ucak.git
   ```

## 2. Se synchroniser avant de commencer

Avant de démarrer une nouvelle tâche, mettez toujours votre fork à jour :

```shell script
git checkout main
git fetch upstream
git merge upstream/main
git push origin main
```

## 3. Créer une branche par fonctionnalité

Ne travaillez jamais directement sur `main`. Créez une branche dédiée :

```shell script
git checkout -b feature/nom-de-la-fonctionnalite
```

Convention de nommage :
- `feature/...` : nouvelle fonctionnalité (ex: `feature/facture-pdf`)
- `fix/...` : correction de bug (ex: `fix/stock-negatif`)
- `docs/...` : documentation uniquement (ex: `docs/etape8-securite`)

## 4. Commits

- Un commit = un changement logique cohérent (évitez les gros commits fourre-tout).
- Message au format : `type: description courte` (ex: `fix: correction du calcul du prix unitaire dans FactureResource`).
- Types courants : `feat`, `fix`, `docs`, `refactor`, `test`, `chore`.

## 5. Avant de proposer vos changements

Vérifiez que le projet compile et que les tests passent :

```shell script
./mvnw compile
./mvnw test
```

## 6. Proposer une Pull Request

1. Poussez votre branche vers **votre fork** :
   ```shell script
   git push origin feature/nom-de-la-fonctionnalite
   ```
2. Sur GitHub, ouvrez une **Pull Request** de votre branche vers `main` du dépôt d'origine (`makhtar2/boutique-quarkus-ucak`).
3. Décrivez clairement ce que fait la PR et pourquoi.
4. Attendez une relecture avant de merger — évitez de merger sa propre PR sans validation d'un autre membre du groupe.

## 7. Conventions du code

- Package racine : `sn.edu.ucak.dar` (ressources REST) / `sn.edu.ucak.dar.entities` (entités JPA) — merci de respecter cette convention, alignée sur celle du professeur.
- Utiliser Lombok (`@Getter`, `@Setter`, `@NoArgsConstructor`) plutôt que d'écrire les accesseurs à la main.
- Toute méthode qui écrit en base doit être annotée `@Transactional`.
- Documenter les nouveaux endpoints dans `docs/` en suivant le format des étapes existantes.

## 8. En cas de conflit

Si `git merge upstream/main` génère des conflits, résolvez-les localement, testez, puis committez la résolution avant de pousser. En cas de doute, demandez de l'aide dans le groupe avant de forcer quoi que ce soit (`git push --force` est interdit sur `main`).
