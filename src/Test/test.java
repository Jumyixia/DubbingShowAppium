package Test;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import BaseClass.BaseFunc;
import BaseClass.DubbingPage;
import BaseClass.PubClass;
import BaseClass.VideoDetailPage;
import FuncTest.Dubbing;
import ObjectFactory.DriverFactory;
import io.appium.java_client.android.AndroidDriver;


public class test{
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public DriverFactory driverfactory = null;
	public PubClass pub = null;
	public Dubbing dubbing = null;
	
	@Before
	public void setUp() throws Exception{
		System.out.println("start.");

		driver = driverfactory.getAppiumDriver();
		basefunc = new BaseFunc(driver,0);
		dubbing = new Dubbing(driver,0);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver,0);
		pub = new PubClass(driver);
		//mem.content = "deleteshiping";
		//mem.start();//开始读取手机的cpu和内存	
	}	
	
	@Test
	public void Test() throws InterruptedException, ParseException{
		System.out.println("------------------start  test.");
		basefunc.enterApp();	
	
		//pub.pushFile(new File("sdcard/test.txt"));
		
		
	}
	
	@After
	public void tearDown() throws Exception{
		System.out.println("end.");
		//driver.quit();
	}

}
