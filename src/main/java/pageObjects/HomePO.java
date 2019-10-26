package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import utils.Browser;

public class HomePO<M extends WebElement> extends BasePO<M> {
    //local variables
    public int elementWairl;
    public String pageTitle;


    /**
     *
     * @throws Exception
     */
    public HomePO() throws Exception {
        super();
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

    @FindBy(css = "#header > div.nav > div > div > nav > span > strong")
    protected M callusNow;

    public M getContactus() {
        return contactus;
    }

    public String callUsNow(){
        return Browser.extractText(callusNow);
    }

}
