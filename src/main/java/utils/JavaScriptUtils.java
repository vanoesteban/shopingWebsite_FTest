package utils;


import drivers.CreateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Selenium JavaScript Executor Utility Class
 */

public class JavaScriptUtils {

    //constructor
    public JavaScriptUtils() {
    }

    /**
     * execute - generic method to execute a non-parameterized command
     *
     * @param command
     */
    public static void execute(String command) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(command);
    }// execute javascript

    /**
     * execute - overloaded method to execute a JS command on WebElement
     *
     * @param command
     * @param element
     */
    public static void execute(String command, WebElement element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(command, element);
    }// execute overloaded

    /**
     * click - method to execute a javascript click event
     *
     * @param element
     */
    public static void click(WebElement element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }// click

    /**
     * click - overloaded element to execute a click event using BY
     *
     * @param by
     */
    public static void click(By by) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebElement element = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

    }// click overloaded

    /**
     * sendKeys
     *
     * @param keys
     * @param element
     */
    public static void sendKeys(String keys, WebElement element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value ='" + keys + "';", element);
    }// sendKeys

    /**
     * isPageReady - method to verify that a page has completely render
     *
     * @param driver
     * @return
     */
    public static boolean isPageReady(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return document.readyState").equals("complete");
    } // isPageReady

    /**
     * isAjaxReady - method to verify that an ajax control has rendered
     * @param driver
     * @return
     */
    public static boolean isAjaxReady(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return JQuery.active ==0");
    }// isAjaxReady



}
