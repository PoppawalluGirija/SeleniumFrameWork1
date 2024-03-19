package rahulshettyaccademy.stepDefination;

import java.util.List;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulShetty.TestComponents.BaseTest;
import rahulShettyAcademy.pageobjects.CheckoutPage;
import rahulShettyAcademy.pageobjects.ConfirmationPage;
import rahulShettyAcademy.pageobjects.cartPage;
import rahulShettyAcademy.pageobjects.landingPage;
import rahulShettyAcademy.pageobjects.productCatolgue;

class StepDefinationImpl  extends BaseTest {
	
	
	public landingPage LandingPage;
	public productCatolgue ProductCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")// this line is from submit order cucumber
      	
	public void I_landed_on_Ecommerce_Page() throws IOException 
	
	{
       LandingPage = LaunchApplication();
	}
	
	 @Given ("^Logged in with username (.+) and password (.+)$")
     public void logged_in_username_and_password(String username, String password)
     {
		  ProductCatalogue= LandingPage.loginApplication(username,password);
     }
	 
	 
	 
	 @When ("^I add product (.+) from Cart$")
	public void  I_add_product_from_Cart(String productName) throws InterruptedException
	{
		 List<WebElement> products = ProductCatalogue.getProductList();
			ProductCatalogue.addProductsToCart(productName);

	}
	 
	 
	 
	 
	    @When("^Checkout(.+) and submit the order$")
	    public void  Checkout_and_submit_the_order(String productName) throws InterruptedException
	    {
	    	
	    	cartPage  cartPage = ProductCatalogue.goTOCartPage();
			
	        
		    Boolean match = cartPage.VerifyProductsDisplay(productName);
		    Assert.assertTrue(match);
		   		   
		    CheckoutPage checkoutPage=cartPage.goToCheckout();
		   
		    
		    checkoutPage.selectCountry("India");
		    checkoutPage.SubmitOrder();
		    
		   
		     confirmationPage= new ConfirmationPage(driver);

	    	
	    }
	    
	   

	    @Then ("{string} message is displayed on Confirmationpage")//String curly braces as it is run time variable//we have given string here because we havent given in examples in the submit ordre cucumber

        public void message_is_displayed_on_Confirmationpage(String string)
        {
	    	  String confirmMessage = confirmationPage.getConfirmationMessage();
		      Assert.assertFalse(confirmMessage.equalsIgnoreCase(string));
		      driver.close();
         }
	    
	    @Then("{string} message is displayed")
	    public void something_message_is_displayed(String strArg1) throws Throwable
	    {

	    	Assert.assertEquals(strArg1, LandingPage.getErrorMessage());
	        driver.close();
	    }

}
