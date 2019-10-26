package testObjects;

import json.JSONDataProvider;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.SearchPO;
import utils.Browser;
import utils.Global_VARS;

public class SearchTest extends BaseTest {

    @Test(groups = {"SMOKETEST"}, dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = true)
    public void searchQuery(String rowId, String description, JSONObject testData) throws Exception {
        SearchPO driverSearch = new SearchPO();
        driverSearch.loadPage();
        driverSearch.searchQueryTop(testData.get("query").toString());
        Assert.assertEquals(true, Browser.elementExists(driverSearch.getFound(), 1));
    }

    @Test(groups = {"SMOKETEST"},dataProvider =  "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = true)
    public void searchQuery_Wrong(String rowId, String description, JSONObject testData) throws Exception{
        SearchPO driverSearch = new SearchPO();
        driverSearch.loadPage();
        driverSearch.searchQueryTop(testData.get("query").toString());
        Assert.assertEquals(true, Browser.elementExists(driverSearch.getNotFound(), 1));
    }

}/* */

