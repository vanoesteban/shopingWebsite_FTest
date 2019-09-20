package utils;


import drivers.CreateDriver;
import org.apache.tools.ant.taskdefs.Java;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.List;

public class Browser {



    public void elementExistConsole(WebElement element, int timer) {
        Boolean result = null;
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);
        if ((!exists.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(element)))))
            System.out.println("The element exist in the webPage %s" + element.getText());
        else System.out.println("The element not exist or isn't visible in the webPage");
    }

    /**
     * waitFor method to wait up to a designated periodbefore
     * throwing exception (static locator)
     *
     * @param element
     * @param timer
     * @throws Exception
     */
    public static void waitFor(WebElement element, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();

        //wait ofr the static element to appear
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }//waitFor

    /**
     * overloaded waitFor method to wait up to a designed period before throwing an exception(dynamic locator)
     *
     * @param by
     * @param timer
     * @thows Exception
     */
    public static void waitFor(By by, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();

        //wait for the dynamic element to appear
        WebDriverWait exists = new WebDriverWait(driver, timer);

        //examples By.id(id), By.name(name)
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(by)));
    } //waitFor overlad by

    /**
     * WairForGone method to wait up to a designated period before
     * throwing exception if element still exists
     *
     * @param by
     * @param timer
     * @throws Exception
     */
    public static void waitForGone(By by, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();

        //wait for the dynamic element to disappear
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOfElementLocated(by)));
    } //waitForGone

    /**
     * waitForURL methot to wait up to a designated period before
     * throwing exception is URL is not found
     *
     * @param url
     * @param seconds
     * @throws Exception
     */
    public static void waitForURL(String url, int seconds) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, seconds);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(url)));
    }// waitForURL

    /**
     * waitFor method to wait up to a designated period before
     * throwing an exception if the title is not found
     *
     * @param title
     * @param timer
     * @throws Exception
     */
    public static void waitFor(String title, int timer) {
        WebDriver driver = CreateDriver.getInstance().getCurrentDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
    }


    public static boolean isAjaxReady(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return jQuery.active==0");
    }

    public static void isPageReady(WebDriver driver) {
        WebDriverWait pageReady = new WebDriverWait(driver, Global_VARS.TIMEOUT_ELEMENT);
        pageReady.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements()));
    }


    public static boolean elementExists(WebElement element, int timeoutSecond) {
        try {
            WebDriver driver = CreateDriver.getInstance().getDriver();
            WebDriverWait exists = new WebDriverWait(driver, timeoutSecond);
            exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
            return true;
        } catch (StaleElementReferenceException | TimeoutException | ElementNotVisibleException | NoSuchElementException e) {
            return false;
        } finally {
            // clean up
        }
    }

    public static <M extends WebElement>  void dropdown(M element, int index) {
        Select dropdown = new Select(element);
        dropdown.selectByIndex(index);
    }

    public static <M extends WebElement>  void write(M element, String keys) {
        element.clear();
        element.sendKeys(keys);
    }

    public static <M extends WebElement>  void click(M element) throws Exception {
        element.click();
    }

    public static <M extends WebElement> void submit(M element) {
        element.submit();
    }

    public static String extractText(WebElement element) {
        return element.getText();
    }

    public static void HoverAndClick(WebElement elementToHover) {
        Actions action = new Actions(CreateDriver.getInstance().getDriver());
        action.moveToElement(elementToHover);
    }

    //TABLES
    /**
     * Get element from a table with a tagname, the element attribute and the index
     * @param idtable
     * @param tagname
     * @param attribute
     * @param index
     *
     */
    public static String elementFromTable(String idtable, String tagname, String attribute, int index){
        WebElement table = CreateDriver.getInstance().getDriver().findElement(By.id(idtable));
        List<WebElement> tableElements = table.findElements(By.tagName(tagname));
        String tableid = tableElements.get(index).getAttribute(attribute);
        return tableid;
    }

    public static List<WebElement> elementsFromTable(String locatorTable){
        WebElement table = CreateDriver.getInstance().getDriver().findElement(By.id(locatorTable));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        List<WebElement> tableTr = tableBody.findElements(By.tagName("tr"));
        return tableTr;
    }


    //LISTS
    public static int returnSizeFromList(String listclassname){
        WebElement list = CreateDriver.getInstance().getDriver().findElement(By.className(listclassname));
        List<WebElement> listElements = list.findElements(By.className("ajax_block_product"));
        return listElements.size();
    }

    //GET ELEMENTS
    public static WebElement getElementById(String id){
        return CreateDriver.getInstance().getDriver().findElement(By.id(id));
    }

}