//$Id: HelpWindow.java,v 1.1 2006/08/29 13:57:02 build Exp $
 package com.adventnet.security.ui; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class HelpWindow extends JWindow
 
 {
	JLabel lab = null;
	JLabel southlab1 = null;
	JLabel southlab2 = null;
	JLabel southlab3 = null;
	JLabel southlab4 = null;
	JLabel southlab5 = null;
	JPanel panel = null;
	JPanel southpanel = null;	

	private boolean runonce = false;

	HelpWindow() {
	     init(false);
	}
	
	HelpWindow(boolean userView) {
	     init(userView);
	}

	public void init (boolean userView)
 {	
		
		panel = new JPanel();
		southpanel = new JPanel();
		
		southlab1 = new JLabel(AuthMain.resourceBundle.getString(" Not Defined operations"));
		southlab2 = new JLabel(AuthMain.resourceBundle.getString(" Allowed operations"));
		southlab3= new JLabel(AuthMain.resourceBundle.getString("  Disallowed operations"));
		
		southlab1.setIcon(AuthMain.getBuilderUiIfInstance().getImage("circle.png"));
		southlab2.setIcon(AuthMain.getBuilderUiIfInstance().getImage("tick01.png"));
		southlab3.setIcon(AuthMain.getBuilderUiIfInstance().getImage("wrong01.png"));
		
		southlab1.setOpaque(true);
		southlab3.setOpaque(true);
		southlab2.setOpaque(true);
		
		if (userView) southpanel.setLayout(new GridLayout(6,1));
		else southpanel.setLayout(new GridLayout(3,1));

		southpanel.add(southlab1);	
		southpanel.add(southlab2);
		southpanel.add(southlab3);
		
		if (userView) {
			southlab4= new JLabel(AuthMain.resourceBundle.getString("  Allowed Operation (Inherited from the Group)"));//NO I18N
			southlab5= new JLabel(AuthMain.resourceBundle.getString("  Disallowed Operation (Inherited from the Group)"));//NO I18N
			southlab4.setIcon(AuthMain.getBuilderUiIfInstance().getImage("tick_disabled.png"));//NO I18N
			southlab5.setIcon(AuthMain.getBuilderUiIfInstance().getImage("cross_disabled.png"));//NO I18N
			southlab4.setOpaque(true);
			southlab5.setOpaque(true);		     
			southpanel.add(southlab4);
			southpanel.add(southlab5);
		}
		
		lab  = new JLabel();
		lab.setOpaque(true);
		panel.setLayout(new BorderLayout());
		panel.add(lab,BorderLayout.CENTER);
 
		panel.add(southpanel, BorderLayout.SOUTH);
		setContentPane(panel);
		
		addFocusListener(new FocusAdapter()
 {
			public void focusLost(java.awt.event.FocusEvent fe)
 {
				dispose();
			}
		} );	

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me)
 {
					dispose();
			}
		} );
		
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke)
 {
				if(ke.getKeyChar() == ke.VK_ESCAPE)
 {
					dispose();
				}
			}
		});
 	}

	public void setVisible(boolean bool)
 {
		if(runonce && bool)
 {
			removeAll();
			dispose();
		}

		super.setVisible(bool);
		
		if(bool)
 {
			runonce = true;
		}
	}
	
	public void display(String text, String bordertext)
 {
		lab.setText(text); 
		panel.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.MatteBorder(2,2,2,2,new Color(-26368)), bordertext,0,2,new Font("Dialog",0,12),Color.black));//NO I18N
	}
 }



