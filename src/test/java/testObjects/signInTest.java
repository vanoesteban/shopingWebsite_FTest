package testObjects;

import drivers.CreateDriver;
import json.JSONDataProvider;
import json.extractcreateAccounjson;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pageObjects.signInPO;
import utils.BrowserUtils;
import utils.Global_VARS;

/**
 * NOTAS: OPERACIONES BASICAS
 * tc001_signIn : Registrar usuario : Deshabilitado para no hacer spam enabled false y en grupo OMITIR DEL XML
 * tc001_signIn : Registrar usuario con datos incorrectos : No debe poder registrarse
 * tc003_signIn : Login usuario : se logea un usuario creado anteriormente con tc_001, datos correctos
 * tc004_signIn : Login usuario : datos incorrectos
 * tc005_signIn : Crear una cuenta que ya existe : se usa la informacion de tc_001;
 */
public class signInTest {


    //local vars
    public static final String DATA_FILE = "src/main/java/json/signInJSON.json";

    @Parameters({"browser", "enviroment"})
    @BeforeClass(alwaysRun = true, enabled = true)
    public void testClassSetup(@Optional(Global_VARS.browser_Chrome) String browser, @Optional(Global_VARS.ENVIRONMENT) String enviroment, ITestContext context) throws Exception {
        JSONDataProvider.dataFile = DATA_FILE;
        Global_VARS.PLATFORM = System.getProperty("os.name").toLowerCase();
        System.out.println(Global_VARS.PLATFORM);
        CreateDriver.getInstance().setDriver(browser, Global_VARS.PLATFORM, enviroment);
    }

    @AfterClass(alwaysRun = true, enabled = true)
    public void testClassTearDown(ITestContext context) throws Exception {
        CreateDriver.getInstance().getDriver().close();
    }

    @BeforeTest(alwaysRun = true)
    public void testSetup(ITestContext testContext) throws Exception {
    }

    @AfterTest(alwaysRun = true)
    public void testTeardown() throws Exception {
       CreateDriver.getInstance().closeDriver();
    }

    /**
     * Method to create an account - POSITIVE
     *
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"OMITIR"}, dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = false)
    public void tc001_signIn(String rowID, String description, JSONObject testData) throws Exception {
        extractcreateAccounjson accountInfo = new extractcreateAccounjson(testData);

        signInPO driverHome = new signInPO();
        driverHome.loadPage();
        driverHome.createAccount(accountInfo.getEmail(), accountInfo.getFirstname(), accountInfo.getLastname(), accountInfo.getPassword(),
                accountInfo.getCompany(), accountInfo.getAddress(), accountInfo.getAddress2(), accountInfo.getCity(), accountInfo.getZip(),
                accountInfo.getAdditionalinfo(), accountInfo.getPhone(), accountInfo.getMobile(), accountInfo.getAlias());
        Assert.assertEquals(driverHome.getTitle(), Global_VARS.login_Title);
        Assert.assertEquals(BrowserUtils.extractText(driverHome.userinfo),
                accountInfo.getFirstname() + " " + accountInfo.getLastname());
    }


    /**
     * Method to create an account - NEGATIVE
     *
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"SMOKETEST"}, dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = true)
    public void tc002_signIn(String rowID, String description, JSONObject testData) throws Exception {
        extractcreateAccounjson accountInfo = new extractcreateAccounjson(testData);

        signInPO driverHome = new signInPO();
        driverHome.loadPage();
        Assert.assertEquals(driverHome.getTitle(), Global_VARS.home_Title);
        driverHome.createAccount(accountInfo.getEmail(), accountInfo.getFirstname(), accountInfo.getLastname(), accountInfo.getPassword(),
                accountInfo.getCompany(), accountInfo.getAddress(), accountInfo.getAddress2(), accountInfo.getCity(), accountInfo.getZip(),
                accountInfo.getAdditionalinfo(), accountInfo.getPhone(), accountInfo.getMobile(), accountInfo.getAlias());
        Assert.assertEquals(driverHome.alertlogin.getText(), Global_VARS.createaccountFailedwrongdata);
    }

    /**
     * Method to login - POSITIVE
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"SMOKETEST"}, dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = true)
    public void tc003_signIn(String rowID, String description, JSONObject testData) throws Exception {

        signInPO driverHome = new signInPO();
        driverHome.loadPage();
        Assert.assertEquals(driverHome.getTitle(), Global_VARS.home_Title);
        driverHome.login(testData.get("email").toString(), testData.get("password").toString());
        Assert.assertEquals(driverHome.getTitle(), Global_VARS.login_Title);
        Assert.assertEquals(BrowserUtils.extractText(driverHome.userinfo),
                testData.get("firstname") + " " + testData.get("lastname"));
        driverHome.logout();
    }

    /**
     * Method to login - NEGATIVE
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"SMOKETEST"}, dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = true)
    public void tc004_signIn(String rowID, String description, JSONObject testData) throws Exception {

        signInPO driverHome = new signInPO();
        driverHome.loadPage();
        Assert.assertEquals(driverHome.getTitle(), Global_VARS.home_Title);
        driverHome.loginwitherror(testData.get("email").toString(), testData.get("password").toString());
        BrowserUtils.waitFor(driverHome.alertlogin, 2);
        Assert.assertEquals(driverHome.alertlogin.getText(), Global_VARS.autentFailed);

    }

    /**
     * Create account - Account Exist
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"OMITIR"}, dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = true)
    public void tc005_signIn(String rowID, String description, JSONObject testData) throws Exception {

        signInPO driverHome = new signInPO();
        driverHome.loadPage();
        Assert.assertEquals(driverHome.getTitle(), Global_VARS.home_Title);
        driverHome.createAccountExist(testData.get("email").toString());
        Assert.assertEquals(driverHome.alertcreateaccount.getText(), Global_VARS.emailRegitered);
    }
}
