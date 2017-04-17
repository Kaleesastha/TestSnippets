//$Id: GetTreeFromDB.java,v 1.2 2007/05/11 12:24:31 srajeswari Exp $
package com.adventnet.nms.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Properties;
import  org.w3c.dom.Element;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.db.util.TreeAPI;


public class GetTreeFromDB extends HttpServlet 
{
    /**Provides Information string.
     */
    public String getServletInfo() 
    {
        return "This servlet returns an Element to construct a tree";
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
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {   
        PrintWriter out=null;
        String nodeID=null;
        Connection con=null;
        String userName = req.getParameter("userName");
		String action =req.getParameter("ACTION-ON-NO-PRIVILEGE");
        if (req.getParameter("nodeID")!=null)
            nodeID = req.getParameter("nodeID");
        try{
            con = NmsUtil.relapi.getConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //throw e;  
        }
        try {
            res.setStatus( HttpServletResponse.SC_OK );
            res.setContentType( "text/html" );
            out = res.getWriter();
            TreeAPI tree = new TreeAPI(con);
            String root = tree.getNodeFromDB(userName,nodeID,action);
			out.println(root);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            throw e;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //  throw w;
        }
          

        out.flush();
        out.close();
    }
}
