//$Id: DownloadTrustStore.java
 
/*
 * Purpose - Servlet Class for downloading truststore file to the WebStart Client.
 */

package com.adventnet.nms.servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;

/**
 * Servlet Class for downloading truststore file to the WebStart Client.
 */

public class DownloadTrustStore extends HttpServlet
{
	/**
	 * Methods that downloads the truststore to the Client. It will be stored in user's home directory.
	 */
    public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession(true);
        ServletOutputStream sos = response.getOutputStream();
        response.setContentType("text/html"); //No I18N

        if(session != null)
        {
	     
            File file = new File("./conf/Truststore.truststore");//No I18N
            FileInputStream fis = null;

            try
            {
                fis = new FileInputStream(file);
                int size = (int)file.length();
                byte[] buffer = new byte[size];
                fis.read(buffer,0,size);
                sos.write(buffer,0,size);
                sos.flush();
			}
            catch ( Exception e )
            {
                e.printStackTrace(); 
            }
            finally
            {
                try 
				{ 
					if ( fis != null )
					{
						fis.close();
					}
					if ( sos != null )
					{
						sos.close(); 
					}
				} 
				catch (Exception e) {}
            }

        } 
        else
        {
			NmsLogMgr.MISCERR.log("HttpSession is null", Log.SUMMARY);//No I18N 
        }
    }
}
