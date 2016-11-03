//$Id: LoginLogoutObserver.java,v 1.2 2008/09/25 13:56:04 tinku Exp $
package test;

import java.util.*;
import java.rmi.*;
import java.io.*;

import com.adventnet.nms.util.*;
import com.adventnet.nms.commonbe.*;

public class LoginLogoutObserver implements ClientConnectionObserver,Serializable,Runnable
{
	public static GenericBEAPI geApi=null;
	public static Thread th=null;
	
	public static void main(String ar[]){
		LoginLogoutObserver lilo=new LoginLogoutObserver();
		lilo.registerForNotification();
		th=new Thread("Login logout Observer");//No internationalization
	}

	//To keep the instance in memory	
	public void run(){
		while(true){
			try{
				th.sleep(5000);
			}
			catch(Exception e){
				System.out.println("Thread Exception"+e.getMessage());//No internationalization
			}
		}
	}

	//To register for LILO Notification
	public void registerForNotification(){
		try{
			geApi=(GenericBEAPI)Naming.lookup("//localhost/GenericBEAPI");
			if(geApi!=null){
				System.out.println("Got the handle for the API");//No internationalization
			}
			else{
				System.out.println("Unable to get the handle");//No internationalization
				System.exit(0);
			}
			java.rmi.server.UnicastRemoteObject.exportObject(this,0); 
			boolean result=geApi.registerClientConnectionObserver(this);
			if(result){
				System.out.println("Successfully registered with the Observer");//No internationalization
			}
			else{
				System.out.println("Error in regeistering Observer");//No internationalization
			}
			
					
		}
		catch(Exception e){
			System.out.println("Error while registering"+e);//No internationalization
		}
	}

	//For getting update when there is a client notification for login, login failure and logout
	public void handleClientConnection(String TypeOfNotification,Properties prop){
		try{
			if(TypeOfNotification.equalsIgnoreCase("Login_Success")){
				System.out.println("Notification for Success received");//No internationalization
				Enumeration enumProp=prop.propertyNames();
				while(enumProp.hasMoreElements()){
					String strKey=(String)enumProp.nextElement();
					String strVal=(String)prop.getProperty(strKey);
					System.out.println("The prop values are"+strKey+":"+strVal);//No internationalization
				}
			}
			else if(TypeOfNotification.equalsIgnoreCase("Login_Failure")){
				System.out.println("Notification for failure received");//No internationalization
				Enumeration enumProp=prop.propertyNames();
				while(enumProp.hasMoreElements()){
					String strKey=(String)enumProp.nextElement();
					String strVal=(String)prop.getProperty(strKey);
					System.out.println("The prop values are"+strKey+":"+strVal);//No internationalization
				}
												
			}
			else if(TypeOfNotification.equalsIgnoreCase("Logout")){
				System.out.println("Notification for Logout received");//No internationalization
				Enumeration enumProp=prop.propertyNames();
				while(enumProp.hasMoreElements()){
					String strKey=(String)enumProp.nextElement();
					String strVal=(String)prop.getProperty(strKey);
					System.out.println("The prop values are"+strKey+":"+strVal);//No internationalization
				}
				
												
			}
		}
		catch(Exception e){
			System.out.println("Exception in handleClient Connection"+e);//No internationalization
		}
	}
}
