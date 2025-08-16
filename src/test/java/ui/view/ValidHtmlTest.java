package ui.view;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidHtmlTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test // Voer deze test uit als je je project opgeladen hebt
    public void isValidHtml() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        // verander naar de url die je wil valideren
        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/laridon-ferre_war/");

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("p.success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of " +
                "this service).", pass.getText());
    }
}