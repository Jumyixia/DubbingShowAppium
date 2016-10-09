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
import BaseClass.SourceViewPage;
import BaseClass.UploadPage;
import BaseClass.VideoDetailPage;
import ObjectFactory.DriverFactory;
import Util.SystemHelper;
import io.appium.java_client.android.AndroidDriver;

public class Dubbing {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public PubClass pub = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public UploadPage uploadpage = null;
	public SourceViewPage srview = null;
	public int guidetype = 1; //��ʾtips�Ƿ����1Ϊ���ڣ�0������
	private By by_source;
	
	public Dubbing(AndroidDriver driver,int guidetype){
		this.driver = driver;
		basefunc = new BaseFunc(driver,guidetype);
		pub = new PubClass(driver);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver,guidetype);
		uploadpage = new UploadPage(driver);
		srview = new SourceViewPage(driver,guidetype );
		
		by_source = By.id("com.happyteam.dubbingshow:id/container");	//�زĿ�һ���ز���
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
	 * 1. ����ҳ�����زĿ�
	 * 2. �زĿ��ϻ���ѡ��������Ƶ�����ز�Ԥ��Ȼ�������������
	 * 3. ����һ��ʱ���������������������
	 * 4. �ڵ�����������������������������Ч
	 * 5. �����ɽ����ϴ����棬�ϴ��ɹ����ص���ҳ 
	 */
	public void testCase06() {
		basefunc.enterSomeFunc("2");
		if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/btnSearch"),10)){//ͨ���ж��Ƿ����������ť���ж��Ƿ�����زĿ�
			pub.swipeToUp(500);
			SystemHelper.sleep(1);
			List<WebElement> sourcelist = driver.findElements(by_source);
			int count = pub.getrandomWithoutZero(sourcelist.size());
			sourcelist.get(count).click();
			
			if(srview.enterDubbing()){
				//ȥ������ҳ
				pub.tab(pub.appScreen()[0]/2,pub.appScreen()[1]*1/4);
				pub.tab(pub.appScreen()[0]/2,pub.appScreen()[1]*1/4);
				pub.tab(pub.appScreen()[0]/2,pub.appScreen()[1]*1/4);
				
				try {
					dubbingpage.EnterViewPage(10);
					dubbingpage.vol();
					dubbingpage.pitch();
					dubbingpage.fx();
					dubbingpage.enterUploadPage(1);	
					uploadpage.Upload();
					
					if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/btnBack"))){
						driver.findElement(By.id("com.happyteam.dubbingshow:id/btnBack")).click();
						if(!pub.isElementExist(By.name("����"))){
							System.out.println("back to home failed.");
						}
					}else{
						System.out.println("upload back to sourcePage failed.");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("enter sourcelib failed.");
		}
	}
	
	/**
	 * 1. ����ҳ+�Ž���Ȥζ����
	 * 2. Ȥζ����ҳ�濪��ʵ������ʼ������
	 * 3. ������ɺ��Զ������������
	 * 4. ��������������������������ѡ��ͨ����������������
	 * 5. ��������������󣬵����ɰ�ť�����ϴ�ҳ�棬�ϴ���Ʒ������ҳ
	 */
	public void testCase07() {
		basefunc.enterSomeFunc("1");
		if(basefunc.enterQuickDubbing(1)){//������������ɹ�
			dubbingpage.Living(1);
			try {
				dubbingpage.EnterViewPage(0);
				dubbingpage.addBg(1);
				dubbingpage.bgvol();

				dubbingpage.enterUploadPage(1);
				uploadpage.Upload();

				
				if (!pub.isElementExist(By.name("����"))) {
					System.out.println("back to home failed.");
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {
			System.out.println("enter qwpy failed.");
		}
	}
	
	
	/**
	 * ��������
	 * 1. ����ҳ����+�Ž�������ز��б�
	 * 2. �ϻ�ѡ���زĽ����ز�Ԥ�����棬������������case �������Ƿ���˫���زģ���Ĭ��Ϊ���ˣ�
	 * 3. ���ÿ�����������Ȼ��ʼ����
	 * 4. �������������������
	 * 5. �ڵ�����������ɰ�ť�����ϴ�ҳ�棬���ϴ���������ϴ����ص��زĿ�
	 * 6. �زĿ������أ����ص���ҳ
	 */
	public void testCase08(){
		basefunc.enterSomeFunc("3");
		if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/btnSearch"),10)){//ͨ���ж��Ƿ����������ť���ж��Ƿ�����زĿ�
			pub.swipeToUp(500);
			SystemHelper.sleep(1);
			WebElement hysource = driver.findElement(by_source);
			//	�˴�������ز�û����ʾʱ������ᱨ��
			WebElement hysourcetime = hysource.findElement(By.id("com.happyteam.dubbingshow:id/tv_time"));
			dubbingpage.hysourcetime = hysourcetime.getText().trim();
			hysource.click();
			
			if(srview.enterDubbing()){
				//ȥ������ҳ
				pub.tab(pub.appScreen()[0]/2,pub.appScreen()[1]/2);
				
				try {
					dubbingpage.HyEnterViewPage();
					
					dubbingpage.enterUploadPage(1);	
					uploadpage.Upload();
					
					if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/btnBack"))){
						driver.findElement(By.id("com.happyteam.dubbingshow:id/btnBack")).click();
						if(!pub.isElementExist(By.name("����"))){
							System.out.println("back to home failed.");
						}
					}else{
						System.out.println("upload back to sourcePage failed.");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}else {
			System.out.println("enter sourcelib failed.");
		}
	}
	
	
}