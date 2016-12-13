package BaseClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Util.SystemHelper;

import io.appium.java_client.android.AndroidDriver;

public class BaseFunc {

	public AndroidDriver driver;
	public PubClass pub= null;
	public PubString str = null;
		
	By by_quickdubbing = null;
	
	By by_view = null;
	By by_dubbing = null;
	By by_bottombar = null;
	
	By by_by_quickdubbing2 = null;
	By by_sourcelib = null;
	By by_jqhylib = null;
	By by_hzgc = null;
	By by_uploadsource = null;
	By by_uploadfilm = null;
	public int guidetype = 1; //提示tips是否存在1为存在，0不存在

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	private By by_qwpy;

	public BaseFunc(AndroidDriver driver, int guidetype){
		this.driver = driver;
		pub = new PubClass(driver);		
		this.guidetype = guidetype;
		str = new PubString();
		
		by_view = By.className("android.widget.ImageView");
		by_quickdubbing = By.id("com.happyteam.dubbingshow:id/iv_fast");	//快速配音
		 //+号页按钮
		by_by_quickdubbing2 = By.id("com.happyteam.dubbingshow:id/sc_view");
		//by_sourcelib = By.id("com.happyteam.dubbingshow:id/sc_view");
		//by_jqhylib = By.id("com.happyteam.dubbingshow:id/jqhy_view");
		by_uploadsource = By.id("com.happyteam.dubbingshow:id/hzgc_view");
		by_uploadfilm = By.id("com.happyteam.dubbingshow:id/upload_st_view");
		by_dubbing = By.id("com.happyteam.dubbingshow:id/dubbing");	//素材库页面的配音按钮
		by_bottombar = By.id("com.happyteam.dubbingshow:id/layoutBottom");	//首页底端的bar
		
		by_qwpy = By.id("com.happyteam.dubbingshow:id/btnPeiyin");	//素材库的趣味配音
	}
	
	
	//进入首页
	public void enterApp() throws InterruptedException{
		System.out.println("----------enterApp");
		pub.checkRights();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebElement view = driver.findElement(by_view);
		
		if(view.getLocation().getY() == 0){//判断是否有启动页
			pub.swipeToLeft(500);
			pub.swipeToLeft(500);
			pub.swipeToLeft(500);//多滑几下，保险
			driver.findElement(By.id("com.happyteam.dubbingshow:id/go")).click();
		}
		System.out.println(df.format(new Date()));
		if(guidetype == 1){
			if(pub.isElementExist(By.name("下次更新"),1)){
				System.out.println("deal with updata...");
				driver.findElement(By.name("下次更新")).click();			
			}
			System.out.println("1::"+df.format(new Date()));
			guidetype = 0;
		}
		System.out.println(df.format(new Date()));
		
		WebElement mark = driver.findElementByName("热门");
		mark.click();
		SystemHelper.sleep(2);

	}
	
	/*
	 * 判断是否在首页，如果不是，则杀掉进程重启app
	 */
	public void EnterHome(){
		System.out.println("BackToHome");
		if(!pub.isElementExist(By.name("热门"), 2)){
			pub.extcmd("adb shell am force-stop " + str.packagename);
			SystemHelper.sleep(1);
			//pub.extcmd("adb shell am start -n " + str.packagename +" / "+ str.startactivity);
			driver.startActivity(str.packagename, str.startactivity);
			SystemHelper.sleep(2);
		}
		
		
	}
	
//	//进入首页四个模块中的一个
//	public void enterFeature(String feature){
//		System.out.println("-----------enterFeature");
//		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
//		WebElement feature_btn = driver.findElementByName(feature);
//		feature_btn.click();		
//	}
	
	//进入具体的频道
	public void enterChannel(String channel){
		System.out.println("-----------enterChannel");
		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		WebElement channel_btn = driver.findElementByName(channel);
		channel_btn.click();
	}
	
	/**
	 * 
	 * @param functype 按弹出框从左到右从上到下依次1，2，3，4，5，6，
	 */
	public void enterSomeFunc(String functype){
		WebElement bottombar = driver.findElement(by_bottombar);
		WebElement plusbtn = bottombar.findElement(By.className("android.view.View"));
		plusbtn.click();
		String type = null;
		type = functype;
		switch (type) {
		case "1":
			driver.findElement(by_by_quickdubbing2).click();
			break;
		case "2":
			driver.findElement(by_hzgc).click();
			break;
		case "3":
			driver.findElement(by_uploadsource).click();
			break;
		case "4":
			driver.findElement(by_uploadfilm).click();
			break;
		case "5":
			break;
		case "6":
			break;
		default:
			break;
		}
	}

	/**
	 * @author Jum
	 * @throws InterruptedException
	 * @Catalog 进入快速配音,无网情况下则进入已配缓存列表即可
	 * @param type 网络情况，0为无网，1为有网
	 */
	public boolean enterQuickDubbing(){
		WebElement quickbtn = driver.findElement(by_qwpy);
		quickbtn.click();
			
		if (pub.isElementExist(By.id("com.happyteam.dubbingshow:id/action"),
				600000)) {
			SystemHelper.sleep(2);
			pub.tab(pub.appScreen()[0] / 2, pub.appScreen()[1] * 1 / 4);
			pub.tab(pub.appScreen()[0] / 2, pub.appScreen()[1] * 1 / 4);
			pub.tab(pub.appScreen()[0] / 2, pub.appScreen()[1] * 1 / 4);
			return true;
		} else {
			System.out.println("enter dubbing failed.");
			return false;
		}

	}
	

	public void enterVideoDetail() throws InterruptedException{
		System.out.println("-----------enterVideoDetail");
		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		WebElement filmimg = driver.findElementById("com.happyteam.dubbingshow:id/film_common_container");
		filmimg.click();
		SystemHelper.sleep(2);
	}
	
	/**
	 * 当前页面：手机登录页面
	 * 结束页面：用户信息注册页面
	 * @throws InterruptedException
	 */
	public void registerByPhone() throws InterruptedException{
		
	}
	
	/**
	 * 当前页面：登录选择弹窗
	 * 结束页面：首页
	 */
	public void loginByPhone() throws InterruptedException{
		WebElement phone = driver.findElementByName("手机号码登录/注册");
		
		phone.click();
		
		WebElement phonenum = driver.findElementById("com.fangyanshow.dialectshow:id/et_number");
		WebElement phonepad = driver.findElementById("com.fangyanshow.dialectshow:id/et_password");
		WebElement ok = driver.findElementById("com.fangyanshow.dialectshow:id/tv_login");
		
		phonenum.sendKeys("18767122238");
		SystemHelper.sleep(1);
		phonepad.click();
		phonepad.sendKeys("000000");
		ok.click();
	}


	public void LoginCheck() {
		// TODO Auto-generated method stub
		
	}
}
