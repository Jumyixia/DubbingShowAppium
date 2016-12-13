package Test;


public class tt{
	public static void main(String[] args) {
		String replycount_string = "ÒÑÓÐ12»Ø¸´";
		int replycount_int = Integer.parseInt(replycount_string.substring(2, replycount_string.length()-2));
		 test();
	//System.out.println((Integer.parseInt(a.substring(0,1))-1));;
		 
	}
	public static void test(){
		String funcName2 = new Throwable().getStackTrace()[1].getMethodName();  
		System.out.println(funcName2);
		
	
	}
	
	public void tst(){
		String name = this.getClass().getName();
		System.out.println(name);
	}
}