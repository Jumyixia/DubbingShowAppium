package FuncTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import BaseClass.BaseFunc;
import BaseClass.DubbingPage;
import BaseClass.PubClass;
import BaseClass.SourceViewPage;
import BaseClass.UploadPage;
import BaseClass.VideoDetailPage;
import Util.Console;
import Util.SystemHelper;
import Util.DriverFactory;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;

public class Dubbing {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public PubClass pub = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public UploadPage uploadpage = null;
	public SourceViewPage srview = null;
	public Console cs = null;
	public int guidetype = 1; //提示tips是否存在1为存在，0不存在
	private By by_source;
	private By by_qwpy;
	private By by_bottombar;
	private By by_btnback;
	
	public Dubbing(AndroidDriver driver,int guidetype){
		this.driver = driver;
		basefunc = new BaseFunc(driver,guidetype);
		pub = new PubClass(driver);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver,guidetype);
		uploadpage = new UploadPage(driver);
		srview = new SourceViewPage(driver,guidetype );
		cs = new Console();
		
		by_source = By.id("com.happyteam.dubbingshow:id/container");	//素材库一个素材项
		by_qwpy = By.id("com.happyteam.dubbingshow:id/btnPeiyin");	//素材库的趣味配音
		by_bottombar = By.id("com.happyteam.dubbingshow:id/layoutBottom");	//首页底端的bar
		by_btnback  = By.id("com.happyteam.dubbingshow:id/btnBack");	//素材库界面的返回按钮
	}
	
	/**
	 * 从首页进入频道列表选择一个频道后进入视频详情，然后进入配音
	 * 配音界面打开背景音
	 * 配音人声音量调整到200，特效调整到50%
	 * 合成后保存草稿箱退回到视频详情页
	 * 断言：判断视频详情页的送礼按钮是否存在
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void testCase01(String channelname,int headset,int living, int dubbingtime ,int dubbingtype, int uploadtype ) throws InterruptedException, ParseException{
		
		basefunc.enterChannel(channelname);
		basefunc.enterVideoDetail();
		videodetailpage.Dubbing();
		dubbingpage.DubbingSet(headset,living);
		dubbingpage.EnterViewPage(dubbingtime);//配音时长可以控制
		dubbingpage.vol();
		dubbingpage.fx();
		dubbingpage.enterUploadPage(dubbingtype);
		uploadpage.SaveToDraft(uploadtype);
		Assert.assertTrue(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/send_gift")));
		
		//从视频详情页退回到首页
		cs.errorLog("asseer .....");
		driver.findElement(By.id("com.happyteam.dubbingshow:id/btnBack")).click();
		driver.findElement(By.id("com.happyteam.dubbingshow:id/btnBack")).click();
	}

	/**
	 * 循环配音 从首页的快速配音进入配音界面 直接普通配音 配音人声音量调整到200，特效调整到50% 保存草稿箱后选择“再配一次”
	 * 最后一个配音选择保存草稿箱后退出配音
	 * 断言：是否会回到素材库
	 * @throws ParseException
	 * @throws InterruptedException
	 * @param times
	 *            循环配音次数
	 */
	public void testCase03(int times) throws ParseException,InterruptedException {
		System.out.println("testCase03");
		basefunc.enterSomeFunc("1");
		if (basefunc.enterQuickDubbing()) {// 进入快速配音成功
			for (int i = 1; i <= times; i++) {
				System.out.println("No. " + i);
				dubbingpage.DubbingSet(1, 0);
				dubbingpage.EnterViewPage(0);// 配音时长可以控制,0表示录完
				dubbingpage.vol();
				dubbingpage.fx();
				dubbingpage.enterUploadPage(1);
				if (i == times) {
					uploadpage.SaveToDraft(0);
				} else {
					uploadpage.SaveToDraft(1);
				}
			}

			Assert.assertTrue(pub.isElementExist(by_qwpy));

			driver.findElement(By.id("com.happyteam.dubbingshow:id/btnBack")).click();
		}else{
			System.out.println("enter qwpy failed.");
		}
	}
	
	/**
	 * 方法不可用（离线情况下，无法进入快速配音）
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
			basefunc.enterQuickDubbing();
	
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
	 * 合成后保存草稿箱回到素材库
	 * 断言：最后是否回到了素材库
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public void testCase05() throws ParseException, InterruptedException{
		//设置网络情况1飞行，2wifi 4data
		driver.setNetworkConnection(new NetworkConnectionSetting(1));
		
		By by_dubbingbtn = By.id("com.happyteam.dubbingshow:id/dubbing");
		List<WebElement> dubbinglist = null;
		
		//basefunc.enterSomeFunc("1");断网情况下直接进入已配
		WebElement bottombar = driver.findElement(by_bottombar);
		WebElement plusbtn = bottombar.findElement(By.className("android.view.View"));
		plusbtn.click();
		
		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		
		dubbinglist = driver.findElements(by_dubbingbtn);
		
		int count = dubbinglist.size();
		for(int i=0; i < count; i++){
			System.out.print("No. " + i + ":  ");
			
			dubbinglist.get(i).click();
			dubbingpage.DubbingSet(0, 1);
			dubbingpage.EnterViewPage(0);// 配音时长可以控制,0表示录完
			dubbingpage.vol();
			dubbingpage.fx();
			dubbingpage.enterUploadPage(3);		
			
		}
		
		//恢复网络为wifi连接
		driver.setNetworkConnection(new NetworkConnectionSetting(2));
		
		Assert.assertTrue(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/container")));
		
		
		
		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		WebElement back =  driver.findElement(by_btnback);
		back.click();
		
	}

	/**
	 * 1. 从首页进入素材库
	 * 2. 素材库上滑后选择任意视频进入素材预览然后进入配音界面
	 * 3. 配音一段时间后，完成配音进入调音界面
	 * 4. 在调音界面条件人声音量、变声、特效
	 * 5. 点击完成进入上传界面，上传成功返回到首页 
	 */
	public void testCase06() {
		basefunc.enterSomeFunc("1");
		if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/iv_add"),10)){//通过判断是否存在搜索按钮来判断是否进入素材库
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
		
		if(basefunc.enterQuickDubbing()){//进入快速配音成功
			dubbingpage.Living(1);
			try {
				dubbingpage.EnterViewPage(0);
				dubbingpage.addBg(1);
				dubbingpage.bgvol();

				dubbingpage.enterUploadPage(1);
				uploadpage.Upload();

				
				Assert.assertTrue(pub.isElementExist(by_qwpy));

				driver.findElement(By.id("com.happyteam.dubbingshow:id/btnBack")).click();
				
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
	 * 方法不可用（没有专门的合演功能啦）
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