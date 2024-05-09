package tkptest.pageObject;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tkptest.AbstractComponents.AbstractComponent;

public class InternalPropertySelectionPage extends AbstractComponent{

	WebDriver driver;
	public InternalPropertySelectionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@role='tab']")
	List<WebElement> tabButtons;

	@FindBy(xpath = "//table[@id='transparentScrollbarDiv']/tbody/tr")
	List<WebElement> rows;

	By loader = By.cssSelector(".loader");
	


	public void getPropertyTab(String tabName) throws InterruptedException {
		waitForElementToDisappear(loader);
		WebElement tabs =  tabButtons.stream().filter(tabBtn->
		tabBtn.getText().equals(tabName)).findFirst().orElse(null);
		tabs.click();
		waitForElementToDisappear(loader);
	}
	
	public void propertySelection(String propName) {
		List<WebElement> matchingRows = rows.stream()
				.filter(row -> row.getText().contains(propName))
				.toList();

		matchingRows.forEach(row -> {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			cells.forEach(cell -> {
				System.out.print(cell.getText() + "\t");
			});
			WebElement viewButton = row.findElement(By.xpath(".//button[text()='View']"));
			viewButton.click();
		});
	}

}
