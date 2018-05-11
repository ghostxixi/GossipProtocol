package suf;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import entity.RequestObject;
import entity.ResponseObject;
import server.handler.*;


public class WorkerThread extends Thread {
	private Socket clientSocket;
	private RequestHandler handler; //��������ӿ�RequestHandler �Ķ���
		
	// TODO Auto-generated constructor stub
	
	 //WorkThread���಻�ܼ̳и���thread�Ĺ�������
	//ֻ���ڹ���������super()���ø���Ĺ�����
	
   public WorkerThread(Socket clientSocket) {
		super();                          //����Ĺ���������Ҫ���ø���Ĺ�����             
		this.clientSocket = clientSocket;
	}
	
   public void run(){
		try{
		  ObjectOutputStream oos;
		  ObjectInputStream ois;
		
		  oos = new ObjectOutputStream(clientSocket.getOutputStream());
		  ois = new ObjectInputStream(clientSocket.getInputStream());
		  
		  RequestObject requestObject = (RequestObject) ois.readObject();
		  ResponseObject responseObject=null;

	      switch (requestObject.getReqType()) {
	      case TALK:
	    	   handler=new TalkHandler();
	    	   break; 
	      //case INSERT:
	      //   handler=new InsertHandler();
	      //   break; 	    	   
	      //case UPDATE:
	      //   handler=new UpdateHandler();
	      //   break; 	       	       
		  default:
			   break;
		}
	          responseObject=
	        		  handler.handleRequest(requestObject);
	          oos.writeObject(responseObject);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
   }

