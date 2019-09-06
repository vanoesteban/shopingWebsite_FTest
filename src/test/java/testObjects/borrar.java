package testObjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Global_VARS;

public class borrar {

    private WebDriver driver;

    @BeforeClass()
    public void testClassSetup() {
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
    }


    @Test()
    public void test1() {
        driver.get(Global_VARS.TARGET_URL);
    }
}
