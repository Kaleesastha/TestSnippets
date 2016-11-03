/*
 * $Id: CollectedGraph.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 *
 * Copyright (c) 2002 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE SUITABILITY
 * OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS
 * SOFTWARE OR ITS DERIVATIVES.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.*;

import com.adventnet.beans.graphs.BarGraph;
import com.adventnet.beans.graphs.LineGraph;
import com.adventnet.nms.poll.corba.PollAPI_CI;
import com.adventnet.nms.poll.corba.PolledData_CI;
import com.adventnet.nms.poll.corba.data_vector;


/**
 * Plots the collected statistics for a TL1 PolledData. plotGraph(PolledData_CI)
 * method should be used to change the PolledData for which collected statistics
 * is to be viewed.
 *
 * @author   Rajesh Purushothaman R.
 * @author   Paul Ponraj S.P.
 * @version  $Revision: 1.1.1.1 $
 *
 * @since    AdventNet Web NMS 2.3 + SP7
 */
public class CollectedGraph extends JDialog 
{
    // Panels.
    private JPanel labelPanel = null;
    private JPanel graphPanel = null;
    private JPanel comboBoxPanel = null;
    private JPanel buttonPanel = null;
    
    // Labels.
    private JLabel agent = null;
    private JLabel statisticName = null;
    private JLabel period = null;
    
    // Graph panel: graph beans.
    private LineGraph lineGraph = null;
    private BarGraph barGraph = null;
    
    // Combo boxes for TL1 Multiple PolledData.
    private JComboBox graphTypeCombo = null;
    private JComboBox componentCombo = null;
    private JComboBox parameterCombo = null;

    // Close button.
    private JButton closeButton = null;
    
    // Listeners.
    private GraphTypeListener graphTypeListener = null;
    private ComponentTypeListener componentTypeListener = null;
    private ParameterTypeListener parameterTypeListener = null;
    
    
    /** 
     * Key: component name as String; Value: List of parameters for
     * corresponding component.
     */
    private Hashtable instanceTable = null;

    private PollAPI_CI pollAPI = null;
    private PolledData_CI pd = null;

    /** Stores start time and end time in case of TL1 Multiple PolledData.*/
    private long startTime = 0;
    private long endTime = 0;


    /**
     * Initializes the collected statistics GUI but keeps it invisible.
     * plotGraph(PolledData_CI) method must be called to plot the graph and make
     * the GUI visible.
     */
    public CollectedGraph(Frame parent, PollAPI_CI pollAPI)
    {
        super(parent, "TL1 Collected Statistics (current day)", true);
        pack();
        this.pollAPI = pollAPI;

        this.setSize(getPreferredSize().width + 554,
                     getPreferredSize().height + 388); 

        initVariables(); 
        setUpGUI(getContentPane()); 
        setUpProperties(); 
        setUpConnections(); 
    }

    private void initVariables()
    {
        labelPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        graphPanel = new JPanel(new CardLayout());
        comboBoxPanel = new JPanel();
        buttonPanel = new JPanel();

        agent = new JLabel();
        statisticName = new JLabel();
        period = new JLabel();

        lineGraph = new LineGraph();
        barGraph = new BarGraph();

        graphTypeCombo = new JComboBox();

        // For TL1 Multiple PolledData.
        componentCombo = new JComboBox();
        parameterCombo = new JComboBox();

        closeButton = new JButton();

        graphTypeListener = new GraphTypeListener();
        componentTypeListener = new ComponentTypeListener();
        parameterTypeListener = new ParameterTypeListener();
    } 

    private void setUpGUI(Container container)
    {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        graphTypeCombo.addItem("Line graph");
        graphTypeCombo.addItem("Bar graph");
        
        labelPanel.add(agent);
        labelPanel.add(statisticName);
        labelPanel.add(period);
        labelPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        graphPanel.add(lineGraph, "Line graph");
        graphPanel.add(barGraph, "Bar graph");
        graphPanel.setBorder(
            BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createBevelBorder(BevelBorder.LOWERED)));

        comboBoxPanel.add(graphTypeCombo);
        comboBoxPanel.add(componentCombo);
        comboBoxPanel.add(parameterCombo);

        buttonPanel.add(closeButton);
        
        container.add(labelPanel);
        container.add(graphPanel);
        container.add(comboBoxPanel);
        container.add(buttonPanel);
    } 

    private void setUpProperties()
    {
        Font scaleFont = new Font("SansSerif", Font.PLAIN, 9);
        Color bgColor = new Color(237, 237, 237);

        try
        {
            lineGraph.setShowxLabel(false);
            lineGraph.setShowyLabel(false);
            lineGraph.setStrokeValue(0.5f);
            lineGraph.setXScalePoints(6);
            lineGraph.setShowXPointsHorizontal(true);
            lineGraph.setAbsoluteTime(true);
            lineGraph.setYScalePoints(10);
            lineGraph.setBgcolor(bgColor);
            lineGraph.setDateFormat(2);
            lineGraph.setXPointColor(Color.black);
            lineGraph.setYPointColor(Color.black);
            lineGraph.setXGrids(10);
            lineGraph.setYGrids(10);
            lineGraph.setXRange(7200000);
            lineGraph.setLinecolor(Color.red);
            lineGraph.setPointColor(Color.blue);
            lineGraph.setShowMarkers(true);
            lineGraph.setShowTitle(false);
            lineGraph.setScaleFont(scaleFont);
        }
        catch (Exception ex)
        {
            showStatus("Exception while setting properties for bean "+lineGraph,
                        ex); 
        }

        try
        {
            barGraph.setShowxLabel(false);
            barGraph.setShowyLabel(false);
            barGraph.setStrokeValue(0.5f);
            barGraph.setXScalePoints(6);
            barGraph.setShowXPointsHorizontal(true);
            barGraph.setAbsoluteTime(true);
            barGraph.setYScalePoints(10);
            barGraph.setBgcolor(bgColor);
            barGraph.setDateFormat(2);
            barGraph.setXPointColor(Color.black);
            barGraph.setYPointColor(Color.black);
            barGraph.setYGrids(10);
            barGraph.setXGrids(10);
            barGraph.setXRange(7200000);
            barGraph.setBarColor(Color.red);
            barGraph.setShowMarkers(true);
            barGraph.setShowTitle(false);
            barGraph.setScaleFont(scaleFont);
        }
        catch (Exception ex)
        {
            showStatus("Exception while setting properties for bean "+barGraph,
                        ex); 
        }

        Font labelFont = new Font("SansSerif", Font.BOLD, 12);

        agent.setForeground(Color.black);
        agent.setFont(labelFont);
        agent.setHorizontalAlignment(SwingConstants.CENTER);
        statisticName.setForeground(Color.black);
        statisticName.setFont(labelFont);
        statisticName.setHorizontalAlignment(SwingConstants.CENTER);
        period.setForeground(Color.black);
        period.setFont(labelFont);
        period.setHorizontalAlignment(SwingConstants.CENTER);

        Font commonFont = new Font("SansSerif", Font.PLAIN, 12);

        graphTypeCombo.setFont(commonFont);
        componentCombo.setFont(commonFont);
        parameterCombo.setFont(commonFont);

        try
        {
            closeButton.setFont(commonFont);
            closeButton.setText("Close");
            closeButton.setMnemonic(KeyEvent.VK_C);
        }
        catch (Exception ex)
        {
            showStatus("Exception while setting properties for bean " 
                        + closeButton, ex); 
        }
    } 

    private void setUpConnections()
    {
        graphTypeCombo.addItemListener(graphTypeListener);
        componentCombo.addItemListener(componentTypeListener);
        closeButton.addActionListener(new CloseButtonListener());
    } 

    private void showStatus(String message)
    {
        System.err.println("Internal Error :"+ message);
    }

    private void showStatus(String message, Exception ex)
    {
        System.err.println("Internal Error :"+ message);
        ex.printStackTrace();
    }

    private long getStartTimeOfDate(Date date)
    {
        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime(date);
        tempCal.set(tempCal.get(Calendar.YEAR), 
                    tempCal.get(Calendar.MONTH),
                    tempCal.get(Calendar.DATE),
                    0, 0, 0);
        Date d = tempCal.getTime();
        return d.getTime();
    }

    private long getEndTimeOfDate(Date date)
    {
        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime(date);
        tempCal.add(Calendar.DATE, 1);
        tempCal.set(tempCal.get(Calendar.YEAR),
                    tempCal.get(Calendar.MONTH),
                    tempCal.get(Calendar.DATE),
                    0, 0, 0);
        Date d = tempCal.getTime();
        return d.getTime();
    }

    private void setMultiple(boolean isMultiple)
    {
        if (isMultiple)
        {
            comboBoxPanel.add(componentCombo, 1);
            comboBoxPanel.add(parameterCombo, 2);
        }
        else
        {
            comboBoxPanel.remove(componentCombo);
            comboBoxPanel.remove(parameterCombo);
        }
    }
    
    private void setInstances(String[] instances)
    {
        if (instances != null && instances.length != 0)
        {
            componentCombo.removeItemListener(componentTypeListener);
            parameterCombo.removeItemListener(parameterTypeListener);
            instanceTable = new Hashtable();	    
            List parameters = null;

            for (int i = 0; i < instances.length; i++)
            {
                int sepIndex = instances[i].indexOf("---");
                if (sepIndex == -1) continue;

                String component = instances[i].substring(0, sepIndex);		
                if (component == null) continue;

                String parameter = instances[i].substring(sepIndex + 3, 
                                                         instances[i].length());

                if (instanceTable.get(component) == null)
                {
                    // If component is not already a key in instanceTable, then
                    // create a List for parameters.
                    componentCombo.addItem(component);
                    parameters = new ArrayList();
                    parameters.add(parameter);
                    instanceTable.put(component, parameters);
                }
                else
                {
                    // If component is already a key in instanceTable, then
                    // add to its existing List of parameters.
                    parameters = (List)instanceTable.get(component);
                    parameters.add(parameter);
                }
            }
            
            parameters = (List)instanceTable.get(componentCombo.getItemAt(0));
            for (Iterator p = parameters.iterator(); p.hasNext(); )
            {
                parameterCombo.addItem(p.next());
            }
            componentCombo.setSelectedIndex(0);
            parameterCombo.setSelectedIndex(0);
            componentCombo.addItemListener(componentTypeListener);
            parameterCombo.addItemListener(parameterTypeListener);
        }
    }

    /**
     * Plots the collected statistics graph for the given PolledData for the
     * current day.
     */
    public boolean plotGraph(PolledData_CI pd)
    {
        if (pd == null) return false;

        this.pd = pd;
        
        agent.setText("Agent: " + pd.getAgent());
        statisticName.setText("Statistic name: " + pd.getName());
        period.setText("Period: " + pd.getPeriod());
        
        String[] in = null;
        try
        {
            if (pd.getIsMultiplePolledData())
            {
                setMultiple(true);

                in = pollAPI.getInstances(pd.getKey());
                if (in == null)
                {
                    JOptionPane.showMessageDialog(null, 
                            "No instances found", "Information Message",
                            JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
                setInstances(in);

                Date d = new Date();
                this.startTime = getStartTimeOfDate(d);
                this.endTime = getEndTimeOfDate(d);

                return plotGraph(in[0]);
            }
            else
            {
                setMultiple(false);
                Calendar cal = Calendar.getInstance();
                String time = String.valueOf(cal.get(Calendar.MONTH) + 1) + "-"
                                + cal.get(Calendar.DAY_OF_MONTH) + "-" 
                                + cal.get(Calendar.YEAR);
                data_vector dv = pollAPI.getCollectedDataForDate_CT(pd.getKey(),
                                                                    time);
                if (dv == null)
                {
                    JOptionPane.showMessageDialog(null, "No data collected", 
                        "Information Message", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }

                setResult(dv.time, dv.values);
                return true;
            }
        }
        catch (Exception exp)
        {
            JOptionPane.showMessageDialog(null, "No data collected: " + exp, 
                    "Information Message", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    private boolean plotGraph(String instance)
    {
        data_vector dv = pollAPI.getCollectedDataForInstance_CT(instance, 
                                            pd.getKey(), startTime, endTime);
        if (dv == null)
        {
            JOptionPane.showMessageDialog(null, "No data collected", 
                    "Information Message", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        setResult(dv.time, dv.values);
        return true;
    }

    private void setResult(long time[], long[] value)
    {
        lineGraph.clearGraph();
        barGraph.clearGraph();

        if (time.length == value.length)
        {
            lineGraph.setMinX(time[0]);
            lineGraph.setStartTime(time[0]);
            lineGraph.setResult(time, value);

            barGraph.setMinX(time[0]);
            barGraph.setStartTime(time[0]);
            barGraph.setResult(time, value);
        }
        else
        {
            System.err.println("Error: Time and value arrays are unequal."); 
            System.err.println("Time.length: " + time.length 
                                + ", Value.length: " + value.length); 
        }
    }


    /** Shows line/bar graph. */
    class GraphTypeListener implements ItemListener
    { 
        public void itemStateChanged(ItemEvent e)
        { 
            if (e.getStateChange() == ItemEvent.DESELECTED) return;
            
            String graphType = (String)e.getItem();
            ((CardLayout)graphPanel.getLayout()).show(graphPanel, graphType);
        } 
    }

    /** 
     * Choose the component of a TL1 Multiple PolledData. Parameter combo box
     * is updated and the first parameter is selected. 
     */
    class ComponentTypeListener implements ItemListener
    {
        public void itemStateChanged(ItemEvent e)
        {
            if (e.getStateChange() == ItemEvent.DESELECTED) return;

            parameterCombo.removeItemListener(parameterTypeListener);
            parameterCombo.removeAllItems();

            String component = (String)e.getItem();

            if (component == null || component.equals("")) return;

            List parameters = (List)instanceTable.get(component);
            if (parameters == null || parameters.size() == 0) return;
                
            for (Iterator i = parameters.iterator(); i.hasNext(); )
            {
                parameterCombo.addItem(i.next());
            }
            parameterCombo.addItemListener(parameterTypeListener);

            plotGraph(component + "---" + parameters.get(0));
        }
    }

    /** 
     * Choose a parameter for corresponding component. Graph is plotted for
     * "&lt;component&gt;-&lt;parameter&gt;". 
     */
    class ParameterTypeListener implements ItemListener
    {
        public void itemStateChanged(ItemEvent e)
        {
            if (e.getStateChange() == ItemEvent.DESELECTED) return;

            String component = (String)componentCombo.getSelectedItem();
            String parameter = (String)parameterCombo.getSelectedItem();
            String instance;

            if (parameter == null || parameter.equals("")) return;

            if (component.equals("") || component == null)
            {
                instance = parameter;
            }
            else
            {
                instance = component + "---" + parameter;
            }

            plotGraph(instance);
        }
    }

    /** Hides the graph window. */
    class CloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            setVisible(false);
            componentCombo.removeAllItems();
            parameterCombo.removeAllItems();
            instanceTable = null;
            pd = null;
        }
    }
}
