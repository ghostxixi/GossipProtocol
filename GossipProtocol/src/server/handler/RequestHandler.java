package server.handler;

import entity.RequestObject;
import entity.ResponseObject;

public interface RequestHandler {
	//abstract method
	public ResponseObject handleRequest(RequestObject requestObject);
	}