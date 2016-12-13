package FuncTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import BaseClass.BaseFunc;
import BaseClass.DubbingPage;
import BaseClass.PubClass;
import BaseClass.SourceViewPage;
import BaseClass.UploadPage;
import BaseClass.VideoDetailPage;
import Util.Console;
import Util.SystemHelper;
import Util.DriverFactory;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;

public class Dubbing {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public PubClass pub = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public UploadPage uploadpage = null;
	public SourceViewPage srview = null;
	public Console cs = null;
	public int guidetype = 1; //��ʾtips�Ƿ����1Ϊ���ڣ�0������
	private By by_source;
	private By by_qwpy;
	private By by_bottombar;
	private By by_btnback;
	
	public Dubbing(AndroidDriver driver,int guidetype){
		this.driver = driver;
		basefunc = new BaseFunc(driver,guidetype);
		pub = new PubClass(driver);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver,guidetype);
		uploadpage = new UploadPage(driver);
		srview = new SourceViewPage(driver,guidetype );
		cs = new Console();
		
		by_source = By.id("com.happyteam.dubbingshow:id/container");	//�زĿ�һ���ز���
		by_qwpy = By.id("com.happyteam.dubbingshow:id/btnPeiyin");	//�زĿ��Ȥζ����
		by_bottombar = By.id("com.happyteam.dubbingshow:id/layoutBottom");	//��ҳ�׶˵�bar
		by_btnback  = By.id("com.happyteam.dubbingshow:id/btnBack");	//�زĿ����ķ��ذ�ť
	}
	
	/**
	 * ����ҳ����Ƶ���б�ѡ��һ��Ƶ���������Ƶ���飬Ȼ���������
	 * ��������򿪱�����
	 * ������������������200����Ч������50%
	 * �ϳɺ󱣴�ݸ����˻ص���Ƶ����ҳ
	 * ���ԣ��ж���Ƶ����ҳ������ť�Ƿ����
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void testCase01(String channelname,int headset,int living, int dubbingtime ,int dubbingtype, int uploadtype ) throws InterruptedException, ParseException{
		
		basefunc.enterChannel(channelname);
		basefunc.enterVideoDetail();
		videodetailpage.Dubbing();
		dubbingpage.DubbingSet(headset,living);
		dubbingpage.EnterViewPage(dubbingtime);//����ʱ�����Կ���
		dubbingpage.vol();
		dubbingpage.fx();
		dubbingpage.enterUploadPage(dubbingtype);
		uploadpage.SaveToDraft(uploadtype);
		Assert.assertTrue(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/send_gift")));
		
		//����Ƶ����ҳ�˻ص���ҳ
		cs.errorLog("asseer .....");
		driver.findElement(By.id("com.happyteam.dubbingshow:id/btnBack")).click();
		driver.findElement(By.id("com.happyteam.dubbingshow:id/btnBack")).click();
	}

	/**
	 * ѭ������ ����ҳ�Ŀ������������������� ֱ����ͨ���� ������������������200����Ч������50% ����ݸ����ѡ������һ�Ρ�
	 * ���һ������ѡ�񱣴�ݸ�����˳�����
	 * ���ԣ��Ƿ��ص��زĿ�
	 * @throws ParseException
	 * @throws InterruptedException
	 * @param times
	 *            ѭ����������
	 */
	public void testCase03(int times) throws ParseException,InterruptedException {
		System.out.println("testCase03");
		basefunc.enterSomeFunc("1");
		if (basefunc.enterQuickDubbing()) {// ������������ɹ�
			for (int i = 1; i <= times; i++) {
				System.out.println("No. " + i);
				dubbingpage.DubbingSet(1, 0);
				dubbingpage.EnterViewPage(0);// ����ʱ�����Կ���,0��ʾ¼��
				dubbingpage.vol();
				dubbingpage.fx();
				dubbingpage.enterUploadPage(1);
				if (i == times) {
					uploadpage.SaveToDraft(0);
				} else {
					uploadpage.SaveToDraft(1);
				}
			}

			Assert.assertTrue(pub.isElementExist(by_qwpy));

			driver.findElement(By.id("com.happyteam.dubbingshow:id/btnBack")).click();
		}else{
			System.out.println("enter qwpy failed.");
		}
	}
	
	/**
	 * ���������ã���������£��޷��������������
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
			basefunc.enterQuickDubbing();
	
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
	 * �ϳɺ󱣴�ݸ���ص��زĿ�
	 * ���ԣ�����Ƿ�ص����زĿ�
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public void testCase05() throws ParseException, InterruptedException{
		//�����������1���У�2wifi 4data
		driver.setNetworkConnection(new NetworkConnectionSetting(1));
		
		By by_dubbingbtn = By.id("com.happyteam.dubbingshow:id/dubbing");
		List<WebElement> dubbinglist = null;
		
		//basefunc.enterSomeFunc("1");���������ֱ�ӽ�������
		WebElement bottombar = driver.findElement(by_bottombar);
		WebElement plusbtn = bottombar.findElement(By.className("android.view.View"));
		plusbtn.click();
		
		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		
		dubbinglist = driver.findElements(by_dubbingbtn);
		
		int count = dubbinglist.size();
		for(int i=0; i < count; i++){
			System.out.print("No. " + i + ":  ");
			
			dubbinglist.get(i).click();
			dubbingpage.DubbingSet(0, 1);
			dubbingpage.EnterViewPage(0);// ����ʱ�����Կ���,0��ʾ¼��
			dubbingpage.vol();
			dubbingpage.fx();
			dubbingpage.enterUploadPage(3);		
			
		}
		
		//�ָ�����Ϊwifi����
		driver.setNetworkConnection(new NetworkConnectionSetting(2));
		
		Assert.assertTrue(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/container")));
		
		
		
		driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		WebElement back =  driver.findElement(by_btnback);
		back.click();
		
	}

	/**
	 * 1. ����ҳ�����زĿ�
	 * 2. �زĿ��ϻ���ѡ��������Ƶ�����ز�Ԥ��Ȼ�������������
	 * 3. ����һ��ʱ���������������������
	 * 4. �ڵ�����������������������������Ч
	 * 5. �����ɽ����ϴ����棬�ϴ��ɹ����ص���ҳ 
	 */
	public void testCase06() {
		basefunc.enterSomeFunc("1");
		if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/iv_add"),10)){//ͨ���ж��Ƿ����������ť���ж��Ƿ�����زĿ�
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
		
		if(basefunc.enterQuickDubbing()){//������������ɹ�
			dubbingpage.Living(1);
			try {
				dubbingpage.EnterViewPage(0);
				dubbingpage.addBg(1);
				dubbingpage.bgvol();

				dubbingpage.enterUploadPage(1);
				uploadpage.Upload();

				
				Assert.assertTrue(pub.isElementExist(by_qwpy));

				driver.findElement(By.id("com.happyteam.dubbingshow:id/btnBack")).click();
				
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
	 * ���������ã�û��ר�ŵĺ��ݹ�������
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