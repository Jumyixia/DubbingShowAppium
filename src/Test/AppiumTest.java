package Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BaseClass.BaseFunc;
import BaseClass.DubbingPage;
import BaseClass.VideoDetailPage;
import FuncTest.Dubbing;
import ObjectFactory.DriverFactory;
import Util.SystemHelper;
import io.appium.java_client.android.AndroidDriver;


public class AppiumTest{
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public DriverFactory driverfactory = null;
	public Dubbing dubbing= null;
	
	@Before
	public void setUp() throws Exception{
		System.out.println("start.");

		driver = driverfactory.getAppiumDriver();
		basefunc = new BaseFunc(driver);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver);
		dubbing = new Dubbing(driver);
		//mem.content = "deleteshiping";
		//mem.start();//��ʼ��ȡ�ֻ���cpu���ڴ�	
	}	
	
	@Test
	public void Test() throws InterruptedException, ParseException{
		System.out.println("------------------start  test.");
		basefunc.enterApp();	
		//	dubbing.testCase02();
		dubbing.testCase03(20);
//		Assert.assertTrue(driver.findElement(By.name("����")).isDisplayed());
//		System.out.println(df.format(new Date()));
	}
	
	@After
	public void tearDown() throws Exception{
		System.out.println("end.");
		//mem.stopRunning();//ֹͣcpu��ȡ���ڴ�
		//driver.quit();
	}
	

}
