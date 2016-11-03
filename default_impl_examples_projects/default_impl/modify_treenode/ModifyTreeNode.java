//$Id: ModifyTreeNode.java,v 1.5 2010/10/29 13:45:53 swaminathap Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory



package com.adventnet.nms.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.TitledBorder;

import java.util.*;
import java.net.URL;
import javax.swing.tree.TreePath;

import com.adventnet.nms.util.*;
import com.adventnet.nms.tree.XMLTreeModel;
import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.mapui.MapApplet;




public class ModifyTreeNode implements NmsFrame,ActionListener,KeyListener
{
 	//<Begin_Variable_Declarations>
    private boolean initialized = false;
    private javax.swing.JApplet applet = null;
    private static JFrame treeNodeFrame = null;
    private static byte typeOfOperation ;
    public  Vector clients=null;
    
    javax.swing.JButton applyButton = null;
    javax.swing.JButton  closeButton = null;
    javax.swing.JButton backButton = null;
    javax.swing.JButton  nextButton = null;

    
    javax.swing.JButton  helpButton = null;
    
    javax.swing.JPanel  Top = null;
    javax.swing.JPanel  okCloseHelpPanel = null;
    javax.swing.JPanel  centerPanel = null;
    javax.swing.JPanel mainPanel = null;
    javax.swing.JPanel  defaultPropPanel = null;
    javax.swing.JPanel  defaultCenterPanel = null;
    javax.swing.JPanel  labelPanel = null;
    javax.swing.JPanel  textFieldPanel = null;
    javax.swing.JPanel  htmlClientPanel = null;
    javax.swing.JPanel  defaultTopPanel = null;
    javax.swing.JPanel  defaultRightPanel = null;
    javax.swing.JPanel  defaultLeftPanel = null;
    javax.swing.JPanel  defaultBottomPanel = null;
    javax.swing.JPanel  userPropPanel = null;
    javax.swing.JPanel  userMainPanel = null;
    javax.swing.JPanel  userLeftPanel2 = null;
    javax.swing.JPanel  userTopPanel = null;
    javax.swing.JPanel  userRightPanel2 = null;
    javax.swing.JPanel  userBottomPanel = null;
    javax.swing.JPanel  userCenterPanel = null;
    javax.swing.JPanel  leftAdjustPanel = null;
    javax.swing.JPanel  rightAdjustPanel = null;
    javax.swing.JPanel  topAdjustPanel = null;
    javax.swing.JPanel backNextPanel = null;
    javax.swing.JPanel displayPanel = null;
    javax.swing.JPanel nodePanel = null;
    javax.swing.JPanel panelPanel = null;
    javax.swing.JPanel framePanel = null;
    javax.swing.JPanel labelPanel1 = null;
    javax.swing.JPanel labelPanel2 = null;
    javax.swing.JPanel labelPanel3 = null;
    javax.swing.JPanel textFieldPanel1 = null;
    javax.swing.JPanel textFieldPanel2 = null;
    javax.swing.JPanel textFieldPanel3 = null;
    javax.swing.JPanel treeComboLabel = null;
    javax.swing.JPanel treePanel = null;
    javax.swing.JTextField nodeIndexTextField = null;
    

    
    
    
    javax.swing.JLabel tips = null;
    javax.swing.JLabel labelArray[] = null;
    
    javax.swing.JTextField textFieldArray[] = null;

    
    javax.swing.JComboBox htmlComboBox = null;
    
    javax.swing.JScrollPane scrollPane = null;
    
    CardLayout card = new CardLayout();
    //<End_Variable_Declarations>
    
    TreeCombo comp= null;
    JTree tree = null;
    PropertyPanel propPanel = null;
    JComboBox actionComboBox = null;
    
    private String id = null;
    private boolean firstTime = true;
    private Random genId = null;
    
    private int componentValue[] = { 0,1,1,1,2,1,0,1,1,1};

    private String defaultProps[] = { "NONE","TREE-NAME","PANEL-NAME","MENU-FILE-NAME","NONE","INIT_ON_STARTUP","NONE","ICON-FILE","TREE-POPUP-MENU","FRAME-TITLE"};
    private String propsToSkip[] = { "ID","URL","TARGET","Client","CENTER","TABLE-COLUMNS","VIEW-CRITERIA","VIEW-LENGTH","PROTOCOLS","DEVICE-REF","MODULE-NAME","NODE-INDEX","PARENT","NODE-TYPE","parent","panelkey","SHOW-TREENODE-PROPERTIES","INDEX","className","DEFAULT-CLOSE-OPERATION","panelName","tobeselected","PANEL-KEY"};

    private int actionType;
    JTree tree1 = null;
    TreeCombo comp1 = null;
    
    boolean operationDone = true;
    
    Font thisFont = null;
    

    
    
    public ModifyTreeNode()
    {
        //<Begin_ModifyTreeNode>
        //pack();
        //treeNodeFrame.setTitle(NmsClientUtil.GetString("ModifyTreeNode"));
        
        //<End_ModifyTreeNode>
    }
    
    public void init(javax.swing.JApplet applet)
    {
        //<Begin_ModifyTreeNode_java.applet.Applet>
        this.applet = applet;
        //pack();
        //this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        String temp = applet.getParameter("Operation").trim();

        if (temp.equals("Add"))
        {
            actionType =1;
        }
        else if (temp.equals("Modify"))
        {
            actionType = 0;
        }
        else if (temp.equals("Delete"))
        {
            actionType = 2;
        }
        else if (temp.equals("Move"))
        {
            actionType = 3;
        }
        //<End_ModifyTreeNode_java.applet.Applet>
        
    }
    
    public void setVisible(boolean bl)
    {
        //<Begin_setVisible_boolean>
                    
       	if(bl)
       	{
            init();
            start();
            treeNodeFrame.setVisible(bl);
        }
        else
        {
            if ( treeNodeFrame != null )
            {
		deRegisterFromAllComponents();
                treeNodeFrame.setVisible(bl);
		if( comp != null )
		    {
			comp.setVisible(bl);
		    }
		
		if ( comp1 != null )
		    {
			comp1.setVisible(bl);
		    }
		stop();
                treeNodeFrame = null;
            }
        }
        
        //<End_setVisible_boolean>
    }
    

     
    public void setUpProperties()throws Exception
    { 
        
        
        String labelNames[] = { NmsClientUtil.GetString("Select the Node to Modify"),NmsClientUtil.GetString("Name"),NmsClientUtil.GetString("Class Name"),NmsClientUtil.GetString("Menu File Name"),NmsClientUtil.GetString("Include Tree Node in"),NmsClientUtil.GetString("Initialize the Panel On Startup ?"),NmsClientUtil.GetString("Default Close operation"),NmsClientUtil.GetString("Icon File Name"),NmsClientUtil.GetString("Popup Menu File Name"),NmsClientUtil.GetString("Title")};
        
        if(actionType == 1)
        {
            labelNames[1] +=NmsClientUtil.GetString(" (*)");
            labelNames[2] +=NmsClientUtil.GetString(" (*)");
        }
            
        
        int labelWidth[] = { 94,94,106,144,120,147,70,146,40,94,147,147,120};
        
        if(actionType == 1)
        {
            labelNames[0] = NmsClientUtil.GetString("Parent Node");
        }
        
        boolean visibleFlag[][] = {{ true,true,false,true,true,false,true,true,true,true},
                                   { true,true,true,true,true,true,true,true,true,true},
                                   { true,true,false,true,true,true,true,true,true,true},
                                   { true,true,true,true,true,true,true,true,true,true}};
        
        //<Begin_setUpProperties>
        
        try
        {
            okCloseHelpPanel.setPreferredSize(new Dimension(284,31));
        }
        catch(Exception ex)
        {
            showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+okCloseHelpPanel,ex); 
        }
        
        try
        {
            backButton.setActionCommand("back");//No Internationalisation
            backButton.setLabel(NmsClientUtil.GetString("<<Back"));
        }
        catch(Exception ex)
        {
            showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+backButton,ex); 
        }
        
        try
        {
            nextButton.setActionCommand("next");
            nextButton.setLabel(NmsClientUtil.GetString("Next>>"));
        }
        catch(Exception ex)
        {
            showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+nextButton,ex); 
        }
        
        try
        {
            closeButton.setPreferredSize(new Dimension(80,25));
            closeButton.setActionCommand("close");//No Internationalisation
            closeButton.setLabel(NmsClientUtil.GetString("Close"));
        }
        catch(Exception ex)
        {
            showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+closeButton,ex); 
        }
        
        try
        {
            applyButton.setPreferredSize(new Dimension(80,25));
            applyButton.setActionCommand("apply");//No Internationalisation
            applyButton.setLabel(NmsClientUtil.GetString("Apply"));
        }
        catch(Exception ex)
        {
            showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+applyButton,ex); 
        }
        
        try
        {
            helpButton.setPreferredSize(new Dimension(80,25));
            helpButton.setActionCommand("help");//No Internationalisation
            helpButton.setLabel(NmsClientUtil.GetString("Help"));
        }
        catch(Exception ex)
        {
            showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+helpButton,ex); 
        }
        
        
        try
        {
            centerPanel.setBorder(new javax.swing.border.BevelBorder(0));
            if(actionType == 2)
            {
                centerPanel.setPreferredSize(new Dimension(421,100));
            }
            else if (actionType ==3)
            {
                centerPanel.setPreferredSize(new Dimension(421,350));
            }
            else 
            {
                centerPanel.setPreferredSize(new Dimension(421,1040));
            }
        }
        catch(Exception ex)
        {
            showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+centerPanel,ex); 
        }
        
        try
        {
            defaultPropPanel.setName(NmsClientUtil.GetString("Default Properties"));
            defaultPropPanel.setAlignmentX((float)1.0);
            defaultPropPanel.setAlignmentY((float)1.0);
            defaultPropPanel.setBorder(new TitledBorder(NmsClientUtil.GetString("Default Properties")));
        }
        catch(Exception ex)
        {
            showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+defaultPropPanel,ex); 
        }
        
        try
        {
            defaultCenterPanel.setPreferredSize(new Dimension(383,300));
            
        }
        catch(Exception ex)
        {
            showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+defaultCenterPanel,ex); 
        }
        
        for(int i=0;i<labelNames.length;i++)
        {
            labelArray[i].setText("         "+NmsClientUtil.GetString(labelNames[i]));//No Internationalisation
            labelArray[i].setPreferredSize(new Dimension(labelWidth[i],10));
        }
        
        
        int j=0;
        
        for(int i=0;i<componentValue.length;i++)
        {
            if(componentValue[i] == 1)
            {
                textFieldArray[j].setPreferredSize(new Dimension(4,10));
                textFieldArray[j].setEnabled(visibleFlag[actionType][i]);
                j++;
            }
            else if(componentValue[i] == 2)
            {

                Vector supportedClients=getClientsSupported();
                            
                  if(supportedClients.contains("all"))
                  {
                  htmlComboBox.addItem(NmsClientUtil.GetString("both Java UI and HTML UI"));
                  }
                  if(supportedClients.contains("all")||( supportedClients.contains("javaonly")))
                  {
                      htmlComboBox.addItem(NmsClientUtil.GetString("Java UI only"));

                  }

                  if(supportedClients.contains("all")||( supportedClients.contains("htmlonly")))
                  {

                  htmlComboBox.addItem(NmsClientUtil.GetString("HTML UI only"));
                  }

                htmlComboBox.setActionCommand("htmlcombobox");//No Internationalisation
            }
        }
            
        try
        {
            defaultTopPanel.setPreferredSize(new Dimension(284,50));
        }
        catch(Exception ex)
        {
            showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+defaultTopPanel,ex); 
        }
        
        try
        {
            defaultRightPanel.setPreferredSize(new Dimension(2,10));
        }
        catch(Exception ex)
        {
            showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+defaultRightPanel,ex); 
        }

        try
          {
            defaultLeftPanel.setPreferredSize(new Dimension(2,10));
          }
          catch(Exception ex)
          {
              showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+defaultLeftPanel,ex); 
          }
          try
          {
            defaultBottomPanel.setPreferredSize(new Dimension(10,2));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+defaultBottomPanel,ex); 
          }

          try
          {
              userMainPanel.setBorder( new TitledBorder(NmsClientUtil.GetString("User Defined Properties")));

          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+userMainPanel,ex); 
          }

          try
          {
            userLeftPanel2.setPreferredSize(new Dimension(2,10));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+userLeftPanel2,ex); 
          }

          try
          {
            userTopPanel.setPreferredSize(new Dimension(284,60));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+userTopPanel,ex); 
          }

          try
          {
            userRightPanel2.setPreferredSize(new Dimension(2,10));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+userRightPanel2,ex); 
          }

          try
          {
            userBottomPanel.setPreferredSize(new Dimension(10,2));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+userBottomPanel,ex); 
          }
          try
          {
            leftAdjustPanel.setPreferredSize(new Dimension(3,10));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+leftAdjustPanel,ex); 
          }

          try
          {
            rightAdjustPanel.setPreferredSize(new Dimension(3,10));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+rightAdjustPanel,ex); 
          }

          try
          {
            topAdjustPanel.setPreferredSize(new Dimension(10,3));
            
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+topAdjustPanel,ex); 
          }
		userMainPanel.setPreferredSize(new Dimension(userMainPanel.getPreferredSize().width+10,userMainPanel.getPreferredSize().height+10));
		defaultCenterPanel.setPreferredSize(new Dimension(defaultCenterPanel.getPreferredSize().width+10,defaultCenterPanel.getPreferredSize().height+10));
		centerPanel.setPreferredSize(new Dimension(centerPanel.getPreferredSize().width+10,centerPanel.getPreferredSize().height+10));
		okCloseHelpPanel.setPreferredSize(new Dimension(okCloseHelpPanel.getPreferredSize().width+0,okCloseHelpPanel.getPreferredSize().height+12));

  //< End_setUpProperties>
                
                if ((actionType == 2)||(actionType == 3))
                {
                    //comp.setEnabled(false);
                    propPanel.setEnabled(false);
                    actionComboBox.setEnabled(false);
                    
                }
                switch(actionType)
                {
                case 0: 
                    {
                        treeNodeFrame.setTitle(NmsClientUtil.GetString("Modify Tree Node")); 
                    }
                    break;
                case 1:  
                    {
                        treeNodeFrame.setTitle(NmsClientUtil.GetString("Add Tree Node"));
                    }
                    break;
                case 2: 
                    {
                        treeNodeFrame.setTitle(NmsClientUtil.GetString("Delete Tree Node"));
                    }
                    break;
                case 3: 
                    {
                        treeNodeFrame.setTitle(NmsClientUtil.GetString("Move Tree Node"));
                    }
                    break;
                }

                Image img;
                if((img=NmsClientUtil.getFrameIcon())!=null)
                {
                    treeNodeFrame.setIconImage(img);
                }
                else
                {
                    treeNodeFrame.setIconImage(NmsClientUtil.getImage(applet.getDocumentBase()+"../images/logo.png"));
                } 
  }    

  public void init()
  { 

  //<Begin_init>
      
      if(treeNodeFrame == null)
      {
          typeOfOperation = (byte)actionType;
          treeNodeFrame = new JFrame();
          
          if (initialized == true) return; 
          //treeNodeFrame.setSize(getPreferredSize().width+600,getPreferredSize().height+658); 
          if((actionType == 1) || (actionType ==0))
          {
              treeNodeFrame.setBounds(210,50,650,620);
          }
          else if (actionType == 2)
          {
              treeNodeFrame.setBounds(210,250,500,280);
          }
          else if (actionType == 3)
          {
              treeNodeFrame.setBounds(210,250,600,300);
          }
          
          Container container = treeNodeFrame.getContentPane();
          container.setLayout(new BorderLayout()); 

          try 
          { 
              initVariables(); 
              setUpGUI(container); 
              setUpProperties(); 
              setUpConnections(); 

              setTheFont(treeNodeFrame);
              
              initValues();
         
              
        
          } 
          catch(Exception ex) 
          { 
              showStatus(NmsClientUtil.GetString("Error in init method"),ex); 
          } 
          // let us set the initialzed variable to true so  
          // we dont initialize again even if init is called 
          initialized = true; 
          setUpMenus();
          setUpToolBar();
      }
      else
      {

          if( treeNodeFrame.getState() == Frame.ICONIFIED)
          {
              treeNodeFrame.setState(Frame.NORMAL);
          }
          String errorMessage[] = { NmsClientUtil.GetString("Modify"),NmsClientUtil.GetString("Add"),NmsClientUtil.GetString("Delete"),NmsClientUtil.GetString("Move")};


          if( actionType != typeOfOperation)
          {
              showErrorMessage(errorMessage[typeOfOperation] + NmsClientUtil.GetString(" node operation is in process. Please finish it "));
          }
          treeNodeFrame.requestFocus();
          return;
      }


  //<End_init>
  } 
 
  public void setUpConnections()throws Exception
  {    
      closeButton.addActionListener(this);
      applyButton.addActionListener(this);
      backButton.addActionListener(this);
      nextButton.addActionListener(this);
      
      helpButton.addActionListener(this);
      comp.addActionListener(this);
      actionComboBox.addActionListener(this);
      htmlComboBox.addActionListener(this);
      
      //propPanel.JButton1.setActionCommand("add");
      //propPanel.JButton2.setActionCommand("delete");
      //propPanel.JButton3.setActionCommand("modify");

      propPanel.keyText.addKeyListener(this);
      propPanel.valueText.addKeyListener(this);
      propPanel.JTable1.addMouseListener(new MouseAdapter()
          {
              public void mouseClicked(MouseEvent me)
              {
                  activateApplyButton();
              }
          });

      treeNodeFrame.addWindowListener(new WindowAdapter()
              {
                  public void windowClosing(WindowEvent we)
                  {
                      setVisible(false);
                  }
                  
          });
      
          


      for(int i=0;i<textFieldArray.length;i++)
      {
          textFieldArray[i].addKeyListener(this);
      }
  } 
    
    private void deRegisterFromAllComponents()
    {
	closeButton.removeActionListener(this);
	applyButton.removeActionListener(this);
	backButton.removeActionListener(this);
	nextButton.removeActionListener(this);
	
	helpButton.removeActionListener(this);
	if( comp != null )
	    {
		comp.removeActionListener(this);
	    }
	if( comp1 != null )
	    {
		comp1.removeActionListener(this);
	    }
	actionComboBox.removeActionListener(this);
	htmlComboBox.removeActionListener(this);
	
	propPanel.keyText.removeKeyListener(this);
	propPanel.valueText.removeKeyListener(this);
	for(int i=0;i<textFieldArray.length;i++)
	    {
		textFieldArray[i].removeKeyListener(this);
	    }
	if( nodeIndexTextField != null )
	    {
		nodeIndexTextField.removeKeyListener(this);
	    }
    }
    
    private void releaseMemory(Container c)
    {
        if ( c != null )
        {
            Component[] comps = c.getComponents();
            if ( comps != null )
            {
                for(int i =0;i<comps.length;i++)
                {
                    releaseMemory((Container)comps[i]);
                    comps[i] = null;
                }
            }

	    if( c instanceof java.awt.Window)
		{
		    ((java.awt.Window)c).dispose();
		}
        }
    }


    private void setTheFont(Container c)
    {
        Component[] comps = c.getComponents();
        for(int i =0;i<comps.length;i++)
        {

            setTheFont((Container)comps[i]);
            comps[i].setFont(thisFont);
        }
    }
    
    public void keyTyped(KeyEvent ke)
    {
        activateApplyButton();

    }
    public void keyPressed(KeyEvent ke)
    {

    }

    public void keyReleased(KeyEvent ke)
    {
    }


  public void actionPerformed(ActionEvent ae) 
  { 
      String command = ae.getActionCommand().trim();

      if (command.equals("apply")) 
      {
          if(applyButton.isEnabled())
          {
              NmsClientUtil.busyCursor(treeNodeFrame); 
              operationDone = true;
              getValues();
              NmsClientUtil.normalCursor(treeNodeFrame);
              
              if(operationDone)
              {
                  applyButton.setEnabled(false);
                  comp.revalidate();
                  comp.repaint();
                  comp.requestFocus();
              }
              
              if((actionType == 0)||(actionType ==1))
              {
                  card.show(mainPanel,"defaultProp");
                  nextButton.setEnabled(true);
                  backButton.setEnabled(false);
              }
          }
      }
      else if (command.equals("close"))
      {
          setVisible(false);
          
      }
      else if (command.equals("help"))
      {
          NmsClientUtil.busyCursor(treeNodeFrame);

          if(actionType == 0)
              NmsClientUtil.showHelpBasedOnKey("Tree_Operations_Modify_Node");
          else if(actionType == 1)
              NmsClientUtil.showHelpBasedOnKey("Tree_Operations");
          else if(actionType == 2)
              NmsClientUtil.showHelpBasedOnKey("Tree_Operations_Delete_Node");
          else if(actionType == 3)
              NmsClientUtil.showHelpBasedOnKey("Tree_Operations_Move_Node");
          
          NmsClientUtil.normalCursor(treeNodeFrame);
      }
      
        
      else if (command.equals("back"))
      {
          nextButton.setEnabled(true);
          backButton.setEnabled(false);
          if(actionType == 0)
          {
              tips.setText(NmsClientUtil.GetString("Click next to modify user defined properties"));
          }
          else if(actionType == 1)
          {
              tips.setText(NmsClientUtil.GetString("Click next to add user defined properties"));
          }
          card.previous(mainPanel);
          textFieldArray[0].requestFocus();
          
      }
      else if (command.equals("next"))//No Internationalisation
      {
          nextButton.setEnabled(false);
          backButton.setEnabled(true);
          if(actionType == 0)
          {
              tips.setText(NmsClientUtil.GetString("Click back to modify default properties"));
          }
          else if(actionType == 1)
          {
              tips.setText(NmsClientUtil.GetString("Click back to add default properties"));
          }
          card.next(mainPanel);
          propPanel.keyText.requestFocus();
            
          
      }
      else if(command.equals("combobox"))//No Internationalisation
      {

          Hashtable props = comp.getSelectedParentProps();
          activateApplyButton();
          setValues(props);
          comp.requestFocus();
          
          textFieldArray[0].requestFocus();
      }
      else if ((command.equals("actioncombobox"))||(command.equals("combobox1"))||(command.equals("htmlcombobox")))//No Internationalisation
      {
          activateApplyButton();
      }
    }
    
    private  void activateApplyButton()
    {
        if(!applyButton.isEnabled())
        {
            applyButton.setEnabled(true);
        }
        
    }

            
        
  public void initVariables()throws Exception
  { 
      
      String defaultPropNames[] = { "id","treeName","panelName","menuFileName","htmlClient","init","action","iconFile","treePopupMenu","frameTitle"};

  //<Begin_initVariables>
      Top= new javax.swing.JPanel();
      okCloseHelpPanel= new javax.swing.JPanel();
      centerPanel= new javax.swing.JPanel();
      mainPanel = new javax.swing.JPanel();
      defaultPropPanel= new javax.swing.JPanel();
      defaultCenterPanel= new javax.swing.JPanel();
      labelPanel= new javax.swing.JPanel();
      textFieldPanel= new javax.swing.JPanel();
      htmlClientPanel= new javax.swing.JPanel();
      defaultTopPanel= new javax.swing.JPanel();
      defaultRightPanel= new javax.swing.JPanel();
      defaultLeftPanel= new javax.swing.JPanel();
      defaultBottomPanel= new javax.swing.JPanel();
      userPropPanel= new javax.swing.JPanel();
      userMainPanel= new javax.swing.JPanel();
      userLeftPanel2= new javax.swing.JPanel();
      userTopPanel= new javax.swing.JPanel();
      userRightPanel2= new javax.swing.JPanel();
      userBottomPanel= new javax.swing.JPanel();
      userCenterPanel= new javax.swing.JPanel();
      leftAdjustPanel= new javax.swing.JPanel();
      rightAdjustPanel= new javax.swing.JPanel();
      topAdjustPanel= new javax.swing.JPanel();
      backNextPanel = new javax.swing.JPanel();
      displayPanel = new javax.swing.JPanel();
      nodePanel = new javax.swing.JPanel();
      panelPanel = new javax.swing.JPanel();
      framePanel = new javax.swing.JPanel();
      labelPanel1 = new javax.swing.JPanel();
      labelPanel2 = new javax.swing.JPanel();
      labelPanel3 = new javax.swing.JPanel();
      textFieldPanel1 = new javax.swing.JPanel();
      textFieldPanel2 = new javax.swing.JPanel();
      textFieldPanel3 = new javax.swing.JPanel();
      treeComboLabel = new javax.swing.JPanel();
      treePanel = new javax.swing.JPanel();
      tips = new javax.swing.JLabel();

      
      
      
      htmlComboBox = new javax.swing.JComboBox();
      
      actionComboBox = new javax.swing.JComboBox();
      
      thisFont =  NmsClientUtil.getFont("DIALOG");//No Internationalisation
      
  
      
      closeButton= new javax.swing.JButton();
      backButton= new javax.swing.JButton();
      nextButton= new javax.swing.JButton();
      
      

      applyButton = new javax.swing.JButton();
      helpButton= new javax.swing.JButton();

      
      labelArray = new javax.swing.JLabel[defaultPropNames.length]; 
     
      
      for(int i=0;i<defaultPropNames.length;i++)
      {
          labelArray[i] = new javax.swing.JLabel();
          labelArray[i].setName(NmsClientUtil.GetString(defaultPropNames[i] + "Label"));//No Internationalisation
      }

      int count=0;
      for(int i=0;i<componentValue.length;i++)
      {
          if (componentValue[i] == 1)
          {
              count++;
          }
      }
          
      textFieldArray = new javax.swing.JTextField[count];
      
      int j=0;
      for(int i=0;i<defaultPropNames.length;i++)
      {
          if(componentValue[i] == 1)
          {
              if(j == 0)
                  textFieldArray[j] = new ASCIITextField();
              else
                  textFieldArray[j] = new javax.swing.JTextField();
              textFieldArray[j].setName(NmsClientUtil.GetString(defaultPropNames[i] + "TextField"));//No Internationalisation
              j++;
          }
      }

  //<End_initVariables>

        propPanel = new PropertyPanel();
        genId = new Random();

        
  } 
  public void setUpToolBar()
  { 

  //<Begin_setUpToolBar>

  //<End_setUpToolBar>
  } 
  public void setUpGUI(Container container)throws Exception
  { 
      
      String imageDir = null;
        if(NmsClientUtil.applet != null)
        {
            imageDir = NmsClientUtil.applet.getDocumentBase () +	"../images";//No Internationalisation
        }
        
         ImageIcon addImage = NmsClientUtil.getImageIcon(new URL (imageDir+"/addnode" + NmsClientUtil.getImageType()));//No Internationalisation
       
     ImageIcon modifyImage = NmsClientUtil.getImageIcon(new URL (imageDir+"/modifynode" + NmsClientUtil.getImageType()));//No Internationalisation
     ImageIcon deleteImage  = NmsClientUtil.getImageIcon(new URL (imageDir+"/deletenode" + NmsClientUtil.getImageType()));//No Internationalisation
     ImageIcon moveImage  = NmsClientUtil.getImageIcon(new URL (imageDir+"/movenode" + NmsClientUtil.getImageType()));//No Internationalisation

     

      //<Begin_setUpGUI_Container>
      container.add(Top,BorderLayout.CENTER);
      Top.setLayout(new BorderLayout(5,5));
      Top.add(okCloseHelpPanel,BorderLayout.SOUTH);
      okCloseHelpPanel.setLayout(new FlowLayout(1,5,5));
      okCloseHelpPanel.add(applyButton);
      okCloseHelpPanel.add(closeButton);
     
      okCloseHelpPanel.add(helpButton);
      Top.add(centerPanel,BorderLayout.CENTER);
       
     
      Top.add(leftAdjustPanel,BorderLayout.WEST);
      leftAdjustPanel.setLayout(new FlowLayout(1,5,5));
      Top.add(rightAdjustPanel,BorderLayout.EAST);
      rightAdjustPanel.setLayout(new FlowLayout(1,5,5));
      Top.add(topAdjustPanel,BorderLayout.NORTH);
      topAdjustPanel.setLayout(new BorderLayout(5,5));

      
      
      backNextPanel.setPreferredSize(new Dimension(500,40));
      backNextPanel.setLayout(null);
      tips.setBounds(15,5,300,25);
      backButton.setBounds(400,5,100,25);
      nextButton.setBounds(505,5,100,25);
      
      
      
    

      backNextPanel.add(tips);
      backNextPanel.add(backButton);
      backNextPanel.add(nextButton);

      treeNodeFrame.setResizable(false);
      tree = NmsUiAPI.getNmsTree();
      comp = new TreeCombo((XMLTreeModel)tree.getModel());
      comp.setActionCommand("combobox");//No Internationalisation
      
     
      
      
      
        
      
      if((actionType == 1)||(actionType ==0))
      {
          mainPanel.setLayout(card);
          centerPanel.setLayout(new BorderLayout(5,5));
          centerPanel.add(mainPanel,BorderLayout.CENTER);
          
          backButton.setEnabled(false);
      
     
          mainPanel.add(defaultPropPanel,"defaultProp");//No Internationalisation
          mainPanel.add(userPropPanel,"userProp");//No Internationalisation
          
          JPanel iconPanel1 = new JPanel();
          JPanel iconPanel2 = new JPanel();

          iconPanel1.setLayout(null);
          iconPanel2.setLayout(null);
          iconPanel1.setPreferredSize(new Dimension(500,50));
          iconPanel2.setPreferredSize(new Dimension(500,50));
          
          
          defaultTopPanel.add(iconPanel1);
          userTopPanel.add(iconPanel2);
          
          JLabel label1 = new JLabel(addImage);
          JLabel label2 = new JLabel(addImage);
          JLabel label9 = new JLabel(modifyImage);
          JLabel label10 = new JLabel(modifyImage);
          

          label1.setSize(new Dimension(32,32));
          label2.setSize(new Dimension(32,32));
          label9.setSize(new Dimension(32,32));
          label10.setSize(new Dimension(32,32));
          
          label1.setBounds(0,0,32,32);
          label2.setBounds(0,0,32,32);
          label9.setBounds(0,0,32,32);
          label10.setBounds(0,0,32,32);
          
          

          if(actionType == 1)
          {
          
              iconPanel1.add(label1);
              iconPanel2.add(label2);
          }
          else if (actionType == 0)
          {
              iconPanel1.add(label9);
              iconPanel2.add(label10);
          }
              
          
          JLabel label3 = new JLabel(NmsClientUtil.GetString("User can specify the default tree node properties here for the tree node "));
          
          JLabel label4 = new JLabel(NmsClientUtil.GetString("Any additional user defined properties for the node to be added can be"));
          JLabel label5 = new JLabel(NmsClientUtil.GetString("to be added"));
          JLabel label6 = new JLabel(NmsClientUtil.GetString("specified here"));
          
          JLabel label7 = new JLabel(NmsClientUtil.GetString("User can modify default tree node properties here"));
          JLabel label8 = new JLabel(NmsClientUtil.GetString("User can modify user defined tree node properties here"));
          
          label3.setBounds(42,0,450,15);
          label4.setBounds(42,0,450,15);
          label5.setBounds(42,18,400,15);
          label6.setBounds(42,18,400,15);
          label7.setBounds(42,9,400,15);
          label8.setBounds(42,9,400,15);

          if(actionType == 1)
          {
              iconPanel1.add(label3);
              iconPanel1.add(label5);
              
              iconPanel2.add(label4);
              iconPanel2.add(label6);
          }
          else if(actionType == 0)
          {
              iconPanel1.add(label7);
              iconPanel2.add(label8);
          }
              
          
          
          card.show(mainPanel,"defaultProp");
          
          defaultPropPanel.setLayout(new BorderLayout(5,5));

          defaultPropPanel.add(defaultCenterPanel,BorderLayout.CENTER);
          //centerPanel.add(defaultPropPanel,BorderLayout.CENTER);
          defaultCenterPanel.setLayout(new BorderLayout(5,5));
          defaultCenterPanel.add(displayPanel,BorderLayout.NORTH);
          displayPanel.setLayout(new BorderLayout(5,5));

          JPanel mandatoryPanel = new JPanel();
          //mandatoryPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
          mandatoryPanel.setLayout(new BorderLayout());
          JLabel mandatoryLabel = new JLabel(NmsClientUtil.GetString("Fields marked with (*) are mandatory"));

          mandatoryPanel.add(mandatoryLabel, BorderLayout.WEST);
          
          JPanel mandatoryFramePanel = new JPanel();
          mandatoryFramePanel.setLayout(new BorderLayout(5,5));
          mandatoryFramePanel.add(framePanel,BorderLayout.NORTH);
          mandatoryFramePanel.add(mandatoryPanel,BorderLayout.CENTER);
          
          displayPanel.add(nodePanel,BorderLayout.NORTH);
          displayPanel.add(panelPanel,BorderLayout.CENTER);
          displayPanel.add(mandatoryFramePanel,BorderLayout.SOUTH);
          
          nodePanel.setLayout(new GridLayout(0,2,5,5));
          nodePanel.setBorder(new TitledBorder(NmsClientUtil.GetString("Tree Node Attributes")));          
          nodePanel.setPreferredSize(new Dimension(440,165));
          
          panelPanel.setLayout(new GridLayout(0,2,5,5));
          panelPanel.setBorder(new TitledBorder(NmsClientUtil.GetString("Panel Attributes")));
          panelPanel.setPreferredSize(new Dimension(440,110));

          framePanel.setLayout(new GridLayout(0,2,5,5));
          framePanel.setBorder(new TitledBorder(NmsClientUtil.GetString("Frame Attributes")));
          
          framePanel.setPreferredSize(new Dimension(440,80));
          
          nodePanel.add(labelPanel1);
          nodePanel.add(textFieldPanel1);
          
          panelPanel.add(labelPanel2);
          panelPanel.add(textFieldPanel2);

          framePanel.add(labelPanel3);
          framePanel.add(textFieldPanel3);

          labelPanel1.setLayout(new GridLayout(5,0,2,2));
          labelPanel2.setLayout(new GridLayout(3,0,2,2));
          labelPanel3.setLayout(new GridLayout(2,0,2,2));
          
          textFieldPanel1.setLayout(new GridLayout(5,0,2,2));
          textFieldPanel2.setLayout(new GridLayout(3,0,2,2));
          textFieldPanel3.setLayout(new GridLayout(2,0,2,2));

          
          defaultPropPanel.add(defaultTopPanel,BorderLayout.NORTH);
          defaultTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
          defaultPropPanel.add(defaultRightPanel,BorderLayout.EAST);
          defaultRightPanel.setLayout(new FlowLayout(1,5,5));
          defaultPropPanel.add(defaultLeftPanel,BorderLayout.WEST);
          defaultLeftPanel.setLayout(new FlowLayout(1,5,5));
          defaultPropPanel.add(defaultBottomPanel,BorderLayout.SOUTH);
          defaultBottomPanel.setLayout(new FlowLayout(1,5,5));  
          
  
          
          userPropPanel.setLayout(new BorderLayout(5,5));
          userPropPanel.add(userMainPanel,BorderLayout.CENTER);
          userMainPanel.setLayout(new BorderLayout(5,5));
          userMainPanel.add(userLeftPanel2,BorderLayout.WEST);
          userLeftPanel2.setLayout(new FlowLayout(1,5,5));
          userMainPanel.add(userTopPanel,BorderLayout.NORTH);
          userTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
          userMainPanel.add(userRightPanel2,BorderLayout.EAST);
          userRightPanel2.setLayout(new FlowLayout(1,5,5));
          userMainPanel.add(userBottomPanel,BorderLayout.SOUTH);
          userBottomPanel.setLayout(new FlowLayout(1,5,5));
          userMainPanel.add(userCenterPanel,BorderLayout.CENTER);
          userCenterPanel.setLayout(new BorderLayout(5,5));
     

          
          //labelPanel.setLayout(new GridLayout(11,0,2,2));
          
          /**
          for(int i=0;i<labelArray.length;i++)
          {
              labelPanel.add(labelArray[i]);
          }
          */

          labelPanel1.add(labelArray[0]);
          labelPanel1.add(labelArray[1]);
          labelPanel1.add(labelArray[7]);
          labelPanel1.add(labelArray[8]);
          labelPanel1.add(labelArray[4]);

          labelPanel2.add(labelArray[2]);
          labelPanel2.add(labelArray[3]);
          labelPanel2.add(labelArray[5]);

          labelPanel3.add(labelArray[9]);
          labelPanel3.add(labelArray[6]);
          
          
          
          //textFieldPanel.setLayout(new GridLayout(11,0,3,3));

           tree = NmsUiAPI.getNmsTree();
           //comp = new TreeCombo((XMLTreeModel)tree.getModel());
           comp.setActionCommand("combobox");//No Internationalisation
           textFieldPanel1.add(comp);

          textFieldPanel1.add(textFieldArray[0]);
          textFieldPanel1.add(textFieldArray[4]);
          textFieldPanel1.add(textFieldArray[5]);

          textFieldPanel2.add(textFieldArray[1]);
          textFieldPanel2.add(textFieldArray[2]);
          textFieldPanel2.add(textFieldArray[3]);

          textFieldPanel3.add(textFieldArray[6]);

          
          int j=0;
          for(int i=0;i<componentValue.length;i++)
          {
              if (componentValue[i] == 0)
              {
                  switch(i)
                  {
                  case 6:
                      {
                          actionComboBox.addItem(NmsClientUtil.GetString("Do Nothing"));
                          actionComboBox.addItem(NmsClientUtil.GetString("Hide Only"));
                          actionComboBox.addItem(NmsClientUtil.GetString("Dispose Only"));
                          actionComboBox.addItem(NmsClientUtil.GetString("Dispose and Remove Tree Node"));
                           actionComboBox.addItem(NmsClientUtil.GetString("Dispose and Remove Tree Node from DB"));
                          
                          //actionComboBox.setSelectedItem("HIDE_ONLY");
                          actionComboBox.setActionCommand("actioncombobox");
                          textFieldPanel3.add(actionComboBox);
                      }
                      break;
                  }
                  
                  
              }

              /**
              else if (componentValue[i] == 1)
              {
                  textFieldPanel.add(textFieldArray[j]);
                  j++;
              }
              */
              else if (componentValue[i] == 2)
              {
                  
                  htmlClientPanel.setLayout(new GridLayout(1,2,5,5));
                  
                  htmlClientPanel.add(htmlComboBox);
                  textFieldPanel1.add(htmlClientPanel);
              }
              
          }
          
          centerPanel.add(backNextPanel,BorderLayout.SOUTH);
          //centerPanel.add(userPropPanel);
          userCenterPanel.add(propPanel,BorderLayout.CENTER);

      }
      else if(actionType ==2)
      {

          JPanel iconPanel1 = new JPanel();
          JPanel contPanel = new JPanel();
          JPanel warningPanel = new JPanel();
          
          iconPanel1.setLayout(null);
          iconPanel1.setPreferredSize(new Dimension(440,40));
          
          warningPanel.setLayout(null);
          warningPanel.setPreferredSize(new Dimension(440,60));
          
          JLabel label1 = new JLabel(deleteImage);

          label1.setSize(new Dimension(32,32));
          
          label1.setBounds(0,0,32,32);
          
          iconPanel1.add(label1);
          
          JLabel label3 = new JLabel(NmsClientUtil.GetString("User can delete any tree node in the tree by using "));
          JLabel labels = new JLabel(NmsClientUtil.GetString("this user interface"));
          
          JLabel warningLabel1 = new JLabel(NmsClientUtil.GetString(" Note : Any node deleted will permanently get removed "));
          
          warningLabel1.setBounds(42,20,400,15);
          warningPanel.add(warningLabel1);  
          
          JLabel warningLabel2 = new JLabel("          " +NmsClientUtil.GetString("      from the client tree for this user"));
          
          warningLabel2.setBounds(42,38,400,15);
          warningPanel.add(warningLabel1);  
          warningPanel.add(warningLabel2);
                                            
          
          
          label3.setBounds(42,0,350,15);
          labels.setBounds(42,18,250,15);
          
          
          iconPanel1.add(label3);
          iconPanel1.add(labels);
              

          JPanel deletePanel = new JPanel();
          deletePanel.setPreferredSize(new Dimension(450,30));
          deletePanel.setLayout(new GridLayout(0,2,5,5));
         

          centerPanel.setLayout(new BorderLayout(5,5));
          contPanel.setBorder(new TitledBorder(NmsClientUtil.GetString("Delete Tree Node")));
          
          contPanel.add(iconPanel1,BorderLayout.CENTER);
          contPanel.add(deletePanel,BorderLayout.NORTH);
          contPanel.add(warningPanel,BorderLayout.SOUTH);
          centerPanel.add(contPanel,BorderLayout.CENTER);
          
          
          JLabel label2 = new JLabel("            "+NmsClientUtil.GetString("        Tree Node"));
          
          deletePanel.add(labelPanel);
          deletePanel.add(textFieldPanel);
          
          labelPanel.setLayout(new GridLayout(1,1,5,5));
          labelPanel.add(label2);
          
          textFieldPanel.setLayout(new GridLayout(1,1,5,5));
          tree = NmsUiAPI.getNmsTree();
          textFieldPanel.add(comp);
      }
      else if(actionType == 3)
      {

          JPanel iconPanel1 = new JPanel();
          JPanel contPanel = new JPanel();
          
          iconPanel1.setLayout(null);
          iconPanel1.setPreferredSize(new Dimension(560,40));
          
          JLabel label1 = new JLabel(moveImage);

          label1.setSize(new Dimension(32,32));
          
          label1.setBounds(0,0,32,32);
          
          iconPanel1.add(label1);
          
          JLabel label3 = new JLabel(NmsClientUtil.GetString("User can move the tree node to the desired location in the tree using "));
          JLabel labels = new JLabel(NmsClientUtil.GetString("this user interface"));
          
          label3.setBounds(44,0,500,15);
          labels.setBounds(44,18,500,15);
          
          
          iconPanel1.add(label3);
          iconPanel1.add(labels);
              

          JPanel movePanel = new JPanel();
          movePanel.setPreferredSize(new Dimension(530,100));
          movePanel.setLayout(new GridLayout(1,3,5,5));
         

          centerPanel.setLayout(new BorderLayout(5,5));
          contPanel.setBorder(new TitledBorder(NmsClientUtil.GetString("Move Tree Node")));
          
          contPanel.add(iconPanel1,BorderLayout.CENTER);
          contPanel.add(movePanel,BorderLayout.NORTH);
          centerPanel.add(contPanel,BorderLayout.CENTER);
          
          
          JLabel label2 = new JLabel("       "+NmsClientUtil.GetString("Select the Tree Node to be moved"));
          JLabel label4 = new JLabel("       "+NmsClientUtil.GetString("Select the Destination Tree Node"));
          
          movePanel.add(labelPanel);
          movePanel.add(textFieldPanel);
          
          labelPanel.setLayout(new GridLayout(3,1,5,5));
          labelPanel.add(label2);
          labelPanel.add(label4);
          labelPanel.add(new JLabel("       " + NmsClientUtil.GetString("Node Index")));
          
          textFieldPanel.setLayout(new GridLayout(3,1,5,5));
          tree = NmsUiAPI.getNmsTree();
          
          tree1 = NmsUiAPI.getNmsTree();
          comp1 = new TreeCombo((XMLTreeModel)tree1.getModel());

          //fix to associate the parent node in the destination -usability issue
                      XMLNode node = null;
            JTree tree = NmsUiAPI.getNmsTree();

            TreePath path =tree.getSelectionPath();
            if(path == null)
            {
                node = (XMLNode) tree.getModel().getRoot();
            }
            else
            {
                node = (XMLNode) path.getLastPathComponent();
            }


            XMLNode parentNode = node.getParentNode();
	
            if(parentNode != null)
            {
           	comp1.setParentNode((String) parentNode.getAttribute("ID"));
            }
            else
            {
		String nodeName = node.getNodeName();
		comp1.setParentNode(nodeName);	
            }

          comp1.setActionCommand("combobox1");//No Internationalisation
          comp1.addActionListener(this);
          
          textFieldPanel.add(comp);
          textFieldPanel.add(comp1);
          nodeIndexTextField = new JTextField("0");//No Internationalisation
          nodeIndexTextField.addKeyListener(this);
          textFieldPanel.add(nodeIndexTextField);
              

      }
          
          
          
          
          
          
          
          /*********************************/

	
  //<End_setUpGUI_Container>
  } 
  public void setUpMenus()
  { 

  //<Begin_setUpMenus>

  //<End_setUpMenus>
  } 
  public void stop()
  { 

  //<Begin_stop>
      releaseMemory(treeNodeFrame);

  //<End_stop>
  } 
  public void start()
  { 

  //<Begin_start>
     
  //<End_start>
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
               //value = (String)com.adventnet.apiutils.Utility.getParameter(input);
           }    
           if(value == null)
           {
            if (input.equals("HOST")) value = "localhost"; //No Internationalisation
            if (input.equals("PORT")) value = "161"; //No Internationalisation
            }
        return value;

  //<End_getParameter_String>
  } 

     

  
  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println(NmsClientUtil.GetString("Internal Error :"+ message));
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
      System.out.println(NmsClientUtil.GetString("Internal Error :"+ message));
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }
    
    private void initValues()
    {
        XMLNode selectedNode = null;
        JTree tree = NmsUiAPI.getNmsTree();

        TreePath path =tree.getSelectionPath();
        if(path == null)
        {
            selectedNode = (XMLNode) tree.getModel().getRoot();
        }
        else
        {
            selectedNode = (XMLNode) path.getLastPathComponent();
        }
        

        Hashtable selectedNodeProp = selectedNode.getAttributeList();
        comp.setParentNode((String)selectedNodeProp.get("ID")); //No Internationalisation
        if((actionType == 0)||(actionType ==1))
        {
            applyButton.setEnabled(false);
        }
        if(!firstTime)
        {
            setValues(selectedNodeProp);
            comp.requestFocus();
        }
        else
        {
            firstTime = false;
        }
        
        
    }
    
    private void getValues()
    {
        //boolean refreshComboBox = false;
        String refreshString = null;

        Hashtable selectedNodeProperties = comp.getSelectedParentProps();
        Hashtable cloneOfSelectedNodeProp = (Hashtable)selectedNodeProperties.clone();
        
        Hashtable selectedNodeProp = new Hashtable();

        XMLNode root= (XMLNode)NmsUiAPI.getNmsTree().getModel().getRoot();
        XMLNode xmlNode = NmsClientUtil.getMainPanel().checkIfBelongs(root,(String)selectedNodeProperties.get("ID"));//No Internationalisation
        
        XMLNode parentNode = xmlNode.getParentNode();


        int j=0;
        for(int i=0;i<componentValue.length;i++)
        {
            if(componentValue[i] == 1)
            {
                if(defaultProps[i] != "NONE")//No Internationalisation
                {
                    selectedNodeProp.put(defaultProps[i],textFieldArray[j].getText());
                    cloneOfSelectedNodeProp.remove(defaultProps[i]);
                }
                j++;
            }
        }
        
        for(int i=0;i<propsToSkip.length;i++) 
        { 
            if(cloneOfSelectedNodeProp.get(propsToSkip[i])!= null)  
            {  
                cloneOfSelectedNodeProp.remove(propsToSkip[i]);  
            }  
        }  
        //selectedNodeProp.put("Client" , "true");
       
        int selectedIndex = actionComboBox.getSelectedIndex();
        String actionString[] = { "DO_NOTHING_ON_CLOSE" , "HIDE_ON_CLOSE","DISPOSE_ON_CLOSE","DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE","DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE"};
        if(selectedIndex >=0)
        {
            selectedNodeProp.put("DEFAULT-CLOSE-OPERATION",actionString[selectedIndex]);
        }

        selectedIndex = htmlComboBox.getSelectedIndex();
        String htmlString[] = { "All","Java","HTML"};

        if(selectedIndex >=0)
        {
            selectedNodeProp.put("Client",htmlString[selectedIndex]);
        }
        
        

        if((actionType == 0)||(actionType == 1))
        {

            String nameOfNode = textFieldArray[0].getText().trim();


            if(nameOfNode.equals(""))
            {
                showErrorMessage(NmsClientUtil.GetString(" Tree node name is empty - please give a valid entry"));
                textFieldArray[0].requestFocus();
                return;
            }

            XMLNode tempXmlNode = null;
            
            if(actionType == 0)
            {
                tempXmlNode = parentNode;
            }
            else if (actionType == 1)
            {
                tempXmlNode = xmlNode;
            }

            if(tempXmlNode == null)
            {
                showErrorMessage(NmsClientUtil.GetString("Cannot modify this node"));
                textFieldArray[0].requestFocus();
                return;
            }
            
            Vector childNodes = tempXmlNode.getChildNodes();
            String parentNodeName = (String)tempXmlNode.getAttribute("TREE-NAME");//No Internationalisation
            String selectedNodeId = (String)selectedNodeProperties.get("ID");//No Internationalisation
            
            for( int i=0;i< childNodes.size(); i++)
            {
                XMLNode temp = (XMLNode) childNodes.elementAt(i);
                String treeName  = (String)temp.getAttribute("TREE-NAME");//No Internationalisation

                if(treeName != null)
                {
                    if(treeName.equalsIgnoreCase(nameOfNode))
                    {
                        if(actionType == 0)
                        {
                            String id = (String)temp.getAttribute("ID");//No Internationalisation

                            if(!(id.equals(selectedNodeId)))
                            {
                                showErrorMessage(java.text.MessageFormat.format(NmsClientUtil.GetString("Cannot modify this node - Tree node {0} already exists under {1}"), new String[]{treeName, parentNodeName}));
                                textFieldArray[0].requestFocus();
                                return;
                            }

                        }
                        else if (actionType == 1)
                        {                            
                            showErrorMessage(java.text.MessageFormat.format(NmsClientUtil.GetString("Cannot add this node - Tree node {0} already exists under {1}"), new String[]{treeName, parentNodeName}));
                            textFieldArray[0].requestFocus();
                            return;
                        }
                    }
                }
            }

            String menuName = textFieldArray[2].getText().trim();
            if(menuName.equals(""))//No Internationalisation
            {
                int option = showConfirmMessage(NmsClientUtil.GetString("Menu file name is blank  - Do you want to proceed"));
                if(option != JOptionPane.OK_OPTION)
                {
                    textFieldArray[2].requestFocus();
                    return;
                }
            }
            else
            {
                if(!checkMenuFile(menuName))
                {

                    int option = showConfirmMessage(NmsClientUtil.GetString("Menu file ") + " " + menuName + " " + NmsClientUtil.GetString(" is not found - Do you want to proceed"));

                    if(option != JOptionPane.OK_OPTION)
                    {
                        textFieldArray[2].requestFocus();
                        return;
                    }

                }


                

            }
        }
        
        if (actionType == 0)
        {
            refreshString = (String) selectedNodeProperties.get("ID");//No Internationalisation
            selectedNodeProp.put("ID",(String)selectedNodeProperties.get("ID"));
            

            
            for(int i=0;i<propPanel.getRows();i++)
            {
                String temp[]= propPanel.getProperty(i);
                
                if(cloneOfSelectedNodeProp.get(temp[0]) ==null)
                {
                    selectedNodeProp.put(temp[0] , temp[1]);
                }
                else
                {
                    selectedNodeProp.put(temp[0] , temp[1]);
                    cloneOfSelectedNodeProp.remove(temp[0]);
                }
            }
            
            Enumeration keys = cloneOfSelectedNodeProp.keys();
            
            while(keys.hasMoreElements())
            {
                String temp = (String)keys.nextElement();
                selectedNodeProperties.remove(temp);
            }

            String panel = (String)selectedNodeProperties.get("PANEL-KEY");
            
            if(panel != null)
            {
                selectedNodeProp.remove("PANEL-NAME");
                selectedNodeProp.put("PANEL-KEY",panel);
            }
           
        
            if ((parentNode == null)||(xmlNode.getAttribute("ID").equals("WebNMS-Panels"))) 
            { 
                showErrorMessage(NmsClientUtil.GetString("Cannot modify this node")); 
                return; 
            } 
           
            String key = (String)xmlNode.getAttribute("PANEL-KEY");
            String panelname = (String)xmlNode.getAttribute("PANEL-NAME");
            if((key == null || key.equals("")) && (panelname == null || panelname.equals("")))
                return;

            selectedNodeProp.put("parent",(String)parentNode.getAttribute("ID"));//No Internationalisation

            
            boolean flag = NmsUiAPI.modifyTreeNode((String)selectedNodeProperties.get("ID") ,null, hashToProperties(selectedNodeProp),true);//No Internationalisation

            if(!flag)
            {
                showErrorMessage(NmsClientUtil.GetString(" Problem in modifying this  node - Some of the attributes may be wrong"));
                return;
            }
            else
            {
                propPanel.JTable1.clearSelection();
                propPanel.keyText.setText("");//No Internationalisation
                propPanel.valueText.setText("");//No Internationalisation
            }
                
            

        }
        else if (actionType == 1)
        {
            
            String panelName = (String)textFieldArray[1].getText();
            if(panelName.equals(""))//No Internationalisation
            {
                showErrorMessage(NmsClientUtil.GetString(" Panel class name is empty - please give a valid entry"));
                textFieldArray[1].requestFocus();
                return;
            }

            Class className = null; 
            try 
            { 
               className = Class.forName(panelName); 
            } 
            catch(ClassNotFoundException e) 
            { 
                showErrorMessage(java.text.MessageFormat.format(NmsClientUtil.GetString("Specified class name {0} is not found in the client class path. Please rectify it "), new String[]{panelName}));
                    textFieldArray[1].requestFocus(); 
                    return; 
            } 

            String temp1 = (String) selectedNodeProperties.get("TREE-NAME"); //No Internationalisation
            temp1+="."; 
            int val = genId.nextInt(); 
            if(val < 0) 
                { 
                    val = Math.abs(val); 
                } 
            String temp2 = (String) Integer.toString(val); 
            selectedNodeProp.put("ID",temp1+temp2); 
            selectedNodeProp.put("parent",(String) selectedNodeProperties.get("ID")); 
                      
            for(int i=0;i<propPanel.getRows();i++) 
                { 
                    String temps[] = propPanel.getProperty(i); 
                    selectedNodeProp.put(temps[0] , temps[1]); 
                } 

            String frameName = (String) selectedNodeProp.get("FRAME-TITLE");

                      
            if((frameName == null)||(frameName.equals("")))
                {
                    String treeName = (String) selectedNodeProp.get("TREE-NAME");
                    selectedNodeProp.put("FRAME-TITLE" , treeName);
                }
                      
            String icon = (String) selectedNodeProp.get("ICON-FILE");
            if(icon.equals(""))
                {
                    icon = "images/redDot.png";
                    selectedNodeProp.put("ICON-FILE",icon);
                }
                        
                        

            boolean flag1 = NmsUiAPI.addTreeNode((String)selectedNodeProp.get("parent") ,null,hashToProperties(selectedNodeProp),false); //No Internationalisation
                        
            if(!flag1)
                {
                    showErrorMessage(NmsClientUtil.GetString(" Problem in adding node - Some of the attributes may be wrong"));
                    return;
                }
             

            /**************************************************/

            if(refreshString != null)
            {
                comp.setParentNode(refreshString);  
            } 
            

            int k=0;
            for(int i=0;i<componentValue.length;i++)
            {
                if(componentValue[i] == 1)
                {
                    textFieldArray[k].setText("");//No Internationalisation
                    k++;
                }
            }
            actionComboBox.setSelectedItem(NmsClientUtil.GetString("Do Nothing"));
            htmlComboBox.setSelectedItem(NmsClientUtil.GetString("both Java UI and HTML UI"));
            
            if(propPanel.getRows() > 0)
            {
                propPanel.deleteAllRows();
            }
            
            
            
        }
        else if (actionType == 2)
        {
            
            String id = (String) selectedNodeProperties.get("ID");//No Internationalisation
            String name = (String) selectedNodeProperties.get("TREE-NAME");//No Internationalisation
            boolean canDeleteThisNode = true ;
            
            if((id.equals("Events"))||(id.equals("Alerts"))||(id.equals("Maps"))||(id.equals("Network Database"))||(id.equals("Stats Admin"))||(id.equals("Mib Manager"))||(id.equals("TL1 Browser"))||(id.equals("Policy"))||(id.equals("config"))||(id.equals("WebNMS-Panels"))||(id.equals("AdventNet")))//No Internationalisation
            {
                canDeleteThisNode  = false;
            }
            if(canDeleteThisNode)
            {
                refreshString = (String) parentNode.getAttribute("ID");
                String string2 = (String) selectedNodeProperties.get("NODE-FORALL"); //No Internationalisation
                if(!xmlNode.isLeaf())
                {
                    
			int returnType = -1;
			if(string2 != null && string2.equals("true")){

				returnType = showConfirmMessage(NmsClientUtil.GetString("Deleting this node will delete all its sub nodes for All the Users - Do you want to proceed "));//No Internationalisation
			}else{
				returnType = showConfirmMessage(NmsClientUtil.GetString("Deleting this node will delete all its sub nodes - Do you want to proceed "));//No Internationalisation
			}
                    
                    if(returnType == JOptionPane.YES_OPTION)
                    {
                        NmsUiAPI.removeTreeNode((String)selectedNodeProperties.get("ID") , (String)parentNode.getAttribute("ID"), false);//No Internationalisation
                    }
                }
                else
                {
                   
			int returnType = -1;
			if(string2 != null && string2.equals("true")){

				returnType = showConfirmMessage(NmsClientUtil.GetString("Deleting this node will delete this node for All the users,  Do you want to delete the node ") + " " + NmsClientUtil.GetString(name));//No Internationalisation
			}else{

				returnType = showConfirmMessage(NmsClientUtil.GetString("  Do you want to delete the node ") + " " + NmsClientUtil.GetString(name));//No Internationalisation
			}

                    
                    if(returnType == JOptionPane.YES_OPTION)
                    {
                        NmsUiAPI.removeTreeNode((String)selectedNodeProperties.get("ID") , (String)parentNode.getAttribute("ID"), false);//No Internationalisation
                    }
                }

                
                if(operationDone)
                {
                    /*************************************/
                    XMLNode node = (XMLNode) NmsUiAPI.getNmsTree().getSelectionPath().getLastPathComponent();
                    
                    Hashtable nodeProp = node.getAttributeList();
                    comp.setParentNode((String)nodeProp.get("ID"));//No Internationalisation
                    
                    /********************************************/
                }
            }
            else
            {
                showErrorMessage(NmsClientUtil.GetString("Cannot delete this node"));   
            }
            
        }
        else if (actionType == 3)
        {
            
            //Hashtable newParent = comp1.getSelectedParentProps();
            refreshString = (String) selectedNodeProperties.get("ID");//No Internationalisation
            String moveNodeId = (String) selectedNodeProperties.get("ID");//No Internationalisation
            String moveNodeName = (String) selectedNodeProperties.get("TREE-NAME");//No Internationalisation
            
        
            String newParentId = (String) comp1.getSelectedParent();
            if(moveNodeId == newParentId)
            {
                showErrorMessage(NmsClientUtil.GetString(" Invalid move operation"));
                return;
            }
           	
	    Hashtable tempProps = comp1.getSelectedParentProps();
	    String string1 = (String) selectedNodeProperties.get("NODE-FORALL");//No Internationalisation
	    String string2 = (String) tempProps.get("NODE-FORALL");//No Internationalisation

	    if((string1 != null && string1.equals("true")) || (string2 != null && string2.equals("true"))){

		    showErrorMessage(NmsClientUtil.GetString(" Move Operation Can not be performed on All user nodes"));//No Internationalisation
		    return;
	    }

            root= (XMLNode)NmsUiAPI.getNmsTree().getModel().getRoot();
            xmlNode = NmsClientUtil.getMainPanel().checkIfBelongs(root,newParentId);
            
            parentNode = xmlNode.getParentNode();

            Vector children = xmlNode.getChildNodes();
            String nodeName = (String)xmlNode.getAttribute("TREE-NAME");//No Internationalisation

            for(int i=0;i<children.size();i++)
            {
                XMLNode childNode = (XMLNode) children.get(i);

                String name = (String) childNode.getAttribute("TREE-NAME");//No Internationalisation
                String id = (String) childNode.getAttribute("ID");//No Internationalisation

                if( moveNodeName.equals(name))
                {
                    if(!(id.equals(moveNodeId)))
                    {
                        showErrorMessage(java.text.MessageFormat.format(NmsClientUtil.GetString(" Invalid move operation. Tree node {0} is already exists under {1}"), new String[]{moveNodeName, nodeName}));
                        return;
                    }
                }
                    
            }

            String temp = null;
            
            while(parentNode != null)
            {
                temp = (String)parentNode.getAttribute("ID");//No Internationalisation
                
                if (temp == moveNodeId)
                {
                    showErrorMessage(NmsClientUtil.GetString("Invalid move operation "));
                    return;
                }
                
                parentNode = parentNode.getParentNode();
                
            }
            
            int nodeIndex = 0;
            try
            {
                nodeIndex = Integer.parseInt(nodeIndexTextField.getText());
            }
            catch(NumberFormatException e)
            {
                showErrorMessage(NmsClientUtil.GetString("Invalid entries in the node index field"));
                return;
            }

            if(nodeIndex < 0)
            {
                showErrorMessage(NmsClientUtil.GetString("Invalid entries in the node index field"));
                return;
                
            }

            String nodeId = (String)selectedNodeProperties.get("ID");//No Internationalisation

            NmsUiAPI.moveTreeNode(nodeId, newParentId,nodeId,nodeIndex, false);
        }
    }
        

    private void setValues(Hashtable selectedNodeProp)
    {

        Hashtable cloneOfSelectedNodeProp = (Hashtable)selectedNodeProp.clone();

        if(actionType == 0)
        {
            String k = (String)selectedNodeProp.get("PANEL-KEY");//No Internationalisation
            
            if((k != null)&&(k.equals("MapApplet")))//No Internationalisation
            {
                textFieldArray[0].setEditable(false);
                textFieldArray[2].setEditable(false);
                //textFieldArray[5].setEditable(false);
            }
            else
            {
                textFieldArray[0].setEditable(true);
                textFieldArray[2].setEditable(true);
                //textFieldArray[5].setEditable(true);
            }
            
            tips.setText(NmsClientUtil.GetString("Click next to modify user defined properties"));

            int j=0;
            for(int i=0;i<componentValue.length;i++)
            {
                if(componentValue[i] == 1)
                {
                    if(defaultProps[i] != "NONE")//No Internationalisation
                    {
                        if(selectedNodeProp.get(defaultProps[i])!=null)
                        {
                            textFieldArray[j].setText((String)selectedNodeProp.get(defaultProps[i]));
                            cloneOfSelectedNodeProp.remove(defaultProps[i]);
                        }
                        else 
                        {
                            textFieldArray[j].setText("");//No Internationalisation
                        }
                    }
                    else
                    {
                        textFieldArray[j].setText("");//No Internationalisation
                        cloneOfSelectedNodeProp.remove(defaultProps[i]);
                    }
                    j++;
                }
            }

            String panelkey = (String)selectedNodeProp.get("PANEL-KEY");//No Internationalisation
            String panelname = (String)selectedNodeProp.get("PANEL-NAME");//No Internationalisation

      

            if(panelname == null)
            {
                if(panelkey != null)
                {
                    NmsPanel nmspanel = (NmsPanel)NmsClientUtil.getMainPanel().panelList.get(panelkey);
                    textFieldArray[1].setText(nmspanel.getClass().getName());
                }
            }

            for(int i=0;i<propsToSkip.length;i++)
            {
                if (propsToSkip[i].equals("DEFAULT-CLOSE-OPERATION"))//No Internationalisation
                {
                    String actionString = (String)cloneOfSelectedNodeProp.get(propsToSkip[i]);
                    
                    if(actionString != null)
                    {
                        if(actionString.trim().equals("DO_NOTHING_ON_CLOSE"))//No Internationalisation
                        {
                            actionComboBox.setSelectedItem(NmsClientUtil.GetString("Do Nothing"));
                        }
                        
                        else if(actionString.trim().equals("HIDE_ON_CLOSE"))//No Internationalisation
                        {
                            actionComboBox.setSelectedItem(NmsClientUtil.GetString("Hide Only"));
                        }
                        else if(actionString.trim().equals("DISPOSE_ON_CLOSE"))//No Internationalisation
                        {
                            actionComboBox.setSelectedItem(NmsClientUtil.GetString("Dispose Only"));
                        }
                        else if(actionString.trim().equals("DISPOSE_AND_REMOVE_TREENODE_ON_CLOSE"))//No Internationalisation
                        {
                            actionComboBox.setSelectedItem(NmsClientUtil.GetString("Dispose and Remove Tree Node"));
                        }
                        else if(actionString.trim().equals("DISPOSE_AND_REMOVE_TREENODE_FROM_DB_ON_CLOSE"))//No Internationalisation
                        {
                            actionComboBox.setSelectedItem(NmsClientUtil.GetString("Dispose and Remove Tree Node from DB"));
                        }
                    }
                }

                if (propsToSkip[i].equals("Client"))//No Internationalisation
                {
                    String client = (String) cloneOfSelectedNodeProp.get(propsToSkip[i]);
                    if(client != null)
                    {
                        if(client.equals("All"))
                        {
                            htmlComboBox.setSelectedItem(NmsClientUtil.GetString("both Java UI and HTML UI"));
                        }
                        else if (client.equals("Java"))//No Internationalisation
                        {
                            htmlComboBox.setSelectedItem(NmsClientUtil.GetString("Java UI only"));
                        }
                        else if (client.equals("HTML"))//No Internationalisation
                        {
                            htmlComboBox.setSelectedItem(NmsClientUtil.GetString("HTML UI only"));
                        }
                    }
                }
                cloneOfSelectedNodeProp.remove(propsToSkip[i]);
            }
            
            
            
            if(propPanel.getRows() > 0)
            {
                propPanel.deleteAllRows();
            }
            Enumeration userProps = cloneOfSelectedNodeProp.keys();
            while(userProps.hasMoreElements())
            {
                String key = (String)userProps.nextElement();
                String value = (String) cloneOfSelectedNodeProp.get(key);
                propPanel.setProperty(key,value);    
            }
        }
        else if(actionType == 1)
        {
            tips.setText(NmsClientUtil.GetString("Click next to add  user defined properties"));
            //textFieldArray[3].setText(NmsClientUtil.GetString("true")); 
            //textFieldArray[3].setEnabled(true);

        }
    }
    
    private void showErrorMessage(String errorMessage)
    {
        NmsClientUtil.normalCursor(treeNodeFrame);
        JOptionPane.showOptionDialog (treeNodeFrame, NmsClientUtil.GetString(errorMessage), 
                                      NmsClientUtil.GetString("Error Message"), 
                                      JOptionPane.DEFAULT_OPTION, 
                                      JOptionPane.WARNING_MESSAGE, 
                                      null, new Object[]{NmsClientUtil.GetString("OK")}, NmsClientUtil.GetString("OK")); 
        
        operationDone = false;
    }
    
    private int showConfirmMessage(String confirmMessage)
    {
         NmsClientUtil.normalCursor(treeNodeFrame);
        int option = JOptionPane.showConfirmDialog(treeNodeFrame, NmsClientUtil.GetString(confirmMessage),NmsClientUtil.GetString("Confirmation  Message"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        
        if(option != JOptionPane.OK_OPTION)
        {
            operationDone = false;
        }
        else
        {
            NmsClientUtil.busyCursor(treeNodeFrame);
        }

        return option;
    }
    private Properties hashToProperties (Hashtable hash)
    {
        Properties prop = null; 
        if (hash != null) 
        { 
            prop = new Properties (); 
            for (Enumeration enumerate  = hash.keys (); enumerate.hasMoreElements ();) 
            { 
                String key = (String) enumerate.nextElement (); 
                if (hash.get (key) instanceof String) 
                {	 
                    String value = (String) hash.get (key); 
                    prop.put (key, value); 
                } 
                else 
                    prop.put (key, hash.get (key)); 
            } 
		} 
        return prop; 
    }

    public boolean checkMenuFile(String menuName)
    {

        int pos = menuName.lastIndexOf(".");
        if (pos == -1)
            return false;
        menuName = menuName.substring(0,menuName.lastIndexOf("."));
        if(menuName == null || menuName.trim().equals("") || menuName.trim().equals("null"))//No Internationalisation
            return true;
        
        InputStream is = null;
        try
        {
            String urlstring = menuName;
           // servlets change - changing all /xxxservlets/com.adventnet.nms.servlets.yyy call to /servlets/yyy For disabling Invoker servlet - Venkatramanan
            String str = NmsClientUtil.applet.getDocumentBase ()+ "../servlets/MenuFileParser?userName=" + NmsClientUtil.getUserName()+"&menuFilename=" + urlstring+"&checkForFile=TRUE"; //NO I18N
            URL url = new URL(str);
            
            is =  url.openStream();
            DataInputStream d   = new DataInputStream(is);
            //BufferedReader d = new BufferedReader(new InputStreamReader(is));
            
            // Here one string is followed by the stream contents of the specified menufile name contents
            String result = d.readLine();
            if (result.equalsIgnoreCase("File Found"))
                return true;
            else
                return false;
            
        }
        catch(Exception ex)
        {}
        return true;
    }
    public  Vector getClientsSupported()
    {
        clients=new Vector();
        clients.addElement("all");

        return clients;
    }
}
























