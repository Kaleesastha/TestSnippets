//$Id: StoreDataAsCSV.java,v 1.2 2008/09/25 13:58:51 tinku Exp $
package test;

import com.adventnet.nms.poll.CollectedInfo;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.poll.PolledData;
import com.adventnet.nms.poll.PollObserver;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This class registers as PollObserver with PollAPI, to receive notification
 * when data is collected for all PolledData and stores the data in CSV
 * format.  The data for a PolledData is stored in a file whose name is the
 * same as the table name present in the CollectedInfo object for that
 * PolledData.  If the table name ends with a "%", it is replaced with the
 * current date in the format "M_d_yyyy". By default, the CSV files will be
 * created in a directory named "data". The directory name can be specified by
 * setting the system property "dirname". If the property is not set, the
 * default value, "data" is taken.
 */
public class StoreDataAsCSV implements PollObserver
{
    /** 
     * Stores the format of date appended to the file name (or table name),
     * when it ends with a %.
     */
    private SimpleDateFormat sdf;

    /**
     * The name of the directory where the CSV files will be created. 
     */
    private String DIR_NAME;

    /**
     * Instance of PollAPI.
     */
    private PollAPI pollApi;

    /**
     * Creates an instance of StoreDataAsCSV.
     */
    public StoreDataAsCSV()
    {
        sdf = new SimpleDateFormat("M_d_yyyy");
        DIR_NAME = System.getProperty("dirname");
        if (DIR_NAME == null)
        {
            DIR_NAME = "data";
        }
    }

    /**
     * Creates an instance of this class which registers with PollAPI as a
     * PollObserver.
     */
    public static void main(String args[])
    {
        StoreDataAsCSV observer = new StoreDataAsCSV();
        if (args.length != 0)
        {
            try
            {
                observer.register(args[0].trim());
            }
            catch (Exception e)
            {
                System.err.println("Exception occurred while trying to "
                                    + "register: " + e);
            }
        }
        else
        {
            System.out.println("Usage: java " + StoreDataAsCSV.class
                    + " <host name>");
            System.out.println("where,");
            System.out.println("       <host name> is the host where WebNMS"
                                + " server is running.");
        }
    }

    /**
     * Registers with PollAPI as a PollObserver for all PolledData.
     * @param   host    The host in which WebNMS server is running.
     * @throws  RemoteException         if registry could not be contacted or
     *                                  exporting an instance of this class
     *                                  fails.
     * @throws  NotBoundException       if PollAPI is not currently bound.
     * @throws  UnknownHostException    if the IP address of the host could not
     *                                  be found.
     * @throws  MalformedURLException   if the URL passed for looking up PollAPI
     *                                  is not properly formatted.
     */
    private void register(String host) throws RemoteException,
            NotBoundException, UnknownHostException, MalformedURLException
    {
        pollApi = (PollAPI) Naming.lookup("rmi://"
                + InetAddress.getByName(host).getHostName()
                + ":1099/PollAPI");
        UnicastRemoteObject.exportObject(this,0);
        System.out.println("Registering with PollAPI ...");
        pollApi.registerForAllData(this);
        System.out.println("Observer registered!");
    }

    /**
     * Overridden method of the <code>PollObserver</code> interface which gets
     * called when data is collected for any PolledData.
     * @param   colInfo     <code>CollectedInfo</code> object containing the 
     *                      data collected and the PolledData objects for which
     *                      the data was collected
     */
    public void dataUpdate(CollectedInfo colInfo)
    {
        Vector keys = colInfo.getKeys();
        if (keys == null)
        {
            return;
        }
        for (Enumeration e = keys.elements(); e.hasMoreElements(); )
        {
            String key = (String)e.nextElement();
            Hashtable instVal = colInfo.getInstanceValuePair(key);
            if (instVal == null)
            {
                continue;
            }
            PolledData pd = colInfo.getPolledData(key);
            String fileName = colInfo.getTableName(key);
            if (fileName.endsWith("%"))
            {
                fileName = fileName.substring(0, fileName.length() - 1)
                    + sdf.format(new Date()) + ".csv";
            }
            else 
            {
                fileName = fileName + ".csv";
            }
            try
            {
                saveData(fileName, pd.getId(), instVal, colInfo.getTime());
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }

    /**
     * Empty implementation, as this method is never called.
     */
    public void dataUpdate(String pollKey, long time, long value)
    {
    }

    /**
     * Empty implementation, as this method is never called.
     */
    public void dataUpdate(String pollKey, long time, String value)
    {
    }

    /**
     * Saves the data in CSV format in the file with the name
     * <code>fileName</code>.
     * @param   fileName    Name of the file in which data is stored.
     * @param   id          ID of the PolledData.
     * @param   instVal     Hashtable storing the instance of the PolledData as
     *                      key and the value polled as value.
     * @param   time        Time at which the value was polled as a long value.
     * @throws  IOException if an I/O error occurs.
     */
    private void saveData(String fileName, long id, Hashtable instVal,
            long time) throws IOException
    {
        File dir = new File(DIR_NAME);
        dir.mkdirs();
        File file = new File(dir.toString(), fileName);
        FileWriter fw = new FileWriter(file.toString(), true);
        PrintWriter pw = new PrintWriter(fw, true);
        if (file.length() == 0)
        {
            pw.println("POLLID,INSTANCE,TTIME,VAL");
        }
        for (Enumeration e = instVal.keys(); e.hasMoreElements(); )
        {
            String instance = (String) e.nextElement();
            Object value = instVal.get(instance);
            pw.println(id + "," + instance + "," + time + "," + value);
        }
        pw.close();
        fw.close();
    }
}
