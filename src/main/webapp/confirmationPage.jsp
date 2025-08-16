<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verwijder koffie</title>
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
        <h2>verwijder koffie</h2>

        <p>Ben je zeker dat je volgende koffie wil verwijderen?</p>
        <ul class="gevondenKoffie">
            <li>Naam: ${verwijderKoffie.naam}</li>
            <li>Gram koffie: ${verwijderKoffie.gramKoffie}</li>
            <li>Milliliter water: ${verwijderKoffie.mlWater}</li>
            <li>Milliliter melk: ${verwijderKoffie.mlMelk}</li>
        </ul>

        <p>
            <a href="Controller?command=delete&id=${verwijderKoffie.id}">Verwijder</a>
            <a href="Controller?command=overview">Annuleer</a>
        </p>

    </article>

</main>

<footer>
    <div class="container">
        <p>Webontwikkeling 2 - 2021-2022</p>
    </div>
</footer>

</body>
</html>