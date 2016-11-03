//$Id: ThresholdDialog.java,v 1.1.4.3 2013/04/02 14:28:33 thiyagu.s Exp $
package com.adventnet.nms.perfui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.xmlui.NmsPropertiesPanel;
import com.adventnet.nms.xmlui.NmsRoundBorder;

public class ThresholdDialog extends JDialog implements ActionListener{
	

	private javax.swing.JPanel ButtonPanel;
    private javax.swing.JButton Cancel;
    private javax.swing.JPanel ListPanel;
    private javax.swing.JButton OK;
    private javax.swing.JList ThresholdList;
    private javax.swing.JScrollPane jScrollPane1;
    private DefaultListModel model = new DefaultListModel();
    private PollingObjectPropsPanel pollobj;
    private PolledDataPropsPanel pdata;
    private JPanel header;
	public ThresholdDialog(java.awt.Frame parent, boolean modal) {
	      
		 super(parent, modal);
	        initComponents();
	    }

	    public ThresholdDialog(NmsPropertiesPanel panel)
	    {
	        super();
	        setModal(true);
	        initComponents();
	        if(panel instanceof PollingObjectPropsPanel)
	        {
	            pollobj=(PollingObjectPropsPanel)panel;
	            pdata=null;
	        }
	        else if(panel instanceof PolledDataPropsPanel)
	        {
	            pdata=(PolledDataPropsPanel)panel;
	            pollobj=null;
	        }

	        fetchThresholds();
	    }

	       
	   private void initComponents() {

	        ListPanel = new JPanel();
	        jScrollPane1 = new JScrollPane();
	        ThresholdList = new JList();
	        ButtonPanel = new JPanel();
	        OK = new JButton();
	        Cancel = new JButton();
	        header = new JPanel();
	        header.setLayout(new FlowLayout(FlowLayout.LEFT,3,0));
	        header.setBackground(new Color(223,223,223));
	        header.setPreferredSize(new Dimension(30,30));
	        JLabel label = new JLabel(NmsClientUtil.GetString("javaui.perfui.thresholdlist"));//NO I18N
	        label.setFont(new Font(NmsClientUtil.getFont().getName(),1,12));
	       /* JButton button = new JButton(NmsClientUtil.GetString("javaui.perfgui.polleddata.addnew"));//NO I18N
	        button.setFont(new Font(NmsClientUtil.getFont().getName(),0,12));
	        button.setBorderPainted(false);
	        button.setContentAreaFilled(false);
	        button.setActionCommand("add");//NO I18N
	        button.addActionListener(this);*/
	        header.add(label);
	        //header.add(button);
	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	        setTitle(NmsClientUtil.GetString("javaui.perfui.thresholddialog.title"));
	        ListPanel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(51,51, 255)));
	        ListPanel.setPreferredSize(new java.awt.Dimension(200, 310));
	        ListPanel.setLayout(new java.awt.BorderLayout());

	        ThresholdList.setFont(new Font(NmsClientUtil.getFont().getName(),0,12));
	        ThresholdList.setModel(model);
	        jScrollPane1.setViewportView(ThresholdList);

	        ListPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);
	        ListPanel.add(header,BorderLayout.PAGE_START);
	        

	        getContentPane().add(ListPanel, java.awt.BorderLayout.CENTER);

	        //ButtonPanel.setPreferredSize(new java.awt.Dimension(350, 40));
	        ButtonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 7));

	        Color bordercolor = new Color(51,51,51);
	        OK.setFont(new Font(NmsClientUtil.getFont().getName(),0,12));
	        OK.setText(NmsClientUtil.GetString("javaui.filter.Apply"));//NO I18N
	        OK.setBorder(new NmsRoundBorder(5,bordercolor));
	        OK.setBackground(new Color(250,250,250));
	        OK.setPreferredSize(new Dimension(68,22));
	        OK.addActionListener(this);
	        ButtonPanel.add(OK);

	        Cancel.setFont(new Font(NmsClientUtil.getFont().getName(),0,12));
	        Cancel.setText(NmsClientUtil.GetString("javaui.filter.Cancel"));//NO I18N
	        Cancel.setPreferredSize(new Dimension(68,22));
	        Cancel.setBorder(new NmsRoundBorder(5,bordercolor));
	        Cancel.setBackground(new Color(250,250,250));
	        Cancel.addActionListener(this);
	        Cancel.setActionCommand("Cancel");//NO I18N
	        ButtonPanel.add(Cancel);
	        Dimension dim=NmsClientUtil.getScreenSize();
	        setLocation(dim.width/3,dim.height/5);
	        getContentPane().add(ButtonPanel, java.awt.BorderLayout.PAGE_END);

	        pack();
	    }

	    private void OKActionPerformed(java.awt.event.ActionEvent evt) {                                   
	        
	        if(ThresholdList.getSelectedIndex()<0)
	        {
	            JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.performance.threshold.selection.error"), null, WIDTH);//NO I18N

	        }
	        Object[] values=ThresholdList.getSelectedValues();
	        
	               
	        
	         int num=0;
	         String thresh="";//NO I18N
	         while(num<=values.length-1)
	         {
	           
	           if(num==0)
	           thresh=values[num].toString();
	           else
	           thresh=thresh+","+values[num].toString();
	           
	           num++;
	         }
	         if(pollobj != null)
		     {
	        	pollobj.setThresholdField(thresh); 
		     }

	        else if(pdata != null)
	        {
	        	pdata.setThresholdField(thresh);
	        }
	            dispose();
	    }                                  

	    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {                                       
	        // TODO add your handling code here:
	        dispose();
	    }                                      

	    public void fetchThresholds()
	    {
	    
	    	
	    	Vector thresh =ThresholdUI.getThresholdNames();
	    	model.clear();
	        if(thresh!=null)
	        {
	          for(int i=0;i<=thresh.size()-1;i++)
	          {
	           String name= (String) thresh.elementAt(i);
	           model.addElement(name);
	           }
	       }   
	    }

		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource()==OK)
			{
				if(ThresholdList.getSelectedIndex()<0)
		        {
		            JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.performance.threshold.selection.error"), null, WIDTH);//NO I18N
                }
				else
				{
		        Object[] values=ThresholdList.getSelectedValues();
		        
		        
		            
		        
		         int num=0;
		         String thresh="";//NO I18N
		         while(num<=values.length-1)
		         {
		           
		           if(num==0)
		           thresh=values[num].toString();
		           else
		           thresh=thresh+","+values[num].toString();
		           
		           num++;
		         }
		         if(pollobj != null)
			     {
		        	pollobj.setThresholdField(thresh); 
			     }

		        else if(pdata != null)
		        {
		            pdata.setThresholdField(thresh);
		        }
		            dispose();
				}
			}
			else if(e.getActionCommand().equalsIgnoreCase("Cancel"))//NO I18N
			{
				dispose();
			}
			
		}
	    
}
