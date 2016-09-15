/**
$Id: PollAPItesting.java,v 1.2 2003/06/18 08:57:31 elangovan Exp $

File Name      : PollAPItesting.java
Description    : This program contains 16 of the PollAPI methods in a more userfrienlier way.
Other Info     : This program is a more generalised version to validate 16 methods of Poll API 		 testplan.

USAGE          : java PollAPIPolledData2 <host name> <RMI PortNumber>	

 
Things to do before running this program:
	1.Make sure that this program(PollAPIPolledData2.java) is stored in the <WEBNMS_HOME>/	
	classes directory.
	2.Compile the program and then Recompile with -deprecation.
	3.Have the following jar files in the CLASSPATH(AdventNetUI.jar,AdventNetUtils.jar,	
	ApiUtils.jar,BeanBuilderClasses.jar,ClientAPI.jar,NmsClientClasses.jar,jcchartK.jar,
	servlet.jar,jta.jar,jta20.jar,ManagementClient.jar,ManagementServer.jar,AdventNetFramework.
	jar,AdventNetBeanBuilder.jar,javax.servlet.jar,ConfigPanel.jar,crimson.jar,xalan.jar,jaxp.
	jar)
	4.Enter the number of the method ,that is to be executed.


Owner Name     : G.Elangovan	

Change History	: G.Elangovan		03-06-2003		Initial version 

**/


import com.adventnet.nms.util.*;
import com.adventnet.nms.poll.*;
import com.adventnet.nms.poll.PolledData;
import com.adventnet.nms.topodb.*;
import java.io.*;
import java.sql.*;
import java.rmi.*;
import java.util.*;
import java.net.InetAddress;

public class PollAPItesting
{
    public static void main(String a[])
    {
	PollAPI pollapi=null;
	TopoAPI topoapi=null;
        
        if(a!=null && a.length<2)
        {
           System.out.println("USAGE: java PollAPIPolledData hostName RmiPortnumber");
           return;
        }
        
        String hostName = a[0];
	String portNumber=a[1];
	{
        	try
        	{
           		 pollapi = (PollAPI)Naming.lookup("rmi://" + hostName + ":"+ portNumber +"/PollAPI");
           		 System.out.println("Got pollapi handle");
        	}
       		 catch(Exception e)
        	{
           		 System.out.println("exception while getting pollapi handle"+e);
          		  return;
        	}
	}
	BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        while(true) 
            {
                System.out.println("\n--------------------------------\n");
                System.out.println("Methods available for testing");
		System.out.println("\n--------------------------------\n");

		System.out.println("1.  addPoll");
		System.out.println("2.  addthresholdobject");
		System.out.println("3.  deletePoll");
		System.out.println("4.  deleteThresholdObject");
		System.out.println("5.  getActivePollers");
		System.out.println("6.  getAllThresholdObjects");
		System.out.println("7.  getcollectedData");	
		System.out.println("8.  getCollectedValues");
		System.out.println("9.  getInstances");
		System.out.println("10. getList");	
		System.out.println("11. getNumPollObjects");
              	System.out.println("12. getObjectNamesWithProps");
		System.out.println("13. getObjects");
		System.out.println("14. getPolleddata");
		System.out.println("15. getPolledDataForNode");
		System.out.println("16. getPolledDataForNodes");
		System.out.println("17. getPollsForAgent");
		System.out.println("18. getPropertiesOfObject");
		System.out.println("19. getTableNames");
		System.out.println("20. modifypoll");
		System.out.println("21. Modifythresholdobject");
		System.out.println("22. removePoll");
		System.out.println("23. resumeAllPollsForAgent");
		System.out.println("24. removepolleddatafornode");
		System.out.println("25. setdebugmode");	
		System.out.println("26. setPolledDataForNode");
		System.out.println("27. setPolledDataForNodes");
		System.out.println("28. startDataPoll");
		System.out.println("29. stopall");
		System.out.println("30. stopDataPoll");	
		System.out.println("31. suspendAllPollsForAgent");
		System.out.println("32. updateNumericTypeOfPd");
		System.out.println("99. stop : to stop testing");
		System.out.println("\n--------------------------------\n");
                System.out.print("Enter the method number -->> ");

                try{
			choice = buf.readLine();
                	if(choice.equalsIgnoreCase("1")) 
                		{
        	      		Addpoll(pollapi);
                		}
                	else if(choice.equalsIgnoreCase("2")) 
                		{
	               		Addthresholdobject(pollapi);	
                		}
			else if(choice.equalsIgnoreCase("3")) 
                		{
                		Deletepoll(pollapi);
                		}
			else if(choice.equalsIgnoreCase("4")) 
                		{
                		Deletethresholdobject(pollapi);
                		}
			else if(choice.equalsIgnoreCase("5")) 
                		{
                		Getactivepollers(pollapi);
                		}
			else if(choice.equalsIgnoreCase("6")) 
                		{
                		Getallthresholdobjects(pollapi);
                		}
			else if(choice.equalsIgnoreCase("7")) 
                		{
                		Getcollecteddata(pollapi);
	               		}
			else if(choice.equalsIgnoreCase("8")) 
                		{
                		Getcollectedvalues(pollapi);
                		}
			else if(choice.equalsIgnoreCase("9")) 
                		{
                		Getinstances(pollapi);
                		}
			else if(choice.equalsIgnoreCase("10")) 
                		{
                		Getlist(pollapi);
                		}
			else if(choice.equalsIgnoreCase("11")) 
                		{
                		Getnumpollobjects(pollapi);
                		}
			else if(choice.equalsIgnoreCase("12")) 
                		{
                		Getobjectnameswithprops(pollapi);
                		}
			else if(choice.equalsIgnoreCase("13")) 
                		{
                		Getobjects(pollapi);
                		}
			else if(choice.equalsIgnoreCase("14")) 
                		{
                		Getpolleddata(pollapi);
                		}
			else if(choice.equalsIgnoreCase("15")) 
                		{
				Getpolleddatafornode(pollapi);
                		}
			else if(choice.equalsIgnoreCase("16")) 
                		{
                		Getpolleddatafornodes(pollapi);
                		}
			else if(choice.equalsIgnoreCase("17")) 
                		{
                		Getpollsforagent(pollapi);
                		}
			else if(choice.equalsIgnoreCase("18")) 
                		{
                		Getpropertiesofobject(pollapi);
                		}
			else if(choice.equalsIgnoreCase("19")) 
                		{
                		Gettablenames(pollapi);
                		}
			else if(choice.equalsIgnoreCase("20")) 
                		{
                		Modifypoll(pollapi);
                		}
			else if(choice.equalsIgnoreCase("21")) 
                		{
                		Modifythresholdobject(pollapi);
                		}
			else if(choice.equalsIgnoreCase("22")) 
                		{
                		Removepoll(pollapi);
                		}
			else if(choice.equalsIgnoreCase("23")) 
                		{
                		Resumeallpollsforagent(pollapi);
                		}
			else if(choice.equalsIgnoreCase("24")) 
                		{
            			Removepolleddatafornode(pollapi);
                		}
			else if(choice.equalsIgnoreCase("25")) 
                		{
                		Setdebugmode(pollapi);  			        				}
			else if(choice.equalsIgnoreCase("26")) 
                		{
               		 	Setpolleddatafornode(pollapi);  			        
                		}
			else if(choice.equalsIgnoreCase("27")) 
                		{
                		Setpolleddatafornodes(pollapi);
                		}
			else if(choice.equalsIgnoreCase("28")) 
                		{
                		Startdatapoll(pollapi);
                		}
			else if(choice.equalsIgnoreCase("29")) 
                		{
                		Stopall(pollapi);
                		}
			else if(choice.equalsIgnoreCase("30")) 
                		{
                		Stopdatapoll(pollapi);
                		}
			else if(choice.equalsIgnoreCase("31")) 
                		{
                		Suspendallpollsforagent(pollapi);
                		}
			else if(choice.equalsIgnoreCase("32")) 
                		{
                		Updatenumerictypeofpd(pollapi);
                		}
			else if(choice.equalsIgnoreCase("99")) 
                		{
				System.out.println("\n--------------------------------\n");
				System.out.println("\n--------------------------------\n");
	           		System.out.println("Thank you for using this program");
				System.out.println("\n--------------------------------\n");
				System.out.println("\n--------------------------------\n");
				break;
                		}
                	else 
                		{
                		    System.out.println("wrong choice");
    	           		}
			}	
		catch(Exception e)
			{
			System.out.println("Exception in getting input");
			}			
	       
		System.out.println("\n__________________________________________________\n");
		System.out.println("\n__________________________________________________\n");
		System.out.println("\n Press any key to continue...\n");
		try{
		String s=buf.readLine();
		}catch(Exception e){
		}
		System.out.println("Bye");
		
   		 }
		}







/****************   The METHODS CONTINUE IN THE ORDER OF THEIR CHOICE NUMBER   ****************/




	    /* ----------------------------------------------------------------- */
            /*************************	1.	AddPoll	**************************/
            /* ----------------------------------------------------------------- */
	
	
private static void Addpoll(PollAPI pollapi)
  	{
	try
            {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String param = null;
		PolledData pd = new PolledData();	
        	System.out.println("\n You have selected addPoll Method \n"); 
		System.out.println("Enter the polleddata name:: ");
       		param=br.readLine();
		pd.setName(param); 
		System.out.println("Enter the Agent Name:: ");
		param=br.readLine();
		pd.setAgent(param);
       		System.out.println("Enter the polleddata oid:: ");
       		param=br.readLine();
		pd.setOid(param);
		System.out.println("Enter the polleddata owner name:: ");	
		param=br.readLine();
		pd.setOwnerName(param);
		pollapi.addPoll(pd); 
                System.out.println("PolledData added...");
            }
            catch(Exception e)
            {
                System.out.println("exception while adding PD "+e);
                e.getMessage();
                e.printStackTrace();
            }
	}




            /* ----------------------------------------------------------------- */
            /*****************	2.	Addthresholdobject	******************/
            /* ----------------------------------------------------------------- */


public  static void Addthresholdobject(PollAPI pollapi)
    {
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the Threshold name");
            String s = bufferedreader.readLine();
            ThresholdObject thresholdobject = new ThresholdObject(s);
            System.out.println("Enter the Category");
            String s1 = bufferedreader.readLine();
            thresholdobject.setCategory(s1);
            System.out.println("Enter the Threshold kind(long/string/percentage)");
            String s2 = bufferedreader.readLine();
            thresholdobject.setKind(s2);
            System.out.println("Enter the Threshold type(max/min/equal)");
            String s3 = bufferedreader.readLine();
            thresholdobject.setThresholdType(s3);
            System.out.println("Enter the Threshold value");
            long l = Long.parseLong(bufferedreader.readLine());
            System.out.println("the long value entered is " + l);
            thresholdobject.setThresholdValue(l);
            System.out.println("Enter the rearm value(Lesserthan threshold value)");
            long l1 = Long.parseLong(bufferedreader.readLine());
            System.out.println("The long rearm is " + l1);
            thresholdobject.setRearmValue(l1);
            System.out.println("Enter the send clear (optional)");
            Boolean boolean1 = new Boolean(bufferedreader.readLine());
            boolean flag = boolean1.booleanValue();
            System.out.println("the send clear entered is " + flag);
            thresholdobject.setSendClearStatus(flag);
            System.out.println("Enter the severity(0/1/2/3/4/5/6)");
            int i = Integer.parseInt(bufferedreader.readLine());
            thresholdobject.setSeverity(i);
            System.out.println("Enter the allowed values(optional)");
            String s4 = bufferedreader.readLine();
            thresholdobject.setAllowedValues(s4);
            System.out.println("Enter the disallowed values(optional)");
            String s5 = bufferedreader.readLine();
            thresholdobject.setDisAllowedValues(s5);
            boolean flag1 = pollapi.addThresholdObject(thresholdobject);
            System.out.println("The threshold has been added... " + flag1);
            System.out.println("GET-ALL-THRESHOLD-OBJECTS");
            Vector vector = pollapi.getAllThresholdObjects();
            ThresholdObject thresholdobject1;
            for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements(); System.out.println("The thresholdname is " + thresholdobject1.getName()))
                thresholdobject1 = (ThresholdObject)enumeration.nextElement();

        }
        catch(Exception exception)
        {
            System.out.println("Exception caught in addThresholdObject() " + exception);
            exception.printStackTrace();
        }
    }


	    /* ----------------------------------------------------------------- */
            /********************	3.	 deletePoll	 *****************/
            /* ----------------------------------------------------------------- */


private static void Deletepoll(PollAPI PoAPI)
  	{
        try 
        	{
         	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	String agent1 = null;
        	System.out.println("\n You have selected Delete Poll Method \n");        
          	System.out.print("Enter the agentname for which the polleddata has tobe deleted  :: ");
		agent1 = br.readLine();
		PoAPI.deletePoll(agent1);
		System.out.println("\n--------------------------------\n");
		System.out.println("\n--------------------------------\n");
		System.out.println("PolledData deleted successfully...");
        	System.out.println("\n--------------------------------\n");
		System.out.println("\n--------------------------------\n");
		} 
	catch(Exception e)
  	        {
  	        System.out.println("exception while deleting PD "+e);
  	        e.getMessage();
  	        e.printStackTrace();
  	        }
       }
 	    


 	    /* ----------------------------------------------------------------- */
            /*****************	4.	 Deletethresholdobject	******************/
            /* ----------------------------------------------------------------- */

public  static void Deletethresholdobject(PollAPI pollapi)
    {
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
            Vector vector = pollapi.getAllThresholdObjects();
            System.out.println("The number of threshold objects available are " + vector.size());
            for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements();)
	            {
	            ThresholdObject thresholdobject = (ThresholdObject)enumeration.nextElement();
		    System.out.println(""+thresholdobject.getName());
		    }
	    System.out.println("Enter the ThresholdName that has to be deleted ");  		   	    String s = bufferedreader.readLine();
	    for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements();)
	        {
	        ThresholdObject thresholdobject = (ThresholdObject)enumeration.nextElement();
 		if(thresholdobject.getName().equals(s))
                {
                    boolean flag = pollapi.deleteThresholdObject(s);
                    System.out.println("The threshold has been deleted " + flag);
                }
            }

            Vector vector1 = pollapi.getAllThresholdObjects();
            System.out.println("The number of threshold objects available after removal are " + vector1.size());
        }
        catch(Exception exception)
        {
            System.out.println("Exception in method deleteThresholdObject() " + exception);
            exception.printStackTrace();
        }
    }



	    /* ----------------------------------------------------------------- */
            /*****************	5.	Getactivepollers	******************/
            /* ----------------------------------------------------------------- */

public  static void Getactivepollers(PollAPI pollapi)
    {
    try
        {
        System.out.println("GET-ACTIVE-POLLERS");
        Vector vector = pollapi.getActivePollers();
//System.out.println(vector.toString());
	String [] name = (String [])vector.elementAt(0);
	String [] hostName = (String [])vector.elementAt(1);
	if(name!=null && hostName!=null && name.length>0)
	{
		System.out.println(name.length);
		for(int i=0;i<name.length;i++)
		{
			System.out.println(name[i] + " --- " + hostName[i]);
		}
	}
	else
	{
		System.out.println("No Activepollers");	
	}

        }
        catch(Exception exception)
        {
            System.out.println("Caught exception " + exception);
        }
    }


	    /* ----------------------------------------------------------------- */
            /*****************	6.	Getallthresholdobjects	******************/
            /* ----------------------------------------------------------------- */

public  static void Getallthresholdobjects(PollAPI pollapi)
    {
        try
        {
            System.out.println("GET-ALL-THRESHOLD-OBJECTS");
            Vector vector = pollapi.getAllThresholdObjects();
            ThresholdObject thresholdobject;
            for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements(); System.out.println("The key is " + thresholdobject.getName()))
                thresholdobject = (ThresholdObject)enumeration.nextElement();

        }
        catch(Exception exception)
        {
            System.out.println("Exception caught is " + exception);
        }
    }




	    /* ----------------------------------------------------------------- */
            /******************	7.	Getcollecteddata	******************/
            /* ----------------------------------------------------------------- */


public static void Getcollecteddata(PollAPI pollapi)
    {
        try
        {
            Object obj = null;
            String s2 = null;
            Object obj1 = null;
            Object obj2 = null;
	    BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Enter the start time details:");
	    System.out.println("Enter the year:");
            int inpyr=Integer.parseInt(bufferedreader.readLine());
	    inpyr=inpyr-1900;
	    System.out.println("Enter the month:");
            int inpmt=Integer.parseInt(bufferedreader.readLine());
    	    inpmt=inpmt-1;
	    System.out.println("Enter the date:");
            int inpdt=Integer.parseInt(bufferedreader.readLine());
	    System.out.println("Enter the time ");
	    System.out.println("Enter the hour :");
	    int inphr=Integer.parseInt(bufferedreader.readLine());
	    System.out.println("Enter the minute :");
	    int inpmi=Integer.parseInt(bufferedreader.readLine());
   	    System.out.println("Enter the seconds :");
	    int inpsc=Integer.parseInt(bufferedreader.readLine());
	    java.util.Date date = new java.util.Date(inpyr,inpmt,inpdt,inphr,inpmi,inpsc);
	    long l = date.getTime();	    
	    System.out.println(""+l);            
	    System.out.println("Enter the End time details:");
	    System.out.println("Enter the year:");
            inpyr=Integer.parseInt(bufferedreader.readLine());
	    inpyr=inpyr-1900;
	    System.out.println("Enter the month:");
            inpmt=Integer.parseInt(bufferedreader.readLine());
    	    inpmt=inpmt-1;
	    System.out.println("Enter the date:");
            inpdt=Integer.parseInt(bufferedreader.readLine());
	    inpdt=inpdt;
	    System.out.println("Enter the End Time : ");
	    System.out.println("Enter the hour :");
	    inphr=Integer.parseInt(bufferedreader.readLine());
	    System.out.println("Enter the minute :");
	    inpmi=Integer.parseInt(bufferedreader.readLine());
   	    System.out.println("Enter the seconds :");
	    inpsc=Integer.parseInt(bufferedreader.readLine());
	    java.util.Date date1= new java.util.Date(inpyr,inpmt,inpdt,inphr,inpmi,inpsc);
	    long l1 = date1.getTime();
	    System.out.println(""+l1);
            System.out.println("timestart :" + (new java.util.Date(l)));
            System.out.println("timeend :" + new java.util.Date(l1));
            CollectedData collecteddata = null;
            System.out.println("Enter the number of arguments to be passed (2/3/4):");
            int i1 = Integer.parseInt(bufferedreader.readLine());
	    System.out.println("Enter the (3) details for one argument in any method");
            System.out.println("1. Enter the polled Data name :");
            String s5 = bufferedreader.readLine();
            System.out.println("2. Enter agent name:");
            String s6 = bufferedreader.readLine();
            System.out.println("3. Enter oid string:");
            String s7 = bufferedreader.readLine();
            s2 = s5 + "\t" + s6 + "\t" + s7;
            switch(i1)
            {
            case 2: // '\002'

		System.out.println("You have selected two arguments for getcollecteddata");
		System.out.println("The first argument is the Polleddata key, which you have entered as "+s2);
                System.out.println("The second argument can be string(deprecated)/long(deprecated)/date ");
                System.out.println("Enter your choice of argument.Enter S or D or L");
                String s3 = bufferedreader.readLine();
                if(s3.equalsIgnoreCase("s"))
                {
		System.out.println("Enter the String(It may be 'current'/(mm/m)-dd-yyyy) :");
                    String s8 = bufferedreader.readLine();	
                    System.out.println("Date :" + s8);
                    Vector vector = pollapi.getCollectedData(s2, s8);
                    for(Enumeration enumeration2 = vector.elements(); enumeration2.hasMoreElements();)
                    {
                        long al[] = (long[])enumeration2.nextElement();
                        long al3[] = (long[])enumeration2.nextElement();
                        for(int k2 = 0; k2 < al.length; k2++)
                            System.out.println("The ttime is :" + al[k2] + " and the corresponding value for " + s2 + " is " + al3[k2]);

                    }

                    System.out.println(vector.size());
                    break;
                }
                if(s3.equalsIgnoreCase("d"))
                {
                    System.out.println("Date :" + date);
                    collecteddata = pollapi.getCollectedData(s2, date);
                    Long along[] = collecteddata.getTimes();
                    Object aobj[] = collecteddata.getValues();
                    int i = collecteddata.getType();
                    for(int j1 = 0; j1 < along.length; j1++)
                        System.out.println("The ttime is :" + along[j1] + " for " + s2 + " with " + i + " has value " + aobj[j1]);

                    break;
                }
                if(s3.equalsIgnoreCase("l"))
                {
                    System.out.println("The lasttimevalue after which the datas are going to be displayed is " + l);
                    Vector vector1 = pollapi.getCollectedData(s2, l);
                    System.out.println("GET_COLLECTED_DATA(string,long)");
                    for(Enumeration enumeration = vector1.elements(); enumeration.hasMoreElements();)
                    {
                        long al1[] = (long[])enumeration.nextElement();
                        long al4[] = (long[])enumeration.nextElement();
                        for(int i2 = 0; i2 < al1.length; i2++)
                            System.out.println("The " + s2 + "has time " + al1[i2] + " and value " + al4[i2]);

                    }

                } else
                {
                    System.out.println("Enter the correct option");
                }
                break;

            case 3: // '\003'

		System.out.println("You have selected three arguments for getcollecteddata");
		System.out.println("The first agrument may be string/instance");
                System.out.println("Enter the option u want to check(Enter S or I)");
                String s4 = bufferedreader.readLine();
                if(s4.equalsIgnoreCase("s"))
                {
                    System.out.println("GET-COLLECTED-DATA(STRING key,LONG timestart,LONG timeend)");
		    System.out.println("The First argument :"+s2);
                    System.out.println("The second argument :"+l);
 		    System.out.println("the third argument:"+l1);
		    Vector vector2 = pollapi.getCollectedData(s2, l, l1);
                    System.out.println("The collected data is " + vector2);
                    for(Enumeration enumeration1 = vector2.elements(); enumeration1.hasMoreElements();)
                    {
                        long al2[] = (long[])enumeration1.nextElement();
                        long al5[] = (long[])enumeration1.nextElement();
                        for(int j2 = 0; j2 < al2.length; j2++)
                            System.out.println("The polled data " + s2 + " has time " + al2[j2] + " and value " + al5[j2]);

                    }

                    break;
                }
                if(s4.equalsIgnoreCase("i"))
                {
                    PolledData polleddata = pollapi.getPolledData(s2);
                    boolean flag = polleddata.getIsMultiplePolledData();
                    System.out.println("The Pd is multiple " + flag);
                    String s = polleddata.getInstance();
                    System.out.println("The instance is " + s);  
		    System.out.println("The First argument :"+s2);
                    System.out.println("The second argument :"+l);
 		    System.out.println("the third argument:"+l1);
                    System.out.println("The third arument may be date/long");
                    System.out.println("Enter your option(D/L)");
                    String s9 = bufferedreader.readLine();
                    if(s9.equalsIgnoreCase("d"))
                    {
                        System.out.println("GET-COLLECTED-DATA(STRING instance,STRING key,DATE date)");
                        collecteddata = pollapi.getCollectedData(s, s2, date);
                    } else
                    if(s9.equalsIgnoreCase("l"))
                    {
                        System.out.println("GET-COLLECTED-DATA(STRING instance,STRING key,LONG timestart");
                        collecteddata = pollapi.getCollectedData(s, s2, l);
                    } else
                    {
                        System.out.println("The option entered is wrong");
                    }
                    Long along1[] = collecteddata.getTimes();
                    Object aobj1[] = collecteddata.getValues();
                    int j = collecteddata.getType();
                    for(int l2 = 0; l2 < along1.length; l2++)
                        System.out.println("The time and values are " + along1[l2] + "**" + aobj1[l2] + "of type" + j);

                } else
                {
                    System.out.println("Please enter the correct option ");
                }
                break;

            case 4: // '\004'
		System.out.println("You have selected four arguments for getcollecteddata");
		System.out.println("The first argument is the Polleddata key, which you have entered as "+s2);
		PolledData polleddata1 = pollapi.getPolledData(s2);
		//System.out.println("Enter the Instance string whose value is needed :");
            	//String s1 = bufferedreader.readLine();
		//	String s1 = polleddata1.getInstance();
                System.out.println("The instance string whose value is needed (First argument) is " + s7);
  		System.out.println("The Second argument is " + s2);
		System.out.println("The Third argument is " + l);
		System.out.println("the Fourth argument is "+l1);
                CollectedData collecteddata1 = pollapi.getCollectedData(s7, s2, l, l1);
                Long along2[] = collecteddata1.getTimes();
                Object aobj2[] = collecteddata1.getValues();
                int k = collecteddata1.getType();
                System.out.println("INSTANCE,KEY,STARTTIME,ENDTIME");
                for(int k1 = 0; k1 < along2.length; k1++)
                    System.out.println("The time is :" + along2[k1] + " and value is " + aobj2[k1] + " of type " + k);

                break;

            default:
                System.out.println("The number of allowable arguments for getCollectedData() is 2,3 and 4");
                break;
            }
        }
        catch(Exception exception)
        {
            System.out.println("Exception caught in method getCollectedData() " + exception);
            exception.printStackTrace();
        }
    }

	




	    /* ----------------------------------------------------------------- */
            /*****************	8.	getCollectedValues	******************/
            /* ----------------------------------------------------------------- */


public  static void Getcollectedvalues(PollAPI pollapi)
    {
        java.util.Date date = new java.util.Date();
       /* long l = System.currentTimeMillis() - 0x16e360L;
        long l1 = l + 0x927c0L;*/
        try
        {
            CollectedData collecteddata = null;
            PolledData polleddata = null;
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the number of arguments you want to enter (2/3) :");
            int i = Integer.parseInt(bufferedreader.readLine());
	    System.out.println("Enter the (3) details for one argument in any method");
            System.out.println("1. Enter the polled Data name :");
            String s1 = bufferedreader.readLine();
            System.out.println("2. Enter agent name:");
            String s2 = bufferedreader.readLine();
            System.out.println("3. Enter oid string:");
            String s3 = bufferedreader.readLine();
            String s = s1 + "\t" + s2 + "\t" + s3;            
	    System.out.println(""+s);
            polleddata = pollapi.getPolledData(s);
            System.out.println("Enter the start time details:");
	    System.out.println("Enter the year:");
            int inpyr=Integer.parseInt(bufferedreader.readLine());
	    inpyr=inpyr-1900;
	    System.out.println("Enter the month:");
            int inpmt=Integer.parseInt(bufferedreader.readLine());
    	    inpmt=inpmt-1;
	    System.out.println("Enter the date:");
            int inpdt=Integer.parseInt(bufferedreader.readLine());
	    System.out.println("Enter the time ");
	    System.out.println("Enter the hour :");
	    int inphr=Integer.parseInt(bufferedreader.readLine());
	    System.out.println("Enter the minute :");
	    int inpmi=Integer.parseInt(bufferedreader.readLine());
   	    System.out.println("Enter the seconds :");
	    int inpsc=Integer.parseInt(bufferedreader.readLine());
	    java.util.Date date1 = new java.util.Date(inpyr,inpmt,inpdt,inphr,inpmi,inpsc);
	    long l = date1.getTime();	    
	    System.out.println(""+l);            
	switch(i)
            {
            case 2: // '\002'
	    collecteddata = pollapi.getCollectedValues(s, l);
            break;

            case 3: // '\003'
		    System.out.println("Enter the End time details:");
		    System.out.println("Enter the year:");
        	    int inpyr1=Integer.parseInt(bufferedreader.readLine());
		    inpyr1=inpyr1-1900;
		    System.out.println("Enter the month:");
        	    int inpmt1=Integer.parseInt(bufferedreader.readLine());
    		    inpmt1=inpmt1-1;
		    System.out.println("Enter the date:");
        	    int inpdt1=Integer.parseInt(bufferedreader.readLine());
		    System.out.println("Enter the time ");
		    System.out.println("Enter the hour :");
		    int inphr1=Integer.parseInt(bufferedreader.readLine());
		    System.out.println("Enter the minute :");
		    int inpmi1=Integer.parseInt(bufferedreader.readLine());
   		    System.out.println("Enter the seconds :");
		    int inpsc1=Integer.parseInt(bufferedreader.readLine());
		    java.util.Date date2 = new java.util.Date(inpyr1,inpmt1,inpdt1,inphr1,inpmi1,inpsc1);
		    long l1 = date2.getTime();	    
		    System.out.println(""+l1);            
		    collecteddata = pollapi.getCollectedValues(s, l, l1);
        	    break;
            default:
                System.out.println("The option selected is wrong.Select 2 or 3");
                break;
            }
            Long along[] = collecteddata.getTimes();
            Object aobj[] = collecteddata.getValues();
            int j = collecteddata.getType();
            for(int k = 0; k < along.length; k++)
                System.out.println("The time is " + along[k] + " and value is " + aobj[k] + " of type " + j);

        }
        catch(Exception exception)
        {
            System.out.println("Exception caught :" + exception);
        }
    }




	    /* ----------------------------------------------------------------- */
            /*******************	9.	 getInstances ********************/
            /* ----------------------------------------------------------------- */
private static void Getinstances(PollAPI pollapi)
  	{               
            try
            {
               	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String key = null;
        	System.out.println("\n You have selected getInstances Method \n");        
		System.out.println("Enter the polleddata name, press <tab> key , agentname , press <tab> key , enter Multiple object oid and press <enter> key:: ");
                key=br.readLine();
                PolledData pd = pollapi.getPolledData(key);
                MultiplePolledData mpd = (MultiplePolledData)pd;
                System.out.println("PD ID:: " + pd.getId());
                Vector v = pollapi.getInstances(mpd);
                if(v != null)
                {
                    for(int i=0;i<v.size();i++)
                        System.out.println(v.get(i));
                }
                else
                {
                    System.out.println("No instances returned");
                }
            }
            catch(Exception e)
            {
                System.out.println("exception while getting instances for PD "+e);
                e.getMessage();
                e.printStackTrace();
            }

	}	



	    /* ----------------------------------------------------------------- */
            /*********************** 10.  getList ********************************/
            /* ----------------------------------------------------------------- */

	private static void Getlist(PollAPI pollapi)
  	{
        	try 
        	{
	        Vector vec= pollapi.getCompleteList();
                if(vec!=null && vec.size()>0)
                {
		    System.out.println(vec.size());
		
                    for(int i=0;i<vec.size();i++)
                    {
		        System.out.println((String)vec.get(i));
                    }
                        
                }
                else
                {
		    System.out.println("\nNo elements returned\n");
                    }
            }
            catch(Exception e)
            {
                System.out.println("exception while getting complete list of PD "+e);
                e.getMessage();
                e.printStackTrace();
            }
}





	    /* ----------------------------------------------------------------- */
            /****************	11.	 getNumPollObjects ***********************/
            /* ----------------------------------------------------------------- */

	private static void Getnumpollobjects(PollAPI PoAPI)
  	{
        	try 
        	{
 			int count = PoAPI.getNumPollObjects();
                	System.out.println("\n--------------------------------\n");
			System.out.println("\n--------------------------------\n");
			System.out.println("Poll Count :: " + count);
			System.out.println("\n--------------------------------\n");
			System.out.println("\n--------------------------------\n");
            	} 
            	catch(Exception e)
            	{
              		 System.out.println("exception while getting count of poll objects "+e);
               		 e.getMessage();
               		 e.printStackTrace();
         	  }
	}
	
	








	   /* ------------------------------------------------------------------- */
            /****************	12.	 getObjectNamesWithProps *****************/
            /* ----------------------------------------------------------------- */

            // --1--
  private static void Getobjectnameswithprops(PollAPI pollapi)
  	{ try 
        	{
            		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	                Properties matchProp = new Properties();
			System.out.println("\n You have selected getObjectNamesWithProps Method \n");        
			while(true)
				{
				System.out.print("Enter the property name(Can select any property under DataCollection Detailed properties)  :: ");
			      	String  prop = br.readLine();
       				System.out.print("Enter the Value of the property name tobe matched with  :: ");
				String val= br.readLine();  
				matchProp.put(prop,val);
				System.out.println("Want to continue with more matching criteria? :(Y/N)::");
				String choice=br.readLine();			
				if (choice.equalsIgnoreCase("N"))
					{
						break;
					}
				}

			
          		Vector v = pollapi.getObjectNamesWithProps(matchProp);
          	      if(v!=null)
          	      {
          		          System.out.println("PolledData vector size:: "+v.size());
          		          for(int i=0;i<v.size();i++)
          		          {
          		              String key = (String)v.get(i);
          		              System.out.println("key"+i+" :: "+key);
          		          }
          	      }
          	      else
          	      {
          		          System.out.println("Return vector is empty");
          	      }
          	  } 
            catch(Exception e)
            {
                System.out.println("exception while getting PolledData Names "+e);
                e.getMessage();
                e.printStackTrace();
            }
            
} 



        
	   


	    /* ----------------------------------------------------------------- */
            /***************	13.	 getObjects 	**************************/
            /* ----------------------------------------------------------------- */
	

private static void Getobjects(PollAPI pollapi)
  	{ 
	try
            {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	                String prop1 = null;
			String val1= null;
			String classname = null;
			Properties matchProp = new Properties();
			System.out.println("\n You have selected getObjects Method \n");        			System.out.println("\n Enter the classname (PolledData/MultiplePolledData)\n");	
			classname=br.readLine();			
			while(true)
			{
				System.out.print("Enter the property name :: ");
			      	String  prop = br.readLine();
       				System.out.print("Enter the Value of the property name tobe matched with  :: ");
				String val= br.readLine();  
				matchProp.put(prop,val);
				System.out.println("Want to continue with more matching criteria? :(Y/N)::");
				String choice=br.readLine();			
				if (choice.equalsIgnoreCase("N"))
					{
						break;
					}
			}
          		Vector v = pollapi.getObjects(classname, matchProp);
               		 if(v!=null)
                		{
                   		 System.out.println("PolledData vector size:: "+v.size());
                   	 	for(int i=0;i<v.size();i++)
                    			{
                        			PolledData pd = (PolledData)v.get(i);
                        			System.out.println("key"+i+" :: "+pd.getKey());
                    			}
                		}
                		else
                		{
                    		System.out.println("Return vector is empty");
                		}
           		} 
            catch(Exception e)
            {
                System.out.println("exception while getting PolledData objects "+e);
                e.getMessage();
                e.printStackTrace();
            }
	}


   	    /* ----------------------------------------------------------------- */
            /********************* 14.	 getPolledData	**************************/
            /* ----------------------------------------------------------------- */

  private static void Getpolleddata(PollAPI pollapi)
  	{             
            try
            {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String key1= null;
        	System.out.println("\n You have selected getPolledData Method \n");        
		System.out.println("Enter the polleddata name, press <tab> key , agentname , press <tab> key , enter object oid and press <enter> key:: ");
                key1=br.readLine();
                PolledData pd1 = pollapi.getPolledData(key1);
                if(pd1 != null)
                {
                    System.out.println("PD retrieved :: " + key1);
                    System.out.println("NAME :: " + pd1.getName());
                }
                else
                {
                    System.out.println("PD is null :: " + key1);
                }
            }
            catch(Exception e)
            {
                System.out.println("exception while getting PD "+e);
                e.getMessage();
                e.printStackTrace();
            }
	}






	    /* ----------------------------------------------------------------- */
            /*****************	15.	Getpolleddatafornode	******************/
            /* ----------------------------------------------------------------- */

 public  static void Getpolleddatafornode(PollAPI pollapi)
    {
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the ipaddress :");
            String s = bufferedreader.readLine();
		int cnt=0;
            Vector vector = pollapi.getPolledDataForNode(s);
            for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements();)
		{
		 System.out.println("The polled data for nodes are " + ((PolledData)enumeration.nextElement()).getKey());
		cnt++;
		
        }
	System.out.println("No of elements:"+cnt);
	if (cnt==0)
		{
		System.out.println("Sorry !  The ipaddress you have entered doesnot match with the configured nodes");
		}
}
        catch(Exception exception)
        {
            System.out.println("Exception in getPolledDataForNode() " + exception);
        }
    }

	    /* ----------------------------------------------------------------- */
            /****************	16.	getPolleddatafornodes	******************/
            /* ----------------------------------------------------------------- */

    public static void Getpolleddatafornodes(PollAPI pollapi)
    {
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the no of arguments to be entered (1/3)	");
            int i = Integer.parseInt(bufferedreader.readLine());
            Vector vector = new Vector();
            Vector vector1 = null;
            switch(i)
            {
            case 1: // '\001'
                boolean flag = true;
                while(flag) 
                {
                    System.out.println("Enter the ipaddress ");
                    vector.addElement(bufferedreader.readLine());
                    System.out.println("Do u want to enter more ipaddress(Y/N)");
                    String s = bufferedreader.readLine();
                    if(s.equalsIgnoreCase("y"))
                        flag = true;
                    else
                    if(s.equalsIgnoreCase("n"))
                    {
                        flag = false;
                        String as[] = new String[vector.size()];
                        for(int j = 0; j < vector.size(); j++)
                            as[j] = (String)vector.elementAt(j);

                        vector1 = pollapi.getPolledDataForNodes(as);
                    }
                }
                break;

            case 3: // '\003'
                System.out.println("Enter the starting ipaddress");
                String s1 = bufferedreader.readLine();
                System.out.println("Enter the ending ipaddress");
                String s2 = bufferedreader.readLine();
                System.out.println("Enter the netmask");
                String s3 = bufferedreader.readLine();
                vector1 = pollapi.getPolledDataForNodes(s1, s2, s3);
                break;

            case 2: // '\002'
            default:
                System.out.println("The option entered is wrong");
                break;
            }
            System.out.println("GET-POLLED-DATA-FOR-NODES");
            PolledData polleddata;
            for(Enumeration enumeration = vector1.elements(); enumeration.hasMoreElements(); 	    System.out.println("The DNS name of the polled data is " + polleddata.getDnsName()))
            {
                polleddata = (PolledData)enumeration.nextElement();
                System.out.println("The key of the polled data is " + polleddata.getKey());
                System.out.println("The name of the polled data is " + polleddata.getName());
            }

        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            System.out.println("Exception in getPolledDataForNodes() " + exception);
        }
    }
   
	    /* ----------------------------------------------------------------- */
            /****************	17.	getPollsForAgent	 *****************/
            /* ----------------------------------------------------------------- */

     	private static void Getpollsforagent(PollAPI pollapi)
  	{    
            try
            {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String agent1 = null;
        	System.out.println("\n You have selected getPollsForAgent Method \n");        
          	System.out.print("Enter the agentname for which the polleddata tobe got  :: ");
		agent1 = br.readLine();
		Vector pollData = pollapi.getPollsForAgent(agent1);            
                System.out.println("Got polled data: " + pollData.size() + "\n");
                for (int i=0; i<pollData.size(); i++) 
                {
                    String key = (String)pollData.elementAt(i);
                    System.out.println("key: " + key);
                }                
            } 
            catch(Exception e)
            {
                System.out.println("exception while getting polls for agent "+e);
                e.getMessage();
                e.printStackTrace();
            }
        } 
	   
	    /* ----------------------------------------------------------------- */
            /*************	18.	 getPropertiesOfObject 	******************/
            /* ----------------------------------------------------------------- */

     
       	private static void Getpropertiesofobject(PollAPI pollapi)
	{
	try
            {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String key = null;
        	System.out.println("\n You have selected Get properties of the object Method \n");        
          	System.out.println("Enter the polleddata name, press <tab> key , agentname , press <tab> key , enter object oid  and press <Enter> key :: ");
   		System.out.println(" --- or ---");
		System.out.print("Enter the polleddata name, press <tab> key , agentname , press <tab> key , enter object oid ,press <tab> key , enter owner name and press <enter> key:: ");
		key=br.readLine();
		System.out.println(""+key);
		System.out.println("Enter the number of properties to be viewed :");
		int num = Integer.parseInt(br.readLine());
		String pro2[]=new String[100];
		
		for (int i=0;i<num;i++)
				{
			System.out.println("Enter the property to be viewed for the entered polledData :");
//			pro2[i]=null;
			pro2[i]=br.readLine();
                		}	

Properties prop = pollapi.getPropertiesOfObject(key);
                if(prop!=null)
                {
                 System.out.println("PolledData Properties retrieved");
                /*    System.out.println("name :: " + prop.getProperty("name"));
                    System.out.println("agent :: " + prop.getProperty("agent"));
                    System.out.println("oid :: " + prop.getProperty("oid"));
                    System.out.println("ownername :: " + prop.getProperty("ownerName"));
                    System.out.println("period :: " + prop.getProperty("period"));
                    System.out.println("isMultiplePolledData :: " + prop.getProperty("isMultiplePolledData"));*/
		    
	for (int i=0;i<num;i++){
			System.out.println(""+pro2[i]+":: "+prop.getProperty(""+pro2[i]));
                		}	

		
		
                }
                else
                {
                    System.out.println("Properties are empty");
                }

            } 


            catch(Exception e)
            {
                System.out.println("exception while igetting PolledData object Properties "+e);
                e.getMessage();
                e.printStackTrace();
            }
           
	}

	

	    /* ----------------------------------------------------------------- */
            /****************	19.	Gettablenames	**************************/
            /* ----------------------------------------------------------------- */

public  static void Gettablenames(PollAPI pollapi)
    {
        try
        {
            System.out.println("GET-TABLE-NAMES");
            Vector vector = pollapi.getTableNames();
            for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements(); System.out.println("The polled datas are stored in the tables " + enumeration.nextElement()));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            System.out.println("Exception in getTableNames() " + exception);
        }
    }

	


	    /* ----------------------------------------------------------------- */
            /****************	20.	Modifypoll	**************************/
            /* ----------------------------------------------------------------- */



public static void Modifypoll(PollAPI pollapi)
    {
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the poll name (Cannot Change this field ! - Give it unchanged ):");
            String s = bufferedreader.readLine();
            System.out.println("Enter the poll agent(Cannot Change this field ! - Give it unchanged ):");
            String s1 = bufferedreader.readLine();
            System.out.println("Enter the poll oid (Cannot Change this field ! - Give it unchanged ):");
            String s2 = bufferedreader.readLine();
            PolledData polleddata = new PolledData();
            Properties properties = new Properties();
            properties.put("name", s);
            properties.put("agent", s1);
            properties.put("oid", s2);
            polleddata.setProperties(properties);
            System.out.println("Enter the period (Give other value than the existing one for the given polled Data) ");
            int i = Integer.parseInt(bufferedreader.readLine());
            polleddata.setPeriod(i);           
	    polleddata.setUserProperty("test","true");
	    System.currentTimeMillis();
            System.out.println("Enter the active state(true/false)(Give other value than the existing one for the given polled Data)");
            Boolean boolean1 = new Boolean(bufferedreader.readLine());
            boolean flag = boolean1.booleanValue();
            System.out.println("The active state entered by the user " + flag);
            polleddata.setActive(flag);
            System.out.println("active " + polleddata.getActive());
            pollapi.modifyPoll(polleddata);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            System.out.println("Exception caught " + exception);
        }
    }


	    /* ----------------------------------------------------------------- */
            /****************	21.	Modifythresholdobject	******************/
            /* ----------------------------------------------------------------- */

 public static void Modifythresholdobject(PollAPI pollapi)
    {
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
            Vector vector = pollapi.getAllThresholdObjects();
            System.out.println("The number of threshold objects available are " + vector.size());
            for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements();)
	            {
	            ThresholdObject thresholdobject = (ThresholdObject)enumeration.nextElement();
		    System.out.println(""+thresholdobject.getName());
		    }
	    System.out.println("Enter the ThresholdName that has to be Modifyied :");  		   	    String s = bufferedreader.readLine();
	    for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements();)
	        {
	        ThresholdObject thresholdobject = (ThresholdObject)enumeration.nextElement();
                if(thresholdobject.getName().equals(s))
                {
                    //System.out.println("Enter the threshold name");
                    //String s1 = bufferedreader.readLine();
                    thresholdobject.setName(s);
                    System.out.println("Enter the Threshold kind(long/string/percentage)");
                    String s2 = bufferedreader.readLine();
                    thresholdobject.setKind(s2);
                    System.out.println("Enter the Threshold type(max/min/equal)");
                    String s3 = bufferedreader.readLine();
                    thresholdobject.setThresholdType(s3);
                    System.out.println("Enter the Threshold value");
                    long l = Long.parseLong(bufferedreader.readLine());
                    thresholdobject.setThresholdValue(l);
                    System.out.println("Enter the rearm value");
                    long l1 = Long.parseLong(bufferedreader.readLine());
                    thresholdobject.setRearmValue(l1);
                    System.out.println("Enter the allowed values");
                    String s4 = bufferedreader.readLine();
                    thresholdobject.setAllowedValues(s4);
                    System.out.println("Enter the disallowed values");
                    String s5 = bufferedreader.readLine();
                    thresholdobject.setDisAllowedValues(s5);
                    System.out.println("Enter the severity(0/1/2/3/4/5/6)");
                    int i = Integer.parseInt(bufferedreader.readLine());
                    thresholdobject.setSeverity(i);
                    System.out.println("Enter the oid type");
                    String s6 = bufferedreader.readLine();
                    thresholdobject.setOidType(s6);
                    System.out.println("Enter the oid");
                    String s7 = bufferedreader.readLine();
                    thresholdobject.setOidValue(s7);
                    boolean flag = pollapi.modifyThresholdObject(thresholdobject);
                    System.out.println(flag);
                }
            }

        }
        catch(Exception exception)
        {
            System.out.println("Exception in modifyThresholdObject() " + exception);
            exception.printStackTrace();
        }
    }
	        




	    /* ----------------------------------------------------------------- */
            /*******************     22. removePoll	       *******************/
            /* ----------------------------------------------------------------- */

private static void Removepoll(PollAPI pollapi)
  	{               
            try
            {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String key = null;
        	System.out.println("\n You have selected removepoll Method \n");        
		System.out.print("Enter the polleddata name, press <tab> key , agentname , press <tab> key , enter object oid ,press <tab> key , enter owner name and press <enter> key:: ");
                key=br.readLine();
		pollapi.removePoll(key);
                System.out.println("PolledData removed...");
            }
            catch(Exception e)
            {
                System.out.println("exception while removing PD "+e);
                e.getMessage();
                e.printStackTrace();
            }
           }

   	   
 	             
            
	    /* ----------------------------------------------------------------- */
            /*****************	23.	resumeAllPollsForAgent 	******************/
            /* ----------------------------------------------------------------- */

private static void Resumeallpollsforagent(PollAPI pollapi)
  	{ try 
        	{
            		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	                String agent1 = null;
        		System.out.println("\n You have selected Resume all the Poll data for an Agent Method \n");        
          		System.out.print("Enter the agentname for which the polleddata has tobe Resumed  :: ");
		        agent1 = br.readLine();
			boolean status = pollapi.resumeAllPollsForAgent(agent1);
	              
			System.out.println("Polling resumed for :: " + agent1 + " :: " + status);
			System.out.println("\n--------------------------------\n");
			System.out.println("\n--------------------------------\n");
		        
		}                  
            
            catch(Exception e)
            {
                System.out.println("exception while resuming polls for agent "+e);
                e.getMessage();
                e.printStackTrace();
            }
	}


            /* ----------------------------------------------------------------- */
            /*****************	24.	Removepolleddatafornode	******************/
            /* ----------------------------------------------------------------- */

  public static void Removepolleddatafornode(PollAPI pollapi)
    {
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the polled Data name ");
            String s = bufferedreader.readLine();
            System.out.println("Enter the agent name");
            String s1 = bufferedreader.readLine();
            System.out.println("Enter the oid");
            String s2 = bufferedreader.readLine();
            String s3 = s + "\t" + s1 + "\t" + s2;
            System.out.println("Enter the ipaddress ");
            String s4 = bufferedreader.readLine();
            boolean flag = pollapi.removePolledDataForNode(s4, s3);
            System.out.println("The pd for the given ipaddress has been removed " + flag);
            /*Vector vector = pollapi.getCompleteList();
            for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements(); System.out.println(enumeration.nextElement()));
            System.out.println("The total number of objects is " + vector.size());*/
        }
        catch(Exception exception)
        {
            System.out.println("Exception in method removePolledDataForNode() " + exception);
            exception.printStackTrace();
        }
    }

 
	    /* ----------------------------------------------------------------- */
            /***************	25.	Setdebugmode	**************************/
            /* ----------------------------------------------------------------- */

public static void Setdebugmode(PollAPI pollapi)
    {
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the debug mode");
            Boolean boolean1 = new Boolean(bufferedreader.readLine());
            boolean flag = boolean1.booleanValue();
            pollapi.setDebugMode(flag);
            System.out.println("The debugging mode has been set to " + flag);
        }
        catch(Exception exception)
        {
            System.out.println("Exception in method setDebugMode() " + exception);
            exception.printStackTrace();
        }
    }



	    /* ----------------------------------------------------------------- */
            /****************	26.	Setpolleddatafornode	******************/
            /* ----------------------------------------------------------------- */


 public static  void Setpolleddatafornode(PollAPI pollapi)
    {
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the Polled Data name ");
            String s = bufferedreader.readLine();
            System.out.println("Enter the agent");
            String s1 = bufferedreader.readLine();
            System.out.println("Enter the oid");
            String s2 = bufferedreader.readLine();
            String s3 = s + "\t" + s1 + "\t" + s2;
            PolledData polleddata = pollapi.getPolledData(s3);
            polleddata.setPeriod(212);
            System.out.println("Enter the ipaddress (Give a new one to changewith) ");
            String s4 = bufferedreader.readLine();
            boolean flag = pollapi.setPolledDataForNode(s4, polleddata);
            System.out.println("The ipaddress has been set for the PolledData " + flag);
          /*  Vector vector = pollapi.getCompleteList();
            for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements(); System.out.println(enumeration.nextElement()));
            System.out.println("The total number of objects is " + vector.size());*/
        }
        catch(Exception exception)
        {
            System.out.println("Exception in method setPolledDataForNode() " + exception);
            exception.printStackTrace();
        }
    }

	  

	    
	    /* ----------------------------------------------------------------- */
            /*************	27.	SetPolledDataForNodes	******************/
            /* ----------------------------------------------------------------- */


public static void Setpolleddatafornodes(PollAPI pollapi)
    {
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the no of arguments to be entered ");
            int i = Integer.parseInt(bufferedreader.readLine());
	    System.out.println("\n You have selected setPolledDataForNodes Method \n"); 
            System.out.println("Enter the name ");
            String s = bufferedreader.readLine();
            System.out.println("Enter the agent");
            String s1 = bufferedreader.readLine();
            System.out.println("Enter the oid");
            String s2 = bufferedreader.readLine();
            String s3 = s + "\t" + s1 + "\t" + s2;
	    System.out.println("Hai");
            PolledData polleddata = pollapi.getPolledData(s3);
            System.out.println(" pd.id" + polleddata.getId());
            polleddata.setPeriod(212);
            Vector vector = new Vector();
            boolean flag = false;
            switch(i)
            {
            case 2: // '\002'
                boolean flag1 = true;
                while(flag1) 
                {
                    System.out.println("Enter the ipaddress ");
                    vector.addElement(bufferedreader.readLine());
                    System.out.println("Do u want to enter more ipaddress(Y/N)");
                    String s4 = bufferedreader.readLine();
                    if(s4.equalsIgnoreCase("y"))
                        flag1 = true;
                    else
                    if(s4.equalsIgnoreCase("n"))
                    {
                        flag1 = false;
                        String as[] = new String[vector.size()];
                        for(int j = 0; j < vector.size(); j++)
                            as[j] = (String)vector.elementAt(j);

                        flag = pollapi.setPolledDataForNodes(as, polleddata);
                    }
                }
                break;

            case 4: // '\004'
                System.out.println("Enter the starting ipaddress");
                String s5 = bufferedreader.readLine();
                System.out.println("Enter the ending ipaddress");
                String s6 = bufferedreader.readLine();
                System.out.println("Enter the netmask");
                String s7 = bufferedreader.readLine();
                flag = pollapi.setPolledDataForNodes(s5, s6, s7, polleddata);
                break;

            case 3: // '\003'
            default:
                System.out.println("The option entered is wrong");
                break;
            }
            System.out.println("SET-POLLED-DATA-FOR-NODES");
            System.out.println("The PolledData have been set " + flag);
            Vector vector1 = pollapi.getCompleteList();
            for(Enumeration enumeration = vector1.elements(); enumeration.hasMoreElements(); System.out.println(enumeration.nextElement()));
            System.out.println("The total number of objects is " + vector1.size());
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            System.out.println("Exception in setPolledDataForNodes() " + exception);
        }
    }




	    /* ----------------------------------------------------------------- */
            /*****************	28.	Startdatapoll	**************************/
            /* ----------------------------------------------------------------- */

public static void Startdatapoll(PollAPI pollapi)
    {
        try
        {
            boolean flag = pollapi.startDataPoll();
            System.out.println("Result: " + flag);
        }
        catch(Exception exception)
        {
            System.out.println("Exception in startDataPoll() " + exception);
            exception.printStackTrace();
        }
    }




	    /* ----------------------------------------------------------------- */
            /*******************	29.	Stopall	**************************/
            /* ----------------------------------------------------------------- */


 public static void Stopall(PollAPI pollapi)
    {
        try
        {
            pollapi.stopAll();
        }
        catch(Exception exception)
        {
            System.out.println("Exception in stopAll() " + exception);
            exception.printStackTrace();
        }
    }




	    /* ----------------------------------------------------------------- */
            /********************	30.	Stopdatapoll	******************/
            /* ----------------------------------------------------------------- */



public static void Stopdatapoll(PollAPI pollapi)
    {
        try
        {
            boolean flag = pollapi.stopDataPoll();
            System.out.println("Result: " + flag);
        }
        catch(Exception exception)
        {
            System.out.println("Exception in stopDataPoll() " + exception);
            exception.printStackTrace();
        }
    }


 	    /* ----------------------------------------------------------------- */
            /*********	31.	suspendAllPollsForAgent	   ***********************/
            /* ----------------------------------------------------------------- */
private static void Suspendallpollsforagent(PollAPI pollapi)
  	{  
        	try 
        	{
            		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	                String agent1 = null;
        		System.out.println("\n You have selected Suspend all the Poll data for an Agent Method \n");        
          		System.out.print("Enter the agentname for which the polleddata has tobe suspended  :: ");
		        agent1 = br.readLine();
			boolean status = pollapi.suspendAllPollsForAgent(agent1);
	              
			System.out.println("Polling suspended for :: " + agent1 + " :: " + status);
			System.out.println("\n--------------------------------\n");
			System.out.println("\n--------------------------------\n");
		        
		} 
		catch(Exception e)
  	          {
  	              System.out.println("exception while Suspending PD "+e);
  	              e.getMessage();
  	              e.printStackTrace();
  	          }
       }


 

	    /* ----------------------------------------------------------------- */
            /************	32.	 updateNumericTypeOfPd	******************/
            /* ----------------------------------------------------------------- */

private static void Updatenumerictypeofpd(PollAPI pollapi)
  	{ 
	try
            {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String key = null;
		String param=null;
        	System.out.println("\n You have selected updateNumericTypeOfPd Method \n");        
		System.out.print("Enter the polleddata name, press <tab> key , agentname , press <tab> key , enter object oid ,press <tab> key , enter owner name and press <enter> key:: ");
		key=br.readLine();
		System.out.println("Enter the number of the type to be converted (1-->long\t 2-->String)");
		param=br.readLine();
		if (param.equalsIgnoreCase("1"))
			pollapi.updateNumericTypeOfPd(key,1);
		else
			pollapi.updateNumericTypeOfPd(key,2);
                System.out.println("Numeric type updated");
        	System.out.println("Check the updated Numeric type of Polled Data through the data base quary .(i.e in mysql \"select * from PolledData where id=<pollid>;\")");  

		}
            catch(Exception e)
            {
                System.out.println("exception while updating numeric type for PD "+e);
                e.getMessage();
                e.printStackTrace();
            }
}

}