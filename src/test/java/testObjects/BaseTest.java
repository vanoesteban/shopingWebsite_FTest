package testObjects;

import drivers.CreateDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pageObjects.BasePO;
import utils.BrowserUtils;
import utils.Global_VARS;

import javax.xml.bind.annotation.XmlElementDecl;

/**
 * NOTAS: TEST PARA PROBAR QUE LOS WEBDRIVERS ABREN CORRECTAMENTE. INTERNET EXPLORER KOF KOF
 */
public class BaseTest {

    @BeforeTest(alwaysRun = true, enabled = true)
    protected void testSetup() throws Exception {

    }

    @AfterTest(alwaysRun = true, enabled = true)
    protected void testTeardown() throws Exception {
    CreateDriver.getInstance().closeDriver();
    }

    /**
     * Page exists with elemente - not all elementes include - FIREFOX
     * @throws Exception
     */
    @Test(groups = {"SMOKETEST"}, alwaysRun = true, enabled = true)
    public void tc001_homePage_firefox() throws Exception {
        String platform = System.getProperty("os.name");
        System.out.println(platform);
        CreateDriver.getInstance().setDriver("firefox", platform, Global_VARS.ENVIRONMENT);
        BasePO firefoxHome = new BasePO();
        firefoxHome.loadPage();
       // Assert.assertEquals(firefoxHome.elementExists(), true);
        Assert.assertEquals(firefoxHome.getTitle(), Global_VARS.home_Title);
        CreateDriver.getInstance().getDriver().close();
    }

    /**
     * age exists with elemente - not all elementes include - CHROME
     * @throws Exception
     */
    @Test(groups = {"SMOKETEST"}, alwaysRun = true, enabled = true)
    public void tc001_homePage_chrome() throws Exception {
        String platform = System.getProperty("os.name");
        System.out.println(platform);
        CreateDriver.getInstance().setDriver("chrome", platform, Global_VARS.ENVIRONMENT);
        BasePO chromeHome = new BasePO();
        chromeHome.loadPage();
        //Assert.assertEquals(chromeHome.elementExists(), true);
        Assert.assertEquals(chromeHome.getTitle(), Global_VARS.home_Title);
        CreateDriver.getInstance().getDriver().close();
    }

    @Test(groups = {"OMITIR"}, alwaysRun = true, enabled = true)
    public void tc001_homePage_internetexplorer() throws Exception {
        String platform = System.getProperty("os.name");
        System.out.println(platform);
        CreateDriver.getInstance().setDriver("internetexplorer", platform, Global_VARS.ENVIRONMENT);
        BasePO ieHome = new BasePO();
        ieHome.loadPage();
       // Assert.assertEquals(ieHome.elementExists(), true);
        //Assert.assertEquals(ieHome.getTitle(), "WebDriver");
        CreateDriver.getInstance().getDriver().quit();
    }

}

