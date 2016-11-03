/* $Id: SendEmailEventAction.java,v 1.2 2010/10/29 13:45:49 swaminathap Exp $ */
package com.adventnet.nms.eventdb;

// java imports
import java.util.Properties;
import com.adventnet.nms.alertdb.Alert;
/**
 * SendEmailEventAction will be started as a separate process.The SendEmailEventAction extended the 
 * FilterActionBaseImpl class to get buffer mechanism.when incoming events have email action,
 * events will be converted corresponding action objects and it will added the input queue.
 * since we have used buffer mechanism,if the queue reached maximum size,action object from the
 * queue copied from input queue to database table. The SendEmailEventAction thread will copy the action 
 * from input queue to output queue and the action will be executed by iterating output queue.
 * Note : SendEmailEventAction class will be runnable class
 * 
 */

// SendEmailEventAction
public class SendEmailEventAction extends FilterActionBaseImpl implements Runnable 

{
	//Action Name
	private String axnKey="ACTION_NAME";  // No I18N
	private boolean startFAThread=true;	
	/*To initialize the startup variable,since */
	public void initialize() 
	{			
		initializeFilterAction("com.adventnet.nms.eventdb.SendEmail","event","SendEmailEventAction","EMAILPROPS"); //No I18N	
		schedule();

	}	
	/*we have create a separate scheduler to execute the filter actions.we have used  same scheduler to schedule the SendEmailEventAction */
	public void schedule()
	{
		EventMgr.actionScheduler.scheduleTask(this, System.currentTimeMillis() + 100);

	}	
	/* Need to check before the shutdown the server ,whether we need to execute
	 the filter action available in output queue based on parameter
	 gracefulShutDownFilterAction*/

	public void shutDown() 
	{
		super.shutDown();
	}
		
	/* If the incoming events match email action ,this method will be called .we are constructing the action objects from the event
	   the objects will be added to the input queue.In case if the queue reached maximum size .All action objects from the
	   queue copied from input queue to database table */
	public void addEventToInQ(Event event) 
	{
		Properties prop=getActionObjFromEvent(event);
		addObjectToInQ(prop);			
	}	
	
	
	public void addAlertToInQ(Alert alt) 
	{		
	}
	// start the thread
	
	public void run() 
	{
		runAction(); //No I18N
		if(!EventMgr.emgr.shutDownFlag && startFAThread )
		{			
			schedule();
		}		
		
	}	
	/*Constructing the send email action object from the event*/
	private Properties getActionObjFromEvent(Event e)
	{
		SendEmail act;		
		String axnName=e.getUserProperty(axnKey);
		Object obj = NotifierList.getFilterAction(axnName);
		act=(SendEmail)obj;		
		Properties sendEmailProps =new Properties();
		sendEmailProps=act.getProperties();
		sendEmailProps.put("subject", act.parseStr(act.subject,e,true));     //No I18N
		sendEmailProps.put("server",act.parseStr(act.smtpAccountName,e,true)); //No I18N
		sendEmailProps.put("message",act.parseStr(act.message, e, true)); //No I18N
		sendEmailProps.put("source", e.getSource());		//No I18N
		return sendEmailProps;
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
}
