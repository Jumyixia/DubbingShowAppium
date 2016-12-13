package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Util.DriverFactory;
import Util.Console;

public class SystemHelper {

	public static String getUserdir() {
		return System.getProperty("user.dir");
	}

	public static boolean isAndroid() {
		String os = System.getProperty("JD.OS");
		if (os.equalsIgnoreCase("android"))
			return true;
		else
			return false;
	}

	public static boolean isIOS() {
		String os = System.getProperty("JD.OS");
		if (os.equalsIgnoreCase("ios"))
			return true;
		else
			return false;
	}

	public static boolean isFuzzing(){
		if(System.getProperty("isFuzzing").equalsIgnoreCase("false")){
			return false;
		}else{
			return true;
		}
	}
	
	public static String getOS() {
		return System.getProperty("JD.OS").toString();
	}

	public static String getDefaultDriver() {
		return System.getProperty("JD.DefaultDriver").toString();
	}

	public static String getMacAddress() {
		String macAddress = "";
		try {
			boolean flag = false;
			NetworkInterface networkInterface = null;
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface
						.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress address = inetAddresses.nextElement();
					String ipAddr = address.getHostAddress();
					if (ipAddr != null
							&& (ipAddr.startsWith("192.") || ipAddr
									.startsWith("10."))) {
						flag = true;
						break;
					}
				}
				if (flag)
					break;
			}
			if (flag && networkInterface != null && networkInterface.isUp()) {
				byte[] addr = networkInterface.getHardwareAddress();
				if (addr != null && addr.length > 0) {
					for (byte add : addr) {
						String hex = Integer.toHexString(add & 0xff);
						while (hex.length() < 2)
							hex = "0" + hex;
						macAddress += hex;
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return macAddress.toUpperCase();
	}

	/**
	 * ִ�е�ǰϵͳ������ɹ�����true,ʧ�ܷ���false;
	 * 
	 * @param command
	 * @return
	 */

	public static void exec(String command) {
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Process exec(String[] command) {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return process;
	}

	/**
	 * android�������
	 */
	public static void clearAndroidCache() {
		if (System.getProperty("os.name").contains("Mac")) {
			SystemHelper
					.exec("/usr/local/bin/adb shell pm clear com.jingdong.app.mall");
		} else {
			SystemHelper.exec("adb shell pm clear com.jingdong.app.mall");
		}
	}

	/**
	 * ios�������
	 * 
	 * @return
	 */
	public static boolean clearIOSCache() {
		try {
			SystemHelper
					.exec("/bin/rm -rf /Users/"
							+ System.getProperty("user.name")
							+ "/Library/Application Support/iPhone Simulator/7.1/Applications/"
							+ System.getProperty("IOS.RandomAppName")
							+ "/Library/");
			
			return true;
		} catch (Exception e) {
			Console.errorLog("command execution failed!!!");
			return false;
		}
	}

	public static void launchAndroidApp() {
		if (System.getProperty("os.name").contains("Mac")) {
			SystemHelper
					.exec("/usr/local/bin/adb shell am start com.jingdong.app.mall/com.jingdong.app.mall.MainActivity");
		} else {
			SystemHelper
					.exec("adb shell am start com.jingdong.app.mall/com.jingdong.app.mall.MainActivity");
		}
	}

	public static void launchIOSApp() {
		// TODO Auto-generated method stub
		DriverFactory.getAppiumDriver().launchApp();
	}

	public static String getMonkeytalkAgentPath() {
		return System.getProperty("MT.Path");
	}

	public static void sleep(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getOSName() {
		String osName = System.getProperty("os.name"); // ����ϵͳ����
		// String osArch = System.getProperty("os.arch"); //����ϵͳ����
		// String osVersion = System.getProperty("os.version"); //����ϵͳ�汾
		// System.out.println(osName);
		// System.out.println(osArch);
		// System.out.println(osVersion);
		return osName;
	}

	public static boolean isAndroidPlatform() {
		if (getOSName().toLowerCase().contains("windows")) {
			return true;
		}
		return false;
	}

	public static boolean isIOSPlatform() {
		if (getOSName().toLowerCase().contains("os x")) {
			return true;
		}
		return false;
	}

	/**
	 * ����ת�ַ�������
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String transferDateToString(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String dateStr = simpleDateFormat.format(date);
		return dateStr;
	}

	/**
	 * �ַ���ת���ڵķ���
	 * 
	 * @param dateString
	 * @param dateFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date transferStringToDate(String dateString, String dateFormat)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		Date date = simpleDateFormat.parse(dateString);
		return date;
	}

	/**
	 * ��ȡadb��������Ļ�ϵ����
	 * 
	 * @param adbCommand
	 *            ��Ҫִ�е�adb����
	 * @param outputPath
	 *            �������������Ҫ���浽���ļ�·��
	 */
	public static void getAdbExecuteOutput(String adbCommand, String outputPath) {
		try {
			// ִ�� ADB ����
			Process process = Runtime.getRuntime().exec(adbCommand);

			// ���������ж�ȡ�ı�
			BufferedReader br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			// ����һ��д������ָ������ļ�����·��
			FileWriter fireWriter = new FileWriter(new File(outputPath));

			String line = null;

			// ѭ����ȡ
			while ((line = br.readLine()) != null) {
				// ѭ��д��
				fireWriter.write(line + "\n");
			}

			// ˢ�������
			fireWriter.flush();

			// �ر������
			fireWriter.close();

			// �ر������
			process.getOutputStream().close();

			System.out.println("����ִ�����!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡadb��������е�����ֶ�
	 * 
	 * @param adbCommand
	 *            Ҫִ�е�adb����
	 * @param findFilter
	 *            Ҫ���ҵĹؼ��ֶ�
	 * @return
	 */
	public static String getAdbExecuteOutputTargetContent(String adbCommand,
			String findFilter) {

		String result = null;
		try {
			Process process = Runtime.getRuntime().exec(adbCommand);

			BufferedReader bf = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			while ((result = bf.readLine()) != null) {
				if (result.contains(findFilter)) {
					break;
				}
			}

			process.getOutputStream().close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}


	

	/**
	 * ��long��ʱ��ת��Ϊ�����Сʱ�������ʽ�������ַ�����ʽ���أ���������ʱ��longС��1000�򷵻�xx���� ����������᷵��
	 * xxСʱxx����xx��
	 */
	public static String transferLongToTimeStr(long timeduration) {
		long hourDivide = 3600 * 1000;
		long minDivide = 1000 * 60;
		long secDivide = 1000;

		long hour = 0;
		long min = 0;
		long sec = 0;
		if (timeduration < 0) {
			return "ʱ�䳤����Ϊ������������Դ�Ƿ�Ϸ�";
		} else if (timeduration < 1000) {
			return timeduration + "����";
		} else {
			hour = timeduration / hourDivide;
			min = (timeduration % hourDivide) / minDivide;
			sec = ((timeduration % hourDivide) % minDivide) / secDivide;
			return hour + "Сʱ" + min + "����" + sec + "��";
		}
	}

	public static Date[] getDateFromStringBatched(String[] allTimeStrings,
			String format) throws ParseException {
		Date[] dates = new Date[allTimeStrings.length];
		for (int i = 0; i < allTimeStrings.length; i++) {
			dates[i] = SystemHelper.transferStringToDate(allTimeStrings[i],
					format);
		}
		return dates;
	}

	public static String[] filterAllStringByRegex(
			String[] stringArrayNeedsToTrans, String Regex) {
		Pattern p = Pattern.compile(Regex);
		StringBuffer sb = new StringBuffer();
		Matcher m = null;
		for (int i = 0; i < stringArrayNeedsToTrans.length; i++) {
			m = p.matcher(stringArrayNeedsToTrans[i]);
			if (m.find()) {
				sb.append(m.group());
				sb.append(";");
			}
		}
		String[] results = sb.toString().split(";");
		return results;
	}

	public static String filterSingleStringByRegex(String stringNeedToTrans,
			String Regex) {
		Pattern p = Pattern.compile(Regex);
		Matcher m = p.matcher(stringNeedToTrans);
		if (m.find()) {
			return m.group();
		} else {
			return null;
		}
	}

	public static String[] getAllFileNames(String filePath) {
		File targetFile = new File(filePath);
		String[] allFilesName = null;

		if (!targetFile.exists()) {
			allFilesName = new String[1];
			allFilesName[0] = "���ļ��в�����";
		} else if (targetFile.list().length == 0) {
			allFilesName = new String[1];
			allFilesName[0] = "���ļ���Ϊ��";
		} else if (targetFile.list().length==1){
			File target1 = new File(filePath + "/" + targetFile.list()[0]);
			if(target1.isHidden()){
				allFilesName = new String[1];
				allFilesName[0] = "���ļ���Ϊ��";
			}else if(targetFile.list()[0].matches("[a-zA-Z0-9]+[.]{1}[pPjJ]{1}[pPnN]{1}[gG]{1}")){
				allFilesName = targetFile.list();
			}
		}else {
			allFilesName = targetFile.list();
		}
		return allFilesName;
	}

	public static Map<Date, String> getFileNameAndDate(String[] allFileNames,
			String timeStringRegex, String timeFormat) throws ParseException {
		Map<Date, String> fileAndDate = new HashMap<Date, String>();
		for (int i = 0; i < allFileNames.length; i++) {
			String timeStamp = filterSingleStringByRegex(allFileNames[i],
					timeStringRegex);
			Date date = transferStringToDate(timeStamp, timeFormat);
			fileAndDate.put(date, allFileNames[i]);
		}
		return fileAndDate;

	}
	
	public static void main(String[] args) throws IOException, ParseException {
//		SystemHelper.exec("cmd /c del /Q " + System.getProperty("user.dir")
//				+ "\\screenshots");
		System.out.println(getAdbExecuteOutputTargetContent("adb shell dumpsys package com.jingdong.app.mall", "versionCode"));
		System.out.println(getAdbExecuteOutputTargetContent("adb shell dumpsys package com.jingdong.app.mall", "versionCode").trim().split(" ")[0]);
		
	}

}

