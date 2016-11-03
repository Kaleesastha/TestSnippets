//$Id: ShowCustomView.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $

 package com.adventnet.nms.tools.CustomView ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;
import javax.swing.tree.*;
import java.util.*;
 public class ShowCustomView 
 {
	DefaultMutableTreeNode node=null; 
	CustomViewObject custViewObject=null;
	PropertyWizard propWizard=null;
	String type="";
	String viewId="";
	String user="";
	Properties customViewProps=null;
	public ShowCustomView(DefaultMutableTreeNode selectedNode,Properties p)
	 {
		node=selectedNode;
		customViewProps=p;
	 }	
	public JPanel showPanel()
	{
		custViewObject =(CustomViewObject)node.getUserObject();
		type=custViewObject.getCustViewType();
		propWizard=new PropertyWizard(type,customViewProps);
		return propWizard;
	}
		
	
 }


