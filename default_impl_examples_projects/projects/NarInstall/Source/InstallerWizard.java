

/*
  $Id: InstallerWizard.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
 */


// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory


//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.tools.nar;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import com.adventnet.nms.util.XMLDataReader;
import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.util.XMLDataWriter;
import com.adventnet.nms.tools.utils.ToolsUtil;
public class InstallerWizard extends JDialog implements ActionListener
{	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "DeploymentWizard";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel buttonPanel = null;
	javax.swing.JButton previous = null;
	javax.swing.JButton next = null;
	javax.swing.JButton finish = null;
	javax.swing.JButton cancel = null;
	javax.swing.JPanel cardPanel = null;
	//<End_Variable_Declarations>
    CardLayout card=null;
    
  	Users userScreen=null;
	Tree treeScreen=null;
	MapMenu mapScreen=null;
	ListMenu listScreen=null;
	FrameMenu frameScreen=null;
	PanelMenu panelScreen=null;
	ToolBarMenu toolbarScreen=null;
    InstalledList installListScreen=null;
	int noOfScreens=0;  
	int count=0;
   	boolean showTree=false; 
    Hashtable installationDetails;
    Vector v;
    boolean device=false;
    boolean nondevice=false;
    boolean tree=false;
    boolean general=false;
    NarExtracter extract;
    String filename="";
    DeviceSpecificInstall di;
    boolean everything;
  public InstallerWizard()
  {
        super(InstUninst.topFrame,true);
       
      //<Begin_InstallerWizard>
    pack();
  
    //<End_InstallerWizard>
 
    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
  }
   
    public boolean startInstall(NarExtracter narextract,String name)
    {
       
        everything=true;
        extract=narextract;
        filename=name;
        showTree=false;
        
        setVisible(true);
        
       
        if(everything &&(device||nondevice||tree||general))
        {
						String[] message=null;
						if(extract.resources)
						{
								message=new String[]{resourceBundle.getString("Installed Successfully"),resourceBundle.getString("Except for the following resources"),extract.resourceName};
						}
						else
						{
								message = new String[]{resourceBundle.getString("Installed Successfully")};
						}
						
            JOptionPane.showMessageDialog(null,message,resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        else
        {
          
            deleteXml(filename);
              return false;
        }
    }
	

  public InstallerWizard(java.applet.Applet applet)
  {
    //<Begin_InstallerWizard_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_InstallerWizard_java.applet.Applet>
  }

 
    public void start()
  { 
      cardPanel.removeAll();
      noOfScreens=0;  
      count=0;
      previous.setEnabled(false);
      next.setEnabled(true);
      installationDetails=new Hashtable();
  
      addScreens();
  
      userScreen.addUsersToList();

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
        buttonPanel= new javax.swing.JPanel();
        previous= new javax.swing.JButton();
        next= new javax.swing.JButton();
        finish= new javax.swing.JButton();
        cancel= new javax.swing.JButton();
        cardPanel= new javax.swing.JPanel();

  
        //<End_initVariables>
		previous.setEnabled(false);
		card=new CardLayout();
		
		
		
		
                
               
  }
    
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(buttonPanel,BorderLayout.SOUTH);
buttonPanel.setLayout(new FlowLayout(1,5,5));
buttonPanel.add(previous);
buttonPanel.add(next);
buttonPanel.add(finish);
buttonPanel.add(cancel);
Top.add(cardPanel,BorderLayout.CENTER);
cardPanel.setLayout(new FlowLayout(1,5,5));

  
//<End_setUpGUI_Container>
	cardPanel.setLayout(card);
	previous.addActionListener(this);
	previous.setActionCommand("previous");
	next.addActionListener(this);
	next.setActionCommand("next");
	finish.addActionListener(this);
	finish.setActionCommand("finish");
	cancel.addActionListener(this);
	cancel.setActionCommand("cancel");
	this.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent we){
			 everything=false;
             setVisible(false);
             deleteXml(filename);

		}
		});
        next.setMnemonic('n');
        previous.setMnemonic('p');
        finish.setMnemonic('f');
        cancel.setMnemonic('c');
        
       
        
  }
    public void addScreens()
    {
        
        userScreen=new Users(extract);
	addCard("user",userScreen);

        treeScreen=new Tree();
        if(extract.validatekey(extract.attribute))
        {
           
            addCard("tree",treeScreen);
        }
	if(extract.deviceSpecific)
	{
            mapScreen=new MapMenu();
            listScreen=new ListMenu(extract.deviceAttributes);
            addCard("map",mapScreen);
            addCard("list",listScreen);
                
	}
	else
	{
            frameScreen=new FrameMenu();
            panelScreen=new PanelMenu();
            toolbarScreen=new ToolBarMenu();
            addCard("frame",frameScreen);
            addCard("panel",panelScreen);
            //addCard("toolbar",toolbarScreen);
               
	}
        installListScreen=new InstalledList();
        addCard("installlist",installListScreen);
    }
        

        
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            previous.setText(resourceBundle.getString("<< Previous"));
            previous.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+previous,ex); 
          }

//<UserCode_Begin_Bean_previous>

            previous.setText(ToolsUtil.getMenuName(resourceBundle.getString("<< Previous"),'p'));
//<UserCode_End_Bean_previous>

          try
          {
            next.setFont(new Font("Dialog",0,12));
            next.setText(resourceBundle.getString("Next >>"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+next,ex); 
          }

//<UserCode_Begin_Bean_next>
            next.setText(ToolsUtil.getMenuName(resourceBundle.getString("Next >>"),'n'));

//<UserCode_End_Bean_next>

          try
          {
            finish.setText(resourceBundle.getString("Finish"));
            finish.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+finish,ex); 
          }

//<UserCode_Begin_Bean_finish>
		     finish.setText(ToolsUtil.getMenuName(resourceBundle.getString("Finish"),'f'));

//<UserCode_End_Bean_finish>

          try
          {
            cancel.setText(resourceBundle.getString("Cancel"));
            cancel.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancel,ex); 
          }

//<UserCode_Begin_Bean_cancel>
            cancel.setText(ToolsUtil.getMenuName(resourceBundle.getString("Cancel"),'c'));

//<UserCode_End_Bean_cancel>

  
          //<End_setUpProperties>
  } 
	public void addCard(String s,Component screen)
	{
            cardPanel.add(s,screen);
		noOfScreens++;
	}
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
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
        this.setSize(getPreferredSize().width+642,getPreferredSize().height+430); 
          setTitle(resourceBundle.getString("new_screen1"));
        Container container = getContentPane();
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
       this.setSize(700,500); 
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        //setModal(true);
        setTitle(resourceBundle.getString("Installer Wizard"));
       this.setLocation( (dim.width-700)/2, (dim.height-500)/2 );
      
  } 

     
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("next"))
        {
            count++;                        
            if(count==1)
            {
               
                userScreen.setAddedUsersList();
                if(userScreen.validateUsers())
                {
                    getUsers();
                    previous.setEnabled(true);
                    card.next(cardPanel);
                }
                else
                {
                    count--;
                }
                         
            }
            if(count==2&&extract.validatekey(extract.attribute))
            {
                treeScreen.setTableValues();
                if(treeScreen.validateTrees())
                {
                    installationDetails.put("tree",treeScreen.getTableValues());
                    previous.setEnabled(true);
                    card.next(cardPanel);
                }
                else
                {
                    count--;
                }
            }
            if(extract.deviceSpecific)
            {
                if(count==noOfScreens-2)
                {
                    mapScreen.setMapMenuInformations();
                    if(mapScreen.validateMap())
                    {
                        installationDetails.put("map",mapScreen.getMapMenuInformations());
                        previous.setEnabled(true);
                        card.next(cardPanel);
                    }
                    else
                    {
                        count--;
                    }
                                
                }
                else if(count==noOfScreens-1)
                {
                    listScreen.setListMenuInformations();
                    if(listScreen.validateList())
                    {
                        installationDetails.put("list",listScreen.getListMenuInformations());
                        previous.setEnabled(true);
                        card.next(cardPanel);
                    }
                    else
                    {
                        count--;
                    }
                }
            }
            else 
            {
                if(count==noOfScreens-2)
                {
                    frameScreen.setFrameMenuInformations();
                    if(frameScreen.validateFrame())
                    {
                        installationDetails.put("frame",frameScreen.getFrameMenuInformations());
                        previous.setEnabled(true);
                        card.next(cardPanel);
                    }
                    else
                    {
                        count--;
                    }
                }
                else if(count==noOfScreens-1)
                {
                    panelScreen.setPanelMenuInformations();
                    if(panelScreen.validatePanel())
                    {
                      
                        installationDetails.put("panel",panelScreen.getPanelMenuInformations());
                        previous.setEnabled(true);
                        card.next(cardPanel);
                    }
                    else
                    {
                        count--;
                    }
                }
                /*else if(count==noOfScreens-1)
                {
                    toolbarScreen.setToolBarMenuInformations();
                    if(toolbarScreen.validateToolBar())
                    {
                        installationDetails.put("toolbar",toolbarScreen.getToolBarMenuInformations());
                        previous.setEnabled(true);
                        card.next(cardPanel);
                    }
                    else
                    {
                        count--;
                    }
                }*/
            }

            if(count==noOfScreens-1)
            {
                next.setEnabled(false);
                listInstallationDetails();
            }
                   
        }
        else if(ae.getActionCommand().equals("previous"))
        {
            next.setEnabled(true);
            card.previous(cardPanel);
            count--;
            if(count==0)
            {
                previous.setEnabled(false);
            }
        }
        else if(ae.getActionCommand().equals("cancel"))
        {
            if( JOptionPane.showConfirmDialog(this,resourceBundle.getString("Do you really want to quit installation?"),resourceBundle.getString("Confirm"),JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            {
                everything=false;
                setVisible(false);
                deleteXml(filename);
            }
        }
        else if(ae.getActionCommand().equals("finish"))
        {
            boolean everythingok=true;
            if(count==0)
            {
                userScreen.setAddedUsersList();
                getUsers();
            }
            if(count==1&&extract.validatekey(extract.attribute))
            {
                treeScreen.setTableValues();
                installationDetails.put("tree",treeScreen.getTableValues());
				
            }
            if(extract.deviceSpecific)
            {
                if(count==noOfScreens-3)
                {
                    mapScreen.setMapMenuInformations();
                    installationDetails.put("map",mapScreen.getMapMenuInformations());
                        
                }
                if(count==noOfScreens-2)
                {
                    listScreen.setListMenuInformations();
                    installationDetails.put("list",listScreen.getListMenuInformations());
                }
            }
            else
            {
                if(count==noOfScreens-3)
                {
                    frameScreen.setFrameMenuInformations();
                    installationDetails.put("frame",frameScreen.getFrameMenuInformations());
                }
                if(count==noOfScreens-2)
                {
                    panelScreen.setPanelMenuInformations();
                    installationDetails.put("panel",panelScreen.getPanelMenuInformations());
                }
                /*if(count==noOfScreens-2)
                {
                    toolbarScreen.setToolBarMenuInformations();
                    installationDetails.put("toolbar",toolbarScreen.getToolBarMenuInformations());
                    }*/
            }
            
            if(userScreen.validateUsers())
            {
                if(extract.deviceSpecific)
                {
                    if(mapScreen.validateMap()&&listScreen.validateList())
                    {
                        di=new DeviceSpecificInstall(extract);
                        device=di.start(installationDetails);
                    }
                    else
                    {
                        everythingok=false;
                    }
                }
                else
                {
                    if(frameScreen.validateFrame()&&panelScreen.validatePanel())
                    {
                        NonDeviceSpecificInstall ndi=new NonDeviceSpecificInstall(extract);
                        nondevice=ndi.start(installationDetails);
                    }
                    else
                    {
                        everythingok=false;
                    }
                }
                if(installationDetails.containsKey("tree"))
                {
                    if(treeScreen.validateTrees())
                    {
                        v=(Vector)installationDetails.get("tree");
                        if(!v.isEmpty())
                        {
                            TreeInstall ti=new TreeInstall(extract);
                            tree=ti.start(installationDetails);
							showTree=true;
                        }
                    }
                    else
                    {
                        everythingok=false;
                    }
                }
            }
            else
            {
                everythingok=false;
            }
                        
            if(everythingok)
            {
        //There is no need to edit NmsPanels.conf when show in tree is true
                GeneralInstall gi=new GeneralInstall(showTree);
                general=gi.start(extract);
                WriteUninstallXml uix=new WriteUninstallXml(installationDetails,extract,filename,di);
                everything=uix.getResult();
                dispose();
            }
        }
    }
    
        
        public void listInstallationDetails()
            {
                StringBuffer buffer=new StringBuffer();
        	    Hashtable h=null;
                Vector v=(Vector)installationDetails.get("users");
                if(!v.isEmpty())
                {
                    buffer.append(resourceBundle.getString("USERS LIST:-"));
                    buffer.append("\n");
           
                    for(int i=0;i<v.size();i++)
                    {
                        buffer.append("               "+v.elementAt(i).toString());
                        buffer.append("\n");
                    }
                }
                /*if(installationDetails.containsKey("oid"))
                {
                    if(installationDetails.get("oid").toString().equals("true"))
                    {
                        buffer.append("OID Updation    :"+installationDetails.get("oid").toString());
                    }
                }*/
                if(installationDetails.containsKey("tree"))
                {
                    v=(Vector)installationDetails.get("tree");
                    if(!v.isEmpty())
                    {
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("TREE INFORMATION:-"));
                        buffer.append("\n");
                        for(int j=0;j<v.size();j++)
                        {
                            Hashtable v1=(Hashtable)v.elementAt(j);
                
                            buffer.append(resourceBundle.getString("User               :")+v1.get("username").toString());
                            buffer.append("\n");
                            buffer.append(resourceBundle.getString("Node Name  :")+v1.get("treename").toString());
                            buffer.append("\n");
                            buffer.append(resourceBundle.getString("Icon                :")+v1.get("treeicon").toString());
                            buffer.append("\n");
                            buffer.append(resourceBundle.getString("Parent Node  :")+v1.get("parentnode").toString());
                            buffer.append("\n");
							if(v1.containsKey("menufilename"))
							{
								buffer.append(resourceBundle.getString("Menu File Name:")+v1.get("menufilename").toString());
								buffer.append("\n");
							}	
							if(v1.containsKey("treepopup"))
							{
								buffer.append(resourceBundle.getString("Tree Popup Menu:")+v1.get("treepopup").toString());
								buffer.append("\n");
							}
							if(v1.containsKey("tablepopup"))
							{
								buffer.append(resourceBundle.getString("Table Popup Menu:")+v1.get("tablepopup").toString());
								buffer.append("\n");
							}
							buffer.append("\n");	

                        }
                    }
                }

                if(installationDetails.containsKey("map"))
                {
                    v=(Vector)installationDetails.get("map");
                    if(!v.isEmpty())
                    {
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("MAPMENU INFORMATION:-"));
                        buffer.append("\n");
                        buffer.append( resourceBundle.getString("Menu File Name :")+v.elementAt(0).toString());
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("Menu Item Name:")+v.elementAt(1).toString());
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("Map Image          :")+v.elementAt(2).toString().substring(v.elementAt(2).toString().lastIndexOf(File.separator)+1));
                        buffer.append("\n");
                    }
                }
                if(installationDetails.containsKey("list"))
                {
                    v=(Vector)installationDetails.get("list");
                    if(!v.isEmpty())
                    {
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("LISTMENU INFORMATION:-"));
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("Menu File Name:")+v.elementAt(0).toString());
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("Menu Item Name:")+v.elementAt(1).toString());
                        buffer.append("\n");
                        if(v.elementAt(2).toString().equalsIgnoreCase("true"))
                        {
                            Vector v3=(Vector)v.elementAt(3);
                            String image=v3.elementAt(1).toString();
                            buffer.append(resourceBundle.getString("Transparent Image:")+image.substring(image.lastIndexOf(File.separator)+1));
                            buffer.append("\n");
                        }
                        else
                        {
                
                            buffer.append(resourceBundle.getString("Non Transparent Image"));
                            buffer.append("\n");
                            Vector severityImages=(Vector)v.elementAt(3);
                            for(int i=0;i<severityImages.size();i++)
                            {
                     
                                Vector severity=(Vector)severityImages.elementAt(i);
                                String s="";
                                if(severity.elementAt(1)!=null)
                                {
                                    s=severity.elementAt(1).toString();
                                     buffer.append(severity.elementAt(0).toString()+" :"+s.substring(s.lastIndexOf(File.separator)+1));
                                }
                               
                                buffer.append("\n");
                            }
                        }
                    }
                }
                if(installationDetails.containsKey("frame"))
                {
                    h=(Hashtable)installationDetails.get("frame");
                    if(!h.isEmpty())
                    {
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("FRAME MENU INFORMATION"));
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("Menu Name:")+h.get("menuname").toString());
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("Menu Item Name:")+h.get("menuitem").toString());
                        buffer.append("\n");
						if(h.containsKey("operation"))
						{
							buffer.append(resourceBundle.getString("Operation Name:")+h.get("operation").toString());
							buffer.append("\n");
						}
						if(h.containsKey("privilege"))
						{
							buffer.append(resourceBundle.getString("Action on No Privilege:")+h.get("privilege").toString());
							buffer.append("\n");
						}
						if(h.containsKey("acceleratormodifier"))
						{
							buffer.append(resourceBundle.getString("Modifier:")+h.get("acceleratormodifier").toString());
							buffer.append("\n");
						}
						if(h.containsKey("acceleratorkey"))
						{
							buffer.append(resourceBundle.getString("ShortCut Key:")+h.get("acceleratorkey").toString());
							buffer.append("\n");
						}
						if(h.containsKey("shortcutkey"))
						{
							buffer.append(resourceBundle.getString("Mnemonics:")+h.get("shortcutkey").toString());
							buffer.append("\n");
						}
					}
                }
                if(installationDetails.containsKey("panel"))
                {
                    h=(Hashtable)installationDetails.get("panel");
                    if(!h.isEmpty())
                    {
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("PANEL MENU INFORMATION"));
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("Menu File Name:")+h.get("menufilename").toString());
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("Menu Name:")+h.get("menuname").toString());
                        buffer.append("\n");
                        buffer.append(resourceBundle.getString("Menu Item Name:")+h.get("menuitem").toString());
                        buffer.append("\n");
						if(h.containsKey("operation"))
						{
							buffer.append(resourceBundle.getString("Operation Name:")+h.get("operation").toString());
							buffer.append("\n");
						}
						if(h.containsKey("privilege"))
						{
							buffer.append(resourceBundle.getString("Action on No Privilege:")+h.get("privilege").toString());
							buffer.append("\n");
						}
						 if(h.containsKey("acceleratormodifier"))
						{
							buffer.append(resourceBundle.getString("Modifier:")+h.get("acceleratormodifier").toString());
							buffer.append("\n");
						}
						if(h.containsKey("acceleratorkey"))
						{
							buffer.append(resourceBundle.getString("ShortCut Key:")+h.get("acceleratorkey").toString());
							buffer.append("\n");
						}
						if(h.containsKey("shortcutkey"))
						{
							buffer.append(resourceBundle.getString("Mnemonics:")+h.get("shortcutkey").toString());
							buffer.append("\n");
						}
						
					}
				}
                /*if(installationDetails.containsKey("toolbar"))
                {
                    v=(Vector)installationDetails.get("toolbar");
                    if(!v.isEmpty())
                    {
                        buffer.append("\n");
                        buffer.append("TOOLBAR INFORMATION");
                        buffer.append("\n");
                        buffer.append("Toolbar Name:"+v.elementAt(0).toString());
                        buffer.append("\n");
                        String image=v.elementAt(1).toString();
                        buffer.append("toolbar Icon :"+image.substring(image.lastIndexOf(File.separator)+1));
                        buffer.append("\n");
                        buffer.append("Tooltip text:"+v.elementAt(2).toString());
                        buffer.append("\n");
                    }
            
                    }*/
                installListScreen.listArea.setText(buffer.toString());
            }
   
    
    
   
        public void getUsers()
            {
                v=new Vector();
       
                Vector list=userScreen.getAddedUsersList();

                for(int i=0;i<list.size();i++)
                {
                    if(list.elementAt(i).toString().equals("<All Existing Users>"))
                    {
                        Vector allUsers=userScreen.getAllExistingUsers();
                        for(int j=0;j<allUsers.size();j++)
                        {
                            if(!v.contains(allUsers.elementAt(j)))
                                v.addElement(allUsers.elementAt(j));
                            else
                                continue;
                        }
                    }
                    else
                    {
                        if(!v.contains(list.elementAt(i)))
                        {
                            v.addElement(list.elementAt(i));
                        }
                    }
                }
       
        
                installationDetails.put("users",v);
                /*boolean update=userScreen.oidUpdate.isSelected();
                if(update==true)
                {
                    installationDetails.put("oid","true");
                }
                else
                {
                    installationDetails.put("oid","");
                }*/
                treeScreen.setUsers(v);
				if(frameScreen!=null)
				{
					if(v.contains("<Any NewUser>"))
					{
						frameScreen.newUser=true;
					}
					else
					{
						frameScreen.newUser=false;
						if(installationDetails.containsKey("operation"))
						{
							installationDetails.remove("operation");
							if(installationDetails.containsKey("privilege"))
							{
								installationDetails.remove("privilege");
							}
						}
					}
							frameScreen.instantiateOperation();

				}
					
                if(panelScreen!=null)
                {
                    if(v.contains("<Any NewUser>"))
                    {
                        panelScreen.newUser=true;
                    }
                    else
                    {
                        panelScreen.newUser=false;
                    }
                    panelScreen.instantiateOperation();
                }
            }
    public void deleteXml(String fname)
    {
        fname = fname.substring(fname.lastIndexOf(File.separator)+1);
        fname = fname.substring(0,fname.length()-3)+"xml";
        File f = new File(System.getProperty("user.dir")+File.separator+"NetMonitor"+File.separator+"build"+File.separator+fname);
        if(f.exists())
                f.delete();
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





        public void setVisible(boolean bl)
  {
                //<Begin_setVisible_boolean>
       	if(bl)
       	{
       	  init();
          start();
        }
        else
        {
          stop();
        }
        super.setVisible(bl);
  
                 //<End_setVisible_boolean>
      
             
            }
    
 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
    }









