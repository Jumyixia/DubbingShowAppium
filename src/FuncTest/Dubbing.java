package FuncTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import BaseClass.BaseFunc;
import BaseClass.DubbingPage;
import BaseClass.PubClass;
import BaseClass.UploadPage;
import BaseClass.VideoDetailPage;
import ObjectFactory.DriverFactory;
import io.appium.java_client.android.AndroidDriver;

public class Dubbing {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public PubClass pub = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public UploadPage uploadpage = null;
	public int guidetype = 1; //��ʾtips�Ƿ����1Ϊ���ڣ�0������
	
	public Dubbing(AndroidDriver driver,int guidetype){
		this.driver = driver;
		basefunc = new BaseFunc(driver,guidetype);
		pub = new PubClass(driver);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver,guidetype);
		uploadpage = new UploadPage(driver);
	}
	
	/**
	 * ����ҳ����Ƶ���б�ѡ��һ��Ƶ���������Ƶ���飬Ȼ���������
	 * ��������򿪱�����
	 * ������������������200����Ч������50%
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void testCase01() throws InterruptedException, ParseException{
		basefunc.enterFeature("Ƶ��");
		basefunc.enterChannel("����");
		basefunc.enterVideoDetail();
		videodetailpage.Dubbing();
		dubbingpage.DubbingSet(1, 0);
		dubbingpage.EnterViewPage(9000);//����ʱ�����Կ���
		dubbingpage.vol();
		dubbingpage.fx();
	}

	/**
	 * ѭ������
	 * ����ҳ�Ŀ�������������������
	 * ֱ����ͨ����
	 * ������������������200����Ч������50%
	 * ����ݸ����ѡ������һ�Ρ�
	 * @throws ParseException
	 * @throws InterruptedException
	 * @param times ѭ����������
	 */
	public void testCase03(int times) throws ParseException, InterruptedException{
		System.out.println("testCase03");
		basefunc.enterQuickDubbing(1);
		for (int i = 1; i <= times; i++) {
			System.out.println("No. " + i);
			dubbingpage.DubbingSet(1, 0);
			dubbingpage.EnterViewPage(0);// ����ʱ�����Կ���,0��ʾ¼��
			dubbingpage.vol();
			dubbingpage.fx();
			dubbingpage.enterUploadPage(1);
			uploadpage.SaveToDraft(1);
		}
	}
	
	/**
	 * ����ѭ������
	 * ����ҳ�Ŀ�������������������
	 * ֱ����ͨ����
	 * ������������������200����Ч������50%
	 * �����ɰ�ť�����汾���Լ��ݸ���
	 * @param times ѭ����������
	 * @throws ParseException
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void testCase04(int times) throws ParseException, InterruptedException, IOException{
		By by_dubbingbtn = By.id("com.happyteam.dubbingshow:id/dubbing");
		System.out.println("testCase04");		
		for (int i = 1; i <= times; i++) {
			basefunc.enterQuickDubbing(0);
	
			driver.findElement(by_dubbingbtn).click();
			dubbingpage.DubbingSet(0, 0);
			dubbingpage.EnterViewPage(15000);// ����ʱ�����Կ���,0��ʾ¼��
			dubbingpage.vol();
			dubbingpage.fx();
			dubbingpage.enterUploadPage(3);		
			
			
		}
	}
	
	/**
	 * ����ģʽ�£�ʵ������������ʵ�����պϳɵ�ʱ��
	 * ѭ�������仺���е��ز�
	 * Ԥ���������������������Ч
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public void testCase05() throws ParseException, InterruptedException{
		By by_dubbingbtn = By.id("com.happyteam.dubbingshow:id/dubbing");
		List<WebElement> dubbinglist = null;
		for(int i=0; i < 2; i++){
			System.out.print("No. " + i + ":  ");
			
			basefunc.enterQuickDubbing(0);	
			driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
			dubbinglist = driver.findElements(by_dubbingbtn);
			WebElement DUB = driver.findElement(by_dubbingbtn);
			dubbinglist.get(i).click();
			dubbingpage.DubbingSet(0, 1);
			dubbingpage.EnterViewPage(0);// ����ʱ�����Կ���,0��ʾ¼��
			dubbingpage.vol();
			dubbingpage.fx();
			dubbingpage.enterUploadPage(3);		
		}
		pub.pullFile("time.txt");
		
		
	}

	/**
	 * 
	 */
	public void testCase06() {
		// TODO Auto-generated method stub
		pub.WriteinFile("1234567890", "123.txy");
	}
	
	
}