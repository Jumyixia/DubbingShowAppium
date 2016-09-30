package BaseClass;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ObjectFactory.DriverFactory;
import Util.SystemHelper;
import io.appium.java_client.android.AndroidDriver;

public class DetailPage {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public PubClass pub = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public UploadPage uploadpage = null;
	public SourceViewPage srview = null;
	public int guidetype = 1; //��ʾtips�Ƿ����1Ϊ���ڣ�0������
	
	public int film_time = 0;
	
	private By by_source;
	private By by_homepagelist;
	private By by_homefilmcontainer;
	private By by_filmtime;
	private By by_back;
	private By by_wait;
	private By by_endtag;
	private By by_play;
	
	
	public DetailPage(AndroidDriver driver,int guidetype){
		this.driver = driver;
		basefunc = new BaseFunc(driver,guidetype);
		pub = new PubClass(driver);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver,guidetype);
		uploadpage = new UploadPage(driver);
		srview = new SourceViewPage(driver,guidetype );
		
		by_source = By.id("com.happyteam.dubbingshow:id/container");	//�زĿ�һ���ز���
		by_homepagelist = By.id("com.happyteam.dubbingshow:id/list");	//��ҳ�����б�
		by_homefilmcontainer = By.id("com.happyteam.dubbingshow:id/film_common_container");	//��ҳ�����б��е���Ƶ
		by_filmtime = By.id("com.happyteam.dubbingshow:id/filmTime");	//��Ƶ�����ϵ�ʱ��
		
		//��Ƶ����ҳ
		by_back = By.id("com.happyteam.dubbingshow:id/btnBack");	//���ذ�ť
		by_wait = By.id("com.happyteam.dubbingshow:id/media_anim_image_view");	//��Ƶ�ϵ����ڼ��ض���
		by_play = By.id("com.happyteam.dubbingshow:id/video_play_btn");	//���Ű�ť
		by_endtag = By.id("com.happyteam.dubbingshow:id/linear_cb");	//������ɺ���ز���ť
	}
	
	/**
	 * ��δ��������������ù����ݲ����ã�����ò��С�����
	 * ����Ƶ�б��ȡһ����Ƶ��������Ƶ����
	 * ����Ƶ���������Ƶ������Ȼ���˳�
	 * �����Ƶ�޷�������ȴ�30����˳�
	 */
	public void viewFilmFromHomeToEnd(){
		WebElement homelist = driver.findElement(by_homepagelist);
		//List<WebElement> homefilm = homelist.findElements(by_homefilmcontainer);
		List<WebElement> time = homelist.findElements(by_filmtime); //��ѡ����ʱ������Ƶ
		System.out.println("==="+time.size());
		int count = time.size();
		WebElement targetfilm = time.get(pub.getrandom(count));	//�ӵ�ǰҳ�����ѡ��һ����Ƶ
		// ����ѡ����Ƶ��ʱ��
		String time1 = targetfilm.getText().substring(0, 2);
		String time2 =targetfilm.getText().substring(3, 5);
		film_time = Integer.parseInt(time1) * 60000 + Integer.parseInt(time2) * 1000;
		
		targetfilm.click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 
		//	����ҳ�Ŀؼ�
		WebElement back_btn = driver.findElement(by_back);
		//WebElement cb_btn = driver.findElement(by_endtag);
		

		if(pub.waitUntilDisappear(by_wait,30)){
			if(pub.isElementExist(by_play)){
				System.out.println("no start ");
				driver.findElement(by_play).click();
				SystemHelper.sleep(film_time);
			}else{
				System.out.println("start");
				SystemHelper.sleep(film_time-30);
			}
			if(pub.isElementExist(by_endtag, 10)){
				System.out.println("film is end.");
			}else{
				System.out.println("some error.");
			}			
		}else{			
			System.out.println("film loading failed.");
		}
		
		back_btn.click();
		SystemHelper.sleep(1);
	}
	
	/**
	 * ����ҳ���ѡ��һ����Ƶ��������Ƶ����ҳ
	 * ����в��Ű�ť�������Ű�ť
	 * ���ź�ȴ�50��Ȼ���˳�
	 */
	public void viewFilmFromHome(){
		WebElement homelist = driver.findElement(by_homepagelist);
		List<WebElement> homefilm = homelist.findElements(by_homefilmcontainer);
		//List<WebElement> time = homelist.findElements(by_filmtime); //��ѡ����ʱ������Ƶ
		int count = homefilm.size();
		WebElement targetfilm = homefilm.get(pub.getrandom(count));	//�ӵ�ǰҳ�����ѡ��һ����Ƶ
		// ����ѡ����Ƶ��ʱ��
		//String time1 = targetfilm.getText().substring(0, 2);
		//String time2 =targetfilm.getText().substring(3, 5);
		//film_time = Integer.parseInt(time1) * 60000 + Integer.parseInt(time2) * 1000;
		
		targetfilm.click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 
		//	����ҳ�Ŀؼ�
		WebElement back_btn = driver.findElement(by_back);
		//WebElement cb_btn = driver.findElement(by_endtag);
		

			if(pub.isElementExist(by_play,10)){
				System.out.println("no start ");
				driver.findElement(by_play).click();
				SystemHelper.sleep(30);
			}else{
				System.out.println("start");
				SystemHelper.sleep(30);
			}
			if(pub.isElementExist(by_endtag, 10)){
				System.out.println("film is end.");
			}else{
				System.out.println("error or no end.");
			}			
			
		back_btn.click();
		SystemHelper.sleep(1);
	}
}
