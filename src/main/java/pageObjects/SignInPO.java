package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.Browser;

public class SignInPO<M extends WebElement> extends BasePO<M> {


    public SignInPO() throws Exception {
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
        Browser.click(this.signIn);
        Browser.write(this.email_create, email);
        Browser.click(this.submitcreate);
        Browser.waitFor(this.firstname, 5);
        Browser.click(this.Mr);
        Browser.write(this.firstname, firstname);
        Browser.write(this.lastname, lastname);
        Browser.write(this.password, password);
        Browser.dropdown(this.days, 10);
        Browser.dropdown(this.months, 5);
        Browser.dropdown(this.years, 25);
        Browser.write(this.company, company);
        Browser.write(this.address1, address);
        Browser.write(this.address2, address2);
        Browser.write(this.city, city);
        Browser.dropdown(this.country, 1);
        Browser.dropdown(this.state, 1);
        Browser.write(this.other, other);
        Browser.write(this.phone, phone);
        Browser.write(this.mobile, mobile);
        Browser.write(this.zip, zip);
        Browser.write(this.alias, alias);
        Browser.click(this.register);
    }

    public void createAccountExist(String email) throws Exception{
        Browser.click(this.signIn);
        Browser.write(this.email_create, email);
        Browser.click(this.submitcreate);
        Browser.waitFor(this.alertcreateaccount, 2);
    }

    public void login(String email, String password) throws Exception
    {
        Browser.click(this.signIn);
        Browser.write(this.email, email);
        Browser.write(this.password, password);
        Browser.click(this.submitlogin);
    }

    public void loginwitherror(String email, String password) throws Exception {
        Browser.click(this.signIn);
        Browser.write(this.email, email);
        Browser.write(this.password, password);
        Browser.click(this.submitlogin);
    }

    public void logout() throws Exception{
        Browser.click(this.logout);
    }
}

