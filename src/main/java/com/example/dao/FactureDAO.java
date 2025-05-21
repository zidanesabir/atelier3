package com.example.dao;

import com.example.model.Facture;
import com.example.model.LigneFacture;
import com.example.model.Article;
import java.sql.*;
import java.util.*;

public class FactureDAO {
    private String jdbcURL = "jdbc:mysql://mysql:3306/gestion_commandes_db?useSSL=false&allowPublicKeyRetrieval=true";
    private String jdbcUser = "root";
    private String jdbcPassword = "root";
    
    // Récupérer toutes les factures
    public List<Facture> getAll() {
        List<Facture> liste = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM facture")) {
            
            while (rs.next()) {
                Facture f = new Facture();
                f.setId(rs.getInt("id"));
                f.setDate(rs.getDate("date"));
                f.setMontantTotal(rs.getDouble("montant_total"));
                liste.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
    
    // Trouver une facture par son ID
    public Facture findById(int id) {
        Facture facture = null;
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM facture WHERE id = ?")) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    facture = new Facture();
                    facture.setId(rs.getInt("id"));
                    facture.setDate(rs.getDate("date"));
                    facture.setMontantTotal(rs.getDouble("montant_total"));
                    
                    // Charger les lignes de facture associées
                    facture.setLignesFacture(getLignesFactureByFactureId(id, conn));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return facture;
    }
    
    // Récupérer les lignes de facture pour une facture donnée
    private List<LigneFacture> getLignesFactureByFactureId(int factureId, Connection conn) throws SQLException {
        List<LigneFacture> lignes = new ArrayList<>();
        String sql = "SELECT lf.*, a.nom, a.prix FROM ligne_facture lf " +
                     "JOIN article a ON lf.article_id = a.id " +
                     "WHERE lf.facture_id = ?";
                     
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, factureId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LigneFacture ligne = new LigneFacture();
                    ligne.setId(rs.getInt("id"));
                    ligne.setQuantite(rs.getInt("quantite"));
                    ligne.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                    
                    Article article = new Article();
                    article.setId(rs.getInt("article_id"));
                    article.setNom(rs.getString("nom"));
                    article.setPrix(rs.getDouble("prix"));
                    
                    ligne.setArticle(article);
                    
                    Facture facture = new Facture();
                    facture.setId(factureId);
                    ligne.setFacture(facture);
                    
                    lignes.add(ligne);
                }
            }
        }
        return lignes;
    }
    
    // Créer une nouvelle facture
    public boolean save(Facture facture) {
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword)) {
            conn.setAutoCommit(false);
            
            // Insérer la facture
            String sql = "INSERT INTO facture (date, montant_total) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setDate(1, new java.sql.Date(facture.getDate().getTime()));
                stmt.setDouble(2, facture.getMontantTotal());
                
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    conn.rollback();
                    return false;
                }
                
                // Récupérer l'ID généré
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int factureId = generatedKeys.getInt(1);
                        facture.setId(factureId);
                        
                        // Si la facture a des lignes, les sauvegarder aussi
                        if (facture.getLignesFacture() != null) {
                            for (LigneFacture ligne : facture.getLignesFacture()) {
                                saveLigneFacture(ligne, factureId, conn);
                            }
                        }
                    } else {
                        conn.rollback();
                        return false;
                    }
                }
            }
            
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Sauvegarder une ligne de facture
    private boolean saveLigneFacture(LigneFacture ligne, int factureId, Connection conn) throws SQLException {
        String sql = "INSERT INTO ligne_facture (facture_id, article_id, quantite, prix_unitaire) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, factureId);
            stmt.setInt(2, ligne.getArticle().getId());
            stmt.setInt(3, ligne.getQuantite());
            stmt.setDouble(4, ligne.getPrixUnitaire());
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    // Mettre à jour une facture existante
    public boolean update(Facture facture) {
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword)) {
            conn.setAutoCommit(false);
            
            // Mettre à jour la facture
            String sql = "UPDATE facture SET date = ?, montant_total = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDate(1, new java.sql.Date(facture.getDate().getTime()));
                stmt.setDouble(2, facture.getMontantTotal());
                stmt.setInt(3, facture.getId());
                
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    conn.rollback();
                    return false;
                }
                
                // Si on veut mettre à jour les lignes, on supprime d'abord les anciennes
                deleteLignesFacture(facture.getId(), conn);
                
                // Puis on ajoute les nouvelles lignes
                if (facture.getLignesFacture() != null) {
                    for (LigneFacture ligne : facture.getLignesFacture()) {
                        saveLigneFacture(ligne, facture.getId(), conn);
                    }
                }
            }
            
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Supprimer les lignes de facture
    private void deleteLignesFacture(int factureId, Connection conn) throws SQLException {
        String sql = "DELETE FROM ligne_facture WHERE facture_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, factureId);
            stmt.executeUpdate();
        }
    }
    
    // Supprimer une facture
    public boolean delete(int id) {
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword)) {
            conn.setAutoCommit(false);
            
            // Supprimer d'abord les lignes de facture
            deleteLignesFacture(id, conn);
            
            // Puis supprimer la facture
            String sql = "DELETE FROM facture WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int affectedRows = stmt.executeUpdate();
                
                if (affectedRows == 0) {
                    conn.rollback();
                    return false;
                }
            }
            
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}