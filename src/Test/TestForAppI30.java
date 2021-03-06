package Test;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.BaseFunc;
import BaseClass.DetailPage;
import BaseClass.DubbingPage;
import BaseClass.PubClass;
import BaseClass.ScreenCut;
import BaseClass.VideoDetailPage;
import FuncTest.Circle;
import FuncTest.Dubbing;
import FuncTest.Login;
import FuncTest.MesCenter;
import Util.DriverFactory;
import Util.SystemHelper;
import Util.Console;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class TestForAppI30{
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public DriverFactory driverfactory = null;
	public Dubbing dubbing= null;
	public Login login = null;
	public MesCenter mescenter = null;
	public PubClass pub = null;
	public ScreenCut scut = null;
	public DetailPage detailpage = null;
	public Circle circle = null;
	
	@BeforeClass
	public void setUp() throws Exception{
		System.out.println("start.");

		driver = driverfactory.getAppiumDriver();
		basefunc = new BaseFunc(driver,0);
		dubbing = new Dubbing(driver,0);
		login = new Login(driver, 0);
		mescenter = new MesCenter(driver, 0);
		pub = new PubClass(driver);
		scut = new ScreenCut(driver);
		detailpage = new DetailPage(driver, 0);
		circle = new Circle(driver, 0);
		scut.start();
		//mem.content = "deleteshiping";
		//mem.start();//开始读取手机的cpu和内存	
	}	
	
	/*
	 * 每一个测试test需要最终将页面回到首页
	 * 在首页选择视频进入视频详情页每个视频看30秒
	 */
	@Test(priority = 1)
	public void Test1() throws InterruptedException, ParseException, IOException{
		basefunc.enterApp();
		System.out.println("------------------start  test1.");
		for (int i = 0; i <5; i++) {
			pub.swipeToUp(500);
			detailpage.viewFilmFromHome();
			basefunc.EnterHome();
		}
	}
		
	@Test(priority = 2)
	public void Test2() throws InterruptedException, ParseException, IOException{
		System.out.println("------------------start  test2.");
		dubbing.testCase06();
		basefunc.EnterHome();
		
	}
	

	@Test(priority = 3,enabled = true)
	public void Test3() throws InterruptedException, ParseException, IOException{
		System.out.println("------------------start  test3.");
		dubbing.testCase07();
		basefunc.EnterHome();
	}
	
	@Test(priority = 4,enabled = true)
	public void Test4() throws InterruptedException, ParseException, IOException{
		System.out.println("------------------start  test4.");
		dubbing.testCase08();
		basefunc.EnterHome();
	}
	
	@Test(priority = 5,enabled = true)
	public void Test5() throws InterruptedException, ParseException, IOException{
		System.out.println("------------------start  test5.");
		 circle.testCase01();
		 basefunc.EnterHome();
	}
	
	
	
	
	@AfterClass
	public void tearDown() throws Exception{
		System.out.println("end.");
		scut.stopRunning();
		//mem.stopRunning();//停止cpu获取和内存
		driver.quit();
	}
	

}
