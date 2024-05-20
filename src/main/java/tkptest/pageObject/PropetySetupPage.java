package tkptest.pageObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tkptest.AbstractComponents.AbstractComponent;

public class PropetySetupPage extends AbstractComponent {

	WebDriver driver;

	public PropetySetupPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Add Property']")
	WebElement addPropertyLink;

	@FindBy(xpath = "//input[@formcontrolname ='name']")
	WebElement propName;

	@FindBy(xpath = "//input[@formcontrolname='code']")
	WebElement propUnoCode;

	@FindBy(xpath = "//input[@formcontrolname='pmsPropertyCode']")
	WebElement propPmsCode;

	@FindBy(xpath = "//mat-select[@formcontrolname='connectionType']")
	WebElement propConnType;

	@FindBy(xpath = "//mat-option[@role='option']")
	List<WebElement> menuOption;

	@FindBy(xpath = "//mat-select[@formcontrolname='brandID']")
	WebElement brandID;

	@FindBy(xpath = "//mat-option[@role='option']")
	List<WebElement> brandMenu;

	@FindBy(xpath = "//input[@formcontrolname='totalFloors']")
	WebElement totFloors;

	@FindBy(xpath = "//input[@formcontrolname='numberOfRooms']")
	WebElement nosOfRooms;

	@FindBy(xpath = "//input[@formcontrolname='address']")
	WebElement address;

	@FindBy(xpath = "//mat-select[@formcontrolname='country']")
	WebElement country;

	@FindBy(xpath = "//mat-select[@formcontrolname='state']")
	WebElement state;

	@FindBy(xpath = "//mat-select[@formcontrolname='city']")
	WebElement city;

	@FindBy(xpath = "//input[@formcontrolname='pinCode']")
	WebElement pinCode;

	@FindBy(xpath = "//input[@formcontrolname='phone']")
	WebElement phone;

	@FindBy(xpath = "//input[@formcontrolname='email']")
	WebElement email;

	@FindBy(xpath = "//mat-select")
	List<WebElement> timeZone;

	@FindBy(xpath = "//mat-select[@formcontrolname='currency']")
	WebElement currency;

	@FindBy(xpath = "//button[@class='btnDone ng-star-inserted']")
	WebElement saveBtn;

	@FindBy(xpath = "//div[@class='col heading fw-light']")
	WebElement propListCountHeader;

	@FindBy(xpath = "//div[@class='listingBox ng-star-inserted']//div[@class='addressHotels']/a")
	List<WebElement> propNameinList;

	@FindBy(xpath = "//img[@class='abc']")
	WebElement searchIcon;

	@FindBy(xpath = "//input[@name='searchFilter']")
	WebElement typeHere;

	@FindBy(xpath = "//div[@class='listingBox ng-star-inserted']//div[@class='codBox custom-tooltip']/div[@class='tooltiptext']")
	List<WebElement> listingBrand_propCode;

	@FindBy(xpath = "//p[@class='alert-message']")
	WebElement alertPropCreate;

	By loader = By.cssSelector(".loader");
	By overlay = By.cssSelector("div.loader-overlay");
	By link = By.cssSelector(".searchInActive");
	By btn = By.className(".btnDone.ng-star-inserted");

	public void propertyListingCountRow() throws Exception {
		Thread.sleep(5000);
		String propListCount = propListCountHeader.getText();
		System.out.println(propListCount);
		Pattern pattern = Pattern.compile("\\b(\\d+)\\b");
		Matcher matcher = pattern.matcher(propListCount);
		if (matcher.find()) {
			// Return the matched number
			System.out.println("The total live property=" + matcher.group(1));
		} else {
			System.out.println("No Property Found");
		}
	}

	public void propertyDetails(String pName, String pCode, String pmsCode, String conType, String brndID,
			String nosOfRm, String addr, String cntry, String sts, String cty, String pin, String ph, String mail,
			String tzone, String curncy) throws Exception {
		addPropertyLink.click();
		waitForElementToDisappear(loader);
		propName.sendKeys(pName);
		propUnoCode.sendKeys(pCode);
		propPmsCode.sendKeys(pmsCode);
		propConnType.click();
		connTypeMenuList(conType);
		brandID.click();
		brandMenuList(brndID);
		nosOfRooms.sendKeys(nosOfRm);
		address.sendKeys(addr);
		country.click();
		countryMenuList(cntry);
		state.click();
		stateMenuList(sts);
		city.click();
		cityMenuList(cty);
		pinCode.sendKeys(pin);
		phone.sendKeys(ph);
		email.sendKeys(mail);
	}

	public String saveProperty() {
		// TODO Auto-generated method stub
		saveBtn.click();
		String alertText = alertPropCreate.getText();
		waitForElementToDisappear(loader);
		return alertText;
	}

	public void connTypeMenuList(String conType) throws InterruptedException {
		waitForElementToDisappear(loader);
		WebElement menuList = menuOption.stream().filter(ele -> ele.getText().equals(conType)).findFirst().orElse(null);
		if (menuList != null) {
			menuList.click();
			return;
		}
	}

	public void brandMenuList(String brndMenu) throws Exception {
		waitForElementToDisappear(loader);
		WebElement menuList = menuOption.stream().filter(ele -> ele.getText().equals(brndMenu)).findFirst()
				.orElse(null);
		if (menuList != null) {
			menuList.click();
			return;
		}
	}

	public void countryMenuList(String countryMenu) throws Exception {
		WebElement menuList = menuOption.stream().filter(ele -> ele.getText().equals(countryMenu)).findFirst()
				.orElse(null);
		if (menuList != null) {
			menuList.click();
			waitForElementToDisappear(overlay);
		}
	}

	public void stateMenuList(String stateMenu) throws Exception {
		WebElement menuList = menuOption.stream().filter(ele -> ele.getText().contains(stateMenu)).findFirst()
				.orElse(null);
		if (menuList != null) {
			menuList.click();
			waitForElementToDisappear(overlay);
		}
	}

	public void cityMenuList(String cityMenu) throws Exception {
		WebElement menuList = menuOption.stream().filter(ele -> ele.getText().equals(cityMenu)).findFirst()
				.orElse(null);
		if (menuList != null) {
			menuList.click();
			waitForElementToDisappear(overlay);
		}
	}

	public void timeZoneMenuList(String tzoneMenu) throws Exception {
		WebElement matSelect = timeZone.stream().filter(str -> str.getText().equals("Time Zone")).findFirst()
				.orElse(null);
		if (matSelect != null) {
			matSelect.click();
			System.out.println(matSelect);
//			WebElement menuList = menuOption.stream().filter(ele -> ele.getText().equals(tzoneMenu)).findFirst()
//					.orElse(null);
//			if (menuList != null) {
//				menuList.click();
//			}
		}

	}

	public void currencyMenuList(String curncyMenu) throws Exception {
		WebElement menuList = menuOption.stream().filter(ele -> ele.getText().equals(curncyMenu)).findFirst()
				.orElse(null);
		if (menuList != null) {
			menuList.click();
		}
	}
	

	public void propertyListingBox(String propToSeach) throws Exception {
		// Search Property
		searchIcon.click();
		typeHere.sendKeys(propToSeach);
		for (WebElement prop : propNameinList) {
			String propName = prop.getText();
			System.out.println(propName);
			if(propName.contains(propToSeach)) {
				for(WebElement brand : listingBrand_propCode) {
                   System.out.println(brand.getText());
				}
			}
		}
	}

}
