package com.example.servlet;

import com.example.dao.LigneFactureDAO;
import com.example.model.LigneFacture;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ignesFactureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LigneFactureDAO dao = new LigneFactureDAO();
        List<LigneFacture> lignes = dao.getAll();
        req.setAttribute("lignes", lignes);
        req.getRequestDispatcher("listeLignesFacture.jsp").forward(req, res);
    }
}
