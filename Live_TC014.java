package com.cj.ui;

import static org.junit.Assert.*;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cj.util.SmartProperties;

/**
 * 
 * @author JungHyunLee 
 * Date : 2016-02-22
 * Subject : CJ Mall 운영 
 * Name : TC_014
 * Scenario : 검색어 입력시 검색 결과 페이지로 이동 확인
 * Assertion : "~" 검색결과 Text 체크
 *
 */

public class Live_TC014 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	private String userId = null;
	private String passwd = null;
	private String browser = null;
	private long waitTime = 50;
	private String videoPath = null;
	private String testpasswd = null;
	private String word_tc014_01 = null;
	private String word_tc051_01 = null;
	private String word_tc051_02 = null;
	private String word_tc052_01 = null;
	private String word_tc098_01 = null;
	private String word_tc098_02 = null;
		

	@Before
	public void setUp() throws Exception {
		String path = System.getProperty("user.dir"); // current path of project
		
		SmartProperties sp = SmartProperties.getInstance();
		userId = sp.getProperty("ID");
		passwd = sp.getProperty("PWD");
		waitTime = Long.parseLong(sp.getProperty("WaitTime"));
		videoPath = sp.getProperty("VIDEO_LOC");
		browser = sp.getProperty("Browser");
		word_tc014_01 = sp.getProperty("Word_TC014_01");
		word_tc051_01 = sp.getProperty("Word_TC051_01");
		word_tc051_02 = sp.getProperty("Word_TC051_02");
		word_tc052_01 = sp.getProperty("Word_TC052_01");
		word_tc098_01 = sp.getProperty("Word_TC098_01");
		word_tc098_02 = sp.getProperty("Word_TC098_02");
		testpasswd = sp.getProperty("TESTPWD");

		if (browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
			}
			
		else {
			System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
			//driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			driver = new ChromeDriver(options);
		}
		sp.list(System.out);

		System.out.println("userId 	= " + userId);
		System.out.println("passwd 	= " + passwd);
		System.out.println("waitTime=" + waitTime);
		System.out.println("videoPath = " + videoPath);
		System.out.println("word_tc014_01 = " + word_tc014_01);
		System.out.println("word_tc051_01 = " + word_tc051_01);
		System.out.println("word_tc051_02 = " + word_tc051_02);
		System.out.println("testpasswd=" + testpasswd);

		baseUrl = "http://www.cjmall.com/";
		driver.manage().window().maximize();
	}	

	@Test
	public void TC014() throws Exception {
		try{
			WebDriverWait wait = null;
			
			driver.manage().window().maximize();

		    int window_num = 0;
		    String mainWindow = driver.getWindowHandle();
		    System.out.println("main Windows ="+mainWindow);

		    Set<String> handles = driver.getWindowHandles();
		    window_num = handles.size();

		    System.out.println("Windows Num ="+window_num);

		    for (String handle : handles) {
		    	System.out.println("windows handles :"+handle);
		    	if (!handle.equals(mainWindow)){
		    		driver.switchTo().window(handle);
		    		System.out.println("Switch Windows");
		    		break;
		    		}
		    	}
			
			// 메인 페이지 요청
			driver.get(baseUrl + "/index_tab1.jsp");
			System.out.println("메인 페이지 요청");
			
			// 메인 페이지 뜨고, 로그인 객체가 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[1]/a/span")));
			System.out.println("메인 페이지 뜨고, 로그인 객체가 뜰 때까지 기다림");
			
			if(window_num > 0){
			Set<String> allWindows1 = driver.getWindowHandles();
		    for(String curWindow1 : allWindows1){
		        driver.switchTo().window(curWindow1);
		    }
	  		Point hoverItem = driver.findElement(By.xpath(".//*[@id='pClose']")).getLocation();
	  		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(hoverItem.getY())+");");
		  	System.out.println("테스트");
		  	
		  	Thread.sleep(3000);
		  			  	
		    //driver.close();
		  	driver.findElement(By.xpath(".//*[@id='pClose']")).click();
		    Thread.sleep(3000);

		    Set<String> allWindows0 = driver.getWindowHandles();
		    for(String curWindow0 : allWindows0){
		        driver.switchTo().window(curWindow0);
		    }
		    
		    Thread.sleep(3000);
			}
			
			// 검색창에 검색어 입력 후 검색
			boolean isExist = existElement(driver, By.xpath(".//*[@id='srch_disp_value']"), "검색창");
			if (isExist) {
				// 검색창 클릭
				driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).click();
				driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).clear();
				
				// 검색어 "노트북" 입력
				driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).sendKeys(word_tc014_01); 
				driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/form/fieldset/div/button")).click();
				System.out.println("'노트북' 검색" );
				
				// 검색결과 페이지 뜰 때까지 기다림
				wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='search_result_text']/strong/a")));
				System.out.println("검색결과 페이지 뜰 때까지 기다림");
				
				// "~" 검색결과 Text 체크
				boolean isExist2 = existElement(driver, By.xpath(".//*[@id='srch_disp_value']"), "검색결과창");
				if(isExist2 && !"".equals(driver.findElement(By.xpath(".//*[@id='search_result_text']")).getText())){
					System.out.println("[TC_014] success");
					assertTrue(true);
					return;
				} else {
					System.out.println("[TC_014] failure : 검색결과 값이 null...");
					assertTrue(false);
				}
			} else {
				System.out.println("[TC_014] failure : 검색창 찾을 수 없음.");
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_014] failure");
			assertTrue(false);
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();

			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	public boolean existElement(WebDriver wd, By by, String meaning) {
		WebDriverWait wait = new WebDriverWait(wd, 2);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (TimeoutException e) {

			System.out.println("[" + meaning + "] WebElement does not Exist. time out ");
			return false;
		}
		System.out.println("[" + meaning + "] WebElement Exist.");
		return true;
	}

}
