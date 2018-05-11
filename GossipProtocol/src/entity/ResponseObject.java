package entity;

import java.io.Serializable;

import suf.MessageType;

public class ResponseObject implements Serializable {

private static final long serialVersionUID = 1L;
private MessageType resType;
private Object resBody;

public ResponseObject(MessageType type,Object body){
	setResType(type);
	resBody=body;
}

public Object getResBody() {
	return resBody;
}

public void setResBody(Object resBody) {
	this.resBody = resBody;
}


@Override
public String toString() {
	return  "resBody=" + resBody + "]";
}

public MessageType getResType() {
	return resType;
}

public void setResType(MessageType resType) {
	this.resType = resType;
}

}
