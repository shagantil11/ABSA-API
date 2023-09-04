package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Constant.constant;

import Constant.constant;

public class Screenshots {
	
	 public  static String captureScreenShots(WebDriver driver, String screenShotName) {

	        try {

	         
	            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	            TakesScreenshot ts = (TakesScreenshot) driver;
	            File source = ts.getScreenshotAs(OutputType.FILE);
		        String destination = constant.TAKE_SCREEN_SHOT +screenShotName+dateName+".png";
	            File finalDestination = new File(destination);
	            FileUtils.copyFile(source, finalDestination);
	            return destination;

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return "false";
	    }

}