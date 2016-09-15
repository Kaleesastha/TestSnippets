import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
//this is for add update class
/*This class is used to updateLink properties
 *User has to give the link name and mapname in which the link to be update.
 *after entering the linkname and mapname user has  to click on SELECT Button.
 *then user can change the property of the link shown in the panel.
 *to save the changes of the link properties in the database  click on the MODIFY Button
 *  
 */
class UpdateLink extends JPanel implements ActionListener,ItemListener
    //JPanel implements ActionListener
{   //decleration of variables
	String mode = "MODE : UPDATE LINK\n";
	JPanel top = new JPanel();

	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	JPanel centerpanel = new JPanel();

	JLabel mapname;
	JLabel linkname;
	JButton select;
	JButton modify;

	String[] nameofmap; 
	JComboBox mapnames;
	JComboBox symbolNames;

	JTextArea status = new JTextArea(2,10);
	//String[] nameofmap; 
	GetMapFunctions updatelink = new GetMapFunctions();

	public UpdateLink()
	{
		centerpanel.setLayout(gb);	
		setLayout(new BorderLayout());//for whole panel

		mapname = new JLabel("MAP NAME");
		linkname = new JLabel("LINK NAME");
		//above two lables and buttons are added to top Panel
		Font f =new Font("Dialog",Font.BOLD,10);

		select = new JButton("SELECT");
		select.addActionListener(this);
		modify = new JButton("MODIFY");
		modify.addActionListener(this);

		nameofmap = updatelink.getMapNameList();
		if( nameofmap == null)
		{
			nameofmap = new String[0];
		}

		mapnames= new JComboBox(nameofmap) ;
		mapnames.addItemListener(this);
		
		String mapName =(String) mapnames.getSelectedItem();	
		Vector v = updatelink.getSymbolNames(mapName);	
		setSymbolNames(v);
		
		mapnames.setPreferredSize(new Dimension(250,20));
		symbolNames.setPreferredSize(new Dimension(250,20));

		status.setEditable(false);
		status.setText(mode);
		//layout set for top panel
		top.setLayout(gb);
		c.insets = new Insets(2,0,2,1);
		c.gridx = 0;
		c.gridy = 0;
		gb.setConstraints(mapname,c);
		top.add(mapname);

		c.gridx = 1;//c.gridy = 0;
		//	textmap.addFocusListener(this);
		gb.setConstraints(mapnames,c);
		top.add(mapnames);

		c.gridx = 0;
		c.gridy = 1;
		gb.setConstraints(linkname,c);
		top.add(linkname);

		c.gridx = 1;//c.gridy = 1;
		//	textlink.addFocusListener(this);
		gb.setConstraints(symbolNames,c);
		top.add(symbolNames);

		c.insets = new Insets(30,0,2,1);
		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 3;
		gb.setConstraints(select,c);
		top.add(select);

		c.gridx = 0;//c.gridy = 3;
		gb.setConstraints(modify,c);
		top.add(modify);
		c.insets = new Insets(2,0,2,1);
		// components are added to top panel
		add(top,BorderLayout.NORTH);
		add(centerpanel,BorderLayout.CENTER);
		add(status,BorderLayout.SOUTH);

		//setBounds(10,10,800,800);
		setVisible(true);
	}

	int ks=0;
	static Vector v;
	public void actionPerformed(ActionEvent ae)
	{
		String map=null; 
		String link=null;

		map = (String)mapnames.getSelectedItem();
		link =(String) symbolNames.getSelectedItem();

		Object obj = ae.getSource();
		if(obj == select)
		{
			if((map !=null)&&(link !=null)&&(!map.trim().equals(""))&&(!link.trim().equals("")))
			{ 
				status.setText(mode+link+selectFunction(map,link));

			} 
			else
			{
				status.setText(mode+"PLEASE ENTER THE MAPNAME & LINKNAME");
			}

		}

		if((obj == modify))//modify if 1
		{
			try
			{
				status.setText(mode+link+modifyFunction(map));

			}
			catch(Exception e)
			{
				status.setText(mode+"ERROR OCCURED IN MODIFY PROCESS"); 
			}
		}
	}

	public String selectFunction(String map,String link)
	{	
		//for clear the component inside panel
		if(centerpanel.getComponentCount()>0)
		{
			centerpanel.removeAll();
			v.clear();
			centerpanel.updateUI();
		}
		v= new Vector();
		Properties p = updatelink.getPropertiesOfLink(map,link);   
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
					c.gridx = 1;//c.gridy = 1+j;
					gb.setConstraints((JTextField)v.elementAt(i),c);
					centerpanel.add((JTextField)v.elementAt(i));
					i++;

					if(!(i<v.size()))
					{
						centerpanel.updateUI();
						centerpanel.setVisible(true);
						return "properties of selected Linkl";
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
				//System.out.println("inside the propertiesloop"+p);
				return "selected link is found"; 
			}
			else 
				return"SELECTED LINK IS NOT FOUND";
		}
		else 
			return"SELECTED MAP OR LINK NOT FOUND";
	}

	public String modifyFunction(String map)
	{ 	
		Properties p1 = new Properties();;
		status.setText(mode+"");
		int i=0;
		if(v.size()>0)//this will give the number of properties of the link
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
			if(updatelink.updateLink(map,p1))
			{
				if(centerpanel.getComponentCount()>0)
				{	
					centerpanel.removeAll();
					v.clear();
				}

				centerpanel.updateUI();
				return"Link is Updated:";
			}
			else
				return"Link is not updated";

		}
		else//this is not possible
			return" Modified Link properties not found";
	}
	public void itemStateChanged(ItemEvent ce)
	{
		String mapName =(String)mapnames.getSelectedItem();
		Vector v = updatelink.getSymbolNames(mapName);	
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
/**
 *this class is used to add link into the map by giving the mapname and linkname and other properties of the link.
 *then the link is added by clicking on OK Button.
 *clicking on CANCEL Button will cancel the addition of link.
 */ 

class AddLink extends JPanel implements ActionListener,FocusListener
{   //decleration of variables
    Properties propertiesforLink = new Properties();
    String mode = "MODE :ADD LINK\n";
    JLabel[] label = new JLabel[13];
    JTextField[] text = new JTextField[13];
    JTextArea status= new JTextArea(2,10);

    JButton ok;
    JButton cancel;
    JButton userProps;
    // addlink is instantiated here
     GetMapFunctions linkaddition = null;
    
    public AddLink()
    {  
	setLayout(new BorderLayout());//for parentpanel

	JPanel panel = new JPanel();
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	panel.setLayout(gb);//for child panel
	
	Font f =new Font("Dialog",Font.BOLD,10);
	c.anchor = GridBagConstraints.WEST;
	
	c.insets = new Insets(2,0,2,1);

	label[0]= new JLabel("NAME");
       	c.gridx = 0;
	c.gridy = 0;
	gb.setConstraints(label[0],c);
	panel.add(label[0]);
	
	
	label[1] = new JLabel("MAPNAME");
	c.gridy = 1;
	gb.setConstraints(label[1],c);
	panel.add(label[1]);

	label[2]= new JLabel("SOURCE");
	c.gridy = 2;
	gb.setConstraints(label[2],c);
	panel.add(label[2]);
	
	label[3]= new JLabel("DEST");
	c.gridy = 3;
	gb.setConstraints(label[3],c);
	panel.add(label[3]);
	
	label[4]= new JLabel("THICKNESS");
       	c.gridy = 4;
	gb.setConstraints(label[4],c);
	panel.add(label[4]);

	label[5]= new JLabel("OBJ NAME");
       	c.gridy = 5;
	gb.setConstraints(label[5],c);
	panel.add(label[5]);

	label[6]= new JLabel("ANCHORED");
	c.gridy = 6;
	gb.setConstraints(label[6],c);
	panel.add(label[6]);

	label[7]= new JLabel("LABEL");
	c.gridy = 7;
	gb.setConstraints(label[7],c);
	panel.add(label[7]);
	
       	label[8]= new JLabel("OBJ TYPE");
	c.gridy = 8;
	gb.setConstraints(label[8],c);
	panel.add(label[8]);

    label[9]= new JLabel("PARENT NAME");
	c.gridy = 9;
	gb.setConstraints(label[9],c);
	panel.add(label[9]);
	
	label[10]= new JLabel("GROUP NAME");
	c.gridy =10 ;
	gb.setConstraints(label[10],c);
	panel.add(label[10]);

	label[11]= new JLabel("MENUNAME");
	c.gridy = 11;
	gb.setConstraints(label[11],c);
	panel.add(label[11]);
	
	label[12]= new JLabel("ICON NAME");
	c.gridy = 12;
	gb.setConstraints(label[12],c);
	panel.add(label[12]);

	for(int i=0;i<13;i++)
	{
	    text[i] = new JTextField(10);
	    text[i].setFont(f);
	    text[i].addFocusListener(this);
	    c.gridx = 1;
	    c.gridy = i;
	    gb.setConstraints(text[i],c);
	    panel.add(text[i]);
	}

	userProps = new JButton("User Properties");
	userProps.addActionListener(this);
	c.gridx=0;
	c.gridy=13;
	gb.setConstraints(userProps,c);
	panel.add(userProps);

	c.anchor = GridBagConstraints.CENTER;
	ok = new JButton("OK");
	ok.addActionListener(this);
	c.gridx=1;
	//c.gridy=12;
	gb.setConstraints(ok,c);
	panel.add(ok);

	c.insets = new Insets(5,0,5,0);
	cancel = new JButton("CANCEL");
	cancel.addActionListener(this);
	c.gridx=2;//c.gridy=12;
	gb.setConstraints(cancel,c);
	panel.add(cancel);
		
	status.setFont(f);
	status.setEditable(false);
	status.setText(mode);

	add(panel,BorderLayout.CENTER);
	add(status,BorderLayout.SOUTH);
	
	//setSize(700,600);
    }

    public void actionPerformed(ActionEvent ae)
    {
	Object obj = ae.getSource();
	UserProps userPropertiesDialog;
	String map=null; 
	String link=null;
	map = text[1].getText();
	link = text[0].getText();
	
	if((obj == ok)||(obj == userProps ))
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
					propertiesforLink.put( UserKey, userProperties.getProperty(UserKey));
				}
			}
			return;
		}    //checking of mapname &&link name

	    if((link!=null)&&(!link.trim().equals(""))&&(map!=null)&&(!map.trim().equals("")))
	    {
		propertiesforLink.put("name",text[0].getText());
		propertiesforLink.put("mapName",text[1].getText()); 
		propertiesforLink.put("source",text[2].getText());    
		propertiesforLink.put("dest",text[3].getText());
		propertiesforLink.put("thickness",text[4].getText());
		propertiesforLink.put("objName",text[5].getText());    
		propertiesforLink.put("anchored",text[6].getText());
		propertiesforLink.put("label",text[7].getText());
		propertiesforLink.put("objType",text[8].getText());
		propertiesforLink.put("parentName",text[9].getText());    
		propertiesforLink.put("groupName",text[10].getText());    
		propertiesforLink.put("menuName",text[11].getText());
		propertiesforLink.put("iconName",text[12].getText());
		
		linkaddition = new GetMapFunctions();
		try
		{
		    //linkproperties[7] is a map name
		   if(linkaddition.addMapLink(map,propertiesforLink))
		   {
		       status.setText(mode+"  "+link+"LINK IS ADDED: IN THIS "+map);
			   for(int i=0;i<13;i++)
			   {
				   text[i].setText("");
			   }  
		   }
		   else
		       status.setText(mode+"  "+link+"LINK IS NOT ADDED:");
		}
		catch(Exception e)
		{
		    status.setText("ERROR IN ADD LINK"+e);
		}
	    }
	    else
	    {
		status.setText(mode+"please enter the mapname&linkname");
	    }
	    
	}
	else
	{//future enhancement
	}
	
	//
	if(obj==cancel)
	{
	    
	    status.setText(mode+"add link is canceled");    
		for(int i=0;i<13;i++)
		{
			text[i].setText("");
		}
	}
	
    }
    
    public void focusGained(FocusEvent fe)
    {
	status.setText(mode);
    }
    
    public void focusLost(FocusEvent fe1)
    {
    }
}

//------------------------------------------------------------------//
/**
 *
 *This class is used to deleteLink properties
 *User has to give the link name and mapname in which the link to be delete.
 *after entering the linkname and mapname user has  to click on DELETE Button.
 *this change is saved in database by callinf deleteLink()in MapAPI. 
 */
class DeleteLink extends JPanel implements ActionListener,ItemListener
{
	String mode = "MODE :DELETE LINK\n";
	JButton DeleteButton;
	JLabel mapname;

	JLabel linkname;

	String[] nameofmap; 
	JComboBox mapnames;
	JComboBox symbolNames;

	JTextArea text;
	JPanel center;
	GetMapFunctions getDeleteFunction = new GetMapFunctions();
	JLabel empty;

	public  DeleteLink()
	{
		//layout for panel
		setLayout(new BorderLayout());

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		Font f =new Font("Dialog",Font.BOLD,10);

		DeleteButton = new JButton("DELETE");

		DeleteButton.addActionListener(this);

		mapname = new JLabel("MAPNAME");

		linkname = new JLabel("LINKNAME");

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

		text = new JTextArea(2,10);

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

		c.gridx =1;//c.gridy = 0;
		gb.setConstraints(mapnames,c);
		center.add(mapnames);

		c.gridx = 0;
		c.gridy = 1;
		gb.setConstraints(linkname,c);
		center.add(linkname);

		c.gridx = 1;//c.gridy = 1;
		gb.setConstraints(symbolNames,c);
		center.add(symbolNames);

		empty = new JLabel(" ");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		gb.setConstraints(empty,c);
		center.add(empty);

		c.anchor = GridBagConstraints.EAST;
		c.gridwidth =1;//c.gridx = 0;
		c.gridy = 4;
		gb.setConstraints(DeleteButton,c);
		center.add(DeleteButton);


		add(center,BorderLayout.CENTER);
		add(text,BorderLayout.SOUTH);
		//setBounds(5,5,500,600);
		//setVisible(true);
	}

	public void actionPerformed (ActionEvent ae)
	{
		Object obj = ae.getSource();
		if(obj == DeleteButton)
		{
			String map =(String)mapnames.getSelectedItem();
			String link = (String)symbolNames.getSelectedItem();
			if((map !=null)&&(link !=null)&&(!map.trim().equals(""))&&(!link.trim().equals("")))
			{
				try
				{
					boolean b = getDeleteFunction.deleteSelectedLink(map,link);
					if(b)
					{
						text.setText(mode+link+"link is deleted");
						Vector v = getDeleteFunction.getSymbolNames(map);	
						setSymbolNames(v);
					}

					else
					{
						text.setText(mode+link+"link is not found");   
					}
				}
				catch(Exception er)
				{

					text.setText(mode+"error is occured in link deletion");   
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
    

//-------------------------------------------------------------------------//
//--------------END OF LINK FUNCTIONS----------------------------------------//







