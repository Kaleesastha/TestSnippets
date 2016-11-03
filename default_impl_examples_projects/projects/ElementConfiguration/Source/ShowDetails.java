
//$Id: ShowDetails.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;




import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.adventnet.management.config.*;
import com.adventnet.management.config.xml.*;

public class ShowDetails extends JDialog implements ActionListener, ConfigResponseListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel closePanel = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JPanel labelPanel = null;
	com.adventnet.beans.images.ImageLabel ImageLabel1 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel resultPanel = null;
	javax.swing.JPanel buttonPanel = null;
	javax.swing.JRadioButton Tabular = null;
	javax.swing.JRadioButton NotTabular = null;
	javax.swing.JRadioButton xml = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


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

	public void init()
  {
		//<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+623,getPreferredSize().height+484); 
          setTitle(resourceBundle.getString("ShowDetails"));
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
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
	} 
	public void setUpProperties()
  {
		//<Begin_setUpProperties> 

          try
          {
            JButton1.setText(resourceBundle.getString("Save Result"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            closeButton.setText(resourceBundle.getString("Close"));
            closeButton.setFont(new Font("Dialog",1,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+closeButton,ex); 
          }

//<UserCode_Begin_Bean_closeButton>

//<UserCode_End_Bean_closeButton>

          try
          {
            ImageLabel1.setImageName(resourceBundle.getString("./images/taskresult.jpg"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ImageLabel1,ex); 
          }

//<UserCode_Begin_Bean_ImageLabel1>

//<UserCode_End_Bean_ImageLabel1>

          try
          {
            Tabular.setText(resourceBundle.getString("Tabular"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Tabular,ex); 
          }

//<UserCode_Begin_Bean_Tabular>

//<UserCode_End_Bean_Tabular>

          try
          {
            NotTabular.setText(resourceBundle.getString("Text"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NotTabular,ex); 
          }

//<UserCode_Begin_Bean_NotTabular>

//<UserCode_End_Bean_NotTabular>

          try
          {
            xml.setText(resourceBundle.getString("XML"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+xml,ex); 
          }

//<UserCode_Begin_Bean_xml>

//<UserCode_End_Bean_xml>
		resultPanel.setPreferredSize(new Dimension(resultPanel.getPreferredSize().width+469,resultPanel.getPreferredSize().height+108));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+10));
		labelPanel.setPreferredSize(new Dimension(labelPanel.getPreferredSize().width+10,labelPanel.getPreferredSize().height+10));
		closeButton.setPreferredSize(new Dimension(closeButton.getPreferredSize().width+2,closeButton.getPreferredSize().height+0));
		closePanel.setPreferredSize(new Dimension(closePanel.getPreferredSize().width+248,closePanel.getPreferredSize().height+0));

  
          //<End_setUpProperties>
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
        closePanel= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        closeButton= new javax.swing.JButton();
        labelPanel= new javax.swing.JPanel();
        ImageLabel1= new com.adventnet.beans.images.ImageLabel(applet);
        JPanel1= new javax.swing.JPanel();
        resultPanel= new javax.swing.JPanel();
        buttonPanel= new javax.swing.JPanel();
        Tabular= new javax.swing.JRadioButton();
        NotTabular= new javax.swing.JRadioButton();
        xml= new javax.swing.JRadioButton();

  
        //<End_initVariables>
	} 
	public void setUpGUI(Container container)
  {
		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(closePanel,BorderLayout.SOUTH);
closePanel.setLayout(new FlowLayout(1,15,5));
closePanel.add(JButton1);
closePanel.add(closeButton);
Top.add(labelPanel,BorderLayout.NORTH);
labelPanel.setLayout(new BorderLayout(5,5));
labelPanel.add(ImageLabel1,BorderLayout.CENTER);
ImageLabel1.setLayout(new FlowLayout(1,5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(resultPanel,BorderLayout.CENTER);
resultPanel.setLayout(new CardLayout(5,5));
JPanel1.add(buttonPanel,BorderLayout.NORTH);
buttonPanel.setLayout(new GridBagLayout());
inset = new Insets(2,2,2,2);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
buttonPanel.add(Tabular,cons);
inset = new Insets(2,2,2,2);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
buttonPanel.add(NotTabular,cons);
inset = new Insets(2,2,2,2);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
buttonPanel.add(xml,cons);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      closeButton.addActionListener(JButton1_JButton1_conn11);
  
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

	private ConfigPanel configPanel = null;

	private ResultXMLPanel resultXMLPanel = null;

	private String xmlString = null;
	private String taskName = null;


	public ShowDetails(ConfigPanel configPanel, String taskName, ConfigResultEvent result)
	{
		super(configPanel.configClientUtils.getParentFrame(configPanel));

		this.configPanel = configPanel;

		applet = configPanel.applet;

		this.taskName = taskName;

		init();

		configInit(result);
	}

	private void configInit(ConfigResultEvent result)
	{
		String[] columnHeader = new String[]{"Device Name", "Identifier", "Result"};

		ObjectInputStream in = null;
		
		try
		{
			in = new ObjectInputStream(new ByteArrayInputStream(result.getResponseData()));

			int length = in.readInt();
			
			byte[] data = new byte[length];

			in.readFully(data, 0, length);

			xmlString = new String(data);
		}catch(IOException ex)
		{
			System.err.println("Error occured while reading the result XML");
		}
		finally
		{
			if(in != null)	
			{
				try{
					in.close();
				}catch(Exception ex){}
			}
		}

		DeviceResult[] deviceResult = result.getConfigResult();

		int length = 0;

		for(int y = 0; y < deviceResult.length; y++)
		{
			length += deviceResult[y].getAttributeResults().length;
		}

		String[][] values = new String[length][3];

		int rowIncrement = 0;

		for(int i = 0 ; i < deviceResult.length; i++)
		{
			String device = deviceResult[i].getDeviceName();

			AttributeResult[] attrResult = deviceResult[i].getAttributeResults();

			for(int j = 0; j < attrResult.length;j++)
			{	
				values[rowIncrement][0] = device;

				values[rowIncrement][1] = attrResult[j].getIdentifier();

				if(attrResult[j].getStatus() == 0)
				{
					values[rowIncrement][2] = "Success";
				}
				else
				{
					values[rowIncrement][2] = "Failure";
				}

				rowIncrement++;
			}
		}
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		setTitle(resourceBundle.getString("Task Results for - ") + taskName);

		closeButton.setMnemonic('C');

		Tabular.setSelected(true);

		resultXMLPanel = new ResultXMLPanel(configPanel, xmlString);

		resultPanel.add(new ResultTablePanel(configPanel, columnHeader, values), "table");
		resultPanel.add(resultXMLPanel, "xml");

		ButtonGroup bg = new ButtonGroup();

		bg.add(xml);
		bg.add(Tabular);
		bg.add(NotTabular);

		xml.addActionListener(this);
		Tabular.addActionListener(this);
		NotTabular.addActionListener(this);

		JButton1.addActionListener(this);

		configPanel.configClientUtils.centerWindow(this);
	}




	public ShowDetails()
  {
		//<Begin_ShowDetails>
    pack();
  
    //<End_ShowDetails>
	}

	public ShowDetails(java.applet.Applet applet)
  {
		//<Begin_ShowDetails_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ShowDetails_java.applet.Applet>
	}


	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  dispose();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>


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
	public void actionPerformed(ActionEvent ae)
	{
		CardLayout layout = (CardLayout)resultPanel.getLayout();

		if ( ae.getSource() == xml )
		{
			resultXMLPanel.setXMLDisplay(true);
			layout.show(resultPanel,"xml");
		}
		else if( ae.getSource() == Tabular )
		{
			layout.show(resultPanel,"table");
		}
		else if ( ae.getSource() == NotTabular)
		{
			resultXMLPanel.setXMLDisplay(false);
			layout.show(resultPanel,"xml");
		}
		else if(ae.getSource() == JButton1)
		{
			saveResultToFile();
		}
	}

	private void saveResultToFile()
	{
		Object fileName = JOptionPane.showInputDialog(this, resourceBundle.getString("Enter the file name to save the result"), resourceBundle.getString("Input Dialog"), JOptionPane.PLAIN_MESSAGE, null, null, taskName+"_result.txt");

		if(fileName != null)		
		{
			Object[] params = {configPanel.userName+"/"+fileName.toString(), xmlString};

			configPanel.configResponseHandler.sendRequest(NmsConfigConstants.SAVE, params, this);
		}
	}

	public void response(ConfigResultEvent configResultEvent)
	{
		String uniqueID = configResultEvent.getRequestID();

		int workID = configResultEvent.getWorkID();

		int errorCode = configResultEvent.getErrorCode();

		if(workID == NmsConfigConstants.SAVE)			
		{
			if(errorCode != NmsConfigConstants.NO_ERROR)
			{
				System.out.println(resourceBundle.getString("error while saving the result - ")+configResultEvent.getErrorString());
			}		
		}
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}









