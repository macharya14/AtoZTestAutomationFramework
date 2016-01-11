package com.main.utils;

import java.util.Collection;


public interface Elements {

	    PageElement findElement(String locator);

	    Collection<PageElement> findElements(String locator);

	    String getDescription();
	}

