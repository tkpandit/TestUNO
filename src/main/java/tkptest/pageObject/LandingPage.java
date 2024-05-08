package tkptest.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tkptest.AbstractComponents.AbstractComponent;


public class LandingPage extends AbstractComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//input[@formcontrolname='username']")
	WebElement userEmail;

	@FindBy(xpath ="//input[@formcontrolname='password']")
	WebElement passWord;

	@FindBy(xpath ="//button[@type='submit']")
	WebElement submit;

	@FindBy(xpath = "//h4[@class='label col-12']")
	WebElement errorMessage;
	
	By loader = By.cssSelector(".loader");
	

	public void loginApplication(String username , String password) {
		userEmail.sendKeys(username);
		passWord.sendKeys(password);
		submit.click();
	}

	public String getErrorMessage() {
		waitForElementToDisappear(loader);
		return errorMessage.getText();
	}
	
	public void resetLogin(String username1 , String password1) {
		waitForElementToDisappear(loader);
		userEmail.clear();
		passWord.clear();
		userEmail.sendKeys(username1);
		passWord.sendKeys(password1);
		submit.click();
		waitForElementToDisappear(loader);	
	}

	public void goTo(String url) {
		driver.get(url);
	}

}
