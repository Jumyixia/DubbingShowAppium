package BaseClass;

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
	
	By by_quickdubbing = null;
	By by_view = null;
	By by_dubbing = null;
	public int guidetype = 1; //提示tips是否存在1为存在，0不存在


	public BaseFunc(AndroidDriver driver, int guidetype){
		this.driver = driver;
		pub = new PubClass(driver);		
		this.guidetype = guidetype;
		
		by_view = By.className("android.widget.ImageView");
		by_quickdubbing = By.id("com.happyteam.dubbingshow:id/iv_fast");	//快速配音
		by_dubbing = By.id("com.happyteam.dubbingshow:id/dubbing");	//素材库页面的配音按钮
	}
	
	
	//进入首页
	public void enterApp() throws InterruptedException{
		System.out.println("----------enterApp");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebElement view = driver.findElement(by_view);
		
		if(view.getLocation().getY() == 0){//判断是否有启动页
			pub.swipeToLeft(500);
			pub.swipeToLeft(500);
			pub.swipeToLeft(500);//多滑几下，保险
			driver.findElement(By.id("com.happyteam.dubbingshow:id/go")).click();
		}
		
		if(guidetype == 1){
			if(pub.isElementExist(By.name("下次更新"),3)){
				System.out.println("deal with updata...");
				driver.findElement(By.name("下次更新")).click();			
			}
			guidetype = 0;
		}

		WebElement mark = driver.findElementByName("热门");
		mark.click();
		Thread.sleep(2000);

	}
	
	//进入首页四个模块中的一个
	public void enterFeature(String feature){
		System.out.println("-----------enterFeature");
		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		WebElement feature_btn = driver.findElementByName(feature);
		feature_btn.click();		
	}
	
	//进入具体的频道
	public void enterChannel(String channel){
		System.out.println("-----------enterChannel");
		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		WebElement channel_btn = driver.findElementByName(channel);
		channel_btn.click();
	}
	

	/**
	 * @author Jum
	 * @throws InterruptedException
	 * @Catalog 进入快速配音
	 * @param type 网络情况，0为无网，1为有网
	 */
	public void enterQuickDubbing(int type){
		WebElement quickbtn = driver.findElement(by_quickdubbing);
		quickbtn.click();
		if(type == 0){
			WebElement dubbing = driver.findElement(by_dubbing);
			dubbing.click();
		}else {
			if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/action"),600000)){
				SystemHelper.sleep(2);
				pub.tab(pub.appScreen()[0]/2,pub.appScreen()[1]*1/4);
			}else{
				System.out.println("enter dubbing failed.");
			}
		}			
	}
	

	public void enterVideoDetail() throws InterruptedException{
		System.out.println("-----------enterVideoDetail");
		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		WebElement filmimg = driver.findElementById("com.happyteam.dubbingshow:id/filmimg");
		filmimg.click();
		Thread.sleep(2000);
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
		Thread.sleep(1000);
		phonepad.click();
		phonepad.sendKeys("000000");
		ok.click();
	}


	public void LoginCheck() {
		// TODO Auto-generated method stub
		
	}
}
