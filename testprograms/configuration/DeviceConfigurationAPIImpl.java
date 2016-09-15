/* $Id: DeviceConfigurationAPIImpl.java,v 1.2 2003/06/09 07:31:09 rajalakshmytr Exp $
 *
 * File Name      : DeviceConfigurationAPIImpl.java
 * Description    : This file provides implementation for executing all DeviceConfigurationAPI interface methods
 * Other Info     :
 *
 * USAGE          : java DeviceConfigurationAPIImpl
 * Parameter Desc : 
 *               
 * Owner Name     : 
 * Change History(Author Date(dd-mm-yyy) and Description of methods added/modifed/deleted):
 */

import com.adventnet.management.config.*;
import com.adventnet.management.config.snmp.*;
import com.adventnet.nms.util.*;

import java.rmi.Naming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

public class DeviceConfigurationAPIImpl
{

    public static void main(String a[])
    {
        try 
        { 
            BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("\nEnter the name of the host to connect to :: ");
            String hostName = buf.readLine();

            System.out.print("\nEnter the rmi port to connect to :: ");
            int port = Integer.parseInt(buf.readLine());
            String rmiString = "rmi://" + hostName + ":" + port + "/ConfigurationAPI";
            ConfigAPIFactory  apiFactory = (ConfigAPIFactory) Naming.lookup(rmiString);
            if(apiFactory!=null)
            {
                System.out.print("\nConfigurationAPI handle retrieved successfully\n");
            }
            else
            {
                System.out.print("\nCould not retrieve ConfigurationAPI handle\n");
                return;
            }

            System.out.print("\nEnter the user for which config operations should be carried out");
            System.out.print("\nThe method getDeviceConfigurationAPI(userName) will be executed :: ");
            String userName = buf.readLine();
            DeviceConfigurationAPI deviceAPI = apiFactory.getDeviceConfigurationAPI(userName); 
            if(deviceAPI!=null)
            {
                System.out.print("\nDeviceConfigurationAPI handle retrieved successfully\n");
            }
            else
            {
                System.out.print("\nCould not retrieve DeviceConfigurationAPI handle\n");
                return;
            }

            DeviceAuditAPI auditAPI = apiFactory.getDeviceAuditAPI(userName); 

            DeviceConfigurationAPIImpl devconfapi = new DeviceConfigurationAPIImpl();

            String choice = "";
            while(true) 
            {
                System.out.print("\nEnter the method name or help or stop -->> ");
                choice = buf.readLine();

                if(choice.equalsIgnoreCase("getTask")) 
                {
                    devconfapi.getTask(deviceAPI);
                }
                else if(choice.equalsIgnoreCase("getDevices")) 
                {
                    devconfapi.getDevices(deviceAPI);
                }
                else if(choice.equalsIgnoreCase("getStatus")) 
                {
                    devconfapi.getStatus(deviceAPI);
                }
                else if(choice.equalsIgnoreCase("removeTask")) 
                {
                    devconfapi.removeTask(deviceAPI);
                }
                else if(choice.equalsIgnoreCase("stop")) 
                {
                    break;
                }
                else if(choice.equalsIgnoreCase("help"))
                {
                    devconfapi.help();
                }
                else 
                {
                    System.out.println("wrong choice");
                }
            }
            System.out.println("BYE");
        } 
        catch(Exception e) 
        { 
            
            e.printStackTrace(); 
        } 
    }

    private void help()
    {
        System.out.println("\nMethods available for testing");
        System.out.println("getTask");
        System.out.println("getDevices");
        System.out.println("getStatus");
        System.out.println("removeTask");
    }

    private void getTask(DeviceConfigurationAPI deviceAPI)
    {
        try 
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("METHOD TO GET TASK");
        
            System.out.print("Enter task to be retrieved (Eg:demoTask) :: ");
            param = br.readLine();

            String task = deviceAPI.getTask(param);
            System.out.println("Task got successfully :: " + task);
        } 
        catch(Exception e) 
        { 
            System.out.println("Exception thrown while getting task");
            e.printStackTrace(); 
        } 
    }

    private void getDevices(DeviceConfigurationAPI deviceAPI)
    {
        try 
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("METHOD TO GET DEVICES");
        
            System.out.print("Enter task name (Eg:demoTask) :: ");
            param = br.readLine();

            Vector devVec = deviceAPI.getDevices(param);
            System.out.println("Devices retrieved successfully");
            if((devVec != null)&& (devVec.size()>0))
            {
                for(int i=0;i<devVec.size();i++)
                {
                    System.out.println("\n" + (String)devVec.get(i));
                }
            }
            else
            {
                System.out.println("No devices returned: task is not currently under execution");
            }
        } 
        catch(Exception e) 
        { 
            System.out.println("Exception thrown while getting devices task");
            e.printStackTrace(); 
        } 
    }

    private void getStatus(DeviceConfigurationAPI deviceAPI)
    {
        try 
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("METHOD TO GET STATUS");
        
            System.out.println("Enter the method to be executed 1 or 2 :: ");
            System.out.println("1. getStatus(taskName)");
            System.out.println("2. getStatus(taskName,device)");

            while(true)
            {
                param = br.readLine();

                if(param.equalsIgnoreCase("1")) 
                {
                    String taskName = "";
                    System.out.print("Enter task name (Eg:demoTask) :: ");
                    taskName = br.readLine();
                    String status = deviceAPI.getStatus(taskName);
                    System.out.println("\nStatus retrieved successfully :: " + status);
                    break;
                }
                else if(param.equalsIgnoreCase("2"))
                {
                    String taskName = "";
                    System.out.print("Enter task name (Eg:demoTask) :: ");
                    taskName = br.readLine();

                    String device = "";
                    System.out.print("Enter name of the device assigned to this task for configuration :: ");
                    device = br.readLine();

                    String status = deviceAPI.getStatus(taskName,device);
                    System.out.println("\nStatus retrieved successfully :: " + status);
                    break;
                }
                else
                {
                    System.out.println("Enter 1 or 2 :: ");
                }
            }
        } 
        catch(Exception e) 
        { 
            System.out.println("Exception thrown while getting task");
            e.printStackTrace(); 
        } 
    }

    private void removeTask(DeviceConfigurationAPI deviceAPI)
    {
        try 
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("METHOD TO REMOVE TASK");
        
            System.out.print("Enter task to be removed (Eg:demoTask) :: ");
            param = br.readLine();

            deviceAPI.removeTask(param);
            System.out.println("Task deleted successfully");
        } 
        catch(Exception e) 
        { 
            System.out.println("Exception thrown while deleting task");
            e.printStackTrace(); 
        } 
    }
}
