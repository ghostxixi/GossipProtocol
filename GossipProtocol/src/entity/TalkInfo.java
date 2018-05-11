package entity;

import java.io.Serializable;

public class TalkInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String message;
	private int[] vectorTimeStamp;	
	private int nodeNumber;
	
	public TalkInfo(String message,int[] vectorTimeStamp,int nodeNumber){
		this.message = message;
		this.vectorTimeStamp = vectorTimeStamp;
		this.setNodeNumber(nodeNumber);
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
