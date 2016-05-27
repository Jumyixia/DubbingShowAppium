package BaseClass;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.host.media.rtc.webkitRTCPeerConnection;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class PubClass {
	public AndroidDriver driver;
	
	public PubClass(AndroidDriver driver){
		this.driver = driver;
	}
	
	/**
	 * ��ȡappռ��Ļ�Ŀ��
	 * @return
	 */
	public int[] appScreen(){
		int width = driver.manage().window().getSize().getWidth();
		int heightScreen = driver.manage().window().getSize().getHeight();
		int[] appWidthAndHeight = {width,heightScreen};
		return appWidthAndHeight;
	}

	
	//Ԫ���Ƿ����
	public boolean isElementExist(By by){
		try{
			driver.findElement(by);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//�ö���ʱʱ����Ԫ���Ƿ���ڣ���������������ؽ�����粻�������ڳ�ʱ�󷵻ؽ��
	public boolean isElementExist(By by,int timeout){
		try{
			new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(by));
			return true;
		}catch(Exception e){
			return false;
		}
		
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
	
	//��elΪĿ�꣬�ӻ���һ�����ƶ�����Ŀ����
	public void moveTo(WebElement el){
		
	}

	//�ԣ�x,y����ΪĿ�꣬�ӻ���һ�����ƶ�����Ŀ����
	public void moveTo(int x, int y){
		
	}
	
	//�ڿؼ�������press����
	public void press(WebElement el){
		
	}

	//������Ϊ��x,y���ĵ�����press����
	public void press(int x, int y){
		
	}
	

	//�ڿؼ�el�����Ͻǵ�x����ƫ��x��λ��y���ƫ��y��λ������������press������
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
     * @param x ��ʾ ��Ԫ�ص����Ͻ�Ϊ��׼����ڼ���
     * @param y ��ʾ ��Ԫ�ص����Ͻ�Ϊ��׼����ڼ���
     * @param part �������Ϸֱ�ֳɼ���
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
     * @param direction �������� Up��Down��Left��Right
     * @param duration ���β�����Ҫ���ĵ�ʱ�䣬ʱ��Խ��������Խ��
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
	
	//push�ļ���pull�ļ�
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
	
	public void pullFile(File file){
		byte[] resultDate = driver.pullFile("sdcard/test.txt");
		
		System.out.println(new String(Base64.decodeBase64(resultDate))); //��ӡ���Ϊ"test"
		
	}
	
	/*
	 *  ����������������
	 *  screenFile  ���Ϊfilepath��ͼƬ����·���� + filename(ÿ��ͼƬ�����ƣ�
	 */

	/**
	 * @author Jum
	 * @param screenFile
	 */
	public void screenCut(File screenFile){     
		File screen = driver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screen, screenFile); //commons-io-2.0.1.jar�е�api
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}