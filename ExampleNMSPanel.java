
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


public class ExampleNMSPanel extends AbstractBaseNmsPanel implements InternalFrameListener
{
    JMenuBar menuBar;
    public ExampleNMSPanel()
    {
        //super(false);
    }

    private void initLayout()
    {
        String message = "This is an Example of NmsPanel which demonstrates how NmsPanel can be integrated with NMS. In this panel you can design your own UI based on the requirement. The NmsPanel can be invoked from any of panel specific or object specific menu from the mapdata/menus or listmenus directory.";
        setLayout(new BorderLayout());
        JPanel pane1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JTextArea textarea = new JTextArea(20,30);
        textarea.setFont(new Font("SansSerif",Font.BOLD,12));
        textarea.setText(message);
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel msg = new JLabel("NmsPanel Example");
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
		JInternalFrame jif = (JInternalFrame) e.getSource();
		jif.setTitle("ExamplePanel ");
	}
    
    /** init method with Applet parameter */
    public void init(JApplet app)
    {
        initLayout();
    }
	public void init(Properties p)
	{
		super.init(p);
	}

    public String key()
    { 
        return "EXAMPLE_ID"; 
	}

    public void setProperties(Properties prop)
    {
            
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
	    System.out.println("In start");
    }

    public void stop()
    {
	    System.out.println("In stop");
    }

    public void destroy()
    {
	    System.out.println("In destroy");
    }

    public void actionPerformed(ActionEvent e)
    {
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

}//End of class ExampleNMSPanel










