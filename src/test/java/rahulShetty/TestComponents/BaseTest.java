package rahulShetty.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShettyAcademy.pageobjects.landingPage;

public class BaseTest {

	public WebDriver driver;//this is global driver for this class
	public landingPage LandingPage;


	public WebDriver initializeDriver() throws IOException
	{
		//properties class

		Properties prop = new Properties();//System,getProperty in the below line gives the file location dynamically
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulShettyAcademy\\resources\\GlobalData.properties");
		prop.load(fis);//input stream obeject neede in this
	    
		String browserName  =System.getProperty("browser")!=null ? 		System.getProperty("browser"): prop.getProperty("browser");
		//prop.getProperty("browser");

		if(browserName.contains("chrome"))
		{
          ChromeOptions options = new ChromeOptions();
          
		WebDriverManager.chromedriver().setup();//chrome driver will be automatically downloded by this line
		if(browserName.contains("headless")) {
		options.addArguments("headless");
		}
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));//full screen

		}
		else if(browserName.equalsIgnoreCase("firefox")) {

	    }else if(browserName.equalsIgnoreCase("edge"))	{

			System.setProperty("WebDriver.edge.driver","edge.exe");
		    driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	    return driver;
	}

		public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
	   //read json to string
		String jsonContent =  FileUtils.readFileToString(new File(filePath),	
	    StandardCharsets.UTF_8);
	
		//String to HashMap jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		
	return data;
		
		}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		   File source =ts.getScreenshotAs(OutputType.FILE);
	       File file = new File(System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png");
		   FileUtils.copyFile(source, file);
		   return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png" ;

	}
	
	@BeforeMethod(alwaysRun=true)
	public landingPage LaunchApplication() throws IOException
	{
		driver = initializeDriver();
	    LandingPage = new landingPage(driver);//this landingPage called from landing class and created object ofLanding page
		LandingPage.goTo();//from here web page launches
		return LandingPage;

	}



	
	@AfterMethod(alwaysRun=true)

    public void tearDown()
	{
		  driver.close();//this will close the webpage
	}

}
