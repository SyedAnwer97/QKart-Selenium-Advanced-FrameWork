package com.qkart;

import com.qkart.annotation.FrameworkAnnotation;
import com.qkart.constants.FrameworkConstants;
import com.qkart.entity.TestData;
import com.qkart.enums.Category;
import com.qkart.enums.PropertyEnums;
import com.qkart.pages.HomePageHeaderSection;
import com.qkart.pages.LoginPage;
import com.qkart.pages.QKartCommonPage;
import com.qkart.utils.PropertyUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public final class LoginTest extends BaseTest {

    private LoginTest() {
    }

    @FrameworkAnnotation(category = {Category.SMOKE}, author = "Syed")
    @Test
    private void validLoginTest(TestData testData) {
        String loggedInUserName = new HomePageHeaderSection()
                .clickLoginButton(LoginPage.class)
                .login(testData.getUsername(), testData.getPassword(), QKartCommonPage.class)
                .snackbarNotification(HomePageHeaderSection.class)
                .userNameText()
                .getText();
        Assertions.assertThat(loggedInUserName).isEqualToIgnoringCase(PropertyUtils.getProperty(PropertyEnums.USERNAME));
    }

    @FrameworkAnnotation(category = {Category.REGRESSION}, author = "Anwer")
    @Test
    private void invalidUsernameLoginTest(TestData testData) {
        String warningNotification = new HomePageHeaderSection()
                .clickLoginButton(LoginPage.class)
                .login(testData.getInvalidusername(), testData.getPassword(), QKartCommonPage.class)
                .snackbarNotification(QKartCommonPage.class)
                .snackbarNotificationGetText();
        Assertions.assertThat(warningNotification).isEqualToIgnoringCase(FrameworkConstants.getUSERNAME_WARNING());
    }

    @FrameworkAnnotation(category = {Category.MINIREGRESSION}, author = "Syed")
    @Test
    private void invalidPasswordLoginTest(TestData testData) {
        String warningNotification = new HomePageHeaderSection()
                .clickLoginButton(LoginPage.class)
                .login(testData.getUsername(), testData.getInvalidpassword(), QKartCommonPage.class)
                .snackbarNotification(QKartCommonPage.class)
                .snackbarNotificationGetText();
        Assertions.assertThat(warningNotification).isEqualToIgnoringCase(FrameworkConstants.getPASSWORD_WARNING());
    }

    @FrameworkAnnotation(category = {Category.SMOKE, Category.REGRESSION}, author = "Anwer")
    @Test
    private void emptyValueInLoginTextBoxes(TestData testData) {
        String errorNotificationOnBothTextBoxAreEmpty = new HomePageHeaderSection()
                .clickLoginButton(LoginPage.class)
                .clickLogin(QKartCommonPage.class)
                .snackbarNotification(QKartCommonPage.class)
                .snackbarNotificationGetText();

        Assertions.assertThat(errorNotificationOnBothTextBoxAreEmpty)
                .containsIgnoringCase(FrameworkConstants.getEMPTY_FIELD_WARING());

        String errorNotificationOnPasswordTextBoxIsEmpty = new LoginPage()
                .enterUserName(testData.getUsername())
                .clickLogin(QKartCommonPage.class)
                .snackbarNotification(QKartCommonPage.class)
                .snackbarNotificationGetText();

        Assertions.assertThat(errorNotificationOnPasswordTextBoxIsEmpty)
                .containsIgnoringCase(FrameworkConstants.getEMPTY_FIELD_WARING());

        String errorNotificationOnUsernameTextBoxAreEmpty = new LoginPage()
                .enterPassword(testData.getPassword())
                .clickLogin(QKartCommonPage.class)
                .snackbarNotification(QKartCommonPage.class)
                .snackbarNotificationGetText();

        Assertions.assertThat(errorNotificationOnUsernameTextBoxAreEmpty)
                .containsIgnoringCase(FrameworkConstants.getEMPTY_FIELD_WARING());
    }

    @FrameworkAnnotation(category = Category.MINIREGRESSION, author = "Syed")
    @Test
    private void backToExploreTest(TestData testData) {
        new HomePageHeaderSection()
                .clickLoginButton(LoginPage.class)
                .clickBackToExplore(HomePageHeaderSection.class)
                .clickLoginButton(LoginPage.class);
    }

}