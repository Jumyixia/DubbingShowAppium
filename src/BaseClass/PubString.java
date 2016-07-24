
package BaseClass;

import java.io.File;

public class PubString {
	
	public static String app_path = "../apps/dubbingshow";

	public PubString(){
		
	}
	
	
	public static void test(){
		System.out.println(app_path);
		System.out.println(System.getProperty("user.dir"));
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir,"dubbingshow.apk");
		app_path = app.getAbsolutePath();
		System.out.println(app_path);
	}
	public static void main(String[] args) {
		test();
	}
	
	
	//app_path = app.getAbsolutePath();
}
