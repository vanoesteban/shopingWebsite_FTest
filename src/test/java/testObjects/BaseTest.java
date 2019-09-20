package testObjects;

import drivers.CreateDriver;
import json.JSONDataProvider;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utils.Global_VARS;

public class BaseTest {

    //local vars
    public static final String DATA_FILE = "src/main/java/json/MAINJSON.json";

    @Parameters({"browser", "enviroment"})
    @BeforeClass(alwaysRun = true)
    protected void testClassSetup(@Optional(Global_VARS.browser_Chrome) String browser,
                                  @Optional(Global_VARS.ENVIRONMENT) String enviroment,
                                  ITestContext context) throws Exception {
        JSONDataProvider.dataFile = DATA_FILE;
        Global_VARS.PLATFORM = System.getProperty("os.name");
        System.out.println(Global_VARS.PLATFORM);
        CreateDriver.getInstance().setDriver(browser, Global_VARS.PLATFORM, enviroment);

    }

    @BeforeTest(alwaysRun = true, enabled = true)
    protected void testSetup() throws Exception {

    }

    @AfterClass(alwaysRun = true)
    protected void testClassTeardown() throws Exception {
        CreateDriver.getInstance().getDriver().close();
    }

    @AfterTest(alwaysRun = true, enabled = true)
    protected void testTeardown() throws Exception {

    }

}
