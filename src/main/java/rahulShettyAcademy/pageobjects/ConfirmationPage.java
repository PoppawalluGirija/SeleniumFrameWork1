package rahulShettyAcademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShetty.abstractComponenents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {


		WebDriver driver;
		public ConfirmationPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);

	}

		@FindBy(css= "div[aria-label='Order Placed Successfully']")
		WebElement confirmationMessage;

		public String getConfirmationMessage()
		{
			return confirmationMessage.getText();

		}

}
