//$Id: API.java,v 1.2 2002/05/17 11:25:54 santu Exp $
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import java.util.Vector;
import java.rmi.*;
import java.rmi.server.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class API  implements ActionListener
{
    static EventAPI e=null;
    static AlertAPI alertAPI=null;
    
    JFrame f;
    JPanel pan;
    JLabel lab;
    JTextField tf;
    JButton but;
    JRadioButton trap_button;
    JRadioButton event_button;
    JRadioButton alert_button;
    ButtonGroup group;
    TrapObserver trapObserver=null;
        EventObserver eventObserver=null;
        AlertObserver alertObserver=null;
     API()
    {
        f=new JFrame("Tool for registering and deregistering Observers(Event,Trap,Alert)");
        f.setSize(700,110);
        //        f.setBackground(Color.red);
        pan=new JPanel(); pan.setBackground(Color.darkGray);
        //        f.getContentPane().setLayout(new FlowLayout());
        
        lab=new JLabel("Enter the name of the user written Observer");lab.setForeground(Color.orange);
        tf=new JTextField(10);
        but=new JButton("Register");
        // but.setBackground(new Color(180,2,244));
but.setBorder(BorderFactory.createRaisedBevelBorder());
        but.addActionListener(this);
        
        trap_button= new JRadioButton("TrapObserver");
        event_button=new JRadioButton("EventObserver");
            
        alert_button=new JRadioButton("AlertObserver");
        //        trap_button.addActionListener(this);
        //event_button.addActionListener(this);
        //alert_button.addActionListener(this);
        event_button.setBackground(Color.white); event_button.setActionCommand("event");
        trap_button.setBackground(Color.lightGray); trap_button.setActionCommand("trap");
        alert_button.setBackground(Color.gray); alert_button.setActionCommand("alert");
        event_button.setSelected(true);
        group=new ButtonGroup();
        group.add(event_button); group.add(trap_button); group.add(alert_button);
        
       
        JPanel holder =new JPanel();
        holder.setLayout(new GridLayout(3,1));
        
        holder.add(event_button); holder.add(trap_button);holder.add(alert_button);
        
        pan.add(holder) ;
        pan.add(lab); pan.add(tf);pan.add(but);
        f.getContentPane().add(pan);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter()
            {
public void windowClosing(WindowEvent wwe)
        {
            System.out.println("Bye thanks for using the tool ");
        }
                 });
    }
    static int count=0;

    public void actionPerformed(ActionEvent ae)
    {
        
        if(e==null)
        {
            System.out.println("Please wait look up is not yet over"); return;
        }

        Object ooo=null;
          String comm=ae.getActionCommand().trim();
        if(comm.equals("Register"))
        {
            if(tf.getText().trim().equals(""))
            {
                System.out.println("Please enter a observer u have written in the text field"); return;
            }

           
            try
            {
                if(tf.getText().equals("API"))
                {
                  Class.forName(tf.getText()).newInstance();
                  return;
                }
               ooo=Class.forName(tf.getText()).newInstance();
            
            if(trap_button.isSelected())
            {
                trapObserver=(TrapObserver)ooo;
               e.register(trapObserver);
                
            }
            else if (event_button.isSelected())
            {
                eventObserver=(EventObserver)ooo;
               e.registerForEvents(eventObserver);
            }
            else if(alert_button.isSelected())
            {
                 alertObserver=(AlertObserver)ooo;
                 alertAPI.registerForAlerts(alertObserver);
            }
            but.setText("Deregister");
            System.out.println("u have successfully registered "+tf.getText());
            tf.setEditable(false);
            }catch(Exception not)
            {
                if(not instanceof ClassNotFoundException)
                {
                   
                    System.out.println("The observer u have entered is not present in the CLASSPATH");
                    
                }
                not.printStackTrace();
            }
            
        }
        else if(comm.equals("Deregister"))
        {
           
            try
            {
            if(trap_button.isSelected())
            {
               System.out.println(e.deregister(trapObserver));
            }
            else if (event_button.isSelected())
            {
                System.out.println(e.deregisterForEvents(eventObserver));
            }
            else if(alert_button.isSelected())
            {
                 System.out.println( alertAPI.deregisterForAlerts(alertObserver));
            }
            System.out.println(tf.getText()+" is Deregistered");
             tf.setEditable(true);
              but.setText("Register");
            }catch(Exception remote)
            {
                remote.printStackTrace();
            }

           
        }

    }
  public static void main(String args[])
    {
        API ui=new API(); 
        String name="makesh";
                System.out.println(args.length+"  ");
                        if(args.length !=0 && args[0]!=null)
                            name = args[0];  
        try
        {
         e= (EventAPI)Naming.lookup("//"+name+" /EventAPI");
        if(e!=null)
            System.out.println("Succeded in getting the EventAPI handle");
        
        alertAPI=(AlertAPI)Naming.lookup("//"+name+" /AlertAPI");
            if(alertAPI!=null)
                System.out.println("Succeded in getting the AlertAPI handle");
            
  
                
        
        }
        catch(RemoteException e)
        {
            System.out.println("Failed to get the handle = "+e);
            e.printStackTrace();
        }
        catch(Exception ee)
        {
            System.out.println("Exception is thrown = "+ee);
            ee.printStackTrace();
        }
     
    }
    
}

class eventObserver1 extends java.rmi.server.UnicastRemoteObject implements EventObserver
{

    public eventObserver1() throws java.rmi.RemoteException
    {
        super();
    }
    public void update(com.adventnet.nms.eventdb.Event e)
    {
        System.out.println("update for event from event observer1 = "+e+"    count = "+API.count);
        API.count++;
    }


}
 
class eventObserver2 extends java.rmi.server.UnicastRemoteObject implements EventObserver 
{

    public eventObserver2() throws java.rmi.RemoteException
    {
        super();
    }
    public void update(com.adventnet.nms.eventdb.Event e)
    {
        System.out.println("update for event from event observer 2 = "+e+"   count = "+API.count);
        API.count++;
    }

}


class alertObserver1 extends java.rmi.server.UnicastRemoteObject implements AlertObserver
{

    public alertObserver1() throws java.rmi.RemoteException
    {
        super();
    }
    public void update(com.adventnet.nms.alertdb.Alert a)
    {
        System.out.println("update for Alert from  alertObserver1 = "+a);
    }
    public void update(Vector v)
    {
        System.out.println("Bulk update for Alert from alertObserver1 = "+v);
    } 


}
 
class alertObserver2 extends java.rmi.server.UnicastRemoteObject implements AlertObserver 
{

    public alertObserver2() throws java.rmi.RemoteException
    {
        super();
    }
    public void update(com.adventnet.nms.alertdb.Alert a)
    {
        System.out.println("update for Alert from  alertObserver2 = "+a);
    }
    public void update(Vector v)
    {
        System.out.println("Bulk update for Alert from alertObserver2 = "+v);
    } 
}

class trapObserver1 extends java.rmi.server.UnicastRemoteObject implements TrapObserver
{

    public trapObserver1() throws java.rmi.RemoteException
    {
        super();
    }
    public void update(com.adventnet.snmp.snmp2.SnmpPDU pdu)
    {
        System.out.println("update for trap from  trapObserver1 = "+pdu);
    }
   


}
 
class trapObserver2 extends java.rmi.server.UnicastRemoteObject implements TrapObserver 
{

    public trapObserver2() throws java.rmi.RemoteException
    {
        super();
    }
    public void update(com.adventnet.snmp.snmp2.SnmpPDU pdu)
    {
        System.out.println("update for trap from  trapObserver2 = "+pdu);
    }
}




