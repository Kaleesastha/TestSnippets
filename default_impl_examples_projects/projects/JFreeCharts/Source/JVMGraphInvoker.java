//$Id: JVMGraphInvoker.java,v 1.2 2007/01/24 10:01:55 gnanasekar Exp $
package com.adventnet.nms.poll.graphs;

import java.util.*;
import javax.swing.JOptionPane;
import com.adventnet.nms.util.CustomClassInterface;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.NmsUiAPI;
import com.adventnet.nms.poll.*;
import com.adventnet.nms.pollui.StatsAdminPanel;

public class JVMGraphInvoker implements CustomClassInterface
{
     public static Hashtable pDataNameVsDetails = null;
     Properties pDataProps = null;

    public void setProperties(Properties[] p)
    {          
    	
	try 
	{
	     	Properties criteria = new Properties();
	     	criteria.put("name","JVMPD*");
                Vector pollVec =  null;

                try
                {
                    pollVec =  StatsAdminPanel.pollclient.getPolledDataWithProps(criteria);
                }
                catch(NullPointerException npe)
                {
                    JOptionPane.showMessageDialog(NmsClientUtil.getParentFrame(),NmsClientUtil.GetString("javaui.jvmgraph.error.enablepollclient"),NmsClientUtil.GetString("javaui.jvmgraph.error.title"),JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(pollVec == null)
                {
                    JOptionPane.showMessageDialog(NmsClientUtil.getParentFrame(),NmsClientUtil.GetString("javaui.jvmgraph.error.enableperformance"),NmsClientUtil.GetString("javaui.jvmgraph.error.title"),JOptionPane.ERROR_MESSAGE);
                }
                else if(pollVec.isEmpty())
                {
                    JOptionPane.showMessageDialog(NmsClientUtil.getParentFrame(),NmsClientUtil.GetString("javaui.jvmgraph.error.nojvmpdata"),NmsClientUtil.GetString("javaui.jvmgraph.error.title"),JOptionPane.ERROR_MESSAGE);
                }
                else
                {
		String pDataName = null;
		pDataNameVsDetails = new Hashtable();
		for(int i=0;i<pollVec.size();i++)
		{    	
			pDataName = (String)pollVec.elementAt(i);			
			StringTokenizer st = new StringTokenizer(pDataName,"\t");
			String name = st.nextToken();
			String agent  = st.nextToken();
			String oid       = st.nextToken();
			pDataProps = new Properties();
			pDataProps.setProperty("name",name);				 				
			pDataProps.setProperty("agent",agent);
			pDataProps.setProperty("oid",oid);
			//pDataProps.setProperty("ownerName","JVMPD");
			pDataProps.setProperty("isMultiplePolledData","false");
			if ((name.endsWith("MonitorCPU"))||(name.endsWith("MonitorBandwidth")))
			{
				pDataProps.setProperty("isMultiplePolledData","true");
			}
			pDataNameVsDetails.put(agent+"_"+name,pDataProps);
		}	     
		JVMCollectedGraphController cgc = new JVMCollectedGraphController(pDataProps);
                }
	} 
	catch (Exception ex) 
	{
		ex.printStackTrace();
	}
    }
}





