import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

//java imports
import java.util.*;

import com.adventnet.nms.common.corba.*;
import com.adventnet.nms.alertdb.corba.*;
import com.adventnet.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

class CorbaAlertWindow
{

    AlertAPI_CI alertapi = null;
    CorbaAlertObserver observer = null;
    boolean alreadyRegistered = false;
    JFrame frame = null;
    JTable table = null;
    Vector dataVector = null;
    Vector columnVector = null;

    CorbaAlertWindow(String args[])
    {
        initializeCorba(args);
        initializeVectors();
        initializeCorbaWindow();
        observer = new CorbaAlertObserver(); 
    }

    void initializeVectors()
    {
        dataVector = new Vector();
        columnVector = new Vector();
        columnVector.addElement( "source" );
        columnVector.addElement( "entity" ); 
        columnVector.addElement( "severity"); 
        columnVector.addElement( "message" );
        columnVector.addElement( "modTime"); 
        columnVector.addElement( "category"); 
    }

    void initializeCorbaWindow()
    {
        frame = new JFrame("Corba Alert Observer");
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
                    registerAlertObserver();
                }
            });

        JButton delete = new JButton( "Deregister");
        delete.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e)
                {
                    deRegisterAlertObserver(true);
                }
           });

        JButton close = new JButton( "Close");
        close.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e)
                {
                    deRegisterAlertObserver(false);
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
                    deRegisterAlertObserver(false);
                    System.exit(0);
                }
            });
        frame.show();
    }

    void registerAlertObserver()
    {
        if(alreadyRegistered)
        {
            JOptionPane.showMessageDialog(frame,"Already Registered","Corba Alert Window",JOptionPane.ERROR_MESSAGE);
            return; 
        }
        try
        {
            alertapi.registerForAlerts_CT(observer); 
            alreadyRegistered = true;
            JOptionPane.showMessageDialog(frame,"Successfully Registered","Corba Alert Window",JOptionPane.INFORMATION_MESSAGE);

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(frame,"Problem in Registering","Corba Alert Window",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
        }
    }

    void deRegisterAlertObserver(boolean needError)
    {
        if(!alreadyRegistered)
            {
                if(needError)
                    {
                        JOptionPane.showMessageDialog(frame,"Not yet Registered","Corba Alert Window",JOptionPane.ERROR_MESSAGE); 
                    }
                return;
            }
        try
            {
                if(alertapi.deRegisterForAlerts_CT(observer))
                    {
                        alreadyRegistered = false;
                        JOptionPane.showMessageDialog(frame,"Alert Observer Successfully Deregistered","Corba Event Window",JOptionPane.INFORMATION_MESSAGE);
                    }

            }
        catch(Exception e)
            {
                JOptionPane.showMessageDialog(frame,"Problem is Deregistering Alert Observer","Corba Event Window",JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
    }

    private Vector getWindowProperties(NVProperties prop) 
    {
        Vector pureProp = new Vector(); 
        pureProp.addElement(prop.get( "source"));
        pureProp.addElement(prop.get( "entity"));
        pureProp.addElement(prop.get( "severity"));
        pureProp.addElement(prop.get( "message"));
        pureProp.addElement(prop.get( "modTime"));
        pureProp.addElement(prop.get( "category"));
        return pureProp;

    }

    class CorbaAlertObserver extends _AlertObserver_CIImplBase 
    {
        public void update(Alert_CI eve) 
        {
           Property_CT[] ct = eve.getProperties_CT();
            NVProperties prop = new NVProperties(ct);
            dataVector.addElement(getWindowProperties(prop)); 
            table.updateUI(); 
        }

        public void updateWithName(Alert_CI[] al)
                {
                        for(int i=0;i<al.length;i++)
            {
                                update(al[i]);
            }
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

            NameComponent ncomp = new NameComponent("AlertAPI","");
            NameComponent[] NCA = {ncomp};

            alertapi = AlertAPI_CIHelper.narrow(NC.resolve(NCA));
        }
        catch(Exception e)
        {
            System.out.println(e); 
            }
    }

    public static void main(String args[])
    {
        new CorbaAlertWindow(args);
    }
}

