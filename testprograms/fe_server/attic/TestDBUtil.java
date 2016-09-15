package com.adventnet.nms.store;

import java.io.*;
import java.util.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.*;

public class TestDBUtil extends HttpServlet
{
    /*
     *
     *	 Usage   http://localhost:9090/servlets/test.RegisterBEFailOverListenerServlet
     *
     */
	

	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException 
	{
		doGet(req, res);
	}
	

	public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{   
		try{
		   PrintWriter out=res.getWriter();
		   Connection con=DBUtil.getData();
		   PreparedStatement ps=con.prepareStatement("create table t(no varchar(20))");
		   DBUtil.executeTheStatement(ps);
		   out.println("Completed ");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}


}
