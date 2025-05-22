<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Factures</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #333;
        }
        .factures-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .factures-table th, .factures-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .factures-table th {
            background-color: #f2f2f2;
        }
        .factures-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .factures-table tr:hover {
            background-color: #f1f1f1;
        }
        .btn {
            display: inline-block;
            padding: 8px 12px;
            background-color: #4CAF50;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 4px;
            margin: 5px 0;
        }
        .btn-view {
            background-color: #2196F3;
        }
        .btn-add {
            background-color: #4CAF50;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <h1>Liste des Factures</h1>
    
    <a href="facture-ajouter" class="btn btn-add">Ajouter une facture</a>
    
    <table class="factures-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Montant Total</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="facture" items="${factures}">
                <tr>
                    <td>${facture.id}</td>
                    <td><fmt:formatDate value="${facture.date}" pattern="dd/MM/yyyy" /></td>
                    <td><fmt:formatNumber value="${facture.montantTotal}" type="currency" currencySymbol="€" /></td>
                    <td>
                        <a href="facture-detail?id=${facture.id}" class="btn btn-view">Voir détails</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty factures}">
                <tr>
                    <td colspan="4">Aucune facture trouvée</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    
    <p><a href="index.jsp">Retour à l'accueil</a></p>
</body>
</html>