/* $Id: Jmx.java,v 1.1 2006/08/29 13:57:03 build Exp $
 * $(#)Jmx.java
 * Copyright (c) 1998 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.management.*;

import com.adventnet.ssiparser.*;
import com.adventnet.adaptors.html.*;
import com.adventnet.nms.jsp.JspUtility;

/**
 * Sevlet class for the HTTP adaptor of JMX agent. All the communication
 * of the JMX server to the browser through this servlet.
 */
public class Jmx extends HttpServlet
{
	/* Mail class object for both html and shtml parser */
	private HtmlMain main;

	/* Used to handle the static shtml parsing */
	private HtmlParser parser;

	/* Used to handle the dynamic html page generation */
	private DynamicHtml dynamic;

	/* To say about parsing for dynamic htmls files or static html files */
	private boolean dynamicHtmlGen;

	/* If a post request is received */
	private boolean getFromPost;

	/* If a post to add a new row */
	private boolean newRowFlag;

    //NEW CODE --- Balaji
	private boolean deleteRowFlag;
	//NEW CODE --- Balaji

    //changes
    private boolean nextRowFlag ;
    private boolean prevRowFlag ;
    //changes


	/**
	 * Method to initialize all necessary data structure. Here it will
	 * initialize all data structures related to HTML parser. This method
	 * is called once by the web-server.
	 */
    public void init(ServletConfig config) throws ServletException
	 {
		 super.init(config);

		 getFromPost = false;
		 newRowFlag	= false;
		 dynamicHtmlGen = true;

		 main = new HtmlMain();
		 parser = main.getHtmlParser();
		 dynamic = main.getDynamicHtml();
		dynamic.setLogoImageName(HtmlAdaptor.getParentDirectory()+"/ssi/logo.png");
		 initCustomHtmlParameters();
		 //initDynamicHtmlParameters();

	 }
	/**
	 * This will initialize all configurable parameters of the HtmlParser
	 * class. These parameters are used when generating .shtml files.
	 * Using these parameters the MibCompiler will generate a template
	 * html page for each MBean.
	 */
	private void initCustomHtmlParameters()
	{
		parser.setDirectoryName(HtmlAdaptor.getParentDirectory()+"/ssi/");
		parser.setIndexFileName("custom_index.html");
		parser.setServerName("JMX Agent for AGENT-SAMPLE-MIB");
	}

	/**
	 * In the case of dynamic html page generation, the user can
	 * configure certain parameters like logo, font size, footer,
	 * color, etc.
	 */
	private void initDynamicHtmlParameters()
	{
		dynamic.setAuthor("JMX Agent");
		dynamic.setIndexFileName("dynamic_index.html");
		dynamic.setHeaderSize(2);
		dynamic.setLogoImageName(HtmlAdaptor.getParentDirectory()+"/ssi/logo.png");
		dynamic.setLogoSize(72, 234);
		dynamic.setFooterMsg("<BR>&nbsp;Copyright (c) 1999-2000 AdventNet, Inc. All Rights Reserved. \n");
		dynamic.setBackGroundColor("#FFF0F0");
		dynamic.setBackGroundImage(null);
		dynamic.setBackTagLocation(dynamic.TOP_RIGHT);
	}
	//these variables are used as flags and parameter list to do the necessary action.
	private Object searchAttrbValue = null;
	private boolean forSet = false;
	private boolean addRow = false;
	private boolean deleteRow = false;
	private boolean modifyRow = false;
	private String searchAttrb = null;
	private String searchAttrbName = null;
	/**
	 * This method is invoked by the web-server when a message is
	 * posted from the browser.
	 *
	 * @param sreq - the collection of data rcvd from the browser.
	 * @param sres - the collection of data send to the browser.
	 */

    public void doPost(HttpServletRequest sreq, HttpServletResponse sres)
	throws ServletException, IOException
	{
	  //doPost is only for setting the values.
	  //Below lines are added for setting the content type
	  //Start of Setting the content type
	  sreq.getSession(true);
	  HttpSession session = sreq.getSession(true);
	  String id = session.getId();
	  String contentType = null;
	  try
	  {
	  contentType=  "text/html;charset="+JspUtility.getContentType((String)session.getAttribute("Language"),(String)session.getAttribute("Country")) ;
	  }
	  catch(Exception e)
	  {
		  contentType = "text/html";
	  }

	  sres.setContentType(contentType);
	  // End of Setting the content type
	  // end

	  String s1 = sreq.getParameter("arrayType");
	  //s1 will not be null for attributes that are of array type.

	  if(s1 != null)
	  {
		forSet = true;
		if(sreq.getParameter("add") != null)
		{
		  getFromPost = true;
		  newRowFlag = true;
		  addRow = true;
		}

		if(sreq.getParameter("delete") != null)
		{
		  getFromPost = true;
		  newRowFlag = true;
		  deleteRow = true;
		}

		if(sreq.getParameter("modifyButton") != null)
		{
		  modifyRow = true;
		}

		int count = 0;
		Enumeration e = sreq.getParameterNames();
		ObjectName objName = null;
		StringBuffer sb = new StringBuffer();
		String searchAttrbName = null;
		String s2 = null;
		if(sreq.getParameter("attrbName") != null)
		{
		  try{
			objName = new ObjectName(sreq.getParameter("attrbName"));
		  }catch(Exception meexccep)
		  {
			//meexcep.printStackTrace();
		  }
		}
		for (; e.hasMoreElements(); )
		{
		  Object paramName  = e.nextElement();
		  String s = paramName.toString();

		  //if loop is to get the name of the array type attribute.
		  if(s.indexOf(",")>0)
		  {
			count = count + 1;
			StringTokenizer st = new StringTokenizer(s,",");
			s2 = st.nextToken(",");
			searchAttrbName = s2;
		  }
		}

		String[] arrayentries = new String[count];
		String[] arrayentriestmp = null;

		//store the array values.
		for(int i= 0 ;i<count;i++)
		{
		  arrayentries[i] = sreq.getParameter(s2+","+i);
		}

		searchAttrbValue = (Object)arrayentries;

		//if it is to delete a row, remove the row from the array.
		if(sreq.getParameter("delete") != null)
		{
		  if(sreq.getParameter("delete").equals("Delete") == false)
		  {
			arrayentriestmp = new String[count-1];
			int j = 0;
			for(int i= 0 ;i<arrayentries.length;i++)
			{

			  if(arrayentries[i].equals(sreq.getParameter("delete")))
			  {
			  }
			  else
			  {
				arrayentriestmp[j] = arrayentries[i];
				j++;
			  }
			}
			searchAttrbValue = (Object)arrayentriestmp;
			deleteRow = false;
			newRowFlag = false;
			forSet = true;
		  }
		}

		//if it is to modify an existing value of an array, replace with the new values.
		if(sreq.getParameter("modifyButton") != null)
		{
		  if(sreq.getParameter("modifyButton").equals("Modify") == true)
		  {
			searchAttrbValue = (Object)arrayentries;
			searchAttrb = sreq.getParameter("arrayList");
			newRowFlag = false;
			forSet = true;
		  }
		}

		doGet(sreq,sres);
	  }

	  else if(s1 == null)
	  {

		Hashtable table = new Hashtable();

		Enumeration e = sreq.getParameterNames();

		for (; e.hasMoreElements(); )
		{
		  Object paramName  = e.nextElement();
		  String paramValue = sreq.getParameter(paramName.toString());
		  //System.out.println("ParamName is "+paramName+"   "+"ParamValue is "+paramValue);
		  table.put(paramName, paramValue);
		}

		int	retVal = main.setAttributeValue(table);
		if (retVal == main.NEW_ROW)
		{
		  newRowFlag = true;
		}

		if(retVal == main.NEXT_ROW){
		  newRowFlag = true;
		  nextRowFlag = true;
		}
		if(retVal == main.PREV_ROW){
		  newRowFlag = true;
		  prevRowFlag = true;
		}

		if(retVal == main.DELETE_ROW){

		  newRowFlag = true;
		  deleteRowFlag = true;
		}

		else if (retVal == main.POST_ERROR)
		{
		  PrintWriter p =  sres.getWriter();


		  String result = main.createErrorHtmlPage(main.FORBIDDEN);

		  p.println(result);
		  p.flush();
		  p.close();
		  return;
		}
		else if(retVal == main.DO_OPER)
		{
		  PrintWriter p =  sres.getWriter();

		  String result = main.page;

		  p.println(result);
		  p.flush();
		  p.close();
		  return;
		}

		getFromPost = true;
		doGet(sreq,sres);
	  }

	}
   /**
	 * This method is invoked by the web-server when a get is
	 * called from the browser.
	 *
	 * @param sreq - the collection of data rcvd from the browser.
	 * @param sres - the collection of data send to the browser.
	 */
    public void doGet(HttpServletRequest sreq, HttpServletResponse sres)
		throws ServletException, IOException {
		  // replaced by lifei#loc
		  // sres.setContentType("text/html");


		  String result = null;
		  sreq.getSession(true);

		  HttpSession session = sreq.getSession(true);
		  String id = session.getId();
		  //Below lines are added for setting the content type
		  //Start of Setting the content type
		  String contentType = null;
			try
			{
				
		  contentType=  "text/html;charset="+JspUtility.getContentType((String)session.getAttribute("Language"),(String)session.getAttribute("Country")) ;
			}
			catch(Exception e)
			{
				contentType = "text/html";
			}
		  sres.setContentType(contentType);
		  //End of Setting the content type

		  main.setClientInfo(id);

		  PrintWriter p =  sres.getWriter();
		  String fName  = sreq.getParameter("fname");

		  if(fName == null)
		  {
			try
			{
			  p.println(parser.parse("ssi"+File.separator+"index.html"));
			}
			catch(ParseException ex)
			{
			  ex.printStackTrace();

			  result = main.createErrorHtmlPage(HtmlMain.NOT_FOUND);
			  p.println(result);
			  p.flush();
			  p.close();
			  return;
			}
		  }
		  else
		  {
			dynamicHtmlGen = main.getMode(id);

			String timerVal = sreq.getParameter("timeValue");

			if (timerVal != null)
			{
			  try {
				int time = Integer.parseInt(timerVal);
				main.setRefreshTime(id, time);

			  }catch (NumberFormatException ne) {
				ne.printStackTrace();
			  }
			}

			// Check for the admin.html
			if ( fName.equals("admin.html"))
			{
			  String domain = sreq.getParameter("domain");
			  String keys = sreq.getParameter("keys");
			  String className = sreq.getParameter("className");
			  String classLoader = sreq.getParameter("classLoader");
			  String commandType = sreq.getParameter("commandType");
			  String cancel = sreq.getParameter("cancel");
			  String xmlFile = sreq.getParameter("xmlName");

			  // Check for cancel button is pressed
			  if (cancel != null)
			  {
				if (dynamicHtmlGen)
				  fName = dynamic.getIndexFileName();
				else
				  fName = parser.getIndexFileName();
			  }
			  else
			  {
				if ((domain != null) && (keys != null))
				{
				  if (commandType.equals("Register"))
					main.registerMBean(domain, keys, className, classLoader, xmlFile);
				  else
					main.unregisterMBean(domain, keys);

				  if (dynamicHtmlGen)
					fName = dynamic.getIndexFileName();
				  else
					fName = parser.getIndexFileName();
				}
			  }

			  if ( (!dynamicHtmlGen)  || fName.equals("admin.html"))
			  {
				try
				{
				  result = parser.parse(parser.getDirectoryName()+File.separator+fName);
				  p.println(result);
				}
				catch(ParseException ex)
				{
				  p.println("Error 2 : Unable to parse the file.");
				}

				p.flush();
				p.close();
				return;
			  }

			}

			// Check for the filter value and create dynamic html page
			// accordingly.
			if ( (dynamicHtmlGen) && (fName.equals("filter.html")) )
			{
			  String filter = sreq.getParameter("filter");

			  if (filter != null)
			  {
				dynamicHtmlGen = true;
				main.setMode(id, dynamicHtmlGen);
				main.setFilter(id, filter);
				result = dynamic.createDynamicIndexFile(filter,null);

				p.println(result);
				p.flush();
				p.close();
				return;
			  }
			}
			if ( (dynamicHtmlGen) && (fName.equals("notif.html")) )
			{
			  dynamicHtmlGen = true;
			  result = dynamic.createNotificationTable();

			  p.println(result);
			  p.flush();
			  p.close();
			  return;
			}

			String objName = null;
			objName = sreq.getParameter("attrbName");
			// if the doget is called from the dopPost - means for setting values.
			if (getFromPost)
			{
			  getFromPost = false;

			  /* generate html page to add new row */
			  if (newRowFlag)
			  {
				//to add a new row to the array list display
				if(addRow)
				{
				  searchAttrbName=sreq.getParameter("arrayList");
				  result = main.createNewRow(objName,fName,sreq.getParameter("arrayList"),"addRow");
				  addRow = false;
				}

				// to delete a particular row from array type display.
				else if(deleteRow)
				{
				  searchAttrbName=sreq.getParameter("arrayList");
				  result = main.createNewRow(objName,fName,sreq.getParameter("arrayList"),"deleteRow");
				  deleteRow = false;
				}
				//NEW CODE --- Balaji
				else if(deleteRowFlag){

				  result = main.createNewRow(objName, fName,null,"deleteRowTable");
				  deleteRowFlag = false;
				}
				//NEW CODE --- Balaji
				//changes
				else if(nextRowFlag){

				  try{
					main.setStartIndex(Integer.parseInt(sreq.getParameter("sIndex")));
					main.setEndIndex(Integer.parseInt(sreq.getParameter("eIndex")));
				  }catch(Exception nfe){
					nfe.printStackTrace();
				  }
				  result = main.createHtmlPage(objName,null,null,null);
				  nextRowFlag = false;
				}
				else if(prevRowFlag){

				  try{
					main.setStartIndex(Integer.parseInt(sreq.getParameter("sIndex")));
					main.setEndIndex(Integer.parseInt(sreq.getParameter("eIndex")));
				  }catch(Exception nfe){
					nfe.printStackTrace();
				  }
				  result = main.createHtmlPage(objName,null,null,null);
				  prevRowFlag = false;
				}
				//changes

				else
				{
				  result = main.createNewRow(objName, fName,null,"");
				}

				newRowFlag = false;

				if (result != null)
				  p.println(result);

				p.flush();
				p.close();
				return;
			  }
			}


			/* Create default index file with default filter value */
			if (fName.equals(dynamic.getIndexFileName()) && objName == null)
			{
			  dynamicHtmlGen = true;
			  main.setMode(id, dynamicHtmlGen);
			  result = dynamic.createDynamicIndexFile(main.getFilter(),null);

			  p.println(result);
			  p.flush();
			  p.close();
			  return;
			}
			else if (fName.equals(parser.getIndexFileName()))
			{
			  dynamicHtmlGen = false;
			  main.setMode(id, dynamicHtmlGen);
			}

			if (dynamicHtmlGen)
			{
			  String attrbArrayList = sreq.getParameter("attrbArrayList");

			  if (sreq.getParameter("attrbName") != null )
			  {
				fName  = sreq.getParameter("attrbName");
				if(forSet == true){


				  if(modifyRow)
				  {
					// means modifying row for a array type display.
					result = main.createHtmlPage(fName,searchAttrb,searchAttrbValue,searchAttrbName);
				  }
				  else{

					result = main.createHtmlPage(fName,null,searchAttrbValue,searchAttrbName);
				  }

				  result = main.createHtmlPage(fName,null,null,null);

				  forSet = false;
				  searchAttrbName=null;
				  searchAttrb = null;
				}
				else
				{
				  result = main.createHtmlPage(fName,null,null,null);
				}
			  }
			  else if(sreq.getParameter("attrbArrayList") != null)
			  {

				result = main.createHtmlPage(fName,attrbArrayList,null,null);
			  }
			  else{

				result = main.createHtmlPage(fName,null,null,null);
			  }

			  if (result != null)
				p.println(result);

			  p.flush();
			  p.close();
			  return;
			}

			/* This means that the request for customized html file. */
			try
			{
			  String shtml = sreq.getParameter("shtml");

			  if (shtml != null)
				fName = shtml;

			  String temp = parser.parse(parser.getDirectoryName()+File.separator+ fName);
			  p.println(temp);
			}
			catch(ParseException ex)
			{
			  result = main.createErrorHtmlPage(HtmlMain.NOT_FOUND);
			  p.println(result);
			}
		  }

		  p.flush();
		  p.close();
		  return;

		}

	/**
	 * Function used to get the information of the Servlet.
	 *
	 * @return the servlet information.
	 */
	public String getServletInfo()
	{
		return "Jmx Servlet";
	}

	/**
	 * It is used to log the information into a file.
	 */
	public void log(String s)
	{
	}
}
