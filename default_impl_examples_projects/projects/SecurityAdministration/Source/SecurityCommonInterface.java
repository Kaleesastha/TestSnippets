//$Id: SecurityCommonInterface.java,v 1.1 2006/08/29 13:57:02 build Exp $
 package com.adventnet.security.ui ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public interface SecurityCommonInterface 
 {
	
	public void setSecurityModel(com.adventnet.security.ui.AbstractSecurityModel model);
	public void setBuilderUiImpl(com.adventnet.nms.util.CommonBuilderUIImpl uiImpl);
	public void fireDataChanged();
	public void showError(String err);
	public void close();
	
 }









