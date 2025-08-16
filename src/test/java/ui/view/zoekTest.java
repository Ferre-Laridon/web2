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


public class zoekTest {
    private WebDriver driver;
    //pas url aan indien nodig
    private String url = "http://localhost:8080/laridon_ferre_war_exploded/";
    // om te testen via Cyclone, zet bovenstaande url in comments en activeer de volgende lijn
    //private String url = "http://cyclone3.uclllabs.be:8081/laridon-ferre_war/";

    @Before
    public void setUp() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        // voeg een te zoeken koffie toe
        driver.get(url + "Controller?command=addForm");
        voegItemToe("te zoeken", 15, 30, 10); //valide String en int values invullen
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    /**
     * Test valid case
     */
    @Test
    public void test_Zoekveld_correct_invullen_Geeft_gezochte_koffie_terug() {
        // navigeer naar formulier
        driver.get(url + "Controller?command=zoekFormulier");
        // vul veld geldig in
        zoekItem("te zoeken");
        // controleer <title> van pagina die browser toont
        assertEquals("Zoekresultaat", driver.getTitle());
        // controleer <h2>
        assertEquals("zoekresultaat", driver.findElement(By.tagName("h2")).getText());
        // controleer of overzicht nieuw ingevoegd item bevat
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("p")), "We vonden volgende koffie met naam te zoeken:"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("li")), "Naam: te zoeken"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("li")), "Gram koffie: 15.0"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("li")), "Milliliter water: 30"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("li")), "Milliliter melk: 10"));
    }

    /**
     * Invalid case
     */
    @Test
    public void test_Lege_naam_invullen_Toon_formulier_met_foutboodschap() {
        // navigeer naar formulier
        driver.get(url + "Controller?command=zoekFormulier");
        // vul veld geldig in
        zoekItem("");
        // controleer <title> van pagina die browser toont
        assertEquals("Zoekresultaat", driver.getTitle());
        // controleer <h2>
        assertEquals("zoekresultaat", driver.findElement(By.tagName("h2")).getText());
        // controleer of foutboodschap aanwezig is
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("p")), "Gelieve geen lege naam in te vullen."));
    }

    /**
     * Invalid case
     */
    @Test
    public void test_Foute_naam_invullen_Toon_formulier_met_foutboodschap() {
        // navigeer naar formulier
        driver.get(url + "Controller?command=zoekFormulier");
        // vul veld geldig in
        zoekItem("foute naam"); //valide String en int values invullen
        // controleer <title> van pagina die browser toont
        assertEquals("Zoekresultaat", driver.getTitle());
        // controleer <h2>
        assertEquals("zoekresultaat", driver.findElement(By.tagName("h2")).getText());
        // controleer of foutboodschap aanwezig is
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("p")), "We vonden geen koffie met de naam foute naam."));
    }

    /**
     * Methode die velden van formulier invult en op submit knop duwt
     * @param input1 dat ingevuld wordt
     */
    private void zoekItem(String input1) {
        driver.findElement(By.id("naam")).sendKeys(input1);
        driver.findElement(By.id("zoekKnop")).click();
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