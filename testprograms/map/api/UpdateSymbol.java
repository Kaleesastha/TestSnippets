import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
//UpdateSymbol class
/*This class is used to updateSymbol properties.
 *User has to give the symbol name and mapname in which the symbol to be update.
 *after entering the symbolname and mapname user has  to click on SELECT Button.
 *then user can change the property of the symbol shown in the panel.
 *to save the changes of the symbol properties in the database  click on the MODIFY Button
 *  
 */


class UpdateSymbol extends JPanel implements ActionListener,ItemListener
  
{   //decleration of variables
    
    String mode ="MODE:UPDATE SYMBOL\n";
    JPanel top = new JPanel();
    
    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    JPanel centerpanel = new JPanel();
    JLabel[] label = new JLabel[2];
    JButton select;
    JButton modify;
    String[] nameofmap; 
    JComboBox mapnames;
	JComboBox symbolNames;
	
    JTextArea status = new JTextArea(2,10);
	//MapFunctions is instansiated 
	GetMapFunctions updatesymbol = new GetMapFunctions();
    UpdateSymbol()
    {
	setLayout(new BorderLayout());//for parent panel
	centerpanel.setLayout(gb);	

	
	label[0] = new JLabel("MAP NAME");
	label[1] = new JLabel("SYMBOLNAME");
	//above two lables and buttons are added to top Panel
	Font f =new Font("Dialog",Font.BOLD,10);

	select = new JButton("SELECT");
	select.addActionListener(this);
	modify = new JButton("MODIFY");
	modify.addActionListener(this);
		
	nameofmap = updatesymbol.getMapNameList();

	if( nameofmap == null)
	{
		nameofmap = new String[0];
	}
		
	mapnames= new JComboBox(nameofmap) ;
	mapnames.addItemListener(this);
	
	String mapName =(String) mapnames.getSelectedItem();	
	Vector v = updatesymbol.getSymbolNames(mapName);	
	setSymbolNames(v);

	mapnames.setPreferredSize(new Dimension(250,20));
	symbolNames.setPreferredSize(new Dimension(250,20));

	status.setEditable(false);
	status.setText(mode);
	//layout set for top panel
	top.setLayout(gb);

	c.insets = new Insets(2,0,2,1);
	c.anchor = GridBagConstraints.WEST;
	c.gridx = 0;
	c.gridy = 0;
	gb.setConstraints(label[0],c);
	top.add(label[0]);
		
	c.gridx = 1;//c.gridy=0;
	gb.setConstraints(mapnames,c);
	top.add(mapnames);

	c.gridx = 0;
	c.gridy = 1;
	gb.setConstraints(label[1],c);
	top.add(label[1]);

	c.gridx = 1;//c.gridy=1;
	gb.setConstraints(symbolNames,c);
	top.add(symbolNames);
	
	c.insets = new Insets(30,0,2,1);
	c.anchor = GridBagConstraints.CENTER;
	c.gridy = 2;
	gb.setConstraints(select,c);
	top.add(select);

	c.gridx = 0;//c.gridy =2;
	gb.setConstraints(modify,c);
	top.add(modify);
	c.insets = new Insets(2,0,2,1);
	// components are added to top panel
	
	
	add(top,BorderLayout.NORTH);
	
	add(centerpanel,BorderLayout.CENTER);
	add(status,BorderLayout.SOUTH);
	setVisible(true);
	
    }
    int ks=0;//vector size
    static Vector v;
    public void actionPerformed(ActionEvent ae)
    {
	String map=null; 
	String symbol=null;
	map =(String)mapnames.getSelectedItem();
	symbol = (String)symbolNames.getSelectedItem();
	
	Object obj = ae.getSource();
	if(obj == select)
	{
	    if((map !=null)&&(symbol !=null)&&(!map.trim().equals(""))&&(!symbol.trim().equals("")))
	    {  
		
		status.setText(mode+"     "+symbol+"  "+selectFunction(map,symbol));
		
	    }
	    else
		status.setText(mode+"PLEASE SELECT THE MAPNAME&SYMBOLNAME");  
	    
	}
	if(obj == modify)
	{
	    try
	    {
		status.setText(mode+"   "+symbol+modifyFunction(map));
		
	    }
	    catch(Exception e)
	    {
		status.setText(mode+"Error occured in modify Symbol");
	    }
	}
    }
    //--------------------------SELECTFUNCTION---------------------------//
    public String selectFunction(String map,String symbol)
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
	   p = updatesymbol.getPropertiesofSymbol(map,symbol);   
	}
	catch(Exception er)
	{
	    return"error in getting properties of symbol";
	}
	if(p!=null)//p should not be null&it should have some values
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
		for(int i=0;i<v.size();i++)
		{
		    
		     e =prelements.nextElement().toString();
		     k =prkeys.nextElement().toString();
		    
		    ((JLabel)v.elementAt(i)).setText(k);
		    ((JLabel)v.elementAt(i)).setVisible(true);
		    c.gridx = 0;
		    c.gridy = 0+j;
		    //for label allaignment
		    c.anchor = GridBagConstraints.WEST;
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
		    
		    if(!(i<v.size()))
			{
				centerpanel.setVisible(true);
				centerpanel.updateUI();
				return "properties of selected symbol";
			}   
			   
		    else{}
		    
		    k=prkeys.nextElement().toString();
		    e=prelements.nextElement().toString();
		   		   		    
		     c.gridx = 2;
		    ((JLabel)v.elementAt(i)).setText(k);
		    ((JLabel)v.elementAt(i)).setVisible(true);
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
		centerpanel.setVisible(true);
		centerpanel.updateUI();
		return "selected symbol is found"; 
	    }
	    else 
		return"SELECTED SYMBOL IS NOT FOUND";
	    
	}
	else
	    return "SYMBOL OR MAP IS NOT FOUND";
    }
    
    //------------------------MODIFYFUNCTION-----------------------//
    public String modifyFunction(String map)
    { 	
	Properties p1 = new Properties();
	
	int i=0;
	if(v.size()>0)//v.size() give the number of properties for the symbol
	{
	    for(;i<(2*ks);i++)//k is the number of keys in the properties
	    {//for
		
		String  propertykey = ((JLabel) v.elementAt(i)).getText();
		i++;
		String propertyvalue = ((JTextField) v.elementAt(i)).getText();
		p1.put(propertykey,propertyvalue);
	    }//for
	}
	if(p1.size()!=0)
	{
	    try
	    {
		if(updatesymbol.updateSymbol(map,p1))
		{
		    if(centerpanel.getComponentCount()>0)
			{	
				centerpanel.removeAll();
				v.clear();
			}
		    centerpanel.updateUI();

		    return"    Symbol is Updated:";
		    
		}
		else
		    return"     Symbol is not updated";
	    }
	    catch(Exception er)
	    {
		return"error in updating the Symbol";
	    }
	}
	else//this is not possible
	    return" Modified Symbol properties not found";
    }

	public void itemStateChanged(ItemEvent ce)
	{
		String mapName =(String)mapnames.getSelectedItem();
		Vector v = updatesymbol.getSymbolNames(mapName);	
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

//----------------ADD SYMBOL-----------------------------------------------//
/*
 * This class used to add a Symbol to a map.
 * user has to give the properties shown below
 *	mapName		Name of the Map in which the symbol is to be added.
 *  	name		MapSymbolName
 *	label		MapSymbolLabel
 *	objName		Name of the Managed Object
 *	iconName	Name of the Image file which should be painted to 
&nbsp;			represent this symbol		
 *	menuName	Name of the symbol specific menu file which should be 
&nbsp;			invoked while selecting the MapSymbol
 *	status		status of the MapSymbol
* 	anchored	when anchored is set to true , this particular symbol won't be layout
 *	parentName	Name of the MapContainer in which symbol is to be added.
&nbsp;			When parentName is null, the symbol will be added to 
&nbsp;			the MapModel.
 *
 *	objType		Managed object type.It is an integer. 
 *			The predefined values are
 *	  			  0		represents symbol
 *	  			  1		represents Node
 *	  			  2		represents Network
 *				  3		represents gateway
 *				  4		represents sub-symbol
 *				  5		represents site
 * &nbsp;		User can specify his own OBJTYPE which should be defined in 
 
 *user can add the symbol to the map by clicking OK Button.
 *clicking CANCEL Button will cancel the addition of symbols.
 */


class AddSymbol extends JPanel implements ActionListener,FocusListener
{   //decleration of variables
    Properties propertiesforSymbol = new Properties();
    String mode="MODE:ADD SYMBOL\n";
    JLabel symbolname;
    //JLabel properties;
    JLabel[] label = new JLabel[14];
    JButton add;
    JTextField[] text = new JTextField[14];
    JTextArea status;
    JButton ok;
    JButton cancel;
  	JButton userProps;
    // addsymbol is instantiated here
     GetMapFunctions symboladdition = null;
    public AddSymbol()
    { 
	setLayout(new BorderLayout());//for parent panel
	
	JPanel panel = new JPanel();
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	panel.setLayout(gb);
	c.insets = new Insets(5,0,5,0); 
	c.anchor = GridBagConstraints.WEST;
	Font f =new Font("Dialog",Font.BOLD,10);
	
      
	label[0]= new JLabel("NAME");
       	c.gridx = 0;
	c.gridy = 0;
	gb.setConstraints(label[0],c);
	panel.add(label[0]);

	label[1]= new JLabel("LABEL");
      	c.gridy = 1;  
	gb.setConstraints(label[1],c);
	panel.add(label[1]);
	
	label[2]= new JLabel("MAPNAME");
	c.gridy = 2;
	gb.setConstraints(label[2],c);
	panel.add(label[2]);

	label[3]= new JLabel("OBJNAME");
	c.gridy = 3;
	gb.setConstraints(label[3],c);
	panel.add(label[3]);

	label[4]= new JLabel("ICON NAME");
	c.gridy = 4;
	gb.setConstraints(label[4],c);
	panel.add(label[4]);

	label[5]= new JLabel("MENUNAME");
	c.gridy = 5;
	gb.setConstraints(label[5],c);
	panel.add(label[5]);

	label[6]= new JLabel("OBJTYPE");
	c.gridy = 6;
	gb.setConstraints(label[6],c);
	panel.add(label[6]);

       	label[7]= new JLabel("ANCHORED");
	c.gridy = 7;
	gb.setConstraints(label[7],c);
	panel.add(label[7]);

       	label[8]= new JLabel("PARENT NAME");
	c.gridy = 8;
	gb.setConstraints(label[8],c);
	panel.add(label[8]);
	
       	label[9] = new JLabel("HEIGHT");
	c.gridy = 9;
	gb.setConstraints(label[9],c);
	panel.add(label[9]);

       	label[10]= new JLabel("WIDTH");
	c.gridy = 10;
	gb.setConstraints(label[10],c);
	panel.add(label[10]);
	
	label[11]= new JLabel("GROUP NAME");
	c.gridy = 11;
	gb.setConstraints(label[11],c);
	panel.add(label[11]);
	
	label[12]= new JLabel("X");
	c.gridy = 12;
	gb.setConstraints(label[12],c);
	panel.add(label[12]);
	
	label[13]= new JLabel("Y");
	c.gridy = 13;
	gb.setConstraints(label[13],c);
	panel.add(label[13]);
	
	

	for(int i=0;i<14;i++)
	{
	    text[i] = new JTextField(10);
	    text[i].setFont(f);
	    text[i].addFocusListener(this);
	    c.gridx = 1;
	    c.gridy =i;
	    gb.setConstraints(text[i],c);
	    panel.add(text[i]);
	}
	userProps = new JButton("User Properties");
	userProps.addActionListener(this);
	c.gridx=0;
	c.gridy=14;
	gb.setConstraints(userProps,c);
	panel.add(userProps);

	c.anchor = GridBagConstraints.CENTER;
	ok = new JButton("OK");
	ok.addActionListener(this);
	c.gridx=1;
//	c.gridy=14;
	gb.setConstraints(ok,c);
	panel.add(ok);
	
	
	cancel = new JButton("CANCEL");
	cancel.addActionListener(this);
	c.gridx=2;//c.gridy=14;
	gb.setConstraints(cancel,c);
	panel.add(cancel);
	
	status = new JTextArea(2,10);
	status.setFont(f);
	status.setEditable(false);
	status.setText(mode);
	add(panel,BorderLayout.CENTER);
	add(status,BorderLayout.SOUTH);
	//setSize(700,600);

	
    }

	public void actionPerformed(ActionEvent ae)
	{
		UserProps userPropertiesDialog;
		Object obj = ae.getSource();
		String map=null; 
		String symbol=null;
		map = text[2].getText();
		symbol = text[0].getText();
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
						propertiesforSymbol.put( UserKey, userProperties.getProperty(UserKey));
					}
				}
			return;	
			}
			if((map !=null)&&(symbol !=null)&&(!map.trim().equals(""))&&(!symbol.trim().equals("")))
			{

				propertiesforSymbol.put("name",text[0].getText());
				propertiesforSymbol.put("label",text[1].getText());
				propertiesforSymbol.put("mapName",text[2].getText());
				propertiesforSymbol.put("objName",text[3].getText());
				propertiesforSymbol.put("iconName",text[4].getText());
				propertiesforSymbol.put("menuName",text[5].getText());
				propertiesforSymbol.put("objType",text[6].getText());
				propertiesforSymbol.put("anchored",text[7].getText());
				propertiesforSymbol.put("parentName",text[8].getText());
				propertiesforSymbol.put("height",text[9].getText());
				propertiesforSymbol.put("width",text[10].getText());
				propertiesforSymbol.put("groupName",text[11].getText());
				propertiesforSymbol.put("x",text[12].getText());
				propertiesforSymbol.put("y",text[13].getText());

				symboladdition = new GetMapFunctions();
				try
				{
					if(symboladdition.addMapSymbol(map,propertiesforSymbol))
					{
						status.setText(mode+"      "+symbol+"SYMBOL IS ADDED");
						for(int i=0;i<14;i++)
						{
							text[i].setText("");
						}
					}
					else
						status.setText(mode+"  "+symbol+"SYMBOL IS NOT ADDED");

				}
				catch(Exception e)
				{
					System.out.println("ERROR IN ADD SYMBOL"+e);
				}	       
			}
			else 
				status.setText(mode+"PLEASE ENTER THE MAP&SYMBOLNAME");
		}
		//  mapaddition = new GetMapFunctions();
		if(obj==cancel)
		{
			status.setText(mode);
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

//---------------DELETE FUNCTION--------------------------------------//
/*
 *This class is used to delete the symbol from the map.
 *User has to give the name of the symbol and name of the map in which the symbol exists.
 *deleting  symbol in  the map is done  by clicking  on DeleteButton.
 *this change is saved in database by calling deleteSymbol() in MapAPI
 */
class DeleteSymbol extends JPanel implements ActionListener,ItemListener
{
	String mode ="MODE : DELETE SYMBOL\n";
	JButton DeleteButton;
	JLabel mapname;
	JLabel symbolname;

	String[] nameofmap; 
	JComboBox mapnames;
	JComboBox symbolNames;

	JTextArea text;
	JPanel center;
	GetMapFunctions getDeleteFunction = new GetMapFunctions(); 
	JLabel empty;

	public  DeleteSymbol()
	{
		//layout for panel
		setLayout(new BorderLayout());
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		Font f =new Font("Dialog",Font.BOLD,10);
		DeleteButton = new JButton("DELETE");
		DeleteButton.addActionListener(this);
		mapname = new JLabel("MAPNAME");
		symbolname = new JLabel("SYMBOLNAME");
		
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

		center = new JPanel();
		center.setLayout(gb);
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2,0,2,1);
		c.gridx = 0;
		c.gridy = 0;
		gb.setConstraints(mapname,c);
		center.add(mapname);

		c.gridx = 1;//c.gridy = 0;
		gb.setConstraints(mapnames,c);
		center.add(mapnames);

		c.gridx = 0;
		c.gridy = 1;
		gb.setConstraints(symbolname,c);
		center.add(symbolname);

		c.gridx = 1;//c.gridy = 1;
		gb.setConstraints(symbolNames,c);
		center.add(symbolNames);

		empty = new JLabel(" ");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		gb.setConstraints(empty,c);
		center.add(empty);

		c.gridwidth =1;
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 3;
		gb.setConstraints(DeleteButton,c);
		center.add(DeleteButton);

		text = new JTextArea(2,10);
		text.setFont(f);
		text.setEditable(false);
		text.setText(mode);

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
			String symbol = (String)symbolNames.getSelectedItem();

			if((map !=null)&&(symbol !=null)&&(!map.trim().equals(""))&&(!symbol.trim().equals("")))
			{

				try
				{
					boolean b = getDeleteFunction.deleteSelectedSymbol(map,symbol);
					if(b)
					{
						text.setText(mode+symbol+"symbol is deleted");
						Vector v = getDeleteFunction.getSymbolNames(map);	
						setSymbolNames(v);
					}	
					else
						text.setText(mode+symbol+"symbol is not found");   
				}
				catch(Exception er)
				{
					text.setText("Error in deleting the symbol"+symbol);
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
    
    



//--------------------------END-OF SYBOLS FUNCTIONS-----------------------//




















