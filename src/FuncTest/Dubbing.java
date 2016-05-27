package FuncTest;

import java.text.ParseException;

import BaseClass.BaseFunc;
import BaseClass.DubbingPage;
import BaseClass.UploadPage;
import BaseClass.VideoDetailPage;
import ObjectFactory.DriverFactory;
import io.appium.java_client.android.AndroidDriver;

public class Dubbing {
	public AndroidDriver driver = null;
	public BaseFunc basefunc = null;
	public VideoDetailPage videodetailpage = null;
	public DubbingPage dubbingpage= null;
	public UploadPage uploadpage = null;
	
	public Dubbing(AndroidDriver driver){
		basefunc = new BaseFunc(driver);
		videodetailpage = new VideoDetailPage(driver);
		dubbingpage = new DubbingPage(driver);
		uploadpage = new UploadPage(driver);
	}
	
	
	/**
	 * 从首页进入频道列表选择一个频道后进入视频详情，然后进入配音
	 * 配音界面打开背景音
	 * 配音人声音量调整到200，特效调整到50%
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void testCase01() throws InterruptedException, ParseException{
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
	public void testCase02() throws InterruptedException, ParseException{
		basefunc.enterFeature("频道");
		basefunc.enterChannel("动漫");
		basefunc.enterVideoDetail();
		videodetailpage.Dubbing();
		dubbingpage.DubbingSet(0, 0);
		dubbingpage.EnterViewPage(0);//配音时长可以控制,0表示录完
		dubbingpage.vol();
		dubbingpage.fx();
		dubbingpage.enterUploadPage(1);
		uploadpage.SaveToDraft(0);
	}
	
	/**
	 * 循环配音
	 * 从首页的快速配音进入配音界面
	 * 直接普通配音
	 * 配音人声音量调整到200，特效调整到50%
	 * 保存草稿箱后选择“再配一次”
	 * @throws ParseException
	 * @throws InterruptedException
	 * @param times 循环配音次数
	 */
	public void testCase03(int times) throws ParseException, InterruptedException{
		System.out.println("testCase03");
		basefunc.enterQuickDubbing();
		for (int i = 1; i <= times; i++) {
			System.out.println("No. " + i);
			dubbingpage.DubbingSet(1, 0);
			dubbingpage.EnterViewPage(0);// 配音时长可以控制,0表示录完
			dubbingpage.vol();
			dubbingpage.fx();
			dubbingpage.enterUploadPage(1);
			uploadpage.SaveToDraft(0);
		}
	}
}
