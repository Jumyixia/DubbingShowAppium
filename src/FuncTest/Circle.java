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
import Util.DriverFactory;
import Util.Console;
import Util.SystemHelper;
import io.appium.java_client.android.AndroidDriver;

public class Circle {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public PubClass pub = null;
	public VideoDetailPage videodetailpage = null;
	public CirclePage circlepage = null;
	public Console cs = null;
	public int guidetype = 1; //��ʾtips�Ƿ����1Ϊ���ڣ�0������
	private By by_source;
	private By by_backbtn;
	private By by_homebtn;
	
	public Circle(AndroidDriver driver,int guidetype){
		this.driver = driver;
		basefunc = new BaseFunc(driver,guidetype);
		pub = new PubClass(driver);
		videodetailpage = new VideoDetailPage(driver);
		circlepage = new CirclePage(driver, guidetype);
		
		by_source = By.id("com.happyteam.dubbingshow:id/container");	//�زĿ�һ���ز���
		by_backbtn = By.id("com.happyteam.dubbingshow:id/btnBack");	//���ذ�ť
		by_homebtn = By.id("com.happyteam.dubbingshow:id/dubbingTab");	//��ҳ��ť
		cs = new Console();
	}
	
	/**
	 * ����ҳ����Ȧ�ӣ�Ȼ����Ȧ���б����ѡ��һ��Ȧ�ӽ���Ȧ������
	 * ��Ȧ�������ϻ���Ȼ�����ѡ��һ�����ӽ�����������
	 * ��������������Ϊ����������Ҫ��֤�������Ƿ���ȷ����
	 * ���и�������
	 * һ�����֡�һ������//��֧���������
	 * Ȼ���ͣ�Ȼ��ص���������
	 * Ȼ��һֱ���ص���ҳ
	 */
	public void testCase01(){
		String comment_text = "good. haha";	//��֧����������
		circlepage.enterCircleDetailByRandom();
		circlepage.enterFollowPostDetailByRandom();
		circlepage.CommentPost(comment_text,0,0,2,0);
		cs.errorLog("circle 01");
		if(circlepage.SubComment()){
			System.out.println("circle 01---back");
			driver.findElement(by_backbtn).click();	//��������
			driver.findElement(by_backbtn).click();	//Ȧ������
			driver.findElement(by_homebtn).click();	//��ҳ
		}else{
		cs.errorLog("circle 01----fail");
		}
	}
	
	public void testCase02(){
		cs.errorLog("circle 02");
		circlepage.enterCircleDetailByRandom();
		circlepage.enterFollowPostDetailByRandom();
		circlepage.FollowPostByDesc();
		cs.errorLog("circle 02 --");
		driver.findElement(by_backbtn).click();	//��������
		driver.findElement(by_backbtn).click();	//Ȧ������
		driver.findElement(by_homebtn).click();	//��ҳ
		
	}
}