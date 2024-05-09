package tkptest.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tkptest.AbstractComponents.AbstractComponent;

public class MegaMenuSetup extends AbstractComponent {

	WebDriver driver;
	Actions actions;

	public MegaMenuSetup(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='floatLeft col-6']/ul/li/a")
	List<WebElement> options;

	@FindBy(xpath = "//a[normalize-space()='General']")
	WebElement generalPolicyLink;
	
	@FindBy(xpath="//a[normalize-space()='Tax/Charges']")
	WebElement taxLink;

	@FindBy(xpath = "//a[normalize-space()='Room']")
	WebElement roomLink;

	@FindBy(xpath = "//a[normalize-space()='Rate']")
	WebElement rateLink;

	@FindBy(xpath = "//a[normalize-space()='Season']")
	WebElement seasonLink;

	@FindBy(xpath = "//a[normalize-space()='Product']")
	WebElement productLink;

	@FindBy(xpath = "//a[normalize-space()='Incremental Rate']")
	WebElement increRateLink;

	@FindBy(xpath = "//a[@class='w-100'][normalize-space()='Ancillary Service']")
	WebElement ancillaryLink;

	@FindBy(xpath = "//a[normalize-space()='Promotion']")
	WebElement promotionLink;

	@FindBy(xpath = "//a[normalize-space()='Chain']")
	WebElement chainLink;

	@FindBy(xpath = "//a[normalize-space()='Brand']")
	WebElement brandLink;

	@FindBy(xpath = "//a[normalize-space()='Property']")
	WebElement propertyLink;

	@FindBy(xpath = "//a[normalize-space()='Cluster']")
	WebElement clusterLink;
	
	By loader = By.cssSelector(".loader");

	public void masterOptionSetup(String givenOption) {
		waitForElementToDisappear(loader);
		actions = new Actions(driver);
		for (WebElement option : options) {
			if (option.getText().contains("Master Setup")) {
				actions.moveToElement(option).perform();
				// Handle valid option
				switch (givenOption) {
				case "Room":
					roomLink.click();
					return;

				case "Rate":
					rateLink.click();
					return;

				case "Season":
					seasonLink.click();
					return;

				case "Product":
					productLink.click();
					return;

				case "Incremental Rate":
					increRateLink.click();
					return;

				default:
					// Handle invalid option
					System.out.println("Invalid option: " + givenOption);
					return;
				}
			}
		}
	}

	public void policySetup(String givenOption) {
		waitForElementToDisappear(loader);
		actions = new Actions(driver);
		for (WebElement option : options) {
			if (option.getText().contains("Policy Setup")) {
				actions.moveToElement(option).perform();
				// Handle valid option
				switch (givenOption) {
				case "General":
					generalPolicyLink.click();
					return;

				case "Tax/Charges":
					taxLink.click();
					return;
					
				default:
					// Handle invalid option
					System.out.println("Invalid option: " + givenOption);
					return;
				}
			}
		}
	}

	public void generalSetup(String givenOption) {
		waitForElementToDisappear(loader);
		actions = new Actions(driver);
		for (WebElement option : options) {
			if (option.getText().contains("General Setup")) {
				actions.moveToElement(option).perform();
				// Handle valid option
				switch (givenOption) {
				case "Chain":
					chainLink.click();
					return;

				case "Brand":
					brandLink.click();
					return;

				case "Property":
					propertyLink.click();
					return;

				case "Cluster":
					clusterLink.click();
					return;
					
				default:
					// Handle invalid option
					System.out.println("Invalid option: " + givenOption);
					return;
				}
			}
		}
	}
}