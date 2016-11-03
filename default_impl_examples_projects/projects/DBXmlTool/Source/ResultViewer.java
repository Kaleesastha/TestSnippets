//$Id: ResultViewer.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.db.util;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.MessageFormat;



public class ResultViewer extends JDialog  implements  ActionListener, ListSelectionListener,com.adventnet.apiutils.ParameterChangeListener
{

    //THIS PROJECT CANNOT BE REGENERATED.

    private ResourceBundle  resourceBundle = null;
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "DBXmlToolResources";
    //static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel topPanel = null;
	javax.swing.JLabel titleLabel = null;
	javax.swing.JTextArea helpArea = null;
	javax.swing.JPanel contentPanel = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel bottomPanel = null;
	javax.swing.JButton detailsButton = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JPanel detailsPanel = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


	private Vector resultVector = null;
	private DBXmlToolUI parent = null;  
	private JScrollPane scrollPane2 = null;
	private JEditorPane editorPane = null;
	private Font editorFont = new Font("dialog",Font.PLAIN,10);
	private String operation = "";
	private ImageIcon titleImage = new ImageIcon(getClass().getResource("result_title.jpg"));
  


    public ResultViewer(DBXmlToolUI parent, Vector result, String operation, ResourceBundle bundle)
    {
        super(parent, bundle.getString("Update Operation Audit"));
        this.parent = parent;
        this.operation = operation;
        resourceBundle = bundle;
        init();
        populateUI(result);
        parent.centerWindow(this);        
        setVisible(true);
    }  


 

  public void setVisible(boolean bl)
  {
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
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	//resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+350,getPreferredSize().height+303); 
          setTitle(resourceBundle.getString("ScreenTitle"));
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
          showStatus(resourceBundle.getString("Error in init method"),ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
  
  userInit();
  } 
  public String getParameter(String input)
  {
           //<Begin_getParameter_String> 
	if (po != null) 
	 { 
	   if(po.getParameter(input) != null) 
	     {
	       return (String)po.getParameter(input); 
	     }
	 }
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
            topPanel.setBorder(new javax.swing.border.EtchedBorder(1));
            topPanel.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+topPanel,ex); 
          }

//<UserCode_Begin_Bean_topPanel>

//<UserCode_End_Bean_topPanel>

          try
          {
            titleLabel.setHorizontalAlignment(2);
            titleLabel.setFont(new Font("SansSerif",0,12));
            titleLabel.setForeground(new Color(-16777216));
            titleLabel.setHorizontalTextPosition(4);
            titleLabel.setBackground(new Color(-1));
            titleLabel.setOpaque(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+titleLabel,ex); 
          }

//<UserCode_Begin_Bean_titleLabel>

//<UserCode_End_Bean_titleLabel>

          try
          {
            helpArea.setWrapStyleWord(true);
            helpArea.setEditable(false);
            helpArea.setBackground(new Color(-1));
            helpArea.setLineWrap(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+helpArea,ex); 
          }

//<UserCode_Begin_Bean_helpArea>

//<UserCode_End_Bean_helpArea>

          try
          {
            table.setModel(tableModel);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); 
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

          try
          {
            bottomPanel.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+bottomPanel,ex); 
          }

//<UserCode_Begin_Bean_bottomPanel>

//<UserCode_End_Bean_bottomPanel>

          try
          {
            detailsButton.setFont(new Font("SansSerif",0,12));
            detailsButton.setHorizontalTextPosition(4);
            detailsButton.setText(resourceBundle.getString("Details >>"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+detailsButton,ex); 
          }

//<UserCode_Begin_Bean_detailsButton>

//<UserCode_End_Bean_detailsButton>

          try
          {
            closeButton.setFont(new Font("SansSerif",0,12));
            closeButton.setHorizontalTextPosition(4);
            closeButton.setText(resourceBundle.getString("Close"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+closeButton,ex); 
          }

//<UserCode_Begin_Bean_closeButton>

//<UserCode_End_Bean_closeButton>

          try
          {
            detailsPanel.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+detailsPanel,ex); 
          }

//<UserCode_Begin_Bean_detailsPanel>

//<UserCode_End_Bean_detailsPanel>
		closeButton.setPreferredSize(new Dimension(closeButton.getPreferredSize().width+0,closeButton.getPreferredSize().height+4));
		detailsButton.setPreferredSize(new Dimension(detailsButton.getPreferredSize().width+0,detailsButton.getPreferredSize().height+4));

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
	 if(po == null)
	{
	po = new com.adventnet.apiutils.ParameterObject();
	}
        Top= new javax.swing.JPanel();
        topPanel= new javax.swing.JPanel();
        titleLabel= new javax.swing.JLabel();
        helpArea= new javax.swing.JTextArea();
        contentPanel= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        bottomPanel= new javax.swing.JPanel();
        detailsButton= new javax.swing.JButton();
        closeButton= new javax.swing.JButton();
        detailsPanel= new javax.swing.JPanel();
        tableModel= new javax.swing.table.DefaultTableModel();
        initializeParameters(); 

  
          //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(topPanel,cons);
topPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.0,0.1,cons.NORTHWEST,cons.VERTICAL,inset,0,0);
topPanel.add(titleLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
topPanel.add(helpArea,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(contentPanel,cons);
contentPanel.setLayout(new GridBagLayout());
inset = new Insets(2,2,2,2);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
contentPanel.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(bottomPanel,cons);
bottomPanel.setLayout(new FlowLayout(2,5,5));
bottomPanel.add(detailsButton);
bottomPanel.add(closeButton);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(detailsPanel,cons);
detailsPanel.setLayout(new GridBagLayout());

  
//<End_setUpGUI_Container>
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





  public ResultViewer()
  {
    //<Begin_ResultViewer>
    pack();
  
    //<End_ResultViewer>
  }

  public ResultViewer(java.applet.Applet applet)
  {
    //<Begin_ResultViewer_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ResultViewer_java.applet.Applet>
  } 
  public void setProperties(java.util.Properties props)
  {
         //<Begin_setProperties_java.util.Properties> 
	if(po != null)
	{
	po.setParameters(props);
	}
  
         //<End_setProperties_java.util.Properties>
  } 
  private void initializeParameters()
  {
          //<Begin_initializeParameters> 
	 if(po != null) 
	   {
	    po.addParameterChangeListener(this);
	   }

  
          //<End_initializeParameters>
  } 
  public void destroy()
  {
         //<Begin_destroy> 
	if(po != null)
	{
	 po.removeParameterChangeListener(this);
	}
  
         //<End_destroy>
  } 
  public void setParameterObject(com.adventnet.apiutils.ParameterObject paramObj)
  {
         //<Begin_setParameterObject_com.adventnet.apiutils.ParameterObject> 
	this.po=paramObj;

  
         //<End_setParameterObject_com.adventnet.apiutils.ParameterObject>
  } 
  public void parameterChanged(com.adventnet.apiutils.ParameterObject paramObj)
  {
  //<Begin_parameterChanged_com.adventnet.apiutils.ParameterObject> 

  
  //<End_parameterChanged_com.adventnet.apiutils.ParameterObject>
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
  
  
  private void userInit()
  {
       editorPane = new JEditorPane("text/html","");
       editorPane.setEditable(false);
       scrollPane2 = new JScrollPane(editorPane);
       scrollPane2.setBorder(new javax.swing.border.BevelBorder(1));       
       setConstraints(0,0,1,1,0.1,0.1,cons.NORTHWEST,cons.BOTH,inset,0,0);
       detailsPanel.add(scrollPane2,cons);
	Top.remove(detailsPanel);
	detailsPanel.setPreferredSize(new Dimension(100,125));
	detailsPanel.setMinimumSize(new Dimension(100,125));
	SwingUtilities.updateComponentTreeUI(this);
	detailsButton.setActionCommand("Details");
	detailsButton.addActionListener(this);
	closeButton.addActionListener(this);
        Vector dataVector = new Vector();
        dataVector.add(resourceBundle.getString("User Name"));
        dataVector.add(resourceBundle.getString("Result"));
        tableModel.setDataVector(new Vector(),dataVector);
        table.setRowHeight(20);
        table.setBackground(Color.white);
        table.setDefaultEditor(Object.class,null);
        table.setDefaultRenderer(Object.class,new TableRenderer());
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(this);
        table.getTableHeader().setReorderingAllowed(false);
        titleLabel.setIcon(titleImage);
        helpArea.setText(MessageFormat.format(resourceBundle.getString("The result of the \" {0} \" operation for the various users is shown in the table below"), new Object[] {operation}));
        setTitle(resourceBundle.getString("Update Operation Result"));
  }
  
  public void actionPerformed(ActionEvent ae)
  {
       String command = ae.getActionCommand();
       if(command.equals("Details"))
       {
           if(detailsButton.getText().equals(resourceBundle.getString("Details >>")))
            {
            int height = getSize().height;
            int width = getSize().width;                 
                 	detailsButton.setText(resourceBundle.getString("<< Details"));
            		setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		Top.add(detailsPanel,cons);
	            SwingUtilities.updateComponentTreeUI(this);
	            setSize(width,height+125);
	            setVisible(true);
	handleValueChanged(table.getSelectedRow());
            }
            else
            {
		restoreWindow();
            }
       }
       else
       {
            setVisible(false);
            dispose();
       }
  }
  
  private void showError()
  {
	OperationResult result = (OperationResult)resultVector.elementAt(table.getSelectedRow());
	String user = (String)table.getValueAt(table.getSelectedRow(),0);
	String title = "<b><u><font color="+"#"+"CC0000"+">Update operation Failed for \""+user+"\". Reason :</font></u></b>";
	//title = title+"<br><u>Failure Reason</u>";
	editorPane.setText(title+"<br>     "+result.getExceptionMessage());
  }
  
  private void restoreWindow()
  {
            int height = getSize().height;
            int width = getSize().width;       
            detailsButton.setText(resourceBundle.getString("Details >>"));
	setSize(width,height-125);                 
	Top.remove(detailsPanel);	
	SwingUtilities.updateComponentTreeUI(this);	
	setVisible(true);
  }
  
    public void valueChanged(ListSelectionEvent lse)
    {
         int row = table.getSelectedRow();
         if(row != -1)
         {
              detailsButton.setEnabled(true);
         	handleValueChanged(row);
         }
         else
         {
              detailsButton.setEnabled(false);
	editorPane.setText("");
         }
    }
    
    private void handleValueChanged(int row)
    {
         if(table.getSelectedRow() != -1)
         {
	String value = (String)table.getValueAt(row,1);
	String user = (String)table.getValueAt(row,0);
	if(value.equalsIgnoreCase("Success"))
	{
	     editorPane.setText("<b><u><font color="+"#"+"006600"+">"+ MessageFormat.format(resourceBundle.getString("Update operation successful for : {0}"), new Object[] {user} ) +"</font></u></b>");
	}         
	else
	{
	     detailsButton.setEnabled(true);
	     showError();	     
	}
         }
    }
  
     private void populateUI(Vector resultVector)
    {
        this.resultVector = resultVector;
        int count = resultVector.size();
        for(int i=0;i<count;i++)
        {
            OperationResult result = (OperationResult)resultVector.elementAt(i);
            String userName = result.getUserName();
            String resultString = result.getUpdateOperationResult();
            Vector v = new Vector();
            v.addElement(userName);
            v.addElement(resultString);
            tableModel.addRow(v);
        }
        handleValueChanged(0);        
        table.getSelectionModel().addSelectionInterval(0,0);        
    }
    
        class TableRenderer extends JLabel implements TableCellRenderer
    {

        private Font normalFont = new Font("Dialog",Font.PLAIN,12);
        private Font boldFont = new Font("Dialog",Font.BOLD,12);
        private Color color = new Color(0,111,0);

        public TableRenderer()
        {
            super();
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                      boolean isSelected, boolean hasFocus, 
                                      int row, int column)
        {
            if(isSelected)
            {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            }
            else
            {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            if(column == 1)
            {
                String str = value.toString();
                setHorizontalAlignment(SwingConstants.CENTER);
                if(!str.equalsIgnoreCase("success"))
                {
                    setForeground(Color.red);
                }
                else
                {
                    setForeground(color);
                }
                setFont(boldFont);
            }
            else
            {
                setHorizontalAlignment(SwingConstants.LEFT);
                setForeground(Color.black);
                setFont(normalFont);
            }
            setText(value.toString());
            return this;
        }
    }
}
