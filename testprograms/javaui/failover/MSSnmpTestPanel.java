
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


public class MSSnmpTestPanel extends AbstractBaseNmsPanel implements InternalFrameListener
{
    JMenuBar menuBar;
    public MSSnmpTestPanel()
    {
        //super(false);
    }

    static JTextArea textarea=null;
	
    private void initLayout()
    {
        String message = "MS Test SNMP Example .";
        setLayout(new BorderLayout());
        JPanel pane1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
	textarea = new JTextArea(10,40);
        textarea.setFont(new Font("SansSerif",Font.BOLD,12));
        textarea.setText(message);
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel msg = new JLabel("NmsPanel Example");
        top.setBorder(new EtchedBorder());
        top.add(msg);
        textarea.setEditable(true);
        textarea.setLineWrap(true);
        pane1.setBorder(new EtchedBorder());
        gbc.gridx=0; gbc.gridy=0;
        pane1.add(new JScrollPane(textarea),gbc);
        add(pane1,BorderLayout.CENTER);
        add(top,BorderLayout.NORTH);

        // Code For testing Management Client
        JPanel p = new JPanel();
	JLabel host = new JLabel("Host");
	txt1 = new JTextField(20);
	p.add(host);
	p.add(txt1);

	txt2 = new JTextField(20);
	JLabel oid = new JLabel("OID");
	p.add(oid);
	p.add(txt2);
                
	getButtonSnmp = new JButton("SNMP-TEST");
	getButtonSnmp.addActionListener(this);
	p.add(getButtonSnmp);
	
                
	add("North", p);

        
    }

    public static void setMSTextArea(String result)
    {
        //System.out.println(" In setTextArea");
        textarea.setText(result);
    }


    private JTextField txt1=null;
    private JTextField txt2=null;

    private JButton getButtonSnmp=null;



     public void internalFrameActivated(InternalFrameEvent e) 
                {
                    JInternalFrame jif = (JInternalFrame) e.getSource();
                    jif.setTitle("MS-SnmpTest-Panel ");
                }
    
    /** init method with Applet parameter */
    public void init(JApplet app)
    {
        initLayout();
    }

    public String key()
    {
        return "EXAMPLE_SNMP";
    }

    //just to populate this panel's specific menu data.
    // menusVector , This vector is to be initialized at startup. 
    private Vector menusVector = new Vector(5);

    //called from the Web NMS Client.  This will actually hold this
    //panel-specific menus.We shall update this menus from this in
    //our menuVector and return this when getPanelMenuBar is invoked.
    public void setPanelMenuBar (JMenuBar mb)
    {

         menuBar = mb;
		
        for(int i = 0;i<mb.getMenuCount();i++)
        {
            menusVector.addElement(mb.getMenu(i));
        }
        
    }

    //  This will return a MenuBar to be placed in the parent frame. This
    //  menubar will be shown when this panel is chosen.
    public JMenuBar getPanelMenuBar ()
    {
        
        int size = menusVector.size();
        for(int i = 0; i < size;i++)
        {
            JMenu m = ((JMenu)menusVector.elementAt(i));
            menuBar.add(m);
        }
        return menuBar ;
    }

    public void setProperties(Properties prop)
    {
	//System.out.println("In MSSnmpTestPanel");
        //String type = (String)prop.get("Type");

        //This is the ID specified in tree.xml corresponding
        //to this Device-ID otherwise it will be null.
        /*if(type == null)
        {
            return;
        }
        int index = tabPane.indexOfTab(type.trim()) ;
        index = (index >0 )?index : 0;
        tabPane.setSelectedIndex(index);*/
    }



    //Below methods of the interface NmsPanel are not implemented here.
    //As they are of no significance to us.
    public void start()
    {

    }

    public void stop()
    {
    }

    public void destroy()
    {
    }

    public void addNmsPanelEventListener(NmsPanelEventListener l)
    {
    }

    public void removeNmsPanelEventListener(NmsPanelEventListener l)
    {
    }

    public void fireNmsPanelEvent(NmsPanelEvent e)
    {
    }

    public void actionPerformed(ActionEvent e)
    {
	if(e.getSource().equals(getButtonSnmp))
            {
                String host = txt1.getText();
		String oid = txt2.getText();
                SnmpNmsTest.mcSnmpTest(host,oid);
                return;
            }


    }
    public void internalFrameClosed(InternalFrameEvent e) 
    {
    }
    public void internalFrameClosing(InternalFrameEvent e) 
    {
    }
    public void internalFrameDeactivated(InternalFrameEvent e) 
    {
    }
    public void internalFrameDeiconified(InternalFrameEvent e) 
    {
    }
    public void internalFrameIconified(InternalFrameEvent e) 
    {
    }
    public void internalFrameOpened(InternalFrameEvent e) 
    {
    }

}//End of class MSSnmpTestPanel





