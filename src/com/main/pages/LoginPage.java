package com.main.pages;


import org.apache.log4j.Logger;

import com.main.pages.PortalPage;
import com.main.utils.EBy;
import com.main.utils.PageDriver;
import com.main.utils.PageElement;


public class LoginPage extends PortalPage{
	
	public LoginPage(PageDriver driver){
		super(driver);
		logger = Logger.getLogger(LoginPage.class);
	}
	
	
	public void getLoginPage()
    {
		try {
			driver.visibilityWait(EBy.get("portal.loginLink")).click();
		}
		catch(Exception e) {
			logger.error(e);
		}
    }
	

    public String getCookie(String cookieName)
    {
    	return driver.getCookie(cookieName);
    
    }
    

    public boolean performLogin(String username, String password) {
    	
        boolean isDisplayed = false;
        
        try {
        	PageElement uname = driver.findElement("login.username");
        	PageElement passwd = driver.findElement("login.password");
       
            uname.clear();
            passwd.clear();
            uname.sendKeys(username);
            passwd.sendKeys(password);
            driver.findElement("login.loginBtn").click();
            Thread.sleep(2000);
            PageElement ele = driver.visibilityWait(EBy.get("portal.logoutLink"));
            isDisplayed = ele.isEnabled();
        }
        catch (Exception e)
        {
            logger.error(e);
        }
        return isDisplayed;
    }
    

    public String performLogout(String cookieName ) 
    {
    	try {
    		PageElement ele = driver.visibilityWait(EBy.get("portal.logoutLink"));
    		if(ele.isEnabled()) {
    			ele.click();
    			Thread.sleep(5000);
    		}
    	}
    	catch (Exception e)
        {
            logger.error(e);
        }
    	return getCookie(cookieName);
    }


}
