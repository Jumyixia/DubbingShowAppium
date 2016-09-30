package FuncTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import BaseClass.BaseFunc;
import BaseClass.CirclePage;
import BaseClass.DubbingPage;
import BaseClass.PubClass;
import BaseClass.SourceViewPage;
import BaseClass.UploadPage;
import BaseClass.VideoDetailPage;
import ObjectFactory.DriverFactory;
import Util.SystemHelper;
import io.appium.java_client.android.AndroidDriver;

public class Circle {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public PubClass pub = null;
	public VideoDetailPage videodetailpage = null;
	public CirclePage circlepage = null;
	public int guidetype = 1; //提示tips是否存在1为存在，0不存在
	private By by_source;
	
	public Circle(AndroidDriver driver,int guidetype){
		this.driver = driver;
		basefunc = new BaseFunc(driver,guidetype);
		pub = new PubClass(driver);
		videodetailpage = new VideoDetailPage(driver);
		circlepage = new CirclePage(driver, guidetype);
		
		by_source = By.id("com.happyteam.dubbingshow:id/container");	//素材库一个素材项
	}
	
	/**
	 * 从首页进入圈子，然后在圈子列表随机选择一个圈子进入圈子详情
	 * 在圈子详情上滑，然后随机选择一个帖子进入帖子详情
	 * 在帖子详情设置为倒序看帖（需要验证倒序看帖是否正确？）
	 * 进行跟帖操作
	 * 一段文字、一个表情、一段语音
	 * 然后发送，然后回到跟帖详情
	 * 然后一直返回到首页
	 */
	public void testCase01(){
		circlepage.enterCircleDetailByRandom();
		circlepage.FollowPostByDesc();
	}
}