
 //$Id: UpdateConfiguration.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory
//<Begin_Version>
//version$2
//<End_Version>
package com.adventnet.nms.tools.confchanges;

import org.w3c.dom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;
import com.adventnet.nms.util.*;
import java.util.*;
import javax.swing.event.*;
import com.adventnet.nms.tools.utils.ImgConv;
import java.util.jar.*;
import java.lang.reflect.*;
import java.net.*;


public class UpdateConfiguration extends JPanel implements ListSelectionListener,TableModelListener,ActionListener,HyperlinkListener
{
    //<Begin_Variable_Declarations>
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    private String localePropertiesFileName = "ConfFileUpdationResources";
    static  BuilderResourceBundle resourceBundle = null;
    private boolean running=false;
    javax.swing.JPanel Top = null;
    javax.swing.JPanel JPanel3 = null;
    javax.swing.JScrollPane JScrollPane1 = null;
    javax.swing.JTable fileList = null;
    javax.swing.JCheckBox allFiles = null;
    javax.swing.JScrollPane JScrollPane3 = null;
    javax.swing.JEditorPane JEditorPane1 = null;
    GridBagConstraints cons = new GridBagConstraints();
    Insets inset;
    //<End_Variable_Declarations>
    ConfFileTableModel model=null;
    String nmsHome="";
    String s="";
    TableColumn column=null;
    Vector childNodes=null;
    File frameIcon=null;
    Toolkit kit=null;
    Dimension dim=null;
    private UpdateConfChanges updateConfChanges = null;

    public void init()
    {
        //<Begin_init> 
        if(getParameter("RESOURCE_PROPERTIES" ) != null)
        {
            localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
        }
	resourceBundle =  Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+469,getPreferredSize().height+467)); 
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
            value = (String) Utility.getParameter(input);
        }    
        if(value == null)
        {
            if (input.equals("RESOURCE_PROPERTIES")) value = "ConfFileUpdationResources"; 
        }
        return value;

  
        //<End_getParameter_String>
    } 
    public void setUpProperties()
    {
        //<Begin_setUpProperties> 

        try
        {
            Top.setBorder(new javax.swing.border.LineBorder(new Color(-16764109),1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
        }

        //<UserCode_Begin_Bean_components>

        //<UserCode_End_Bean_components>

        try
        {
            JPanel3.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("List of Files"),1,0,new Font("Dialog",0,12),new Color(-16764109)));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
        }

        //<UserCode_Begin_Bean_JPanel3>

       
        //<UserCode_End_Bean_JPanel3>

        try
        {
            allFiles.setFont(new Font("SansSerif",0,12));
            allFiles.setHorizontalTextPosition(4);
            allFiles.setSelected(false);
            allFiles.setText(resourceBundle.getString("Select All Files"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+allFiles,ex); 
        }

        //<UserCode_Begin_Bean_allFiles>

       
        //<UserCode_End_Bean_allFiles>

        try
        {
            JScrollPane3.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Changes Made"),1,0,new Font("Dialog",0,12),new Color(-16764109)));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane3,ex); 
        }

        //<UserCode_Begin_Bean_JScrollPane3>

       
        //<UserCode_End_Bean_JScrollPane3>
        allFiles.setPreferredSize(new Dimension(allFiles.getPreferredSize().width+44,allFiles.getPreferredSize().height+0));
        
  
        //<End_setUpProperties>
	fileList.setModel(model);
	fileList.setSelectionMode(0);	
	column=fileList.getColumnModel().getColumn(0);
	column.setPreferredWidth(125);
	column=fileList.getColumnModel().getColumn(1);
	column.setPreferredWidth(25);
	Object[] object=new Object[2];
        String required = null;
        boolean selectAll = true;
        
	for(int i=0;i<childNodes.size();i++)
        {
            ConfNode node = (ConfNode)childNodes.elementAt(i);
            object[0]= node;
            //If the file is required then it is made selected otherwise it is deselected
            
            required = node.getAttribute("required");
            
            if(required != null)
            {
                if(node.isRequired())
                {
                    object[1]=new Boolean(true);
                    model.addRow(object);
                }
                else
                {
                    object[1]=new Boolean(false);
                    model.addRow(object);
                    selectAll = false;
                }
            }
            
        }
        
        //when all files are required files we can set the check box disabled.
	if(selectAll)
        {
            allFiles.setSelected(true);
            allFiles.setEnabled(false);
        }
        
        JEditorPane1.setEditable(false);
        JEditorPane1.addHyperlinkListener(this);
        
    } 
    public void start()
    {
        //<Begin_start> 
        if(running)
            return;
        running=true;

  
        //<End_start>
    } 
    public void stop()
    {
        //<Begin_stop> 
        if(!running)
            return;
        running=false;

  
        //<End_stop>
    } 
    public void initVariables()
    {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        fileList= new javax.swing.JTable();
        allFiles= new javax.swing.JCheckBox();
        JScrollPane3= new javax.swing.JScrollPane();
        JEditorPane1= new javax.swing.JEditorPane();

  
        //<End_initVariables>
	model=new ConfFileTableModel();
	childNodes = getAllNodes();
	kit=Toolkit.getDefaultToolkit();
	dim=kit.getScreenSize();	
    } 
    public void setUpGUI(Container container)
    {
        //<Begin_setUpGUI_Container> 
        container.add(Top,BorderLayout.CENTER);
        Top.setLayout(new GridBagLayout());
        inset = new Insets(5,5,5,5);
        setConstraints(0,0,1,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
        Top.add(JPanel3,cons);
        JPanel3.setLayout(new BorderLayout(10,10));
        JPanel3.add(JScrollPane1,BorderLayout.CENTER);
        JScrollPane1.getViewport().add(fileList);
        JPanel3.add(allFiles,BorderLayout.NORTH);
        inset = new Insets(5,5,5,5);
        setConstraints(0,1,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
        Top.add(JScrollPane3,cons);
        JScrollPane3.getViewport().add(JEditorPane1);

  
        //<End_setUpGUI_Container>
    } 
    public void setUpConnections()
    {
        //<Begin_setUpConnections> 

  
        //<End_setUpConnections>
	ListSelectionModel lsm=fileList.getSelectionModel();
	lsm.addListSelectionListener(this);
	model.addTableModelListener(this);	
	allFiles.addActionListener(this);	
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


    private Vector getAllNodes()
    {
        
        Vector v     = new Vector();
        Vector nodes = updateConfChanges.getAllNodes();
            
        for(int i=0;i<nodes.size();i++)
        {
            Element node= (Element)nodes.elementAt(i);
            
            if(node.getNodeType()== Node.ELEMENT_NODE )
            {
                if(node.getAttribute("name")!=null)
                {
                    ConfNode confNode = ConfUpgradeManager.createConfNode(node);
                    v.add(confNode);
                }
                    
                    
            }
        }
            
	return v;
    }
    
    
    private Vector getSelectedFiles()
    {	
	Vector v=new Vector();
	int rows=model.getRowCount();
	for(int i=0;i<rows;i++)
        {
            if(((Boolean)model.getValueAt(i,1)).booleanValue())
            {
                v.add(model.getValueAt(i,0));
            }
        }

        
        
	return v;
    }
    
    private void setSelection(boolean flag)
    {
	Vector v=new Vector();
	int rows=model.getRowCount();
        
	for(int i=0;i<rows;i++)
        {
            ConfNode node = (ConfNode)model.getValueAt(i,0);
            
            //for required files there is no need to select them
            if(!node.isRequired())
            {
                model.setValueAt(new Boolean(flag),i,1);
            }
        }	
	
    }
	
    

    private String checkAndModifyFileSeparator(String filePath)
    {
        //regardless of the OS we can change the fileSeparator present in the classpath
        filePath = filePath.replace('\\',File.separatorChar);
        
        return filePath;
    }


    private String descURL = null;

    public void valueChanged(ListSelectionEvent e)
    {
	int i = fileList.getSelectedRow();

        if(i != -1)
        {
            ConfNode confFileNode   = (ConfNode)model.getValueAt(i,0);
            String description      = confFileNode.getDescription();
            description             = checkAndModifyFileSeparator(description);

            if(descURL != null && descURL.equals(description))
            {
                return;
            }
            
            if(description.endsWith(".html"))
            {
                File htmlFile      = new File(description);
                URL htmlFileURL    = null;
                
                try
                {
                    htmlFileURL = htmlFile.toURL();
                    final URL test = htmlFileURL;
                    
                    Runnable run = new Runnable()
                        {
                            public void run()
                            {
                                try
                                {
                                    JEditorPane1.setPage(test);
                                }
                                catch(IOException ie)
                                {
                                    System.out.println(" Error :: while show the html content "+test);
                                }
                                catch(Exception ex)
                                {
                                    // No need to intimate the user if any unexpected error happens here
                                }
                                    
                            }
                        };
                    
                    SwingUtilities.invokeLater(run);
                    
                }
                catch(MalformedURLException mex)
                {
                    System.out.println(" Given URL could not found :: "+htmlFileURL);
                }
                
                
            }
            else if(!description.equals(""))
            {
                JEditorPane1.setText(description);
            }

            descURL = description;
        }
    }



    
    
    public boolean checkForFileSelection()
    {
	Vector v=getSelectedFiles();
	if(v.size()==0)
        {
            return false;
        }
	return true;
    }

    public Vector getFilesAsXMLNode()
    {
        Vector xmlNodes=new Vector();
	Vector v = getSelectedFiles();
        
	for(int i=0;i<v.size();i++)
        {	
            ConfNode confFileNode = (ConfNode)v.elementAt(i);
            xmlNodes.add(confFileNode.getNode()); // All the user selected files are added 
        }

        int count = childNodes.size();

        
        for(int j=0; j < count; j++)
        {
            ConfNode confNode = (ConfNode)childNodes.elementAt(j);
            String required   = confNode.getAttribute("required");
            
            if(required == null && !xmlNodes.contains(confNode))
            {
                /* All the nodes that are not exposed to the user is getting added
                 * here. i.e., If any node does not have required attribute then it will
                 * not be shown to the user and will be update all the time.
                 */
                xmlNodes.add(confNode.getNode()); 
            }
        }
        
        return xmlNodes;
    }
    
    public void tableChanged(TableModelEvent e)
    {
	int row=e.getFirstRow();
	int column =e.getColumn();
        
	if(column==1)
        {
            Vector v = getSelectedFiles();
            
            if(v.size()== model.getRowCount())
            {
                allFiles.setSelected(true);
            }
            else
            {
                allFiles.setSelected(false);
            }
        }
    }
 
    class ConfFileTableModel extends DefaultTableModel
    {
	public boolean isCellEditable(int row,int column)
	{
            if(column==1)
            {
                ConfNode node = (ConfNode)getValueAt(row,0);
                
                //when a file is a required file we can't allow that file to edit
                if(node.isRequired())
                {
                    return false;
                }
                return true;
            }
            return false;
	}
	public String getColumnName(int column)
	{
            if(column==1)
            {
                return "Select";
            }
            return "File Name";
	}
        public int getColumnCount() 
	{
            return 2;
        }
	public Class getColumnClass(int column)
	{
            return getValueAt(0,column).getClass();
	}

    }

    public void actionPerformed(ActionEvent e)
    {
	if(e.getSource().equals(allFiles))
        {
            if(allFiles.isSelected())
            {
                setSelection(true);
                return;
            }
            setSelection(false);
        }

    }

    public UpdateConfiguration()
    {
        //<Begin_UpdateConfiguration>
        this.init();
  
        //<End_UpdateConfiguration>
    }
    public UpdateConfiguration(String home)
    {
	nmsHome=home;
	this.init();
    }

    public UpdateConfiguration(String home,UpdateConfChanges changes)
    {
	nmsHome=home;
        updateConfChanges = changes;
	this.init();
        
    }

    
    public UpdateConfiguration(java.applet.Applet applet)
    {
        //<Begin_UpdateConfiguration_java.applet.Applet>
        this.applet = applet;
        this.init();
  
        //<End_UpdateConfiguration_java.applet.Applet>
    }

 
    public void setProperties(java.util.Properties props)
    {
        //<Begin_setProperties_java.util.Properties> 

  
        //<End_setProperties_java.util.Properties>

        
      
    }

    public void hyperlinkUpdate( HyperlinkEvent hle)
    {
        if(hle.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED)
        {
            Class cls = getClass("com.adventnet.nms.util.BrowserControl");
            
            if(cls != null)
            {
                try
                {
                    Class classArray[]=new Class[1];
                    classArray[0]=Class.forName("java.lang.String");
                    
                    Method method = cls.getMethod("displayURL",classArray);
                    method.invoke(null,new Object[]{hle.getURL().toString()});
                }
                catch(Exception ex)
                {
                }
            }
            
        }
    }

    private Class getClass(String name)
    {
        try
        {
            return  Class.forName(name);
        }
        catch(Exception ce)
        {
            return null;
        }
        
    }
    
}





































