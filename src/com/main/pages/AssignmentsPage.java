package com.main.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;

import com.main.utils.PageDriver;
import com.main.utils.PageElement;
import com.main.pages.LoginPage;


public class AssignmentsPage extends PortalPage{
	
	LoginPage lpage;
	
	public AssignmentsPage(PageDriver driver) {
		super(driver);
		logger = Logger.getLogger(AssignmentsPage.class);
		this.lpage = new LoginPage(driver);
		
	}
	
	public Boolean getLoginStatus(String userName, String passWord) {
        lpage.getLoginPage();
        driver.waitForLoad();
        return(lpage.performLogin(userName, passWord));
	}
	

	public int getAssignments(String assignType) {
		
		 int count = 0;
		 try {
            Actions action = driver.initializeAction();
            PageElement resourceElement = driver.findElement("home:resource");
            resourceElement.mouseOver(action);
            driver.takeScreenShot();
            clickOnAssignments();
            driver.takeScreenShot();
            openAssignmentType(assignType);
            driver.takeScreenShot();
            count = getAssignmentQuestions();
		 }
		 catch (Exception e) {
	         logger.error(e);
	    }
		 return count;
	}

    

    public void clickOnAssignments()
    {
    	driver.implicitWait();
        List<PageElement> elements = (List<PageElement>)driver.findElements("home:resource.list");
        for (PageElement element : elements)
        {
            if(element.getAttribute("href").contains("assignments"))
            { 
            	element.click();
                driver.waitForLoad();
                break;
            }
        }
    }
    
    
    
    
    public void openAssignmentType(String assignType) {
    	
    	try {
	    	driver.implicitWait();
	        PageElement resourceElement = driver.findElement("assignments.questions");
	        if(resourceElement.isDisplayed())
	        {
	        	resourceElement.click();
	        	Thread.sleep(2000);
	        }
    	}
    	catch (Exception e) {
    		logger.debug(e);
    	}
    }
    
    
 
    public int getAssignmentQuestions() {
    	return driver.findElements("assignments.questions.categories").size();
    }
   

    public void openAssignmentName()
    {
    	driver.implicitWait();
    	driver.findElement("assignments.questions.categories.500-must").click();
        driver.getwWindowHandles(false);
        driver.waitForLoad();
    }
   
}
