/* $Id: FilterCommandAlertAction.java,v 1.2 2010/10/29 13:45:49 swaminathap Exp $ */
package com.adventnet.nms.alertdb;


//java imports

import java.util.Properties;
import com.adventnet.nms.eventdb.EventMgr;
import com.adventnet.nms.eventdb.FilterCommand;
import com.adventnet.nms.eventdb.NotifierList;
import com.adventnet.nms.eventdb.FilterActionBaseImpl;
import com.adventnet.nms.eventdb.Event;


/**
 *  FilterCommandAlertAction will be started as a separate process.The FilterCommandAlertAction extended the 
* FilterActionBaseImpl class to get buffer mechanism.when incoming alert have run command action,
* alert will be converted corresponding action objects and it will added the input queue.
* since we have used buffer mechanism,if the queue reached maximum size,action object from the
* queue copied from input queue to database table. The FilterCommandAlertAction thread will copy the action 
* from input queue to output queue and the action will be executed by iterating output queue.
* Note : FilterCommandAlertAction class will be runnable class
*/
// FilterCommandAlertAction


//SendEmailAlertAction
public class FilterCommandAlertAction extends FilterActionBaseImpl implements Runnable 

{
	//Action Name
	private String axnKey="ACTION_NAME";  // No I18N
	private boolean startFAThread=true;
	
	
	public void initialize() 
	{	
		
		initializeFilterAction("com.adventnet.nms.eventdb.FilterCommand","alert","FilterCommandAlertAction","COMMANDPROPS"); // No I18N
		schedule();  //start the scheduler at first time,this method will be called when instantiating this object		

	}	
	/*we have create a separate scheduler to execute the filter actions.we have used  same scheduler to schedule the FilterCommandAlertAction */
	public void schedule()
	{
		
		FaultMgr.actionSchedulerForAlert.scheduleTask(this,System.currentTimeMillis()+100);	

	}	
	/* Need to check before the shutdown the server ,whether we need to execute
	 the filter action available in output queue based on parameter
	 gracefulShutDownFilterAction*/

	public void shutDown() 
	{
		super.shutDown();
	}
		
	// Add the events to input filter queue
	public void addEventToInQ(Event event) 
	{
		//no need implement anything here
	}	
	
	/* If the incoming alert match filter action ,this method will be called .we are constructing the action objects from the alert
	   the objects will be added to the input queue.In case if the queue reached maximum size .All action objects from the
	   queue copied from input queue to database table */
	public void addAlertToInQ(Alert alt) 
	{
		Properties prop=getActionObjFromAlert(alt);
		addObjectToInQ(prop);		
	}
	
	// start the thread
	
	public void run() 
	{
		runAction();  // No I18N
		if(!EventMgr.getFaultMgr().shutDownFlag && startFAThread )
		{			
			schedule();
		}		
		
	}	
	/*Constructing the run command action object from the alert*/
	public Properties getActionObjFromAlert(Alert alt)
	{
		 FilterCommand act;
         String axnName=alt.getUserProperty(axnKey);
         Object obj = NotifierList.getFilterAction(axnName);
         act=(FilterCommand)obj;

         Properties filterCommandProps =new Properties();
         filterCommandProps=act.getProperties();
     
         filterCommandProps.put("command", act.parseStr(act.command,alt));// No I18N
         filterCommandProps.put("time",act.parseStr(act.time,alt));// No I18N
         return filterCommandProps;

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
	
}
