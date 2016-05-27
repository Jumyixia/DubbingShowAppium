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
	 * ����ҳ����Ƶ���б�ѡ��һ��Ƶ���������Ƶ���飬Ȼ���������
	 * ��������򿪱�����
	 * ������������������200����Ч������50%
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void testCaseOne() throws InterruptedException, ParseException{
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
	 * ����ҳ����Ƶ���б�ѡ��һ��Ƶ���������Ƶ���飬Ȼ���������
	 * ��������򿪱�����
	 * ������������������200����Ч������50%
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void testCaseTwo() throws InterruptedException, ParseException{
		basefunc.enterFeature("Ƶ��");
		basefunc.enterChannel("����");
		basefunc.enterVideoDetail();
		videodetailpage.Dubbing();
		dubbingpage.DubbingSet(1, 1);
		dubbingpage.EnterViewPage(0);//����ʱ�����Կ���,0��ʾ¼��
		dubbingpage.vol();
		dubbingpage.fx();
	}
}
