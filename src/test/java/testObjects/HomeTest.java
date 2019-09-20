package testObjects;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePO;
import utils.Global_VARS;

/**
 * NOTAS: TEST PARA PROBAR QUE LOS WEBDRIVERS ABREN CORRECTAMENTE. INTERNET EXPLORER KOF KOF
 */
public class HomeTest extends BaseTest{

    /**
     * Page exists with elemente - not all elementes include.
     *
     * @throws Exception
     */
    @Test(groups = {"OMITIR"}, alwaysRun = true, enabled = false)
    public void loadHomePage() throws Exception {
        HomePO driverHome = new HomePO();
        driverHome.loadPage();
        Assert.assertEquals(driverHome.getTitle(), Global_VARS.home_Title);
    }

}

