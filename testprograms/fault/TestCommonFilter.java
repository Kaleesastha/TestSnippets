

package test;

import com.adventnet.nms.eventdb.EventMgr;
import com.adventnet.nms.eventdb.EventFilterAPI;
import com.adventnet.nms.alertdb.AlertFilterAPI;

//import com.adventnet.nms.eventdb.SendTrap
import com.adventnet.nms.eventdb.FilterCommand;

import com.adventnet.nms.util.RunProcessInterface;

import com.adventnet.nms.server.FilterObject;
import com.adventnet.nms.eventdb.FilterAction;
import java.rmi.*;

import java.util.Properties;

public class TestCommonFilter 
{
    public static void main(String arg[])
    {
        
        TestCommonFilter tcf = new TestCommonFilter();

        EventFilterAPI eventfilterAPI= null ;
        //AlertFilterAPI alertfilterAPI=null;
        
        while ( eventfilterAPI == null )
            //while ( alertfilterAPI == null )
        {
            try
            {
                eventfilterAPI = (EventFilterAPI)Naming.lookup("//localhost/EventFilterAPI");
                //alertfilterAPI = (AlertFilterAPI)Naming.lookup("//localhost/AlertFilterAPI");
            }
            catch (Exception ion)
            {
                System.out.println(" Exception while getting  the Filter Handle");
                ion.printStackTrace();
            }
            //if(alertfilterAPI != null)
            if(eventfilterAPI != null)
            {
                //System.out.println("Succeeded in getting the handle of  EventFilterAPI");
		System.out.println("Succeeded in getting the handle of  EventFilterAPI");
            }
		
        }
        
        //FM-EXPN-PRPN-053 ( 53 ,54 )
        //tcf.setFilters(eventfilterAPI);
        //tcf.setFilters(alertfilterAPI);

        //FM-EXPN-PRPN-055 (55,56)
        //tcf.setFilterActions(eventfilterAPI);
        //tcf.setFilterActions(alertfilterAPI);

        //FM-EXPN-PRPN-57 ( 57,58)
        //tcf.saveFiltersToFile(eventfilterAPI);
        //tcf.saveFiltersToFile(alertfilterAPI);

        //FM-EXPN-PRPN-59 ( 59 ,60)
        //tcf.getFiltersFromFile(eventfilterAPI);
        //tcf.getFiltersFromFile(alertfilterAPI);
          
        //FM-EXPN-PRPN-61 ( 61 ,62 )
        //tcf.getFilters(eventfilterAPI);
        //tcf.getFilters(alertfilterAPI);

        //FM-EXPN-PRPN-63 ( 63 ,64)
        //tcf.deleteFilters(eventfilterAPI);
        //tcf.deleteFilters(alertfilterAPI);

        //FM-EXPN-PRPN-65 ( 65 ,66)
        tcf.deleteFilterActions(eventfilterAPI);
        //tcf.deleteFilterActions(alertfilterAPI);

    }

    public void  setFilters(EventFilterAPI eventfilterAPI)
    //public void  setFilters(AlertFilterAPI alertfilterAPI)
    {

        System.out.println("InsideSetFilters====================");
	
		
        FilterObject foa[] = new FilterObject[1];
        FilterObject fo = new FilterObject();
                
        Properties fop = new Properties();

        fop.put("stringseverity","Critical");
        fop.put("message","Test Event Filter");
        fop.put("Category","Topology");
        fop.put("Entity","sriks");
 
        fo.criteria=fop;

		
 
        FilterAction faa[] =new FilterAction[2];
        FilterAction fa1 =new FilterAction();
        //FilterAction fa2 =new FilterAction();

        Properties fap1 = new Properties();
        fap1.put("name","testsupressallaction109");
        fap1.put("suppressAll","yes");
                 
        FilterCommand command = new FilterCommand();
                

        Properties fap2 = new Properties();
        fap2.put("name","RUNCMD");
        fap2.put("command","ls");
        fap2.put("errappend","true");
        fap2.put("append","true");
        fap2.put("timeout","6");

        command.setProperties(fap2);

                

        fa1.setProperties(fap1);	

        faa[0]=command;	
        faa[1]=fa1;	
        fo.actions = faa;

        fo.filtername="SRIKRISHNANN";
        foa[0]=fo;	

	
        //SetFilters
        try
        {
            eventfilterAPI.setFilters(foa);			
            //alertfilterAPI.setFilters(foa);			
            System.out.println(" Set Filters Called");
        }
        catch(Exception ex)
        {
            System.out.println("Exception while set Filters");
            ex.printStackTrace();	
        }
        
    }

    public void setFilterActions(EventFilterAPI eventfilterAPI)
    //public void setFilterActions(AlertFilterAPI alertfilterAPI)
    {      
        System.out.println(" ============================== Set Filter Action ");
    
        //SetFilterAction
        FilterAction faa1[] =new FilterAction[2];
        FilterAction fa11 =new FilterAction();
        //FilterAction fa2 =new FilterAction();

        Properties fap11 = new Properties();
        fap11.put("name","testsupressallaction");
        fap11.put("suppressAll","false");
                 
        FilterCommand command1 = new FilterCommand();
                

        Properties fap21 = new Properties();
        fap21.put("name","RUNCMD23");
        fap21.put("command","ls");
        fap21.put("errappend","true");
        fap21.put("append","true");
        fap21.put("timeout","6");

        command1.setProperties(fap21);

        fa11.setProperties(fap11);	
        // fa11.setProperties(fap21);	

        faa1[0]=command1;	
        faa1[1]=fa11;	
        //fo.actions = faa;
        //foa[0]=fo;	

          
        String filtername = "new filter";
 
        try
        {
            System.out.println(" Set Filter Action ==> " +eventfilterAPI.setFilterActions(filtername,faa1,true));
            //System.out.println(" Set Filter Action ==> " +alertfilterAPI.setFilterActions(filtername,faa1,true));
        }
        catch(Exception n)
        {
            System.out.println(" Exception while doing setFilterActions()");
            n.printStackTrace();
        }
    }       

    public void saveFiltersToFile(EventFilterAPI eventfilterAPI)
    //  public void saveFiltersToFile(EventFilterAPI eventfilterAPI)
    {

        System.out.println(" ================================== saveFiltersToFile ");
    FilterObject foa[] = new FilterObject[1];
        FilterObject fo = new FilterObject();
                
        Properties fop = new Properties();

        fop.put("stringseverity","Critical");
        fop.put("message","Test Event Filter");
        fop.put("Category","Topology");
        fop.put("Entity","sriks");
 
        fo.criteria=fop;

		
 
        FilterAction faa[] =new FilterAction[2];
        FilterAction fa1 =new FilterAction();
        //FilterAction fa2 =new FilterAction();

        Properties fap1 = new Properties();
        fap1.put("name","testsupressallaction1");
        fap1.put("suppressAll","yes");
                 
        FilterCommand command = new FilterCommand();
                

        Properties fap2 = new Properties();
        fap2.put("name","RUNCMD");
        fap2.put("command","ls");
        fap2.put("errappend","true");
        fap2.put("append","true");
        fap2.put("timeout","6");

        command.setProperties(fap2);

                

        fa1.setProperties(fap1);	

        faa[0]=command;	
        faa[1]=fa1;	
        fo.actions = faa;
        foa[0]=fo;	

        try
        {
            eventfilterAPI.saveFiltersToFile(foa,"/home/srikrishnan/AdventNet/WebNMS/conf/event.filters");
            //alertfilterAPI.saveFiltersToFile(foa,"/home/srikrishnan/AdventNet/WebNMS/conf/alert.filters");
        }
        catch(Exception no)
        {
            System.out.println("Exception while save Filters To file ");
            no.printStackTrace();
        }

    }


    public void getFiltersFromFile(EventFilterAPI eventfilterAPI)
    //  public void getFiltersFromFile(AlertFilterAPI alertfilterAPI)
    {
        System.out.println(" =================================== getFiltersFrom File ");
        try
        {
            eventfilterAPI.getFiltersFromFile("/home/srikrishnan/AdventNet/WebNMS/conf/event.filters");
            //alertfilterAPI.getFiltersFromFile("/home/srikrishnan/AdventNet/WebNMS/conf/alert.filters");
        }
        catch(Exception ion)
        {
            System.out.println(" Exception while getFilters From file ");
            ion.printStackTrace();
        }

    }


    public void getFilters(EventFilterAPI eventfilterAPI)
    // public void getFilters(AlertFilterAPI alertfilterAPI)
    {
        System.out.println(" =================================== getFilters ");
        //GetFilters
        String[] sg = new String[1];
        sg[0] = "new filter";

        try
        {
             FilterObject[]  fooo =  eventfilterAPI.getFilters(sg);
            //FilterObject[]  fooo =  alertfilterAPI.getFilters(sg);
            System.out.println(" Filtername "+fooo[0].filtername);
        }
        catch(Exception n)
        {
            System.out.println(" Exception while doing getFilters()");
            n.printStackTrace();
        }
        
    }
    
    public void deleteFilters(EventFilterAPI eventfilterAPI)
    //public void deleteFilters(AlertFilterAPI alertfilterAPI)
    {

        System.out.println(" ==================================== deleteFilters ");

        //DeleteFilters
        String[] sg = new String[1];
        sg[0] = "SRIKRISHNAN";

        try
        {
            boolean bol = eventfilterAPI.deleteFilters(sg);
            //boolean bol = alertfilterAPI.deleteFilters(sg);
            
            System.out.println(" Deleting the  filter " +bol);
        }
        catch(Exception n)
        {
            System.out.println(" Exception while doing delete Filters()");
            n.printStackTrace();
        }
        
    }       
  
   
    public void deleteFilterActions(EventFilterAPI eventfilterAPI)
    //public void deleteFilterActions(EventFilterAPI eventfilterAPI)
    {
        System.out.println(" =========================================== Delete FilterAction ");

        //DeleteFilterAction
        String[] sg1 = new String[1];
        sg1[0] = "RUNCMD23";
          
        String filtername = "new filter";
 
        try
        {
            System.out.println(" Delete Filter Action ==> " +eventfilterAPI.deleteFilterActions(filtername,sg1));
            //System.out.println(" Delete Filter Action ==> " +alertfilterAPI.deleteFilterActions(filtername,sg1));
        }
        catch(Exception n)
        {
            System.out.println(" Exception while doing deleteFilterActions()");
            n.printStackTrace();
        }
        
    }
        
	

    public void setEnableFilters(EventFilterAPI eventfilterAPI)
    //public void setEnableFilters(AlertFilterAPI alertfilterAPI)
    {
        System.out.println(" ============================== enable Filters ");
        //		String[]  arr  = new String[2];
        //		arr[0]="filtername1";
        //		arr[1]="filtername2";
        
        String [] name = new String[1];
        name[0] = "new filter";
        
        //System.out.println(" ====================================================");
        try
        {
            System.out.println(" Execute enableFilters(name , true)");
            eventfilterAPI.enableFilters(name,false);
            //alertfilterAPI.enableFilters(name,true);
        }
        catch(Exception ex)
        {
            System.out.println("Exception while enabling Filters");
            ex.printStackTrace();	
        }
        
 
    }
	
}




