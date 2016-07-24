package BaseClass;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;


public class LoginFunc {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//ÉèÖÃÈÕÆÚ¸ñÊ½
	
	public AndroidDriver driver;
	public PubClass pub = null;
	public BaseFunc basefunc = null;
	
	public String qq_path = "com.tencent.mobileqq";
	public String sina_path = "";
	public String weixin_path = "";
	public String phonenum = "18767122238";
	public String phonepwt = "qwertyuiop";
	
	
	By by_title = null;
    By by_qq = null;
    By by_sina = null;
    By by_weixin = null;
    By by_phone = null;
    By by_close = null;
    
    By by_qq_tag = null;
    By by_qq_login = null;
	
	public LoginFunc(AndroidDriver driver, int guidetype){
		this.driver = driver;
		pub = new PubClass(driver);
		
		//µÇÂ¼µ¯´°
		by_qq = By.name("QQµÇÂ¼");
		by_sina = By.name("Î¢²©µÇÂ¼");
		by_weixin = By.name("ÍþÐÅµÇÂ¼");
		by_phone = By.name("ÊÖ»úºÅÂëµÇÂ¼/×¢²á");
		by_title = By.name("ÅäÒôÐãµÇÂ¼");
		
		//QQÕÊºÅµÇÂ¼½çÃæ
		by_qq_tag = By.name("ÇÐ»»ÕÊºÅ");
		by_qq_login = By.name("µÇÂ¼");
	}
	
	public void LoginByqq(){
		if(driver.isAppInstalled(qq_path)){
			driver.findElement(by_qq).click();
			if(pub.isElementExist(by_qq_tag, 1)){
				driver.findElement(by_qq_login).click();
				if(!pub.isElementExist(by_title, 1)){
					System.out.println("qq login suc");
				}
			}else {
				System.out.println("no qq account");
			}			
		}else{
			System.out.println("qq is not installed");
		}
		
	}
	
	public void QqUnistalled(){
		
	}
}
