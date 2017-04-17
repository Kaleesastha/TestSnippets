// $Id: TL1CurrentPollServlet.java,v 1.2 2008/02/11 12:27:05 parimala Exp $
/**
* TL1CurrentPollServlet.java
* Copyright 2001. AdventNet, Inc. All Rights Reserved.
* Author Gopi Krishnan J.
**/    

package com.adventnet.nms.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.swing.JOptionPane;

//NMS
import com.adventnet.nms.poll.PolledData;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.util.NmsClientUtil;

public class TL1CurrentPollServlet extends HttpServlet 
{
  /**
   * Provides Information string.
   */
  public String getServletInfo() 
  {
	return "TL1 Current Poll : This servlet returns the parameter needed for TL1 Graphs"; //NO I18N
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
        PolledData pd =null;
        PollAPI papi;
        String key = null;

        try
        {
        for(Enumeration parameterNames = req.getParameterNames();
				parameterNames.hasMoreElements();)
        {
          String param = (String)parameterNames.nextElement();
          String value = (String)req.getParameter(param);

          if (value == null)
		  {
            JOptionPane.showMessageDialog(NmsClientUtil.applet, "Invalid key value for the servlet.", "Error",JOptionPane.ERROR_MESSAGE); //NO I18N
            return;
          }
          ht.put(param,value);
        }

        papi = GenericUtility.getPollAPI();
		if(papi == null)
		{
			System.err.println("TL1 Current Graph : PollAPI is NULL.");//NO I18N
			return;
		}
        key = ht.get("name").toString().trim() + "\t" + //NO I18N
              ht.get("agent").toString().trim() + "\t" + //NO I18N
			  ht.get("oid").toString().trim();//NO I18N
        pd = papi.getPolledData( key );
		if(pd == null)
		{
			System.err.println("TL1 Current Graph : PolledData is NULL.");//NO I18N
			return;
		}
        }
        catch (Exception ee ) 
        {
		  System.err.println("TL1 Current Poll : Exception while reading the \"UserProperty\" of the current poll data.");//NO I18N
		  return;
        }

	    String sendReponse = getIndexList(pd);
        String sendCompId = getComponentId(pd);
        String sendstring = sendReponse + "##" + sendCompId;//NO I18N
		
        try
        {
          DataOutputStream dos = new DataOutputStream(res.getOutputStream());
          dos.writeUTF( sendstring );
          dos.flush();
          dos.close();
        }
        catch (Exception ee ) 
        {
		  System.err.println("TL1 Current Poll : Exception while reading the \"UserProperty\" of the current poll data.");//NO I18N
          ee.printStackTrace();
        }
     }

    private String getComponentId(PolledData pd)
	{
      String str = null;
	  try
	  {
        str = (String)pd.getUserProperty("componentId");//NO I18N
        return str.trim();
      }
	  catch(Exception ex)
	  {
        System.err.println("TL1 Current Graph : Component Id @ Servlet Exception is : "+str);//NO I18N
        str = "noresponse";//NO I18N
		str = str.trim();
        return str;
      }
    }

    private String getIndexList(PolledData pd)
	{
      String str = null;
	  try
	  {
        str = (String)pd.getUserProperty("response");//NO I18N
		str = str.trim();
        return str;
      }
	  catch(Exception ex)
	  {
        System.err.println("TL1 Current Graph : Index List @ Servlet is : "+str);//NO I18N
        str = "noresponse";//NO I18N
        return str.trim();
      }
    }

}
