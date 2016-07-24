
package FuncTest;

import BaseClass.BaseFunc;
import BaseClass.DubbingPage;
import BaseClass.PubClass;
import BaseClass.UploadPage;
import BaseClass.VideoDetailPage;
import io.appium.java_client.android.AndroidDriver;

public class Login {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public PubClass pub = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public UploadPage uploadpage = null;
	public int guidetype = 1; //提示tips是否存在1为存在，0不存在
	
	public Login(AndroidDriver driver,int guidetype){
		this.driver = driver;
		basefunc = new BaseFunc(driver,guidetype);
		pub = new PubClass(driver);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver,guidetype);
		uploadpage = new UploadPage(driver);
	}
}
