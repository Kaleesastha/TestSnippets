/* $Id: SearchPanel.java,v 1.13 2010/10/29 13:46:42 swaminathap Exp $
 */
/*
 * SearchPanel.java
 *
 * Created on July 16, 2005, 9:18 PM
 */

package com.adventnet.nms.util;
import com.adventnet.nms.startclient.*;

import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.text.*;
import java.net.*;

/**
 *
 * @author  balachandar
 */
public class SearchPanel extends javax.swing.JDialog implements ActionListener {
    
    JFrame frm;
    private Applet applet = null;
    Searchable searchable;
    SearchAttributes attributes[];
    DynamicPanel MoreFewerPanel1 = null;
    boolean needRadio = true;
    public Vector headingVector = new Vector();
    public Vector typeVector = new Vector();
    int counter = 0;
    String timeZoneId = null;
    GridBagConstraints cons = new GridBagConstraints();
    Insets inset;
    Hashtable moreFewerPanelTracer = new Hashtable();
    String panelName = "";
    DateFormat nmsFormat = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a",new Locale(NmsClientUtil.language,NmsClientUtil.country)); // no i18n
    public Vector headerNames  = new Vector();
    boolean isConfiguration = false;
    JComboBox searchInCombo = null;
    protected Vector searchHistory1 = null;
    protected Properties searchHistoryProp = null;
    protected NmsCustomPanel nmsCustomPanel;
    String cvId = "";
    Hashtable ht = null;
    String cvId1 = "";
    Hashtable operatorHash = new Hashtable();
    Hashtable ht1 = null; 

      /*Added by Balachandar */
    public SearchPanel(JFrame frame,String title,JApplet app)
    {
        super(frame,title);
        this.applet = app;
        frm = frame;      
        searchHistory1 = new Vector();
        searchHistoryProp = new Properties();
        cvId = NmsUiAPI.getCurrentCustomView();	
     }
    
    public void centerWindow(Component comp)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point screenCenter = new Point ((screenSize.width / 2), (screenSize.height / 2));
        int x = screenCenter.x - comp.getSize ().width / 2;
        int y = screenCenter.y - comp.getSize ().height / 2 - 50;
        if (x < 0)
            x = 0;
        if (y < 0)
            y = 0;
       comp.setLocation (x, y);

    }
       
    public void addMoreFewerPanel(boolean isMoreButtonClicked)
    {
        addMoreFewerPanel(null,isMoreButtonClicked);
    }
    
    public void addMoreFewerPanel(SearchAttributes att,boolean isMoreButtonClicked)
    {
        if(needRadio)
        {
		if(ht.isEmpty())
		{
			MoreFewerPanel1 = new DynamicPanel(this,typeVector,headingVector, counter,timeZoneId,searchHistory1);
		}
		else
		{
			if(isMoreButtonClicked)
			{
				MoreFewerPanel1 = new DynamicPanel(this,typeVector,headingVector, counter,timeZoneId,searchHistory1);
			}
			else
			{
				Vector v = (Vector)ht.get(cvId);
				Hashtable ht1 = (Hashtable)operatorHash.get(cvId);
				MoreFewerPanel1 = new DynamicPanel(this,typeVector,headingVector,counter,timeZoneId,searchHistory1,v,ht1);
			}
		}
		if(att != null)
		{
			MoreFewerPanel1.setSelectedValues(att,ht);
		}		 
        }
        else
        {
            MoreFewerPanel1 = new DynamicPanel(this,headingVector,counter,timeZoneId,searchHistory1);
        }
             
        inset = new Insets(0,0,0,0);
        setConstraints(0,counter,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        jPanel4.add(MoreFewerPanel1,cons);
        //inset = new Insets(0,0,0,0);
        //setConstraints(0,counter+2,1,0,0.0,0.0,cons.NORTH,cons.HORIZONTAL,inset,0,0);
        jPanel4.updateUI();
        counter++;
        moreFewerPanelTracer.put("" +counter,MoreFewerPanel1);//No Internationalisation
        if(counter ==1)
        {
            fewerButton.setEnabled(false);
        }
        else if(counter > 1)
        {
            fewerButton.setEnabled(true);
        }

    }
    
       
    public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY)
    {
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
    
     
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        moreButton = new javax.swing.JButton();
        fewerButton = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        matchAllCheckBox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        searchButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(NmsClientUtil.GetString("javaui.security.authaudit.search"));
        setFont(new java.awt.Font("Bitstream Charter", 0, 10));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(690, 250));
        KeyStroke escKeyStroke = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE,0);
        ((JComponent)getRootPane()).registerKeyboardAction(this,"Cancel", escKeyStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setAlignmentX(0.0F);
        jPanel2.setPreferredSize(new java.awt.Dimension(540, 30));
        jLabel1.setPreferredSize(new java.awt.Dimension(530, 40));
        jLabel1.setIcon(getNmsIcon("search_topband.jpg"));
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(new javax.swing.border.EtchedBorder());
        jPanel3.setPreferredSize(new java.awt.Dimension(540, 130));
        jScrollPane1.setBorder(null);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel4.setMinimumSize(new java.awt.Dimension(495, 25));
        jScrollPane1.setViewportView(jPanel4);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel5.setBorder(new javax.swing.border.EtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(540, 40));
        moreButton.setFont(NmsClientUtil.getFont("DIALOG"));
        moreButton.setMnemonic('m');
        moreButton.setText(NmsClientUtil.GetString("javaui.Search.More"));
        moreButton.setActionCommand("more");
        moreButton.addActionListener(this);
        jPanel8.add(moreButton);

        fewerButton.setFont(NmsClientUtil.getFont("DIALOG"));
        fewerButton.setMnemonic('f');
        fewerButton.setText(NmsClientUtil.GetString("javaui.Search.Fewer"));
        fewerButton.setActionCommand("fewer");
        fewerButton.setEnabled(false);
        fewerButton.addActionListener(this);
        fewerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fewerButtonActionPerformed(evt);
            }
        });

        jPanel8.add(fewerButton);

        jPanel5.add(jPanel8, java.awt.BorderLayout.WEST);

        matchAllCheckBox.setFont(NmsClientUtil.getFont("DIALOG"));
        matchAllCheckBox.setMnemonic('a');
        matchAllCheckBox.setSelected(true);
        matchAllCheckBox.setText(NmsClientUtil.GetString("javaui.Search.MatchAll"));
        matchAllCheckBox.setActionCommand("matchAllCriteria");
        jPanel9.add(matchAllCheckBox);

        jPanel5.add(jPanel9, java.awt.BorderLayout.EAST);

        jPanel5.add(jLabel2, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(jPanel5, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel6.setPreferredSize(new java.awt.Dimension(530, 40));
        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        searchButton.setFont(NmsClientUtil.getFont("DIALOG"));
        searchButton.setText(NmsClientUtil.GetString("javaui.Search.Search"));
        searchButton.setActionCommand("search");
        searchButton.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        searchButton.addActionListener(this);
        searchButton.setIcon(getNmsIcon("search.png"));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jPanel7.add(searchButton);

        closeButton.setFont(NmsClientUtil.getFont("DIALOG"));
        closeButton.setText(NmsClientUtil.GetString("javaui.Search.Close"));
        closeButton.setActionCommand("close");
        closeButton.setRolloverEnabled(true);
        closeButton.addActionListener(this);
        closeButton.setIcon(getNmsIcon("close.png"));
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        jPanel7.add(closeButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 60, 0, 60);
        jPanel6.add(jPanel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(jPanel6, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fewerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fewerButtonActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_fewerButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_closeButtonActionPerformed

    private String formTheDate(String appendString, String[] value, String matchAnyOrAll)
    {

        String temp = "";//No Internationalisation
        String returnString = "";//No Internationalisation

        String time1 = "";//No Internationalisation
        String time2 = "";//No Internationalisation

        if(value[2] != null && !value[2].trim().equals(""))//No Internationalisation
        {
            try
            {
                Date dt = nmsFormat.parse(value[2]);
                time2 = String.valueOf(dt.getTime());
                
            }
            catch(Exception p)
            {
                p.printStackTrace();
            }
            if(value[1].equals(getString("equals")))
            {
		    long startTime=Long.parseLong(time2);
		    temp="<between> "+startTime+" and "+(startTime+999);//To search for events between the current time and the time + 1 second but excluding the time+1th second.ie., 10:10:10:000 to 10:10:10:999 //No I18N
            }
            else if(value[1].equals(getString("not equals")))
            {
                temp = "!"+time2;//No Internationalisation
            }
            else if(value[1].equals(getString("is before")))
            {
                temp ="<between> "+ time1 + " and "+time2;//No Internationalisation
            }
            else if(value[1].equals(getString("is after")))
            {
                temp ="<between> "+ time2 + " and "+time1;//No Internationalisation
            }
            }
        else
            return appendString;

        if(matchAnyOrAll.equals("AND"))//No Internationalisation
        {
            if(appendString != null && !(appendString.trim().equals("")))//No Internationalisation
		{
                returnString =appendString+"&&"+temp;//No Internationalisation
		}
            else
                returnString = temp;
        }
        else
        {
            if(appendString != null && !(appendString.trim().equals("")))//No Internationalisation
                returnString = appendString+","+temp;//No Internationalisation
            else
                returnString = temp;
        }
        //JOptionPane.showMessageDialog(null,"returnString "+returnString,"BMSG",JOptionPane.PLAIN_MESSAGE);
        return returnString;
    }

    
    private String  formTheString(String appendString, String[] value, String matchAnyOrAll)
    {

        String temp = "";//No Internationalisation
        String returnString = "";//No Internationalisation
	operatorHash.put(value[0],value[1]);
	nmsCustomPanel.setPanelVsOperatorKey(cvId,operatorHash);

        if(value[2] != null && !value[2].trim().equals(""))//No Internationalisation
        {
            if(value[1].equals(getString("starts with")))
            {
                temp = value[2].trim()+"*";//No Internationalisation
            }
            else if(value[1].equals(getString("doesn't start with")))
            {
                temp = "!"+value[2].trim()+"*";//No Internationalisation
            }
            else if(value[1].equals(getString("ends with")))
            {
                temp ="*"+value[2].trim();//No Internationalisation
            }
            else if(value[1].equals(getString("equals")))
            {
                temp = value[2].trim();
            }
            else if(value[1].equals(getString("not equals")))
            {
                temp = "!"+value[2].trim();//No Internationalisation
            }
            else if(value[1].equals(getString("contains")))
            {
                temp = "*"+value[2].trim()+"*";//No Internationalisation
            }
            else if(value[1].equals(getString("doesn't contain")))
            {
                temp = "!*"+value[2].trim()+"*";//No Internationalisation
            }
            else if(value[1].equals(getString("doesn't end with")))
            {
                temp = "!*"+value[2].trim();//No Internationalisation
            }
        }
        else if(value[2] == null || value[2].trim().equals(""))//No Internationalisation
        {
            temp = "NULL";
         //   return appendString;
        }
        if(matchAnyOrAll.equals("AND"))//No Internationalisation
        {
            if(appendString != null && !(appendString.trim().equals("")))//No Internationalisation
                returnString =appendString+"&&"+temp;//No Internationalisation
            else
                returnString = temp;
        }
        else
        {
            if(appendString != null && !(appendString.trim().equals("")))//No Internationalisation
                returnString = appendString+","+temp;//No Internationalisation
            else
                returnString = temp;
        }
        return returnString;
    }
    
    private Icon getNmsIcon(String image)
    {
        String imagePath = null;
        if(NmsClientUtil.applet.getDocumentBase() != null)
        {
            
            imagePath = NmsClientUtil.applet.getDocumentBase()+"../images/"+image; //No Internationalization
                               
        }
        else
        {
            imagePath = image;
        }
        try
        {
            return NmsClientUtil.getImageIcon(new URL(imagePath));
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
            return null;
        }
    }

    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("close"))
        {
            setVisible(false);
            dispose();
        }
        if(e.getActionCommand().equals("more"))
        {
            addMoreFewerPanel(true);
        }
        if(e.getActionCommand().equals("fewer"))
        {
            removeMoreFewerPanel();
        }
        if(e.getActionCommand().equals("search"))
        {
	     Vector tempVector = new Vector();
	     //cvId = NmsUiAPI.getCurrentCustomView();
	     
            for(Enumeration enum1 = moreFewerPanelTracer.keys();enum1.hasMoreElements();)
            {
                   DynamicPanel m = (DynamicPanel)moreFewerPanelTracer.get(enum1.nextElement());
                   String v[] = m.getValues();
                   if(v == null)
                   {
                       return;
                   }
		   tempVector.add(v[0]);
                   {
                       int vectorSize = headingVector.size();
                       for(int k = 0;k<vectorSize;k++)
                       {
                           if(v[0] == headingVector.elementAt(k))
                           {
                               String type = (String)typeVector.elementAt(k);
                           
                                if(!type.equals("Date"))
                                {
                                           //JOptionPane.showMessageDialog(null,,"BMSG",JOptionPane.PLAIN_MESSAGE);
                                     /*if(!searchHistory.contains(v[2]) && v[2] != null)
                                     {
                                         searchHistory.add(v[2]);
                                     }*/
                                   if(v[2] != null && !v[2].equals(""))
                                   {
					   if(v[2].indexOf("\'") == -1)
					   {
						   searchHistoryProp.setProperty(v[0],v[2]);
					   }
					   else
					   {
						   String str = v[2];
						   String valueStr = "";
						   for(int i = 0;i<str.length();i++)
						   {
							   if(str.charAt(i)!='\\')
							   {
								   valueStr = valueStr+str.charAt(i);
							   }
						   }
						   searchHistoryProp.setProperty(v[0],valueStr);
					   }

 	                           }
                                    
                                 }
                            }
                       }
		   }
	    }

	    Vector tempVector1 = new Vector();
	    for(int i=(tempVector.size())-1;i>=0;i--)
	    {
		    tempVector1.add(tempVector.elementAt(i));
	    }
	    nmsCustomPanel.setPanelVsSearchKey(cvId,tempVector1);

            if(searchable != null)
            {
            
                int attributeSize = 0;
                for(Enumeration enum1 = moreFewerPanelTracer.keys();enum1.hasMoreElements();)
                {
                    DynamicPanel m = (DynamicPanel)moreFewerPanelTracer.get(enum1.nextElement());
                        String v[] = m.getValues();
                        if(v[2] != null && !v[2].equals(""))
                        {
                                attributeSize++;
                        }
                    
                }
                SearchAttributes[] results = new SearchAttributes[attributeSize];
                int size = 0;
                for(Enumeration enum1 = moreFewerPanelTracer.keys();enum1.hasMoreElements();)
                {
                    DynamicPanel mfp = (DynamicPanel)moreFewerPanelTracer.get(enum1.nextElement());
                    String value[] = mfp.getValues();
                    if(value[2] != null && !value[2].equals(""))
                    {
                        results[size] = new SearchAttributes();
                        results[size].columnName = value[0];
                        int vectorSize = headingVector.size();
                        for(int i=0;i<vectorSize;i++)
                        {
                            if(value[0] == headingVector.elementAt(i))
                            {
                                String type = (String)typeVector.elementAt(i);
                                if(type.equalsIgnoreCase("Date"))
                                {
                                    try
                                    {
                                        Date dt = nmsFormat.parse(value[2]);
                                        results[size].value = String.valueOf(dt.getTime());
                                        
                                    }
                                    catch(Exception e1)
                                    {
                                     e1.printStackTrace();   
                                    }
                                    
                                }
                                else
                                    results[size].value = value[2];
                                results[size].type = mfp.getSelectedIndex(type);
                                results[size].key = (String)headerNames.elementAt(i);
                                break;
                                
                            }
                        }
                        size++;
                    }
                }
                boolean matchAll = true;
                if(!matchAllCheckBox.isSelected())
                    matchAll = false;
                searchable.doSearch(results,matchAll);
                return;
            }
            
            Properties criteriaProp = new Properties();
            String anyOrAll = null;
            if(needRadio)
            {
                if(matchAllCheckBox.isSelected())
                    anyOrAll = "AND";
                else
                    anyOrAll = "OR";
                criteriaProp.put("Arg","temporary");
            }
            StringBuffer sb = new StringBuffer();
            
            for(Enumeration enum1 = moreFewerPanelTracer.keys();enum1.hasMoreElements();)
            {
                DynamicPanel mfp = (DynamicPanel)moreFewerPanelTracer.get(enum1.nextElement());
                String temp = null;
                String value[] = mfp.getValues();
                
                String keyString = null;
                String type = null;
                for(int i=0;i<headingVector.size();i++)
                {
                    if(value[0] == headingVector.elementAt(i))
                    {
                        type = (String)typeVector.elementAt(i);
                        keyString = (String)criteriaProp.get(headerNames.elementAt(i).toString());
                        break;
                    }
                }
                if(keyString ==null)
                    keyString = "";
                String appendedString = "";
                if(type.equals("String"))
                    appendedString = formTheString(keyString,value,anyOrAll);
                else
                    appendedString = formTheDate(keyString,value,anyOrAll);
                sb.append(appendedString);
                if(appendedString != null && !(appendedString.trim().equals("")))
                {
                    for(int i=0;i<headingVector.size();i++)
                    {
                        if(value[0] == headingVector.elementAt(i))
                        {
                            criteriaProp.put(headerNames.elementAt(i).toString(),appendedString);
                        }
                    }
                }
            }
            if(!(sb.toString().trim().equals("")))
            {
                if(isConfiguration)
                {
                    criteriaProp.put("isNode","true");
                    criteriaProp.put("performOR","false");
                    criteriaProp.put("excludeCriteria","false");
                    criteriaProp.put("parentNet",searchInCombo.getSelectedItem());
                }
                submitSearch(criteriaProp,anyOrAll);
            }
            setVisible(false);
            dispose();
        }
    }    
    
    public void removeMoreFewerPanel()
    {
        DynamicPanel panel = (DynamicPanel)moreFewerPanelTracer.remove(""+counter);//No Internationalisation
        jPanel4.remove(panel);
        jPanel4.updateUI();
        this.validate();
        counter--;
        if(counter ==1)
        {
            fewerButton.setEnabled(false);
            moreButton.setEnabled(true);
        }
        else
        {
            fewerButton.setEnabled(true);
            moreButton.setEnabled(true);
        }

    }
    
    public void setVisible(boolean visible)
    {
        if(visible)
        {
            init();
            start();
        }
        else
        {
            stop();
        }
        super.setVisible(visible);
       
    }
    public void init()
    {
    	/*try
        {
       		 UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());
        }
        catch(Exception e)
        {
		System.out.println(NmsClientUtil.GetString("Exception while setting the LookAndFeel"));
	}*/
       initComponents();
       KeyStroke escStroke = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE,0);
       ((JComponent)getRootPane()).registerKeyboardAction(this,"close",escStroke,JComponent.WHEN_IN_FOCUSED_WINDOW);
       searchButton.getRootPane().setDefaultButton(searchButton);
       centerWindow(this);
       ht = nmsCustomPanel.getPanelVsSearchKey();
       ht1 = nmsCustomPanel.getPanelVsOperatorKey();
       operatorHash = nmsCustomPanel.getPanelVsOperatorKey();
       if((cvId != null) && (ht.containsKey(cvId)))
       {
	       Vector v = (Vector)ht.get(cvId);
	       for(int i=0;i<v.size();i++)
	       {
		       addMoreFewerPanel(false);
	       }
       }
       else
       {
	       addMoreFewerPanel(false);
       }	
    }
    public void start()
    {
        
    }
    public void stop()
    {
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
     //             new SearchPanel(new javax.swing.JFrame(), "Search Dialog for Adventnet", new JApplet());
       
        
    }
    public String getString(String key)
    {
        return NmsClientUtil.GetString(key);
    }
    public void submitSearch(Properties criteriaProperty,String matchAnyOrAll)
    {
        NmsCustomPanel.setSearchHistory(searchHistoryProp);

	Vector v = new Vector();
	for(Enumeration en1 = searchHistoryProp.propertyNames();en1.hasMoreElements();)
	{
		String str = (String)en1.nextElement();
		v.addElement(str);
	}            
	
	if(matchAnyOrAll != null)
        {
            if(matchAnyOrAll.equals("AND"))
            {
                criteriaProperty.put("AnyOrAll","MatchAll");

            }
            else
            {
                criteriaProperty.put("AnyOrAll","MatchAny");
            }

        }
        nmsCustomPanel.setProperties(criteriaProperty);

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton fewerButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox matchAllCheckBox;
    private javax.swing.JButton moreButton;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
    
}
