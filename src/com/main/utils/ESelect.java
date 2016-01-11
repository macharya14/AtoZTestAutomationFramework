package com.main.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class ESelect {
	private Logger logger;
    private Select sel;

    public ESelect() {
        logger = Logger.getLogger(ESelect.class);
    }
    
    
    public ESelect(WebElement element) {

        try {
            sel = new Select(element);
        }
        catch (Exception e) {
            logger.error(e);
        }
    }


    //Select option at the given index. 
    public void byIndex(int index) {          
    	sel.selectByIndex(index);    }

    //Select all options that display text matching the argument.
    public void byVisibleText(String text)    {
        sel.selectByVisibleText(text);
    }


    //The first selected option
    public  WebElement firstSelectedOption() {  
    	return sel.getFirstSelectedOption();    
    }

    //All selected options belonging to this select tag
    public List<WebElement> allSelectedOptions() {
    	return sel.getAllSelectedOptions();    
    }

    //All options belonging to this select tag
    public List<WebElement> getallOptions() {
    	return sel.getOptions();    
    }

    //Check the value of "multiple" attribute to 
    //see if element support selecting multiple options at the same time
    public boolean isMultiple()  {
    	return   sel.isMultiple();
    }

    //Select all options that have a value matching the argument. 
    public void byValue(String value) {
    	sel.selectByValue(value);    
    }

    //Clear all selected entries, when the SELECT supports multiple selections.
    public void allDeselect()  {
       sel.deselectAll();    
    }

    //Deselect all options that have a value matching the argument. 
    public void byValueDeselect(String value) {
    	sel.deselectByValue(value);  
    }
    
    //Deselect the option at the given index. 
    public void byIndexDeselect(int index) {
    	sel.deselectByIndex(index);  
    }

    //Deselect all options that display text matching the argument. 
    public void byVisibleTextDeselect(String text) {
    	sel.deselectByVisibleText(text);
    }
    

}
