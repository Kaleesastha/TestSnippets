import java.io.File;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.Vector;
import java.util.Properties;
import java.util.Enumeration;

import com.adventnet.nms.util.DBParamsParser;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.db.util.SQLQueryGenerator;
import com.adventnet.nms.db.util.RelationalInterface;
import com.adventnet.nms.db.util.RelationalInterfaceImpl;
import com.adventnet.nms.store.relational.RelationalUtil;
import com.adventnet.nms.store.relational.RelationalAPI;


public class TestSQLQueryGenscope extends GenericServlet
{
    private static String rootDir;
    private SQLQueryGenerator q;
    private Connection conn = null;
    private PrintWriter pw = null;
    private Statement statement = null;

    public void service(ServletRequest req,ServletResponse res)
    {
        try
        {
            res.setContentType("text/html");
            pw = res.getWriter();
            //rootDir = "C:\\David val\\AdventNet\\WebNMS\\";
            //PureUtils.rootDir = rootDir;
            in();
            test();
            close();
    	}
        catch(Exception j)
        {
            j.printStackTrace();
        }
    }

    private void test()
    {
        pw.println("Starting to test the TestCases!!!!");
        pw.println("INTO THE TEST() FUNCTION");
        pw.println("<br><br>");
        long time1 = System.currentTimeMillis();
        testCase001();
        testCase002();
        testCase003();
        testCase004();
        testCase005();
        testCase006();
        testCase007();
        testCase008();
        testCase009();
        testCase010();
        testCase011();
        testCase012();
        testCase013();
        testCase014();
        testCase015();
        testCase016();
        testCase017();
        testCase018();
        testCase019();
        testCase020();
        testCase021();
        testCase022();
        testCase023();
        testCase024();
        testCase025();
        testCase026();
        testCase027();
        testCase028();
        testCase029();
        testCase030();
        testCase031();
        testCase032();
        testCase033();
        testCase034();
        testCase035();
        testCase036();
        testCase037();
        testCase038();
        testCase039();
        testCase040();
        testCase041();
        testCase042();
        testCase043();
        testCase044();
        testCase045();
        testCase046();
        testCase047();
        testCase048();
        testCase049();
        long time2 = System.currentTimeMillis();
        pw.println("COMPLETED ALL  TESTCASE SIN THE TESTPLAN SF-PERS-SQG: ");
        pw.println("<br>");
        pw.println("Time taken to Validate the 	TestCases : "+(time2-time1));

    }



    private void testCase001()
    {
        try
        {

			String[] colsToView = {"name","IPADDRESS"};
			  Properties bc = new Properties();
			Properties sc [] = new Properties[1];
			sc[0] = new Properties();
        	sc[0].setProperty("name","a*");
        	Properties ac = new Properties();
			            Vector tablesToQuery = new Vector();
			            //tablesToQuery.addElement("TopoObject");
			            String sql = q.getSqlString(colsToView,bc,sc,ac,false) ;
			            pw.println("TESTCASE 001 SQL String is is is : "+sql);
			            pw.println("<br><br>");
        			    pw.println("<br><br>");

        }
        catch(Exception u)
        {
            u.printStackTrace();
        }
    }


    private void testCase002()
    {
        try
        {
           String[] colsToView = {"name","IPADDRESS"};
		   			  Properties bc = new Properties();
		   			Properties sc [] = new Properties[2];
					        sc[0] = new Properties();
					        sc[1] = new Properties();
					        sc[0].setProperty("name","a*");
					sc[1].setProperty("name","b*");

		           	Properties ac = new Properties();
		   			            Vector tablesToQuery = new Vector();
		   			            //tablesToQuery.addElement("TopoObject");
		   			            String sql = q.getSqlString(colsToView,bc,sc,ac,false) ;
		   			            pw.println("TESTCASE 002 SQL String is is is : "+sql);
		   			            pw.println("<br><br>");
		           			    pw.println("<br><br>");

        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

private void testCase003()
    {
        try
        {
           String[] colsToView = {"name","IPADDRESS"};
		   			  Properties bc = new Properties();
		   			  bc.setProperty("name","a*");
		   			Properties sc [] = new Properties[2];
					        sc[0] = new Properties();
					        sc[1] = new Properties();
					        sc[0].setProperty("name","a*");
					sc[1].setProperty("name","b*");

		           	Properties ac = new Properties();
		   			            Vector tablesToQuery = new Vector();
		   			            //tablesToQuery.addElement("TopoObject");
		   			            String sql = q.getSqlString(colsToView,bc,sc,ac,false) ;
		   			            pw.println("TESTCASE 003 SQL String is is is : "+sql);
		   			            pw.println("<br><br>");
		           			    pw.println("<br><br>");

        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


private void testCase004()
    {
        try
        {
           String[] colsToView = {"name","IPADDRESS"};
		   			  Properties bc = new Properties();
		   			  bc.setProperty("name","a*");
		   			Properties sc [] = new Properties[2];
					        sc[0] = new Properties();
					        sc[1] = new Properties();
					        sc[0].setProperty("name","a*");
					sc[1].setProperty("name","b*");

		           	Properties ac = new Properties();
		           	ac.setProperty("name","as*");
		   			            Vector tablesToQuery = new Vector();
		   			            //tablesToQuery.addElement("TopoObject");
		   			            String sql = q.getSqlString(colsToView,bc,sc,ac,false) ;
		   			            pw.println("TESTCASE 004 SQL String is is is : "+sql);
		   			            pw.println("<br><br>");
		           			    pw.println("<br><br>");

        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


private void testCase005()
    {
        try
        {
           String[] colsToView = {"name","IPADDRESS"};
		   			  Properties bc = new Properties();
		   			  bc.setProperty("name","a*");
		   			Properties sc [] = new Properties[2];
					        sc[0] = new Properties();
					        sc[1] = new Properties();
					        sc[0].setProperty("name","a*");
					sc[1].setProperty("name","b*");

		           	Properties ac = new Properties();
		           	ac.setProperty("name","as*");
		           	ac.setProperty("IPADDRESS","192.168.9.1*");
		   			    	        Vector tablesToQuery = new Vector();
		   			            //tablesToQuery.addElement("TopoObject");
		   			            String sql = q.getSqlString(colsToView,bc,sc,ac,false) ;
		   			            pw.println("TESTCASE 005 SQL String is is is : "+sql);
		   			            pw.println("<br><br>");
		           			    pw.println("<br><br>");

        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase006()
    {
        try
        {
           String[] colsToView = {"name","IPADDRESS"};
		   Properties bc = new Properties();
			bc.setProperty("name","a*");
		   			Properties sc [] = new Properties[2];
				        sc[0] = new Properties();
				        sc[1] = new Properties();
				        sc[0].setProperty("name","a*");
	   					sc[1].setProperty("name","b*");
		    Properties ac = new Properties();
			ac.setProperty("name","as*");
			ac.setProperty("IPADDRESS","192.168.9.1*");
	            Vector tablesToQuery = new Vector();
 			            String sql = q.getSqlString(colsToView,bc,sc,ac,true) ;

            pw.println("TESTCASE 006 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase007()
    {
        try
        {
           String[] colsToView = {"name","IPADDRESS"};
		   Properties bc = new Properties();
			bc.setProperty("name","a*");
		   			Properties sc [] = new Properties[2];
				        sc[0] = new Properties();
				        sc[1] = new Properties();
				        sc[0].setProperty("name","a*");
	   					sc[1].setProperty("name","b*");
		    Properties ac = new Properties();
			ac.setProperty("name","as*");
			ac.setProperty("consoleLightString","R*");
	            Vector tablesToQuery = new Vector();
 			            String sql = q.getSqlString(colsToView,bc,sc,ac,false) ;

            pw.println("TESTCASE 007 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase008()
    {
        try
        {
           String[] colsToView = {"name","IPADDRESS"};
		   Properties bc = new Properties();
			bc.setProperty("name","a*");
			bc.setProperty("consoleLightstring","R*");
		   			Properties sc [] = new Properties[2];
				        sc[0] = new Properties();
				        sc[1] = new Properties();
				        sc[0].setProperty("name","a*");
	   					sc[1].setProperty("name","b*");
		    Properties ac = new Properties();
			ac.setProperty("name","as*");
				            Vector tablesToQuery = new Vector();
 			            String sql = q.getSqlString(colsToView,bc,sc,ac,false) ;
            pw.println("TESTCASE 008 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase009()
    {
        try
        {
			  Properties bc = new Properties();


			Properties sc [] = new Properties[1];
			        sc[0] = new Properties();
			        sc[0].setProperty("name","a*");


			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");



			        Properties ac = new Properties();
			       String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);

            pw.println("TESTCASE 009 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase010()
    {
        try
        {
        Properties bc = new Properties();
		Properties sc [] = new Properties[2];
        sc[0] = new Properties();
        sc[1] = new Properties();
        sc[0].setProperty("name","a*");
		sc[1].setProperty("name","b*");
		Vector tablesToQuery = new Vector();
		tablesToQuery.add("ManagedObject");
	 	tablesToQuery.add("TopoObject");
       	Properties ac = new Properties();
		String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		pw.println("TESTCASE 010 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase011()
    {
        try
        {
        Properties bc = new Properties();
        bc.setProperty("name","a*");
		Properties sc [] = new Properties[2];
        sc[0] = new Properties();
        sc[1] = new Properties();
        sc[0].setProperty("name","a*");
		sc[1].setProperty("name","b*");
		Vector tablesToQuery = new Vector();
		tablesToQuery.add("ManagedObject");
	 	tablesToQuery.add("TopoObject");
       	Properties ac = new Properties();
		String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		pw.println("TESTCASE 011 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase012()
    {
        try
        {
        Properties bc = new Properties();
        bc.setProperty("name","a*");
		Properties sc [] = new Properties[2];
        sc[0] = new Properties();
        sc[1] = new Properties();
        sc[0].setProperty("name","a*");
		sc[1].setProperty("name","b*");
		Vector tablesToQuery = new Vector();
		tablesToQuery.add("ManagedObject");
	 	tablesToQuery.add("TopoObject");
       	Properties ac = new Properties();
       	ac.setProperty("name","as*");
		String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		pw.println("TESTCASE 012 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

private void testCase013()
    {
        try
        {
        Properties bc = new Properties();
        bc.setProperty("name","a*");
		Properties sc [] = new Properties[2];
        sc[0] = new Properties();
        sc[1] = new Properties();
        sc[0].setProperty("name","a*");
		sc[1].setProperty("name","b*");
		Vector tablesToQuery = new Vector();
		tablesToQuery.add("ManagedObject");
	 	tablesToQuery.add("TopoObject");
       	Properties ac = new Properties();
       	ac.setProperty("name","as*");
       	ac.setProperty("IPADDRESS","192.168.9.1*");
		String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		pw.println("TESTCASE 013 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

private void testCase014()
    {
        try
        {
        Properties bc = new Properties();
        bc.setProperty("name","a*");
		Properties sc [] = new Properties[2];
        sc[0] = new Properties();
        sc[1] = new Properties();
        sc[0].setProperty("name","a*");
		sc[1].setProperty("name","b*");
		Vector tablesToQuery = new Vector();
		tablesToQuery.add("ManagedObject");
	 	tablesToQuery.add("TopoObject");
       	Properties ac = new Properties();
       	ac.setProperty("name","as*");
       	ac.setProperty("IPADDRESS","192.168.9.1*");
		String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,true);
		pw.println("TESTCASE 014 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

private void testCase015()
    {
        try
        {
        Properties bc = new Properties();
        bc.setProperty("name","a*");
		Properties sc [] = new Properties[2];
        sc[0] = new Properties();
        sc[1] = new Properties();
        sc[0].setProperty("name","a*");
		sc[1].setProperty("name","b*");
		Vector tablesToQuery = new Vector();
		tablesToQuery.add("ManagedObject");
	 	tablesToQuery.add("TopoObject");
       	Properties ac = new Properties();
       	ac.setProperty("name","as*");
       	ac.setProperty("IPADDRESS","192.168.9.1*");
		String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,true);
		pw.println("TESTCASE 015 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

private void testCase016()
    {
        try
        {
        Properties bc = new Properties();
        bc.setProperty("name","a*");
        bc.setProperty("consoleLightString","r*");
		Properties sc [] = new Properties[2];
        sc[0] = new Properties();
        sc[1] = new Properties();
        sc[0].setProperty("name","a*");
		sc[1].setProperty("name","b*");
		Vector tablesToQuery = new Vector();
		tablesToQuery.add("ManagedObject");
	 	tablesToQuery.add("TopoObject");
       	Properties ac = new Properties();
       	ac.setProperty("name","as*");
       	String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		pw.println("TESTCASE 016 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase017()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
		   Properties sc [] = new Properties[1];
		           sc[0] = new Properties();
		           sc[0].setProperty("name","a*");
			Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		            String sql=q.getSqlString(colsToView,bc,sc,ac,false);
					pw.println("TESTCASE 017 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,1,150);
			pw.println("TESTCASE 017 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE017*** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

private void testCase018()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
		  Properties sc [] = new Properties[2];
		          sc[0] = new Properties();
		          sc[1] = new Properties();
		          sc[0].setProperty("name","a*");
				  sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		            String sql=q.getSqlString(colsToView,bc,sc,ac,false);
					pw.println("TESTCASE 018 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,1,150);
			pw.println("TESTCASE 018 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE018  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase019()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
           bc.setProperty("name","a*");
		  Properties sc [] = new Properties[2];
		          sc[0] = new Properties();
		          sc[1] = new Properties();
		          sc[0].setProperty("name","a*");
				  sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		            String sql=q.getSqlString(colsToView,bc,sc,ac,false);
					pw.println("TESTCASE 019 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,1,150);
			pw.println("TESTCASE 019 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE019  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase020()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
           bc.setProperty("name","a*");
		  Properties sc [] = new Properties[2];
		          sc[0] = new Properties();
		          sc[1] = new Properties();
		          sc[0].setProperty("name","a*");
				  sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		           ac.setProperty("name","as*");
		            String sql=q.getSqlString(colsToView,bc,sc,ac,false);
					pw.println("TESTCASE 20 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,1,150);
			pw.println("TESTCASE 20 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE 20  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase021()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
           bc.setProperty("name","a*");
		  Properties sc [] = new Properties[2];
		          sc[0] = new Properties();
		          sc[1] = new Properties();
		          sc[0].setProperty("name","a*");
				  sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		           ac.setProperty("name","as*");
		           ac.setProperty("IPADDRESS","192.168.9.1*");
		            String sql=q.getSqlString(colsToView,bc,sc,ac,false);
					pw.println("TESTCASE 21 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,1,150);
			pw.println("TESTCASE 21 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE 21  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }


private void testCase022()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
           bc.setProperty("name","a*");
		  Properties sc [] = new Properties[2];
		          sc[0] = new Properties();
		          sc[1] = new Properties();
		          sc[0].setProperty("name","a*");
				  sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		           ac.setProperty("name","as*");
		           ac.setProperty("IPADDRESS","192.168.9.1*");
		            String sql=q.getSqlString(colsToView,bc,sc,ac,true);
					pw.println("TESTCASE 22 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,1,150);
			pw.println("TESTCASE 22 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE 22  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }


private void testCase023()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
           bc.setProperty("name","a*");
		  Properties sc [] = new Properties[2];
		          sc[0] = new Properties();
		          sc[1] = new Properties();
		          sc[0].setProperty("name","a*");
				  sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		           ac.setProperty("name","as*");
		           ac.setProperty("consoleLightString","R*");
		            String sql=q.getSqlString(colsToView,bc,sc,ac,true);
					pw.println("TESTCASE 23 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,1,150);
			pw.println("TESTCASE 23 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE 23  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase024()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
           bc.setProperty("name","a*");
           bc.setProperty("consoleLightstring","R*");
		  Properties sc [] = new Properties[2];
		          sc[0] = new Properties();
		          sc[1] = new Properties();
		          sc[0].setProperty("name","a*");
				  sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		           ac.setProperty("name","as*");
		            String sql=q.getSqlString(colsToView,bc,sc,ac,false);
					pw.println("TESTCASE 24 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,1,150);
			pw.println("TESTCASE 24 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE 24  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase025()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
           Properties sc [] = new Properties[1];
		          sc[0] = new Properties();
		          sc[0].setProperty("name","a*");
					Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		            String sql=q.getSqlString(colsToView,bc,sc,ac,false);
					pw.println("TESTCASE 25 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,true,1,150);
			pw.println("TESTCASE 25 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE 25  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase026()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
           Properties sc [] = new Properties[1];
		          sc[0] = new Properties();
		          sc[0].setProperty("name","a*");
					Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		            String sql=q.getSqlString(colsToView,bc,sc,ac,false);
					pw.println("TESTCASE 26 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,5,150);
			pw.println("TESTCASE 26 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE 26  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }


private void testCase027()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
           Properties sc [] = new Properties[1];
		          sc[0] = new Properties();
		          sc[0].setProperty("name","a*");
					Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		            String sql=q.getSqlString(colsToView,bc,sc,ac,false);
					pw.println("TESTCASE 27 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,5,10);
			pw.println("TESTCASE 27 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE 27  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }


private void testCase028()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
           Properties sc [] = new Properties[1];
		          sc[0] = new Properties();
		          sc[0].setProperty("name","a*");
					Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		            String sql=q.getSqlString(colsToView,bc,sc,ac,false);
					pw.println("TESTCASE 28 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,0,10);
			pw.println("TESTCASE 28 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE 28  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }


private void testCase029()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
           Properties sc [] = new Properties[1];
		          sc[0] = new Properties();
		          sc[0].setProperty("name","a*");
					Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		            String sql=q.getSqlString(colsToView,bc,sc,ac,false);
					pw.println("TESTCASE 29 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,-1,10);
			pw.println("TESTCASE 29 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE 29  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }


private void testCase030()
    {
        try
        {
		   String [] colsToView = {"name","IPADDRESS"};
           Properties bc = new Properties();
           Properties sc [] = new Properties[1];
		          sc[0] = new Properties();
		          sc[0].setProperty("name","a*");
					Vector tablesToQuery = new Vector();
		           Properties ac = new Properties();
		            String sql=q.getSqlString(colsToView,bc,sc,ac,false);
					pw.println("TESTCASE 30 SQL String is is is : "+sql);
		   Vector v = q.executeQuery(sql,colsToView,bc,sc,ac,false,5,-10);
			pw.println("TESTCASE 30 vector is : "+v);
		               Enumeration en = v.elements();
		               while(en.hasMoreElements())
		               {   pw.println("TESTCASE 30  *** DATA : "+en.nextElement());
		                   pw.println("<br><br>");
            			}
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }


    private void testCase031()
    {
        try
        {
            Properties bc = new Properties();
			Properties sc [] = new Properties[1];
			    sc[0] = new Properties();
			    sc[0].setProperty("name","a*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,1,100);
            pw.println("TESTCASE **031* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

    private void testCase032()
    {
        try
        {
            Properties bc = new Properties();
			Properties sc [] = new Properties[2];
			        sc[0] = new Properties();
			        sc[1] = new Properties();
			        sc[0].setProperty("name","a*");
					sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,1,100);
            pw.println("TESTCASE **032* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase033()
    {
        try
        {
            Properties bc = new Properties();
            bc.setProperty("name","a*");
			Properties sc [] = new Properties[2];
			        sc[0] = new Properties();
			        sc[1] = new Properties();
			        sc[0].setProperty("name","a*");
					sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,1,100);
            pw.println("TESTCASE **033* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase034()
    {
        try
        {
            Properties bc = new Properties();
            bc.setProperty("name","a*");
			Properties sc [] = new Properties[2];
			        sc[0] = new Properties();
			        sc[1] = new Properties();
			        sc[0].setProperty("name","a*");
					sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           ac.setProperty("name","as*");
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,1,100);
            pw.println("TESTCASE **034* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase035()
    {
        try
        {
            Properties bc = new Properties();
            bc.setProperty("name","a*");
			Properties sc [] = new Properties[2];
			        sc[0] = new Properties();
			        sc[1] = new Properties();
			        sc[0].setProperty("name","a*");
					sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           ac.setProperty("name","as*");
           ac.setProperty("IPADDRESS","192.168.9.1*");
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,1,100);
            pw.println("TESTCASE **035* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase036()
    {
        try
        {
            Properties bc = new Properties();
            bc.setProperty("name","a*");
			Properties sc [] = new Properties[2];
			        sc[0] = new Properties();
			        sc[1] = new Properties();
			        sc[0].setProperty("name","a*");
					sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           ac.setProperty("name","as*");
           ac.setProperty("IPADDRESS","192.168.9.1*");
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,true);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,1,100);
            pw.println("TESTCASE **036* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }


private void testCase037()
    {
        try
        {
            Properties bc = new Properties();
            bc.setProperty("name","a*");
			Properties sc [] = new Properties[2];
			        sc[0] = new Properties();
			        sc[1] = new Properties();
			        sc[0].setProperty("name","a*");
					sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           ac.setProperty("name","as*");
          ac.setProperty("consoleLightString","R*");
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,1,100);
            pw.println("TESTCASE **037* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase038()
    {
        try
        {
            Properties bc = new Properties();
		         bc.setProperty("name","a*");
				bc.setProperty("consoleLightString","R*");
			Properties sc [] = new Properties[2];
			        sc[0] = new Properties();
			        sc[1] = new Properties();
			        sc[0].setProperty("name","a*");
					sc[1].setProperty("name","b*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           ac.setProperty("name","as*");
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,1,100);
            pw.println("TESTCASE **038* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase039()
    {
        try
        {
            Properties bc = new Properties();
		         Properties sc [] = new Properties[1];
			        sc[0] = new Properties();
			        sc[0].setProperty("name","a*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,5,100);
            pw.println("TESTCASE **039* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase040()
    {
        try
        {
            Properties bc = new Properties();
		         Properties sc [] = new Properties[1];
			        sc[0] = new Properties();
			        sc[0].setProperty("name","a*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,5,10);
            pw.println("TESTCASE **040* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase041()
    {
        try
        {
            Properties bc = new Properties();
		         Properties sc [] = new Properties[1];
			        sc[0] = new Properties();
			        sc[0].setProperty("name","a*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,0,10);
            pw.println("TESTCASE **041* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

private void testCase042()
    {
        try
        {
            Properties bc = new Properties();
		         Properties sc [] = new Properties[1];
			        sc[0] = new Properties();
			        sc[0].setProperty("name","a*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,-1,10);
            pw.println("TESTCASE **042* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }


private void testCase043()
    {
        try
        {
            Properties bc = new Properties();
		         Properties sc [] = new Properties[1];
			        sc[0] = new Properties();
			        sc[0].setProperty("name","a*");
			Vector tablesToQuery = new Vector();
			 tablesToQuery.add("ManagedObject");
			 tablesToQuery.add("TopoObject");
           Properties ac = new Properties();
           String sql=q.getSqlStringToGetAllProps(bc,sc,ac,tablesToQuery,false);
		   q.executeQueryAndGetAllProps(sql,tablesToQuery,bc,sc,ac,1,-10);
            pw.println("TESTCASE **043* SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        { m.printStackTrace();}
    }

    private void testCase044()
    {
        try
        { 	Properties bc = new Properties();
			Properties sc [] = new Properties[1];
			        sc[0] = new Properties();
			        sc[0].setProperty("name","a*");
	        Properties ac = new Properties();
            String sql = q.getSqlStringForCount(bc,sc,ac,false);
            pw.println("TESTCASE 044 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {m.printStackTrace();}
    }


    private void testCase045()
    {
        try
        {
        Properties bc = new Properties();
		Properties sc [] = new Properties[2];
	        sc[0] = new Properties();
	        sc[1] = new Properties();
	        sc[0].setProperty("name","a*");
			sc[1].setProperty("name","b*");
        Properties ac = new Properties();
		String sql = q.getSqlStringForCount(bc,sc,ac,false);
		pw.println("TESTCASE 045 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase046()
    {
        try
        {
        Properties bc = new Properties();
        bc.setProperty("name","a*");
		Properties sc [] = new Properties[2];
	        sc[0] = new Properties();
	        sc[1] = new Properties();
	        sc[0].setProperty("name","a*");
			sc[1].setProperty("name","b*");
        Properties ac = new Properties();
		String sql = q.getSqlStringForCount(bc,sc,ac,false);
		pw.println("TESTCASE 046 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

private void testCase047()
    {
        try
        {
        Properties bc = new Properties();
        bc.setProperty("name","a*");
		Properties sc [] = new Properties[2];
	        sc[0] = new Properties();
	        sc[1] = new Properties();
	        sc[0].setProperty("name","a*");
			sc[1].setProperty("name","b*");
        Properties ac = new Properties();
        ac.setProperty("name","as*");
		String sql = q.getSqlStringForCount(bc,sc,ac,false);
		pw.println("TESTCASE 047 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

private void testCase048()
    {
        try
        {
        Properties bc = new Properties();
        bc.setProperty("name","a*");
		Properties sc [] = new Properties[2];
	        sc[0] = new Properties();
	        sc[1] = new Properties();
	        sc[0].setProperty("name","a*");
			sc[1].setProperty("name","b*");
        Properties ac = new Properties();
        ac.setProperty("name","as*");
        ac.setProperty("IPADDRESS","192.168.9.1*");
		String sql = q.getSqlStringForCount(bc,sc,ac,false);
		pw.println("TESTCASE 048 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

private void testCase049()
    {
        try
        {
        Properties bc = new Properties();
        bc.setProperty("name","a*");
		Properties sc [] = new Properties[2];
	        sc[0] = new Properties();
	        sc[1] = new Properties();
	        sc[0].setProperty("name","a*");
			sc[1].setProperty("name","b*");
        Properties ac = new Properties();
        ac.setProperty("name","as*");
        ac.setProperty("IPADDRESS","192.168.9.1*");
		String sql = q.getSqlStringForCount(bc,sc,ac,false);
		pw.println("TESTCASE 049 SQL String is is is : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void in()
    {
        DBParamsParser parse = null;
        String parseFileName = PureUtils.rootDir + "conf" + "/" + "database_params.conf";
        File parseFile = new File(parseFileName);
        try
        {
            parse = DBParamsParser.getInstance(parseFile);//Database related details are read to create a DB connection.
        }
        catch(Exception et)
        {
            et.printStackTrace();
        }
        String url = parse.getURL();
        String user = parse.getUserName();
        String driver = parse.getDriverName();
        String passwd = parse.getPassword();
        try
        {
            Class.forName(driver);
            conn=DriverManager.getConnection(url,user,passwd);
            pw.println("CONNECTION...:  " + conn);
            pw.println("<br><br>");
            statement = conn.createStatement();

        }
        catch(Exception ce)
        {
            pw.println(ce.getMessage());
            pw.println("<br><br>");
            ce.printStackTrace();
        }

        try
        {
            RelationalAPI relapi = new RelationalAPI(url,user,passwd,driver,false);
            RelationalUtil.init(relapi);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        RelationalInterfaceImpl relImpl = new RelationalInterfaceImpl();
        q = new SQLQueryGenerator(conn, "TopoDB", "ManagedObject", "TOPOUSERPROPS", relImpl);
  //   q = new SQLQueryGenerator(conn, "EventDB", "Event", "EVENTUSERPROPS", relImpl);
//        q = new SQLQueryGenerator(conn, "AlertDB", "Alert", "ALERTUSERPROPS", relImpl);

        pw.println("Created an instance of the SQL Q G : "+q);
        pw.println("<br><br>");
    }

    private void close()
    {
        try
        {
            conn.close();
        }
        catch(Exception ii)
        {
            ii.printStackTrace();
        }
        try
        {
            statement.close();
        }
        catch(Exception k)
        {
            k.printStackTrace();
        }
    }
}
