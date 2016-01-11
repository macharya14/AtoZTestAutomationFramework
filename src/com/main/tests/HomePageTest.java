package com.main.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.main.pages.HomePage;


public class HomePageTest extends BasePageTest{
	
	private HomePage hpage;
	
	@BeforeClass
	public void setupPage() {
		hpage = new HomePage(driver);
	}
	
	
	
	@Test(priority = 1)
	public void checkTitle()
	{
		driver.takeScreenShot();
		Assert.assertEquals(hpage.getTitle(), "QA/QE/SDET Training.");
	}
	
	
	
	@Test(priority = 2)
	public void checkLogo() 
	{
		driver.takeScreenShot();
		Assert.assertEquals(hpage.isLogoDisplayed(), true);
	}
	
	
	@Test(priority = 3)
	public void checkSliderImageCount()
	{
		driver.takeScreenShot();
		Assert.assertEquals(hpage.getSliderImageCount(), 8);
		driver.takeScreenShot();
	}
	
	
	@Test(priority = 4)
	public void checkNavBar()
	{
		Assert.assertEquals(hpage.getNavBarItemsCount(), 7);
	}
	
	
	@Test(priority = 5)
	public void checkInfoPanel()
	{
		Assert.assertEquals(hpage.getContactNumber(), "925-400-7330");
	}
	
	
	@Test(priority = 6)
	public void checkNavBarResources()
	{
		Assert.assertEquals(hpage.getNavBarResourcesCount(), 5);
	}
	
	
	@Test(priority = 7)
	public void checkHeader() 
	{
		Assert.assertEquals(hpage.getHeaderIconCount(), 4);
		Assert.assertEquals(hpage.clickHeaderIconFB(), true);
	}
	
	
	@Test(priority = 8)
	public void checkFooter() 
	{
		Assert.assertEquals(hpage.getFooterIconCount(), 4);
	}

}
