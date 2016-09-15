package com.adventnet.nms.severity;
import javax.swing.*;
import javax.swing.JSplitPane;
import java.awt.*;
import java.awt.event.*;


public class Severity extends JFrame implements ActionListener 
{
    // CardLayout card = (CardLayout)(cards.getLayout()); 
    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel cards;
    JPanel selectionPanel;
    //JPanel resultPanel;
     //components for splitPane
    JSplitPane resultPanel;
    JPanel left= new JPanel();
    JPanel right = new JPanel();
    //this panel contains the right side panel and status bar
    JPanel forStatus = new JPanel();

    JButton start;
    JButton clear = new JButton("CLEAR");
    ButtonGroup group =new ButtonGroup();
    ButtonGroup forsplitpane = new ButtonGroup();
    
    JRadioButton geturl;
    JRadioButton swdefault;
    JRadioButton gifserverhost;
    JTextField turl;
    JTextField tserverhost;
    

    //panels are belonging to right side of splitPane
    JPanel forNames = new JPanel();
    //this components are put in the left side of the splitPane
    JRadioButton getnames;
    JRadioButton getcolor;
    JRadioButton getvalue;
    JRadioButton getname;
    JRadioButton compare;
    
    //status field 
    JTextField status = new JTextField(10);

    //components for getcolor panel
    JPanel forColor = new JPanel();
    JTextField nameinput= new JTextField(10);
    JTextField coloroutput = new JTextField(10);
    JButton submit;
    //components for getvalue panel
    JPanel forValue = new JPanel();
    JTextField valueinput= new JTextField(10);
    JTextField valueoutput = new JTextField(10);
    JButton valueButton;

    //for components for getName panel
    JPanel forName = new JPanel();
    JTextField nameoutput;
    JTextField severitynameinput;
    //components for Compare panel
    JPanel forComparition = new JPanel();
    JTextField compareoutput;
    JTextField severityinput1;
    JTextField severityinput2;
    //Severity class constructor
    public  Severity()
    {   
	cards = new JPanel(new CardLayout());
	selectionPanel = new JPanel();
	//for status bar
	status.setEditable(false);
	
	tabbedPane.add("SELECT",Selection());
	tabbedPane.add("RESULT",Result());
	tabbedPane.setEnabledAt(1,false);
	getContentPane().add(tabbedPane);
	setBounds(50,30,600,400);
	setVisible(true);
    }
    // start of main()

    public static void main(String a[])
    {
	new Severity();
    }
    //end of main()


    //this method will return panel
    //the panel consists of three options for getting connection with the SeverityInfo.
    //User can select any one of these option. then user should give the corresponding input for getting connection with the SeverityInfo getInstance()
    public JPanel Selection()
    {
	selectionPanel.setLayout(new BorderLayout());
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	c.insets = new Insets(2,0,2,2);
	
	JPanel centerPanel = new JPanel();
	centerPanel.setLayout(gb);

	JLabel lurl = new JLabel("Enter URL");
	JLabel lhost = new JLabel("Enter Hostname");

	start = new JButton("CONTINUE");
	swdefault = new JRadioButton("StartWith Default",true);
	group.add(swdefault);

	geturl = new JRadioButton("GetInstance From URL");
	group.add(geturl);
	
	gifserverhost = new JRadioButton("Get Instance From Serverhost");
	group.add(gifserverhost);
	turl = new JTextField(10);
	turl.setEditable(false);
	tserverhost = new JTextField(10);
	tserverhost.setEditable(false);

	c.anchor = GridBagConstraints.WEST;
	c.gridwidth =2;
	c.gridx = 0;
	c.gridy = 0;
	addComponent(gb,c,centerPanel,swdefault,"JRadioButton");

	c.gridy =1;
	addComponent(gb,c,centerPanel,geturl,"JRadioButton");

	c.anchor = GridBagConstraints.CENTER;	
	c.gridwidth =1;
	c.gridy =2;
	addComponent(gb,c,centerPanel,lurl,"JLabel");

	c.gridx = 1;
	addComponent(gb,c,centerPanel,turl,"JTextField");
	
	c.anchor = GridBagConstraints.WEST;
	c.gridwidth=2;
	c.gridx =0;
	c.gridy =3;
	addComponent(gb,c,centerPanel,gifserverhost,"JRadioButton");

	c.anchor = GridBagConstraints.CENTER;
	c.gridwidth =1;
	c.gridy =4;
	addComponent(gb,c,centerPanel,lhost,"JLabel");

	c.gridx=1;
	addComponent(gb,c,centerPanel,tserverhost,"JTextField");
	
	c.insets=new Insets(50,0,0,0);
	c.gridy=5;
	addComponent(gb,c,centerPanel,start,"JButton");
	
	status.setEditable(false);
	
	selectionPanel.add(centerPanel,BorderLayout.CENTER);
	selectionPanel.add(status,BorderLayout.SOUTH);
        

	return selectionPanel;
    }
    //this method return the result panel(splitPane)
    //splitPane consists of two Panel
    //1.left panel
    //2.right Panel
    public JSplitPane Result()
    {   
	resultPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	resultPanel.setRightComponent(RightPanel());
	resultPanel.setLeftComponent(LeftPanel());
	
	return resultPanel;
    }
    //leftside panel of the  splitPane.
    //this panel consists of five Options.
    //user can select any one of the option.
    //to test the SeverityInfo method
    public JPanel LeftPanel()
    {
	//left = new JPanel();
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	c.insets = new Insets(5,0,5,2);
	c.anchor = GridBagConstraints.WEST;
	left.setLayout(gb);
	

	getnames    = new JRadioButton("GET NAMES");
	getcolor    = new JRadioButton("GET COLOR");
	getvalue    = new JRadioButton("GET VALUE");
	getname     = new JRadioButton("GET NAME");
	compare     = new JRadioButton("COMPARE");
	//forsplitpane is Button Group.
	// five Buttons are added with this ButtonGroup
	forsplitpane.add(getnames);
	forsplitpane.add(getcolor);
	forsplitpane.add(getvalue);
	forsplitpane.add(getname);
	forsplitpane.add(compare);

	c.gridx =0;
	c.gridy =0;
	addComponent(gb,c,left,getnames,"JRadioButton");

	c.gridy =1;
	addComponent(gb,c,left,getcolor,"JRadioButton");
	
	c.gridy =2;
	addComponent(gb,c,left,getvalue,"JRadioButton");
	
	c.gridy= 3;
	addComponent(gb,c,left,getname,"JRadioButton");
	
	c.gridy =4;
	addComponent(gb,c,left,compare,"JRadioButton");
	
	return left;
    }
    //this method will return the right side panel of the split pane
    //this consists of five panels.
    //one panel is shown at a time with the use of cardlayout.
    //the panel is selected by clicking on any one of the option available on the left side of the pane.
    public JPanel RightPanel()
    {
	
	forStatus.setLayout(new BorderLayout());
	right.setLayout(new CardLayout());
	right.add(forNames,"FORNAMES");
	right.add(forColor,"FORCOLOR");
	right.add(forValue,"FORVALUE");
	right.add(forName,"FORNAME");
	right.add(forComparition,"FORCOMPARE");
	forStatus.add(right,BorderLayout.CENTER);
	forStatus.add(status,BorderLayout.SOUTH);
	return forStatus;
    }
    //this method is used to add a component in the corresponding panel
    //if it is Button means ActioListener is added to the Button
    //otherwise the component is added to the panel. 
     public void addComponent(GridBagLayout gb,GridBagConstraints c,JPanel panel,JComponent component,String type)
    {
	if((type.equals("JButton"))||(type.equals("JRadioButton")))
	{
	    if(type.equals("JButton"))
		((JButton)component).addActionListener(this);
	    else
		((JRadioButton)component).addActionListener(this);
	    
	    gb.setConstraints(component,c);
	    panel.add(component);
	    return;
	}
	else 
	{
	    gb.setConstraints(component,c);
	    panel.add(component);
	    return;
	}
	
    }
    //this method will invoke the method related to the Button in which Mouse is clicked.
    public void actionPerformed(ActionEvent ae)
    {
	//cardlayout  is used for show one panel at a time in right side panel of the split pane
	CardLayout card = (CardLayout)(right.getLayout());
	//for clear the status bar
       status.setText("");
	
	// for select the action
	Object selectedbutton = ae.getSource();
	if(selectedbutton == start)
	{
	    if(severityInfoConnection()==null)
	    {
		status.setText("connection is refused");
		tabbedPane.setEnabledAt(1,false);
		return;
	    }
	    else
	    {
		tabbedPane.setEnabledAt(1,true);
		//this will select the next panel in tabbed pane
		tabbedPane.setSelectedIndex((tabbedPane.indexOfTab((String)tabbedPane.getTitleAt((tabbedPane.getSelectedIndex())+1))));
		return;
	    }
	}  
	//swdefault means Startwithdefault option
	if(selectedbutton ==swdefault)
	{
	    if(tserverhost.isEditable())
		tserverhost.setEditable(false); 
	    
	    if(turl.isEditable())
		turl.setEditable(false);
	    
	    tserverhost.setText("");
	    turl.setText("");
	    status.setText("");
	    return;
	} 
	
	//geturl means GetInstance from URL
	if(selectedbutton == geturl)
	{ 
	    if(tserverhost.isEditable())
		tserverhost.setEditable(false);
	    tserverhost.setText("");
	    status.setText("");
	    turl.setEditable(true);
	    return;
	}
	//gifserverhost means GetInstance from serverhost
	if(selectedbutton == gifserverhost)
	{  
	    if(turl.isEditable())
		turl.setEditable(false);
	    turl.setText("");
	    status.setText("");
	    tserverhost.setEditable(true);
	    return;
	} 
	//this will invoke the getNames method to get the Severity Names
	if(selectedbutton==getnames)
	{
	    ((JPanel)getNames()).updateUI();
	    card.show(right,"FORNAMES");
	    return;
	}
	//this will invoke the getColor method to get the color related to Severity Name.
	if(selectedbutton==getcolor)
	{
	    ((JPanel)getColor()).updateUI();
	    card.show(right,"FORCOLOR");
	    return;  
	}
	//this will invoke the getValue method to get the value related to Severity Name.
	if(selectedbutton==getvalue)
	{
	    ((JPanel)getValue()).updateUI();
	    card.show(right,"FORVALUE");
	    return;
	}
	//this will invoke the severityName method to get the name  related to Severity Value.
	if(selectedbutton==getname)
	{
	    ((JPanel)severityName()).updateUI();
	    card.show(right,"FORNAME");
	    return;
	}
	////this will invoke the severitycomparition method to get the highest critcality of  the given two Severity values.
	if(selectedbutton==compare)
	{
	    ((JPanel)severityComparition()).updateUI();
	    card.show(right,"FORCOMPARE");
	    return;
	}

	//clear is used to clear the textfields of input and output on the selected panel.
	
	if(selectedbutton==clear)
	{
	    if(forColor.isShowing())
	    {
		nameinput.setText("");
		coloroutput.setText("");
		return;
	    }
	    else if(forValue.isShowing())
	    {
		valueinput.setText("");
		valueoutput.setText("");
		return;
	    }
	    else if(forName.isShowing())
	    {
		severitynameinput.setText("");
		nameoutput.setText("");
		return;
	    } 
	    else if(forComparition.isShowing())
	    {
		severityinput1.setText("");
		severityinput2.setText("");
		compareoutput.setText("");
		return;
	    }    
	}
	//this will invoke the getColor method in SeverityInfo after getting the required input from the user. 
	if(selectedbutton == submit)
	{
	    String severityinput =(String)nameinput.getText();
	    if(!(severityinput.trim().equals("")))
	    {
		if((SeverityInfo.getInstance().getColor(severityinput))!=null)
		{
		    int red= (SeverityInfo.getInstance().getColor(severityinput)).getRed();
		    int blue = (SeverityInfo.getInstance().getColor(severityinput)).getBlue();
		    int green = (SeverityInfo.getInstance().getColor(severityinput)).getGreen();
		    coloroutput.setText("  "+red+"  "+blue+"  "+green);
		    coloroutput.setForeground(SeverityInfo.getInstance().getColor(severityinput));
		    status.setText("RESULT IS OBTAINED");
		    return;
		}
		else
		{
		    coloroutput.setText("");
		    status.setText("color is not found");
		    return;
		}
	    }
	    coloroutput.setText("");
	    status.setText("enter the severity value");
	    return;
	}
	//this will invoke the getName method in SeverityInfo after getting the required input from the user. 
	
	if((((JButton)selectedbutton).getName()).equals("name"))
	{
	    
	    try
	    {
		String value = (String)(severitynameinput.getText());
		if(value!=null)
		{
		    Integer severityinput = new Integer(value);
		    int i = severityinput.intValue();
		    if(i!=-1)
		    {
			String n= SeverityInfo.getInstance().getName(i);
			if(n!=null)
			{
			    nameoutput.setText(n);
			    status.setText("RESULT IS OBTAINED");
			    return;
			}
			else
			    {
				nameoutput.setText("null");
				 status.setText("RESULT IS NOT OBTAINED");
				return;
			    }
		    }
		    else
		    {
			nameoutput.setText(" ");
			status.setText("name is  not found");
			return;
		    }
		}
		else
		{
		    status.setText("enter the value");
		    nameoutput.setText(" "); 
		    return;
		}
	    }
	    catch(Exception e)
	    {
		status.setText("invalid number");
		nameoutput.setText(" "); 
		return;
	    }
	}
	
	//this will invoke the getValue method in SeverityInfo after getting the required input from the user. 
	if(selectedbutton==valueButton)
	{
	    String severityinput =(String)valueinput.getText();
	    if(!(severityinput.trim().equals("")))
	    {
		int v= SeverityInfo.getInstance().getValue(severityinput);
		valueoutput.setText(" "+v);
		status.setText("RESULT IS OBTAINED");
		return;
	    }
	    else
	    {
		valueoutput.setText("");
		status.setText("Value is not found");
		return;
	    }
	}
	//this will invoke the getName method in SeverityInfo after getting the required input from the user. 

	if((((JButton)selectedbutton).getName()).equals("critical"))
	{
	    
	    try
	    {
		Integer input1 = new Integer((String)severityinput1.getText());
		Integer input2 = new Integer((String)severityinput2.getText());
		if((input1!=null)&&(input2!=null))
		{
		    int severity1 = input1.intValue();
		    int severity2 = input2.intValue();
		    if((severity1!=-1)&&(severity2!=-1))
		    {
			int value= SeverityInfo.getInstance().compare(severity1,severity2);
			if(value==0)
			    status.setText(value+"(BOTH ARE EQUAL)");
			else if(value==1)
			    status.setText(value+"  "+severity1+"(FIRST IS GREATER)"); 
			else if(value == -1)
			    status.setText(value+"  "+severity2+ "(SECOND IS GREATER)"); 
			compareoutput.setText(""+value);
			return;
		    }
		}
		else
		{ 
		    status.setText("IN VALID INPUT");
		    compareoutput.setText("");
		    return;
		}
	    }
	    catch(Exception e)
	    {
		status.setText("criticality is  not found");
		compareoutput.setText("");
		return;
	    }
	}
	
    }
    //this method will return the panel for getSeverityNames 
    //to get the severity Names
    JList names;
    public JPanel getNames()
    {	
	
  	
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	c.anchor = GridBagConstraints.CENTER;
	c.insets = new Insets(2,2,2,0);
	forNames.setLayout(gb);
	
	if((forNames.getComponentCount())>0)
	    forNames.removeAll();
	if((SeverityInfo.getInstance().getNames())!=null)
	    names = new JList((SeverityInfo.getInstance().getNames())); 
       	else
	    names = new JList();  
	JLabel lnames = new JLabel("SEVERITY NAMES");
	c.gridx=0;
	c.gridy =0;
	addComponent(gb,c,forNames,lnames,"JLabel");

	c.gridwidth =2;
	c.gridy =1;
	JScrollPane listPane = new JScrollPane(names);
	listPane.setPreferredSize(new Dimension(180,120));
	addComponent(gb,c,forNames,listPane,"JScrollPane");

	return forNames;
	
    }
    //this method will return the Panel for the getColor of the given severity name
    public JPanel getColor()
    {
	if((forColor.getComponentCount())>0)
	    forColor.removeAll();
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	c.anchor = GridBagConstraints.WEST;
	c.insets = new Insets(2,2,2,0);
	forColor.setLayout(gb);
	
	JLabel lcolor = new JLabel("Enter Severity Name");
	c.gridy=0;
	c.gridx =0;
	addComponent(gb,c,forColor,lcolor,"JLabel");
	
	c.gridx =1;
	addComponent(gb,c,forColor,nameinput,"JTextField");
	
	c.gridy =1;
	coloroutput.setEditable(false);
	coloroutput.setBackground(Color.white);
	addComponent(gb,c,forColor,coloroutput,"JTextField");
	
	JLabel output = new JLabel("Color ");
	c.gridx =0;
	addComponent(gb,c,forColor,output,"JLabel");
	
	c.insets = new Insets(30,0,0,0);
	c.gridx=1;
	c.anchor = GridBagConstraints.CENTER;
	submit = new JButton("SUBMIT");
	c.gridy =2;
	addComponent(gb,c,forColor,submit,"JButton");
	
	c.gridx =0;
	addComponent(gb,c,forColor,clear,"JButton");
	return forColor;
    }
    //this method will return the panel for the getValue of the given SeveityName.   
    public JPanel getValue()
    {
	if((forValue.getComponentCount())>0)
	    forValue.removeAll();
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	c.anchor = GridBagConstraints.WEST;
	c.insets = new Insets(2,2,2,0);
	forValue.setLayout(gb);
	
	JLabel lvalue = new JLabel("Enter Severity Name");
	c.gridy=0;
	c.gridx =0;
	addComponent(gb,c,forValue,lvalue,"JLabel");
	
	c.gridx =1;
	addComponent(gb,c,forValue,valueinput,"JTextField");
	
	c.gridy =1;
	valueoutput.setEditable(false);
	valueoutput.setBackground(Color.white);
	addComponent(gb,c,forValue,valueoutput,"JTextField");
	
	JLabel valuelabel = new JLabel("VALUE ");
	c.gridx =0;
	addComponent(gb,c,forValue,valuelabel,"JLabel");
	
	c.gridx=1;
	c.insets = new Insets(30,0,0,0);
	valueButton = new JButton("SUBMIT");
	valueButton.setName("value");
	c.gridy =3;
	c.anchor = GridBagConstraints.CENTER;
	addComponent(gb,c,forValue,valueButton,"JButton");

	c.gridx =0;
	addComponent(gb,c,forValue,clear,"JButton");

	return forValue;
    }
    //this method will return the panel for getSeverity name for the given severity value.     
    public JPanel severityName()
    {
	if((forName.getComponentCount())>0)
	    forName.removeAll();
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	c.anchor = GridBagConstraints.WEST;
	c.insets = new Insets(2,2,2,0);
	forName.setLayout(gb);
	
	severitynameinput = new JTextField(10);
	nameoutput = new JTextField(10);
	JButton nameButton = new JButton("get the name");
	
	
	JLabel lname = new JLabel("Enter Severity value");
	c.gridy=0;
	c.gridx =0;
	addComponent(gb,c,forName,lname,"JLabel");
	
	c.gridx =1;
	addComponent(gb,c,forName,severitynameinput,"JTextField");
	
	c.gridy =1;
	nameoutput.setEditable(false);
	nameoutput.setBackground(Color.white);
	addComponent(gb,c,forName,nameoutput,"JTextField");
	
	JLabel namelabel = new JLabel("NAME ");
	c.gridx =0;
	addComponent(gb,c,forName,namelabel,"JLabel");

	c.insets = new Insets(30,0,0,0);
	nameButton = new JButton("SUBMIT");
	nameButton.setName("name");
	c.gridx =1;
	c.gridy =3;
	c.anchor = GridBagConstraints.CENTER;
	addComponent(gb,c,forName,nameButton,"JButton");
	
	c.gridx =0;
	addComponent(gb,c,forName,clear,"JButton");
	return forName;
    }
    //this method will return the panel for compare the two severity and get the result of severity value which has the highest criticality.  
    public JPanel severityComparition()
    {
	if((forComparition.getComponentCount())>0)
	    forComparition.removeAll();

	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	c.anchor = GridBagConstraints.WEST;
	c.insets = new Insets(2,2,2,0);
	forComparition.setLayout(gb);
	
	severityinput1 = new JTextField(10);
	severityinput2 = new JTextField(10);
	compareoutput = new JTextField(10);

	JButton criticalButton = new JButton("get Criticality");
	JLabel severity1 = new JLabel("Enter Severity1 value");
	c.gridy=0;
	c.gridx =0;
	addComponent(gb,c,forComparition,severity1,"JLabel");
	
	c.gridx =1;
	addComponent(gb,c,forComparition,severityinput1,"JTextField");
		
	
	JLabel severity2 = new JLabel("Enter Severity2 value");
	c.gridy=1;
	c.gridx =0;
	addComponent(gb,c,forComparition,severity2,"JLabel");
	
	c.gridx =1;
	addComponent(gb,c,forComparition,severityinput2,"JTextField");
	
	JLabel namelabel = new JLabel("RESULT");
	c.gridx =0;
	c.gridy =2;
	addComponent(gb,c,forComparition,namelabel,"JLabel");

	c.anchor =GridBagConstraints.EAST;
	c.gridx =1;
	compareoutput.setEditable(false);
	compareoutput.setBackground(Color.white);
	addComponent(gb,c,forComparition,compareoutput,"JTextField");
	
	criticalButton = new JButton("COMPARE");
	criticalButton.setName("critical");
	c.insets = new Insets(40,0,0,0);
	c.gridy =4;
	addComponent(gb,c,forComparition,criticalButton,"JButton");

	c.gridx =0;
	addComponent(gb,c,forComparition,clear,"JButton");
	return forComparition;
    }
    
    //to get the connection of the severityInfo
    public SeverityInfo severityInfoConnection()
    {
	if(swdefault.isSelected())
	{
	    return SeverityInfo.getInstance();   
	}
	else if(geturl.isSelected())
	{	   
	    String url=(String)turl.getText();
	    if(!(url.trim().equals("")))
	       {
		   return SeverityInfo.getInstance(url);
	       }
	    else
		return null;
	}
	else if(gifserverhost.isSelected())
	{
	    String host =(String)tserverhost.getText();
	    if(!(host.trim().equals("")))
	    {
		return SeverityInfo.getInstanceFromHost(host);
	    }
	    else return null;
	}
	return null;
    }
}
///////////////////end of severityinfo test example////////////////////////////













