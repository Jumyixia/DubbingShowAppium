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
	 * ����ҳ����Ƶ���б�ѡ��һ��Ƶ���������Ƶ���飬Ȼ���������
	 * ��������򿪱�����
	 * ������������������200����Ч������50%
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void testCase02() throws InterruptedException, ParseException{
		basefunc.enterFeature("Ƶ��");
		basefunc.enterChannel("����");
		basefunc.enterVideoDetail();
		videodetailpage.Dubbing();
		dubbingpage.DubbingSet(0, 0);
		dubbingpage.EnterViewPage(0);//����ʱ�����Կ���,0��ʾ¼��
		dubbingpage.vol();
		dubbingpage.fx();
		dubbingpage.enterUploadPage(1);
		uploadpage.SaveToDraft(0);
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
		basefunc.enterQuickDubbing();
		for (int i = 1; i <= times; i++) {
			System.out.println("No. " + i);
			dubbingpage.DubbingSet(1, 0);
			dubbingpage.EnterViewPage(0);// ����ʱ�����Կ���,0��ʾ¼��
			dubbingpage.vol();
			dubbingpage.fx();
			dubbingpage.enterUploadPage(1);
			uploadpage.SaveToDraft(0);
		}
	}
}
