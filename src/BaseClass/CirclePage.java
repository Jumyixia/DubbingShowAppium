/**
 * 
 */
package BaseClass;

import io.appium.java_client.android.AndroidDriver;

import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Util.SystemHelper;

public class CirclePage {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	
	public AndroidDriver driver;
	public PubClass pub = null;
	public BaseFunc basefunc = null;	
	public int roletype = 0; //��¼��ǰ���е��ǵ�����ʺ�������
	public int guidetype = 1; //��ʾtips�Ƿ����1Ϊ���ڣ�0������
	
	By btn_more = null;
	By entersource = null;
	By enterviewsource = null;

	private By by_circletab;
	private By by_sourcelist;
	private By by_roles;
	private By by_circlecontainer;
	private By by_circle;
	private By by_posttitle;	
	private By by_postlist;
	private	By by_post_more;
	private By by_post_reply;

	private By by_hotpost;

	private By by_backbtn;
	private By by_followpost_cl;

	private By by_replycount;

	private By by_gallery;

	private By by_camera;

	private By by_record;

	private By by_movie;

	private By by_content;

	private By by_ok;

	private By by_recordbtn;

	private By by_replypostbtn;

	private By by_subbtn;
	
	
	
	public CirclePage(AndroidDriver driver, int guidetype){
		this.driver = driver;
		pub = new PubClass(driver);
		this.guidetype = guidetype;
		
		by_circletab = By.id("com.happyteam.dubbingshow:id/circlesTab");	//��ҳȦ��tab��ť
		by_circlecontainer = By.id("com.happyteam.dubbingshow:id/load_more_list_view_container");	//Ȧ���б�
		by_circle = By.id("com.happyteam.dubbingshow:id/item_circles_image");	//Ȧ���б�ľ���Ȧ����
		
		//Ȧ������ҳ�б�
		by_hotpost = By.id("com.happyteam.dubbingshow:id/hotTopic");
		by_posttitle = By.id("com.happyteam.dubbingshow:id/item_article_title");	//Ȧ������������б�����ӱ���
		by_postlist = By.id("com.happyteam.dubbingshow:id/circles_normal_articles_lv");
		by_post_more = By.id("com.happyteam.dubbingshow:id/btn_article_more");	//����������ఴť
		by_post_reply = By.id("com.happyteam.dubbingshow:id/relpyall");	//������ť
		
		//��������ҳ
		by_backbtn = By.id("com.happyteam.dubbingshow:id/btnBack");	//��������ҳ���ذ�ť
		by_followpost_cl = By.id("com.happyteam.dubbingshow:id/lc");	//������¥��
		by_replycount = By.id("com.happyteam.dubbingshow:id/replycount");	//�ظ�����
		by_replypostbtn = By.id("com.happyteam.dubbingshow:id/relpyall");	//�ظ�������ť
		
		//���и���ҳ��		
		by_content= By.id("com.happyteam.dubbingshow:id/content");	//д��������
		by_gallery = By.id("com.happyteam.dubbingshow:id/gallery");	//���
		by_camera = By.id("com.happyteam.dubbingshow:id/camera");	//����
		by_record = By.id("com.happyteam.dubbingshow:id/recorder");	//����
		by_recordbtn = By.id("com.happyteam.dubbingshow:id/btnRecord");	//¼����ť
		by_movie = By.id("com.happyteam.dubbingshow:id/movie");	//��Ƶ
				
		by_ok = By.id("com.happyteam.dubbingshow:id/btnRight");	//��ɰ�ť
		by_subbtn = By.id("com.happyteam.dubbingshow:id/btnSubmit");	//�������ұߵİ�ť�����ԡ�ȷ����
	}
	

	/**
	 * ����ҳ����Ȧ�ӣ��ж��Ƿ����Ȧ��
	 * ��Ȧ��ҳ�����Ȧ�����飬ͨ���ж��Ƿ�������ӻ����ö�����ȷ���Ƿ����ɹ�
	 */
	public void enterCircleDetailByRandom(){
		WebElement circletab_btn = driver.findElement(by_circletab);
		circletab_btn.click();
		if(pub.isElementExist(by_circle, 5)){//�ж��Ƿ����Ȧ��ͬʱ�Ƿ���س�Ȧ���б�
			WebElement circlecontainer = driver.findElement(by_circlecontainer);
			List<WebElement> circles = circlecontainer.findElements(by_circle);
			int count = circles.size();
			circles.get(pub.getrandom(count)).click();			

			
			//����Ȧ������
			if(pub.isElementExist(by_posttitle, 10)||pub.isElementExist(by_hotpost, 10)){//�ж��Ƿ�������ӻ����ö�����
				System.out.println("result_enterCircleDetailByRandom_pass : enter circle success");
			}  else {
				System.out.println("result_enterCircleDetailByRandom_fail : no circle or load failed.");
			}

		}else{
			System.out.println("result_enterCircleDetailByRandom_fail : enter circle failed or no circle");
		}
		 
	}
	
	public void enterFollowPostDetailByRandom(){
		int count = 0;
		WebElement postlist = driver.findElement(by_postlist);
		if(pub.isElementExist(by_posttitle, 10)){
			List<WebElement> posts = postlist.findElements(by_posttitle);
			count = posts.size();
			
			//	System.out.println("wait");
			//	SystemHelper.sleep(5);
			posts.get(pub.getrandom(count)).click();		
				
			//����������
			if(pub.isElementExist(by_post_reply, 10)){
				System.out.println("result_enterFollowPostDetailByRandom_pass : enter postdetail pass.");
			} else {
				System.out.println("result_enterFollowPostDetailByRandom_fail : enter postdetail failed.");
			} 
		}else  if(pub.isElementExist(by_hotpost, 10)){
				
			List<WebElement> hotposts = postlist.findElements(by_hotpost);
			count = hotposts.size();
			hotposts.get(pub.getrandom(count)).click();
		//  hotposts.get(pub.getrandom(0)).click();


			// ��������
			if (pub.isElementExist(by_post_reply, 10)) {
				System.out.println("result_enterFollowPostDetailByRandom_pass : enter postdetail pass.");
			} else {
				System.out.println("result_enterFollowPostDetailByRandom_fail : enter postdetail failed.");
			}
		} else {
			System.out.println("result_enterFollowPostDetailByRandom_fail : enter circledetail failed or no post.");
		}
			
	}
	
	/**
	 * 
	 */
	public void FollowPostByDesc(){
		//�ж��Ƿ��и���
		WebElement replycount = driver.findElement(by_replycount);
		String replycount_string = replycount.getText();
		if(replycount_string != null){
		int replycount_int = Integer.parseInt(replycount_string.substring(2, replycount_string.length()-2));
		System.out.println(replycount_int+"......");
		
		
		if(replycount_int > 1){
			// �ȼ������������λ��
			WebElement post_reply = driver.findElement(by_post_reply);
			int x = pub.appScreen()[0] / 2;
			int y = pub.appScreen()[1] - post_reply.getSize().height * 5;

			WebElement post_more = driver.findElement(by_post_more);
			post_more.click();
			SystemHelper.sleep(1);
			pub.tab(x, y);

			// ��֤�������Ƿ���ȷ
			SystemHelper.sleep(2);
			pub.swipeToElement(by_followpost_cl, "Up", 500);
			List<WebElement> followpost_cls = driver.findElements(by_followpost_cl);

			for (int n = 0; n < 10; n++) {
				if (followpost_cls.size() > 1) {// ��ȡ���㼰���ϵĸ���

					String clfirst = followpost_cls.get(0).getText();
					String clsecond = followpost_cls.get(1).getText();
					System.out.println(Integer.parseInt(clfirst.substring(0,
							clfirst.length() - 1)));

					if (Integer.parseInt(clfirst.substring(0,clfirst.length() - 1)) > Integer.parseInt(clsecond
							.substring(0, clsecond.length() - 1))) {// �ж��Ƿ�¥��1����¥��2
						System.out.println("result_FollowPostByDesc_pass : FollowPostByDesc pass.");
					} else {
						System.out.println("result_FollowPostByDesc_fail : FollowPostByDesc error occured.");
					}
					break;
				} else {
					pub.swipeToUp(500);
				}
				if (n == 4) {
					if (followpost_cls.size() == 1) {
						System.out.println("result_FollowPostByDesc_fail : FollowPostByDesc cl = 2. try again");
						driver.findElement(by_backbtn).click();
						pub.swipeToUp(500);
						enterFollowPostDetailByRandom();
						FollowPostByDesc();
					}
					System.out.println("result_FollowPostByDesc_fail : FollowPostByDesc cl < 2.");
				}
			}
		}else{
			System.out.println("result_FollowPostByDesc_fail : FollowPostByDesc cl = 2. try again");
			driver.findElement(by_backbtn).click();
			pub.swipeToUp(500);
			enterFollowPostDetailByRandom();
			FollowPostByDesc();
		}
		}else {
			System.out.println("result_FollowPostByDesc_fail : network error");
		}
		
	}

	/**
	 * ʵ��comment_text , duration�����ֶη�ʽ��֧��������ֺ�����
	 * �������ݺ���Ҫ����ύ��ť
	 * @param comment_text
	 * @param pic
	 * @param takepic
	 * @param duration  ����ʱ��
	 * @param l
	 */
	public void CommentPost(String comment_text, int pic, int takepic, int duration, int l) {
		// TODO Auto-generated method stub
		WebElement btn_post_reply = driver.findElement(by_post_reply);
		btn_post_reply.click();
		CommentTextPost(comment_text);
		CommentRecordPost(duration);

	}

	/**
	 * ������������
	 * �ò����ᵼ����������������Ҫ��ִ���������ֲ�������ִ�����������յȲ���
	 * @param text	��Ҫ������ַ����ݲ���������
	 */
	public void CommentTextPost(String text){
		WebElement contentarea = driver.findElement(by_content);
		contentarea.click();
		contentarea.sendKeys(text);
	}

	public void CommentGalleryPost() {

	}

	public void CommentCameraPost() {
		
	}

	/**
	 * 
	 * @param duration ����¼����ʱ������Ϊ��λ
	 */
	public void CommentRecordPost(int duration) {
		driver.findElement(by_record).click();
		WebElement record_btn = driver.findElement(by_recordbtn);
		pub.longPress(record_btn,duration*1000);
	}

	public void CommentMoviePost(String text) {

	}
	
	
	public boolean SubComment(){
		WebElement contentarea = driver.findElement(by_content);
		WebElement sub_btn = driver.findElement(by_ok);
		if(!contentarea.getText().equals("д����...")){
			sub_btn.click();
			if(pub.isElementExist(by_subbtn, 6)){
				driver.findElement(by_subbtn).click();
				
				//ѭ���ж��Ƿ񻹻��ϴ�ʧ��
				for(int i = 0; i < 5; i++){					
					if(pub.isElementExist(by_replypostbtn, 5)){
						System.out.println("result_SubComment_pass ��sub success");
						return true;
					}else if(pub.isElementExist(by_subbtn, 5)){
						driver.findElement(by_subbtn).click();
					}	
				}
				//����ѭ����û��return true ��˵���ύʧ��
				System.out.println("result_SubComment_fail : sub error. network unconnect.");
				return false;
				
			}else if(!pub.isElementExist(by_subbtn, 6)){
				System.out.println("result_SubComment_pass ��sub success");
				return true;				
			}
			
		}else {
			System.out.println("result_SubComment_fail : no content.");
			return false;
		}
		return false;
		
	}
}
