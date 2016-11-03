/* $Id: SendTrapEventAction.java,v 1.2 2010/10/29 13:45:49 swaminathap Exp $ */

package com.adventnet.nms.eventdb;


//Nms imports
import java.util.Vector;
import java.util.Enumeration;

import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.management.log.Log;
import com.adventnet.nms.alertdb.Alert;
/**
 * SendTrapEventAction  will be started as a separate process.
 * when incoming events have sendTrap action,it will be executed in separated thread.
 * It will take the events from input vector and execute the filter action  
 * 
 * 
 */
public class SendTrapEventAction implements FilterActionInterface,Runnable
{
		
//adding events to input queue
	
	
	private Vector inQForFilter = new Vector();
	private Vector outQForFilter = new Vector();
	
	private String axnKey="ACTION_NAME"; //No I18N
	private Integer syncInQ= new Integer(1);
	private boolean startFAThread=true;
	//Initialize the sceduler
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
					Event evt = (Event)en.nextElement();					
					if(!EventMgr.emgr.shutDownFlag || EventMgr.emgr.gracefulShutDownFilterAction)
					{					
							try
							{
								String axnName=evt.removeUserProperty(axnKey);
								Object obj = NotifierList.getFilterAction(axnName);
								SendTrap act = (SendTrap)obj;
								act.runAction(evt);
							}
							catch(Exception anye)
							{
								NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception in SendTrapEventAction method while processing trap filter action from output queue")+anye,anye) ; //No I18N
								
							}
					}
					
				}
				outQForFilter.removeAllElements();			
				
			}		
			catch(Exception anye)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception in SendTrapEventAction.run() method Thread ")+anye,anye) ; //No I18N
			}		
			
			if(!EventMgr.emgr.shutDownFlag && startFAThread )
			{
				schedule();
			}	
			
		}	
	

	/* If the incoming events match sendtrap action ,this method will be called .we cannot use the buffer mechanism to store sendtrap object.
	 * trap actions contains varbinds ,we cannot store them in table.If the queue reached maximum size ,we will remove event from input queue based in REMOVE_ACTION_FROM_QUEUE
	 parameter.Here there are chance to loss the event by executing it */
	
	public void addEventToInQ(Event e)
	{
		Event oldEvent;
		synchronized(syncInQ)
		{
			oldEvent=(Event)e.clone();
			
			int queueSize=inQForFilter.size();
			if(queueSize>=EventMgr.emgr.MAX_FILTER_ACTION_DATA_QUEUE)
			{
				if(EventMgr.emgr.REMOVE_ACTION_FROM_QUEUE==0)
				{
					NmsLogMgr.MISCERR.log("SendTrap Filter Action queue is full ,REMOVE_ACTION_FROM_QUEUE parameter is configured as zero,so incoming actions cannot be add into the queue",Log.VERBOSE); //No Internationalisation                          		
				}
				else if(EventMgr.emgr.REMOVE_ACTION_FROM_QUEUE>=EventMgr.emgr.MAX_FILTER_ACTION_DATA_QUEUE)
				{
					NmsLogMgr.MISCERR.log("SendTrap Filter Action queue is full,REMOVE_ACTION_FROM_QUEUE value is greater than MAX_FILTER_ACTION_DATA_QUEUE ,emptying the imput action queue and added the incoming actions to the queue",Log.VERBOSE); //No Internationalisation                          		
					inQForFilter = new Vector();  
					inQForFilter.addElement(oldEvent);
				}
				else
				{
					int removeElements=EventMgr.emgr.REMOVE_ACTION_FROM_QUEUE; //removing no of actions from the queue based on value to set in 	int s=inQForFilter.size();
					 
					NmsLogMgr.MISCERR.log("SendTrap Filter Action queue is full,REMOVE_ACTION_FROM_QUEUE is configured as "+removeElements+ " so action removed from the queue and added further actions ",Log.VERBOSE); //No Internationalisation                        
				
					int fromIndex=queueSize-removeElements;
					int toIndex=queueSize-1;					
					for(int i=toIndex;i>=fromIndex;i--)
					{
						inQForFilter.removeElementAt(i);
					}				
					inQForFilter.addElement(oldEvent);											
				}						
			}		
			else
			{
				inQForFilter.addElement(oldEvent);
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
		Event evt;
		int count =	inQCopyForFilter.size();	
		for (int i=0;i<count;i++) 
		{
			evt = (Event) inQCopyForFilter.elementAt(i);
			outQForFilter.addElement(evt);
		}
		inQCopyForFilter=null;		
		
	}
	/*We have create a separate scheduler to execute the filter actions.we have used  same scheduler to schedule the SendTrapEventAction */
	public void schedule() 
	{
	      	EventMgr.actionScheduler.scheduleTask(this,System.currentTimeMillis()+100);
	}
		
//	Need to check before the shutdown the server ,whether we need to execute the filter action available in output queue based on parameter gracefulShutDownFilterAction 	
	
	public void shutDown()
	{
		
		 while(EventMgr.emgr.gracefulShutDownFilterAction && (inQForFilter.size() > 0 || outQForFilter.size()>0))
         {         
	       NmsLogMgr.MISCERR.log("Before shutdown the server,we are executing trap filter action for the events",Log.VERBOSE); //No Internationalisation
    	   run();		
		   try
		   {
				 Thread.sleep(500);
		   }
		   catch(Exception e) 
		   {
				 NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception  while  shut down the  Server while executing the trap filter action in separate thread  "), e);//No I18N

		   }
        }
	}
	
	public void addAlertToInQ(Alert alt) 
	{
	   //No need to provide implementation here.
	}
	
	/**This method can be used to pause the filter action thread at runtime which is running. 
	 * This method is invoked on calling the EventAPI.pauseFilterAction() method
	 */
	public void pauseFilterAction()
	{
		startFAThread=false;		
	}
	/**This method can be used to resume the filter action thread at runtime which has been paused. 
	 * This method is invoked on calling the EventAPI.resumeFilterAction() method
	 */
	public void resumeFilterAction()
	{
		startFAThread=true;
		schedule();
	}
	
	public void cleanFilterActionTable()
	{
		//No need provide implemenation here.since we have not used any table for sendTrap action
	}

}

