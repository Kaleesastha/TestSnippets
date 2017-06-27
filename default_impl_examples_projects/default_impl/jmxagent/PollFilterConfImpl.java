//$Id: PollFilterConfImpl.java,v 1.3.6.1 2012/01/25 05:12:45 karen.r Exp $
package com.adventnet.nms.jmxagent;

import com.adventnet.nms.util.*;

import com.adventnet.nms.poll.Collector;
import com.adventnet.nms.poll.PollFilter;

import java.io.*;
import java.util.*;

public class PollFilterConfImpl
{

    Hashtable pollFilterTable = new Hashtable();

    private AdventNet_WebNMS_MIB_JMX agentName = null;

    PollFilterConfImpl(AdventNet_WebNMS_MIB_JMX agentRef)
    {
        agentName = agentRef;
	//loadPollFIlters();
    }

    boolean loaded = false;

    /*
     * Initializing all the Poll filters
     */
    void loadPollFIlters()
    {
	if(!agentName.initPoll() || loaded)
	    return;

	try
	{
	    /*
	     * Getting the vector of all the poll filter details
	     */
	    Vector pollFilterVector = Collector.pollmgr.getFilterList();

	    for(int i = 0 ; i < pollFilterVector.size(); i++)
	    {
		PollFilter filter = (PollFilter)pollFilterVector.elementAt(i);

		PollingFiltersEntry pollingFiltersEntry = new PollingFiltersEntry();

		pollingFiltersEntry.setPollingFilterIndex(new Integer(i+1));
		pollingFiltersEntry.setPollingFilterClassName(filter.getClass().getName());

		Object[] index = new Object[1];
		index[0] = new Integer(i+1);
		pollFilterTable.put(index,pollingFiltersEntry);
	    }
	    loaded = true;
	}
	catch(Exception e){}
    }
}



