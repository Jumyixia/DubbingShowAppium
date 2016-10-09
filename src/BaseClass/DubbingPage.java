package BaseClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import Util.SystemHelper;


import io.appium.java_client.android.AndroidDriver;

public class DubbingPage {
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	
	public AndroidDriver driver;
	public PubClass pub = null;
	public BaseFunc basefunc = null;	
	public int roletype = 0; //��¼��ǰ���е��ǵ�����ʺ�������
	public int guidetype = 1; //��ʾtips�Ƿ����1Ϊ���ڣ�0������
	public String log = null;
	public String hysourcetime = null;
	
	//	��������	
	By by_back = null;
	By by_headset = null;
	By by_living = null;
	By by_dubbing = null;
	By by_script_container = null;
	By by_scirpt_count = null;
	By by_coopera = null; 
	By by_role1 = null;
	By by_role2 = null;
	By by_roleall = null;
	By by_play = null;
	By by_video_time = null;
	By by_complete = null;
	By by_btnSubmit = null;
	By by_btnCancel = null;
	//  Ԥ������
	By by_review_title = null;
	By by_playbtn = null;
	//	����
	By by_dubbingSeekbar = null;
	By by_pitch = null;
	By by_fx = null;
	By by_mix = null;
	By by_roomsize = null;
	By by_echo = null;
	By by_vol = null;
	
	By by_guide = null;
	//	������
	By by_bgfx = null;
	By by_bgvol = null;
	By by_btnback = null;
	By by_bg = null;
	By by_musiclist = null;

	private By by_next;

	private By by_ok;

	private By by_record;
	
	public DubbingPage(AndroidDriver driver, int guidetype){
		this.driver = driver;
		pub = new PubClass(driver);
		this.guidetype = guidetype;
		
		//���水ť��ʼ��--��������
		by_back = By.id("com.happyteam.dubbingshow:id/back");		//����
		by_headset = By.id("com.happyteam.dubbingshow:id/headset");	//������
		by_dubbing = By.id("com.happyteam.dubbingshow:id/action");	//����
		by_living = By.id("com.happyteam.dubbingshow:id/living");	//ʵ��
		by_script_container = By.id("com.happyteam.dubbingshow:id/script_container");	//̨��
		by_scirpt_count = By.id("com.happyteam.dubbingshow:id/scirpt_count");	//̨������
		by_coopera = By.id("com.happyteam.dubbingshow:id/coopera");	//����
		by_role1 = By.id("com.happyteam.dubbingshow:id/role1_tv");	//������һ����ɫ
		by_role2 = By.id("com.happyteam.dubbingshow:id/role2_tv");	//�����ڶ�����ɫ
		by_roleall = By.id("com.happyteam.dubbingshow:id/roleall");
		by_play = By.id("com.happyteam.dubbingshow:id/play");	//ԭ������
		by_video_time = By.id("com.happyteam.dubbingshow:id/video_time");	//��Ƶʱ��
		by_complete = By.id("com.happyteam.dubbingshow:id/complete");	//��ɰ�ť
		by_btnSubmit = By.id("com.happyteam.dubbingshow:id/btnSubmit"); 	// �����ɰ�ťʱ������ѯ�ʵ�����ȷ����ť
		by_btnCancel = By.id("com.happyteam.dubbingshow:id/btnCancel");	//����ѯ�ʵ�����ȡ����ť
		//������������
		by_record = By.id("com.happyteam.dubbingshow:id/record");	//������ť
		
		
		//���水ť��ʼ��--Ԥ������
		by_review_title = By.name("Ԥ��");
		by_playbtn = By.id("com.happyteam.dubbingshow:id/play_button");	//Ԥ�����Ű�ť
		by_guide  = By.id("com.happyteam.dubbingshow:id/btnGuideFixStart");
		by_dubbingSeekbar = By.id("com.happyteam.dubbingshow:id/dubbingSeekbar"); //����������������
		by_vol = By.id("com.happyteam.dubbingshow:id/vol"); 	//��������
		by_pitch = By.id("com.happyteam.dubbingshow:id/pitch");	//��������
		by_fx =By.id("com.happyteam.dubbingshow:id/fx");	//������Ч
		by_mix = By.id("com.happyteam.dubbingshow:id/mix");	//��������
		by_roomsize = By.id("com.happyteam.dubbingshow:id/roomsize");	//�����ռ�
		by_echo = By.id("com.happyteam.dubbingshow:id/echo");	//��������
		
		by_bgfx = By.id("com.happyteam.dubbingshow:id/bgfx");
		by_bgvol = By.id("com.happyteam.dubbingshow:id/bgvol");
		by_bg = By.id("com.happyteam.dubbingshow:id/imgBgCount");
		by_musiclist = By.id("com.happyteam.dubbingshow:id/listView");
		by_next = By.id("com.happyteam.dubbingshow:id/btnRight");
		by_ok = By.id("com.happyteam.dubbingshow:id/complete");
		
		
		
		
		by_btnback  = By.id("com.happyteam.dubbingshow:id/btnBack");	//�زĿ����ķ��ذ�ť
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
	
	/**
	 * ���ú���
	 * @param set 0��ʾ��������ȫ����ɫ��1Ϊ��һ����ɫ��2Ϊ��ڶ�����ɫ
	 */
	public void Coopera(int set){
		WebElement cooperabtn = null;
		cooperabtn = driver.findElement(by_coopera);
		if(set == 1){			
			WebElement role1 = null;
			cooperabtn.click();
			role1 = driver.findElement(by_role1);
			role1.click();
			roletype = 1; //���õ�ǰ����Ϊ�����������
		}else if(set == 2){
			WebElement role2 = null;
			cooperabtn.click();
			role2 = driver.findElement(by_role1);
			role2.click();
			roletype = 1;//���õ�ǰ����Ϊ�����������
		}else if(set == 3){
			cooperabtn.click();
			WebElement roleall = null;
			roleall = driver.findElement(by_role2);
			roleall.click();
		}
	}
	
	/*
	 * ��ʼ��������ɽ���Ԥ������
	 * dubbing_time: <8000 || >=�ز�ʱ�� ��ֱ�ӵ���������ֱ���Զ���ת������ҳ��
	 * dubbing_time: >=8000 || <�ز�ʱ�� ��dubbing_time��ʱ������ɰ�ť
	 */
	public void EnterViewPage(int dubbing_time) throws ParseException, InterruptedException{
		int video_time_int = 0;//�ز�ʱ��ת���ɰ����	
		int x=0,y =0;
		String video_time,time1,time2 = null;

		WebElement complete_btn = null;
		WebElement endconfirm_btn = null;
		System.out.println("-----------EnterViewPage.");

		if(dubbing_time >= 8000){
			// ������ɰ�ť��λ��
			List<WebElement> bottom = (driver.findElement(By.id("com.happyteam.dubbingshow:id/bottom_container"))).findElements(By.className("android.widget.FrameLayout"));
			System.out.println(bottom.size());
			x = bottom.get(0).getLocation().x + bottom.get(0).getSize().getWidth()/2;
			y = bottom.get(0).getLocation().y + bottom.get(0).getSize().getHeight()/2;
			System.out.println(x+"==="+y);
		}

		
		List<WebElement> dubbing_btn = driver.findElements(by_dubbing);
		if(dubbing_btn.size() > 1){	//��������ҳ�����⴦��
			 dubbing_btn.get(1).click();
		}else{
			dubbing_btn.get(0).click();
		}
		
		video_time = driver.findElement(by_video_time).getText();

		time1 = video_time.substring(6,8);
		time2 = video_time.substring(9,11);
		video_time_int = Integer.parseInt(time1)*60000 + Integer.parseInt(time2)*1000;

		log =  "video_time_int ,  " + video_time_int/1000 ;
		
		SystemHelper.sleep(3);		

		//��ʼ���������
		if(dubbing_time >= 8000 && dubbing_time <= video_time_int){
			
			SystemHelper.sleep(dubbing_time/1000);
					
			System.out.println(x+",,,"+y);
			pub.tab(x, y);
		
			if(pub.isElementExist(by_btnSubmit)){//�������ѯ�ʵ�������ȷ����ť
				endconfirm_btn = driver.findElement(by_btnSubmit);
				endconfirm_btn.click();	
			}
			
		}else if(dubbing_time < 8000){
			SystemHelper.sleep(video_time_int/1000);
			//�Զ������ɰ�ť�ȴ��ϳ�,��Ԥ������ȴ�2��
			SystemHelper.sleep(2);
			

		}else if(dubbing_time >= video_time_int){
			SystemHelper.sleep(video_time_int/ 1000);
			complete_btn = driver.findElement(by_complete);
			System.out.println(df.format(new Date()));
			complete_btn.click();
			if(pub.isElementExist(by_btnSubmit)){//�������ѯ�ʵ�������ȷ����ť
				endconfirm_btn = driver.findElement(by_btnSubmit);
				endconfirm_btn.click();	
			}
		}
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//WebElement review_title = driver.findElement(by_review_title);

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		System.out.println(pub.isElementExist(by_review_title,30));
		
		//System.out.println("guidetype = " + guidetype);
		if(guidetype == 1){
			if (pub.isElementExist(by_guide, 1)) {
				WebElement guide = driver.findElement(by_guide);
				guide.click();
			}	
			guidetype = 0;
		}
			
	//	System.out.println("enter yulan");			
	}
	
	
	public void HyEnterViewPage() {
		int video_time_int = 0;//�ز�ʱ��ת���ɰ����	
		int x=0,y =0;
		String video_time,time1,time2 = null;

		WebElement complete_btn = null;
		WebElement endconfirm_btn = null;
		System.out.println("-----------HyEnterViewPage.");

		WebElement dubbing_btn = driver.findElement(by_record);

		dubbing_btn.click();
		
		System.out.println(hysourcetime);
		if (hysourcetime != null) {
			time1 = hysourcetime.substring(0, 2);
			time2 = hysourcetime.substring(3, 5);
			video_time_int = Integer.parseInt(time1) * 60000 + Integer.parseInt(time2) * 1000;
			//�ȴ������Լ�����ʱ��
			SystemHelper.sleep(3+video_time_int/1000);		
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}else{
			driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
		}		

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//WebElement review_title = driver.findElement(by_review_title);


		System.out.println(pub.isElementExist(by_review_title,30));
		
		SystemHelper.sleep(3);
		
		//System.out.println("guidetype = " + guidetype);
		if(guidetype == 1){
			if (pub.isElementExist(by_guide, 1)) {
				WebElement guide = driver.findElement(by_guide);
				guide.click();
			}	
			guidetype = 0;
		}
			
	//	System.out.println("enter yulan");			
	}
	
	
	/**
	 * ��Ԥ�����������Ű�ť��������Ƶ
	 */
	public void view(){
		WebElement playbtn = driver.findElement(by_playbtn);
		playbtn.click();
	}
	
	/**
	 * @category ����������Ч����Ϊ50%	 
	 */
	public void fx(){
		
		WebElement fx = driver.findElement(by_fx);
		fx.click();
		if(!pub.isElementExist(by_mix, 5)){
			fx.click();
		}
		
		WebElement mix = driver.findElement(by_mix);
		WebElement roomsize = driver.findElement(by_roomsize);	
		WebElement echo = driver.findElement(by_echo);
		
		pub.tapOnElement(mix, 10, 15, 20);
		pub.tapOnElement(roomsize, 10, 15, 20);
		pub.tapOnElement(echo, 5, 15, 20);
	}
	
	//ֱ�ӽ�����������200
	public void vol(){
		//System.out.println("-----------begin vol");
		WebElement dubbingSeekbar = driver.findElement(by_dubbingSeekbar);
		pub.swipeOnElement(dubbingSeekbar, "Down", 1000);
		pub.swipeOnElement(dubbingSeekbar, "Down", 1000);
	}
	
	public void pitch(){
		driver.findElement(by_pitch).click();
		WebElement dubbingSeekbar = driver.findElement(By.id("com.happyteam.dubbingshow:id/pitchSeekbar"));
		pub.swipeOnElement(dubbingSeekbar, "Down", 1000);
		SystemHelper.sleep(8);
	}
	
	public void bgvol(){
		driver.findElement(by_bgvol).click();
		WebElement dubbingSeekbar = driver.findElement(By.id("com.happyteam.dubbingshow:id/backgroudSeekbar"));
		pub.swipeOnElement(dubbingSeekbar, "Down", 1000);
		SystemHelper.sleep(5);
	}
	
	/**
	 * ����������
	 * @param type 1Ϊʹ���Դ���ͨ������ 2Ϊ��ӱ�������
	 */
	public void addBg(int type){
		SystemHelper.sleep(2);
		System.out.println("adbg");
		
		for(int i = 0; i <2; i++){
		
			WebElement bgbtn = driver.findElement(by_bg);
			bgbtn.click();
			SystemHelper.sleep(1);
			pub.tab(pub.appScreen()[0] / 2, pub.appScreen()[1] - 10); // ������������ť�޷�ʶ��
			if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/txtTitle"), 5)){//�ж��Ƿ����ͨ�ñ�����ҳ��
				break;
			}
		}
		
		if(pub.isElementExist(By.id("com.happyteam.dubbingshow:id/txtTitle"), 5)){//�ж��Ƿ����ͨ�ñ�����ҳ��
			if(type == 1){
				WebElement musiclist = driver.findElement(by_musiclist);
				List<WebElement> musics_random = musiclist.findElements(By.className("android.widget.FrameLayout"));
				WebElement music = musics_random.get(pub.getrandom(7));
				WebElement nextbtn = driver.findElement(by_next);
				music.click();
				for(int i = 0; i<5; i++){
					
					if(nextbtn.getAttribute("enabled").equals("false")){
						//��ͨ�����������ѡ��һ����ͨ��"��һ��"��ť�Ƿ�ɵ�ȷ���Ƿ��Ѿ�����						
						SystemHelper.sleep(10);
						
					}else {
						System.out.println(1);
						nextbtn.click();
						
						if(pub.isElementExist(By.name("����������"), 3)){
							System.out.println(2);
							driver.findElement(by_ok).click();
							System.out.println(3);
							if(pub.isElementExist(By.name("Ԥ��"))){
								System.out.println("add bg success.");
							}
							break;
						}else{
							System.out.println("enter cut page failed.");
						}
					}
				}
				
			}else if(type == 2){
				 
			}else{
				System.out.println("type error.");
			}
		}else{
			System.out.println("enter add music list failed.");			
		}
		
		
		
	}

	/**
	 * ����Ԥ�����������
	 * @param type 1����/�������  2������� 3����
	 * @throws ParseException 
	 */
	public void enterUploadPage(int type) throws ParseException{
		long end = 0,start;
		WebElement complete = driver.findElement(by_complete);
		complete.click();
		//basefunc.LoginCheck();
		if(type == 1){
			//ͨ���ж��ϴ�ҳ���ֶΣ�ȷ���Ƿ�����ϴ�����
			if(pub.isElementExist(By.name("�ϴ���Ʒ"),600000)){
				SystemHelper.sleep(2);
			}else{
				System.out.println("enter uploadpage failed.");
			}
		}else if(type == 3){
			start = df.parse(df.format(new Date())).getTime();
			driver.findElement(by_btnSubmit).click();
			SystemHelper.sleep(2);
			if(pub.isElementExist(by_btnSubmit, 600000)){
			
				driver.findElement(by_btnSubmit).click();
				end = df.parse(df.format(new Date())).getTime();
			}
			
			long time = end-start;
			log = log + ",using:," + time/1000;
			//���ص���ҳ
			driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
			WebElement back =  driver.findElement(by_btnback);
			back.click();
			System.out.println(log);
			pub.WriteinFile(log, "time.txt");
			
		}
		
	}
	
	/**
	 * ����Ԥ�����������
	 * @param type 1����/�������  2������� 3����
	 */
	public void enterUploadPage(){
		LoginCheck();
		// ͨ���ж��ϴ�ҳ���ֶΣ�ȷ���Ƿ�����ϴ�����
		if (pub.isElementExist(By.name("�ϴ���Ʒ"), 600000)) {
			SystemHelper.sleep(2);
		} else {
			System.out.println("enter uploadpage failed.");
		}
	}
	
	/**
	 * �ڵ����ɰ�ť��ͨ���ж��Ƿ���ֵ�¼������ȷ���Ƿ���Ҫ��¼
	 */
	public void LoginCheck(){
		WebElement complete = driver.findElement(by_complete);
		complete.click();
		while(pub.isElementExist(By.name("��¼"),2)){
			//���е�¼����
			pub.tab(570, 1278);
			complete.click();
		}
	}
		
}
