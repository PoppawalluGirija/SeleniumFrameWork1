package rahulShettyAcademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import rahulShetty.abstractComponenents.AbstractComponents;

public class landingPage  extends AbstractComponents{

	WebDriver driver;

public landingPage(WebDriver driver)//this is a constructor /intialization of code starts here
    {

	   super(driver);//this creted for abstract compnent constructor to acces driver
	   this.driver=driver;//this.driver = this local class driver from line 9 and driver is from submitOrder class
    }



     @FindBy(id="userEmail")
     WebElement userEmail;

     @FindBy(id="userPassword")
     WebElement PasswordEle;

     @FindBy(id="login")
     WebElement submit;

     @FindBy(id= "toast-container")
     WebElement errorMessage;

     public productCatolgue loginApplication(String email, String password) {
    	 userEmail.sendKeys(email);//userEmail from line 18//sendKeys email will be from submitOrder class (this line LandingPage.loginApplication)
    	 PasswordEle.sendKeys(password);
    	 submit.click();
    	 productCatolgue ProductCatalogue = new productCatolgue(driver);//dis for opening new window
         return ProductCatalogue;

     }

     public String getErrorMessage()
     {
    	 waitForWebElementToAppear(errorMessage);
    	return  errorMessage.getText();
     }

     public void goTo() {
    	 driver.get("https://rahulshettyacademy.com/client");
    }

}
