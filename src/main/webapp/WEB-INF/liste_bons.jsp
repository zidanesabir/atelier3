<%@ taglib prefix="c" uri="https://jakarta.ee/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Bons de Livraison</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        table {
            border-collapse: collapse;
            width: 70%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #aaa;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>

<h2>Liste des Bons de Livraison</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Date</th>
        <th>Adresse</th>
    </tr>
    <c:forEach var="b" items="${bons}">
        <tr>
            <td>${b.id}</td>
            <td>${b.date}</td>
            <td>${b.adresse}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
