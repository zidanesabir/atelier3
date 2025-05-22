package com.example.dao;

import com.example.model.Article;
import com.example.model.Facture;
import com.example.model.LigneFacture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LigneFactureDAO {

    private Connection getConnection() throws SQLException {
        // Modifier les paramètres selon votre configuration de base de données
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/votre_base", "utilisateur", "mot_de_passe");
    }

    public List<LigneFacture> getAll() {
        List<LigneFacture> lignes = new ArrayList<>();
        String sql = "SELECT * FROM ligne_facture";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                LigneFacture ligne = new LigneFacture();
                ligne.setId(rs.getInt("id"));
                ligne.setQuantite(rs.getInt("quantite"));
                ligne.setPrixUnitaire(rs.getDouble("prix_unitaire"));

                // Si vous avez des méthodes pour charger Article et Facture par ID
                ArticleDAO articleDAO = new ArticleDAO();
                FactureDAO factureDAO = new FactureDAO();
                ligne.setArticle(articleDAO.getById(rs.getInt("article_id")));
                ligne.setFacture(factureDAO.getById(rs.getInt("facture_id")));

                lignes.add(ligne);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lignes;
    }

    // Vous pouvez aussi ajouter ici d'autres méthodes : insert, delete, update, etc.
}
