/**
 * 
 */
package Util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**  
 * ��˵��   
 *  
 * @author Jum  
 * @date 2016-6-14 ����10:48:24 
 */
/**
 * @author Jum
 *
 */
public class ttest {
	static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null;  
  
    public static void main(String[] args) {  
        sql = "select *from comment limit 3";//SQL���  
        db1 = new DBHelper(sql);//����DBHelper����  
        
      
        try {  
            ret = db1.pst.executeQuery();//ִ����䣬�õ������  
            
            while (ret.next()) {  
                String uid = ret.getString(1);  
                String ufname = ret.getString(2);  
                String ulname = ret.getString(3);  
                String udate = ret.getString(8);  
                System.out.println(uid + "\t" + ufname + "\t" + ulname + "\t" + udate );  
            }//��ʾ����  
            ret.close();  
            db1.close();//�ر�����  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
