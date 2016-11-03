//$Id: PollObjOidList.java,v 1.1.4.5 2013/08/09 07:00:04 vijayalakshmiv Exp $
package com.adventnet.nms.perfui;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PollObjOidList.java
 *
 * Created on Mar 23, 2011, 6:40:02 PM
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.adventnet.nms.util.NmsClientUtil;

/**
 *
 * @author vijayalakshmiv
 */
public class PollObjOidList extends JPanel implements ListSelectionListener{
	
	public DefaultListModel listmodel= new DefaultListModel();
	private javax.swing.JList OidList ;
	private PollingObjectPropsPanel propspanel;
	private int selindex=0;
	public String operation="";
	//Vector to maintain collection criteria for each OID.
	private Vector dataid;
    /** Creates new form PollObjOidList */
    public PollObjOidList(PollingObjectPropsPanel propspanel) {
        initComponents();
        this.propspanel=propspanel;
    }

   
    private void initComponents() {

        javax.swing.JPanel PdlistPanel = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        OidList = new javax.swing.JList();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());
        setPreferredSize(new Dimension(170,600));
        PdlistPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 7), BorderFactory.createLineBorder(new Color(204, 204, 204))));
        PdlistPanel.setLayout(new java.awt.BorderLayout());
        OidList.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        OidList.setModel(listmodel);
        OidList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                OidListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(OidList);
      
        PdlistPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel1.setBackground(new java.awt.Color(223, 223, 223));
        jLabel1.setFont(new Font(NmsClientUtil.getFont().getName(),1,NmsClientUtil.getFont().getSize()));
        jLabel1.setText(NmsClientUtil.GetString("javaui.perfgui.polldata.dataidentifierlist"));//NO I18N
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 25));
        PdlistPanel.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        add(PdlistPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>

    public void populateList(Vector oid)
    {
    	 listmodel=(DefaultListModel)OidList.getModel();
         
         if(listmodel.getSize() > 0)
 		{
 			listmodel.removeAllElements();
 		}
        
 		for(Enumeration e = oid.elements();e.hasMoreElements();)
 		{
 			listmodel.addElement(e.nextElement());
 		}
		String[] toenable = {"decimalDataTableName","statsDataTableName","stringDataTableName","name","oid","type"};//NO I18N
		propspanel.disableEnableComponents(toenable,true,"PollObjPage2");
		JButton browse = propspanel.getControl("browsemib");
		if(browse != null)
		{
			browse.setEnabled(true);
		}
 		OidList.updateUI();
    }
    private void OidListValueChanged(javax.swing.event.ListSelectionEvent evt) {
        // TODO add your handling code here:

        if(evt.getValueIsAdjusting())
        	return;
        
        selindex = OidList.getSelectedIndex();
        if(selindex>=0)//NO I18N
        {	
        if(dataid.isEmpty())
    			return;
    	Properties props = (Properties)dataid.elementAt(selindex);
    	if(props != null)
        propspanel.populateDcPropsPanel(props,selindex);
    	propspanel.changeDataIdControls(true);
        }
     
}
    
    public void addOid(String oid)
    {
    	listmodel.addElement(oid);
    	OidList.updateUI();
    	OidList.clearSelection();
    }

    public void deleteOid()
    {
    	listmodel.removeElementAt(selindex);
    	OidList.updateUI();
    	OidList.clearSelection();
    }
    
    public void modifyOid()
    {
    	OidList.clearSelection();
    }
    
    public void addToVector(Properties props,int index)
    {
    	Properties props1 = (Properties) props.clone();
    	for(Enumeration en = props1.keys();en.hasMoreElements();)
    	{
    		String key = en.nextElement().toString();
    		Object obj = props1.get(key);
    		if(obj == null)
    			props.remove(key);
    		else if(obj instanceof String && props1.get(key).toString().equals(""))
    			props.remove(key);
    	}
    	
    	if(dataid==null)
    		dataid=new Vector();
    	if(!dataid.contains(props))
    	{	
    	if(index == -1)
    		dataid.add(props);
    		else
    			dataid.add(index,props);
    	}
    	
    }
    
    public void removeFromVector(int index)
    {
    	dataid.remove(index);
    }
    
    public Vector getDcVector()
    {
    	return dataid;
    }


	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		OidListValueChanged(e);
	}
}
