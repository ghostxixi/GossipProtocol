package suf;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import entity.RequestObject;
import entity.ResponseObject;
import server.handler.*;


public class WorkerThread extends Thread {
	private Socket clientSocket;
	private RequestHandler handler; //类变量，接口RequestHandler 的对象
		
	// TODO Auto-generated constructor stub
	
	 //WorkThread子类不能继承父类thread的构造器；
	//只能在构造器中用super()调用父类的构造器
	
   public WorkerThread(Socket clientSocket) {
		super();                          //子类的构造器首先要调用父类的构造器             
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

