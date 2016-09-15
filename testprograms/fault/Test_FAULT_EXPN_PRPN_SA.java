package test;

import java.sql.*;
import java.rmi.*;
import java.util.*;
import java.io.*;

import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.management.transaction.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.store.*;
import com.adventnet.nms.fault.*;
import com.adventnet.nms.commonbe.*;
import com.adventnet.nms.server.FilterObject;

import javax.transaction.RollbackException;

public class Test_FAULT_EXPN_PRPN_SA
{
    String hostName = "localhost";
    String port = "1099";

    TransactionAPI transAPI = null;
    TrapAPI trapAPI = null;
    EventParserAPI eventParserAPI = null;
    EventFilterAPI eventFilterAPI = null;
    AlertFilterAPI alertFilterAPI = null;
    AlertAPI alertAPI = null;
    EventAPI eventAPI = null;
    String entity = "rajalakshmytr.india.adventnet.com";

    public static void main(String[] a)
    {
        if(a.length<2)
        {
            System.out.println("Usage: java Test_FAULT_EXPN_PRPN_SA hostName port");
            return;
        }
        Test_FAULT_EXPN_PRPN_SA tt = new Test_FAULT_EXPN_PRPN_SA(a[0], a[1]);
        tt.start();
    }

    public Test_FAULT_EXPN_PRPN_SA(String h, String p)
    {
        hostName = h;
        port = p;
    }

    public void start()
    {
        try
        {
            //while(transAPI == null || trapAPI == null || eventFilterAPI == null || eventParserAPI == null || alertFilterAPI == null)
            //{
            transAPI = TransactionAPI.getInstance();
            trapAPI = (TrapAPI)Naming.lookup("//"+hostName+":"+port+"/TrapAPI");
            eventParserAPI  = (EventParserAPI)Naming.lookup("//"+hostName+":1099/EventParserAPI");
            eventFilterAPI = (EventFilterAPI)Naming.lookup("//"+hostName+":1099/EventFilterAPI");
            alertFilterAPI = (AlertFilterAPI)Naming.lookup("//"+hostName+":1099/AlertFilterAPI");
            alertAPI = (AlertAPI)Naming.lookup("//"+hostName+":1099/AlertAPI");
            eventAPI = (EventAPI)Naming.lookup("//"+hostName+":1099/EventAPI");
            /*if(transAPI == null || trapAPI == null || eventFilterAPI == null || eventParserAPI == null || alertFilterAPI == null || alertAPI ==null || eventAPI == null)
            {
                try
                {
                    Thread.sleep(200);
                }
                catch(Exception ee)
                {
                    ee.printStackTrace();
                }
                }*/
            //}
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return;
        }

        System.out.println(" SUCCESSFULLY Got TrapAPI && TransactionAPI handles ===");

        System.out.println(" PROCESS IS READY TO START ==*");
        /*
        try
        {
            System.out.println("PREAMBLE");
            System.out.println("Remove all permission for MIB file:mibs/RFC1213-MIB within 30 seconds");
            System.out.println("waiting...");
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        performTrap011(trapAPI);        
        */
        /*
        try
        {
            System.out.println("PREAMBLE");
            System.out.println("Remove modify permission for trap.parsers file within 30 seconds");
            System.out.println("waiting...");
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        performTrap013(trapAPI);
        performTrap018(trapAPI);
        */
        /*     
        try
        {
            System.out.println("PREAMBLE");
            System.out.println("Remove read permission for trap.filters file within 30 seconds");
            System.out.println("waiting...");
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        performTrap025(trapAPI);
        */
        /*
        try
        {
            System.out.println("PREAMBLE");
            System.out.println("Remove write permission for event.filters file within 30 seconds");
            System.out.println("waiting...");           
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        performFilter36();
        */
        /*
        try
        {
            System.out.println("PREAMBLE");
            System.out.println("Remove write permission for alert.filters file within 30 seconds");
            System.out.println("waiting...");             
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        performFilter38();
        performFilter56();        
        performFilter64();
        */
        /*
        try
        {
            System.out.println("PREAMBLE");
            System.out.println("Remove read permission for alert.filters file within 30 seconds");
            System.out.println("waiting...");             
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        performFilter59();
        */
        /*
        try
        {
            System.out.println("PREAMBLE");
            System.out.println("Remove write permission for event.parsers file within 30 seconds");
            System.out.println("waiting..."); 
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        performFilter46();
        performFilter50();
        performFilter52();
        */
        /*
        try
        {
            System.out.println("PREAMBLE");
            System.out.println("Remove read permission for event.parsers file within 30 seconds");
            System.out.println("waiting...");             
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        performFilter47();
        */

        /*
        addAlertAnnotation88();
        addAlertAnnotation89();
        clearAlert92();
        clearAlert93();
        clearAlert96();
        deleteAlert97();
        deleteAlert98();        
        deleteAlert101();
        pickUpAlert102();
        pickUpAlert103();
        pickUpAlert106();
        unPickAlert107();
        unPickAlert108();           
        unPickAlert111();
        updateAlert112();
        updateAlert113();
        performUpdateAlert113();
        updateAlert116();
        performUpdateAlert116();
        performUpdateAlert117();        
        performUpdateAlert120();
        performUpdateNotes121();
        performUpdateNotes122();        
        performUpdateNotes125();
        performLockTest126();
        performLockTest127();
        performPurgeEvent128();
        performPurgeEvent129();        
        performPurgeAlert132();
        performPurgeAlert133();        
        performPurgeAlert136();
        */

        // --------------------------------------------------------------------------------- 
        /*
        try
        {
            System.out.println("PREAMBLE");
            System.out.println(" Drop the Table : TrapEventParser within 30 seconds");
            System.out.println("waiting...");
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        performTrap001(trapAPI);
        performTrap004(trapAPI);
        performFilter39();

        try
        {
            System.out.println("PREAMBLE");
            System.out.println("Drop the table TrapFilter within 30 seconds");
            System.out.println("waiting...");
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        performTrap019(trapAPI);
        performTrap022(trapAPI);

        try
        {
            System.out.println("PREAMBLE");
            System.out.println("Drop the table TrapDisabledMO within 30 seconds");
            System.out.println("waiting...");
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        performTrap031(trapAPI);
        performTrap032(trapAPI);
        performTrap033(trapAPI);
        performTrap034(trapAPI);
        */

        /*
        try
        {
            System.out.println("PREAMBLE");
            System.out.println(" Drop the Table : EventAlertFilter within 30 seconds");
            System.out.println("waiting..."); 
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        performFilter53();
        */
        
        /*
        try
        {
            System.out.println("PREAMBLE");
            System.out.println("Execute: drop table ANNOTATION within 30 seconds");
            System.out.println("waiting..."); 
            Thread.sleep(120000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        addAlertAnnotation91();
        */

        //addAlertAnnotation90();
        //clearAlert94();
        //clearAlert95();
        //deleteAlert99();        
        //deleteAlert100();
        //pickUpAlert104();
        //pickUpAlert104_1();
        //pickUpAlert105();
        //unPickAlert109();
        //unPickAlert110();
        //unPickAlert110_1();
        //performUpdateAlert114();
        //performUpdateAlert115();
        //performUpdateAlert118();
        //performUpdateAlert119();
        //performUpdateNotes123();
        //performUpdateNotes124();
        //performPurgeEvent130();
        //performPurgeEvent131();
        //performPurgeAlert134();
        performPurgeAlert135();
        //getAlertsBasedOnGVM137();
        //getAlertsBasedOnGVM138();
        //getNextAlertBasedOnGVM139();
        //getNextAlertBasedOnGVM140();
        //getNextAlertBasedOnModTime141();

        //group1 start
        /*
        getNextAlertBasedOnModTime142();
        getAlertCount143();
        getAlertCount144();
        getAlertCount145();
        getAlertCount146();
        getTotalAlertCount147();
        getTotalAlertCount148();        
        getTotalAlertCount149();
        getTotalAlertCount150();
        getTotalAlertCount151();
        getTotalAlertCount152();
        */
        //group1 end

        //group2 start
        
        //deleteAlertHistory153();
        //deleteAlertHistory154();
        //deleteAlertAnnotation155();
        //deleteAlertAnnotation156();
        //purgeAlertDB157();
        
        //group2 end
    }

    private void performTrap001(TrapAPI trapAPI)
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-001");
        TrapParser parser = new TrapParser();
        Properties p = new Properties();
        p.put("name","TP1");
        p.put("type","1");
        parser.setProperties(p);

        try
        {
            trapAPI.setTrapParser(parser);
        }
        catch(Exception ee)
        {
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-001*** Successful");
                ee.printStackTrace();
            }
        }
    }

    private void performTrap004(TrapAPI trapAPI)
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-004");

        TrapParser[] array = new TrapParser[3];

        for(int i=0;i<array.length;i++)
        {
            TrapParser parser = new TrapParser();
            Properties p = new Properties();
            p.put("name","TP1"+i);
            p.put("type","1");
            parser.setProperties(p);
            array[i]=parser;
        }

        try
        {
            trapAPI.setTrapParsers(array,false);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-004*** Successful");                
            }
        }
    }

    private void performTrap011(TrapAPI trapAPI)
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-011");
        String mibName = "mibs/RFC1213-MIB";
        try
        {
            trapAPI.getTrapParsersFromMib(mibName);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-011*** Successful");                
            }
        }
    }

    private void performTrap013(TrapAPI trapAPI)
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-013");
        try
        {
            TrapParser[] array = trapAPI.getAllTrapParsers();
            trapAPI.saveTrapParsersToFile(array,"conf/trap.parsers");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-013*** Successful");
            }
        }
    }

    private void performTrap018(TrapAPI trapAPI)
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-018");
        try
        {
            String[] array = {"coldStart","warmStart"};
            trapAPI.deleteTrapParsers(array);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-018*** Successful");                
            }
        }
    }

    private void performTrap019(TrapAPI trapAPI)
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-019");
        TrapFilterHolder holder = new TrapFilterHolder();
        holder.setEnterpriseOID(".1.2.3");
        holder.setGenericType("6");
        holder.setSpecificType("9");
        try
        {
            holder.setTrapFilter((TrapFilter)Class.forName("test.LinkUpDownTrapFilter").newInstance());
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }

        try
        {
            trapAPI.setTrapFilter(holder,false);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-019*** Successful");                
            }
        }
    }

    private void performTrap022(TrapAPI trapAPI)
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-022");
        TrapFilterHolder holder = new TrapFilterHolder();
        holder.setEnterpriseOID(".1.2.3");
        holder.setGenericType("6");
        holder.setSpecificType("9");
        try
        {
            holder.setTrapFilter((TrapFilter)Class.forName("test.LinkUpDownTrapFilter").newInstance());
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }

        TrapFilterHolder[] holderArray = new TrapFilterHolder[1];
        holderArray[0]=holder;
        try
        {
            trapAPI.setTrapFilters(holderArray,false,true);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-022*** Successful");                
            }
        }
    }

    private void performTrap025(TrapAPI trapAPI)
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-025");
        try
        {
            trapAPI.getTrapFiltersFromFile("conf/trap.filters");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-025*** Successful");                
            }
        }
    }

    private void performTrap031(TrapAPI trapAPI)
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-031");
        String[] groups= {"gone","gtwo"};
        try
        {
            trapAPI.disableTrapsForGroups(groups,false);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-031*** Successful");                
            }
        }
    }

    private void performTrap032(TrapAPI trapAPI)
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-032");
        String[] nodes= {"none","ntwo"};
        try
        {
            trapAPI.disableTrapsForNodes(nodes,false);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-032*** Successful");                
            }
        }
    }

    private void performTrap033(TrapAPI trapAPI)
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-033");
        String[] groups= {"gone","gtwo"};
        try
        {
            trapAPI.enableTrapsForGroups(groups);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-033*** Successful");                
            }
        }
    }

    private void performTrap034(TrapAPI trapAPI)
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-034");
        String[] nodes= {"none","ntwo"};
        try
        {
            trapAPI.enableTrapsForNodes(nodes);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-034*** Successful");                
            }
        }
    }

    private void performFilter36()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-036");
        String array[]={"this"};
        try
        {
            eventFilterAPI.enableFilters(array,false);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-036*** Successful");
            }
        }
    }

    private void performFilter38()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-038");
        String array[]={"this"};
        try
        {
            alertFilterAPI.enableFilters(array,false);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-038*** Successful");
            }
        }
    }

    private void performFilter39()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-039");
        Properties king = new Properties();
        king.setProperty("winn","jker");

        try
        {
            eventParserAPI.setEventParser(king);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-039*** Successful");
            }
        }
    }

    private void performFilter46()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-046");
        Properties prop[] = new Properties[1];

        Properties king = new Properties();
        king.setProperty("winn","jker");

        prop[0]=king;

        try
        {
            eventParserAPI.saveEventParsersToFile(prop,"conf/event.parsers");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-046*** Successful");
            }
            else if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-046*** Successful");
            }
        }
    }

    private void performFilter47()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-047");        
        try
        {
            Properties[] prop = eventParserAPI.getEventParsersFromFile("conf/event.parsers");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-047*** Successful");
            }
        }
    }

    private void performFilter50()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-050");
        String array[] = {"exampleparser"};
        try
        {
            Thread.sleep(60000);
            eventParserAPI.enableParsers(array,false);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-050*** Successful");
            }
            else if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-050*** Successful");
            }
        }
    }

    private void performFilter52()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-052");
        try
        {
            eventParserAPI.deleteEventParser("this");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-052*** Successful");
            }
            else if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-052*** Successful");
            }
        }
    }

    private void performFilter53()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-053");
        try
        {
            boolean succ = alertFilterAPI.setFilters(getFilterObjectArray());
            System.out.println(succ);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-053*** Successful");
            }
        }
    }

    private void performFilter56()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-056");
        String array[] = {"this"};
        try
        {
            FilterAction[] actionArray = new FilterAction[4];
            actionArray[0]=getSuppressAction();
            alertFilterAPI.setFilterActions("test", actionArray, true);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-056*** Successful");
            }
            else if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-056*** Successful");                
            }
        }
    }

    private void performFilter59()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-059");
        String array[] = {"this"};
        try
        {
            alertFilterAPI.getFiltersFromFile("conf/alert.filters");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-059*** Successful");                
            }
        }
    }

    private void performFilter64()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-064");
        String array[] = {"this"};
        try
        {
            alertFilterAPI.deleteFilters(array);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof NmsStorageException)
            {
                System.out.println(" ***FM-EXPN-PRPN-064*** Successful");  
            }
        }
    }

    private FilterObject[] getFilterObjectArray()
    {
        FilterObject[] array = new FilterObject[1];
        for( int i=0;i<1;i++)
        {
            FilterObject object = new FilterObject();
            object.filtername = "winner"+i;
            object.criteria = new Properties();
            assignFilterActions(object);
            array[i] = object;
        }
        return array;
    }

    private FilterAction getSuppressAction()
    {
        FilterAction action = new FilterAction();
        Properties p = new Properties();
        p.put("name","test");
        p.put("suppressAll","false");
        p.put("suppressInt","60");
        action.setProperties(p);
        return action;
    }

    private FilterObject assignFilterActions(FilterObject object)
    {
        FilterAction[] actionArray = new FilterAction[1];
        actionArray[0]=getSuppressAction();
        object.actions=actionArray;
        return object;
    }

    private void addAlertAnnotation88()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-088");

        addEvent();        
        try
        {
            transAPI.begin(10000);
            Thread.sleep(15000);
            alertAPI.addAnnotation(getAnnotation());
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-088*** Successful"); 
            }            
            try
            {
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void addAlertAnnotation89()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-089");
        addEvent();
        
        try
        {
            transAPI.begin(10000);
            alertAPI.addAnnotation(getAnnotation());
            Thread.sleep(15000);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof RollbackException)
            {
                System.out.println(" ***FM-EXPN-PRPN-089*** Successful"); 
            }
            ee.printStackTrace();
            try
            {
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void addAlertAnnotation90()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-090");
        addEvent();

        try
        {
            AlertAnnotation aa = getAnnotation();
            transAPI.begin(-1);
            try
            {
                System.out.println("PREAMBLE");
                System.out.println("Execute: drop table ANNOTATION within 30 seconds");
                System.out.println("waiting..."); 
                Thread.sleep(120000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }            
            alertAPI.addAnnotation(aa);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-090*** Successful"); 
            }
            try
            {
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                e.printStackTrace();
            }
        }
    }
    
    private void addAlertAnnotation91()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-091");
        addEvent();
        
        try
        {
            alertAPI.addAnnotation(getAnnotation());
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-091*** Successful"); 
            }
        }
    }


    private void clearAlert92()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-092");
        addEvent();
        Alert alt = null;

        try
        {
            transAPI.begin(10000);
            alt = alertAPI.checkOutIfAvailable(entity);
            Thread.sleep(15000);
            
            alertAPI.clearAlert(alt,true);
            alertAPI.unlock(alt);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-092*** Successful"); 
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void clearAlert93()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-093");
        addEvent();
        Alert alt = null;

        try
        {
            transAPI.begin(10000);
            alt = alertAPI.checkOutIfAvailable(entity);
            alertAPI.clearAlert(alt,true);
            alertAPI.unlock(alt);
            Thread.sleep(15000);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof RollbackException)
            {
                System.out.println(" ***FM-EXPN-PRPN-093*** Successful"); 
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void clearAlert94()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-094");
        addEvent();
        Alert alt = null;

        try
        {
            transAPI.begin(-1);
            alt = alertAPI.checkOutIfAvailable(entity);
            try
            {
                System.out.println("Execute: drop table ANNOTATION within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            alertAPI.clearAlert(alt,true);
            alertAPI.unlock(alt);
            transAPI.commit();
            System.out.println(" ***FM-EXPN-PRPN-094*** Successful"); 
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void clearAlert95()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-095");
        addEvent();
        Alert alt = null;

        try
        {
            alt = alertAPI.checkOutIfAvailable(entity);
            try
            {
                System.out.println("Execute: drop table ANNOTATION within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            alertAPI.clearAlert(alt,true);
            alertAPI.unlock(alt);
            System.out.println(" ***FM-EXPN-PRPN-095*** Successful"); 
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void clearAlert96()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-096");
        addEvent();
        Alert alt = null;

        try
        {
            alt = alertAPI.checkOutIfAvailable(entity);
            Alert second = alertAPI.getAlert(entity);
            alertAPI.clearAlert(second,true);
            alertAPI.unlock(alt);
        }
        catch(Exception ee)
        {
            if(ee instanceof AccessDeniedException)
            {
                System.out.println(" ***FM-EXPN-PRPN-096*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void deleteAlert97()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-097");
        addEvent();
        Alert alt = null;

        try
        {
            transAPI.begin(10000);
            Thread.sleep(15000);
            alt = alertAPI.checkOutIfAvailable(entity);
            alertAPI.deleteAlert(alt,true);
            alertAPI.unlock(alt);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-097*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void deleteAlert98()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-098");
        addEvent();
        Alert alt = null;

        try
        {
            transAPI.begin(10000);
            
            alt = alertAPI.checkOutIfAvailable(entity);
            alertAPI.deleteAlert(alt,true);
            alertAPI.unlock(alt);
            Thread.sleep(15000);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof RollbackException)
            {
                System.out.println(" ***FM-EXPN-PRPN-098*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }
    
    private void deleteAlert99()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-099");
        addEvent();
        Alert alt = null;

        try
        {
            transAPI.begin(-1);
            alt = alertAPI.checkOutIfAvailable(entity);
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            alertAPI.deleteAlert(alt,true);
            alertAPI.unlock(alt);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-099*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void deleteAlert100()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-100");
        addEvent();
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        Alert alt = null;

        try
        {
            alt = alertAPI.checkOutIfAvailable(entity);
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            alertAPI.deleteAlert(alt,true);
            alertAPI.unlock(alt);
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-100*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void deleteAlert101()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-101");
        addEvent();
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        Alert alt = null;

        try
        {
            alt = alertAPI.checkOutIfAvailable(entity);
            Alert second = alertAPI.getAlert(entity);
            alertAPI.deleteAlert(second,true);
            alertAPI.unlock(alt);
        }
        catch(Exception ee)
        {
            if(ee instanceof AccessDeniedException)
            {
                System.out.println(" ***FM-EXPN-PRPN-101*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void pickUpAlert102()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-102");
        addEvent();
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        Alert alt = null;

        try
        {
            transAPI.begin(10000);
            Thread.sleep(15000);
            alt = alertAPI.checkOutIfAvailable(entity);
            alertAPI.pickUpAlert(alt,"king",true);
            alertAPI.unlock(alt);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-102*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }
    
    private void pickUpAlert103()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-103");
        addEvent();
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        Alert alt = null;
        try
        {
            transAPI.begin(10000);
            
            alt = alertAPI.checkOutIfAvailable(entity);
            alertAPI.pickUpAlert(alt,"king",true);
            Thread.sleep(15000);
            alertAPI.unlock(alt);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof RollbackException)
            {
                System.out.println(" ***FM-EXPN-PRPN-103*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void pickUpAlert104_1()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-104_1");
        addEvent();
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        Alert alt = null;
        try
        {
            alt = alertAPI.checkOutIfAvailable(entity);
            try
            {
                System.out.println("Execute: drop table ANNOTATION within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            alertAPI.pickUpAlert(alt,"king",true);
            System.out.println(" ***FM-EXPN-PRPN-104_1*** Successful");
            alertAPI.unPickAlert(alt,"king",true);
            alertAPI.unlock(alt);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void pickUpAlert104()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-104");
        addEvent();
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        Alert alt = null;

        try
        {
            transAPI.begin(-1);
            
            alt = alertAPI.checkOutIfAvailable(entity);
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }

            alertAPI.pickUpAlert(alt,"king",true);
            
            alertAPI.unlock(alt);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-104*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void pickUpAlert105()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-105");
        addEvent();
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        Alert alt = null;

        try
        {
            alt = alertAPI.checkOutIfAvailable(entity);
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            alertAPI.pickUpAlert(alt,"king",true);
            alertAPI.unlock(alt);
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-105*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }
    
    private void pickUpAlert106()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-106");
        addEvent();
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        Alert alt = null;

        try
        {
            alt = alertAPI.checkOutIfAvailable(entity);
            Alert second = alertAPI.getAlert(entity);
            alertAPI.pickUpAlert(second,"king",true);
            alertAPI.unlock(alt);
        }
        catch(Exception ee)
        {
            if(ee instanceof AccessDeniedException)
            {
                System.out.println(" ***FM-EXPN-PRPN-106*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void unPickAlert107()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-107");
        addEvent();
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        Alert alt = null;

        try
        {
            alertAPI.pickUpAlert(entity,"king");
            transAPI.begin(10000);
            
            alt = alertAPI.checkOutIfAvailable(entity);
            
            Thread.sleep(15000);
            alertAPI.unPickAlert(alt,"king",true);
            
            alertAPI.unlock(alt);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-107*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }
    
    private void unPickAlert108()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-108");
        addEvent();
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        Alert alt = null;

        try
        {
            alertAPI.pickUpAlert(entity,"king");
            transAPI.begin(10000);
            
            alt = alertAPI.checkOutIfAvailable(entity);
            
            alertAPI.unPickAlert(alt,"king",true);
            
            alertAPI.unlock(alt);
            Thread.sleep(15000);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof RollbackException)
            {
                System.out.println(" ***FM-EXPN-PRPN-108*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void unPickAlert109()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-109");
        addEvent();
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        Alert alt = null;

        try
        {
            alertAPI.pickUpAlert(entity,"king");
            
            transAPI.begin(-1);
            
            alt = alertAPI.checkOutIfAvailable(entity);
            
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            alertAPI.unPickAlert(alt,"king",true);
            
            alertAPI.unlock(alt);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-109*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void unPickAlert110()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-110");
        addEvent();
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        Alert alt = null;

        try
        {
            alt = alertAPI.checkOutIfAvailable(entity);
            alertAPI.pickUpAlert(alt,"king",true);
            
            alertAPI.unlock(alt);
            
            alt = alertAPI.checkOutIfAvailable(entity);
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }

            alertAPI.unPickAlert(alt,"king",true);
            
            alertAPI.unlock(alt);
        }
        catch(Exception ee)
        {
            System.out.println(" Exception at 110===="+ee);
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-110*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void unPickAlert110_1()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-110_1");
        addEvent();
        Alert alt = null;
        try
        {            
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            alertAPI.pickUpAlert(entity,"king");
                        
            alt = alertAPI.checkOutIfAvailable(entity);
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            
            alertAPI.unPickAlert(alt,"king",true);
            alertAPI.unlock(alt);            
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-110_1*** Successful");
            }
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void unPickAlert111()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-111");
        addEvent();
        Alert alt = null;
        try
        {
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            alertAPI.pickUpAlert(entity,"king");
            alt = alertAPI.checkOutIfAvailable(entity);
            Alert second = alertAPI.getAlert(entity);
            alertAPI.unPickAlert(second,"king",true);
            alertAPI.unlock(alt);
        }
        catch(Exception ee)
        {
            if(ee instanceof AccessDeniedException)
            {
                System.out.println(" ***FM-EXPN-PRPN-111*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void updateAlert112()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-112");
        addEvent();
        Alert alt = null;
        try
        {
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            transAPI.begin(10000);
            
            alt = alertAPI.checkOutIfAvailable(entity);
            alt.setMessage("Test For 112");
            Thread.sleep(15000);
            alertAPI.updateAlert(alt);
            
            alertAPI.unlock(alt);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-112*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void updateAlert113()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-113");
        addEvent();
        Alert alt = null;
        try
        {
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            transAPI.begin(10000);
            
            alt = alertAPI.checkOutIfAvailable(entity);
            alt.setMessage("Test For 113");
            
            alertAPI.updateAlert(alt);
            
            alertAPI.unlock(alt);
            Thread.sleep(15000);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof RollbackException)
            {
                System.out.println(" ***FM-EXPN-PRPN-113*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void performUpdateAlert113()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-113");
        addEvent();
        
        try
        {
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }

        try
        {
            alertAPI.deleteAlertHistory(entity);
            System.out.println(" HISTORY FOR THIS ALERT DELETED SUCCESSFULLY FM-EXPN-PRPN-113");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        
        Alert alt = null;
        
        String testMessage="test"+System.currentTimeMillis();
        try
        {
            transAPI.begin(10000);


            alt= alertAPI.checkOutIfAvailable(entity);
            
            alt.setMessage(testMessage);
            alertAPI.updateAlert(alt);

            Thread.sleep(15000);

            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof RollbackException)
            {
                System.out.println(" ***FM-EXPN-PRPN-113*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        checkForResult(testMessage,false,"FM-EXPN-PRPN-113");
    }

    private void performUpdateAlert114()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-114");
        addEvent();
        
        try
        {
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }

        try
        {
            alertAPI.deleteAlertHistory(entity);
            System.out.println(" HISTORY FOR THIS ALERT DELETED SUCCESSFULLY FM-EXPN-PRPN-114");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        
        Alert alt = null;
        
        String testMessage="thisistotest"+System.currentTimeMillis();
        try
        {
            transAPI.begin(10000);


            alt= alertAPI.checkOutIfAvailable(entity);
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            alt.setMessage(testMessage);
            alertAPI.updateAlert(alt);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-114*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        checkForResult(testMessage,false,"FM-EXPN-PRPN-114");
        
    }

    private void performUpdateAlert115()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-115");
        addAndDeleteHistory(true,"FM-EXPN-PRPN-115",true);
        
        Alert alt = null;
        
        String testMessage="thisistotest"+System.currentTimeMillis();
        try
        {
            alt= alertAPI.checkOutIfAvailable(entity);
            
            alt.setMessage(testMessage);
            
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }

            alertAPI.updateAlert(alt);
            alertAPI.unlock(alt);
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-115*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();

            }
        }
        
        checkForResult(testMessage,false,"FM-EXPN-PRPN-115");
    }

    private void updateAlert116()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-116");
        addEvent();
        Alert alt = null;
        try
        {
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            transAPI.begin(10000);
            
            alt = alertAPI.checkOutIfAvailable(entity);
            alt.setMessage("Test For 116");
            Thread.sleep(15000);
            alertAPI.updateAlert(alt,true);
            
            alertAPI.unlock(alt);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-116*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
                transAPI.commit();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while commiting =="+e);
                ee.printStackTrace();
            }
        }
    }

    private void performUpdateAlert116()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-116");
        addEvent();
        
        try
        {
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }

        try
        {
            alertAPI.deleteAlertHistory(entity);
            System.out.println(" HISTORY FOR THIS ALERT DELETED SUCCESSFULLY FM-EXPN-PRPN-116");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        
        Alert alt = null;
        
        String testMessage="test"+System.currentTimeMillis();
        try
        {
            transAPI.begin(10000);


            alt= alertAPI.checkOutIfAvailable(entity);
            Thread.sleep(15000);
            
            alt.setMessage(testMessage);
            alt.setSeverity(4);
            alertAPI.updateAlert(alt,true);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-116*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        checkForResult(testMessage,false,"FM-EXPN-PRPN-116");
        
    }

    private void performUpdateAlert117()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-117");
        addEvent();
        
        try
        {
            Thread.sleep(30000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }

        try
        {
            alertAPI.deleteAlertHistory(entity);
            System.out.println(" ***FM-EXPN-PRPN-117*** Successful");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        
        Alert alt = null;
        
        String testMessage="test"+System.currentTimeMillis();
        try
        {
            transAPI.begin(10000);


            alt= alertAPI.checkOutIfAvailable(entity);
            
            alt.setMessage(testMessage);
            alertAPI.updateAlert(alt,true);
            
            Thread.sleep(15000);

            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof RollbackException)
            {
                System.out.println(" ***FM-EXPN-PRPN-117*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        checkForResult(testMessage,false,"FM-EXPN-PRPN-117");
    }

    private void performUpdateAlert118()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-118");
        addEvent();
        
        try
        {
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }

        try
        {
            alertAPI.deleteAlertHistory(entity);
            System.out.println(" HISTORY FOR THIS ALERT DELETED SUCCESSFULLY FM-EXPN-PRPN-118");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        
        Alert alt = null;
        
        String testMessage="thisistotest"+System.currentTimeMillis();
        try
        {
            transAPI.begin(10000);


            alt= alertAPI.checkOutIfAvailable(entity);
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            
            alt.setMessage(testMessage);
            alt.setWebNMS(null);
            alertAPI.updateAlert(alt,true);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-118*** Successful");
            }
            System.out.println("Exception while fetching Alert =="+ee);
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        checkForResult(testMessage,false,"FM-EXPN-PRPN-118");
        
    }

    private void performUpdateAlert119()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-119");
        addEvent();
        
        try
        {
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }

        try
        {
            alertAPI.deleteAlertHistory(entity);
            System.out.println(" HISTORY FOR THIS ALERT DELETED SUCCESSFULLY FM-EXPN-PRPN-119");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        
        Alert alt = null;
        
        String testMessage="thisistotest"+System.currentTimeMillis();
        try
        {
            alt= alertAPI.checkOutIfAvailable(entity);
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }            

            alt.setMessage(testMessage);
            alertAPI.updateAlert(alt,true);
        }
        catch(Exception ee)
        {
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-119*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        checkForResult(testMessage,false,"FM-EXPN-PRPN-119");
    }


    private void performUpdateAlert120()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-120");
        addAndDeleteHistory(true,"FM-EXPN-PRPN-120",true);
        
        Alert alt = null;
        
        String testMessage="test"+System.currentTimeMillis();
        try
        {
        
            Thread.sleep(30000);
            alt= alertAPI.checkOutIfAvailable(entity);

            Alert second = alertAPI.getAlert(entity);
            second.setMessage(testMessage);
            alertAPI.updateAlert(second,true);
            alertAPI.unlock(alt);
        }
        catch(Exception ee)
        {
            if(ee instanceof AccessDeniedException)
            {
                System.out.println(" ***FM-EXPN-PRPN-120*** Successful");
            }
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        checkForResult(testMessage,false,"FM-EXPN-PRPN-120");
    }

    private void performUpdateNotes121()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-121");
        addAndDeleteHistory(true,"FM-EXPN-PRPN-121",false);
        
        Alert alt = null;
        
        String testMessage="test"+System.currentTimeMillis();
        try
        {
        
            transAPI.begin(10000);

            Thread.sleep(15000);
            alt= alertAPI.checkOutIfAvailable(entity);
            
            alertAPI.updateNotes(alt,"saraana",testMessage,true);
            alertAPI.unlock(alt);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-121*** Successful");
            }
            
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        checkForAnnotationResult(testMessage,false,"FM-EXPN-PRPN-121");
    }

    private void performUpdateNotes122()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-122");
        addAndDeleteHistory(true,"FM-EXPN-PRPN-122",false);
        
        Alert alt = null;
        
        String testMessage="test"+System.currentTimeMillis();
        try
        {
            transAPI.begin(10000);

            alt= alertAPI.checkOutIfAvailable(entity);
            
            alertAPI.updateNotes(alt,"saraana",testMessage,true);
            alertAPI.unlock(alt);
            Thread.sleep(15000);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof RollbackException)
            {
                System.out.println(" ***FM-EXPN-PRPN-122*** Successful");
            }
            
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        checkForAnnotationResult(testMessage,false,"FM-EXPN-PRPN-122");
    }


    private void performUpdateNotes123()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-123");
        addAndDeleteHistory(true,"FM-EXPN-PRPN-123",true);
        
        Alert alt = null;
        
        String testMessage="testthisandqueengoneout"+System.currentTimeMillis();
        try
        {   
            alt= alertAPI.checkOutIfAvailable(entity);

            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }

            transAPI.begin(10000);            
            
            alertAPI.updateNotes(alt,"saraana",testMessage,true);
            alertAPI.unlock(alt);
            
            transAPI.commit();
        }
        catch(Exception ee)
        {
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-123*** Successful");
            }
            
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        checkForAnnotationResult(testMessage,false,"FM-EXPN-PRPN-123");
    }

    private void performUpdateNotes124()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-124");
        addAndDeleteHistory(true,"FM-EXPN-PRPN-124",true);
        
        Alert alt = null;
        
        String testMessage="testthisandqueengoneout"+System.currentTimeMillis();
        try
        {
            alt= alertAPI.checkOutIfAvailable(entity);
            
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            alertAPI.updateNotes(alt,"saraana",testMessage,true);
            alertAPI.unlock(alt);
        }
        catch(Exception ee)
        {
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-124*** Successful");
            }
            
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }        
        checkForAnnotationResult(testMessage,false,"FM-EXPN-PRPN-124");
    }

    private void performUpdateNotes125()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-125");
        addAndDeleteHistory(true,"FM-EXPN-PRPN-125",false);
        
        Alert alt = null;
        
        String testMessage="test"+System.currentTimeMillis();
        try
        {
            alt= alertAPI.checkOut(entity,5);
            alertAPI.updateNotes(alertAPI.getAlert(entity),"saraana",testMessage,true);
            alertAPI.unlock(alt);
        }
        catch(Exception ee)
        {
            if(ee instanceof AccessDeniedException)
            {
                System.out.println(" ***FM-EXPN-PRPN-125*** Successful");
            }
            
            ee.printStackTrace();
            try
            {
                alertAPI.unlock(alt);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }        
        checkForAnnotationResult(testMessage,false,"FM-EXPN-PRPN-125");
    }

    private void performLockTest126()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-126");
        addEvent();
        Alert first = null;

        try
            {
                Thread.sleep(20000);
            
                first = alertAPI.checkOutIfAvailable(entity);

                Alert second = alertAPI.checkOut(entity,10);
                alertAPI.unlock(first);
            }
        catch(Exception ee)
        {
            if(ee instanceof TimeoutException)
            {
                System.out.println(" ***FM-EXPN-PRPN-126*** Successful");
                ee.printStackTrace();
            }
            try
            {
                alertAPI.unlock(first);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    private void performLockTest127()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-127");        
        Alert first = null;

        try
        {
            first = alertAPI.checkOut(entity,5);
            Alert second = alertAPI.checkOutIfAvailable(entity);
            alertAPI.unlock(first);
        }
        catch(Exception ee)
        {
            if(ee instanceof TimeoutException)
            {
                System.out.println(" ***FM-EXPN-PRPN-127*** Successful");
                ee.printStackTrace();
            }
            try
            {
                alertAPI.unlock(first);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void performPurgeEvent128()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-128");
        try
        {
            transAPI.begin(10000);
            Thread.sleep(30000);
            eventAPI.purgeEventDB();
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-128*** Successful");
                
                Event evt = null;
                try
                {
                    transAPI.commit();
                    evt = eventAPI.getEventByID(1);
                    if(evt != null)
                    {
                        System.out.println(" ***FM-EXPN-PRPN-128*** Successful");
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void performPurgeEvent129()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-129");
        try
        {
            transAPI.begin(10000);
            eventAPI.purgeEventDB();
            Thread.sleep(15000);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof RollbackException)
            {
                System.out.println(" ***FM-EXPN-PRPN-129*** Successful");
                
                Event evt = null;
                try
                {
                    evt = eventAPI.getEventByID(1);
                    transAPI.commit();
                    if(evt != null)
                    {
                        System.out.println(" ***FM-EXPN-PRPN-129*** Successful");
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void performPurgeEvent130()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-130");
        try
        {
            System.out.println("Execute: drop table Event within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        
        try
        {
            transAPI.begin(10000);
            eventAPI.purgeEventDB();
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-130*** Successful");
                Event evt = null;
                try
                {
                    transAPI.commit();
                    evt = eventAPI.getEventByID(1);
                    if(evt != null)
                    {
                        System.out.println(" ***FM-EXPN-PRPN-130*** Successful");
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void performPurgeEvent131()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-131");
        try
        {
            System.out.println("Execute: drop table Event within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            eventAPI.purgeEventDB();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-131*** Successful");
                Event evt = null;
                try
                {
                    evt = eventAPI.getEventByID(1);
                    if(evt != null)
                    {
                        System.out.println(" ***FM-EXPN-PRPN-131*** Successful");
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void performPurgeAlert132()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-132");
        try
        {
            transAPI.begin(10000);
            Thread.sleep(15000);
            alertAPI.purgeAlertDB();
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-132*** Successful");
                
                Alert alt = null;
                try
                {
                    transAPI.commit();
                    alt = alertAPI.getAlert(entity);
                    if(alt != null)
                    {
                        System.out.println(" ***FM-EXPN-PRPN-132*** Successful");
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void performPurgeAlert133()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-133");
        addAndDeleteHistory(true,"FM-EXPN-PRPN-133",true);
        try
        {
            transAPI.begin(10000);
            alertAPI.purgeAlertDB();
            Thread.sleep(15000);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof RollbackException)
            {
                System.out.println(" ***FM-EXPN-PRPN-133*** Successful");
                
                Alert alt = null;
                try
                {
                    transAPI.commit();
                    alt = alertAPI.getAlert(entity);
                    if(alt != null)
                    {
                        System.out.println(" Test case PASSED FM-EXPN-PRPN-133");
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void performPurgeAlert134()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-134");
        try
        {
            System.out.println("Execute: drop table Alert within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        
        try
        {
            transAPI.begin(-1);
            alertAPI.purgeAlertDB();
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-134*** Successful");
                
                Alert alt = null;
                try
                {
                    transAPI.commit();
                    alt = alertAPI.getAlert(entity);
                    if(alt != null)
                    {
                        System.out.println(" Test case PASSED FM-EXPN-PRPN-134");
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if(ee instanceof javax.transaction.NotSupportedException)
            {
                System.out.println(" ***FM-EXPN-PRPN-134*** Successful");
            }
        }
    }
    
    private void performPurgeAlert135()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-135");
        try
        {
            System.out.println("Execute: drop table Alert within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            alertAPI.purgeAlertDB();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-135*** Successful");
                
                Alert alt = null;
                try
                {
                    alt = alertAPI.getAlert(entity);
                    if(alt != null)
                    {
                        System.out.println(" Test case PASSED FM-EXPN-PRPN-135");
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


    private void performPurgeAlert136()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-136");
        try
        {
            addAndDeleteHistory(true,"FM-EXPN-PRPN-136",true);
            Alert alt = alertAPI.checkOutIfAvailable(entity);
            if(alt != null)
            {
                System.out.println(" First Alert Checked Out Successfully=============================");
            }
    
            alertAPI.purgeAlertDB();
    
            System.out.println(" AlertDB purged Successfully==================");

            addAndDeleteHistory(true,"FM-EXPN-PRPN-136",true);

            System.out.println(" Event is added =================");
        
            Alert second = alertAPI.checkOutIfAvailable(entity);

            if(second != null)
            {
                System.out.println(" Second Alert Checked Out Successfully=============================");
            }
            System.out.println(" ***FM-EXPN-PRPN-136*** Successful");
            alertAPI.unlock(second);

        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void getAlertsBasedOnGVM137()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-137");
        try
        {
            Vector v = alertAPI.getAlertsBasedOnGroupViewMode();
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }

            transAPI.begin(10000);
        
            Vector second = alertAPI.getAlertsBasedOnGroupViewMode();
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-137*** Successful");
            }
        }
    }

    private void getAlertsBasedOnGVM138()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-138");
        try
        {
            Vector v = alertAPI.getAlertsBasedOnGroupViewMode();
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        
            Vector second = alertAPI.getAlertsBasedOnGroupViewMode();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-138*** Successful");
            }
        }
    }

    private void getNextAlertBasedOnGVM139()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-139");
        try
        {
            Vector v = alertAPI.getAlertsBasedOnGroupViewMode();
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            transAPI.begin(10000);        
            Vector second = alertAPI.getNextAlertBasedOnGroupViewMode("winner");
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-139*** Successful");
            }
        }
    }

    private void getNextAlertBasedOnGVM140()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-140");
        try
        {
            Vector v = alertAPI.getAlertsBasedOnGroupViewMode();
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            Vector second = alertAPI.getNextAlertBasedOnGroupViewMode("winner");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-140*** Successful");
            }
        }
    }

    private void getNextAlertBasedOnModTime141()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-141");
        try
        {
            Vector v = alertAPI.getAlertsBasedOnGroupViewMode();
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            
            transAPI.begin(10000);        
            Alert second = alertAPI.getNextAlertBasedOnModtime("winner");
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-141*** Successful");
            }
        }
    }


    private void getNextAlertBasedOnModTime142()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-142");
        try
        {
            Vector v = alertAPI.getAlertsBasedOnGroupViewMode();
            try
            {
                System.out.println("Execute: drop table Alert within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
                 
            Alert second = alertAPI.getNextAlertBasedOnModtime("winner");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-142*** Successful");
            }
        }
    }

    private void getAlertCount143()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-143");
        try
        {
            System.out.println("Execute: drop table Alert within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            transAPI.begin(10000);        
            int second = alertAPI.getAlertCount("winner",2);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-143*** Successful");
            }
        }
    }

    private void getAlertCount144()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-144");
        try
        {
            System.out.println("Execute: drop table Alert within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            int second = alertAPI.getAlertCount("winner",3);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-144*** Successful");
            }
        }
    }

    private void getAlertCount145()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-145");
        try
        {
            System.out.println("Execute: drop table Alert within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            transAPI.begin(10000);
            
            String[] array= {"winner"};
            
            int second = alertAPI.getAlertCount(array,2);
        
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-145*** Successful");
            }
        }
    }

    private void getAlertCount146()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-146");
        try
        {
            System.out.println("Execute: drop table Alert within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            String[] array= {"winner"};
        
            int second = alertAPI.getAlertCount(array,2);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-146*** Successful");
            }
        }
    }


    private void getTotalAlertCount147()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-147");
        try
        {
            System.out.println("Execute: drop table Alert within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            transAPI.begin(10000);
            int second = alertAPI.getTotalAlertCount();
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-147*** Successful");
            }
        }
    }

    private void getTotalAlertCount148()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-148");
        try
        {
            System.out.println("Execute: drop table Alert within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            int second = alertAPI.getTotalAlertCount();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-148*** Successful");
            }
        }
    }

    private void getTotalAlertCount149()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-149");
        try
        {
            System.out.println("Execute: drop table Alert within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            transAPI.begin(10000);
            int second = alertAPI.getTotalAlertCount("winner");
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-149*** Successful");
            }
        }
    }

    private void getTotalAlertCount150()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-150");
        try
        {
            System.out.println("Execute: drop table Alert within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            int second = alertAPI.getTotalAlertCount("winner");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-150*** Successful");
            }
        }
    }

    private void getTotalAlertCount151()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-151");
        try
        {
            System.out.println("Execute: drop table Alert within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            transAPI.begin(10000);
            String[] array = {"array"};
            int second = alertAPI.getTotalAlertCount(array);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-151*** Successful");
            }
        }
    }

    private void getTotalAlertCount152()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-152");
        try
        {
            System.out.println("Execute: drop table Alert within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            String[] array = {"array"};
            int second = alertAPI.getTotalAlertCount(array);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-152*** Successful");
            }
        }
    }

    private void deleteAlertHistory153()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-153");
        try
        {
            addEvent();
            try
            {
                System.out.println("Execute: drop table ANNOTATION within 20 seconds");
                Thread.sleep(20000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }

            transAPI.begin(10000);
            alertAPI.deleteAlertHistory(entity);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-153*** Successful");
            }
        }
    }

    private void deleteAlertHistory154()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-154");
        try
        {
            System.out.println("Execute: drop table ANNOTATION within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            alertAPI.deleteAlertHistory(entity);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-154*** Successful");
            }
        }
    }

    private void deleteAlertAnnotation155()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-155");
        try
        {
            System.out.println("Execute: drop table ANNOTATION within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            transAPI.begin(10000);
            alertAPI.deleteAlertAnnotation(entity);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-155*** Successful");
            }
        }
    }

    private void deleteAlertAnnotation156()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-156");
        try
        {
            System.out.println("Execute: drop table ANNOTATION within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            alertAPI.deleteAlertAnnotation("entity");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof FaultException)
            {
                System.out.println(" ***FM-EXPN-PRPN-156*** Successful");
            }
        }
    }

    private void purgeAlertDB157()
    {
        System.err.println("--------------------------");
        System.err.println("Executing FM-EXPN-PRPN-157");
        try
        {
            System.out.println("Execute: drop table ALERTUSERPROPS within 20 seconds");
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        try
        {
            alertAPI.purgeAlertDB();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            if(ee instanceof UserTransactionException)
            {
                System.out.println(" ***FM-EXPN-PRPN-157*** Successful");
            }
        }
    }

    private void addEvent()
    {
        try
        {
            Thread.sleep(20000);
            Event evt = new Event();
            evt.setEntity(entity);
            evt.setSource(entity);
            evt.setTime(System.currentTimeMillis());
            evt.setCategory("cate");
            evt.setSeverity(3);
            evt.setText("Time "+System.currentTimeMillis());
            evt.setWebNMS("king");
            eventAPI.addEvent(evt);
        }
        catch(Exception ee)
        {
            System.out.println("Exception while Adding Events ==");
            ee.printStackTrace();
        }
    }

    private void DropAlertTable()
    {
        
    }

    private AlertAnnotation getAnnotation()
    {
        AlertAnnotation ann = new AlertAnnotation();
		ann.setEntity(entity);
		ann.setWho("winner");
		ann.setNotes("This is king's test");
		ann.setModTime(System.currentTimeMillis());
		return ann;
    }

    private boolean checkForResult(String testMessage,boolean deleteAlert,String tcNumber)
    {
        boolean checkOne = false;
        boolean checkTwo = false;
        try
        {
            Alert checkAlert = alertAPI.checkOutIfAvailable(entity);
            alertAPI.unlock(checkAlert);
            if(checkAlert.getMessage() !=testMessage)
            {
                System.out.println(" Transaction Rolled Back in Alert Table for "+tcNumber);
                checkOne = true;
            }
        
        
            Vector annotation = alertAPI.getHistory(entity);
        
            if(annotation == null || annotation.size() ==0)
            {
                System.out.println(" Transaction Rolled Back For Update Alert in ANNOTATION Table "+tcNumber);
                checkTwo = true;
            }
            if(deleteAlert)
            {
                alertAPI.deleteAlert(entity);
                System.out.println(" Alert Deleted For ===============================>>>"+tcNumber); 
            }
            if(checkOne && checkTwo)
            {
                System.out.println(" RESULT FOR TC "+tcNumber+" =============>>>>> PASSED");
                return true;
            }
        }
        catch(Exception ee)
        {
            System.out.println(" Exception while Testing ==");
            ee.printStackTrace();
        }
        return false;
    }

    private boolean checkForAnnotationResult(String testMessage,boolean deleteAlert,String tcNumber)
    {
        boolean checkTwo = false;
        try
        {
                   
            Vector annotation = alertAPI.getAnnotation(entity);
        
            if(annotation == null || annotation.size() ==0)
            {
                System.out.println(" Transaction Rolled Back For Update Alert in ANNOTATION Table "+tcNumber);
                checkTwo = true;
            }
            if(deleteAlert)
            {
                alertAPI.deleteAlert(entity);
                System.out.println(" Alert Deleted For ===============================>>>"+tcNumber); 
            }
            if(checkTwo)
            {
                System.out.println(" RESULT FOR TC "+tcNumber+" =============>>>>> PASSED");
                return true;
            }
        }
        catch(Exception ee)
        {
            System.out.println(" Exception while Testing ==");
            ee.printStackTrace();
        }
        return false;
    }

    private void addAndDeleteHistory(boolean deleteAnnotation,String tcNumber,boolean addEvent)
    {
        if(addEvent)
        {
            addEvent();
        }
        
        try
        {
            Thread.sleep(20000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }

        try
        {
            if(deleteAnnotation)
            {
                alertAPI.deleteAlertHistory(entity);
                System.out.println(" HISTORY FOR THIS ALERT DELETED SUCCESSFULLY "+tcNumber);
            }
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    public boolean isInitialized()
    {
        //System.out.println(" My TestTrapAPI is initialized =============");
        return true;
    }

    public void shutDown()
    {
    }
}



