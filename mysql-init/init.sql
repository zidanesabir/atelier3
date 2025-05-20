CREATE DATABASE IF NOT EXISTS gestion_commandes_db;
USE gestion_commandes_db;

CREATE TABLE article (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100),
    prix DOUBLE
);

CREATE TABLE panier (
    id INT PRIMARY KEY AUTO_INCREMENT,
    total DOUBLE
);

CREATE TABLE facture (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    montant_total DOUBLE
);

CREATE TABLE bon_livraison (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    adresse VARCHAR(200)
);

CREATE TABLE ligne_facture (
    id INT PRIMARY KEY AUTO_INCREMENT,
    facture_id INT,
    article_id INT,
    quantite INT,
    prix_unitaire DOUBLE,
    FOREIGN KEY (facture_id) REFERENCES facture(id),
    FOREIGN KEY (article_id) REFERENCES article(id)
);

CREATE TABLE ligne_livraison (
    id INT PRIMARY KEY AUTO_INCREMENT,
    bon_livraison_id INT,
    article_id INT,
    quantite INT,
    FOREIGN KEY (bon_livraison_id) REFERENCES bon_livraison(id),
    FOREIGN KEY (article_id) REFERENCES article(id)
);

CREATE TABLE panier_article (
    panier_id INT,
    article_id INT,
    PRIMARY KEY (panier_id, article_id),
    FOREIGN KEY (panier_id) REFERENCES panier(id),
    FOREIGN KEY (article_id) REFERENCES article(id)
);

-- Insertion des articles
INSERT INTO article (nom, prix) VALUES 
('Ordinateur portable', 899.99),
('Smartphone', 499.99),
('Casque audio', 149.99);

-- Insertion de paniers
INSERT INTO panier (total) VALUES 
(1549.97),
(649.98),
(899.99);

-- Association des articles aux paniers
-- Panier 1 (1549.97) - Ordinateur portable + Casque audio
INSERT INTO panier_article (panier_id, article_id) VALUES 
(1, 1),
(1, 3);

-- Panier 2 (649.98) - Smartphone + Casque audio
INSERT INTO panier_article (panier_id, article_id) VALUES 
(2, 2),
(2, 3);

-- Panier 3 (899.99) - Ordinateur portable
INSERT INTO panier_article (panier_id, article_id) VALUES 
(3, 1);

-- Vérification
SELECT * FROM panier;
