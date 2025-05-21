package com.example.dao;

import com.example.model.BonLivraison;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BonLivraisonDAO {
    private final String url = "jdbc:mysql://localhost:3306/ta_base";
    private final String user = "root";
    private final String password = "";

    public List<BonLivraison> getAllBons() {
        List<BonLivraison> list = new ArrayList<>();
        String query = "SELECT * FROM bon_livraison";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                BonLivraison b = new BonLivraison(
                        rs.getInt("id"),
                        rs.getString("date"),
                        rs.getString("adresse")
                );
                list.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
