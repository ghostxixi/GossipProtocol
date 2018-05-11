package suf;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import utils.Utils;

public class config {
	public static final String node = "node2Count";
	public static final int nodeNumber = 2;
	private static final String nodeCollection = "localhost:10001,localhost:9003";
	public static int metadata_operate;
	public static List<InetSocketAddress> seeds;
	private static Random random = new Random();
	public static int[] vectorTimeStamp = new int[3];
	//private static int metadata_update;
	
	public static void config_matedata(){
		Connection con = DBUtil.getConnection();
		Statement stat;
		try {
			stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stat.executeQuery("select * from metadata");
			rs.next();
			metadata_operate = rs.getInt(2);
			
			for(int i = 3 ; i < 6 ; i++){
				vectorTimeStamp[i - 3] = rs.getInt(i);
			}
			
			String[] hosts = nodeCollection.split(",", -1);
			seeds = new ArrayList<InetSocketAddress>(hosts.length);
	        for (String host : hosts)
	        {
	        	seeds.add(Utils.parseInetSocketAddress(host.trim()));
	        }
		} catch (SQLException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
	}
	
	public static void updateVectorTimeStamp(){
		String sql = "update metadata set node1Count = " + vectorTimeStamp[0] +
				", node2Count = " + vectorTimeStamp[1] + ", node3Count = " + vectorTimeStamp[2]
						+ " where ID = 1";
		DBUtil.update(sql);
	}
	
	public static void AddInsertCount(){
		metadata_operate++;
		vectorTimeStamp[nodeNumber - 1] = vectorTimeStamp[nodeNumber - 1] + 1;
		String sql = "update metadata set operateCount = " + metadata_operate + "," +
				node + " = " + vectorTimeStamp[nodeNumber - 1] + " where ID = 1";
		DBUtil.update(sql);
	}
	
	public static InetSocketAddress getInetSocketAddress(){
		Collections.shuffle(seeds, random);
		return seeds.get(0);
	}
	
	public static void main(String[] args){
		config_matedata();		
	}
	
	
}
