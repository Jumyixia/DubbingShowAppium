package BaseClass;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class MesCenterPage {
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
	public AndroidDriver driver;
	public PubClass pub = null;
	public BaseFunc basefunc = null;
	
	By centericon = null;
	By contentedit = null;
	By sendbtn = null;
	
	public MesCenterPage(AndroidDriver driver){
		this.driver = driver;
		pub = new PubClass(driver);
		
		
		centericon = By.id("com.happyteam.dubbingshow:id/newsTab"); //切换到消息中心的按钮
		contentedit = By.id("com.happyteam.dubbingshow:id/editContent"); //聊天界面的输入框
		sendbtn = By.id("com.happyteam.dubbingshow:id/btnSend");
	}
	
	
	public void enterCenter(){
		driver.findElement(centericon).click();
	}
	
	public void enterSecendPage(By by){
		driver.findElement(by).click();
	}
	
	public void sentContent(String content){
		
		WebElement editText = driver.findElement(contentedit);
		WebElement sendBtn = driver.findElement(sendbtn);
		editText.click();
		System.out.println(content);
		editText.sendKeys(content);
		sendBtn.click();
	}
}
