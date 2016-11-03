/* $Id: FilterCommandEventAction.java,v 1.2 2010/10/29 13:45:49 swaminathap Exp $ */
package com.adventnet.nms.eventdb;

// java imports
import java.util.Properties;

import com.adventnet.nms.alertdb.Alert;
/**
 * FilterCommandEventAction will be started as a separate process.The FilterCommandEventAction extended the 
 * FilterActionBaseImpl class to get buffer mechanism.when incoming events have run command action,
 * events will be converted corresponding action objects and it will added the input queue.
 * since we have used buffer mechanism,if the queue reached maximum size,action object from the
 * queue copied from input queue to database table. The FilterCommandEventAction thread will copy the action 
 * from input queue to output queue and the action will be executed by iterating output queue.
 * Note : FilterCommandEventAction class will be runnable class
 */

// SendEmailEventAction
public class FilterCommandEventAction extends FilterActionBaseImpl implements Runnable 

{
	//Action Name
	private String axnKey="ACTION_NAME";  // No I18N
	private boolean startFAThread=true;
	
	/*To initialize the startup variable*/
	public void initialize() 
	{
		initializeFilterAction("com.adventnet.nms.eventdb.FilterCommand","event","FilterCommandEventAction","COMMANDPROPS"); //No I18N 
		schedule();//start the scheduler at first time,this method will be called when instantiating this object		
	}
	/* If the incoming events match filter action ,this method will be called .we are constructing the action objects from the event
	   the objects will be added to the input queue.In case if the queue reached maximum size .All action objects from the
	   queue copied from input queue to database table */

	public void addEventToInQ(Event event)
	{
		Properties prop=this.getActionObjFromEvent(event);
		addObjectToInQ(prop);
		
	}
	
	public void addAlertToInQ(Alert alt) 
	{
		//No need to provide implementation here.
	}
	/*we have create a separate scheduler to execute the filter actions.we have used  same scheduler to schedule the FilterCommandEventAction */
	public void schedule() {
		EventMgr.actionScheduler.scheduleTask(this, System.currentTimeMillis() + 100);

	}
	/*Constructing the run command action object from the event*/
	public Properties getActionObjFromEvent(Event e)
	{
		 FilterCommand act;
         String axnName=e.getUserProperty(axnKey);
         Object obj = NotifierList.getFilterAction(axnName);
         act=(FilterCommand)obj;

         Properties filterCommandProps =new Properties();
         filterCommandProps=act.getProperties();       
         filterCommandProps.put("command", act.parseStr(act.command,e,true)); //No I18N
         filterCommandProps.put("time",act.parseStr(act.time,e,true));   //No I18N
         return filterCommandProps;

	}
	
	
	/* Need to check before the shutdown the server ,whether we need to execute
	the filter action available in output queue based on parameter
	 gracefulShutDownFilterAction*/

	public void shutDown() 
	{
		super.shutDown();
	}
	// start the thread

	public void run() 
	{
		runAction();  //No I18N
		if(!EventMgr.emgr.shutDownFlag && startFAThread )
		{			
			schedule();
		}
		
	}
	/**This method can be used to resume the filter action thread at runtime which has been paused. 
	 * This method is invoked on calling the EventAPI.resumeFilterAction() method
	 */
	public void pauseFilterAction()
	{
		startFAThread=false;		
	}
	/**This method can be used to pause the filter action thread at runtime which is running. 
	 * This method is invoked on calling the EventAPI.pauseFilterAction() method
	 */
	public void resumeFilterAction()
	{
		startFAThread=true;
		schedule();
	}
	
}
