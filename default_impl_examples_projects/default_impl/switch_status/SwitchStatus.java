/*
$Id: SwitchStatus.java,v 1.2 2008/07/09 10:21:48 johnpaul Exp $
*/

package com.adventnet.nms.mapui;

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.*;
import com.adventnet.snmp.beans.*;
import com.adventnet.nms.topodb.SnmpNode;
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.border.*;

/**
* 
* This is an example to illustrate the use of MapClientAPI.It shows how a map can be formed 
* dynamically at run time. In this we collect the details 
* of the switch and its ports by SNMP queries and show these details in a map. Each port
* of the switch is represented by a MapSymbolComponent and the links are used to distinguish the 
* ports on the basis of there ifSpeed. All ports with same ifSpeed are connected to each 
* other by a link. The color of the MapSymbolComponent represents the status of that particular 
* port (whether it is up or down). The queries are done at regular time interval to check 
* for status update of the port. The Default time interval is set to two minutes which can 
* be changed according to the requirement from the GUI. 
* The class implements MapListener so that it can listen to the map events and accordingly
* change the map display.
* The class also implements com.adventnet.nms.util.UserClassInterface so that this class can
* be invoked by via the "invokeclasson*" option as menu item action.
*
**/
public class SwitchStatus implements MapListener, ActionListener, com.adventnet.nms.util.UserClassInterface
{
    MapSymbolComponent ms1=new MapSymbolComponent();
    MapSymbolComponent ms2=new MapSymbolComponent();
    MapClientAPI mapClient=null;
    Hashtable swStatus=null;
    DefaultMapModel mapModel=null;
    JFrame jf=null;
    JFrame switchPane=null;
    JTextField timeT = null;
    
    Vector portMap=null;
    Vector status=null;
    Hashtable infoFields = null;
    JLabel portLabel = null;
    boolean layoutReady = false;
    JPanel mapPanel;
    String switchName = null;
    int portNos=0;
    int delay =120000;
    int k =1;
    int count = 0;
    int count2 = 0;
    int num =1;
    javax.swing.Timer timer ; 
    JProgressBar progressBar   = null;
    JFrame frame = new JFrame(NmsClientUtil.GetString("status"));
    JPanel statusPanel = new JPanel();
    JLabel statusLabel = new JLabel();
    SwitchStatus st = null ;
    String community = null;
    
    
    public String getResultString(String[] args) 
    {
        
        switchName = args[0];
        Properties moProp = MapClientAPI.getInstance().getManagedObjectProperties(switchName);
        community = moProp.getProperty("community");
        if(community == null || community.equals(""))
        {
            community = "public";
        }
        //delay = 6000;
        progressBar = new JProgressBar(0,49);
        statusPanel.add(progressBar);
        statusPanel.add(statusLabel);
        int count1 = 0;
        progressBar.setValue(count1);
        frame.getContentPane().add(statusPanel);
        frame.setSize(350,175);
        NmsClientUtil.centerWindow(frame);
        frame.setVisible(true);
        st = this;
        Thread someThread = new Thread()
            {
                public void run() 
                {
                    //swStatus is the Hashtable which contains all the switch and its port details
		    //to be shown in the map.
                    swStatus=getSwitchStats(switchName);
                    //this timer will call for query after after specific time interval i.e. delay.
                    timer = new  javax.swing.Timer(delay, new ActionListener()
                        {
                            public void actionPerformed(ActionEvent evt)
                            {
                                count = 0;
                                num=1;
                                //swStatus is the Hashtable which contains all the switch and its port details
                                //to be shown in the map.
                                Thread addThread = new Thread()  
                                    {
                                        public void run() 
                                        {   
                                            swStatus=getSwitchStats(switchName);
                                            mapModel.updateListeners();                       
                                        }
                                    };
                                addThread.start();
                                // this thread takes roughly 20 sec.s to complete .
                                // so the time delay lesser than 20 seconds will put the thread in Queue.
                                // so the Queuesize will be keep on increasing and model will not get updated in that specified 
                                // period -  to fix client grays_out problem - balajir
                                
                                // This is called so that the updates  are reflected in the map. 
                                // This method will call repaint so that if there is any color change due to 
                                // status update it will dynamically change in the map.
                                
                            }
                        }
                                                   );	
                    
			
                    if ( k == 1 )
                    {
			
			timer.setDelay(120000);
			//This is the way of creating a model dynamically. 
			Properties p=new Properties();
                        p.put("name",switchName);
			//To specify which all layouts can be used here. $nmsgrid means only nmsgrid layout
                        //can be used.
			p.put("topology", "$nmsgrid");
			//Will specify the initial layout that will be used to arrange the symbols
			//and the links.
                        p.put("currentTopology", "nmsgrid");
			p.put("mapSymbolRenderer","com.adventnet.nms.mapui.SwitchRenderer");
                        p.put("helpDoc",NmsClientUtil.getHelpURL("Map_Client_Features"));
			//a new model is created with properties as specified above.
			mapModel=new DefaultMapModel(p);
			//The anchor property of the model is set to true so that the map is 
			//non-editable. In this map we do not allow any dragging of symbol. 
			//You can only view the map.
			mapModel.setAnchored(true);
			
			//This method is called for adding the symbols and the links to the model. 
			if(( swStatus == null) || (swStatus.size() == 0)) 
			{
                            NmsClientUtil.showError(NmsClientUtil.GetString("Response timeout, switch may be busy or down"));
                            frame.dispose();
                            return ;
			}
                        
			addSymbols(mapModel,swStatus);
			//To get a instance of mapclient api.
			mapClient=MapClientAPI.getInstance();
			mapClient.addMap(mapModel);
			jf=new JFrame();
			jf.addWindowListener(wadpater);
			jf.setSize(800,600);
			//We call the setlayout method to layout the symbols and the links accordingly.
			setLayouts(jf);
			mapModel.addMapListener(st);
			jf.show();
			mapModel.updateListeners();
			k=k+1;
                    }
                }
            };
        someThread.start();
        return "";
    }
    /**
     * This method returns a Hashtable which has the details of the switches like
     * its name, number of ports, physical location of the switch,the user/contact 
     * person and also the port details corresponding to that switch.
     * First we find the switch details by doing SNMP queries and then we use these 
     * details to find out the port details for that switch.
     **/
    Hashtable getSwitchStats(String arg)
    {
        delay = 120000;
        SnmpOID numPorts=new SnmpOID(".1.3.6.1.2.1.17.1.2.0");
        SnmpOID basePort=new SnmpOID(".1.3.6.1.2.1.17.1.4.1");
        SnmpOID sysName=new SnmpOID(".1.3.6.1.2.1.1.5.0");
        SnmpOID sysLoc=new SnmpOID(".1.3.6.1.2.1.1.6.0");
        SnmpOID sysCon=new SnmpOID(".1.3.6.1.2.1.1.4.0");
        
        SnmpOID basePortIndex=null;
        SnmpOID ifOperStatus=null;
        SnmpOID ifDescrStatus=null;
        SnmpOID ifSpeedStatus=null;
        SnmpVarBind varbinds=null;
        
        Hashtable switchStatusTable=new Hashtable();
        Vector ifVect=null;
        SnmpTarget target1 = new SnmpTarget(NmsClientUtil.applet);
        target1.setAttemptPartial(true);
        target1.setTargetHost(arg);
        target1.setCommunity(community);
        
        try
        {
            target1.setSnmpOID(sysName);
            varbinds = target1.snmpGetVariableBinding(); 
            switchStatusTable.put("name",(String)(varbinds.getVariable().toValue()));//shanks
            target1.setSnmpOID(sysLoc);
            varbinds = target1.snmpGetVariableBinding(); 
            switchStatusTable.put("location",(String)(varbinds.getVariable().toValue()));//shanks
            target1.setSnmpOID(sysCon);
            varbinds = target1.snmpGetVariableBinding(); 
            switchStatusTable.put("contact",(String)(varbinds.getVariable().toValue()));//shanks
            target1.setSnmpOID(numPorts);
            varbinds = target1.snmpGetVariableBinding(); 
            portNos=((Integer)varbinds.getVariable().toValue()).intValue(); 
            int[] portArr=new int[portNos];
            target1.setSnmpOID(basePort);
            for(int i=0;i<portNos;i++)
            {
                varbinds = target1.snmpGetNextVariableBinding(); 
                SnmpVar sv = varbinds.getVariable();
                if(sv==null) break;
                portArr[i]=	((Integer)sv.toValue()).intValue() ; 
            }
            
            int[] portIndexArr=new int[portArr.length];
            Hashtable ports=new Hashtable();
            
            for(int i=0;i<portArr.length;i++) 
            {
                String oid=".1.3.6.1.2.1.17.1.4.1.2."+portArr[i];
                basePortIndex=new SnmpOID(oid);
                target1.setSnmpOID(basePortIndex);
                varbinds = target1.snmpGetVariableBinding(); 
                SnmpVar sv1 = varbinds.getVariable();
                if(sv1==null) break;
                portIndexArr[i]=((Integer)sv1.toValue()).intValue();
                
                ports.put(new Integer(portIndexArr[i]),new Integer(portArr[i]));
            }
            
            ifVect=new Vector(portIndexArr.length);
            portMap=new Vector(portIndexArr.length);
            switchStatusTable = Query(ifVect,portMap,count,switchStatusTable,varbinds,target1,ifSpeedStatus,ifDescrStatus,ifOperStatus,portIndexArr,ports);
            num = num +1;
            
            if(count<portArr.length)
            {
                
                switchStatusTable = Query(ifVect,portMap,count,switchStatusTable,varbinds,target1,ifSpeedStatus,ifDescrStatus,ifOperStatus,portIndexArr,ports);	
            }
            
            
            count2=1;
            timer.start();
            
        }
        catch(Exception ex)
        {
            //JOptionPane.showMessageDialog(null, target1.getErrorString() ,"alert", JOptionPane.ERROR_MESSAGE);
        }
        return switchStatusTable;
    }
    
    /**
     * This method returns a Hashtable which contains the switch as well as the port details
     * for that particular switch. In this method the port details like ifOperStatus, 
     * ifDescr, ifSpeed are collected by SNMP querying and are added to the the 
     * Hashtable containing switch details.	
     **/
    Hashtable  Query(Vector ifVect,Vector portMap,int count1,Hashtable switchStatusTable,SnmpVarBind varbinds,SnmpTarget target1,SnmpOID ifSpeedStatus,SnmpOID ifDescrStatus,SnmpOID ifOperStatus,int [] portIndexArr,Hashtable ports)
    {
        try
        {
            for(int i=count1;i<portIndexArr.length;i++)
            {
                
                
                
                String operoid=".1.3.6.1.2.1.2.2.1.8."+portIndexArr[i];
                ifOperStatus=new SnmpOID(operoid);
                target1.setSnmpOID(ifOperStatus);
                varbinds=target1.snmpGetVariableBinding(); 
                SnmpVar sv2 = varbinds.getVariable();
                if (sv2==null) break;
                ifVect.addElement((Integer)sv2.toValue());
                
                String descroid=".1.3.6.1.2.1.2.2.1.2."+portIndexArr[i];
                ifDescrStatus=new SnmpOID(descroid);
                target1.setSnmpOID(ifDescrStatus);
                varbinds=target1.snmpGetVariableBinding(); 
                SnmpVar sv3 = varbinds.getVariable();
                if (sv3==null) break;
                ifVect.addElement((String)sv3.toValue());
                
                String speedoid=".1.3.6.1.2.1.2.2.1.5."+portIndexArr[i];
                ifSpeedStatus=new SnmpOID(speedoid);
                target1.setSnmpOID(ifSpeedStatus);
                varbinds=target1.snmpGetVariableBinding(); 
                SnmpVar sv4 = varbinds.getVariable();
                if (sv4==null) break;
                ifVect.addElement((String)sv4.toString());
                
                portMap.addElement(ports.get(new Integer(portIndexArr[i])));
                Vector temp=(Vector)ifVect.clone();
				//Port details are put into the Hashtable after the querying.
                switchStatusTable.put(portMap.elementAt(i),temp);//shanks
                ifVect.removeAllElements();
                count= i+1;
                
                
                
                
                progressBar.setMaximum(portIndexArr.length);
                statusLabel.setText(NmsClientUtil.GetString("Querying for port ") + count );
                progressBar.setValue(count);
                if( count2 != 1  && count < portIndexArr.length)
                {
                    
                    progressBar.setValue(count);
                }
                else 
                {
                    //System.out.println(" the value of count in for "  + count);
                    frame.setVisible(false);
                    frame.dispose();
                }
                
                
            }
        }
        catch(Exception ex)
        {
            
            if (num >=5)
                JOptionPane.showMessageDialog(null, target1.getErrorString() ,"alert", JOptionPane.ERROR_MESSAGE);	
            
        }
        return switchStatusTable;
    }	
    /**
     * This method adds MapSymbols representing various ports of the switch and links to
     * show the connection of the ports with equal ifSpeed to the model.
     **/
    
    void addSymbols(DefaultMapModel dm,Hashtable switchStat)
    {
        
        
        Properties prop = new Properties();
        prop.put("isPort","false");
        prop.put("speed","0");
        
        if (portMap==null) return;
        dm.setBackground(new Color(255,255,200));
        
        for(int i=0;i<portMap.size();i++)
        {
            MapSymbolComponent ms1=new MapSymbolComponent();
            MapSymbolComponent ms2 = new MapSymbolComponent();
            ms1.setName("symb"+i);
            ms1.setObjType(1); 
            ms1.setAnchored(false);
            ms1.setProperties(prop);
            dm.addSymbol(ms1);
        }
        prop.put("isPort","true");
        
        for(int i=0;i<portMap.size();i++)
        {
            try
            {
                Vector status=(Vector)switchStat.get((Integer)portMap.elementAt(i));
                MapSymbolComponent ms=new MapSymbolComponent();
                ms.setName("port"+(i+1));
                ms.setLabel( ((Integer)portMap.elementAt(i)).toString());
                prop.put("speed",status.elementAt(2));
                ms.setObjType(1); 
                ms.setAnchored(false);
                ms.setIconName("switchPort.png");
                String s=(status.elementAt(0)).toString();
                ms.setStatus(getStatus(Integer.parseInt(s)));
                ms.setProperties(prop);
                dm.addSymbol(ms);
            }
            catch(Exception ex)
            {}
        }
        int l = 0;
        for(int i=0;i<dm.getSymbolCount()/2-1;i++)
        {	
            MapLinkComponent Link = new MapLinkComponent();	
            int s = (Integer.parseInt((dm.getSymbol(i+portMap.size())).getProperty("speed")));
            int s1 = (Integer.parseInt((dm.getSymbol(i+portMap.size()+1)).getProperty("speed")));
            
            if(s==s1)
            {
                Link.setName("link" + l);
                l++;
                Link.setSourceObj(dm.getSymbol(i));
                Link.setThickness(1);
                Link.setDestObj(dm.getSymbol(i+1));
                dm.addLink(Link);
            }
            MapLinkComponent Link1 = new MapLinkComponent();	
            Link1.setName("link" + l);
            l++;
            Link1.setSourceObj(dm.getSymbol(i));
            Link1.setThickness(1);
            Link1.setDestObj(dm.getSymbol(i+portMap.size()));
            dm.addLink(Link1);
        }
        
        MapLinkComponent Link = new MapLinkComponent();	
        Link.setName(dm.getSymbol(portMap.size() - 1).getName() + "---" + dm.getSymbol(portMap.size() * 2 - 1).getName());
        Link.setSourceObj(dm.getSymbol(portMap.size()-1));
        Link.setThickness(1);
        Link.setDestObj(dm.getSymbol((portMap.size()*2)-1));
        dm.addLink(Link);
    }
    
    int getStatus(int status)
    {
        SeverityInfo info = SeverityInfo.getInstance();
        SeverityIterator si = info.getIterator();
        if (status==1) return info.getClear();
        else if (status == 2) 
        {
            si.moveToHighest();
            return si.getCurrent();
        }
        else if (status == 3) 
        {
            si.moveToHighest();
            return si.getPreviousCriticality();
        }
        else return info.getSpecialPurposeSeverity();
    }
    
    /** 
     * This method returns a JPanel. This is a method for forming a JPanel GUI for various
     * details showing the switch and its ports details. 
     **/
    
    private JPanel createPanel(String label[], String value[], int textLength)
    {
        JPanel resultPanel = new JPanel(new java.awt.GridLayout(label.length,1));
        JPanel tempPanel = null;
        JLabel l = null;
        JTextField tf = null;
        for (int i=0; i<label.length; i++)
        {
            tempPanel = new JPanel(new java.awt.GridLayout(1,2));
            l = new JLabel(label[i],SwingConstants.RIGHT);
            if (textLength != 0)
                tf = new JTextField(value[i],15);
            else	
                tf = new JTextField(value[i],15);
            tempPanel.add(l);
            JPanel forText = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
            forText.setForeground(Color.yellow);
            forText.add(tf);
            tempPanel.add(forText);
            tempPanel.setBackground(new Color(200,250,200));
            resultPanel.add(tempPanel);
            infoFields.put(label[i],tf);
        }
        return resultPanel;	
    }
    
    /**
     * This is a method to set the layout for the map. All the different symbols links and 
     * panels are placed according to the layout specified i.e. nmsgrid layout.
     **/
    void setLayouts(JFrame jf)
    {
        
        if (swStatus.size() == 0)
        {
            return;
        }
        NmsClientUtil.busyCursor(jf);
        if (infoFields == null)
            infoFields = new Hashtable();
        //for mappanel
        mapPanel = new JPanel();
        mapPanel.setLayout(new java.awt.BorderLayout());
        AutoLayout layout = MapClientInitializer.getLayoutInstanceByKeyName("nmsgrid");
        ((NmsGridLayout)layout).setRows(2);
        ((NmsGridLayout)layout).setCols(portMap.size());
        mapPanel.add(BorderLayout.CENTER,mapClient.getMapCanvas(mapModel.getMapName()));
        //for infopanel
        JPanel infoPanel = new JPanel();
        JPanel dmPanel;	
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        infoPanel.setLayout(gridBag);
        String lo=(String)swStatus.get("location");
        String co=(String)swStatus.get("contact");
        JLabel nameLabel = new JLabel(NmsClientUtil.GetString("Configuration Of") + switchName);
        nameLabel.setForeground(Color.blue);
        nameLabel.setBackground(Color.white);
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 1;
        gridBag.setConstraints(nameLabel,c);
        infoPanel.add(nameLabel);
        
        String labels[] = {NmsClientUtil.GetString("Name Of Switch"),NmsClientUtil.GetString("Number Of Ports"),NmsClientUtil.GetString("Physical Location"),NmsClientUtil.GetString("User/Contact Name")};
        String values[] = {switchName, String.valueOf(portNos), lo, co};
        c.anchor = GridBagConstraints.SOUTH;		
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 3;
        c.gridheight = 15;
        c.gridx = 0;
        c.gridy = 2;
        dmPanel = new JPanel();
        dmPanel.add(createPanel(labels,values,0));
        gridBag.setConstraints(dmPanel,c);
        infoPanel.add(dmPanel);
        portLabel = new JLabel(NmsClientUtil.GetString("Details Of Port"));
        portLabel.setForeground(Color.blue);
        c.anchor = GridBagConstraints.NORTH;		
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 4;
		c.gridy = 1;
		gridBag.setConstraints(portLabel,c);
		infoPanel.add(portLabel);
		
		String details[] = {NmsClientUtil.GetString("ifOper Status"),NmsClientUtil.GetString("ifDescr"),NmsClientUtil.GetString("ifSpeed")};
		String vals[] = {"", "", ""};
		c.anchor = GridBagConstraints.SOUTH;		
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridwidth = 3;
		c.gridheight = 15;
		c.gridx = 4;
		c.gridy = 2;
		dmPanel = new JPanel();
		dmPanel.add(createPanel(details,vals,18));
		gridBag.setConstraints(dmPanel,c);
		infoPanel.add(dmPanel);	
		infoPanel.setBackground(new Color(250,250,200));		
		GridBagLayout gridBag1 = new GridBagLayout();
		GridBagConstraints c1 = new GridBagConstraints();
		jf.getContentPane().setLayout(gridBag);
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.weightx = 1;
		c1.weighty = 0.5;
		c1.gridwidth =1;
		c1.gridheight =2;
		c1.gridx = 0;
		c1.gridy = 1;
		c1.anchor = GridBagConstraints.CENTER;
		gridBag.setConstraints(infoPanel,c1);
		jf.getContentPane().add(infoPanel);
		mapPanel.add(getBottomPanel(), BorderLayout.SOUTH);
		c1.fill = GridBagConstraints.BOTH;
		c1.anchor =GridBagConstraints.SOUTH;	
		c1.weightx = 1;
		c1.weighty = 0.5;
		c1.gridwidth =1;
		c1.gridheight =1;
		c1.gridx = 0;
		c1.gridy = GridBagConstraints.RELATIVE;		
		gridBag.setConstraints(mapPanel,c1);		
		jf.getContentPane().add(mapPanel);
		jf.getContentPane().setBackground(new Color(250,250,200));
		jf.setTitle(NmsClientUtil.GetString("Switch Status"));
		layoutReady = true;
		NmsClientUtil.normalCursor(jf);
	}

	/**
	* This method returns the bottom most JPanel which tells us about the colors of the 
	* symbol while the link is up or down. It also gives the option to the user to change
	* the time interval of querying.
	**/
	private JPanel getBottomPanel()
	{
		SeverityInfo info = SeverityInfo.getInstance();
		Color up = info.getColor(info.getClear());
		SeverityIterator iterator = info.getIterator();
		iterator.moveToHighest();
		Color down = info.getColor(iterator.getCurrent());

		JPanel leftPanel = new JPanel();
		JTextField tf = new JTextField(3);
		tf.setEnabled(false);
		tf.setBackground(up);
		leftPanel.add(tf);
		JLabel l = new JLabel(NmsClientUtil.GetString("Link Up"));
		leftPanel.add(l);
		tf = new JTextField(3);
		tf.setEnabled(false);
		tf.setBackground(down);
		leftPanel.add(tf);
		l = new JLabel(NmsClientUtil.GetString("Link Faulty"));
		leftPanel.add(l);
		JButton button = new JButton(NmsClientUtil.GetString("Hide ToolBar"));
		button.setName("toolbarbutton");
                button.setActionCommand("Hide ToolBar");
		button.addActionListener(this);
		leftPanel.add(button);
		JLabel time = new JLabel (NmsClientUtil.GetString("Poll Time in millisec"));
		leftPanel.add(time);
		timeT= new JTextField("120000",10);
		leftPanel.add(timeT);
		JButton set = new JButton(NmsClientUtil.GetString("Set"));
		set.setName("setButton");

		set.addActionListener(this);
		leftPanel.add(set);
		
		return leftPanel;
	}
	/**
	* This method defines the action to be performed when a button in the GUI is pressed.
	* eg For hiding and showing the toolbar.
	* For changing the time interval of query.
	**/
	public void actionPerformed(ActionEvent evt)
		
	{
		if (evt.getSource() instanceof JButton)
		{
			JButton button = (JButton) evt.getSource();
			if (button.getName() != null && button.getName().equals("toolbarbutton"))
			{
				if (button.getText() != null && button.getActionCommand().equals("Show ToolBar"))
				{
					mapPanel.remove(mapClient.getMapCanvas(mapModel.getMapName()));	
					mapPanel.add(BorderLayout.CENTER,mapClient.getMapCanvas(mapModel.getMapName()));
					button.setActionCommand("Hide ToolBar");
                                        button.setText(NmsClientUtil.GetString("Hide ToolBar"));
                                        
				}
				else
				{
					mapPanel.remove(mapClient.getMapCanvas(mapModel.getMapName()));	
					mapPanel.add(BorderLayout.CENTER,mapClient.getMapCanvas(mapModel.getMapName()));
                                        button.setActionCommand("Show ToolBar");
                                        button.setText(NmsClientUtil.GetString("Show ToolBar"));
				}
			}
                        else 
			{
			  count = 0;
			  num=1;
			  delay = Integer.parseInt(timeT.getText());
			  timer.setDelay(delay);
                          Thread newThread = new Thread()   //new thread is added to fix grays-out problem
                              {
                                  public void run() 
                                  {                          
                                      getSwitchStats(switchName);
                                  }
                              };
                          newThread.start();
                        }
		}
	}
	/**
	* This method is called when a particular port is selected. When the port is selected
	* all its details collected are displayed in the GUI. 
	**/
	private void updatePortDetails()
	{
		if (!layoutReady) return;
		try
		{
			Vector symbols = mapModel.getSelectedSymbols();
			if (symbols == null || symbols.size() == 0 || symbols.size() > 1 )
			{
				portLabel.setText(NmsClientUtil.GetString("Details of Port"));
				JTextField tf = (JTextField) infoFields.get(NmsClientUtil.GetString("ifOper Status"));
				if (tf != null) tf.setText("");
				tf = (JTextField) infoFields.get(NmsClientUtil.GetString("ifDescr"));
				if (tf != null) tf.setText("");
				tf = (JTextField) infoFields.get(NmsClientUtil.GetString("ifSpeed"));
				if (tf != null) tf.setText("");
				return;
			}
			MapSymbolComponent ms = (MapSymbolComponent) symbols.firstElement();
			int port = 0;
			
			try
			{
                            port = Integer.parseInt(ms.getLabel().trim());
			}
			catch(Exception ex)
			{
                            return ;//to catch the exception that is thrown when we select a non port symbol.
			}
			
			
			portLabel.setText(NmsClientUtil.GetString("Details of Port") + port);
			status = (Vector)swStatus.get(new Integer(port));
			JTextField tf = (JTextField) infoFields.get(NmsClientUtil.GetString("ifOper Status"));
			if (tf != null) tf.setText(((Integer)status.elementAt(0)).toString());
			tf = (JTextField) infoFields.get(NmsClientUtil.GetString("ifDescr"));
			if (tf != null) tf.setText((String)status.elementAt(1));
			tf = (JTextField) infoFields.get(NmsClientUtil.GetString("ifSpeed"));
			if (tf != null) tf.setText((String)status.elementAt(2));
			
		}
		catch(Exception exc)
		{
                    System.err.println("Error: " + exc);
                    exc.printStackTrace();
		}
	}
    /**
     *	This method will be called when ever a port is selected to show the port details.  
     **/
    
    public void changeMap(MapEvent me)
    {
        if(me.getEventType() == me.SYMBOLS_SELECTED)
        {
            updatePortDetails();
        }
    }
    
    
    /**
     *	This method catches the window closing event and stops the timer which is used  
     *	for querying the switch after particular time interval.
     **/	
    WindowAdapter wadpater = new WindowAdapter(){
            public void windowClosing(WindowEvent evt)
            {
		timer.stop();
		//deleteMap should be called on the window closing event so that it is removed 
		//from the client model. If the map is not deleted then it will remain in the 
		//client model.
		mapClient.deleteMap(switchName);
            }
        };
}

