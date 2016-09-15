/* $Id: SimpleThread.java,v 1.1 2003/10/11 07:13:14 priya Exp $
 *
 * File Name      : SimpleThread.java
 * Description    : To add the multiple Number of Mos through multiple treads
 * Other Info     : 
 *
 * USAGE          : java SimpletThread <hostname> <no.of threads>
 * Parameter Desc :              
 *
 * Owner Name     : priya
 * Change History(Author Date(dd-mm-yyy) and Description of methods added/modifed/deleted):
 */

import com.adventnet.nms.topodb.*;
import java.rmi.*;

public class SimpleThread extends Thread
{
    private int countDown = 5;
    private static int threadCount = 0;
    private int threadNumber = ++threadCount;
    private static TopoAPI topoApi = null;

    public SimpleThread()
    {
        System.out.println("Making " + threadNumber);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                //Uncomment the below lines to add Node

                //ManagedObject myMo = new ManagedObject();
                //myMo.setName("MO_thread_"+threadNumber + "_" + countDown);

		// Incomment the below lines to chk ManagedObjects
                SnmpNode myMo = new SnmpNode();
                myMo.setName("MO_thread_"+threadNumber + "_" + countDown);
                topoApi.addObject(myMo);
            }
            catch(Exception e)
            {
                System.out.println(" Exception while adding mo" + e.getMessage() + "with thread" + Thread.currentThread().getName());
            }
            if(--countDown == 0) return;
        }
    }
    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            System.out.println(" Usage: SimpleThread <rmiHostName> <Threads:int>");
            System.out.println(" ex: SimpleThread meerav 5 ");
            System.out.println(" Will Add 10 MOs in 5 threads (each thread adding 5 mos) parallely");
            System.exit(0);
        }
        String hostName = args[0];
        int maxThread = Integer.parseInt(args[1]);
     
      
      try
        {
            topoApi=(TopoAPI)Naming.lookup("rmi://" + hostName + "/TopoAPI");
        }
        catch(Exception e)
        {
            System.out.println(" Exception while getting topoapi handle" + e.getMessage());
        }
        //Would add 30 objects. Change thread numbers here..
        for(int i = 0; i < maxThread; i++)
            new SimpleThread().start();
        System.out.println("All Threads Started");
    }
} ///:~
