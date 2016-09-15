import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class ConsistencyChecker
{
    private String host = null;
    private String db = null;
    private Connection conn = null;
    private PrintStream ps = null;   
    private String fileName = null;
    private boolean exitWhenCompleted = true;

    public ConsistencyChecker()
    {
	ConnectBox conBox = new ConnectBox(null);
		
	conBox.show();
	conn = conBox.getConnection();
	start(conBox.getHost(), conBox.getdb(), conn);
    }
    
    public ConsistencyChecker(String hostName, String database, Connection con)
    {
	exitWhenCompleted = false;
	start(hostName, database, con);
    }
	
    private void start(String hostName, String database, Connection con)
    {
	host = hostName;
	db = database;
	conn = con;
	openFile();
	
	if (!canStart())
	    return;
	
	try { checkDataConsistency(); }
	catch(Exception ex) {}
    }
	
    public void checkDataConsistency() throws Exception
    {
	printStartMessages();
	
	customCheck();
	checkParentChildStatusConsistency();
	checkMO_AlertStatusConsistency();
	checkEventAlertStatusConsistency();	
	checkForDuplicateEvents();

	printEndMessages();
	exit();
    }

    private void customCheck() {}
	
    private void checkMO_AlertStatusConsistency() throws Exception
    {
	String queryToCheckTopoVsFault = 
	    "select name, status, severity from ManagedObject, Alert where (name=source) and (status != severity)";
		
	Statement stm = null;
	ResultSet rset = null;
	
	try
	{
	    stm = conn.createStatement();
	    rset = stm.executeQuery(queryToCheckTopoVsFault);
		    
	    ps.println("CHECK : Consistency between status of MO and Alert");
	    ps.println("**************************************************");
	    ps.println();
	    boolean empty = true;
	    while(rset.next())
	    {
		empty = false;

		ps.println("MO = " + rset.getString(1));
		ps.println("Status = " + rset.getString(2) + " Alert Status = " + rset.getString(3));
		ps.println();
	    }
	    if(empty)
		ps.println("Check : PASSED");
	    ps.println();
			
	    System.out.println("Alert MO Status check completed");
	}
	catch(Exception test1e)
	{
	    System.out.println("Exception while running test1 "+test1e);
	    test1e.printStackTrace();
	}
	finally
	{
	    ps.flush();
	    if (stm != null) stm.close();
	    if (rset != null) rset.close();
	}
    }
	
    private void checkEventAlertStatusConsistency() throws Exception
    {
	ps.println("CHECK : Consistency between Events and Alerts");
	ps.println("*********************************************");
	ps.println();

	Statement stm = null;
	ResultSet rset = null;

	try
	{	    
	    String queryToCheckEventVsAlert = 
		"select Event.entity, Alert.entity, Event.severity, Alert.severity from Event, Alert where (Event.ID=Alert.ID) and (Event.severity != Alert.severity or Event.entity != Alert.entity)";
	    stm = conn.createStatement();
	    rset = stm.executeQuery(queryToCheckEventVsAlert);
		    
	    boolean empty = true;

	    while(rset.next())
	    {
		empty = false;
		ps.println("EVENT ENTITY = " + rset.getString(1) + ";" + "ALERT ENTITY = " + rset.getString(2));
		ps.println("Event Status = " + rset.getString(3) + ";" + "Alert Status = " + rset.getString(4));
		ps.println();
	    }
			
	    if(empty)
		ps.println("Check : PASSED");
	    ps.println();
			
	    System.out.println("Event Alert Consistency check completed");
	}
	catch(Exception test2e)
	{
	    System.out.println(" Exception while running test1 "+test2e);
	    test2e.printStackTrace();
	}
	finally
	{
	    ps.flush();
	    if (stm != null) stm.close();
	    if (rset != null) rset.close();
	}
    }
	
    /*
      private void checkEventAlertStatusConsistency() throws Exception
      {
      ps.println("CHECK : Consistency between Events and Alerts");
      ps.println("*********************************************");
      ps.println();

      Statement stm1 = null;
      Statement stm2 = null;
      ResultSet rset1 = null;
      ResultSet rset2 = null;

      try
      {
      String queryToGetMaxIdsOfEvents = 
      "select max(id) from Event group by entity";
      stm1 = conn.createStatement();
      rset1 = stm1.executeQuery(queryToGetMaxIdsOfEvents);
		    
      StringBuffer temp = new StringBuffer();
      temp.append("''");
			
      while(rset1.next())
      temp.append(", '"+rset1.getString(1)+"'");
		    	    
      String commaSepIDs = temp.toString();
		    
      String queryToCheckEventVsAlert = 
      "select Event.entity, Event.severity, Alert.severity from Event, Alert where (Event.ID=Alert.ID) and (Alert.ID in ( "+commaSepIDs+" ) )"+" and Event.severity != Alert.severity";
      stm2 = conn.createStatement();
      rset2 = stm2.executeQuery(queryToCheckEventVsAlert);
		    
      boolean empty = true;

      while(rset2.next())
      {
      empty = false;
      ps.println("EVENT ENTITY = " + rset2.getString(1));
      ps.println("Event Status = " + rset2.getString(2) + " Alert Status = " + rset2.getString(3));
      ps.println();
      }
			
      if(empty)
      ps.println("Check : PASSED");
      ps.println();
			
      System.out.println("Event Alert Consistency check completed");
      }
      catch(Exception test2e)
      {
      System.out.println(" Exception while running test1 "+test2e);
      test2e.printStackTrace();
      }
      finally
      {
      ps.flush();
      if (stm1 != null) stm1.close();
      if (rset1 != null) rset1.close();
      if (stm2 != null) stm2.close();
      if (rset2 != null) rset2.close();
      }
      }
    */
		
    private void checkForDuplicateEvents() throws Exception
    {
	ps.println("CHECK : Checking for duplicate events");
	ps.println("*************************************");
	
	Statement stm1 = null;
	ResultSet rset1 = null;
	Statement stm2 = null;
	ResultSet rset2 = null;
		
	try
	{
	    String queryToGetAlertEntities = 
		"select entity,severity from Alert";
	    stm1 = conn.createStatement();
	    rset1 = stm1.executeQuery(queryToGetAlertEntities);

	    Vector noAlertEvents = new Vector();
	    boolean empty = true;
		   
	    while(rset1.next())
	    {
		String entityName = rset1.getString(1);
					
		String queryToCheckCons_Entities = 
		    "select entity, ID, severity from Event where entity = '"+entityName+"' order by -ID";
		// NOTE "order by -ID". This means descending order by ID. "order by ID" would do the opposite.   
		stm2 = conn.createStatement();
		rset2 = stm2.executeQuery(queryToCheckCons_Entities);
					
		rset2.next();
		
		EventData event1 = new EventData(rset2.getString(1), rset2.getString(2),
						 rset2.getString(3));
		int severity = event1.getSeverity();
		if ( shouldGenerateAlert(severity) && (severity != Integer.parseInt(rset1.getString(2))) )
		{
		    EventData event = new EventData(event1);
		    noAlertEvents.addElement(event);
		}

		int duplicateEvents = 0;
				
		while(rset2.next())
		{
		    EventData event2 = new EventData(rset2.getString(1), rset2.getString(2),
						     rset2.getString(3));
		    if(event1.getSeverity() != event2.getSeverity()) 
		    {
			event1.setData(event2);
			duplicateEvents = 0;
			continue;
		    }
		    else
		    {
			empty = false;
			duplicateEvents++;		
			if (duplicateEvents == 1)
			{
			    ps.println(); ps.println();
			    event1.print(ps);
			}
			event2.print(ps);
		    }
		}
		ps.flush();
	    }
		    
	    if(empty)
		ps.println("Check : PASSED");
	    ps.println();
			
	    System.out.println("Duplicate Events check completed");
		
	    if (noAlertEvents.size() == 0)
		return;
		
	    ps.println("ALERTS ON FOLLOWING ENTITES CONTAIN INCONSISTENT SEVERITY");
	    ps.println("*********************************************************");
	    for (Enumeration e = noAlertEvents.elements(); e.hasMoreElements(); )
	    {
		EventData event = (EventData)e.nextElement();
		ps.println("ALERT ENTITY = " + event.getEntity());
		ps.println("LATEST EVENT = " + event.getId() + ";" + " SEVERITY " + event.getSeverity());
		ps.println();
	    }
	}
	catch(Exception test3e)
	{
	    System.out.println("Exception while running test1 "+test3e);
	    test3e.printStackTrace();
	}
	finally
	{
	    if (stm1 != null) stm1.close();
	    if (rset1 != null) rset1.close();
	    if (stm2 != null) stm2.close();
	    if (rset2 != null) rset2.close();
	}
    }
    
    private void checkParentChildStatusConsistency() throws Exception
    {
	ps.println("CHECK : Checking for consistent status of IF's and their parenNodes");
	ps.println("*******************************************************************");
	ps.println();
	
	Statement stm1 = null;
	ResultSet rset1 = null;
	Statement stm2 = null;
	ResultSet rset2 = null;
	Hashtable node_vs_status = null;
	
	try
	{
	    String query1 = "select parentnode, status from IpAddress, ManagedObject where IpAddress.parentnode= ManagedObject.name";
	    stm1 = conn.createStatement();
	    rset1 = stm1.executeQuery(query1);
	    
	    node_vs_status = new Hashtable();
	    while(rset1.next())
	    {
		int status = Integer.parseInt(rset1.getString(2));
		node_vs_status.put(rset1.getString(1), new IntWrapper(status));
	    }
	    stm1.close();
	    rset1.close();
	    
	    //System.out.println(" node_vs_status is "+node_vs_status);
	    
	    String query2 = "select parentnode, status from IpAddress, ManagedObject where IpAddress.name = ManagedObject.name";
	    stm2 = conn.createStatement();
	    rset2 = stm2.executeQuery(query2);
	    
	    while(rset2.next())
	    {
		IntWrapper nodeData = (IntWrapper)( node_vs_status.get(rset2.getString(1)) );
		int nodeStatus = nodeData.getStatus();
		int if_status = Integer.parseInt(rset2.getString(2));
		if ( lessSevere(nodeStatus, if_status) )
			nodeData.updateErrorState(-1);
		else if ( equallySevere(nodeStatus, if_status) )
			nodeData.updateErrorState(1);
	    }
		
		Vector lessSevereNodes = new Vector();
		Vector moreSevereNodes = new Vector();
		
	    for(Enumeration e = node_vs_status.keys();e.hasMoreElements();)
	    {
		String nodeName = (String)e.nextElement();
		IntWrapper nodeData = (IntWrapper)(node_vs_status.get(nodeName));
		int errorState = nodeData.getErrorState();
		if (errorState == -1)
			lessSevereNodes.addElement(nodeName);
		else if (errorState == 0)
			moreSevereNodes.addElement(nodeName);
	    }
		
		if ( (lessSevereNodes.size() > 0) || (moreSevereNodes.size() > 0) )
			ps.println("THE SEVERITY OF FOLLOWING NODES IS INCONSISTENT WITH INTERFACES :");
		
		boolean noError = true;
		for (int i = 0; i < lessSevereNodes.size(); i++)
		{
			noError = false;
			//if (i==0) ps.println("THE FOLLOWING NODES ARE LESS SEVERE THAN AT LEAST ONE INTERFACE :");
			ps.println("   " + lessSevereNodes.elementAt(i));
		}
		
		for (int i = 0; i < moreSevereNodes.size(); i++)
		{
			noError = false;
			//if (i==0) ps.println("THE FOLLOWING NODES ARE MORE SEVERE THAN ALL THE INTERFACES :");
			ps.println("   " + moreSevereNodes.elementAt(i));
		}
		if(noError)
		ps.println("Check : PASSED");
		ps.println();
	}
	catch(Exception ex)
	{
	    System.out.println(" Exception when doing NODE-IF test" + ex);
	    ex.printStackTrace();
	}
	finally
	{
	    if(rset2 != null) rset2.close();
	    if(stm2 != null) stm2.close();
	}
	
	System.out.println("IF vs Parent Node check completed");
    }
	
	private static final int CRITICAL = 1;
	private static final int CLEAR = 5;
	
	private static boolean unknownSeverity(int severity)
	{
		return (severity < CRITICAL) || (severity > CLEAR); 
	}
	
	private static boolean lessSevere(int status1, int status2)
	{
		if (unknownSeverity(status1))
			return !unknownSeverity(status2);
		else if (unknownSeverity(status2))
			return false;

		return status1 > status2;
	}
	
	private static boolean equallySevere(int status1, int status2)
	{
		if (lessSevere(status1, status2))
			return false;
		if (unknownSeverity(status1) && unknownSeverity(status2))
			return true;
		/*
		if (unknownSeverity(status2) && (status1 == CLEAR))
			return true;
		*/
		
		return status1 == status2;
	}

	private static boolean shouldGenerateAlert(int severity)
	{
	    return (severity >= CRITICAL) && (severity <= CLEAR);
	}
	
    private static class IntWrapper
    {
	private int status = 0;
	private int errorState = 0;	// UNKNOWN
	
	public IntWrapper(int stat)
	{
		this.status = stat;
	}

	public int getStatus()
	{
	    return this.status;
	}

	public void updateErrorState(int err)
	{
		if (errorState >= 0)
			errorState = err;
	}
	public int getErrorState()
	{
	    return errorState;
	}
	
    }

    private void openFile()
    {
	String dir = "./";
	String baseFileName = "DatabaseChecker";
	String extn = ".txt";
	fileName = baseFileName +"_"+ host + extn;
	File file = new File(dir+fileName);
	int index = 0;
	while (true)
	{
	    if (!file.exists())
		break;
	    index++;
	    fileName = baseFileName+"_"+host+index+extn;
	    file = new File(dir+fileName);
	}
		
	try
	{
	    ps = new PrintStream( new BufferedOutputStream( new FileOutputStream(file)));
	}
	catch(Exception reOut)
	{
	    System.out.println("Exception while re-directing output"+ reOut);
	}
    }
	
    private boolean canStart()
    {
	if ( (conn != null) && (fileName != null) && (ps != null) )
	    return true;
	
	if (conn == null)
	    JOptionPane.showMessageDialog(null, "Connection not established. Please reconnect.", "No Connection", JOptionPane.ERROR_MESSAGE);   	
	else if ( (fileName == null) || (ps == null) )
	    JOptionPane.showMessageDialog(null, "Unable to open output file.", "File Open Error", JOptionPane.ERROR_MESSAGE);   	
	
	return false;
    }
	
    private void printStartMessages()
    {
	System.out.println("---------Started Consistency checker----------");
	
	ps.println("CONSISTENCY CHECKER on Host = " + host + " DB = " + db);
	ps.println();
	ps.println();
    }
	
    private void printEndMessages()
    {
	ps.println("**** CONSISTENCY CHECKER COMPLETED ****");
	ps.flush();
	
	System.out.println("Consistency Checker completed");
	JOptionPane.showMessageDialog(null, "Completed Consistency check. Please open " + 
				      fileName + " to view the test results.","Completed",JOptionPane.INFORMATION_MESSAGE);

    }
	
    private void exit()
    {
	if ( exitWhenCompleted )
	System.exit(0);
    }
	
    private static class EventData
    {
	private String entity;
	private String id;
	private int severity;
		
	public EventData(String entity, String id, String sev)
	{
	    this.entity = entity;
	    this.id = id;
	    this.severity = Integer.parseInt(sev);
	}
		
	public EventData(EventData event)
	{
	    setData(event);
	}
	
	public void setData(EventData event)
	{
	    this.entity = event.entity;
	    this.id = event.id;
	    this.severity = event.severity;			
	}
		
	public String getEntity() 
	{
	    return entity;
	}
	public String getId()
	{
	    return id;
	}
	public int getSeverity()
	{
	    return severity;
	}
	
	public void print(PrintStream ps)
	{
	    ps.println("EVENT ENTITY = " + entity + "; ID = " + id + "; Severity = " + severity);
	}
    }
    
    public static void main(String[] args)
    {
	new ConsistencyChecker();
    }
}









