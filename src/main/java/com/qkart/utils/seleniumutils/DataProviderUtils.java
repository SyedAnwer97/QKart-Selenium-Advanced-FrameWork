package com.qkart.utils.seleniumutils;

import com.qkart.constants.FrameworkConstants;
import com.qkart.datasupplier.TestDataSupplier;
import com.qkart.entity.TestData;
import com.qkart.utils.ExcelReaderUtils;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class DataProviderUtils {

    @DataProvider(parallel = true)
    public Object[] getExcelData(Method method) {
        String testName = method.getName().toLowerCase();
        List<TestData> testDataList;
        List<Map<String, String>> excelTestDataList = ExcelReaderUtils.readExcel(FrameworkConstants.getEXCEL_SHEET_NAME());
        excelTestDataList.removeIf(map -> !map.get("testname").equalsIgnoreCase(testName));
        excelTestDataList.removeIf(map -> map.get("execute").equalsIgnoreCase("no"));
        testDataList = excelTestDataList.stream().map(TestDataSupplier::getTestData).collect(Collectors.toList());
        return testDataList.toArray();
    }

}