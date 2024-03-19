package rahulShettyTest.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulShetty.TestComponents.BaseTest;
import rahulShetty.TestComponents.Retry;
import rahulShettyAcademy.pageobjects.cartPage;
import rahulShettyAcademy.pageobjects.productCatolgue;

public class ErrorValidationTest extends BaseTest {

      
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	     
	    public void LoginErrorValidation() throws IOException, InterruptedException
	   {

        LandingPage.loginApplication("surabathulagirija@gmail.com", "Dagar@123");
	     Assert.assertEquals("Incorrect email or password.", LandingPage.getErrorMessage());
		
	 
	   }


      	@Test
		public void ProductErrorValidation() throws InterruptedException 
		{

		String productName = "ADIDAS ORIGINAL";

		productCatolgue ProductCatalogue= LandingPage.loginApplication("jk@gmail.com","Rahul@shetty1");
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addProductsToCart(productName);
		cartPage  CartPage = ProductCatalogue.goTOCartPage();
		Thread.sleep(3000);
       Boolean match = CartPage.VerifyProductsDisplay("ADIDASS");
	    Assert.assertFalse(match);//this is for error check
       	
		}
      	
      



}
