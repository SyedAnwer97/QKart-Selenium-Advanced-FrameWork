package com.qkart.datasupplier;

import com.qkart.entity.TestData;
import com.qkart.enums.ExcelHeader;

import java.util.Map;

public final class TestDataSupplier {

    private TestDataSupplier() {
    }

    public static TestData getTestData(Map<String, String> testData) {
        return TestData.builder()
                .setUsername(testData.getOrDefault(ExcelHeader.USERNAME.name().toLowerCase(), ""))
                .setExecute(testData.getOrDefault(ExcelHeader.EXECUTE.name().toLowerCase(), ""))
                .setPassword(testData.getOrDefault(ExcelHeader.PASSWORD.name().toLowerCase(), ""))
                .setInvalidusername(testData.getOrDefault(ExcelHeader.INVALIDUSERNAME.name().toLowerCase(), ""))
                .setInvalidpassword(testData.getOrDefault(ExcelHeader.INVALIDPASSWORD.name().toLowerCase(), ""))
                .setProduct1(testData.getOrDefault(ExcelHeader.PRODUCT1.name().toLowerCase(), ""))
                .setProduct2(testData.getOrDefault(ExcelHeader.PRODUCT2.name().toLowerCase(), ""))
                .setProduct3(testData.getOrDefault(ExcelHeader.PRODUCT3.name().toLowerCase(), ""))
                .build();
    }

}