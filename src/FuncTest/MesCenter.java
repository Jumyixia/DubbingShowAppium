package FuncTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import BaseClass.BaseFunc;
import BaseClass.DubbingPage;
import BaseClass.MesCenterPage;
import BaseClass.PubClass;
import BaseClass.UploadPage;
import BaseClass.VideoDetailPage;
import ObjectFactory.DriverFactory;
import Util.SystemHelper;
import io.appium.java_client.android.AndroidDriver;

public class MesCenter {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public PubClass pub = null;
	public MesCenterPage mescenter = null;
	
	public int guidetype = 1; //提示tips是否存在1为存在，0不存在
	
	public MesCenter(AndroidDriver driver,int guidetype){
		this.driver = driver;
		basefunc = new BaseFunc(driver,guidetype);
		pub = new PubClass(driver);
		mescenter = new MesCenterPage(driver);
	}
	
	public void SentMesTosb(){
		mescenter.enterCenter();
		mescenter.enterSecendPage(By.name("超人音乐学院声乐系"));
		for(int i = 0; i < 50; i++){
			System.out.print(i+"---");
			mescenter.sentContent(i+"");			
		}
	}
}
