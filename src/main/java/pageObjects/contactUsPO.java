package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Browser;

public class contactUsPO<M extends WebElement> extends BasePO<M> {

    /**
     * Constructor - PageFactory
     *
     * @throws Exception
     */
    public contactUsPO() throws Exception {
        super();
    }

    @FindBy(className = "alert-danger")
    protected M alertError;

    public M getalertError(){
        return alertError;
    }

    @FindBy(className = "alert-success")
    protected M alertSuccess;

    public M getalertSuccess(){
        return alertSuccess;
    }

    @FindBy(id = "contact-link")
    protected M contactUs;

    @FindBy(id = "id_contact")
    protected M subjectHeading;

    @FindBy(id = "email")
    protected M email;

    @FindBy(id = "id_order")
    protected M orderReference;

    @FindBy(id = "message")
    protected M message;

    @FindBy(id = "uniform-fileUpload")
    protected M attachFile;

    @FindBy(id = "fileUpload")
    protected M fileUpload;

    @FindBy(id = "submitMessage")
    protected M send;


    public void clickContactUs() throws Exception {
        Browser.click(contactUs);
    }

    public void dropsubjectHeading(int index) {
        Browser.dropdown(subjectHeading, index);
    }

    public void writeEmail(String keys) {
        Browser.write(email, keys);
    }

    public void writeorderReference(String keys) {
        Browser.write(orderReference, keys);
    }

    public void writeMessage(String keys) {
        Browser.write(message, keys);
    }

    public void clickAttachFile() throws Exception {
        Browser.click(attachFile);
    }

    public void clickSend() throws Exception {
        Browser.click(send);
    }

    public void writeFileUpload(String file){
        fileUpload.sendKeys(file);
    }


}
