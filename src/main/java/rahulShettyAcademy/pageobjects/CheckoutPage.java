package rahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShetty.abstractComponenents.AbstractComponents;

public class CheckoutPage extends AbstractComponents  {

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}


	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath = "//span[normalize-space()='India']")
	WebElement selectCountry;

	@FindBy(xpath = "//a[normalize-space()='Place Order']")
	WebElement submit;



	public By results = By.cssSelector(".ta-results");

		public void selectCountry(String countryName) throws InterruptedException {
			
		    Actions a = new Actions(driver);
	 a.sendKeys(country, countryName).build().perform(); 
	 Thread.sleep(1000);
	 //waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
		
	   JavascriptExecutor js= (JavascriptExecutor)driver;
       js.executeScript("window.scrollBy(0,500)");
      
        
	}

	public ConfirmationPage SubmitOrder() throws InterruptedException
	{
		
		submit.click();//this will help in land in the confirmation page
		
		Thread.sleep(3000);
	    return new ConfirmationPage(driver);
	}

	

}





/*a.moveToElement(country).perform();
a.click(country);
a.sendKeys("India").perform();

Thread.sleep(3000);
WebElement country= driver.findElement(By.cssSelector("input[placeholder='Select Country']")); 
			  
		//this four line of code is useful to choose the drop down option to select the country*/
