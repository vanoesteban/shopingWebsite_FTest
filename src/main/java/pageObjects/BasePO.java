package pageObjects;

import drivers.CreateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;
import utils.Global_VARS;
import utils.JavaScriptUtils;

public class BasePO <M extends WebElement> {
    //local variables
    public int elementWairl;
    public String pageTitle;


    //constructor
    public BasePO() throws Exception {
        PageFactory.initElements(CreateDriver.getInstance().getDriver(), this);
    }

    //WebElements to sync
    @FindBy(id = "header")
    @CacheLookup
    protected M header;

    @FindBy(id = "slider_row")
    @CacheLookup
    protected M slider_row;

    @FindBy(id = "homefeatured")
    @CacheLookup
    protected M homefeatured;

    @FindBy(id = "htmlcontent_home")
    @CacheLookup
    protected M htmlcontent_home;

    @FindBy(id = "facebook_block")
    @CacheLookup
    protected M facebook_block;

    @FindBy(id = "cmsinfo_block")
    @CacheLookup
    protected M cmsinfo_block;

    @FindBy(id = "editorial_block_center")
    @CacheLookup
    protected M editorial_block_center;


    @FindBy(id = "contact-link")
    @CacheLookup
    protected M contactus;

    @FindBy(className = "login")
    @CacheLookup
    protected M signIn;

    @FindBy(xpath = "//*[@id=\"header_logo\"]/a/img")
    @CacheLookup
    protected M logo;

    @FindBy(id = "search_query_top")
    @CacheLookup
    protected M search_txt;

    @FindBy(name = "submit_search")
    @CacheLookup
    protected M search_btn;

    @FindBy(linkText = "Cart")
    @CacheLookup
    protected M cart;

    @FindBy(linkText = "Women")
    @CacheLookup
    protected M women_btn;

    @FindBy(linkText = "Dresses")
    @CacheLookup
    protected M dresses_btn;

    @FindBy(linkText = "T-shirts")
    @CacheLookup
    protected M tshirts_btn;

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
     * getCopyright - method to return the page copyright text
     * @throws Exception
     */


    /**
     * Load PAge and sync against header.
     *
     * @throws Exception
     */
    public void loadPage() throws Exception {
        WebDriver siteDriver = CreateDriver.getInstance().getDriver();
        CreateDriver.getInstance().setDriver(siteDriver);
        siteDriver.navigate().to(Global_VARS.TARGET_URL);
        siteDriver.manage().window().maximize();
        /*if (!(JavaScriptUtils.isPageReady(siteDriver))) {
            BrowserUtils.waitFor(Global_VARS.home_Title, Global_VARS.TIMEOUT_SECOND);
        }*/

    }

    public void loadPageLogin(String browser) throws Exception {
        WebDriver siteDriver = CreateDriver.getInstance().getDriver();
        CreateDriver.getInstance().setDriver(siteDriver);
        siteDriver.navigate().to(Global_VARS.LOGIN_URL);
        siteDriver.manage().window().maximize();
        if (!(JavaScriptUtils.isPageReady(siteDriver))) {
            BrowserUtils.waitFor(cart, Global_VARS.TIMEOUT_SECOND);
        }

    }

    public boolean elementExists() throws Exception{
        return BrowserUtils.elementExists(homefeatured, 1/2);

    }



}
