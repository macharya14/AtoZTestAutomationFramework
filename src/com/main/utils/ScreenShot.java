package com.main.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class ScreenShot {
	
	TakesScreenshot takesScreenshot;

	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	
	 public ScreenShot(TakesScreenshot takesScreenshot) {
        this.takesScreenshot = takesScreenshot;
    }
 
	
	public String getFormattedDate() {
        return format.format(new Date()).toString();
    }
	
    
    public void takeScreenShot(boolean takeScreenshot) {
    	if(takeScreenshot) {
	    	try {
		        String date = getFormattedDate();
		        String userdir = System.getProperty("user.dir");
		        File file1 = new File(userdir+"/screenshots");
		        String projectPath = file1.getAbsolutePath();
		        if(!file1.exists())
		        	file1.mkdir();
		        String newDir = projectPath+"/"+date;
		        File file = new File(newDir);
		        if(!file.exists())
		            file.mkdir();
		        
		        String latestFilePath = file.getPath();
		        File[] files =  new File(latestFilePath).listFiles();
		        int count = (files.length) + 1;
		        File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		        String path = newDir+"/Screen"+count+".png";
		        FileUtils.copyFile(scrFile, new File(path));
	    	}
	    	catch( IOException io) {
	    		io.printStackTrace();
	    	}
	    }
    }
}
