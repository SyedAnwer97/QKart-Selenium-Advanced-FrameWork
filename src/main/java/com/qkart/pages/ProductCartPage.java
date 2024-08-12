package com.qkart.pages;

import com.qkart.driver.DriverManager;
import com.qkart.enums.SeleniumLocators;
import com.qkart.factory.WaitFactory;
import com.qkart.utils.seleniumutils.DynamicXpathUtils;
import com.qkart.utils.seleniumutils.LocatorUtils;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public final class ProductCartPage extends PageActions {

    private static final String PRODUCT_LIST = "//*[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-3 css-1q5pok1']//*[@class='MuiBox-root css-0']";
    private static final String REMOVE_PRODUCT_ICON = "//*[local-name()='svg' and @data-testid='RemoveOutlinedIcon']//*[local-name()='path']";
    private static final String CART_PRODUCT = "//div[normalize-space()='%s']";

    private static final By PRODUCT_LIST_IN_CART = LocatorUtils.ByLocator(SeleniumLocators.XPATH, PRODUCT_LIST);
    private static final By ICON_REMOVE_PRODUCT_CART = LocatorUtils.ByLocator(SeleniumLocators.XPATH, REMOVE_PRODUCT_ICON);

    @SneakyThrows
    public <T> T productAddedInCart(String productName, Class<T> clazz) {
        WaitFactory.waitStrategy(wait -> wait.until(ExpectedConditions
                .visibilityOfElementLocated(LocatorUtils
                        .ByLocator(SeleniumLocators.XPATH, DynamicXpathUtils.getXpath(CART_PRODUCT, productName)))));
        return clazz.cast(clazz.getConstructor().newInstance());
    }

    public WebElement checkProductInCart() {
        List<WebElement> productCartList = DriverManager.getDriver().findElements(PRODUCT_LIST_IN_CART);
        int size = productCartList.size();
        return DriverManager.getDriver().findElement(LocatorUtils
                .ByLocator(SeleniumLocators.XPATH, PRODUCT_LIST + "[" + size + "]"));
    }

    public void dataCleanUp() {
        List<WebElement> elements = DriverManager.getDriver().findElements(ICON_REMOVE_PRODUCT_CART);
        for (int i = elements.size(); i > 0; i--) {
            clickAction(LocatorUtils
                    .ByLocator(SeleniumLocators.XPATH, "(" + REMOVE_PRODUCT_ICON + ")[" + i + "]"), "product remove icon");
            int j = i;
            WaitFactory.waitStrategy(a -> a.until(ExpectedConditions
                    .invisibilityOfElementLocated(LocatorUtils.ByLocator(SeleniumLocators.XPATH,
                            PRODUCT_LIST + "[" + j + "]"))));
        }
    }

}