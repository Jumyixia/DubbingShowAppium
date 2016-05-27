package FuncTest;

import java.text.ParseException;

import BaseClass.BaseFunc;
import BaseClass.DubbingPage;
import BaseClass.VideoDetailPage;
import ObjectFactory.DriverFactory;
import io.appium.java_client.android.AndroidDriver;

public class Dubbing {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	
	public Dubbing(AndroidDriver driver){
		basefunc = new BaseFunc(driver);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver);
	}
	
	
	/**
	 * 从首页进入频道列表选择一个频道后进入视频详情，然后进入配音
	 * 配音界面打开背景音
	 * 配音人声音量调整到200，特效调整到50%
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void testCaseOne() throws InterruptedException, ParseException{
		basefunc.enterFeature("频道");
		basefunc.enterChannel("动漫");
		basefunc.enterVideoDetail();
		videodetailpage.Dubbing();
		dubbingpage.DubbingSet(1, 0);
		dubbingpage.EnterViewPage(9000);//配音时长可以控制
		dubbingpage.vol();
		dubbingpage.fx();
	}
	
	/**
	 * 从首页进入频道列表选择一个频道后进入视频详情，然后进入配音
	 * 配音界面打开背景音
	 * 配音人声音量调整到200，特效调整到50%
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void testCaseTwo() throws InterruptedException, ParseException{
		basefunc.enterFeature("频道");
		basefunc.enterChannel("动漫");
		basefunc.enterVideoDetail();
		videodetailpage.Dubbing();
		dubbingpage.DubbingSet(1, 1);
		dubbingpage.EnterViewPage(0);//配音时长可以控制,0表示录完
		dubbingpage.vol();
		dubbingpage.fx();
	}
}
