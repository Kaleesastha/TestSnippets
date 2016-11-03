//$Id: NetconfPanel.java,v 1.1.4.1 2012/05/29 06:26:56 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$3
//<End_Version>
package com.adventnet.nms.config;



import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.management.config.netconf.*;


public class NetconfPanel extends JPanel
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";//No I18N
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;

	private boolean isTemplate = false;


	public NetconfPanel(ConfigPanel panel, boolean template)
	{
		isTemplate = template;
		configPanel = panel;

		applet = configPanel.applet;

		init();
		configInit();
	}

	private void configInit()
	{
		table.setDefaultRenderer(Object.class, new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);

		String[] headers = new String[NetconfAttributeConstants.ATTRIBUTE_KEYS.length];
		for (int i=0;i<NetconfAttributeConstants.ATTRIBUTE_KEYS.length;	i++)
		{
			headers[i] = resourceBundle.getString(NetconfAttributeConstants.ATTRIBUTE_KEYS[i]);
		}
		tableModel.setDataVector( new Object[0][0], headers);

	}

	public NetconfPanel()
  {
		//<Begin_NetconfPanel>
    this.init();

    //<End_NetconfPanel>
	}

	public NetconfPanel(java.applet.Applet applet)
  {
		//<Begin_NetconfPanel_java.applet.Applet>
    this.applet = applet;
    this.init();

    //<End_NetconfPanel_java.applet.Applet>
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
	public void setUpProperties()
  {

		//<Begin_setUpProperties>

          try
          {
            table.setModel(tableModel);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); //No I18N
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

          try
          {
            JButton1.setLabel(resourceBundle.getString("Add"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); //No I18N
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JButton2.setLabel(resourceBundle.getString("Modify"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); //No I18N
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            JButton3.setLabel(resourceBundle.getString("Delete"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); //No I18N
          }

//<UserCode_Begin_Bean_JButton3>

//<UserCode_End_Bean_JButton3>

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JTextArea1.setOpaque(false);
            JTextArea1.setEditable(false);
            JTextArea1.setText(resourceBundle.getString("Use the buttons given below the table to configure the Attributes for this task."));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); //No I18N
          }

//<UserCode_Begin_Bean_JTextArea1>

//<UserCode_End_Bean_JTextArea1>
		JButton3.setPreferredSize(new Dimension(JButton3.getPreferredSize().width+1,JButton3.getPreferredSize().height+1));
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+0,JButton2.getPreferredSize().height+1));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+15,JButton1.getPreferredSize().height+1));


          //<End_setUpProperties>
	}
	public void init()
  {

		//<Begin_init>
	if(getParameter("RESOURCE_PROPERTIES" ) != null)//No I18N
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");//No I18N
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);//No I18N
        if (initialized) return;
        setPreferredSize(new Dimension(getPreferredSize().width+549,getPreferredSize().height+416));
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
          showStatus(resourceBundle.getString("Error in init method"),ex);//No I18N
        }
        // let us set the initialized variable to true so
        // we dont initialize again even if init is called
        initialized = true;


         //<End_init>

	}
	public void setUpConnections()
  {

		//<Begin_setUpConnections>

      JButton3_JButton3_conn JButton3_JButton3_conn1 =  new JButton3_JButton3_conn();
      JButton3.addActionListener(JButton3_JButton3_conn1);
      JButton1_JButton1_conn JButton1_JButton1_conn1 =  new JButton1_JButton1_conn();
      JButton1.addActionListener(JButton1_JButton1_conn1);
      JButton2_JButton2_conn JButton2_JButton2_conn1 =  new JButton2_JButton2_conn();
      JButton2.addActionListener(JButton2_JButton2_conn1);

      //<End_setUpConnections>
	}
	public void initVariables()
  {

		//<Begin_initVariables>
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel2= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JTextArea1= new javax.swing.JTextArea();
        tableModel= new javax.swing.table.DefaultTableModel();


        //<End_initVariables>
	}

	public void setUpGUI(Container container)
  {

		//<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(JButton1);
JPanel2.add(JButton2);
JPanel2.add(JButton3);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel3.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel3.add(JTextArea1,cons);


//<End_setUpGUI_Container>
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
            if (input.equals("HOST")) value = "localhost"; //No I18N
            if (input.equals("PORT")) value = "161"; //No I18N
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; //No I18N
            }
        return value;


           //<End_getParameter_String>
	}





	public void showStatus(String message)
  {
		//<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);//No I18N
     //<End_showStatus_String>
	}
	public void showStatus(String message,Exception ex)
  {
		//<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);//No I18N
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
	}

	public void addbuttonaction()
	{
		if(isTemplate)
		{
			NetconfTemplateAdd ta = new NetconfTemplateAdd(configPanel,this);
			ta.setVisible(true);
		}
		else
		{
			NetconfAdd ta = new NetconfAdd(configPanel,this);
			ta.setVisible(true);
		}
	}

	public void addRow(Vector rowData)
	{
		tableModel.addRow(rowData);
	}

	public void updateRow(Vector rowData)
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			tableModel.removeRow(row);
			tableModel.insertRow(row, rowData);
		}
	}




	public void modifyButtonActionPerformed()
	{
		int row = table.getSelectedRow();

		if(row != -1)
		{
			Vector rowData = new Vector();

			for(int i=0; i<tableModel.getColumnCount(); i++)
			{
				rowData.addElement(tableModel.getValueAt(row, i));
			}

			if(isTemplate)
			{
				NetconfTemplateAdd templateAdd = new NetconfTemplateAdd(configPanel, this, rowData);
				templateAdd.setVisible(true);
			}
			else
			{
				NetconfAdd add = new NetconfAdd(configPanel, this, rowData);
				add.setVisible(true);
			}
		}
	}




	public void deleteButtonActionPerformed()
	{
		int [] selectedRows = table.getSelectedRows();
		int length = selectedRows.length;
		if(length == 0)
		{
			return;
		}
		//JOptionPane jp = new JOptionPane();
		//int option =  jp.showConfirmDialog(null, resourceBundle.getString("Are you sure about deleting the selected item(s)"),null,jp.YES_NO_OPTION);//No I18N
		if (configPanel.configClientUtils.getConfirmation(this))
		{
			for(int j=length-1;j >=0;j--)
			{
				tableModel.removeRow(selectedRows[j]);
			}
		}
	}

	public Vector getAllValues()
	{
		return tableModel.getDataVector();
	}

	public void setValues(Vector data)
	{
		Vector columns = new Vector();
		for (int i=0;i<NetconfAttributeConstants.ATTRIBUTE_KEYS.length;	i++)
		{
			columns.addElement(resourceBundle.getString(NetconfAttributeConstants.ATTRIBUTE_KEYS[i]));
		}

		tableModel.setDataVector(data, columns);
	}


  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties>


  //<End_setProperties_java.util.Properties>
  }




//<Begin__class_JButton1_JButton1_conn>

 class JButton1_JButton1_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          addbuttonaction();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn>
 }//<End__class_JButton1_JButton1_conn>
//<Begin__class_JButton2_JButton2_conn>

 class JButton2_JButton2_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          modifyButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn>
 }//<End__class_JButton2_JButton2_conn>




//<Begin__class_JButton3_JButton3_conn>

 class JButton3_JButton3_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          deleteButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton3_JButton3_conn>
 }//<End__class_JButton3_JButton3_conn>
}

















