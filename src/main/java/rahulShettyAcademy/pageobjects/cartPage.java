package rahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShetty.abstractComponenents.AbstractComponents;

public class cartPage extends AbstractComponents{

		WebDriver driver;

	@FindBy(css = "li[class='totalRow'] button[type='button']")
	WebElement checkoutEle;

	@FindBy(css=".cartSection h3")//this is to check the rigth product added to the cart
	private List<WebElement> cartProducts;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	public Boolean VerifyProductsDisplay(String productName) {

		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;

	}

	public CheckoutPage goToCheckout() throws InterruptedException  {
			
		checkoutEle.click();
		Thread.sleep(2000);
	return	new CheckoutPage(driver);

	}






}
