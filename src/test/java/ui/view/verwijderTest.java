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

public class verwijderTest {
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
    public void test_Koffie_verwijderen_Toont_verwijderpagina() {
        // navigeer naar overzicht
        driver.get(url + "Controller?command=overview");
        driver.findElement(By.linkText("Verwijder")).click();
        // controleer <title> van pagina die browser toont
        assertEquals("Verwijder koffie", driver.getTitle());
        // controleer <h2>
        assertEquals("verwijder koffie", driver.findElement(By.tagName("h2")).getText());
        // controleer of overzicht nieuw ingevoegd item bevat
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("p")), "Ben je zeker dat je volgende koffie wil verwijderen?"));
    }

    /**
     * Test valid case
     */
    @Test
    public void test_Na_koffie_verwijderen_Toont_overzichtspagina() {
        // navigeer naar overzicht
        driver.get(url + "Controller?command=overview");
        driver.findElement(By.linkText("Verwijder")).click();
        // controleer <title> van pagina die browser toont
        assertEquals("Verwijder koffie", driver.getTitle());
        // controleer <h2>
        assertEquals("verwijder koffie", driver.findElement(By.tagName("h2")).getText());
        // klik op verwijder
        driver.findElement(By.linkText("Verwijder")).click();
        // controleer of overzicht getoond wordt
        assertEquals("Koffie overzicht", driver.getTitle());
        assertEquals("bekijk alle koffies", driver.findElement(By.tagName("h2")).getText());

    }

    /**
     * Valid case
     */
    @Test
    public void test_Op_annuleren_klikken_Toont_overzichtspagina() {
        // navigeer naar overzicht
        driver.get(url + "Controller?command=overview");
        driver.findElement(By.linkText("Verwijder")).click();
        // controleer <title> van pagina die browser toont
        assertEquals("Verwijder koffie", driver.getTitle());
        // controleer <h2>
        assertEquals("verwijder koffie", driver.findElement(By.tagName("h2")).getText());
        // klik op verwijder
        driver.findElement(By.linkText("Annuleer")).click();
        // controleer of overzicht getoond wordt
        assertEquals("Koffie overzicht", driver.getTitle());
        assertEquals("bekijk alle koffies", driver.findElement(By.tagName("h2")).getText());
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