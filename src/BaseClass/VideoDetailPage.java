package BaseClass;

import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ObjectFactory.DriverFactory;
import Util.SystemHelper;

import io.appium.java_client.android.AndroidDriver;

public class VideoDetailPage {
/*
 * role_name��ɫ����/�ز�������

dubbing_name������

btn_video_detail_dubbing������ť

video_detail_praise���ް�ť

 */

	public AndroidDriver driver;
	public PubClass pub= null;
	
	public VideoDetailPage(AndroidDriver driver){
		this.driver = driver;
		pub= new PubClass(driver);
	}
	
	
	//��������
	public void Dubbing() throws InterruptedException{
		System.out.println("-----------enter dubbing");
		WebElement dubbing_btn = driver.findElementById("com.happyteam.dubbingshow:id/btn_video_detail_dubbing");
		dubbing_btn.click();
		if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/action"),600000)){
			SystemHelper.sleep(2);
			pub.tab(pub.appScreen()[0]/2,pub.appScreen()[1]/2);
		}else{
			System.out.println("enter dubbing failed.");
		}		
			
	}
	
	
	//�ж��ز��Ƿ�������
	public boolean isDubbing(){
		By by = By.id("com.happyteam.dubbingshow:id/btn_video_detail_dubbing");
		if(pub.isElementExist(by)){
			return true;
		}else{
			return false;
		}
		
	}
	
	public int getRoleCount(){
		int role_count = 0;
		
		List<WebElement> roles = driver.findElementsById("com.happyteam.dubbingshow:id/role_name");
		role_count = roles.size();
				
		return role_count-1;
	}
	
}
