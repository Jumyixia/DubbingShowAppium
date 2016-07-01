package FuncTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import BaseClass.BaseFunc;
import BaseClass.DubbingPage;
import BaseClass.PubClass;
import BaseClass.UploadPage;
import BaseClass.VideoDetailPage;
import ObjectFactory.DriverFactory;
import io.appium.java_client.android.AndroidDriver;

public class Dubbing {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public PubClass pub = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public UploadPage uploadpage = null;
	public int guidetype = 1; //提示tips是否存在1为存在，0不存在
	
	public Dubbing(AndroidDriver driver,int guidetype){
		this.driver = driver;
		basefunc = new BaseFunc(driver,guidetype);
		pub = new PubClass(driver);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver,guidetype);
		uploadpage = new UploadPage(driver);
	}
	
	/**
	 * 从首页进入频道列表选择一个频道后进入视频详情，然后进入配音
	 * 配音界面打开背景音
	 * 配音人声音量调整到200，特效调整到50%
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void testCase01() throws InterruptedException, ParseException{
		basefunc.enterFeature("频道");
		basefunc.enterChannel("动漫");
		basefunc.enterVideoDetail();
		videodetailpage.Dubbing();
		dubbingpage.DubbingSet(1, 0);
		dubbingpage.EnterViewPage(9000);//配音时长可以控制
		dubbingpage.vol();
		dubbingpage.fx();
	}

	/**
	 * 循环配音
	 * 从首页的快速配音进入配音界面
	 * 直接普通配音
	 * 配音人声音量调整到200，特效调整到50%
	 * 保存草稿箱后选择“再配一次”
	 * @throws ParseException
	 * @throws InterruptedException
	 * @param times 循环配音次数
	 */
	public void testCase03(int times) throws ParseException, InterruptedException{
		System.out.println("testCase03");
		basefunc.enterQuickDubbing(1);
		for (int i = 1; i <= times; i++) {
			System.out.println("No. " + i);
			dubbingpage.DubbingSet(1, 0);
			dubbingpage.EnterViewPage(0);// 配音时长可以控制,0表示录完
			dubbingpage.vol();
			dubbingpage.fx();
			dubbingpage.enterUploadPage(1);
			uploadpage.SaveToDraft(1);
		}
	}
	
	/**
	 * 离线循环配音
	 * 从首页的快速配音进入配音界面
	 * 直接普通配音
	 * 配音人声音量调整到200，特效调整到50%
	 * 点击完成按钮，保存本地以及草稿箱
	 * @param times 循环配音次数
	 * @throws ParseException
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void testCase04(int times) throws ParseException, InterruptedException, IOException{
		By by_dubbingbtn = By.id("com.happyteam.dubbingshow:id/dubbing");
		System.out.println("testCase04");		
		for (int i = 1; i <= times; i++) {
			basefunc.enterQuickDubbing(0);
	
			driver.findElement(by_dubbingbtn).click();
			dubbingpage.DubbingSet(0, 0);
			dubbingpage.EnterViewPage(15000);// 配音时长可以控制,0表示录完
			dubbingpage.vol();
			dubbingpage.fx();
			dubbingpage.enterUploadPage(3);		
			
			
		}
	}
	
	/**
	 * 离线模式下，实况配音，计算实况美颜合成的时间
	 * 循环配已配缓存中的素材
	 * 预览界面调节人声音量和特效
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public void testCase05() throws ParseException, InterruptedException{
		By by_dubbingbtn = By.id("com.happyteam.dubbingshow:id/dubbing");
		List<WebElement> dubbinglist = null;
		for(int i=0; i < 2; i++){
			System.out.print("No. " + i + ":  ");
			
			basefunc.enterQuickDubbing(0);	
			driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
			dubbinglist = driver.findElements(by_dubbingbtn);
			WebElement DUB = driver.findElement(by_dubbingbtn);
			dubbinglist.get(i).click();
			dubbingpage.DubbingSet(0, 1);
			dubbingpage.EnterViewPage(0);// 配音时长可以控制,0表示录完
			dubbingpage.vol();
			dubbingpage.fx();
			dubbingpage.enterUploadPage(3);		
		}
		pub.pullFile("time.txt");
		
		
	}

	/**
	 * 
	 */
	public void testCase06() {
		// TODO Auto-generated method stub
		pub.WriteinFile("1234567890", "123.txy");
	}
	
	
}