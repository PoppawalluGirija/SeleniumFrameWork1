package rahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShetty.abstractComponenents.AbstractComponents;

public class productCatolgue extends AbstractComponents{

	WebDriver driver;

public productCatolgue(WebDriver driver)//this is a constructor /intialization of code starts here
    {
	   super(driver);//came from parent
	   this.driver=driver;
	  PageFactory.initElements(driver, this);
    }

     @FindBy(css=".mb-3")
     List<WebElement> products;

     @FindBy(css =".ng-animating")
    WebElement spinner;//the spinner and driver here are page object factory

     By productsBy=By.cssSelector(".mb-3");
     By addToCart = By.cssSelector(".card-body button:last-of-type");
     By toastMessage = By.cssSelector("#toast-container");





     public  List<WebElement> getProductList() {

    	 waitForElementToAppear(productsBy);//wait for the products to appear
    	 return  products;
     }


     public WebElement getProductByName(String productName)

     {
    	 WebElement prod =	getProductList().stream().filter(product->
			product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
            return prod;
     }

     public void addProductsToCart(String productName) throws InterruptedException  
     {
	   
    	WebElement prod = getProductByName(productName);
  Thread.sleep(3000);
    	prod.findElement(addToCart).click();
    	 waitForElementToAppear(toastMessage);
    	 waitForElementToDisAppear(spinner);

     }


     public void goTo() {
    	 driver.get("https://rahulshettyacademy.com/client");
     }

}
