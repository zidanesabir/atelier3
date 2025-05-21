package com.example.model;
import java.util.Date;

public class Facture {
    private int id;
    private Date date;
    private double montantTotal;
    
    public Facture() {}
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    
    public double getMontantTotal() { return montantTotal; }
    public void setMontantTotal(double montantTotal) { this.montantTotal = montantTotal; }
}