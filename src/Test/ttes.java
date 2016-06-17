/**
 * 
 */
package Test;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ttes extends TestCase{

    public ttes(String name) {
        super(name);
    }
    
    public void test1(){
    	System.out.println("11111111");
    }

    public void test2(){
    	System.out.println("22222222");
    }
    
    public void test3(){
    	System.out.println("33333333");
    }
	
    public static Test suite() {
        TestSuite suite=new TestSuite();
        suite.addTest(new ttes("test3"));
        suite.addTest(new ttes("test1"));
        suite.addTest(new ttes("test2"));
      
        return  suite;
    }
}
