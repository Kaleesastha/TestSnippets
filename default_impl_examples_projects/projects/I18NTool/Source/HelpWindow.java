// $Id: HelpWindow.java,v 1.2 2011/04/19 07:27:52 prabakaran Exp $
 package com.adventnet.management.i18n.tools ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class HelpWindow extends JWindow
 {

     public HelpWindow(java.util.ResourceBundle resourceBundle)
     {	
		
		
JPanel panel = new JPanel();
panel.setBackground(Color.white);
panel.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Internationalization (I18N) help")));
JLabel lab = new JLabel();
lab.setText("<html><body>"+resourceBundle.getString("The I18N Editor is a tool to edit the properties file used for  internationalization.The features provided in this tool are :  Open and save a properties file , add delete and modify entries in the file , Find entries and also sort them.The EnglishToNative.properties contains a set of entries which are prominent , i.e these are a set which when modified remove all traces of strings like 'WebNMS'  ,'nms-support@india.webnms.com' etc , from the NMS ( strings which are specefic to webnms.) .Since NMS 2.3 these are tagged as PROMINENT .The editor can pluck them out for editing separately.The editor can also be used to convert a text file in the old format  i.e. with ' || ' separator , to a properties file")+"<br><br><font color=red size=4>"+resourceBundle.getString("I18N Resource File Editor ver 1.0    Zoho Corp.")+"<font></body></html>" );	//No I18N	
lab.setOpaque(true);
lab.setBackground(new Color(237,236,171));
panel.setLayout(new BorderLayout());
panel.add(lab,BorderLayout.CENTER);		
setContentPane(panel);
addMouseListener(
						new MouseAdapter(){
							public void mouseClicked(MouseEvent me)
							{

										dispose();

							}
						}
					);
		
		addKeyListener( new KeyAdapter()
						{
								public void keyTyped(KeyEvent ke)
								{
									if(ke.getKeyChar() == ke.VK_ESCAPE)
										{
											dispose();	
										}	
								}
					 	}	
					);
 	 }
	
 }




