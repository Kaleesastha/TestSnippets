// $Id: BannerServlet.java,v 1.6 2008/09/12 13:08:40 sudharshan Exp $
package com.adventnet.nms.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.util.NmsUtil;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;
import java.io.IOException;
import com.adventnet.management.transaction.TransactionAPI;

public class BannerServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    {
        //String banner = NmsUtil.getBannerMessage();
        ResultSet rs1=null;
        ResultSet rs2=null;
        ResultSet rs21=null;
        ResultSet rs3=null;
        String userName = req.getHeader("userName");//NO I18N
        int failedCount=0;
        String lastSuc="?";//NO I18N
        String bReq = req.getHeader("request");//NO I18N
        PrintWriter out = null;
        try
        {
            out = res.getWriter();
        }
        catch(IOException ioe)
        {
            System.err.println(" [ BannerServlet ] Error getting print writer" + ioe.getMessage() );//NO I18N
            return;
        }
        
        if(bReq !=null && bReq.equals("get"))//NO I18N
        {
        
            try
            {
                RelationalAPI relapi=null;
		TransactionAPI txnAPI=TransactionAPI.getInstance();
                while(relapi==null)
                {
                        relapi = NmsUtil.relapi;
                }
		txnAPI.begin();
                //Get MAX
                String q1="select MAX(AUTHAUDITID) from AuthAudit where STATUS='SUCCESS' AND USERNAME='"+userName+"'";//NO I18N
                Statement st1 = relapi.query(q1,true);
                rs1 = st1.getResultSet();
                int max=0;
                if(rs1.next())
                {
                        max = rs1.getInt(1);
                }
                //Get MAX-1
                String q2="select MAX(AUTHAUDITID) from AuthAudit where STATUS='SUCCESS' AND AUTHAUDITID < "+max+" AND USERNAME='"+userName+"'";//NO I18N
                Statement st2 = relapi.query(q2,true);
                rs2 = st2.getResultSet();
                int maxButOne=0;
                if(rs2.next())
                {
                    maxButOne = rs2.getInt(1);
                }
                String q21 = "select AUDITTIME from AuthAudit where AUTHAUDITID='"+maxButOne+"'";//NO I18N
                Statement st21 = relapi.query(q21,true);
                rs21 = st21.getResultSet();
                if(rs21.next())
                {
                    lastSuc = rs21.getString(1);
                }
                
                //Get FAILURE COUNT
                String q3 = "select AUTHAUDITID from AuthAudit where STATUS='FAILURE' AND ( AUTHAUDITID > " + maxButOne + " AND AUTHAUDITID < "+max + " )" + " AND USERNAME='"+userName+"'";//NO I18N
                Statement st3 = relapi.query(q3,true);
                rs3 = st3.getResultSet();
                Vector v= new Vector();
                
                while(rs3.next())
                {
                    failedCount=rs3.getRow();
                    v.addElement(new Integer(rs3.getInt(1)));
                }
                
                //Remove old entries 
                // [ WARNING] Should not delete entries in AuthAuditExt table but you can delete entries, if you have your own(custom) AuthAudit table
                /*String deleteIDs="";
                for(int i=0;i<v.size();i++)
                {
                    deleteIDs=deleteIDs + " OR AUTHAUDITID='" + (v.elementAt(i).toString())+ "'";//NO I18N
                }
                String q4="delete from AuthAuditExt where AUTHAUDITID='" +maxButOne +"'" + deleteIDs;//NO I18N
                relapi.query(q4,true);*/
		txnAPI.commit();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(rs1!=null)
                        rs1.close();
                    if(rs2!=null)
                        rs2.close();
                    if(rs21!=null)
                        rs21.close();
                    if(rs3!=null)
                        rs3.close();
                }
                catch(Exception e){        
                }
            }
            
            try
            {
                
                out.println(failedCount + "\n"+ lastSuc + "\n" + NmsUtil.getBannerMessage());//NO I18N
                out.flush();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    out.close();
                }
                catch(Exception e)
                {}
            }
            
        }
        else if(bReq !=null && bReq.equals("get_bmessage"))//NO I18N
        {
            try
            {
                out.println(NmsUtil.getBannerMessage());//NO I18N
                out.flush();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    out.close();
                }
                catch(Exception e)
                {}
        }


        }
        else if(bReq != null && bReq.equals("configure"))//NO I18N
        {
            String newBannerMessage = req.getHeader("bMessage");//NO I18N
            try
            {
                NmsUtil.setBannerMessage(newBannerMessage);
                out.println("success");//NO I18N
                out.flush();
            }
            catch(Exception e)
            {
		try
            	{
                    out.println(e.getMessage());
		    out.flush();		    
		}
	        catch(Exception ee)
		{
		}
                //e.printStackTrace();
            }
            finally
            {
                try
                {
                    out.close();
                }
                catch(Exception e)
                {}
            }

        }
    }
}
