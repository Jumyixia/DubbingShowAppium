package BaseClass;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;


public class LoginFunc {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	
	public AndroidDriver driver;
	public PubClass pub = null;
	public BaseFunc basefunc = null;
	
	public String qq_path = "com.tencent.mobileqq";
	public String sina_path = "";
	public String weixin_path = "";
	public String phonenum = "18767122238";
	public String phonepwt = "qwertyuiop";
	public String qq_account = "507879537";
	public String qq_pwd = "123aaa";
	
	
	By by_title = null;
    By by_qq = null;
    By by_sina = null;
    By by_weixin = null;
    By by_phone = null;
    By by_close = null;
    
    By by_qq_tag = null;
    By by_qq_login = null;
    By by_qq_account = null;
    By by_qq_pwd = null;
	
	public LoginFunc(AndroidDriver driver, int guidetype){
		this.driver = driver;
		pub = new PubClass(driver);
		
		//��¼����
		by_qq = By.name("QQ��¼");
		by_sina = By.name("΢����¼");
		by_weixin = By.name("���ŵ�¼");
		by_phone = By.name("�ֻ������¼/ע��");
		by_title = By.name("�������¼");
		
		//QQ�ʺŵ�¼����
		by_qq_tag = By.name("�л��ʺ�");
		by_qq_login = By.name("�� ¼");
		by_qq_account = By.id("com.tencent.mobileqq:id/account");
		by_qq_pwd = By.id("com.tencent.mobileqq:id/password");
	}
	
	/*
	 * �����е�¼������δ���ж��Ƿ��¼�ɹ�
	 */
	public void LoginByqq(){
		System.out.println("000");
		if(driver.isAppInstalled(qq_path)){
			//driver.findElement(by_qq).click();
			pub.tab(pub.appScreen()[0]*1/4, pub.appScreen()[1]/2);
			if(pub.isElementExist(by_qq_tag, 1)){
				driver.findElement(by_qq_login).click();
			}else {
				System.out.println("no qq account and now login 507879537");
				WebElement qqaccount =  driver.findElement(by_qq_account);
				WebElement qqpwd = driver.findElement(by_qq_pwd);
				WebElement qqlogin = driver.findElement(by_qq_login);
				qqaccount.click();
				qqaccount.sendKeys(qq_account);
				qqpwd.click();
				qqpwd.sendKeys(qq_pwd);
				qqlogin.click();
			}	
		}else{
			System.out.println("qq is not installed");
		}
		
	}
	
	public void Unistalledqq(){
		System.out.println("000");
		if(driver.isAppInstalled(qq_path)){
			driver.removeApp("com.happyteam.dubbingshow");
		}else{
			if(driver.isAppInstalled(qq_path)){
				System.out.println("uninstall qq failed");
			}else{
				pub.tab(pub.appScreen()[0]*1/4, pub.appScreen()[1]/2);
			}
		}
		
	}
}
