
/*
$Id: UserInterfaceMap.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $
*/

/**
 UserInterfaceMap.java
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * this UserInterface used for addition,deletion and Update the Map Objects in the map database.
 *addition,deletion and update the Symbol Objects in the mapdatabase.
 *the same three function can be performed for Link Objects and Group Objects
 *and Container Objects also.
 */
public class UserInterfaceMap extends JFrame implements ActionListener
{
    //declaration of panes and other symbols
    JTabbedPane jtp = new JTabbedPane();
    public String PanelSelection = "";
    
    //Button for continue the process
    JPanel selectionPanel;
    JButton start;
    JTextField host;
    JRadioButton swdefault;
    JRadioButton serverhost;
    ButtonGroup group =new ButtonGroup();
    
    GetMapFunctions hostname;
    
    public  UserInterfaceMap()
    {

	//start is the panel which will start the process of test the MapAPI Method
	jtp.addTab("START",Start());
	

	jtp.addTab("MAP",new MapPanel());
       	jtp.addTab("SYMBOL",new SymbolPanel());
	jtp.addTab("CONTAINER",new ContainerPanel());
	jtp.addTab("LINK",new LinkPanel());
	jtp.addTab("GROUP",new GroupPanel());
	jtp.addTab("OTHERS",new OthersPanel());

	int count=jtp.getTabCount();
	for(int i=0;i<count-1;i++)
	{
	    jtp.setEnabledAt(i+1,false);
	}
	
	addWindowListener( new WindowAdapter( )
			   {
			       public void windowClosing( WindowEvent e )
				   {
				       System.exit(0);
				   }   
			   });
				   
	getContentPane().add(jtp);
	
	setBounds(50,30,670,750);
	setVisible(true);
    }
    public static void main(String a[])
    {
	new UserInterfaceMap();
    }
    public void actionPerformed(ActionEvent ae)
    {
	Object obj = ae.getSource(); 
	
	if((obj==swdefault)||(obj==serverhost))
	{
	    int count=jtp.getTabCount();
	    for(int i=0;i<count-1;i++)
	    {
		jtp.setEnabledAt(i+1,false);
	    }
	}
	
	
	
	if(obj==swdefault)
	{
	    host.setEditable(false);
	    host.setText("");
	    return;
	}
	if(obj==serverhost)
	{
	    host.setText("");
	    host.setEditable(true);
	    return;
	}
		
	if(obj == start)
	{
	    String thostname = host.getText();
	    if((thostname!=null)&&(!(thostname.trim().equals(""))))
	    {
		
		hostname.setHostName(thostname);
	    }
	    else 
		hostname.setHostName(null);
	    int count=jtp.getTabCount();
	    for(int i=0;i<count-1;i++)
	    {
		jtp.setEnabledAt(i+1,true);
	    }
	    //to select the next pane
	    jtp.setSelectedIndex((jtp.indexOfTab((String)jtp.getTitleAt((jtp.getSelectedIndex())+1))));
	    
	    return;
	}
	
    }
    //this method will return panel
    //the panel consists of three options for getting rmi connection .
    //User can select any one of these option. then user should give the corresponding input for getting connection 
    public JPanel Start()
    {
	selectionPanel = new JPanel();
	
	selectionPanel.setLayout(new BorderLayout());
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	c.insets = new Insets(2,0,2,2);
	
	JPanel centerPanel = new JPanel();
	centerPanel.setLayout(gb);
	
	JLabel lhost = new JLabel("Enter Hostname");

	start = new JButton("CONTINUE");
	swdefault = new JRadioButton("StartWith Default",true);
	group.add(swdefault);

	serverhost = new JRadioButton("Start With desired hostname");
	group.add(serverhost);

	host = new JTextField(10);
	
	c.anchor = GridBagConstraints.WEST;
	c.gridwidth =2;
	c.gridx = 0;
	c.gridy = 0;
	swdefault.addActionListener(this);
	gb.setConstraints(swdefault,c);
	centerPanel.add(swdefault);

	c.gridy =1;
	serverhost.addActionListener(this);
	gb.setConstraints(serverhost,c);
	centerPanel.add(serverhost);
	
	c.anchor = GridBagConstraints.CENTER;	
	c.gridwidth =1;
	c.gridy =2;
	gb.setConstraints(lhost,c);
	centerPanel.add(lhost);

	c.gridx = 1;
	host.setEditable(false);
	gb.setConstraints(host,c);
	centerPanel.add(host);

	c.insets=new Insets(50,0,0,0);
	c.gridy=5;
	start.setName("start");
	start.addActionListener(this);
	gb.setConstraints(start,c);
	centerPanel.add(start);
			
	selectionPanel.add(centerPanel,BorderLayout.CENTER);

	return selectionPanel;
    }       
}
//----------------FOR MAP PANEL---------------------------------------//
/**
 *This class used to perform three functions related to map
 *addition,deletion and update the Map Objects in the database
 *panel has three Buttons to select the particular option
 *In map addition user has to give the properties like mapname label etc
 *click on the adddefaultmap defaultmap is added
 *click on the addcustommap user has to give custom properties then the custom map is added.
 *In deletemap user has to select the map from the list then click on the ok Button will delete selected map from the map database.
 *In updatemap user has to select the map from the list then click on the select Button show the properties of the selected map.
 *then user can do desired changes in the properties.
 *after making  changes  click on MODIFY Button to save the changes in database.
 */
class MapPanel extends JPanel implements ActionListener
{  
    JPanel cards = new JPanel(new CardLayout());
    String PanelSelection = "";
    //JTextArea remarks;
    public MapPanel()
    {   
	//textfield for remarks
	//remarks = new JTextArea(5,10);

	JPanel p1= new JPanel();
	FlowLayout fl =new FlowLayout();
	p1.setLayout(fl);
	JButton addbutton = new JButton("ADD MAP");
	addbutton.addActionListener(this);
        p1.add(addbutton,FlowLayout.LEFT);
	
       	JButton deletebutton = new JButton("DELETE MAP");
	deletebutton.addActionListener(this);
	p1.add(deletebutton);
	JButton updatebutton = new JButton("UP DATE MAP");
	updatebutton.addActionListener(this);
	p1.add(updatebutton);

	
	//remarks.setEditable(false);
	//JScrollPane notes = new JScrollPane(remarks);
	
	//cardlayout for empty panel
	BorderLayout bl = new BorderLayout();
	setLayout(bl);
	add(p1,bl.NORTH);
	//add(notes,BorderLayout.SOUTH);
	add(cards,BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent ae)
    {
	JPanel emptypanel = new JPanel();
	cards.add(emptypanel,"EMPTYPANEL");
	CardLayout card = (CardLayout)(cards.getLayout());
	card.show(cards,"EMPTYPANEL");
	
	PanelSelection = ae.getActionCommand();
	//remarks.setText(PanelSelection);
	if(PanelSelection.equals("ADD MAP"))
	{
	    AddMap am = new AddMap();
	    cards.add(am,"ADD MAP");
	    card = (CardLayout)(cards.getLayout());
	    card.show(cards,PanelSelection);
	}
	if(PanelSelection.equals("DELETE MAP"))
	{
	    DeleteMap dm = new DeleteMap();
	    cards.add(dm,"DELETE MAP");
	    card = (CardLayout)(cards.getLayout());
	    card.show(cards,PanelSelection);
	}
	if(PanelSelection.equals("UP DATE MAP"))
	{
	    UpdateMap um = new UpdateMap();
	    cards.add(um,"UP DATE MAP");
	    card = (CardLayout)(cards.getLayout());
	    card.show(cards,PanelSelection);
         }
    }
}
//--------------------------SYMBOL PANEL  ----------------------------------//
/**
 * 
 *This panel used for perform three functions related to map symbol
 *addition,deletion and update the Map Symbol in the database
 *This panel has three Buttons to select the particular option
 *In Symbol addition user has to give the mapname and symbol name,Object name,icon name and menuname etc.
 *user can add the symbol to the map by clicking OK Button.
 *clicking CANCEL Button will cancel the addition of symbols.
 *In Delete symbol
 *User has to give the name of the symbol and name of the map in which the symbol exists.
 *deleting  symbol in  the map is done  by clicking  on DeleteButton.
 *this change is saved in database by calling deleteSymbol() in MapAPI
 *In Update Symbol user has to give the symbol name and mapname in which the symbol to be update.
 *after entering the symbolname and mapname user has  to click on SELECT Button.
 *then user can change the property of the symbol shown in the panel.
 *to save the changes of the symbol properties in the database  click on the MODIFY Button
 */
class SymbolPanel extends JPanel implements ActionListener
{  
    JPanel cards = new JPanel(new CardLayout());
    
    String PanelSelection = "";
    // JTextArea remarks;
    public SymbolPanel()
    {   
	//textfield for remarks
	//remarks = new JTextArea(5,10);
	JPanel p1= new JPanel();
	FlowLayout fl =new FlowLayout();
	p1.setLayout(fl);
	JButton addbutton = new JButton("ADD SYMBOL");
	addbutton.addActionListener(this);
        p1.add(addbutton,FlowLayout.LEFT);
	
       	JButton deletebutton = new JButton("DELETE SYMBOL");
	deletebutton.addActionListener(this);
	p1.add(deletebutton);

	JButton updatebutton = new JButton("UP DATE SYMBOL");
	updatebutton.addActionListener(this);
	p1.add(updatebutton);
	//remarks.setEditable(false);

	//JScrollPane notes = new JScrollPane(remarks);
	//cardlayout for empty panel
	JPanel emptypanel = new JPanel();
	cards.add(emptypanel,"EMPTYPANEL");

	BorderLayout bl = new BorderLayout();
	setLayout(bl);
	add(p1,bl.NORTH);
	//add(notes,BorderLayout.SOUTH);
	add(cards,BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent ae)
    {
	JPanel emptypanel = new JPanel();
	cards.add(emptypanel,"EMPTYPANEL");
	CardLayout card = (CardLayout)(cards.getLayout());
	card.show(cards,"EMPTYPANEL");

	PanelSelection = ae.getActionCommand();
	//remarks.setText(PanelSelection);
	if(PanelSelection.equals("ADD SYMBOL"))
	{
	    AddSymbol as = new AddSymbol();
	    cards.add(as,"ADD SYMBOL");
	    card = (CardLayout)(cards.getLayout());
	    card.show(cards,PanelSelection);
	}
	if(PanelSelection.equals("DELETE SYMBOL"))
	{
	    DeleteSymbol ds = new DeleteSymbol();
	    cards.add(ds,"DELETE SYMBOL");
	    card = (CardLayout)(cards.getLayout());
	    card.show(cards,PanelSelection);
	}
	if(PanelSelection.equals("UP DATE SYMBOL"))
	{
	    UpdateSymbol us = new UpdateSymbol();
	    cards.add(us,"UP DATE SYMBOL");
	    card = (CardLayout)(cards.getLayout());
	    card.show(cards,PanelSelection);
         }
    }
}
//-------------------- FOR LINK PANEL -----------------------------//
/**
 ** 
 *This panel used for perform three functions related to map link.
 *addition,deletion and update the Map Link in the database
 *This panel has three Buttons to select the particular option
 *In Link addition user has to give the mapname and link name,Object name,icon name and menuname etc.
 *user can add the link to the map by clicking OK Button.
 *clicking CANCEL Button will cancel the addition of links.
 *In Delete Link user has to give the name of the link and name of the map in which the link exists.
 *deleting  link in  the map is done  by clicking  on DeleteButton.
 *this change is saved in database by calling deleteLink() in MapAPI
 *In Update Link user has to give the link name and mapname in which the link to be update.
 *after entering the linkname and mapname user has  to click on SELECT Button.
 *then user can change the property of the link shown in the panel.
 *to save the changes of the link properties in the database  click on the MODIFY Button. 
 */
class LinkPanel extends JPanel implements ActionListener
{
    JPanel cards = new JPanel(new CardLayout());
    String PanelSelection = "";
    
    public LinkPanel()
    {   
	
	JPanel p1= new JPanel();
	FlowLayout fl =new FlowLayout();
	p1.setLayout(fl);
	JButton addbutton = new JButton("ADD LINK");
	addbutton.addActionListener(this);
        p1.add(addbutton,FlowLayout.LEFT);
	
       	JButton deletebutton = new JButton("DELETE LINK");
	deletebutton.addActionListener(this);
	p1.add(deletebutton);

	JButton updatebutton = new JButton("UP DATE LINK");
	updatebutton.addActionListener(this);
	p1.add(updatebutton);
	
	//cardlayout for empty panel
	JPanel emptypanel = new JPanel();
	cards.add(emptypanel,"EMPTYPANEL");
	BorderLayout bl = new BorderLayout();
	setLayout(bl);
	add(p1,bl.NORTH);
	
	add(cards,BorderLayout.CENTER);
	//setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
	JPanel emptypanel = new JPanel();
	cards.add(emptypanel,"EMPTYPANEL");
	CardLayout card = (CardLayout)(cards.getLayout());
	card.show(cards,"EMPTYPANEL");
	
	PanelSelection = ae.getActionCommand();
	
	if(PanelSelection.equals("ADD LINK"))
	{
	    AddLink al = new AddLink();
	    cards.add(al,"ADD LINK");
	    card = (CardLayout)(cards.getLayout());
	    card.show(cards,PanelSelection);
	}
	if(PanelSelection.equals("DELETE LINK"))
	{
	    DeleteLink dl = new DeleteLink();
	    cards.add(dl,"DELETE LINK");
	    card = (CardLayout)(cards.getLayout());
	    card.show(cards,PanelSelection);
	}
	if(PanelSelection.equals("UP DATE LINK"))
	{
	    UpdateLink ul = new UpdateLink();
	    cards.add(ul,"UP DATE LINK");
	    card = (CardLayout)(cards.getLayout());
	    card.show(cards,PanelSelection);
	}
    }
}
//-------------------CONTAINER PANEL ---------------------------------//   
/**
 * *This panel used for perform three functions related to map Container.
 *addition,deletion and update the Map Container in the database
 *This panel has three Buttons to select the particular option
 *In Container addition user has to give the mapname and container name,Object name,icon name and menuname etc.
 *user can add the container to the map by clicking OK Button.
 *clicking CANCEL Button will cancel the addition of container.
 *In Delete Container user has to give the name of the cotainer and name of the map in which the container exists.
 *deleting  container in  the map is done  by clicking  on DeleteButton.
 *this change is saved in database by calling deleteContainer() in MapAPI
 *In Update Container user has to give the container name and mapname in which the container to be update.
 *after entering the containername and mapname user has  to click on SELECT Button.
 *then user can change the property of the container shown in the panel.
 *to save the changes of the container properties in the database  click on the MODIFY Button. 
 */
 class ContainerPanel extends JPanel implements ActionListener
{
    JPanel cards = new JPanel(new CardLayout());
    String PanelSelection = "";
    //JTextArea remarks;
    public ContainerPanel()
    {
	//remarks = new JTextArea(5,10);
	JPanel p1= new JPanel();
	FlowLayout fl =new FlowLayout();
	p1.setLayout(fl);
	
	JButton addbutton = new JButton("ADD CONTAINER");
	addbutton.addActionListener(this);
        p1.add(addbutton,FlowLayout.LEFT);
	
       	JButton deletebutton = new JButton("DELETE CONTAINER");
	deletebutton.addActionListener(this);
	p1.add(deletebutton);
	
	JButton updatebutton = new JButton("UP DATE CONTAINER");
	updatebutton.addActionListener(this);
	p1.add(updatebutton);
	//remarks.setEditable(false);
	//JScrollPane notes = new JScrollPane(remarks);
	
	//cardlayout for empty panel
	JPanel emptypanel = new JPanel();
	cards.add(emptypanel,"EMPTYPANEL");
	BorderLayout bl = new BorderLayout();
	setLayout(bl);

	add(p1,bl.NORTH);
	//add(notes,BorderLayout.SOUTH);
	add(cards,BorderLayout.CENTER);
	//setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        JPanel emptypanel = new JPanel();
	cards.add(emptypanel,"EMPTYPANEL");
	CardLayout card = (CardLayout)(cards.getLayout());
	card.show(cards,"EMPTYPANEL");
	
	PanelSelection = ae.getActionCommand();
	//remarks.setText(PanelSelection);//this will tell about ur process 
	if(PanelSelection.equals("ADD CONTAINER"))
	{
	    AddContainer ac = new AddContainer();
	    cards.add(ac,"ADD CONTAINER");
	    card = (CardLayout)(cards.getLayout());
	    card.show(cards,PanelSelection);
	}
	if(PanelSelection.equals("DELETE CONTAINER"))
	{
	    DeleteContainer dc = new DeleteContainer();
	    cards.add(dc,"DELETE CONTAINER");
	    card = (CardLayout)(cards.getLayout());
	    card.show(cards,PanelSelection);
	}
	if(PanelSelection.equals("UP DATE CONTAINER"))
	{
	    UpdateContainer uc = new UpdateContainer();
	    cards.add(uc,"UP DATE CONTAINER");
	    card = (CardLayout)(cards.getLayout());
	    card.show(cards,PanelSelection);
	}
    }
}
//---------------------------GROUP PANEL ----------------------------//
/**
 *This panel used for perform three functions related to map Group.
 *addition,deletion and update the Map Group in the database
 *This panel has three Buttons to select the particular option
 *In Container addition user has to give the mapname and group name,Object name,icon name and menuname etc.
 *user can add the group to the map by clicking OK Button.
 *clicking CANCEL Button will cancel the addition of group.
 *In Delete Group user has to give the name of the group and name of the map in which the group exists.
 *deleting  group in  the map is done  by clicking  on DeleteButton.
 *this change is saved in database by calling deleteGroup() in MapAPI
 *In Update Group user has to give the group name and mapname in which the group to be update.
 *after entering the groupname and mapname user has  to click on SELECT Button.
 *then user can change the property of the group shown in the panel.
 *to save the changes of the group properties in the database  click on the MODIFY Button. 
 */

class GroupPanel extends JPanel implements ActionListener
{
	JPanel cards = new JPanel(new CardLayout());
	String PanelSelection = "";
	//   JTextArea remarks;
	public GroupPanel()
	{
		//remarks = new JTextArea(5,10);
		JPanel p1= new JPanel();
		FlowLayout fl =new FlowLayout();
		p1.setLayout(fl);

		JButton addbutton = new JButton("ADD GROUP");
		addbutton.addActionListener(this);
		p1.add(addbutton,FlowLayout.LEFT);

		JButton deletebutton = new JButton("DELETE GROUP");
		deletebutton.addActionListener(this);
		p1.add(deletebutton);

		JButton updatebutton = new JButton("UP DATE GROUP");
		updatebutton.addActionListener(this);
		p1.add(updatebutton);
		//remarks.setEditable(false);
		//JScrollPane notes = new JScrollPane(remarks);

		//cardlayout for empty panel
		JPanel emptypanel = new JPanel();
		cards.add(emptypanel,"EMPTYPANEL");
		BorderLayout bl = new BorderLayout();
		setLayout(bl);

		add(p1,bl.NORTH);
		//add(notes,BorderLayout.SOUTH);
		add(cards,BorderLayout.CENTER);
		//setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{

		JPanel emptypanel = new JPanel();
		cards.add(emptypanel,"EMPTYPANEL");
		CardLayout card = (CardLayout)(cards.getLayout());
		card.show(cards,"EMPTYPANEL");

		PanelSelection = ae.getActionCommand();
		//remarks.setText(PanelSelection);//this will tell about ur process 
		if(PanelSelection.equals("ADD GROUP"))
		{
			AddGroup ag = new AddGroup();
			cards.add(ag,"ADD GROUP");
			card = (CardLayout)(cards.getLayout());
			card.show(cards,PanelSelection);
		}
		if(PanelSelection.equals("DELETE GROUP"))
		{
			DeleteGroup dg = new DeleteGroup();
			cards.add(dg,"DELETE GROUP");
			card = (CardLayout)(cards.getLayout());
			card.show(cards,PanelSelection);
		}
		if(PanelSelection.equals("UP DATE GROUP"))
		{
			UpdateGroup ug = new UpdateGroup();
			cards.add(ug,"UP DATE GROUP");
			card = (CardLayout)(cards.getLayout());
			card.show(cards,PanelSelection);
		}
	}
} 






























