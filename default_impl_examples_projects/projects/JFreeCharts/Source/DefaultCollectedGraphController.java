/*$Id: DefaultCollectedGraphController.java,v 1.1.6.1 2012/04/05 08:27:52 wesley Exp $*/



// import java classes

package com.adventnet.nms.poll.graphs;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

// import AdventNet classes
import com.adventnet.nms.poll.CollectedData;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.pollui.SnmpCollectedData;

public class DefaultCollectedGraphController implements CollectedGraphController
{
    Properties selectedPD = new Properties();
    Vector instanceVector = new Vector();
    Hashtable collectedData = new Hashtable();
    boolean islong = false;
    boolean isString = false;
    boolean isMultiple = false;
    boolean isDecimal = false;
    CollectedGraphViewer viewer = null;
    private SnmpCollectedData scd = null;

    public DefaultCollectedGraphController(Properties selectedPD)
    {
        this.selectedPD = selectedPD;
        isMultiple = (Boolean.valueOf(selectedPD.getProperty("isMultiplePolledData"))).booleanValue();
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
                            
                            if (type == 1)                    
                            {
                                islong = true;
                            }
                            else if (type == 2)                    
                            {
                                isString = true;
                            }
                            else if(type == 3)
                            {
                            	isDecimal = true;
                            }
                            	
                            firstTime = false;
                        }
                        collectedData.put(instance, cd);
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
            viewer.plotStrTypeData(collectedData);
        }
        else
        {
            plotData();            
        }
        viewer.setUIVisible();
    }

    private void initializeViewer()
    {
        viewer = new CollectedGraphViewer();
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
            Vector inst = scd.getInstanceVector();
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
        else if(Val[0] instanceof Double)
        {
            double [] lVals = new double[count];
            for(int i=0;i<count;i++)
            {
                lVals[i] = ((Double)Val[i]).doubleValue();
            }
            tempData.add(lVals);            
        }
        else
        {
            return null;
        }

        CollectedData c = new CollectedData(tempData);

        return(new CollectedData(tempData));
    }

    public void plotData()
    {
        if(collectedData!=null && collectedData.size()>0)
        {
            viewer.plotData(collectedData);
        }
        else
        {
            viewer.setDataNA();
        }
    }
    
    public void fetchData(Vector instances, long startTime, long endTime) 
    {
        if (instances == null)
            instances = instanceVector;

        if (instances==null || instances.size()==0)
        {
            getInstancesForPD();
        }
        
        if (instances != null && instances.size() > 0)
        {
            collectedData.clear();
            
            for (Enumeration e = instances.elements(); e.hasMoreElements();)
            {
                String instance = (String)e.nextElement();
                CollectedData cd = getData(instance, startTime, endTime);
                
                if (cd == null)
                    continue;
                collectedData.put(instance, cd);
                
            }
            if (isString)
            {
                viewer.plotStrTypeData(collectedData);
            }
            else
            {
                plotData();
            }
        }
    }

}










