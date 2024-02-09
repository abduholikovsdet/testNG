package com.crm.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReportGenerator {

    public static ExtentReports getExtentReport(){
        ExtentReports report = new ExtentReports();
        File extentReportFile = new File(System.getProperty("user.dir")+ "/target/html-report/report.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        report.attachReporter(sparkReporter);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("CRM Test Automation");
        sparkReporter.config().setDocumentTitle("CRM Test Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
        return report;
    }


}
