package entity;
import java.io.Serializable;
import suf.MessageType;

public class RequestObject implements Serializable {

private static final long serialVersionUID = 1L;
private MessageType reqType;
private Object reqBody;

public RequestObject(MessageType reqType, Object reqBody) {
	super();
	this.reqType = reqType;
	this.reqBody = reqBody;
}
public MessageType getReqType() {
	return reqType;
}
public void setReqType(MessageType reqType) {
	this.reqType = reqType;
}
public Object getReqBody() {
	return reqBody;
}
public void setReqBody(Object reqBody) {
	this.reqBody = reqBody;
}
@Override
public String toString() {
	return "RequestObject [reqType=" + reqType + ", reqBody=" + reqBody + "]";
}

}

