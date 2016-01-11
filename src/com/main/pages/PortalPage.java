package com.main.pages;

import org.apache.log4j.Logger;

import com.main.base.BasePage;
import com.main.utils.PageDriver;



public class PortalPage extends BasePage{
	PageDriver driver;
	
	public PortalPage(PageDriver driver) {
		this.driver = driver;
		logger = Logger.getLogger(PortalPage.class);
	}

	public String getTitle() {
		return(driver.getTitle());
	}
	
	
	public boolean isLogoDisplayed() {
		Boolean isDisplayed = false;
		try{
			isDisplayed = driver.findElement("portal.logo").isDisplayed();
		}
		catch (Exception e) {
			logger.error(e);
		}
		return isDisplayed;
	}
	
	
	public boolean isLoginEnabled() {
		
		Boolean isEnabled = false;
		try{
			isEnabled = driver.findElement("portal.loginLink").isEnabled();
		}
		catch (Exception e){
			logger.error(e);
		}
		return isEnabled;
	}
	
	
	public int getHeaderIconCount() {
		int count = 0;
		try{
			count = driver.findElements("header.social.links").size();
			}
			catch(Exception e) {
				logger.error(e);
			}
			return count;
	}
	
	
	public boolean clickHeaderIconFB() {
		Boolean isfacebook = false;
		try{
			driver.findElement("header.social.links.fblink").click();
			isfacebook = driver.getCurrentUrl().contains("facebook")?true:false;
			driver.navigateBack();
		}
		catch (Exception e) {
			logger.error(e);
		}
		return isfacebook;
	}
	
	
	public int getFooterIconCount() {
		int count = 0;
		try{
			driver.implicitWait();
			count = driver.findElements("footer.social.links").size();
		}
		catch (Exception e) {
			logger.error(e);
		}
		return count;
	}
	
	
	public String getContactNumber() {
		String text = null;
		try{
			text = driver.findElement("portal.contact.number").getText();
		}
		catch (Exception e){
			logger.error(e);
		}
		return text;
	}

}
