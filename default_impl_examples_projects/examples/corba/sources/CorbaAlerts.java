/*
 * Copyright AdventNet, Inc., 1999
 * File : CorbaAlerts.java
 * @ author Devesh Gupta
 *
 * This class demonstrates the use of CORBA APIs
 * Here the AlertAPi is accessed through CORBA
 * and AlertObserver registers with it to get
 * notifications of Alert.
 */

//CORBA imports
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

//java imports
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Component;

//WebNMS imports
import com.adventnet.util.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.common.corba.*;
import com.adventnet.nms.alertdb.corba.*;

/** This is a view of Web NMS Alerts accessed via CORBA **/
public class CorbaAlerts implements TableModel
{
    public static void main(String args[])
    {

        try { //setup the look and feel for native platform L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Couldn't use the system "
			       + "look and feel: " + e);
        }

	String url = "file:" + new java.io.File("../../../conf/SeverityInfo.conf").getAbsolutePath();
	SeverityInfo.getInstance(url);
	
	CorbaAlerts corbaAlerts = new CorbaAlerts(args);

	JFrame frame = new JFrame(); 

	// Instantiate the JFC table with the SnmpTableModel instance as model
	JTable table = new JTable(corbaAlerts);

	table.sizeColumnsToFit(-1);  
	
	JScrollPane scrollpane = new JScrollPane(table);
	scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	scrollpane.setViewportView(table);
	frame.getContentPane().add(scrollpane);
	
	WindowListener l = new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		System.exit(0);
	    }
	};
	frame.addWindowListener(l);
	corbaAlerts.theTable = table;

	frame.setTitle("AdventNet Web NMS CORBA Client");
	frame.setSize(700,300);
	frame.setVisible(true);

    }

    /** This constructor sets up the CORBA connection **/
    public CorbaAlerts(String args[]) {
        Property_CT[] prop = null;
        NVProperties NVP = null;
        
        //initialize the orb
        ORB orb = ORB.init(args,null);
        
        NameComponent nc1 = new NameComponent("com","com");
        NameComponent nc2 = new NameComponent("adventnet","adventnet");
        NameComponent nc3 = new NameComponent("nms","nms");
        NameComponent[] NC = {nc1,nc2,nc3};
        
        try {
            NamingContext nmsContext = NamingContextHelper.narrow
		((NamingContextHelper.narrow(orb.resolve_initial_references
					     ("NameService"))).resolve(NC));
            NameComponent ncomp = new NameComponent("AlertAPI","");
            NameComponent[] nca = { ncomp };
            AlertAPI_CI alert_api = AlertAPI_CIHelper.narrow(nmsContext.resolve(nca));
            String[] names_arr = alert_api.getCompleteList_CT();
            for (int i=0; i< names_arr.length; i++)
            {            	
                prop = alert_api.getPropertiesOfObject_CT(names_arr[i]);
                NVP = new NVProperties(prop);
		Alert alert = new Alert();
                alert.setId(i+1);
                alert.setSource(NVP.getProperty("source"));
                alert.setEntity(NVP.getProperty("entity"));                
                alert.setSeverity(getIntSeverity(NVP.getProperty("stringseverity")));
                alert.setPreviousSeverity(getIntSeverity(NVP.getProperty("stringpreviousseverity")));
                alert.setCategory(NVP.getProperty("category"));
                alert.setCreateTime((new Date(Long.parseLong(NVP.getProperty("createTime")))).getTime());
                alert.setModTime((new Date(Long.parseLong(NVP.getProperty("modTime")))).getTime());
                alert.setMessage(NVP.getProperty("message"));

		alerts.addElement(alert);
            }                                                                                                                                                  
        }
        catch(Exception e)
        {
            System.err.println ();
            e.printStackTrace();
        }

    }

    /** Get the integer severity **/
    int getIntSeverity(String s) {
	
	if (s == null)
		return SeverityInfo.getInstance().getSpecialPurposeSeverity();

	return SeverityInfo.getInstance().getValue(s);
	/*
	if (s==null) return 0;
	if (s.equals("Critical")) return 1;
	if (s.equals("Major")) return 2;
	if (s.equals("Minor")) return 3;
	if (s.equals("Warning")) return 4;
	if (s.equals("Clear")) return 5;
	if (s.equals("Info")) return 6;
	return 0;
	*/
    }

    /** The following methods implement TableModel Methods needed by JTable **/

    public int getRowCount() { return alerts.size();  }
    public int getColumnCount() { return 5; }
    public String getColumnName(int columnIndex) { 
	if (columnIndex == 0) return "Severity";
	if (columnIndex == 1) return "Date";
	if (columnIndex == 2) return "Source";
	if (columnIndex == 3) return "Entity";
	if (columnIndex == 4) return "Description";
	return "Description";
    }
    public Class getColumnClass(int columnIndex) {
	return String.class;
    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	return false;
    }
    public java.lang.Object getValueAt(int rowIndex, int columnIndex) {
	if (rowIndex >= alerts.size()) return null;
	Alert alert = (Alert)alerts.elementAt(rowIndex);
	if (columnIndex == 0) { 
	    setCellColor(rowIndex, 0, alert.getColor());
	    return alert.severityStr(); 
	}
	if (columnIndex == 1) { 
	    setCellColor(rowIndex, 1, alert.getColor());
	    return (new Date(alert.getModTime())).toString();
	}
	if (columnIndex == 2) { 
	    setCellColor(rowIndex, 2, alert.getColor());
	    return alert.getSource();
	}
	if (columnIndex == 3) { 
	    setCellColor(rowIndex, 3, alert.getColor());
	    return alert.getEntity();
	}
	if (columnIndex == 4) { 
	    setCellColor(rowIndex, 4, alert.getColor());
	    return alert.getMessage();
	}
	return null;
    }

    public void setValueAt(java.lang.Object aValue, int rowIndex,
			   int columnIndex) {}

    public void addTableModelListener(TableModelListener l) {}
    public void removeTableModelListener(TableModelListener l) {}


    /** Sets the background color of individual cell as specified **/
    void setCellColor(int row, int col, Color color) {
	if (theTable == null) return;
	TableCellRenderer renderer = theTable.getCellRenderer(row,col);
	if (renderer instanceof Component) 
	    ((Component)renderer).setBackground(color);
    }


    /** A JTable reference for setting colors **/
    JTable theTable = null;
    /** The list of alerts **/
    Vector alerts = new Vector();
    
	/** Holds reference  of SeverityInfo, to get severity values
	* from XML file*/
}

