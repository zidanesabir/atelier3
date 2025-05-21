package com.example.servlet;

import com.example.dao.BonLivraisonDAO;
import com.example.model.BonLivraison;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/bons")
public class BonLivraisonServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BonLivraisonDAO dao = new BonLivraisonDAO();
        List<BonLivraison> bons = dao.getAllBons();

        request.setAttribute("bons", bons);
        request.getRequestDispatcher("/WEB-INF/views/liste.jsp").forward(request, response);
    }
}
