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
		
	By by_quickdubbing = null;
	By by_view = null;
	By by_dubbing = null;
	public int guidetype = 1; //��ʾtips�Ƿ����1Ϊ���ڣ�0������

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ

	public BaseFunc(AndroidDriver driver, int guidetype){
		this.driver = driver;
		pub = new PubClass(driver);		
		this.guidetype = guidetype;
		
		by_view = By.className("android.widget.ImageView");
		by_quickdubbing = By.id("com.happyteam.dubbingshow:id/iv_fast");	//��������
		by_dubbing = By.id("com.happyteam.dubbingshow:id/dubbing");	//�زĿ�ҳ���������ť
	}
	
	
	//������ҳ
	public void enterApp() throws InterruptedException{
		System.out.println("----------enterApp");
		pub.checkRights();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebElement view = driver.findElement(by_view);
		
		if(view.getLocation().getY() == 0){//�ж��Ƿ�������ҳ
			pub.swipeToLeft(500);
			pub.swipeToLeft(500);
			pub.swipeToLeft(500);//�໬���£�����
			driver.findElement(By.id("com.happyteam.dubbingshow:id/go")).click();
		}
		System.out.println(df.format(new Date()));
		if(guidetype == 1){
			if(pub.isElementExist(By.name("�´θ���"),1)){
				System.out.println("deal with updata...");
				driver.findElement(By.name("�´θ���")).click();			
			}
			System.out.println("1::"+df.format(new Date()));
			guidetype = 0;
		}
		System.out.println(df.format(new Date()));
		
		WebElement mark = driver.findElementByName("����");
		mark.click();
		SystemHelper.sleep(2);

	}
	
	/*
	 * �ж��Ƿ�����ҳ��������ǣ���ɱ����������app
	 */
	public void BackToHome(){
		WebElement quickbtn = driver.findElement(by_quickdubbing);
		if(!quickbtn.isDisplayed()){
			driver.closeApp();
			driver.launchApp();
		}
		
	}
	
	//������ҳ�ĸ�ģ���е�һ��
	public void enterFeature(String feature){
		System.out.println("-----------enterFeature");
		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		WebElement feature_btn = driver.findElementByName(feature);
		feature_btn.click();		
	}
	
	//��������Ƶ��
	public void enterChannel(String channel){
		System.out.println("-----------enterChannel");
		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		WebElement channel_btn = driver.findElementByName(channel);
		channel_btn.click();
	}
	

	/**
	 * @author Jum
	 * @throws InterruptedException
	 * @Catalog �����������,�����������������仺���б���
	 * @param type ���������0Ϊ������1Ϊ����
	 */
	public void enterQuickDubbing(int type){
		WebElement quickbtn = driver.findElement(by_quickdubbing);
		quickbtn.click();
		if(type == 1){		
			if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/action"),600000)){
				SystemHelper.sleep(2);
				pub.tab(pub.appScreen()[0]/2,pub.appScreen()[1]*1/4);
				pub.tab(pub.appScreen()[0]/2,pub.appScreen()[1]*1/4);
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
		SystemHelper.sleep(2);
	}
	
	/**
	 * ��ǰҳ�棺�ֻ���¼ҳ��
	 * ����ҳ�棺�û���Ϣע��ҳ��
	 * @throws InterruptedException
	 */
	public void registerByPhone() throws InterruptedException{
		
	}
	
	/**
	 * ��ǰҳ�棺��¼ѡ�񵯴�
	 * ����ҳ�棺��ҳ
	 */
	public void loginByPhone() throws InterruptedException{
		WebElement phone = driver.findElementByName("�ֻ������¼/ע��");
		
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
