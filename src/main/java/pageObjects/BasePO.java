package pageObjects;

import drivers.CreateDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Global_VARS;


public class BasePO<M extends WebElement> {

    /**
     * Constructor - PageFactory
     * @throws Exception
     */
    public BasePO() throws Exception {
        PageFactory.initElements(CreateDriver.getInstance().getDriver(), this);
    }

    /**
     * getTitle - method to return title of the current page
     *
     * @return
     * @throws Exception
     */
    public String getTitle() throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        return driver.getTitle();
    }


    /**
     * Load PAge
     *
     * @throws Exception
     */
    public void loadPage() throws Exception {
        WebDriver siteDriver = CreateDriver.getInstance().getDriver();
        CreateDriver.getInstance().setDriver(siteDriver);
        siteDriver.navigate().to(Global_VARS.TARGET_URL);
        siteDriver.manage().window().maximize();
    }


}
