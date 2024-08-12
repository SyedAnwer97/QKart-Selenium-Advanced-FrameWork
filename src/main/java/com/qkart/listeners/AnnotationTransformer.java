package com.qkart.listeners;

import com.qkart.utils.seleniumutils.DataProviderUtils;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class AnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryFailedTest.class);
        annotation.setDataProviderClass(DataProviderUtils.class);
        annotation.setDataProvider("getExcelData");
    }

}
