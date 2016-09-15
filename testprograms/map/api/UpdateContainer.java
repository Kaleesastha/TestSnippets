//-----------------------------UPDATE CONTAINER--------------------------//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
//this is for add map class

class UpdateContainer extends JPanel implements ActionListener,ItemListener
    //JPanel implements ActionListener
{   //decleration of variables

	JPanel top = new JPanel();
	String mode = "MODE :UPDATE CONTAINER\n";
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	JPanel centerpanel = new JPanel();

	JLabel mapname;
	JLabel containername;
	JButton select;
	JButton modify;

	String[] nameofmap; 
	JComboBox mapnames;
	JComboBox symbolNames;

	JTextArea status = new JTextArea(2,10);
	GetMapFunctions updatecontainer = new GetMapFunctions();
	public UpdateContainer()
	{
		centerpanel.setLayout(gb);	
		setLayout(new BorderLayout());//for whole panel

		mapname = new JLabel("MAP NAME");
		containername = new JLabel("CONTAINER NAME");
		//above two lables and buttons are added to top Panel
		Font f =new Font("Dialog",Font.BOLD,10);

		select = new JButton("SELECT");
		select.addActionListener(this);
		modify = new JButton("MODIFY");
		modify.addActionListener(this);

		nameofmap = updatecontainer.getMapNameList();

		if( nameofmap == null)
		{
			nameofmap = new String[0];
		}

		mapnames= new JComboBox(nameofmap) ;
		mapnames.addItemListener(this);

		String mapName =(String) mapnames.getSelectedItem();	
		Vector v = updatecontainer.getSymbolNames(mapName);	
		setSymbolNames(v);
		
		mapnames.setPreferredSize(new Dimension(250,20));
		symbolNames.setPreferredSize(new Dimension(250,20));

		status.setEditable(false);
		status.setText(mode);
		JScrollPane notes = new JScrollPane(status);

		//layout set for top panel
		top.setLayout(gb);
		c.anchor = GridBagConstraints.WEST;
		c.insets =new Insets(1,0,2,2);
		c.gridx = 0;
		c.gridy = 0;
		gb.setConstraints(mapname,c);
		top.add(mapname);

		c.gridx = 1;//c.gridy = 0;
		gb.setConstraints(mapnames,c);
		top.add(mapnames);

		c.gridx = 0;
		c.gridy = 1;
		gb.setConstraints(containername,c);
		top.add(containername);

		c.gridx = 1;//c.gridy = 1;
		gb.setConstraints(symbolNames ,c);
		top.add(symbolNames );

		c.insets = new Insets(30,0,2,1);
		c.anchor=GridBagConstraints.CENTER;
		c.gridy = 2;
		gb.setConstraints(select,c);
		top.add(select);

		c.gridx = 0;//c.gridy = 2;
		gb.setConstraints(modify,c);
		top.add(modify);
		c.insets =new Insets(1,0,2,2);

		// components are added to top panel
		add(top,BorderLayout.NORTH);
		add(centerpanel,BorderLayout.CENTER);
		add(notes,BorderLayout.SOUTH);

		setVisible(true);
	}
	int ks=0;//vector size
	static Vector v;
	public void actionPerformed(ActionEvent ae)
	{
		String map=null; 
		String container=null;
		map = (String) mapnames.getSelectedItem();
		container =(String)symbolNames.getSelectedItem();

		Object obj = ae.getSource();

		if(obj == select)
		{
			if((map !=null)&&(container !=null)&&(!map.trim().equals(""))&&(!container.trim().equals("")))
			{ 
				status.setText(mode+"    "+container+selectFunction(map,container));

			} 
			else
			{
				status.setText(mode+"PLEASE ENTER THE MAPNAME & CONTAINER");
			}
		}

		if((obj == modify))
		{
			try
			{
				status.setText(mode+"    "+container+modifyFunction(map));

			}
			catch(Exception e)
			{
				status.setText(mode+"ERROR OCCURED IN MODIFY PROCESS");

			}

		}
	}

	public String selectFunction(String map,String container)
	{
		if(centerpanel.getComponentCount()>0)
		{
			centerpanel.removeAll();
			v.clear();
			centerpanel.updateUI();
		}
		v= new Vector();
		Properties p;
		try
		{
			p = updatecontainer.getContainerProperties(map,container);   
		}
		catch(Exception er)
		{
			return"error in getting properties of container";
		}
		if(p!=null)//p should not be null& p.key value should be>0
		{
			if(p.size()!=0)
			{
				Enumeration prkeys = p.keys();
				Enumeration prelements = p.elements();

				ks= p.size();// k is the number keys properties
				int j=0;

				for(int i=0;i<(2*ks);i++)
				{
					if((i%2)==1)
						v.addElement(new JTextField(10));
					else
						v.addElement(new JLabel());
				}
				String k;
				String e;
				//for label allaignment
				c.anchor = GridBagConstraints.WEST;

				for(int i=0;i<(v.size());i++)
				{

					e =prelements.nextElement().toString();
					k =prkeys.nextElement().toString();

					c.gridx = 0;
					c.gridy = 0+j;
					((JLabel)v.elementAt(i)).setText(k);
					gb.setConstraints((JLabel)v.elementAt(i),c);
					centerpanel.add((JLabel)v.elementAt(i));
					i++;

					if((k.equals("status"))||(k.equals("isManaged"))||(k.equals("discover"))||(k.equals("mapName")))
					{
						((JTextField) v.elementAt(i)).setEditable(false);
						((JTextField) v.elementAt(i)).setBackground(Color.white);
					}
					else
						((JTextField) v.elementAt(i)).setEditable(true);

					((JTextField) v.elementAt(i)).setText(e);
					c.gridx = 1;
					gb.setConstraints((JTextField)v.elementAt(i),c);
					centerpanel.add((JTextField)v.elementAt(i));
					i++;

					if(!(i<(v.size())))
					{
						centerpanel.updateUI();
						centerpanel.setVisible(true);
						return "properties of selected container";
					}
					else{}
					k=prkeys.nextElement().toString();
					e=prelements.nextElement().toString();

					c.gridx = 2;
					((JLabel)v.elementAt(i)).setText(k);
					gb.setConstraints((JLabel)v.elementAt(i),c);
					centerpanel.add((JLabel)v.elementAt(i));
					i++;

					if((k.equals("status"))||(k.equals("isManaged"))||(k.equals("discover"))||(k.equals("mapName")))
					{
						((JTextField) v.elementAt(i)).setEditable(false);
						((JTextField) v.elementAt(i)).setBackground(Color.white);
					}
					else
						((JTextField) v.elementAt(i)).setEditable(true);

					((JTextField) v.elementAt(i)).setText(e);
					c.gridx = 3;
					gb.setConstraints((JTextField)v.elementAt(i),c);
					centerpanel.add((JTextField)v.elementAt(i));
					j++;

				}
				centerpanel.updateUI();
				centerpanel.setVisible(true);
				return "selected container is found"; 
			}
			else 
				return"SELECTED CONTAINER IS NOT FOUND";
		}
		else 
			return"SELECTED MAP OR CONTAINER NOT FOUND";
	}

	public String modifyFunction(String map)
	{ 	
		Properties p1 = new Properties();;
		int i=0;
		if(v.size()>0)// v.size give the number of properties of the container
		{
			for(;i<(2*ks);i++)//k is the number of keys in the properties
			{
				String  propertykey = ((JLabel) v.elementAt(i)).getText();
				i++;
				String propertyvalue = ((JTextField) v.elementAt(i)).getText();
				p1.put(propertykey,propertyvalue);
			}
		}

		if(p1.size()!=0)
		{
			try
			{
				if(updatecontainer.updateContainer(map,p1))
				{ 
					if(centerpanel.getComponentCount()>0)
					{
						centerpanel.removeAll();
						v.clear();
					}
					centerpanel.updateUI();
					return"Container is Updated:";
				}
				else
					return"Container is not updated";
			}
			catch(Exception er)
			{
				return"error occured in update container"; 
			}
		}
		else//this is not possible
			return" Modified Container properties not found";
	}
	public void itemStateChanged(ItemEvent ce)
	{
		String mapName =(String)mapnames.getSelectedItem();
		Vector v = updatecontainer.getSymbolNames(mapName);	
		setSymbolNames(v);
	}
	private void setSymbolNames(Vector v)
	{

		if( symbolNames == null )
			symbolNames = new JComboBox();
		else
			symbolNames.removeAllItems();

		if(v != null && v.size() >= 0)
		{
			String name = null;
			for(int i= 0; i< v.size(); i++)
			{
				name =(String)v.elementAt(i);
				name = name.substring(0,name.indexOf("\t")); 
				symbolNames.addItem(name);
			}
		}
	}   
}
//----------------------------------------------------------------------//

class AddContainer extends JPanel implements ActionListener,FocusListener
{   //decleration of variables
    Properties propertiesforContainer = new Properties();
    public String[] containerproperties= new String[13];
    String mode = "MODE :ADD CONTAINER\n";
    JLabel[] label = new JLabel[16];
    JTextField[] text = new JTextField[16];
    JTextArea status = new JTextArea(2,10);
    JButton ok;
    JButton cancel;
   JButton userProps; 
    // addcontainer is instantiated here
     GetMapFunctions containeraddition = null;
    public AddContainer()
    {	
	setLayout(new BorderLayout());//for parent 

	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();

	JPanel p1 = new JPanel();
	p1.setLayout(gb);//for child panel
	c.anchor = GridBagConstraints.WEST;
	Font f =new Font("Dialog",Font.BOLD,10);
	
	JScrollPane notes = new JScrollPane(status);
	c.insets=new Insets(3,1,3,1);
	label[0]= new JLabel("NAME");
       	c.gridx = 0;
	c.gridy = 0;
	gb.setConstraints(label[0],c);
	p1.add(label[0]);
	
	label[1]= new JLabel("LABEL");
	c.gridy = 1;
	gb.setConstraints(label[1],c);
	p1.add(label[1]);
	
	label[2]= new JLabel("MAPNAME");
	c.gridy = 2;
	gb.setConstraints(label[2],c);
	p1.add(label[2]);

	label[3]= new JLabel("OBJNAME");
	c.gridy = 3;
	gb.setConstraints(label[3],c);
	p1.add(label[3]);

	label[4]= new JLabel("LAYOUT");
	c.gridy = 4;
	gb.setConstraints(label[4],c);
	p1.add(label[4]);

	label[5]= new JLabel("ICON NAME");
	c.gridy = 5;
	gb.setConstraints(label[5],c);
	p1.add(label[5]);

	label[6]= new JLabel("MENUNAME");
	c.gridy = 6;
	gb.setConstraints(label[6],c);
	p1.add(label[6]);

	label[7]= new JLabel("OBJTYPE");
	c.gridy = 7;
	gb.setConstraints(label[7],c);
	p1.add(label[7]);

       	label[8]= new JLabel("ANCHORED");
	c.gridy = 8;
	gb.setConstraints(label[8],c);
	p1.add(label[8]);

       	label[9]= new JLabel("PARENT NAME");
	c.gridy =9;
	gb.setConstraints(label[9],c);
	p1.add(label[9]);

	label[10]= new JLabel("GROUP NAME");
	c.gridy =10;
	gb.setConstraints(label[10],c);
	p1.add(label[10]);

	

	label[11]= new JLabel("X");
	c.gridy =11;
	gb.setConstraints(label[11],c);
	p1.add(label[11]);
	
	label[12]= new JLabel("Y");
	c.gridy =12;
	gb.setConstraints(label[12],c);
	p1.add(label[12]);

	label[13]= new JLabel("HEIGHT");
	c.gridy =13;
	gb.setConstraints(label[13],c);
	p1.add(label[13]);

	label[14]= new JLabel("WIDTH");
	c.gridy =14;
	gb.setConstraints(label[14],c);
	p1.add(label[14]);
	
	label[15]= new JLabel("CONTAINMENT");
	c.gridy =15;
	gb.setConstraints(label[15],c);
	p1.add(label[15]);

	for(int i=0;i<16;i++)
	{
	    text[i] = new JTextField(10);
	    text[i].addFocusListener(this);
	    text[i].setFont(f);
	    c.gridy =i;
	    c.gridx = 1;
	    gb.setConstraints(text[i],c);
	    p1.add(text[i]);
	}
	
	//c.insets = new Insets(10,1,0,1);
	userProps = new JButton("User Properties");
	userProps.addActionListener(this);
	c.gridx=0;
	c.gridy=16;
	gb.setConstraints(userProps,c);
	p1.add(userProps);
	
	c.anchor = GridBagConstraints.CENTER;
	ok = new JButton("OK");
	ok.addActionListener(this);
	c.gridx=1;
	//c.gridy=16;
	gb.setConstraints(ok,c);
	p1.add(ok);
	
	cancel = new JButton("CANCEL");
	cancel.addActionListener(this);
	c.gridx=2;	//c.gridy=16;
	gb.setConstraints(cancel,c);
	p1.add(cancel);
		
	status.setFont(f);
	status.setText(mode);
	status.setEditable(false);

	
	add(p1,BorderLayout.CENTER);
	add(status,BorderLayout.SOUTH);
	
    }

    public void actionPerformed(ActionEvent ae)
    {
	Object obj = ae.getSource();
   UserProps userPropertiesDialog;
	 String map=null; 
	 String container=null;
	 map = text[2].getText();
	 container = text[0].getText();
	if((obj == ok)||(obj == userProps))
	{
		if(obj==userProps)
		{
			userPropertiesDialog = new UserProps();
			Properties userProperties = userPropertiesDialog.getUserProperties();
			if(userProperties !=null && userProperties.size()>0)
			{
				Enumeration key = userProperties.keys();
				for(; key.hasMoreElements();)
				{	
					String UserKey =(String) key.nextElement();
					propertiesforContainer.put( UserKey, userProperties.getProperty(UserKey));
				}
			}
			return;
		}
		if((map !=null)&&(container !=null)&&(!map.trim().equals(""))&&(!container.trim().equals("")))
	    {		    
		propertiesforContainer.put("name",text[0].getText());
		propertiesforContainer.put("label",text[1].getText()); 
		propertiesforContainer.put("mapName",text[2].getText());
		propertiesforContainer.put("objName",text[3].getText());
		propertiesforContainer.put("layout",text[4].getText());
		propertiesforContainer.put("iconName",text[5].getText());   
		propertiesforContainer.put("menuName",text[6].getText());
		propertiesforContainer.put("objType",text[7].getText());
		propertiesforContainer.put("anchored",text[8].getText());
		propertiesforContainer.put("parentName",text[9].getText());
		propertiesforContainer.put("groupName",text[10].getText());
		propertiesforContainer.put("x",text[11].getText());
		propertiesforContainer.put("y",text[12].getText());
		propertiesforContainer.put("height",text[13].getText());
		propertiesforContainer.put("width",text[14].getText());
		propertiesforContainer.put("containment",text[15].getText());
		
		containeraddition = new GetMapFunctions();
		try
		{
		    if(containeraddition.addContainer(map,propertiesforContainer))
		    {
			status.setText(mode+"       "+container+"CONTAINER IS ADDED");
			for(int i=0;i<16;i++)
			{
			    text[i].setText("");
			}
		    }
		    else
			status.setText(mode+"   "+container+"CONTAINER IS NOT ADDED");
		    
		}
		catch(Exception e)
		{
		    System.out.println("ERROR IN ADD CONTAINER"+e);
		}	       
		
		   
	    }
	    else 
		status.setText(mode+"PLEASE ENTER THE MAP&CONTAINER");
	}
	if(obj==cancel)
	{
	    status.setText(mode+"add container is canceled");
	    for(int i=0;i<16;i++)
	    {
		text[i].setText("");
	    }
	    
	}
	
	
    }
    public void focusGained(FocusEvent fe)
    {
	status.setText(mode);
    }
    
    public void focusLost(FocusEvent f)
    {
    }      
    
}

//------------------------------------------------------------------------//

class DeleteContainer extends JPanel implements ActionListener,ItemListener
{
	String mode = "MODE :DELETE CONTAINER\n";
	JButton DeleteButton;
	JLabel mapname;
	JLabel containername;

	String[] nameofmap; 
	JComboBox mapnames;
	JComboBox symbolNames;

	JTextArea text = new JTextArea(2,10);
	JPanel center;
	GetMapFunctions  getDeleteFunction = new GetMapFunctions();
	public  DeleteContainer()
	{
		//layout for panel
		setLayout(new BorderLayout());
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		Font f =new Font("Dialog",Font.BOLD,10);

		DeleteButton = new JButton("DELETE");
		DeleteButton.addActionListener(this);
		mapname = new JLabel("MAPNAME");
		containername = new JLabel("CONTAINER NAME");
		
		nameofmap = getDeleteFunction.getMapNameList();
		if( nameofmap == null)
		{
			nameofmap = new String[0];
		}
		mapnames= new JComboBox(nameofmap) ;
		mapnames.addItemListener(this);

		String mapName =(String) mapnames.getSelectedItem();	
		Vector v = getDeleteFunction.getSymbolNames(mapName);	
		setSymbolNames(v);

		mapnames.setPreferredSize(new Dimension(250,20));
		symbolNames.setPreferredSize(new Dimension(250,20));

		text.setFont(f);
		text.setEditable(false);
		text.setText(mode);

		center = new JPanel();
		center.setLayout(gb);
		c.insets =new Insets(2,0,2,1);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		gb.setConstraints(mapname,c);
		center.add(mapname);

		c.gridx = 1;//	c.gridy = 0;
		gb.setConstraints(mapnames,c);
		center.add(mapnames);



		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		gb.setConstraints(containername,c);
		center.add(containername);

		c.gridx = 1;      //c.gridy = 1;
		gb.setConstraints(symbolNames,c);
		center.add(symbolNames);

		c.insets = new Insets(30,0,2,1);
		c.anchor = GridBagConstraints.CENTER;	//	c.gridx = 0;
		c.gridy = 2;
		gb.setConstraints(DeleteButton,c);
		center.add(DeleteButton);

		JScrollPane notes = new JScrollPane(text);
		add(center,BorderLayout.CENTER);
		add(notes,BorderLayout.SOUTH);
	}

	public void actionPerformed (ActionEvent ae)
	{
		Object obj = ae.getSource();
		if(obj == DeleteButton)
		{
			String map =(String)mapnames.getSelectedItem();
			String container = (String)symbolNames.getSelectedItem();

			if((map !=null)&&(container !=null)&&(!map.trim().equals(""))&&(!container.trim().equals("")))
			{
				try
				{
					if(getDeleteFunction.deleteContainer(map,container))
					{
						text.setText(mode+"    "+container+"container is deleted");
						Vector v = getDeleteFunction.getSymbolNames(map);	
						setSymbolNames(v);

					}	
					else
						text.setText(mode+"  "+container+"container is not found");   
				}
				catch(Exception er)
				{
					text.setText(mode+"  "+container+"error in delete ");      
				}

			}
			else
			{
				text.setText(mode+"you have to enter both names");
			}
		}

	}
	public void itemStateChanged(ItemEvent ce)
	{
		String mapName =(String)mapnames.getSelectedItem();
		Vector v = getDeleteFunction.getSymbolNames(mapName);	
		setSymbolNames(v);
	}
	private void setSymbolNames(Vector v)
	{

		if( symbolNames == null )
			symbolNames = new JComboBox();
		else
			symbolNames.removeAllItems();

		if(v != null && v.size() >= 0)
		{
			String name = null;
			for(int i= 0; i< v.size(); i++)
			{
				name =(String)v.elementAt(i);
				name = name.substring(0,name.indexOf("\t")); 
				symbolNames.addItem(name);
			}
		}
	}	
}
//-------------END OF CONTAINER-------------------------------------//
//------------------------------FOR GROUP FUNCTION------------------------------//
//---------------------UPDATE GROUP---------------------------------------//
class UpdateGroup extends JPanel implements ActionListener,ItemListener
    //JPanel implements ActionListener
{   //decleration of variables
    
    JPanel top = new JPanel();
    String mode = "MODE : UPDATE GROUP\n";
    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    JPanel centerpanel = new JPanel();
    JLabel mapname;
    JLabel groupname;
    JButton select;
    JButton modify;

	String[] nameofmap; 
	JComboBox mapnames;
	JComboBox symbolNames;

    JTextArea status = new JTextArea(2,10);
    GetMapFunctions updategroup = new GetMapFunctions();
    public UpdateGroup()
    {
	centerpanel.setLayout(gb);	
	setLayout(new BorderLayout());//for whole panel
	mapname = new JLabel("MAP NAME");
	groupname = new JLabel("GROUP NAME");
	//above two lables and buttons are added to top Panel
	Font f =new Font("Dialog",Font.BOLD,10);

	select = new JButton("SELECT");
	select.addActionListener(this);
	modify = new JButton("MODIFY");
	modify.addActionListener(this);

	nameofmap = updategroup.getMapNameList();
	if( nameofmap == null)
	{
		nameofmap = new String[0];
	}

	mapnames= new JComboBox(nameofmap) ;
	mapnames.addItemListener(this);

	String mapName =(String) mapnames.getSelectedItem();	
	Vector v = updategroup.getSymbolNames(mapName);	
	setSymbolNames(v);
	
	mapnames.setPreferredSize(new Dimension(250,20));
	symbolNames.setPreferredSize(new Dimension(250,20));

	status.setEditable(false);
	status.setText(mode);
	JScrollPane notes = new JScrollPane(status);

	//layout set for top panel
	top.setLayout(gb);
	c.anchor = GridBagConstraints.WEST;
	c.insets = new Insets(2,0,2,1);
	c.gridx = 0;
	c.gridy = 0;
	gb.setConstraints(mapname,c);
	top.add(mapname);
	
	c.gridx = 1;	//c.gridy = 0;
	gb.setConstraints(mapnames,c);
	top.add(mapnames);

       	c.gridx = 0;
	c.gridy = 1;
	gb.setConstraints(groupname,c);
	top.add(groupname);

	c.gridx = 1;	//c.gridy = 1;
	gb.setConstraints(symbolNames,c);
	top.add(symbolNames);

	c.insets = new Insets(30,0,2,1);
	c.anchor = GridBagConstraints.CENTER;
	c.gridy = 2;
	gb.setConstraints(select,c);
	top.add(select);

	c.gridx = 0;	//gridy = 2;
	gb.setConstraints(modify,c);
	top.add(modify);
	c.insets = new Insets(2,0,2,1);

	// components are added to top panel
	add(top,BorderLayout.NORTH);
	add(centerpanel,BorderLayout.CENTER);
	add(notes,BorderLayout.SOUTH);
    }
    int ks=0;//vectorsize for properties
    static Vector v;
    public void actionPerformed(ActionEvent ae)
    {
	 String map=null; 
	 String group=null;
	 map = (String)mapnames.getSelectedItem();
	 group =(String) symbolNames.getSelectedItem();
	 Object obj = ae.getSource();

	 if(obj == select)
	 {
	     if((map !=null)&&(group !=null)&&(!map.trim().equals(""))&&(!group.trim().equals("")))
		 status.setText(mode+group+selectFunction(map,group));
	     else
		 status.setText(mode+"PLEASE ENTER THE MAPNAME & GRUOP");
	 }
	 if((obj == modify))//modify if 1
	 {
	     try
	     {
		 status.setText(mode+group+modifyFunction(map));
		
	     }
	     catch(Exception e)
	     {
		 status.setText(mode+"ERROR OCCURED IN MODIFY PROCESS"); 
	     }
	 }
    }

    public String selectFunction(String map,String group)
    {
	if(centerpanel.getComponentCount()>0)
	{
	    centerpanel.removeAll();
		v.clear();
	    centerpanel.updateUI();
	}
	v= new Vector();
	Properties p;
	try
	{
	  p = updategroup.getGroupProperties(map,group);   
	}
	catch(Exception er)
	{
	   return"error in getting properties";
	}
	if(p!=null)//p should not be null& p.key value should be>0
	{
	    
	    if(p.size()!=0)
	    {
		Enumeration prkeys = p.keys();
		Enumeration prelements = p.elements();
		
		ks= p.size();// k is the number keys properties
		int j=0;
		for(int i=0;i<(2*ks);i++)
		{
		  
		      if((i%2)==1)
			v.addElement(new JTextField(10));
		    else
			v.addElement(new JLabel());
		}
		String e;
		String k;
		
		//for label allaignment
		c.anchor = GridBagConstraints.WEST;
		
		for(int i=0;i<v.size();i++)
		{
		    e =prelements.nextElement().toString();
		    k =prkeys.nextElement().toString();
		   
		        
		    ((JLabel)v.elementAt(i)).setText(k);
		    c.gridx = 0;
		    c.gridy = 0+j;
		    gb.setConstraints((JLabel)v.elementAt(i),c);
		    centerpanel.add((JLabel)v.elementAt(i));
		    i++;
		
		    if((k.equals("status"))||(k.equals("isManaged"))||(k.equals("discover"))||(k.equals("mapName")))
		    {
			((JTextField) v.elementAt(i)).setEditable(false);
			((JTextField) v.elementAt(i)).setBackground(Color.white);
		    }
		    else
			((JTextField) v.elementAt(i)).setEditable(true);

		    ((JTextField) v.elementAt(i)).setText(e);
		    c.gridx = 1;
		    c.gridy =0+j;
		    gb.setConstraints((JTextField)v.elementAt(i),c);
		    centerpanel.add((JTextField)v.elementAt(i));
		    i++;
		    
		    if(!(i<v.size()))
		    {
			centerpanel.updateUI();
			centerpanel.setVisible(true);
			return "properties of selected group";
		    }
		    else{}
		    
		    
		    k=prkeys.nextElement().toString();
		    e=prelements.nextElement().toString();
		    
		    c.gridx = 2;
		    ((JLabel)v.elementAt(i)).setText(k);
		    gb.setConstraints((JLabel)v.elementAt(i),c);
		    centerpanel.add((JLabel)v.elementAt(i));
		    i++;
		    
		    if((k.equals("status"))||(k.equals("isManaged"))||(k.equals("discover"))||(k.equals("mapName")))
		    {
			((JTextField) v.elementAt(i)).setEditable(false);
			((JTextField) v.elementAt(i)).setBackground(Color.white);
		    }
		    else
			((JTextField) v.elementAt(i)).setEditable(true);
		   
		    ((JTextField) v.elementAt(i)).setText(e);
		    c.gridx = 4;
		    c.gridy = 0+j;
		    gb.setConstraints((JTextField)v.elementAt(i),c);
		    centerpanel.add((JTextField)v.elementAt(i));
		    j++; 
		  
		}
		centerpanel.updateUI();
		centerpanel.setVisible(true);
		return "selected group is found"; 
	    }
	    else 
		return"SELECTED GROUP IS NOT FOUND";
	}
	else 
	    return"SELECTED MAP OR GROUP NOT FOUND";
    }
    
    public String modifyFunction(String map)
    { 	
	Properties p1 = new Properties();;
	status.setText(mode+"");
	int i=0;
	if(v.size()>0)//this will give the number of properties of the group
	{
	    for(;i<(2*ks);i++)//k is the number of keys in the properties
	    {
		String  propertykey = ((JLabel) v.elementAt(i)).getText();
		i++;
		String propertyvalue = ((JTextField) v.elementAt(i)).getText();
		p1.put(propertykey,propertyvalue);
	    }
	}
	
	if(p1.size()!=0)
	{
	    try
	    {
		if(updategroup.updateGroup(map,p1))
		{
		    if(centerpanel.getComponentCount()>0)
			{	
				centerpanel.removeAll();
				v.clear();
			}
		    centerpanel.updateUI();

		    return"Group is Updated:";
		}
		else
		    return"Group is not updated";
	    }
	    catch(Exception er)
	    {
		return"error in up date the group";
	    }
	}
	else//this is not possible
	    return" GROUP PROPERTIES IS NULL";
    }
    public void itemStateChanged(ItemEvent ce)
	{
		String mapName =(String)mapnames.getSelectedItem();
		Vector v = updategroup.getSymbolNames(mapName);	
		setSymbolNames(v);
	}
	private void setSymbolNames(Vector v)
	{

		if( symbolNames == null )
			symbolNames = new JComboBox();
		else
			symbolNames.removeAllItems();

		if(v != null && v.size() >= 0)
		{
			String name = null;
			for(int i= 0; i< v.size(); i++)
			{
				name =(String)v.elementAt(i);
				name = name.substring(0,name.indexOf("\t")); 
				symbolNames.addItem(name);
			}
		}
	}      
}
//---------------------ADD GROUP--------------------------------------------//

class AddGroup extends JPanel implements ActionListener,FocusListener
{   //decleration of variables
    Properties propertiesforGroup = new Properties();
    JLabel[] label = new JLabel[14];
    JTextField[] text = new JTextField[14];
    JButton add;
    JTextArea status = new JTextArea(2,10);
    JButton ok;
    JButton cancel;
    JButton userProps;
    String mode = "MODE :ADDGROUP\n";
    // addcontainer is instantiated here
    GetMapFunctions groupaddition = null;
    public AddGroup()
    {
	setLayout (new BorderLayout());//for parent panel 
	
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	
	JPanel p1 = new JPanel();
	p1.setLayout(gb);//for child panel
	
	
	c.insets = new Insets(5,0,5,0); 
	c.anchor = GridBagConstraints.WEST;
	Font f =new Font("Dialog",Font.BOLD,10);
	
	JScrollPane notes = new JScrollPane(status);
	
	label[0]= new JLabel("NAME");
       	c.gridx = 0;
	c.gridy = 0;
	gb.setConstraints(label[0],c);
	p1.add(label[0]);

	
	label[1]= new JLabel("LABEL");
       	c.gridy = 1;
	gb.setConstraints(label[1],c);
	p1.add(label[1]);
	
	label[2]= new JLabel("MAPNAME");
	c.gridy = 2;
	gb.setConstraints(label[2],c);
	p1.add(label[2]);

	label[3]= new JLabel("OBJNAME");
	c.gridy = 3;
	gb.setConstraints(label[3],c);
	p1.add(label[3]);

	label[4]= new JLabel("ICON NAME");
	c.gridy = 4;
	gb.setConstraints(label[4],c);
	p1.add(label[4]);

	label[5]= new JLabel("MENUNAME");
	c.gridy = 5;
	gb.setConstraints(label[5],c);
	p1.add(label[5]);

	label[6]= new JLabel("OBJTYPE");
	c.gridy = 6;
	gb.setConstraints(label[6],c);
	p1.add(label[6]);

   	label[7]= new JLabel("ANCHORED");
	c.gridy = 7;
	gb.setConstraints(label[7],c);
	p1.add(label[7]);

   	label[8]= new JLabel("PARENT NAME");
	c.gridx = 0;
	c.gridy = 8;
	gb.setConstraints(label[8],c);
	p1.add(label[8]);
	
	label[9]= new JLabel("GROUP NAME");
	c.gridx = 0;
	c.gridy = 9;
	gb.setConstraints(label[9],c);
	p1.add(label[9]);

	label[10]= new JLabel("X");
	c.gridx = 0;
	c.gridy = 10;
	gb.setConstraints(label[10],c);
	p1.add(label[10]);

	label[11]= new JLabel("Y");
	c.gridx = 0;
	c.gridy = 11;
	gb.setConstraints(label[11],c);
	p1.add(label[11]);

	label[12]= new JLabel("HEIGHT");
	c.gridx = 0;
	c.gridy = 12;
	gb.setConstraints(label[12],c);
	p1.add(label[12]);

	label[13]= new JLabel("WIDTH");
	c.gridx = 0;
	c.gridy = 13;
	gb.setConstraints(label[13],c);
	p1.add(label[13]);
	
	

	for(int i=0; i<14;i++)
	{
	    text[i] = new JTextField(10);
	    text[i].setFont(f);
	    text[i].addFocusListener(this);
	    c.gridx = 1; 
	    c.gridy = i;
	    gb.setConstraints(text[i],c);
	    p1.add(text[i]);
	}
	
	userProps = new JButton("User Properties");
	userProps.addActionListener(this);
	c.gridx=0;
	c.gridy=14;
	gb.setConstraints(userProps,c);
	p1.add(userProps);
	
	c.anchor = GridBagConstraints.CENTER;
//	c.insets = new Insets(0,0,5,0);
	ok = new JButton("OK");
	ok.addActionListener(this);
	c.gridx=1;
//	c.gridy=14;
	gb.setConstraints(ok,c);
	p1.add(ok);
	
	cancel = new JButton("CANCEL");
	cancel.addActionListener(this);
	c.gridx=2;//	c.gridy=14;
	gb.setConstraints(cancel,c);
	p1.add(cancel);
	
	status.setFont(f);
	status.setEditable(false);
	status.setText(mode);
	
	add(p1,BorderLayout.CENTER);
	add(status,BorderLayout.SOUTH);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
	Object obj = ae.getSource();
	UserProps userPropertiesDialog;
	String map=null; 
	String group=null;
	map = text[2].getText();
	group = text[0].getText();
	if((obj == ok)||( obj == userProps))
	{
		if(obj==userProps)
		{
			userPropertiesDialog = new UserProps();
			Properties userProperties = userPropertiesDialog.getUserProperties();
			if(userProperties !=null && userProperties.size()>0)
			{
				Enumeration key = userProperties.keys();
				for(; key.hasMoreElements();)
				{	
					String UserKey =(String) key.nextElement();
					propertiesforGroup.put( UserKey, userProperties.getProperty(UserKey));
				}
			}
			return;
		}


		if((map !=null)&&(group !=null)&&(!map.trim().equals(""))&&(!group.trim().equals("")))
		{
			propertiesforGroup.put("name",text[0].getText());
			propertiesforGroup.put("label",text[1].getText());
			propertiesforGroup.put("mapName",text[2].getText());
			propertiesforGroup.put("objName",text[3].getText());
			propertiesforGroup.put("iconName",text[4].getText());   
			propertiesforGroup.put("menuName",text[5].getText());
			propertiesforGroup.put("objType",text[6].getText());
			propertiesforGroup.put("anchored",text[7].getText());
			propertiesforGroup.put("parentName",text[8].getText());
			propertiesforGroup.put("groupName",text[9].getText());
			propertiesforGroup.put("x",text[10].getText());
			propertiesforGroup.put("y",text[11].getText());
			propertiesforGroup.put("height",text[12].getText());
			propertiesforGroup.put("width",text[13].getText());

			groupaddition = new GetMapFunctions();
			try
			{
				if(groupaddition.addGroup(map,propertiesforGroup))
				{
					status.setText(mode+"     "+group+"GROUP IS ADDED");
					for(int i=0;i<14;i++)
					{
						text[i].setText("");
					}
				}
				else
					status.setText(mode+"       "+group+"GROUP IS NOT ADDED");

			}
			catch(Exception e)
			{
				System.out.println("ERROR IN ADD GROUP"+e);
			}	       

		}
		else 
			status.setText(mode+"PLEASE ENTER THE MAP&GROUPNAME");

	}

	if(obj==cancel)
	{
	    
	    status.setText(mode+"add group is canceled:");
	    for(int i=0;i<14;i++)
	    {
		text[i].setText("");
	    }
	}
	
    }
    public void focusGained(FocusEvent fe)
    {
	status.setText(mode);
    }
    
    public void focusLost(FocusEvent f)
    {
    }    
}

//--------------DELETEGROUP--------------------------------------------------//

class DeleteGroup extends JPanel implements ActionListener,ItemListener
{
    String mode ="MODE :DELETE GROUP\n";
    JButton DeleteButton;
    JLabel mapname;
    JLabel groupname;
	
	String[] nameofmap; 
	JComboBox mapnames;
	JComboBox symbolNames;

    JTextArea text = new JTextArea(2,10);
    JPanel center;
    GetMapFunctions	getDeleteFunction = new GetMapFunctions();
    public  DeleteGroup()
	{
		//layout for panel
		setLayout(new BorderLayout());
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		Font f =new Font("Dialog",Font.BOLD,10);

		DeleteButton = new JButton("DELETE");
		DeleteButton.addActionListener(this);
		mapname = new JLabel("MAPNAME");
		groupname = new JLabel("GROUP NAME");
		
		nameofmap = getDeleteFunction.getMapNameList();
		if( nameofmap == null)
		{
			nameofmap = new String[0];
		}
		mapnames= new JComboBox(nameofmap) ;
		mapnames.addItemListener(this);

		String mapName =(String) mapnames.getSelectedItem();	
		Vector v = getDeleteFunction.getSymbolNames(mapName);	
		setSymbolNames(v);

		mapnames.setPreferredSize(new Dimension(250,20));
		symbolNames.setPreferredSize(new Dimension(250,20));


		text.setFont(f);
		text.setEditable(false);
		text.setText(mode);

		center = new JPanel();
		center.setLayout(gb);
		c.insets = new Insets(2,0,2,1);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		gb.setConstraints(mapname,c);
		center.add(mapname);

		c.gridx = 1;//	c.gridy = 0;
		gb.setConstraints(mapnames,c);
		center.add(mapnames);


		c.gridx = 0;
		c.gridy = 1;
		gb.setConstraints(groupname,c);
		center.add(groupname);

		c.gridx = 1;//	c.gridy = 1;
		gb.setConstraints(symbolNames,c);
		center.add(symbolNames);
		c.insets = new Insets(30,0,2,1);	
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth =1;//	c.gridx = 0;
		c.gridy = 3;
		gb.setConstraints(DeleteButton,c);
		center.add(DeleteButton);

		JScrollPane notes = new JScrollPane(text);

		add(center,BorderLayout.CENTER);
		add(notes,BorderLayout.SOUTH);
		//setBounds(5,5,500,600);
		//setVisible(true);
	}
      
    public void actionPerformed (ActionEvent ae)
	{
		Object obj = ae.getSource();
		if(obj == DeleteButton)
		{
			String map =(String)mapnames.getSelectedItem();
			String group = (String)symbolNames.getSelectedItem();
			if((map !=null)&&(group !=null)&&(!map.trim().equals(""))&&(!group.trim().equals("")))
			{
			try
				{
					if(getDeleteFunction.deleteGroup(map,group))
					{
						text.setText(mode+group+"group is deleted");
						Vector v = getDeleteFunction.getSymbolNames(map);	
						setSymbolNames(v);
					}	
					else
						text.setText(mode+group+"group is not found");   
				}
				catch(Exception er)
				{
					text.setText("error in delete group");    
				}
			}
			else
				text.setText(mode+"you have to enter both names");
		}
	}
      
	public void itemStateChanged(ItemEvent ce)
	{
		String mapName =(String)mapnames.getSelectedItem();
		Vector v = getDeleteFunction.getSymbolNames(mapName);	
		setSymbolNames(v);
	}
	private void setSymbolNames(Vector v)
	{

		if( symbolNames == null )
			symbolNames = new JComboBox();
		else
			symbolNames.removeAllItems();

		if(v != null && v.size() >= 0)
		{
			String name = null;
			for(int i= 0; i< v.size(); i++)
			{
				name =(String)v.elementAt(i);
				name = name.substring(0,name.indexOf("\t")); 
				symbolNames.addItem(name);
			}
		}
	}

}
//-------------------------------------------------------------------//
//---------------------------END OF GROUP FUNCTIONS--------------------//
//--------------------------------------------------------------------//








