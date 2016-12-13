package Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.BaseFunc;
import BaseClass.CirclePage;
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
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class AppiumTest {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage = null;
	public DriverFactory driverfactory = null;
	public Dubbing dubbing = null;
	public Login login = null;
	public MesCenter mescenter = null;
	public PubClass pub = null;
	public ScreenCut scut = null;
	public DetailPage detailpage = null;
	public CirclePage circlepage = null;
	public Circle circle = null;
	public Console cs = null;
	HashMap<String, String> testdata = null;

	@BeforeClass
	public void setUp() throws Exception {
		System.out.println("start.");

		driver = driverfactory.getAppiumDriver();
		cs = new Console();
		basefunc = new BaseFunc(driver, 0);
		dubbing = new Dubbing(driver, 0);
		login = new Login(driver, 0);
		mescenter = new MesCenter(driver, 0);
		pub = new PubClass(driver);
		scut = new ScreenCut(driver);
		detailpage = new DetailPage(driver, 0);
		circlepage = new CirclePage(driver, 0);
		circle = new Circle(driver, 0);
		
		testdata = pub.getTestDataInput("D:/TestData.xlsx");
		// scut.start();
		// mem.content = "deleteshiping";
		// mem.start();//开始读取手机的cpu和内存
	}

	@Test(priority = 1, enabled = true)
	public void Test1() throws InterruptedException, ParseException,
			IOException {
		cs.debugLog("start test.");

		basefunc.enterApp();

		pub.swipeToUp(500);
		// System.out.println(dubbing.getClass().getName());
		// String funcName2 = new
		// Throwable().getStackTrace()[1].getMethodName();
		// System.out.println(funcName2);

		// dubbing.testCase06();

		
		
	}

	@Test(priority = 2, enabled = false)
	public void TestCircle01() throws InterruptedException, ParseException,
			IOException {

		basefunc.EnterHome();
		circle.testCase01();

	}

	@Test(priority = 3, enabled = false)
	public void TestCircle02() throws InterruptedException, ParseException,
			IOException {

		basefunc.EnterHome();
		circle.testCase02();

	}

	@Test(priority = 4, enabled = false)
	public void TestDubbing01() throws InterruptedException, ParseException,
			IOException {

		Assert.assertTrue(testdata
				.containsKey("Dubbing.testCase01.dubbingtype"));
		
		String channelname =testdata.get("Dubbing.testCase01.channelname");
		int headset = Integer.parseInt(testdata.get("Dubbing.testCase01.headset"));
		int living = Integer.parseInt(testdata.get("Dubbing.testCase01.living"));
		int dubbingtime =Integer.parseInt(testdata.get("Dubbing.testCase01.dubbingtime"));
		int dubbingtype = Integer.parseInt(testdata.get("Dubbing.testCase01.dubbingtype"));
		int uploadtype = Integer.parseInt(testdata.get("Dubbing.testCase01.uploadtype"));
		
		
		cs.warnLog(channelname+headset+living+ dubbingtime +dubbingtype+ uploadtype);
		basefunc.EnterHome();
		dubbing.testCase01(channelname,headset,living, dubbingtime ,dubbingtype, uploadtype);

	}

	@Test(priority = 5, enabled = false)
	public void TestDubbing02() throws InterruptedException, ParseException,
			IOException {

		basefunc.EnterHome();
		dubbing.testCase03(1);

	}

	@Test(priority = 6, enabled = false)
	public void TestDubbing03() throws InterruptedException, ParseException,
			IOException {

		basefunc.EnterHome();
		dubbing.testCase05();

	}

	@Test(priority = 7, enabled = false)
	public void TestDubbing04() throws InterruptedException, ParseException,
			IOException {

		basefunc.EnterHome();
		dubbing.testCase06();

	}

	@Test(priority = 8, enabled = false)
	public void TestDubbing05() throws InterruptedException, ParseException,
			IOException {

		basefunc.EnterHome();
		dubbing.testCase07();

	}

	@AfterClass
	public void tearDown() throws Exception {
		System.out.println("end.");
		scut.stopRunning();
		// mem.stopRunning();//停止cpu获取和内存
		driver.quit();
	}

}
