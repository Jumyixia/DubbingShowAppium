package Util;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import BaseClass.PubString;
//import Listeners.MTLimport Util.Console;
import Util.SystemHelper;

//import com.gorillalogic.monkeytalk.java.MonkeyTalkDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {
	

	private static Object currentDriver = null;
	//private static MonkeyTalkDriver mtDriver;
	private static AndroidDriver appiumDriver;
	private static WebDriver webDriver;
	private static int appiumPort = 4723;
	private static Vector<String> ports = new Vector<String>();
	private static PubString str = new PubString();
	
	/**
	 * 初始化MTdriver 不要修改此方法
	 * 
	 * @return
	 * @author duzhe

	public static MonkeyTalkDriver getMTDriver() {
		if (mtDriver == null) {
			if (SystemHelper.isAndroid()) {
				// android连接
				mtDriver = new MonkeyTalkDriver(new File("."),
						"AndroidEmulator");
				mtDriver.setAdb(new File(System.getProperty("Adb.path")));
				mtDriver.setThinktime(2000);
				mtDriver.setTimeout(5000);
				// 去除verbose时输出中文乱码，使用自定义监听
				mtDriver.setVerbose(false);
				mtDriver.setScriptListener(new MTListener());
				mtDriver.setScreenshots(false);
				mtDriver.setScreenshotOnError(false);
			} else if (SystemHelper.isIOS()) {
				// ios连接
				mtDriver = new MonkeyTalkDriver(new File("."), "ios");
				mtDriver.setThinktime(2000);
				mtDriver.setTimeout(2000);
				// 去除verbose时输出中文乱码，使用自定义监听
				mtDriver.setVerbose(false);
				mtDriver.setScriptListener(new MTListener());
			}
		}
		currentDriver = mtDriver;
		return mtDriver;
	}
	 */
	
	public static AndroidDriver getAppiumDriver() {
		System.out.println("getAppiumDriver");
		String port = null;
		if (appiumDriver == null) {
			try {
				Runtime.getRuntime().exec("cmd /c start " + str.cmdPath);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		SystemHelper.sleep(10);

		DesiredCapabilities capabilities = new DesiredCapabilities();

		System.out.println("start android");
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir,str.apkname);

		System.out.println("start capabilities");
		// 设置参数
		capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"5.0.2");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Ju");
		// 配置中的app名称必须与实际安装的app名称一致
		capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,str.packagename);
		capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,str.startactivity);
		capabilities.setCapability(MobileCapabilityType.APP_WAIT_ACTIVITY,str.startactivity);
		capabilities.setCapability(MobileCapabilityType.APP_WAIT_PACKAGE,str.packagename);
		capabilities.setCapability("autoLaunch", true);
		capabilities.setCapability("noSign","noSign");
		capabilities.setCapability("unicodeKeyboard", true);
		// capabilities.setCapability("autoWebview","true");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"600");
		// 不重新安装app，appium安装导致app提示恶意篡改
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("fullReset", false);
		System.out.println(capabilities);
		// capabilities.setCapability("udid", getUdid());
		try {
			System.out.println("start appiumDriver");
			// appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:"+port+"/wd/hub"), capabilities);
			//appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			 appiumDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
			
			System.out.println("start ok");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		currentDriver = appiumDriver;
		// 加入list
		// ports.add(String.valueOf(port));
		return appiumDriver;
	}

	public static void main(String[] args) throws IOException, ParseException {
	}

	public static Object getCurrentDriver() {
		return currentDriver;
	}

	private static String getAppiumPort() {
		int newPort = appiumPort + 2 * ports.size();
		return String.valueOf(newPort);
	}

	private static String getUdid() {
		String[] ids = System.getProperty("Phones.udid").split(",");
		return ids[ports.size()].trim();
	}
}
