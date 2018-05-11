package server.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import entity.AskRecord;
import entity.RequestObject;
import entity.ResponseObject;
import entity.TalkInfo;
import suf.DBUtil;
import suf.MessageType;
import suf.config;

public class TalkHandler implements RequestHandler{

	@Override
	public ResponseObject handleRequest(RequestObject requestObject) {
		// TODO Auto-generated method stub
		TalkInfo info = (TalkInfo)requestObject.getReqBody();

		int nodeNumber = info.getNodeNumber();
		int[] vectorTimeStamp = info.getVectorTimeStamp();
		System.out.println("ÊÕµ½£º" + vectorTimeStamp[config.nodeNumber - 1]);
		if(config.vectorTimeStamp[config.nodeNumber - 1] > vectorTimeStamp[config.nodeNumber - 1]){
			int num = config.vectorTimeStamp[config.nodeNumber - 1] - vectorTimeStamp[config.nodeNumber - 1];
	        Vector<Vector<String>> rows = new Vector<Vector<String>>();
			Connection con = DBUtil.getConnection();
			Statement stat;
			try {
				stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String sql = "SELECT * FROM record order by ID desc LIMIT " + num;
				ResultSet rs = stat.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
	            while (rs.next()) {
	            	Vector<String> currow = new Vector<String>();
	                for (int i = 1; i <= rsmd.getColumnCount(); ++i) {  
	                    currow.addElement(rs.getString(i));  
	                }  
	                rows.addElement(currow);
	            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ResponseObject(MessageType.ASKRECORD,new AskRecord(config.vectorTimeStamp,config.nodeNumber,rows));
		}
		else{
			return new ResponseObject(MessageType.NOOPERATE, "good morning");
		}
	}

}
