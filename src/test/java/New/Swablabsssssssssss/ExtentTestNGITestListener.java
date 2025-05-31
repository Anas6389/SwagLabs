package New.Swablabsssssssssss;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports; 
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentTestNGITestListener implements ITestListener{
	
	
		
		  private static ExtentReports extent;
		    public static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

		    @Override
		    public void onStart(ITestContext context) {
		        String testClassName = context.getCurrentXmlTest().getClasses().get(0).getName(); // full class name with package
		        String simpleClassName = testClassName.substring(testClassName.lastIndexOf('.') + 1); // extract only class name

		        extent = ExtentReportManager.createInstance(simpleClassName);
		    }

		    @Override
		    public void onTestStart(ITestResult result) {   //to get text name function
		        if (extent == null) {
		            // Fallback: create instance based on class name if not already set
		            String testClassName = result.getTestClass().getName();
		            extent = ExtentReportManager.createInstance(testClassName);
		        }
		        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		        testThread.set(test);
		    }

		    @Override
		    public void onTestSuccess(ITestResult result) {
		        testThread.get().log(Status.PASS, "Test Passed");
		    }

		    @Override
		    public void onTestFailure(ITestResult result) {
		        testThread.get().log(Status.FAIL, "Test Failed");
		        testThread.get().log(Status.FAIL, result.getThrowable());
		    }

		    @Override
		    public void onTestSkipped(ITestResult result) {
		        testThread.get().log(Status.SKIP, "Test Skipped");
		    }

		    @Override
		    public void onFinish(ITestContext context) {  //to remove old files
		        if (extent != null) {
		            extent.flush();
		        }
		    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


