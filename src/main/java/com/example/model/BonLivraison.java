package com.example.model;

public class BonLivraison {
    private int id;
    private String date;
    private String adresse;

    public BonLivraison(int id, String date, String adresse) {
        this.id = id;
        this.date = date;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getAdresse() {
        return adresse;
    }
}
