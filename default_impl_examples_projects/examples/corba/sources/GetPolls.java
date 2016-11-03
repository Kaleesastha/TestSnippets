/*
 * $Id: GetPolls.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 *
 * Copyright (c) 1999 - 2002 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE SUITABILITY
 * OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS
 * SOFTWARE OR ITS DERIVATIVES.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.*;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import com.adventnet.nms.common.corba.Property_CT;
import com.adventnet.nms.poll.PollConstants;
import com.adventnet.nms.poll.corba._PollUnitObserver_CIImplBase;
import com.adventnet.nms.poll.corba.PollAPI_CI;
import com.adventnet.nms.poll.corba.PollAPI_CIHelper;
import com.adventnet.nms.poll.corba.PolledData_CI;
import com.adventnet.util.NVProperties;


/**
 * <p>Shows a table of PolledData (with protocol as TL1). Registers as
 * PollUnitObserver via Web NMS's northbound CORBA. Any
 * addition/deletion/modification of PolledData in Web NMS will hence be
 * reflected in the table.</p>
 * <p>For PolledData with numeric type of collected data, a collected statistics
 * graph can be plotted by right-clicking the corresponding PolledData and
 * choosing "Plot -&gt; Collected Statistics" in the popup menu that is
 * displayed.</p>
 *
 * @author   unascribed
 * @version  $Revision: 1.1.1.1 $
 *
 * @since    AdventNet Web NMS 2.2
 */
public class GetPolls extends AbstractTableModel
{
    private PollAPI_CI pollAPI; 

    // If this array is changed, getValueAt and getColumnClass methods may have
    // to be modified as well.
    private String[] columns = {"ID", "Agent", "DNS name", "Name", 
                                "Command", "MO", "Period", "Active", 
                                "Multiple PolledData", "Data type"};
    private List polledDataObjects = new Vector();
    private CorbaPollListener cps = new CorbaPollListener();

    private JPopupMenu menu;
    private CollectedGraph cg;
    private JTable pdTable;
    private JLabel statusLabel;


    /**
     * Initializes the PollAPI, populates the table of TL1 PolledData and
     * registers as listener for PolledData notifications.
     */
    public GetPolls(String args[]) throws Exception
    {
        Property_CT[] prop = null;
        NVProperties NVP = null;
        ORB orb = ORB.init(args, null);
        NameComponent nc1 = new NameComponent("com", "com");
        NameComponent nc2 = new NameComponent("adventnet", "adventnet");
        NameComponent nc3 = new NameComponent("nms", "nms");
        NameComponent[] NC = {nc1, nc2, nc3};
        
        NamingContext nmsContext = NamingContextHelper.narrow((NamingContextHelper.narrow(orb.resolve_initial_references("NameService"))).resolve(NC));
        NameComponent ncomp = new NameComponent("PollAPI", "");
        NameComponent[] nca = { ncomp };
        pollAPI = PollAPI_CIHelper.narrow(nmsContext.resolve(nca));

        Property_CT[] pollNVP = new Property_CT[1];
        pollNVP[0] = new Property_CT();
        pollNVP[0].name = "protocol";
        pollNVP[0].value = "TL1";

        pdTable = new JTable(this);
        pdTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pdTable.sizeColumnsToFit(-1);  

        refreshTable();
        pollAPI.register_CT(cps);
        constructGUI();
    }

    /** 
     * Fetches the TL1 PolledData using PollAPI_CI, updates the internal
     * PolledData list and updates the JTable.
     */
    private void refreshTable()
    {
        polledDataObjects.clear();

        Property_CT[] pollNVP = new Property_CT[1];
        pollNVP[0] = new Property_CT();
        pollNVP[0].name = "protocol";
        pollNVP[0].value = "TL1";

        // Get all PolledData keys with protocol as TL1.
        String[] polledDataKeys = pollAPI.getObjectNamesWithProps_CT(pollNVP);
        PolledData_CI pd;
        for (int i = 0; i < polledDataKeys.length; i++) 
        {
            pd = pollAPI.getPolledData_CT(polledDataKeys[i]);
            if (pd != null)
            {
                polledDataObjects.add(pd);
            }
        }
        fireTableDataChanged();
        if (statusLabel != null) 
        {
            statusLabel.setText("TL1 PolledData count: " 
                                + polledDataObjects.size());
        }
    }


    /*
     * AbstractTableModel methods.
     */

    public int getRowCount() 
    { 
        return polledDataObjects.size(); 
    }
    
    public int getColumnCount() 
    { 
        return columns.length; 
    }
    
    public String getColumnName(int columnIndex) 
    { 
        return columns[columnIndex];
    }

    public Class getColumnClass(int columnIndex)
    {
        if (columnIndex == 0 || columnIndex == 6)
        {
            // Poll ID and polling period columns.
            return Integer.class;
        }
        else if (columnIndex == 7 || columnIndex == 8)
        {
            // Active and Multiple PolledData columns.
            return Boolean.class;
        }
        else
        {
            return Object.class;
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        if (rowIndex >= polledDataObjects.size()) return null;

        PolledData_CI pd = (PolledData_CI)polledDataObjects.get(rowIndex);
        Property_CT[] props = pd.getProperties_CT();
        NVProperties nvp = new NVProperties(props);

        if (columnIndex == 0) 
        {
            return Integer.valueOf(nvp.getProperty("id"));
        }
        else if (columnIndex == 1) 
        {
            return nvp.getProperty("agent");
        }
        else if (columnIndex == 2)
        {
            return nvp.getProperty("dnsName");
        }
        else if (columnIndex == 3) 
        {
            return nvp.getProperty("name");
        } 
        else if (columnIndex == 4)
        {
            return nvp.getProperty("oid");
        }
        else if (columnIndex == 5) 
        {
            return nvp.getProperty("parentObj");
        }
        else if (columnIndex == 6) 
        {
            return Integer.valueOf(nvp.getProperty("period"));
        }
        else if (columnIndex == 7)
        {
            return Boolean.valueOf(nvp.getProperty("active"));
        }
        else if (columnIndex == 8) 
        {
            return Boolean.valueOf(nvp.getProperty("isMultiplePolledData"));
        }
        else if (columnIndex == 9) 
        {
            return (pd.getNumericType() == 1) ? "Numeric" : "Non-numeric";
        }
        return null;
    }
    //------------------------------------------------------------------------


    private PolledData_CI getPolledData(int row)
    {
        if (row >= polledDataObjects.size()) return null;

        return (PolledData_CI)polledDataObjects.get(row);
    }

    private void showCollectedStatistics()
    {
        int row = pdTable.getSelectedRow();
        if (row == -1) return;

        PolledData_CI pd = getPolledData(row);
        if (pd == null) return;

        if (cg.plotGraph(pd))
        {
            cg.setVisible(true);
        }
    }
   
    private JPopupMenu getPopupMenu()
    {
        if (menu == null) 
        {
            menu = new JPopupMenu();
            JMenu plot = new JMenu("Plot");
            JMenuItem collect = new JMenuItem("Collected Statistics");
            collect.setActionCommand("Collected");
            collect.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    if (ae.getActionCommand().equals("Collected"))
                    {
                        showCollectedStatistics();
                    }
                }
            });

            plot.add(collect);
            menu.add(plot);
        }
        return menu;
    }

    private void constructGUI()
    {
        JFrame frame = new JFrame(); 
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout(3, 3));

        // Top: heading "TL1 PolledData".
        JLabel heading = new JLabel("TL1 PolledData");
        heading.setForeground(Color.black);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel headingPanel = new JPanel();
        headingPanel.add(heading);
        contentPane.add(headingPanel, BorderLayout.NORTH);
        
        // Middle: table of TL1 PolledData.
        JTable table = new JTable(this);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.sizeColumnsToFit(-1);  
        JScrollPane scrollPane = new JScrollPane(table);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        refreshButton.setMnemonic(KeyEvent.VK_R);
        refreshButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                refreshTable();
            }
        });
        
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        gbc.insets = new Insets(0, 0, 3, 0);
        middlePanel.add(scrollPane, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(3, 0, 3, 0);
        middlePanel.add(refreshButton, gbc);
        contentPane.add(middlePanel, BorderLayout.CENTER);

        // Bottom: status panel.
        JPanel statusPanel = new JPanel(new GridBagLayout());
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.insets = new Insets(2, 2, 2, 5);
        JLabel staticStatusLabel = new JLabel("Status");
        staticStatusLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        staticStatusLabel.setForeground(Color.black);
        statusPanel.add(staticStatusLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.insets = new Insets(2, 0, 2, 0);
        statusLabel = new JLabel("TL1 PolledData count: " 
                                    + polledDataObjects.size());
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusLabel.setForeground(Color.black);
        statusPanel.add(statusLabel, gbc);
        statusPanel.setBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        contentPane.add(statusPanel, BorderLayout.SOUTH);

        table.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent me)
            {
                if (SwingUtilities.isRightMouseButton(me))
                {
                    int clickedRow = pdTable.rowAtPoint(me.getPoint());
                    int selectedRow = pdTable.getSelectedRow();

                    if (clickedRow == -1) return;
                    
                    if (clickedRow != selectedRow)
                    {
                        pdTable.setRowSelectionInterval(clickedRow, clickedRow);
                    }
                    
                    PolledData_CI pd = getPolledData(clickedRow);
                    if (pd == null) return;

                    if (pd.getNumericType() == 1)
                    {
                        getPopupMenu().show(me.getComponent(), 
                                            me.getX(), me.getY());
                    }
                }
            }
        });

        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) 
            {
                try
                {
                    if (pollAPI != null) pollAPI.deregister_CT(cps);
                    System.exit(0);
                }
                catch (Exception ex)
                {
                    System.err.println("Exception while closing CORBA client:"); 
                    ex.printStackTrace(); 
                    System.err.println();
                    System.err.println("Web NMS Server maybe down or may have"
                        + " been restarted.");
                    System.exit(2);
                }
            }
        });
        
        this.pdTable = table;

        cg = new CollectedGraph(frame, pollAPI);

        frame.setTitle("AdventNet Web NMS CORBA Client");
        frame.setSize(700, 300);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - frame.getWidth())/2,
                          (screenSize.height - frame.getHeight())/2);
        frame.setVisible(true);
    }


    /**
     * This class implements the PollUnitObserver_CI interface to receive
     * notifications about addition/modification/deletion of PolledData. Only
     * PolledData with protocol as TL1 are considered.
     */
    class CorbaPollListener extends _PollUnitObserver_CIImplBase
    {
        public void update_CT(int type, PolledData_CI pd)
        {
            Property_CT[] props = pd.getProperties_CT();
            NVProperties nvp = new NVProperties(props);
            if (!nvp.get("protocol").equals("TL1")) return;

            if (type == PollConstants.MODIFY_OBJ_DETAILS)
            {
                // TL1 PolledData modification.
                int row = findPdRow(pd);
                if (row != -1) 
                {
                    polledDataObjects.set(row, pd);
                    fireTableRowsUpdated(row, row);
                    statusLabel.setText("TL1 PolledData modified: " 
                                + pd.getName() + " (ID: " + pd.getId() + ")");
                }
            }
            else if (type == PollConstants.ADD_NEW_OBJECT)
            {
                // TL1 PolledData addition.
                polledDataObjects.add(pd);
                int row = polledDataObjects.size();
                fireTableRowsInserted(row, row);
                statusLabel.setText("TL1 PolledData added: " + pd.getName()
                                + " (ID: " + pd.getId() + ")");
            }
            else if (type == PollConstants.REMOVE_OBJECT)
            {
                // TL1 PolledData deletion.
                int row = findPdRow(pd);
                if (row != -1)
                {
                    polledDataObjects.remove(row);
                    fireTableRowsDeleted(row, row);
                    statusLabel.setText("TL1 PolledData deleted: " 
                                + pd.getName() + " (ID: " + pd.getId() + ")");
                }
            }
        }

        private int findPdRow(PolledData_CI pd)
        {
            int noOfPDs = polledDataObjects.size();
            PolledData_CI availablePD;
            for (int i = 0; i < noOfPDs; i++)
            {
                availablePD = (PolledData_CI)polledDataObjects.get(i);
                if (availablePD.getKey().equals(pd.getKey()))
                {
                    return i;
                }
            }
            return -1;
        }
    }

    public static void main(String args[])
    {
        try 
        { 
            //setup the look and feel for native platform L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) 
        {
            System.err.println("Couldn't use the system look and feel: " + e);
        }

        try 
        { 
            GetPolls getpolls = new GetPolls(args);
        } 
        catch (Exception e) 
        {
            System.err.println("Problem initializing CORBA client: ");
            e.printStackTrace(); 
            System.exit(1);
        }
    }
}
