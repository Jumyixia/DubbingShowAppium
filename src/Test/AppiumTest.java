package Test;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.openqa.selenium.Keys;

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
	@Test(priority = 1)
	public void Test1() throws InterruptedException, ParseException, IOException{
		System.out.println("------------------start  test.");
		//basefunc.enterApp();
		System.out.println(driver.getCapabilities());
		System.out.println("getContext"+driver.getContext());
		System.out.println(""+driver.getCapabilities().getPlatform());
				
		SystemHelper.sleep(5);
	//	basefunc.enterQuickDubbing(1);
		System.out.println(driver.currentActivity());
		//driver.closeApp();
		SystemHelper.sleep(5);
		System.out.println("-----------1");
		
		driver.runAppInBackground(3);
		System.out.println("-----------2");
		//SystemHelper.sleep(7);
		if(!driver.currentActivity().equals(".act.home.HomeActivity")){
			
			
		//	driver.getKeyboard().sendKeys("4");
			
			//driver.startActivity("com.happyteam.dubbingshow", ".act.home.HomeActivity", null, null);
			//driver.pressKeyCode(AndroidKeyCode.BACK); //需要capabilities.setCapability("unicodeKeyboard", false);
			//driver.navigate().back();
			
			/*
			 * HashMap<String, Integer> keycode = new HashMap<String, Integer>();
			keycode.put("keycode",4);
			driver.execute("mobile: keyevent", keycode);
			 */
			
			System.out.println("-----------3");
			System.out.println(driver.currentActivity());

			//sendKeyEvent(AndroidKeyCode.HOME);
		}
		
			
		
		
	//	System.out.println("----launchApp.");
		//driver.resetApp();
	//	driver.launchApp();
		//basefunc.enterApp();			
		//dubbing.testCase06();
//		Assert.assertTrue(driver.findElement(By.name("热门")).isDisplayed());
//		System.out.println(df.format(new Date()));
	}
	
	@Test(priority = 2, enabled = false)
	public void Test2() throws InterruptedException, ParseException, IOException{
		System.out.println("------------------start  test.");
		basefunc.enterQuickDubbing(1);
		//basefunc.enterApp();			
		//dubbing.testCase06();
//		Assert.assertTrue(driver.findElement(By.name("热门")).isDisplayed());
//		System.out.println(df.format(new Date()));
		
	}
	
	
	@AfterClass
	public void tearDown() throws Exception{
		System.out.println("end.");
		//mem.stopRunning();//停止cpu获取和内存
		driver.quit();
	}
	

}
