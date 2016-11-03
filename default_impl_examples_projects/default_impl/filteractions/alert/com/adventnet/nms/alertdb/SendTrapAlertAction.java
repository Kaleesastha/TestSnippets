/* $Id: SendTrapAlertAction.java,v 1.2 2010/10/29 13:45:49 swaminathap Exp $ */

package com.adventnet.nms.alertdb;

/**SendTrapAlertAction  will be started as a separate process.
 * when incoming alerts have sendTrap action,it will be executed in separated thread.
 * It will take the alert from input vector and execute the filter action  
 * */

import java.util.Enumeration;
import java.util.Vector;

import com.adventnet.management.log.Log;
import com.adventnet.nms.eventdb.EventMgr;
import com.adventnet.nms.eventdb.NotifierList;
import com.adventnet.nms.eventdb.SendTrap;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.eventdb.FilterActionInterface;
import com.adventnet.nms.eventdb.Event;

public class SendTrapAlertAction implements Runnable, FilterActionInterface
{
	
//	adding events to input queue
	
	private Vector inQForFilter = new Vector();
	private Vector outQForFilter = new Vector();
	private boolean startFAThread=true;
	private String axnKey="ACTION_NAME"; //No I18N
	private Integer syncInQ= new Integer(1);

	// Initialize the scheduler

	public void initialize()
	{
		schedule();  //start the scheduler at first time,this method will be called when instantiating this object	
		
	}	
// start the thread
	
	public void run()
	{
		try
		{
			moveToOutQForFilter();			
			for (Enumeration en = outQForFilter.elements();en.hasMoreElements();) 
			{
				Alert alt = (Alert)en.nextElement();				
				if(!EventMgr.getFaultMgr().shutDownFlag || EventMgr.getFaultMgr().gracefulShutDownFilterAction)
				{					
						try
						{
							String axnName=alt.removeUserProperty(axnKey);
							SendTrap act = (SendTrap) NotifierList.getFilterAction(axnName);
							act.runAction(alt);
						}
						catch(Exception anye)
						{
							NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception in SendTrapAlertAction method while processing trap filter action from output queue")+anye,anye) ;//No I18N
							
						}
				}
				
			}
			outQForFilter.removeAllElements();			
			
		}	
		catch(Exception anye)
		{
			NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception in SendTrapAlertAction.run() method Thread ")+anye,anye) ; // No I18N
		}
			
		if(!EventMgr.getFaultMgr().shutDownFlag && startFAThread)
		{
			schedule();
		}
		
	}
	
	/* If the incoming alert match send trap action ,this method will be called .we cannot use the buffer mechanism to store sendtrap object.
	 * trap actions contains varbinds ,we cannot store them in table.If the queue reached maximum size ,we will remove alert from input queue based in REMOVE_ACTION_FROM_QUEUE
	 parameter.Here there are chance to loss the alert by executing it */

	public void  addAlertToInQ(Alert a)
	{
		Alert oldAlert=null;
		synchronized(syncInQ)
		{			
			oldAlert=(Alert)a.clone();
			int queueSize=inQForFilter.size();
			if(queueSize>=EventMgr.emgr.MAX_FILTER_ACTION_DATA_QUEUE)
			{
				int removeElements=EventMgr.emgr.REMOVE_ACTION_FROM_QUEUE; //removing no of actions from the queue based on value to set in 	int s=inQForFilter.size();
			
				if(removeElements==0)
				{
					NmsLogMgr.MISCERR.log("SendTrap Filter Action queue is full ,REMOVE_ACTION_FROM_QUEUE parameter is configured as zero,so incoming actions cannot be add into the queue",Log.VERBOSE); //No Internationalisation                          		
				}
				else if(removeElements>=EventMgr.emgr.MAX_FILTER_ACTION_DATA_QUEUE)
				{
					NmsLogMgr.MISCERR.log("SendTrap Filter Action queue is full,REMOVE_ACTION_FROM_QUEUE value is greater than MAX_FILTER_ACTION_DATA_QUEUE ,emptying the imput action queue and added the incoming actions to the queue",Log.VERBOSE); //No Internationalisation                          		
					inQForFilter = new Vector();  
					inQForFilter.addElement(oldAlert);
				}
				else
				{			
					 
					NmsLogMgr.MISCERR.log("SendTrap Filter Action queue is full,REMOVE_ACTION_FROM_QUEUE is configured as "+removeElements+ " so action removed from the queue and added further actions ",Log.VERBOSE); //No Internationalisation                        
				
					int fromIndex=queueSize-removeElements;
					int toIndex=queueSize-1;					
					for(int i=toIndex;i>=fromIndex;i--)
					{
						inQForFilter.removeElementAt(i);
					}				
					inQForFilter.addElement(oldAlert);												
				}					
				
			}
			else
			{
				inQForFilter.addElement(oldAlert);
			}

		}
	
	}

	
	//Moves the event from input Q to output Q and execute the events
	public  void moveToOutQForFilter() 
	{
		Vector inQCopyForFilter=null;
		
		synchronized(syncInQ)
        {
            inQCopyForFilter = inQForFilter;
            inQForFilter = new Vector();
        }
		Alert alt;
		int count =	inQCopyForFilter.size();	
		for (int i=0;i<count;i++) 
		{
			alt = (Alert) inQCopyForFilter.elementAt(i);
			outQForFilter.addElement(alt);
		}
		inQCopyForFilter=null;		
		
	}
	
	/*we have create a separate scheduler to execute the filter actions.we have used  same scheduler to schedule the SendTrapAlertAction */
	public void schedule() 
	{
	   FaultMgr.actionSchedulerForAlert.scheduleTask(this,System.currentTimeMillis()+100);
	}
	
	
// Need to check before the shutdown the server ,whether we need to execute the filter action available in output queue based on parameter gracefulShutDownFilterAction 	
	
	public void shutDown()
	{
		
		 while(EventMgr.getFaultMgr().gracefulShutDownFilterAction && (inQForFilter.size() > 0 || outQForFilter.size()>0))
         {
          	  NmsLogMgr.MISCERR.log("Before shutdown the server,we are executing trap filter action for the alerts",Log.VERBOSE); //No Internationalisation

			 run();		
			 try
			 {
				 Thread.sleep(500);
			 }
			 catch(Exception e) 
			 {
				 NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception  while  shut down the  Server while executing the filter action in separate thread  "), e);  //NO I18N

			 }
        }
	}

	public void addEventToInQ(Event evt) 
	{
		//no need to implement anything there.
	}
	
	/**This method can be used to resume the filter action thread at runtime which has been paused. 
	 * This method is invoked on calling the AlertAPI.resumeFilterAction() method
	 */
	public void pauseFilterAction()
	{
		startFAThread=false;		
	}
	/**This method can be used to pause the filter action thread at runtime which is running. 
	 * This method is invoked on calling the AlertAPI.pauseFilterAction() method
	 */
	public void resumeFilterAction()
	{
		startFAThread=true;
		schedule();
	}
	public void cleanFilterActionTable()
	{
		//No need provide implementation here.since we have not used any table for sendTrap action
	}

}

