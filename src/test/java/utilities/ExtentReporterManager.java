package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseCase;

public class ExtentReporterManager implements ITestListener 
{
	public ExtentSparkReporter sparkReporter;  //UI of the report-how it looks like-dark/white theme,where to display info,color,alignment
	public ExtentReports extent;  //populates common info of the report like who is executing test cases,tester name,browser name OS,project 
	public ExtentTest test;  //creating test cases entries in the reports and update status of the test methods
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);*/
		                     //OR
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  //time stamp
		repName="Test-Report-"+ timeStamp + ".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName); //specify location of the report
		
		sparkReporter.config().setDocumentTitle("WebShop Automation Report");  //Title of report
		sparkReporter.config().setReportName("WebShop Functional Testing");  //Name of reports
		sparkReporter.config().setTheme(Theme.DARK);  //STANDARD=White
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","DemoWebShop");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("SubModule","Customers");
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups",includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+" got successfully executed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String imgPath= BaseCase.captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e1) {
			e1.printStackTrace(); 
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+" got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) 
	{
		extent.flush();
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File (pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		/*try 
		{
		URL url=new URL("file:///"+System.getProperty("user.dir") + "\\reports\\" +repName);
		      //Create the email message
		ImageHtmlEmail email=new ImageHtmlEmail();    //dependency=Apache-commons-email
		email.setDataSourceResolver(new DataSourceUrlResolver (url));
		email.setHostName("smpt.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("   ","password")); //who is sending email
		email.setSSLOnConnect(true);
		email.setFrom("   ");  //sender
		email.setSubject("Test Results");
		email.setMsg("Please find Attatched report...");
		email.addTo("   ");  //receiver
		email.attach(url,"extent report","please check report...");
		email.send();  //send the email
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		*/
	}
}


/*
 * add listeners before test in xml file
 * 
 * <groups>
  		<run>
  			 <include name="Sanity"/> 
  			<!-- <include name="Regression"/>-->
  			<!-- <include name="Master"/>-->
  		</run>
  </groups>
<listeners>
	<listener class-name="utilities.ExtentReporterManager"/>
</listeners>
*/

			
		
		
	
	
		
		
		
	

