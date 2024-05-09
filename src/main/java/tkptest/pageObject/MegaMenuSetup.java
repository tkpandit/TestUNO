package tkptest.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tkptest.AbstractComponents.AbstractComponent;

public class PolicySetup extends AbstractComponent {

	WebDriver driver;

	public PolicySetup(WebDriver driver) {
        super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@class='floatLeft col-6']/ul/li/a")
	List<WebElement> option;
	
	By loader = By.cssSelector(".loader");
	
	public void policySetup() {
		waitForElementToDisappear(loader);
		option.forEach(ele -> {
			System.out.println(ele.getText());
		});
	}
}
