package tkptest.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import tkptest.TestComponent.BaseTest;


public class InValidLogin extends BaseTest{

	@Test
	public void assertSuccesLogin() throws InterruptedException{

		landingPage.loginApplication(prop.getProperty("wrongUsername"), prop.getProperty("wrongPassword"));
//		Thread.sleep(1000);
		String error = landingPage.getErrorMessage();
		Assert.assertEquals(landingPage.getErrorMessage(), "Invalid Username and Password!!");
        System.out.print(error);

	}



}
