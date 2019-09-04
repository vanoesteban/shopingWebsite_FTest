package testObjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Global_VARS;

public class borrar {

    private WebDriver driver;

    @BeforeClass()
    public void testClassSetup(){
        WebDriverManager.chromedriver().setup();
    }

    @Before()
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After()
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test()
    public void test1(){
        driver.navigate().to(Global_VARS.TARGET_URL);
    }
}
