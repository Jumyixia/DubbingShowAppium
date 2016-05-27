package BaseClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;


import io.appium.java_client.android.AndroidDriver;

public class DubbingPage {
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
	public AndroidDriver driver;
	public PubClass pub = null;
	public BaseFunc basefunc = null;
	
	//	配音界面	
	By by_back = null;
	By by_headset = null;
	By by_living = null;
	By by_dubbing = null;
	By by_script_container = null;
	By by_scirpt_count = null;
	By by_coopera = null; 
	By by_play = null;
	By by_video_time = null;
	By by_complete = null;
	By by_endconfirm = null;
	//  预览界面
	By by_review_title = null;
	By by_dubbingSeekbar = null;
	By by_fx = null;
	By by_mix = null;
	By by_roomsize = null;
	By by_echo = null;
	By by_vol = null;
	By by_bgfx = null;
	By by_bgvol = null;
	By by_guide = null;
	
	public DubbingPage(AndroidDriver driver){
		this.driver = driver;
		pub = new PubClass(driver);
		
		//界面按钮初始化--配音界面
		by_back = By.id("com.happyteam.dubbingshow:id/back");		//返回
		by_headset = By.id("com.happyteam.dubbingshow:id/headset");	//背景音
		by_dubbing = By.id("com.happyteam.dubbingshow:id/action");	//配音
		by_living = By.id("com.happyteam.dubbingshow:id/living");	//实况
		by_script_container = By.id("com.happyteam.dubbingshow:id/script_container");	//台词
		by_scirpt_count = By.id("com.happyteam.dubbingshow:id/scirpt_count");	//台词数据
		by_coopera = By.id("com.happyteam.dubbingshow:id/coopera");	//合作
		by_play = By.id("com.happyteam.dubbingshow:id/play");	//原声播放
		by_video_time = By.id("com.happyteam.dubbingshow:id/video_time");	//视频时长
		by_complete = By.id("com.happyteam.dubbingshow:id/complete");	//完成按钮
		by_endconfirm = By.id("com.happyteam.dubbingshow:id/btnSubmit"); 	// 点击完成按钮时，弹出询问弹窗的确定按钮
		//界面按钮初始化--预览界面
		by_review_title = By.name("预览");
		by_guide  = By.id("com.happyteam.dubbingshow:id/btnGuideFixStart");
		by_dubbingSeekbar = By.id("com.happyteam.dubbingshow:id/dubbingSeekbar"); //人声音量调整区域
		by_vol = By.id("com.happyteam.dubbingshow:id/vol"); 	//人声音量
		by_fx =By.id("com.happyteam.dubbingshow:id/fx");	//人声特效
		by_mix = By.id("com.happyteam.dubbingshow:id/mix");	//人声混响
		by_roomsize = By.id("com.happyteam.dubbingshow:id/roomsize");	//人声空间
		by_echo = By.id("com.happyteam.dubbingshow:id/echo");	//人声回声
		
		by_bgfx = By.id("com.happyteam.dubbingshow:id/bgfx");
		by_bgvol = By.id("com.happyteam.dubbingshow:id/bgvol");
		
	}

	//配音
	public void Dubbing(){
		WebElement dubbing_btn = driver.findElement(by_dubbing);
		dubbing_btn.click();
	}
	
	/**
	 * 配音界面设置
	 * @param headset	1为需要开启 0为需要关闭
	 * @param living	1为需要开启 0为需要关闭
	 */
	public void DubbingSet(int headset, int living){
		this.Headset(headset);
		this.Living(living);
	}

	
	/**
	 * @param set 1为需要开启 0为需要关闭
	 */
	public void Headset(int set){
		WebElement headset = driver.findElement(by_headset);
		switch (set) {
		case 1:
			if(!headset.isSelected()){
				headset.click();
				By by_sub = By.id("com.happyteam.dubbingshow:id/btnSubmit");				
				if(pub.isElementExist(by_sub)){
					driver.findElement(by_sub).click();
				}
			}
			break;
		case 0:
			if(headset.isSelected()){
				headset.click();
			}			
			break;
		default:
			break;
		}
	}
	
	/**
	 * @param set 1为需要开启 0为需要关闭
	 */
	public void Living(int set){
		WebElement linving = driver.findElement(by_living);
		switch (set) {
		case 1:
			if(!linving.isSelected()){
				linving.click();
				By by_sub = By.name("允许");				
				if(pub.isElementExist(by_sub)){
					driver.findElement(by_sub).click();
					if(pub.isElementExist(by_sub)){
						driver.findElement(by_sub).click();
					}
				}
			}
			break;
		case 0:
			if(linving.isSelected()){
				linving.click();
			}			
			break;
		default:
			break;
		}
	}
	
	
	
	/*
	 * 完成进入预览界面
	 * dubbing_time: <8000 || >=素材时长 则直接等配音结束直接自动跳转到配音页面
	 * dubbing_time: >=8000 || <素材时长 按dubbing_time的时间点击完成按钮
	 */
	public void EnterViewPage(int dubbing_time) throws ParseException, InterruptedException{
		int video_time_int = 0;//素材时长转换成按秒计	
		String video_time,time1,time2 = null;
		
		System.out.println("-----------EnterViewPage.");
		System.out.println(dubbing_time);
		
		WebElement dubbing_btn = driver.findElement(by_dubbing);
		WebElement complete_btn = null;
		WebElement endconfirm_btn = null;
		dubbing_btn.click();

		video_time = driver.findElement(by_video_time).getText();

		time1 = video_time.substring(6,8);
		time2 = video_time.substring(9,11);
		video_time_int = Integer.parseInt(time1)*60000 + Integer.parseInt(time2)*1000;

		System.out.println("video_time_int: " + video_time_int);
		
		System.out.println("完成计算"+df.format(new Date()));
		
		Thread.sleep(3000);

		//开始配音并完成
		if(dubbing_time >= 8000 && dubbing_time <= video_time_int){

			Thread.sleep(dubbing_time);
			
			pub.tab(890, 1650);
		
			if(pub.isElementExist(by_endconfirm)){//如果存在询问弹窗则点击确定按钮
				endconfirm_btn = driver.findElement(by_endconfirm);
				endconfirm_btn.click();	
			}
			
		}else if(dubbing_time < 8000){
			
			System.out.println(1);
	
			Thread.sleep(dubbing_time);
			//自动点击完成按钮等待合成
			System.out.println(df.format(new Date()));

		}else if(dubbing_time >= video_time_int){
			System.out.println(11);
			Thread.sleep(dubbing_time );
			complete_btn = driver.findElement(by_complete);
			System.out.println(df.format(new Date()));
			complete_btn.click();
			if(pub.isElementExist(by_endconfirm)){//如果存在询问弹窗则点击确定按钮
				endconfirm_btn = driver.findElement(by_endconfirm);
				endconfirm_btn.click();	
			}

		}
			pub.isElementExist(by_review_title,30000);
			WebElement guide = driver.findElement(by_guide);
			guide.click();
			System.out.println("enter yulan");		
		
	}
	
	/**
	 * @category 调节人生特效，均为50%
	 */
	public void fx(){
		
		WebElement fx = driver.findElement(by_fx);
		fx.click();
		
		WebElement mix = driver.findElement(by_mix);
		WebElement roomsize = driver.findElement(by_roomsize);	
		WebElement echo = driver.findElement(by_echo);
	
		pub.tapOnElement(mix, 10, 11, 20);
		pub.tapOnElement(roomsize, 10, 11, 20);
		pub.tapOnElement(echo, 5, 11, 20);
	}
	
	//直接将音量调整到200
	public void vol(){
		System.out.println("-----------begin vol");
		WebElement dubbingSeekbar = driver.findElement(by_dubbingSeekbar);
		pub.swipeOnElement(dubbingSeekbar, "Down", 1000);
		pub.swipeOnElement(dubbingSeekbar, "Down", 1000);
	}
	
	public void ViewPage(){
		
	}
	
	/**
	 * 配音预览界面点击完成
	 * @param type 1单配/合作完成  2发起合作 3离线
	 */
	public void enterUploadPage(int type){
		WebElement complete = driver.findElement(by_complete);
		complete.click();
		basefunc.LoginCheck();
		if(type == 1){
			//通过判断上传页面字段，确定是否进入上传界面
		}
		
	}
		
		
}
