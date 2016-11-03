//$Id: addCustProps.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import com.adventnet.nms.tools.objtorel.TransverseListener;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.util.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class addCustProps extends AbstractTransversePanel implements ActionListener,TransverseListener {
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JPanel  panel_NameValue = null;
	javax.swing.JTextField  text_DefaultVal = null;
	javax.swing.JTextField  text_PropName = null;
	javax.swing.JLabel  label_PropName = null;
	javax.swing.JLabel  label_DefaultValue = null;
	javax.swing.JComboBox  cmb_DataType = null;
	javax.swing.JLabel  label_Access = null;
	javax.swing.JLabel  label_DataType = null;
	javax.swing.JComboBox  cmb_Access = null;
	javax.swing.JPanel  panel_AddModDel = null;
	javax.swing.JButton  button_Add = null;
	javax.swing.JButton  button_Modify = null;
	javax.swing.JButton  button_Delete = null;
	javax.swing.JScrollPane  tableScroller = null;
	javax.swing.JTable  customPropertyTable = null;
	javax.swing.JTextArea  textInfo = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

    CustomPropTableModel cptm=null;
    ListSelectionModel   tableSelModel=null;
    int rowNum=0;
    TransverseContainer  transCon=null;
    Properties           moProperties=null;
	BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
    public addCustProps()
  {
        //<Begin_addCustProps>
    this.init();
  
    //<End_addCustProps>
    }

    public addCustProps(java.applet.Applet applet)
  {
        //<Begin_addCustProps_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_addCustProps_java.applet.Applet>
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
    public void setUpProperties()throws Exception
  { 

        //<Begin_setUpProperties>

          try
          {
            panel_NameValue.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Field Properties"),1,2,new Font("Dialog",0,12),new Color(-10066279)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panel_NameValue,ex); 
          }

          try
          {
            text_PropName.setToolTipText(resourceBundle.getString("Enter name of property"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+text_PropName,ex); 
          }

          try
          {
            label_PropName.setHorizontalAlignment(2);
            label_PropName.setText(resourceBundle.getString("Property Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label_PropName,ex); 
          }

          try
          {
            label_DefaultValue.setHorizontalTextPosition(2);
            label_DefaultValue.setText(resourceBundle.getString("Default Value"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label_DefaultValue,ex); 
          }

          try
          {
            cmb_DataType.setToolTipText(resourceBundle.getString("Select Datatype for property"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cmb_DataType,ex); 
          }

          try
          {
            label_Access.setText(resourceBundle.getString("Access Modifiers"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label_Access,ex); 
          }

          try
          {
            label_DataType.setText(resourceBundle.getString("DataType"));
            label_DataType.setHorizontalAlignment(2);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label_DataType,ex); 
          }

          try
          {
            button_Add.setText(resourceBundle.getString("Add"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+button_Add,ex); 
          }

          try
          {
            button_Modify.setText(resourceBundle.getString("Modify"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+button_Modify,ex); 
          }

          try
          {
            button_Delete.setText(resourceBundle.getString("Delete"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+button_Delete,ex); 
          }
 
          try
          {
            textInfo.setWrapStyleWord(true);
            textInfo.setLineWrap(true);
            textInfo.setEditable(false);
            textInfo.setText(resourceBundle.getString("Please enter the properties you would like to add.The properties which have been added are reflected in the table shown below:-"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+textInfo,ex); 
          }
		button_Delete.setPreferredSize(new Dimension(button_Delete.getPreferredSize().width+10,button_Delete.getPreferredSize().height+0));
		button_Modify.setPreferredSize(new Dimension(button_Modify.getPreferredSize().width+6,button_Modify.getPreferredSize().height+0));
		button_Add.setPreferredSize(new Dimension(button_Add.getPreferredSize().width+26,button_Add.getPreferredSize().height+0));

  //<End_setUpProperties>
  		textInfo.setBackground(getBackground());
		cmb_DataType.setMaximumRowCount(9);
        cmb_DataType.addItem("boolean");	
        cmb_DataType.addItem("int");
        cmb_DataType.addItem("long");
        cmb_DataType.addItem("float");
        cmb_DataType.addItem("String");
		cmb_DataType.addItem("double");
		cmb_DataType.addItem("Date");
		cmb_DataType.addItem("Time");
		cmb_DataType.addItem("Timestamp");

        cmb_Access.addItem("public");
        cmb_Access.addItem("protected");
        cmb_Access.addItem("private");
        cmb_Access.addItem("package");
        button_Add.setMnemonic(KeyEvent.VK_A);
        button_Delete.setMnemonic(KeyEvent.VK_D);
        button_Modify.setMnemonic(KeyEvent.VK_M);
		
		text_PropName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				if((ke.getKeyChar()>='a' && ke.getKeyChar()<='z') || (ke.getKeyChar()>='A' && ke.getKeyChar()<='Z') || (ke.getKeyChar()==ke.VK_BACK_SPACE) || (ke.getKeyChar()>='0' && ke.getKeyChar()<='9') || ke.getKeyChar()=='_') {
					//Do Nothing
				}
				else {
					ke.consume();
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});


		
    } 

	
	

	
    public void init()
  { 
        if(false) {
            //<Begin_init>
        if (initialized == true) return; 
        //setPreferredSize(new Dimension(getPreferredSize().width+787,getPreferredSize().height+585)); 
        //setSize(getPreferredSize()); 
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
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
      setUpMenus();
      setUpToolBar();
        

  //<End_init>
        }
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
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
        setUpMenus();
        setUpToolBar();

    } 
    public void setUpConnections()throws Exception
  { 
        //<Begin_setUpConnections>

  //<End_setUpConnections>
    } 
    public void initVariables()throws Exception
  { 
        //<Begin_initVariables>
        Top= new javax.swing.JPanel();
        panel_NameValue= new javax.swing.JPanel();
        text_DefaultVal= new javax.swing.JTextField();
        text_PropName= new javax.swing.JTextField();
        label_PropName= new javax.swing.JLabel();
        label_DefaultValue= new javax.swing.JLabel();
        cmb_DataType= new javax.swing.JComboBox();
        label_Access= new javax.swing.JLabel();
        label_DataType= new javax.swing.JLabel();
        cmb_Access= new javax.swing.JComboBox();
        panel_AddModDel= new javax.swing.JPanel();
        button_Add= new javax.swing.JButton();
        button_Modify= new javax.swing.JButton();
        button_Delete= new javax.swing.JButton();
        tableScroller= new javax.swing.JScrollPane();
        customPropertyTable= new javax.swing.JTable();
        textInfo= new javax.swing.JTextArea();

  //<End_initVariables>
        Object tableHdr[]={ resourceBundle.getString("Serial Number"),resourceBundle.getString("DataType"),resourceBundle.getString("Property Name"),resourceBundle.getString("Default Value"),resourceBundle.getString("AccessModifiers")};
        cptm=new CustomPropTableModel(tableHdr,0);
        customPropertyTable.setModel(cptm);
    } 
    public void setUpToolBar()
  { 
        //<Begin_setUpToolBar>

  //<End_setUpToolBar>
    } 
    public void setUpGUI(Container container)throws Exception
  { 

        //<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new GridBagLayout());
	inset = new Insets(5,5,5,5);
	setConstraints(0,3,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(panel_NameValue,cons);
	panel_NameValue.setLayout(new GridBagLayout());
	inset = new Insets(10,10,10,10);
	setConstraints(1,2,3,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
	panel_NameValue.add(text_DefaultVal,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(1,0,3,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
	panel_NameValue.add(text_PropName,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panel_NameValue.add(label_PropName,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panel_NameValue.add(label_DefaultValue,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(3,1,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
	panel_NameValue.add(cmb_DataType,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	panel_NameValue.add(label_Access,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(2,1,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
	panel_NameValue.add(label_DataType,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(1,1,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
	panel_NameValue.add(cmb_Access,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(0,3,0,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panel_NameValue.add(panel_AddModDel,cons);
	panel_AddModDel.setLayout(new FlowLayout(2,15,5));
	panel_AddModDel.add(button_Add);
	panel_AddModDel.add(button_Modify);
	panel_AddModDel.add(button_Delete);
	inset = new Insets(5,5,5,5);
	setConstraints(0,1,0,2,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
	Top.add(tableScroller,cons);
	tableScroller.getViewport().add(customPropertyTable);
	inset = new Insets(5,5,5,5);
	setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(textInfo,cons);
	
  //<End_setUpGUI_Container>

        // Added CustomCode	
        button_Add.addActionListener(this);
        button_Modify.addActionListener(this);
        button_Delete.addActionListener(this);
        customPropertyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableSelModel=customPropertyTable.getSelectionModel();
        // This is done to populate the textfields and combo Box to reflect the changes that
        // made by selecting a table in the screen.

        tableSelModel.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent lse) {
                    if(lse.getFirstIndex()==-1) {
                        return;
                    }	
                    int row;
                    if((row=customPropertyTable.getSelectedRow())==-1) {
                        return;
                    }
                    String datatype=(String)customPropertyTable.getValueAt(row,1);
                    String accMod=(String)customPropertyTable.getValueAt(row,4);
                    //String fieldMod=(String)customPropertyTable.getValueAt(row,5);
                    text_PropName.setText((String)customPropertyTable.getValueAt(row,2));
                    for(int i=0;i<cmb_DataType.getItemCount();i++) {
                        if(datatype.trim().equals(((String)cmb_DataType.getItemAt(i)).trim())) {
                            cmb_DataType.setSelectedIndex(i);
                        }
                    }	
                    text_DefaultVal.setText((String)customPropertyTable.getValueAt(row,3));
                    for(int i=0;i<cmb_Access.getItemCount();i++) {
                        if(accMod.trim().equals(((String)cmb_Access.getItemAt(i)).trim())) {
                            cmb_Access.setSelectedIndex(i);
                        }
                    }
                    /*if(fieldMod.trim().length()!=0) {
                        StringTokenizer stk=new StringTokenizer(fieldMod);
                        while(stk.hasMoreTokens()) {
                            if(stk.nextToken().equals("static")) {
                                chb_static.setSelected(true);
                                chb_final.setSelected(true);
                            }
                        }
                    }*/
                }
            });
    }

    public void setUpMenus()
  { 

        //<Begin_setUpMenus>

  //<End_setUpMenus>
    } 
    public void stop()
  { 

        //<Begin_stop>

  //<End_stop>
    } 
    public void start()
  { 

        //<Begin_start>

  //<End_start>
    } 

    public void showStatus(String message)
  {
        //<Begin_showStatus_String>
     System.out.println(resourceBundle.getString("Internal Error :")+ message);
     //<End_showStatus_String>
    }
    
    public void showStatus(String message,Exception ex)
  {
        //<Begin_showStatus_String_Exception>
     System.out.println(resourceBundle.getString("Internal Error :")+ message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
    }
	
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==button_Add) {
            button_Add_Modify_Clicked(ae,true);
        }
        else if(ae.getSource()==button_Delete) {
            button_Delete_Clicked(ae);
        }
        else if(ae.getSource()==button_Modify) {
            button_Add_Modify_Clicked(ae,false);
        }
    }

    public void addTransverseContainer(TransverseContainer tCon) {
        transCon=tCon;
    }

    private void populateMOPropertiesFromModel() {
        moProperties=new Properties();
        Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
        Element rootNode=(Element)doc.getDocumentElement();
        NodeList propNodeList=rootNode.getChildNodes();
        Element tempElement=null;
        for(int i=0;i<propNodeList.getLength();i++) {
			Node tempNode = propNodeList.item(i);
            if(tempNode.getNodeType() != org.w3c.dom.Node.ELEMENT_NODE)
	        {//Maybe Text Node or junk.
				continue;
			}
            tempElement = (Element)tempNode;
            moProperties.put(tempElement.getAttribute("NAME"),tempElement.getAttribute("DATATYPE"));
        }
    }

    public void button_Ok_Clicked(ActionEvent ae) {
        // Add these variables in the table into the XML - Element.
        // Get the Root Node of the ManagedObject from TransverseContainer.
        
        // Assume that already an XmlDocument is created and stored and in
        // TransverseContainer.  Get the document from TransverseContainer
        // and add these values to that root Node.

        Document doc;
        Element root;
        doc=(Document)transCon.getTransverseComponent("XMLMODEL");
        root=(Element)doc.getDocumentElement();
        NamedNodeMap nnm=root.getAttributes();
        Node attr;
		
        for(int i=0;i<customPropertyTable.getRowCount();i++) {
            Element node=(Element)doc.createElement("MOPROPERTY");
            node.setAttribute("NAME",(String)customPropertyTable.getValueAt(i,2));
            node.setAttribute("DATATYPE",(String)customPropertyTable.getValueAt(i,1));
            if( ((String)customPropertyTable.getValueAt(i,3)).trim().length()!=0) {
                node.setAttribute("DEFAULTVALUE",(String)customPropertyTable.getValueAt(i,3));
            }
            root.appendChild(node);
        }
        /*try {
            doc.write(System.out);
            transCon.addTransverseComponent("XMLMODEL",doc);
             ((Document)transCon.getTransverseComponent("XMLMODEL")).write(System.out);
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }*/
        ((JFrame)getParentContainer((JComponent)ae.getSource())).dispose();
    }
    
    public void button_Cancel_Clicked(ActionEvent ae) {
        // Do nothing.
        // just make the window invisible.
        ((JFrame)getParentContainer((JComponent)ae.getSource())).dispose();
    }
    
    public Container getParentContainer(Container con) {
        for(;!(con instanceof Window);con=con.getParent());
        return (Window)con;
    }
    
    private void button_Add_Modify_Clicked(ActionEvent ae,boolean isAdd) {
        String fieldModifier="   ";
        populateMOPropertiesFromModel();
        if(text_PropName.getText().trim().length()==0) {
            JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter PropertyName"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
            return;
        }
        // See if the given name occurs in the Table or before.
		if(isAdd) {
	        for(int i=0;i<customPropertyTable.getRowCount();i++) {
    	        if(((String)customPropertyTable.getValueAt(i,2)).equals(text_PropName.getText().trim())) {
	        JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("PropertyName name {0} is already found !!!"), new String[]{text_PropName.getText().trim()}),
		resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
            	    return;
		        }
        	}
			if(Character.isUpperCase(text_PropName.getText().trim().charAt(0))){
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter propertynames starting in lowercase Characters"),resourceBundle.getString("Message"),JOptionPane.OK_OPTION);
				return;
			}
			if(!Character.isLetter(text_PropName.getText().trim().charAt(0))) {
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter propertynames starting in lowercase Characters"),resourceBundle.getString("Message"),JOptionPane.OK_OPTION);
				return;
			}
		}
        if(text_DefaultVal.getText().trim().length()!=0) {
            try {
				
                switch(cmb_DataType.getSelectedIndex()) {
                case 0:
					// Changed code from equalsIgnoreCase to equals
                    if( !text_DefaultVal.getText().trim().equals("true") && 
                       !text_DefaultVal.getText().trim().equals("false")) {
                        //Show Error Message
                        JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter true / false"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
                        return;
                    }	
                    break;
                case 1:
                    try {
                        Integer.parseInt(text_DefaultVal.getText().trim());
                    }catch(Exception e) {
                        throw new NumberFormatException(resourceBundle.getString("Please enter an integer"));
                    }
                    break;
                case 2:
                    try {
                        Long.parseLong(text_DefaultVal.getText().trim());
                    }catch(Exception e) {
                        throw new NumberFormatException(resourceBundle.getString("Please enter a long value"));
                    }	
                    break;
                case 3:
                    try {
                        Float.parseFloat(text_DefaultVal.getText().trim());
                    }
                    catch(Exception e) {
                        throw new NumberFormatException(resourceBundle.getString("Please enter a float Value"));
                    }
                    break;
                case 5:
                    try {
                        Double.parseDouble(text_DefaultVal.getText().trim());
                    }
                    catch(Exception e) {
                        throw new NumberFormatException(resourceBundle.getString("Please enter a double Value"));
                    }
                    break;
				case 6:
					try {
						if(!text_DefaultVal.getText().trim().equals("null")) {
							java.sql.Date.valueOf(text_DefaultVal.getText().trim());
						}	
					}
					catch(Exception e) {
						throw new NumberFormatException(resourceBundle.getString("Please enter a valid Date"));
					}
					break;
				case 7:
					try {
						if(!text_DefaultVal.getText().trim().equals("null")) {
						java.sql.Time.valueOf(text_DefaultVal.getText().trim());
						}
					}
					catch(Exception e) {
						throw new NumberFormatException(resourceBundle.getString("Please enter a valid Time"));
					}
					break;
				case 8:
					try {
						if(!text_DefaultVal.getText().trim().equals("null")) {
						java.sql.Timestamp.valueOf(text_DefaultVal.getText().trim());
						}
					}
					catch(Exception e) {
						throw new NumberFormatException(resourceBundle.getString("Please enter a valid Timestamp"));
					}
					break;

                }
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage(),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
                return;
            }	
        }

        /*if(chb_static.isSelected()) {
            fieldModifier=" static ";
        }
        if(chb_final.isSelected()) {
            fieldModifier=fieldModifier + " final ";
        }*/

        if(isAdd) {			
            Object[] data= { String.valueOf(customPropertyTable.getRowCount()+1),cmb_DataType.getSelectedItem(),text_PropName.getText().trim() , (text_DefaultVal.getText().trim().length()==0)?"   ":text_DefaultVal.getText().trim(),cmb_Access.getSelectedItem()};
            ((DefaultTableModel)customPropertyTable.getModel()).addRow(data);		
        }
        else {
            button_Modify_Clicked(ae);
        }	
    }

    private void button_Delete_Clicked(ActionEvent ae) {
        if(customPropertyTable.getSelectedRow() !=-1) {
			int selectedRow=customPropertyTable.getSelectedRow();
            ((DefaultTableModel)customPropertyTable.getModel()).removeRow(customPropertyTable.getSelectedRow());
			for(int i=selectedRow;i<((DefaultTableModel)customPropertyTable.getModel()).getRowCount();i++)
			{
				((DefaultTableModel)customPropertyTable.getModel()).setValueAt(new Integer(i+1),i,0);
			}
			
            cmb_DataType.setSelectedIndex(0);
            text_PropName.setText("");
            text_DefaultVal.setText("");
        }
    }
		
    private void button_Modify_Clicked(ActionEvent ae) {
        // This function will be called from ActionHandler of Add button since validation
        // can be done using that code.
        if(customPropertyTable.getSelectedRow()==-1) {
            JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select a row to Modify"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
            return;
        }
        for(int i=0;i<customPropertyTable.getRowCount();i++) 
		{
			if(customPropertyTable.getSelectedRow() == i )
			{
				continue;
			}
   	        if(((String)customPropertyTable.getValueAt(i,2)).equals(text_PropName.getText().trim()))
			{
			   JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(
		resourceBundle.getString("PropertyName {0} is already found "), new String[]{text_PropName.getText().trim()}),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);

           	    return;
			}
       	}
        // move the following code to Table Mouse Clicked
        int row=customPropertyTable.getSelectedRow();
        customPropertyTable.setValueAt(text_PropName.getText().trim(),row,2);
        customPropertyTable.setValueAt(cmb_DataType.getSelectedItem(),row,1);
        customPropertyTable.setValueAt(text_DefaultVal.getText().trim(),row,3);
        customPropertyTable.setValueAt(cmb_Access.getSelectedItem(),row,4);
    }
	
    /*
    public static void main(String[] args) {
        addCustProps acp=new addCustProps();
        JFrame frame=new JFrame();
        frame.getContentPane().add(acp,BorderLayout.CENTER);
        TransverseContainer tCon=new TransverseContainer();
        acp.addTransverseContainer(tCon);
        frame.setSize(600,400);
        //frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);	
        frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });
    }*/

 
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
	
    public void loadScreenData() {
        // This method is called by TransversePanel to load Screen 
        // data.
        for(int i=customPropertyTable.getRowCount()-1;i>=0;i--) {
            ((DefaultTableModel)customPropertyTable.getModel()).removeRow(i);
        }
        Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
        Element rootNode=doc.getDocumentElement();
        NodeList moProp=rootNode.getElementsByTagName("MOPROPERTY");
        for(int i=0;i<moProp.getLength();i++) {
            Element node=(Element)moProp.item(i);
            Object[] data= { new Integer(i+1),
                        ((node.getAttribute("dataType")==null)? " ":node.getAttribute("dataType")) ,
                        ((node.getAttribute("name")==null) ? "  ":node.getAttribute("name")),
                        ((node.getAttribute("defaultValue")==null) ? "  ":node.getAttribute("defaultValue")),
                        ((node.getAttribute("accessModifier")==null) ? "  ":node.getAttribute("accessModifier")),
                        //((node.getAttribute("fieldModifier")==null) ? "  ":node.getAttribute("fieldModifier")) 
			};
            ((DefaultTableModel)customPropertyTable.getModel()).addRow(data);
        }
    }

    public int nextActionPerformed(String str) {
		/*if(customPropertyTable.getRowCount()==0){
		//	JOptionPane.showMessageDialog(null,"Please add propertynames to define a ManagedObject","Message",JOptionPane.OK_OPTION);
			return 1;
		}*/
        Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
        Element rootNode=doc.getDocumentElement();
        NodeList moProp=rootNode.getElementsByTagName("MOPROPERTY");
        for(int i=moProp.getLength()-1;i>=0;i--) {
            Node node=moProp.item(i);
            
            rootNode.removeChild(node);
        }
        for(int i=0;i<customPropertyTable.getRowCount();i++) {
            Element tempNode=doc.createElement("MOPROPERTY");
            tempNode.setAttribute("dataType",(String)customPropertyTable.getValueAt(i,1));
            tempNode.setAttribute("name",(String)customPropertyTable.getValueAt(i,2));
            if(((String)customPropertyTable.getValueAt(i,3)).trim().length()!=0) {
                tempNode.setAttribute("defaultValue",(String)customPropertyTable.getValueAt(i,3));
            }
			
            if(((String)customPropertyTable.getValueAt(i,4)).trim().length()!=0) {
                tempNode.setAttribute("accessModifier",(String)customPropertyTable.getValueAt(i,4));
            }
            rootNode.appendChild(tempNode);
        }
        transCon.addTransverseComponent("XMLMODEL",doc);
        NodeList nodeList=doc.getElementsByTagName("CLASS");
        Element clsInfo=(Element)nodeList.item(0);
        if(clsInfo.getAttribute("extends")==null) {
            clsInfo.setAttribute("extends","com.adventnet.nms.topodb.ManagedObject");
        }
        return 1;	
    }
    
    public int previousActionPerformed(String str) {
		//return 1;
		 return	nextActionPerformed(str);
    }

    public boolean finishActionPerformed() {
        return false;
    }
	
    private Window getParentContainer() {
        Container con=getParent();
        for(;!(con instanceof Window);con=con.getParent());
        return (Window)con;
    }

    public void cancelActionPerformed(String str) {
        if(str.trim().equals("Screen4")) {
			int returnVal=JOptionPane.showConfirmDialog(null,resourceBundle.getString("MO Generation is not complete. Do you want to cancel ?"),resourceBundle.getString("Confirm"),JOptionPane.YES_NO_OPTION);
			if(returnVal==JOptionPane.YES_OPTION) {
				getParentContainer().dispose();
			}
        }
    }

    public void closeActionPerformed() {
    }
}



