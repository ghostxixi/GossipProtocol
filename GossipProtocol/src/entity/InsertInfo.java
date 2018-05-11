package entity;

import java.io.Serializable;

public class InsertInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String message;
	public InsertInfo(String message){
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
