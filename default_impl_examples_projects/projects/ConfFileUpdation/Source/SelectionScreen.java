//$Id: SelectionScreen.java,v 1.2.4.1 2013/06/12 10:54:16 wesley Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$54
//<End_Version>
package com.adventnet.nms.tools.confchanges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SelectionScreen extends JPanel implements ActionListener, FocusListener
{
    //<Begin_Variable_Declarations>
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    private String localePropertiesFileName = "ConfFileUpdationResources";
    static BuilderResourceBundle resourceBundle = null;
    private boolean running=false;
    javax.swing.JPanel Top = null;
    javax.swing.JPanel JPanel1 = null;
    javax.swing.JLabel JLabel2 = null;
    javax.swing.JTextField JTextField1 = null;
    javax.swing.JRadioButton update = null;
    javax.swing.JRadioButton query = null;
    javax.swing.JRadioButton revert = null;
    javax.swing.JTextArea JTextArea1 = null;
    javax.swing.JPanel JPanel2 = null;
    javax.swing.JTextField javahome = null;
    javax.swing.JButton javachome = null;
    javax.swing.JLabel jdkhome = null;
    javax.swing.JLabel JLabel1 = null;
    javax.swing.JSeparator JSeparator2 = null;
    javax.swing.JPanel languagePanel = null;
    javax.swing.JLabel languageLabel = null;
    javax.swing.JComboBox languageCombo = null;
    javax.swing.JLabel JLabel4 = null;
    javax.swing.JTextArea languageText = null;
    javax.swing.JLabel JLabel5 = null;
    javax.swing.JSeparator JSeparator1 = null;
    javax.swing.JLabel JLabel3 = null;
    javax.swing.ButtonGroup grp = null;
    GridBagConstraints cons = new GridBagConstraints();
    Insets inset;
    //<End_Variable_Declarations>
    JFileChooser chooser = null;

    public void init()
    {
				//<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
            localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+642,getPreferredSize().height+591)); 
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
            JPanel1.setBorder(new javax.swing.border.LineBorder(new Color(-16764109),1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
        }

        //<UserCode_Begin_Bean_JPanel1>

			
        //<UserCode_End_Bean_JPanel1>

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
            update.setFont(new Font("SansSerif",0,12));
            update.setHorizontalTextPosition(4);
            update.setHorizontalAlignment(2);
            update.setVerticalAlignment(0);
            update.setVerticalTextPosition(0);
            update.setText(resourceBundle.getString("Update Configuration Changes"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+update,ex); 
        }

        //<UserCode_Begin_Bean_update>

			
        //<UserCode_End_Bean_update>

        try
        {
            query.setFont(new Font("SansSerif",0,12));
            query.setHorizontalTextPosition(4);
            query.setHorizontalAlignment(2);
            query.setVerticalAlignment(0);
            query.setVerticalTextPosition(0);
            query.setText(resourceBundle.getString("Query Details of the Changes"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+query,ex); 
        }

        //<UserCode_Begin_Bean_query>

			
        //<UserCode_End_Bean_query>

        try
        {
            revert.setFont(new Font("SansSerif",0,12));
            revert.setHorizontalTextPosition(4);
            revert.setHorizontalAlignment(2);
            revert.setVerticalAlignment(0);
            revert.setVerticalTextPosition(0);
            revert.setText(resourceBundle.getString("Revert Configuration Changes"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+revert,ex); 
        }

        //<UserCode_Begin_Bean_revert>

			
        //<UserCode_End_Bean_revert>

        try
        {
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setLineWrap(true);
            JTextArea1.setEditable(false);
            JTextArea1.setText(resourceBundle.getString("Tool to update the Configuration files to support the features that are added or Bugs fixed as a part of the Service Pack. Select the Update action to update the configuration files to the latest format. Select Revert option to revert to any of the earlier version. Specify the JDK Home for compiling the JSP files. If JDK Home is not specified JSP files will not be compiled"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); 
        }

        //<UserCode_Begin_Bean_JTextArea1>
        JTextArea1.setBackground(JPanel1.getBackground());
			
        //<UserCode_End_Bean_JTextArea1>

        try
        {
            javachome.setText(resourceBundle.getString("..."));
            javachome.setFont(new Font("dialog",0,12));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+javachome,ex); 
        }

        //<UserCode_Begin_Bean_javachome>

			
        //<UserCode_End_Bean_javachome>

        try
        {
            jdkhome.setText(resourceBundle.getString("JDK HOME"));
            jdkhome.setFont(new Font("dialog",0,12));
            jdkhome.setForeground(new Color(-13434829));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+jdkhome,ex); 
        }

        //<UserCode_Begin_Bean_jdkhome>

			
        //<UserCode_End_Bean_jdkhome>

        try
        {
            JLabel1.setForeground(new Color(-13434829));
            JLabel1.setFont(new Font("dialog",0,12));
            JLabel1.setText(resourceBundle.getString("Specify JDK Home for compiling the JSP files.(Eg: c:\\jdk1.6)"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
        }

        //<UserCode_Begin_Bean_JLabel1>
 
        //<UserCode_End_Bean_JLabel1>

        //<UserCode_Begin_Bean_languagePanel>
        
        languagePanel.setVisible(false);

        String spVersion = System.getProperty("SP_VERSION");
        
        if(spVersion != null && spVersion.equals("AdventNet_Web_NMS-2.3-SP-8"))
        {
            languagePanel.setVisible(true);
        }
        //<UserCode_End_Bean_languagePanel>

        try
        {
            languageLabel.setForeground(new Color(-13434829));
            languageLabel.setFont(new Font("dialog",0,12));
            languageLabel.setText(resourceBundle.getString("Language "));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+languageLabel,ex); 
        }

        //<UserCode_Begin_Bean_languageLabel>

        //<UserCode_End_Bean_languageLabel>

        try
        {
            languageCombo.setFont(new Font("dialog",0,12));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+languageCombo,ex); 
        }

        //<UserCode_Begin_Bean_languageCombo>
        String[] languages={"English","Chinese","Japanese"};
        for(int i=0;i<languages.length;i++)
        {
            languageCombo.addItem(languages[i]);
        }
        //When the system property is set we have to set the same to the combo

        String userGivenLanguage=System.getProperty("LANGUAGE");
        //Usergiven language is selected 
        if(userGivenLanguage!=null)
        {
            for(int j=0;j<languageCombo.getItemCount();j++)
            {
                if(languageCombo.getItemAt(j).toString().equalsIgnoreCase(userGivenLanguage))
                {
                    languageCombo.setSelectedIndex(j);
                    break;
                }
            }
        }
        //<UserCode_End_Bean_languageCombo>

        try
        {
            JLabel4.setText(resourceBundle.getString("               "));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
        }

        //<UserCode_Begin_Bean_JLabel4>

        //<UserCode_End_Bean_JLabel4>

        try
        {
            languageText.setForeground(new Color(-13434829));
            languageText.setFont(new Font("dialog",0,12));
            languageText.setEditable(false);
            languageText.setLineWrap(true);
            languageText.setWrapStyleWord(true);
            languageText.setBackground(new Color(-3355444));
            languageText.setText(resourceBundle.getString("Resource files are bundled in the product for the following languages.Please select the required language."));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+languageText,ex); 
        }

        //<UserCode_Begin_Bean_languageText>

			
        //<UserCode_End_Bean_languageText>

        //<UserCode_Begin_Bean_grp>
        grp.add(update);
        grp.add(revert);
        grp.add(query); 
			
        //<UserCode_End_Bean_grp>
        JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+110,JPanel1.getPreferredSize().height+30));

  
        //<End_setUpProperties>
        JTextArea1.setText(resourceBundle.getString("Configuration Updater updates Configuration files to support all features that are added or Bugs that are fixed in the Service Pack.Select Update option to update the configuration files to the latest format.Select Revert option to revert to any of the earlier version.Select Query option to view the changes made in each configuration file"));
				//JTextArea1.setBorder(new javax.swing.border.EtchedBorder(0));

        grp.setSelected(update.getModel(),true);
        javachome.addActionListener(this);
        javahome.addFocusListener(this);
        String option = (String)System.getProperty("SELECTED_OPTION");
        if(option != null)
        {
            if(option.trim().toLowerCase().equals("update"))
            {
                update.setSelected(true);
                revert.setEnabled(false);
                query.setEnabled(true);
                javahome.setEnabled(true);
                javachome.setEnabled(true);
                jdkhome.setEnabled(true);
                JLabel1.setEnabled(true);
                languageCombo.setEnabled(true);
                languageLabel.setEnabled(true);
                languageText.setEnabled(true);
            }
            else if(option.trim().toLowerCase().equals("revert"))
            {	
                revert.setSelected(true);
                update.setEnabled(false);
                query.setEnabled(false);
                javahome.setEnabled(false);
                javachome.setEnabled(false);
                jdkhome.setEnabled(false);
                JLabel1.setEnabled(false);
                languageCombo.setEnabled(false);
                languageLabel.setEnabled(false);
                languageText.setEnabled(false);
                
            }
            else if(option.trim().toLowerCase().equals("query"))
            {	
                query.setSelected(true);
                revert.setEnabled(false);
                update.setEnabled(false);
                javahome.setEnabled(false);
                javachome.setEnabled(false);
                jdkhome.setEnabled(false);
                JLabel1.setEnabled(false);
                languageCombo.setEnabled(false);
                languageLabel.setEnabled(false);
                languageText.setEnabled(false);
            }
        }
        String jHome = (String)System.getProperty("JAVAHOME");
        if(jHome != null)
        {
            javahome.setText(jHome);
        }
				
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
        JPanel1= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JTextField1= new javax.swing.JTextField();
        update= new javax.swing.JRadioButton();
        query= new javax.swing.JRadioButton();
        revert= new javax.swing.JRadioButton();
        JTextArea1= new javax.swing.JTextArea();
        JPanel2= new javax.swing.JPanel();
        javahome= new javax.swing.JTextField();
        javachome= new javax.swing.JButton();
        jdkhome= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        JSeparator2= new javax.swing.JSeparator();
        languagePanel= new javax.swing.JPanel();
        languageLabel= new javax.swing.JLabel();
        languageCombo= new javax.swing.JComboBox();
        JLabel4= new javax.swing.JLabel();
        languageText= new javax.swing.JTextArea();
        JLabel5= new javax.swing.JLabel();
        JSeparator1= new javax.swing.JSeparator();
        JLabel3= new javax.swing.JLabel();
        grp= new javax.swing.ButtonGroup();

  
        //<End_initVariables>

    } 
    public void setCurrentVersion(String ver)
    {
        JTextField1.setText(ver);
    }
    public void setUpGUI(Container container)
    {
				//<Begin_setUpGUI_Container> 
        container.add(Top,BorderLayout.CENTER);
        Top.setLayout(new BorderLayout(5,5));
        Top.add(JPanel1,BorderLayout.CENTER);
        JPanel1.setLayout(new GridBagLayout());
        inset = new Insets(5,5,5,5);
        setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
        JPanel1.add(JLabel2,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
        JPanel1.add(JTextField1,cons);
        inset = new Insets(5,20,0,5);
        setConstraints(0,3,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
        JPanel1.add(update,cons);
        inset = new Insets(5,20,5,5);
        setConstraints(0,5,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
        JPanel1.add(query,cons);
        inset = new Insets(5,20,0,5);
        setConstraints(0,4,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
        JPanel1.add(revert,cons);
        inset = new Insets(10,5,5,10);
        setConstraints(0,1,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
        JPanel1.add(JTextArea1,cons);
        inset = new Insets(0,5,5,5);
        setConstraints(0,6,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
        JPanel1.add(JPanel2,cons);
        JPanel2.setLayout(new GridBagLayout());
        inset = new Insets(5,5,5,5);
        setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
        JPanel2.add(javahome,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(2,2,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
        JPanel2.add(javachome,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
        JPanel2.add(jdkhome,cons);
        inset = new Insets(5,0,5,5);
        setConstraints(0,1,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        JPanel2.add(JLabel1,cons);
        inset = new Insets(5,0,5,5);
        setConstraints(0,0,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
        JPanel2.add(JSeparator2,cons);
        inset = new Insets(0,5,5,5);
        setConstraints(0,7,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
        JPanel1.add(languagePanel,cons);
        languagePanel.setLayout(new GridBagLayout());
        inset = new Insets(10,5,5,5);
        setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
        languagePanel.add(languageLabel,cons);
        inset = new Insets(10,5,5,5);
        setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
        languagePanel.add(languageCombo,cons);
        inset = new Insets(10,5,5,5);
        setConstraints(2,2,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
        languagePanel.add(JLabel4,cons);
        inset = new Insets(5,0,5,5);
        setConstraints(0,1,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        languagePanel.add(languageText,cons);
        inset = new Insets(0,0,0,0);
        setConstraints(0,3,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
        languagePanel.add(JLabel5,cons);
        inset = new Insets(5,0,5,5);
        setConstraints(0,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        languagePanel.add(JSeparator1,cons);
        inset = new Insets(0,0,0,0);
        setConstraints(0,8,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
        JPanel1.add(JLabel3,cons);

  
        //<End_setUpGUI_Container>
    } 
    public void setUpConnections()
    {
				//<Begin_setUpConnections> 

        update_query_conn update_query_conn1 =  new update_query_conn();
        update.addItemListener(update_query_conn1);
  
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

    public String getSelection()
    {
        if(update.isSelected())
        {
            return "Update";
        }
        else if(revert.isSelected())
        {
            return "Revert";
        }
        else if(query.isSelected())
        {
            return "Query";
        }
        return "";
    }

    public SelectionScreen()
    {
				//<Begin_SelectionScreen>
        this.init();
  
        //<End_SelectionScreen>
    }

    public SelectionScreen(java.applet.Applet applet)
    {
				//<Begin_SelectionScreen_java.applet.Applet>
        this.applet = applet;
        this.init();
  
        //<End_SelectionScreen_java.applet.Applet>
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

    public void actionPerformed( java.awt.event.ActionEvent arg0)
    {
        if(chooser == null)
        {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            chooser.setDialogTitle("Select the JDK Home directory ");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }
        
        int val = chooser.showOpenDialog(this);
        
        if(val == JFileChooser.APPROVE_OPTION)
        {
            javahome.setText(chooser.getSelectedFile().getAbsolutePath());
            System.setProperty("JAVAHOME",javahome.getText());
        }
    } 

		

    public void focusGained(FocusEvent fe)
    {
    }

    public void focusLost(FocusEvent fe)
    {
        System.setProperty("JAVAHOME",javahome.getText().trim());
    }

    /*
    
      //Will be called from Init screen while clicking next button 
      //will be set only for WebNMS 2.3+SP8
                
      void setLanguageToSystem()
      {
      String spVersion=System.getProperty("SP_VERSION");
      if(spVersion.equals("AdventNet_Web_NMS-2.3-SP-8"))
      {
      System.setProperty("LANGUAGE",languageCombo.getSelectedItem().toString());
      }
      }
    
    */


 


    //<Begin__class_update_query_conn>

    class update_query_conn implements java.awt.event.ItemListener, java.io.Serializable 
    {

        //<TOP_PART>
        //<UserCode_Begin_Connection_update_query_conn>

        //Listener Interface Methods Are Added Below 


        public void itemStateChanged( java.awt.event.ItemEvent arg0)
        {
            if(update.isSelected())
            {
                javachome.setEnabled(true);
                javahome.setEnabled(true);
                jdkhome.setEnabled(true);
                JLabel1.setEnabled(true);              
                languageCombo.setEnabled(true);
                languageLabel.setEnabled(true);
                languageText.setEnabled(true);
            }
            if(revert.isSelected() || query.isSelected())
            {
                javachome.setEnabled(false);
                javahome.setEnabled(false);
                jdkhome.setEnabled(false);
                JLabel1.setEnabled(false);
                languageCombo.setEnabled(false);
                languageLabel.setEnabled(false);
                languageText.setEnabled(false);
            }
        }
        //<UserCode_End_Connection_update_query_conn>
    }//<End__class_update_query_conn>
}















