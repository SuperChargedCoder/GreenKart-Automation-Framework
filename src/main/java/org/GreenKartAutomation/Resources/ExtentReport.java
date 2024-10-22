package org.GreenKartAutomation.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "\\TestNG_Reports\\TestReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("GreenKart Testing Report");
		reporter.config().setDocumentTitle("GreenKar Test Results");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA Engineer", "Shubham Chaurasia");
		return extent;
	}
}
