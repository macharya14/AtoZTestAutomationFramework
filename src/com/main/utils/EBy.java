package com.main.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;



public class EBy {

    private static HashMap<String, String> elocators;

    public static void loadJsonMap(String jsonLocatorPath) {
        if (elocators != null)
            return;
        elocators = new HashMap<String, String>();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(jsonLocatorPath));
            JSONObject jsonObject = (JSONObject) obj;
            for (Object key : jsonObject.keySet()) {
                elocators.put((String) key, (String) jsonObject.get(key));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static String getValue(String key) {
        try {
            return elocators.get(key);
        } catch (Exception ex) {

        }
        return "";
    }

    
    public static By get(String locatorkey) throws Exception {
   
        try {
        		String value = getValue(locatorkey);
        		if (value.contains("=")) {
                    String[] values = value.split("=");
        		
                if (values[0].toLowerCase().equals("css")) {
                    return By.cssSelector(values[1]);
                }
                if (values[0].toLowerCase().equals("xpath")) {
                    return By.xpath(values[1]);
                }
                if (values[0].toLowerCase().equals("id")) {
                    return By.id(values[1]);
                }
                if (values[0].toLowerCase().equals("name")) {
                    return By.name(values[1]);
                }
                if (values[0].toLowerCase().equals("class")) {
                    return By.className(values[1]);
                }
                if (values[0].toLowerCase().equals("link")) {
                    return By.linkText(values[1]);
                }
                if (values[0].toLowerCase().equals("plink")) {
                    return By.partialLinkText(values[1]);
                }
                if (values[0].toLowerCase().equals("tag")) {
                    return By.tagName(values[1]);
                }
                return By.cssSelector(values[1]);
            }
        	return(By.cssSelector(value));	
        } catch (Exception e) {
            throw e;
        }
    }
}