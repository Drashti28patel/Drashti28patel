package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import data.DataFile;
import pages.LoginPage;
import utilities.Xls_Reader;

public class LoginTest {
	LoginPage lp = new LoginPage();
	DataFile df = new DataFile();

	@BeforeMethod
	public void beforeMethod() throws IOException  {
		lp.openBrowser();
		lp.openScotiabankLoginPage();
	}

	@AfterMethod
	public void afterMethod() {
		lp.closeBrowser();
	}

	@Test
	public void verifyWrongPasswordTest() throws InterruptedException {
		lp.login(df.wrongEmail, df.wrongPassword);
		Assert.assertEquals(lp.readGlobalError(), df.globalErr);
	}

	@Test
	public void verifyInvalidEmailTest() throws InterruptedException {
		lp.login(df.emailWithSpecialChar,df.wrongPassword);
		Assert.assertEquals(lp.readEmailError(), df.specialCharErr);
	}

	@Test
	public void verifyEmptyEmailTest() throws InterruptedException {
		lp.login("",df.wrongPassword );
		Assert.assertEquals(lp.readEmailError(),df.emptyEmailErr );
	}
}