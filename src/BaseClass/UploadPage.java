/**
 * @author Jum: 
 * @version 创建时间：2016年5月26日 下午9:31:43 
 */
package BaseClass;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Util.SystemHelper;

import io.appium.java_client.android.AndroidDriver;

public class UploadPage {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
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
		by_funny = By.id("com.happyteam.dubbingshow:id/funny");	//恶搞
		by_imitation = By.id("com.happyteam.dubbingshow:id/imitation");	//模仿
		by_japan = By.id("com.happyteam.dubbingshow:id/japan");	//日语
		by_english = By.id("com.happyteam.dubbingshow:id/english");	//英语
		by_local = By.id("com.happyteam.dubbingshow:id/local");	//方言
		by_dongman = By.id("com.happyteam.dubbingshow:id/dongman");	//动漫
		by_read = By.id("com.happyteam.dubbingshow:id/read");	//朗诵
		by_share_sina_cb = By.id("com.happyteam.dubbingshow:id/share_sina_cb");	//分享-新浪微博
		by_share_wechat_cb = By.id("com.happyteam.dubbingshow:id/share_wechat_cb");	//分享-朋友圈
		by_savebtn = By.id("com.happyteam.dubbingshow:id/savebtn");	//保存到本地
		by_uploadbtn = By.id("com.happyteam.dubbingshow:id/uploadbtn");	//上次作品
		by_btnCancel = By.id("com.happyteam.dubbingshow:id/btnCancel");	//弹窗的取消按钮
		by_btnSubmit = By.id("com.happyteam.dubbingshow:id/btnSubmit");	//弹窗的确定按钮
		//by_btnSubmit = By.id("");
		
	}
	
	/**
	 * 
	 * @param again 是否再次配音0否，1是
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
	 * 保存到本地
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
	 * 上传，暂未考虑网络情况
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
