package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter ; // UI of the report 
	public ExtentReports extent ;    // populate common data on the report 
	public ExtentTest test; // creating entries in the report and updating status of the testcases.
	String repName;
	
	public void onStart(ITestContext testContext) {
		
	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	repName = "Test-Report-"+timeStamp+".html";
	sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\"+repName);
	
	sparkReporter.config().setDocumentTitle("Opencart Automation Report");
    sparkReporter.config().setReportName("functional Testing");
    sparkReporter.config().setTheme(Theme.DARK);
    extent = new ExtentReports();
    extent.attachReporter(sparkReporter);
    
    extent.setSystemInfo("Application", "Opencart");
    extent.setSystemInfo("Module", "Admin");
    extent.setSystemInfo("Sub-module", "Customer");
    extent.setSystemInfo("User Name", System.getProperty("user.name"));
    extent.setSystemInfo("Environment", "QA");
    
    String os = testContext.getCurrentXmlTest().getParameter("os");
    extent.setSystemInfo("Operating System", os);

    String browser = testContext.getCurrentXmlTest().getParameter("browser");
    extent.setSystemInfo("Browser", browser);

	
	}
	
	  public void onTestSuccess(ITestResult result) {
		  test = extent.createTest(result.getName()); // create a new entry in the report 
		  test.log(Status.PASS,"Test Case Passed is "+result.getName()); // update the status of the tc
	  
	  }

	  public void onTestFailure(ITestResult result) {
		  test = extent.createTest(result.getName()); // create a new entry in the report 
		  test.log(Status.FAIL,"Test Case failed is "+result.getName()); // update the status of the tc
		  test.log(Status.FAIL,"Reason for failure is "+result.getThrowable());
		  try {
			  String imgPath = new BaseClass().captureScreen(result.getName());
			  test.addScreenCaptureFromPath(imgPath);
		  }catch(IOException e1) {
			  e1.printStackTrace();
		  }
	  	  }

	  public void onTestSkipped(ITestResult result) {
		  test = extent.createTest(result.getName()); // create a new entry in the report 
		  test.log(Status.SKIP,"Test Case Skipped is "+result.getName()); // update the status of the tc

		  }

	public void onFinish(ITestContext context) {
		extent.flush();
	}



}
