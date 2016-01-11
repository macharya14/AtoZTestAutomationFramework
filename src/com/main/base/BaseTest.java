package com.main.base;



import org.apache.log4j.Logger;

import com.main.utils.Configuration;


public abstract class BaseTest {
	
	 public static final Configuration config;

	    static {
	        config = new Configuration(true);
	    }

	    public Logger logger;

	    public BaseTest() {
	        logger = Logger.getLogger(BaseTest.class);
	    }

}
