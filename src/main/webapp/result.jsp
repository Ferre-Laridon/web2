<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Zoekresultaat</title>
    <link rel="icon" href="img/favicon.png">
    <link rel="stylesheet" href="style/normalize.css">
    <link rel="stylesheet" href="style/style.css">
</head>
<body>

<header>
    <jsp:include page="header.jsp"/>
</header>

<main>
    <article class="container">

        <h2>zoekresultaat</h2>

        <c:choose>
            <c:when test="${gevondenKoffie != null}">
                <p>
                    We vonden volgende koffie met naam ${gevondenKoffie.naam}:
                </p>
                <ul class="gevondenKoffie">
                    <li>Naam: ${gevondenKoffie.naam}</li>
                    <li>Gram koffie: ${gevondenKoffie.gramKoffie}</li>
                    <li>Milliliter water: ${gevondenKoffie.mlWater}</li>
                    <li>Milliliter melk: ${gevondenKoffie.mlMelk}</li>
                </ul>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${empty vindNaam}">
                        <p>Gelieve geen lege naam in te vullen.</p>
                    </c:when>
                    <c:otherwise>
                        <p>We vonden geen koffie met de naam ${vindNaam}.</p>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>

    </article>
</main>

<footer>
    <div class="container">
        <p>Webontwikkeling 2 - 2021-2022</p>
    </div>
</footer>

</body>
</html>
