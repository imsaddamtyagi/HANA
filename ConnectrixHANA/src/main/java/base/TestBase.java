package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

 public class TestBase 
{
	
	
	public static AndroidDriver<WebElement> driver;
	public static Properties prop;
	

	
	public TestBase()
	{		
		try
		{					
			prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
			prop.load(ip);		
		} catch(FileNotFoundException e) {e.printStackTrace();
		} catch(IOException e){e.printStackTrace();
		}}
	
	public static void initialization() throws InterruptedException, MalformedURLException
	{
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");
		caps.setCapability("autoAcceptAlerts", true);
		caps.setCapability(MobileCapabilityType.APP,prop.getProperty("ApkPath")); // Apk Path
				
		URL url = new URL("http:0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<WebElement>(url,caps);
		System.out.println("Launching Application with Apk version: "+prop.getProperty("ApkPath"));
		System.out.println("Using Test Data Excel Sheet: "+prop.getProperty("ExcelName"));
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(3000);	
		
		  WebDriverWait wait = new WebDriverWait(driver,40);
		  wait.until(ExpectedConditions.alertIsPresent()); 
		  Alert alert =  driver.switchTo().alert(); alert.accept();
		 
        Thread.sleep(2000);	
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_mobile")).sendKeys(prop.getProperty("username"));
		System.out.println("User mobile number to Login: "+prop.getProperty("username"));
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/et_password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.id("com.uneecops.sapsalesapp:id/btn_login")).click();
		Thread.sleep(20000);
		System.out.println("Login Successfull");
		  WebDriverWait wait1 = new WebDriverWait(driver,40);
		  wait1.until(ExpectedConditions.alertIsPresent()); 
		  Alert alert1 = driver.switchTo().alert(); alert1.accept();
		 
 		Thread.sleep(3000);
 		
 		System.out.println("Switching to HANA Branch...");
 		driver.findElement(By.id("com.uneecops.sapsalesapp:id/iv_default_profile")).click();
 		Thread.sleep(500);
 		driver.findElement(By.id("com.uneecops.sapsalesapp:id/switch_account")).click();
 		Thread.sleep(500);
 		System.out.println("Switching from available accounts...");
	 //	driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Hana_Branch_Test\")"))).click();
    //	Thread.sleep(500);
   //	System.out.println("HANA Branch Selected");
		
	String Branch=	driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Stage_Hana_Branch_Test\")"))).getText();
	 driver.findElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"Stage_Hana_Branch_Test\")"))).click();

	Thread.sleep(2000); // wait to move to Dashboard section
		System.out.println(Branch+" Selected");
 		
 	
 		
		//         Application update.
	//	  WebElement element = (new WebDriverWait(driver, 20))
	//	  .until(ExpectedConditions.elementToBeClickable(By.id(
	//	  "com.uneecops.sapsalesapp:id/btnCancel"))); element.click(); // Cancel button
		 
		 		
 	

		
	}
	
	

}
