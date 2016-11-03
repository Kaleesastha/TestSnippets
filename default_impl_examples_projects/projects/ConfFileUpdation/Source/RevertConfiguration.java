//$Id: RevertConfiguration.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$2
//<End_Version>
package com.adventnet.nms.tools.confchanges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import com.adventnet.nms.tools.utils.PrintToTextArea;


public class RevertConfiguration extends JPanel implements ActionListener 
{
		//<Begin_Variable_Declarations>
		private boolean initialized = false;
		private java.applet.Applet applet = null;
		private String localePropertiesFileName = "ConfFileUpdationResources";
		static BuilderResourceBundle resourceBundle = null;
		private boolean running=false;
		javax.swing.JPanel Top = null;
		javax.swing.JLabel JLabel2 = null;
		javax.swing.JTextField JTextField1 = null;
		javax.swing.JLabel JLabel1 = null;
		javax.swing.JComboBox JComboBox1 = null;
		javax.swing.JPanel JPanel1 = null;
		javax.swing.JPanel JPanel2 = null;
		javax.swing.JButton clear = null;
		javax.swing.JScrollPane JScrollPane1 = null;
		javax.swing.JTextArea JTextArea1 = null;
		javax.swing.JTextField revertVersion = null;
		GridBagConstraints cons = new GridBagConstraints();
		Insets inset;
		//<End_Variable_Declarations>
    String nmsHome="";
		PrintToTextArea cprint=null;
		PrintToTextArea cprint1=null;
		private boolean showTextField=false;

		public void setInitialized(boolean value)
		{
				initialized = value;
		}

		public void setShowTextField(boolean value)
		{
				showTextField=value;
		}

		public boolean getShowTextField()
		{
				return showTextField;
		}

		public void init()
		{
				//<Begin_init> 
				if(getParameter("RESOURCE_PROPERTIES" ) != null)
				{
                                    localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
				}
				resourceBundle = Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
				if (initialized) return; 
				setPreferredSize(new Dimension(getPreferredSize().width+462,getPreferredSize().height+425)); 
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
						showStatus(resourceBundle.getString("Error in init method"),ex); 
				} 
				// let us set the initialized variable to true so  
				// we dont initialize again even if init is called 
				initialized = true; 


				//<End_init>
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
						value = (String)Utility.getParameter(input);
				}    
				if(value == null)
				{
						if (input.equals("RESOURCE_PROPERTIES")) value = "ConfFileUpdationResources"; 
				}
				return value;


				//<End_getParameter_String>
		} 
		public void setUpProperties()
		{
				//<Begin_setUpProperties> 

				try
				{
						Top.setBorder(new javax.swing.border.LineBorder(new Color(-16764109),1));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
				}

				//<UserCode_Begin_Bean_components>

				//<UserCode_End_Bean_components>

				try
				{
						JLabel2.setHorizontalAlignment(2);
						JLabel2.setFont(new Font("SansSerif",0,12));
						JLabel2.setForeground(new Color(-16777216));
						JLabel2.setHorizontalTextPosition(4);
						JLabel2.setText(resourceBundle.getString("Current Version"));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
				}

				//<UserCode_Begin_Bean_JLabel2>

				//<UserCode_End_Bean_JLabel2>

				try
				{
						JTextField1.setHorizontalAlignment(2);
						JTextField1.setFont(new Font("SansSerif",0,12));
						JTextField1.setEditable(false);
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField1,ex); 
				}

				//<UserCode_Begin_Bean_JTextField1>

				//<UserCode_End_Bean_JTextField1>

				try
				{
						JLabel1.setHorizontalAlignment(2);
						JLabel1.setFont(new Font("SansSerif",0,12));
						JLabel1.setForeground(new Color(-16777216));
						JLabel1.setHorizontalTextPosition(4);
						JLabel1.setText(resourceBundle.getString("Revert To Version"));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
				}

				//<UserCode_Begin_Bean_JLabel1>

				//<UserCode_End_Bean_JLabel1>

				try
				{
						JComboBox1.setFont(new Font("SansSerif",0,12));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JComboBox1,ex); 
				}

				//<UserCode_Begin_Bean_JComboBox1>

				//<UserCode_End_Bean_JComboBox1>

				try
				{
						JPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Log Message"),1,0,new Font("Dialog",0,12),new Color(-16764109)));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
				}

				//<UserCode_Begin_Bean_JPanel1>

				//<UserCode_End_Bean_JPanel1>

				try
				{
						clear.setFont(new Font("SansSerif",0,12));
						clear.setHorizontalTextPosition(4);
						clear.setText(resourceBundle.getString("Clear"));
						clear.setBorder(new javax.swing.border.BevelBorder(0));
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+clear,ex); 
				}

				//<UserCode_Begin_Bean_clear>

				//<UserCode_End_Bean_clear>

				try
				{
						JTextArea1.setWrapStyleWord(true);
						JTextArea1.setEditable(false);
						JTextArea1.setLineWrap(true);
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); 
				}

				//<UserCode_Begin_Bean_JTextArea1>

				//<UserCode_End_Bean_JTextArea1>

				try
				{
						revertVersion.setEditable(false);
				}
				catch(Exception ex)
				{
						showStatus(resourceBundle.getString("Exception while setting properties for bean ")+revertVersion,ex); 
				}

				//<UserCode_Begin_Bean_revertVersion>

				//<UserCode_End_Bean_revertVersion>
				clear.setPreferredSize(new Dimension(clear.getPreferredSize().width+30,clear.getPreferredSize().height+6));


				//<End_setUpProperties>


		}
		public void setLogInfo()
		{

                    File log=new File(nmsHome+File.separator+"logs");
				if(!log.exists())
				{
						log.mkdirs();
				}

				try
				{
						File outLog = new File(log,"ConfChange.log");
						File errLog = new File(log,"ConfChangeErr.log");
						cprint = new PrintToTextArea(JTextArea1,outLog);
						cprint1 = new PrintToTextArea(JTextArea1,errLog);
				}
				catch(Exception e)
				{
						System.err.println(	e.getMessage());
				}
				System.setOut(cprint);
				System.setErr(cprint1);

		}

		public void start()
		{
				//<Begin_start> 
				if(running)
						return;
				running=true;


				//<End_start>
		} 
		public void stop()
		{
				//<Begin_stop> 
				if(!running)
						return;
				running=false;


				//<End_stop>
		} 
		public void initVariables()
		{
				//<Begin_initVariables> 
				Top= new javax.swing.JPanel();
				JLabel2= new javax.swing.JLabel();
				JTextField1= new javax.swing.JTextField();
				JLabel1= new javax.swing.JLabel();
				JComboBox1= new javax.swing.JComboBox();
				JPanel1= new javax.swing.JPanel();
				JPanel2= new javax.swing.JPanel();
				clear= new javax.swing.JButton();
				JScrollPane1= new javax.swing.JScrollPane();
				JTextArea1= new javax.swing.JTextArea();
				revertVersion= new javax.swing.JTextField();


				//<End_initVariables>
				clear.addActionListener(this);
				//setLogInfo();
		} 
		public void setUpGUI(Container container)
		{
				//<Begin_setUpGUI_Container> 
				container.add(Top,BorderLayout.CENTER);
				Top.setLayout(new GridBagLayout());
				inset = new Insets(5,5,5,5);
				setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
				Top.add(JLabel2,cons);
				inset = new Insets(5,5,5,5);
				setConstraints(1,0,2,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
				Top.add(JTextField1,cons);
				inset = new Insets(5,5,5,5);
				setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
				Top.add(JLabel1,cons);
				inset = new Insets(5,5,5,5);
				setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
				Top.add(JComboBox1,cons);
				inset = new Insets(5,5,5,5);
				setConstraints(0,2,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
				Top.add(JPanel1,cons);
				JPanel1.setLayout(new BorderLayout(5,5));
				JPanel1.add(JPanel2,BorderLayout.SOUTH);
				JPanel2.setLayout(new FlowLayout(2,10,10));
				JPanel2.add(clear);
				JPanel1.add(JScrollPane1,BorderLayout.CENTER);
				JScrollPane1.getViewport().add(JTextArea1);
				inset = new Insets(5,5,5,5);
				setConstraints(2,1,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
				Top.add(revertVersion,cons);


				//<End_setUpGUI_Container>
		}

		public void showTextField()
		{
				if(getShowTextField())
				{
						JComboBox1.setVisible(false);
						revertVersion.setVisible(true);
						revertVersion.setText(System.getProperty("REVERT_VERSION"));
				}
				else
				{
						JComboBox1.setVisible(true);
						revertVersion.setVisible(false);
				}
		}
		
		public void setUpConnections()
		{
				//<Begin_setUpConnections> 


				//<End_setUpConnections>
		} 




		public void showStatus(String message)
		{
				//<Begin_showStatus_String>
				System.out.println("Internal Error :"+ message);
				//<End_showStatus_String>
		}
		public void showStatus(String message,Exception ex)
		{
				//<Begin_showStatus_String_Exception>
				System.out.println("Internal Error :"+ message);
				ex.printStackTrace();
				//<End_showStatus_String_Exception>
		}












		public RevertConfiguration()
		{
				//<Begin_RevertConfiguration>
				this.init();

				//<End_RevertConfiguration>
		}
		public RevertConfiguration(String home)
		{
				nmsHome=home;
				this.init();
		}
		public RevertConfiguration(java.applet.Applet applet)
		{
				//<Begin_RevertConfiguration_java.applet.Applet>
				this.applet = applet;
				this.init();

				//<End_RevertConfiguration_java.applet.Applet>
		}
		public void addItem(String version)
		{
				JComboBox1.addItem(version);	
		}
		public String getSelectedVersion()
		{
				if(JComboBox1.getSelectedItem()!=null)
				{
						return JComboBox1.getSelectedItem().toString();
				}
				return "";
		}
		/*private Vector getInstalledVersions()
			{
			Vector v=new Vector();
			File f=new File(nmsHome+File.separator+"conf"+File.separator+"backup");
			File[] files=f.listFiles();
			for(int i=0;i<files.length;i++)
			{
			if(files[i].isDirectory())
			{
			v.add(files[i].getName());
			}
			}
			return v;
			}*/
		public void actionPerformed(ActionEvent e)
		{
				if(e.getSource().equals(clear))
				{
                                    JTextArea1.setText("");
				}
		}
		public void setCurrentVersion(String currentVersion,String installedVersion)
		{
				JTextField1.setText(currentVersion);
				JComboBox1.removeAllItems();
				StringTokenizer tok=new StringTokenizer(installedVersion,",");
				Vector data= new Vector(1);
				while(tok.hasMoreTokens())
				{
						data.add(tok.nextToken());
				}
				for(int i=data.size();i>0;i--)
				{
						JComboBox1.addItem(data.elementAt(i-1));
				}
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



		public void setProperties(java.util.Properties props)
		{
				//<Begin_setProperties_java.util.Properties> 


				//<End_setProperties_java.util.Properties>
		}
}










