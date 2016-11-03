/*
  $Id: StatisticsForTable.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ 
*/
// Statistics.java

package com.adventnet.nms.pollui;

import java.util.Vector;
import java.util.Properties;
import com.adventnet.nms.util.NmsClientUtil;
import javax.swing.*;

/** This is a program that plots the graph in a frame for data
	 collected from for the Polled Data(PD).
*/


public class StatisticsForTable 
{
	
    private SnmpCollectedData scd = null;
    //  private long times[];
    //private long vals[];
    private int xinterval = 10;
    private boolean dontListen = false;
    private SnmpCollectedTable scr;
    private Vector instanceVector,data;
    private String dummyKeyer =null;
    int dataLength = 0;
    /** This is OLD constructor and this is used by 
     * WebNMSHome/default_impl/client_server_communication/TestClient.java. 
     * This constructed will be removed in our next release WebNMS3.0.
     * @deprecated  As of WebNMS2.3, 
     * @see #Statistics(Properties selectedRow,String title)
     */
     public StatisticsForTable(String keyer, String title,javax.swing.JApplet applet,String multiple)
    {
        dummyKeyer = keyer;
        Properties p = new Properties();
        p.put("name",converter());
        p.put("agent",converter());
        p.put("oid",converter());
        if(dummyKeyer != null)
            p.put("ownerName",converter());
        p.put("isMultiplePolledData",multiple);
        commonMethod(p,title);
    }
    
    private String converter()
    {
        int endIndex = dummyKeyer.indexOf("__tab__");
        if(endIndex != -1)
        {
            String str=  dummyKeyer.substring(0,endIndex);
            dummyKeyer = dummyKeyer.substring(endIndex+7);
            return str;
        }
        else
        {   
            String str = dummyKeyer;
            dummyKeyer = null;
            return str;
        }
    }
    /**
     * Here SnmpColletedData object is created and graph is created 
     * based on the data collected.
     * @param selectedRow  properties of selected Polling Unit
     * @param title
    */ 
    public StatisticsForTable(Properties selectedRow,String title)
    {
        commonMethod(selectedRow,title);
    }

    private void commonMethod(Properties selectedRow,String title)
    {
        scd = null;     
        /** If there is no data for Polled Data TL1CollectedData constructor
         * will throw NullPoiterException and only multiple instance PollingUnit.
         * For single Instance Polling Unit it will throw NullPointerException
         * only in getData method.
         */
	 long l2=0;
	 long l=0;
	 l= System.currentTimeMillis();
         l2= l-(24*60*60*1000);	 
	 String fromDate = NmsClientUtil.formatDate(l2);		
	 String toDate = NmsClientUtil.formatDate(l); 
        try
        {
           scd = new SnmpCollectedData((String)selectedRow.get("name"),(String)selectedRow.get("agent"), (String)selectedRow.get("oid"), (String)selectedRow.get("ownerName"),(String)selectedRow.get("isMultiplePolledData"));
        }
        catch(NullPointerException e)
        {
	 int re = JOptionPane.showConfirmDialog(NmsClientUtil.getParentFrame(), NmsClientUtil.GetString("No Data is collected from ") + fromDate+NmsClientUtil.GetString(" to ")+toDate+"." + "\n" +"         "+NmsClientUtil.GetString(" Do you want to plot Graph for some other Date"), NmsClientUtil.GetString("Confirm"),JOptionPane.ERROR_MESSAGE);
           if(re==0)
	   {
            scr = new SnmpCollectedTable(title,Boolean.valueOf((String)selectedRow.get("isMultiplePolledData")).booleanValue(),this,l2,l);
            scr.setUIVisible();
	    return;
	   }
	   else
	   {
		   return;
           }
            //NmsClientUtil.err(NmsClientUtil.GetString("Data is not yet collected for the selected Polling Unit. Please try after some time."));
            //return;
        }
        /** If Argument <b>isMultiplePolledData</b>  for the API is anyother string
         * rather than <i>true</i> or <i>false</i> constructor will throw 
         * <b>IllegalArgumentException</b>.
         */
        catch(IllegalArgumentException iae)
        {
           System.err.println(NmsClientUtil.GetString("Wrong argument is given for the API."));
          return;
        }

        instanceVector = scd.getInstanceVector();
       
        /** In case of single instance PollingUnit getData
         *  method may throw <b>NullPointerException</b> when
         * data is not yet collected for the Polling Unit in DB.
         */
	
	 data=null;
        try
        {
        
        if(instanceVector  != null)
            data = scd.getData((String)instanceVector.elementAt(0),l2,l);
        else
            data = scd.getData(null,l2,l);
        }
        catch(NullPointerException e)
        {
	   
	   int re = JOptionPane.showConfirmDialog(NmsClientUtil.getParentFrame(), NmsClientUtil.GetString("No Data is collected from ") + fromDate+NmsClientUtil.GetString(" to ")+toDate+"." + "\n" +"         "+NmsClientUtil.GetString(" Do you want to get Data for some other Date ?"), NmsClientUtil.GetString("Confirm"),JOptionPane.ERROR_MESSAGE);
           if(re==0)
	   {
             scr = new SnmpCollectedTable(title,scd.isMultipleInstance(),this,l2,l);
             scr.setUIVisible();
	     return;
	   }
	   else
	   {
		   return;
           }	
	     /*long l3=0;	
	     int count=1;
	     while(count<9 && data ==null)
	      {
	        try
		 {
		  count++;
         	  l3= l2-(24*60*60*1000);
            	  data = scd.getData(null,l3,l2);
		  l2=l3;
		 }
		 catch(Exception ex)
		 {
		  l2=l3;	 
		  if(count==8)
		   {	  
                    NmsClientUtil.err(NmsClientUtil.GetString("Data is not yet collected."));
                    return;
		   }
		 }
	       } 	     
            NmsClientUtil.err(NmsClientUtil.GetString("Data is not yet collected.")+e);
            return;*/
        }
        
        
        long times[] =null;
	times= (long[])data.elementAt(0);
        Object[] ob = (Object[])data.elementAt(1);
        /** If the received data contains Long type data 
         *  it will plotted in the UI. 
         *  If the received data contains String type data 
         *  it will throw a message and ends.
         */
        //if(ob[0] instanceof Long)
        //{
             dataLength = ob.length;
            //long vals[]= new long[dataLength];
	    /*long maxY=0;
            for(int i =0; i<dataLength ;i++)
	    {
	    	long val=((Long)ob[i]).longValue();
                vals[i]=val;
                if( val > maxY)
                    maxY= val;
            }*/		    
            scr = new SnmpCollectedTable(title,scd.isMultipleInstance(),this,times[0],times[times.length-1]);
            scr.setUIVisible();
            plotOnInterval(times,ob);
        //}
        /*else if( ob[0] instanceof String)
        {
            NmsClientUtil.err(NmsClientUtil.GetString("Cannot plot graph for String values"));
        }*/
    }
    

    Vector getInstances()
    {
        return instanceVector;
    }
    

    /** This method will be called from UI whenever Instance or Fetch Button is clicked
     *  in UI. This method gets corresponding data and then passes the data to plotTheGraph 
     *  method of UI
     */

  void fetchData(String instance,long startTime, long endTime) 
    {
        try
         {
             data = scd.getData(instance, startTime, endTime);
         }
        catch(Exception e)
        {
             NmsClientUtil.err(NmsClientUtil.GetString("Cannot plot data for the given date"));
             return ;
        }
        long times[] = (long[])data.elementAt(0);
        Object[] ob = (Object[])data.elementAt(1);
        dataLength  = ob.length;
        plotOnInterval(times,ob);
       
    }
    /** This method divides the data each of 10 units 
        and passes each unit separately to the UI class.
        In UI each type data is received the graph size is increased
        and scrollpane holds it. This makes a effect such that scrollpane increment
        corresponding to data length.
    */
    private void plotOnInterval(long[] times, Object[] vals)
    {
        scr.showTable(times,vals);
/*        int k= times.length/xinterval;
        if(k == 0 || ((times.length%xinterval) >0))
           k=k+1;
           for(int i= 1; i<k+1 ;i++)
           {
               if( i == k)
               {
                   if(i==1)
                       scr.plotTheGraph(times,vals,true);
                   else
                       scr.plotTheGraph(times,vals,false);
                   break;
               }
               else
               {
                   long[] time = new long[xinterval*i];
                   long[] value = new long[xinterval*i];
                   for(int j = 0 ; j <xinterval*i ; j++)
                   {
                       time[j] = times[j];
                       value[j]= vals[j];                   
                   }
                   if(i==1)
                       scr.plotTheGraph(time,value,true);
                   else
                       scr.plotTheGraph(time,value,false);
               }
           }*/
    }
}

 










