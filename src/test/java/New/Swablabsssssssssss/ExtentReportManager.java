package New.Swablabsssssssssss;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	
	    private static ExtentReports extent;

	    public static ExtentReports createInstance(String testClassName)  {
	    	 File reportDir = new File(System.getProperty("user.dir") + "/target/HTML Reports");

             if(reportDir.exists()) {
          	   
          	   for(File file : reportDir.listFiles()) {
          		   file.delete();
          	   }
             }
             String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
 	        String reportPath = System.getProperty("user.dir") + "/target/HTML Reports/ExtentReport_" + testClassName + "_" + timestamp + ".html";

	            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
	            reporter.config().setReportName("SauceDemo Automation Report");
	            reporter.config().setDocumentTitle("Test Results");

	            extent = new ExtentReports();
	            extent.attachReporter(reporter);
	            extent.setSystemInfo("Tester", "Anas");
	            extent.setSystemInfo("System environment", "HP");
	            extent.setSystemInfo("Operating system", "Windows 11");
	            extent.setSystemInfo("Browser", "Google chrome");

	        
	        return extent;
	    }
	}


