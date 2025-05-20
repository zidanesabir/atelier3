package com.example.model;

public class Panier {
    private int id;
    private double total;
    
    // Constructeur par défaut
    public Panier() {
    }
    
    // Constructeur avec paramètres
    public Panier(int id, double total) {
        this.id = id;
        this.total = total;
    }
    
    // Getters et setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    @Override
    public String toString() {
        return "Panier [id=" + id + ", total=" + total + "]";
    }
}