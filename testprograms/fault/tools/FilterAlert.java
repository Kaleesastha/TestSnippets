/* $Id: FilterAlert.java,v 1.1 2003/01/13 12:05:15 rajalakshmytr Exp $
 *
 * Utility class which can be used to add/modify/delete AlertFilters/EventFilters 
 * through RMI.
 *
 * USAGE: java FilterAlert hostName APIName
 *
 * hostName: Name of the machine where NMS is running.
 * APIName : EventFilterAPI/AlertFilterAPI.
 */

import java.util.*;
import java.rmi.Naming;

import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.server.*;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.security.authentication.*;

public class FilterAlert
{
    private FilterObject[] getFilterObjectArray()
    {
        FilterObject[] array = new FilterObject[50];
        for( int i=0;i<50;i++)
        {
            FilterObject object = new FilterObject();
            object.filtername = "winner"+i;
            object.criteria = getMatchingProperties();
            assignFilterActions(object);
            array[i] = object;
        }
        return array;
    }
	
    private FilterAction getSuppressAction()
    {
        FilterAction action = new FilterAction();
        Properties p = new Properties();
        p.put("name","filterTest");
        p.put("suppressAll","false");
        p.put("ddking","queen");
        p.put("suppressInt","60");
        action.setProperties(p);
        return action;
    }	
	
    private FilterObject assignFilterActions(FilterObject object)
    {
        FilterAction[] actionArray = new FilterAction[4];
        actionArray[0]=getSuppressAction();
        actionArray[1]=getSendTrapAction();
        actionArray[2]=getCommandAction();
        actionArray[3]=getSendMailAction();
        //actionArray[0]=getUserFilter();
        object.actions=actionArray;
        return object;
    } 
	
    private FilterAction getCommandAction()
    {
        FilterCommand action = new FilterCommand();
        Properties p = new Properties();
        p.put("name","singam");
        p.put("command","ping saravanakumar");
        p.put("append","true");
        p.put("errappend","false");
        p.put("timeout","10");
        action.setProperties(p);
        return action;
    }	
	
    private UserFilter getUserFilter()
    {
        UserFilter filter = new UserFilter();
        Properties p = new Properties();
        p.setProperty("name","userFilter");
        p.setProperty("userclass","AddFilter");
        filter.setProperties(p);
        return filter;
    }	
	
    private SendEmail getSendMailAction()
    {
        SendEmail action = new SendEmail();
        Properties p = new Properties();
        p.put("name","email");
        p.put("server","mail-server1");
        p.put("fromAddress","saravanakumar@india.adventnet.com");
        p.put("toAddress","saravanakumar@india.adventnet.com");
        p.put("subject","king");
        p.put("message","queen");
        action.setProperties(p);
        return action;
    }

    private SendTrap getSendTrapAction()
    {
        SendTrap action = new SendTrap();
        Properties actionProp = new Properties();
        actionProp.put("name","trap name");
        actionProp.put("peername","saravanakumar");
        actionProp.put("generic","2");
        actionProp.put("trap_port","5000");
        actionProp.put("specific","3");
        actionProp.put("timeticks","123");
        actionProp.put("vb[0]0",".1.2.3.1.2.1.11");
        actionProp.put("vb[1]0","STRING"); 
        actionProp.put("vb[2]0","Test");
        actionProp.put("vb[0]1",".1.2.3.1.2.1.11.11");
        actionProp.put("vb[1]1","STRING"); 
        actionProp.put("vb[2]1","king");

        action.setProperties(actionProp);
        return action;	
    }

    private Properties getMatchingProperties()
    {
        Properties p = new Properties();
        p.put("severity","1");
        p.put("entity","makesh");
        return p;
    }
	
    public static void main(String s[]) throws Exception
    {
        if(s.length != 2)
        {
            System.out.println("USAGE: java FilterAlert hostName APIName");
            return;
        }	
        FilterAlert filter = new FilterAlert();
        //RMIAccessAPI rmiapi = (RMIAccessAPI)Naming.lookup("//"+s[0]+"/RMIAccessAPI");
        CommonFilterAPI api = null;
        if(s[1].equals("AlertFilterAPI"))
        {
            api = (AlertFilterAPI)Naming.lookup("//"+s[0]+"/AlertFilterAPI");
        }
        if(s[1].equals("EventFilterAPI"))
        {
            api = (EventFilterAPI)Naming.lookup("//"+s[0]+"/EventFilterAPI");
        }	
        System.out.println("api is ===="+api);
        FilterAction[] array = new FilterAction[1];
        //array[0]=filter.getSendTrapAction();
        //array[0]=filter.getCommandAction();
        array[0]=filter.getSendMailAction();
        //System.out.println("Success is =="+api.setFilterActions("winner",array,true));
		
        //for( int i=0;i<50;i++)
        {
            System.out.println("Success is "+api.setFilters(filter.getFilterObjectArray()));
        }
        
        String[] nameArray = new String[50];

        for(int i=0;i<50;i++)
        {
            nameArray[i]="winner"+i;
        }
        api.deleteFilters(nameArray);
        //System.out.println(" Complete list is =="+api.getCompleteList()+ "  size is =="+api.getCompleteList().length);

        //api.saveFiltersToFile(filter.getFilterObjectArray(),"conf/queen.filters");
        //String[] string = new String[2];
        //string[0]="testname";
        //string[1]="testname1";
        //api.deleteFilters(string);
        //api.deleteFilterActions("testname",string);
        //((AlertFilterAPI)api).enableFilters(string,true);
    }	
}
