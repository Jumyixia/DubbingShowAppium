package BaseClass;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ObjectFactory.DriverFactory;
import Util.SystemHelper;
import io.appium.java_client.android.AndroidDriver;

public class DetailPage {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public PubClass pub = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public UploadPage uploadpage = null;
	public SourceViewPage srview = null;
	public int guidetype = 1; //提示tips是否存在1为存在，0不存在
	
	public int film_time = 0;
	
	private By by_source;
	private By by_homepagelist;
	private By by_homefilmcontainer;
	private By by_filmtime;
	private By by_back;
	private By by_wait;
	private By by_endtag;
	private By by_play;
	
	
	public DetailPage(AndroidDriver driver,int guidetype){
		this.driver = driver;
		basefunc = new BaseFunc(driver,guidetype);
		pub = new PubClass(driver);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver,guidetype);
		uploadpage = new UploadPage(driver);
		srview = new SourceViewPage(driver,guidetype );
		
		by_source = By.id("com.happyteam.dubbingshow:id/container");	//素材库一个素材项
		by_homepagelist = By.id("com.happyteam.dubbingshow:id/list");	//首页热门列表
		by_homefilmcontainer = By.id("com.happyteam.dubbingshow:id/film_common_container");	//首页热门列表中的视频
		by_filmtime = By.id("com.happyteam.dubbingshow:id/filmTime");	//视频封面上的时间
		
		//视频详情页
		by_back = By.id("com.happyteam.dubbingshow:id/btnBack");	//返回按钮
		by_wait = By.id("com.happyteam.dubbingshow:id/media_anim_image_view");	//视频上的正在加载动画
		by_play = By.id("com.happyteam.dubbingshow:id/video_play_btn");	//播放按钮
		by_endtag = By.id("com.happyteam.dubbingshow:id/linear_cb");	//播放完成后的重播按钮
	}
	
	/**
	 * 【未考虑网络情况，该功能暂不可用，网络好才行……】
	 * 在视频列表获取一个视频，进入视频详情
	 * 在视频详情浏览视频到结束然后退出
	 * 如果视频无法加载则等待30秒后退出
	 */
	public void viewFilmFromHomeToEnd(){
		WebElement homelist = driver.findElement(by_homepagelist);
		//List<WebElement> homefilm = homelist.findElements(by_homefilmcontainer);
		List<WebElement> time = homelist.findElements(by_filmtime); //仅选择有时长的视频
		System.out.println("==="+time.size());
		int count = time.size();
		WebElement targetfilm = time.get(pub.getrandom(count));	//从当前页面随机选择一个视频
		// 计算选中视频的时长
		String time1 = targetfilm.getText().substring(0, 2);
		String time2 =targetfilm.getText().substring(3, 5);
		film_time = Integer.parseInt(time1) * 60000 + Integer.parseInt(time2) * 1000;
		
		targetfilm.click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 
		//	详情页的控件
		WebElement back_btn = driver.findElement(by_back);
		//WebElement cb_btn = driver.findElement(by_endtag);
		

		if(pub.waitUntilDisappear(by_wait,30)){
			if(pub.isElementExist(by_play)){
				System.out.println("no start ");
				driver.findElement(by_play).click();
				SystemHelper.sleep(film_time);
			}else{
				System.out.println("start");
				SystemHelper.sleep(film_time-30);
			}
			if(pub.isElementExist(by_endtag, 10)){
				System.out.println("film is end.");
			}else{
				System.out.println("some error.");
			}			
		}else{			
			System.out.println("film loading failed.");
		}
		
		back_btn.click();
		SystemHelper.sleep(1);
	}
	
	/**
	 * 从首页随机选择一个视频，进入视频详情页
	 * 如果有播放按钮则点击播放按钮
	 * 播放后等待50秒然后退出
	 */
	public void viewFilmFromHome(){
		WebElement homelist = driver.findElement(by_homepagelist);
		List<WebElement> homefilm = homelist.findElements(by_homefilmcontainer);
		//List<WebElement> time = homelist.findElements(by_filmtime); //仅选择有时长的视频
		int count = homefilm.size();
		WebElement targetfilm = homefilm.get(pub.getrandom(count));	//从当前页面随机选择一个视频
		// 计算选中视频的时长
		//String time1 = targetfilm.getText().substring(0, 2);
		//String time2 =targetfilm.getText().substring(3, 5);
		//film_time = Integer.parseInt(time1) * 60000 + Integer.parseInt(time2) * 1000;
		
		targetfilm.click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 
		//	详情页的控件
		WebElement back_btn = driver.findElement(by_back);
		//WebElement cb_btn = driver.findElement(by_endtag);
		

			if(pub.isElementExist(by_play,10)){
				System.out.println("no start ");
				driver.findElement(by_play).click();
				SystemHelper.sleep(30);
			}else{
				System.out.println("start");
				SystemHelper.sleep(30);
			}
			if(pub.isElementExist(by_endtag, 10)){
				System.out.println("film is end.");
			}else{
				System.out.println("error or no end.");
			}			
			
		back_btn.click();
		SystemHelper.sleep(1);
	}
}
