// $Id: CombinedAlarmChart.java,v 1.7 2007/07/07 08:05:21 sureshbabu Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details

//<Begin_Version>
//version$3
//<End_Version>
package com.adventnet.nms.alertui.accpanel;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import com.adventnet.nms.severity.*;

import org.jfree.data.*;
import org.jfree.data.category.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.entity.*;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.axis.NumberAxis;
import java.awt.event.*;
import com.adventnet.nms.util.NmsClientUtil;

public class CombinedAlarmChart extends JPanel  implements   ACCPanelChartInterface,com.adventnet.apiutils.ParameterChangeListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	public com.adventnet.nms.alertui.accpanel.BarChart combinedChart = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	//<End_Variable_Declarations>
	private Vector severityList=null;
	private SeverityInfo sevInfo;
	javax.swing.DefaultComboBoxModel defaultModel=null;
	Vector comboVector=null;
	private Font comboFont=null;

	String str="false";
	Vector updatedVector=null;
	SeverityInfo updatedSevInfo=null;
	public JComboBox comboBar = null;
	private JPanel comboPanel = null;
	private boolean addCategoryCombo = false;
	private String defaultCategory = null;
	private String showAllCategory=null;
	private Font font = NmsClientUtil.getFont("DIALOG");
	String type=null;
	String yAxisValue;//sets the y-axis limit initially
	int yValue;

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
	{  yAxisValue= NmsClientUtil.applet.getParameter("SET_YAXIS_LIMIT");//sets the y-axis limit initially
		if(yAxisValue!=null)
		{
			yValue=Integer.parseInt(yAxisValue);
		}
		else
		{
			yValue=0;
		}
		addCategoryCombo = false;
		str = NmsClientUtil.applet.getParameter("SHOW_CATEGORY_BAR_GRAPH");
		if(str != null && str.equalsIgnoreCase("true"))
		{
			addCategoryCombo = true;
			defaultCategory = NmsClientUtil.GetString("nmsclient.accalarmui.totals");
			showAllCategory= NmsClientUtil.GetString("nmsclient.accalarmui.allcategory");
		}
		else
		{
			str="false";
		}
		//<Begin_init>
		if (initialized) return;
		setPreferredSize(new Dimension(getPreferredSize().width+250,getPreferredSize().height+200));
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
		if(str.equalsIgnoreCase("true"))
		{
		actionToBePerformed();
		}
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
			combinedChart.set3DEffect(true);
			combinedChart.setVertical(true);
			combinedChart.setPlotOutlineColor(new Color(-1));
			combinedChart.setBackground(new Color(-1));
			//combinedChart.setLegendOutlineColor(new Color(-1));
			combinedChart.setEnabled(true);
			combinedChart.setTitle((org.jfree.chart.title.TextTitle) null);
		}
		catch(Exception ex)
		{
			showStatus("Exception while setting properties for bean "+combinedChart,ex);
		}

		//<UserCode_Begin_Bean_combinedChart>
		combinedChart.getCategoryPlot().getRangeAxis().setLabel(NmsClientUtil.GetString("nmsclient.accpanel.alarmcount"));
		combinedChart.getCategoryPlot().getDomainAxis().setLabel(null);
		combinedChart.getCategoryPlot().getRangeAxis().setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createIntegerTickUnits());
		((NumberAxis)combinedChart.getCategoryPlot().getRangeAxis()).setRangeType(RangeType.POSITIVE);
		combinedChart.getCategoryPlot().getRangeAxis().setLowerBound(0d);
		((NumberAxis)combinedChart.getCategoryPlot().getRangeAxis()).setAutoRangeStickyZero(true);


		//<UserCode_End_Bean_combinedChart>


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
		combinedChart= new com.adventnet.nms.alertui.accpanel.BarChart();
		initializeParameters();


		//<End_initVariables>

		if(str.equalsIgnoreCase("true"))
		{
			comboFont=NmsClientUtil.getFont("DIALOG");
			comboVector=new Vector();
			comboVector.addElement(defaultCategory);
			comboVector.addElement(showAllCategory);
			defaultModel=new DefaultComboBoxModel(comboVector);
			comboPanel = new JPanel();
			comboBar = new javax.swing.JComboBox(defaultModel);
		}
		severityList=new Vector();
	}
	public void setUpGUI(Container container)
	{
		//<Begin_setUpGUI_Container>
		container.add(Top,BorderLayout.CENTER);
		Top.setLayout(new BorderLayout(0,0));
		Top.add(combinedChart,BorderLayout.CENTER);
		combinedChart.setLayout(new FlowLayout(1,5,5));


		//<End_setUpGUI_Container>


		if(addCategoryCombo)
		{
			comboPanel.setLayout(new GridBagLayout());
			comboPanel.setOpaque(true);
			comboPanel.setBackground(new Color(-1));
			Insets inset = new Insets(0,0,0,0);
			GridBagConstraints cons = new GridBagConstraints();
			setConstraints(cons , -1,-1,1,1,0.0,0.0,cons.CENTER,cons.CENTER,inset,0,0);

			comboPanel.add(comboBar,cons );
			Top.add(comboPanel, BorderLayout.SOUTH);
			comboBar.setBackground(new Color(-1));
			comboBar.setFont(comboFont);
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





	public CombinedAlarmChart()
	{
		//<Begin_CombinedAlarmChart>
		this.init();

		//<End_CombinedAlarmChart>
	}

	public CombinedAlarmChart(java.applet.Applet applet)
	{
		//<Begin_CombinedAlarmChart_java.applet.Applet>
		this.applet = applet;
		this.init();

		//<End_CombinedAlarmChart_java.applet.Applet>
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

	// <Start of Action to be performed on clicking the Item from the Combomenu
	//<Added by Tinku>

	public void actionToBePerformed()
	{
		comboBar.addActionListener(new ActionListener()
				{
				public void actionPerformed(ActionEvent evt)
				{

				updateCounts(updatedVector,updatedSevInfo);
				}
				} );
	}
	// <End of Action to be performed on clicking the Item from the Combomenu

	//<Added by Tinku
	public void updateCombo(Vector v,SeverityInfo i)
	{
		updatedVector=v;
		updatedSevInfo=i;
	}

	// <This method handles to display the category in the combo box>
	public void updateCounts(Vector vec, SeverityInfo sevInfo)
	{
		String category = "";
		if(!addCategoryCombo)
		{
			//category=showAllCategory;
			updateCounts(vec, sevInfo, category);
			return;
		}
		updateCombo (vec, sevInfo);
		category = (String)defaultModel.getSelectedItem();
		Vector vect = new Vector();
		for (Enumeration enumerate = vec.elements(); enumerate.hasMoreElements();)
		{
			Properties prop = (Properties)enumerate.nextElement();
			String ccat = prop.getProperty("category");
			String ctot = prop.getProperty("total");
			if(ccat.equalsIgnoreCase("Totals"))
				ccat=defaultCategory;

			if((category.equalsIgnoreCase(showAllCategory) || ccat.equals(category) || ccat.equals(defaultCategory)) && !ctot.equals("0"))

			{

				vect.addElement(prop);
			}
			if(ccat.equals(category)&&!ctot.equals("0"))
			{

				vect.addElement(prop);
			}

			if(!comboVector.contains(ccat) && !ctot.equals("0") )
			{

				defaultModel.addElement(ccat);
			}
			if(!category.equals(ccat) && ctot.equals("0"))
			{
				defaultModel.removeElement(ccat);
			}
			if(!(category.equalsIgnoreCase(defaultCategory)))
				if(category.equals(ccat) && ctot.equals("0"))
				{
					defaultModel.setSelectedItem(defaultCategory);
					return;
				}
		}
		if(comboVector.size()==1)
			defaultModel.addElement(defaultCategory);

		//defaultModel.setSelectedItem(category);
		updateCounts(vect, sevInfo, category);
	}





	public void updateCounts(Vector vect, SeverityInfo sevInfo,String allCategory)
	{
		boolean dataPresent = false;
		//	int prevValue = 0;
		int prevValue=yValue;
		Hashtable sevVsCount=new Hashtable();
		//NmsClientUtil.dbg("Calling severityProp ");
		try
		{
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for(Enumeration enu = vect.elements();enu.hasMoreElements();)
			{
				Properties severityProp = (Properties) enu.nextElement();
				String category = (String) severityProp.getProperty("category");
				String totalValue=(String)severityProp.getProperty("total");
				if(category == null) break;
				//NmsClientUtil.dbg("severityProp "+severityProp);
				/*commented by show Totals in the bar-graph --Tinku
				  if(category.equals("Totals")||category.equals("Categories-Total"))
				  {
				  continue;
				  }
				 */

				/*Issue ID 18302. Removing alert category from bar chart if the number of alerts (of that category) is zero*/
				if(!allCategory.equals(category) && totalValue.equals("0"))
				{
					continue;
				}
				if(!allCategory.equalsIgnoreCase(defaultCategory))
				{
					//if(category.equals(NmsClientUtil.GetString("Totals"))||category.equals(NmsClientUtil.GetString("Categories-Total")))
                    if(category.equals("Totals")||category.equals("Categories-Total")) // Ticket ID : 23541
                    {
						continue;
					}
				}
				for(Enumeration sevEnu = severityProp.propertyNames();sevEnu.hasMoreElements();)
				{
					String severityString = sevEnu.nextElement().toString();
					if(severityString.equals("category") || severityString.equals("total"))  continue;
					String value = severityProp.getProperty(severityString);
					sevVsCount.put(severityString,value);
				}
				int sevSize=severityList.size();
				for(int i=0;i<sevSize;i++)
				{
					String sevName=severityList.elementAt(i).toString();
					int value=Integer.parseInt(sevVsCount.get(sevName).toString());
					/*
					   if(i == sevInfo.getClear())
					   {
					   continue;
					   }
					 */
					if(value > 0)
					{
						dataPresent = true;
					}
					if(prevValue < value)
					{
						prevValue = value;
					}

					if(category.equalsIgnoreCase("Totals"))
					{
						String setCategory=defaultCategory;
						type=NmsClientUtil.GetString("nmsclient.accalarmui.totalwise");
						dataset.addValue(value, sevName, setCategory+" -"+type);
						//                       dataset.addValue(value, sevName, NmsClientUtil.GetString("nmsclient.accalarmui.totals"));
					}
					else

						dataset.addValue(value, sevName, NmsClientUtil.GetString(category));

					//NmsClientUtil.dbg("Setting values "+value+"  sevName "+sevName+"  category  "+category);
				}
			}

			if(!dataPresent)
			{
				combinedChart.getCategoryPlot().getRangeAxis().setLowerBound(0.0);
			}
			//if(prevValue > 0)
			if(prevValue > yValue)
			{
				combinedChart.getCategoryPlot().getRangeAxis().setUpperBound((new Integer(prevValue)).floatValue());
			}
			else
			{
				//Fix for -1 appearing in empty ACCPanel. Patch integration 19630
				if (yValue != 0)
				{
					combinedChart.getCategoryPlot().getRangeAxis().setUpperBound((new Integer(yValue)).floatValue());
				}
				else
				{
					combinedChart.getCategoryPlot().getRangeAxis().setUpperBound((new Integer(1)).floatValue());
				}
			}

			if ((yValue != 0) || (prevValue > yValue))
			{
				combinedChart.getCategoryPlot().setDataset(dataset);
			}
			else
			{
				combinedChart.getCategoryPlot().setDataset(new DefaultCategoryDataset());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean isHandCursorNeeded(int x, int y)
	{
		boolean needed = false;
		ChartEntity secEntity = combinedChart.getEntityForPoint(x, y);
		if(secEntity != null && secEntity instanceof CategoryItemEntity)
		{
			needed = true;
		}
		return needed;
	}

	public String getCategory(int x, int y)
	{
		String cat = null;
		try
		{
			ChartEntity secEntity = combinedChart.getEntityForPoint(x, y);
			if(secEntity != null && secEntity instanceof CategoryItemEntity)
			{
				CategoryItemEntity section = (CategoryItemEntity) secEntity;
				cat = section.getCategory().toString();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return cat;
	}

	public int getSeverity(int x, int y)
	{
		int sev = -1;
		try
		{
			ChartEntity secEntity = combinedChart.getEntityForPoint(x, y);
			if(secEntity != null && secEntity instanceof CategoryItemEntity)
			{
				CategoryItemEntity section = (CategoryItemEntity) secEntity;
				int series = section.getSeries();
				String sevName=(String)severityList.elementAt(series);
				sev = NmsClientUtil.severityInfo.getValue(sevName);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sev;
	}

	public Component getOriginalComponent()
	{
		return combinedChart;
	}
	public void initialize(Vector v){
		this.severityList=v;
		CategoryItemRenderer rend = combinedChart.getCategoryPlot().getRenderer();
		int sevSize=v.size();
		for(int i=0;i<sevSize;i++){
			String severity=v.elementAt(i).toString();
			Color bgColor = NmsClientUtil.severityInfo.getColor(severity);
			if(bgColor!=null){
				rend.setSeriesPaint(i, bgColor);
			}
		}

	}


	public void setConstraints(GridBagConstraints cons , int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY )
	{
		//<Begin_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int>
		//<End_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int>


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

	}

}














