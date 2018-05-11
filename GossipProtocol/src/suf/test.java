package suf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import utils.Utils;

public class test {
	public test(){
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
		Connection con = DBUtil.getConnection();
		Statement stat;
		try {
			stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stat.executeQuery("SELECT * FROM record order by record_id desc LIMIT 2");
			ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
            	Vector<String> currow = new Vector<String>();
                for (int i = 1; i <= rsmd.getColumnCount(); ++i) {  
                    currow.addElement(rs.getString(i));  
                }  
                rows.addElement(currow);
            }
            System.out.println(Utils.CheckString(rows.get(1).get(4)));
            System.out.println(rows.size());
            String sql = "SELECT count(*) FROM record where record_id = " + rows.get(1).get(0) + 
            		" and node = '" + rows.get(1).get(1) + "'";
            rs = stat.executeQuery(sql);
            rs.next();
            System.out.println(rs.getString(1));
            //DBUtil.update(Utils.CheckString(rows.get(1).get(4)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	}
	
	public static void main(String[] argc){
		new test();
	}

}
