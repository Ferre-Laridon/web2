<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.model.Koffie" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Koffie overzicht</title>
    <link rel="icon" href="img/favicon.png">
    <link rel="stylesheet" href="style/normalize.css">
    <link rel="stylesheet" href="style/style.css">
</head>

<body>

<header>
    <jsp:include page="header.jsp">
        <jsp:param name="hier" value="overview"/>
    </jsp:include>
</header>

<main>
    <article class="container">

        <h2>bekijk alle koffies</h2>

        <c:if test="${foutmelding != null}">
            <p id="message">${foutmelding}</p>
        </c:if>

        <table>
            <thead>
            <tr>
                <th>Naam</th>
                <th>Koffie</th>
                <th>Water</th>
                <th>Melk</th>
                <th>Pas aan</th>
                <th>Verwijder</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="koffie" items="${koffies}">
            <tr>
                <td>${koffie.naam}</td>
                <td>${koffie.gramKoffie}</td>
                <td>${koffie.mlWater}</td>
                <td>${koffie.mlMelk}</td>
                <td><a href="Controller?command=edit&id=${koffie.id}">Pas aan</a></td>
                <td><a href="Controller?command=confirmationPage&id=${koffie.id}">Verwijder</a></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>

        <p>U voegde al
            <c:choose>
                <c:when test="${aantalToegevoegd == null}">
                    0
                </c:when>
                <c:otherwise>
                    ${aantalToegevoegd}
                </c:otherwise>
            </c:choose>
            keer een nieuw item toe.</p>

    </article>
</main>

<footer>
    <div class="container">
        <p>Webontwikkeling 2 - 2021-2022</p>
    </div>
</footer>

</body>

</html>