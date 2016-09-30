/**
 * 
 */
package BaseClass;

import io.appium.java_client.android.AndroidDriver;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;

public class SourcePage {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	
	public AndroidDriver driver;
	public PubClass pub = null;
	public BaseFunc basefunc = null;	
	public int roletype = 0; //��¼��ǰ���е��ǵ�����ʺ�������
	public int guidetype = 1; //��ʾtips�Ƿ����1Ϊ���ڣ�0������
	
	By btn_more = null;
	By entersource = null;
	By enterviewsource = null;

	private By by_container;

	private By by_sourcelist;

	private By by_sourceinfo;

	private By by_roles;
	
	
	
	
	
	public SourcePage(AndroidDriver driver, int guidetype){
		this.driver = driver;
		pub = new PubClass(driver);
		this.guidetype = guidetype;
		
		by_sourcelist = By.id("com.happyteam.dubbingshow:id/viewPager");	//�ز��б�
		by_container = By.id("com.happyteam.dubbingshow:id/container");	//ÿһ���ز���
		by_sourceinfo = By.id("com.happyteam.dubbingshow:id/sourceInfo");	//�ز���Ϣ
		by_roles = By.id("android.widget.LinearLayout");	//��ɫ��Ϣ
	}
	

	/**
	 * ѡ��һ���زĽ����ز�Ԥ������
	 * @param type 1Ϊ���˻����޽�ɫ�زģ�2Ϊ˫��ɫ�ز�
	 */
	public void enterSourceView(int type){
		
	}
	
	/**
	 * 
	 * ���ѡ��һ���زģ���¼�زĵ�ʱ����Ȼ������ز�Ԥ��
	 * @param srtype	1Ϊ���˻����޽�ɫ�زģ�2Ϊ˫��ɫ�ز�
	 * @param dubbingtype	1Ϊ�����زģ�2Ϊ�����ز�
	 */
	public void enterSourceView(int srtype,int dubbingtype){
		
		
	}

}
