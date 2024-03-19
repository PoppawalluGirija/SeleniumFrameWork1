package rahulShetty.TestComponents;



import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulShettyAcademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();

	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//this will not interept overrideing
	@Override
public void onTestStart(ITestResult result) {
	test=extent.createTest(result.getMethod().getMethodName());
   extentTest.set(test);//unique thread id
}

@Override
public void onTestSuccess(ITestResult result) {
	test.log(Status.PASS, "Test Passed");
}

@Override
public void onTestFailure(ITestResult result) {
	extentTest.get().fail( result.getThrowable());
	
	try {
		
	driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
	          .get(result.getInstance());
	}catch (Exception e1){
		e1.printStackTrace();
	}
	
	
	
	String filePath = null;
	try {
	filePath = getScreenshot(result.getMethod().getMethodName(),driver);
	}catch (Exception e) {
		e.printStackTrace();
	}
	test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	//Screenshot.Attach to report
}

public String getScreenshot(String methodName, WebDriver driver) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void onTestSkipped(ITestResult result) {
	
}

@Override
public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	
}

@Override
public void onStart(ITestContext context) {
	
}

@Override
public void onFinish(ITestContext context) {
	extent.flush();
	
}


}
