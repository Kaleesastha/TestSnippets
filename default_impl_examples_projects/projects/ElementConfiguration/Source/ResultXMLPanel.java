
//$Id: ResultXMLPanel.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;
import com.adventnet.management.config.xml.ConfigResult;
import com.adventnet.management.config.xml.DeviceResult;
import com.adventnet.management.config.xml.AttributeResult;


public class ResultXMLPanel extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JEditorPane resultArea = null;
	//<End_Variable_Declarations>

	String resultXML =null;
	String htmlResultXML =  null;

	public ResultXMLPanel()
  {
		//<Begin_ResultXMLPanel>
    this.init();
  
    //<End_ResultXMLPanel>
	}

	public ResultXMLPanel(java.applet.Applet applet)
  {
		//<Begin_ResultXMLPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ResultXMLPanel_java.applet.Applet>
	}



	public ResultXMLPanel(ConfigPanel configPanel, String xml)
	{
		applet = configPanel.applet;

		init();

		resultXML = xml;
		resultArea.setEditable(false);
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
        setPreferredSize(new Dimension(getPreferredSize().width+521,getPreferredSize().height+353)); 
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
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+0,JScrollPane1.getPreferredSize().height+9));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+10));

  
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
        JPanel1= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        resultArea= new javax.swing.JEditorPane();

  
        //<End_initVariables>
	} 
	public void setUpGUI(Container container)
  {
		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(resultArea);

  
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

	void setXMLDisplay(boolean xmlDisplay)
	{
		if ( xmlDisplay )
		{
			resultArea.setContentType("text/plain");
			resultArea.setText(resultXML);
			return;
		}
		if ( htmlResultXML != null )
		{
			resultArea.setContentType("text/html");
			resultArea.setText(htmlResultXML);
			return;
		}
		resultArea.setContentType("text/html");
		StringBuffer sb = new StringBuffer(500);
		try
		{
			ConfigResult configResult = new ConfigResult(resultXML);
			DeviceResult[] deviceResult = configResult.getConfigResult();
			if ( deviceResult == null )	
			{
				System.out.println(resourceBundle.getString(" Result is null"));
				return;
			}
			if ( deviceResult.length == 0 ) 
			{
				System.out.println(resourceBundle.getString(" Device Result is empty"));
				return;
			}
			for ( int i = 0;i < deviceResult.length; ++i)
			{
				if ( deviceResult[i] == null ) continue;
				if ( i != 0 ) sb.append("<br>");
				if ( deviceResult[i].getSubTaskName() != null )
				{
					sb.append("<b><font color=\"#3333FF\">"+resourceBundle.getString("SubTaskName: ")+deviceResult[i].getSubTaskName()+"</font></b><br>");
				}
				sb.append("<b><font color=\"#3333FF\">"+resourceBundle.getString("Device: ")+deviceResult[i].getDeviceName()+"</font></b><br>");
				AttributeResult attrResultArr[] = deviceResult[i].getAttributeResults();
				if ( attrResultArr == null )
				{
					sb.append("Attribute Result is null <br><br>");
					continue;
				}
				for( int j = 0; j < attrResultArr.length; ++j)
				{
					if ( attrResultArr[j] == null ) continue;
					sb.append("<br><font color=\"#3333FF\">"+resourceBundle.getString("Identifier: ")+attrResultArr[j].getIdentifier()+"</font><br>");
					String label = attrResultArr[j].getLabel();
					if (label != null ) sb.append("<font color=\"#3333FF\">"+resourceBundle.getString("Label: ")+ label+"</font><br>");
					String message = attrResultArr[j].getMessage();
					if ( message == null ) continue;
					StringTokenizer  st = new StringTokenizer(message,"\n,\t, ",true);
					sb.append("<font color=\"#009900\">");
					while(st.hasMoreTokens() )
					{
						String token = (String)st.nextToken();
						if ( token.equals("\n"))  
						{
							sb.append("<br>");
							continue;
						}
						if( token.equals("\t")) 
						{
							sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
							continue;
						}
                        if ( token.equals(" "))
                        {
                            sb.append("&nbsp;");
                            continue;
                        }
						sb.append(token);
					}
					sb.append("</font><br>");
					//sb.append("<font color=\"#FF6600\">"+message+"</font><br>");
				}
			}
			htmlResultXML = sb.substring(0);
			resultArea.setText(htmlResultXML);


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
