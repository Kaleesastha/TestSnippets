
//package com.adventnet.nms.topodb;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.rmi.*;
import java.lang.reflect.*;
//import java.rmi.server.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.topodb.*;

public class MapAPITest implements ActionListener
{
    MapAPI topo = null;
    boolean setParameters;
    JFrame frame;
    JTextField t1;

    JPanel paneTop;
    JPanel pane;

    JPanel paneLeft;
    JLabel p1L0;
    JLabel p1L1;
    JLabel p1L2;
    JLabel p1L3;
    JLabel p1L4;

    JPanel paneRight;
    JComboBox p2Combo;
    JTextField p2t1;
    JTextField p2t2;
    JTextField p2t3;
    JTextField p2t4;

    JPanel paneBottom;
    JButton executeButton;
    JButton clearButton;

    JPanel paneConnect;
    JTextField hostName;
    JButton connectButton;
    JTextArea area;
    JScrollPane scroll;

    Vector methNameVect;
    Vector methVect;
    Method currentMethod;
    JLabel[] paneLeftArray = new JLabel[4];
    JTextField[] paneRightText = new JTextField[4];
    int currentMethodFieldCount = 0;
    StringBuffer strBuf = new StringBuffer();

    public MapAPITest (Vector methodNameVector, Vector methodVector) //throws java.rmi.RemoteException 
    {
        methNameVect = methodNameVector;
        methVect = methodVector;
        frame = new JFrame("API Test");
        frame.getContentPane().setLayout(new BorderLayout());

        paneTop = new JPanel();
        paneTop.setLayout(new GridLayout(0,2));

        paneLeft = new JPanel();
        paneLeft.setLayout(new GridLayout(5,0));
        p1L0 = new JLabel();
        p1L1 = new JLabel("Give first parameter");
        p1L2 = new JLabel("Give Second parameter");
        p1L3 = new JLabel("Give third parameter");
        p1L4 = new JLabel("Give fourth parameter");
        paneLeftArray[0] = p1L1;
        paneLeftArray[1] = p1L2;
        paneLeftArray[2] = p1L3;
        paneLeftArray[3] = p1L4;
        paneLeft.add(p1L0);
        paneLeft.add(p1L1);
        paneLeft.add(p1L2);
        paneLeft.add(p1L3);
        paneLeft.add(p1L4);
        paneTop.add(paneLeft);

        paneRight = new JPanel();
        paneRight.setLayout(new GridLayout(5,0));

        p2Combo = new JComboBox(methodNameVector);
        p2Combo.addActionListener(this);
        p2t1 = new JTextField();
        p2t2 = new JTextField();
        p2t3 = new JTextField();
        p2t4 = new JTextField();

        paneRight.add(p2Combo);
        paneRight.add(p2t1);
        paneRight.add(p2t2);
        paneRight.add(p2t3);
        paneRight.add(p2t4);
        paneRightText[0] = p2t1;
        paneRightText[1] = p2t2;
        paneRightText[2] = p2t3;
        paneRightText[3] = p2t4;
        paneTop.add(paneRight);

        paneBottom = new JPanel();
        paneBottom.setLayout(new GridLayout(0,2));

        executeButton = new JButton("Execute");
        executeButton.addActionListener(this);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        paneBottom.add(executeButton);
        paneBottom.add(clearButton);

        pane = new JPanel();

        pane.setLayout(new BorderLayout());
        pane.add(paneTop, BorderLayout.NORTH);
        pane.add(paneBottom, BorderLayout.SOUTH);
        frame.getContentPane().add(pane, BorderLayout.NORTH);

        area = new JTextArea();
	scroll = new JScrollPane(area);
	frame.getContentPane().add(scroll,BorderLayout.CENTER);
        //        frame.getContentPane().add(area);      

        paneConnect = new JPanel();
        paneConnect.setLayout(new GridLayout(0,2));
        connectButton = new JButton("Connect");
        connectButton.addActionListener(this);
        hostName = new JTextField();
        hostName.setEditable(true);
        paneConnect.add(connectButton);
        paneConnect.add(hostName);
        frame.getContentPane().add(paneConnect, BorderLayout.SOUTH);
        frame.setBounds(100,50,500,400);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() 
	    {
                public void windowClosing(WindowEvent e) 
		{
                    System.exit(0);
                } 
            });
    }

    public void actionPerformed(ActionEvent evt) 
    {
        if(evt.getSource().equals(executeButton)) 
	    {
		invokeMethod();
	    }
	else if(evt.getSource().equals(p2Combo)) 
	    {
            try 
		{
		    callMethod(p2Combo.getSelectedItem().toString());
		}
	    catch(Exception e) 
		{
		    e.printStackTrace();
		}
	    }
	else if(evt.getSource().equals(clearButton)) 
	    {
		clear();
	    }
	else if(evt.getSource().equals(connectButton)) 
	    {
		getTopoAPI(hostName.getText());
	    }
    }
    
    void getTopoAPI(String serverName) 
    {
        try 
	    {
		if ((serverName.trim().equals("")) || (serverName.trim() == null)) 
		    {
			serverName = "jagan";
			topo = (MapAPI)Naming.lookup("rmi:///MapAPI");
		    }
		else 
		    {
			topo = (MapAPI)Naming.lookup("rmi://" + serverName.trim() + "/MapAPI");
		    }
		area.setText("connected to host " + serverName);
	    }
	catch(Exception e) 
	    {
		e.printStackTrace();
		area.setText("Exception while establishing connection to " + serverName);
	    }
    }

    void clear() 
    {
        //hostName.setText("");
        //p2t1.setText("");
        //p2t2.setText("");
        //p2t3.setText("");
        //p2t4.setText("");
        area.setText("");
        strBuf = new StringBuffer();
    }
    
    void callMethod(String choice) throws Exception 
    {
        //strBuf = new StringBuffer();
        int num =  methNameVect.indexOf(choice);
        currentMethod = (Method)methVect.elementAt(num);
        Class[] c = currentMethod.getParameterTypes();
        for (int i=0; i<c.length; i++)
	    {
		paneLeftArray[i].setText(c[i].toString());
		paneRightText[i].setEditable(true);
	    }
        currentMethodFieldCount = c.length;
        //strBuf.append("No of paramters to be passed " + c.length + "\n");
        for (int i=c.length; i<4; i++) 
	    {
		paneLeftArray[i].setText("");
		paneRightText[i].setEditable(false);
	    }
        //area.setText(strBuf.toString());
    }

    void invokeMethod() 
    {
        //strBuf = new StringBuffer();
        Object[] ob = new Object[currentMethodFieldCount];
        for (int i=0; i<currentMethodFieldCount; i++) 
	    {
		if (!(paneLeftArray[i].getText().equals(""))) 
		    {
			if (((paneLeftArray[i].getText()).indexOf("[Ljava.lang.String")) != -1) 
			    {
				StringTokenizer stTok = new StringTokenizer(paneRightText[i].getText(), " ");
				String[] strArr = new String[stTok.countTokens()];
				int count=0;
				while (stTok.hasMoreTokens()) 
				    {
					strArr[count] = stTok.nextToken();
					count++;
				    }
				ob[i] = strArr;
			    }
			else if (((paneLeftArray[i].getText()).indexOf("String")) != -1) 
			    {
				String temp = paneRightText[i].getText();
				if ( temp.indexOf(" ") != -1 ) 
				    {
					StringTokenizer stok = new StringTokenizer(temp, " ");
					if ( stok.countTokens() > 0 ) 
					    {
						String result = stok.nextToken() + "\t";
						while (stok.hasMoreTokens()) 
						    {
							result = result + stok.nextToken() + "\t";
						    }
						ob[i] = result.substring(0,result.lastIndexOf("\t"));
					    }
					else 
					    ob[i] = temp;
				    }
				else
				    ob[i] = temp;
			    }
			else if (((paneLeftArray[i].getText()).indexOf("boolean")) != -1) 
			    {
				ob[i] = new Boolean(Boolean.valueOf(paneRightText[i].getText()).booleanValue());
			    }
			else if (((paneLeftArray[i].getText()).indexOf("int")) != -1) 
			    {
				ob[i] = new Integer(Integer.parseInt(paneRightText[i].getText()));
			    }
			else if (((paneLeftArray[i].getText()).indexOf("Properties")) != -1) 
			    {
			    String val = paneRightText[i].getText();
			    Properties prop = null;
			    if ( !val.equalsIgnoreCase("null") && !(val.trim().equals("")) ) 
				{
				    StringTokenizer stTok = new StringTokenizer(val, " ");
				    prop = new Properties();
				    while (stTok.hasMoreTokens()) 
					{
					    String key = (String) stTok.nextToken();
					    String value = (String) stTok.nextToken();
					    if (value.trim().equals("#"))
						value = " ";
					    prop.put(key,value);
					}
				}
			    ob[i] = prop;
			    }
			else if (((paneLeftArray[i].getText()).indexOf("ManagedObject")) != -1) {
			    if ((currentMethod.getName().indexOf("addObject")) != -1) {
				ManagedObject obj = new ManagedObject();
				obj.setName(paneRightText[i].getText());
				ob[i] = obj;
			    }
			    else 
				{
				    Node obj = new Node();
				    StringTokenizer stTok = new StringTokenizer(paneRightText[i].getText(), ",");
				    obj.setIpAddress(stTok.nextToken());
				    if (stTok.hasMoreTokens())
					{
					    obj.setName(stTok.nextToken());
					}
				    ob[i] = obj;
				}
			}
			else
			    {
				ob[i] = null;
			    }
		    }
		else
		    {
			ob = null;
		    }
	    }
	
        for (int i=0; i<ob.length; i++) 
	    {
		strBuf.append(" ob " + i + "               " + ob[i] + "\n");
	    }
        Class returnType = currentMethod.getReturnType();
        strBuf.append("return Type " + returnType.getName() + "\n");
        strBuf.append("_______________________________________________________________ " + "\n");
        strBuf.append("invoking method " + currentMethod.getName() + "\n");
        if (((returnType.getName()).indexOf("java.util.Vector")) != -1) 
	    {
		Vector v = null;
		try 
		    {
			v = (Vector) currentMethod.invoke(topo, ob);
		    }
		catch ( Exception ee ) 
		    {
			ee.printStackTrace();
		    }
		if (v == null)
		    strBuf.append("null" + "\n");
		strBuf.append("Vector :: " + v.size());
		strBuf.append( " elements : " + " \n" );
		for ( Enumeration en = v.elements(); en.hasMoreElements(); )
		    strBuf.append( (String)en.nextElement() + " \n");
	    }
	else if (((returnType.getName()).indexOf("java.lang.Object")) != -1) {
            try 
		{
		    Object obj = currentMethod.invoke(topo, ob);
		    if (obj == null) 
			{
			    strBuf.append("null" + "\n");
			}
		    else 
			{
			    if ( obj instanceof ManagedMapObject )
				strBuf.append("ManagedMapObject :: " + ((ManagedMapObject)obj).getProperties() + " \n");
			    else if ( obj instanceof MapDB ) 
				{
				    strBuf.append("MapDB :: props = " + ((MapDB)obj).getProperties() + " \n");
				    strBuf.append("         criteria props = " + ((MapDB)obj).getCriteriaProperties() + " \n");
				}
			    else if ( obj instanceof MapSymbol ) 
				strBuf.append("MapSymbol :: " + ((MapSymbol)obj).getProperties() + " \n");
			}
		}
	    catch(Exception e) 
		{
		    e.printStackTrace();
		}
        }
	else if (((returnType.getName()).indexOf("[Ljava.lang.String")) != -1) 
	    {
		try 
		    {
			String[] strArr1 = (String[])currentMethod.invoke(topo, ob);
			for (int i=0; i<strArr1.length; i++) 
			    {
				strBuf.append("stringArray element at " + i + "  is " + strArr1[i] + "\n");
			    }
		    }
		catch(Exception e) 
		    {
			e.printStackTrace();
		    }
	    }
	else
	    {
		try 
		    {
			strBuf.append("result " + currentMethod.invoke(topo, ob) + "\n");
		    }
		catch(Exception e) 
		    {
			e.printStackTrace();
		    }
	    }
        strBuf.append("_______________________________________________________________" + "\n");
        area.setText(strBuf.toString());
    }
    
    public static void main(String args[]) 
    {
        Vector methodVector = new Vector();
        Vector methodNameVector = new Vector();
        MapAPI topoDefa = null;
        try {
            try {
                if ( args.length > 0 )
                    topoDefa = (MapAPI)Naming.lookup("//"+args[0]+"/MapAPI");
                else
                    topoDefa = (MapAPI)Naming.lookup("//localhost/MapAPI");
            }
	    catch(Exception e) 
		{
		    //                topoDefa = (TopoAPI)new TopoDB();
		}
        }
	catch(Exception e) 
	    {
		e.printStackTrace();
	    }
	//          if ( topoDefa == null )
	//              topoDefa = new MapAPIImpl();
        Method[] m = (topoDefa.getClass()).getMethods();
        for (int i=0; i<m.length; i++) 
	    {
		Class[] cm = m[i].getExceptionTypes();
		for (int j=0; j<cm.length; j++) 
		    {
			if ((cm[j].getName()).equals("java.rmi.RemoteException")) {
			    if ((m[i].getModifiers()) == (Modifier.PUBLIC)) {
				methodVector.addElement(m[i]);
			    }
			}
		    }
	    }
        for (Enumeration e = methodVector.elements(); e.hasMoreElements();) 
	    {
		String met = ((Method)e.nextElement()).getName();
		while (true) 
		    {
			if (methodNameVector.contains(met)) 
			    {
				met = met + "1";
			    }
			else 
			    {
				break;
			    }
		    }
		methodNameVector.addElement(met);
	    }
        try 
	    {
		MapAPITest t = new MapAPITest(methodNameVector, methodVector);
	    }
	catch(Exception e) 
	    {
		e.printStackTrace();
	    }
    }
}
