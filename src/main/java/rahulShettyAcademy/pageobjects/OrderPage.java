package rahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShetty.abstractComponenents.AbstractComponents;

public class OrderPage extends AbstractComponents{

		WebDriver driver;

	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	WebElement checkoutEle;

	@FindBy(css=".cartSection h3")
	public List<WebElement> cartProducts;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	public Boolean VerifyOrderDisplay(String productName) {

		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;

	}

	public CheckoutPage goToCheckout()  {
		
		checkoutEle.click();
	return	new CheckoutPage(driver);

	}






}
