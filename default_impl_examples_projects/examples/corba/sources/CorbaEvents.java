/*
 * Copyright AdventNet, Inc., 1999
 *
 * File : CorbaEvents
 *
 * This class demonstrates the usage of CORBA API.
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

// WebNMS imports
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.common.corba.*;
import com.adventnet.nms.eventdb.corba.*;
import com.adventnet.util.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;

public class CorbaEvents  implements TableModel {
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

	CorbaEvents corbaEvents = new CorbaEvents(args);

	JFrame frame = new JFrame(); 

	// Instantiate the JFC table with the SnmpTableModel instance as model
	JTable table = new JTable(corbaEvents);

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
	corbaEvents.theTable = table;

	frame.setTitle("AdventNet Web NMS CORBA Client");
	frame.setSize(700,300);
	frame.setVisible(true);

    }

    /** This constructor sets up the CORBA connection **/
    public CorbaEvents(String args[]) {
	ORB orb = null;
        Property_CT[] prop = null;
        NVProperties NVP = null;
            
	try
	    {
		orb = orb.init(args,null);

		NameComponent nc  = new NameComponent("com","com");
		NameComponent nc1 = new NameComponent("adventnet","adventnet");
		NameComponent nc2 = new NameComponent("nms","nms");
		
		NameComponent[] nca = {nc,nc1,nc2};
		
		NamingContext nmsContext = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));
		
		NamingContext NC = NamingContextHelper.narrow(nmsContext.resolve(nca));

		NameComponent ncomp = new NameComponent("EventAPI","");
		NameComponent[] NCA = {ncomp};
		
		EventAPI_CI eventapi = EventAPI_CIHelper.narrow(NC.resolve(NCA));
		String[] names_arr = eventapi.getCompleteList_CT();

		for (int i=0; i< names_arr.length; i++) {            	
		    prop = eventapi.getPropertiesOfObject_CT(names_arr[i]);
		    NVP = new NVProperties(prop);

		    Event event = new Event();
		    event.setId(i+1);
		    event.setSource(NVP.getProperty("source"));
		    event.setNode(NVP.getProperty("node"));
		    event.setEntity(NVP.getProperty("entity"));                
		    event.setSeverity(getIntSeverity(NVP.getProperty("stringseverity")));
		    event.setCategory(NVP.getProperty("category"));
		    event.setTime((new Date(Long.parseLong(NVP.getProperty("time")))).getTime());
		    event.setText(NVP.getProperty("text"));

		    events.addElement(event);
		}

	    } catch(Exception e) {
		System.out.println(e);
	    }
    }

    /** Get the integer severity **/
    Color getColor(Event e) {
	int i = e.getSeverity();
	/*
	if (i==1) return Color.red;
	if (i==2) return Color.orange;
	if (i==3) return Color.yellow;
	if (i==4) return Color.cyan;
	if (i==5) return Color.green;
	return Color.white;
	*/
	Color color = SeverityInfo.getInstance().getColor(i);
	if (color == null)
		color = Color.white;
	return color;	
    }

    /** Get the integer severity **/
    String severityStr(Event e) {
	int i = e.getSeverity();
	/*
	if (i==1) return "Critical";
	if (i==2) return "Major";
	if (i==3) return "Minor";
	if (i==4) return "Warning";
	if (i==5) return "Clear";
	return "Info";
	*/
	SeverityInfo sevInfo = SeverityInfo.getInstance();
	String sevName = sevInfo.getName(i);
	if (sevName == null)
		sevName = sevInfo.getName(sevInfo.getInfo());
	return sevName;	
    }

    /** Get the integer severity **/
    int getIntSeverity(String s) {
	if (s==null)
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

    public int getRowCount() { return events.size();  }
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
	if (rowIndex >= events.size()) return null;
	Event event = (Event)events.elementAt(rowIndex);
	if (columnIndex == 0) { 
	    setCellColor(rowIndex, 0, getColor(event));
	    return severityStr(event); 
	}
	if (columnIndex == 1) { 
	    setCellColor(rowIndex, 1, Color.white);
	    return (new Date(event.getTime())).toString();
	}
	if (columnIndex == 2) { 
	    setCellColor(rowIndex, 2, Color.white);
	    return event.getSource();
	}
	if (columnIndex == 3) { 
	    setCellColor(rowIndex, 3, Color.white);
	    return event.getEntity();
	}
	if (columnIndex == 4) { 
	    setCellColor(rowIndex, 4, Color.white);
	    return event.getText();
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
    /** The list of events **/
    Vector events = new Vector();
    
}
 
