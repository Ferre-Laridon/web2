<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Zoek koffie</title>
    <link rel="icon" href="img/favicon.png">
    <link rel="stylesheet" href="style/normalize.css">
    <link rel="stylesheet" href="style/style.css">
</head>
<body>

<header>
    <jsp:include page="header.jsp">
        <jsp:param name="hier" value="zoekFormulier"/>
    </jsp:include>
</header>

<main>
    <article class="container">

        <p>Geef de naam van de koffie die je wil zoeken.</p>
        <form action="Controller" novalidate>
            <p>
                <label for="naam">Naam: </label>
                <input type="text" id="naam" name="vindNaam">
                <input type="hidden" name="command" value="result">
                <input id="zoekKnop" type="submit" value="Zoek">
            </p>
        </form>

    </article>
</main>

<footer>
    <div class="container">
        <p>Webontwikkeling 2 - 2021-2022</p>
    </div>
</footer>

</body>
</html>
