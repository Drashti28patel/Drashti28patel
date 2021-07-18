package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;

public class LoginPage {

	WebDriver driver;
/*	public WebElement emailBox = driver.findElement(By.id("usernameInput-input"));
	public WebElement passwordBox = driver.findElement(By.id("password-input"));
	public WebElement LoginButton = driver.findElement(By.id("signIn"));
	public WebElement globalError = driver.findElement(By.id("globalError"));
	public WebElement emailError = driver.findElement(By.className("Error__text"));
*/
	
	@FindBy(id ="usernameInput-input")				//it is used when want to declare some webelement as public but cz of driver.findElement() doesn't know what driver is it will fail there.
	public WebElement emailBox;
	
	@FindBy(id ="password-input")
	public WebElement passwordBox;
	
	@FindBy(id ="signIn")
	public WebElement LoginButton;
	
	@FindBy(id ="globalError")
	public WebElement globalError;
	
	@FindBy(className ="Error__text")
	public WebElement emailError;
	
	public void openBrowser() throws IOException {
		FileInputStream fi = new FileInputStream("C:\\Testing\\prop.properties");
		Properties prop = new Properties();
		prop.load(fi);
		String browser = prop.getProperty("browser");
		if(browser.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\SeleniumJars\\geckodriver.exe");
			driver = new FirefoxDriver();	
		}else {
			System.setProperty("webdriver.chrome.driver", "C:\\SeleniumJars\\chromedriver.exe");
			driver = new ChromeDriver();	
		}
		
		PageFactory.initElements(driver, this);							// when @FindBy is used, always have to add this line before webelement is called, best place to add this is in browser opening method
	}

	public void openScotiabankLoginPage() {
		driver.get("https://auth.scotiaonline.scotiabank.com/online?oauth_key=0yqPE52JNnc&oauth_key_signature=eyJraWQiOiJrUFVqdlNhT25GWUVDakpjMmV1MXJvNGxnb2VFeXJJb2tCbU1oX3BiZXNVIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJyZWZlcmVyIjoiaHR0cHM6XC9cL3d3dy5nb29nbGUuY29tXC8iLCJvYXV0aF9rZXkiOiIweXFQRTUySk5uYyIsImNvbnNlbnRfcmVxdWlyZWQiOmZhbHNlLCJyZWRpcmVjdF91cmkiOiJodHRwczpcL1wvd3d3LnNjb3RpYW9ubGluZS5zY290aWFiYW5rLmNvbVwvb25saW5lXC9sYW5kaW5nXC9vYXV0aGxhbmRpbmcuYm5zIiwiZXhwIjoxNjE1MDUzMTU2LCJpYXQiOjE2MTUwNTE5NTYsImp0aSI6IjZiZWY5MzhhLWNmMTEtNGU4OS05OTk3LTIzNDBjMmExMDNmNyIsImNsaWVudF9pZCI6IjhlZTkwYzM5LTFjNTItNGZmNC04YWU2LWE3YjU0YzUzOTkzMyIsImNsaWVudF9tZXRhZGF0YSI6eyJDaGFubmVsSUQiOiJTT0wiLCJBcHBsaWNhdGlvbkNvZGUiOiJINyJ9LCJpc3N1ZXIiOiJodHRwczpcL1wvcGFzc3BvcnQuc2NvdGlhYmFuay5jb20ifQ.M57e0QBTyGU71noPMqmx0pTd2XmNx-TDs0_yvhsY_80riY8VGJzEZeHAGQ7uCBtMWdvtS6_ZBNcAf1KxAMm4cKwcSLr_zNcwOOZHxfqHC_lvdmrFiUZUN9v7lGB0Z9W3GxtmFISZaXQZDiT_ko1jp8poOIcd5MFaeL0h6a1zxUrdZl8XR0dMo_85_5obSCTy0MpUYM-11X7Zb-DGwQrUP1pgv2oXgjDWjx-b0Md_UD5Di2MPk9o76tm3fGS8zfYms7TLFF4ccgbRYKkoh7i0ibR1x1zctCOuMvFqXl52JWbYAq6iZXvBlzz9bzkPjIyainp6QOv3krM-c4D-c3aCIg&preferred_environment=");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void closeBrowser() {
		driver.quit();
	}

	public void login(String email, String password) throws InterruptedException {
		emailBox.sendKeys(email);
		passwordBox.sendKeys(password);
		LoginButton.click();
		Thread.sleep(2000);
	}

	public String readGlobalError() {
		String actualErr = globalError.getText();
		System.out.println(actualErr);
		return actualErr;
	}

	public String readEmailError() {
		String actualError = emailError.getText();
		System.out.println(actualError);
		return actualError;
	}

}
