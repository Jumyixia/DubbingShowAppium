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
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
	public AndroidDriver driver;
	public PubClass pub = null;
	public BaseFunc basefunc = null;	
	public int roletype = 0; //记录当前进行的是单配合适合作配音
	public int guidetype = 1; //提示tips是否存在1为存在，0不存在
	
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
		
		by_circletab = By.id("com.happyteam.dubbingshow:id/circlesTab");	//首页圈子tab按钮
		by_circlecontainer = By.id("com.happyteam.dubbingshow:id/load_more_list_view_container");	//圈子列表
		by_circle = By.id("com.happyteam.dubbingshow:id/item_circles_image");	//圈子列表的具体圈子项
		
		//圈子详情页列表
		by_hotpost = By.id("com.happyteam.dubbingshow:id/hotTopic");
		by_posttitle = By.id("com.happyteam.dubbingshow:id/item_article_title");	//圈子详情的帖子列表的帖子标题
		by_postlist = By.id("com.happyteam.dubbingshow:id/circles_normal_articles_lv");
		by_post_more = By.id("com.happyteam.dubbingshow:id/btn_article_more");	//帖子详情更多按钮
		by_post_reply = By.id("com.happyteam.dubbingshow:id/relpyall");	//跟帖按钮
		
		//帖子详情页
		by_backbtn = By.id("com.happyteam.dubbingshow:id/btnBack");	//帖子详情页返回按钮
		by_followpost_cl = By.id("com.happyteam.dubbingshow:id/lc");	//跟帖的楼层
		by_replycount = By.id("com.happyteam.dubbingshow:id/replycount");	//回复数量
		by_replypostbtn = By.id("com.happyteam.dubbingshow:id/relpyall");	//回复主贴按钮
		
		//进行跟帖页面		
		by_content= By.id("com.happyteam.dubbingshow:id/content");	//写跟帖区域
		by_gallery = By.id("com.happyteam.dubbingshow:id/gallery");	//相册
		by_camera = By.id("com.happyteam.dubbingshow:id/camera");	//拍照
		by_record = By.id("com.happyteam.dubbingshow:id/recorder");	//语言
		by_recordbtn = By.id("com.happyteam.dubbingshow:id/btnRecord");	//录音按钮
		by_movie = By.id("com.happyteam.dubbingshow:id/movie");	//视频
				
		by_ok = By.id("com.happyteam.dubbingshow:id/btnRight");	//完成按钮
		by_subbtn = By.id("com.happyteam.dubbingshow:id/btnSubmit");	//弹窗上右边的按钮（重试、确定）
	}
	

	/**
	 * 从首页进入圈子，判断是否存在圈子
	 * 从圈子页面进入圈子详情，通过判断是否存在帖子或是置顶帖来确定是否进入成功
	 */
	public void enterCircleDetailByRandom(){
		WebElement circletab_btn = driver.findElement(by_circletab);
		circletab_btn.click();
		if(pub.isElementExist(by_circle, 5)){//判断是否进入圈子同时是否加载出圈子列表
			WebElement circlecontainer = driver.findElement(by_circlecontainer);
			List<WebElement> circles = circlecontainer.findElements(by_circle);
			int count = circles.size();
			circles.get(pub.getrandom(count)).click();			

			
			//进入圈子详情
			if(pub.isElementExist(by_posttitle, 10)||pub.isElementExist(by_hotpost, 10)){//判断是否存在帖子或者置顶帖子
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
				
			//帖子详情中
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


			// 帖子详情
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
		//判断是否有跟帖
		WebElement replycount = driver.findElement(by_replycount);
		String replycount_string = replycount.getText();
		if(replycount_string != null){
		int replycount_int = Integer.parseInt(replycount_string.substring(2, replycount_string.length()-2));
		System.out.println(replycount_int+"......");
		
		
		if(replycount_int > 1){
			// 先计算出倒序看帖的位置
			WebElement post_reply = driver.findElement(by_post_reply);
			int x = pub.appScreen()[0] / 2;
			int y = pub.appScreen()[1] - post_reply.getSize().height * 5;

			WebElement post_more = driver.findElement(by_post_more);
			post_more.click();
			SystemHelper.sleep(1);
			pub.tab(x, y);

			// 验证倒序看帖是否正确
			SystemHelper.sleep(2);
			pub.swipeToElement(by_followpost_cl, "Up", 500);
			List<WebElement> followpost_cls = driver.findElements(by_followpost_cl);

			for (int n = 0; n < 10; n++) {
				if (followpost_cls.size() > 1) {// 获取两层及以上的跟帖

					String clfirst = followpost_cls.get(0).getText();
					String clsecond = followpost_cls.get(1).getText();
					System.out.println(Integer.parseInt(clfirst.substring(0,
							clfirst.length() - 1)));

					if (Integer.parseInt(clfirst.substring(0,clfirst.length() - 1)) > Integer.parseInt(clsecond
							.substring(0, clsecond.length() - 1))) {// 判断是否楼层1大于楼层2
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
	 * 实现comment_text , duration两个字段方式，支持添加文字和语音
	 * 输入内容后不需要点击提交按钮
	 * @param comment_text
	 * @param pic
	 * @param takepic
	 * @param duration  语音时长
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
	 * 跟帖文字内容
	 * 该操作会导致清空输入框，所以需要先执行输入文字操作，再执行语音、拍照等操作
	 * @param text	需要输入的字符，暂不考虑中文
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
	 * @param duration 本次录音的时长，秒为单位
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
		if(!contentarea.getText().equals("写跟帖...")){
			sub_btn.click();
			if(pub.isElementExist(by_subbtn, 6)){
				driver.findElement(by_subbtn).click();
				
				//循环判断是否还会上传失败
				for(int i = 0; i < 5; i++){					
					if(pub.isElementExist(by_replypostbtn, 5)){
						System.out.println("result_SubComment_pass ：sub success");
						return true;
					}else if(pub.isElementExist(by_subbtn, 5)){
						driver.findElement(by_subbtn).click();
					}	
				}
				//跳出循环且没有return true 则说明提交失败
				System.out.println("result_SubComment_fail : sub error. network unconnect.");
				return false;
				
			}else if(!pub.isElementExist(by_subbtn, 6)){
				System.out.println("result_SubComment_pass ：sub success");
				return true;				
			}
			
		}else {
			System.out.println("result_SubComment_fail : no content.");
			return false;
		}
		return false;
		
	}
}
