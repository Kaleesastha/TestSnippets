/*
	$Id: SnmpQuery.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/
/*
 * @(#)snmpGraph.java	
 * Copyright (c) 1996 Advent Network Management, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 * 
 * ADVENT NETWORK MANAGEMENT, INC. MAKES NO REPRESENTATIONS OR WARRANTIES 
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENT NETWORK 
 * MANAGEMENT, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY 
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE 
 * OR ITS DERIVATIVES.
 */
package com.adventnet.nms.pollui;

import java.math.BigInteger;
import javax.swing.JApplet;

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.beans.*;
import com.adventnet.nms.util.NmsClientUtil;

public class SnmpQuery extends Thread implements ResultListener  {
    
    private ShowValue sv =null; 	 
    boolean firstTime = true;
    SnmpPoller poller = null;
    private boolean isHandledInEvent=false;
    private BigInteger prevValue = null;
    private BigInteger currentValue = null;
    //public static final int DATE_INSTANCE =com.adventnet.beans.graphs.Graph.DATE_INSTANCE;
    /**Constant for date display format . This is used to set the display format of x value.*/
    //public static final int DATE_TIME_INSTANCE =com.adventnet.beans.graphs.Graph.DATE_TIME_INSTANCE;
    /**Constant for date display format . This is used to set the display format of x value.*/
    //public  static final int TIME_INSTANCE = com.adventnet.beans.graphs.Graph.TIME_INSTANCE;
    
  
    public SnmpQuery(String title, SnmpPoller poller,java.util.Vector mulOid)
    {
        panelSettings(poller,title,mulOid);    
    }

   
    private void panelSettings(SnmpPoller poller,String title,java.util.Vector mulOid)
    {
        this.poller = poller;
        sv = new com.adventnet.nms.pollui.ShowValue(this,mulOid,title);
        sv.setSize(500,400);
        poller.addResultListener(this);
        NmsClientUtil.centerWindow(sv);
        sv.setVisible(true);
    }
 
   

    public void run() 
    { // the main thread function
        poller.run();
        
    } // end of run()

   
    public void setNumericResult(long res)
    {
        if(!isHandledInEvent)
        {
            sv.setResult(res);
        }         
    }

    public void setResult(ResultEvent e)
    { 	
        BigInteger currentValue=null;  	
        double res =0;
        SnmpPDU spdu =(SnmpPDU)e.getResponse();
        if(spdu ==null)
            return;	   
        SnmpVarBind bind =spdu.getVariableBinding(0);
        if(bind ==null)
            return;	   
        SnmpVar var = bind.getVariable();
        if(var ==null)
            return;	
        if(var.getType()== SnmpAPI.COUNTER64)
        {
            isHandledInEvent=true;  
            currentValue = ((SnmpCounter64)var).toBigInteger();

            if(!(poller.getAbsoluteCounters()))
            {
                if(firstTime)
                {
                    firstTime=false;
                }
                else if(prevValue != currentValue)
                {
                    Double diff = findCounter64Diff(currentValue,prevValue);
                    if(diff != null)
                    {
                        res = diff.doubleValue();
                    }
                    else
                    {
                        return;
                    }
                }
                else
                {
                    res = 0;
                }
                prevValue = currentValue;
            }
            else
            {
                res=currentValue.doubleValue();     
            }
        }
        else if(var.getType()== SnmpAPI.TIMETICKS)
        {   
            isHandledInEvent=true;  
            res = (double)(((SnmpTimeticks)var).longValue());
        }
        else
        {
            return;
        }

        if(sv!=null)
        {
            sv.setResult(res);
        }
	
    } 

    // holds the max long value 2^63-1
    private long negatelastBit = 0X7FFFFFFFFFFFFFFFL;
    // holds the max Counter64 value 2^64-1
    private double maxC64Value = 18446744073709551615d;

    /* This method is calculates the difference between two consecutive values
     * for a Counter64 type OID. This calculation is not provided by poller. Once
     * Counter64 type OID value reaches maximum(2^64-1), it wraps to zero. When this
     * occurs the difference is calculated using formula
     * (currentValue + maximum value possible) - previousValue + 1
     */
    private Double findCounter64Diff(BigInteger currentValue,BigInteger prevValue)
    {
        double counter64diff = (currentValue.subtract(prevValue)).doubleValue();

        if (counter64diff <0)
        {
            BigInteger temp = currentValue.add(getMax(negatelastBit));
            counter64diff = ((temp.subtract(prevValue)).doubleValue())+1;
        }

        if (counter64diff < 0 || counter64diff > maxC64Value)
            counter64diff = 0;
        
        return new Double(counter64diff);
    }

    /* This method calculates the max value a Counter64 OID can take, which is 2^64-1. 
     * Max value is calculated using the following logic
     * variable bg1 ---> : 2^63-1 (max value for long)      (in decimal format)
     *                   : 011111111111111111111111111111111(in binary format)
     * variable bg2 ---> : 100000000000000000000000000000000(in binary format)
     * bg1.or(bg2)  ---> : 111111111111111111111111111111111(in binary format)
     *                   : 2^64-1 (max value for Counter64) (in decimal format)
     */ 
    private BigInteger getMax(long bit)
    {
        BigInteger bg1 = BigInteger.valueOf(bit);
        BigInteger bg2 = BigInteger.valueOf(1).shiftLeft(63);
        BigInteger maxbg = bg1.or(bg2);
        return maxbg;
    }
	
    public String getObjectID()
    {
        return	poller.getObjectID();
    }
    public void  setObjectID(String oid)
    {
        poller.setObjectID(oid);
    }
    public void setStringResult(String s)
    {
    }
    /*	public void setResult(ResultEvent e)
	{
        SnmpPDU spdu =(SnmpPDU)e.getResponse();
        SnmpVarBind bind =spdu.getVariableBinding(0);
        SnmpVar var = bind.getVariable();
        long res=0;	
        if(var.getType()== SnmpAPI.COUNTER64)
        {
        isCounter64=true;  
             		   
        BigInteger value = ((SnmpCounter64)var).toBigInteger();		 
        res = value.longValue();	
        sv.setResult(res);	
	}
	}*/
    public void stopPoll()
    {
        poller.stopPolling();
    }
    public void restartPoll()
    { 
        poller.restartPolling();
    }
    public void close()
    {
        sv.dispose();
        poller.stopPolling();
    }
    public void setPollInterval(long period)
    {
        poller.setPollInterval((int)period);	
    }	
    
}
