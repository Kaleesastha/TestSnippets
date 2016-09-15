import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/*
 *this class is used to update the map properties like mapname, groupname etc
 *user has to select the particular map from the list.
 *click on SELECT Button to get the properties of the selected map.
 *and user can do any desired changes in the properties.
 *after making  changes  click on MODIFY Button to save the changes in database
 */

public class UpdateMap extends JPanel implements ActionListener  
    //JPanel implements ActionListener
{   //decleration of variables
    
    JPanel top = new JPanel();
   
    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    JPanel centerpanel = new JPanel();

    JComboBox mapnames;
    JLabel propertykey;
    JLabel propertyvalue;
    JButton select;
    JButton modify;
    JTextField key = new JTextField(10);
    JTextField value = new JTextField(10);
    JTextArea status = new JTextArea(2,10);
    String[] nameofmap; 
    GetMapFunctions updatemapfunction = new GetMapFunctions();
    String mode = "MODE :UP DATE MAP\n";
    UpdateMap()
    {
	centerpanel.setLayout(gb);	
	
	if(updatemapfunction.getMapNameList()==null)
	    nameofmap = new String[0];
	else
	    nameofmap = updatemapfunction.getMapNameList();
	
	setLayout(new BorderLayout());
	
	propertykey = new JLabel("KEY");
	propertyvalue = new JLabel("VALUE");
	//above two lables are added to center panel
	
	mapnames  = new JComboBox(nameofmap);
	select = new JButton("SELECT");
	select.addActionListener(this);
	modify = new JButton("MODIFY");
	modify.addActionListener(this);
	
	status.setEditable(false);
	status.setText(mode);
	// components are added to top panel
	top.setLayout(new FlowLayout());
	top.add(mapnames);
	top.add(select);
	top.add(modify);

	add(top,BorderLayout.NORTH);
	add(centerpanel,BorderLayout.CENTER);
	add(status,BorderLayout.SOUTH);

	//setBounds(10,10,800,800);
	setVisible(true);
    }

    int k=0;
    static Vector v;
    public void actionPerformed(ActionEvent ae)
    {
	Object obj = ae.getSource();
	String selectedmapname = mapnames.getSelectedItem().toString();
       
	
	if(selectedmapname!=null)
	{    
	    
	    if((obj == select)&&(selectedmapname.length()!=0))
	    {
		if(centerpanel.getComponentCount()>0)
		{
		    centerpanel.removeAll();
		    v.clear();
	    	status.setText(mode+"");
			centerpanel.updateUI();
		}
		v= new Vector();
		Properties p = updatemapfunction.getPropertiesofSelectedMap(selectedmapname);
		if((p==null)||(p.size()==0))
		{
		    status.setText(mode+selectedmapname+"properties is null");
		    v.removeAllElements();
		    if(centerpanel.getComponentCount()>0)
			centerpanel.removeAll();//
		    //centerpanel.setVisible(false);//for null map
		    return;
		}
		else
		    centerpanel.setVisible(true);
		Enumeration prkeys = p.keys();
		Enumeration prelements = p.elements();
		
		k= p.size();
		int j=0;
		for(int i=0;i<(2*k);i++)
		{
		    if((i%2)==1)
			v.addElement(new JTextField(10));
		    else
			v.addElement(new JLabel());
		}
		//for label allaignment
		c.anchor = GridBagConstraints.WEST;

		for(int i=0;i<v.size();i++)
		{
		    ((JLabel)v.elementAt(i)).setText(prkeys.nextElement().toString());
		    ((JLabel)v.elementAt(i)).setVisible(true);
		    c.gridx = 0;
		    c.gridy = 0+j;
		    gb.setConstraints((JLabel)v.elementAt(i),c);
		    centerpanel.add((JLabel)v.elementAt(i));
		    i++;
		    		    
		    ((JTextField) v.elementAt(i)).setEditable(true);
		    ((JTextField) v.elementAt(i)).setText(prelements.nextElement().toString());
		    c.gridx = 1;
		    gb.setConstraints((JTextField)v.elementAt(i),c);
		    centerpanel.add((JTextField)v.elementAt(i));
		    i++;
		    if(i>=v.size())
		    {
			centerpanel.updateUI();
			centerpanel.setVisible(true);
			return;
		    }
		    else
		    {
		    }
		    c.gridx = 2;
		    ((JLabel)v.elementAt(i)).setText(prkeys.nextElement().toString());
		    ((JLabel)v.elementAt(i)).setVisible(true);
		    gb.setConstraints((JLabel)v.elementAt(i),c);
		    centerpanel.add((JLabel)v.elementAt(i));
		    i++;
		    		    
		    ((JTextField) v.elementAt(i)).setEditable(true);
		    ((JTextField) v.elementAt(i)).setText(prelements.nextElement().toString());
		    c.gridx = 3;
		    gb.setConstraints((JTextField)v.elementAt(i),c);
		    centerpanel.add((JTextField)v.elementAt(i));
		    
		    
		    j++; 
		}
		centerpanel.updateUI();
		centerpanel.setVisible(true);
		return;
	    }
	}
	else
	{
	}
	if ((obj == modify)&&(v.size()!=0))
	{
	    status.setText(mode+"");
	    Properties p = new Properties();
	    int i=0;
	    for(;i<(2*k);i++)
	    {
		
		String  propertykey = ((JLabel) v.elementAt(i)).getText();
		i++;
		String propertyvalue = ((JTextField) v.elementAt(i)).getText();
		p.put(propertykey,propertyvalue);
	    }
	    if(updatemapfunction.updateMap(selectedmapname,p))
	    {
		status.setText(mode+"Map is Updated:");
		if(centerpanel.getComponentCount()>0)
		{
		    centerpanel.removeAll();
		    v.clear();
		}
		centerpanel.updateUI();
		
	    }
	    else
		status.setText(mode+"mapis not updated");
	    
	}
	else
	    status.setText(mode+"");
	
    }
    
    
}

//--------------------------------------------------------------------//
/* To add a Custom map into the system.
 * This facilitates creating of maps based on certain properties .
 *. By custom properties here we mean the
 * the Properties of the ManagedObject which will be used for filtering and creating the
 * map . User can give any properties of TopoDB as Custom Map criteria. 
 * The customprops given will be used to query the TopoDB to filter a set of ManagedObjects
 * matching it and a map comprising of mapsymbols representing these objects will be created

 *click on ADDCUSTOMMAP .In the new Frame fill the custom properties.
 *click on MORE to add properties and FEWER to delete properties.
then click OK Button to add them.
 *clicking CANCEL Button cancels the custom properties.
 */
class UserProps extends  JDialog implements ActionListener
{
    int nooftextfield=0;
    int r=0;
    int a=0;
    String[] properties;
    JLabel title;
    
    JButton more;
    JButton fewer;
    JButton ok;
    JButton cancel;
    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    JPanel p1 = new JPanel();//this is for textfield
    JPanel p2 = new JPanel();//this is for label 
    JPanel p3 = new JPanel();//this is for Buttons (fewer and more) 
    JPanel p4 =new JPanel();// this is for ok and cancel buttons
    JScrollPane texts;
    Vector v =new Vector();
    Properties p =new Properties();

    public UserProps()//JFrame frame)
    { 
       setModal(true);
	   setTitle("Fill more properties");
       //super(frame,"frame",true);
	
	
	//layout settings this is for whole frame
	getContentPane().setLayout(new BorderLayout());
	
	//this is for text field
	p1.setLayout(gb);
	
	//this is for text and label &button (more &fewer)
	p2.setLayout(new BorderLayout());
	
	
	//this is for buttons(more&fewer)
	p3.setLayout(new FlowLayout());
	

	//this is for ok &cancel button
	p4.setLayout(new FlowLayout());

	Font f =new Font("Dialog",Font.BOLD,13);
	title = new JLabel("KEYS      VALUES",SwingConstants.CENTER);
	title.setFont(f);
	int k=1;
	
	v.addElement(new JTextField(10));
	v.addElement(new JTextField(10));
	c.insets = new Insets(3,0,3,0); 
	c.gridx = 0;
	c.gridy = 1;
	gb.setConstraints((JTextField)v.elementAt(nooftextfield),c);
	p1.add((JTextField)v.elementAt(nooftextfield));
	nooftextfield++;
	
	c.gridx = 1;
	c.gridy = 1;
	gb.setConstraints((JTextField)v.elementAt(nooftextfield),c);
	p1.add((JTextField)v.elementAt(nooftextfield));
	nooftextfield++;

	texts = new JScrollPane(p1);
	more = new JButton("MORE");
	more.addActionListener(this);
	
	p3.add(more);//,FlowLayout.RIGHT);
	
	fewer = new JButton("FEWER");
	
	fewer.addActionListener(this);
	
	p3.add(fewer);//,FlowLayout.LEFT);
	
	ok = new JButton("OK");
	ok.addActionListener(this);
	p4.add(ok);//,FlowLayout.RIGHT);
	
	cancel = new JButton("CANCEL");
	cancel.addActionListener(this);
	p4.add(cancel);
	
	p2.add(title,BorderLayout.NORTH);
	p2.add(texts,BorderLayout.CENTER);
	p2.add(p3,BorderLayout.SOUTH);
	getContentPane().add(p2,BorderLayout.CENTER);
	getContentPane().add(p4,BorderLayout.SOUTH);

	setBounds(50,50,600,400);
	setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
	Object obj = ae.getSource();
	
	if(nooftextfield<=2)
	{
	    fewer.setEnabled(false);
	}
	
	if(nooftextfield<0)
	{
	    nooftextfield =0;
	}
	//p1.setVisible(true);
	if(nooftextfield>=0)
	{  
	    if(obj == more)
	    {
		a++;
		
		if(nooftextfield>=2)
		{
		    fewer.setEnabled(true);
		}
		if(a>0)
		{
		    v.addElement(new JTextField(10));
		    v.addElement(new JTextField(10));
		    
		    c.gridx = 0;
		    c.gridy = 1+a;
		    gb.setConstraints((JTextField)v.elementAt(nooftextfield),c);
		    p1.add((JTextField)v.elementAt(nooftextfield));
		    nooftextfield++;
		    
		    c.gridx = 1;
		    gb.setConstraints((JTextField)v.elementAt(nooftextfield),c);
		    p1.add((JTextField)v.elementAt(nooftextfield));
		    nooftextfield++;
		
		    p1.updateUI();
		}
	    }

	    if(obj == fewer)
	    {
		
		if(nooftextfield<=2)
		{
		    fewer.setEnabled(false);
		}
		r++;
		
		
		if((nooftextfield>2)&&(r>=0))
		{
		    nooftextfield--;
		    
		    c.gridx = 0;
		    c.gridy = 1+a;
		    gb.setConstraints((JTextField)v.elementAt(nooftextfield),c);
		    p1.remove((JTextField)v.elementAt(nooftextfield));
		    v.removeElementAt(nooftextfield);
		    nooftextfield--;

		    c.gridx = 1;
		    gb.setConstraints((JTextField)v.elementAt(nooftextfield),c);
		    p1.remove((JTextField)v.elementAt(nooftextfield));
		    v.removeElementAt(nooftextfield); 
		    
		    p1.updateUI();
		    p1.validate();
		    a--;
		}
		
	    }
	}

	//for getting the text keys and elements

	if(obj == ok)
	{
	    int i=0;
	    for(;i<nooftextfield;i++)
	    {
		String  propertykey = ((JTextField) v.elementAt(i)).getText();
		i++;
		String propertyvalue = ((JTextField) v.elementAt(i)).getText();
		p.put(propertykey,propertyvalue);
	    }

	    // System.out.println("entered properties in ok "+p);
	    dispose();//setVisible(false);
	}
	 
	 
	if(obj == cancel)
	{
	    v.removeAllElements();
	    p = null;
	    dispose();//setVisible(false);
	}
    }

    public Properties getUserProperties()
    {
	return p;
    }
}
//-----------------------------------------------------------------------//
/*
 *This panel will show that what are the prperties  needed for add map in the database.
 *here user has two options for adding map in to the system.
 *1.adddefaultmap
 *2.addcustommap

 *for default map no need for extra properties.
 */
class AddMap extends JPanel implements ActionListener,FocusListener
{   //decleration of variables
    Properties propertiesformap = new Properties();
    JLabel[] label = new JLabel[16];
    JButton adddefaultmap;
    JButton addcustommap;
    JTextField[] text= new JTextField[16];
    JComboBox texttopology;
    JTextArea status;
    String[] boxtopology = {"grid","tree","container_tree","ethernet","flow","star","ring"}; 
    Properties cp = new Properties();
    //addmap is instantiated here
    GetMapFunctions mapaddition = null;
    UserProps custommapaddition = null;

    String mode = "MODE :ADD MAP\n";

    public AddMap()
    {   
	setLayout(new BorderLayout());//for parent panel
	
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();

	JPanel panel = new JPanel();
	panel.setLayout(gb);//for child panel
	c.insets = new Insets(5,0,5,0); 
	c.anchor = GridBagConstraints.WEST;

	Font f =new Font("Dialog",Font.BOLD,10);
	label[0] = new JLabel("NAME");
	c.gridx = 0;
	c.gridy = 0;
	gb.setConstraints(label[0],c);
	panel.add(label[0]);
	
	text[0] = new JTextField(10);
	text[0].addFocusListener(this);
	text[0].setFont(f);
	c.gridx = 1;
	gb.setConstraints(text[0],c);
	panel.add(text[0]);

	label[1]= new JLabel("LABEL");
       	c.gridx = 0;
	c.gridy = 1;
	gb.setConstraints(label[1],c);
	panel.add(label[1]);

	text[1] = new JTextField(10);
	text[1].setFont(f);
       	c.gridx = 1;
	gb.setConstraints(text[1],c);
	panel.add(text[1]);

	label[2]= new JLabel("TOPOLOGY");
	c.gridx = 0;
	c.gridy = 2;
	gb.setConstraints(label[2],c);
	panel.add(label[2]);

	texttopology = new JComboBox(boxtopology);
	texttopology.setEditable(true);
	texttopology.setFont(f);
       	c.gridx = 1;
	gb.setConstraints(texttopology,c);
	panel.add(texttopology);

	label[3]= new JLabel("IMAGENAME");
	c.gridx = 0;
	c.gridy = 3;
	gb.setConstraints(label[3],c);
	panel.add(label[3]);

	text[3] = new JTextField(10);
	text[3].setFont(f);
       	c.gridx = 1;
	gb.setConstraints(text[3],c);
	panel.add(text[3]);

	label[4] = new JLabel("MENUNAME");
	c.gridx = 0;
	c.gridy = 4;
	gb.setConstraints(label[4],c);
	panel.add(label[4]);

	text[4] = new JTextField(10);
	text[4].setFont(f);
	c.gridx = 1;
	gb.setConstraints(text[4],c);
	panel.add(text[4]);

       	label[5] = new JLabel("ANCHOR");
	c.gridx = 0;
	c.gridy = 5;
	gb.setConstraints(label[5],c);
	panel.add(label[5]);

	text[5] = new JTextField(10);
	text[5].setFont(f);
       	c.gridx = 1;
	gb.setConstraints(text[5],c);
	panel.add(text[5]);

       	label[6]= new JLabel("HELP DOC");
	c.gridx = 0;
	c.gridy = 6;
	gb.setConstraints(label[6],c);
	panel.add(label[6]);

	text[6] = new JTextField(10);
	text[6].setFont(f);
	text[6].setText("help/maphelp.html");
	text[6].setEditable(true);
       	c.gridx = 1;
	gb.setConstraints(text[6],c);
	panel.add(text[6]);

       	label[7] = new JLabel("MAPSYMBOLRENDERER");
	c.gridx = 0;
	c.gridy = 7;
	gb.setConstraints(label[7],c);
	panel.add(label[7]);

	text[7] = new JTextField(10);
	text[7].setFont(f);
	text[7].setText("com.adventnet.nms.mapui.MapSymbolRendererImpl");
	text[7].setEditable(true);
       	c.gridx = 1;
	gb.setConstraints(text[7],c);
	panel.add(text[7]);

	label[8] = new JLabel("MAPLINKRENDERER");
	c.gridx = 0;
	c.gridy = 8;
	gb.setConstraints(label[8],c);
	panel.add(label[8]);

	text[8] = new JTextField(10);
	text[8].setFont(f);
	text[8].setText("com.adventnet.nms.mapui.MapLinkRendererImpl");
	text[8].setEditable(true);
       	c.gridx = 1;
	gb.setConstraints(text[8],c);
	panel.add(text[8]);
	


	label[9]= new JLabel("MAPLISTENER");
	c.gridx = 0;
	c.gridy = 9;
	gb.setConstraints(label[9],c);
	panel.add(label[9]);

	text[9] = new JTextField(10);
	text[9].setFont(f);
       	c.gridx = 1;
	gb.setConstraints(text[9],c);
	panel.add(text[9]);

	
	label[10]= new JLabel("PARENTMAP KEY");
	c.gridx= 0;
	c.gridy = 10;
	gb.setConstraints(label[10],c);
	panel.add(label[10]);

	text[10] = new JTextField(10);
	text[10].setFont(f);
       	c.gridx = 1;
	gb.setConstraints(text[10],c);
	panel.add(text[10]);
	
	label[11]= new JLabel("INDEX");
	c.gridx= 0;
	c.gridy = 11;
	gb.setConstraints(label[11],c);
	panel.add(label[11]);
	
	text[11] = new JTextField(10);
	text[11].setFont(f);
       	c.gridx = 1;
	gb.setConstraints(text[11],c);
	panel.add(text[11]);
	
	label[12]= new JLabel("TREE ICON FILE NAME");
	c.gridx= 0;
	c.gridy = 12;
	gb.setConstraints(label[12],c);
	panel.add(label[12]);

	text[12] = new JTextField(10);
	text[12].setFont(f);
       	c.gridx = 1;
	gb.setConstraints(text[12],c);
	panel.add(text[12]);
	
	label[13]= new JLabel("AUTO PLACEMENT");
	c.gridx= 0;
	c.gridy = 13;
	gb.setConstraints(label[13],c);
	panel.add(label[13]);

	text[13] = new JTextField(10);
	text[13].setFont(f);
       	c.gridx = 1;
	gb.setConstraints(text[13],c);
	panel.add(text[13]);
	
	

	//c.insets = new Insets(20,0,0,0);
	addcustommap = new JButton("ADD CUSTOM MAP");
	addcustommap.addActionListener(this);
	c.gridx=0;
	c.gridy=16;
	gb.setConstraints(addcustommap,c);
	panel.add(addcustommap);
	
	adddefaultmap = new JButton("ADD DEFAULT MAP");
	adddefaultmap.addActionListener(this);
	c.gridx=1;
	//c.gridy=16;
	gb.setConstraints(adddefaultmap,c);
	panel.add(adddefaultmap);
	
	status = new JTextArea(2,10);
	status.setFont(f);
	status.setText(mode+"");
	status.setEditable(false);
       
	add(panel,BorderLayout.CENTER);
	add(status,BorderLayout.SOUTH);

	//setSize(700,600);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
	Object obj = ae.getSource();
		
	propertiesformap.put("name",text[0].getText());
	propertiesformap.put("label",text[1].getText());    
	propertiesformap.put("topology",texttopology.getSelectedItem().toString());
	propertiesformap.put("imageName",text[3].getText());
	propertiesformap.put("menuName",text[4].getText());    
	propertiesformap.put("anchored",text[5].getText());
	propertiesformap.put("helpDoc",text[6].getText());
	propertiesformap.put("mapSymbolRenderer",text[7].getText());    
	propertiesformap.put("mapLinkRenderer",text[8].getText());
	propertiesformap.put("mapListener",text[9].getText());
	propertiesformap.put("parentMapKey",text[10].getText());
	propertiesformap.put("index",text[11].getText());    
	propertiesformap.put("treeIconFileName",text[12].getText());		
	propertiesformap.put("autoPlacement",text[13].getText());

	//custommapaddition = new Custommap();
	 mapaddition = new GetMapFunctions();
	String dmapname = text[0].getText().toString();
	if(obj==adddefaultmap)
	{	
	    addDefaultMapFunction(dmapname,propertiesformap);
	    
	  return;
	  
	}
	if(obj==addcustommap)
	{
	    //System.out.println("inside custom map");
	     custommapaddition = new UserProps();
	    try
	    {
		
		cp = custommapaddition.getUserProperties();
		
		if((cp!=null)&&(cp.size()>0)&&(!dmapname.trim().equals("")))
		{
		    try
		    {
			if(mapaddition ==null)
			{
			    System.out.println("mapaddition is null");
			}
			if(mapaddition.addSelectedCustomMap(dmapname,propertiesformap,cp))
			    status. setText(mode+"  "+dmapname+"custom map is added");
			else
			    status.setText(mode+"       "+dmapname+"custom map is  not added");
		    }
		    catch(Exception er)
		    {
			System.out.println("error in add Custom Map"+er);
		    }
		    for(int i=0;i<14;i++)
		    {
			if(!((i==2)||(i==6)||(i==7)||(i==8)))
			    text[i].setText("");
		    }
		}
		else
		    status.setText(mode+" please enter the customproperty OR MapName");  
	    }
	    catch(Exception e)
	    {
		System.out.println("ERROR IN ADDCUSTOMMMAP"+e);
	    }
	}
    }

    //
    /*
     *This method used for adding Defaultmap in to the system.
     *using addMap API call
     */    
    public  void addDefaultMapFunction(String mapname,Properties prop)
    {	
	if((prop.size() !=0)&&(!mapname.trim().equals("")))
	{  
	    mapaddition = new GetMapFunctions();
	    try
	    {
		//System.out.println("mapName"+mapname);
		if(mapaddition.addDefaultMap(mapname,prop))
		    status.setText(mode+mapname+"default map is added");
		else
		    status.setText(mode+mapname+"default map is not added"); 
		 for(int i=0;i<12;i++)
		    {
			if(!((i==2)||(i==6)||(i==7)||(i==8)))
			    text[i].setText("");
		    }
	    }
	    catch(Exception e )
	    {
		System.out.println("error"+e);
		
	    }
	}
	else
	    status.setText(mode+"enter the map name");
    }

public void focusGained(FocusEvent fe)
    {
	status.setText(mode);
    }
    
public void focusLost(FocusEvent f)
    {
    }
}



//------------------------------------------------------------------//
/*
 *This class is used for delete the map in the database.
  *using the deleteMap API call.
  *clicking OK Button will delete the selected map.
 */
class DeleteMap extends JPanel implements ActionListener
{
    JButton ok;
    JLabel mapname;
    JComboBox mapnamelist;
    JTextArea text;
    JPanel center;
    String[] mapnames;
    GetMapFunctions getdeleteFunction = null;
    String mode = "MODE:DELETE MAP\n";
    public DeleteMap()
    {  //layout for panel 
	
	setLayout(new BorderLayout());
	GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
	
	Font f =new Font("Dialog",Font.BOLD,10);
	ok = new JButton("OK");
	ok.addActionListener(this);
	mapname = new JLabel("MAPNAMES");
	text = new JTextArea(2,10);
	text.setFont(f);
	text.setText(mode+"");
	text.setEditable(false);
	
	getdeleteFunction = new GetMapFunctions();
	//getting the map names
	if(getdeleteFunction.getMapNameList()==null)
	    mapnames = new String[0];
	else
	    mapnames = getdeleteFunction.getMapNameList();

	mapnamelist = new JComboBox(mapnames);
	
	center = new JPanel();
	center.setLayout(gb);
	c.insets = new Insets(5,0,5,0);
	c.gridx = 1;
	c.gridy = 0;
	gb.setConstraints(mapname,c);
	center.add(mapname);
	c.insets = new Insets(20,0,5,0);
	c.gridy = 1;
	gb.setConstraints(mapnamelist,c);
	center.add(mapnamelist);
		
	c.gridx = 2;
	gb.setConstraints(ok,c);
	center.add(ok);

	text =new JTextArea(2,10);
	text.setEditable(false);
	text.setText(mode);
	add(center,BorderLayout.CENTER);
	add(text,BorderLayout.SOUTH);
	setVisible(true);
	
    }
    
    public void actionPerformed (ActionEvent ae)
    {
	Object obj = ae.getSource();
	String selectedmapname = new String();
	if(mapnamelist.getSelectedItem()!=null)
	   {
	       selectedmapname = mapnamelist.getSelectedItem().toString();
	       if((obj == ok)&&(selectedmapname.length()!=0))
	       {
		  if(getdeleteFunction.deleteSelectedMap(selectedmapname))
		  {
		      text.setText(mode+"  "+selectedmapname+"is deleted");
		      mapnamelist.removeItem(selectedmapname);
		  }
		  else
		     text.setText(mode+"  "+selectedmapname+"is not deleted");  
	       }
	   }
	else//this condition wont come
	    text.setText(mode+"    "+selectedmapname+"no map is available");
    }
}
    
 

//---------------END OF MAPFUNCTINS--------------------------------------//

//---------------------------OTHERS PANEL--------------------------------//
/**
 *This panel used for perform three functions related to map.
 *like DoesTheMApExist,getCustomMapNames,getDefaultmapNames,
 *getMapNames.user can get the performence of above
 *by clicking on the corresponding buttons.
 */
//-----------------------------------------------------------------//

class OthersPanel extends JPanel implements ActionListener
{
	//getMapFunctions is instantiated here
	GetMapFunctions otherFunctions = null;

	JPanel mainPanel = new JPanel();


	JList customNames;
	JList defaultNames;
	JList allMapNames;

	JPanel left= new JPanel();
	JPanel right = new JPanel();
	//status panel for showing the result
	JPanel forStatus = new JPanel();

	JButton clear = new JButton("CLEAR");

	JPanel forCustomMapNames = new JPanel();
	JPanel forDefaultMapNames = new JPanel();
	JPanel forMapNames = new JPanel();
	JPanel forMapSymbols = new JPanel();
	JPanel forMapObjects = new JPanel();
	
	JPanel forMapExist = new JPanel();
	JTextField mapinput= new JTextField(10);
	JTextField mapoutput = new JTextField(10);
	JButton mapButton;
	JButton GetSymbolNames;
	JButton GetSymbolNamesAWObject;

	ButtonGroup forsplitpane = new ButtonGroup();
	JRadioButton rmapExist;
	JRadioButton rcustomMapNames;
	JRadioButton rdefaultMapNames;
	JRadioButton rmapNames;
	JRadioButton symbolNames;
	JRadioButton symbolNamesAWObject;
	
	JTextField status = new JTextField(10);

	public OthersPanel()
	{
		setLayout(new BorderLayout());


		JPanel empty = new JPanel();
		empty.add(new JLabel("  "));

		mainPanel.setLayout(new BorderLayout());
		//this method return the others panel(splitPane)
		//splitPane consists of two Panel
		//1.left panel
		//2.right Panel

		JSplitPane resultPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		resultPanel.setRightComponent(RightPanel());
		resultPanel.setLeftComponent(LeftPanel());

		resultPanel.setVisible(true);
		mainPanel.add(empty,BorderLayout.NORTH);
		mainPanel.add(resultPanel,BorderLayout.CENTER);
		add(mainPanel,BorderLayout.CENTER);

	}
	public void actionPerformed(ActionEvent ae)
	{
		//cardlayout  is used for show one panel at a time in right side panel of the split pane
		CardLayout card = (CardLayout)(right.getLayout());
		//for clear the status bar
		status.setText("");

		// for select the action
		Object selectedbutton = ae.getSource();

		//this will invoke the getNames method to get the Severity Names
		if(selectedbutton==rmapExist)
		{
			((JPanel)mapExist()).updateUI();
			card.show(right,"FORMAPEXIST");
			return;
		}
		//this will invoke the getColor method to get the color related to Severity Name.
		if(selectedbutton==rcustomMapNames)
		{
			((JPanel)customMapNames()).updateUI();
			card.show(right,"FORCUSTOMMAPNAMES");
			return;  
		}
		//this will invoke the getValue method to get the value related to Severity Name.
		if(selectedbutton==rdefaultMapNames)
		{
			((JPanel)defaultNames()).updateUI();
			card.show(right,"FORDEFAULTMAPNAMES");
			return;
		}
		//this will invoke the severityName method to get the name  related to Severity Value.
		if(selectedbutton==rmapNames)
		{
			((JPanel)mapNames()).updateUI();
			card.show(right,"FORMAPNAME");
			return;
		}

		//clear is used to clear the textfields of input and output on the selected panel.

		if(selectedbutton==clear)
		{
			if(forMapExist.isShowing())
			{
				mapinput.setText("");
				mapoutput.setText("");
				return;
			}
			else
				return;
		}


		//this will invoke the getColor method in SeverityInfo after getting the required input from the user. 
		if(selectedbutton == mapButton)
		{
			String mapname =(String)mapinput.getText();
			if(!(mapname.trim().equals("")))
			{
				if(otherFunctions.doesTheMapExist(mapname))
				{
					status.setText("MAP EXISTS");
					mapoutput.setText("true");
					return;
				}
				else
				{
					mapoutput.setText("false");
					status.setText("MAP DOES NOT EXIST");
					return;
				}
			}
			else
			{
				status.setText("enter the mapname");
				return;
			}
		}
		if((selectedbutton== symbolNames)||(selectedbutton == GetSymbolNames))
		{
			otherFunctions = new GetMapFunctions();
			if(selectedbutton == symbolNames )
			{
				if(forMapSymbols.isShowing())
				{
					if(forMapSymbols.getComponentCount()>3)
						forMapSymbols.remove(3);
				}
				else
				{
					if(forMapSymbols.getComponentCount()>0)
						forMapSymbols.removeAll();
					mapSymbols();
				}
			}
			else
			{
					if(forMapSymbols.getComponentCount()>3)
						forMapSymbols.remove(3);
				String mapName = (String)(((JTextField)((forMapSymbols).getComponent(1))).getText());	
				if(!(mapName.trim().equals("")))
				{
					if(mapName.indexOf(".netmap")== -1)
						mapName = mapName+".netmap";

					Vector v = otherFunctions.getSymbolNames(mapName);	
					if(v ==null)
						return;

					JList symbolNames = new JList(v);
					GridBagConstraints c = new GridBagConstraints();
					c.anchor = GridBagConstraints.CENTER;
					c.gridx = 0;
					c.gridy = 4;

					JScrollPane listPane = new JScrollPane(symbolNames);

					listPane.setPreferredSize(new Dimension(300,220));
					addComponent((GridBagLayout)forMapSymbols.getLayout(),c,forMapSymbols,listPane,"JScrollPane");
				}
				else
					status.setText("Enter the Mapname");
			}
			forMapSymbols.updateUI();
		 	card.show(right,"FORMAPSYMBOLS");
			return;
		}
		if((selectedbutton== symbolNamesAWObject)||(selectedbutton == GetSymbolNamesAWObject))
		{
			otherFunctions = new GetMapFunctions();
			if(selectedbutton == symbolNamesAWObject )
			{
				if(forMapObjects.isShowing())
				{
					if(forMapObjects.getComponentCount()>3)
						forMapObjects.remove(3);
				}
				else
				{
					if(forMapObjects.getComponentCount()>0)
						forMapObjects.removeAll();
					mapSymbolsAWObject();
				}
			}
			else
			{
				if(forMapObjects.getComponentCount()>3)
					forMapObjects.remove(3);
				String objName = (String)(((JTextField)((forMapObjects).getComponent(1))).getText());	
				
				if(!(objName.trim().equals("")))
				{
					Vector v = otherFunctions.getSymbolNamesAssociatedWithObject(objName);	
					if(v ==null)
						return;

					JList symbolNames = new JList(v);
					GridBagConstraints c = new GridBagConstraints();
					c.anchor = GridBagConstraints.EAST;
					c.gridx = 0;
					c.gridy = 4;

					JScrollPane listPane = new JScrollPane(symbolNames);

					listPane.setPreferredSize(new Dimension(300,220));
					addComponent((GridBagLayout)forMapObjects.getLayout(),c,forMapObjects,listPane,"JScrollPane");
				}
				else
					status.setText("Enter the ObjectName");
			}
			forMapObjects.updateUI();
		 	card.show(right,"FORMAPOBJECT");
			return;
		} 
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


		rmapExist      = new JRadioButton("DOES THE MAP EXIST");
		rcustomMapNames= new JRadioButton("CUSTOM MAP NAMES");
		rdefaultMapNames = new JRadioButton("DEFAULT MAP NAMES");
		rmapNames  = new JRadioButton("ALL MAP NAMES");
		symbolNames = new JRadioButton("Map Symbols");
		symbolNamesAWObject = new JRadioButton("Map Symbols AWObject");
		
		//forsplitpane is Button Group.
		// five Buttons are added with this ButtonGroup
		forsplitpane.add(rmapExist);
		forsplitpane.add(rcustomMapNames);
		forsplitpane.add(rdefaultMapNames);
		forsplitpane.add(rmapNames);
		forsplitpane.add(symbolNames);
		forsplitpane.add(symbolNamesAWObject);
		
		
		c.gridx =0;
		c.gridy =0;
		addComponent(gb,c,left,rmapExist,"JRadioButton");

		c.gridy =1;
		addComponent(gb,c,left,rcustomMapNames,"JRadioButton");

		c.gridy =2;
		addComponent(gb,c,left,rdefaultMapNames,"JRadioButton");

		c.gridy= 3;
		addComponent(gb,c,left,rmapNames,"JRadioButton");

		c.gridy= 4;
		addComponent(gb,c,left,symbolNames,"JRadioButton");
		
		c.gridy= 5;
		addComponent(gb,c,left,symbolNamesAWObject,"JRadioButton");

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
		right.add(forMapExist,"FORMAPEXIST");
		right.add(forCustomMapNames,"FORCUSTOMMAPNAMES");
		right.add(forDefaultMapNames,"FORDEFAULTMAPNAMES");
		right.add(forMapNames,"FORMAPNAME");
		right.add(forMapSymbols,"FORMAPSYMBOLS");
		right.add(forMapObjects,"FORMAPOBJECT");
		
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

	//this method will return the panel for the does the MapExist of the given MapName.   
	public JPanel mapExist()
	{
		otherFunctions = new GetMapFunctions();

		if((forMapExist.getComponentCount())>0)
			forMapExist.removeAll();
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2,2,2,0);
		forMapExist.setLayout(gb);

		JLabel lvalue = new JLabel("MAP NAME");
		c.gridy=0;
		c.gridx =0;
		addComponent(gb,c,forMapExist,lvalue,"JLabel");

		c.gridx =1;
		addComponent(gb,c,forMapExist,mapinput,"JTextField");

		c.gridy =1;
		mapoutput.setEditable(false);
		mapoutput.setBackground(Color.white);
		addComponent(gb,c,forMapExist,mapoutput,"JTextField");

		JLabel maplabel = new JLabel("RESULT");
		c.gridx =0;
		addComponent(gb,c,forMapExist,maplabel,"JLabel");

		c.gridx=1;
		c.insets = new Insets(30,0,0,0);
		mapButton = new JButton("SUBMIT");
		c.gridy =3;
		c.anchor = GridBagConstraints.CENTER;
		addComponent(gb,c,forMapExist,mapButton,"JButton");

		c.gridx =0;
		addComponent(gb,c,forMapExist,clear,"JButton");

		return forMapExist;
	}

	public JPanel customMapNames()
	{	

		otherFunctions = new GetMapFunctions();

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(2,2,2,0);
		forCustomMapNames.setLayout(gb);

		if((forCustomMapNames.getComponentCount())>0)
			forCustomMapNames.removeAll();

		if((otherFunctions.getCustomMapNames())!=null)
			customNames = new JList(otherFunctions.getCustomMapNames()); 
		else
			customNames = new JList();  
		JLabel lnames = new JLabel("CustomMapNames");
		c.gridx=0;
		c.gridy =0;
		addComponent(gb,c,forCustomMapNames,lnames,"JLabel");

		c.gridwidth =2;
		c.gridy =1;
		JScrollPane listPane = new JScrollPane(customNames);
		listPane.setPreferredSize(new Dimension(300,220));
		addComponent(gb,c,forCustomMapNames,listPane,"JScrollPane");

		return forCustomMapNames;

	}

	public JPanel defaultNames()
	{	

		otherFunctions = new GetMapFunctions();

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(2,2,2,0);
		forDefaultMapNames.setLayout(gb);

		if((forDefaultMapNames.getComponentCount())>0)
			forDefaultMapNames.removeAll();

		if((otherFunctions.getDefaultmapNames())!=null)
			defaultNames = new JList(otherFunctions.getDefaultmapNames()); 
		else
			defaultNames = new JList();  
		JLabel lnames = new JLabel("DefaultMapNames");
		c.gridx=0;
		c.gridy =0;
		addComponent(gb,c,forDefaultMapNames,lnames,"JLabel");

		c.gridwidth =2;
		c.gridy =1;
		JScrollPane listPane = new JScrollPane(defaultNames);
		listPane.setPreferredSize(new Dimension(320,220));
		addComponent(gb,c,forDefaultMapNames,listPane,"JScrollPane");

		return forDefaultMapNames;

	}

	public JPanel mapNames()
	{	

		otherFunctions = new GetMapFunctions();

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(2,2,2,0);
		forMapNames.setLayout(gb);

		if((forMapNames.getComponentCount())>0)
			forMapNames.removeAll();

		if((otherFunctions.getMapNameList())!=null)
			allMapNames = new JList(otherFunctions.getMapNameList()); 
		else
			allMapNames = new JList();  
		JLabel lnames = new JLabel("MapNames");
		c.gridx=0;
		c.gridy =0;
		addComponent(gb,c,forMapNames,lnames,"JLabel");

		c.gridwidth =2;
		c.gridy =1;
		JScrollPane listPane = new JScrollPane(allMapNames);
		listPane.setPreferredSize(new Dimension(300,220));
		addComponent(gb,c,forMapNames,listPane,"JScrollPane");

		return forMapNames;

	}  
	public JPanel mapSymbols()
	{	
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(2,2,2,0);
		forMapSymbols.setLayout(gb);
		
		JLabel lnames = new JLabel("Enter MapName");
		c.gridx=0;
		c.gridy =0;
		addComponent(gb,c,forMapSymbols,lnames,"JLabel");

		JTextField MapName = new JTextField(10);
		c.gridx =1;
		addComponent(gb,c,forMapSymbols,MapName,"JTextField");
		
		GetSymbolNames = new JButton("get Symbol Names");
		c.gridx =0;
		c.gridy =1;
		addComponent(gb,c,forMapSymbols,GetSymbolNames,"JButton");
		return forMapSymbols;
	}
	public JPanel mapSymbolsAWObject()
	{	
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(2,2,2,0);
		forMapObjects.setLayout(gb);

		JLabel lnames = new JLabel("Enter ObjectName");
		c.gridx=0;
		c.gridy =0;
		addComponent(gb,c,forMapObjects,lnames,"JLabel");

		//c.anchor = GridBagConstraints.WEST;
		JTextField ObjectName = new JTextField(10);
		c.gridy =1;
		addComponent(gb,c,forMapObjects,ObjectName,"JTextField");

		//c.anchor = GridBagConstraints.CENTER;
		GetSymbolNamesAWObject = new JButton("get Symbol Names Associated with Object");
		c.gridy =2;
		addComponent(gb,c,forMapObjects,GetSymbolNamesAWObject,"JButton");
		return forMapObjects;
	}

} 
