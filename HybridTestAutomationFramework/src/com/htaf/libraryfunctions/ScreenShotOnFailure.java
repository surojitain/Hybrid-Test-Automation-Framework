package com.htaf.libraryfunctions;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotOnFailure {
	
	public static void getScreenshot(String testCaseName, WebDriver driver) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ss aa");
		String destDir = System.getProperty("user.dir") + "/src/Screenshots";
		new File(destDir).mkdirs();
		String destFile = testCaseName + " (" + dateFormat.format(new Date()) + ").png";

        try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Reporter.setEscapeHtml(false);
		//Reporter.log("Saved <a href=../screenshots/" + destFile + ">Screenshot</a>");
	}

}
