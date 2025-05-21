package com.example.servlet;

import com.example.dao.PanierDAO;
import com.example.model.Panier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/paniers")
public class ListePanierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final PanierDAO panierDAO = new PanierDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Panier> paniers = panierDAO.getAll();
        request.setAttribute("paniers", paniers);
        request.getRequestDispatcher("/WEB-INF/listePaniers.jsp").forward(request, response);
    }
}