package drivers;

// Import WebDrivers


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import utils.File_IO;

//import utils.File_IO;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Selenium singleton Class
 *
 * @Author: Vano Islas
 */
public class CreateDriver {

    //local variables
    public static CreateDriver instance = null;
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    private ThreadLocal<AppiumDriver<MobileElement>> mobileDriver =
            new ThreadLocal<AppiumDriver<MobileElement>>();
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();
    private ThreadLocal<String> sessionBrowser = new ThreadLocal<String>();
    private ThreadLocal<String> sessionPlatform = new ThreadLocal<String>();
    private ThreadLocal<String> sessionVersion = new ThreadLocal<String>();
    private String enviroment;
    private Properties driverProps;
    private String getEnv = null;

     private static final String propertyFile = new File("src/main/java/properties/selenium.properties").getAbsolutePath();

    {
        //get The seleium.properties file with the browsers and driver configs
        try {
            driverProps = File_IO.loadProps(propertyFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Constructor
    private CreateDriver() {
    }

    //Method to retrive the active driver instance
    public static CreateDriver getInstance() {
        if (instance == null) {
            instance = new CreateDriver();
        }
        return instance;
    }

    /**
     * setDriver method to create Driver instance
     *
     * @param browser
     * @param platform
     * @param enviroment
     * @param preferences
     */
    @SafeVarargs
    public final void setDriver(String browser,
                                String platform,
                                String enviroment,
                                Map<String, Object>... preferences) throws Exception {

        String localHub = "app or media for simulate mobile";
        DesiredCapabilities caps;
        String getPlatform;


        //if error on browser type into switch set level to 7
        switch (browser) {
            case "firefox":
                caps = DesiredCapabilities.firefox();

                FirefoxOptions ffOptions = new FirefoxOptions();
                FirefoxProfile ffProfile = new FirefoxProfile();

                ffProfile.setPreference("browser.autofocus", true);
                ffProfile.setPreference("browser.tabs.remote.autostart.2", true);

                caps.setCapability(FirefoxDriver.PROFILE, ffProfile);
                caps.setCapability("marionette", true);

                if (enviroment.equalsIgnoreCase("local")) {
                    if(platform.equalsIgnoreCase("windows 10")){
                    System.setProperty("webdriver.gecko.driver", driverProps.getProperty("gecko.driver.windows.path"));
                    }
                    if(platform.equalsIgnoreCase("macos")){
                        System.setProperty("webdriver.gecko.driver", driverProps.getProperty("gecko.driver.macos.path"));
                    }
                    webDriver.set(new FirefoxDriver(ffOptions.merge(caps)));
                }

                break;
            case "chrome":
                caps = DesiredCapabilities.chrome();

                ChromeOptions chOptions = new ChromeOptions();
                Map<String, Object> chPreferences = new HashMap<String, Object>();

                chPreferences.put("credentials_enable_service", false);
                chOptions.setExperimentalOption("prefs", chPreferences);
                chOptions.addArguments("--disable-plugins", "--disable-extensions", "-disable-poput-blocking");
                caps.setCapability(ChromeOptions.CAPABILITY, chOptions);
                caps.setCapability("applicationCacheEnabled", false);

                if (enviroment.equalsIgnoreCase("local")) {
                    if(platform.equalsIgnoreCase("windows 10")){
                        System.setProperty("webdriver.chrome.driver", driverProps.getProperty("chrome.driver.windows.path"));
                    }
                    if(platform.equalsIgnoreCase("macos")){
                        System.setProperty("webdriver.chrome.driver", driverProps.getProperty("chrome.driver.macos.path"));
                    }
                    webDriver.set(new ChromeDriver(chOptions.merge(caps)));
                }

                break;
            case "internetexplorer":
               caps = DesiredCapabilities.internetExplorer();

                InternetExplorerOptions ieOptions = new InternetExplorerOptions();


                caps.setCapability("ignoreZoomSetting", true);
                if (enviroment.equalsIgnoreCase("local")) {
                    System.setProperty("webdriver.ie.driver", driverProps.getProperty("ie.driver.windows.path"));
                    webDriver.set(new InternetExplorerDriver(ieOptions.merge(caps)));
                }
                break;
            case "safari":
                caps = DesiredCapabilities.safari();

                SafariOptions saOptions = new SafariOptions();

                caps.setCapability(SafariOptions.CAPABILITY, saOptions);
                caps.setCapability("autpAccepAlerts", true);

                if (enviroment.equalsIgnoreCase("local")) {
                    System.setProperty("webdriver.safari.driver", driverProps.getProperty("safari.driver.windows.path"));
                    webDriver.set(new SafariDriver(saOptions.merge(caps)));
                }
                break;
            case "edge":
                caps = DesiredCapabilities.edge();

                EdgeOptions edOptions = new EdgeOptions();

                edOptions.setPageLoadStrategy("normal");
                caps.setCapability("requireWindowsFocus", true);

                if (enviroment.equalsIgnoreCase("local")) {
                    System.setProperty("webdriver.edge.driver", driverProps.getProperty("edge32.driver.windows.path"));
                    webDriver.set(new EdgeDriver(edOptions.merge(caps)));
                }
                break;
            case "ipad":
            case "iphone":
                //we use browser variable to setup the mobile
                if (browser.equalsIgnoreCase("ipad")) {
                    caps = DesiredCapabilities.ipad();
                } else {
                    caps = DesiredCapabilities.iphone();
                }
                caps.setCapability("appName", "https://myapp.com/myApp.zip");
                caps.setCapability("udid", "12345678"); //physical device
                caps.setCapability("device", "iPhone"); //or Ipad
                mobileDriver.set(new IOSDriver<MobileElement>(new URL(localHub), caps));
                break;
            case "android":
                caps = DesiredCapabilities.android();
                caps.setCapability("appName", "https://myapp.com/myApp.apk");
                caps.setCapability("udid", "12345678"); //physical device
                caps.setCapability("device", "Android");
                mobileDriver.set((new AndroidDriver<MobileElement>(new URL(localHub), caps)));
                break;
        }

        getEnv = "local";
        getPlatform = platform;

    }

    /**
     * Overloaded setDriver method to swith driver to specific WebDriver
     * if running concurrent drivers
     *
     * @param driver Webdriver instance to switch to
     */
    public void setDriver(WebDriver driver) {
        webDriver.set(driver);
        sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId().toString());
        sessionBrowser.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getBrowserName());
        sessionPlatform.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getPlatform().toString());
    }

    /**
     * Overladed setDriver method to switch to specific AppiumDriver if running concurrent drivers
     *
     * @param driver AppiumDriver instance to switch to
     */
    public void setDriver(AppiumDriver<MobileElement> driver) {
        mobileDriver.set(driver);
        sessionId.set(mobileDriver.get().getSessionId().toString());
        sessionBrowser.set(mobileDriver.get().getCapabilities().getBrowserName());
        sessionPlatform.set(mobileDriver.get().getCapabilities().getPlatform().toString());
    }

    /**
     * getDriver will retrieve the active WebDriver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return webDriver.get();
    }

    /**
     * getDriver method will retrieve the active AppiumDriver
     *
     * @param mobile boolean parameter
     */
    public AppiumDriver<MobileElement> getDriver(boolean mobile) {
        return mobileDriver.get();
    }

    /**
     * getCurrentDriver method will retrieve the active WebDriver or AppiumDriver
     *
     * @return WebDriver
     */
    public WebDriver getCurrentDriver() {
        try {
            if (getInstance().getSessionBrowser().contains("iphone")
                    || getInstance().getSessionBrowser().contains("ipad")
                    || getInstance().getSessionBrowser().contains("android"))
                return getInstance().getDriver(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getInstance().getDriver();
    } //getCurrentDriver

    /**
     * getSessionId method to retrieve active id
     *
     * @return String
     * @throws Exception
     */
    public String getSessionId() throws Exception {
        return sessionId.get();
    }

    /**
     * getSessionBrowser method to retrieve active browser
     *
     * @return String
     * @throws Exception
     */
    public String getSessionBrowser() throws Exception {
        return sessionBrowser.get();
    }

    /**
     * getSessionVersion method to retrieve active version
     *
     * @return String
     * @throws Exception
     */
    public String getSessionVersion() throws Exception {
        return sessionVersion.get();
    }

    /**
     * getSessionPlatform method to retrieve active platform
     *
     * @return String
     * @throws Exception
     */
    public String getSessionPlatform() throws Exception {
        return sessionPlatform.get();
    }


    /**
     * driverWait method pauses the driver in seconds
     *
     * @param seconds to pause
     */
    public void driverWait(long seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
        }
    } //wait in seconds

    /**
     * driverRefresh method reloads the current browser page
     */
    public void driverRefresh() {
        getCurrentDriver().navigate().refresh();
    } //refresh browser

    /**
     * closeDriver method quit the current active driver
     */
    public void closeDriver() {
        try {
            getDriver().quit();
        }

        catch ( Exception e ) {
            // do something
        }
    }//CloseDriver


}
