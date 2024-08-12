package com.qkart;

import com.qkart.driver.Driver;
import com.qkart.driver.DriverManager;
import com.qkart.enums.PropertyEnums;
import com.qkart.utils.PropertyUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeMethod
    protected void setup(Method method) {
        Driver.initDriver();
        DriverManager.getDriver().get(PropertyUtils.getProperty(PropertyEnums.URL));
    }

    @AfterMethod
    protected void tearDown() {
        Driver.tearDown();
    }

}
