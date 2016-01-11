package com.main.utils;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;



public class PageElement implements Elements{
	
	 private  final WebDriver driver;
	 private  final WebElement element;
	 private Logger logger;
	 private Collection<PageElement> elements = null;

	  
	
	public PageElement(WebDriver driver, WebElement element){
		this.driver = driver;
		this.element = element;
		logger = Logger.getLogger(PageElement.class);
		
	}
	
	

	@Override
	public PageElement findElement(String locator) {
		try {
	            return new PageElement(driver, element.findElement(EBy.get(locator)));
	    } catch (Exception e) {
	            logger.error(e);
	            return null;
	    }
	}
	

	@Override
	public Collection<PageElement> findElements(String locator) {
		 
	     try {
            Collection<WebElement> webElements = element.findElements(EBy.get(locator));
            if (webElements.size() > 0) {
                elements = new ArrayList<PageElement>();
            }
            for (WebElement element : webElements) {
                PageElement ele = new PageElement(driver, element);
                if (elements != null) elements.add(ele);
            }
            return elements;
        } catch (Exception e) {
            logger.error(e);
            return null;
	    }
	}
	

	@Override
	public String getDescription() {
		return element.getTagName();
	}
	
	public void mouseOver(Actions action)
    {
        action.moveToElement(element).build().perform();
    }
	
	
	public boolean isDisplayed() {
        return element.isDisplayed();
    }
	
	
	public boolean isEnabled() {
        return element.isEnabled();
    }
	
	public String getAttribute(String name) {
        return element.getAttribute(name);
    }
	
	
	public int size() {
		return elements.size();
	}
	
	public void click() {
		element.click();
	}
	
	public void clear() {
		element.clear();
	}
	
	public void sendKeys(String keys) {
		element.sendKeys(keys);
	}
	
	public String getText() {
		return element.getText();
	}

}
