/*
$Id: FetchPollData.java,v 1.3.4.1 2012/04/05 08:33:03 wesley Exp $
*/

package com.adventnet.nms.servlets;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;

import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.poll.*;


import com.adventnet.management.log.Log;

public class FetchPollData extends HttpServlet 
{
	
    /**Provides Information string.
     */
	public String getServletInfo() 
    {
        return "This servlet returns an applet tag for poll data graphs";
    }
    /**
     * Handle POST the same as GET.
     * This method is simply a call to doGet().
     *
     * @param req encapsulates the request to the servlet
     * @param res encapsulates the response from the servlet
     * @see javax.servlet.http.HttpServletRequest#getPathTranslated
     * @exception ServletException will be passed on from included servlets
     * @see #doGet
     */
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {
        doGet(req, res);
    }
    
	/**doGet() is the overridden method of HttpServlet.
	* 
	* @param req HttpServletRequest object. 
	* @param res HttpServletResponse object.
	* 
	* @return void.
	* 
	* @exception IOException produced by failed or interrupted I/O operations.
	*/

	public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException 
	{   
        Hashtable ht = new Hashtable();
        MultiplePolledData mpd = null;
        PolledData pd =null;
        PollAPI obj;
        res.setStatus( HttpServletResponse.SC_OK );
        res.setContentType( "text/html" );
        for(Enumeration parameterNames = req.getParameterNames();
                parameterNames.hasMoreElements();)
        {
            String param = (String)parameterNames.nextElement();
            String value = (String)req.getParameter(param);

            if (value == null) value = "-";

            ht.put(param,value);
        }

        String key =  (String) ht.get("PDATA");
        if (key == null) 
        {
            errorPage("Parameter name not specified ", req,res);
            return;
        }

        String pollkey = GenericUtility.replace(key, "__tab__", "\t");   //No I18N 
        //added by lifei
        pollkey = new String(pollkey.getBytes("8859_1"), "UTF8"); //No I18N

        try
        {
            pollkey = URLDecoder.decode(pollkey);
        }
        catch(Exception e)
        {
            System.err.println(" Exception in decoding :"+e);
        }

        String multiple = (String) ht.get("MULTIPLE");
        boolean b =false;
        try {
            if(multiple.equals("true")){
                obj = GenericUtility.getPollAPI();
                PolledData pd1 = obj.getPolledData(pollkey);
                b = pd1.getIsMultiplePolledData();
                if(b) {
                    mpd = (MultiplePolledData)pd1;
                }
                else
                    pd = pd1;
            }
            else{
                obj = GenericUtility.getPollAPI();
                pd = obj.getPolledData(pollkey);
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            NmsLogMgr.MISCUSER.log("Remote exception: " + e.getMessage(),Log.SUMMARY);
            NmsLogMgr.MISCERR.fail("",e);
            errorPage("Error getting Poll access.", req,res);
            return;
        }
        if ((pd == null) && (mpd == null))
        {
            com.adventnet.nms.commonfe.GenericFEAPIImpl.log("FetchPollData Error getting PolledData: "+pollkey);
            errorPage("FetchPollData Error getting PolledData: "+pollkey , req,res);
            return;
        }
        String timeStart = (String) ht.get("STARTTIME");
        String timeEnd = (String) ht.get("ENDTIME");
        long startTime=0, endTime=0;
        if(timeStart !=null  && timeEnd !=null)
        {
            try
            {
                startTime = Long.parseLong(timeStart);
            }
            catch(NumberFormatException e)
            {

                System.err.println("Error while parsing start Time in FetchPolledData: : "+e.getMessage());
            }
            try
            {
                endTime = Long.parseLong(timeEnd);
            }
            catch(NumberFormatException e)
            {
                /* Print the error message properly */
                System.err.println("Error while parsing end Time in FetchPolledData: "+e.getMessage());
            }
            if(startTime == 0 || endTime == 0)
            {
                startTime = System.currentTimeMillis()-(24*60*60*1000);
                endTime = System.currentTimeMillis();
            }
        }
        Vector values = null;
        CollectedData colval=null;        
        try {
            String sindex = (String)ht.get("INDEX");
            //int index = Integer.parseInt(sindex);
            if((sindex.equals("-2")) && (multiple.equals("true")))
            {
                values = obj.getInstances(mpd);
                PrintWriter p1 = res.getWriter();
                if (ht == null) p1.println( " Error getting Parameters.");
                else 
                {
                    String path = req.getPathTranslated();
                    String name = path;	    
                    if(values != null && values.size() != 0)
                    { 
                        p1.println("OKOKOK");
                        for(int i=0;i<values.size();i++)
                        {       
                            String str = (String)values.elementAt(i);
                            p1.println(str + " ");
                        }
                    } 
                    p1.flush();
                    p1.close();
                }
            }
            else if(multiple.equals("true"))
            {
                colval = obj.getCollectedData(sindex,pollkey,startTime,endTime);
            }
            else
            {
                colval = obj.getCollectedValues(pollkey,startTime,endTime);
            }
        }
        catch (Exception ee ) 
        {
            ee.printStackTrace();
        }

        if (colval == null) 
        {
            errorPage("FetchPollData Error getting collected data: "+pollkey , req,res);
            return;
        }

        PrintWriter p = res.getWriter();
        if (ht == null) p.println( " Error getting Parameters.");
        else 
        {
            String path = req.getPathTranslated();
            String name = path;	    
            if(colval!=null)
            { 
                p.println("OKOKOK");
                Long time[] = (Long[])colval.getTimes();
                int type=colval.getType();
                if(type==1)
                {
                    p.println("LONG");
                    Long val[] = (Long[])colval.getValues();
                    for(int i=0;i<time.length;i++)
                    {       
                        p.println(time[i].toString() + " " + val[i].toString());                
                    }
                }
                else if(type==2)
                {
                    p.println("STRING");
                    String strVal[] = (String[])colval.getValues();
                    for(int i=0;i<time.length;i++)
                    {       
                        p.println(time[i].toString() + " " + strVal[i]);                
                    }
                }
                else if(type==3)
                {
                    p.println("DECIMAL");
                    Double dval[] = (Double[])colval.getValues();
                    for(int i=0;i<time.length;i++)
                    {       
                        p.println(time[i].toString() + " " + dval[i].toString());                
                    }
                }
            }
            p.flush();
            p.close();
        }
        }

	
	/** To print the error message.
	 * 
	 * @param req encapsulates the request to the servlet
     * @param res encapsulates the response from the servlet
     * 
     * @exception IOException produced by failed or interrupted I/O operations.
	 */
	void errorPage(String s, HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PrintWriter out = res.getWriter();
        out.println(s);
    }    
}

