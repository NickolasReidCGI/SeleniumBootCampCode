package com.one.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DemoQAParameterizedValues {
	
	static WebDriver driver;
	
	static String[] excelSheetNames = { "Positive Test Case","N TC - bad password","Switch Tab"}; 
	
	static ArrayList<String> tabs;	public static void main(String args[]) throws IOException, InterruptedException{
		
		init();
		readExcel(excelSheetNames[1]);
		close();
		
	}
	
	public static void init(){
		System.setProperty("webdriver.chrome.driver", "Binaries\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demoqa.com");
		tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.manage().window().maximize();
		//Find the clickable UNIQUE button named REGISTER and store it in a variable.
		WebElement register =  driver.findElement(By.linkText("Registration"));
		//Click the button to move to the registration screen.
		register.click();
	}
	
	
	public static void close() throws IOException, InterruptedException{
		//Tell Selenium to take screenshot
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);				
		//Save the file to your computer
		FileUtils.copyFile(scrFile, new File("ScreenShot.jpeg"));
		// Sleep just so it doesn't close so fast.
		Thread.sleep(2000L);	
		driver.quit();
	}
	
	public static void createAndFindElement(String type, String location, String content) throws IOException{
		if(content.equals("click")){
			switch(type){
				case "name" :
					driver.findElement(By.name(location)).click();
					break;
				case "xpath" :
					driver.findElement(By.xpath(location)).click();
					break;		
				case "id" :
					driver.findElement(By.id(location)).click();
					break;
				default:
					System.out.println("Something went wrong");
					break;
				}
		}else{
			switch(type){
				case "name" :
					driver.findElement(By.name(location)).sendKeys(content);
					break;
				case "xpath" :
					driver.findElement(By.xpath(location)).sendKeys(content);
					break;		
				case "dropdown" :
					Select dropDownElement = new Select(driver.findElement(By.name(location)));
					dropDownElement.selectByValue(content);
					break;
				case "id" :
					driver.findElement(By.id(location)).sendKeys(content);
					break;
				case "screenshot" :
					//Tell Selenium to take screenshot
					File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);				
					//Save the file to your computer
					FileUtils.copyFile(scrFile, new File(content + ".jpeg"));
					break;
				case "newtab" :
					((JavascriptExecutor)driver).executeScript("window.open();");
					
					break;
				case "tab" :
					 driver.switchTo().window(tabs.get(1)); //switches to new tab
					 driver.get("https://reddit.com");
					 break;
				default:
					System.out.println("Something went wrong");
					break;
				}
		}
	}
	
	
	
	public static void readExcel(String excelSheet) throws IOException{
        String location = null;
        String content = null;
        String type = null;
		Workbook workbook = null;
		int counter = 0;
		 int counterRow = 0;
		 try {
	            FileInputStream excelFile = new FileInputStream(new File("C:\\Users\\nickolas.reid\\workspace\\SeleniumProjectsForCourse\\Keywords\\InputSheet.xlsx"));
	            workbook = new XSSFWorkbook(excelFile);
	            Sheet datatypeSheet = workbook.getSheet(excelSheet);
	            
	            Iterator<Row> iterator = datatypeSheet.iterator();
	            counterRow = 0;
	            while (iterator.hasNext()) {	
	                Row currentRow = iterator.next();
	                Iterator<Cell> cellIterator = currentRow.iterator();
	                counter = 1;
	                while (cellIterator.hasNext() && counterRow != 0) {
	                    Cell currentCell = cellIterator.next();
	                    switch (counter){
		                    case 2:
		                    	//Grab the way we'll select an element on a page
		                    	type = currentCell.toString();
		                    	break;
		                    case 3:
		                    	// Grab the location of the element on the page
		                    	location = currentCell.toString();
		                    	break;
		                    case 4:
		                    	//Grab the content we're looking 
		                    	content = currentCell.toString();	
		                    	//IF the content is a number such as 8. Then remove decimals.
		                    	content = content.replaceAll("\\.0*$", "");
	                    		// Call method to do the selenium work.
		                    	createAndFindElement(type,location,content);
		                    	break;
	                    }
	                    counter++;	                   
	                }
	                counterRow++;	             
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	        	workbook.close();
	        }	
	}
	
	
	
}
