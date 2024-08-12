package com.qkart.pages;

import com.qkart.enums.SeleniumLocators;
import com.qkart.utils.seleniumutils.LocatorUtils;
import lombok.SneakyThrows;
import org.openqa.selenium.By;

public final class LoginPage extends PageActions {

    private static final By TEXT_BOX_USER_NAME = LocatorUtils.ByLocator(SeleniumLocators.ID, "username");
    private static final By TEXT_BOX_PASSWORD = LocatorUtils.ByLocator(SeleniumLocators.ID, "password");
    private static final By BUTTON_LOGIN = LocatorUtils.ByLocator(SeleniumLocators.XPATH, "//button[.='Login to QKart']");
    private static final By BUTTON_BACK_TO_EXPLORE = LocatorUtils.ByLocator(SeleniumLocators.XPATH, "//button[.='Back to explore']");


    public LoginPage enterUserName(String username) {
        sendKeysAction(TEXT_BOX_USER_NAME, username, "username");
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeysAction(TEXT_BOX_PASSWORD, password, "password");
        return this;
    }

    @SneakyThrows
    public <T> T clickLogin(Class<T> clazz) {
        clickAction(BUTTON_LOGIN, "loginButton");
        return clazz.cast(clazz.getConstructor().newInstance());
    }

    @SneakyThrows
    public <T> T clickBackToExplore(Class<T> clazz) {
        clickAction(BUTTON_BACK_TO_EXPLORE, "Back To Explore");
        return clazz.cast(clazz.getConstructor().newInstance());
    }

    @SneakyThrows
    public <T> T login(String username, String Password, Class<T> clazz) {
        return enterUserName(username).enterPassword(Password).clickLogin(clazz);
    }

}