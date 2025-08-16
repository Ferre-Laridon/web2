<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Koffie</title>
    <link rel="icon" href="img/favicon.png">
    <link rel="stylesheet" href="style/normalize.css">
    <link rel="stylesheet" href="style/style.css">
</head>

<body>

<header>
    <jsp:include page="header.jsp">
        <jsp:param name="hier" value="home"/>
    </jsp:include>
</header>

<main>

    <article class="container">
        <h2>mijn favoriete koffies</h2>

        <figure>
            <blockquote cite="https://quotecatalog.com/quote/justina-chen-adventure-in-li-L75BjPp">
                <p>Adventure in life is good ...</p>
                <p>consistency in Coffee even better.</p>
            </blockquote>
            <figcaption>—Justina Chen Headley, <cite>North of Beautiful</cite></figcaption>
        </figure>

        <p>Ik drink erg graag koffie, maar al die verhoudingen vanbuiten kennen, blijft erg moeilijk.
        Deze site maakt dit proces een heel pak makkelijker.</p>

        <!-- Berekening -->
        <c:choose>
            <c:when test="${not empty koffies}">
                <p>De koffie met de grootste verhouding cafeïne is momenteel
                    <strong class="gekozenKoffie">${meesteCaff.naam}</strong>.</p>
            </c:when>
            <c:otherwise>
                <p>Voeg koffies toe aan de lijst om te weten te komen welke koffie de hoogste verhouding caffeïne
                    heeft.</p>
            </c:otherwise>
        </c:choose>

        <!-- Session -->
        <c:choose>
            <c:when test="${empty laatstVerwijderdeKoffie}">
                <p>U heeft nog niets verwijderd.</p>
            </c:when>
            <c:otherwise>
                <p>Laatst verwijderde koffie: ${laatstVerwijderdeKoffie.naam}.</p>
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