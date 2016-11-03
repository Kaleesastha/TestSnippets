// $Id: ACCPanel.java,v 1.2 2007/10/19 13:42:51 johnpaul Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details

//<Begin_Version>
//version$5
//<End_Version>
package com.adventnet.nms.alertui.accpanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;

import com.adventnet.nms.startclient.*;

import com.adventnet.nms.util.NmsClientUtil;
import java.net.URL;

public class ACCPanel extends JPanel  implements com.adventnet.apiutils.ParameterChangeListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	com.adventnet.nms.util.CustomImagePanel ImageLabel1 = null;
	com.adventnet.beans.panels.CardPanel chartPanel = null;
	javax.swing.JPanel headPanel = null;
	javax.swing.JPanel radButPanel = null;
	javax.swing.JRadioButton comTabRadBut = null;
	javax.swing.JRadioButton comChaRadBut = null;
	javax.swing.JRadioButton sevChaRadBut = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel titleLabel = null;
	javax.swing.ButtonGroup radButGroup = null;
	javax.swing.JRadioButton catChaRadBut = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	SeverityInfo sevInfo;
	private Hashtable alarmPanelOrigiPanelMapper = new Hashtable();
        private String imageDir=NmsClientUtil.applet.getDocumentBase () +    "../images";
	private Vector removedSeverities=null;
	private Vector selectedSeverities=null;
	private static ACCPanel accPanel;
	String getValues=null;
	String showView=null;

	public static ACCPanel getInstance()

	{

		if(accPanel == null)

		{
			accPanel = new ACCPanel();
		}

		return accPanel;

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
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+300,getPreferredSize().height+300)); 
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
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
        //<End_init>
		initUserProps();
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
            ImageLabel1.setBackgroundImageOption("Scaled");
            ImageLabel1.setBackgroundImage(getImageIcon("alpanel_background.png"));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ImageLabel1,ex); 
          }

//<UserCode_Begin_Bean_ImageLabel1>

	
//<UserCode_End_Bean_ImageLabel1>

          try
          {
            java.lang.String[]  chartPanelcardAndClassNames_array = new java.lang.String[ 3 ]; 
            chartPanelcardAndClassNames_array[ 0 ] = "SeverityChart=com.adventnet.nms.alertui.accpanel.SeverityAlarmChart";
            chartPanelcardAndClassNames_array[ 1 ] = "CombinedChart=com.adventnet.nms.alertui.accpanel.CombinedAlarmChart";
            chartPanelcardAndClassNames_array[ 2 ] = "CombinedTable=com.adventnet.nms.alertui.accpanel.CombinedAlarmTable";
            chartPanel.setCardAndClassNames(chartPanelcardAndClassNames_array);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+chartPanel,ex); 
          }

//<UserCode_Begin_Bean_chartPanel>

	
//<UserCode_End_Bean_chartPanel>

          try
          {
            radButPanel.setPreferredSize(new Dimension(217,20));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+radButPanel,ex); 
          }

//<UserCode_Begin_Bean_radButPanel>

	
//<UserCode_End_Bean_radButPanel>

          try
          {
            comTabRadBut.setVerticalAlignment(1);
            comTabRadBut.setToolTipText("Severity and Category - All Alarms");
            comTabRadBut.setIcon(getImageIcon("alarm_combined_table.png"));
            comTabRadBut.setSelectedIcon(getImageIcon("alarm_combined_table_on.png"));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+comTabRadBut,ex); 
          }

//<UserCode_Begin_Bean_comTabRadBut>
		comTabRadBut.setToolTipText(NmsClientUtil.GetString("nmsclient.accpanel.tooltiptext1"));
	
//<UserCode_End_Bean_comTabRadBut>

          try
          {
            comChaRadBut.setVerticalAlignment(1);
            comChaRadBut.setToolTipText("Severity and Category - Active Alarms");
            comChaRadBut.setIcon(getImageIcon("alarm_combined.png"));
            comChaRadBut.setSelectedIcon(getImageIcon("alarm_combined_on.png"));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+comChaRadBut,ex); 
          }

//<UserCode_Begin_Bean_comChaRadBut>
		comChaRadBut.setToolTipText(NmsClientUtil.GetString("nmsclient.accpanel.tootiptext2"));
	
//<UserCode_End_Bean_comChaRadBut>

          try
          {
            sevChaRadBut.setVerticalAlignment(1);
            sevChaRadBut.setToolTipText("Severity only - Active Alarms");
            sevChaRadBut.setIcon(getImageIcon("alarm_severity.png"));
            sevChaRadBut.setSelectedIcon(getImageIcon("alarm_severity_on.png"));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sevChaRadBut,ex); 
          }

//<UserCode_Begin_Bean_sevChaRadBut>
		sevChaRadBut.setToolTipText(NmsClientUtil.GetString("nmsclient.accpanel.tootiptext3"));
	
//<UserCode_End_Bean_sevChaRadBut>

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

	
//<UserCode_End_Bean_JLabel1>

          try
          {
            titleLabel.setHorizontalAlignment(0);
            titleLabel.setHorizontalTextPosition(0);
            titleLabel.setVerticalTextPosition(0);
            titleLabel.setIconTextGap(0);
            titleLabel.setText("Alarm Summary View");
            titleLabel.setVerticalAlignment(1);
            titleLabel.setIcon(getImageIcon("alpanel_top.png"));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+titleLabel,ex); 
          }

//<UserCode_Begin_Bean_titleLabel>
		titleLabel.setText(NmsClientUtil.GetString("nmsclient.accpanel.title"));
	
//<UserCode_End_Bean_titleLabel>

//<UserCode_Begin_Bean_radButGroup>
		radButGroup.add(comTabRadBut);
		radButGroup.add(comChaRadBut);
		radButGroup.add(catChaRadBut);
		radButGroup.add(sevChaRadBut);

	
//<UserCode_End_Bean_radButGroup>

          try
          {
            catChaRadBut.setToolTipText("Category only");
            catChaRadBut.setVerticalAlignment(1);
            catChaRadBut.setIcon(getImageIcon("alarm_category.png"));
            catChaRadBut.setSelectedIcon(getImageIcon("alarm_category_on.png"));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+catChaRadBut,ex); 
          }

//<UserCode_Begin_Bean_catChaRadBut>
		catChaRadBut.setToolTipText(NmsClientUtil.GetString("nmsclient.accpanel.tooltiptext4"));
	
//<UserCode_End_Bean_catChaRadBut>
		titleLabel.setPreferredSize(new Dimension(titleLabel.getPreferredSize().width+123,titleLabel.getPreferredSize().height+2));
		radButPanel.setPreferredSize(new Dimension(radButPanel.getPreferredSize().width+39,radButPanel.getPreferredSize().height+6));
		headPanel.setPreferredSize(new Dimension(headPanel.getPreferredSize().width+10,headPanel.getPreferredSize().height+18));
		ImageLabel1.setPreferredSize(new Dimension(ImageLabel1.getPreferredSize().width+234,ImageLabel1.getPreferredSize().height+142));

  
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
        ImageLabel1= new com.adventnet.nms.util.CustomImagePanel();
        chartPanel= new com.adventnet.beans.panels.CardPanel(applet);
        headPanel= new javax.swing.JPanel();
        radButPanel= new javax.swing.JPanel();
        comTabRadBut= new javax.swing.JRadioButton();
        comChaRadBut= new javax.swing.JRadioButton();
        sevChaRadBut= new javax.swing.JRadioButton();
        JLabel1= new javax.swing.JLabel();
        titleLabel= new javax.swing.JLabel();
        radButGroup= new javax.swing.ButtonGroup();
        catChaRadBut= new javax.swing.JRadioButton();
        initializeParameters(); 

  
          //<End_initVariables>
	removedSeverities=new Vector();
	selectedSeverities=new Vector();
	}
	public void setUpGUI(Container container)
  {
		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(0,0));
Top.add(ImageLabel1,BorderLayout.CENTER);
ImageLabel1.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,10);
setConstraints(0,0,1,1,1.0,1.0,cons.CENTER,cons.BOTH,inset,0,0);
ImageLabel1.add(chartPanel,cons);
Top.add(headPanel,BorderLayout.NORTH);
headPanel.setLayout(new BorderLayout(0,0));
headPanel.add(radButPanel,BorderLayout.CENTER);
radButPanel.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
radButPanel.add(comTabRadBut,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
radButPanel.add(comChaRadBut,cons);
inset = new Insets(0,0,0,0);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
radButPanel.add(sevChaRadBut,cons);
inset = new Insets(0,0,0,0);
setConstraints(3,0,1,1,1.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
radButPanel.add(JLabel1,cons);
headPanel.add(titleLabel,BorderLayout.NORTH);

  
//<End_setUpGUI_Container>
	}
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      java.awt.event.ActionListener comTabRadBut_radButPanel_conn1 =  new java.awt.event.ActionListener()
  {

     //<TOP_PART>

//<UserCode_Begin_Connection_comTabRadBut_radButPanel_conn>






			//Listener Interface Methods Are Added Below


			public void actionPerformed( java.awt.event.ActionEvent arg0)
			{
				if(comTabRadBut.isSelected())
				{
					//titleLabel.setText("Alarm Count");
					chartPanel.showCard("CombinedTable");
				}
				else if(comChaRadBut.isSelected())
				{
					//titleLabel.setText("Alarm Count");
					chartPanel.showCard("CombinedChart");
				}
				else if(catChaRadBut.isSelected())
				{
					//titleLabel.setText("Alarm Count by Category");
					chartPanel.showCard("CategoryChart");
				}
				else if(sevChaRadBut.isSelected())
				{
					//titleLabel.setText("Alarm Count by Severity");
					chartPanel.showCard("SeverityChart");
				}
			}
		
//<UserCode_End_Connection_comTabRadBut_radButPanel_conn>
};
      comTabRadBut.addActionListener(comTabRadBut_radButPanel_conn1);
      comChaRadBut.addActionListener(comTabRadBut_radButPanel_conn1);
      catChaRadBut.addActionListener(comTabRadBut_radButPanel_conn1);
      sevChaRadBut.addActionListener(comTabRadBut_radButPanel_conn1);
  
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

	public ACCPanel()
  {
		//<Begin_ACCPanel>
    this.init();
  
    //<End_ACCPanel>
		do
		{
			sevInfo = NmsClientUtil.severityInfo;
			try
			{
				Thread.sleep(50);
			}
			catch(InterruptedException ie)
			{
			}
		} while(sevInfo == null);
			}

	public ACCPanel(java.applet.Applet applet)
  {
		//<Begin_ACCPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ACCPanel_java.applet.Applet>
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
	initializeParameters();
  
         //<End_setParameterObject_com.adventnet.apiutils.ParameterObject>
	}
	public void parameterChanged(com.adventnet.apiutils.ParameterObject paramObj)
  {
		//<Begin_parameterChanged_com.adventnet.apiutils.ParameterObject> 

  
  //<End_parameterChanged_com.adventnet.apiutils.ParameterObject>
	}

	public void updateCounts(Vector vect)
	{
		String[] cards = chartPanel.getCardNames();

		//If some severities are removed by users update the count.
		if(removedSeverities.size()>0){
			updateTotal(vect);
		}	
		for(int i=0;i<cards.length;i++)
		{
			ACCPanelChartInterface alarmPanel = (ACCPanelChartInterface) chartPanel.getCard(cards[i]);
			alarmPanel.updateCounts(vect, sevInfo);
		}
	}

	//Change the total value if some severities are removed by user
	private void updateTotal(Vector vect){
		 for(Enumeration enu = vect.elements();enu.hasMoreElements();)
                 {
                       Properties severityProp = (Properties) enu.nextElement();
                       int total=Integer.parseInt(severityProp.getProperty("total"));
		       
		       int count=removedSeverities.size();
 	               for(int i=0;i<count;i++){
				String remSev=removedSeverities.elementAt(i).toString();
				int sevCount=Integer.parseInt(severityProp.getProperty(remSev));
				total=total-sevCount;	
		       }
		       severityProp.put("total",total+"");
		 }
	}

	public void initUserProps()
	{
		ChartMouseListener cml = new ChartMouseListener();
		String[] cards = chartPanel.getCardNames();
		selectedSeverities=getSeverities();
		removedSeverities=getRemovedSeverities();
		for(int i=0;i<cards.length;i++)
		{
			chartPanel.showCard(cards[i]);
			ACCPanelChartInterface alarmPanel = (ACCPanelChartInterface) chartPanel.getCard(cards[i]);
			alarmPanel.initialize(selectedSeverities);
			Component origiPanel = alarmPanel.getOriginalComponent();
			alarmPanelOrigiPanelMapper.put(origiPanel, alarmPanel);
			origiPanel.addMouseListener(cml);
			origiPanel.addMouseMotionListener(cml);
		}
		//chartPanel.showCard("CombinedChart");
		//comChaRadBut.setSelected(true);
		getDefaultDisplay();
	}
	
	//<This method is used to read the parameters "SHOW_ALERT_COUNTS_PANEL".Which ever value is first set for
	//<this parameter that gets displayed as the default view on starting the client -Tinku 
	public void getDefaultDisplay()
	{
	 
	 String getValues= NmsClientUtil.applet.getParameter("SHOW_ALERT_COUNTS_PANEL");
                String showView=getValues.trim().toLowerCase();
                //   System.out.println("chart ="+show);
		if(NmsClientUtil.debugMode)
		{
			System.out.println("SHOW_ALERT_COUNTS_PANEL value :"+showView);//No I18N
		}
                if(showView!=null && !showView.equalsIgnoreCase("all")&& !showView.equals("") && !showView.equalsIgnoreCase("true"))
                {


                        int table=showView.indexOf("table");
                        int pie=showView.indexOf("pie");
                        int bar=showView.indexOf("bar");
                        if(table== -1)
                        {
                                radButPanel.remove(comTabRadBut); //hiding tabular view
                        }
                        else if(table==0)
                        {
                                chartPanel.showCard("CombinedTable");
                                comTabRadBut.setSelected(true);
                        }

                        if(bar==-1)
                        {
                                radButPanel.remove(comChaRadBut); //hiding the bar graph
                        }
                        else if(bar==0)
                        {
                                chartPanel.showCard("CombinedChart");
                                comChaRadBut.setSelected(true);
                        }

                        if(pie==-1)
                        {
                                radButPanel.remove(sevChaRadBut);// hiding the pie graph
                        }
                          else if(pie==0)
                         {
                                chartPanel.showCard("SeverityChart");
                                sevChaRadBut.setSelected(true);

                        }


                }
	 if (showView==null || showView.equals("") || showView.equalsIgnoreCase("all") || showView.equalsIgnoreCase("true"))
                {
                        chartPanel.showCard("CombinedChart");
                        comChaRadBut.setSelected(true);

                }

 	}
	    
	
	//Returns a list of severities removed by users
	private Vector getRemovedSeverities(){
		Vector totalSeverities  = NmsClientUtil.severityInfo.getNames(SeverityInfo.EXCLUDE_NO_CRITICALITY);
		Vector remSeverities=new Vector();
		int count=totalSeverities.size();
		for(int i=0;i<count;i++){
			String severity=totalSeverities.elementAt(i).toString();
			if(!selectedSeverities.contains(severity)){
				remSeverities.add(severity);
			}
		}
		return remSeverities;
	}

	//Returns the list of severities that needs to be shown in the client
	//based on the SEVERITY_NEEDED field.
	private Vector getSeverities(){	
		Vector tempVector  = NmsClientUtil.severityInfo.getNames(SeverityInfo.EXCLUDE_NO_CRITICALITY);
		Vector severityNames = new Vector();
		String sevrNeeded  = NmsClientUtil.applet.getParameter("SEVERITY_NEEDED");
		if(sevrNeeded != null && !sevrNeeded.trim().equalsIgnoreCase("All") && !sevrNeeded.trim().equals(""))
		{
			sevrNeeded = sevrNeeded.trim().toLowerCase();
			boolean notSupport = true;
			boolean isMatched = false;
			for ( int i = 0 ; i < tempVector.size() ; i++)
			{
				String tempSevr = (String)tempVector.elementAt(i);
				String tempSevr2 =tempSevr.trim().toLowerCase();
				int index= sevrNeeded.indexOf(tempSevr2);
				if( index!= -1)
				{
					isMatched = true;
					if(index-1 != -1)
					{
						String notNeeded = sevrNeeded.substring(index-1,index);
						if(notNeeded.equals("!"))
						{
							tempVector.remove(tempSevr);
							continue;
						}
						else
						{
							notSupport =false;
						}
					}
					else
					{
						notSupport =false;
					}
					severityNames.add(tempSevr);
				}

			}
			if(notSupport)
			{
				if(isMatched)
				{
					severityNames=tempVector;
				}
				else
				{
					System.err.println(NmsClientUtil.GetString("Invalid value")+" ("+NmsClientUtil.GetString("Non existing severity")+") "+NmsClientUtil.GetString("given for SEVERITY_NEEDED parameter.Hence severities are excluded in ACC."));
				}
			}
		}
		else
		{
			if(sevrNeeded != null && sevrNeeded.trim().equals(""))
			{
				System.err.println(NmsClientUtil.GetString("Invalid value")+" ("+NmsClientUtil.GetString("empty string")+") "+NmsClientUtil.GetString("given for SEVERITY_NEEDED parameter. Hence all default severities are displayed in ACC."));
			}
			severityNames = tempVector;
		}
		return  severityNames;

	}	

	//Access made 'public' to allow the users override the method - FM Issue ID 1967012
	
	
	
	private void showAlarmPanel(int severity, String category)
	{
		Properties prop = new Properties();
		prop.put("PANEL_NAME","AlertApplet");
		if(severity != -1){
			prop.put("FILTER_SEVERITY", severity+"");
		}
		else if(removedSeverities.size()>0){
			//When few severities are removed by users
			//we should omit the same when total is clicked.
			prop.put("FILTER_STRINGSEVERITY",getSeverityString());
		}	
		if(category != null && !category.equals("all")){
			prop.put("FILTER_CATEGORY", category);
		}
		prop.put("groupViewMode","none");
		prop.put("tobeselected","Alerts");
		java.awt.Event event = new java.awt.Event(this, NmsPanel.CHANGE_PANEL_EVENT,prop);
		NmsPanelEvent nmsevt = new NmsPanelEvent(event);
		NmsClientUtil.getMainPanel().handleNmsPanelEvent(nmsevt);
	}


	//Returns the severity list in the form of a string.
	private String getSeverityString(){
		String severity="";
		int count=selectedSeverities.size();
		for(int i=0;i<count;i++){
			String sevName=selectedSeverities.elementAt(i).toString();
			if(severity.equals("")){
				severity=sevName;
			}
			else{
				severity=severity+","+sevName;
			}
			
		}
		return severity;
	}
	class ChartMouseListener extends MouseAdapter implements MouseMotionListener
	{
		ChartMouseListener()
		{
		}

		public void mouseExited(MouseEvent me)
		{
			setCursor(Cursor.getDefaultCursor());
		}

		public void mousePressed(MouseEvent me)
		{
			if(me.getModifiers() != InputEvent.BUTTON1_MASK || me.getClickCount() != 1)
			{
				return;
			}
			try
			{
				int x = me.getX();
				int y = me.getY();
				Component comp = me.getComponent();
				ACCPanelChartInterface alarmPanel = (ACCPanelChartInterface) alarmPanelOrigiPanelMapper.get(comp);
				String category = alarmPanel.getCategory(x,y);
				int severity = alarmPanel.getSeverity(x,y);
				if(category==null && severity==-1){
				}
				else{
		 //added by Tinku to display the Severity of the Alarms when clicked on the Bar Graph for Totals
		 //                String totalCheck=NmsClientUtil.GetString("nmsclient.accalarmui.totals")+" -"+NmsClientUtil.GetString("nmsclient.accalarmui.totalwise");
		                 
                                     //if(category.equalsIgnoreCase(totalCheck))
                                     if(category.equalsIgnoreCase(NmsClientUtil.GetString("nmsclient.accalarmui.totals")+" -"+NmsClientUtil.GetString("nmsclient.accalarmui.totalwise"))) //No Internationalisation

                                      {
                                        category="all";
                                      }
				 	showAlarmPanel(severity, category);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		public void mouseDragged(MouseEvent me)
		{
		}

		public void mouseMoved(MouseEvent me)
		{
			try
			{
				int x = me.getX();
				int y = me.getY();
				Component comp = me.getComponent();
				ACCPanelChartInterface alarmPanel = (ACCPanelChartInterface) alarmPanelOrigiPanelMapper.get(comp);
				if(alarmPanel.isHandCursorNeeded(x, y))
				{
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				else
				{
					setCursor(Cursor.getDefaultCursor());
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
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
      private ImageIcon getImageIcon(String imageName){
        try{
                URL url= new URL(imageDir+"/"+imageName);
                return NmsClientUtil.getImageIcon(url);
        }
        catch(Exception e){
                System.err.println("Unable to get URL for image "+imageName);
                return null;
        }

}

}














