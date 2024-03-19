package rahulShettyTest.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import rahulShetty.TestComponents.BaseTest;
import rahulShettyAcademy.pageobjects.CheckoutPage;
import rahulShettyAcademy.pageobjects.ConfirmationPage;
import rahulShettyAcademy.pageobjects.OrderPage;
import rahulShettyAcademy.pageobjects.cartPage;
import rahulShettyAcademy.pageobjects.productCatolgue;

public class submitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";
	

      	@Test(dataProvider="getData",groups= {"Purchase"})
		public void SubmitOrder(HashMap<String,String>input) throws IOException, InterruptedException
		{
      		
		productCatolgue ProductCatalogue= LandingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addProductsToCart(input.get("product"));
		
		
		cartPage  CartPage = ProductCatalogue.goTOCartPage();
		
       
	    Boolean match = CartPage.VerifyProductsDisplay(input.get("product"));
	    Assert.assertTrue(match);
	    Thread.sleep(2000);
	   
	    CheckoutPage checkoutPage=CartPage.goToCheckout();
	    Thread.sleep(3000);
	    
	    checkoutPage.selectCountry("India");
	    checkoutPage.SubmitOrder();
	    
	   
	    ConfirmationPage confirmationPage= new ConfirmationPage(driver);
	    
	    String confirmMessage = confirmationPage.getConfirmationMessage();
	      Assert.assertFalse(confirmMessage.equalsIgnoreCase( "THANKYOU FOR THE ORDER."));

	    
		}
      	




      	@Test(dependsOnMethods= {"SubmitOrder"})
      	public void OrderHistoryTest() 
      	{
      		productCatolgue ProductCatalogue= LandingPage.loginApplication("surabathulagirija@gmail.com", "Dajgar@123");
      	OrderPage ordersPage=ProductCatalogue.goToOrdersPage();
        Assert.assertFalse(ordersPage.VerifyOrderDisplay(productName));
      	}



//Extent Reports - used for html
	 
@DataProvider//helps in drive data and pass the multiple data sets
public Object[][] getData() throws IOException
{
	
	
	List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyaccademy\\data\\PurchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
}












}



















//@DataProvider//helps in drive data and pass the multiple data sets
//public Object[][] getData() throws IOException
//{
	//HashMap<String, String> map = new HashMap<String,String>();
//	map.put("email","surabathulagirija@gmail.com");
//	map.put("password", "Dajgar@123");
	//map.put("product", "ADIDAS ORIGINAL");
	
	//HashMap<String, String> map1 = new HashMap<String,String>();
	//map1.put("email","jk@gmail.com");
	//map1.put("password", "Rahul@shetty1");
	//map1.put("product", "IPHONE 13 PRO");








/* Thread.sleep(5000);
       WebElement dropdown = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
       Actions a = new Actions(driver);
	    a.moveToElement(dropdown).perform();  
	    a.click(dropdown).perform();
	    a.sendKeys("India").perform();
	    WebElement optionToSelect = driver.findElement(By.xpath("//span[normalize-space()='India']"));
        a.click(optionToSelect).perform();//span[normalize-space()='British Indian Ocean Territory']
	      Thread.sleep(5000);
	     //
	     // so dis is java casting 
	     
	            // Thread.sleep(5000);
WebElement button = driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted"));
	  button.click();
ConfirmationPage confirmationPage= checkoutPage.SubmitOrder();
      // 
	   // Click the button*/
	     
	    

	
