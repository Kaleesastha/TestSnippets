/* $Id: DynamicPanel.java,v 1.9.6.1 2013/06/26 06:39:04 premanand.k Exp $
 */ 
/*
 * DynamicPanel.java
 *
 * Created on July 17, 2005, 7:13 AM
 */

package com.adventnet.nms.util;

import javax.swing.plaf.basic.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.text.*;
import javax.swing.text.*;

import com.adventnet.nms.startclient.*;
import com.adventnet.beans.probeans.*;
import com.adventnet.nms.util.ErrorDialog;
/**
 *
 * @author  balachandar
 */
public class DynamicPanel extends javax.swing.JPanel implements ItemListener {
    
    private String timeZoneId = null;
    SearchPanel deviceQuery;
    int counter = 0;
    private Vector dataTypeVector = new Vector();
    private Vector headingVector = new Vector();
    private String[] typeNames = null;
    private String[] dateTypes = null;
    boolean needDateComp = false;
    Insets inset;
    GridBagConstraints cons = new GridBagConstraints();
    Component comp = new DateTimeComponent();
    Vector searchHistoryVec = new Vector();
    private ExtendedBasicComboBoxUI xCombo= null;
    JTextComponent editor;
    /**
     * Creates new form DynamicPanel 
     */
    public DynamicPanel() {
        initComponents();
        //setDataForHeader(headerVector);
       
    }
    
    /* Added by Balachandar */
    public DynamicPanel(SearchPanel deviceQuery,Vector headerVector,int count,String timeZoneId,Vector searchHistory)
    {
        
        this.deviceQuery = deviceQuery;
        this.timeZoneId = timeZoneId;
        counter = count;
        this.searchHistoryVec = searchHistory;
        setDataForHeader(headerVector);
        counter++;
    }
    
    /*Added by Balachandar */
    public DynamicPanel(SearchPanel deviceQuery,Vector typeVector,Vector headerVector,int count , String  timeZoneId,Vector searchHistory)
    {
        this.timeZoneId = timeZoneId;
        this.deviceQuery = deviceQuery;
        initializeStrings();
        dataTypeVector = typeVector;
        headingVector = headerVector;
        counter = count;
        initComponents();
        this.init();
        this.searchHistoryVec = searchHistory;
        setDataForHeader(headerVector);
        counter++;
       // fillCombo();
    }

    public DynamicPanel(SearchPanel deviceQuery,Vector typeVector,Vector headerVector,int count , String  timeZoneId,Vector searchHistory,Vector sKeys,Hashtable operatorKeys)
    {
	    this.timeZoneId = timeZoneId;
	    this.deviceQuery = deviceQuery;
	    initializeStrings();
	    dataTypeVector = typeVector;
	    headingVector = headerVector;
	    counter = count;
	    initComponents();
	    this.init();
	    this.searchHistoryVec = searchHistory;
	    setDataForHeader(headerVector,sKeys,operatorKeys);
	    counter++;
    }
    
    public void init()
    {
        
    }
    
    public void setDataForHeader(Vector headerVector)
    {
       
        int size = headerVector.size();
        
        for(int i=0;i<size;i++)
        {
            headerFieldsCombo.addItem(headerVector.elementAt(i));
        }
        headerFieldsCombo.setSelectedIndex(counter % headerVector.size());
        operatorCombo.setSelectedIndex(0);
    }
    
    public void itemStateChanged(ItemEvent ie)
    {
        
        //operatorCombo.removeAllItems();
        if(ie.getSource() == headerFieldsCombo)
        {
            String selectedItem = (String)headerFieldsCombo.getSelectedItem();
            String type = "String";
            for(int i=0;i<dataTypeVector.size();i++)
            {
                if(selectedItem == headingVector.elementAt(i))
                {
                    type = (String)dataTypeVector.elementAt(i);
                    break;
                }
            }
            if(type.equalsIgnoreCase("Date"))    
            {
                needDateComp = true;
                containerPanel.removeAll();
                containerPanel.add(panelForDateComp,BorderLayout.CENTER);
                containerPanel.updateUI();
                operatorCombo.removeAllItems();
                /*DateFormat df = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a");
                if(timeZoneId != null)
                    df.setTimeZone(TimeZone.getTimeZone(timeZoneId));
                
                long lo = System.currentTimeMillis();
                Date dt = new Date(lo);
                ((ProDateTimeComponent)comp).setDateTime(df.format(dt));*/
                for(int j=0;j<dateTypes.length;j++)
                {
                    operatorCombo.addItem(dateTypes[j]);
                }                
               
            }
            else 
            {
                needDateComp = false;
                containerPanel.remove(panelForDateComp);
                containerPanel.add(criteriaCombo,BorderLayout.CENTER);
                operatorCombo.removeAllItems();
                              
                
                if(searchHistoryVec != null)
                {
                    String searchString = (String)headerFieldsCombo.getSelectedItem();
 	            searchHistoryVec = NmsCustomPanel.getSearchHistory(searchString);
                    containerPanel.remove(criteriaCombo);
                    criteriaCombo = new JComboBox(searchHistoryVec);
                    criteriaCombo.setUI(xCombo);
                    //containerPanel.add(criteriaCombo,1);
                    criteriaCombo.setEditable(true);
                    criteriaCombo.setFont(NmsClientUtil.getFont("DIALOG"));
                    containerPanel.add(criteriaCombo, java.awt.BorderLayout.CENTER);
                
                    containerPanel.updateUI();
                    if(searchHistoryVec.size() != 0)
                    {
                         new AutoCompletion(criteriaCombo);
                    }
                    else
                    {
			JComboBox comboBox=criteriaCombo ;
			ComboBoxModel model=comboBox.getModel();
			editor= (JTextComponent) comboBox.getEditor().getEditorComponent();
			editor.setDocument(new LimitDocument(50)); //Search Text fix
                    }
                 }
                for(int j=0;j<typeNames.length;j++)
                {
                    operatorCombo.addItem(typeNames[j]);
                }
                
                
                     
                            
            }
        }
    }
    
    public void initializeStrings()
    {
        typeNames = new String[8];
        
        typeNames[0] = deviceQuery.getString("starts with");
        typeNames[1] = deviceQuery.getString("doesn't start with");
        typeNames[2] = deviceQuery.getString("ends with");
        typeNames[3] = deviceQuery.getString("doesn't end with");
        typeNames[4] = deviceQuery.getString("contains");
        typeNames[5] = deviceQuery.getString("doesn't contain");
        typeNames[6] = deviceQuery.getString("equals");
        typeNames[7] = deviceQuery.getString("not equals");
        
        dateTypes = new String[4];
        dateTypes[0] = deviceQuery.getString("is before");
    dateTypes[1] = deviceQuery.getString("is after");
        dateTypes[2] = deviceQuery.getString("equals");
        dateTypes[3] = deviceQuery.getString("not equals");              
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        headerFieldsCombo = new javax.swing.JComboBox();
        operatorCombo = new javax.swing.JComboBox();
        containerPanel = new javax.swing.JPanel();
        criteriaCombo = new javax.swing.JComboBox();

        setLayout(new java.awt.BorderLayout());

        setMaximumSize(new java.awt.Dimension(550, 110));
        setPreferredSize(new java.awt.Dimension(560, 40));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        jPanel1.setPreferredSize(new java.awt.Dimension(494, 20));
        headerFieldsCombo.setFont(NmsClientUtil.getFont("DIALOG"));
        headerFieldsCombo.setMinimumSize(new java.awt.Dimension(170, 35));
        headerFieldsCombo.setPreferredSize(new java.awt.Dimension(170, 35));
        headerFieldsCombo.addItemListener(this);
        headerFieldsCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headerFieldsComboActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(headerFieldsCombo, gridBagConstraints);

        operatorCombo.setFont(NmsClientUtil.getFont("DIALOG"));
        operatorCombo.setMinimumSize(new java.awt.Dimension(150, 35));
        operatorCombo.setPreferredSize(new java.awt.Dimension(130, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(operatorCombo, gridBagConstraints);

        containerPanel.setLayout(new java.awt.BorderLayout());

        containerPanel.setBackground(new java.awt.Color(255, 255, 255));
        containerPanel.setBorder(new javax.swing.border.EtchedBorder());
        containerPanel.setOpaque(false);
        containerPanel.setPreferredSize(new java.awt.Dimension(210, 35));
        panelForDateComp.setLayout(new BorderLayout());
        //inset = new Insets(0,0,0,0);
        //setConstraints(0,0,1,1,1.0,1.0,cons.CENTER,cons.BOTH,inset,0,0);
        ((DateTimeComponent)comp).setTheFont(deviceQuery.getFont());
	if(timeZoneId != null)
	{
	    ((DateTimeComponent)comp).setTimeZoneID(timeZoneId);
	}
	panelForDateComp.removeAll();
        panelForDateComp.add((DateTimeComponent)comp,BorderLayout.CENTER);

        // criteriaCombo.setBackground(new java.awt.Color(255, 255, 255));
        criteriaCombo.setEditable(true);
        criteriaCombo.setFont(NmsClientUtil.getFont("DIALOG"));
        criteriaCombo.setOpaque(false);
        criteriaCombo.setPreferredSize(new java.awt.Dimension(210, 24));
        xCombo = new ExtendedBasicComboBoxUI();
        criteriaCombo.setBackground(new Color(-1));
        criteriaCombo.setUI(xCombo);
        containerPanel.add(criteriaCombo, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(containerPanel, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    private void headerFieldsComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headerFieldsComboActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_headerFieldsComboActionPerformed

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

    public void fillCombo()
    {
             for(int i=0;i<8;i++)
        {
            operatorCombo.addItem(typeNames[i]);
        }
        this.updateUI();
     }
    
    public String[] getValues()
    {
        String values[] = new String[3];
        values[0] = (String)headerFieldsCombo.getSelectedItem();
        values[1] = (String)operatorCombo.getSelectedItem();
        if(needDateComp)
        {
            /*Date date1 = ((ProDateTimeComponent)comp).getDate();
            String[] month = {"Jan", "Feb", "Mar","Apr", "May", "Jun", "Jul", "Aug","Sep", "Oct", "Nov", "Dec"};
            int fHour;
            if(((ProDateTimeComponent)comp).getAMPM().equals("PM"))	 
                 fHour = ((ProDateTimeComponent)comp).getHour()-12;	 
             else	 
                 fHour = ((ProDateTimeComponent)comp).getHour();
            
            String temp1 = month[((ProDateTimeComponent)comp).getMonth()-1]+" "+((ProDateTimeComponent)comp).getDay()+","+((ProDateTimeComponent)comp).getYear()+" "+fHour+":"+((ProDateTimeComponent)comp).getMinute()+":"+((ProDateTimeComponent)comp).getSecond()+" "+((ProDateTimeComponent)comp).getAMPM();*///No I18N
            values[2] = ((DateTimeComponent)comp).getDateTime();

        }
        else
        {
            editor = (JTextComponent)criteriaCombo.getEditor().getEditorComponent(); //Search Text fix
            String txt = editor.getText();

            if((txt.trim().length()==0) || txt==null)
            {
                showErrorMsg(java.text.MessageFormat.format(NmsClientUtil.GetString("javaui.search.warningmessage"),new String[] {values[0]}));
                return null;
            } 
            
            values[2] = validateField(txt);
        }
        return values;
    }
    
    private String validateField(String s)
    {
        s = s.trim();
        if((s.equals(""))||(s.length()==0))
        {
            return null;
        }
        return s;
    }
    
    public void setSelectedValues(SearchAttributes att,Hashtable ht)
    {
        headerFieldsCombo.setSelectedItem(att.columnName);
        if(att.type < 20 )
        {
            operatorCombo.setSelectedIndex(att.type % 10);
            //criteriaCombo.setText(att.value);
        }
        else
        {
            operatorCombo.setSelectedIndex(att.type % 20);
            DateFormat df = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a");

            if(timeZoneId != null)
                df.setTimeZone(TimeZone.getTimeZone(timeZoneId));

            Long lo = Long.valueOf(att.value);
            Date dt = new Date(lo.longValue());
            
            ((ProDateTimeComponent)comp).setDateFormat(df.format(dt));
        }

    }
    
    public int getSelectedIndex(String type)
    {
        if(type.equalsIgnoreCase("String"))
            return (operatorCombo.getSelectedIndex() + 10);
        else
            return (operatorCombo.getSelectedIndex() + 20);

    }

    public void setDataForHeader(Vector headerVector,Vector sKeys,Hashtable operatorKeys)
    {
	    for(int i=0;i<headerVector.size();i++)
	    {
		    headerFieldsCombo.addItem(headerVector.elementAt(i));
	    }

	    if(sKeys != null)
	    {
		    int hIndex = headerVector.indexOf(sKeys.elementAt(counter));
		    headerFieldsCombo.setSelectedIndex(hIndex);
		    String st = (String)headerFieldsCombo.getSelectedItem();
		    if(operatorKeys != null)
		    {
			    String operator1 = (String)operatorKeys.get(st);
			    int operatorIndex = 0;
			    int i=0;
			    for(i=0;i<typeNames.length;i++)
			    {
				    if(typeNames[i].equals(operator1))
				    {
					    operatorIndex = i;
				    }
			    }
			    operatorCombo.setSelectedIndex(operatorIndex);
		    }
		    else
		    {
			    operatorCombo.setSelectedIndex(0);
		    }
	    }
	    else
	    {
		    headerFieldsCombo.setSelectedIndex(0);
		    operatorCombo.setSelectedIndex(0);
	    }
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel containerPanel;
    private javax.swing.JComboBox criteriaCombo;
    private javax.swing.JComboBox headerFieldsCombo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox operatorCombo;
    // End of variables declaration//GEN-END:variables
    JPanel panelForDateComp = new JPanel();
    JComboBox headerFieldsCombo1 = new JComboBox();

	private void showErrorMsg(String msg)
    	{	
        	JOptionPane.showMessageDialog(this,msg,NmsClientUtil.GetString("javaui.search.title.invalidentry"),JOptionPane.ERROR_MESSAGE);
    	}
    
    public class LimitDocument extends PlainDocument //Search Text fix
    {
       private int limit;
       private ErrorDialog errorWin = null;
       public char[] parr = null;

       public LimitDocument(int limit)
        {
          super();
          setLimit(limit);
        }
       public final int getLimit()
        {
          return limit;
        }
      public void insertString(int offset, String s, AttributeSet attributeSet)
        {
             if(offset < limit)
              {
                  try{
           	 	super.insertString(offset,s,attributeSet);

               	 	parr = s.toCharArray();
               	 	int j = 0;
               	 	for(int i=0;i<parr.length;i++)
               	 	{
          			j = parr[i];
          	 	if (j == 39)
	       	 	{

        			JOptionPane.showMessageDialog(null, NmsClientUtil.GetString("The character entered is not allowed in Search Dialog"),NmsClientUtil.GetString("Invalid Entry"), JOptionPane.ERROR_MESSAGE);//No Internationalisation
//				editor.selectAll();
                        editor.setText(null);
				break;

			}

			}
            //super.insertString(offset,s,attributeSet);
	       }
	       catch(Exception e){}
	   }
	}

      public final void setLimit(int newValue)
       {
          this.limit = newValue;
       }
    }
}

class ExtendedBasicComboBoxUI extends BasicComboBoxUI
{
    public ExtendedBasicComboBoxUI()
    {
        
    }
    public void installComponents()
    {
        super.installComponents();
        comboBox.setBackground(new Color(255,255,255));
        comboBox.remove(arrowButton);
  
        
    }
 }
