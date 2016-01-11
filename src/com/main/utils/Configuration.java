package com.main.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;


public class Configuration {

 	public Browsers browser;
    public String device;
    public String baseUrl;
    public String baseURI;
    public int waitTimeout;
    private Logger logger;
    public String testDatafile;
    public boolean takeScreenShot;


    public Configuration(boolean isWebTest) {
        try {
            this.logger = Logger.getLogger(Configuration.class);

            Properties props = loadProperties(isWebTest);
            setCommonProps(props);
            if(isWebTest)
            {
                setWebProps(props);
            }
            else
            {
                setRestProps(props);
            }

        } catch (Exception e) {
            logger.error(e);
        }

    }
    

    private Properties loadProperties(boolean isWebTest)
    {
        Properties props = new Properties();
        try {
	        if(isWebTest) {
	            props.load(new FileReader(String.format("%s/resources/config.properties", System.getProperty("user.dir"))));
	        }
	        else{
	            props.load(new FileReader(String.format("%s/resources/restConfig.properties", System.getProperty("user.dir"))));
	        }
        } catch (IOException e) {
            logger.error(e);
        }
        return props;
    }
    

    public void setCommonProps(Properties cProps)
    {
        waitTimeout = Integer.parseInt(cProps.getProperty("wait-timeout"));
        testDatafile = cProps.getProperty("test-data-file");
    }
    
    
    public void setWebProps(Properties wProps)
    {
        String wbrowser = wProps.getProperty("browser");
       
        if(wbrowser.toLowerCase().equals("firefox"))
        {
            browser = Browsers.Firefox;
        }
        if(wbrowser.toLowerCase().equals("safari"))
        {
            browser = Browsers.Safari;
        }
        else if(wbrowser.toLowerCase().equals("chrome"))
        {
            browser = Browsers.Chrome;
        }
        else if(wbrowser.toLowerCase().equals("htmlunit"))
        {
            browser = Browsers.HtmlUnit;
        }
        else if(wbrowser.toLowerCase().equals("ie"))
        {
            browser = Browsers.InternetExplorer;
        }
        
        baseUrl = wProps.getProperty("url");
        device = wProps.getProperty("device");
        takeScreenShot = Boolean.valueOf(wProps.getProperty("takeScreenShot"));
    }

    
    public void setRestProps(Properties rProps)
    {
        baseURI = rProps.getProperty("uri");
    }

}


