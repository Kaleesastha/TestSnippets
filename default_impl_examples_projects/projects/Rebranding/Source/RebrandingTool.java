//$Id: RebrandingTool.java,v 1.2.4.1 2012/01/25 05:12:46 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$32
//<End_Version>
package com.adventnet.studio.tools.rebranding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Hashtable;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import javax.swing.filechooser.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.adventnet.beans.utilbeans.OptionDialogInformer;
import com.adventnet.beans.utilbeans.OptionDialogConstants;
import com.adventnet.nms.tools.utils.XmlUtil;
import com.adventnet.nms.tools.utils.JarUtil;

import com.adventnet.nms.tools.utils.FileUtil;
import com.adventnet.nms.tools.utils.PrintToTextArea;
import com.adventnet.beans.images.FilePreviewer;
import com.adventnet.nms.tools.confchanges.BrowserControl;

public class RebrandingTool extends JDialog implements ActionListener,Runnable
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel21 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton help = null;
	javax.swing.JButton rebrand = null;
	javax.swing.JButton cancel = null;
	javax.swing.JPanel JPanel22 = null;
	javax.swing.JRadioButton newvalues = null;
	javax.swing.JRadioButton oldvalues = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JLabel JLabel21 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JLabel JLabel7 = null;
	javax.swing.JLabel JLabel8 = null;
	javax.swing.JTextField companyName = null;
	javax.swing.JTextField productName = null;
	javax.swing.JTextField version = null;
	javax.swing.JTextField companyURL = null;
	javax.swing.JTextField motto = null;
	javax.swing.JTextField copyRight = null;
	javax.swing.JTextField splashImage = null;
	javax.swing.JTextField frameIcon = null;
	javax.swing.JTextField productLogo = null;
	javax.swing.JButton splashButton = null;
	javax.swing.JButton logoButton = null;
	javax.swing.JButton frameIconButton = null;
	javax.swing.JLabel JLabel12 = null;
	javax.swing.JTextField language = null;
	javax.swing.JLabel JLabel13 = null;
	javax.swing.JTextField country = null;
	javax.swing.JLabel JLabel10 = null;
	javax.swing.JLabel contactAddress = null;
	javax.swing.JTextField contactaddress = null;
	javax.swing.JButton caddressButton = null;
	javax.swing.JTextField aboutDialogText = null;
	javax.swing.JButton aboutDialogBrowse = null;
	javax.swing.JLabel JLabel19 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.ButtonGroup ButtonGroup1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	 Hashtable itemVsValue=null;//Will have the items to be rebranded and its value
	 String webNMSHome=null;// Will have the Web NMS home directory          
	 String confHome=null; // Will have the conf home directory 
	 Document rebrandingDoc=null;// WIll have the doc of WebNMS/conf/RebrandingItems.xml
            String lang=null;
            String coun=null;
	RebrandingFileFilter splashFilter=null;
	RebrandingFileFilter iconFilter=null;
	RebrandingFileFilter logoFilter=null;
	RebrandingFileFilter addressFilter=null;
           JFileChooser fileChooser=null;
           FilePreviewer preview=null;
           private boolean standalone=false;

  

public RebrandingTool(String webNMSHome,String confHome){
     this.webNMSHome=webNMSHome;
     this.confHome=confHome;
     this.lang=System.getProperty("LANGUAGE");
     this.coun=System.getProperty("COUNTRY");
}
 
public RebrandingTool(JFrame parent,boolean modal,String webNMSHome,String confHome){
     super(parent,modal);
     this.webNMSHome=webNMSHome;
     this.confHome=confHome;
     this.lang=System.getProperty("LANGUAGE");
     this.coun=System.getProperty("COUNTRY");
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
        if (initialized) return; 
        this.setSize(getPreferredSize().width+528,getPreferredSize().height+570); 
          setTitle("Rebranding Tool");
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
          showStatus("Error in init method",ex); 
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
                 value = (String)com.adventnet.apiutils.Utility.getParameter(input);
           }    
           if(value == null)
           {
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            JPanel21.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JPanel21,ex); 
          }

//<UserCode_Begin_Bean_JPanel21>

//<UserCode_End_Bean_JPanel21>

          try
          {
            help.setLabel("Help");
            help.setFont(new Font("dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+help,ex); 
          }

//<UserCode_Begin_Bean_help>
help.addActionListener(this);
//<UserCode_End_Bean_help>

          try
          {
            rebrand.setText("Rebrand");
            rebrand.setFont(new Font("dialog",0,12));
            rebrand.setMnemonic('r');
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+rebrand,ex); 
          }

//<UserCode_Begin_Bean_rebrand>
rebrand.addActionListener(this);
//<UserCode_End_Bean_rebrand>

          try
          {
            cancel.setFont(new Font("dialog",0,12));
            cancel.setNextFocusableComponent(companyName);
            cancel.setText("Close");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cancel,ex); 
          }

//<UserCode_Begin_Bean_cancel>
cancel.addActionListener(this);
//<UserCode_End_Bean_cancel>

          try
          {
            newvalues.setLabel("New Values");
            newvalues.setSelected(true);
            newvalues.setActionCommand("newvalues");
            newvalues.setToolTipText("Enter the details for rebranding ");
            newvalues.setFont(new Font("dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+newvalues,ex); 
          }

//<UserCode_Begin_Bean_newvalues>

newvalues.addActionListener(this);
ButtonGroup1.add(newvalues);
//<UserCode_End_Bean_newvalues>

          try
          {
            oldvalues.setLabel("Old Values");
            oldvalues.setActionCommand("oldvalues");
            oldvalues.setToolTipText("Enter the Values If you have rebranded manually ");
            oldvalues.setFont(new Font("dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+oldvalues,ex); 
          }

//<UserCode_Begin_Bean_oldvalues>
oldvalues.addActionListener(this);
ButtonGroup1.add(oldvalues);
//<UserCode_End_Bean_oldvalues>

          try
          {
            JLabel1.setText("Company Name");
            JLabel1.setFont(new Font("dialog",0,12));
            JLabel1.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel2.setText("Product Name");
            JLabel2.setFont(new Font("dialog",0,12));
            JLabel2.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel21.setText("Version");
            JLabel21.setFont(new Font("dialog",0,12));
            JLabel21.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel21,ex); 
          }

//<UserCode_Begin_Bean_JLabel21>

//<UserCode_End_Bean_JLabel21>

          try
          {
            JLabel3.setText("Company URL");
            JLabel3.setFont(new Font("dialog",0,12));
            JLabel3.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel4.setText("Motto");
            JLabel4.setFont(new Font("dialog",0,12));
            JLabel4.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JLabel5.setText("Copyright");
            JLabel5.setFont(new Font("dialog",0,12));
            JLabel5.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JLabel6.setText("Splash Image");
            JLabel6.setFont(new Font("dialog",0,12));
            JLabel6.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>

          try
          {
            JLabel7.setText("Frame Icon");
            JLabel7.setFont(new Font("dialog",0,12));
            JLabel7.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel7,ex); 
          }

//<UserCode_Begin_Bean_JLabel7>

//<UserCode_End_Bean_JLabel7>

          try
          {
            JLabel8.setText("Product Logo");
            JLabel8.setFont(new Font("dialog",0,12));
            JLabel8.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel8,ex); 
          }

//<UserCode_Begin_Bean_JLabel8>

//<UserCode_End_Bean_JLabel8>

//<UserCode_Begin_Bean_companyName>
	companyName.setText(itemVsValue.get("$COMPANY").toString());
//<UserCode_End_Bean_companyName>

//<UserCode_Begin_Bean_productName>
productName.setText(itemVsValue.get("$PRODUCT").toString());

//<UserCode_End_Bean_productName>

//<UserCode_Begin_Bean_version>
version.setText(itemVsValue.get("$VERSION").toString());

//<UserCode_End_Bean_version>

//<UserCode_Begin_Bean_companyURL>
companyURL.setText(itemVsValue.get("$URL").toString());
//<UserCode_End_Bean_companyURL>

//<UserCode_Begin_Bean_motto>
motto.setText(itemVsValue.get("$MOTTO").toString());
//<UserCode_End_Bean_motto>

//<UserCode_Begin_Bean_copyRight>
copyRight.setText(itemVsValue.get("$COPYRIGHT").toString());
//<UserCode_End_Bean_copyRight>

          try
          {
            splashImage.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+splashImage,ex); 
          }

//<UserCode_Begin_Bean_splashImage>
splashImage.setText(itemVsValue.get("$SPLASH").toString());
//<UserCode_End_Bean_splashImage>

          try
          {
            frameIcon.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+frameIcon,ex); 
          }

//<UserCode_Begin_Bean_frameIcon>
frameIcon.setText(itemVsValue.get("$FRAMEICON").toString());
//<UserCode_End_Bean_frameIcon>

          try
          {
            productLogo.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+productLogo,ex); 
          }

//<UserCode_Begin_Bean_productLogo>
productLogo.setText(itemVsValue.get("$LOGO").toString());
//<UserCode_End_Bean_productLogo>

          try
          {
            splashButton.setText("...");
            splashButton.setFont(new Font("dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+splashButton,ex); 
          }

//<UserCode_Begin_Bean_splashButton>
splashButton.addActionListener(this);
//<UserCode_End_Bean_splashButton>

          try
          {
            logoButton.setText("...");
            logoButton.setFont(new Font("dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+logoButton,ex); 
          }

//<UserCode_Begin_Bean_logoButton>
logoButton.addActionListener(this);
//<UserCode_End_Bean_logoButton>

          try
          {
            frameIconButton.setText("...");
            frameIconButton.setFont(new Font("dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+frameIconButton,ex); 
          }

//<UserCode_Begin_Bean_frameIconButton>
frameIconButton.addActionListener(this);
//<UserCode_End_Bean_frameIconButton>

          try
          {
            JLabel12.setFont(new Font("dialog",0,12));
            JLabel12.setText("Language");
            JLabel12.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel12,ex); 
          }

//<UserCode_Begin_Bean_JLabel12>

//<UserCode_End_Bean_JLabel12>

//<UserCode_Begin_Bean_language>
	language.setText(itemVsValue.get("$LANGUAGE").toString());

//<UserCode_End_Bean_language>

          try
          {
            JLabel13.setFont(new Font("dialog",0,12));
            JLabel13.setText("Country");
            JLabel13.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel13,ex); 
          }

//<UserCode_Begin_Bean_JLabel13>

//<UserCode_End_Bean_JLabel13>

//<UserCode_Begin_Bean_country>
     country.setText(itemVsValue.get("$COUNTRY").toString());

//<UserCode_End_Bean_country>

          try
          {
            JLabel10.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel10,ex); 
          }

//<UserCode_Begin_Bean_JLabel10>

//<UserCode_End_Bean_JLabel10>

          try
          {
            contactAddress.setFont(new Font("dialog",0,12));
            contactAddress.setName("Contact Address");
            contactAddress.setText("Contact Address");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+contactAddress,ex); 
          }

//<UserCode_Begin_Bean_contactAddress>

//<UserCode_End_Bean_contactAddress>

          try
          {
            contactaddress.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+contactaddress,ex); 
          }

//<UserCode_Begin_Bean_contactaddress>

//<UserCode_End_Bean_contactaddress>

          try
          {
            caddressButton.setName("browse");
            caddressButton.setActionCommand("cAddButton");
            caddressButton.setFont(new Font("dialog",0,12));
            caddressButton.setLabel("...");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+caddressButton,ex); 
          }

//<UserCode_Begin_Bean_caddressButton>
contactaddress.setText(itemVsValue.get("$CONTACTADDRESS").toString());
caddressButton.addActionListener(this);
//<UserCode_End_Bean_caddressButton>

          try
          {
            aboutDialogText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+aboutDialogText,ex); 
          }

//<UserCode_Begin_Bean_aboutDialogText>
aboutDialogText.setText(itemVsValue.get("$ABOUTDIALOG").toString());
//<UserCode_End_Bean_aboutDialogText>

          try
          {
            aboutDialogBrowse.setLabel("...");
            aboutDialogBrowse.setFont(new Font("dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+aboutDialogBrowse,ex); 
          }

//<UserCode_Begin_Bean_aboutDialogBrowse>
aboutDialogBrowse.addActionListener(this);
//<UserCode_End_Bean_aboutDialogBrowse>

          try
          {
            JLabel19.setFont(new Font("dialog",0,12));
            JLabel19.setText("About Dialog Image");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel19,ex); 
          }

//<UserCode_Begin_Bean_JLabel19>

//<UserCode_End_Bean_JLabel19>

          try
          {
            JTextArea1.setBackground(new Color(-3355444));
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setLineWrap(true);
            JTextArea1.setEditable(false);
            JTextArea1.setFont(new Font("dialog",0,12));
            JTextArea1.setText("If Rebranding has been done manually prior using this tool, select \"Old Values\" to specify the Rebranded Entries and click \"Update\". Then, Continue by providing \"New Values\" for Rebranding. ");

            JTextArea1.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JTextArea1,ex); 
          }

//<UserCode_Begin_Bean_JTextArea1>
	JTextArea1.setText("If rebranding has been done manually prior to using this tool, select \"Old Values\" to specify the rebranded entries and click \"Update\", then continue by providing \"New Values\" for rebranding.");

//<UserCode_End_Bean_JTextArea1>
		cancel.setPreferredSize(new Dimension(cancel.getPreferredSize().width+7,cancel.getPreferredSize().height+0));
		help.setPreferredSize(new Dimension(help.getPreferredSize().width+1,help.getPreferredSize().height+0));

  
          //<End_setUpProperties>
  Toolkit kit = Toolkit.getDefaultToolkit();
  Dimension dim = kit.getScreenSize();
  this.setLocation((dim.width-getWidth())/2,(dim.height-getHeight())/2);

 this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
 this.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent we){
           if(standalone){
                    System.exit(0);
           }
           else{
                   setVisible(false);
           }
   }
 });
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel21= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        help= new javax.swing.JButton();
        rebrand= new javax.swing.JButton();
        cancel= new javax.swing.JButton();
        JPanel22= new javax.swing.JPanel();
        newvalues= new javax.swing.JRadioButton();
        oldvalues= new javax.swing.JRadioButton();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();
        JLabel21= new javax.swing.JLabel();
        JLabel3= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        JLabel5= new javax.swing.JLabel();
        JLabel6= new javax.swing.JLabel();
        JLabel7= new javax.swing.JLabel();
        JLabel8= new javax.swing.JLabel();
        companyName= new javax.swing.JTextField();
        productName= new javax.swing.JTextField();
        version= new javax.swing.JTextField();
        companyURL= new javax.swing.JTextField();
        motto= new javax.swing.JTextField();
        copyRight= new javax.swing.JTextField();
        splashImage= new javax.swing.JTextField();
        frameIcon= new javax.swing.JTextField();
        productLogo= new javax.swing.JTextField();
        splashButton= new javax.swing.JButton();
        logoButton= new javax.swing.JButton();
        frameIconButton= new javax.swing.JButton();
        JLabel12= new javax.swing.JLabel();
        language= new javax.swing.JTextField();
        JLabel13= new javax.swing.JLabel();
        country= new javax.swing.JTextField();
        JLabel10= new javax.swing.JLabel();
        contactAddress= new javax.swing.JLabel();
        contactaddress= new javax.swing.JTextField();
        caddressButton= new javax.swing.JButton();
        aboutDialogText= new javax.swing.JTextField();
        aboutDialogBrowse= new javax.swing.JButton();
        JLabel19= new javax.swing.JLabel();
        JTextArea1= new javax.swing.JTextArea();
        ButtonGroup1= new javax.swing.ButtonGroup();

  
        //<End_initVariables>
	itemVsValue=getRebrandingItems();
	fileChooser=new JFileChooser();
	preview=new FilePreviewer(fileChooser);
	splashFilter=new RebrandingFileFilter(new String[]{"png"});
	iconFilter=new RebrandingFileFilter(new String[]{"jpg"});
	logoFilter=new RebrandingFileFilter(new String[]{"png"});
	addressFilter=new RebrandingFileFilter(new String[]{"html"});
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel21,BorderLayout.SOUTH);
JPanel21.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.EAST,cons.HORIZONTAL,inset,0,0);
JPanel21.add(JPanel2,cons);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(help);
JPanel2.add(rebrand);
JPanel2.add(cancel);
inset = new Insets(5,5,5,0);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel21.add(JPanel22,cons);
JPanel22.setLayout(new FlowLayout(0,5,5));
JPanel22.add(newvalues);
JPanel22.add(oldvalues);
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(10,5,5,0);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel2,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel21,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel3,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel4,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,6,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel5,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,7,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel6,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,8,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel7,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,9,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel8,cons);
inset = new Insets(10,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(companyName,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(productName,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(version,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(companyURL,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(motto,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,6,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(copyRight,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,7,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(splashImage,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,8,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(frameIcon,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,9,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(productLogo,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,7,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(splashButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,9,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(logoButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,8,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(frameIconButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,12,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel12,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,12,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(language,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,13,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel13,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,13,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(country,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,14,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JLabel10,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,11,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(contactAddress,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,11,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(contactaddress,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,11,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(caddressButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,10,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(aboutDialogText,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,10,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(aboutDialogBrowse,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,10,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel19,cons);
inset = new Insets(10,5,5,5);
setConstraints(0,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JTextArea1,cons);

  
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





  public RebrandingTool()
  {
       webNMSHome="."; //Just for testing in clientbuilder
       lang="en";//Just for testing in clientbuilder
       coun="US";//Just for testing in clientbuilder
       confHome=".";//Just for testing in clientbuilder
    //<Begin_RebrandingTool>
    pack();
  
    //<End_RebrandingTool>
  }

  public RebrandingTool(java.applet.Applet applet)
  {
    //<Begin_RebrandingTool_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_RebrandingTool_java.applet.Applet>
  } 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
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
  
  private Hashtable getRebrandingItems(){
 	  rebrandingDoc=XmlUtil.parseAndCreateDocument(replaceBackSlash(confHome+"/conf/RebrandingItems.xml"),false);
	  if(rebrandingDoc ==null){
	       return null;
	  }
	  Hashtable table=new Hashtable();
 	  Element root=rebrandingDoc.getDocumentElement();
 	  NodeList itemList=root.getElementsByTagName("item");
 	  int length=itemList.getLength();
 	  for(int i=0;i<length;i++){
 	       if(itemList.item(i).getNodeType()==Node.ELEMENT_NODE){
 	            Element node=(Element)itemList.item(i);
 	            String name=node.getAttribute("name");
 	            String value=node.getAttribute("value");
 	            int index=value.indexOf("$WEBNMSHOME");
 	            if(index!=-1){
 	                 value=value.substring(0,index)+webNMSHome+value.substring(index+"$WEBNMSHOME".length());
	 	       }
 	                   table.put(name,value);
                 }
 	  }
	if(lang!=null&&!lang.trim().equals("")){
 	  table.put("$LANGUAGE",lang);
	}
	else{
             table.put("$LANGUAGE","en");
 	}
	if(coun!=null&& !lang.trim().equals("")){
 	  table.put("$COUNTRY",coun);
	}
	else{
      	  table.put("$COUNTRY","US");
	}
 	  table.put("$PROJECTHOME",confHome);
 	  return table;
 	      	
  
  }
  private String replaceBackSlash(String file){
       file=file.replace('\\',File.separatorChar);
       file=file.replace('/',File.separatorChar);
       return file;
}


public void actionPerformed(ActionEvent ae){
     
	if (ae.getActionCommand().equalsIgnoreCase("rebrand") || ae.getActionCommand().equalsIgnoreCase("update")) 
	{
	  if(companyName.getText().trim().equals(""))
          {
                JOptionPane.showMessageDialog(this,"Company name can't be empty","Message",JOptionPane.INFORMATION_MESSAGE);//No Internationalization
		companyName.requestFocus();
                return;
          }
          if(productName.getText().trim().equals(""))
          {
                JOptionPane.showMessageDialog(this,"Product name can't be empty","Message",JOptionPane.INFORMATION_MESSAGE);//No Internationalization
		productName.requestFocus();
                return;
          }
          if(version.getText().trim().equals(""))
          {
                JOptionPane.showMessageDialog(this,"Version can't be empty","Message",JOptionPane.INFORMATION_MESSAGE);//No Internationalization
		version.requestFocus();
                return;
          }
          if(companyURL.getText().trim().equals(""))
          {
                JOptionPane.showMessageDialog(this,"Company URL can't be empty","Message",JOptionPane.INFORMATION_MESSAGE);//No Internationalization
		companyURL.requestFocus();
                return;
          }
          if(motto.getText().trim().equals(""))
          {
                JOptionPane.showMessageDialog(this,"Motto can't be empty","Message",JOptionPane.INFORMATION_MESSAGE);// No Internationalization
		motto.requestFocus();
                return;
          }
          if(copyRight.getText().trim().equals(""))
          {
                JOptionPane.showMessageDialog(this,"Copyright field can't be empty","Message",JOptionPane.INFORMATION_MESSAGE);//No Internationalization
		copyRight.requestFocus();
                return;
          }
	}
     
     if(ae.getActionCommand().equalsIgnoreCase("rebrand"))
     {

          if(!checkIfAnyItemsModified()){
               JOptionPane.showMessageDialog(this,"There is no new value to rebrand","Message",JOptionPane.INFORMATION_MESSAGE);
               return;
          }
          if(!valuesProper()){
               return;
          }
          rebrand.setEnabled(false);
          cancel.setEnabled(false);
          Thread rebrandThread=new Thread(this);
          rebrandThread.start();
     }
     else if(ae.getActionCommand().equalsIgnoreCase("update"))
     {
          
          
              String message=null;	
    OptionDialogInformer informer=new OptionDialogInformer();
           rebrand.setEnabled(false);
      this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          populateValuesInHash("");
    writeRebrandingItemsXML();
    setTextValues();
    message="Old Values updated successfully ";
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));         
        int dialogType=OptionDialogConstants.INFORM;
        informer.showOptionDialog(this,"Message",OptionDialogConstants.OK_DETAILS,dialogType,message, "Updated");
      rebrand.setEnabled(true);
      
          //update the xml .
          
     }
     else if(ae.getSource()==oldvalues)
     {
          if(oldvalues.isSelected())
          {
          //update.setEnabled(true);
          rebrand.setLabel("Update");
          rebrand.setActionCommand("update");
          newvalues.setSelected(false);
          //rebrand.setEnabled(false);
          setTextValues();
          }
     }
     else if(ae.getSource()==newvalues)
     {
          if(newvalues.isSelected())
          {
               //rebrand.setEnabled(true);
          //update.setEnabled(false);
          rebrand.setLabel("Rebrand");
          rebrand.setActionCommand("rebrand");
          oldvalues.setSelected(false);
          }
          setTextValues();
     }
     else if(ae.getSource()==cancel){
          if(standalone){
               System.exit(0);
          }
          else{
	          this.setVisible(false);
          }
     }
     else if(ae.getSource()==help){
          String prefix="";
          if(standalone){
                 prefix="help/developer_guide";
          }
          File helpFile=new File(prefix+"/designer_tools/rebranding_tool.html");
          if(!helpFile.exists()){
                //If help File doesn't exist show the index.html
                helpFile = new File(prefix+"/index.html");
            }
            BrowserControl.displayURL(helpFile.getAbsolutePath());

     }

     else if(ae.getSource()==splashButton){
          removeFilters();
         fileChooser.setAccessory( preview);
         fileChooser.addChoosableFileFilter(splashFilter);
         fileChooser.setSelectedFile(new File(splashImage.getText()));
          if(fileChooser.showOpenDialog(this)==fileChooser.APPROVE_OPTION){
     	   	splashImage.setText(fileChooser.getSelectedFile().getPath());	
         }
     }
     else if(ae.getSource()==caddressButton){
           removeFilters();
         fileChooser.setAccessory( preview);
         fileChooser.addChoosableFileFilter(addressFilter);
         fileChooser.setSelectedFile(new File(splashImage.getText()));
          if(fileChooser.showOpenDialog(this)==fileChooser.APPROVE_OPTION){
     	   	contactaddress.setText(fileChooser.getSelectedFile().getPath());
     	   		
         }
     }
     else if(ae.getSource()==aboutDialogBrowse){
          
          removeFilters();
         fileChooser.setAccessory( preview);
         fileChooser.addChoosableFileFilter(iconFilter);
         fileChooser.setSelectedFile(new File(aboutDialogText.getText()));
          if(fileChooser.showOpenDialog(this)==fileChooser.APPROVE_OPTION){
          
     	   	aboutDialogText.setText(fileChooser.getSelectedFile().getPath());	
         }
          
     }
     else if(ae.getSource()==logoButton){
          removeFilters();
          fileChooser.setAccessory( preview);
          fileChooser.addChoosableFileFilter(logoFilter);
          fileChooser.setSelectedFile(new File(productLogo.getText()));
          if(fileChooser.showOpenDialog(this)==fileChooser.APPROVE_OPTION){
               productLogo.setText(fileChooser.getSelectedFile().getPath());	
          }
     }
     else if(ae.getSource()==frameIconButton){
          removeFilters();
          fileChooser.setAccessory( preview);
          fileChooser.addChoosableFileFilter(iconFilter);
          fileChooser.setSelectedFile(new File(frameIcon.getText()));
          if(fileChooser.showOpenDialog(this)==fileChooser.APPROVE_OPTION){
               frameIcon.setText(fileChooser.getSelectedFile().getPath());	
          }
     }
}
private void removeFilters(){
     FileFilter[] filters=fileChooser.getChoosableFileFilters();
     for(int i=0;i<filters.length;i++){
          fileChooser.removeChoosableFileFilter(filters[i]);
     }
}

private boolean valuesProper(){
     File file=new File(splashImage.getText());
     if(!file.isFile()){
          JOptionPane.showMessageDialog(this,"Please enter a valid splash image","Message",JOptionPane.INFORMATION_MESSAGE);
          return false;
     }
     file=new File(frameIcon.getText());
     if(!file.isFile()){
          JOptionPane.showMessageDialog(this,"Please enter a valid Frame Icon","Message",JOptionPane.INFORMATION_MESSAGE);
          return false;
     }
     file=new File(productLogo.getText());
     if(!file.isFile()){
          JOptionPane.showMessageDialog(this,"Please enter a valid Product Logo","Message",JOptionPane.INFORMATION_MESSAGE);
          return false;
     }
     if(language.getText().trim().equals("")){
	JOptionPane.showMessageDialog(this,"Please enter a valid language name","Message",JOptionPane.INFORMATION_MESSAGE);
           return false;
     }          
     if(country.getText().trim().equals("")){
	JOptionPane.showMessageDialog(this,"Please enter a valid country name","Message",JOptionPane.INFORMATION_MESSAGE);
           return false;
     }          
     return true;
}
//checks whether any items got modified
private boolean checkIfAnyItemsModified(){
     if(!companyName.getText().trim().equals(itemVsValue.get("$COMPANY").toString())){
          return true;
     }
     else  if(!productName.getText().trim().equals(itemVsValue.get("$PRODUCT").toString())){
          return true;
     }
     else  if(!version.getText().trim().equals(itemVsValue.get("$VERSION").toString())){
          return true;
     }
     else  if(!companyURL.getText().trim().equals(itemVsValue.get("$URL").toString())){
          return true;
     }
     else  if(!motto.getText().trim().equals(itemVsValue.get("$MOTTO").toString())){
          return true;
     }
      else  if(!copyRight.getText().trim().equals(itemVsValue.get("$COPYRIGHT").toString())){
          return true;
     }
     else  if(!splashImage.getText().trim().equals(itemVsValue.get("$SPLASH").toString())){
          return true;
     }
     else  if(!frameIcon.getText().trim().equals(itemVsValue.get("$FRAMEICON").toString())){
          return true;
     }
      else  if(!productLogo.getText().trim().equals(itemVsValue.get("$LOGO").toString())){
          return true;
     }
      else  if(itemVsValue.get("$LANGUAGE")!=null && !language.getText().trim().equals(itemVsValue.get("$LANGUAGE")
		.toString())){
          return true;
     }
      else  if(itemVsValue.get("$COUNTRY")!=null&&!country.getText().trim().equals(itemVsValue.get("$COUNTRY").toString())){
          return true;
     }
     else  if(itemVsValue.get("$ABOUTDIALOG")!=null&&!aboutDialogText.getText().trim().equals(itemVsValue.get("$ABOUTDIALOG").toString())){
          return true;
     }
     
     else  if(itemVsValue.get("$CONTACTADDRESS")!=null&&!aboutDialogText.getText().trim().equals(itemVsValue.get("$CONTACTADDRESS").toString())){
          return true;
     }
	return false;
}


//Rebranding will be done in a seperate thread.
public void run(){
     
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
   
    // Redirecting the out and err streams to logs
     
    PrintToTextArea printToArea=new PrintToTextArea();
    JTextArea textArea=new JTextArea();
    printToArea.setTextArea(textArea);
    File logDir=new File(replaceBackSlash(confHome+"/logs"));
    if(!logDir.exists()){
        logDir.mkdirs();
    } 
    Date date=new Date();
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss");
    String backupName=sdf.format(date);
    File logFile=new File(logDir,"rebranding_"+backupName+".log");

    try{
               printToArea.setFileName(logFile);
               System.setOut(printToArea);
	    System.setErr(printToArea);
    }
    catch(Exception e){
         System.out.println("Unable to log the messages");
         e.printStackTrace();
    }

 
    populateValuesInHash("NEW");
    backupName=confHome+"/rebrandingBackup/"+backupName;
    copyImages();	      
    
    String[] extractFiles ={"adventnet_aboutDlg.jpg","contact_address.html","AboutDialogProps.xml"};    
    File aboutDialogJar=new File(replaceBackSlash(webNMSHome+"/classes/AdventNetAboutDialog.jar"));
    if(standalone) {
        // Extract the AdventNetAboutDialog.jar to update AboutDialogProps.xml
        File targetDir=new File(replaceBackSlash(webNMSHome + File.separator + "jar_files"));
        try
            {
                JarUtil.extractFromJar(aboutDialogJar,targetDir,extractFiles);
            }
        catch(IOException e)
            {
                System.out.println("IO exception while extracting the jar "+e);
                e.printStackTrace();
            }
    }
  
    boolean result=updateConfFiles(backupName);
    if(result){	
	 result= updateProperties(backupName);	     
    }

    String[] entryNames = {"com/adventnet/nms/startclient/adventneticon.jpg",
                           "com/adventnet/nms/startclient/LoginInfoUI.properties"};
   
    if(standalone) {
        FileUtil.copyFile(frameIcon.getText(), webNMSHome + "/jar_files/" + entryNames[0]);
        File clientClassesJar=new File(replaceBackSlash(webNMSHome+"/classes/NmsClientClasses.jar"));
        File[] newFrameIcon=new File[2];
    
        newFrameIcon[0]=new File(webNMSHome + "/jar_files/" + entryNames[0]);
        newFrameIcon[1]=new File(webNMSHome + "/classes/" + entryNames[1]);
        updateTheJar(clientClassesJar, newFrameIcon, entryNames);
        System.out.println("NmsClientClasses.jar Updated");
    }
    else {
        FileUtil.copyFile(frameIcon.getText(), confHome + "/jar_files/" + entryNames[0]);
        FileUtil.copyFile(confHome + File.separator + "classes/com/adventnet/nms/startclient/LoginInfoUI.properties",
                          confHome + File.separator + "jar_files/com/adventnet/nms/startclient/LoginInfoUI.properties");
    }

    // code for updating the AdventNetCLI.jar with the Frame icon
    String treelogo[] = {"com/adventnet/cli/beans/images/treelogo.jpg"};
    if (standalone) {
        File clijar = new File(webNMSHome + "/classes/AdventNetCLI.jar");
        FileUtil.copyFile(frameIcon.getText(), webNMSHome + "/jar_files/" + treelogo[0]);
        updateTheJar(clijar, new File[] {new File(webNMSHome + "/jar_files/" + treelogo[0])}, treelogo);
        System.out.println("AdventNetCLI.jar Updated");
    }
    else {
        // in case of standalone copying the frameicon to as treelogo.jpg
        FileUtil.copyFile(frameIcon.getText(), confHome + "/jar_files/" + treelogo[0]);
    }

    // for standalone tool, updating the entries in the jar file
    if(standalone) {
        // copying the files given by the user under the jar_files directory
        FileUtil.copyFile(aboutDialogText.getText(),replaceBackSlash(webNMSHome + "/jar_files/adventnet_aboutDlg.jpg"));
        FileUtil.copyFile(contactaddress.getText(),replaceBackSlash(webNMSHome + "/jar_files/contact_address.html"));
        File[] fileEntries=new File[3];
        fileEntries[0]=new File(webNMSHome + "/jar_files/adventnet_aboutDlg.jpg");
        fileEntries[1]=new File(webNMSHome + "/jar_files/contact_address.html");
        fileEntries[2]=new File(webNMSHome + "/jar_files/AboutDialogProps.xml");
        updateTheJar(aboutDialogJar, fileEntries, extractFiles);
        System.out.println("AdventNetAboutDialog.jar Updated");
    }
    else {
        // copying the files given by the user under the jar_files directory
        FileUtil.copyFile(aboutDialogText.getText(),replaceBackSlash(confHome + "/jar_files/adventnet_aboutDlg.jpg"));
        FileUtil.copyFile(contactaddress.getText(),replaceBackSlash(confHome + "/jar_files/contact_address.html"));
    }

    // for standalone tool, removing the files once jar updation is completed
    if(standalone) {
        FileUtil.deleteDirectory(new File("jar_files"));
    }
          
    String title=null;
    int dialogType;
    String message=null;	
    OptionDialogInformer informer=new OptionDialogInformer();
    if(result){
    	title="Message";
	dialogType=OptionDialogConstants.INFORM;
	
	message="Rebranding completed successfully";
		
    }
    else{
	title="Error";
	dialogType=OptionDialogConstants.ERROR;
	message="Rebranding Failed";
    }
    populateValuesInHash("");
    writeRebrandingItemsXML();
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));         
    String details="Refer "+confHome+"/logs/ directory \n\n"+textArea.getText();	
    informer.showOptionDialog(this,title,OptionDialogConstants.OK_DETAILS,dialogType,message,details);
    rebrand.setEnabled(true);
    cancel.setEnabled(true);

}

    public void updateTheJar(File jarFile,File[] fileList ,String[] entryNames)
    {
        try
            {
                //JarUtil.removeEntries(jarFile, entryNames);
                JarUtil.addEntries(jarFile, fileList, entryNames);
                 
            }
        catch(IOException ioe)
            {
                System.out.println("IO Exception caught while updating the Jar "+ioe);
                ioe.printStackTrace();
            }
    }


//Populate the hashtable with user given values
private void populateValuesInHash(String type){
     itemVsValue.put("$"+type+"COMPANY",companyName.getText().trim());
     itemVsValue.put("$"+type+"PRODUCT",productName.getText().trim());
     itemVsValue.put("$"+type+"VERSION",version.getText().trim());
     itemVsValue.put("$"+type+"URL",companyURL.getText().trim());
     itemVsValue.put("$"+type+"MOTTO",motto.getText().trim());
     itemVsValue.put("$"+type+"COPYRIGHT",copyRight.getText().trim());
     itemVsValue.put("$"+type+"SPLASH",splashImage.getText().trim());
     itemVsValue.put("$"+type+"FRAMEICON",frameIcon.getText().trim());
     itemVsValue.put("$"+type+"LOGO",productLogo.getText().trim());
     itemVsValue.put("$LANGUAGE",language.getText().trim());
     itemVsValue.put("$COUNTRY",country.getText().trim());
     itemVsValue.put("$"+type+"ABOUTDIALOG",aboutDialogText.getText().trim());
      itemVsValue.put("$"+type+"CONTACTADDRESS",contactaddress.getText().trim());
}


private void copyImages(){
     
     //Copy the given splash image under WebNMS/images directory and 
     //give ./images/imageName in all the conf files.
     String sourceImage=replaceBackSlash(itemVsValue.get("$NEWSPLASH").toString());
     String imageName=sourceImage.substring(sourceImage.lastIndexOf(File.separatorChar)+1);
     String destImage=replaceBackSlash(confHome+"/images/"+imageName);
     System.out.println("Copying file from " +sourceImage+" to "+destImage);
     FileUtil.copyFile(sourceImage,destImage);
     itemVsValue.put("$NEWSPLASH","./images/"+imageName);
     //Copy the splash image as screen.png and about.png files
     destImage=replaceBackSlash(confHome+"/images/screen.png");
      System.out.println("Copying file from " +sourceImage+" to "+destImage);
     FileUtil.copyFile(sourceImage,destImage);
     destImage=replaceBackSlash(confHome+"/images/about.png");
      System.out.println("Copying file from " +sourceImage+" to "+destImage);
     FileUtil.copyFile(sourceImage,destImage);
     
     sourceImage=replaceBackSlash(itemVsValue.get("$NEWFRAMEICON").toString());
     imageName=sourceImage.substring(sourceImage.lastIndexOf(File.separatorChar)+1);
     destImage=replaceBackSlash(confHome+"/images/"+imageName);
      System.out.println("Copying file from " +sourceImage+" to "+destImage);
     FileUtil.copyFile(sourceImage,destImage);
     itemVsValue.put("$NEWFRAMEICON","./images/"+imageName);
     //Copy the frame icon as adventneticon.jpg
     destImage=replaceBackSlash(confHome+"/images/adventneticon.jpg");
      System.out.println("Copying file from " +sourceImage+" to "+destImage);
     FileUtil.copyFile(sourceImage,destImage);
     
     sourceImage=replaceBackSlash(itemVsValue.get("$NEWLOGO").toString());
     imageName=sourceImage.substring(sourceImage.lastIndexOf(File.separatorChar)+1);
     destImage=replaceBackSlash(confHome+"/images/"+imageName);
     System.out.println("Copying file from " +sourceImage+" to "+destImage);
     FileUtil.copyFile(sourceImage,destImage);
     itemVsValue.put("$NEWLOGO","./images/"+imageName);
     destImage=replaceBackSlash(confHome+"/images/logoPane.png");
      System.out.println("Copying file from " +sourceImage+" to "+destImage);
     FileUtil.copyFile(sourceImage,destImage);
     
     
}

private boolean updateConfFiles(String backupName){
     //Create a RebrandingConf document. Which is a template with $VARIABLES in it.
     Document rebrandingConfDoc=XmlUtil.parseAndCreateDocument(replaceBackSlash(confHome+"/conf/RebrandingConf.xml"));
     // Replace the $VARIABLES with actual values
     ReplaceParameters replace=new ReplaceParameters(rebrandingConfDoc,itemVsValue);
     replace.replace();
     RebrandingConf conf=new RebrandingConf(rebrandingConfDoc,replaceBackSlash(webNMSHome),replaceBackSlash(confHome));
     return conf.rebrand(backupName);
}

private boolean updateProperties(String backupName){
      //Create a RebrandingProperties document. Which is a template with $VARIABLES in it.
     Document rebrandingPropsDoc=XmlUtil.parseAndCreateDocument(replaceBackSlash(confHome+"/conf/RebrandingProperties.xml"));
     // Replace the $VARIABLES with actual values
     ReplaceParameters replace=new ReplaceParameters(rebrandingPropsDoc,itemVsValue);
     replace.replace();
     RebrandingProperties props=new RebrandingProperties(rebrandingPropsDoc);
     return props.rebrand(backupName);

}

//Write the user given new values to RebrandingItems.xml so that it can be used in next invocation
private void writeRebrandingItemsXML(){
     //OLDPRODUCT key also updated with the product name given by users.
     itemVsValue.put("$OLDPRODUCT",productName.getText().trim());
     Element root=rebrandingDoc.getDocumentElement();
     NodeList itemList=root.getElementsByTagName("item");
     int length=itemList.getLength();
     for(int i=0;i<length;i++){
          if(itemList.item(i).getNodeType()==Node.ELEMENT_NODE){
               Element item=(Element)itemList.item(i);
               String name=item.getAttribute("name");
               if(itemVsValue.get(name)!=null){
	               item.setAttribute("value",itemVsValue.get(name).toString());
               }
          }
     }

      XmlUtil.writeToXML(rebrandingDoc,replaceBackSlash(confHome+"/conf/RebrandingItems.xml"));
}

public void setTextValues()
  {
      companyName.setText(itemVsValue.get("$COMPANY").toString());
      productName.setText(itemVsValue.get("$PRODUCT").toString());

      version.setText(itemVsValue.get("$VERSION").toString());


      companyURL.setText(itemVsValue.get("$URL").toString());

      motto.setText(itemVsValue.get("$MOTTO").toString());

      copyRight.setText(itemVsValue.get("$COPYRIGHT").toString());
      splashImage.setText(itemVsValue.get("$SPLASH").toString());

      frameIcon.setText(itemVsValue.get("$FRAMEICON").toString());

      productLogo.setText(itemVsValue.get("$LOGO").toString());
    language.setText(itemVsValue.get("$LANGUAGE").toString());
    
       country.setText(itemVsValue.get("$COUNTRY").toString());
       
       aboutDialogText.setText(itemVsValue.get("$ABOUTDIALOG").toString());
         contactaddress.setText(itemVsValue.get("$CONTACTADDRESS").toString());
  }

public static void main(String[] args){
     if(args.length!=2){
          System.out.println("USAGE :RebrandingTool <WebNMS Home> <ConfHome>");
          return;
     }
      RebrandingTool tool=new RebrandingTool(args[0],args[1]);
      // If invoked standalone we need to put System.exit while closing.
      tool.standalone=true;
      tool.setVisible(true);
}

class RebrandingFileFilter extends FileFilter{
   String[] extensions=null;
   String description="Select";
   RebrandingFileFilter(String[] extensions){
        this.extensions=extensions;
        for(int i=0;i<extensions.length;i++){
             description=description+" "+extensions[i];
        }
        description=description+ " files";
   }
   public boolean accept(File f){
        if(f!=null){
        	if(f.isDirectory()){
        	     return true;
        	}
        	String fileName=f.getName();
        	int i=fileName.lastIndexOf(".");
        	 if(i==-1){
        	      return false;
        	 }
        	 fileName=fileName.substring(i+1);
        	 for(int j=0;j<extensions.length;j++){
        	      if(extensions[j].equalsIgnoreCase(fileName)){
        	           return true;
        	      }
        	 }
        
        }
        	 return false;    
   }
  public String getDescription(){
       return description;
  }
  
  
}

}

























































