package suf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.Timer;

import entity.*;
import server.handler.RecordHandler;
import server.handler.ResponseHandler;

public class net implements Runnable,ActionListener{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(9002);
			System.out.println("Server is running....");

			Socket clientSocket;
			WorkerThread worker;
			startTimer();
			for (;;) {
				clientSocket = serverSocket.accept();
				worker = new WorkerThread(clientSocket);
				worker.start();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
   	public void startTimer()
   	{
	// TODO Auto-generated method stub
	Timer timer = new Timer(1000, this);
	timer.start();
   	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
		System.out.println("连接其它节点");
		InetSocketAddress IP = config.getInetSocketAddress();
		Socket server = new Socket(IP.getAddress(),IP.getPort());
		OutputStream os = server.getOutputStream();
		InputStream is = server.getInputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		ObjectInputStream ois = new ObjectInputStream(is);
		//String insert = "insert into product (product_id,product_name,producer,number,date,p_text) values (2,'小猫','厦门',12,'11-30','喵喵')";
		//String update = "update product set p_text = '再喵喵' where product_ID = 2";
		TalkInfo talk = new TalkInfo("test",config.vectorTimeStamp,config.nodeNumber);
		System.out.println(config.vectorTimeStamp[config.nodeNumber - 1]);
		oos.writeObject(new RequestObject(MessageType.TALK,talk));
		ResponseObject responseObject = (ResponseObject) ois.readObject();
		ResponseHandler handler;
		switch (responseObject.getResType()) {
	      case ASKRECORD:
	    	   System.out.println("askrecord");
	    	   handler=new RecordHandler();
	    	   handler.handleResponse(responseObject);	    	   
	    	   break; 
	      case NOOPERATE:
	    	   System.out.println(responseObject.getResBody());
	    	   break; 	    	   	       	       
		  default:
			   break;
		}
		
		oos.close();
		server.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

}
