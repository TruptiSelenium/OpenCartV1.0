package testCases;

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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
    public Logger logger; //log4j
    public Properties p;     
     
	@BeforeClass(groups= {"Sanity","Master","Regression"})
	@Parameters({"os","browser"})
	public void setup(String os,String browser) throws IOException {
		logger = LogManager.getLogger(this.getClass()); //log4j2
		
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		
		p.load(file);
		logger.info("*************Properties File Loaded  ****************");

		//Execute the code in If case for Remote execution on Selenium Grid
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			switch(browser.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome");
							break;
			case "firefox":capabilities.setBrowserName("firefox");
							break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");
						    break;
			default:System.out.println("Invalid browser..");
							return;
			}
			switch(os.toLowerCase()) {
			case "windows": capabilities.setPlatform(Platform.WIN11);
							break;
			case "linux" : capabilities.setPlatform(Platform.LINUX);
							break;
			case "mac" : capabilities.setPlatform(Platform.MAC);
							break;
			default: System.out.println("Invalid OS");
			                 return;
										
			}
			//Launch the remotewebdriver 
			
			driver = new RemoteWebDriver(new URL("http://192.168.1.36:4444/wd/hub"),capabilities);
		}
		
		// Execute below code when the env is local
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(browser.toLowerCase()) {
				case "chrome":		driver = new ChromeDriver();break;
				case "firefox":		driver = new FirefoxDriver();break;
				case "edge":		driver = new EdgeDriver();break;
				case "default" :    System.out.println("Invalid browser..");
									return; 
		}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("appURL")); // getting url from properties file 
		logger.info("*************Application URL Launched ****************");
		//driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		logger.info("*************Application Window Maximized  ****************");

	}
	@AfterClass(groups= {"Sanity","Master","Regression"})
	public void teardown() {
		driver.quit();
		logger.info("*************Application URL Closed  ****************");

	}
	public String  randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String  randomNumber() {
		String generatedNo = RandomStringUtils.randomNumeric(10);
		return generatedNo;
	}
	
	public String  randomAlphanumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNo = RandomStringUtils.randomNumeric(2);
		String generatedAlpha  = generatedString + generatedNo;
		return generatedAlpha;
	}

	public String captureScreen(String tname)throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot )driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname +"_"+timeStamp+".png";
		File TargetFile = new File(targetFilePath);
		srcFile.renameTo(TargetFile);
		
		return targetFilePath;
	}
	
}
