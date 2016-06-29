package Test;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;



import org.junit.Before;
import org.junit.FixMethodOrder;

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

public class AppiumTest{
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	
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
		//mem.start();//��ʼ��ȡ�ֻ���cpu���ڴ�	
	}	
	
	/*
	 * ÿһ������test��Ҫ���ս�ҳ��ص���ҳ
	 */
	@Test(priority = 1)
	public void Test1() throws InterruptedException, ParseException, IOException{
		System.out.println("------------------start  test.");
		basefunc.enterApp();			
	//	basefunc.enterQuickDubbing(1);
		System.out.println(driver.currentActivity());
		//driver.closeApp();
		Thread.sleep(5);
		System.out.println(driver.currentActivity());
		System.out.println("----closeApp.");
		driver.startActivity("com.fangyanshow.dialectshow", ".ui.StartActivity");
		
		
	//	System.out.println("----launchApp.");
		//driver.resetApp();
	//	driver.launchApp();
		//basefunc.enterApp();			
		//dubbing.testCase06();
//		Assert.assertTrue(driver.findElement(By.name("����")).isDisplayed());
//		System.out.println(df.format(new Date()));
	}
	
	@Test(priority = 2, enabled = false)
	public void Test2() throws InterruptedException, ParseException, IOException{
		System.out.println("------------------start  test.");
		basefunc.enterQuickDubbing(1);
		//basefunc.enterApp();			
		//dubbing.testCase06();
//		Assert.assertTrue(driver.findElement(By.name("����")).isDisplayed());
//		System.out.println(df.format(new Date()));
	}
	
	
	@AfterClass
	public void tearDown() throws Exception{
		System.out.println("end.");
		//mem.stopRunning();//ֹͣcpu��ȡ���ڴ�
		//driver.quit();
	}
	

}
