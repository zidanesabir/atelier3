<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Panier" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des Paniers</title>
<style>
table { border-collapse: collapse; width: 60%; margin: 20px auto; }
th, td { padding: 8px; border: 1px solid #ddd; text-align: left; }
th { background-color: #f2f2f2; }
.price { text-align: right; }
</style>
</head>
<body>
<h2 style="text-align: center;">Liste des Paniers</h2>

<%
List<Panier> paniers = (List<Panier>)request.getAttribute("paniers");
if (paniers != null && !paniers.isEmpty()) {
%>
    <p style="text-align: center;">Nombre de paniers : <%= paniers.size() %></p>
    <table>
        <tr><th>ID</th><th>Total</th></tr>
        <% 
        for (Panier p : paniers) { 
        %>
            <tr>
                <td><%= p.getId() %></td>
                <td class="price"><%= String.format("%.2f", p.getTotal()) %> €</td>
            </tr>
        <% 
        } 
        %>
    </table>
<%
} else {
%>
    <p style="text-align: center;">Aucun panier disponible.</p>
<%
}
%>
</body>
</html>