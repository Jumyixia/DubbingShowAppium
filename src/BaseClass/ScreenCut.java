package BaseClass;


import io.appium.java_client.android.AndroidDriver;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import Util.SystemHelper;

public class ScreenCut extends Thread {
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	SimpleDateFormat df2 = new SimpleDateFormat("HHmmssSSS");//设置日期格式
	public AndroidDriver driver;
	public PubString str = new PubString();
	public String filepath = null;
	String memdate, cpudate, words;
	int Stopkey = 0;
	
	public ScreenCut(AndroidDriver driver){
		this.driver = driver;
	}

	public void run() {
		File file = null;
		filepath = str.screencut_filepath + df.format((new Date()));
						
		while (Stopkey == 0) 
		{
			file = new File( filepath + "/" + df2.format(new Date()) + ".png");
			screenCut(file);
			SystemHelper.sleep(1);
			
		}
		Stopkey = 2;
	}
	
	public void stopRunning() {
		Stopkey = 1;
	}

	public boolean isStopped() {
		return Stopkey == 2;
	}
	public void screenCut(File screenFile){     
		File screen = driver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screen, screenFile); //commons-io-2.0.1.jar中的api
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}

