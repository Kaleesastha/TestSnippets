/* This is a test servlet */
package test;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import java.io.*;
import java.util.Properties;
import java.util.Vector;
import java.util.Date;
import java.util.*;
import com.adventnet.nms.util.*;
import com.adventnet.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.store.*;

public class TestQueue2	 extends HttpServlet
{
    HttpServletRequest req = null;
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {
        doGet(req, res);
    }
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		System.out.println( "queue Testing" );
		Queue q	= new Queue(10);
		q.enQ("lakshmi");
		q.enQ("lakshmi1");
		q.enQ("lakshmi2");
		q.enQ("lakshmi3");
		q.enQ("lakshmi4");
		q.enQ("lakshmi5");
		q.enQ("lakshmi6");
		q.enQ("lakshmi7");
		q.enQ("lakshmi8");
		q.enQ("lakshmi9");
		q.enQ("lakshmi10");
		//q.deQFirstElement();
		//q.deQSomeElements();
		
		//System.out.println( "SIZE OF THE ARRAY = " + q.Queue() );
		System.out.println( "SIZE OF THE ARRAY = " + q. getArraySize() );
		System.out.println( " FIST ELEMENT = " + q. firstElement() );
		System.out.println( " GET ELEMENT AT =" + q. elementAt(6));
		System.out.println( "GET ELEMENT" +q.getAllElements());
		System.out.println( "SIZE = " +q.size());
		
		
		
	}
}
