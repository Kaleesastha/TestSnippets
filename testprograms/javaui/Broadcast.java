package test;
import java.awt.*;
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.broadcast.*;
import javax.swing.JApplet;

class Broadcast implements NmsFrame,BroadcastListener
{
     public Broadcast()
    {
        // this class is registered for responses

        BroadcastClient.getInstance().registerForResponse(this);
        sendAMessage();
    }

    public void sendAMessage()
    {
        // this method can be invoked by pressing a button  named "Send Message"

            BroadcastClient.getInstance().send("MY Message","ALLCLIENTS");
    }


    public void receiveAndShow( String data )
    {
        // this method is an interface method in        BroadcastListener
            System.out.println("received data = " + data);
    }

    public void init (JApplet app)
    {
        // method implemented from NmsFrame interface
    }

    public void setVisible(boolean flag)
    {
        // method implemented from NmsFrame interface
    }
} 
