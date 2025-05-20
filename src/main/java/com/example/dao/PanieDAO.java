package com.example.dao;

import com.example.model.Panier;
import java.sql.*;
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
                liste.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
}