package com.main.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class PageDriver{

    public final Configuration configuration;
    private final Browsers browser;
    private WebDriver wdriver;
    private String mainWindowHandler;
    private Logger logger;
    
    private ESelect zSelect;
    private WindowHandler windowHandles = new WindowHandler();
    private FileUpload fileUpload =  new FileUpload();
    

    public PageDriver(Configuration configuration) {
        this.configuration = configuration;
        this.browser = configuration.browser;
        this.logger = Logger.getLogger(PageDriver.class);
        startDriver();
    }
    

    public WebDriver getDriver() {
        if (wdriver == null) {
            try {
                startDriver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wdriver;
    }
    

    public Browsers getBrowser() {
        return browser;
    }
    

    public String getMainWindowHandler() {
        return mainWindowHandler;
    }

    
  
    public PageElement findElement(String locator) {
        try {
            return new PageElement(wdriver, wdriver.findElement(EBy.get(locator)));
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

  
    public Collection<PageElement> findElements(String locator) {
        Collection<PageElement> elements = null;
        try {
            Collection<WebElement> webElements = wdriver.findElements(EBy.get(locator));
            if (webElements.size() > 0) {
                elements = new ArrayList<PageElement>();
            }
            for (WebElement element : webElements) {
                PageElement ele = new PageElement(wdriver, element);
                if (elements != null) elements.add(ele);
            }
            return elements;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
    }
  

    public void quit() {
        if (wdriver != null) {
	        wdriver.quit();
	        wdriver = null;
        }
    }
    

    public void close(){
        if(wdriver != null) {
            wdriver.close();
        }
    }
    

    public void open(String url) {
        wdriver.navigate().to(url);
        try {
            implicitWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public String getTitle() {
        return wdriver.getTitle();
    }
    

    public void implicitWait() {
        if (browser != Browsers.HtmlUnit) {
	        wdriver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
	    }
        else {
        	try {
        	  Thread.sleep(10000);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        }
	 
    }


    public String getCurrentUrl() {
        return wdriver.getCurrentUrl();
    }

    

    public WebDriver getWebDriver()
    {
    	return wdriver;
    }
    
  

    public String getDescription() {
        return "Browser";
    }

    public String getCookie(String cookieName){
         return(wdriver.manage().getCookieNamed(cookieName).getValue());
    }

    
    public void implicitWait(long timeout) {
       if (browser != Browsers.HtmlUnit) {
		        wdriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
       }
       else {
    	   try {
    		   Thread.sleep(timeout);
    	   }
    	   catch (Exception e)
    	   {
	    		logger.error(e);
	    		e.printStackTrace();
    	   }
        }
    }


    public void elementClickWait(By locator ) {
      if (browser != Browsers.HtmlUnit) {
            long timeout = Long.valueOf(configuration.waitTimeout).longValue();
            WebDriverWait wait = new WebDriverWait(wdriver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
      }
    }

    public PageElement visibilityWait(By locator)
    {
    	PageElement pElement = null;
        try {
            long timeout = Long.valueOf(configuration.waitTimeout).longValue();
            WebDriverWait wait = new WebDriverWait(wdriver, timeout);
            WebElement wElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Thread.sleep(2000);
            pElement =  new PageElement(wdriver, wElement);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pElement;
    }
    
    
    public void navigateBack(){
    	wdriver.navigate().back();
    }
    
    
    public void navigateForward() {
    	wdriver.navigate().forward();
    }
    
    
  
    public void waitForLoad()
    {
        if (browser != Browsers.HtmlUnit) {
            ExpectedCondition<Boolean> pageLoadCondition = new
                    ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver wdriver) {
                            return ((JavascriptExecutor) wdriver).executeScript("return document.readyState").equals("complete");
                        }
                    };
            long timeout = Long.valueOf(configuration.waitTimeout).longValue();
            WebDriverWait wait = new WebDriverWait(wdriver, timeout);
            wait.until(pageLoadCondition);
       }
    }
 

    public void presenceWait(By locator)
    {
        try {
        long timeout = Long.valueOf(configuration.waitTimeout).longValue();
        WebDriverWait wait = new WebDriverWait(wdriver, timeout);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
  
    public void getwWindowHandles(boolean isSingleWindow) {

        if(isSingleWindow) {
            windowHandles.switchToWindow(wdriver);
        }
        else {
            windowHandles.windowHandles(wdriver);
        }
    }
    

    public void uploadFile() throws IOException {
        if(fileUpload != null)
        	fileUpload.uploadFile(fileUpload.getFilePath());
    }
    

    public ESelect getwSelect(String locator)
    {
        try {
            zSelect = new ESelect(wdriver.findElement(EBy.get(locator)));
            return zSelect;
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }

        return null;
    }
    

    public String getPageSource()
    {
        return wdriver.getPageSource();
    }
    
    
    public void takeScreenShot()
    {
        if(browser != Browsers.HtmlUnit) {
            ScreenShot screenShot = new ScreenShot((TakesScreenshot) wdriver);
            screenShot.takeScreenShot(configuration.takeScreenShot);
        }
    }
    
    
    public Actions initializeAction()
    {
        return new Actions(wdriver);
    }
    
    

    private void startDriver() {
        try {
            switch (browser) {
                case Firefox:
                    wdriver = startFirefox();
                    break;
                case Chrome:
                   wdriver = startChrome();
                    break;
                case InternetExplorer:
                    wdriver = startInternetExplorer();
                    break;
                case HtmlUnit:
                    wdriver = startHtmlUnit();
                    break;
                case Safari:
                	logger.error("IN start driver-2!");
                    wdriver = startSafari();
                    break;
                default:
                    wdriver = startHtmlUnit();
                    break;
            }
            wdriver.get(configuration.baseUrl);
            if (browser != Browsers.HtmlUnit) {
                wdriver.manage().window().maximize();
                wdriver.manage().deleteAllCookies();
            }
            mainWindowHandler = wdriver.getWindowHandle();

        } catch (Exception ex) {
            logger.error(ex);
        }
    }
    
    private FirefoxDriver startFirefox() {
        FirefoxBinary fbin = new FirefoxBinary();
        fbin.setEnvironmentProperty("DISPLAY", ":2");
        return new FirefoxDriver(fbin, null);
    }
    
    private SafariDriver startSafari() {
    	SafariOptions options = new SafariOptions();
    	options.setUseCleanSession(true);
        return new SafariDriver(options);
    }

    private ChromeDriver startChrome() {
        System.setProperty("webdriver.chrome.driver", String.format("%s/chromedriver.exe", System.getProperty("user.dir")));
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("allow-file-access-from-files");
        options.addArguments("disable-rest-security");
        options.addArguments("ignore-certifcate-errors");
        options.addArguments("--always-authorize-plugins=true");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return new ChromeDriver(capabilities);
    }

  
    private HtmlUnitDriver startHtmlUnit() {
        return new HtmlUnitDriver(true);
    }
    
    

    private InternetExplorerDriver startInternetExplorer() {
        System.setProperty("webdriver.ie.driver", String.format("%s/IEDriverServer.exe", System.getProperty("user.dir")));
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, false);
        caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
        caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, "true");
        caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        return new InternetExplorerDriver(caps);
    }

    
 
}
