//$Id: MOPackager.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class MOPackager {
	
	// Decleration section 
	Document doc=null;
	DefaultMutableTreeNode deployNode=null;
	String projName=null;

	standardPackComp stdPk=null;
	customPackComp custPk=null;
	MapListDetails mapListDet=null;
	packageResults packRes=null;
	JLabel         imageLabel=null;
	TransverseContainer transCon=null;
	TransversePanel deployWizard;
	String fs=File.separator;
	JDialog  dlg=null;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	
	public void setDeployParams(Document document,DefaultMutableTreeNode depNode,String projectName) {
		// Before starting deployment set the initial parameters to this class.
		// 
		doc=document;
		deployNode=depNode;
		projName=projectName;
	}
	
	private boolean isDeviceModelled() {
		Element deviceNode=(Element)doc.getElementsByTagName("DEVICE_PARAMS").item(0);
		if(deviceNode==null) {
			return false;
		}
		return true;
	}
	
	public void startDeployment() {
		imageLabel=new JLabel("");
		imageLabel.setIcon(new ImageIcon("."+fs+"images"+fs+"packing.jpg"));
		deployWizard=new TransversePanel();
		deployWizard.setImageLabel(imageLabel);
		stdPk=new standardPackComp();
		custPk=new customPackComp();
		mapListDet=new MapListDetails();
		packRes=new packageResults();
		transCon=new TransverseContainer();
		deployWizard.addTransverseContainer(transCon);
		transCon.addTransverseComponent("XMLMODEL",doc);
		transCon.addTransverseComponent("deployNode",deployNode);
		transCon.addTransverseComponent("projName",projName);
		
		deployWizard.addComponents("Screen1",stdPk,"."+fs+"images"+fs+"packing.jpg");
		deployWizard.addComponents("Screen2",custPk,"."+fs+"images"+fs+"packing.jpg");
		Element deviceNode=(Element)doc.getElementsByTagName("DEVICE_PARAMS").item(0);
		if(deviceNode!=null) {
			deployWizard.addComponents("Screen3",mapListDet,"."+fs+"images"+fs+"packing.jpg");
		}
		deployWizard.addComponents("Screen4",packRes,"."+fs+"images"+fs+"packing.jpg");
		deployWizard.initialize();
		dlg=new JDialog();
		dlg.setTitle(java.text.MessageFormat.format(resourceBundle.getString("Packager for {0}"),new String[]{deployNode.getParent().toString()}));
		dlg.setModal(true);
		dlg.getContentPane().add(deployWizard,BorderLayout.CENTER);
		dlg.getContentPane().add(imageLabel,BorderLayout.WEST);
		dlg.setSize(800,550);
		dlg.setLocation(getScrLoc(dlg));
		dlg.setVisible(true);
	}	

	public Point getScrLoc(Component comp) {
		// This method will return the x,y that will place a container in the 
		// center of the screen. 
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int width=(int)(d.getWidth()-comp.getSize().getWidth())/2;
		int height=(int)(d.getHeight()-comp.getSize().getHeight())/2;
		return new Point(width,height);
	}
}
