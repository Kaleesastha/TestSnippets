import java.net.Socket;
import java.net.ServerSocket;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class SecondApp 
{
    Socket sock = null;	
    OutputStream os = null;

    public SecondApp(int port, long noOfMsgsPerSec,long totSecs)
    {
        try
        {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started at port : " + port);
            while(sock == null)
            {
                sock = server.accept();
            }
            System.out.println("Socket connected");
            os = sock.getOutputStream();
            //while(true)
            
                String msg = waitForSemicolon(new DataInputStream(sock.getInputStream()));
                System.out.println("Message received " + msg);
                Thread.sleep(1);
				respondToMessage(msg);
                System.out.println("Press Enter to send autonomous messages...");
            while(true)
	    {	
                waitForEnter();
                sendNotifications(noOfMsgsPerSec,totSecs);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String waitForSemicolon(DataInputStream dis)
    {
        int val = -1;
        String message = "";
        boolean scNotReceived = true;
		
        try
        {
            while (scNotReceived)
            {
                if ((val = dis.read()) != -1)
                {
                    byte[] data = new byte[dis.available() + 1];
                    data[0] = (byte) val;
                    dis.readFully(data, 1, data.length-1);
                    message = message + new String(data);
                    if(message.trim().endsWith(";"))
                    {
                        scNotReceived = false;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }	
        return message;
    }

    private void waitForEnter()
    {
        String line = null;
        boolean waitFlag = true;

        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (waitFlag)
            {
                line = br.readLine();
                if(line != null)
                {
                    waitFlag = false;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void respondToMessage(String message)
    {
        String ctag = findCtag(message);
        String response = "\r\n\n   Saravanakumar 11-11-11 22:22:22\r\nM  " + ctag + " COMPLD\r\n     \"ROOT PUBLIC \"\r\n;";
        try
        {
            os.write(response.getBytes());
			os.flush();
			System.out.println(" RESPONSE =="+response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String findCtag(String msg)
    {
        String ct = "";
        int colonCount = 0;
        StringTokenizer stok = new StringTokenizer(msg,":",true);
			
        while(stok.hasMoreElements())
        {
            String token = stok.nextToken();
            if (token.equals(":"))
            {
                ++colonCount;
            }
            else if (colonCount == 3)
            {				
                ct = token;
            }
        }
        return ct;
    }

    private void sendNotifications(long noOfMsgsPerSec,long totSecs)
    {
        int count = 0;
        int min = 1000;
        long modVal = noOfMsgsPerSec;
        long n = noOfMsgsPerSec*totSecs;
        String autonomousMesg = "";

        long start = System.currentTimeMillis();
        System.out.println("Start : " + start); 
        //		while(true) 
        {
            //	if((System.currentTimeMillis() - start) < min*n)
            for(int i=1;i<=n;i++)  // index (i)  value should be start from 1.
            {
                autonomousMesg = "\r\n\n   Source 2002-03-14 19:32:55\r\n** " + i  + " REPT ALM EQPT\r\n   \"ST1-6:MJ,CRDRMVD \"\r\n;";
				//autonomousMesg = "\r\n\n   sundar 2002-03-15 16:29:21\r\n** " + i + " REPT ALM PORT\r\n   \"ST1-3-1:MJ,LOS \"\r\n   \"ST1-3-1:MJ,LOF \"\r\n   \"ST1-3-1:MJ,CGA-RED \"\r\n   \"ST1-5-1:MJ,LOS \"\r\n   \"ST1-5-1:MJ,LOF \"\r\n   \"ST1-5-1:MJ,CGA-RED \"\r\n;";
                try
                {
                    os.write(autonomousMesg.getBytes());
		    if(i % modVal == 0)
			Thread.sleep(990);
		    if((i % 1000) == 0)
                    {
                        long endTime = System.currentTimeMillis();
                        long difInSec = (endTime-start)/1000;
                        long totMsgs = i;
                        long rate = totMsgs/difInSec;
                        System.out.println("*****************"+totMsgs + " autonomous message(s) sent in "+difInSec+" Seconds \n *****************  Sending rate is " + rate+" automsgs/sec");
                    }
                    //Thread.sleep(modVal);
                }
                catch (Exception e)
                {
		   //System.out.println("i Val "+i);
                    //e.printStackTrace();
                }
            }
            /*	else
                {
                break;
                }*/
        }
        long endTime = System.currentTimeMillis();
        long difInSec = (endTime-start)/1000;
        long totMsgs = n;
        long rate = totMsgs/difInSec;
        System.out.println("*****************"+totMsgs + " autonomous message(s) sent in "+difInSec+" Seconds \n *****************  Sending rate is " + rate+" automsgs/sec");
        System.out.println("End : " + System.currentTimeMillis()); 
    }

    public static void main(String args[])
    {
        if (args.length != 3)
        {
            System.out.println("Usage: java SecondApp <port number> <no of msgs per sec> <Total secs>");
        }
        else
        {	
            new SecondApp(Integer.parseInt(args[0]), Long.parseLong(args[1]),Long.parseLong(args[2]));
        }
    }
}
