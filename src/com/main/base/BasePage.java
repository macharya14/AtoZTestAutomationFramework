package com.main.base;

import org.apache.log4j.Logger;

public abstract class BasePage {
	
	public Logger logger;
	
	public BasePage() {
		logger = Logger.getLogger(BasePage.class);
	}

}
