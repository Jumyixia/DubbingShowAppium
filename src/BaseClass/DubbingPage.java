package BaseClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import Util.SystemHelper;


import io.appium.java_client.android.AndroidDriver;

public class DubbingPage {
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
	public AndroidDriver driver;
	public PubClass pub = null;
	public BaseFunc basefunc = null;	
	public int roletype = 0; //记录当前进行的是单配合适合作配音
	public int guidetype = 1; //提示tips是否存在1为存在，0不存在
	public String log = null;
	
	//	配音界面	
	By by_back = null;
	By by_headset = null;
	By by_living = null;
	By by_dubbing = null;
	By by_script_container = null;
	By by_scirpt_count = null;
	By by_coopera = null; 
	By by_role1 = null;
	By by_role2 = null;
	By by_roleall = null;
	By by_play = null;
	By by_video_time = null;
	By by_complete = null;
	By by_btnSubmit = null;
	By by_btnCancel = null;
	//  预览界面
	By by_review_title = null;
	By by_playbtn = null;
	By by_dubbingSeekbar = null;
	By by_fx = null;
	By by_mix = null;
	By by_roomsize = null;
	By by_echo = null;
	By by_vol = null;
	By by_bgfx = null;
	By by_bgvol = null;
	By by_guide = null;
	
	By by_btnback = null;
	
	public DubbingPage(AndroidDriver driver, int guidetype){
		this.driver = driver;
		pub = new PubClass(driver);
		this.guidetype = guidetype;
		
		//界面按钮初始化--配音界面
		by_back = By.id("com.happyteam.dubbingshow:id/back");		//返回
		by_headset = By.id("com.happyteam.dubbingshow:id/headset");	//背景音
		by_dubbing = By.id("com.happyteam.dubbingshow:id/action");	//配音
		by_living = By.id("com.happyteam.dubbingshow:id/living");	//实况
		by_script_container = By.id("com.happyteam.dubbingshow:id/script_container");	//台词
		by_scirpt_count = By.id("com.happyteam.dubbingshow:id/scirpt_count");	//台词数据
		by_coopera = By.id("com.happyteam.dubbingshow:id/coopera");	//合作
		by_role1 = By.id("com.happyteam.dubbingshow:id/role1_tv");	//合作第一个角色
		by_role2 = By.id("com.happyteam.dubbingshow:id/role2_tv");	//合作第二个角色
		by_roleall = By.id("com.happyteam.dubbingshow:id/roleall");
		by_play = By.id("com.happyteam.dubbingshow:id/play");	//原声播放
		by_video_time = By.id("com.happyteam.dubbingshow:id/video_time");	//视频时长
		by_complete = By.id("com.happyteam.dubbingshow:id/complete");	//完成按钮
		by_btnSubmit = By.id("com.happyteam.dubbingshow:id/btnSubmit"); 	// 点击完成按钮时，弹出询问弹窗的确定按钮
		by_btnCancel = By.id("com.happyteam.dubbingshow:id/btnCancel");	//弹出询问弹窗的取消按钮
		//界面按钮初始化--预览界面
		by_review_title = By.name("预览");
		by_playbtn = By.id("com.happyteam.dubbingshow:id/play_button");	//预览播放按钮
		by_guide  = By.id("com.happyteam.dubbingshow:id/btnGuideFixStart");
		by_dubbingSeekbar = By.id("com.happyteam.dubbingshow:id/dubbingSeekbar"); //人声音量调整区域
		by_vol = By.id("com.happyteam.dubbingshow:id/vol"); 	//人声音量
		by_fx =By.id("com.happyteam.dubbingshow:id/fx");	//人声特效
		by_mix = By.id("com.happyteam.dubbingshow:id/mix");	//人声混响
		by_roomsize = By.id("com.happyteam.dubbingshow:id/roomsize");	//人声空间
		by_echo = By.id("com.happyteam.dubbingshow:id/echo");	//人声回声
		
		by_bgfx = By.id("com.happyteam.dubbingshow:id/bgfx");
		by_bgvol = By.id("com.happyteam.dubbingshow:id/bgvol");
		
		by_btnback  = By.id("com.happyteam.dubbingshow:id/btnBack");	//素材库界面的返回按钮
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
	
	/**
	 * 设置合作
	 * @param set 0表示不合作或全部角色，1为第一个角色，2为配第二个角色
	 */
	public void Coopera(int set){
		WebElement cooperabtn = null;
		cooperabtn = driver.findElement(by_coopera);
		if(set == 1){			
			WebElement role1 = null;
			cooperabtn.click();
			role1 = driver.findElement(by_role1);
			role1.click();
			roletype = 1; //设置当前配音为发起合作配音
		}else if(set == 2){
			WebElement role2 = null;
			cooperabtn.click();
			role2 = driver.findElement(by_role1);
			role2.click();
			roletype = 1;//设置当前配音为发起合作配音
		}else if(set == 3){
			cooperabtn.click();
			WebElement roleall = null;
			roleall = driver.findElement(by_role2);
			roleall.click();
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

		WebElement complete_btn = null;
		WebElement endconfirm_btn = null;
		System.out.println("-----------EnterViewPage.");

		List<WebElement> dubbing_btn = driver.findElements(by_dubbing);
		if(dubbing_btn.size() > 1){	//快速配音页面特殊处理
			 dubbing_btn.get(1).click();
		}else{
			dubbing_btn.get(0).click();
		}
		
		video_time = driver.findElement(by_video_time).getText();

		time1 = video_time.substring(6,8);
		time2 = video_time.substring(9,11);
		video_time_int = Integer.parseInt(time1)*60000 + Integer.parseInt(time2)*1000;

		log =  "video_time_int ,  " + video_time_int/1000 ;
		
		Thread.sleep(3000);

		//开始配音并完成
		if(dubbing_time >= 8000 && dubbing_time <= video_time_int){

			Thread.sleep(dubbing_time);
			
			pub.tab(890, 1650);
		
			if(pub.isElementExist(by_btnSubmit)){//如果存在询问弹窗则点击确定按钮
				endconfirm_btn = driver.findElement(by_btnSubmit);
				endconfirm_btn.click();	
			}
			
		}else if(dubbing_time < 8000){
			Thread.sleep(video_time_int);
			//自动点击完成按钮等待合成
		//	System.out.println(df.format(new Date()));

		}else if(dubbing_time >= video_time_int){
			Thread.sleep(video_time_int );
			complete_btn = driver.findElement(by_complete);
			System.out.println(df.format(new Date()));
			complete_btn.click();
			if(pub.isElementExist(by_btnSubmit)){//如果存在询问弹窗则点击确定按钮
				endconfirm_btn = driver.findElement(by_btnSubmit);
				endconfirm_btn.click();	
			}
		}
		pub.isElementExist(by_review_title,30000);
		//System.out.println("guidetype = " + guidetype);
		if(guidetype == 1){
			if (pub.isElementExist(by_guide, 1)) {
				WebElement guide = driver.findElement(by_guide);
				guide.click();
			}	
			guidetype = 0;
		}
			
	//	System.out.println("enter yulan");			
	}
	
	
	/**
	 * 在预览界面点击播放按钮，播放视频
	 */
	public void view(){
		WebElement playbtn = driver.findElement(by_playbtn);
		playbtn.click();
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
		
		pub.tapOnElement(mix, 10, 15, 20);
		pub.tapOnElement(roomsize, 10, 15, 20);
		pub.tapOnElement(echo, 5, 15, 20);
	}
	
	//直接将音量调整到200
	public void vol(){
		//System.out.println("-----------begin vol");
		WebElement dubbingSeekbar = driver.findElement(by_dubbingSeekbar);
		pub.swipeOnElement(dubbingSeekbar, "Down", 1000);
		pub.swipeOnElement(dubbingSeekbar, "Down", 1000);
	}
	
	public void ViewPage(){
		
	}

	/**
	 * 配音预览界面点击完成
	 * @param type 1单配/合作完成  2发起合作 3离线
	 * @throws ParseException 
	 */
	public void enterUploadPage(int type) throws ParseException{
		long end = 0,start;
		WebElement complete = driver.findElement(by_complete);
		complete.click();
		//basefunc.LoginCheck();
		if(type == 1){
			//通过判断上传页面字段，确定是否进入上传界面
			if(pub.isElementExist(By.name("上传作品"),600000)){
				SystemHelper.sleep(2);
			}else{
				System.out.println("enter uploadpage failed.");
			}
		}else if(type == 3){
			start = df.parse(df.format(new Date())).getTime();
			driver.findElement(by_btnSubmit).click();
			SystemHelper.sleep(2);
			if(pub.isElementExist(by_btnSubmit, 600000)){
			
				driver.findElement(by_btnSubmit).click();
				end = df.parse(df.format(new Date())).getTime();
			}
			
			long time = end-start;
			log = log + ",using:," + time/1000;
			//返回到首页
			driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
			WebElement back =  driver.findElement(by_btnback);
			back.click();
			System.out.println(log);
			pub.WriteinFile(log, "time.txt");
			
		}
		
	}
	
	/**
	 * 配音预览界面点击完成
	 * @param type 1单配/合作完成  2发起合作 3离线
	 */
	public void enterUploadPage(){
		LoginCheck();
		// 通过判断上传页面字段，确定是否进入上传界面
		if (pub.isElementExist(By.name("上传作品"), 600000)) {
			SystemHelper.sleep(2);
		} else {
			System.out.println("enter uploadpage failed.");
		}
	}
	
	/**
	 * 在点击完成按钮后，通过判断是否出现登录弹窗，确定是否需要登录
	 */
	public void LoginCheck(){
		WebElement complete = driver.findElement(by_complete);
		complete.click();
		while(pub.isElementExist(By.name("登录"),2)){
			//进行登录操作
			pub.tab(570, 1278);
			complete.click();
		}
	}
		
}
