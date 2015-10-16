package com.htaf.libraryfunctions;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Keywords {
	
	private static WebDriver driver;
	//private static Properties CONFIG;
	private static Logger logger=Logger.getLogger("Keywords");

	public String openBrowser(String data) throws IOException{

		try {
			logger.debug("Opening browser");
			if(data.equalsIgnoreCase("Mozilla") || data.equalsIgnoreCase("Firefox") || data.equalsIgnoreCase("FF")){

				logger.info("Opening  Firefox");      	
				driver=new FirefoxDriver();
			}
			else if(data.equalsIgnoreCase("IE") || data.equalsIgnoreCase("Internet Explorer") || data.equalsIgnoreCase("Explorer")) {

				File file = new File(System.getProperty("user.dir") + "\\src\\IEDriver\\IEDriver.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

				driver=new InternetExplorerDriver();
			}
			else if(data.equalsIgnoreCase("Chrome") || data.equalsIgnoreCase("Google Chrome") || data.equalsIgnoreCase("GC")) {
				logger.info("Opening Chrome Browser"); //System.getProperty("user.dir") + 
				System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir") + "\\src\\ChromeDriver\\chromedriver.exe"));
				//System.setProperty("webdriver.chrome.driver", "D:\\LSQ Functional Automation\\AutomationFramework\\src\\ChromeDriver\\chromedriver.exe");
				driver=new ChromeDriver();
				driver.manage().window().maximize();
			}
		}

		catch (Exception e) {

			logger.error("An Error Occurred: " + e.getMessage());
			return Constants.KEYWORD_FAIL;

		}
		return Constants.KEYWORD_PASS;

	}

	public String navigate(String data){
		logger.info("Navigating to URL");
		try{
			driver.navigate().to(data);
		}catch(Exception e){
			logger.error("An Error Occurred: " + e.getMessage());
			return Constants.KEYWORD_FAIL;
		}
		return Constants.KEYWORD_PASS;
	}

	public String click (String testCaseName, String objectType, String object) {
		logger.info("CLicking on the object specified");

		try {
			if (objectType.equalsIgnoreCase("XPATH")) {
				driver.findElement(By.xpath(object)).click();
			}

			else if (objectType.equalsIgnoreCase("ID"))  {
				driver.findElement(By.id(object)).click();
			}

			else if (objectType.equalsIgnoreCase("CSS")) {
				driver.findElement(By.cssSelector(object)).click();
			}

			else if (objectType.equalsIgnoreCase("NAME")) {
				driver.findElement(By.name(object)).click();
			}
			else if (objectType.equalsIgnoreCase("LINK")) {
				driver.findElement(By.linkText(object)).click();
			}
			else if (objectType.equalsIgnoreCase("CLASS")) {
				driver.findElement(By.className(object)).click();
			}
			else if (objectType.equalsIgnoreCase("TAG")) {
				driver.findElement(By.tagName(object)).click();
			}
			else if (objectType.equalsIgnoreCase("PARTIAL LINK")) {
				driver.findElement(By.partialLinkText(object)).click();
			}
			else {

				ScreenShotOnFailure.getScreenshot(testCaseName, driver);
				logger.error("The click element is not found or the object type is incorrect");
				return Constants.KEYWORD_FAIL;
			}

		}catch (Exception e) {

			ScreenShotOnFailure.getScreenshot(testCaseName, driver);
			logger.error("An Error Occurred: " + e.getMessage());
			return Constants.KEYWORD_FAIL;
		}

		return Constants.KEYWORD_PASS;
	}

	public String setText (String testCaseName, String objectType, String object, String data) {
		logger.info("CLicking on the object specified");

		try {
			if (objectType.equalsIgnoreCase("XPATH")) {
				driver.findElement(By.xpath(object)).clear();
				driver.findElement(By.xpath(object)).sendKeys(data);
			}

			else if (objectType.equalsIgnoreCase("ID"))  {
				driver.findElement(By.id(object)).clear();
				driver.findElement(By.id(object)).sendKeys(data);
			}

			else if (objectType.equalsIgnoreCase("NAME"))  {
				driver.findElement(By.name(object)).clear();
				driver.findElement(By.name(object)).sendKeys(data);
			}
			else if (objectType.equalsIgnoreCase("CSS"))  {
				driver.findElement(By.cssSelector(object)).clear();
				driver.findElement(By.cssSelector(object)).sendKeys(data);
			}
			else {
				ScreenShotOnFailure.getScreenshot(testCaseName, driver);
				logger.error("The Input box is not found or the object type is incorrect");
				return Constants.KEYWORD_FAIL;
			}
		}catch (Exception e) {
			ScreenShotOnFailure.getScreenshot(testCaseName, driver);
			logger.error("An Error Occurred: " + e.getMessage());
			return Constants.KEYWORD_FAIL;
		}

		return Constants.KEYWORD_PASS;
	}

	public String verifyLinkText(String testCaseName, String objectType, String object, String data){
		logger.info("Verifying link Text");

		String actual= null;

		try{
			if (objectType.equalsIgnoreCase("XPATH")) {
				actual=driver.findElement(By.xpath(object)).getText();

			}
			else if (objectType.equalsIgnoreCase("ID")) {
				actual=driver.findElement(By.id(object)).getText();

			}
			else if (objectType.equalsIgnoreCase("NAME")) {
				actual=driver.findElement(By.name(object)).getText();

			}
			else if (objectType.equalsIgnoreCase("CSS")) {
				actual=driver.findElement(By.cssSelector(object)).getText();

			}
			String expected=data;

			if(actual.equals(expected))
				return Constants.KEYWORD_PASS;
			else
				ScreenShotOnFailure.getScreenshot(testCaseName, driver);
			logger.error("The link sent is not available or the object type is not passed correctly");
			return Constants.KEYWORD_FAIL+" -- Link text not verified";
		}catch(Exception e){

			ScreenShotOnFailure.getScreenshot(testCaseName, driver);
			logger.error("An Error Occurred: " + e.getMessage());
			return Constants.KEYWORD_FAIL+" -- Link text not verified"+e.getMessage();

		}

	}

	public String isTextPresent (String testCaseName, String objectType, String object, String text) {
		
		 try
         {
			 if(driver.findElement(By.xpath(object)).getText().contains(text))
				 return Constants.KEYWORD_PASS;
			else
				 return Constants.KEYWORD_FAIL;
         }
         catch (Exception e)
         {
 			 ScreenShotOnFailure.getScreenshot(testCaseName, driver);
             logger.error("An error orrcured: " + e.getMessage());
             return Constants.KEYWORD_FAIL;
         }

	}

	public String pause(String testCaseName, String data){
		try {
			logger.info("Pause function executed");
			long time = (long) Double.parseDouble(data);
			Thread.sleep(time*1000L);
			return Constants.KEYWORD_PASS;
		}
		catch (Exception e) {

			ScreenShotOnFailure.getScreenshot(testCaseName, driver);
			logger.error("NumberFormatException: " + e.getMessage());
			return Constants.KEYWORD_FAIL;
		}

	}

	public String quitBrowser(){
		logger.info("Closing the browser");
		try{
			driver.quit();
		}catch(Exception e){
			logger.error("NumberFormatException: " + e.getMessage());
			return Constants.KEYWORD_FAIL;
		}
		return Constants.KEYWORD_PASS;
	}

	public String waitForElement(WebElement element){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch (Exception e) {
			return Constants.KEYWORD_FAIL+"The wait for element function failed to execute"+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;


	}

	public String clickLink(String testCaseName, String objectType, String object){
		logger.info("Clicking on link ");
		try{
			driver.findElement(By.xpath(object)).click();
		}catch(Exception e){
			ScreenShotOnFailure.getScreenshot(testCaseName, driver);
			logger.error("The link element to click was not found or the object type is incorrect");
			return Constants.KEYWORD_FAIL;
		}

		return Constants.KEYWORD_PASS;
	}

}
