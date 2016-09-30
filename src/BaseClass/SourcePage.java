/**
 * 
 */
package BaseClass;

import io.appium.java_client.android.AndroidDriver;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;

public class SourcePage {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
	public AndroidDriver driver;
	public PubClass pub = null;
	public BaseFunc basefunc = null;	
	public int roletype = 0; //记录当前进行的是单配合适合作配音
	public int guidetype = 1; //提示tips是否存在1为存在，0不存在
	
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
		
		by_sourcelist = By.id("com.happyteam.dubbingshow:id/viewPager");	//素材列表
		by_container = By.id("com.happyteam.dubbingshow:id/container");	//每一个素材项
		by_sourceinfo = By.id("com.happyteam.dubbingshow:id/sourceInfo");	//素材信息
		by_roles = By.id("android.widget.LinearLayout");	//角色信息
	}
	

	/**
	 * 选择一个素材进入素材预览界面
	 * @param type 1为单人或者无角色素材，2为双角色素材
	 */
	public void enterSourceView(int type){
		
	}
	
	/**
	 * 
	 * 随机选择一个素材，记录素材的时长，然后进入素材预览
	 * @param srtype	1为单人或者无角色素材，2为双角色素材
	 * @param dubbingtype	1为配音素材，2为合演素材
	 */
	public void enterSourceView(int srtype,int dubbingtype){
		
		
	}

}
