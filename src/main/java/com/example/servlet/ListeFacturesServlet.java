package com.example.servlet;

import com.example.dao.FactureDAO;
import com.example.model.Facture;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/factures")
public class ListeFacturesServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        FactureDAO dao = new FactureDAO();
        List<Facture> factures = dao.getAll();
        req.setAttribute("factures", factures);
        req.getRequestDispatcher("/WEB-INF/listeFactures.jsp").forward(req, res);
    }
}