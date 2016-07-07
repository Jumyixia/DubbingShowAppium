
package PersonalTest;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.BaseFunc;
import BaseClass.DubbingPage;
import BaseClass.VideoDetailPage;
import FuncTest.Dubbing;
import ObjectFactory.DriverFactory;
import Util.SystemHelper;
import Util.Console;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class AppiumTest{
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public DriverFactory driverfactory = null;
	public Dubbing dubbing= null;
	
	@BeforeClass
	public void setUp() throws Exception{
		System.out.println("start.");

		driver = driverfactory.getAppiumDriver();
		basefunc = new BaseFunc(driver,0);
		dubbing = new Dubbing(driver,0);		
		//mem.content = "deleteshiping";
		//mem.start();//开始读取手机的cpu和内存	
	}	
	
	/*
	 * 每一个测试test需要最终将页面回到首页
	 */
	@Test(priority = 1,enabled = false)
	public void Test1() throws InterruptedException, ParseException, IOException{
		System.out.println("------------------start  test.");
		//By by_title = By.name("个人中心");
		//wait for 60s if WebElemnt show up less than 60s , then return , until 60s
		System.out.println(df.format(new Date()));
		AndroidDriverWait wait = new AndroidDriverWait(driver, 4);
		try {
			WebElement showClose = wait.until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(AndroidDriver d) {
					return d.findElement(By.name("个人中心"));
				}
			});
			System.out.println(df.format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	/*
	WebDriverWait wait = new WebDriverWait(driver, 60);
	
    WebElement e= wait.until(new  ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(by_title);
            }
        });
*/
	
	@Test()
	public void test2(){
		driver.findElementByName("我的").click();
		System.out.println(this.isElementExist(By.name("个人中心"),5));
		driver.findElementByName("消息").click();
		System.out.println(this.isElementExist(By.name("个人中心"),5));
	}
	@AfterClass
	public void tearDown() throws Exception{
		System.out.println("end.");
		//mem.stopRunning();//停止cpu获取和内存
		driver.quit();
	}

	public boolean isElementExist(int timeout){
		try{
			new AndroidDriverWait(driver, 4).until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(AndroidDriver d) {
					return d.findElement(By.name("个人中心"));
				}});
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	
	public boolean isElementExist(By by,int timeout){
		try{
			new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(by));
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	

}

