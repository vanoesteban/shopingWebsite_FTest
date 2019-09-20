package testObjects;

import json.JSONDataProvider;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.SearchPO;
import utils.Browser;
import utils.Global_VARS;

public class SearchTest extends BaseTest{

    @Test(groups = {"SMOKETEST"},dataProvider =  "myData_JSON", dataProviderClass = JSONDataProvider.class, alwaysRun = true, enabled = true)
    public void searchQuery(String rowId, String description, JSONObject testData) throws Exception{
        SearchPO driverSearch = new SearchPO();
        driverSearch.loadPage();
        driverSearch.searchQueryTop(testData.get("query").toString());
        switch(rowId){
            case "searchQuery.01":
                int sizeListdress = Browser.returnSizeFromList("product_list");
                Assert.assertEquals(sizeListdress, 7);
                break;
            case "searchQuery.02":
                int sizeListshirt = Browser.returnSizeFromList("product_list");
                Assert.assertEquals(sizeListshirt, 1);
                break;
            case "searchQuery.03":
                Browser.waitFor(driverSearch.getNotFound(), 2);
                Assert.assertEquals(driverSearch.getNotFound().getText(), Global_VARS.assertNotFoundSearch + " \"women\"");
                break;
        }
    }
}
