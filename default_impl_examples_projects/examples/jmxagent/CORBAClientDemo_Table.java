/* $Id: CORBAClientDemo_Table.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

package com.adventnet.nms.example.jmxagent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;
import java.util.*;
import java.lang.reflect.*;
import javax.management.*;
import com.adventnet.adaptors.clients.corba.*;
import javax.jmx.openmbean.*;
import javax.management.modelmbean.*;
/*
 * This is an example program to access NMS CORBA-Agent. This program demonstrates the GET and SET operation
 * on tables by invoking the corresponding Operations. In this example, we provide an UI for making 
 * a get and set on the Proxy Table. Here, the Proxy Table values like hostName, portNumber, the oid to be 
 * queried are set by invoking the setProxyTable Operation. The value of the Result attribute is obtained 
 * by invoking the getProxyTable Operation and is displayed in the UI
 */

public class CORBAClientDemo_Table extends JFrame implements ActionListener
{
    javax.swing.JPanel  Top = null;
    javax.swing.JPanel  JPanel1 = null;
    javax.swing.JScrollPane JScrollPane1 = null;
    javax.swing.JTable  JTable1 = null;
    javax.swing.JPanel  JPanel2 = null;
    javax.swing.JPanel  JPanel3 = null;
    javax.swing.JButton  addBut = null;
    javax.swing.JButton  refreshBut = null;
    javax.swing.JButton  submitBut = null;
    javax.swing.JButton  exitBut = null;
    javax.swing.JButton  connectBut = null;
    javax.swing.JTextField hostText = null;
    javax.swing.JTextField portText = null;
    javax.swing.JLabel status = null;
    DefaultTableModel tableModel = null;

    private static String hostName = "localhost";
    private static String portNo = "1099";

    //CORBA related 
    MBeanServer server = null;
    ObjectName objectName = null;
    CorbaClient corbaClient = null;
    MBeanOperationInfo[] opeInfo = null;
    MBeanAttributeInfo[] attInfo = null;

    String[] headers = {"SerialNumber","HostName","DevicePort","RequestOid",
			"Community","Service","Result" };
    String[][] val = { {"","","","","","",""} };

    Vector indexVec = new Vector();
    Vector indexVec1 = new Vector();

    /*
     *  The constructor for the program.The functions for initializing the variables and setting
     *  the UI are invoked from here.
     */

    CORBAClientDemo_Table()
    {
	this.setSize(getPreferredSize().width+550,getPreferredSize().height+325); 
	setTitle("CORBAClientDemo_Table");

	server = MBeanServerFactory.createMBeanServer();
	corbaClient = new CorbaClient();

	Container container = getContentPane();
	container.setLayout(new BorderLayout()); 
	initVariables(); 
	setUpGUI(container); 
    } 

    void initVariables()
    { 
	Top= new javax.swing.JPanel();
	JPanel1= new javax.swing.JPanel();

	JTable1= new javax.swing.JTable();
	tableModel = new DefaultTableModel(val,headers);
	JTable1.setModel(tableModel);
	JScrollPane1 = new JScrollPane(JTable1);     

	JPanel3 = new javax.swing.JPanel();
	JPanel2= new javax.swing.JPanel();
	addBut= new javax.swing.JButton("Add");
	addBut.addActionListener(this);
	refreshBut= new javax.swing.JButton("Refresh");
	refreshBut.addActionListener(this);
	submitBut= new javax.swing.JButton("Submit");
	submitBut.addActionListener(this);
	exitBut= new javax.swing.JButton("Exit");
	exitBut.addActionListener(this);
	connectBut= new javax.swing.JButton("Connect");
	connectBut.addActionListener(this);
	hostText = new JTextField(15);
	portText = new JTextField(10);
	hostText.setText(hostName);
	portText.setText(portNo);
	status = new JLabel("Press connect to establish link");
    } 

    void setUpGUI(Container container)
    { 
	container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new BorderLayout(5,5));

	Top.add(JScrollPane1,BorderLayout.CENTER);

	JPanel1.setLayout(new GridLayout(0,3));
	JPanel1.add(new JLabel("HostName:"));
	JPanel1.add(hostText);
	JPanel1.add(connectBut);
	JPanel1.add(new JLabel("PortNo:"));	
	JPanel1.add(portText);
	Top.add(JPanel1,BorderLayout.NORTH);

	JPanel3.setLayout(new BorderLayout(2,0));
	JPanel2.setLayout(new FlowLayout(1,5,5));
	JPanel2.add(addBut);
	JPanel2.add(submitBut);
	JPanel2.add(refreshBut);
	JPanel2.add(exitBut);
	JPanel3.add(JPanel2,BorderLayout.NORTH);
	JPanel3.add(status,BorderLayout.SOUTH);
	Top.add(JPanel3,BorderLayout.SOUTH);
    } 
    
    int newlyAdded = 0;

    public void actionPerformed(ActionEvent ae)
    {
	if(ae.getSource() == connectBut)
	{
	    setUpConnections();
	    removeAllRows();
	    fillValues();
	}
	else if(ae.getSource() == refreshBut)
	{
	    if(!corbaClient.isConnectedWithAdaptor().booleanValue())
	    {
		status.setText("Connection failed");
		return;
	    }
	    removeAllRows();
	    fillValues();	    
	    status.setText("Refreshed!");
	}
	else if(ae.getSource() == addBut)
	{
	    if(!corbaClient.isConnectedWithAdaptor().booleanValue())
	    {
		status.setText("Connection failed");
		return;
	    }
	    newlyAdded = (findNextIndex() + 1); 
	    Object[] newRow = new Object[7];
	    newRow[0] = newlyAdded + "";
	    newRow[1] = "";
	    newRow[2] = "161";
	    newRow[3] = "";
	    newRow[4] = "public";
	    newRow[5] = "0";
	    tableModel.addRow(newRow);

	    JTable1.setRowSelectionInterval(tableModel.getRowCount()-1,tableModel.getRowCount()-1);

	    indexVec1.addElement(new Integer(newRow[0].toString()));
	    status.setText("Row added");
	    addBut.setEnabled(false);
	}
	else if(ae.getSource() == submitBut)
	{
	    if(!corbaClient.isConnectedWithAdaptor().booleanValue())
	    {
		status.setText("Connection failed");
		return;
	    }
	    int selectedIndex = JTable1.getSelectedRow();

	    if(selectedIndex == -1)
	    {
		JOptionPane.showMessageDialog(this,"Select any row");
		return;
	    }

	    Object[] given = new Object[7];
	    given[0] = new Integer(tableModel.getValueAt(selectedIndex,0).toString());
	    given[1] = tableModel.getValueAt(selectedIndex,1);
	    given[2] = new Integer(tableModel.getValueAt(selectedIndex,2).toString());
	    given[3] = tableModel.getValueAt(selectedIndex,3);
	    given[4] = tableModel.getValueAt(selectedIndex,4);
	    given[5] = new Integer(tableModel.getValueAt(selectedIndex,5).toString());
	    if( ((String)given[1]).trim().equals("") || ((String)given[3]).trim().equals("") || given[2].toString().trim().equals("") )
	    {
		JOptionPane.showMessageDialog(this,"Fill the Values");
		return;
	    }

	    given[6] = " ";

	    int index = ((Integer)given[0]).intValue();

	    if(index == newlyAdded) addBut.setEnabled(true);

	    submitRow(given);
	    tableModel.setValueAt(getResultFor(index),selectedIndex,6);

	    JTable1.setRowSelectionInterval(selectedIndex,selectedIndex);
	}
	else if(ae.getSource() == exitBut)
	{
	    System.exit(0);
	}
    }
    /*
     * The connection with the Corba Adaptor is obtained.The attributes and operations of the ProxyTable
     * are obtained by using the objectName.
     */
    void setUpConnections()
    { 
 	try
	{
	    if(corbaClient.isConnectedWithAdaptor().booleanValue())
		corbaClient.disconnect();
	    corbaClient.connect(hostText.getText(),Integer.parseInt(portText.getText()),null);

	    objectName = new ObjectName("AdventNet_WebNMS_MIB_JMX:type=ProxyTable");
	    
	    MBeanInfo mBeanInfo = corbaClient.getMBeanInfo(objectName);
	    
	    opeInfo = mBeanInfo.getOperations();
	    attInfo = mBeanInfo.getAttributes();
	    status.setText("connection Established");
	}
	catch(Exception e)
	{
	    System.out.println("Exception when connect with host " + e);
	}
    }

    void removeAllRows()
    {
	int rowCount = tableModel.getRowCount();
	for (int i = rowCount-1; i >= 0 ; i--)
	    tableModel.removeRow(i);
	indexVec.removeAllElements();
	indexVec1.removeAllElements();
    }

    void fillValues()
    {
	Vector allValues = getAllValues();
	
	if(allValues != null)
	{
	    for(int i = 0; i < allValues.size(); i++)
	    {
		Object[] values = (Object[])allValues.elementAt(i);
		tableModel.addRow(values);
		indexVec.addElement(values[0]);
		indexVec1.addElement(values[0]);
	    }	 
	}
    }

    /*
     * The operation getProxyTable is invoked on the Proxy Table in order to obtain the values present
     * in the table.The operation returns a TabularData object.The values from the TabularData object are
     * read and are displayed in the UI table.
     */
    
    Vector getAllValues()
    {
 	try
	{
	    Vector allValues = new Vector();
	    TabularData td = null;
	    for(int i = 0; i < opeInfo.length; i++)
	    {
		if( opeInfo[i].getName().equals("getProxyTable"))
		{
		    td = (TabularData)corbaClient.invoke(objectName,opeInfo[i].getName(),new Object[]{},new String[]{});
		    break;
		}
	    }

	    Enumeration en = td.enumerate();
	    while(en.hasMoreElements())
	    {
		Object[] index = (Object[])en.nextElement();

		CompositeData c = td.getRow(index);
		
		Object[] values = new Object[7];
		for(int i = 0; i < 7;i++)
		    values[i] = c.getDataItem(headers[i]);

		allValues.addElement(values);
	    }

	    return allValues;
	}
	catch(Exception e)
	{
	    System.out.println("Exception when getting all values " + e);

	    return null;
	}
    }
    /*
     * The setProxyTable operation of the ProxyTable is invoked here, whenever the user inputs a new
     * row or modifies an already existing one.
     */    
    void submitRow(Object[] given)
    {
	try
	{
	    String[] indexNames = new String[]{"SerialNumber"};
	    int index = ((Integer)given[0]).intValue();

	    CompositeData[] comps = new CompositeData[1];
	    comps[0] = new CompositeData(null,headers,given);
		
	    if(isNewRow(index))
	    {
		comps[0].setOperationType(CompositeData.CREATED);
		indexVec.addElement(new Integer(index));
	    }
	    else
	    {
		comps[0].setOperationType(CompositeData.MODIFIED);
		for(int i = 0; i < 6; i++)
		    comps[0].setModified(headers[i],true);
	    }

	    OpenMBeanParameterInfo[] parameterInfo = new OpenMBeanParameterInfo[headers.length];
	    String returnType = null;
	    String instrClassName = "com.adventnet.nms.jmxagent.ProxyEntry";
	    try
	    {
		Class entryClassName = Class.forName(instrClassName);
		    
		for(int i = 0; i < headers.length; i++)
		{
		    String methodName = "get" + headers[i];
		    Method method = entryClassName.getMethod(methodName,null);
		    returnType = method.getReturnType().getName();
		    parameterInfo[i] = new OpenMBeanParameterInfo(headers[i],
								  returnType,
								  null,null,null);
		}
	    }
	    catch(Exception e)
	    {
		System.out.println(" Exception when finding the return types " + e);
	    }

	    TabularParameterInfo tinfo = new TabularParameterInfo(null,null,null,null,null,
								  parameterInfo,
								  indexNames);
	    TabularData td =  new TabularData(tinfo, comps);

	    Object value[] = {td};
	    String type[] = {"javax.jmx.openmbean.TabularData"}; 
	    for(int i = 0; i < opeInfo.length; i++)
	    {
		if( opeInfo[i].getImpact() == opeInfo[i].ACTION)
		{
		    corbaClient.invoke(objectName,opeInfo[i].getName(),value,type);
		}
	    }
	    status.setText("Submission successful!");
	}
	catch(Exception e)
	{
	    System.out.println("Exception when submit the row  " + e);
	}
    }

    /*
     * After the values are submitted to the agent,the result is obtained by invoking the getProxyTable operation
     * for that row.The value of the "Result" attribute is obtained and displayed in the UI.
     */
    String getResultFor(int index)
    {
 	try
	{
	    TabularData td = null;

	    for(int i = 0; i < opeInfo.length; i++)
	    {
		if( opeInfo[i].getName().equals("getProxyTable"))
		{
		    td = (TabularData)corbaClient.invoke(objectName,opeInfo[i].getName(),new Object[]{},new String[]{});
		}
	    }
	    
	    Object[] indexObj = {new Integer(index)};
	    CompositeData c = td.getRow(indexObj);
	    return (String)c.getDataItem("Result");
	}
	catch(Exception e)
	{
	    System.out.println("Exception when getting the value for given index " + e);
	    return "No data";
	}
    }

    int findNextIndex()
    {
	int max = 0;
	for(int i = 0; i < indexVec1.size(); i++)
	{
	    int temp = ((Integer)indexVec1.elementAt(i)).intValue();
	    if( temp > max)
		max = temp; 
	}
	return max;
    }

    boolean isNewRow(int index)
    {
	for(int i = 0; i < indexVec.size(); i++)
	{
	    if(((Integer)indexVec.elementAt(i)).intValue() == index)
		return false;
	}
	return true;
    }

    public static void main(String [] args)
    {
 	for (int i = 0 ; i < args.length; i+=2)
	{
	    if(args[i].trim().equals("-HOST"))
		hostName = args[i+1];
	    if(args[i].trim().equals("-PORT"))
		portNo = args[i+1];
	}

	CORBAClientDemo_Table frame = new CORBAClientDemo_Table();
	frame.setVisible(true);
	frame.addWindowListener(new WindowAdapter()
	    {
		public void windowClosing(WindowEvent evt)
		{
		    System.exit(0);
		}
	    });
    }
}





