package testObjects;

import json.JSONDataProvider;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.contactUsPO;
import utils.Browser;
import utils.Global_Asserts;

public class contactUsTest extends BaseTest{

    @Test(groups = {"SMOKETEST"}, dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = true)
    public void contactUsTest_right(String rowID, String description, JSONObject testData) throws Exception{
        contactUsPO driverContactUs = new contactUsPO();
        driverContactUs.loadPage();
        driverContactUs.clickContactUs();
        driverContactUs.dropsubjectHeading(1);
        driverContactUs.writeEmail(testData.get("email").toString());
        driverContactUs.writeorderReference(testData.get("orderReference").toString());
        driverContactUs.writeMessage(testData.get("message").toString());
        driverContactUs.writeFileUpload(testData.get("attachFile").toString());
        //driverContactUs.clickAttachFile();
        driverContactUs.clickSend();
        Assert.assertEquals(Browser.extractText(driverContactUs.getalertSuccess()), Global_Asserts.assertContactUs_Right);
    }

    @Test(groups = {"SMOKETEST"}, dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true)
    public void contactUsTest_wrong(String rowID, String description, JSONObject testData) throws Exception{
        contactUsPO driverContactUs = new contactUsPO();
        driverContactUs.loadPage();
        driverContactUs.clickContactUs();
        driverContactUs.dropsubjectHeading(1);
        driverContactUs.writeEmail(testData.get("email").toString());
        driverContactUs.writeorderReference(testData.get("orderReference").toString());
        driverContactUs.writeorderReference(testData.get("message").toString());
        driverContactUs.writeFileUpload(testData.get("attachFile").toString());
        driverContactUs.clickSend();
        Assert.assertEquals(Browser.extractText(driverContactUs.getalertError()), Global_Asserts.assertContactUs_Wrong);
    }

}
