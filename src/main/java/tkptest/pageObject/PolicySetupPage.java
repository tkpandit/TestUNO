package tkptest.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tkptest.AbstractComponents.AbstractComponent;

public class PolicySetupPage extends AbstractComponent {
	WebDriver driver;

	public PolicySetupPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@role='tab']")
	List<WebElement> tab;

	@FindBy(xpath = "//table[@role='table']/tbody/tr")
	List<WebElement> rows;

	@FindBy(xpath = "//a[@title='Add New Policy']")
	WebElement addPolicy;

	@FindBy(xpath = "//input[@name ='policyName']")
	WebElement polName;

	@FindBy(xpath = "//mat-radio-group[@name='cancellationNotice']")
	WebElement radioBtnNRF;

	@FindBy(xpath = "//label[@for='mat-radio-3-input']//span[@class='mat-radio-outer-circle']")
	WebElement radioBtnRF;

	@FindBy(xpath = "//input[@name='fullRefundPeriodHrs']")
	WebElement fRFPeriodHrs;

	@FindBy(xpath = "//label[@for='mat-radio-26-input']//span[@class='mat-radio-label-content']")
	WebElement oneNightStay;

	@FindBy(xpath = "//label[@for='mat-radio-27-input']//span[@class='mat-radio-label-content']")
	WebElement fullStay;

	@FindBy(xpath = "//label[@for='mat-radio-28-input']//span[@class='mat-radio-label-content']")
	WebElement customStay;

	@FindBy(xpath = "//mat-checkbox[@name='isNoEndDate']")
	WebElement noEndDate;

	@FindBy(xpath = "//label[@for='mat-checkbox-2-input']//span[@class='mat-checkbox-inner-container']")
	WebElement daySelection;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement saveBtn;

	By loader = By.cssSelector(".loader");

	public void tabOption(String givenTab) throws InterruptedException {
		waitForElementToDisappear(loader);
		WebElement tabs = tab.stream().filter(tabBtn -> tabBtn.getText().equals(givenTab)).findFirst().orElse(null);
		tabs.click();
		System.out.println(tabs.getText());
	}

	public String policyList(String cnPol) throws Exception {
		waitForElementToDisappear(loader);
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.xpath("//td/div[@class='custom-tooltip']"));
			for (WebElement cell : cells) {
				String cellText = cell.getText();
				if (cellText.contains(cnPol)) {
					return cellText;
				}
			}
		}
		return null;
	}

	public void addNewPolicy(String type, String name) throws InterruptedException {
		addPolicy.click();
		waitForElementToDisappear(loader);
		if (type == "Refundable") {
			this.refundablePolicy(name);
		} else {
			this.nonRefundable(name);
		}
	}

	public void refundablePolicy(String name1) {
		polName.click();
		polName.sendKeys(name1);
		radioBtnRF.click();
		fRFPeriodHrs.sendKeys("20");
		oneNightStay.click();
		noEndDate.click();
		daySelection.click();
		saveBtn.click();
	}

	public void nonRefundable(String name1) throws InterruptedException {
		polName.click();
		polName.sendKeys(name1);
		radioBtnNRF.click();
		noEndDate.click();
		daySelection.click();
		saveBtn.click();
	}
}
