package BaseClass;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.Wait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class PubClass {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	public AndroidDriver driver;

	private static Process process;
	
	public PubClass(AndroidDriver driver){
		this.driver = driver;
	}
	
	/**
	 * 获取app占屏幕的宽高
	 * @return
	 */
	public int[] appScreen(){
		int width = driver.manage().window().getSize().getWidth();
		int heightScreen = driver.manage().window().getSize().getHeight();
		int[] appWidthAndHeight = {width,heightScreen};
		return appWidthAndHeight;
	}

	
	//元素是否存在
	public boolean isElementExist(By by){
		try{
			driver.findElement(by);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//置顶超时时间内元素是否存在，如存在则立即返回结果，如不存在则在超时后返回结果
	public boolean isElementExist(By by,int timeout){
		try{
			new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(by));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 等待某个元素出现，如果没有出现则等待“time”秒
	 * @param by
	 * @param time
	 * @return
	 */
	public boolean isByElementDisplayed(By by, int time) {
	    boolean status = false;
	    if (driver.findElement(by).isDisplayed() == false) {
	        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	    } else {
	        status = true;
	    }
	    return status;
	}
	
	public void checkRights(){
		By by = By.name("允许");
		while(isElementExist(by,1)){
			driver.findElement(by).click();
		}
	}
	
	public void KeyOp(){
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.BACK_SPACE);
	
	}
		
	public void tab(int i, int j) {
		try{
			TouchAction ta = new TouchAction(this.driver);
			ta.tap(i, j).perform();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void tab(WebElement el, int x, int y){
		try{
			TouchAction ta = new TouchAction(this.driver);
			ta.tap(el,x,y).perform();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//以el为目标，从还有一个点移动到该目标上
	public void moveTo(WebElement el){
		
	}

	//以（x,y）点为目标，从还有一个点移动到该目标上
	public void moveTo(int x, int y){
		
	}
	
	//在控件上运行press操作
	public void press(WebElement el){
		
	}

	//在坐标为（x,y）的点运行press操作
	public void press(int x, int y){
		
	}
	

	//在控件el的左上角的x坐标偏移x单位，y左边偏移y单位的坐标上运行press操作。
	public void press(WebElement el, int x, int y){
		try{
			TouchAction ta = new TouchAction(this.driver);
			ta.press(el, x, y).perform();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * This Method for swipe down
     * @param x 表示 以元素的左上角为基准横向第几份
     * @param y 表示 以元素的左上角为基准纵向第几份
     * @param part 横纵向上分别分成几份
     */
	public void tapOnElement(WebElement el,int x,int y,int part){
		int elx = el.getLocation().getX();
		int ely = el.getLocation().getY();
		int elWidth = el.getSize().getWidth();
		int elHeight = el.getSize().getHeight();
		int tapx = elx + elWidth*x/part;
		int tapy = ely + elHeight*y/part;
		this.tab(tapx, tapy);
	}
	
	/**
     * This Method for swipe down
     * @param direction 滑动方向 Up、Down、Left、Right
     * @param duration 本次操作需要消耗的时间，时间越长，操作越慢
     */
	public void swipeOnElement(WebElement el, String direction, int duration){
		int x = el.getLocation().getX();
		int y = el.getLocation().getY();
		int elWidth = el.getSize().getWidth();
		int elHeight = el.getSize().getHeight();
		int startx,starty,endx,endy = 0;
		switch(direction){
		case"Up":
			startx = x+elWidth*3/2;
			starty = y+elHeight*4/5;
			endy = y+elHeight*1/5;
			driver.swipe(startx, starty, startx, endy, duration);
			break;
		case "Down":
			startx = x+elWidth*3/5;
			starty = y+elHeight*1/5;
			endy = y+elHeight*4/5;
			driver.swipe(startx, starty, startx, endy, duration);
			break;
		case "Left":
			startx = x+elWidth*1/5;
			starty = y+elHeight*3/5;
			endx = y+elWidth*4/5;
			driver.swipe(startx, starty, endx, starty, duration);
			break;
		case "Right":
			startx = x+elWidth*4/5;
			starty = y+elHeight*3/5;
			endx = y+elWidth*1/5;
			driver.swipe(startx, starty, endx, starty, duration);
			break;
		}
	}
	
	/**
     * This Method for swipe up
     * 
     * @param driver
     * @param during
     */
    public void swipeToUp(int during) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, during);
        // wait for page loading
    }

    /**
     * This Method for swipe down
     * 
     * @param driver
     * @param during
     */
    public void swipeToDown(  int during) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, during);
        // wait for page loading
    }

    /**
     * This Method for swipe Left
     * 
     * @param driver
     * @param during
     */
    public void swipeToLeft(  int during) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, during);
        // wait for page loading
    }

    /**
     * This Method for swipe Right
     * 
     * @param driver
     * @param during
     */
    public void swipeToRight(int during) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, during);
        // wait for page loading
    }
	
    
    /*
	 * ------创建不存在的文件--------
	 */
	public boolean buildfile(String path) {
		System.out.println("create folder !");
		try {
			File myFilePath = new File(path);
			System.out.println(myFilePath.exists());
			if (!myFilePath.exists()) {
				
				myFilePath.mkdirs();
				System.out.println("create folder suc!");
			}
			return true;
		} catch (Exception e) {
			System.out.println("create folder fail!");
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * -------数据写入CSV文档-------------
	 */
	public void WriteinFile(String str1, String Filename) {		
		try {
			this.buildfile("/data/local/tmp/appium_tfile");
			BufferedWriter bw = new BufferedWriter(new FileWriter("/data/local/tmp/appium_tfile/"+Filename, true));
			bw.write(str1);
			bw.flush();
			bw.newLine();
			bw.write("\r\n");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    
	//push文件、pull文件
	public void pushFile(File file){
		String content = null;
		try {
			content = FileUtils.readFileToString(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] data = Base64.encodeBase64(content.getBytes());
		driver.pushFile("sdcard/test.txt", data);
		
	}
	
	public void pullFile(String filename){
		byte[] resultDate = driver.pullFile("/data/local/tmp/appium_tfile/" + filename);
		
		
		System.out.println(new String(Base64.decodeBase64(resultDate))); //打印结果为"test"
		
	}
	
	/*
	 *  截屏并保存至本地
	 *  screenFile  最好为filepath（图片保存路径） + filename(每张图片的名称）
	 */

	/**
	 * @author Jum
	 * @param screenFile
	 * 
	 */
	public void screenCut(File screenFile){     
		File screen = driver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screen, screenFile); //commons-io-2.0.1.jar中的api
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 执行cmd命令
	 * @param cmd 传入须执行的命令
	 * 杀掉app：adb shell am force-stop packagename
	 * 启动app：adb shell am start -n packagename/第一个启动的Activitity
	 */
	public void extcmd(String cmd){
		Runtime runtime = Runtime.getRuntime();
		try {
			process = runtime.exec(cmd);
			/*支持读取执行结果
			 * BufferedInputStream in = new BufferedInputStream(process.getInputStream());			
			byte c[] = new byte[10];
			int n = 0;
			while ((n = in.read(c)) != -1) {
				String temp = new String(c, 0, n);
				System.out.println(temp);
			}
			
			in.close();
			 */
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}