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
import BaseClass.SourceViewPage;
import BaseClass.UploadPage;
import BaseClass.VideoDetailPage;
import ObjectFactory.DriverFactory;
import Util.SystemHelper;
import io.appium.java_client.android.AndroidDriver;

public class Dubbing {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public PubClass pub = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public UploadPage uploadpage = null;
	public SourceViewPage srview = null;
	public int guidetype = 1; //提示tips是否存在1为存在，0不存在
	private By by_source;
	
	public Dubbing(AndroidDriver driver,int guidetype){
		this.driver = driver;
		basefunc = new BaseFunc(driver,guidetype);
		pub = new PubClass(driver);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver,guidetype);
		uploadpage = new UploadPage(driver);
		srview = new SourceViewPage(driver,guidetype );
		
		by_source = By.id("com.happyteam.dubbingshow:id/container");	//素材库一个素材项
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
	 * 1. 从首页进入素材库
	 * 2. 素材库上滑后选择任意视频进入素材预览然后进入配音界面
	 * 3. 配音一段时间后，完成配音进入调音界面
	 * 4. 在调音界面条件人声音量、变声、特效
	 * 5. 点击完成进入上传界面，上传成功返回到首页 
	 */
	public void testCase06() {
		basefunc.enterSomeFunc("2");
		if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/btnSearch"),10)){//通过判断是否存在搜索按钮来判断是否进入素材库
			pub.swipeToUp(500);
			SystemHelper.sleep(1);
			List<WebElement> sourcelist = driver.findElements(by_source);
			int count = pub.getrandomWithoutZero(sourcelist.size());
			sourcelist.get(count).click();
			
			if(srview.enterDubbing()){
				//去掉引导页
				pub.tab(pub.appScreen()[0]/2,pub.appScreen()[1]*1/4);
				pub.tab(pub.appScreen()[0]/2,pub.appScreen()[1]*1/4);
				pub.tab(pub.appScreen()[0]/2,pub.appScreen()[1]*1/4);
				
				try {
					dubbingpage.EnterViewPage(10);
					dubbingpage.vol();
					dubbingpage.pitch();
					dubbingpage.fx();
					dubbingpage.enterUploadPage(1);	
					uploadpage.Upload();
					
					if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/btnBack"))){
						driver.findElement(By.id("com.happyteam.dubbingshow:id/btnBack")).click();
						if(!pub.isElementExist(By.name("热门"))){
							System.out.println("back to home failed.");
						}
					}else{
						System.out.println("upload back to sourcePage failed.");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("enter sourcelib failed.");
		}
	}
	
	/**
	 * 1. 从首页+号进入趣味配音
	 * 2. 趣味配音页面开启实况、开始配音；
	 * 3. 配音完成后，自动进入调音界面
	 * 4. 调音界面条件背景音音量、并选择通用音乐新增背景音
	 * 5. 完成新增背景音后，点击完成按钮进入上传页面，上传作品返回首页
	 */
	public void testCase07() {
		basefunc.enterSomeFunc("1");
		if(basefunc.enterQuickDubbing(1)){//进入快速配音成功
			dubbingpage.Living(1);
			try {
				dubbingpage.EnterViewPage(0);
				dubbingpage.addBg(1);
				dubbingpage.bgvol();

				dubbingpage.enterUploadPage(1);
				uploadpage.Upload();

				
				if (!pub.isElementExist(By.name("热门"))) {
					System.out.println("back to home failed.");
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {
			System.out.println("enter qwpy failed.");
		}
	}
	
	
	/**
	 * 合演配音
	 * 1. 从首页热门+号进入合演素材列表
	 * 2. 上滑选择素材进入素材预览界面，进入配音（该case 不考虑是否是双人素材，均默认为单人）
	 * 3. 设置开启背景音，然后开始配音
	 * 4. 完成配音后进入调音界面
	 * 5. 在调音界面点击完成按钮进入上传页面，在上传界面完成上传返回到素材库
	 * 6. 素材库点击返回，返回到首页
	 */
	public void testCase08(){
		basefunc.enterSomeFunc("3");
		if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/btnSearch"),10)){//通过判断是否存在搜索按钮来判断是否进入素材库
			pub.swipeToUp(500);
			SystemHelper.sleep(1);
			WebElement hysource = driver.findElement(by_source);
			//	此处如果该素材没有显示时长，则会报错
			WebElement hysourcetime = hysource.findElement(By.id("com.happyteam.dubbingshow:id/tv_time"));
			dubbingpage.hysourcetime = hysourcetime.getText().trim();
			hysource.click();
			
			if(srview.enterDubbing()){
				//去掉引导页
				pub.tab(pub.appScreen()[0]/2,pub.appScreen()[1]/2);
				
				try {
					dubbingpage.HyEnterViewPage();
					
					dubbingpage.enterUploadPage(1);	
					uploadpage.Upload();
					
					if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/btnBack"))){
						driver.findElement(By.id("com.happyteam.dubbingshow:id/btnBack")).click();
						if(!pub.isElementExist(By.name("热门"))){
							System.out.println("back to home failed.");
						}
					}else{
						System.out.println("upload back to sourcePage failed.");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}else {
			System.out.println("enter sourcelib failed.");
		}
	}
	
	
}