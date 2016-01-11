package com.main.utils;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandler {
	
	private static String mainWindowsHandle; // Stores current window handle
	
	
	public static boolean switchToWindow(WebDriver driver, String title){
		
	mainWindowsHandle = driver.getWindowHandle();
	// Get all the available windows
	Set<String> handles = driver.getWindowHandles(); 
	  for(String handle : handles)
	  {
		// switch back to each window in loop
	    driver.switchTo().window(handle); 
	    if(driver.getTitle().equals(title)) 
	     return true; 
	  }
	  // Switch back to original window handle
	  driver.switchTo().window(mainWindowsHandle); 
	  // Failed to find window with given title, return false
	  return false; 
	 }
	
	
	public void switchToWindow(WebDriver driver)
    {
        String newWindow = driver.getWindowHandle();
        driver.switchTo().window(newWindow);
    }


    public void windowHandles(WebDriver driver)
    {
        Iterator<String> handles = driver.getWindowHandles().iterator();
        while(handles.hasNext()){
            String child = handles.next();
            driver.switchTo().window(child);
        }
    }
}
