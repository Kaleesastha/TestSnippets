/*$Id: JVMCollectedGraphController.java,v 1.3 2007/07/31 14:24:52 sumitha Exp $*/

package com.adventnet.nms.poll.graphs;

// import java classes
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

// import AdventNet classes
import com.adventnet.nms.poll.CollectedData;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.pollui.SnmpCollectedData;

public class JVMCollectedGraphController implements JVMGraphController
{
    Properties selectedPD = new Properties();
    Vector instanceVector = new Vector();
    Hashtable cData = new Hashtable();
    boolean isString = false;
    boolean isMultiple = false;
    JVMDetailsViewer viewer = null;
    private SnmpCollectedData scd = null;

    public JVMCollectedGraphController(Properties selectedPD)
    {
        this.selectedPD = selectedPD;
        isMultiple = false;
        initializeViewer();
        getInstancesForPD();
        long endTime = System.currentTimeMillis();
        long startTime = endTime - (24*60*60*1000);

        if (instanceVector != null && instanceVector.size() > 0)
        {
            boolean firstTime = true;
            
            for (Enumeration e = instanceVector.elements(); e.hasMoreElements();)
            {
                String instance = (String)e.nextElement();
                try
                {
                    CollectedData cd = getData(instance, startTime, endTime);
                    if (cd != null)
                    {                
                        if (firstTime)
                        {
                            int type = cd.getType();
                            
                            if (type != 1)                    
                            {
                                isString = true;
                            }
                            firstTime = false;
                        }
                        cData.put(instance, cd);
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        if(isString)
        {
            viewer.plotStrTypeData(cData);
        }
        else
        {
            plotData();            
        }
        viewer.setUIVisible();
    }

    private void initializeViewer()
    {
        viewer = new JVMDetailsViewer();
        viewer.setSelectedPData(selectedPD.getProperty("agent")+"_"+selectedPD.getProperty("name"));
        viewer.setController(this);
        Properties props = new Properties();
        String title = selectedPD.getProperty("agent");
        String subTitle = selectedPD.getProperty("name");
        props.put("title",title);
        props.put("subTitle",subTitle);
        props.put("xLabel","Time of Collection");
        String yAxisLabel = selectedPD.getProperty("yAxisLabel");
        if(yAxisLabel != null)
        {
            props.put("yLabel",yAxisLabel);
        }
        else
        {
            props.put("yLabel",selectedPD.getProperty("oid"));
        }
       viewer.setChartProperties(props);
    }

    public void getInstancesForPD()
    {
        try
        {
            scd = new SnmpCollectedData(selectedPD.getProperty("name"), selectedPD.getProperty("agent"), selectedPD.getProperty("oid"), selectedPD.getProperty("ownerName"), selectedPD.getProperty("isMultiplePolledData"));
        }
        catch(NullPointerException e)
        {
            return;
        }
        catch(IllegalArgumentException iae)
        {
            System.err.println(NmsClientUtil.GetString("Wrong argument is given for the API."));
            return;
        }
        if(scd != null)
        {
            Vector inst = new Vector();
            instanceVector = new Vector();
            inst = scd.getInstanceVector();
            if(inst!=null)
            {
                instanceVector = inst;
            }
            else 
            {
                if(!isMultiple)
                {
                    instanceVector.add("-1");
                }
            }
        }
    }

    public CollectedData getData(String instance, long sTime, long eTime)
    {
        if(scd==null)
            return null;
        Vector data = null;
        try
        {
            data = scd.getData(instance,sTime,eTime);
            if(data==null || data.size()<=0)
            {
                return null;
            }
        }
        catch(Exception e)
        {
            return null;
        }

        Vector tempData = new Vector();
        tempData.add(data.elementAt(0));
        Object[] Val = (Object[])data.elementAt(1);
        int count = Val.length;
        if(Val[0] instanceof Long)
        {
            long [] lVals = new long[count];
            for(int i=0;i<count;i++)
            {
                lVals[i] = ((Long)Val[i]).longValue();
            }
            tempData.add(lVals);            
        }
        else if(Val[0] instanceof String)
        {
            String [] strValues = new String[count];
            for(int i=0;i<count;i++)
            {
                strValues[i] = (String)Val[i];
            }
            tempData.add(strValues);
        }
        else
        {
            return null;
        }

        //CollectedData c = new CollectedData(tempData);
        return(new CollectedData(tempData));
    }

    public void plotData()
    {
        if(cData!=null && cData.size()>0)
        {
            viewer.plotData(cData);
        }
        else
        {
            viewer.setDataNA();
        }
    }
    
public synchronized void fetchData(String pDataName, long startTime, long endTime) 
{
     	this.selectedPD = (Properties)JVMGraphInvoker.pDataNameVsDetails.get(pDataName);
     	Properties tempProps = new Properties();
     	String title = selectedPD.getProperty("agent");
       	String subTitle = selectedPD.getProperty("name");
	tempProps.put("title",title);
	tempProps.put("subTitle",subTitle);
	tempProps.put("xLabel","Time of Collection");
	String yAxisLabel = selectedPD.getProperty("yAxisLabel");
	if(yAxisLabel != null)
	{
		tempProps.put("yLabel",yAxisLabel);
	}
	else
	{
	     		tempProps.put("yLabel",selectedPD.getProperty("oid"));
	}
	viewer.setChartProperties(tempProps);

	getInstancesForPD();       
	cData.clear();
        for (Enumeration e = instanceVector.elements(); e.hasMoreElements();)
        {
            String instance = (String)e.nextElement();
            try
            {
                CollectedData cd = getData(instance, startTime, endTime);
		if(cd != null && (!cd.equals("")))
		{
		    cData.put("-1", cd);
		    if (isString)
		    {
			viewer.plotStrTypeData(cData);
		    }
		    else
		    {
			plotData();
		    }
	        }
		
	    }
	    catch(Exception ex)
            {
                ex.printStackTrace();
            }

	 }
       }

}













