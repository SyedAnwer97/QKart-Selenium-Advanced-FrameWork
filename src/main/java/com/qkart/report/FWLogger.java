package com.qkart.report;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.qkart.enums.LogStatus;
import com.qkart.report.extentreport.ExtentManager;
import com.qkart.report.log4j.Log4j;
import com.qkart.utils.seleniumutils.ScreenShotUtils;

import java.util.EnumMap;
import java.util.function.Consumer;

public final class FWLogger {

    private FWLogger() {
    }

    private static final EnumMap<LogStatus, Consumer<String>> map = new EnumMap<>(LogStatus.class);
    private static final EnumMap<LogStatus, Consumer<Throwable>> throwableMap = new EnumMap<>(LogStatus.class);

    private static final Consumer<String> info = (message) -> Log4j.getLogger().info(message);
    private static final Consumer<String> error = (message) -> Log4j.getLogger().error(message);
    private static final Consumer<String> startTestCase = Log4j::startTestCase;
    private static final Consumer<String> endTestCase = Log4j::endTestCase;
    private static final Consumer<String> testInfo = (message) -> Log4j.getTestLogger().info(message);
    private static final Consumer<String> testError = (message) -> Log4j.getTestLogger().error(message);

    private static final Consumer<String> PASS = (message) -> ExtentManager.getTest().pass(message);
    private static final Consumer<String> FAIL = (message) -> ExtentManager.getTest().fail(message);
    private static final Consumer<String> INFO = (message) -> ExtentManager.getTest().info(message);
    private static final Consumer<String> SKIP = (message) -> ExtentManager.getTest().skip(message);
    private static final Consumer<Throwable> THROWABLE = (e) -> ExtentManager.getTest().fail(e);
    private static final Consumer<String> FAILSCREENSHOT = (message) -> ExtentManager.getTest().fail(message,
            MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenShotUtils.getScreenShotAsBase64String()).build());

    static {
        map.put(LogStatus.EXTENTPASS, PASS);
        map.put(LogStatus.EXTENTFAIL, FAIL);
        map.put(LogStatus.EXTENTSKIP, SKIP);
        map.put(LogStatus.EXTENTINFO, INFO);
        map.put(LogStatus.EXTENTSCREENSHOT, FAIL.andThen(FAILSCREENSHOT));
        throwableMap.put(LogStatus.EXTENTTHOWABLE, THROWABLE);

        map.put(LogStatus.LOG4JINFO, info);
        map.put(LogStatus.LOG4JERROR, error);
        map.put(LogStatus.LOG4JSTARTTEST, startTestCase);
        map.put(LogStatus.LOG4JENDTEST, endTestCase);
        map.put(LogStatus.LOG4JTESTINFO, testInfo);
        map.put(LogStatus.LOG4JTESTERROR, testError);

        map.put(LogStatus.EXRENTPASSLOG4JINFO, PASS.andThen(testInfo));
        map.put(LogStatus.EXRENTFAILLOG4JERROR, FAILSCREENSHOT.andThen(testError));

    }

    public static void log(LogStatus logStatus, String logMessage) {
        map.get(logStatus).accept(logMessage);
    }

    public static void log(LogStatus logStatus, Throwable throwable) {
        throwableMap.get(logStatus).accept(throwable);
    }
}
