/* $Id: FetchFile.java,v 1.3 2008/10/20 13:31:33 aravinds Exp $ */
package com.adventnet.nms.servlets;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;
//import	org.w3c.dom.*;
import com.adventnet.nms.util.*;
//import org.apache.crimson.tree.*;
//import com.adventnet.nms.fe.utils.*;
public class FetchFile extends HttpServlet 
{


	/**Provides Information string.
	 */
	public String getServletInfo() 
	{
		return "This servlet returns the requested file.";
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

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
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
	String fileName = null;

	String dirName = null;

	String userName = null;

	String alternateDirName = null;

	String forI18N = null;

			PrintWriter out=null;
	public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException 
		{	
			boolean fileExist = false;
			File file = null;

			fileName = req.getParameter("fileName");
			dirName = req.getParameter("dirName");
			userName = req.getParameter("userName");
			alternateDirName = req.getParameter("alternateDirName");
			forI18N = req.getParameter("forI18N");
			//String checkForFile=req.getParameter("checkForFile");
			try{
				res.setStatus( HttpServletResponse.SC_OK );
				res.setContentType( "text/html" );
				out = res.getWriter();
				out.flush();
				String url=PureUtils.rootDir+( (dirName != null)?dirName+File.separator:"")+fileName;
				file=new File(url);
				if((fileExist = file.exists()) == false)
				{
					if((forI18N != null) && (forI18N.equals("TRUE")))
					{
						url=PureUtils.rootDir+dirName+File.separator+"EnglishToNative.txt";
						file=new File(url);
						fileExist = file.exists();
					}else if(alternateDirName != null)
					{
						url=PureUtils.rootDir+alternateDirName+File.separator+fileName;
						file=new File(url);
						fileExist = file.exists();
					}
				}
				out.flush();
				if((file != null) && fileExist)
				{
					writeTheFile(file);
				}else
				{
					if(!tryInMapmenusAndListmenus())
						out.println("File Not Found");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			out.flush();
			out.close();
		}

	private void writeTheFile(File file)
	{
		try
		{
		out.println("File Found");
		FileInputStream fin= new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fin));
		out.flush();
		String line=null;
		while((line = br.readLine()) != null)
		{
			line = line.trim();
			//if(line.equals("")) continue;
			if(!line.startsWith("<!DOCTYPE"))
			{
				out.println(line);
			}
		}
		fin.close();
		br.close(); 
		}catch(Exception ex )
		{
			System.err.println("Exception occured while reading the file "+file);
			ex.printStackTrace();
		}


	}

	private boolean tryInMapmenusAndListmenus()
	{
		StringBuffer str = null;
		if(dirName == null)
		{
			str = new StringBuffer(fileName);
		}
		else
		{
			str = new StringBuffer(dirName);
		}
		
		String path;
		if(NmsUtil.createUserDir)
		{
			path = "/users/" + userName; //No I18N
		}
		else
		{
			path = "/html/defaultsToAllUsers"; //No I18N
		}
		String searchStr = path +"/listmenus/"; //No I18N
		int i = fileName.indexOf(searchStr); 
		if(i == -1)
		{
			searchStr = path +"/mapmenus/"; //No I18N
			if( (i = fileName.indexOf(searchStr)) != -1) 
			{
				str.replace(i,i+searchStr.length(),"/mapdata/menus/"); //No I18N
			}else
			{
				return false;
			}
		}
		else
		{
			str.replace(i,i+searchStr.length(),"/listmenus/"); //No I18N
		}

		String url = null;
		if(dirName == null)
		{
			url=PureUtils.rootDir+str;
		}
		else
		{
			url=PureUtils.rootDir+dirName+File.separator+fileName;
		}
		File file=new File(url);
		if(file.exists())
		{
			writeTheFile(file);
			return true;
		}
		return false;
	}


}
