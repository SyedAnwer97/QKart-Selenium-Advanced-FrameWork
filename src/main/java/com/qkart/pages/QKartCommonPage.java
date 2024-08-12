package com.qkart.pages;

import com.qkart.driver.DriverManager;
import com.qkart.enums.SeleniumLocators;
import com.qkart.utils.seleniumutils.LocatorUtils;
import lombok.SneakyThrows;
import org.openqa.selenium.By;

public class QKartCommonPage extends PageActions {

    private static final By SNACKBAR_NOTIFICATION = LocatorUtils.ByLocator(SeleniumLocators.ID, "notistack-snackbar");

    @SneakyThrows
    public <T> T snackbarNotification(Class<T> clazz) {
        elementIsDisplayed(SNACKBAR_NOTIFICATION, "snackBarNotification");
        return clazz.cast(clazz.getConstructor().newInstance());
    }

    public String snackbarNotificationGetText() {
        return DriverManager.getDriver().findElement(SNACKBAR_NOTIFICATION).getText();
    }

    public String getAlertText() {
        return alertAction().getText();
    }

    @SneakyThrows
    public <T> T accecptAlert(Class<T> clazz) {
        alertAction().accept();
        return clazz.cast(clazz.getConstructor().newInstance());
    }

}
