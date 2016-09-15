package com.adventnet.nms.testdb.tools;
/*
* $Id: 
* 
*/

import com.adventnet.nms.testdb.util.*;
import com.adventnet.nms.testdb.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import java.util.*;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.server.*;
import com.adventnet.snmp.snmp2.*;
import java.io.*;

public class TrapReceiver extends UnicastRemoteObject implements TrapObserver,Serializable  
{
   static EventAPI TheAPI           =   null; 	
   int tempcount                    =   0;
   public int count                 =   0;    
   long starttime                   =   0;
   long stoptime                    =   0;   
   public int trapCount             =   0;  
   public Vector rateVector         =   new Vector();     
   
   public TrapReceiver() throws RemoteException  {}

  public void update(com.adventnet.snmp.snmp2.SnmpPDU pdu)
  {
       tempcount++;
	   if(tempcount==1)
	   starttime=System.currentTimeMillis();
	   if(tempcount%100==0)
	   {
		 stoptime=System.currentTimeMillis();	
         String trapRate = String.valueOf(tempcount*1000/(stoptime-starttime));
         rateVector.addElement(trapRate);   
		 System.out.println("100 Traps Received At A Rate Of "+trapRate+" per Second");		 		 
	     tempcount=0;
         count++;
		}
  }
   
   
  public void registerClient(TrapObserver trapobs)
  {        
        
        //System.out.println("Register Client Called ");            
		try
		{
			String trapapi = "//" +java.net.InetAddress.getLocalHost().getHostName() +"/EventAPI";
			
			TheAPI = (EventAPI)Naming.lookup(trapapi);                        			
			boolean result = TheAPI.register(trapobs);
			if(!result)
               System.out.println("Trap Observer Registration Failed");			  
			//else // System.out.println("Registration Succeeded");		
			}catch (Exception ex)
			{
	 			System.out.println("TrapReceiver Exception : "+ex);
	 			ex.printStackTrace();
			}
    }
				
	
public static void main(String args[])
{
  
  try{
  	    TrapReceiver obstraps =new TrapReceiver();
        obstraps.trapCount=Integer.parseInt(args[0]);
  	    obstraps.registerClient(obstraps);
  	    Thread.sleep((obstraps.trapCount*1000)/4);
  	    int drops=obstraps.trapCount-1000;
  	    //System.out.println((count*100)+" Traps were Received");
  	    //System.out.println(drops+" traps were droped");        
     }
    catch(Exception x)
	{
 	 x.printStackTrace();
	}

} 

 
 
} 
 

 
 
 
