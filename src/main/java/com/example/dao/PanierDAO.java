package com.example.dao;

import com.example.model.Panier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PanierDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_commandes_db?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL non trouvé !", e);
        }
    }

    public List<Panier> getAll() {
        List<Panier> paniers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM panier")) {

import java.util.*;

public class PanierDAO {
    private String jdbcURL = "jdbc:mysql://mysql:3306/paniers_db?useSSL=false&allowPublicKeyRetrieval=true";
    private String jdbcUser = "root";
    private String jdbcPassword = "root";

    public List<Panier> getAll() {
        List<Panier> liste = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM panier")) {

            while (rs.next()) {
                Panier p = new Panier();
                p.setId(rs.getInt("id"));
                p.setTotal(rs.getDouble("total"));
                paniers.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
        return paniers;
                liste.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
}