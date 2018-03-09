package com.one.project;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoQAHardCoded {
	
	static String textForDesc = "The European languages are members of the same family. Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and their most common words." + 
"Everyone realizes why a new common language would be desirable: one could refuse to pay expensive translators. To achieve this, it would be necessary to have uniform grammar, pronunciation and more common words." +
"If several languages coalesce, the grammar of the resulting language is more simple and regular than that of the individual languages. The new common language will be more simple and regular than the existing European languages. It will be as simple as Occidental; in fact, it will be Occidental. " +
"To an English person, it will seem like simplified English, as a skeptical Cambridge friend of mine told me what Occidental is. The European languages are members of the same family. Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and their most common words. Everyone realizes why a new common language would be desirable: one could refuse to pay expensive translators. To";
	
	static String password = "ab**45$$io&^%plm,.//.,abcde";
	static int randUserName = new Random().nextInt(100) * 579;
	
	static String emailAdressText = "so.one@cgi.com";
	
	public static void main(String args[]) throws InterruptedException, IOException{
		
		//Hard Link
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\nickolas.reid\\workspace\\SeleniumProjectsForCourse\\Binaries\\chromedriver.exe");
		//Relative Path
		System.setProperty("webdriver.chrome.driver", "Binaries\\chromedriver.exe");
		WebDriver driver;
		driver = new ChromeDriver();
		//Open browser and go to newtours.demoaut.com
		driver.get("http://demoqa.com");
		driver.manage().window().maximize();
		
		
		//Find the clickable UNIQUE button named REGISTER and store it in a variable.
		WebElement register =  driver.findElement(By.linkText("Registration"));
		//Click the button to move to the registration screen.
		register.click();
		//Find the firstnName element and store it into a variable
		WebElement firstName = driver.findElement(By.name("first_name"));
		//Enter the persons first name
		firstName.sendKeys("Some");
		//Find the last name element and store it into a variable using XPath indecies
		WebElement lastName = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
		//Enter the persons last name
		lastName.sendKeys("One");
		//check off martial status
		WebElement martialStatus = driver.findElement(By.name("radio_4[]"));
		martialStatus.click();
		//Check off check box 
		driver.findElement(By.xpath("//input[@value='dance']")).click();
		driver.findElement(By.xpath("//input[@value='reading']")).click();
		//Select country element 
		Select dropDownCountry = new Select(driver.findElement(By.name("dropdown_7")));
		//enter country
		dropDownCountry.selectByValue("Canada");		
		//select birth month element
		Select dropDownMonth = new Select(driver.findElement(By.name("date_8[date][mm]")));
		//enter birth month
		dropDownMonth.selectByIndex(4);
		//Select birth day element
		Select dropDownDay = new Select(driver.findElement(By.name("date_8[date][dd]")));
		//Enter birth day
		dropDownDay.selectByIndex(8);
		//select birth year element
		Select dropDownYear = new Select(driver.findElement(By.name("date_8[date][yy]")));
		//Enter birth year
		dropDownYear.selectByValue("1978");
		//Find the phone element and store it into a variable using XPath
		WebElement phone = driver.findElement(By.xpath("(.//input[@name='phone_9'])"));
		//Enter the persons phone number that's wrong
		phone.sendKeys("123456");
		//clear out the textbox
		phone.clear();
		//Enter a proper phone number 
		phone.sendKeys("19024933500");	
		//Select username
		WebElement username = driver.findElement(By.name("username"));
		//Send the username
		username.sendKeys(Integer.toString(randUserName));	
		//Select email by ID
		WebElement email = driver.findElement(By.name("e_mail"));
		//Send email address
		email.sendKeys(emailAdressText);
		//Select the description text box
		WebElement description = driver.findElement(By.id("description"));
		//Send the description text
		description.sendKeys(textForDesc);				
		//Select the password box
		WebElement passwordInit = driver.findElement(By.name("password"));
		//send the password
		passwordInit.sendKeys(password);				
		//Grab the password confirm box and store in Variable.
		WebElement passwordConfirm = driver.findElement(By.id("confirm_password_password_2"));
		//Send the password confirmation
		passwordConfirm.sendKeys(password);				
		
		//Click confirm button
		WebElement confirmButton = driver.findElement(By.xpath("//input[@value='Submit']"));
		confirmButton.click();
		
		//Tell Selenium to take screenshot
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		
		//Save the file to your computer
		FileUtils.copyFile(scrFile, new File("ScreenShot.jpeg"));
		
		// Sleep just so it doesn't close so fast.
		Thread.sleep(2000L);		
		//Close the web browser
		driver.quit();
		
	}

}
