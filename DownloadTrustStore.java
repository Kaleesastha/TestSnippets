/*-Dnms.server.host

Enable RMI Security

NAT IP.
FE-API-CSB - testplans/fe_server/fe_api_csb_tp.html

PERFORMANCE MODULE

Testcases to be validated
PM-ADMN-065,160-165 -
PM-API-096,097
PM-ADMN-063,111,112,190,191
PM-ADMN-001,026,073,205 
PM-API-177,183,184,190,197-199,206,208,280,281,323,325,327
PM-ADMN-027,188,189 
PM-API-014,016 to 018,026,128,217,333,334 
PM-ADMN-004,074 
PM-API-088,091,094
PM-ADMN-082,084,087 
PM-API-251-253,261 
PM-CONF-REP-007,009
PM-ADMN-080 
PM-API-245,249
PM-ADMN-012 to 014,043,062
PM-CONF-PARM-008
PM-ADMN-032,035,097-099,209-212 
PM-API-271
PM-ADMN-115,116
PM-ADMN-057 
PM-API-020,135,139,159
PM-ADMN-055,056 
PM-API-012,013,081,084,085,118,119,157-158,226-228
PM-API-303,308,310,312,314,316,317 
PM-CUST-TAB-001 to 004 ,007-010,013-016,019-022
PM-API-021,024
PM-TRN-037 to 045


FM-API-EV-104,106 
005 to 010
FM-API-EV-011 to 018 
FM-API-EV-042,043 
FM-API-EV-087 to 090 
FM-API-EV-019 
FM-API-EV-021 
FM-API-EV-028 to 031 
FM-API-EV-032 to 034 
FM-API-EV-022 (30)



Service.

*/
//$Id: DownloadTrustStore.java
 
/*
 * Purpose - Servlet Class for downloading truststore file to the WebStart Client.
 */

package truststore;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

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
        response.setContentType("text/html");

        if(session != null)
        {
	     
            File file = new File("./conf/Truststore.truststore");
			System.out.println(file);
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
			System.err.println("HttpSession is null"); 
        }
    }
}
