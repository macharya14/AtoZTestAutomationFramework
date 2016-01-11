package com.main.tests;


import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.main.pages.AssignmentsPage;

public class AssignmentsPageTest extends BasePageTest{

	    private AssignmentsPage apage;
	    private String sheetName;

	    @BeforeTest
	    @Parameters("sheetName")
	    public void init(String sheetName)
	    {
	        apage = new AssignmentsPage(driver);
	        this.sheetName = sheetName;
	    }

	    @DataProvider(name = "assign-data")
	    public Object[][] getUsers() throws Exception {
	        Object[][] data = excelRead.getSimpleExcelData(driver.configuration.testDatafile, sheetName);
	        return data;
	    }

	    @Test(priority = 1,dataProvider = "assign-data")
	    public void testAssignments(String userName, String passWord, String assignType)
	    {
	    	if(apage.getLoginStatus(userName, passWord)) {
		        int actual = apage.getAssignments(assignType);
		        Assert.assertEquals(actual, 4);
		        driver.takeScreenShot();
	    	}
	    }
	    

	    @AfterTest
	    public void close()
	    {
	        driver.close();
	    }

}
