package rahulShetty.abstractComponenents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulShettyAcademy.pageobjects.OrderPage;
import rahulShettyAcademy.pageobjects.cartPage;


public class AbstractComponents {

	WebDriver driver;//created constructor  using super from landing page
	public AbstractComponents(WebDriver driver) {
	      this.driver= driver;
		  PageFactory.initElements(driver, this);
	}

	@FindBy (css="[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css="[routerlink*='myorders']")
     WebElement orderHeader;
	
	
	public void waitForElementToAppear(By findBy) {//findBy is wriiten here as in landing lage we have given userid,email,password as by elements
    	 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
         wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
     }
	

	 public  void waitForWebElementToAppear(WebElement findBy) {//dis webelement wait for  landing page error message method
    	 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
         wait.until(ExpectedConditions.visibilityOf(findBy));
     }


	 public cartPage goTOCartPage() {//dis created for header elements in cart page

	cartHeader.click();
	cartPage CartPage = new cartPage(driver);
	return CartPage;
	 }
	 
	 public OrderPage goToOrdersPage()
	 
	 {
		 orderHeader.click();
		 OrderPage orderPage = new OrderPage(driver);
		 return orderPage;
	}

     public void waitForElementToDisAppear(WebElement ele)  {//this is web element

    	
    	 //4seconds= Application
    	 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

    	 wait.until(ExpectedConditions.visibilityOf(ele));
     }



	public  void waitForElementsFindHere( By findBy) {

		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

}
