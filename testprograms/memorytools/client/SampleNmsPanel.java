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


public class SampleNmsPanel extends AbstractBaseNmsPanel implements InternalFrameListener
{
	Thread t=null;
    JMenuBar menuBar;
    public SampleNmsPanel()
    {
		System.out.println("Invoked11111111 SampleNmsPanel"+new Date());
        //super(false);
    }

    private void initLayout()
    {
        String message = "This is a Sample Panel";
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
		jif.setTitle("Sample Panel ");
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
        return "SAMPLE_PANEL"; 
	}

    public void setProperties(Properties prop)
    {
		System.out.println("Invoked11111111 setProperties"+new Date());
            
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

}//End of class SampleNmsPanel










