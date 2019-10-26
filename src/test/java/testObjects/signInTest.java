package testObjects;

import json.JSONDataProvider;
import json.extractcreateAccounjson;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.SignInPO;
import utils.Browser;
import utils.Global_Asserts;
import utils.Global_VARS;

public class signInTest extends BaseTest {

    /**
     * Method to create an account - POSITIVE
     * DISABLE
     *
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"OMITIR"}, dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = false)
    public void createAccount_Positive(String rowID, String description, JSONObject testData) throws Exception {
        extractcreateAccounjson accountInfo = new extractcreateAccounjson(testData);
        SignInPO driversignIn = new SignInPO();
        driversignIn.loadPage();
        driversignIn.createAccount(accountInfo.getEmail(), accountInfo.getFirstname(), accountInfo.getLastname(), accountInfo.getPassword(),
                accountInfo.getCompany(), accountInfo.getAddress(), accountInfo.getAddress2(), accountInfo.getCity(), accountInfo.getZip(),
                accountInfo.getAdditionalinfo(), accountInfo.getPhone(), accountInfo.getMobile(), accountInfo.getAlias());
        Assert.assertEquals(driversignIn.getTitle(), Global_VARS.login_Title);
        Assert.assertEquals(Browser.extractText(driversignIn.userinfo),
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
    public void createAccount_Negative(String rowID, String description, JSONObject testData) throws Exception {
        extractcreateAccounjson accountInfo = new extractcreateAccounjson(testData);
        SignInPO driversignIn = new SignInPO();
        driversignIn.loadPage();
        Assert.assertEquals(driversignIn.getTitle(), Global_VARS.home_Title);
        driversignIn.createAccount(accountInfo.getEmail(), accountInfo.getFirstname(), accountInfo.getLastname(), accountInfo.getPassword(),
                accountInfo.getCompany(), accountInfo.getAddress(), accountInfo.getAddress2(), accountInfo.getCity(), accountInfo.getZip(),
                accountInfo.getAdditionalinfo(), accountInfo.getPhone(), accountInfo.getMobile(), accountInfo.getAlias());
        switch (rowID) {
            case "createAccount_Negative.01":
                Assert.assertEquals(driversignIn.alertlogin.getText(), Global_Asserts.assertAccountNegative1);
                break;
            case "createAccount_Negative.02":
                Assert.assertEquals(driversignIn.alertlogin.getText(), Global_Asserts.assertAccountNegative2);
                break;
            case "createAccount_Negative.03":
                Assert.assertEquals(driversignIn.alertlogin.getText(), Global_Asserts.assertAccountNegative3);
                break;
        }
    }

    /**
     * Method to login - POSITIVE
     *
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"SMOKETEST"}, dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = true)
    public void login_Positive(String rowID, String description, JSONObject testData) throws Exception {
        SignInPO driversignIn = new SignInPO();
        driversignIn.loadPage();
        Assert.assertEquals(driversignIn.getTitle(), Global_VARS.home_Title);
        driversignIn.login(testData.get("email").toString(), testData.get("password").toString());
        Assert.assertEquals(driversignIn.getTitle(), Global_VARS.login_Title);
        Assert.assertEquals(Browser.extractText(driversignIn.userinfo),
                testData.get("firstname") + " " + testData.get("lastname"));
        driversignIn.logout();
    }

    /**
     * Method to login - NEGATIVE
     *
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"SMOKETEST"}, dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = true)
    public void login_Negative(String rowID, String description, JSONObject testData) throws Exception {
        SignInPO driversignIn = new SignInPO();
        driversignIn.loadPage();
        Assert.assertEquals(driversignIn.getTitle(), Global_VARS.home_Title);
        driversignIn.login(testData.get("email").toString(), testData.get("password").toString());
        Browser.waitFor(driversignIn.alertlogin, 2);
        Assert.assertEquals(driversignIn.alertlogin.getText(), Global_Asserts.assertloginNegative);

    }

    /**
     * Create account - Account Exist
     *
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"OMITIR"}, dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = true)
    public void createExistentAccount(String rowID, String description, JSONObject testData) throws Exception {
        SignInPO driversignIn = new SignInPO();
        driversignIn.loadPage();
        Assert.assertEquals(driversignIn.getTitle(), Global_VARS.home_Title);
        driversignIn.createAccountExist(testData.get("email").toString());
        Assert.assertEquals(driversignIn.alertcreateaccount.getText(), Global_Asserts.assertCreateExistentAccount);
    }
}
