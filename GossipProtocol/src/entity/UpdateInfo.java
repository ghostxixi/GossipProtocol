package entity;

import java.io.Serializable;

public class UpdateInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String message;
	public UpdateInfo(String message){
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
