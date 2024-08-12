package com.qkart.report.log4j;

import com.qkart.enums.LogStatus;
import com.qkart.report.FWLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class Log4j {

    private static final Logger LOGGER = LogManager.getLogger();

    private static Logger getCurrentLogger() {
        return LOGGER;
    }

    private static void startLog(String testCaseName) {
        ThreadContext.put("logFilename", testCaseName);
    }

    public synchronized static void startTestCase(String sTestCaseName) {
        sTestCaseName = sTestCaseName.replaceAll("[^a-zA-Z0-9]", "_").replaceAll("_+", "_");
        startLog(sTestCaseName);
        FWLogger.log(LogStatus.LOG4JTESTINFO, "\n\n************** Execution Started : " + sTestCaseName + "  **************\n");
    }

    public synchronized static void endTestCase(String sTestCaseName) {
        sTestCaseName = sTestCaseName.replaceAll("[^a-zA-Z0-9]", "_").replaceAll("_+", "_");
        FWLogger.log(LogStatus.LOG4JTESTINFO, "\n\n************** Execution End : " + sTestCaseName + "  **************\n");
    }

    public static Logger getLogger() {
        String logFileName = "log4j";
        ThreadContext.put("logFilename", logFileName);
        return LOGGER;

    }

    public static Logger getTestLogger() {
        return getCurrentLogger();
    }

}
