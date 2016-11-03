/** $Id */

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.help.about;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.applet.*;
import java.lang.reflect.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import  com.adventnet.nms.util.*;
import com.adventnet.nms.startclient.*;


public class AboutDialog extends JDialog implements ActionListener, HyperlinkListener
{
    //<Begin_Variable_Declarations>
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    private String localePropertiesFileName = "about_DialogResources";
    private boolean running=false;
    javax.swing.JPanel Top = null;
    javax.swing.JPanel topPanel = null;
    javax.swing.JPanel warlingPanel = null;
    javax.swing.JLabel warningLbl = null;
    javax.swing.JSeparator JSeparator1 = null;
    javax.swing.JTextArea warningTxtArrea = null;
    javax.swing.JPanel infoAndTitlePanelMain = null;
    javax.swing.JLabel aboutImageLbl = null;
    javax.swing.JPanel infoAndTitlePanel = null;
    javax.swing.JPanel infoPanel = null;
    javax.swing.JLabel licenceInfoLbl = null;
    javax.swing.JTextArea licenceInfoTxtArea = null;
    javax.swing.JPanel titlePanel = null;
    javax.swing.JLabel productNameLbl = null;
    javax.swing.JLabel productVerLbl = null;
    javax.swing.JLabel productSPLbl = null;
    javax.swing.JEditorPane licenceInfoEditorPane = null;
    javax.swing.JPanel toolDetPanel = null;
    javax.swing.JLabel toolNameLbl = null;
    javax.swing.JLabel showToolNameLbl = null;
    javax.swing.JPanel bottomPanel = null;
    javax.swing.JLabel copyRightLbl = null;
    javax.swing.JButton okBtn = null;
    javax.swing.JScrollPane scrollpane = null;
    GridBagConstraints cons = new GridBagConstraints();
    Insets inset;
    //<End_Variable_Declarations>

    private ResourceBundle resourceBundle = null;
    
    private Locale locale=null;

    //Configurable GUI parameters
    private String imageFileName     = "adventnet_aboutDlg.jpg";
    private String productNameStr    = null;
    private String productVersionStr = " ";
    private String toolName          = "";
    private String contactInfoFile   = "contact_address.html";
    private String copyrightInfo     = "";
    
    // Now this GUI is used for parsing the xml to get the configurable parameters ,
    // this must be separated in the future.

    public AboutDialog(JFrame frm, boolean isModal,ResourceBundle bundle)
    {
        super(frm, isModal);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        resourceBundle = bundle;
        setLocale();
        
        //parse xml file
        initializeParameters();
    }
    
    public AboutDialog(Applet app,JFrame frm, boolean isModal,ResourceBundle bundle)
    {
        super(frm, isModal);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        resourceBundle = bundle;
        applet = app;
        setLocale();

        //parse xml file
        initializeParameters();
    }

    
    public AboutDialog(JFrame frm, boolean isModal,ResourceBundle bundle,String internalToolName)
    {
        super(frm, isModal);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        resourceBundle = bundle;
        setLocale();

        //parse xml file
        initializeParameters();
        
    }
    
    public AboutDialog(Applet app,JFrame frm, boolean isModal,ResourceBundle bundle,String internalToolName)
    {
        super(frm, isModal);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        resourceBundle = bundle;
        applet = app;
        setLocale();

        //parse xml file
        initializeParameters();
    }


    public AboutDialog(Frame frm, boolean isModal)
    {
        super(frm, isModal);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //parse xml file
        initializeParameters();
    }
  
    
    private void initializeParameters()
    {
        String fileName = "AboutDialogInfo.xml";
        URL fileURL     = null;
        
        try
        {
            if(applet != null)
            {
                fileURL = new URL(applet.getDocumentBase() + ".." + File.separator + "conf" + File.separator + fileName);


                // To find out whether the url is vaild or not TODO :: should replace with a neat method

                try
                {
                    fileURL.openStream();
                }
                catch(IOException ie)
                {
                    fileURL = null;
                }
                
            }
            else
            {
                File htmlFile = new File(System.getProperty("user.dir") + File.separator + "conf" + File.separator + fileName);
                if(htmlFile.exists())
                {
                    fileURL = htmlFile.toURL();
                }
                
            }
        }
        catch(Exception ex)
        {
            //Could not read xml file from "conf" directory, hence read it from the jar file.
            fileURL = null;
        }

        
        
        if(fileURL == null )
        {
            fileURL = this.getClass().getResource("/com/adventnet/help/about/"+fileName);
        }

        Element rootNode   = parse(fileURL);
        Element paramNode  = getChildElementNamed(rootNode,"ParametersFileName");

        if(paramNode != null)
        {
            updateParametersInfo(paramNode);
        }
        
    }

    private void setLocale()
    {
        //  if(resourceBundle != null)
        //  {
        //     locale = resourceBundle.getLocale();
        //  }
        locale = Locale.getDefault();
        
    }
    
    private Element getChildElementNamed(Element parent,String myChildName)
    {
        NodeList childs   = parent.getElementsByTagName(myChildName);
        int noOfChilds    = childs.getLength();

        if(noOfChilds == -1 || noOfChilds == 0)
        {
            return null;
        }
        
        Element child    = (Element)childs.item(0);
        String nodeName  = child.getNodeName();
        
        if( nodeName.equals(myChildName) )
        {
            return child;
        }
        
        return null;
        
    }

    /**
     * Return the element value of the given Element
     *
     * @param element an <code>Element</code> value
     * @return a <code>String</code> value
     * @exception Exception if an error occurs
     */
    private String getElementValue(Element element)
    {
        Node node=element.getFirstChild();

        if(!(node.getNodeType()== Node.TEXT_NODE))
	{
            return null;
	}
        
	return node.getNodeValue();
    }
    
    
    private void updateParametersInfo(Element paramNode)
    {
        String fileName  = getElementValue(paramNode);
        URL paramFileURL = this.getClass().getResource("/"+fileName);

        if(paramFileURL == null)
        {
            System.out.println(" Error :: could not read the file \" "+ fileName + " \" from classpath.");
            paramFileURL = this.getClass().getResource("/"+"AboutDialogProps.xml");
        }
        
        Element rootNode = parse(paramFileURL);
        
        Element guiParamsElement = getChildElementNamed(rootNode,"GUIParameters");

        if(guiParamsElement == null)
        {
            return;
        }
        
        String image_file     = null;
        String product_name   = null;
        String product_ver    = null;
        String contact_info   = null;
        String tool_name      = null;
        String copyright_info = null;
        Element childElement  = null;

        childElement   = getChildElementNamed(guiParamsElement,"ImageName");
        if(childElement != null)
        {
            image_file      = getElementValue(childElement);
            setImageFileName(image_file);
        }
        
        childElement   = getChildElementNamed(guiParamsElement,"ProductName");
        if(childElement != null)
        {
            product_name   = getElementValue(childElement);
            productNameStr = product_name;
            //setProductName(nodeValue);
        }
        
        childElement   = getChildElementNamed(guiParamsElement,"ProductVersion");
        if(childElement != null)
        {
            product_ver    = getElementValue(childElement);
            productVersionStr = product_ver;
            //setProductVersion(nodeValue);
        }
        
        childElement   = getChildElementNamed(guiParamsElement,"ContactInfo");
        if(childElement != null)
        {
            contact_info   = getElementValue(childElement);
            setContactInfo(contact_info);
        }
        
        childElement   = getChildElementNamed(guiParamsElement,"ToolName");
        if(childElement != null)
        {
            tool_name      = getElementValue(childElement);
            setInternalToolName(tool_name);
        }
        
        childElement   = getChildElementNamed(guiParamsElement,"CopyrightInfo");
        copyright_info = getElementValue(childElement);
        setCopyrightInfo(copyright_info);
        
    }
    
    private Element parse(URL xmlFileURL)
    {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document xmlDocArg = null ;
        Element rootNode = null;
        
        try
        {
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            xmlDocArg = docBuilder.parse(xmlFileURL.toString());
            rootNode  = xmlDocArg.getDocumentElement();
        }
        catch (SAXParseException err)
        {
            StringBuffer errBuff = new StringBuffer();
            errBuff.append("Error occurred while parsing the uri ");
            errBuff.append(err.getSystemId ());
            errBuff.append(", at line " + err.getLineNumber ());
            errBuff.append("\n   " + err.getMessage ());
            // print stack trace as below
            //throwParseException(errBuff.toString());
            
        }
        catch (SAXException e)
        {
            Exception	x = e.getException ();
            if( x == null)
            {
                x = e;
            }
            
            
        }
        catch(javax.xml.parsers.ParserConfigurationException pcEx)
        {
            System.out.println(" Exception occured while parsing the xml file  "+xmlFileURL);
        }
        catch(java.io.IOException ie)
        {
            System.out.println("IO Exception ");
        }
        
        return rootNode;
    }


    private void setImageFileName(String imagePath)
    {
        imageFileName = imagePath;
    }

    public void setProductName(String str)
    {
        //This public method must be changed to private. 
        
        //productNameStr = str;
    }
    
    public void setProductVersion(String str)
    {
        //This public method must be changed to private.
        
        //productVersionStr = str;
    }

    private void setContactInfo(String htmlFileName)
    {
        contactInfoFile = htmlFileName;
    }

    private void setInternalToolName(String tool_name)
    {
        toolName = tool_name;
    }
    
    private void setCopyrightInfo(String copyrightInfoArg)
    {
        copyrightInfo = copyrightInfoArg;
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

    public void stop()
    {
        //<Begin_stop> 
        if(!running)
            return;
        running=false;

  
        //<End_stop>
    } 
    public void start()
    {
        //<Begin_start> 
        if(running)
            return;
        running=true;

  
        //<End_start>
    } 
    public void init()
    {

        //<Begin_init> 
	//  if(getParameter("RESOURCE_PROPERTIES" ) != null)
        //  	{
        //  		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
        //  	}
        //  	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+549,getPreferredSize().height+361); 
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
            showStatus(getString("Error in init method"),ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
        //<End_init>

        setTitle(getString("About ")+getString(productNameStr));
        productSPLbl.setVisible(false);
        toolNameLbl.setVisible(false);
        showToolNameLbl.setVisible(true);
        
        if(toolName != null && !toolName.equals(""))
        {
            showToolNameLbl.setText(" "+getString(toolName));
            toolNameLbl.setVisible(true);
            showToolNameLbl.setVisible(true);
        }
        

      

        okBtn.addActionListener(this);
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(dim.width * 0.55),415);
        
        //pack();
        setResizable(true);
        
        KeyStroke escStroke =  KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE,0);
        ((JComponent)getRootPane()).registerKeyboardAction( new EscListener(),"Cancel",escStroke,JComponent.WHEN_IN_FOCUSED_WINDOW);

    } 


    //Commented because we are reading the product version from the xml file.
    
    /*

      private String getServicePackVersion()
      {
      try
      {
      Class cl = getClass("com.adventnet.tools.update.installer.UpdateManager");
      String spVer = null;

      if( cl !=null )
      {
      String curPatchVersion = null;
      String productVersion = null;
      String confDir = "./conf";
                
      try
      {
      Class classArray[]=new Class[1];
      classArray[0]=Class.forName("java.lang.String");

      Method getCurPatchVersMeth = cl.getMethod("getServicePackVersionAlone",classArray);
      curPatchVersion = (String)getCurPatchVersMeth.invoke(null,new Object[]{confDir});
                    
      Method getProductVersionMeth = cl.getMethod("getProductVersion",classArray);
      productVersion = (String)getProductVersionMeth.invoke(null,new Object[]{confDir});
                    
      }
      catch(Exception ex)
      {
      curPatchVersion = null;
      }

                
      if(curPatchVersion != null )
      {
      //productSPLbl.setText(getString("Update :")+ curPatchVersion);
      //productSPLbl.setVisible(true);
                    

      if(productVersion.equals("2.3") && curPatchVersion.equals("8"))
      {
      spVer = "0.0";
      }
      else
      {
      spVer = "0"+"."+curPatchVersion;
      }
                    
      return spVer;
      }
      else
      {
      spVer = "0.0";
      return spVer;
      }
      }
            
      }
      catch(Exception ex)
      {
            
      }

      return null;
      }


    */
    public String getParameter(String input)
    {
        //<Begin_getParameter_String> 
        String value = null;
        if ( applet != null)
        {    
            value = applet.getParameter(input);
        }    
           
        if(value == null)
        {
            if (input.equals("MS_MODE")) value = "3"; 
        }
        return value;

  
        //<End_getParameter_String>
    } 
    public void setUpProperties()
    {
     

        //<Begin_setUpProperties> 

        try
        {
            topPanel.setBorder(new javax.swing.border.SoftBevelBorder(0));
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+topPanel,ex); 
        }

        //<UserCode_Begin_Bean_topPanel>

        //<UserCode_End_Bean_topPanel>

        try
        {
            warningLbl.setText(getString("Warning"));
            warningLbl.setFont(new Font("Dialog",0,12));
            warningLbl.setForeground(new Color(-16777216));
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+warningLbl,ex); 
        }

        //<UserCode_Begin_Bean_warningLbl>

        warningLbl.setFont(new Font("Dialog",0,12));
          
        //<UserCode_End_Bean_warningLbl>

        try
        {
            warningTxtArrea.setLineWrap(true);
            warningTxtArrea.setWrapStyleWord(true);
            warningTxtArrea.setFont(new Font("Dialog",0,10));
            warningTxtArrea.setEditable(false);
            warningTxtArrea.setSelectionColor(new Color(-3355444));
            warningTxtArrea.setOpaque(false);
            warningTxtArrea.setText(getString("This software is protected by copyright law. Unauthorized reproduction of this software, or any portion of it, is not permitted by law."));
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+warningTxtArrea,ex); 
        }

        //<UserCode_Begin_Bean_warningTxtArrea>

                    
          
        //<UserCode_End_Bean_warningTxtArrea>

        try
        {
            licenceInfoLbl.setForeground(new Color(-16777216));
            licenceInfoLbl.setFont(new Font("Dialog",0,12));
            licenceInfoLbl.setText(getString("This product is licensed to:"));
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+licenceInfoLbl,ex); 
        }

        //<UserCode_Begin_Bean_licenceInfoLbl>

        //<UserCode_End_Bean_licenceInfoLbl>

        try
        {
            licenceInfoTxtArea.setText(getString(""));
            licenceInfoTxtArea.setEditable(false);
            licenceInfoTxtArea.setSelectionColor(new Color(-3355444));
            licenceInfoTxtArea.setBorder(new javax.swing.border.BevelBorder(1));
            licenceInfoTxtArea.setOpaque(false);
            licenceInfoTxtArea.setRows(2);
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+licenceInfoTxtArea,ex); 
        }

        //<UserCode_Begin_Bean_licenceInfoTxtArea>
        javax.swing.border.CompoundBorder compBorder = new javax.swing.border.CompoundBorder(new javax.swing.border.BevelBorder(1),new javax.swing.border.EmptyBorder(3,3,3,3));
        licenceInfoTxtArea.setBorder(compBorder);

        //<UserCode_End_Bean_licenceInfoTxtArea>

        try
        {
            productNameLbl.setForeground(new Color(-16777216));
            productNameLbl.setFont(new Font("Dialog",1,14));
            
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+productNameLbl,ex); 
        }

        //<UserCode_Begin_Bean_productNameLbl>

        productNameLbl.setText(getString(productNameStr));
          
        //<UserCode_End_Bean_productNameLbl>

        try
        {
            productVerLbl.setForeground(new Color(-16777216));
            productVerLbl.setFont(new Font("Dialog",1,14));
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+productVerLbl,ex); 
        }

        //<UserCode_Begin_Bean_productVerLbl>

        productVerLbl.setText(getString(productVersionStr));
          
        //<UserCode_End_Bean_productVerLbl>

        try
        {
            productSPLbl.setToolTipText(getString(""));
            productSPLbl.setFont(new Font("Dialog",0,12));
            productSPLbl.setForeground(new Color(-16777216));
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+productSPLbl,ex); 
        }

        //<UserCode_Begin_Bean_productSPLbl>

        //<UserCode_End_Bean_productSPLbl>

        try
        {
            licenceInfoEditorPane.setEditable(false);
            licenceInfoEditorPane.setOpaque(false);
            licenceInfoEditorPane.setSelectionColor(new Color(-3355444));
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+licenceInfoEditorPane,ex); 
        }

        //<UserCode_Begin_Bean_licenceInfoEditorPane>

                  

        //<UserCode_End_Bean_licenceInfoEditorPane>

        try
        {
            toolNameLbl.setForeground(new Color(-16777216));
            toolNameLbl.setFont(new Font("Dialog",0,12));
            toolNameLbl.setText(getString("Tool Name: "));
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+toolNameLbl,ex); 
        }
        
        //<UserCode_Begin_Bean_toolNameLbl>
        
        //<UserCode_End_Bean_toolNameLbl>
        
        try
        {
            showToolNameLbl.setForeground(new Color(-16777216));
            showToolNameLbl.setText(getString(""));
            showToolNameLbl.setFont(new Font("Dialog",0,12));
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+showToolNameLbl,ex); 
        }

        //<UserCode_Begin_Bean_showToolNameLbl>

        //<UserCode_End_Bean_showToolNameLbl>

        try
        {
            copyRightLbl.setForeground(new Color(-16777216));
            copyRightLbl.setFont(new Font("Dialog",0,10));
            copyRightLbl.setText(getString(copyrightInfo));
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+copyRightLbl,ex); 
        }

        //<UserCode_Begin_Bean_copyRightLbl>

        //<UserCode_End_Bean_copyRightLbl>

        try
        {
            okBtn.setText(getString("OK"));
            okBtn.setFont(new Font("Dialog",0,12));
        }
        catch(Exception ex)
        {
            showStatus(getString("Exception while setting properties for bean ")+okBtn,ex); 
        }

        //<UserCode_Begin_Bean_okBtn>

        getRootPane().setDefaultButton(okBtn);
        okBtn.setActionCommand("OK");
                    
          
        //<UserCode_End_Bean_okBtn>
        //infoAndTitlePanel.setPreferredSize(new Dimension(infoAndTitlePanel.getPreferredSize().width+234,infoAndTitlePanel.getPreferredSize().height+177));
        aboutImageLbl.setPreferredSize(new Dimension(aboutImageLbl.getPreferredSize().width+0,aboutImageLbl.getPreferredSize().height+131));
        infoAndTitlePanelMain.setPreferredSize(new Dimension(infoAndTitlePanelMain.getPreferredSize().width+104,infoAndTitlePanelMain.getPreferredSize().height+10));
        topPanel.setPreferredSize(new Dimension(topPanel.getPreferredSize().width+10,topPanel.getPreferredSize().height+10));

  
        //<End_setUpProperties>


        aboutImageLbl.setPreferredSize(new Dimension(115,285));
        aboutImageLbl.setMinimumSize(new Dimension(115,285));
                
        licenceInfoEditorPane.addHyperlinkListener(this);
        licenceInfoEditorPane.setBackground(infoAndTitlePanel.getBackground());
        
        
        try
        {
            if(contactInfoFile.endsWith(".html"))
            {
                int endOfFileName = contactInfoFile.indexOf(".html");
                contactInfoFile   = contactInfoFile.substring(0,endOfFileName);
            }

            if(locale == null || locale.toString().equals("en_US") || locale.toString().equals(""))
            {
                URL fileURL = getFromClasspath(contactInfoFile.trim()+".html");
                licenceInfoEditorPane.setPage(fileURL);
            }
            else
            {
                URL fileURL = getFromClasspath(contactInfoFile+"_"+locale.toString()+".html");
                
                if(fileURL == null)
                {
                    fileURL = getFromClasspath(contactInfoFile.trim()+".html");
                }
                licenceInfoEditorPane.setPage(fileURL);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    private URL getFromClasspath(String path)
    {
        URL fileURL = null;
        
        try
        {
            fileURL = getClass().getResource("/"+path);
        }
        catch(Exception e)
        {
            System.out.println(" Error :: invalid url "+path);
        }
        
        return fileURL;
    }

    
    public void initVariables()
    {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        topPanel= new javax.swing.JPanel();
        warlingPanel= new javax.swing.JPanel();
        warningLbl= new javax.swing.JLabel();
        JSeparator1= new javax.swing.JSeparator();
        warningTxtArrea= new javax.swing.JTextArea();
        infoAndTitlePanelMain= new javax.swing.JPanel();
        aboutImageLbl= new javax.swing.JLabel();
        infoAndTitlePanel= new javax.swing.JPanel();
        infoPanel= new javax.swing.JPanel();
        licenceInfoLbl= new javax.swing.JLabel();
        licenceInfoTxtArea= new javax.swing.JTextArea();
        titlePanel= new javax.swing.JPanel();
        productNameLbl= new javax.swing.JLabel();
        productVerLbl= new javax.swing.JLabel();
        productSPLbl= new javax.swing.JLabel();
        licenceInfoEditorPane= new javax.swing.JEditorPane();
        toolDetPanel= new javax.swing.JPanel();
        toolNameLbl= new javax.swing.JLabel();
        showToolNameLbl= new javax.swing.JLabel();
        bottomPanel= new javax.swing.JPanel();
        copyRightLbl= new javax.swing.JLabel();
        okBtn= new javax.swing.JButton();
	scrollpane = new JScrollPane(infoAndTitlePanel);
  
        //<End_initVariables>

      
        //  ImageIcon aboutImg = com.adventnet.apiutils.Utility.findImage("/adventnet_aboutDlg.jpg",null);
        
//          if(aboutImg != null)
//          {
//              aboutImageLbl.setImageIcon(aboutImg);
//          }

        URL urlforImage = AboutDialog.class.getResource("/"+imageFileName);
                
        if(urlforImage != null)
        {
            ImageIcon icon = new ImageIcon(urlforImage);
            aboutImageLbl.setIcon(icon);
        }
        

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
    public void setUpGUI(Container container)
    {

      
        //<Begin_setUpGUI_Container> 
        container.add(Top,BorderLayout.CENTER);
        Top.setLayout(new BorderLayout(5,5));
        Top.add(topPanel,BorderLayout.CENTER);
        topPanel.setLayout(new BorderLayout(5,5));
        topPanel.add(warlingPanel,BorderLayout.SOUTH);
        warlingPanel.setLayout(new GridBagLayout());
        inset = new Insets(5,5,0,5);
        setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
        warlingPanel.add(warningLbl,cons);
        inset = new Insets(0,0,0,5);
        setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        warlingPanel.add(JSeparator1,cons);
        inset = new Insets(0,5,0,5);
        setConstraints(0,1,2,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        warlingPanel.add(warningTxtArrea,cons);
        topPanel.add(infoAndTitlePanelMain,BorderLayout.CENTER);
        infoAndTitlePanelMain.setLayout(new BorderLayout(5,5));
        infoAndTitlePanelMain.add(aboutImageLbl,BorderLayout.WEST);
        aboutImageLbl.setLayout(null);
        infoAndTitlePanelMain.add(scrollpane,BorderLayout.CENTER);
        infoAndTitlePanel.setLayout(new GridBagLayout());
        inset = new Insets(0,3,0,3);
        setConstraints(0,2,2,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        infoAndTitlePanel.add(infoPanel,cons);
        infoPanel.setLayout(new GridBagLayout());
        inset = new Insets(0,0,0,0);
        setConstraints(0,0,1,1,0.1,0.0,cons.WEST,cons.NONE,inset,0,0);
        infoPanel.add(licenceInfoLbl,cons);
	scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        inset = new Insets(0,0,0,0);
        setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        infoPanel.add(licenceInfoTxtArea,cons);
        inset = new Insets(5,3,0,3);
        setConstraints(0,0,2,1,0.1,0.01,cons.NORTH,cons.HORIZONTAL,inset,0,0);
        infoAndTitlePanel.add(titlePanel,cons);
        titlePanel.setLayout(new GridBagLayout());
        inset = new Insets(2,2,2,2);
        setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
        titlePanel.add(productNameLbl,cons);
        inset = new Insets(2,2,2,2);
        setConstraints(1,0,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
        titlePanel.add(productVerLbl,cons);
        inset = new Insets(2,2,2,5);
        setConstraints(0,1,2,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
        titlePanel.add(productSPLbl,cons);
        inset = new Insets(5,7,0,7);
        setConstraints(0,3,2,1,0.1,0.1,cons.SOUTH,cons.BOTH,inset,0,0);
        infoAndTitlePanel.add(licenceInfoEditorPane,cons);
        inset = new Insets(0,3,7,3);
        setConstraints(0,1,2,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        infoAndTitlePanel.add(toolDetPanel,cons);
        toolDetPanel.setLayout(new GridBagLayout());
        inset = new Insets(0,0,0,0);
        setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
        toolDetPanel.add(toolNameLbl,cons);
        inset = new Insets(0,0,0,0);
        setConstraints(1,0,0,1,0.01,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        toolDetPanel.add(showToolNameLbl,cons);
        Top.add(bottomPanel,BorderLayout.SOUTH);
        bottomPanel.setLayout(new GridBagLayout());
        inset = new Insets(5,5,0,0);
        setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
        bottomPanel.add(copyRightLbl,cons);
        inset = new Insets(5,0,5,5);
        setConstraints(1,0,1,1,0.1,0.0,cons.EAST,cons.NONE,inset,0,0);
        bottomPanel.add(okBtn,cons);

  
        //<End_setUpGUI_Container>


    } 
    public void setUpConnections()
    {
        //<Begin_setUpConnections> 

  
        //<End_setUpConnections>
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

    //*************UserAdded Methods******************************

    public void updateValues()
    {
		StringBuffer userInfoStrBuff = null;
		try
		{
			MainSocketClient msc = PureClientUtils.commonSocket;
			if(msc!=null)
			{
				userInfoStrBuff = msc.getLicenseDetails();
			}
		}
		catch(Exception ex)
		{
			showStatus(getString("Error in obtaining license details "),ex); // No I18N
		}
        if(userInfoStrBuff != null && userInfoStrBuff.length() > 0)
        {
            licenceInfoTxtArea.setText(userInfoStrBuff.toString());
        }
        else
        {
            licenceInfoTxtArea.setVisible(false);
            licenceInfoLbl.setVisible(false);
        }
        
    }


    /**private StringBuffer getUserInfo()
    {
        StringBuffer  infoStrBuff=null;
        
        try
        {
            String uName="";
            String compName="";
            String evalDays="";
            String userType="";
            
            Class cls        = Class.forName("com.adventnet.tools.prevalent.Wield");
            
            //Do not change the order of method calls
            
            Method getInsMeth = cls.getMethod("getInstance",null);
            Object wieldObj   = getInsMeth.invoke(null,null);

            Class classArray[]=new Class[1];
            classArray[0]=Class.forName("java.lang.String");

            //Fix - to avoid the error dialog, if license info not available

            StringBuffer strBuff = new StringBuffer();
            
            strBuff.append("p");
            strBuff.append("e");
            strBuff.append("ti");
            strBuff.append("n");
            strBuff.append("f");
            strBuff.append("o.d");
            strBuff.append("a");
            strBuff.append("t");
            
            String fileNameArg = strBuff.toString();
            String dir = System.getProperty("user.dir");
            File lFile = new File(dir+File.separatorChar+fileNameArg);
            
            if(!lFile.exists())
            {
                URL fileUrl = this.getClass().getResource("/"+fileNameArg);
                
                try
                {
                    String fName = getFileName(fileUrl);
                    lFile = new File(fName);
                }
                catch(Exception ex)
                {
                    // do nothing as file name will be lFile and we are not able get the file through url
                    return null;
                }
            }
            
            
            Method validateInvokeMeth   = cls.getMethod("validateInvoke",classArray);
            validateInvokeMeth.invoke(wieldObj,new Object[]{"About"});
                        
            Method getUsrNamMeth   = cls.getMethod("getUserName",null);
            uName     = (String)getUsrNamMeth.invoke(wieldObj,null);
            
            Method getCompNameMeth = cls.getMethod("getCompanyName",null);
            compName  = (String)getCompNameMeth.invoke(wieldObj,null);
            
            Method getUserTypeMeth = cls.getMethod("getUserType",null);
            userType  = (String)getUserTypeMeth.invoke(wieldObj,null);

            if(userType != null && userType.equals("R"))
            {
                infoStrBuff = new StringBuffer();
                //String uName = ind.getTheUserName();
                
                if( uName != null && !uName.equals("") && compName != null && !compName.equals(""))
                {
                    uName = uName.replace('@', ' ');
                    infoStrBuff.append(getString("User Name : ")+ uName + "\n");
                    infoStrBuff.append(getString("Company Name : ") + compName + "\n");
                    //infoStrBuff.append("Company Name : " + ind.getTheCompanyName() + "\n");
                    
                }
                
            }
            else if(userType != null && userType.equals("T"))
            {
                Method getEvalDaysMeth = cls.getMethod("getEvaluationDays",null);
                Object obj = getEvalDaysMeth.invoke(wieldObj,null);
                
                if(obj != null)
                {
                    evalDays = obj.toString();
                }
                
                licenceInfoLbl.setText(getString("Evaluation License Information"));
                infoStrBuff = new StringBuffer();
                //String uName = ind.getTheUserName();
                
                if( uName != null && !uName.equals("") && (evalDays != null && !evalDays.equals("")) )
                {
                    uName = uName.replace('@', ' ');
                    //uName = uName + "@"+ ind.getTheCompanyName();
                    infoStrBuff.append(getString("User Name : ") + uName + "\n");
                    //infoStrBuff.append("Days Remaining : " + ind.getNoOfEvalDays() + "\n");
                    infoStrBuff.append(getString("Days Remaining : ")+ evalDays + "\n");
                }
            }

        }
        catch(Exception ex)
        {
            infoStrBuff = null;
        }

        return infoStrBuff;
    }*/



    class EscListener implements java.awt.event.ActionListener, java.io.Serializable
    {
        public void actionPerformed( java.awt.event.ActionEvent arg0)
        {
            if(arg0.getActionCommand().equals("Cancel"))
            {
                setVisible(false);  //Code to be executed when <ESC> is pressed
            }
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        String actCommand = ae.getActionCommand();
        if(actCommand.equals("OK"))
        {
            setVisible(false);
        }
    }

    public void hyperlinkUpdate( HyperlinkEvent hle)
    {
        if(hle.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED)
        {
            if(applet != null)
            {
                applet.getAppletContext().showDocument(hle.getURL(),"_balnk");
            }
            else
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


    //***********************************************************



  

  

    public static void main (String[] args) 
    {
        AboutDialog aboutDlg = new AboutDialog(null, true);
        aboutDlg.init();
        aboutDlg.updateValues();
        aboutDlg.setVisible(true);
    }



 
    public void setProperties(java.util.Properties props)
    {
        //<Begin_setProperties_java.util.Properties> 

  
        //<End_setProperties_java.util.Properties>
    }




    public AboutDialog()
    {
        //<Begin_AboutDialog>
        pack();
  
        //<End_AboutDialog>
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public AboutDialog(java.applet.Applet applet)
    {
        //<Begin_AboutDialog_java.applet.Applet>
        this.applet = applet;
        pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
 
        //<End_AboutDialog_java.applet.Applet>
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


    private String getString(String key)
    {
        if(resourceBundle != null)
            {
                try
                    {
                        String value = resourceBundle.getString(key);
                        
                        if(value != null && !value.equals(""))
                            {
                                return value;
                            }
                        else
                            {
                                return key;
                            }
                    }
                catch(Exception ex)
                    {
                        return key;
                    }
            }
        else
            {
                return key;
            }
        
    }

    private String getFileName(URL fileUrl) throws Exception
    {
        String javaVersion = System.getProperty("java.version");
        String dir = System.getProperty("user.dir");
        String fileName = null;
        
        if(javaVersion.startsWith("1.4"))
        {
            // fix for JDK1.4
            try
            {
                Class a=Class.forName("java.net.URI");
                Class classArray[]=new Class[1];
                classArray[0]=Class.forName("java.lang.String");
                Constructor cons = a.getConstructor(classArray);
                Object [] args = { fileUrl.toString() };
                Object uri = cons.newInstance(args);
                Method method=a.getMethod("getPath",null);
                fileName = (String)method.invoke(uri,null);
            }
            catch(Exception ex)
            {
                throw ex;
            }
        }
        else
        {
            fileName = fileUrl.getFile();
        }

        return fileName;
    }
    
    
}













