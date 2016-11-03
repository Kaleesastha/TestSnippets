//$Id: QueryDetails.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$3
//<End_Version>

package com.adventnet.nms.tools.confchanges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import java.util.*;
import com.adventnet.nms.util.*;
import java.util.jar.*;
import org.w3c.dom.*;
import java.lang.reflect.*;
import java.net.*;


public class QueryDetails extends JPanel implements ListSelectionListener,HyperlinkListener
{
    //<Begin_Variable_Declarations>
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    private String localePropertiesFileName = "ConfFileUpdationResources";
    static  BuilderResourceBundle resourceBundle = null;
    private boolean running=false;
    javax.swing.JPanel Top = null;
    javax.swing.JScrollPane JScrollPane1 = null;
    javax.swing.JList fileList = null;
    javax.swing.JScrollPane JScrollPane3 = null;
    javax.swing.JEditorPane JEditorPane1 = null;
    GridBagConstraints cons = new GridBagConstraints();
    Insets inset;
    //<End_Variable_Declarations>
    String nmsHome="";
    String s="";
    private UpdateConfChanges updateConfChanges = null;
    


    public QueryDetails()
    {
        //<Begin_QueryDetails>
        this.init();
  
        //<End_QueryDetails>
    }
    public QueryDetails(String home)
    {
        nmsHome=home;	 
        this.init();
  
    }

    public QueryDetails(String home,UpdateConfChanges changes)
    {
        nmsHome=home;	 
        this.init();
        updateConfChanges = changes;
        
    }
    public QueryDetails(java.applet.Applet applet)
    {
        //<Begin_QueryDetails_java.applet.Applet>
        this.applet = applet;
        this.init();
  
        //<End_QueryDetails_java.applet.Applet>
    }


  


 

  

    public void init()
    {
        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
            localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle =  Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+604,getPreferredSize().height+448)); 
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
            JScrollPane1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("List of Files"),1,0,new Font("Dialog",0,12),new Color(-10066279)));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
        }

        //<UserCode_Begin_Bean_JScrollPane1>

        //<UserCode_End_Bean_JScrollPane1>

        //<UserCode_Begin_Bean_fileList>
        fileList.setBackground(JScrollPane1.getBackground());
        //<UserCode_End_Bean_fileList>

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

        try
        {
            JEditorPane1.setEditable(false);
            JEditorPane1.setOpaque(true);
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JEditorPane1,ex); 
        }

        //<UserCode_Begin_Bean_JEditorPane1>

        JEditorPane1.addHyperlinkListener(this);
          
        //<UserCode_End_Bean_JEditorPane1>

  
        //<End_setUpProperties>

        JScrollPane1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),"List of Files",1,0,new Font("Dialog",0,12),new Color(-16764109)));
        fileList.setListData(getAllNodes());
        fileList.setSelectionMode(0);	
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
        JScrollPane1= new javax.swing.JScrollPane();
        fileList= new javax.swing.JList();
        JScrollPane3= new javax.swing.JScrollPane();
        JEditorPane1= new javax.swing.JEditorPane();

  
        //<End_initVariables>
        
        JScrollPane3= new javax.swing.JScrollPane(JEditorPane1);
        JScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane3.setPreferredSize(new Dimension(250, 145));
        JScrollPane3.setMinimumSize(new Dimension(10, 10));
    } 
    public void setUpGUI(Container container)
    {
        //<Begin_setUpGUI_Container> 
        container.add(Top,BorderLayout.CENTER);
        Top.setLayout(new GridBagLayout());
        inset = new Insets(5,5,5,5);
        setConstraints(0,0,1,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
        Top.add(JScrollPane1,cons);
        JScrollPane1.getViewport().add(fileList);
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
        fileList.addListSelectionListener(this);
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
    
    
    private String descURL = null;

    public void valueChanged(ListSelectionEvent e)
    {
        ConfNode file      = (ConfNode)fileList.getSelectedValue();
        String description = file.getDescription();
        description        = checkAndModifyFileSeparator(description);
    

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


    private String checkAndModifyFileSeparator(String filePath)
    {
        //regardless of the OS we can change the fileSeparator present in the classpath
        filePath = filePath.replace('\\',File.separatorChar);

        return filePath;
    }
 
    public void setProperties(java.util.Properties props)
    {
        //<Begin_setProperties_java.util.Properties> 

  
        //<End_setProperties_java.util.Properties>
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

}






