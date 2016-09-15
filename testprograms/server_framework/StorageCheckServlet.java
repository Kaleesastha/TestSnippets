// $Id: StorageCheckServlet.java,v 1.2 2001/08/17 09:56:27 subramani Exp $

package test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import com.adventnet.nms.util.*;
import com.adventnet.management.transaction.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.store.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.poll.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class StorageCheckServlet extends HttpServlet 
{

    PrintWriter out = null;
    static int i = 1000;
    private Statement stmt = null;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            out = response.getWriter();
            response.setContentType("text/html");
            out.println("<HTML>");
            out.println("<BODY>");
            out.println("Successfully connected to the server !!");
            RelationalAPI relapi = NmsUtil.relapi;
            Connection conn = relapi.getConnection();
            stmt = conn.createStatement();

            // testcase 001
            {
            trace("MAX Size of DBVector "+DBVector.getMaxValue());
            }

            // testcase 005
            {
            DBVector db = new DBVector("ManagedObject_Name", "ownername", false ,conn,false);

            trace("TC 005 Is table created - should get false here "+isTableExists("ManagedObject_Name"));
            }

            // testcase 006
            {
            
                dropTable("ManagedObject_Name_CreateTable");
            DBVector db = new DBVector("ManagedObject_Name_CreateTable", "ownername", false ,conn,true);
            db.addElement("test1");
            db.addElement("test2");
            trace("TC 006 Is table created - should get true here "+isTableExists("ManagedObject_Name_CreateTable"));
            }

            // testcase 009 - 016
            {
                DBVector db = new DBVector("ManagedObject_Name_009", "ownername", false ,conn,true);
                db.addElement("test1");
                db.addElement("test2");
                trace("TC 009 Result of DBVector.addElement method "+(db.contains("test1") && db.contains("test2")));
                trace("TC 010 Result of DBVector.size () - should get two here "+db.size());
                trace("TC 013 Result of DBVector.contains - should get true  "+db.contains("test1"));
                String result = "";
                for(Enumeration en = db.elements() ; en.hasMoreElements();)
                {
                    result = result + (String)en.nextElement() + " ";

                }
                trace("TC 014 Result of DBVector.enumerations - should get test1 test2  "+result);

                db.removeElement("test2");
                trace("TC 016 Result of DBVector.removeElement - should get true  "+(!db.contains("test2")));
                
            }

            
            // testcase 019
            {
                String tablename = "DBTOPO_1";
            DBHashtable db = new DBHashtable(tablename, "keystring","valuestring", false ,conn,false);

            trace("TC 019 Is table created - should get false here "+isTableExists(tablename));
            }

            // testcase 020
            {
                String tablename = "DBTOPO_1";
                dropTable(tablename);
            DBHashtable db = new DBHashtable(tablename, "keystring","valuestring", false ,conn,true);
            db.put("test1","test1");
            db.put("test2","test2");
            trace("TC 020 Is table created - should get true here "+isTableExists(tablename));
            }

            //testcase 023,024 , 025 , 029
            {
                String tablename = "DBTOPO_2";
                dropTable(tablename);
                DBHashtable db = new DBHashtable(tablename, "keystring","valuestring", false ,conn,true);
                db.put("test1_key","test1_value");
                db.put("test2_key","test2_value");
                db.put("one_key","two_value");

                if(db.get("test1_key").equals("test1_value") && db.get("test2_key").equals("test2_value"))
                {
                    trace("TC 023, 024 DBHashtable.put , DBHashtable.get methods working");
                }
                else
                {
                    error("ERROR !!!!!!!!!!! TC 023,024 DBHashtable.put , DBHashtable.get methods failed to work ");
                }

                trace("TC 025 size method of DBHashtable should return number 3 here "+db.size());
            
                if(db.contains("test1_value") && db.contains("test2_value") && db.contains("two_value")&& !db.contains("wrongvalue") && db.containsValue("two_value") && !db.containsValue("wrongvalue"))
                {
                    trace("TC 029 - method contains and containsValue working ");
                }
                else
                {
                    error("ERROR !!!!!!! TC 029 method contains and containsValue not working");
                }
            }
            
            // testcase 034
            {
                String tablename = "DBPROPTABLE_1";

            DBPropertytable db = new DBPropertytable(tablename, "keystring1","propstring1","valuestring1", false ,conn,false);

            trace("TC 034 Is table created - should get false here "+isTableExists(tablename));
            }

            // testcase 035
            {
                String tablename = "DBTOPO_1";
                dropTable(tablename);
            DBPropertytable db = new DBPropertytable(tablename, "keystring1","propstring1","valuestring1", false ,conn,true);
            
            //            db.put("test1","test1");
            //            db.put("test2","test2");
            trace("TC 035 Is table created - should get true here "+isTableExists(tablename));
            }

            {
            }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.println("</BODY>");
            out.println("</HTML>");
                    
        }

    }
    private void trace(String str)
    {
        System.out.println(str);
        
        out.println("<BR>"+str);

    }

    private void error(String str)
    {
        System.err.println(str);
        out.println("<FONT size=6 color=red>");
        out.println("<BR>"+str);
        out.println("</FONT>");
        

    }


    private boolean isTableExists(String tablename)
    {
        try
        {
            stmt.executeQuery("select count(*) from "+tablename);
            return true;
        }catch(Exception e)
        {
            return false;
        }
        

    }

    private boolean dropTable(String tablename)
    {
        try
        {
            stmt.executeQuery("drop table "+tablename);
            return true;
        }catch(Exception e)
        {
            return false;
        }
        

    }

}
