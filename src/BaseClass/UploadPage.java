/**
 * @author Jum: 
 * @version ����ʱ�䣺2016��5��26�� ����9:31:43 
 */
package BaseClass;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Util.SystemHelper;

import io.appium.java_client.android.AndroidDriver;

public class UploadPage {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	
	public AndroidDriver driver;
	public PubClass pub;
	
	By by_backbtn = null;
	By by_saveToDraft = null;
	By by_title = null;
	By by_pri_switch_tv = null;
	By by_funny = null;
	By by_imitation = null;
	By by_japan = null;
	By by_english = null;
	By by_local = null;
	By by_dongman = null;
	By by_read = null;
	By by_share_sina_cb = null;
	By by_share_wechat_cb = null;
	By by_savebtn = null;
	By by_uploadbtn = null;
	By by_btnCancel = null;
	By by_btnSubmit = null;
	
	public UploadPage(AndroidDriver driver){
		this.driver = driver;
		pub = new PubClass(driver);
		
		by_backbtn = By.id("com.happyteam.dubbingshow:id/btnBack");
		by_saveToDraft = By.id("com.happyteam.dubbingshow:id/saveToDraft");
		by_title = By.id("com.happyteam.dubbingshow:id/title");
		by_pri_switch_tv = By.id("com.happyteam.dubbingshow:id/pri_switch_tv");
		by_funny = By.id("com.happyteam.dubbingshow:id/funny");	//���
		by_imitation = By.id("com.happyteam.dubbingshow:id/imitation");	//ģ��
		by_japan = By.id("com.happyteam.dubbingshow:id/japan");	//����
		by_english = By.id("com.happyteam.dubbingshow:id/english");	//Ӣ��
		by_local = By.id("com.happyteam.dubbingshow:id/local");	//����
		by_dongman = By.id("com.happyteam.dubbingshow:id/dongman");	//����
		by_read = By.id("com.happyteam.dubbingshow:id/read");	//����
		by_share_sina_cb = By.id("com.happyteam.dubbingshow:id/share_sina_cb");	//����-����΢��
		by_share_wechat_cb = By.id("com.happyteam.dubbingshow:id/share_wechat_cb");	//����-����Ȧ
		by_savebtn = By.id("com.happyteam.dubbingshow:id/savebtn");	//���浽����
		by_uploadbtn = By.id("com.happyteam.dubbingshow:id/uploadbtn");	//�ϴ���Ʒ
		by_btnCancel = By.id("com.happyteam.dubbingshow:id/btnCancel");	//������ȡ����ť
		by_btnSubmit = By.id("com.happyteam.dubbingshow:id/btnSubmit");	//������ȷ����ť
		//by_btnSubmit = By.id("");
		
	}
	
	/**
	 * 
	 * @param again �Ƿ��ٴ�����0��1��
	 */
	public void SaveToDraft(int again){
		WebElement savetodraft = driver.findElement(by_saveToDraft);
		savetodraft.click();
		if(again == 1){
			WebElement submit = driver.findElement(by_btnSubmit);
			submit.click();
			if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/action"),600000)){
				SystemHelper.sleep(2);				
			}else{
				System.out.println("enter dubbing failed.");
			}
		}else {
			WebElement cancel = driver.findElement(by_btnCancel);
			cancel.click();
		}
	}
	
	/**
	 * ���浽����
	 */
	public void SaveToLocal(){
		WebElement savebtn = driver.findElement(by_savebtn);
		savebtn.click();
		if(pub.isElementExist(by_btnSubmit, 600000)){
			WebElement submit = driver.findElement(by_btnSubmit);
			submit.click();
		}		
	}
	
	/**
	 * �ϴ�����δ�����������
	 */
	public void Upload(){
		WebElement uploadbtn = driver.findElement(by_uploadbtn);
		uploadbtn.click();
		WebElement submit = driver.findElement(by_btnSubmit);
		submit.click();
		if(pub.isElementExist(by_btnSubmit, 600000)){
			submit = driver.findElement(by_btnSubmit);
			submit.click();
		}
	}
	
}
