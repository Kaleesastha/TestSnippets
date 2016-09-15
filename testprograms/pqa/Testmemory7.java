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

public class Testmemory7 extends HttpServlet
{
    HttpServletRequest req = null;
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {
        doGet(req, res);
    }
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		System.out.println( "Memory Testing" );
		MemoryTable mem	= new MemoryTable(10);
		mem.put("key11","vembu");
		mem.put("key1","vembu1");
		mem.put("key2","vembu2");
		mem.put("key3","vembu3");
		mem.put("key4","vembu4");
		mem.put("key5","vembu5");
		mem.put("key6","vembu6");
		mem.put("key7","vembu7");
		mem.put("key8","vembu8");
		mem.put("key9","vembu9");

		mem.put("key10","vembu10");
		//mem.remove("vembu9");
		mem.wipeOut();
		//mem.remove("key1");
		
		System.out.println( "get object = " + mem. get("key5"));
		
		System.out.println( " contains key = " + mem. containsKey("key6" ));
		//System.out.println( " GET ELEMENT AT =" + mem. elementAt(6));
		System.out.println( "contains  =  " +mem.contains("vembu8"));
		System.out.println( "contains  =  " +mem.contains("vembu1"));
		System.out.println( "SIZE = " +mem.size());
		
		
		
	}
}
