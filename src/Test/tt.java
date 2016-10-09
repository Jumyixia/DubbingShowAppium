package Test;


public class tt{
	public static void main(String[] args) {
		String replycount_string = "ÒÑÓÐ12»Ø¸´";
		int replycount_int = Integer.parseInt(replycount_string.substring(2, replycount_string.length()-2));
	
		System.out.println(replycount_int);
	
	//System.out.println((Integer.parseInt(a.substring(0,1))-1));;
	}
}