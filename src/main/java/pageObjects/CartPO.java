package pageObjects;

import drivers.CreateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Browser;
import utils.JavaScriptUtils;

import java.util.List;

public class CartPO<M extends WebElement> extends BasePO<M> {

    //NAVBAR BUTTONS
    @FindBy(css = "#block_top_menu > ul > li:nth-child(1) > a")
    protected M womenlinkvar;

    @FindBy(css = "#block_top_menu > ul > li.sfHoverForce > a")
    protected M tshirtlinkVar;

    //PRODUCT LIST
    @FindBy(className = "product_list")
    protected M producList;

    @FindBy(css = "#center_column > ul > li:nth-child(1)")
    protected M hoverMouse;

    //DRESSES ELEMENTS
    @FindBy(css = "#center_column > ul > li:nth-child(1) > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default")
    protected M addtocartDress1;

    @FindBy(css = "#center_column > ul > li:nth-child(2) > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default")
    protected M addtocartDress2;


    //popup windows after add product
    @FindBy(id = "layer_cart")
    protected M layerCart;

    @FindBy(css = "#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a")
    protected M proceedtoCheckout;

    @FindBy(css = "#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > span")
    protected M continueShopping;

    @FindBy(className = "cross")
    protected M closecross;

    @FindBy(className = "shopping_cart")
    protected M cartbutton;

    @FindBy(className = "heading-counter")
    protected M cartContains;

    @FindBy(id = "summary_products_quantity")
    protected M productsQuantity;

    //delete cart elements
    @FindBy(id = "cart_summary")
    protected M tableCart;

    @FindBy(className = "alert-warning")
    protected M emptyCart;

    public CartPO() throws Exception {
        super();
    }

    //Setters and Getters

    public M getTableCart() {
        return this.tableCart;
    }

    public M getEmptyCart() {
        return this.emptyCart;
    }

    public M getWomenlinkvar() {
        return this.womenlinkvar;
    }

    public M getAddtocartDress1() {
        return this.addtocartDress1;
    }

    public M getProceedtoCheckout() {
        return this.proceedtoCheckout;
    }

    public M getCartContains() {
        return this.cartContains;
    }

    public M getProductsQuantity() {
        return this.productsQuantity;
    }

    public M getProducList(){
        return this.producList;
    }


    //*********************************************************************************************************************
    //ADDING ELEMENTS TO CART
    public void addtoCartCheckout(M element) throws Exception {
        JavaScriptUtils.click(element);
        if (Browser.elementExists(layerCart, 1)) {
            JavaScriptUtils.click(proceedtoCheckout);
        }
    }

    public void addtoCartContinue(WebElement element) throws Exception {
        JavaScriptUtils.click(element);
        if (Browser.elementExists(layerCart, 1)) {
            JavaScriptUtils.click(continueShopping);
        }
    }

    public void addListToCart() throws Exception {
        Browser.waitFor(producList, 1);
        int listSize = Browser.returnSizeFromList("product_list");
        for (int i = 1; i < listSize; i++) {
            WebElement elementadded = CreateDriver.getInstance().getDriver().findElement(By.cssSelector(
                    "#center_column > ul > li:nth-child(" + i +
                            ") > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default"));
            addtoCartContinue(elementadded);
        }
    }

    public void addWomenOutfit() throws Exception {
        Browser.click(womenlinkvar);
        JavaScriptUtils.click(addtocartDress1);
        if (Browser.elementExists(layerCart, 1)) {
            JavaScriptUtils.click(proceedtoCheckout);
        }
    }

    //**********************************************************************************************************************
    //DELETE ELEMENTS
    public void deleteCartElement() throws Exception {
        String elementtable = Browser.elementFromTable("cart_summary", "tr",
                "id", 8);
        String elementtoDelete = elementtable.substring(8, 15);
        WebElement cartDelete = CreateDriver.getInstance().getDriver().findElement(By.id(elementtoDelete));
        Browser.click(cartDelete);

    }

    public void deleteCartAllElements() throws Exception {
        List<WebElement> elementsFromTable = Browser.elementsFromTable("cart_summary");
        for (int i = 0; i < elementsFromTable.size(); i++) {
            String elementToDelete = elementsFromTable.get(i).getAttribute("id").substring(8);
            Browser.getElementById(elementToDelete).click();
        }

    }

    public void goToCart() throws Exception {
        Browser.click(cartbutton);
    }
}

