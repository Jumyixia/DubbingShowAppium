/**
 * 
 */
package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class file {

	public static boolean buildfile(String path) {
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
	public static void WriteinFile(String str1, String Filename) {		
		try {
			buildfile("/data/local/tmp/appium_tfile");
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

	public static void main(String[] args) throws IOException {
		WriteinFile("1234567890", "123.txt");
		
		BufferedReader bw = new BufferedReader(new FileReader("/data/local/tmp/appium_tfile/123.txt"));
		System.out.println(bw.readLine());;
	}
}
