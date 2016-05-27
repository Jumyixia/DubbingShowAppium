package BaseClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;


import io.appium.java_client.android.AndroidDriver;

public class DubbingPage {
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	
	public AndroidDriver driver;
	public PubClass pub = null;
	public BaseFunc basefunc = null;
	
	//	��������	
	By by_back = null;
	By by_headset = null;
	By by_living = null;
	By by_dubbing = null;
	By by_script_container = null;
	By by_scirpt_count = null;
	By by_coopera = null; 
	By by_play = null;
	By by_video_time = null;
	By by_complete = null;
	By by_endconfirm = null;
	//  Ԥ������
	By by_review_title = null;
	By by_dubbingSeekbar = null;
	By by_fx = null;
	By by_mix = null;
	By by_roomsize = null;
	By by_echo = null;
	By by_vol = null;
	By by_bgfx = null;
	By by_bgvol = null;
	By by_guide = null;
	
	public DubbingPage(AndroidDriver driver){
		this.driver = driver;
		pub = new PubClass(driver);
		
		//���水ť��ʼ��--��������
		by_back = By.id("com.happyteam.dubbingshow:id/back");		//����
		by_headset = By.id("com.happyteam.dubbingshow:id/headset");	//������
		by_dubbing = By.id("com.happyteam.dubbingshow:id/action");	//����
		by_living = By.id("com.happyteam.dubbingshow:id/living");	//ʵ��
		by_script_container = By.id("com.happyteam.dubbingshow:id/script_container");	//̨��
		by_scirpt_count = By.id("com.happyteam.dubbingshow:id/scirpt_count");	//̨������
		by_coopera = By.id("com.happyteam.dubbingshow:id/coopera");	//����
		by_play = By.id("com.happyteam.dubbingshow:id/play");	//ԭ������
		by_video_time = By.id("com.happyteam.dubbingshow:id/video_time");	//��Ƶʱ��
		by_complete = By.id("com.happyteam.dubbingshow:id/complete");	//��ɰ�ť
		by_endconfirm = By.id("com.happyteam.dubbingshow:id/btnSubmit"); 	// �����ɰ�ťʱ������ѯ�ʵ�����ȷ����ť
		//���水ť��ʼ��--Ԥ������
		by_review_title = By.name("Ԥ��");
		by_guide  = By.id("com.happyteam.dubbingshow:id/btnGuideFixStart");
		by_dubbingSeekbar = By.id("com.happyteam.dubbingshow:id/dubbingSeekbar"); //����������������
		by_vol = By.id("com.happyteam.dubbingshow:id/vol"); 	//��������
		by_fx =By.id("com.happyteam.dubbingshow:id/fx");	//������Ч
		by_mix = By.id("com.happyteam.dubbingshow:id/mix");	//��������
		by_roomsize = By.id("com.happyteam.dubbingshow:id/roomsize");	//�����ռ�
		by_echo = By.id("com.happyteam.dubbingshow:id/echo");	//��������
		
		by_bgfx = By.id("com.happyteam.dubbingshow:id/bgfx");
		by_bgvol = By.id("com.happyteam.dubbingshow:id/bgvol");
		
	}

	//����
	public void Dubbing(){
		WebElement dubbing_btn = driver.findElement(by_dubbing);
		dubbing_btn.click();
	}
	
	/**
	 * ������������
	 * @param headset	1Ϊ��Ҫ���� 0Ϊ��Ҫ�ر�
	 * @param living	1Ϊ��Ҫ���� 0Ϊ��Ҫ�ر�
	 */
	public void DubbingSet(int headset, int living){
		this.Headset(headset);
		this.Living(living);
	}

	
	/**
	 * @param set 1Ϊ��Ҫ���� 0Ϊ��Ҫ�ر�
	 */
	public void Headset(int set){
		WebElement headset = driver.findElement(by_headset);
		switch (set) {
		case 1:
			if(!headset.isSelected()){
				headset.click();
				By by_sub = By.id("com.happyteam.dubbingshow:id/btnSubmit");				
				if(pub.isElementExist(by_sub)){
					driver.findElement(by_sub).click();
				}
			}
			break;
		case 0:
			if(headset.isSelected()){
				headset.click();
			}			
			break;
		default:
			break;
		}
	}
	
	/**
	 * @param set 1Ϊ��Ҫ���� 0Ϊ��Ҫ�ر�
	 */
	public void Living(int set){
		WebElement linving = driver.findElement(by_living);
		switch (set) {
		case 1:
			if(!linving.isSelected()){
				linving.click();
				By by_sub = By.name("����");				
				if(pub.isElementExist(by_sub)){
					driver.findElement(by_sub).click();
					if(pub.isElementExist(by_sub)){
						driver.findElement(by_sub).click();
					}
				}
			}
			break;
		case 0:
			if(linving.isSelected()){
				linving.click();
			}			
			break;
		default:
			break;
		}
	}
	
	
	
	/*
	 * ��ɽ���Ԥ������
	 * dubbing_time: <8000 || >=�ز�ʱ�� ��ֱ�ӵ���������ֱ���Զ���ת������ҳ��
	 * dubbing_time: >=8000 || <�ز�ʱ�� ��dubbing_time��ʱ������ɰ�ť
	 */
	public void EnterViewPage(int dubbing_time) throws ParseException, InterruptedException{
		int video_time_int = 0;//�ز�ʱ��ת���ɰ����	
		String video_time,time1,time2 = null;
		
		System.out.println("-----------EnterViewPage.");
		System.out.println(dubbing_time);
		
		WebElement dubbing_btn = driver.findElement(by_dubbing);
		WebElement complete_btn = null;
		WebElement endconfirm_btn = null;
		dubbing_btn.click();

		video_time = driver.findElement(by_video_time).getText();

		time1 = video_time.substring(6,8);
		time2 = video_time.substring(9,11);
		video_time_int = Integer.parseInt(time1)*60000 + Integer.parseInt(time2)*1000;

		System.out.println("video_time_int: " + video_time_int);
		
		System.out.println("��ɼ���"+df.format(new Date()));
		
		Thread.sleep(3000);

		//��ʼ���������
		if(dubbing_time >= 8000 && dubbing_time <= video_time_int){

			Thread.sleep(dubbing_time);
			
			pub.tab(890, 1650);
		
			if(pub.isElementExist(by_endconfirm)){//�������ѯ�ʵ�������ȷ����ť
				endconfirm_btn = driver.findElement(by_endconfirm);
				endconfirm_btn.click();	
			}
			
		}else if(dubbing_time < 8000){
			
			System.out.println(1);
	
			Thread.sleep(dubbing_time);
			//�Զ������ɰ�ť�ȴ��ϳ�
			System.out.println(df.format(new Date()));

		}else if(dubbing_time >= video_time_int){
			System.out.println(11);
			Thread.sleep(dubbing_time );
			complete_btn = driver.findElement(by_complete);
			System.out.println(df.format(new Date()));
			complete_btn.click();
			if(pub.isElementExist(by_endconfirm)){//�������ѯ�ʵ�������ȷ����ť
				endconfirm_btn = driver.findElement(by_endconfirm);
				endconfirm_btn.click();	
			}

		}
			pub.isElementExist(by_review_title,30000);
			WebElement guide = driver.findElement(by_guide);
			guide.click();
			System.out.println("enter yulan");		
		
	}
	
	/**
	 * @category ����������Ч����Ϊ50%
	 */
	public void fx(){
		
		WebElement fx = driver.findElement(by_fx);
		fx.click();
		
		WebElement mix = driver.findElement(by_mix);
		WebElement roomsize = driver.findElement(by_roomsize);	
		WebElement echo = driver.findElement(by_echo);
	
		pub.tapOnElement(mix, 10, 11, 20);
		pub.tapOnElement(roomsize, 10, 11, 20);
		pub.tapOnElement(echo, 5, 11, 20);
	}
	
	//ֱ�ӽ�����������200
	public void vol(){
		System.out.println("-----------begin vol");
		WebElement dubbingSeekbar = driver.findElement(by_dubbingSeekbar);
		pub.swipeOnElement(dubbingSeekbar, "Down", 1000);
		pub.swipeOnElement(dubbingSeekbar, "Down", 1000);
	}
	
	public void ViewPage(){
		
	}
	
	/**
	 * ����Ԥ�����������
	 * @param type 1����/�������  2������� 3����
	 */
	public void enterUploadPage(int type){
		WebElement complete = driver.findElement(by_complete);
		complete.click();
		basefunc.LoginCheck();
		if(type == 1){
			//ͨ���ж��ϴ�ҳ���ֶΣ�ȷ���Ƿ�����ϴ�����
		}
		
	}
		
		
}
