//$Id: GetConfigPanel.java,v 1.1.4.1 2012/05/29 06:26:56 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$2
//<End_Version>
package com.adventnet.nms.config;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;




public class GetConfigPanel extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";//No I18N
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel namespaceLbl = null;
	javax.swing.JTextField namespaceTF = null;
	javax.swing.JLabel srcLbl = null;
	javax.swing.JComboBox sourceCombo = null;
	javax.swing.JLabel typeLbl = null;
	javax.swing.JComboBox typeCombo = null;
	javax.swing.JLabel filterLbl = null;
	javax.swing.JTextField filterTF = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>



  

  


  


 

  

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
	if(getParameter("RESOURCE_PROPERTIES" ) != null)//No I18N
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");//No I18N
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);//No I18N
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+425,getPreferredSize().height+348)); 
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
          showStatus(resourceBundle.getString("Error in init method"),ex); //No I18N
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
            if (input.equals("HOST")) value = "localhost"; //No I18N
            if (input.equals("PORT")) value = "161"; //No I18N
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; //No I18N
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            namespaceLbl.setText(resourceBundle.getString("Namespace"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+namespaceLbl,ex); //No I18N
          }

//<UserCode_Begin_Bean_namespaceLbl>

//<UserCode_End_Bean_namespaceLbl>

          try
          {
            srcLbl.setText(resourceBundle.getString("Source "));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+srcLbl,ex); //No I18N
          }

//<UserCode_Begin_Bean_srcLbl>

//<UserCode_End_Bean_srcLbl>

//<UserCode_Begin_Bean_sourceCombo>
sourceCombo.addItem("Startup");//No I18N
sourceCombo.addItem("Running");//No I18N
sourceCombo.addItem("Candidate");//No I18N
//<UserCode_End_Bean_sourceCombo>

          try
          {
            typeLbl.setText(resourceBundle.getString("Filter Type"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+typeLbl,ex); //No I18N
          }

//<UserCode_Begin_Bean_typeLbl>

//<UserCode_End_Bean_typeLbl>

//<UserCode_Begin_Bean_typeCombo>
typeCombo.addItem("xPath String");//No I18N
//typeCombo.addItem("Element");//No I18N - commented as you cannot give element from the gui

//<UserCode_End_Bean_typeCombo>

          try
          {
            filterLbl.setText(resourceBundle.getString("Filter Path"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+filterLbl,ex); //No I18N
          }

//<UserCode_Begin_Bean_filterLbl>

//<UserCode_End_Bean_filterLbl>

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        namespaceLbl= new javax.swing.JLabel();
        namespaceTF= new javax.swing.JTextField();
        srcLbl= new javax.swing.JLabel();
        sourceCombo= new javax.swing.JComboBox();
        typeLbl= new javax.swing.JLabel();
        typeCombo= new javax.swing.JComboBox();
        filterLbl= new javax.swing.JLabel();
        filterTF= new javax.swing.JTextField();

  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(namespaceLbl,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(namespaceTF,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(srcLbl,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(sourceCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(typeLbl,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(typeCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(filterLbl,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(filterTF,cons);

  
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
     System.out.println("Internal Error :"+ message);//No I18N
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);//No I18N
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }





  public GetConfigPanel()
  {
    //<Begin_GetConfigPanel>
    this.init();
  
    //<End_GetConfigPanel>
  }

  public GetConfigPanel(java.applet.Applet applet)
  {
    //<Begin_GetConfigPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_GetConfigPanel_java.applet.Applet>
  } 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
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
  public Vector getRowData()
  {
	  String configOperation = "get-config";//No I18N
	  String configAction = "";//No I18N
	  String namespace = "";//No I18N
	  String configPath = "";//No I18N
	  String subPath = "";//No I18N
	  String configUrl = "";//No I18N
	  String source ="";//No I18N
	  String target ="";//No I18N
	  String sourceUrl="";//No I18N
	  String targetUrl="";//No I18N
	  String defaultAction="none";//No I18N
	  String testOption="";//No I18N
	  String errorOption="";//No I18N
	  String xPathFilter="";//No I18N

	  Vector rowData = new Vector();
	  
	  namespace=namespaceTF.getText();

	  if(sourceCombo.getSelectedItem().equals("Startup"))//No I18N
	  {
		  source = "startup"; //No I18N
	  }
	  else if(sourceCombo.getSelectedItem().equals("Running"))//No I18N
	  {
		  source = "running"; //No I18N
	  }
	  else if(sourceCombo.getSelectedItem().equals("Candidate"))//No I18N
	  {
		  source = "candidate"; //No I18N
	  }
	  
	  if(typeCombo.getSelectedItem().toString().equals("Element"))//No I18N
	  {
		  configPath=filterTF.getText();
	  }
	  else if (typeCombo.getSelectedItem().toString().equals("xPath String"))//No I18N
	  {
		  xPathFilter=filterTF.getText();
	  }


	  rowData.add(configOperation);
	  rowData.add(configAction);
	  rowData.add(namespace);
	  rowData.add(configPath);
	  rowData.add(subPath);
	  rowData.add(configUrl);
	  rowData.add(source);
	  rowData.add(target);
	  rowData.add(sourceUrl);
	  rowData.add(targetUrl);
	  rowData.add(defaultAction);
	  rowData.add(testOption);
	  rowData.add(errorOption);
	  rowData.add(xPathFilter);
	  return rowData;
  }



  public void populateData(Vector rowData) {

	  String namespace =(String)rowData.get(2);
	  String source=(String)rowData.get(6);
	  String configPath=(String)rowData.get(3);
	  String xPathFilter=(String)rowData.get(13);
	  
	  namespaceTF.setText(namespace);
	  
	  if (!source.equals(""))//No I18N
	  {
		  if (source.equals("startup"))//No I18N
		  {
			  sourceCombo.setSelectedItem("Startup");//No I18N
		  }
		  else if (source.equals("candidate"))//No I18N
		  {
			  sourceCombo.setSelectedItem("Candidate");//No I18N
		  }
		  else if (source.equals("running"))//No I18N
		  {
			  sourceCombo.setSelectedItem("Running");//No I18N
		  }
	  }
	  
	  if(!configPath.equals(""))//No I18N
	  {
		  typeCombo.setSelectedItem("Element");//No I18N
		  filterTF.setText(configPath);
	  }
	  else 
	  {
		  typeCombo.setSelectedItem("xPath String");//No I18N
		  filterTF.setText(xPathFilter);
	  }

  }
}





