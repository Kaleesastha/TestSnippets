/**This test program is used to test the DBXmlUtility's methods.
 **
 **set AdventNet/WebNMS/classes/NmsClientClasses.jar and AdventNet/WebNMS/mysql/MMMySQLDriver/mysql_comp.jar 
 **in the classpath and compile it. 
 ** 
 **run this file as "java DBXmlUtilityTest <WebNMS path> <MYSQL/SOLID/TIMESTEN/ORACLE/SYBAS>"
 **
 **/

import java.io.*;
import java.util.*;
import java.util.Vector;
import java.util.Properties;
import java.rmi.RemoteException;
import javax.naming.*;
import java.rmi.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import com.adventnet.nms.db.util.NodeInterface;


import com.adventnet.nms.fe.common.NmsTreeAPI;
import com.adventnet.nms.fe.common.PanelTreeNode;
import com.adventnet.nms.fe.common.ViewData;
import com.adventnet.nms.fe.common.TableColumn;
import com.adventnet.nms.util.*;
import com.adventnet.nms.db.util.*;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.PureServerUtils;
import com.adventnet.nms.fe.common.*;
import com.adventnet.nms.db.*;

import com.adventnet.nms.fe.alert.AlertCustomViewUtility ;
import com.adventnet.nms.fe.event.EventCustomViewUtility ;
import com.adventnet.nms.fe.topo.TopoCustomViewUtility ;

import com.adventnet.nms.fe.config.AuditCustomViewUtility ;
import com.adventnet.nms.fe.perf.PerfCustomViewUtility ;

import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.store.relational.RelationalUtil;
import com.adventnet.nms.fe.utils.NmsFEUtil;

public class DBXmlUtilityTest extends DBXmlUtility
{

    DBXmlUtility dbxmlutil = null;
    
    Connection con = null;
   
    private void startProcess(String WebNmsHome, String Database)
    {

	// Connection con=null; 
        
        String webnmshome = WebNmsHome+File.separator;
        System.out.println("WEBNMS HOME... :  "+webnmshome);
        PureUtils.rootDir=webnmshome;
        PureUtils.usersDir=webnmshome;
        String db1 = Database.toUpperCase(); 
        System.out.println("DATA BASE..... :  "+db1);
	System.out.println();
        
        try
	    {
		if (db1.equals("MYSQL"))
		    {
			con = getMYSQLConnection();
		    }
		else if (db1.equals("ORACLE"))
		    {
			con = getORACLEConnection();
		    }
		else if (db1.equals("TIMESTEN"))
		    {
			con = getTIMESTENConnection();
		    }
		else if (db1.equals("SOLID"))
		    {
			con = getSOLIDConnection();
		    }
		else if (db1.equals("SYBASE"))
		    {
			con = getSYBASEConnection();
		    }
		else
		    {
			System.out.println("DATABASE NOT SELECTED");
			System.exit(0);
		    }
                
                //sunilg
                dbxmlutil = DBXmlUtility.getInstance(con);
                String parseFileName = PureUtils.rootDir+"conf"+"/"+"database_params.conf";
                File parseFile = new File(parseFileName);
                DBParamsParser parse = DBParamsParser.getInstance(parseFile);//Database related details are read to create a DB connection. 
                String url = parse.getURL();
                String user = parse.getUserName();
                String driver = parse.getDriverName();
                String passwd = parse.getPassword();
                RelationalAPI relapi = new RelationalAPI(url,user,passwd,driver,false);
                RelationalUtil.init(relapi);                
            }
        catch(Exception e)
	    {
		// return new OperationResult(userName,"Failed", "Failed", e.toString());
		e.printStackTrace();
	    }
	try
	    {
		//CustomViewUtilities for all are created to register in DBXmlUtility to get update for CV related tables.
		AlertCustomViewUtility acvu = new AlertCustomViewUtility(con);
		EventCustomViewUtility ecvu = new EventCustomViewUtility(con);
		TopoCustomViewUtility tcvu = new TopoCustomViewUtility(con);
		AuditCustomViewUtility aucvu = new AuditCustomViewUtility(con);
		PerfCustomViewUtility pcvu = new PerfCustomViewUtility(con);
		JdbcAPI jdbc = (JdbcAPI) JdbcAPIImpl.getAPI();

		PureServerUtils.getDatabaseParams();

		acvu.setJDBCAPI(jdbc);
		ecvu.setJDBCAPI(jdbc);
		tcvu.setJDBCAPI(jdbc);
		pcvu.setJDBCAPI(jdbc);
		aucvu.setJDBCAPI(jdbc);

		SeverityFEAPIImpl sevAPI = (SeverityFEAPIImpl)SeverityFEAPIImpl.getAPI();
		acvu.setSeverityAPI(sevAPI);
		ecvu.setSeverityAPI(sevAPI);
		tcvu.setSeverityAPI(sevAPI);
		pcvu.setSeverityAPI(sevAPI);
		aucvu.setSeverityAPI(sevAPI);

		//Register to update Custonview related table when DB is updated
		dbxmlutil.registerObjectForTable("Alerts", acvu);
		dbxmlutil.registerObjectForTable("Events", ecvu);
		dbxmlutil.registerObjectForTable("Network Database", tcvu);
		dbxmlutil.registerObjectForTable("Audit",aucvu);
		dbxmlutil.registerObjectForTable("Stats Admin", pcvu);                
	      
	    }
        catch(Exception e)
	    {
		System.out.println("unable to instantiate DBXmlUtility");
		e.printStackTrace();
		System.exit(0);
	    }

        
 
	/* try
	   {
            
	   //String sr = dbxmlutil.getPreviousNode("root","Alerts");
	   String sr = dbxmlutil.getPreviousNodeForNodeIndex("root","Fault",2); 
	   System.out.println("The previous node is ... "+sr);
	   }
	   catch(Exception e)
	   {
	   e.printStackTrace();
	   }   */    
        //sunil
        /*executeTestCase004();
        executeTestCase028();
        executeTestCase029();
        executeTestCase034();
        executeTestCase075();*/
        
        //executeTestCase084();
        //executeTestCase085();
        //executeTestCase086();
        //executeTestCase087();
        executeTestCase108();
	executeTestCase122();
        //sunil

	// System.out.println(" DBXmlUpdate object is successfully created");
        /*executeTestCase025();
          
       	executeTestCase001();
	executeTestCase002();
	executeTestCase012();
        executeTestCase026();
	executeTestCase029_1();
	executeTestCase075();
	executeTestCase109();
	executeTestCase116();

	executeTestCase123();
	executeTestCase124();
	System.out.println();*/
        System.exit(0);
    }

    private Connection getMYSQLConnection()
    {
        try
	    {
		Class.forName("org.gjt.mm.mysql.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost/WebNmsDB","root",null);
	    }
        catch(Exception e)
	    {
		e.printStackTrace();
		return null;
	    }
    }
    
    private Connection getORACLEConnection()
    {
        try
	    {
            
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@kernel-win:1521:oracle","BFW3","BFW3");
	    }
        catch(Exception e)
	    {
		e.printStackTrace();
		return null;
	    }      
    }

    private Connection getTIMESTENConnection()
    {
        try
	    {
		Class.forName("com.timesten.jdbc.TimesTenDriver");
		return DriverManager.getConnection("jdbc:timesten:direct:WebNmsDB","root",null);
	    }
        catch(Exception e)
	    {
		e.printStackTrace();
		return null;
	    }
    }
    
    private Connection getSOLIDConnection()
    {
        try
	    {
		Class.forName("solid.jdbc.SolidDriver");
		return DriverManager.getConnection("jdbc:solid://nms-clienttest1:1313/dba/dba","dba","dba"); 
	    }
        catch(Exception e)
	    {
		e.printStackTrace();
		return null;
	    }
    }

    private Connection getSYBASEConnection()
    {
        try
	    {
		Class.forName("com.sybase.jdbc2.jdbc.SybDriver");
		return DriverManager.getConnection("jdbc:sybase:Tds:fe-test:2048/feDB","sa",null);
	    }
        catch(Exception e)
	    {
		e.printStackTrace();
		return null;
	    }
    }

    private void executeTestCase001()
    {
	try
	    {	
		Vector v = dbxmlutil.getAllNodeID("root","Events");
		if( v!=null )
		    {
			System.out.println("CATS-JCF-API-DXU-001   ---> PASSED");
		    }
		else
		    {
			System.out.println("CATS-JCF-API-DXU-001   ---> FAILED");
		    }
	    }
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }
    private void executeTestCase002()
    {
	try
	    {
		Vector v = dbxmlutil.getAllNodeID(null,null);
	    }
	catch(Exception e)
	    {
		//e.printStackTrace();
		System.out.println("CATS-JCF-API-DXU-002   ---> PASSED");
	    }
	System.out.println("CATS-JCF-API-DXU-002   ---> FAILED");
    }

    private void executeTestCase004()
    {

            try
            {
                System.out.println("Properties "+dbxmlutil.getAllAttributes("test", "Network Database"));
            }
        catch(Exception es)
	    {
		// return new OperationResult(userName,"Failed", "Failed", es.toString());
		//es.printStackTrace();
		es.printStackTrace();
	    }
    }

    private void executeTestCase012()
    {
	try
	    {
		Properties viewProperties  = new Properties();
		viewProperties.put("ICON-FILE","images/tick.png");
		viewProperties.put("TREE-NAME","MKG");
		viewProperties.put("ID","Events");
		viewProperties.put("PARENT","Fault");

		Properties panelProperties = new Properties();
		panelProperties.put("PARENT","Maps");

		//adding newNode
		boolean add1 = dbxmlutil.addNode("newNode_12","LEVEL-1","All","Fault","Default",viewProperties,panelProperties);		  
		//removing newNode
		boolean remove = dbxmlutil.removeNode("newNode_12","All","Fault",true);
		
		//readding the newNode
		boolean add2 = dbxmlutil.addNode("newNode_12","LEVEL-1","All","Fault","Default",viewProperties,panelProperties);		  
		if(add2==true)
		    {
			System.out.println("CATS-JCF-API-DXU-012   ---> PASSED");
		    }
		else
		    {
			System.out.println("CATS-JCF-API-DXU-012   ---> FAILED");
		    }
		//removing newNode
		boolean remove1 = dbxmlutil.removeNode("newNode_12","All","Fault",true);
	    }
	catch(Exception e)
	    {
		//e.printStackTrace();
	    }
    }

    private void executeTestCase025()
    {
	try
	    {
		Properties viewProperties  = new Properties();
		viewProperties.put("ICON-FILE","images/tick.png");
		viewProperties.put("TREE-NAME","MKG");
		viewProperties.put("ID","Events");
		viewProperties.put("PARENT","Fault");

		Properties panelProperties = new Properties();
		panelProperties.put("PARENT","Maps");
		boolean add25 = dbxmlutil.addNode("newNode_25","LEVEL-1","All","Fault","Default",viewProperties,panelProperties);
		boolean node = dbxmlutil.modifyNode("newNode_25","All","Default",viewProperties,panelProperties);
		boolean remove25 = dbxmlutil.removeNode("newNode","All","Fault",true);
		if(node==true)
		    {
			System.out.println("CATS-JCF-API-DXU-025   ---> PASSED");
		    }
		else
		    {
			System.out.println("CATS-JCF-API-DXU-025   ---> FAILED");
		    }
	    }
	catch(Exception e)
	    {
		//e.printStackTrace();
	    }
    }

    private void executeTestCase026()
    {
	try
	    {
		Properties viewProperties  = new Properties();
		viewProperties.put("ICON-FILE","images/tick.png");
		viewProperties.put("TREE-NAME","MKG");
		viewProperties.put("ID","Events");
		viewProperties.put("PARENT","Fault");
		Properties panelProperties = new Properties();
		panelProperties.put("PARENT","Maps");
		
		boolean add26 = dbxmlutil.addNode("newNode_26","LEVEL-1","All","Fault","Default",viewProperties,panelProperties);
		boolean node261 = dbxmlutil.modifyNode("newNode","All","Default",viewProperties,panelProperties);

		boolean remove26 = dbxmlutil.removeNode("newNode_26","All","Fault",true);
		boolean node26 = dbxmlutil.modifyNode("newNode","All","Default",viewProperties,panelProperties);

		//System.out.println("node261 , node26"+node261+node26);
		if(node26)
		    {
			System.out.println("CATS-JCF-API-DXU-026   ---> FAILED");
		    }
		else
		    {
			System.out.println("CATS-JCF-API-DXU-026   ---> PASSED");
		    }
	    }
	catch(Exception e)
	    {
		//e.printStackTrace();
	    }
    }

    private void executeTestCase028()
    {
	try
	    {
		boolean movenode = dbxmlutil.moveNode("ipnet.netmap","All","WebNMS-Panels");
		if(movenode)
		    {		
			System.out.println("CATS-JCF-API-DXU-028   ---> PASSED");
		    }
		else
		    {
			System.out.println("CATS-JCF-API-DXU-028   ---> FAILED");
		    }
	    }
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }

    private void executeTestCase029()
    {
	try
	    {
		boolean movenode = dbxmlutil.moveNode("ipnet.netmap","All","Maps");
                

                if(movenode)
		    {		
			System.out.println("CATS-JCF-API-DXU-028   ---> PASSED");
		    }
                else
		    {
			System.out.println("CATS-JCF-API-DXU-028   ---> FAILED");
		    }
	    }
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }

    private void executeTestCase034()
    {
	try
	    {
                System.out.println("Properties "+dbxmlutil.getNodeAttributes("Network Database", "root","Network Database"));

            }
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }
    
    private void executeTestCase075()
    {
	try
	    {
		String rootnode = dbxmlutil.getRootNodeID("root",null);

		if(rootnode.equals("AdventNet"))
		    {
			System.out.println("CATS-JCF-API-DXU-075   ---> PASSED");
		    }
		else
		    {
			System.out.println("CATS-JCF-API-DXU-075   ---> FAILED");
		    }
	    }
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }    

    private void executeTestCase084()
    {
	try
	{
            Properties viewProperties  = new Properties();
            viewProperties.put("ICON-FILE","images/tick.png");
            viewProperties.put("TREE-NAME","MKG");
            viewProperties.put("ID","Maps_Val22");
            viewProperties.put("PARENT","Maps");
            Properties panelProperties = new Properties();
            panelProperties.put("PARENT","Maps");
            
            boolean add1 = dbxmlutil.addNode("Maps_Val22","LEVEL-1","All","Maps","Default",viewProperties,panelProperties);
            
            if(add1)
	    {
                System.out.println("CATS-JCF-API-DXU-084   ---> PASSED");
            }
            else
	    {
                System.out.println("CATS-JCF-API-DXU-084   ---> FAILED");
            }
            
        }
	catch(Exception e)
	 {
             e.printStackTrace();
         }
    }    

    
    private void executeTestCase085()
    {
	try
	{
            Properties viewProperties  = new Properties();
            viewProperties.put("ICON-FILE","images/AdventNet.jpg");
            viewProperties.put("TREE-NAME","MKG");
            viewProperties.put("ID","Map_Val22");
            //viewProperties.put("PARENT","Fault");
            Properties panelProperties = new Properties();
            //panelProperties.put("PARENT","Fault");
            
            boolean add1 = dbxmlutil.modifyNode("Map_Val22","All","Default",viewProperties,panelProperties);
            
            if(add1)
	    {
                System.out.println("CATS-JCF-API-DXU-085   ---> PASSED");
            }
            else
	    {
                System.out.println("CATS-JCF-API-DXU-085   ---> FAILED");
            }
            
        }
	catch(Exception e)
	 {
             e.printStackTrace();
         }
    }    

    private void executeTestCase086()
    {
	try
	{
            boolean add1 = dbxmlutil.removeNode("Fault","root","Default",false);
            
            if(add1)
	    {
                System.out.println("CATS-JCF-API-DXU-086   ---> PASSED");
            }
            else
	    {
                System.out.println("CATS-JCF-API-DXU-086   ---> FAILED");
            }
            
        }
	catch(Exception e)
	 {
             e.printStackTrace();
         }
    }    
    
    private void executeTestCase087()
    {
	try
	{
            boolean add1 = dbxmlutil.removeNode("Fault","root","Default",true);
            
            if(add1)
	    {
                System.out.println("CATS-JCF-API-DXU-086   ---> PASSED");
            }
            else
	    {
                System.out.println("CATS-JCF-API-DXU-086   ---> FAILED");
            }
            
        }
	catch(Exception e)
	 {
             e.printStackTrace();
         }
    }    

    private void executeTestCase088()
    {
	try
	{
            boolean movenode = dbxmlutil.moveNode("ipnet.netmap","All","WebNMS-Panels");
            
            if(movenode)
	    {
                System.out.println("CATS-JCF-API-DXU-088   ---> PASSED");
            }
            else
	    {
                System.out.println("CATS-JCF-API-DXU-088   ---> FAILED");
            }
            
        }
	catch(Exception e)
	 {
             e.printStackTrace();
         }
    }    

    private void executeTestCase108()
    {
	try
	    {
		boolean root = dbxmlutil.isRootNodeExist("root","Maps" );
                //System.out.println("Root Node     = " +root);
		if(root)
		    {  
			System.out.println("CATS-JCF-API-DXU-108   ---> PASSED");
		    }
		else
		    {
			System.out.println("CATS-JCF-API-DXU-108   ---> FAILED");
		    }
	    }
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }

    private void executeTestCase109()
    {
	try
	    {
		boolean root109 = dbxmlutil.isRootNodeExist("root","SHIVA" );     
                
		if(root109)
		    {
			System.out.println("CATS-JCF-API-DXU-109   ---> FAILED");
		    }
		else
		    {
			System.out.println("CATS-JCF-API-DXU-109   ---> PASSED");
		    }
	    }
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }

    private void executeTestCase116()
    {
	try
	    {
		String last = dbxmlutil.getLastChild("root","shivaprakash"); 
                
		if(last=="START")
		    {

			System.out.println("CATS-JCF-API-DXU-116   ---> PASSED");
		    }
		else
		    {
			System.out.println("CATS-JCF-API-DXU-116   ---> FAILED");
		    }

	    }
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }

    private void executeTestCase122()
    {
	try
	    {
		Hashtable hash = dbxmlutil.getAllPanelAttribute("root" );
                System.out.println("The all panel attributes are     = " +hash);  
		if(hash != null)
		    {
			System.out.println("CATS-JCF-API-DXU-122   ---> PASSED");
		    }
		else
		    {
			System.out.println("CATS-JCF-API-DXU-122   ---> FAILED");
		    }
	    }
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }

    private void executeTestCase123()
    {
	try
	    {
		Hashtable hash = dbxmlutil.getAllPanelAttribute("SHIVA" );      
		int s = (int)hash.size();
		if(s == 0)
		    {
			System.out.println("CATS-JCF-API-DXU-123   ---> PASSED");
		    }
		else
		    {
			System.out.println("CATS-JCF-API-DXU-123   ---> FAILED");
		    }
	    }
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }

    private void executeTestCase124()
    {
	try
	    {
		Hashtable hash = dbxmlutil.getAllPanelAttribute(null);   
                int s = (int)hash.size();
		if(s == 0) 
		    {
			System.out.println("CATS-JCF-API-DXU-124   ---> PASSED");
		    }
		else
		    {
			System.out.println("CATS-JCF-API-DXU-124   ---> FAILED");
		    }
	    }
	catch(Exception e)
	    {
		//e.printStackTrace();
	    }
    }



    public static void main(String[] args)
    {
	System.out.println();
	System.out.println();
	System.out.println("  REPORT OF CF_API_DXU TEST PLAN  ");
	System.out.println();

	DBXmlUtilityTest test = new DBXmlUtilityTest();

	if (args.length == 2)
	    {
		test.startProcess(args[0],args[1]);
	    }
	else
	    {
		System.out.println("USAGE: java DBXmlUpdateTest <Full path of WebNMS_Home> DATABASE(MYSQL/SOLID/TIMESTEN/ORACLE/SYBASE)");
		System.out.println();
		
	    }
    }
}


    
/**


   public DBXmlUtilityTest()
   {
   
   try
   {
   
   Connection con = null;
   DBXmlUpdate dbxmlupdate = null;
   
   try
            {
            
            Class.forName("org.gjt.mm.mysql.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/WebNmsDB","","");
            System.out.println("CONNECTION...:  "+con);
            
            Class.forName("solid.jdbc.SolidDriver");
            con=DriverManager.getConnection("jdbc:solid://nms-clienttest1:1313/dba/dba","dba","dba");
            System.out.println("CONNECTION...:  "+con);


            }
            catch(Exception ce)
            {
            System.out.println(ce.getMessage());
            ce.printStackTrace();
            }
            
            dbxmlutil = DBXmlUtility.getInstance(con);
            System.out.println("Instance got... :   " +dbxmlutil);
            
            //public boolean addNode(String nodeId,String nodeType,String userName,String parent,String moduleName,Properties panelProperties, Properties viewProperties) throws NmsStorageException
            Properties panelProperties  = new Properties();
               panelProperties.put("ICON-FILE","images/small.png");
               panelProperties.put("TREE-NAME","ramlaxman")
               ;
               //panelProperties.put("PARENT","Maps");
               Properties viewProperties = new Properties();
               
               boolean result=false;
               try
               {
               result = db.updateNode("node6","root",panelProperties);
               }
            catch(Exception e)
            {
            e.printStackTrace();
            }
            System.out.println(" result = " + result);
             
            //Properties viewProperties = new Properties();
            //  String viewProperties = db.getRootNodeID("root","Maps");
            //              System.out.println("View......"+viewProperties);
            //              Properties props = new Properties();
            //              props.put("PARENT","Events");
            //              Properties views = new Properties();
            //              views.put("ICON-FILE","images/i18n.jpg");
            //              boolean node = db.modifyNode("Test2","root","Device",props,views);
            //              System.out.println("Create node...."+node);
            
            //boolean node = db.removeNode("Test","All","Events",true);
            //System.out.println("Create node...."+node);
            //Vector v = db.getAllNodeId("root","Events");
            //System.out.println("All Node ID..."+v);
            //Hashtable hash =null;
            //treeAPI = new TreeAPI(con,true);
            
            // String name="//ramarb/TreeAPI";
            //String name="//192.168.15.55/TreeAPI";
            // nmsTreeAPI = (NmsTreeAPI) Naming.lookup(name);
            // System.out.println("Successfully created the tree api instance");
            //System.out.println("Successfully got the tree api handle");
            Properties viewProperties  = new Properties();
            viewProperties.put("ICON-FILE","images/tick.png");
            viewProperties.put("TREE-NAME","MKG");
            viewProperties.put("ID","Events");
            viewProperties.put("PARENT","Fault");
            //boolean result = dbxmlutil.updateNode("Events","root",viewProperties);

           
            Properties panelProperties = new Properties();
            panelProperties.put("PARENT","Maps");

            //Properties attributeList = new Properties();
            
            //attributeList.put("ID","Events");
            //attributeList.put("PARENT","Fault");

            //viewProperties.put("attributeList",attributeList);


            //System.out.println("Updated .... "+result);
            //boolean movenode = dbxmlutil.moveNode("ipnet.netmap","All","WebNMS-Panels");
            //System.out.println("Moved Node  : " +movenode);
            //boolean node = dbxmlutil.modifyNode("Alerts","All","Default",viewProperties,panelProperties);
            //System.out.println("Modify node...."+node);
            //boolean node = dbxmlutil.removeNode("192.168.0.0.netmap","All","Default",true);     
            //System.out.println("remove node...."+node);
            //boolean root = db.addNode("BRAMAR","LEVEL-1","All","Fault","Default",viewProperties,panelProperties);
            //System.out.println(" root = " + root);
            //int nodeCount = db.getNodeCount("root");
            //System.out.println("node count is:..."+nodeCount);
            //Properties hash = db.getNodeAttributes("ipnet.netmap","root","Maps");
            //System.out.println(" prop = " + hash);
            //Properties hash = db.getAllAttributes("guest",null);
            //System.out.println(" prop = " + hash);
            //String lastChild = db.getLastChild("root","Maps");
            //System.out.println("The last child is:..."+lastChild);
            //boolean nodeExists = db.isRootNodeExist("root","AdventNet");
            //System.out.println(" nodeExists = " + nodeExists);
            //int index = db.getMaxChildIndex("root","Maps");
            //System.out.println("index are..."+index);
            //boolean delete = db.deleteAllTraces("Default","guest");
            //System.out.println("Delete all traces..: "+delete);
            //boolean isnodeexist = db.isNodeExist("root",null);
            //System.out.println("Node Exist : "+isnodeexist);
            //int nodeindex = db.getNodeIndex("root","Events");
            //System.out.println("node index is : "+nodeindex);
            //boolean data = db.isDataExist("root");
            //System.out.println("data exists : "+data);
            //boolean userentry = dbxmlutil.removeUserEntry("guest");
            //System.out.println("User entry removed..: "+userentry);
            //Hashtable paneltable = db.getPanelTreeData(null);
            //System.out.println("Panel table " +paneltable);
            //String rootnode = dbxmlutil.getRootNodeID("root",null);
            //System.out.println("Root node is "+rootnode);
            //String previousnode = db.getPreviousNode(null,null);
            //System.out.println(" The previous node is : "+previousnode);
            //String previousnode = dbxmlutil.getPreviousNodeForNodeIndex("root","Fault",1);
            //System.out.println(" The previous node for the given index is : "+previousnode);
            //Hashtable attributes = db.getAllPanelAttribute(null);
            //System.out.println(" All Panel Attributes are : " +attributes);
            
            PureUtils.rootDir="F:/AdventNet/WebNMS/";
            PureUtils.usersDir="F:/AdventNet/WebNMS/";

            
            try
            {
            Class.forName(dbDriver);
            con=DriverManager.getConnection(url,userName,password);
            }
               catch(Exception e)
               {
               System.out.println(" Exception while getting the DB
               Connection ");
               e.printStackTrace();
               System.out.println(e.getMessage());
           }
           
                      
           
           //dbxmlupdate = new DBXmlUpdate(con);
           
           dbxmlutil = DBXmlUtility.getInstance(con);
           try
           {
           //CustomViewUtilities for all are created to register in
           //DBXmlUtility to get update for CV related tables.
           AlertCustomViewUtility acvu = new AlertCustomViewUtility(con);
           EventCustomViewUtility ecvu = new EventCustomViewUtility(con);
           TopoCustomViewUtility tcvu = new TopoCustomViewUtility(con);
           
           AuditCustomViewUtility aucvu = new AuditCustomViewUtility(con);
           PerfCustomViewUtility pcvu = new PerfCustomViewUtility(con);
               
           JdbcAPI jdbc = new JdbcAPIImpl();
           
           PureServerUtils.getDatabaseParams();
           
           acvu.setJDBCAPI(jdbc);
           ecvu.setJDBCAPI(jdbc);
           tcvu.setJDBCAPI(jdbc);
           pcvu.setJDBCAPI(jdbc);
           aucvu.setJDBCAPI(jdbc);
           
           SeverityFEAPIImpl sevAPI =(SeverityFEAPIImpl)SeverityFEAPIImpl.getAPI();
           acvu.setSeverityAPI(sevAPI);
           ecvu.setSeverityAPI(sevAPI);
           tcvu.setSeverityAPI(sevAPI);
           pcvu.setSeverityAPI(sevAPI);
           aucvu.setSeverityAPI(sevAPI);
           
           //Register to update Custonview related table when DB is
           dbxmlutil.registerObjectForTable("Alerts", acvu);
           dbxmlutil.registerObjectForTable("Events", ecvu);
           dbxmlutil.registerObjectForTable("Network Database", tcvu);
           dbxmlutil.registerObjectForTable("Audit",aucvu);
           dbxmlutil.registerObjectForTable("Stats Admin", pcvu);
           System.out.println(dbxmlutil.getNodeAttributes("Events","root","Default"));
           //System.out.println(dbxmlutil.getAllAttributes("root","Events"));
               
           }
           catch(Exception es)
           {
               es.printStackTrace();
           }
           
           //EventCustomViewUtility ecvu = new EventCustomViewUtility(con);
           //  db.registerObjectForTable("Events", ecvu);
           //  System.out.println(db.getNodeAttributes("Events","root","Events")); 
           
           
           }
           catch(Exception e)
           {
           System.out.println(" Exception occured while creating NmsTreeAPI ");
           e.printStackTrace();
           }
           
    }
   
**/

    
