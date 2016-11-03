// $Id: MainScreen.java,v 1.2 2006/12/11 15:13:56 jegannathan Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.runtimeconfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

// import com.adventnet.nms.tools.discoveryconfig.DiscoveryConfigSession;  //Modified for Build Compilation
import com.adventnet.nms.util.*;
import com.adventnet.nms.util.CommonBuilderUIInterface;
import com.adventnet.nms.startclient.AbstractNmsPanel;
import com.adventnet.nms.startnms.GenericConstants;
import com.adventnet.nms.runtimeconfig.ApplyToServerInterface;

public class MainScreen extends JPanel implements ResultEventListener,ApplyToServerInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel mainPanel = null;
	javax.swing.JTabbedPane tabbedPane = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	SeedParser sParser = null;
    XMLNode rootNode = null;
    GeneralScreen genScr = null;
    RediscoveryScheduler rediscSch = null;
    InitialParametersScreen initScr = null;
    ProtocolScreen protoScr = null;
    SNMPScreen snmpScr = null;
    ICMPScreen icmpScr = null;
    NativePingScreen natPingScr = null;
    NetworkDiscoveryScreen netDiscScr = null;
    NodeDiscoveryScreen nodeDiscScr = null;
    CriteriaScreen critScr = null ;
    ResultEventAdapter reAdapter = null;
    private static String uiClassName = null;
    static JButton applyButton = null;
    static boolean isClient = false;
    boolean  isModified = false;
    boolean refetchFlag = false;
    static JFrame frame = null;




  	public MainScreen()
  {
    //<Begin_MainScreen>
    this.init();
  
    //<End_MainScreen>
  }


    public static void setParentObject(JFrame jf)
    {
        frame = jf;
    }


    public MainScreen(String className,String interfaceClassName)
	{
		//Used by stand alone Discovery Configurator
		this(className,interfaceClassName,false,null);
	}			

	
    public MainScreen(String className,String interfaceClassName,boolean isClient , java.applet.Applet appl)
	{
		//Used in Client
		this.applet = appl;
        this.isClient = isClient;
        try
        {
            uiClassName = interfaceClassName;
            Class adapterClass = Class.forName(className);
            Method meth = adapterClass.getMethod("getInstance",null);
            reAdapter = (ResultEventAdapter)meth.invoke(null,null);
            reAdapter.register(this); 
            getTheSeedFile();
        }
        catch(java.lang.Exception ex)
        {
            ex.printStackTrace();
        }
    }	

	public Vector rememberCommentNode()
	{
		XMLNode seed = null;
		Vector  seedVector  = sParser.getXMLNode("SEED");
		Vector childNodes = null;
		if(seedVector != null)
		{
			for(int i = 0; i < seedVector.size(); i++)
			{
				seed = (XMLNode)seedVector.elementAt(i);
			}
			childNodes = seed.getChildNodes();
		}
		return childNodes;
	}

  public MainScreen(java.applet.Applet applet)
  {
    //<Begin_MainScreen_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_MainScreen_java.applet.Applet>
	  }

 
    public void start()
  { 

  //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        mainPanel= new javax.swing.JPanel();
        tabbedPane= new javax.swing.JTabbedPane();

  
        //<End_initVariables>
  } 

  public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY )
  { 

  //<Begin_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int> 
	cons.gridx = x;
	cons.gridy = y;
	cons.gridwidth = width;
	cons.gridheight = height;
	cons.weightx = wtX;
	cons.weighty = wtY;
	cons.anchor = anchor;
	cons.fill = fill;
	cons.insets = inset;
	cons.ipadx = padX;
	cons.ipady = padY;
	
  
         //<End_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int>
  } 
    
    public static boolean exit = true;
    
    
    public void deRegister()
    {
        reAdapter.deRegister(this);
    }

    
    public void getTheSeedFile()
    {
        try
        {
            if(resourceBundle==null){
                if(getParameter("RESOURCE_PROPERTIES" ) != null)
                    {
                        localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
                    }
                resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
            }
            if(isClient && !refetchFlag)
            {
            	com.adventnet.nms.runtimeconfig.RuntimeConfigFrame.setBusyCursor(this);
            	com.adventnet.nms.runtimeconfig.RuntimeConfigFrame.setStatusText(resourceBundle.getString("Loading Discovery Configurator...."));
            }		
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream outp = new DataOutputStream(byteStream);
            outp.writeInt(GenericConstants.SEED_FILE );
            outp.writeInt(GenericConstants.GET);
            outp.flush();
            byte[] bytes = byteStream.toByteArray();
            outp.close();
            byteStream.reset();
            byteStream.close(); 
            reAdapter.send(bytes);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }                  
    }
//changed for rta memory issue fix 

    public boolean  applyToServer()
	{
       try
       {
		if(isClient)
		{
			if(!isModified())
			{
				com.adventnet.nms.runtimeconfig.RuntimeConfigFrame.setStatusText(resourceBundle.getString("No changes to apply"));
				return true;
			}
		}
	
		writeToTheSeedFile(getTheModifiedRootNode());
		setIsModified(false);
	
		// Fix starts for - No status message while the changes applied successfully
	
		com.adventnet.nms.runtimeconfig.RuntimeConfigFrame.setStatusText(resourceBundle.getString("Configuration changes applied to Server successfully"));
	
		// Fix ends for - No status message while the changes applied successfully

       }
       catch(Exception exce)
       {
         exce.printStackTrace(); //added   
         return false;  
       }
       return true;
	}
//end of change    

    public boolean isModified()
    {
        return getIsModified();
    }

    public void writeToTheSeedFile(XMLNode modifyNode )
    {
        try
        {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream outp = new DataOutputStream(byteStream);
            outp.writeInt(GenericConstants.SEED_FILE );
            outp.writeInt(GenericConstants.SET);
            if(modifyNode != null)
            {
                byte[] serverData = NmsClientUtil.serializeObject(modifyNode );
                int len = serverData.length;
                outp.writeInt(len);
                outp.write(serverData,0,len);  
            }
            outp.flush();
            byte[] bytes = byteStream.toByteArray();
            outp.close();
            byteStream.reset();
            byteStream.close(); 
            reAdapter.send(bytes);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }                  
    }
    public void callBack(ResultEventObject evt)
    {
        //Response From Server Comes Here
        DataInputStream inp = null;
        ByteArrayInputStream bais = evt.getResultEventData();
        try
        {
            inp = new DataInputStream(bais);
            int type = evt.getEventType();
            if(type == GenericConstants.SEED_FILE)
            {
                int operationIndex = inp.readInt();
                //Return the value to the UI
                switch(operationIndex)
                {
                case GenericConstants.ADD:
                    {
                        //updateTheUI(); 
                        break;    
                    }
                case GenericConstants.DELETE:
                    {
                        //updateTheUI(); 
                        break;    
                    }
                case GenericConstants.MODIFY:
                    {
                        //updateTheUI(); 
                        break;    
                    }
                case GenericConstants.SET:
                    {
                        //updateTheUI(); 
                        break;    
                    }
                case GenericConstants.RELOAD:
                    {
                        //updateTheUI(); 
                        break;    
                    }
                case GenericConstants.GET:
                    {
                        int datalength = inp.readInt();
                        byte xmlarr[] = new byte[datalength];
                        inp.readFully(xmlarr);
                        XMLNode rootNode = (XMLNode)NmsClientUtil.deSerializeObject(xmlarr);
                        init(new SeedParser(rootNode));
                        if(isClient)
                        {
                            com.adventnet.nms.runtimeconfig.RuntimeConfigFrame.setDefaultCursor(this);
                            com.adventnet.nms.runtimeconfig.RuntimeConfigFrame.setStatusText(resourceBundle.getString("Done"));
                        }	
                        break;    
                    }
                default:
                    {
                        break;
                    }

                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

	public String getNmsHome() 
    {
        return System.getProperty("user.dir");
    }
    

   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(2,2,2,2);
setConstraints(0,0,0,0,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(mainPanel,cons);
mainPanel.setLayout(new BorderLayout(5,5));
mainPanel.add(tabbedPane,BorderLayout.CENTER);

  
//<End_setUpGUI_Container>
	if(isClient)
        {
            JPanel applyPanel = new JPanel();
            applyPanel.setLayout(new FlowLayout(2,5,5));
            applyButton = new JButton(resourceBundle.getString("Apply"));
            applyPanel.add(applyButton);
            mainPanel.add(applyPanel,BorderLayout.SOUTH);
        }

  } 
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            tabbedPane.setFont(new Font("Dialog",1,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tabbedPane,ex); 
          }

//<UserCode_Begin_Bean_tabbedPane>

//<UserCode_End_Bean_tabbedPane>

  
          //<End_setUpProperties>
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
	if(isClient)
        {
            applyButton.addMouseListener(new applyInnerClass());
            applyButton.addKeyListener(new applyInnerKeyClass());
        }
  } 
 class applyInnerClass extends MouseAdapter implements Serializable
    {
        public void mouseClicked(MouseEvent me)
        {
            applyToServer();
        }
    }

    class applyInnerKeyClass extends KeyAdapter implements Serializable {
        public void keyPressed(KeyEvent ke) {
            int keyCode = ke.getKeyCode();
            //Allow for Keys "Enter" and "SpaceBar"
            if( (keyCode == ke.VK_ENTER) || (keyCode == ke.VK_SPACE) ) {
                applyToServer();
            }
        }
    }

  public void stop()
  { 

  //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
  } 
   
  public String getParameter(String input)
  { 

  //<Begin_getParameter_String> 
           String value = null;
           if ( applet != null)
           {    
                 value = applet.getParameter(input);
           }    
           else
           {    
                 value = (String)com.adventnet.apiutils.Utility.getParameter(input);
           }    
           if(value == null)
           {
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources"; 
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
  } 


	

	public void init()
  {
		//<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+567,getPreferredSize().height+333)); 
        setSize(getPreferredSize()); 
        Container container = this;
        container.setLayout(new BorderLayout()); 
        try 
        { 
          initVariables(); 
          setUpGUI(container); 
          setUpProperties(); 
          setUpConnections(); 
        } 
        catch(Exception ex) 
        { 
          showStatus(resourceBundle.getString("Error in init method"),ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
		
		}

  	public void init(SeedParser parser)
	{
		//mtest	resourceBundle = com.adventnet.apiutils.Utility.getBundle(getParameter("RESOURCE_PROPERTIES"),applet);
		this.init();
		sParser = parser;
		rediscSch = new RediscoveryScheduler(sParser,frame);
		rediscSch.init(applet);
		rediscSch.setModal(true);
		initScr = new InitialParametersScreen(sParser,frame);
		initScr.init(applet);
		initScr.setModal(true);
		genScr = new GeneralScreen(rediscSch,initScr,sParser);
		genScr.init(applet); 
		snmpScr = new SNMPScreen(sParser,frame);
		snmpScr.init(applet);
		snmpScr.setModal(true);
		icmpScr = new ICMPScreen(sParser,frame);
		icmpScr.init(applet);
		icmpScr.setModal(true);
		natPingScr = new NativePingScreen(sParser,frame);
		natPingScr.init(applet);
		natPingScr.setModal(true);
		protoScr = new  ProtocolScreen(snmpScr,icmpScr,natPingScr);
		protoScr.init(applet);
		netDiscScr = new NetworkDiscoveryScreen(sParser);
		netDiscScr.init(applet);
		nodeDiscScr = new NodeDiscoveryScreen(sParser);
		nodeDiscScr.init(applet);
		critScr = new CriteriaScreen(sParser);
		critScr.init(applet);


		tabbedPane.addTab(resourceBundle.getString("General"),genScr);
		tabbedPane.addTab(resourceBundle.getString("Protocol"),protoScr);
		tabbedPane.addTab(resourceBundle.getString("Network Discovery"),netDiscScr);
		tabbedPane.addTab(resourceBundle.getString("Node Discovery"),nodeDiscScr);
		tabbedPane.addTab(resourceBundle.getString("Criteria"),critScr);
		genScr.populatingScreen();
		netDiscScr.populatingScreen();
		nodeDiscScr.populatingScreen();
		critScr.populateObjectAllowCrit();
		critScr.populateObjectDisallowCrit();
	}

   /**
     * Get the value of IsModified.
     * @return value of IsModified.
     */
    public boolean  getIsModified() 
    {
        if(genScr != null && protoScr != null && netDiscScr!= null && nodeDiscScr!= null && critScr != null)
		{
			if(genScr.getIsModified() || protoScr.getIsModified() || netDiscScr.getIsModified() || nodeDiscScr.getIsModified() || critScr.getIsModified())
			{
				isModified = true;
			}
			else
			{
				isModified = false;
			}
		}
        return isModified;
    }
    
    /**
     * Set the value of IsModified.
     * @param v  Value to assign to IsModified.
     */
    public void setIsModified(boolean val) 
    {
        isModified = val;
        genScr.setIsModified(val);
        protoScr.setIsModified(val);
        netDiscScr.setIsModified(val);
        nodeDiscScr.setIsModified(val);
        critScr.setIsModified(val);
    }
    
    public void showingAutoDiscovery()
    {
	if(isClient)
        genScr.showingAutoDiscovery();
    }

    private static CommonBuilderUIInterface cbui = null;
    
    public static CommonBuilderUIInterface getCommonInterface()
    {
        if(cbui == null)
        {
            try
            {
                Class newClass = Class.forName(uiClassName);
                cbui = (CommonBuilderUIInterface)newClass.newInstance();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return cbui;
    }


    public XMLNode getTheModifiedRootNode()
    {
        rootNode = new XMLNode();
        rootNode.setNodeType(XMLNode.ELEMENT);	
        rootNode.setNodeName("SEED");
        
	Vector childNodes = rememberCommentNode();
	if(childNodes != null)
	{
	   for(int k = 0; k< childNodes.size(); k++)
	  {	
		XMLNode commentNodes = (XMLNode)childNodes.elementAt(k);
		if(commentNodes!= null && commentNodes.getNodeType() == XMLNode.COMMENT)
		{
			rootNode.addChildNode(commentNodes);
		}
	}
	}
      /* XMLNode[] commentNode = rememberCommentNode();
        for(int i = 0; i < commentNode.length; i++)
	{
	   rootNode.addChildNode(commentNode[i]);
	}*/


        XMLNode discovery = new XMLNode();
        discovery.setNodeType(XMLNode.ELEMENT);	
        discovery.setNodeName("DISCOVERY");

        Hashtable tableGen = genScr.addToXMLNode();
        Hashtable tableNewSNMP = snmpScr.addToXMLNode();
        Hashtable tableOldSNMP = snmpScr.oldAttributeValues();
        Hashtable tableNewICMP = icmpScr.addToXMLNode();
        Hashtable tableOldICMP = icmpScr.oldAttributeValues();
        Hashtable tableWeek = rediscSch.addDayOfTheWeek();
        Hashtable tableMonth = rediscSch.addDayOfTheMonth();
        Hashtable tableHour = rediscSch.addHourOfTheDay();
        Hashtable tableRedisc = rediscSch.addRediscoveryInterval();
        
        if(snmpScr.isOK_Clicked() == true)
        {
            if(tableNewSNMP != null)
            {
                for(Enumeration en = tableNewSNMP.keys();en.hasMoreElements();) 
                {
                    String key = (String)en.nextElement();
                    String value = (String)tableNewSNMP.get(key);
                    tableGen.put(key,value);
                }
            }
        }
        else if(snmpScr.isOK_Clicked() == false)
        {
            if(tableOldSNMP != null)
            {
                for(Enumeration en = tableOldSNMP.keys();en.hasMoreElements();) 
                {
                    String key = (String)en.nextElement();
                    String value = (String)tableOldSNMP.get(key);
                    tableGen.put(key,value);
                } 
            }
        }

        if(icmpScr.isOK_Clicked() == true)
        {
            if(tableNewICMP != null)
            {
                for(Enumeration en = tableNewICMP.keys();en.hasMoreElements();) 
                {
                    String key = (String)en.nextElement();
                    String value = (String)tableNewICMP.get(key);
                    tableGen.put(key,value);
                }
            }
        }
        else if(icmpScr.isOK_Clicked() == false)
        {
            if(tableOldICMP != null)
            {
                for(Enumeration en = tableOldICMP.keys();en.hasMoreElements();) 
                {
                    String key = (String)en.nextElement();
                    String value = (String)tableOldICMP.get(key);
                    tableGen.put(key,value);
                }
            }
        }

        if(rediscSch.isOK_Clicked() == true) 
        {
            if(tableWeek != null)
            {
                for(Enumeration en = tableWeek.keys();en.hasMoreElements();) 
                {
                    String key = (String)en.nextElement();
                    String value = (String)tableWeek.get(key);
                    tableGen.put(key,value);
                }
            }
            if(tableMonth != null)
            {
                for(Enumeration en = tableMonth.keys();en.hasMoreElements();) 
                {
                    String key = (String)en.nextElement();
                    String value = (String)tableMonth.get(key);
                    tableGen.put(key,value);
                }
            }
            if(tableHour != null)
            {
                for(Enumeration en = tableHour.keys();en.hasMoreElements();) 
                {
                    String key = (String)en.nextElement();
                    String value = (String)tableHour.get(key);
                    tableGen.put(key,value);
                }
            }
            if(tableRedisc != null)
            {
                for(Enumeration en = tableRedisc.keys();en.hasMoreElements();) 
                {
                    String key = (String)en.nextElement();
                    String value = (String)tableRedisc.get(key);
                    tableGen.put(key,value);
                }
            }
        }
        else if(rediscSch.isOK_Clicked() == false) 
        {
            Hashtable oldHash = rediscSch.oldAttributeValues();
            if(oldHash != null)
            {
                for(Enumeration en = oldHash.keys();en.hasMoreElements();) 
                {
                    String key = (String)en.nextElement();
                    String value = (String)oldHash.get(key);
                    tableGen.put(key,value);
                }
            }
        }

        discovery.setAttributeList(tableGen);

        rootNode.addChildNode(discovery);
        
        if(natPingScr.isOK_Clicked() == true)
        {
            if(natPingScr.addToXMLNode() != null)
            {
                rootNode.addChildNode(natPingScr.addToXMLNode());
            }
        }
        if(natPingScr.isOK_Clicked() == false)
        {
            if(natPingScr.oldXMLNode() != null)
            {
                rootNode.addChildNode(natPingScr.oldXMLNode());
            }
        }
        
        if(initScr.isOK_Clicked() == true)
        {        
            if(initScr.addToXMLNode() != null)
            {
                rootNode.addChildNode(initScr.addToXMLNode());
            }
        }
        else if(initScr.isOK_Clicked() == false)
        {
            if(initScr.oldXMLNode() != null)
            {
                rootNode.addChildNode(initScr.oldXMLNode());
            }
        }

        Vector critNodes = critScr.addToXMLNode();
        
        for(int i=0; i<critNodes.size();i++)
        {
            XMLNode node = (XMLNode)critNodes.elementAt(i);
            Hashtable checkHash = node.getAttributeList();
            if(checkHash != null)
            {
                if(!checkHash.isEmpty())
                {
                    rootNode.addChildNode(node);
                }
            }
        }
        
        Vector netNodes = netDiscScr.addToXMLNode();
        
        for(int i=0;i<netNodes.size();i++)
        {
            XMLNode node = (XMLNode)netNodes.elementAt(i);
            Vector cnodes = node.getChildNodes();
            String nodeName = node.getNodeName().trim();
            XMLNode parent = new XMLNode();
            parent.setNodeName(nodeName);
            parent.setNodeType(XMLNode.ELEMENT);
            for(int j=0;j<cnodes.size();j++)
            {
                XMLNode child =  (XMLNode)cnodes.elementAt(j);
                Hashtable checkHash = child.getAttributeList();
                if(checkHash != null)
                {
                    if(!checkHash.isEmpty())
                    {
                        parent.addChildNode(child);
                    }
                }
            }
            if(parent.getChildCount() != 0)
            {
                rootNode.addChildNode(parent);
            }
        }
        
        Vector ipNodes = nodeDiscScr.addToXMLNode();

        
        for(int i=0; i<ipNodes.size();i++)
        {
            XMLNode node = (XMLNode)ipNodes.elementAt(i);
            Vector cnodes = node.getChildNodes();
            String nodeName = node.getNodeName().trim();
            XMLNode parent = new XMLNode();
            parent.setNodeName(nodeName);
            parent.setNodeType(XMLNode.ELEMENT);
            for(int j=0;j<cnodes.size();j++)
            {
                XMLNode child =  (XMLNode)cnodes.elementAt(j);
                Hashtable checkHash = child.getAttributeList();
                if(checkHash != null)
                {
                    if(!checkHash.isEmpty())
                    {
                        parent.addChildNode(child);
                    }
                }
            }

            if(parent.getChildCount()!= 0)
            {
                rootNode.addChildNode(parent);
            }
        }
        
        return rootNode;
    }

    public static class NmsPanelScreenWrapper extends com.adventnet.nms.startclient.AbstractNmsPanel
    {
  
        /** Returns a name for the Panel */
        public String key()
        {
            return "TabbedPane";
        }
        /** Returns the Name of the screen */
        public String getPanelClassName()
        {
            return "TabbedPane";
        }
    }   

  
  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }

 public void refetchData()
    {
        //refetchFlag = true;
        //getTheSeedFile();
    }

      public static void setApplyButton(boolean b)
	{
		if(applyButton!=null)
		applyButton.setEnabled(b);
	}



 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

  //Added for RTA Changes Requested  SPP Team
    public static void  setUIClassName( String strUIClassName)
    {
        uiClassName = strUIClassName;
    }
   //Add Ends
    
    
 //spp fix memory issue -integration
 public void dispose()
 {
   tabbedPane.removeAll(); 
   deRegister();
   snmpScr.close();
   icmpScr.close();
   natPingScr.close();
   initScr.close();
   rediscSch.close();
   protoScr.dispose();
   netDiscScr.dispose();
   nodeDiscScr.dispose();
   netDiscScr=null;
   nodeDiscScr=null;
   genScr.dispose();
   genScr=null;
   rediscSch = null;
   initScr = null;
   protoScr = null;
   snmpScr = null;
   icmpScr = null;
   natPingScr = null;
   critScr = null ;
   reAdapter = null;
  
   
   }
   
 //end of addition   
}
















































