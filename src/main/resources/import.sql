-- =========================================================================
-- Script d'initialisation de la base de données (Boutique UCAK)
-- =========================================================================

-- 1. Insertion des Marques
INSERT INTO marque (nom, description) VALUES ('Asus', 'Ordinateurs et composants informatiques');
INSERT INTO marque (nom, description) VALUES ('Logitech', 'Périphériques informatiques haut de gamme');
INSERT INTO marque (nom, description) VALUES ('Bose', 'Systèmes de sonorisation et casques audio');

-- 2. Insertion des Produits
-- Comme import.sql exécute des requêtes SQL natives brutes, nous devons spécifier les colonnes obligatoires (comme dateCreation et dateModification)
INSERT INTO produit (code, nom, description, prix, marque_id, dateCreation, dateModification) 
VALUES ('PRD0001', 'ZenBook Pro 14', 'Ordinateur portable Asus ultra-fin pour professionnels', 750000.00, 1, NOW(), NOW());

INSERT INTO produit (code, nom, description, prix, marque_id, dateCreation, dateModification) 
VALUES ('PRD0002', 'MX Master 3S', 'Souris sans fil ergonomique Logitech', 65000.00, 2, NOW(), NOW());

INSERT INTO produit (code, nom, description, prix, marque_id, dateCreation, dateModification) 
VALUES ('PRD0003', 'QC Ultra Headphone', 'Casque audio réducteur de bruit sans fil Bose', 220000.00, 3, NOW(), NOW());

-- 3. Insertion des Stocks associés
INSERT INTO stock (quantite, produit_id, date_creation, date_modification) 
VALUES (10, 1, NOW(), NOW());

INSERT INTO stock (quantite, produit_id, date_creation, date_modification) 
VALUES (50, 2, NOW(), NOW());

INSERT INTO stock (quantite, produit_id, date_creation, date_modification) 
VALUES (15, 3, NOW(), NOW());

-- 4. Insertion des Clients
INSERT INTO client (nom, prenom, email, adresse_livraison, telephone) 
VALUES ('Diallo', 'Mamadou', 'mamadou.diallo@email.com', 'Dakar, Sénégal', '+221 77 123 45 67');

INSERT INTO client (nom, prenom, email, adresse_livraison, telephone) 
VALUES ('Ndiaye', 'Aminata', 'aminata.ndiaye@email.com', 'Thies, Sénégal', '+221 77 987 65 43');

-- 5. Insertion des Cartes de Fidélité
INSERT INTO carte (dateCreation, proprietaire_id) 
VALUES (NOW(), 1);

INSERT INTO carte (dateCreation, proprietaire_id) 
VALUES (NOW(), 2);