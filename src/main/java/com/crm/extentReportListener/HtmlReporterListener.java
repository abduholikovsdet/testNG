package com.crm.extentReportListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.crm.basepage.TestBase;
import com.crm.util.ExtentReportGenerator;
import com.crm.util.TestUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class HtmlReporterListener extends TestBase implements ITestListener {

    ExtentReports report = ExtentReportGenerator.getExtentReport();
    ExtentTest eTest;
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        eTest = report.createTest(testName);
        eTest.log(Status.INFO, testName+ " execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        eTest.log(Status.PASS, testName+ " execution Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            String testName = result.getName();
            eTest.log(Status.FAIL, testName+ " execution Failed");
//            WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
            eTest.addScreenCaptureFromPath(TestUtil.takeScreenshot(driver), testName);
            eTest.log(Status.INFO, result.getThrowable());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        eTest.log(Status.INFO, testName+ " execution Skipped");
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();

    }
}
