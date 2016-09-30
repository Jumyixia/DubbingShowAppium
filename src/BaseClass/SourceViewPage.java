/**
 * 
 */
package BaseClass;

import io.appium.java_client.android.AndroidDriver;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Util.SystemHelper;

public class SourceViewPage {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	
	public AndroidDriver driver;
	public PubClass pub = null;
	public BaseFunc basefunc = null;
	public DubbingPage dubbingpage = null;
	public int roletype = 0; //��¼��ǰ���е��ǵ�����ʺ�������
	public int guidetype = 1; //��ʾtips�Ƿ����1Ϊ���ڣ�0������
	
	By btn_more = null;
	
	By by_dubbing = null; 

	
	By by_sourcetime = null;
	By by_dubbingcount = null;
	
	
	
	public SourceViewPage(AndroidDriver driver, int guidetype){
		this.driver = driver;
		pub = new PubClass(driver);
		dubbingpage = new DubbingPage(driver, guidetype);
		this.guidetype = guidetype;
		
		btn_more = By.id("com.happyteam.dubbingshow:id/btn_jump_report_video");//���ϽǸ��ఴť
		by_dubbing = By.id("com.happyteam.dubbingshow:id/btnNowDubbing"); //������ť

		by_sourcetime = By.id("com.happyteam.dubbingshow:id/video_total_time");	//�ز�ʱ��
		by_dubbingcount = By.id("com.happyteam.dubbingshow:id/tvNowDubbing");	//��������
	}
	

	/**
	 * ������������
	 * 
	 */
	public boolean enterDubbing(){

		WebElement dubbingbtn = driver.findElement(by_dubbing);
		WebElement dubbingcount_text = driver.findElement(by_dubbingcount);
		String tag = dubbingcount_text.getText();
		
		dubbingbtn.click();
		if(tag.contains("����")){
			if (pub.isElementExist(By.id("com.happyteam.dubbingshow:id/action"), 600000)) {
				SystemHelper.sleep(2);
				return true;
			} else {
				System.out.println("enter dubbing failed.");
				return false;

			}
		}else{
			if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/record"),900000)){
				SystemHelper.sleep(2);
				return true;			
			}else{
				System.out.println("enter hydubbing failed.");
				return false;
				
			}
		}
		
	}

}
