package ui.view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class addTest {
    private WebDriver driver;
    //pas url aan indien nodig
    private String url = "http://localhost:8080/laridon_ferre_war_exploded/";
    // om te testen via Cyclone, zet bovenstaande url in comments en activeer de volgende lijn
    //private String url = "http://cyclone3.uclllabs.be:8081/laridon-ferre_war/";

    @Before
    public void setUp() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    /**
     * Test valid case
     */
    @Test
    public void test_Formulier_alles_invullen_en_melk_niet_0_Gaat_naar_overzicht_en_toont_nieuw_item_in_tabel() {
        // navigeer naar formulier
        driver.get(url + "Controller?command=addForm");
        // vul velden geldig in
        voegItemToe("input1", 20, 10, 15); //valide String en int values invullen
        // controleer <title> van pagina die browser toont
        assertEquals("Koffie overzicht", driver.getTitle());
        // controleer <h2>
        assertEquals("bekijk alle koffies", driver.findElement(By.tagName("h2")).getText());
        // controleer of overzicht nieuw ingevoegd item bevat
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "input1"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "20.0"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "10"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "15"));
    }

    /**
     * Test valid case
     */
    @Test
    public void test_Formulier_alles_invullen_en_melk_0_Gaat_naar_overzicht_en_toont_nieuw_item_in_tabel() {
        // navigeer naar formulier - Pas url hieronder aan indien nodig
        driver.get(url + "Controller?command=addForm");
        // vul velden geldig in
        voegItemToe("input2", 20, 10, 0); //valide String en int values invullen
        // controleer <title> van pagina die browser toont
        assertEquals("Koffie overzicht", driver.getTitle());
        // controleer <h2>
        assertEquals("bekijk alle koffies", driver.findElement(By.tagName("h2")).getText());
        // controleer of overzicht nieuw ingevoegd item bevat
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "input2"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "20.0"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "10"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "0"));
    }

    /**
     * Invalid case
     */
    @Test
    public void test_Lege_naam_toegevoegd_Toon_formulier_met_foutboodschap() {
        // navigeer naar formulier
        driver.get(url + "Controller?command=addForm");
        // vul velden in
        voegItemToe("", 20, 10, 0); //voor lege inputvelden "" en voor integer waarden 0
        // controleer <title> van pagina die browser toont
        assertEquals("Voeg koffie toe", driver.getTitle());
        // controleer <h2>
        assertEquals("voeg koffie toe", driver.findElement(By.tagName("h2")).getText());
        // controleer of foutboodschap aanwezig is
        assertEquals("Vul een geldige naam in.",driver.findElement(By.id("message")).getText());
    }

    /**
     * Invalid case
     */
    @Test
    public void test_Fout_aantal_gram_koffie_toegevoegd_Toon_formulier_met_foutboodschap() {
        // navigeer naar formulier
        driver.get(url + "Controller?command=addForm");
        // vul velden in
        voegItemToe("Koffie nul", 0, 10, 0); //voor lege inputvelden "" en voor integer waarden 0
        // controleer <title> van pagina die browser toont
        assertEquals("Voeg koffie toe", driver.getTitle());
        // controleer <h2>
        assertEquals("voeg koffie toe", driver.findElement(By.tagName("h2")).getText());
        // controleer of foutboodschap aanwezig is
        assertEquals("Vul een geldige hoeveelheid koffie in.",driver.findElement(By.id("message")).getText());
    }

    /**
     * Invalid case
     */
    @Test
    public void test_Foute_hoeveelheid_water_toegevoegd_Toon_formulier_met_foutboodschap() {
        // navigeer naar formulier
        driver.get(url + "Controller?command=addForm");
        // vul velden in
        voegItemToe("Water nul", 20, 0, 0); //voor lege inputvelden "" en voor integer waarden 0
        // controleer <title> van pagina die browser toont
        assertEquals("Voeg koffie toe", driver.getTitle());
        // controleer <h2>
        assertEquals("voeg koffie toe", driver.findElement(By.tagName("h2")).getText());
        // controleer of foutboodschap aanwezig is
        assertEquals("Vul een geldige hoeveelheid water in.",driver.findElement(By.id("message")).getText());
    }

    /**
     * Invalid case
     */
    @Test
    public void test_Foute_hoeveelheid_melk_toegevoegd_Toon_formulier_met_foutboodschap() {
        // navigeer naar formulier
        driver.get(url + "Controller?command=addForm");
        // vul velden in
        voegItemToe("Melk negatief", 20, 10, -5); //voor lege inputvelden "" en voor integer waarden 0
        // controleer <title> van pagina die browser toont
        assertEquals("Voeg koffie toe", driver.getTitle());
        // controleer <h2>
        assertEquals("voeg koffie toe", driver.findElement(By.tagName("h2")).getText());
        // controleer of foutboodschap aanwezig is
        assertEquals("Vul een geldige hoeveelheid melk in.",driver.findElement(By.id("message")).getText());
    }

    /**
     * Invalid case
     */
    @Test
    public void test_Alles_fout_toegevoegd_Toon_formulier_met_foutboodschap() {
        // navigeer naar formulier
        driver.get(url + "Controller?command=addForm");
        // vul velden in
        voegItemToe("", 0, 0, -5); //voor lege inputvelden "" en voor integer waarden 0
        // controleer <title> van pagina die browser toont
        assertEquals("Voeg koffie toe", driver.getTitle());
        // controleer <h2>
        assertEquals("voeg koffie toe", driver.findElement(By.tagName("h2")).getText());
        // controleer of foutboodschap aanwezig is
        assertTrue(paginaBevatItemMetText(driver.findElements(By.id("message")), "Vul een geldige naam in."));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.id("message")), "Vul een geldige hoeveelheid koffie in."));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.id("message")), "Vul een geldige hoeveelheid water in."));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.id("message")), "Vul een geldige hoeveelheid melk in."));
    }

    /**
     * Methode die velden van formulier invult en op submit knop duwt
     * @param input1, input2, input3, input4: velden die ingevuld worden
     */
    private void voegItemToe(String input1, int input2, int input3, int input4) {
        //Pas url hieronder aan indien nodig
        driver.findElement(By.id("naam")).sendKeys(input1);
        driver.findElement(By.id("gramKoffie")).sendKeys(input2 + "");
        driver.findElement(By.id("milliliterWater")).sendKeys(input3 + "");
        driver.findElement(By.id("milliliterMelk")).sendKeys(input4 + "");
        driver.findElement(By.id("submit")).click();
    }

    /**
     * Returnt true indien lijst webelements de gezochte tekst bevat
     * @param items: op overzichtspagina's zijn alle items vervat in een container, bijv <td>
     * @param tekst die opgezocht moet worden
     */
    private boolean paginaBevatItemMetText(List<WebElement> items, String tekst) {
        for (WebElement item : items) {
            if (item.getText().equals(tekst)) {
                return true;
            }
        }
        return false;
    }
}