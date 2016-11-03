//$Id: TrapConfImpl.java,v 1.3.6.1 2012/01/25 05:12:45 karen.r Exp $
package com.adventnet.nms.jmxagent;

import com.adventnet.nms.eventdb.*;
import java.io.*;
import java.util.*;
// User code starts here
import com.adventnet.utils.jmx.*;
//import com.adventnet.common.agent.*;
import com.adventnet.utilities.common.*;

// User code ends here
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.utilities.common.AgentException;
import com.adventnet.utilities.common.CommonUtils;

public class TrapConfImpl
{

    private TrapAPI trapAPI;
    private AdventNet_WebNMS_MIB_JMX agentName = null;

    Hashtable trapFilterTable = new Hashtable();

    TrapConfImpl(AdventNet_WebNMS_MIB_JMX agentRef)
    {
        agentName = agentRef;
        //loadTrapDatas();
    }


    void loadTrapDatas()
    {
        // obtain TrapAPI handle.
        if(trapAPI == null)
            getTrapAPIHandle();
        else
            return;

        if(trapAPI == null)
            return;

        try
            {
                TrapFilterHolder[] trapHolder = trapAPI.getAllTrapFilters();

                //getting all the trap filters in the server
                for(int i = 0; i<trapHolder.length;i++)
                    {

                        TrapFilterEntry trapFilterEntry = new TrapFilterEntry();
                        //getting an instance of the entry file and setting the data to it
                        trapFilterEntry.setTrapFilterIndex(new Integer(++agentName.trapindex));  //index

                        String className = trapHolder[i].getClassName();  //reading the trapfilter holder for the classname
                        if(className != null)
                            {
                                trapFilterEntry.setAlertFilterClassName(className);
                            }

                        String filterName = trapHolder[i].getFilterName(); //filtername
                        if(filterName != null)
                            {
                                trapFilterEntry.setTrapFilterName(filterName);
                            }

                        boolean enable = trapHolder[i].getEnableType(); //enable
                        if(enable == true)
                            trapFilterEntry.setTrapFilterEnable(new Integer(1));
                        else
                            trapFilterEntry.setTrapFilterEnable(new Integer(2));

                        String generic = trapHolder[i].getGenericType(); //gt
                        if(generic != null)
                            {
                                Integer n = new Integer(Integer.parseInt(generic));
                                trapFilterEntry.setGenericType(n.toString());
                            }

                        String specific = trapHolder[i].getSpecificType();//st
                        if(specific != null)
                            {
                                Integer n = new Integer(Integer.parseInt(specific));
                                trapFilterEntry.setSpecificType(n.toString());
                            }

                        String enterprise = trapHolder[i].getEnterpriseOID();//enterpriseoid
                        if(enterprise != null)
                                {
                                    trapFilterEntry.setEnterpriseOID(enterprise);
                                }

                        String trap = trapHolder[i].getTrapOID();//trapoid,if trapoid is not null,then version is v1
                        if(trap != null)
                            {
                                trapFilterEntry.setTrapOID(trap);
                            }

                        //trapFilterEntry.setSetFilter(trapFilterEntry.getSetFilter());
                        Object[] index = new Object[1];
                        index[0] = new Integer(agentName.logindex);
                        trapFilterTable.put(index,trapFilterEntry);

                    }//end of for


            }
        catch(Exception e)
            {
            }

    }//end of void


    // FOR SETTING THE VALUES THRU AGENT INTO THE TRAP FILTERS

    void setTrapDatas(TrapFilterEntry entry) throws AgentException
    {
        // obtain TrapAPI handle.
        if(trapAPI == null)
            {
                getTrapAPIHandle();
                if(trapAPI == null)
                    return;
            }

        TrapFilterHolder  th = new TrapFilterHolder();
        //for each row of the table, a new th is created,so at the last only all the entries
        //given by the user are filled into the th.
        try
            {
                        th.setClassName(entry.getAlertFilterClassName());
                        if(entry.getTrapFilterName().equals(""))
                            th.setFilterName(entry.getAlertFilterClassName());//key
                        else
                            th.setFilterName(entry.getTrapFilterName());

                        if(entry.getTrapFilterEnable().intValue() == 1)
                            th.setEnableType(true);
                        else
                            th.setEnableType(false);

                        //if trapOID IS present then it is a v3 trap
                        if(entry.getTrapOID().equals(""))
                            {
                                String generic = entry.getGenericType();
                                if(!generic.equals(""))
                                    {
                                        th.setGenericType(generic);
                                    }

                                String specific = entry.getSpecificType();
                                if(!specific.equals(""))
                                    {
                                        th.setSpecificType(specific);
                                    }

                                String enterprise = entry.getEnterpriseOID();
                                if(!enterprise.equals(""))
                                    {
                                         th.setEnterpriseOID(enterprise);
                                    }
                            }
                        else
                            {
                                th.setTrapOID(entry.getTrapOID());
                            }


                    }catch(Exception exc)
                        {
                            // agentName.agentErr.fail("exception",exc);//No I18N
                            exc.printStackTrace();
                        }

                try
                    {
                        Class c = Class.forName(entry.getAlertFilterClassName());
                        TrapFilter tf = (TrapFilter)c.newInstance();
                        th.setTrapFilter(tf);
                        trapAPI.setTrapFilter(th,true ) ;
                    }
                catch(Exception ext)
                    {
                        throw new AgentException("error in value",CommonUtils.GENERR);//No I18N
                        // agentName.agentErr.fail("exception ",ext);//No I18N
                        //ext.printStackTrace();
                    }
    }//end of void

    private void getTrapAPIHandle()
    {
        try
            {
                trapAPI = (TrapAPI)NmsUtil.getAPI("TrapAPI");//No I18N
            }
        catch(Exception e)
            {
                //agentName.agentErr.fail("Exception while getting TrapAPI handle in TrapConfImpl file",e);//No I18N
                e.printStackTrace();
            }
    }
}

