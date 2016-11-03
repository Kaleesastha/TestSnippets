//$Id: AddMoreIPAddress.java,v 1.1.4.1 2012/01/25 05:12:46 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$20
//<End_Version>
package com.adventnet.nms.runtimeconfig;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.*;

import com.adventnet.nms.runtimeconfig.*;
import com.adventnet.nms.util.NmsClientUtil;

public class AddMoreIPAddress extends JDialog 
implements java.awt.event.ActionListener ,java.awt.event.KeyListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources"; //No I18N
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel centerPanel = null;
	javax.swing.JScrollPane scrollPane = null;
	javax.swing.JPanel ipContainer = null;
	javax.swing.JButton delButton = null;
	javax.swing.JTextField ipField = null;
	javax.swing.JPanel buttonPanel = null;
	javax.swing.JButton moreButton = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>



              private int count = 0;
               private NodeDiscoveryScreen nodedcscrn = null;

               private Vector ipfieldArr = new Vector();
               private Vector sameIP = new Vector();


        public AddMoreIPAddress(NodeDiscoveryScreen nodedcscrn,java.applet.Applet applet)
        {
	  this.applet = applet;
          this.nodedcscrn = nodedcscrn;
          init();
          setLocationRelativeTo(nodedcscrn);
          setVisible(true);
        } 
        public AddMoreIPAddress(NodeDiscoveryScreen nodedcscrn,Vector sameIP,java.applet.Applet applet)
        {
	  this.applet = applet;
          this.nodedcscrn = nodedcscrn;
          this.sameIP = sameIP;
          init();
          setLocationRelativeTo(nodedcscrn);
          setVisible(true);
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
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES"); //No I18N
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet); //No I18N
        if (initialized) return; 
        try 
        { 
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); //No I18N
        } 
        catch(Exception e)
        {
           e.printStackTrace();
        }
        this.setSize(getPreferredSize().width+376,getPreferredSize().height+174); 
          setTitle(resourceBundle.getString("Add More IPAddress(es).")); //No I18N
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
          showStatus(resourceBundle.getString("Error in init method"),ex);  //No I18N
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources"; 
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            centerPanel.setBackground(new Color(-1118482));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting propertiesfor bean ")+centerPanel,ex);  //No I18N
          }

//<UserCode_Begin_Bean_centerPanel>

//<UserCode_End_Bean_centerPanel>

          try
          {
            scrollPane.setBackground(new Color(-1381399));
            scrollPane.setHorizontalScrollBarPolicy(31);
            scrollPane.setMaximumSize(new Dimension(385,211));
            scrollPane.setPreferredSize(new Dimension(406,15));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+scrollPane,ex); //No I18N
          }

//<UserCode_Begin_Bean_scrollPane>

//<UserCode_End_Bean_scrollPane>

          try
          {
            ipContainer.setAlignmentX((float)0.0);
            ipContainer.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Add More IP Address(es)."),2,2,new Font("Dialog",0,12),new Color(-13421773)));  //No I18N
            ipContainer.setBackground(new Color(-855825));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ipContainer,ex); //No I18N
          }

//<UserCode_Begin_Bean_ipContainer>

//<UserCode_End_Bean_ipContainer>

          try
          {
            delButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/close.png",applet,true)); //No I18N
            delButton.setMaximumSize(new Dimension(19,19));
            delButton.setMinimumSize(new Dimension(19,19));
            delButton.setPreferredSize(new Dimension(19,19));
            delButton.setToolTipText(resourceBundle.getString("Delete")); //No I18N 
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+delButton,ex);  //No I18N
          }

//<UserCode_Begin_Bean_delButton>

//<UserCode_End_Bean_delButton>

          try
          {
            ipField.setMaximumSize(new Dimension(2147483647,27));
            ipField.setMinimumSize(new Dimension(320,27));
            ipField.setPreferredSize(new Dimension(320,27));
            ipField.setToolTipText(resourceBundle.getString("IPAddress")); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ipField,ex);  //No I18N
          }

//<UserCode_Begin_Bean_ipField>

//<UserCode_End_Bean_ipField>

          try
          {
            buttonPanel.setBackground(new Color(-1380630));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+buttonPanel,ex); //No I18N
          }

//<UserCode_Begin_Bean_buttonPanel>

//<UserCode_End_Bean_buttonPanel>

          try
          {
            moreButton.setText(resourceBundle.getString("More")); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+moreButton,ex);  //No I18N
          }

//<UserCode_Begin_Bean_moreButton>

//<UserCode_End_Bean_moreButton>

          try
          {
            okButton.setText(resourceBundle.getString("OK")); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex); //No I18N
          }

//<UserCode_Begin_Bean_okButton>

//<UserCode_End_Bean_okButton>

          try
          {
            cancelButton.setText(resourceBundle.getString("Cancel")); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex);  //No I18N
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+0,cancelButton.getPreferredSize().height+1));
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+0,okButton.getPreferredSize().height+1));
		moreButton.setPreferredSize(new Dimension(moreButton.getPreferredSize().width+0,moreButton.getPreferredSize().height+1));
		buttonPanel.setPreferredSize(new Dimension(buttonPanel.getPreferredSize().width+190,buttonPanel.getPreferredSize().height+6));
		scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width+5,scrollPane.getPreferredSize().height+81));

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        centerPanel= new javax.swing.JPanel();
        scrollPane= new javax.swing.JScrollPane();
        ipContainer= new javax.swing.JPanel();
        delButton= new javax.swing.JButton();
        ipField= new javax.swing.JTextField();
        buttonPanel= new javax.swing.JPanel();
        moreButton= new javax.swing.JButton();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();

  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(null);
centerPanel.setBounds(0,0,370,165);
Top.add(centerPanel);
centerPanel.setLayout(new BorderLayout(5,5));
centerPanel.add(scrollPane,BorderLayout.NORTH);
scrollPane.getViewport().add(ipContainer);
ipContainer.setLayout(new GridBagLayout());
inset = new Insets(0,0,10,10);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
ipContainer.add(delButton,cons);
inset = new Insets(0,0,10,10);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
ipContainer.add(ipField,cons);
centerPanel.add(buttonPanel,BorderLayout.WEST);
buttonPanel.setLayout(new FlowLayout(1,5,5));
buttonPanel.add(moreButton);
buttonPanel.add(okButton);
buttonPanel.add(cancelButton);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
   moreButton.addActionListener(this);
   okButton.addActionListener(this);
   okButton.setActionCommand(resourceBundle.getString("OK")); //No I18N
   cancelButton.addActionListener(this);
   cancelButton.setActionCommand(resourceBundle.getString("Cancel")); //No I18N
   delButton.setActionCommand(resourceBundle.getString("Remove")); //No I18N
   delButton.addActionListener(this);
   delButton.setName(Integer.toString(count));
   delButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
   ipField.setName(Integer.toString(count));
    addKeyListnerField(ipField);
    updateProbDialog();
   ipfieldArr.addElement(ipField);
   ipContainer.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("javaclient.runtime.addmoreip.addipadd.value"),2,2,new Font("Dialog",0,12),new Color(-13421773))); //No I18N
  setTitle(resourceBundle.getString("javaclient.runtime.addmoreip.addipadd.value"));
  ipField.setToolTipText(resourceBundle.getString("javaclient.runtime.addmoreip.ipaddress.value"));

  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 



  
  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message); //No I18N
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message); //No I18N
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }





  

   
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
  
  public void actionPerformed( java.awt.event.ActionEvent evt)
 {
      if(evt.getActionCommand().equals("More")) 
      {
             JButton delButton1 = new JButton();
             delButton1.setIcon(com.adventnet.apiutils.Utility.findImage("./images/close.png",applet,true)); //No I18N
	     delButton1.setMinimumSize(new Dimension(19,19));
	     delButton1.setMaximumSize(new Dimension(19,19));
	     delButton1.setPreferredSize(new Dimension(19,19));
	     delButton1.setName(Integer.toString(++count));
	     delButton1.addActionListener(this);
	     delButton1.setActionCommand(resourceBundle.getString("Remove")); //No I18N
	     delButton1.setToolTipText(resourceBundle.getString("Delete"));  //No I18N
	     delButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
	            
	     JTextField ipField1 = new JTextField();
	     ipField1.setMaximumSize(new Dimension(214748,27));
	     ipField1.setMinimumSize(new Dimension(320,27));
	     ipField1.setPreferredSize(new Dimension(320,27));
	     ipField1.setToolTipText(resourceBundle.getString("javaclient.runtime.addmoreip.ipaddress.value"));
	     ipfieldArr.addElement(ipField1);
	     addKeyListnerField(ipField1);
	     //scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(),scrollPane.getHeight()+60)); 
	     ipField1.setName(Integer.toString(count));
	       
	        if(count < 5)
	        {	          
	          	scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(),scrollPane.getHeight()+45));
	          	centerPanel.setSize(new Dimension(centerPanel.getWidth(),centerPanel.getHeight()+45));
	         	setSize(getWidth(),getHeight()+45);
	        }
	  int row = cons.gridy+1	;
	  inset = new Insets(10,0,10,10);
	  setConstraints(0,row,1,1,1.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	  ipContainer.add(delButton1,cons);
	  inset = new Insets(0,0,0,10);
	  setConstraints(1,row,0,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	  ipContainer.add(ipField1,cons); 
       }
       else if(evt.getActionCommand().equals("Remove"))
       {
    	    JButton selctbut = (JButton)evt.getSource();
    	    String butname = selctbut.getName();
    	  	Component comparray[] = ipContainer.getComponents();
    	  	for(int i = 0 ; i < comparray.length; i++)
    	  	{
    	  			if(comparray[i] instanceof JButton)
    	  			{
    	  					if(((JButton)comparray[i]).getName() != null)
    	  					{
    	  						if(((JButton)comparray[i]).getName().equals(butname))
    	  						{
    	  							ipContainer.remove(comparray[i]);
    	  						}
    	  					}
    	  			}
    	  			else if(comparray[i] instanceof JTextField)
    	  			{
    	  				if(((JTextField)comparray[i]).getName() != null)
    	  				{
    	  					if(((JTextField)comparray[i]).getName().equals(butname))
    	  					{
    	  						ipContainer.remove(comparray[i]);
    	  						ipfieldArr.remove(comparray[i]);
    	  						break;
    	  					}
    	  				}
    	  			}
    	  	}
    	  	--count;
    	  	if(count < 5 && count != -1)
    	  	{

            	scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(),scrollPane.getHeight()-30));
            	centerPanel.setSize(new Dimension(centerPanel.getWidth(),centerPanel.getHeight()-30));
            	setSize(getWidth(),getHeight()-30);
    	  	}
        	           if(count == -1)
	  	{
			nodedcscrn.comboIPAddr.removeAllItems();
	  		this.dispose();
	  	}
      }
      else if (evt.getActionCommand().equals("OK"))
      {
           
           nodedcscrn.comboIPAddr.removeAllItems();
    	  
    	  for(int i =0; i < ipfieldArr.size(); ++i)
    	  {
    		  String fieldname = ((JTextField)ipfieldArr.elementAt(i)).getText().trim();
    		  if((fieldname == null) || fieldname.equals(""))
    		  {
    			  continue;
    		  }
    		  	nodedcscrn.comboIPAddr.addItem(fieldname);
    	  }
    	  setVisible(false);
   }
      else if (evt.getActionCommand().equals("Cancel"))
      {
	  nodedcscrn.comboIPAddr.removeAllItems();
    	  this.dispose();
      }
      validate();
      invalidate();
 }
 private void updateProbDialog()
  {
	  int itemscount = nodedcscrn.comboIPAddr.getItemCount();
      
      if(itemscount != 0)
      {
      	 for(int i = 0 ; i < itemscount ; i++)
      	 { 
      		 String itemName = (String) nodedcscrn.comboIPAddr.getItemAt(i);
      		 
      		 if(i == 0)
      		 {
      			ipField.setText(itemName);
      			ipField.setToolTipText(resourceBundle.getString("javaclient.runtime.addmoreip.duplicate.value"));
      			if(sameIP.size() != 0)
      			{
      				if(checkIPSameVec(itemName))
      				{
      					ipField.setBackground(new Color(-3289909));
      				}
      			}
      			
      		 }
      		 else
      		 {
      		 JButton delButton1 = new JButton();
             delButton1.setIcon(com.adventnet.apiutils.Utility.findImage("./images/close.png",applet,true)); //No I18N
     	     delButton1.setMinimumSize(new Dimension(19,19));
     	     delButton1.setMaximumSize(new Dimension(19,19));
     	     delButton1.setPreferredSize(new Dimension(19,19));
     	     delButton1.setName(Integer.toString(++count));
     	     delButton1.addActionListener(this);
     	     delButton1.setActionCommand(resourceBundle.getString("Remove")); //No I18N 
     	     delButton1.setToolTipText(resourceBundle.getString("Delete")); //No I18N
     	     delButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
     	            
     	     JTextField ipField1 = new JTextField();
     	     ipField1.setMaximumSize(new Dimension(214748,27));
     	     ipField1.setMinimumSize(new Dimension(320,27));
     	     ipField1.setPreferredSize(new Dimension(320,27));
     	     ipField1.setText(itemName);
     	     	if(sameIP.size() != 0)
     	     	{
     	     		if(checkIPSameVec(itemName))
     	     		{
     	     			ipField1.setText(itemName);
     	     			ipField1.setBackground(new Color(-3289909));
     	     			ipField1.setToolTipText(resourceBundle.getString("javaclient.runtime.addmoreip.duplicate.value"));
     	     		}
     	     	}
     	     ipfieldArr.addElement(ipField1);
     	     addKeyListnerField(ipField1);
     	     ipField1.setName(Integer.toString(count));
     	       
    	        if(count < 5)
     	        {	 
     	          	scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(),scrollPane.getHeight()+120));
     	          	scrollPane.setSize(new Dimension(scrollPane.getWidth(),scrollPane.getHeight()+50));
     	          	
     	          	centerPanel.setSize(new Dimension(centerPanel.getWidth(),centerPanel.getHeight()+45));
     	         	setSize(getWidth(),getHeight()+45);
     	        }
	     	  int row = cons.gridy+1;
	     	  inset = new Insets(10,0,10,10);
	     	  setConstraints(0,row,1,1,1.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	     	  ipContainer.add(delButton1,cons);
	     	  inset = new Insets(0,0,0,10);
	     	  setConstraints(1,row,0,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	     	  ipContainer.add(ipField1,cons); 
      		 }
      		  validate();
      	      invalidate();
      	 }
      	 
       }
  }
  private boolean checkIPSameVec(String ipName)
  {
	  for(int i=0; i < sameIP.size();i++)
	  {
		  	if(sameIP.elementAt(i).equals(ipName))
		  	{
		  			return true;
		  	}
	  }
	  return false;
  }
  private void addKeyListnerField(JTextField jTextField) {

         jTextField.addKeyListener(new KeyAdapter()
         {
				public void keyTyped(KeyEvent ke) 
				{ 
             	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || (ke.getKeyChar()== ke.VK_BACK_SPACE) || (ke.getKeyChar() == '.'))
					{ 
             		//DO Nothing 
             	} 
             	else
					{ 
                 	ke.consume(); 
                 	Toolkit.getDefaultToolkit().beep(); 
             	} 
				}
            });
		
}
        public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
 	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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



  
  public AddMoreIPAddress()
  {
    //<Begin_AddMoreIPAddress>
    pack();
  
    //<End_AddMoreIPAddress>
  }

  public AddMoreIPAddress(java.applet.Applet applet)
  {
    //<Begin_AddMoreIPAddress_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_AddMoreIPAddress_java.applet.Applet>
  }
}







































