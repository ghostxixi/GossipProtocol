package entity;

import java.io.Serializable;
import java.util.Vector;

public class AskRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	private int[] vectorTimeStamp;
	private int nodeNumber;
	private Vector<Vector<String>> rows;
	
	public AskRecord(int[] vectortimestamp,int nodenumber,Vector<Vector<String>> row){
		this.setRows(row);
		this.setVectorTimeStamp(vectortimestamp);
		this.setNodeNumber(nodenumber);
	}

	public Vector<Vector<String>> getRows() {
		return rows;
	}

	public void setRows(Vector<Vector<String>> rows) {
		this.rows = rows;
	}

	public int[] getVectorTimeStamp() {
		return vectorTimeStamp;
	}

	public void setVectorTimeStamp(int[] vectorTimeStamp) {
		this.vectorTimeStamp = vectorTimeStamp;
	}

	public int getNodeNumber() {
		return nodeNumber;
	}

	public void setNodeNumber(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}

}
