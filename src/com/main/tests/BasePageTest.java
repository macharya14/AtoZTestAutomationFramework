package com.main.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.main.base.BaseTest;
import com.main.utils.EBy;
import com.main.utils.ExcelRead;
import com.main.utils.PageDriver;
import com.main.utils.ScreenShot;

public class BasePageTest extends BaseTest{

	public static PageDriver driver;
	public ScreenShot screenshots;
	public ExcelRead excelRead;
	
	
	@BeforeSuite
	public void initTest() {
		driver = new PageDriver(config);
		excelRead = new ExcelRead();
		String jsonLocatorPath = String.format("%s/resources/locators.json", System.getProperty("user.dir"));
		EBy.loadJsonMap(jsonLocatorPath);
	}
	
	
	@AfterSuite
	public void quitTest() {
		driver.quit();
		driver = null;
	}

}
