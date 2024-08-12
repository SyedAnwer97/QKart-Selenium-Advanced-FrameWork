package com.qkart;

import com.qkart.annotation.FrameworkAnnotation;
import com.qkart.constants.FrameworkConstants;
import com.qkart.entity.TestData;
import com.qkart.enums.Category;
import com.qkart.pages.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    private AddToCartTest(){}

    @FrameworkAnnotation(category = {Category.SMOKE, Category.REGRESSION}, author = "Syed")
    @Test()
    private void addToCartWithoutLoginTest(TestData testData) {
        String notificationText = new HomePageHeaderSection()
                .enterTextInTheSearchBar(testData.getProduct1(), HomePageProductSection.class)
                .clickAddToCartButton(testData.getProduct1(), HomePageHeaderSection.class)
                .snackbarNotification(HomePageHeaderSection.class).snackbarNotificationGetText();

        Assertions.assertThat(notificationText)
                .isEqualToIgnoringCase(FrameworkConstants.getLOGIN_WARING());
    }

    @FrameworkAnnotation(category = {Category.SMOKE, Category.SANITY}, author = "Anwer")
    @Test
    private void addToCartAfterLoginTest(TestData testData) {
        String productNameInCart = new HomePageHeaderSection()
                .clickLoginButton(LoginPage.class)
                .login(testData.getUsername(), testData.getPassword(), HomePageHeaderSection.class)
                .enterTextInTheSearchBar(testData.getProduct1(), HomePageProductSection.class)
                .clickAddToCartButton(testData.getProduct1(), ProductCartPage.class)
                .productAddedInCart(testData.getProduct1(), ProductCartPage.class)
                .checkProductInCart()
                .getText();

        new HomePageHeaderSection()
                .enterTextInTheSearchBar(testData.getProduct2(), HomePageProductSection.class)
                .clickAddToCartButton(testData.getProduct2(), ProductCartPage.class)
                .productAddedInCart(testData.getProduct2(), ProductCartPage.class)
                .checkProductInCart();

        new HomePageHeaderSection()
                .enterTextInTheSearchBar(testData.getProduct3(), HomePageProductSection.class)
                .clickAddToCartButton(testData.getProduct3(), ProductCartPage.class)
                .productAddedInCart(testData.getProduct3(), ProductCartPage.class)
                .checkProductInCart();

        new ProductCartPage().dataCleanUp();

        Assertions.assertThat(productNameInCart).containsIgnoringCase(testData.getProduct1());
    }

    @FrameworkAnnotation(category = Category.SANITY, author = "Anwer")
    @Test(dependsOnMethods = "addToCartAfterLoginTest")
    private void selectSizeForTheProductTest(TestData testData) {
        String cartProductName = new HomePageHeaderSection()
                .clickLoginButton(LoginPage.class)
                .login(testData.getUsername(), testData.getPassword(), HomePageHeaderSection.class)
                .enterTextInTheSearchBar(testData.getProduct1(), HomePageProductSection.class)
                .selectSizeForTheProduct(testData.getProduct1(), 8, HomePageProductSection.class)
                .clickAddToCartButton(testData.getProduct1(), ProductCartPage.class)
                .productAddedInCart(testData.getProduct1(), ProductCartPage.class)
                .checkProductInCart()
                .getText();

        new ProductCartPage().dataCleanUp();
        Assertions.assertThat(cartProductName).containsIgnoringCase(testData.getProduct1());
    }

    @FrameworkAnnotation(category = Category.REGRESSION, author = "Syed")
    @Test
    private void addOutOfStocksTcCartTest(TestData testData) {
        String alertText = new HomePageHeaderSection()
                .enterTextInTheSearchBar(testData.getProduct1(), HomePageProductSection.class)
                .clickAddToCartButton(testData.getProduct1(), QKartCommonPage.class)
                .getAlertText();

        new QKartCommonPage().accecptAlert(QKartCommonPage.class);
        Assertions.assertThat(alertText).containsIgnoringCase(FrameworkConstants.getOUT_OF_STOCK_WARING());
    }

}
