package server.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import entity.AskRecord;
import entity.ResponseObject;
import suf.DBUtil;
import suf.config;
import utils.Utils;

public class RecordHandler implements ResponseHandler{

	@Override
	public void handleResponse(ResponseObject responseObject) {
		// TODO Auto-generated method stub
		int count = 0;
		AskRecord record = (AskRecord)responseObject.getResBody();
		int[] vectorTimeStamp = record.getVectorTimeStamp();
		int nodeNumber = record.getNodeNumber();
		Connection con = DBUtil.getConnection();
		Statement stat;
		Vector<Vector<String>> rows = record.getRows();
		for(int i = (rows.size() - 1) ; i >= 0 ; i--){
			System.out.println(Utils.CheckString(rows.get(i).get(5)));
			try {
				stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	            String sql = "SELECT count(*) FROM record where record_id = " + rows.get(i).get(1) + 
	            		" and node = '" + rows.get(i).get(2) + "'";
	            ResultSet rs = stat.executeQuery(sql);
	            rs.next();
	            System.out.println("newtest:" + rs.getString(1));
	            if(rs.getInt(1) == 0){
	            	System.out.println(Utils.CheckString(rows.get(i).get(5)));
	            	DBUtil.update(Utils.CheckString(rows.get(i).get(5)));
					String recordsql = "insert into record (record_id,node,type,tableAndID,text,date) values ("
							+ rows.get(i).get(1) + ",'" + rows.get(i).get(2) + "','" + rows.get(i).get(3) + "','" + rows.get(i).get(4) + "','"
							+ rows.get(i).get(5) + "','" + rows.get(i).get(6) + "')";
					DBUtil.update(recordsql);
					count++;
	            }

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		config.vectorTimeStamp[nodeNumber -1] = vectorTimeStamp[nodeNumber - 1];
		config.vectorTimeStamp[config.nodeNumber - 1] = config.vectorTimeStamp[config.nodeNumber - 1] + count;
		System.out.println("12-2:" + config.vectorTimeStamp[nodeNumber]);
		config.updateVectorTimeStamp();
		
	}

}
