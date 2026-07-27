package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class BaseCase 
{
	  public static WebDriver driver;
	  public Logger logger;
	  public Properties p;
	
    //@SuppressWarnings("deprecation")
	//@BeforeClass(groups={"Sanity","Regression","Master"})
    //@Parameters({"os","browser"})
    
    @BeforeClass
    @Parameters({"os","browser"})
    public void setup(String os,String br) throws IOException
    {
	
		//Loading config.properties file
    	FileReader file=new FileReader(".//src//test//resources//config.properties");     //pre defined class
    	p=new Properties();  //create object
    	p.load(file);
    	
    	logger=LogManager.getLogger(this.getClass());
    	
    	  //if environment is remote
//    	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
//    	{
//    		DesiredCapabilities capabilities=new DesiredCapabilities();
//    		//capabilities.setPlatform(Platform.WIN11);    for operating system
//    		if(os.equalsIgnoreCase("windows"))
//    		{
//    			capabilities.setPlatform(Platform.WIN11);
//    		}
//    		else if(os.equalsIgnoreCase("mac"))
//    		{
//    			capabilities.setPlatform(Platform.MAC);
//    		}
//    		else
//    		{
//    			System.out.println("No matching os");
//    			return;
//    		}
//    		//capabilities.setBrowserName("chrome");  for Browser
//    		switch(br.toLowerCase())
//    		{
//    		case "chrome":capabilities.setBrowserName("Chrome"); break;
//    		case "edge":capabilities.setBrowserName("MicrosoftEdge"); break;
//    		default:System.out.println("No matching browser"); 
//    		return;
//    		}
//    		driver=new RemoteWebDriver(new URL("http://localhost:4444"),capabilities);
//    	}
// 
    	if (p.getProperty("execution_env").equalsIgnoreCase("remote"))
    	{
    	    switch (br.toLowerCase())
    	    {
    	        case "chrome":

    	            ChromeOptions options = new ChromeOptions();
    	            options.setPlatformName("Windows 11");

    	            driver = new RemoteWebDriver(
    	                    new URL("http://localhost:4444"),
    	                    options);
    	            break;

    	        case "edge":

    	            EdgeOptions edgeOptions = new EdgeOptions();
    	            edgeOptions.setPlatformName("Windows 11");

    	            driver = new RemoteWebDriver(
    	                    new URL("http://localhost:4444"),
    	                    edgeOptions);
    	            break;

    	        default:
    	            throw new RuntimeException("Invalid browser name");
    	    }
    	}
    	if(p.getProperty("execution_env").equalsIgnoreCase("local"))
    	{
    		switch(br.toLowerCase())
        	{
        	case "chrome":driver=new ChromeDriver(); break;
        	case "edge" :driver=new EdgeDriver(); break;
        	case "firefox":driver=new FirefoxDriver(); break;
        	default:System.out.println("Invalid browser name.."); return;
        	}	
    	}
    			
    		
    	//driver=new ChromeDriver();
    	driver.manage().deleteAllCookies();
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	driver.get(p.getProperty("appURL"));   //reading url from properties file
	}
    
    @AfterClass(groups={"Sanity","Regression","Master"})
    public void teardown()
    {
    	driver.quit();
    }
    
    
    public String randomString()
    {
    	String generatedString=RandomStringUtils.randomAlphabetic(5);
    	return generatedString;
    }
    public String randomNumber()
    {
    	String generatedNumber=RandomStringUtils.randomNumeric(10);
    	return generatedNumber;
    }
    public String randomAlphaNumeric()
    {
    	String generatedString=RandomStringUtils.randomAlphabetic(3);
    	String generatedNumber=RandomStringUtils.randomNumeric(3);
    	return (generatedString+"@"+generatedNumber);
    }
    
    public static String captureScreen(String tname) throws IOException
    {
    	String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    	
    	TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
    	File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
    	
    	String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_"+ timeStamp + ".png";
    	File targetFile=new File (targetFilePath);
    	
    	//sourceFile.renameTo(targetFile);
    	FileHandler.copy(sourceFile, targetFile);
//    	return targetFilePath;
    	return "..\\screenshots\\"+ tname + "_"+ timeStamp + ".png";
    }
    
}
    		
    		
    		
    	
    	
    	
    	
    	
    	
    	




    	
    	
