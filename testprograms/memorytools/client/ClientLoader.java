package  com.adventnet.nms.clinet.examples;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.event.*;
import java.util.Vector;
import java.util.*;
import java.util.Properties;
import javax.swing.border.*;
import java.awt.*;

import com.adventnet.nms.startclient.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.util.NmsPanelEventListener;
import com.adventnet.nms.util.NmsPanelEvent;
import com.adventnet.nms.mapui.*;


public class ClientLoader extends AbstractBaseNmsPanel implements InternalFrameListener,Runnable
{
	private Thread t=null;
	private final int sleeper=10000;
	private int firsttimeflag=0;
	private String CVID="";
	private String treename="";
	private String cvname="";
	private int mapoperation=4;
	private int cvoperations=2;
	private int navigations=6;

	public ClientLoader()
	{
		cvname="NewCV"+System.currentTimeMillis();
		treename="NewTreeNode"+System.currentTimeMillis();
	}

	private void initLayout()
	{
		String message = "This example simulates an Active Client";
		System.out.println("Invoked22222222 initLayout"+new Date());
		setLayout(new BorderLayout());
		JPanel pane1 = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		JTextArea textarea = new JTextArea(20,30);
		textarea.setFont(new Font("SansSerif",Font.BOLD,12));
		textarea.setText(message);
		JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel msg = new JLabel("Clinet Activator");
		top.setBorder(new EtchedBorder());
		top.add(msg);
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		pane1.setBorder(new EtchedBorder());
		gbc.gridx=0; gbc.gridy=0;
		pane1.add(new JScrollPane(textarea),gbc);
		add(pane1,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);

	}
	public void internalFrameActivated(InternalFrameEvent e) 
	{
		System.out.println("Invoked11111111 internalFrameActivated"+new Date());

		JInternalFrame jif = (JInternalFrame) e.getSource();
		jif.setTitle("Client Loader ");
		System.out.println("Method is : getCurrentCustomView()----->"+NmsUiAPI.getCurrentCustomView());
		System.out.println("Method is : getCurrentModuleId()----->"+NmsUiAPI.getCurrentModuleId());
		System.out.println("Method is : getCurrentNodeId() ----->"+NmsUiAPI.getCurrentNodeId());
		t=new Thread(this);
		t.start();
	}
	public void run()
	{
		try{
			String[] modules=NmsUiAPI.getModuleIds();
			System.out.println("Method is : getModuleIds() ----->"+Arrays.asList(modules));
			Thread.sleep(sleeper);
			MapModelAPI mmapi=MapModelAPI.getInstance();
			System.out.println("Maps Are "+mmapi);
			Vector mapnames=mmapi.getMapNames();
				int mapoperationsdone=0;
			System.out.println("Size of the Map:::"+mapnames.size());
			for(int i=0;i<mapnames.size() && mapoperationsdone<=mapoperation;i++)
			{
				mapoperationsdone++;
				String mapname=(String)mapnames.elementAt(i);
				System.out.println("MapName Is --->"+mapname);			
				NmsUiAPI.selectTreeNode(mapname,true);

				DefaultMapModel dmm=mmapi.getMapModel(mapname,true);
				System.out.println("MAP DETAILS ##NAME##--->"+dmm.getParentName());
				System.out.println("MAP DETAILS ##Properties##--->"+dmm.getProperties());
				System.out.println("MAP DETAILS ##Criteria Properties##--->"+dmm.getMapCriteriaProperties());
				String newmapname="NewMap"+System.currentTimeMillis()+".netmap";
				String parentmapname=mapname;
				Properties newmapprop=dmm.getProperties();
				newmapprop.put("name",newmapname);
				newmapprop.put("label","LABEL_"+newmapname);
				Properties criteriaprop=dmm.getMapCriteriaProperties();

				System.out.println("ADD MAP ---->"+mmapi.addNewMap(newmapname,parentmapname,1,newmapprop,criteriaprop,true));

				mmapi.saveMap(newmapname);
				NmsUiAPI.selectTreeNode(newmapname,true);
				System.out.println("NEW MAP SELECTED");
				Thread.sleep(sleeper);
				mmapi.deleteMap(newmapname);
				System.out.println("MAP DELETED");
				Thread.sleep(sleeper);

				dmm=mmapi.reLoadMap(mapname,true);
				System.out.println("PARENT MAP RELOADED");
				Thread.sleep(sleeper);
				NmsUiAPI.selectTreeNode(mapname,true);
				System.out.println("PARENT MAP SELECTED");

				Vector symbolsvec=dmm.getSymbols();
				String linkname="testlink"+System.currentTimeMillis();
				if(symbolsvec.size()>2)
				{
					MapLinkComponent mlc=new MapLinkComponent();
					mlc.setDest(((MapSymbolComponent)symbolsvec.elementAt(0)).getName());
					mlc.setSource(((MapSymbolComponent)symbolsvec.elementAt(symbolsvec.size()-1)).getName());
					mlc.setName(linkname);
					mlc.setMapName(mapname);
					System.out.println("LINK TO BE ADDED "+dmm.addLink(mlc,true));
					Thread.sleep(sleeper);
				}
			}


			Properties[] proper=new Properties[0];
                        EventsHandling eventobj = new  EventsHandling();
                   //   eventobj.setProperties(proper);

	 System.out.println("TreeLabel for Events going to Change New");
         eventobj.ModifyTreeLabel();
	 Thread.sleep(sleeper);
         System.out.println("TreeLabel Succesfully Changed");
         System.out.println("Events Icon Going to Change");
         eventobj.ModifyTreeIcon();
	 Thread.sleep(sleeper);
         System.out.println("EventsIcon Succesfully Changed");
         System.out.println("NewNode ToBe Added in NetWorkNodes");
         eventobj.AddLeafToNode();
	 Thread.sleep(sleeper);
         System.out.println("NewNode Succesfully added in NetWorkNodes");
         System.out.println("NewNode going to delete from NetWorkNodes");
         eventobj.RemoveLeaffromNode();
	 Thread.sleep(sleeper);
//         System.out.println("New Label is Again change into Events");
//         eventobj.AgainModifyTreeLabel();
//         Thread.sleep(sleeper);
         System.out.println("Events Successfully Modified");
         System.out.println("Panel Event Display will be changing now");
         eventobj.ChangePanelEvent();
	 Thread.sleep(sleeper);
         System.out.println("Display Change is done");
         System.out.println("TreeSelection Change Testcase");
         eventobj.ChangeTreeSelection();
	 Thread.sleep(sleeper);
         System.out.println("Changing of Tree Selection Done");
         System.out.println("Changing TreeNode By Index Option");
         eventobj.MoveNodeToNewIndex();
	 Thread.sleep(sleeper);
         System.out.println("Tree Change Done");
         System.out.println("Events Move into NetWorkDB");
         eventobj.MoveTreeNode();
 	 Thread.sleep(sleeper);
         System.out.println("Successfully Moved");
         System.out.println("PanelEvent Closing TestCase");
         eventobj.ClosePanelEvent();
	 Thread.sleep(sleeper);
         System.out.println("Panel Closed Successfully");
         System.out.println("Events Prop. is Change Now");
         eventobj.ModifyTreeNodeProperties();
	 Thread.sleep(sleeper);
         System.out.println("Property Change is Completed");



			for(int j=0;j<modules.length && j<=navigations;j++)
			{
				System.out.println("Module to be selected ---->"+modules[j]);
				if(!modules[j].equals("CLIENT_LOAD"))
				{
					System.out.println("SELECTED ---->"+modules[j]);
					NmsUiAPI.selectTreeNode(modules[j],true);
					System.out.println("NODE ID IS----->"+NmsUiAPI.getCurrentNodeId());
					Thread.sleep(sleeper);
					System.out.println("NODE ID IS----->"+NmsUiAPI.getCurrentNodeId());
				}
			}
			CVID=addNDCV();
			Thread.sleep(sleeper);
			NmsUiAPI.selectTreeNode("Network Database.Networks",true);
			Thread.sleep(sleeper); 
			NmsUiAPI.selectTreeNode(CVID,true);
			Thread.sleep(sleeper);
			boolean nodeadded=addTN();
			Thread.sleep(sleeper);
			NmsUiAPI.selectTreeNode(treename,true);
			System.out.println("NODE ID IS----->"+NmsUiAPI.getCurrentNodeId());
			Thread.sleep(sleeper);
			System.out.println("NODE ID IS----->"+NmsUiAPI.getCurrentNodeId());
			NmsUiAPI.selectTreeNode(CVID,true);
			for(int uu=0;uu<cvoperations;uu++)
			{
				modifyNDCV_1(CVID);
				Thread.sleep(sleeper);
				modifyNDCV_2(CVID);
				Thread.sleep(sleeper);
			}                     
			System.out.println("CVSD:JDJD--->"+CVID);
			if(CVID!=null)
			{
				Thread.sleep(sleeper);
				delNDCV(CVID);
			}
			Thread.sleep(sleeper);
			delTN();
//saran
//			Properties[] proper=new Properties[0];
//			EventsHandling eventobj = new  EventsHandling();
//			eventobj.setProperties(proper);			 		
//saran


			int i=0 ;
			while(i<3)
			{
				i++;
				System.out.println("control in thread---->"+i);
				Thread.sleep(sleeper);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean addTN()
	{
		String parentID="Network Database";
		String select=treename;
		Properties prop =new Properties();
		prop.put("ID",treename);
		prop.put("TREE-NAME",treename);
		prop.put("PANEL-NAME","com.adventnet.nms.clinet.examples.SampleNmsPanel");
		prop.put( "ICON-FILE","images/usertester.png");
		prop.put("IMAGE-NAME","images/usertester.png");
		prop.put("TREE-POPUP-MENU","TreeOperations.xml");
		System.out.println("Adding Tree Node ");
		return NmsUiAPI.addTreeNode(parentID,select,prop,true);

	}

	public String addNDCV()
	{
		String moduleId="Network Database";
		String parentNodeId="Network Database";
		Properties criteria=new Properties();
		criteria.put("FieldsWanted","name=name;classname=classname");
		criteria.put("isNode","true");
		Properties treeNodeProps=new Properties();
		treeNodeProps.put("TREE-NAME",cvname);
		boolean sync=true;
		return NmsUiAPI.addCustomView(moduleId, parentNodeId, criteria, treeNodeProps, sync);

	}


	public void modifyNDCV_2(String CustomViewId)
	{	
		Properties criteria=new Properties();
		criteria.put("FieldsWanted","name=name;classname=classname;isNode=isNode");
		criteria.put("isNode","true");
		Properties treeNodeProps=new Properties();
		treeNodeProps.put("TREE-NAME",cvname);
		NmsUiAPI.modifyCustomView( "Network Database", CustomViewId, criteria,  treeNodeProps, true); 
	}


	public void modifyNDCV_1(String CustomViewId)
	{	
		Properties criteria=new Properties();
		criteria.put("FieldsWanted","classname=classname;isNode=isNode");
		criteria.put("isNode","false");
		Properties treeNodeProps=new Properties();
		treeNodeProps.put("TREE-NAME",cvname);
		NmsUiAPI.modifyCustomView( "Network Database", CustomViewId, criteria,  treeNodeProps, true);
	}


	public void delNDCV(String CVID)
	{
		System.out.println("DELETING CVID --->"+NmsUiAPI.removeCustomView("Network Database",CVID,true));
	}
	public void delTN()
	{
		System.out.println("DELETING TNID --->"+NmsUiAPI.removeTreeNode(treename,"Alerts",true));
	}


	/** init method with Applet parameter */
	public void init(JApplet app)
	{
		System.out.println("Invoked11111111 init"+new Date());
		initLayout();
	}
	public void init(Properties p)
	{
		System.out.println("Invoked11111111 init2"+new Date());
		super.init(p);
	}

	public String key()
	{ 
		System.out.println("Invoked11111111 key"+new Date());
		return "CLIENT_LOAD"; 
	}

	public void setProperties(Properties prop)
	{
		try{
			if(firsttimeflag==1)
			{
				delNDCV(CVID);
				Thread.sleep(sleeper);
				delTN();
				Thread.sleep(sleeper);
				System.exit(0);
			}
			else
				firsttimeflag=1;
			System.out.println("Invoked11111111 setProperties"+new Date());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	//Below methods of the interface NmsPanel are not implemented here.
	//As they are of no significance to us.
	public void start()
	{
		System.out.println("Invoked11111111 start"+new Date());

	}

	public void stop()
	{
		System.out.println("Invoked11111111 stop"+new Date());
	}

	public void destroy()
	{
		System.out.println("Invoked11111111 destroy"+new Date());
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Invoked11111111 actionPerformed"+new Date());
	}
	public void internalFrameClosed(InternalFrameEvent e) 
	{
		System.out.println("Invoked11111111 internalFrameClosed"+new Date());
	}
	public void internalFrameClosing(InternalFrameEvent e) 
	{
		System.out.println("Invoked11111111 internalFrameClosing"+new Date());
	}
	public void internalFrameDeactivated(InternalFrameEvent e) 
	{
		System.out.println("Invoked11111111 internalFrameDeactivated"+new Date());
	}
	public void internalFrameDeiconified(InternalFrameEvent e) 
	{
		System.out.println("Invoked11111111 internalFrameDeiconified"+new Date());
	}
	public void internalFrameIconified(InternalFrameEvent e) 
	{
		System.out.println("Invoked11111111 internalFrameIconified"+new Date());
	}
	public void internalFrameOpened(InternalFrameEvent e) 
	{
		System.out.println("Invoked11111111 InternalFrameEvent"+new Date());
	}

}//End of class ClientLoader
