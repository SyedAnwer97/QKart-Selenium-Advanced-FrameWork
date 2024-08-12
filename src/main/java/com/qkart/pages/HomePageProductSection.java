package com.qkart.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import com.qkart.enums.SeleniumLocators;
import com.qkart.utils.seleniumutils.DynamicXpathUtils;
import com.qkart.utils.seleniumutils.LocatorUtils;
import lombok.SneakyThrows;
import org.openqa.selenium.By;

import java.time.Duration;

public final class HomePageProductSection extends PageActions {

    private static final String PRODUCT_ADD_TO_CART = "//*[*='%s']/..//button[.='Add to cart']";
    private static final String PRODUCT_SIZE = "//*[*='%s']/../..//select";

    @SneakyThrows
    public <T> T clickAddToCartButton(String productName, Class<T> clazz) {
        By buttonAddToCart = LocatorUtils.ByLocator(SeleniumLocators.XPATH,
                DynamicXpathUtils.getXpath(PRODUCT_ADD_TO_CART, productName));
        clickAction(buttonAddToCart, productName + " : add to cart button");
        return clazz.cast(clazz.getConstructor().newInstance());
    }

    @SneakyThrows
    public <T> T selectSizeForTheProduct(String productName, int size, Class<T> clazz) {
        selectAction(LocatorUtils.ByLocator(SeleniumLocators.XPATH,
                        DynamicXpathUtils.getXpath(PRODUCT_SIZE, productName)),
                select -> select.selectByVisibleText(String.valueOf(size)),"size");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(2));
        return clazz.cast(clazz.getConstructor().newInstance());
    }

}
