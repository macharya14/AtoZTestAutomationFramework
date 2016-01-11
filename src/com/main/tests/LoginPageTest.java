package com.main.tests;


import org.testng.annotations.Test;
import org.testng.Assert;
import static org.testng.Assert.assertTrue;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.main.pages.LoginPage;


public class LoginPageTest extends BasePageTest{
	
    private String cookieName;
    private String sheetName;
    private LoginPage lpage;
   
  
    @BeforeTest
    @Parameters({"sessionCookie","sheetName"})
    public void init(String cookieName, String sheetName)
    {
        this.cookieName = cookieName;
        this.sheetName = sheetName;
        logger.debug("Cookie name:Sheetname=" + cookieName + ":" + sheetName);
    }
    
    
    @BeforeClass
    public void setupPage() {
    	 lpage = new LoginPage(driver);
    	 logger.debug("In setupPage");
    }


    @DataProvider(name = "users-data")
    public Object[][] getUsers() throws Exception {
        Object[][] data = excelRead.getSimpleExcelData(driver.configuration.testDatafile, sheetName);
        return data;
    }

    @Test(priority = 1,dataProvider = "users-data")
    public void testLogin(String username, String password)
    {
        lpage.getLoginPage();
        boolean actual = lpage.performLogin(username, password);
        driver.takeScreenShot();
        assertTrue(actual);
    }
    
  
    @Test(dependsOnMethods = {"testLogin"})
    public void testLogout()
    {
    	driver.takeScreenShot();
        String preSessionId = lpage.getCookie(cookieName);
        String postSessionId = lpage.performLogout(cookieName);
        Assert.assertNotEquals(preSessionId, postSessionId);
        driver.takeScreenShot();
    }


}
