package tkptest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import tkptest.TestComponent.BaseTest;
import tkptest.pageObject.Dashboard;
import tkptest.pageObject.InternalPropertySelectionPage;
import tkptest.pageObject.MegaMenuSetup;

public class UnoLogin extends BaseTest {

	Dashboard db;
    
	
	@Test(priority=1)
	public void assertInvalidLogin() throws Exception {
		landingPage.loginApplication(prop.getProperty("wrongUsername"), prop.getProperty("wrongPassword"));
		Assert.assertEquals(landingPage.getErrorMessage(), "Invalid Username and Password!!");
	}
	
	@Test(priority=2)
	public void assertSuccessLogin() throws Exception{
		String url = driver.getCurrentUrl();
		landingPage.resetLogin(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println(url);
		Assert.assertEquals(url, "https://uno-qa.rategain.com/login");
	}

	@Test(priority=3)
	public void propertyListPage() throws Exception {
		String url = driver.getCurrentUrl();
		InternalPropertySelectionPage intPropSelection = new InternalPropertySelectionPage(driver);
		System.out.println(url);
		Assert.assertEquals(url, "https://uno-qa.rategain.com/intermediate-Page");
		intPropSelection.getPropertyTab(prop.getProperty("tabName"));
		intPropSelection.propertySelection(prop.getProperty("propName"));     
	}
	
	@Test (priority=4)
	public void dashboardPageUrl() throws Exception {
		 db = new Dashboard(driver);
		 String url = db.dashBoardUrl();
		 System.out.println(url);
		 Assert.assertEquals(url, "https://uno-qa.rategain.com/crs-dashboard");
	}
	
	@Test(priority = 5)
	public void dashboardDefaultProperty() throws Exception {
		db = new Dashboard(driver);
		String defaultProp= db.defaultProperty();
	    System.out.println(defaultProp);
	    Assert.assertEquals(defaultProp, prop.getProperty("propName"));
	}
	
	@Test(priority = 6)
	public void dashboardPageTitle() throws Exception {
		db = new Dashboard(driver);
		String title = db.dashBoardTitle();
		System.out.println(title);
		Assert.assertEquals(title, "DASHBOARD");
	}
	
	@Test(priority = 7)
	public void megaMenuTitle() throws Exception {
		db = new Dashboard(driver);
		String menuTxt= db.megaMenuOption();
		System.out.println(menuTxt);
		Assert.assertTrue(menuTxt.contains("Menu"));
	}
	
	@Test(priority = 8)
	public void promotionBanner() throws Exception{
		db = new Dashboard(driver);
		String promoTxt = db.promotionFooter();
		Assert.assertEquals(promoTxt, "PROMOTIONS AND SPECIAL RATES");
	}
	
	@Test(priority = 9)
	public void RoomSetup() throws Exception {
		MegaMenuSetup mms = new MegaMenuSetup(driver);
		mms.policySetup("General");
		
	}
	
}
