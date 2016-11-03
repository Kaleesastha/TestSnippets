//$Id: ThemeSelector.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.alf.themes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import com.adventnet.nms.util.CustomClassInterface;
import com.adventnet.nms.util.NmsClientUtil;
import javax.swing.table.*;
import javax.swing.event.*;
import java.net.URL;
import com.adventnet.nms.startclient.MainPanel;

public class ThemeSelector extends JDialog implements CustomClassInterface
{

    //<Begin_Variable_Declarations>
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    private boolean running=false;
    javax.swing.JPanel Top = null;
    javax.swing.JPanel JPanel1 = null;
    javax.swing.JLabel JLabel1 = null;
    javax.swing.JPanel JPanel2 = null;
    javax.swing.JPanel JPanel5 = null;
    javax.swing.JScrollPane JScrollPane1 = null;
    javax.swing.JTable table = null;
    javax.swing.JPanel JPanel6 = null;
    javax.swing.JScrollPane JScrollPane2 = null;
    javax.swing.JTextArea JTextArea1 = null;
    javax.swing.JLabel JLabel2 = null;
    javax.swing.JPanel JPanel3 = null;
    javax.swing.JButton JButton2 = null;
    javax.swing.JButton JButton3 = null;
    javax.swing.JButton JButton4 = null;
    javax.swing.table.DefaultTableModel tableModel = null;
    GridBagConstraints cons = new GridBagConstraints();
    Insets inset;
    //<End_Variable_Declarations>
    private boolean invalid = false;

    public void setProperties(Properties [] p)
    {
 	//Added by Balan to avoid Exception when Themes.conf file does not present under conf directory
            try
            {
                URL url = new URL(NmsClientUtil.applet.getDocumentBase()+"../conf/Themes.conf");
                if(!checkForThemeFile(url))
                {
                    System.err.println("Could not find Themes.conf file");
                    return;
                }
            }
            catch(Exception exce)
            {
                exce.printStackTrace();
            }
        //Add Ends

        if(invalid)
            return;
        this.init();
        this.setVisible(true);
    }

    public void setVisible(boolean bl)
    {
        if(invalid)
            return;
        //<Begin_setVisible_boolean>
       	if(bl)
       	{
            init();
            start();
        }
        else
        {
            stop();
        }
        super.setVisible(bl);  
        //<End_setVisible_boolean>
    }

    public void stop()
    {
        //<Begin_stop> 
        if(!running)
            return;
        running=false;  
        //<End_stop>
    } 

    public void start()
    {
        //<Begin_start> 
        if(running)
            return;
        running=true;
        //<End_start>
    } 

    public void init()
    {
        //<Begin_init> 
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+537,getPreferredSize().height+415); 
        setTitle(NmsClientUtil.GetString("Select Theme"));
        Container container = getContentPane();
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
            showStatus("Error in init method",ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
        //<End_init>
	// JButton1.setIcon(com.adventnet.apiutils.Utility.findImage("com/adventnet/gui/plaf/images/contextualhelp.png",applet,true));  
	
	String image = NmsClientUtil.applet.getDocumentBase()+"../images/theme_editor.png";
	JLabel2.setIcon(NmsClientUtil.getImageIcon(image));

	Vector v = new Vector();
	v.add(NmsClientUtil.GetString("Theme Name"));
	tableModel.setDataVector(new Vector(),v);

	table.getSelectionModel().addListSelectionListener(new SelectionListener());
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	table.setDefaultEditor(Object.class,null);
	JTableHeader header = table.getTableHeader();
	header.setPreferredSize(new Dimension(header.getPreferredSize().width,header.getPreferredSize().height+10));

	try
	{
            URL url = new URL(NmsClientUtil.applet.getDocumentBase()+"../conf/Themes.conf");
            AdventNetThemeAPI.getInstance().setThemeConfigFile(url);
	}
	catch(Exception e)
	{
            e.printStackTrace();
        }
	Vector list = AdventNetThemeAPI.getInstance().getThemeFilesListWithDisplayNames();
	Vector data = new Vector();
        Vector vctr = new Vector();
        vctr.add("Default");
        data.add(vctr);
	for(int i=0;i<list.size();i++)
	{
            Vector v1 = new Vector();
            v1.add(list.elementAt(i));
            data.add(v1);
	}
	tableModel.setDataVector(data,v);
	NmsClientUtil.centerWindow(this);
	table.setDefaultRenderer(Object.class,new TableRenderer());
        table.setRowSelectionInterval(0,0);
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
            value = (String)com.adventnet.apiutils.Utility.getParameter(input);
        }    
        if(value == null)
        {
        }
        return value;  
        //<End_getParameter_String>
    } 

    public void setUpProperties()
    {
        //<Begin_setUpProperties> 
        try
        {
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(1));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+JPanel1,ex); 
        }
        try
        {
            JLabel1.setText(NmsClientUtil.GetString("Theme settings"));
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setFont(NmsClientUtil.getFont("DIALOG"));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+JLabel1,ex); 
        }
        try
        {
            table.setModel(tableModel);
            table.setRowHeight(44);
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+table,ex); 
        }
        try
        {
            JPanel6.setPreferredSize(new Dimension(200,77));
            JPanel6.setMinimumSize(new Dimension(200,66));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+JPanel6,ex); 
        }
        try
        {
            JScrollPane2.setBorder(new javax.swing.border.BevelBorder(1));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+JScrollPane2,ex); 
        }
        try
        {
            JTextArea1.setEditable(false);
            JTextArea1.setFont(new Font("Dialog",0,13));
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setLineWrap(true);
            JTextArea1.setBackground(new Color(-52));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+JTextArea1,ex); 
        }
        try
        {
            JLabel2.setBorder(new javax.swing.border.BevelBorder(1));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+JLabel2,ex); 
        }
        try
        {
            JPanel3.setBorder(new javax.swing.border.EtchedBorder(1));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+JPanel3,ex); 
        }
        try
        {
            JButton2.setText(NmsClientUtil.GetString("Apply"));
            JButton2.setFont(NmsClientUtil.getFont("DIALOG"));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+JButton2,ex); 
        }
        try
        {
            JButton3.setText(NmsClientUtil.GetString("Close"));
            JButton3.setFont(NmsClientUtil.getFont("DIALOG"));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+JButton3,ex); 
        }
        try
        {
            JButton4.setText(NmsClientUtil.GetString("Help"));
            JButton4.setFont(NmsClientUtil.getFont("DIALOG"));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+JButton4,ex); 
        }  
        //<End_setUpProperties>
    } 

    public void initVariables()
    {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel6= new javax.swing.JPanel();
        JScrollPane2= new javax.swing.JScrollPane();
        JTextArea1= new javax.swing.JTextArea();
        JLabel2= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        JButton2= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        JButton4= new javax.swing.JButton();
        tableModel= new javax.swing.table.DefaultTableModel();  
        //<End_initVariables>
    } 

    public void setUpGUI(Container container)
    {
        //<Begin_setUpGUI_Container> 
        container.add(Top,BorderLayout.CENTER);
        Top.setLayout(new GridBagLayout());
        inset = new Insets(5,5,5,5);
        setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        Top.add(JPanel1,cons);
        JPanel1.setLayout(new FlowLayout(1,5,5));
        JPanel1.add(JLabel1);
        inset = new Insets(5,5,5,5);
        setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
        Top.add(JPanel2,cons);
        JPanel2.setLayout(new GridBagLayout());
        inset = new Insets(5,5,5,5);
        setConstraints(0,0,1,1,0.8,0.1,cons.CENTER,cons.BOTH,inset,0,0);
        JPanel2.add(JPanel5,cons);
        JPanel5.setLayout(new GridBagLayout());
        inset = new Insets(0,0,0,0);
        setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
        JPanel5.add(JScrollPane1,cons);
        JScrollPane1.getViewport().add(table);
        inset = new Insets(5,5,5,5);
        setConstraints(1,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
        JPanel2.add(JPanel6,cons);
        JPanel6.setLayout(new GridBagLayout());
        inset = new Insets(0,0,5,0);
        setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
        JPanel6.add(JScrollPane2,cons);
        JScrollPane2.getViewport().add(JTextArea1);
        inset = new Insets(0,0,0,0);
        setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        JPanel6.add(JLabel2,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        Top.add(JPanel3,cons);
        JPanel3.setLayout(new FlowLayout(2,5,5));
        JPanel3.add(JButton2);
        JPanel3.add(JButton3);
        JPanel3.add(JButton4);
        //<End_setUpGUI_Container>
    } 
    public void setUpConnections()
    {
        //<Begin_setUpConnections> 
        JButton3_JButton3_conn JButton3_JButton3_conn1 =  new JButton3_JButton3_conn();
        JButton3.addActionListener(JButton3_JButton3_conn1);
        JButton2_JButton2_conn JButton2_JButton2_conn1 =  new JButton2_JButton2_conn();
        JButton2.addActionListener(JButton2_JButton2_conn1);  
        JButton4_JButton4_conn JButton4_JButton4_conn1 =  new JButton4_JButton4_conn();
        JButton4.addActionListener(JButton4_JButton4_conn1);
        //<End_setUpConnections>
    } 
  
    public void showStatus(String message)
    {
        //<Begin_showStatus_String>
        System.out.println(NmsClientUtil.GetString("Internal Error :")+NmsClientUtil.GetString( message));
        //<End_showStatus_String>
    }

    public void showStatus(String message,Exception ex)
    {
        //<Begin_showStatus_String_Exception>
        System.out.println(NmsClientUtil.GetString("Internal Error :")+ NmsClientUtil.GetString(message));
        ex.printStackTrace();
        //<End_showStatus_String_Exception>
    }

    public ThemeSelector()
    {
        super(NmsClientUtil.getParentFrame());
	//Added by Balan to avoid Exception when Themes.conf file does not present under conf directory
            try
            {
                URL url = new URL(NmsClientUtil.applet.getDocumentBase()+"../conf/Themes.conf");
                if(!checkForThemeFile(url))
                {
                    return;
                }
            }
            catch(Exception exce)
            {
                exce.printStackTrace();
            }
        //Add Ends

        String lookAndFeel = UIManager.getLookAndFeel().getName();
        if(!(lookAndFeel.equals("Metal") || lookAndFeel.equals("AdventNet Look And Feel")))
        {
            String msg = NmsClientUtil.GetString("Themes are not supported for this LookAndFeel");
            JOptionPane.showMessageDialog(NmsClientUtil.getFrame(this),msg,NmsClientUtil.GetString("Invalid L & F"),JOptionPane.ERROR_MESSAGE);
            invalid = true;
            return;
        }
        //<Begin_ThemeSelector>
        pack();  
        //<End_ThemeSelector>
    }

    public ThemeSelector(java.applet.Applet applet)
    {
	//Added by Balan to avoid Exception when Themes.conf file does not present under conf directory
            try
            {
                URL url = new URL(NmsClientUtil.applet.getDocumentBase()+"../conf/Themes.conf");
                if(!checkForThemeFile(url))
                {
                    return;
                }
            }
            catch(Exception exce)
            {
                exce.printStackTrace();
            }
        //Add Ends

        //<Begin_ThemeSelector_java.applet.Applet>
        this.applet = applet;
        pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
        //<End_ThemeSelector_java.applet.Applet>
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

    class TableRenderer extends JLabel implements TableCellRenderer
    {
        ImageIcon icon = null;
        public TableRenderer()
        {
            super();
            setOpaque(true);
            icon = NmsClientUtil.getImageIcon(NmsClientUtil.applet.getDocumentBase()+"../images/theme_icon.png");
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if(isSelected)
            {
                //setBackground(table.getSelectionBackground());
                //setForeground(table.getSelectionForeground());
                setBackground(Color.green);
                setForeground(Color.black);
            }
            else
            {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            setIcon(icon);
            setFont(table.getFont());
            setText((String)value);
            return this;
        }
    }

    class SelectionListener implements ListSelectionListener
    {
        public void valueChanged(ListSelectionEvent lse)
        {
            int row = table.getSelectedRow();
            if(row != -1)
            {
                String value = (String)table.getValueAt(row,0);
                String description;
                if(value.equals("Default"))
                    description = NmsClientUtil.GetString("Default metal look and feel");
                else
                    description = AdventNetThemeAPI.getInstance().getThemeDescription(value);
                JTextArea1.setText(description);
            }
        }
    }
    
    private void applyButtonActionPerformed()
    {
        String lookAndFeel = UIManager.getLookAndFeel().getName();
        if(!(lookAndFeel.equals("Metal") || lookAndFeel.equals("AdventNet Look And Feel")))
        {
            String msg = NmsClientUtil.GetString("Themes are not supported for this LookAndFeel");
            JOptionPane.showMessageDialog(NmsClientUtil.getFrame(this),msg,NmsClientUtil.GetString("Invalid L & F"),JOptionPane.ERROR_MESSAGE);
            invalid = true;
            return;
        }
        int row = table.getSelectedRow();
        if(row >= 0)
        {
            String name = (String)table.getValueAt(row,0);
            if(name.equals("Default"))
                AdventNetThemeAPI.getInstance().setMetalTheme();
            else
                AdventNetThemeAPI.getInstance().setThemeWithDisplayName(name);

            SwingUtilities.updateComponentTreeUI(this);
            this.repaint();
            MainPanel main = NmsClientUtil.getMainPanel();
            main.updateAllComponentsUI();
        }
        else
        {
            String msg = NmsClientUtil.GetString("Please select a theme.");
            JOptionPane.showMessageDialog(NmsClientUtil.getFrame(this),msg,NmsClientUtil.GetString("Invalid theme"),JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private void closeButtonActionPerformed()
    {
        this.setVisible(false);
        this.dispose();
    }
 
    //<Begin__class_JButton3_JButton3_conn>
    class JButton3_JButton3_conn implements java.awt.event.ActionListener, java.io.Serializable 
    {
        //<TOP_PART> - Please do not remove this comment or write any custom code above this
        //Listener Interface Methods Are Added Below 
        public void actionPerformed( java.awt.event.ActionEvent arg0)
        {
            closeButtonActionPerformed();
        }
    }
    
    //<End__class_JButton3_JButton3_conn>
    //<Begin__class_JButton2_JButton2_conn>
    class JButton2_JButton2_conn implements java.awt.event.ActionListener, java.io.Serializable 
    {        
        //<TOP_PART> - Please do not remove this comment or write any custom code above this
        //Listener Interface Methods Are Added Below 
        public void actionPerformed( java.awt.event.ActionEvent arg0)
        {
            applyButtonActionPerformed();
        }
    }

    //<End__class_JButton3_JButton3_conn>
    //<Begin__class_JButton2_JButton2_conn>
    class JButton4_JButton4_conn implements java.awt.event.ActionListener, java.io.Serializable 
    {        
        //<TOP_PART> - Please do not remove this comment or write any custom code above this
        //Listener Interface Methods Are Added Below 
        public void actionPerformed( java.awt.event.ActionEvent arg0)
        {
            helpButtonActionPerformed();
        }
    }

    private void helpButtonActionPerformed()
    {
        String doc = NmsClientUtil.getHelpURL("Theme_Details");
        if(doc != null)
            NmsClientUtil.showHelpBasedOnKey("Theme_Details");
        else
            NmsClientUtil.showHelp("Theme_Details","help/developer_guide/designer_tools/theme_manager/theme_manager_intro.html");
    }
    //<End__class_JButton2_JButton2_conn>

	 //Added by Balan to avoid Exception when Themes.conf file does not present under conf directory
    public boolean checkForThemeFile(URL url)
    {
        boolean bRetValue = false;
        int     nResponseCode = -1;
        try
            {
			
		if(url.getProtocol().equalsIgnoreCase("https"))
                      {
                        try
			{
                         Object  objTempArray[] = com.adventnet.nms.util.SSLUtil.getHttpsStreamObject(url);
                         nResponseCode= ((Integer) objTempArray[1]).intValue();
                         objTempArray= null;
			}
			catch(Exception exe)
                        {
                         nResponseCode = -1;
                        }

                      }
                else
                     {
	               java.net.HttpURLConnection httpCon = (java.net.HttpURLConnection) url.openConnection();
                         if(httpCon != null)
                         {
				try
                                    {
                                      nResponseCode =httpCon.getResponseCode();
                                    }
                                    catch(Exception exe)
                                    {
                                     nResponseCode = -1;
                                    } 
                                    httpCon = null;
                         }
                     }
            if( nResponseCode == 200)
                {
                    bRetValue = true;
                }
            else
                {
                    bRetValue = false;
                }
                url = null;
            }
            catch(Exception exce)
            {
                exce.printStackTrace();
                return false;
            }

        return bRetValue;

    }
    //Add Ends
}
