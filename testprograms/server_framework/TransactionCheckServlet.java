// $Id: TransactionCheckServlet.java,v 1.2 2001/08/03 11:02:57 subramani Exp $

package test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import com.adventnet.nms.util.*;
import com.adventnet.management.transaction.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.poll.*;
import java.sql.*;
import java.io.*;

public class TransactionCheckServlet extends HttpServlet {

    PrintWriter out = null;
    static int i = 1000;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		response.setContentType("text/html");
		out.println("<HTML>");
		out.println("<BODY bgColor=white>");
		out.println("<BR>");
		out.println("<BR>");
		out.println("<CENTER>");
		out.println("<FONT size=6 color=green>");
		out.println("Successfully connected to the server !!");
                if(request.getParameter("ID") != null)
                {
                    i = Integer.parseInt(request.getParameter("ID"));
                }
                try
                {
                    RelationalAPI relapi = (RelationalAPI) NmsUtil.relapi;
                    TransactionAPI tranapi=relapi.getTransactionAPI();
                    i++;

                    // TOPO
                    {
                        String name = "testobject"+i;
  
                        TopoAPI topoApi = (TopoAPI)NmsUtil.getAPI("TopoAPI");

  
                        //Start transaction:
                        tranapi.begin();
  
                        ManagedObject amo=new ManagedObject();
                        amo.setName(name);
                        topoApi.addObject(amo);
  
                        //Check if object exists in database:
                        if(topoApi.getByName(name)==null) trace("ManagedObject "+name+" is not visible  yet!");
  
                        //Commit transaction:
                        tranapi.commit();
  
                        //Check if object exists in database now:
                        if(topoApi.getByName(name)!=null) trace("ManagedObject "+name+" is visible now!");
                    }
                    // Event
                    {
                        ResultSet rs = NmsUtil.relapi.query("select max(id) from Event");
                        while(rs.next())
                        {
                            i = rs.getInt(1);
                        }
                        //i++;
                        String name = "event" + i;
                        tranapi.begin();
                        AlertAPI alertapi = (AlertAPI)NmsUtil.getAPI("AlertAPI");
                        EventAPI api = (EventAPI)NmsUtil.getAPI("EventAPI");


                        Event event = new Event();
                        event.setId(i);
                        event.setEntity(name);
                        event.setSource(name);
                        event.setSeverity(3);
                        
                        api.addEvent(event);
                        if(api.getEventByID(i) == null)
                        {
                            trace("Event " + name + " not visible now ");
                        }
                        
                        // wait for 3 seconds for event to be converted into Alert
                        Thread.sleep(3000);
                        if(alertapi.getAlert(name) == null)
                        {
                            trace("Alert with the entity " + name + " not visible now ");
                        }

                        tranapi.commit();

                        if(api.getEventByID(i) != null)
                        {
                            trace("Event " + name + " visible now ");
                        }

                        if(alertapi.getAlert(name) != null)
                        {
                            trace("Alert with the entity " + name + " visible now ");
                        }
                    }                        
                    

                    // MAP
                    {
                        String name = "testobject"+i;
  
                        MapAPI mapapi = (MapAPI)NmsUtil.getAPI("MapAPI");

  
                        //Start transaction:
                        tranapi.begin();
                        name ="testmap_"+i+".netmap";
                        mapapi.addMap(name,null);
                        
                        //Check if object exists in database:
                        if(mapapi.getMap(name)==null) trace("MAP "+name+" is not visible  yet!");
  
                        //Commit transaction:
                        tranapi.commit();
  
                        //Check if object exists in database now:
                        if(mapapi.getMap(name)!=null) trace("MAP "+name+" is visible now!");
                    }

                    // PolledData
                    {
                        String name = "polleddata"+i;
                        String agent = "agent";
                        String oid = "4.4.0";
                        String key = name+"\t"+agent+"\t"+oid;
                        PollAPI pollapi = (PollAPI)NmsUtil.getAPI("PollAPI");

                        PolledData pd = new PolledData();
                        pd.setName(name);
                        pd.setAgent(agent);
                        pd.setOid(oid);
                        //Start transaction:
                        tranapi.begin();
                        pollapi.addPoll(pd);
                        
                        //Check if object exists in database:
                        if(pollapi.getPolledData(key)==null) trace("PolledData "+key+" is not visible  yet!");
  
                        //Commit transaction:
                        tranapi.commit();
  
                        //Check if object exists in database now:
                        if(pollapi.getPolledData(key)!=null) trace("PolledData "+key+" is visible now!");
                    }


                }catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    out.println("</FONT>");
                    out.println("</CENTER>");
                    out.println("</BODY>");
                    out.println("</HTML>");
                    
                }

	}
    private void trace(String str)
    {
        System.out.println(str);
        
        out.println("<BR>"+str);

    }

}
