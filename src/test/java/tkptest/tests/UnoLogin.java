package tkptest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import tkptest.TestComponent.BaseTest;
import tkptest.pageObject.DashboardPage;
import tkptest.pageObject.InternalPropertySelectionPage;
import tkptest.pageObject.MegaMenuSetup;
import tkptest.pageObject.PropetySetupPage;

public class UnoLogin extends BaseTest {

	DashboardPage db;

	@Step("Test case 02")
	@Test(priority = 1)
	public void assertInvalidLogin() throws Exception {
		landingPage.loginApplication(prop.getProperty("wrongUsername"), prop.getProperty("wrongPassword"));
		Assert.assertEquals(landingPage.getErrorMessage(), "Invalid Username and Password!!");
	}

	@Step("Test case 03")
	@Test(priority = 2)
	public void assertSuccessLogin() throws Exception {
		String url = driver.getCurrentUrl();
		landingPage.resetLogin(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println(url);
		Assert.assertEquals(url, "https://uno-qa.rategain.com/login");
	}

	@Step("Test case 04")
	@Test(priority = 3)
	public void propertyListPage() throws Exception {
		String url = driver.getCurrentUrl();
		InternalPropertySelectionPage intPropSelection = new InternalPropertySelectionPage(driver);
		System.out.println(url);
		Assert.assertEquals(url, "https://uno-qa.rategain.com/intermediate-Page");
		intPropSelection.getPropertyTab(prop.getProperty("tabName"));
		intPropSelection.propertySelection(prop.getProperty("propName"));
	}

	@Step("Test case 05")
	@Test(priority = 4)
	public void dashboardPageUrl() throws Exception {
		db = new DashboardPage(driver);
		String url = db.dashBoardUrl();
		System.out.println(url);
		Assert.assertEquals(url, "https://uno-qa.rategain.com/crs-dashboard");
	}

	@Step("Test case 06")
	@Test(priority = 5)
	public void dashboardDefaultProperty() throws Exception {
		db = new DashboardPage(driver);
		String defaultProp = db.defaultProperty();
		System.out.println(defaultProp);
		Assert.assertEquals(defaultProp, prop.getProperty("propName"));
	}

	@Step("Test case 07")
	@Test(priority = 6)
	public void dashboardPageTitle() throws Exception {
		db = new DashboardPage(driver);
		String title = db.dashBoardTitle();
		System.out.println(title);
		Assert.assertEquals(title, "DASHBOARD");
	}

	@Step("Test case 08")
	@Test(priority = 7)
	public void megaMenuTitle() throws Exception {
		db = new DashboardPage(driver);
		String menuTxt = db.megaMenuOption();
		System.out.println(menuTxt);
		Assert.assertTrue(menuTxt.contains("Menu"));
	}

	@Step("Test case 09")
	@Test(priority = 8)
	public void promotionBanner() throws Exception {
		db = new DashboardPage(driver);
		String promoTxt = db.promotionFooter();
		Assert.assertEquals(promoTxt, "PROMOTIONS AND SPECIAL RATES");
	}

	@Step("Test case 10")
	@Test(priority = 9)
	public void propertySetup() throws Exception {
		MegaMenuSetup mms = new MegaMenuSetup(driver);
		mms.generalSetup("Property");
		PropetySetupPage psup = new PropetySetupPage(driver);
		psup.propertyListingCountRow();
		psup.propertyDetails(prop.getProperty("propName1"), prop.getProperty("propUnoCode"),
				prop.getProperty("propPmsCode"), prop.getProperty("connType"), prop.getProperty("propBrand"),
				prop.getProperty("nosOfRooms"), prop.getProperty("propAddr"), prop.getProperty("propCountry"),
				prop.getProperty("propState"), prop.getProperty("propCity"), prop.getProperty("pinCode"),
				prop.getProperty("phone"), prop.getProperty("email"), prop.getProperty("timeZone"),
				prop.getProperty("currency"));
		String successProp = psup.saveProperty();
		Assert.assertEquals(successProp, "Property Created Successfully");
	}

	@Step("Test case 11")
	@Test(priority = 10)
	public void propPresentOnList() throws Exception {
		PropetySetupPage psup1 = new PropetySetupPage(driver);
        psup1.propertyListingBox(prop.getProperty("propName1"));
	}

//	@Test(priority=10)
//	public void PolicySetup() throws Exception {
//		MegaMenuSetup mms = new MegaMenuSetup(driver);
//		mms.policyOptionSetup("General");
//		PolicySetupPage psp = new PolicySetupPage(driver);
//		psp.tabOption("Cancellation");
//		psp.addNewPolicy(prop.getProperty("policyType"), prop.getProperty("policyName"));
//		String polName = psp.policyList(prop.getProperty("policyName"));
//		System.out.println(polName);
//		Assert.assertEquals(polName, prop.getProperty("policyName"));		
//	}

}
