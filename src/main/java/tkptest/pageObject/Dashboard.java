package tkptest.pageObject;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tkptest.AbstractComponents.AbstractComponent;




public class Dashboard extends AbstractComponent{
	
	WebDriver driver;
	
	public Dashboard(WebDriver driver) {
        super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='nav-link dropdown-toggle dArrowNone cursor-pointer propertyDropdown']")
	WebElement propDropdwon;
	
	By loader = By.cssSelector(".loader");
	By promoBanner = By.cssSelector(".promotion-banner");
	By propIcon = By.cssSelector(".nav-link dropdown-toggle dArrowNone cursor-pointer propertyDropdown");
	
	@FindBy(xpath= "//div[@class='menu-today ng-star-inserted']")
	WebElement menuToday;

	
	@FindBy(xpath ="//img[@id='openImg']")
	WebElement megaMenuIcon;

	
	@FindBy(xpath = "//p[@class='m-0 primary-txt']")
	WebElement promoFooterText;
	
	@FindBy(xpath ="//strong[@class='pageNameHeading ng-star-inserted']")
	WebElement dBTitle;
	
	public String dashBoardUrl() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.urlToBe("https://uno-qa.rategain.com/crs-dashboard"));
//		waitForElementToDisappear(loader);
		String url = this.driver.getCurrentUrl();
		return url;
	}
	
	public String defaultProperty(){
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String defualt_Prop = propDropdwon.getText();
		return defualt_Prop;
	}
	
	public String megaMenuOption() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		megaMenuIcon.click();
		String menuText = menuToday.getText();
		return menuText;
	}
	
	public String dashBoardTitle() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		String title = dBTitle.getText();
		return title;
	}
	

	public String promotionFooter() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
//		megaMenuOption();
		String promotext = promoFooterText.getText();
		return promotext;
	}
	
}
