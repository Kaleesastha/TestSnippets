/* $Id: EventQue.java,v 1.1 2003/01/14 05:54:32 rajalakshmytr Exp $
 *
 * This implementation will check the que size periodicaly for given 
 * interval and given stretch of time. 
 * You could add events using performance simulator tool.
 *
 * Usage: java EventQue hostName period(milliseconds) TotalTime(seconds)
 *
 * hostName:  Name of the machine where NMS is running
 * period:    The time period in which events are buffered
 * TotalTime: The total time interval after which all the buffered events are dumped into files
 */

import java.io.*;
import java.rmi.Naming;

import com.adventnet.nms.eventdb.EventAPI;
import com.adventnet.security.authentication.RMIAccessAPI;

public class EventQue
{
    public EventQue()
    {
    }

    public static void main(String args[])
    {
        String hostName = "localhost";
        if(args.length != 3)
        {
            System.out.println("Usage: java EventQue hostName period(milliseconds) TotalTime(seconds)");
            return;
        }
        try
        {
            hostName=args[0];
            RMIAccessAPI rmiaccessapi = (RMIAccessAPI)Naming.lookup("//" + hostName + "/RMIAccessAPI");
            EventAPI eventapi = (EventAPI)rmiaccessapi.getAPI("root", "public", "EventAPI");
            System.out.println("GOT HANDLE");
            StringBuffer stringbuffer = new StringBuffer();
            boolean flag = false;
            int i = Integer.parseInt(args[1]);
            long l = System.currentTimeMillis();
            int j = Integer.parseInt(args[2]);
            for(long l1 = (System.currentTimeMillis() - l) / 1000L; l1 < (long)j; l1 = (System.currentTimeMillis() - l) / 1000L)
            {
                Thread.sleep(i);
                String s1 = "QUE SIZE  " + eventapi.getEventQueueSize();
                stringbuffer.append(s1 + "\n");
                System.out.println(s1);
            }

            File file = new File("." + File.separator + "QueResult.txt");
            file.createNewFile();
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            fileoutputstream.write(stringbuffer.toString().getBytes());
            fileoutputstream.close();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
