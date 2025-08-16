<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pas koffie aan</title>
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

        <h2>pas koffie aan</h2>

        <c:if test="${not empty errors}">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li id="message">${error}</li>
                </c:forEach>
            </ul>
        </c:if>

        <form name="Controller" action="Controller" novalidate>
            <p>
                <label for="naam">Naam<strong class="verplicht">*</strong></label>
                <input type="text" id="naam" name="naam" value="${koffie.naam}"
                       required>
            </p>
            <p>
                <label for="gramKoffie">Gram koffie<strong class="verplicht">*</strong></label>
                <input type="number" id="gramKoffie" step="0.1" name="gramKoffie" value="${koffie.gramKoffie}"
                       required>
            </p>
            <p>
                <label for="milliliterWater">Milliliter water<strong class="verplicht">*</strong></label>
                <input type="number" id="milliliterWater" step="1" name="milliliterWater" value="${koffie.mlWater}"
                       required>
            </p>
            <p>
                <label for="milliliterMelk">Milliliter melk</label>
                <input type="number" id="milliliterMelk" step="1" name="milliliterMelk" value="${koffie.mlMelk}">
            </p>
            <input type="hidden" name="oudeId" value="${koffie.id}">
            <input type="hidden" name="command" value="editConfirmation">
            <p><input type="submit" id="submit" value="Pas aan"></p>
        </form>

        <p>Velden met een <strong class="verplicht">*</strong> zijn verplicht.</p>

    </article>
</main>

<footer>
    <div class="container">
        <p>Webontwikkeling 2 - 2021-2022</p>
    </div>
</footer>

</body>

</html>
