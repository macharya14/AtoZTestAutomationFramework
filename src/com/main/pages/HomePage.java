package com.main.pages;


import org.apache.log4j.Logger;

import com.main.utils.PageDriver;

public class HomePage extends PortalPage{

	public HomePage(PageDriver driver) {
		super(driver);
		logger = Logger.getLogger(HomePage.class);
	}
	
	public int getSliderImageCount(){
		int count = 0;
		try{
		count = driver.findElements("home.slider.items").size();
		}
		catch(Exception e){
			logger.error(e);
		}
		return count;
	}

	
	public int getNavBarItemsCount(){
		int count = 0;
		try{
		count = driver.findElements("portal.navbar").size();
		}
		catch(Exception e){
			logger.error(e);
		}
		return count;
	}
	
	public int getNavBarResourcesCount(){
		int count = 0;
		try{
		count = driver.findElements("portal.navbar.resources").size();
		}
		catch(Exception e) {
			logger.error(e);
		}
		return count;
	}
	
	
	
}
