package com.qkart.utils;

import com.qkart.constants.FrameworkConstants;
import com.qkart.enums.LogStatus;
import com.qkart.enums.PropertyEnums;
import com.qkart.exception.PropertyValueNotFoundException;
import com.qkart.report.FWLogger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class PropertyUtils {

    private PropertyUtils() {
    }

    private static final Properties properties = new Properties();
    private static final Map<String, String> map = new HashMap<>();

    static {
        try {
            properties.load(ResourceFinderUtils.getResource(FrameworkConstants.getPROPERTY_FILE_LOCATION()));
            properties.stringPropertyNames().forEach(key -> {
                if (System.getProperties().containsKey(key)) properties.setProperty(key, System.getProperty(key));
            });
            properties.forEach((key, value) -> map.put(key.toString(), value.toString()));
            displayPropertyInConsole();
        } catch (Exception e) {
            System.exit(0);
        }
    }

    public static String getProperty(PropertyEnums key) {
        if (Objects.isNull(key) && Objects.isNull(map.get(key.name()))) {
            throw new PropertyValueNotFoundException("Please check the property file. The give key is not found in the propertyFile");
        }
        return map.get(key.name().toLowerCase());
    }

    private static void displayPropertyInConsole() {
        FWLogger.log(LogStatus.LOG4JINFO, "reading the property file from the location " + FrameworkConstants.getPROPERTY_FILE_LOCATION());
        FWLogger.log(LogStatus.LOG4JINFO, "******* Properties *******");
        map.forEach((key, value) -> FWLogger.log(LogStatus.LOG4JINFO, key + " : " + value));
        FWLogger.log(LogStatus.LOG4JINFO, "******* Properties *******");
    }

}
