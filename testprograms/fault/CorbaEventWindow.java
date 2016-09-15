import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

//java imports
import java.util.*;

import com.adventnet.nms.common.corba.*;
import com.adventnet.nms.eventdb.corba.*;
import com.adventnet.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

class CorbaEventWindow
{

    EventAPI_CI eventapi = null;
    CorbaObserver observer = null;
    boolean alreadyRegistered = false;
    JFrame frame = null;
    JTable table = null;
    Vector dataVector = null;
    Vector columnVector = null;

    CorbaEventWindow(String args[])
    {
        initializeCorba(args);
        initializeVectors();
        initializeCorbaWindow();
        observer = new CorbaObserver();
    }

    void initializeVectors()
    {
        dataVector = new Vector();
        columnVector = new Vector();
        columnVector.addElement( "source" );
        columnVector.addElement( "entity");
        columnVector.addElement( "severity");
        columnVector.addElement( "text" );
        columnVector.addElement( "time" );
        columnVector.addElement( "category");
    }

    void initializeCorbaWindow()
    {
        frame = new JFrame("Corba Event Observer");
        frame.setSize(600,600);
        table = new JTable();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setDataVector(dataVector,columnVector);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(scrollPane,BorderLayout.CENTER);

        JPanel lowerPanel = new JPanel();
        JButton add = new JButton( "Register");
        add.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    registerEventObserver();
                }
            });

        JButton delete = new JButton( "Deregister");
        delete.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    deRegisterEventObserver(true);
                }
            });

        JButton close = new JButton( "Close");
        close.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    deRegisterEventObserver(false);
                    System.exit(0);
                }
            });

        JButton clear = new JButton( "Clear");
        clear.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    dataVector.clear();
                    table.updateUI();
                }
            });

        lowerPanel.add(add);
        lowerPanel.add(delete);
        lowerPanel.add(clear);
        lowerPanel.add(close);

        frame.getContentPane().add(lowerPanel,BorderLayout.SOUTH);

        frame.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    deRegisterEventObserver(false);
                    System.exit(0);
                }
            });
        frame.show();
    }

    void registerEventObserver()
    {
        if(alreadyRegistered)
        {
            JOptionPane.showMessageDialog(frame,"Already Registered","Corba Event Window",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try
        {
            eventapi.registerForEvents_CT(observer);
            alreadyRegistered = true;
            JOptionPane.showMessageDialog(frame,"Successfully Registered","Corba Event Window",JOptionPane.INFORMATION_MESSAGE);

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(frame,"Problem in Registering","Corba Event Window",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    void deRegisterEventObserver(boolean needError)
    {
        if(!alreadyRegistered)
            {
                if(needError)
                    {
                        JOptionPane.showMessageDialog(frame,"Not yet Registered","Corba Event Window",JOptionPane.ERROR_MESSAGE);
                    }
                return;
            }
        try
            {
                if(eventapi.deRegisterForEvents_CT(observer))
                    {
                        alreadyRegistered = false;
                        JOptionPane.showMessageDialog(frame,"Event Observer Successfully Deregistered","Corba Event Window",JOptionPane.INFORMATION_MESSAGE);
                    }

            }
        catch(Exception e)
            {
                JOptionPane.showMessageDialog(frame,"Problem is Deregistering Event Observer","Corba Event Window",JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
    }

    private Vector getWindowProperties(NVProperties prop)
    {
        Vector pureProp = new Vector();
        pureProp.addElement(prop.get( "source"));
        pureProp.addElement(prop.get( "entity"));
        pureProp.addElement(prop.get( "severity"));
        pureProp.addElement(prop.get( "text"));
        pureProp.addElement(prop.get( "time"));
        pureProp.addElement(prop.get( "category"));
        return pureProp;

    }

    class CorbaObserver extends _EventObserver_CIImplBase
    {
        public void update_CT(Event_CI eve)
        {
            Property_CT[] ct = eve.getProperties_CT();
            NVProperties prop = new NVProperties(ct);
            dataVector.addElement(getWindowProperties(prop));
            table.updateUI();
        }
    }

    public void initializeCorba(String args[])
    {
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

            NamingContext nmsContext = NamingContextHelper.narrow(orb.resolve_initial_references( "NameService"));

            NamingContext NC = NamingContextHelper.narrow(nmsContext.resolve(nca));

            NameComponent ncomp = new NameComponent("EventAPI","");
            NameComponent[] NCA = {ncomp};

            eventapi = EventAPI_CIHelper.narrow(NC.resolve(NCA));
        }
        catch(Exception e)
        {
            System.out.println(e);
            }
    }

    public static void main(String args[])
    {
        new CorbaEventWindow(args);
    }
}

//End of test program

