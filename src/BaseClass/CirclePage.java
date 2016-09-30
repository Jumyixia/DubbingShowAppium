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
	private By by_circlelist;
	private By by_circle;
	private By by_posttitle;	
	private By by_postlist;
	private	By by_post_more;
	private By by_post_reply;

	private By by_hotpost;

	private By by_followpost_cl;
	
	
	
	public CirclePage(AndroidDriver driver, int guidetype){
		this.driver = driver;
		pub = new PubClass(driver);
		this.guidetype = guidetype;
		
		by_circletab = By.id("com.happyteam.dubbingshow:id/circlesTab");	//首页圈子tab按钮
		by_circlelist = By.id("com.happyteam.dubbingshow:id/load_more_list_view_container");	//圈子列表
		by_circle = By.id("com.happyteam.dubbingshow:id/item_circles_image");	//圈子列表的具体圈子项
		
		//圈子详情页列表
		by_hotpost = By.id("com.happyteam.dubbingshow:id/hotTopic");
		by_posttitle = By.id("com.happyteam.dubbingshow:id/item_article_title");	//圈子详情的帖子列表的帖子标题
		by_postlist = By.id("com.happyteam.dubbingshow:id/circles_normal_articles_lv");
		by_post_more = By.id("com.happyteam.dubbingshow:id/btn_article_more");	//帖子详情更多按钮
		by_post_reply = By.id("com.happyteam.dubbingshow:id/relpyall");	//跟帖按钮
		by_followpost_cl = By.id("com.happyteam.dubbingshow:id/lc");	//跟帖的楼层
	}
	

	/**
	 * 
	 */
	public void enterCircleDetailByRandom(){
		WebElement circletab_btn = driver.findElement(by_circletab);
		circletab_btn.click();
		if(pub.isElementExist(by_circle, 5)){//判断是否进入圈子同时是否加载出圈子列表
			WebElement circlelist = driver.findElement(by_circlelist);
			List<WebElement> circles = circlelist.findElements(by_circle);
			int count = circles.size();
			circles.get(pub.getrandom(count)).click();
			

			
			//进入圈子详情
			if(pub.isElementExist(by_posttitle, 10)){//判断是否存在帖子或者置顶帖子
				WebElement postlist = driver.findElement(by_postlist);
				List<WebElement> posts = circlelist.findElements(by_posttitle);
				count = posts.size();
				posts.get(pub.getrandom(count)).click();
				
				//进入帖子详情
				if(pub.isElementExist(by_post_reply, 10)){
					System.out.println("result_enterCircleDetailByRandom_pass : enter postdetail pass.");
				} else {
					System.out.println("result_enterCircleDetailByRandom_fail : enter postdetail failed.");
				}
			}else  if(pub.isElementExist(by_hotpost, 10)){
				WebElement postlist = driver.findElement(by_postlist);
				List<WebElement> hotposts = circlelist.findElements(by_hotpost);
				count = hotposts.size();
				hotposts.get(pub.getrandom(count)).click();

				// 进入帖子详情
				if (pub.isElementExist(by_post_reply, 10)) {
					System.out.println("result_enterCircleDetailByRandom_pass : enter postdetail pass.");
				} else {
					System.out.println("result_enterCircleDetailByRandom_fail : enter postdetail failed.");
				}
			} else {

				System.out.println("result_enterCircleDetailByRandom_fail : enter circledetail failed or no post.");
			}
			
		}else{
			System.out.println("result_enterCircleDetailByRandom_fail : enter circle failed or no circle");
		}
		 
	}
	

	public void ReplyPost(){
		
	}
	
	//
	public void FollowPostByDesc(){
		//先计算出倒序看帖的位置
		WebElement post_reply = driver.findElement(by_post_reply);
		int x = pub.appScreen()[0]/2;
		int y = pub.appScreen()[1] - post_reply.getSize().height*4;
		
		WebElement post_more = driver.findElement(by_post_more);
		post_more.click();
		SystemHelper.sleep(1);
		pub.tab(x, y);
		
		//验证倒序看帖是否正确
		SystemHelper.sleep(2);
		pub.swipeToElement(by_followpost_cl, "Up", 500 );
		List<WebElement> followpost_cls = driver.findElements(by_followpost_cl);
		
		if(followpost_cls.size()<=1){//获取两层及以上的跟帖
			pub.swipeToUp(500);
		}
		if (Integer.parseInt(followpost_cls.get(0).getText().substring(0, 1)) > 
			Integer.parseInt(followpost_cls.get(1).getText().substring(0, 1))) {//判断是否楼层1大于楼层2
			System.out.println("result_FollowPostByDesc_pass : FollowPostByDesc pass.");
		}else{
			System.out.println("result_FollowPostByDesc_fail : FollowPostByDesc error occured.");
		}
	}


	/**
	 * 
	 */
	public void a() {
		// TODO Auto-generated method stub
		
	}

}
