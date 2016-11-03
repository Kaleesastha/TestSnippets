/* $Id: SendEmailAlertAction.java,v 1.2 2010/10/29 13:45:49 swaminathap Exp $ */

package com.adventnet.nms.alertdb;


//java imports

import java.util.Properties;
import com.adventnet.nms.eventdb.EventMgr;
import com.adventnet.nms.eventdb.NotifierList;
import com.adventnet.nms.eventdb.SendEmail;
import com.adventnet.nms.eventdb.FilterActionBaseImpl;
import com.adventnet.nms.eventdb.Event;

/**
 * SendEmailAlertAction will be started as a separate process.The SendEmailAlertAction extended the 
 * FilterActionBaseImpl class to get buffer mechanism.when incoming alert have email action,
 * alert will be converted corresponding action objects and it will added the input queue.
 * since we have used buffer mechanism,if the queue reached maximum size,action object from the
 * queue copied from input queue to database table. The SendEmailAlertAction thread will copy the action 
 * from input queue to output queue and the action will be executed by iterating output queue.
 * Note : SendEmailAlertAction class will be runnable class
 * 
 */
// SendEmailAlertAction
public class SendEmailAlertAction extends FilterActionBaseImpl implements Runnable 

{
	//Action Name
	private String axnKey="ACTION_NAME";  // No I18N
	private boolean startFAThread=true;
		
	/*To initialize the startup variable*/
	public void initialize() 
	{	
		
		initializeFilterAction("com.adventnet.nms.eventdb.SendEmail","alert","SendEmailAlertAction","EMAILPROPS");     //No I18N  
		schedule();  //start the scheduler at first time,this method will be called when instantiating this object		

	}	
	/*we have create a separate scheduler to execute the filter actions.we have used  same scheduler to schedule the SendEmailAlertAction */
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
		
	
	public void addEventToInQ(Event event) 
	{
		//no need to implement anything here
	}	
	/* If the incoming alert match email action ,this method will be called .we are constructing the action objects from the alert
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
		runAction(); //No I18N
		if(!EventMgr.getFaultMgr().shutDownFlag && startFAThread )
		{			
			schedule();
		}		
		
	}	
	/*Constructing the send email action object from the alert*/
	private Properties getActionObjFromAlert(Alert alt)
	{
		SendEmail act;		
		String axnName=alt.getUserProperty(axnKey);
		Object obj = NotifierList.getFilterAction(axnName);
		act=(SendEmail)obj;		
		Properties sendEmailProps =new Properties();
		sendEmailProps=act.getProperties();		
		sendEmailProps.put("subject", act.parseStr(act.subject,alt)); //No I18N
		sendEmailProps.put("server",act.parseStr(act.smtpAccountName,alt)); //No I18N
		sendEmailProps.put("message",act.parseStr(act.message, alt)); //No I18N
		sendEmailProps.put("source", alt.getSource());		 //No I18N
		return sendEmailProps;
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
