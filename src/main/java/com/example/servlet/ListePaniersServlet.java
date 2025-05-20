package com.example.servlet;

import com.example.dao.PanierDAO;
import com.example.model.Panier;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ListePaniersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PanierDAO dao = new PanierDAO();
        List<Panier> paniers = dao.getAll();
        req.setAttribute("paniers", paniers);
        req.getRequestDispatcher("liste.jsp").forward(req, res);
    }
}