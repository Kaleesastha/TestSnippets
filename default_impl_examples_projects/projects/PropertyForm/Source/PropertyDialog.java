//$Id: PropertyDialog.java,v 1.2 2008/04/19 07:11:39 johnpaul Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$17
//<End_Version>
package com.adventnet.nms.mapui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.*;

//XML import
import org.w3c.dom.*;
import javax.xml.parsers.*;



//NMS Import
import com.adventnet.nms.util.NmsClientUtil;



public class PropertyDialog extends JDialog  implements ActionListener,com.adventnet.apiutils.ParameterChangeListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel buttonPanel = null;
	javax.swing.JPanel propertyPanel = null;
	javax.swing.JPanel bottomPanel = null;
	javax.swing.JCheckBox saveChanges = null;
	javax.swing.JButton modifyButton = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JButton helpButton = null;
	javax.swing.JLabel JLabel1 = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	ButtonGroup buttonGroup=null;
	private Document doc=null;
	private ActionListener modifyAction=null;
  	private Vector rightPanels=null;

  


  


 

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
        this.setSize(getPreferredSize().width+583,getPreferredSize().height+441); 
          setTitle("ScreenTitle");
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
 constructGUI();
 KeyStroke escStroke = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE,0);
 ((JComponent)getRootPane()).registerKeyboardAction(this,"Cancel", escStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
 String title=doc.getDocumentElement().getAttribute("title");
 setTitle(NmsClientUtil.GetString(title));
  NmsClientUtil.setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
  NmsClientUtil.centerWindow(this);

  } 
  public String getParameter(String input)
  {
           //<Begin_getParameter_String> 
	if (po != null) 
	 { 
	   if(po.getParameter(input) != null) 
	     {
	       return (String)po.getParameter(input); 
	     }
	 }
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
            buttonPanel.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+buttonPanel,ex); 
          }

//<UserCode_Begin_Bean_buttonPanel>

//<UserCode_End_Bean_buttonPanel>

          try
          {
            propertyPanel.setBorder(new javax.swing.border.EmptyBorder(0,0,0,0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+propertyPanel,ex); 
          }

//<UserCode_Begin_Bean_propertyPanel>

//<UserCode_End_Bean_propertyPanel>

          try
          {
            saveChanges.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+saveChanges,ex); 
          }

//<UserCode_Begin_Bean_saveChanges>
String isSaveNeeded=doc.getDocumentElement().getAttribute("isSaveCheckbox");
if(isSaveNeeded.equalsIgnoreCase("false")){
     saveChanges.setVisible(false);
}
else{
     saveChanges.setText(NmsClientUtil.GetString("javaclient.propform.savechanges"));
     saveChanges.setSelected(false);
     saveChanges.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.PLAIN,(NmsClientUtil.getFont()).getSize()));	        
}
//<UserCode_End_Bean_saveChanges>

          try
          {
            modifyButton.setHorizontalTextPosition(4);
            modifyButton.setText("Modify");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+modifyButton,ex); 
          }

//<UserCode_Begin_Bean_modifyButton>
modifyButton.addActionListener(modifyAction);
modifyButton.getRootPane().setDefaultButton(modifyButton);
modifyButton.setText(NmsClientUtil.GetString("javaclient.propform.modifybutton"));
modifyButton.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.PLAIN,(NmsClientUtil.getFont()).getSize()));

//<UserCode_End_Bean_modifyButton>

          try
          {
            closeButton.setHorizontalTextPosition(4);
            closeButton.setText("Close");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+closeButton,ex); 
          }

//<UserCode_Begin_Bean_closeButton>
closeButton.addActionListener(this);
closeButton.setActionCommand("Cancel");
closeButton.setText(NmsClientUtil.GetString("javaclient.propform.closebutton"));
closeButton.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.PLAIN,(NmsClientUtil.getFont()).getSize()));
//<UserCode_End_Bean_closeButton>

          try
          {
            helpButton.setHorizontalTextPosition(4);
            helpButton.setText("Help");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+helpButton,ex); 
          }

//<UserCode_Begin_Bean_helpButton>
helpButton.setActionCommand("help");
helpButton.addActionListener(this);
helpButton.setText(NmsClientUtil.GetString("javaclient.propform.helpbutton"));
helpButton.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.PLAIN,(NmsClientUtil.getFont()).getSize()));
//<UserCode_End_Bean_helpButton>

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
	 if(po == null)
	{
	po = new com.adventnet.apiutils.ParameterObject();
	}
        Top= new javax.swing.JPanel();
        buttonPanel= new javax.swing.JPanel();
        propertyPanel= new javax.swing.JPanel();
        bottomPanel= new javax.swing.JPanel();
        saveChanges= new javax.swing.JCheckBox();
        modifyButton= new javax.swing.JButton();
        closeButton= new javax.swing.JButton();
        helpButton= new javax.swing.JButton();
        JLabel1= new javax.swing.JLabel();
        initializeParameters(); 

  
          //<End_initVariables>
buttonGroup=new ButtonGroup();
rightPanels=new Vector();
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(buttonPanel,BorderLayout.WEST);
buttonPanel.setLayout(new GridBagLayout());
Top.add(propertyPanel,BorderLayout.CENTER);
propertyPanel.setLayout(new CardLayout(5,5));
Top.add(bottomPanel,BorderLayout.SOUTH);
bottomPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
bottomPanel.add(saveChanges,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
bottomPanel.add(modifyButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
bottomPanel.add(closeButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(4,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
bottomPanel.add(helpButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
bottomPanel.add(JLabel1,cons);

  
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





  

   
  public void setProperties(java.util.Properties props)
  {
         //<Begin_setProperties_java.util.Properties> 
	if(po != null)
	{
	po.setParameters(props);
	}
  
         //<End_setProperties_java.util.Properties>
  } 
  private void initializeParameters()
  {
          //<Begin_initializeParameters> 
	 if(po != null) 
	   {
	    po.addParameterChangeListener(this);
	   }

  
          //<End_initializeParameters>
  } 
  public void destroy()
  {
         //<Begin_destroy> 
	if(po != null)
	{
	 po.removeParameterChangeListener(this);
	}
  
         //<End_destroy>
  } 
  public void setParameterObject(com.adventnet.apiutils.ParameterObject paramObj)
  {
         //<Begin_setParameterObject_com.adventnet.apiutils.ParameterObject> 
	this.po=paramObj;

  
         //<End_setParameterObject_com.adventnet.apiutils.ParameterObject>
  } 
  public void parameterChanged(com.adventnet.apiutils.ParameterObject paramObj)
  {
  //<Begin_parameterChanged_com.adventnet.apiutils.ParameterObject> 

  
  //<End_parameterChanged_com.adventnet.apiutils.ParameterObject>
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



private void constructGUI(){
     Element root=doc.getDocumentElement();
     NodeList groupList=root.getElementsByTagName("GROUP");
     int length=groupList.getLength();
     for(int i=0;i<length;i++){
          Element group=(Element)groupList.item(i);
          addLeftButton(group,i);
          addRightPanel(group);
     }
}

private void addLeftButton(Element group,int position){
     String groupName=group.getAttribute("name");
     String normalImage=group.getAttribute("normalImage");
     String mouseOverImage=group.getAttribute("mouseOverImage");

     PreviewButton button=new PreviewButton();
     //button.setForeground(new Color(-1)); --Setting font color to white commented as Text is invisible in XP.
     button.setFont(new Font(NmsClientUtil.getFont().getName(),Font.BOLD,NmsClientUtil.getFont().getSize()));
     button.setVerticalTextPosition(3);
     button.setHorizontalTextPosition(0);
     button.setRolloverEnabled(true);
     button.setMinimumSize(new Dimension(90,90));
     button.setBackground(new Color(-8355712));
     button.setPreferredSize(new Dimension(90,90));
     button.setSelected(false);
     button.setText(NmsClientUtil.GetString(groupName));
     Icon icon=getIcon(normalImage);
     if(icon!=null){

	     button.setIcon(icon);
     }
     icon=getIcon(mouseOverImage);
     if(icon!=null){
	     button.setRolloverIcon(icon);
     }
     if(position==0){
          button.doClick();
	 
     }
     button.addActionListener(this);
     buttonGroup.add(button);
     button.setActionCommand(groupName);
     inset = new Insets(0,0,0,0);
     setConstraints(0,position,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
     buttonPanel.add(button,cons);

     
}

private void addRightPanel(Element group){
     
     PropertyPanel panel=new PropertyPanel(group);
     rightPanels.add(panel);
     propertyPanel.add(panel,group.getAttribute("name"));
}

private Icon getIcon(String image){
    String imagePath=null;
     if(NmsClientUtil.applet!=null){
	     imagePath=NmsClientUtil.applet.getDocumentBase() +    "../"+image;
     }
     if(imagePath==null){
	return null;
     }
     try{
         return NmsClientUtil.getImageIcon(new URL(imagePath));
     }
     catch(Exception e){
          System.err.println(e.getMessage());
          return null;
     }
     
}

public void actionPerformed(ActionEvent ae){
     if(ae.getActionCommand().equals("Cancel")){
          setVisible(false);
     }
     else if(ae.getActionCommand().equals("help")){
           String helpURL =NmsClientUtil.getHelpURL(doc.getDocumentElement().getAttribute("helpURL"));
           NmsClientUtil.showHelp(helpURL);
     }
     else{
               ((CardLayout)propertyPanel.getLayout()).show(propertyPanel,ae.getActionCommand());
     }

}

public PropertyDialog(JFrame parent,ActionListener actionListener,Document doc){

     super(parent,doc.getDocumentElement().getAttribute("title"),false);
     this.doc=doc;
     this.modifyAction=actionListener;

}


  public PropertyDialog()
  {
    //<Begin_PropertyDialog>
    pack();
  
    //<End_PropertyDialog>
  }

  public PropertyDialog(java.applet.Applet applet)
  {
    //<Begin_PropertyDialog_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_PropertyDialog_java.applet.Applet>
  }
  
  void updateXML(){
      //Update the XML with new values	
       for(int i=0;i<rightPanels.size();i++){
            PropertyPanel panel=(PropertyPanel)rightPanels.elementAt(i);
            panel.updateXML();
       } 
       String isSaveNeeded=doc.getDocumentElement().getAttribute("isSaveCheckbox");
       if(isSaveNeeded.equalsIgnoreCase("true")){
                 doc.getDocumentElement().setAttribute("save",saveChanges.isSelected()+"");
       }
       else if(isSaveNeeded.equalsIgnoreCase("false"))
       {
	       doc.getDocumentElement().setAttribute("save","true");
       }
  }

}























