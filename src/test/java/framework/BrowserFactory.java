package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BrowserFactory extends BaseEntity {
    private static final String OPERATING_SYSTEM_NAME = System.getProperty("os.name");
    private static final String PROP_CHROME = "webdriver.chrome.driver";
    private static final String PROP_FIREFOX = "webdriver.gecko.driver";
    private static final String DRIVER_CHROME = "chromedriver";
    private static final String DRIVER_FIREFOX = "geckodriver";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String LINUX = "linux";
    private static final String EXE = ".exe";
    private static final String BROWSER_NAME = "browserName";
    private static String DRIVER_PATH = "src/test/resources/drivers/";
    private static BrowserFactory instance = null;
    private static Log log = Log.getInstance();


    private BrowserFactory() throws IOException {
        getDriverPath();
        setDriver(PropertyReader.getProperty(BROWSER_NAME));
    }

    public static BrowserFactory getInstance() {
        try {
            if (instance == null) {
                instance = new BrowserFactory();
            }
        } catch (IOException ex) {
            log.error("loc.err.open.browser");
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    private static void getDriverPath() throws IOException {
        DRIVER_PATH = new File(DRIVER_PATH).getCanonicalPath();
    }

    private static void setDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case CHROME:
                setPropertyBrowser(PROP_CHROME, DRIVER_CHROME);
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                setPropertyBrowser(PROP_FIREFOX, DRIVER_FIREFOX);
                driver = new FirefoxDriver(capabilityForFirefox());
                break;
            default:
                setPropertyBrowser(PROP_CHROME, DRIVER_CHROME);
                driver = new ChromeDriver();
        }
    }

    private static void setPropertyBrowser(String prop, String driverName) {
        System.setProperty(prop, Paths.get(DRIVER_PATH, driverName.concat(getOs())).toString());
    }


    private static String getOs() {
        return BrowserFactory.OPERATING_SYSTEM_NAME.toLowerCase().equals(LINUX) ? "" : EXE;
    }

    public static void switchHandles(int goHandle, int returnHandle) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(goHandle));
        driver.close();
        driver.switchTo().window(tabs.get(returnHandle));
    }

    private static DesiredCapabilities capabilityForChrome() {
        String downloadFilepath = DRIVER_PATH;
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        chromePrefs.put("safebrowsing.enabled", "true");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        System.setProperty("webdriver.chrome.logfile", "src/test/resources/logChrome.txt");
        System.setProperty("webdriver.chrome.verboseLogging", "true");
        return cap;
    }

    private static FirefoxOptions capabilityForFirefox() {
        FirefoxProfile prof = new FirefoxProfile();
        prof.setPreference("browser.download.dir", DRIVER_PATH);
        prof.setPreference("browser.download.folderList", 2);
        prof.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-debian-package, application/octet-stream");
        prof.shouldLoadNoFocusLib();
        FirefoxOptions option = new FirefoxOptions();
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "src/test/resources/logFirefox.txt");
        return option.setProfile(prof);

    }

    public static void quitDriver() {
        driver.quit();
        instance = null;
    }

    @Override
    protected String formatLogMsg(String message) {
        return message;
    }
}




