package utils;

import java.io.File;

/**
 * @author Carl Cocchiaro
 * <p>
 * Global Variable Utility Class
 */
public class Global_VARS {

    // browser defaults
    public static final String no_Browser = "";
    public static final String browser_Chrome = "chrome";
    public static final String browser_Firefox = "firefox";
    public static final String browser_IE = "internet explorer";
    public static final String browser_Edge = "edge";
    public static final String browser_Safari = "safari";
    public static String PLATFORM = "";
    public static final String ENVIRONMENT = "local";

    //titles
    public static final String home_Title = "My Store";
    public static final String login_Title = "My account - My Store";

    //error labels
    public static final String emailRegitered = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
    public static final String autentFailed = "There is 1 error\n" + "Authentication failed.";
    public static final String createaccountFailedwrongdata = "There are 7 errors\n" +
            "lastname is invalid.\n" +
            "firstname is invalid.\n" +
            "postcode is invalid.\n" +
            "city is invalid.\n" +
            "phone is invalid.\n" +
            "phone_mobile is invalid.\n" +
            "The Zip/Postal code you've entered is invalid. It must follow this format: 00000";


    //mobile default
    public static final String BROWSER_Mobile = "safari";
    public static final String PLATFORM_Mobile = "iphone";
    public static final String ENVIRONMENT_Mobile = "saucelabs";
    public static final String MOBILE = "Iphone 8 Simulator";


    // suite folder defaults
    public static String SUITE_NAME = null;
    public static final String TARGET_URL = "http://automationpractice.com/index.php";
    public static final String LOGIN_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation";
    public static String propFile = "selenium.properties";
    public static final String SE_PROPS = new File(propFile).getAbsolutePath();

    //test outputh path defaults
    public static final String TEST_OUTPUT_PATH = "test-output/";
    public static final String LOGFILE_PATH = TEST_OUTPUT_PATH + "Logs/";
    public static final String REPORT_PATH = TEST_OUTPUT_PATH + "Reports/";
    public static final String BITMAP_MAP = TEST_OUTPUT_PATH + "Bitmaps/";
    public static final String REPORT_CONFIG_FILE = "src/main/java/com/framework/ux/utils/chapter10/extent-config.xml";
    public static final String TEST_PROPS_PATH = "properties/testenviroment.properties";


    // suite timeout defaults
    public static final int TIMEOUT_MINUTE = 60;
    public static final int TIMEOUT_SECOND = 1;
    public static final int TIMEOUT_ZERO = 0;
    public static final int TIMEOUT_ELEMENT = 10;
    public static final int TIME_PAGEREADYLIMIT = 120;

}
