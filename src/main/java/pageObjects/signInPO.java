package pageObjects;

import drivers.CreateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.BrowserUtils;

public class signInPO<M extends WebElement> extends BasePO<M> {


    public signInPO() throws Exception {
    super();

    }

    @FindBy(className = "login")
    @CacheLookup
    protected M signIn;

    @FindBy(id = "email_create")
    protected M email_create;

    @FindBy(id = "SubmitCreate")
    protected M submitcreate;

    @FindBy(id = "SubmitLogin")
    protected M submitlogin;

    @FindBy(id = "id_gender1")
    protected M Mr;

    @FindBy(id = "id_gender2")
    protected M Mrs;

    @FindBy(id = "customer_firstname")
    protected M firstname;

    @FindBy(id = "customer_lastname")
    protected M lastname;

    @FindBy(id = "email")
    protected M email;

    @FindBy(id = "passwd")
    protected M password;

    @FindBy(id = "firstname")
    protected M addressfirstname;

    @FindBy(id = "lastname")
    protected M addresslastname;

    @FindBy(id = "company")
    protected M company;

    @FindBy(id = "address1")
    protected M address1;

    @FindBy(id = "address2")
    protected M address2;

    @FindBy(id = "city")
    protected M city;

    @FindBy(id = "postcode")
    protected M zip;

    @FindBy(id = "other")
    protected M other;

    @FindBy(id = "phone")
    protected M phone;

    @FindBy(id = "phone_mobile")
    protected M mobile;

    @FindBy(id = "alias")
    protected M alias;

    @FindBy(id = "days")
    protected M days;

    @FindBy(id = "months")
    protected M months;

    @FindBy(id = "years")
    protected M years;

    @FindBy(id = "id_state")
    protected M state;

    @FindBy(id = "id_country")
    protected M country;

    @FindBy(id = "submitAccount")
    protected M register;

    @FindBy(className = "logout")
    private M logout;

    //error messages
    @FindBy(className = "alert-danger")
    public M alertlogin;

    @FindBy(id = "create_account_error")
    public M alertcreateaccount;

    //assert elements
    @FindBy(className = "header_user_info")
    public M userinfo;

    public void createAccount(String email, String firstname, String lastname, String password,String company,String address,String address2,String city,String zip,String other,String phone,String mobile, String alias ) throws Exception{
        this.signIn.click();
        this.email_create.sendKeys(email);
        this.submitcreate.click();
        BrowserUtils.waitFor(this.firstname, 5);

        this.Mr.click();
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
        String emailverify = this.email.getText();
        this.password.sendKeys(password);
        BrowserUtils.dropdown(this.days, 10);
        BrowserUtils.dropdown(this.months, 5);
        BrowserUtils.dropdown(this.years, 25);
        //this.addressfirstname.sendKeys(firstname);
        //this.addresslastname.sendKeys(lastname);
        this.company.sendKeys(company);
        this.address1.sendKeys(address);
        this.address2.sendKeys(address2);
        this.city.sendKeys(city);
        BrowserUtils.dropdown(this.country, 1);
        BrowserUtils.dropdown(this.state, 1);
        this.other.sendKeys(other);
        this.phone.sendKeys(phone);
        this.mobile.sendKeys(mobile);
        this.zip.sendKeys(zip);
        BrowserUtils.write(this.alias, alias);
        this.register.click();
        //Assert.assertEquals(userinfo.getText(), "Jorge Esteban");
    }

    public void createAccountExist(String email) throws Exception{
        this.signIn.click();
        this.email_create.sendKeys(email);
        this.submitcreate.click();
        BrowserUtils.waitFor(this.alertcreateaccount, 2);
    }

    public void login(String email, String password) throws Exception
    {
        this.signIn.click();
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        this.submitlogin.click();
        Assert.assertEquals(userinfo.getText(), "Jorge Esteban");

    }

    public void loginwitherror(String email, String password) throws Exception {
        this.signIn.click();
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        this.submitlogin.click();

    }

    public void logout(){
        logout.click();
    }


}

