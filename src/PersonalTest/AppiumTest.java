
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
import Util.DriverFactory;
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
		basefunc = new BaseFunc(driver,1);
		dubbing = new Dubbing(driver,0);		
		//mem.content = "deleteshiping";
		//mem.start();//开始读取手机的cpu和内存	
	}	
	
	/*
	 * 每一个测试test需要最终将页面回到首页
	 */
	@Test(priority = 1)
	public void Test1() throws InterruptedException, ParseException, IOException{
		System.out.println("------------------start  test.");
		
		
		//basefunc.enterApp();	
		System.out.println(driver.isAppInstalled("com.tencent.mobileqq"));
		driver.installApp(System.getProperty("user.dir") + "/apps/mobileqq.apk");
	//	driver.removeApp("com.tencent.mobileqq");
	}
	
	@Test(enabled = false)
	public void test2(){
		
	}
	@AfterClass
	public void tearDown() throws Exception{
		System.out.println("end.");
		//mem.stopRunning();//停止cpu获取和内存
		driver.quit();
	}

}

