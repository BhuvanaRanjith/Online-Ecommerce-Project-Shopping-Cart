package Org.Ecommerce.TestComponents;

import GlobalData.ExtentReportNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;

import org.testng.ITestResult;

import java.io.IOException;

public class Listener extends BaseClass implements ITestListener {
    ExtentTest test;
    ExtentReports extent= extent= ExtentReportNG.getReportObject();
    ThreadLocal<ExtentTest> extenttest=new ThreadLocal<ExtentTest>();//Thread Safe
    @Override
    public void onTestStart(ITestResult result)
    {

        test=extent.createTest(result.getMethod().getMethodName());
        extenttest.set(test);//Unique Thread Id

    }
    @Override
    public void onTestSuccess(ITestResult result)
    {
        test.log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extenttest.get().fail(result.getThrowable());
        try {
            driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e1) {
            e1.printStackTrace();

        }
        String filePath;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
