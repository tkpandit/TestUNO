package tkptest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import tkptest.TestComponent.BaseTest;
import tkptest.pageObject.Dashboard;
import tkptest.pageObject.InternalPropertySelectionPage;
import tkptest.pageObject.PolicySetup;

public class UnoLogin extends BaseTest {

	Dashboard db;
    
	
	@Test(priority=1)
	public void assertSuccesLogin() throws Exception {
		landingPage.loginApplication(prop.getProperty("wrongUsername"), prop.getProperty("wrongPassword"));
		Assert.assertEquals(landingPage.getErrorMessage(), "Invalid Username and Password!!");	
		
		landingPage.resetLogin(prop.getProperty("username"), prop.getProperty("password"));
		String url = driver.getCurrentUrl();
		System.out.println(url);
		Assert.assertEquals(url, "https://uno-qa.rategain.com/intermediate-Page");
	}

	@Test(priority=2)
	public void propertyListPage() throws Exception {
		
		InternalPropertySelectionPage intPropSelection = new InternalPropertySelectionPage(driver);
		intPropSelection.getPropertyTab(prop.getProperty("tabName"));
		intPropSelection.propertySelection(prop.getProperty("propName"));
		db = new Dashboard(driver);
		String dProp= db.defaultProperty();
		System.out.println(dProp);
		Assert.assertEquals(dProp, prop.getProperty("propName"));
	}

	
	@Test (priority=3)
	public void dashboardPage() throws Exception {
		
		 db = new Dashboard(driver);
		 String menuTxt = db.megaMenuOption();
		 System.out.println(menuTxt);
		 
	}
	
	@Test(priority=4)
	public void promotionBanner() throws Exception{
		db = new Dashboard(driver);
		String promoTxt = db.promotionFooter();
		Assert.assertEquals(promoTxt, "PROMOTIONS AND SPECIAL RATES");
	}
	
	@Test(priority=5)
	public void masterOptionList() throws Exception {
		
		PolicySetup polSetup = new PolicySetup(driver);
		polSetup.policySetup();
	}
	
}
