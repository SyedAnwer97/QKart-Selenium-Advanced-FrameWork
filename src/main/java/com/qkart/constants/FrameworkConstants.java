package com.qkart.constants;

import lombok.Getter;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    private @Getter
    static final String PROPERTY_FILE_LOCATION = "propertyfiles/config.property";
    private @Getter
    static final String EXCEL_FILE_LOCATION = "excelfiles/testdata.xlsx";
    private @Getter
    static final String EXCEL_SHEET_NAME = "testdata";
    private @Getter
    static final String USERNAME_WARNING = "Username does not exist";
    private @Getter
    static final String PASSWORD_WARNING = "Password is incorrect";
    private @Getter
    static final String EMPTY_FIELD_WARING = "is a required field";
    private @Getter
    static final String LOGIN_WARING = "Login to add an item to the Cart";
    private @Getter
    static final String OUT_OF_STOCK_WARING = "The given product is out of stock";

}