package com.qkart.utils.seleniumutils;

import com.qkart.enums.SeleniumLocators;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class LocatorUtils {

    private LocatorUtils() {
    }

    private static final Map<SeleniumLocators, Function<String, By>> map = new HashMap<>();

    private static final Function<String, By> ID = By::id;
    private static final Function<String, By> NAME = By::name;
    private static final Function<String, By> CLASSNAME = By::className;
    private static final Function<String, By> TAGNAME = By::tagName;
    private static final Function<String, By> LINKTEXT = By::linkText;
    private static final Function<String, By> PARTIALLINKTEXT = By::partialLinkText;
    private static final Function<String, By> XPATH = By::xpath;
    private static final Function<String, By> CSSSELECTOR = By::cssSelector;

    static {
        map.put(SeleniumLocators.ID, ID);
        map.put(SeleniumLocators.NAME, NAME);
        map.put(SeleniumLocators.CLASSNAME, CLASSNAME);
        map.put(SeleniumLocators.TAGNAME, TAGNAME);
        map.put(SeleniumLocators.LINKTEXT, LINKTEXT);
        map.put(SeleniumLocators.PARTIALLINKTEXT, PARTIALLINKTEXT);
        map.put(SeleniumLocators.XPATH, XPATH);
        map.put(SeleniumLocators.CSSSELECTOR, CSSSELECTOR);
    }

    public static By ByLocator(SeleniumLocators locators, String value) {
        return map.get(locators).apply(value);
    }

}
