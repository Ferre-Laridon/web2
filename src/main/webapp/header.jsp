<div class="container">
    <h1>koffie: het zwarte goud</h1>

    <nav>
        <ul>
            <li>
                <a href="Controller?command=home" ${param.hier eq "home"?"id='hier'":""}>
                    <strong>home</strong>
                </a>
            </li>
            <li>
                <a href="Controller?command=addForm" ${param.hier eq "add"?"id='hier'":""}>
                    <strong>voeg toe</strong>
                </a>
            </li>
            <li>
                <a href="Controller?command=overview" ${param.hier eq "overview"?"id='hier'":""}>
                    <strong>overzicht</strong>
                </a>
            </li>
            <li>
                <a href="Controller?command=zoekFormulier" ${param.hier eq "zoekFormulier"?"id='hier'":""}>
                    <strong>zoek</strong>
                </a>
            </li>
        </ul>
    </nav>

</div>