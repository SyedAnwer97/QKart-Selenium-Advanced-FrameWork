package com.qkart.pages;

import com.qkart.driver.DriverManager;
import com.qkart.enums.PropertyEnums;
import com.qkart.enums.SeleniumLocators;
import com.qkart.utils.seleniumutils.DynamicXpathUtils;
import com.qkart.utils.seleniumutils.LocatorUtils;
import com.qkart.utils.PropertyUtils;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public final class HomePageHeaderSection extends PageActions {

    private static final String DYNAMIC_USERNAME_TEXT = "//*[.='%s']";

    private static final By TEXTBOX_SEARCH_BAR = LocatorUtils.ByLocator(SeleniumLocators.NAME, "search");
    private static final By BUTTON_LOGIN = LocatorUtils.ByLocator(SeleniumLocators.XPATH, "//button[.='Login']");
    private static final By SNACKBAR_NOTIFICATION = LocatorUtils.ByLocator(SeleniumLocators.ID, "notistack-snackbar");

    @SneakyThrows
    public <T> T enterTextInTheSearchBar(String productName, Class<T> clazz) {
        clearAction(TEXTBOX_SEARCH_BAR);
        sendKeysAction(TEXTBOX_SEARCH_BAR, productName, "searchBar");
        return clazz.cast(clazz.getConstructor().newInstance());
    }

    @SneakyThrows
    public <T> T clickLoginButton(Class<T> clazz) {
        clickAction(BUTTON_LOGIN, "loginButton");
        return clazz.cast(clazz.getConstructor().newInstance());
    }

    @SneakyThrows
    public <T> T snackbarNotification(Class<T> clazz) {
        PageActions.elementIsDisplayed(SNACKBAR_NOTIFICATION, "snackBarNotification");
        return clazz.cast(clazz.getConstructor().newInstance());
    }

    public String snackbarNotificationGetText() {
        return DriverManager.getDriver().findElement(SNACKBAR_NOTIFICATION).getText();
    }

    @SneakyThrows
    public WebElement userNameText() {
        By username = LocatorUtils.ByLocator(SeleniumLocators.XPATH,
                DynamicXpathUtils.getXpath(DYNAMIC_USERNAME_TEXT, PropertyUtils.getProperty(PropertyEnums.USERNAME)));
        PageActions.elementIsDisplayed(username, "username");
        return DriverManager.getDriver().findElement(username);
    }


}

