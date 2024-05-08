package tkptest.pageObject;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	
	public String defaultProperty(){
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		String defualt_Prop = propDropdwon.getText();
		return defualt_Prop;
	}
	
	public String  megaMenuOption() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		megaMenuIcon.click();
		String menuText = menuToday.getText();
		return menuText;
	}
	
	public String promotionFooter() {
		waitForElementToAppear(promoBanner);
		megaMenuOption();
		String promotext = promoFooterText.getText();
		return promotext;
	}
	
}
