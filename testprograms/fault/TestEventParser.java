
package test;

import com.adventnet.nms.eventdb.EventMgr;
import com.adventnet.nms.eventdb.EventParserAPI;
import com.adventnet.nms.util.RunProcessInterface;
import java.rmi.Naming;
import java.util.Properties;


public class TestEventParser
{

/*************************************************************************************************************************
// File path can be changed according the users directory. In some places the path has been hardcoded.                  //
// This testprogram can be used to validate the Exception Propagation of the EventParserAPI methods.                    // 
// Testplan for this testprograme is fault_expn_prpn_tp.html( from 039 to 52 ) .                                        //
// I have also marked some of the testcase id's as invalid and this needs to be taken care by the validator while testing.
/*************************************************************************************************************************/


    public static void main(String args[])
    {
        TestEventParser  evtpar  = new TestEventParser();

        EventParserAPI eventParserAPI ;
        try
        {
            eventParserAPI = (EventParserAPI)Naming.lookup("//localhost/EventParserAPI");


            if(eventParserAPI != null)
            {
                System.out.println(" Succeded in getting the Handle");

                //FM-EXPN-PRPN-039 ( 39,40,41)
                //evtpar.setEventParser1(eventParserAPI);

                //FM-EXPN-PRPN-042 ( 42,43,44)
                evtpar.setEventParsers1(eventParserAPI);

                //FM-EXPN-PRPN-045 ( 45 ,46)
                //evtpar.saveEventParsersToFile(eventParserAPI);

                //FM-EXPN-PRPN-047 ( 47, 48)
                //evtpar.getEventParsersFromFile(eventParserAPI);
                
                //FM-EXPN-PRPN-049 ( 49 ,50)
                //evtpar.setEnableParsers(eventParserAPI);

                //FM-EXPN-PRPN-051 ( 51 ,52 )
                //evtpar.deleteEventParser(eventParserAPI);
            }

        }
        catch(Exception e)
        {
            System.out.println(" Exception while getting the Handle of EventParserAPI");
            e.printStackTrace();
        }
    }

    public void  setEventParser1(EventParserAPI eventParserAPI)
    {
        // To produce the SQL Exception , set the name of the parser with the charecter length more than 100 charecter.
        // p1.put("name","testttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
        // while setting the parser with this name will defenetly throw SQL Exception and inturn will throw NmsStorageException.
        Properties p1 = new Properties();
        //p1.put("name","TestEventP");
        p1.put("severityMatch","Critical");        
        p1.put("textDefn","text1");
        p1.put("categoryDefn","$category");
        p1.put("Message","$Message");


        //calling setEventParser(properties)
        boolean result1 =false;
        try
        {
            result1 = eventParserAPI.setEventParser(p1);
            System.out.println(" hi ...... Inside the setEventParser == > " +result1 );
        }
        catch(Exception e)
        {
            System.out.println(" Exception while setting the Properties for the Event Parser using setEventParser @@@>>");
            e.printStackTrace();
        }
    }

    
    public void  setEventParsers1(EventParserAPI eventParserAPI)
    {
        Properties[] parr = new Properties[2];
        
        Properties p1 = new Properties();
        p1.put("name","testttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");

        //        p1.put("name","TestEventParser0");
        p1.put("severityMatch","Critical");        
        p1.put("textDefn","text1");
        p1.put("categoryDefn","$category");
        p1.put("Message","$Message");


        Properties p2 = new Properties();
        p2.put("name","TestEventParser2");
        p2.put("textDefn","text2");
        p2.put("severityMatch","Warning");
        p2.put("severityDefn","Major");
        p2.put("Message","$Message");
        
        parr[0]=p1;
        parr[1]=p2;


        //calling setEventParsers(properties[] , bool)
        boolean result2 =false;
        try
        {
            result2=eventParserAPI.setEventParsers(parr,false);
            System.out.println(" hi ...... Inside the setEventParsers == > " +result2 );
        }
        catch(Exception e)
        {
            System.out.println(" Exception while setting the Properties for the Event Parser using setEventParsers ==> ");
            e.printStackTrace();
        }

    }


    public void saveEventParsersToFile(EventParserAPI eventParserAPI)

    {
        Properties[] parr = new Properties[2];
        
        Properties p1 = new Properties();
        p1.put("name","Parser1");
        p1.put("severityMatch","Critical");        
        p1.put("textDefn","text1");
        p1.put("categoryDefn","$category");
        p1.put("Message","$Message");


        Properties p2 = new Properties();
        p2.put("name","Parser2");
        p2.put("textDefn","text2");
        p2.put("severityMatch","Warning");
        p2.put("severityDefn","Major");
        p2.put("Message","$Message");
        
        parr[0]=p1;
        parr[1]=p2;


        //Saving the parses to a specified file
        
        try
        {
            eventParserAPI.saveEventParsersToFile(parr,"/home/srikrishnan/AdventNet/WebNMS/conf/event.parsers");
                   
        }
        catch(Exception ion)
        {
            System.out.println("SaveEventParsersToFile "+ ion.getMessage());
            ion.printStackTrace();
        }
        
    }
        

    public void getEventParsersFromFile(EventParserAPI eventParserAPI)
    {
        //Retrieve the parsers from the saved files
        
        try
        {
            System.out.println(" GET EVT PARSERS " +eventParserAPI.getEventParsersFromFile("/home/srikrishnan/AdventNet/WebNMS/conf/event.parsers"));
        }
        catch (Exception exp)
        {
            System.out.println(" Exception while retrieving the parsers from the given file");
            exp.printStackTrace();
        }
       
    }

    public void setEnableParsers(EventParserAPI eventParserAPI)
    {
        System.out.println(" set Enable Event Parsers Called  ==> ");
                
        String[] sty = new String [1];
        sty[0]="TestEventP";
        //        sty[1]="TestEventParser777";
                
        try
        {
            System.out.println(" enableParsersCalled ==>");                    
            eventParserAPI.enableParsers(sty,false);
                    
        }
        catch(Exception e)
        {
            System.out.println(" Exception while Enabling the Event Parser  " +e);
            e.printStackTrace();
                    
        }
    }
                
    public void deleteEventParser(EventParserAPI eventParserAPI)
    {
        String stg ="TestEventP";
        boolean delvalue = false;
        try
        {
            delvalue = eventParserAPI.deleteEventParser(stg);
            System.out.println(" Delete Event Parser ========>>  " +delvalue);
        }
 
        catch(Exception e)
        {
            System.out.println(" Exception while deleting the Event Parser ");
            e.printStackTrace();
        }

    }
    
}











