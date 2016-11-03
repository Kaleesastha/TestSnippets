//$Id: PolledDataAgentSearch.java,v 1.1.4.12 2013/08/14 07:45:31 karen.r Exp $

package com.adventnet.nms.perfui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import com.adventnet.nms.pollui.PerfUIData;
import com.adventnet.nms.pollui.StatsAdminPanel;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.xmlui.NmsRoundBorder;

/**
 *This class displays the Polled data agent search window
 * @author vijayalakshmiv
 */

public class PolledDataAgentSearch extends javax.swing.JDialog implements ActionListener,KeyListener{

	// Variables declaration - do not modify
    private javax.swing.JButton first;
    private javax.swing.JButton previous;
    private javax.swing.JButton next;
    private javax.swing.JButton last;
   
    private JPanel jPanel2;
    private JPanel jPanel6;
    private JLabel jLabel4;
    int i=0;
	
	//private JPanel firstcard;
	//private JPanel lastcard;
	//private JPanel currentcard;
	
	private int count=0;
	
	private String selectedvalue="";//No I18N
	private boolean firsttime=true;
	private int agentsize=0;
	private int start=1;
	private int end=20;
	private String toget="";
	private int index=0;
	private JTable jTable1;
	private Vector agents=new Vector();
	private PerfUIData perfdata;
	private PolledDataPropsPanel props;
	private int total=0;
	private  JTextField searchf;
	private int current=1;
	private boolean onlyone=false;
	private ImageIcon prev,prevdis,fir,firdis,nex,nexdis,las,lastdis;
    // End of variables declaration
	
	/** Creates new form PolledDataAgentSearch */
	
    public PolledDataAgentSearch(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
 
    }
    
    public PolledDataAgentSearch(PolledDataPropsPanel props)
    {
    	super(NmsClientUtil.getParentFrame());//fix for making the log console accessible.
    	setModal(false);
    	this.perfdata=PerfUIData.getInstance();
    	this.props=props;
    	initComponents();
    	
    }

   private void initComponents() {

	   JPanel jPanel1 = new JPanel();
	   JLabel jLabel1 = new JLabel();
       searchf = new JTextField();
      
        jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JLabel jLabel3 = new JLabel();
        JPanel jPanel4 = new JPanel();
        first = new JButton();
        previous = new JButton();
        jLabel4 = new JLabel();
        next = new JButton();
        last = new JButton();
        jPanel6 = new JPanel();
    
        

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        //jPanel1.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 15));

        jLabel1.setFont(new Font(NmsClientUtil.getFont().getName(),0,12));
        jLabel1.setText(NmsClientUtil.GetString("javaui.perfgui.polleddata.searchhost"));
        jLabel1.setBorder(BorderFactory.createEmptyBorder(0,3,0,0));
        
        jPanel1.add(jLabel1);

        JPanel sp = new JPanel();
        sp.setPreferredSize(new Dimension(200,22));
        sp.setLayout(new BorderLayout());
        sp.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        sp.setBackground(Color.white);
        searchf.addKeyListener(this);
        searchf.setBorder(null);
    	searchf.setPreferredSize(new Dimension(20,10));
        sp.add(searchf,BorderLayout.CENTER);
        try {
			prev=NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/previous_perf.png"));
			prevdis=NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/previous_perf_dis.png"));
			fir=NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/first_perf.png"));
			firdis=NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/first_perf_dis.png"));
			nex=NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/next_perf.png"));
			nexdis=NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/next_perf_dis.png"));
			las=NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/last_perf.png"));
			lastdis=NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/last_perf_dis.png"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JButton button = new JButton();
        button.setContentAreaFilled(false);
    	//button.setBorderPainted(false);
    	button.setMargin(new Insets(0,0,0,0));
    	button.setActionCommand("search");
    	//button.setBackground(new Color(223,223,223));
    	button.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
    	
    	try {
			button.setIcon(NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/search1.png")));
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		button.addActionListener(this);
		sp.add(button,BorderLayout.LINE_END);
		jPanel1.add(sp);
        
        //Addin context-sensitive help here
        
        JButton help = new JButton(); 
        help.setContentAreaFilled(false);
        help.setMargin(new Insets(0,0,0,0));
       // help.setBackground(new Color(223,223,223));
    	help.setBorder(null);
    	try {
			help.setIcon(NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/help_contextual1.png")));
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		help.setToolTipText(NmsClientUtil.GetString("javaui.perfgui.pdagentsearch.cshelp"));
		jPanel1.add(help);
		sp.add(button,BorderLayout.LINE_END);
    	

       // jPanel1.add(jLabel2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(242, 242, 242));
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 10),BorderFactory.createLineBorder(new java.awt.Color(90,135,226))));
        
        jPanel3.setBackground(new java.awt.Color(223, 223, 223));
        jPanel3.setPreferredSize(new java.awt.Dimension(550,25));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font(NmsClientUtil.getFont().getName(), 1, 11));
        jLabel3.setText(NmsClientUtil.GetString("javaui.perfgui.polleddata.hostlist"));
        jLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
        jLabel3.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        jPanel3.add(jLabel3, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(223, 223, 223));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 7));

        first.setBorder(null);
        first.setBorderPainted(false);
        first.setContentAreaFilled(false);
        first.setActionCommand("first");//NO I18N
        first.setEnabled(false);
        
		first.setIcon(firdis);//No I18N
		
        first.addActionListener(this);
        jPanel4.add(first);

       
        previous.setBorder(null);
        previous.setBorderPainted(false);
        previous.setContentAreaFilled(false);
        previous.setActionCommand("previous");//NO I18N
        
		previous.setIcon(prevdis);//No I18N
		
        previous.setEnabled(false);
        previous.addActionListener(this);
       
        jPanel4.add(previous);

        jLabel4.setFont(NmsClientUtil.getFont());
        
        
        jPanel4.add(jLabel4);

       
        next.setBorder(null);
        next.setBorderPainted(false);
        next.setContentAreaFilled(false);
        next.setActionCommand("next");//NO I18N
        next.setIcon(nex);//No I18N
		
        next.addActionListener(this);
        jPanel4.add(next);

        last.setBorder(null);
        last.setBorderPainted(false);
        last.setContentAreaFilled(false);
        last.setActionCommand("last");//NO I18N
        last.setIcon(las);//No I18N
		
        last.addActionListener(this);
        
        jPanel4.add(last);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);
        
        setAgents();
        toget="*";//No I18N
        sendAgentRequest();
        addBottomPanel();
        
       

        

       

        //jPanel2.add(jPanel6, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);
        Dimension d = NmsClientUtil.getScreenSize();
        setLocation(4*d.width/10,d.height/4);

        pack();
    }// </editor-fold>
    
    private void addBottomPanel()
    {
    	javax.swing.JPanel jPanel7 = new javax.swing.JPanel();

    	jPanel7.setBackground(new java.awt.Color(245, 245, 245));

    	jPanel7.setPreferredSize(new java.awt.Dimension(10, 50));

    	jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 13));
    	
    	JButton ok = new JButton();
    	
        ok.setText(NmsClientUtil.GetString("javaui.perfgui.polleddatasearch.okbutton"));
        ok.setActionCommand("ok");//NO I18N
        ok.addActionListener(this); 
        ok.setBackground(new Color(250,250,250));
		ok.setFont(new Font(NmsClientUtil.getFont().getName(),0,12));
		//ok.setPreferredSize(new Dimension(68,22));
		Color buttoncolor = new Color(51,51,51);
		ok.setBorder(new NmsRoundBorder(5,buttoncolor));
		
        jPanel7.add(ok);

        JButton cancel = new JButton();
        
        cancel.setText(NmsClientUtil.GetString("javaui.perfgui.polleddatasearch.cancelbutton"));
        cancel.setActionCommand("cancel");//NO I18N
        cancel.setBackground(new Color(250,250,250));
		cancel.setFont(new Font(NmsClientUtil.getFont().getName(),0,12));
		//cancel.setPreferredSize(new Dimension(68,22));
		cancel.setBorder(new NmsRoundBorder(5,buttoncolor));
		
		
        cancel.addActionListener(this); 
        jPanel7.add(cancel);
        getContentPane().add(jPanel7, java.awt.BorderLayout.PAGE_END);
    	
    }
    
    private void OkCancelActionPerformed(ActionEvent evt)
    {
    	if(evt.getActionCommand().equals("ok"))//NO I18N
    	{
    		props.setAgent(selectedvalue);
    	}
    	props.agentsearch=null;
    	dispose();
    }
    private void flipPage(ActionEvent evt)
    {
    
    	String command = evt.getActionCommand();
    	if(total%20==0)
        	index=total/20;
        	else
        		index=Math.round(total/20)+1;
    	changeAll(true);
    	
    	if(evt.getActionCommand().equals("first"))//NO I18N
    	{
    		start=1;
    		end=20;
    		//previous.setIcon(prevdis);
    		previous.setEnabled(false);
			//first.setIcon(firdis);
			first.setEnabled(false);
			if(total>20)
			{
				next.setEnabled(true);
				last.setEnabled(true);
			}
    		
    	}
    	else if(evt.getActionCommand().equals("previous"))//NO I18N
    	{
    		start=start-20;
    		end=start+19;
    		//check for first page
    		if(start ==1)
    		{
    			//previous.setIcon(prevdis);
    			previous.setEnabled(false);
    			//first.setIcon(firdis);
    			first.setEnabled(false);
    		}
    		next.setEnabled(true);
    		last.setEnabled(true);
    		
    	}
    	else if(evt.getActionCommand().equals("next"))//NO I18N
		{
    		start=end+1;
    		end=start+19;
    		   		
    		//Check for last page
    		if(end>total)
    		{
    			end=total;
    			//next.setIcon(nexdis);
    			//last.setIcon(lastdis);
    			next.setEnabled(false);
    			last.setEnabled(false);
    		}
    		previous.setIcon(prev);
			previous.setEnabled(true);
			first.setIcon(fir);
			first.setEnabled(true);
		}
    	
    	else if(evt.getActionCommand().equalsIgnoreCase("last"))//NO I18N
    	{
    		start=(20*(index-1))+1;
    		end=total;
    		//next.setIcon(nexdis);
			//last.setIcon(lastdis);
			next.setEnabled(false);
			last.setEnabled(false);
			
			previous.setIcon(prev);
			previous.setEnabled(true);
			first.setIcon(fir);
			first.setEnabled(true);
    	}
    	
    	
    	sendAgentRequest();
    }
    
    private void changeAll(boolean flag)
    
    {
    if(!flag)
    {	
    previous.setIcon(prevdis);
	next.setIcon(nexdis);
	first.setIcon(firdis);
	last.setIcon(lastdis);
    }
    else{
    	previous.setIcon(prev);
    	next.setIcon(nex);
    	first.setIcon(fir);
    	last.setIcon(las);
    }
    }
    
    private void addValuestoTable()
    {
       	Runnable run = new Runnable()
    	{
        
		public void run()
		{
			jTable1.clearSelection();
		DefaultTableModel model=(DefaultTableModel) jTable1.getModel();	
    	while(model.getRowCount()>0){
    		model.removeRow(0);
    	  }
    	model.setRowCount(10);
    	model.setColumnCount(2);
        
    	int j=0;
    	boolean change=false;
    	for(i=0;i<=agents.size()-1;i++)
    	{
    	 if(!change)
    	 {	
    		 model.setValueAt(agents.elementAt(i),j,0);
    	 }
    	 else
    	 {
    		 model.setValueAt(agents.elementAt(i),j,1);
    	 }
    	 j++;
    					
    	if(j==10)
    	{
    			j=0;
    			change=true;
    		}	
    					
    	}
    	
    	//jTable1.setModel(model);
    	model.fireTableRowsUpdated(0,10);	
    	jTable1.updateUI();//Move this line to fix NPE. 
    	//start=i;
		}
    	};
    	SwingUtilities.invokeLater(run);
    	
    	setLabelText();
    		 
    }
    private void setAgents()
    {
    	
       	int cardsnum =agents.size()/20;
    	//for(int i=0;i<=cardsnum-1;i++)
    	
    		
    		//if(firsttime){
    		jTable1 = new JTable();
    		DefaultTableModel model=new DefaultTableModel(10,2);
    		jTable1.setModel(model);
    		setTableProperties();
    		
    		
            
            //jTable1.setPreferredSize(new Dimension(500,300));
    		
            
    		jPanel2.add(jTable1,BorderLayout.CENTER);//No I18N
    		//}
    		/*if(i==0)
    			firstcard=panel;
    		lastcard=panel;*/
    		jPanel2.validate();
        	jPanel2.repaint();
    		
    }
    private void sendAgentRequest()
    {
    	String username = NmsClientUtil.userName;
    	Properties props = new Properties();
    	props.put("start",start);
    	props.put("total",end);
    	props.put("isascending",true);
    	props.put("tosearch",toget);
    	perfdata.sendDataToServer(props,"Polled Data agents");
    	{	
    	agentsize=agents.size();
    	}
    }
    
    public void setAgentsVector(Vector agents,int total)
    {
    	//if(!agents.isEmpty())
    		//agents.clear();
    	this.agents=agents;
    	this.total=total;
    	if(agents.size()==0)
    	{
    		start=0;
    	}
    	if(total<20)
    	{	
    		end=total;
    		onlyone=true;
    		changeAll(false);
    	}
    	if(total <= 20)
    	{
    		next.setEnabled(false);
    		last.setEnabled(false);
    	}
    	    	
    	//setAgents();
    	addValuestoTable();
    }
    
    private void setLabelText()
    {
    	jLabel4.setText(NmsClientUtil.GetString("javaui.perfgui.polleddata.showing")+" "+start+" "+NmsClientUtil.GetString("javaui.perfgui.polleddata.showing.to")+" "+end+" "+NmsClientUtil.GetString("javaui.perfgui.polleddata.showing.of")+" "+total );//NO I18N
    }
    
    /*private void setIndices()
    {
    	if(firstpage)
    	{
    		start=0;
    		end=20;
    	}
    	/*else if(currentcard == lastcard)
    	{
    		start=end+1;
    		end=agentsize;
    	}
    	else{
    		start=end+1;
    		end=start+20;
    	}
    	index=end/20;
    }*/
    private void valueSelected(ListSelectionEvent evt)
    {
    }
    private void setTableProperties()
    {
    	
    	jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
  
    	//jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(90,135,226)));
    	jTable1.setShowVerticalLines(true);
    	JTextField tf = (JTextField) jTable1.getEditorComponent();
    	if(tf!=null)
    	tf.setEditable(false);
        jTable1.setTableHeader(null);
        jTable1.setCellSelectionEnabled(true);
        jTable1.setRowHeight(23);
        jTable1.setBackground(Color.white);
        jTable1.setSelectionBackground(new Color(90,135,226));
        jTable1.setSelectionForeground(Color.WHITE);
        jTable1.setGridColor(new Color(245,245,245));
        jTable1.setIntercellSpacing(new Dimension(10,0));
        jTable1.setShowHorizontalLines(false);
        
        ListSelectionModel cellSelectionModel = jTable1.getSelectionModel();
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt)
            {
                if(evt.getValueIsAdjusting())
                	return;
                
               setSelectedValue(jTable1);
                
            }
        });
        
    }

    private void setSelectedValue(JTable table)
    {
    	int row=table.getSelectedRow();
    	int column=table.getSelectedColumn();
    	if(row<0 || column<0)
    	{
    		selectedvalue="";
    	}
    	else{
	    	Object obj = table.getModel().getValueAt(row, column);
	    	if(obj!=null)//added to avoid a NPE
	    	{
	    		selectedvalue= obj.toString();
	    	}
	    	else{
	    		selectedvalue="";
	    	}
    	}
    }

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equalsIgnoreCase("ok")||e.getActionCommand().equalsIgnoreCase("cancel"))
			OkCancelActionPerformed(e);
		else if(e.getActionCommand().equalsIgnoreCase("search"))
		{
			toget=searchf.getText();
			start=1;
			end=20;
			next.setIcon(nex);
			last.setIcon(las);
			sendAgentRequest();
			
		}
		else
			flipPage(e);
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()== e.VK_ENTER)
		{
			JTextField t = (JTextField) e.getSource();
			toget=t.getText();
			if(toget.equals(""))
			{
				toget = "*";
			}
			else if(!toget.contains("*"))
			{
				toget="*"+toget+"*";
			}
			start=1;
			end=20;
			next.setEnabled(true);
			last.setEnabled(true);
			next.setIcon(nex);
			last.setIcon(las);
			next.setVisible(true);
			last.setVisible(true);
			first.setEnabled(false);
			previous.setEnabled(false);
			sendAgentRequest();
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
		
    
    
}

