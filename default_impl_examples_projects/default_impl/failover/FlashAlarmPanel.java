//$Id: FlashAlarmPanel.java,v 1.2 2008/09/25 13:42:26 tinku Exp $
package com.adventnet.nms.alertui;

//java imports
import java.util.Properties;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JApplet;
import java.awt.event.ActionEvent;


//Nms imports
import com.adventnet.nms.alertdb.AlertAPI;
import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.alertdb.DAlert;
import com.adventnet.nms.alertdb.AlertActionInformer;
import com.adventnet.nms.alertdb.AlertListener;
import com.adventnet.nms.util.NmsUiAPI;
import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.util.ListView;
import com.adventnet.nms.util.ListViewHandler;
import com.adventnet.nms.util.ListViewModel;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.GUIElement;
import com.adventnet.nms.util.PropForm;
import com.adventnet.nms.util.FormView;
import com.adventnet.nms.util.PropComponent;
import com.adventnet.nms.startclient.AbstractBaseNmsPanel;

/**
 * This AbstractBaseNmsPanel will display the Alerts that are received during the 
 * FailOver period.This NmsPanel will be added into the tree dynamically under Alarms view 
 * whenever BE failover is happen.In preFailOverNotification this panel will be initiated 
 * And it will lookup the AlertAPI from RMI registry and register itself as a AlertListener. 
 * So whenever Alert is added or updated it will be notified. And it will update the failover 
 * Alarms view. After failover, this failover Alarms view will disappear.
 * 
 * In all the cases this Panel will lookup the AlertAPI from standby server 
 * RMI registry even though the client is connected to standalone FE Server.
 * 
 */

public class FlashAlarmPanel extends AbstractBaseNmsPanel implements AlertListener,ListViewModel,ListViewHandler
{
	private JApplet applet = null; //Nms main applet
	private Vector inRowColors = new Vector();
	private Vector alertVector = new Vector();
	private Hashtable alerts = new Hashtable();
	private ListView listPanel = null;  //show the alerts in view
	private AlertAPI api = null;  //AlertAPI handle
	private String viewName = "FailOverAlarmsView";

	public void init(JApplet applet)
	{
		this.applet = applet;
	}

	public String key()
	{
		return viewName;
	}

	public void setProperties(Properties prop) {}

	//Called by FlashAlarmBrowser after getting pre failover notification 
	//for construct the ListView and receive Alert update.
	void initialize(Properties prop)
	{
		String hostName = prop.getProperty("HOSTNAME");
		int rmiPort = Integer.parseInt(prop.getProperty("RMI_REG_PORT"));
		constructListView();
		try
		{
			UnicastRemoteObject.exportObject(this,0);
			api = (AlertAPI)Naming.lookup("rmi://"+hostName+":"+rmiPort+"/AlertAPI");
			api.addAlertListener(this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	//Called by FlashAlarmBrowser after getting post failover notification 
	//for release view resources.
	void releaseResources()
	{
		try
		{
			inRowColors = new Vector();
			alerts = new Hashtable();
			alertVector = new Vector();
			listPanel.updateTable(alertVector, null, inRowColors); //Update the view

			api.removeAlertListener(this);
			UnicastRemoteObject.unexportObject(this, true);
			//This remove call should be asynchronous.Then only the client cache the request
			//and send it to the server once the communication is ready.
			//Remove the TreeNode in seperate thread instead of in main thread.
			Thread t = new Thread() {
				public void run()
				{
					try
					{
						Thread.sleep(3000); //wait for three seconds.
						boolean flag = NmsUiAPI.removeTreeNode(viewName, "Alerts", false);
						if(flag)
							System.out.println("FailOverAlarms view successfully removed from tree");
					}
					catch(Exception e) {}
				}};
			t.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Implementation for AlertListener
	public void update(XMLNode node)
	{
		//No implementation for Bulk update.
	}
	public void listenAlert(AlertActionInformer action)
	{
		if(action.isBatchUpdate())
		{
			Vector alertList = action.getAlertList();
			for(int i=0; i<alertList.size(); i++)
			{
				handleAndShowAlert((Alert)alertList.elementAt(i));
			}
		}
		else 
			handleAndShowAlert(action.getAlert());

		listPanel.updateTable(alertVector, null, inRowColors); //Update the view
	}

	private void handleAndShowAlert(Alert alert)
	{
		//Create the DAlert which will take care of update the view.
		Properties prop = alert.getProperties();
		DAlert dAlert = new DAlert(prop);
		String entity = alert.getEntity();
		if(alerts.get(entity) == null && alert.getSeverity() != -1)
		{
			//Add the alert in the view
			alerts.put(entity,prop); 
			addAlert(dAlert);
		}
		else if(alerts.get(entity) != null)
		{
			if(alert.getSeverity() == -1)
			{
				//Delete the Alert from the view
				deleteAlert(entity);
			}
			else
			{
				//Update the Alert present in the view
				updateAlert(dAlert, entity);
				alerts.put(entity,prop); 
			}
		}
	}

	private void addAlert(DAlert dAlert)
	{
		inRowColors.insertElementAt(dAlert.getColor(), 0);
		alertVector.insertElementAt(dAlert, 0);
	}

	private void updateAlert(DAlert dAlert, String entity)
	{
		//Update means delete and Alert.
		deleteAlert(entity);
		addAlert(dAlert);
	}


	private void deleteAlert(String entity)
	{
		//Find the position of the alert that need to be removed from view
		int size = alertVector.size();
		int position = -1;
		for(int i=0; i<size; i++)
		{
			DAlert tempAlert = (DAlert)alertVector.elementAt(i);
			if(tempAlert.getProperty("entity").equals(entity))
			{
				position = i;
				break;
			}
		}
		inRowColors.removeElementAt(position);
		alertVector.removeElementAt(position);
		Properties prop = (Properties)alerts.remove(entity);
	}

	//Construct the ListView
	private void constructListView() 
	{
		//Get the properties from already existing Alarms view and create a view
		//similar to Alarms view
		Properties prop = NmsUiAPI.getTreeNodeProperties("Alerts");
		String tableColumns = prop.getProperty("TABLE-COLUMNS");
		if(tableColumns == null) return;
		String[] inColumnNames = null;
		Vector columnLabels = new Vector(5,5);
		Vector columnFieldsVector = new Vector(5,5);     
		Vector columnWidthsVector = new Vector(5,5);  
		StringTokenizer tok = new StringTokenizer(tableColumns,";");
		while (tok.hasMoreTokens())
		{
			String temp = tok.nextToken();
			temp = temp.trim();
			int indx = -1;
			if ((indx = temp.indexOf('=')) != -1)
			{
				String columnLabel = temp.substring(0,indx).trim();       
				int fieldIndex = indx + 1;   
				indx = temp.indexOf('=', indx + 1);  
				if (indx != -1)     
				{
					String columnField = temp.substring(fieldIndex, indx).trim();
					columnLabels.addElement(columnLabel);
					columnFieldsVector.addElement(columnField);    
					int columnWidth = Integer.parseInt(temp.substring(indx + 1).trim()); 
					columnWidthsVector.addElement(new Integer(columnWidth));
				}           
				else
				{
					columnLabels.addElement(columnLabel);
					String columnField = temp.substring(fieldIndex).trim();
					columnFieldsVector.addElement(columnField);
				}
			}
		}
		inColumnNames = new String[columnLabels.size()];
		int columns = columnLabels.size();
		for (int i = 0; i < columns; i++ )
		{
			inColumnNames[i] = (String)columnLabels.elementAt(i);
		}
		listPanel = new ListView(inColumnNames, columnFieldsVector, columnWidthsVector, alertVector, this);
		listPanel.setSelectionBGColor((String)getCurrentNodeProperties().get("TABLE-SELECTION-BGCOLOR"));
		listPanel.setDrawColumnSeparatorLines(false);
		//Set the ListViewModel
		listPanel.setListViewModel(this);   
		listPanel.showColor(inRowColors);
		add("Center", listPanel);
		addNodeIntoTree(prop);
	}

	//Add the node into tree dynamically using NmsUiAPI with the properties as 
	//Alarms view properties
	private void addNodeIntoTree(Properties prop)
	{
		Properties treeProp[] = new Properties[1];
		treeProp[0] = new Properties();
		treeProp[0].setProperty("TREE-NAME", viewName);
		treeProp[0].setProperty("ICON-FILE", prop.getProperty("ICON-FILE"));
		treeProp[0].setProperty("PANEL-KEY", viewName);
		treeProp[0].setProperty("PANEL-NAME", this.getClass().getName());
		treeProp[0].setProperty("ID", viewName);
		treeProp[0].setProperty("PARENT", "Alerts");
		treeProp[0].setProperty("MODULE-NAME", "Alerts");
		treeProp[0].setProperty("MENU-FILE-NAME", prop.getProperty("MENU-FILE-NAME"));
		//This Add call should be asynchronous.Then only the client cache the request
		//and send it to the Server once the communication is ready.
		boolean flag = NmsUiAPI.addNodesToTree(treeProp, "Alerts", viewName, false);
		if(flag)
		{
			System.out.println("FailOverAlarms view successfully added into tree");
		}
	}

	//implementation for ListViewModel
	public String getProperty(Object cellobject,String stringvar)
	{
		return ((DAlert)cellobject).getProperty(stringvar);
	}
	public Integer getColumnType(Object cellobject,String stringvar)
	{
		return ((DAlert)cellobject).getColumnType(stringvar);
	}

	//Implementation for ListViewHandler.Only double click have the implementation

	public void handleDoubleClick(int rowId)
	{
		showAlertDetails(rowId);
	}
	public void handleRowSelection(int rowId) {}
	public void handleRowDeselection(int rowId) {}
	public void handleMultipleSelection(boolean[] selectionStatus) {}
	public boolean handleSorting(String sortInfo[]) { return false;}
	public void handleRightClick(int rowId,java.awt.Component comp,int x,int y) {}
	public Object[]  handleColumnResizing( Object[] columnWidthsVector) { return null;}

	//Handle the menu actions.
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		if(!command.equals("Details"))
		{
			NmsClientUtil.showError(NmsClientUtil.getFrame(this) ,NmsClientUtil.GetString("This operation not supported during FailOver period"));
			return;
		}
		int[] intarr = listPanel.getSelectedRows();
		if (intarr == null || intarr.length == 0)
		{
			NmsClientUtil.showError (NmsClientUtil.getFrame(this),NmsClientUtil.GetString("No alarm selected"));
			return;
		}
		showAlertDetails(intarr[0]);
		//Get the Alert Properties from alerts Hashtable and shown in UI
	}

	private void showAlertDetails(int pos)
	{
		DAlert dAlert = (DAlert)alertVector.elementAt(pos);
		Properties prop = (Properties)alerts.get(dAlert.getProperty("entity"));
		prop.remove("stringpreviousseverity");
		FormView fv = new FormView (new ListAlarmProp(prop).getPropForm(), NmsClientUtil.getFrame(this));
		NmsClientUtil.centerWindow(fv);
		fv.setVisible (true);
	}

	//Construct a ListAlarmProp to show the alarm details
	class ListAlarmProp implements PropComponent
	{
		Properties prop = null;
		ListAlarmProp(Properties prop) 
		{
			this.prop = prop;
		}
		public PropForm getPropForm()
		{
			GUIElement props[]=new GUIElement[prop.size()*2];
			int i =0;
			for (Enumeration en=prop.keys(); en.hasMoreElements();)
			{
				String key = (String)en.nextElement ();
				String value = (String)prop.get(key);
				String temp =key.substring(0,1);
				key = temp.toUpperCase()+key.substring(1,key.length());
				props[i] = new GUIElement("label", NmsClientUtil.GetString (key));
				props[i + 1] = new GUIElement(key, "textfield", value, false,"false");
				i += 2;
			}
			PropForm form = new PropForm (NmsClientUtil.GetString ("Alarm"), props, this);
			form.okLabel = NmsClientUtil.GetString ("OK");
			form.helptext = NmsClientUtil.GetString ("Alarm Details");
			form.layout = "GRIDBAG";
			form.applet = applet;
			return form;
		}

		public void setValues() {}

		public Properties getProperties()
		{
			return null;
		}
		public boolean PropAction(java.awt.Event e,Object arg)
		{
			return true;
		}

		public void setProperties (Properties p) {}
	}
}



