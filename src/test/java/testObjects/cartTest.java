package testObjects;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.CartPO;
import utils.Browser;
import utils.Global_Asserts;
import utils.Global_VARS;
import utils.JavaScriptUtils;

public class cartTest extends BaseTest {


    @Test(groups = {"SMOKETEST"}, alwaysRun = true, enabled = true)
    public void addToCart() throws Exception {
        CartPO driverCart = new CartPO();
        driverCart.loadPage();
        Browser.click(driverCart.getWomenlinkvar());
        driverCart.addtoCartCheckout(driverCart.getAddtocartDress1());
        Browser.waitFor(driverCart.getTableCart(), 1);
        Assert.assertEquals(driverCart.getCartContains().getText(),
                Global_Asserts.assertProductInCart + " " + driverCart.getProductsQuantity().getText());
    }

    @Test(groups = {"SMOKETEST"}, alwaysRun = true)
    public void deleteCartSummary() throws Exception {
        CartPO driverCart = new CartPO();
        driverCart.loadPage();
        Browser.click(driverCart.getWomenlinkvar());
        driverCart.addtoCartCheckout(driverCart.getAddtocartDress1());
        Browser.waitFor(driverCart.getTableCart(), 1);
        driverCart.deleteCartElement();
        Browser.waitFor(driverCart.getEmptyCart(), 1);
        Assert.assertEquals(driverCart.getEmptyCart().getText(), Global_Asserts.assertEmptyCart);
    }

    @Test(groups = {"SMOKETEST"}, alwaysRun = true)
    public void deleteAllCartbutton() throws Exception {
        CartPO driverCart = new CartPO();
        driverCart.loadPage();
        Browser.click(driverCart.getWomenlinkvar());
        Browser.waitFor(driverCart.getProducList(), 1);
        driverCart.addListToCart();
        JavaScriptUtils.click(driverCart.getProceedtoCheckout());
        Browser.waitFor(driverCart.getTableCart(), 1);
        driverCart.deleteCartAllElements();
        Browser.waitFor(driverCart.getEmptyCart(), 2);
        Assert.assertEquals(driverCart.getEmptyCart().getText(), Global_Asserts.assertEmptyCart);
    }

}
