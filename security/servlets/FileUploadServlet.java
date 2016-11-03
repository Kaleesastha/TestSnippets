//$Id: FileUploadServlet.java,v 1.3 2008/06/16 08:07:54 venkatramanan Exp $
package com.adventnet.nms.servlets;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Statement;
import com.adventnet.nms.util.*;

/*------------------------------------------------------------------------------
* File Upload Servlet
*-----------------------------------------------------------------------------*/
public class FileUploadServlet extends HttpServlet
{
  public void doPost (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    BufferedWriter uploadFile = null;
      String uploadFileName = null;
    try
    {
      // Get the Input Stream
      String uploadDirectory = new String (com.adventnet.nms.util.PureUtils.rootDir);
      ServletInputStream input = request.getInputStream();
      BufferedReader theReader = new BufferedReader(new InputStreamReader(input));

      String fileName = request.getParameter("fileName");//NO I18N
      if(fileName.endsWith(".txt")){
	      uploadFileName = uploadDirectory+"/reports/".replace("\\", "/")+fileName;//NO I18N
	      System.out.println("Creating File "+fileName);//NO I18N
	      uploadFile = new BufferedWriter(new FileWriter(uploadFileName));
	      System.out.println("File Creation Completed"+fileName);//NO I18N

	      String line = null;
	      while ((line = theReader.readLine()) != null)
	      {
		      uploadFile.write(line);
		      uploadFile.newLine();
	      }

	      response.setContentType("text/html");//NO I18N
	      PrintWriter out = response.getWriter();
	      out.println("Successfully written polleddata file"); //NO I18N     
	      out.close();
      }
      else{
	      response.setContentType("text/html");//NO I18N
	      PrintWriter out = response.getWriter();
	      out.println("Only text files can be uploaded. " +fileName +" is not a valid polleddata file");//NO I18N
	      out.close();
      }
    }

	    
    catch (Exception e)
    {
       System.out.println(e);
      response.setContentType("text/html");//NO I18N
      PrintWriter out = response.getWriter();
      out.println("Error in writing the " +uploadFileName +" polleddata file");//NO I18N
      System.err.println("Error in writing the " +uploadFileName +" polleddata file");  //NO I18N    
      e.printStackTrace();
      out.close();
    }
    finally
    {
      if (uploadFile != null)
      {
        uploadFile.close();
      }
    }
	    /*Periodically the DataSyncUpHandler updates the database with the data collected from Distributed Poller.
	     * Previous this file is named as InsertFlatFiletoDB. After discussion, this has been changed to DataSyncUpHandler*/
    if(uploadFileName != null){
	    DataSyncUpHandler DBSync = new DataSyncUpHandler();
	    DBSync.fileToDB(uploadFileName);
    }
  }
}
