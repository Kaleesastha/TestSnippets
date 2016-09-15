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
import com.adventnet.management.log.Log;
import com.adventnet.nms.store.*;

public class TestSZ9 extends HttpServlet
{
    HttpServletRequest req = null;
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {
        doGet(req, res);
    }
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		System.out.println( "Serialized vector  Testing" );
		DBVector szvec = new DBVector( "SZTest2", "SColumn", false, null, true );
		
		DBIndexedVector db= new DBIndexedVector("SZTest2A",null,true);
		szvec.setMaxValue(10);
		szvec.addElement( "AdventNet" );
		szvec.addElement( "AdventNet1" );
		szvec.addElement( "AdventNet2" );
		szvec.addElement( "AdventNet3" );
		szvec.addElement( "AdventNet4" );
		szvec.addElement( "AdventNet5" );
		szvec.addElement( "AdventNet6" );
		szvec.addElement( "AdventNet7" );
		szvec.addElement( "AdventNet8" );
		szvec.addElement( "AdventNet9" );
		szvec.addElement( "AdventNet10" );

		szvec.addElement( "AdventNet11" );
		szvec.addElement( "AdventNet12" );
		szvec.addElement( "AdventNet13" );
		db.setMaxValue(5);
		db.addElement( "vembu1" );
		db.addElement( "vembu2" );
		db.addElement( "vembu3" );
		
		db.insertElementAt( "vembu4",1 );
		
		//szvec.removeAllElements();
		System.out.println("contains"+szvec.contains("AdventNet"));
		

     for (Enumeration e = db.elements() ; e.hasMoreElements() ;) {
         System.out.println(e.nextElement());
		 
     }
 

		
		System.out.println( "elements = "+szvec.elements());
		
		System.out.println( "GET max value"+szvec.getMaxValue());
		System.out.println( "Vector Size = " + szvec.size() );
		System.out.println( "elements at = "+ db.elementAt(-1));
		System.out.println( "contains at = "+ db.contains("vembu") );
		System.out.println( "Vector = " + szvec.toString() );
		System.out.println( "is empty = " + szvec.isEmpty());
		System.out.println( "index of object = " + db.indexOf("AdventNet") );
		szvec.saveState() ;
		db.saveState() ;
		szvec.clone() ;
		if( szvec.isEmpty() )
			{
			System.out.println( "Empty" );
			}
		else
			{
			System.out.println( "Not Empty" );
			
			}
		
	}
}
