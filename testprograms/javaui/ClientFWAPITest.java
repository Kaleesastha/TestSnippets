import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*;
import java.util.Properties;
import com.adventnet.nms.util.ClientFrameWorkAPI;
import com.adventnet.nms.util.ServerShutDownInterface;
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.util.PureClientUtils;

public class ClientFWAPITest  extends JFrame implements ActionListener,SocketConnection,ServerShutDownInterface

{
    Container cntr = null;
    JLabel        lname,lpass,lport,lserver =null;
    JTextField    tname,tport,tserver =null;
    JPasswordField tpass           = null;
    JButton       butConnect      = null;
    JTextArea     jtDisplayArea   = null;
    JScrollPane   jscrPane        = null;
    static final int   ID_CONNECT = 100;
    Properties  pts = null;
   
    ClientFWAPITest()
    {
        cntr       = this.getContentPane();
        lname      = new JLabel("UserName");
        lpass      = new JLabel("PassWord");
        lport      = new JLabel("Port");
        lserver    = new JLabel("Server Name");
        tname         = new JTextField();
        tpass         = new JPasswordField();
        tport         = new JTextField();
        tserver       = new JTextField();
        butConnect    = new JButton("Connect");
        jtDisplayArea = new JTextArea();
        jscrPane      = new JScrollPane(jtDisplayArea);
        cntr.setLayout(null);
        addControls();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void close()
    {
        // Called when the socket is closed
    }
    public void receive(byte bs[])
    {
       // Called when the socket  receives Message
    }
    public void actionPerformed(ActionEvent aevent)
    {
        try
        {
         
        pts = new Properties();
        pts.put("USERNAME",tname.getText().trim());

        pts.put("PASSWORD",tpass.getText().trim());
        pts.put("WEB-SERVER-PORT",tport.getText().trim());
        pts.put("HOSTNAME",tserver.getText().trim());

        ClientFrameWorkAPI cfwapi = new ClientFrameWorkAPI(pts);
        if(cfwapi != null)
        {
            cfwapi.registerParentComponent(this);
            cfwapi.registerforServerShutdown(this);
        }
        MainSocketClient masocketclnt = PureClientUtils.commonSocket;
        if(masocketclnt != null )
        {
         
           masocketclnt.registerForResponses(this,"Main");
           masocketclnt.setClientFrameWorkAPI(cfwapi);
           masocketclnt. showCustomizedServerClosedDialog( new JOptionPane("TConnection LostT",
                                                                           JOptionPane.ERROR_MESSAGE,
                                                                           JOptionPane.YES_NO_OPTION,
                                                                           null),"IServerClosedI");
        }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       
    }

    private void addControls()
    {
        lname.setBounds(10,10,80,20);
        cntr.add(lname);
        tname.setBounds(150,10,80,20);
        cntr.add(tname);
        lpass.setBounds(10,50,80,20);
        cntr.add(lpass);
        tpass.setBounds(150,50,80,20);
        cntr.add(tpass);
        lport.setBounds(10,100,80,20);
        cntr.add(lport);
        tport.setBounds(150,100,80,20);
        cntr.add(tport);
        lserver.setBounds(10,140,80,20);
        cntr.add(lserver);
        tserver.setBounds(150,140,80,20);
        cntr.add(tserver);
        butConnect.setBounds(10,180,90,20);
        cntr.add(butConnect);
        // jscrPane.setBounds(10,220,80,80);
        //cntr.add(jscrPane);
        butConnect.setActionCommand(""+ID_CONNECT);
        butConnect.addActionListener(this);
        setSize(400,400);
    }
    public  void   serverShutdownResponse()
    {
        System.out.println("Server Shutdown Called....");
        //        System.exit(0);
    }

   
    public static void main(String n[])
    {
        ClientFWAPITest  cf = new ClientFWAPITest(); 
    }
}
