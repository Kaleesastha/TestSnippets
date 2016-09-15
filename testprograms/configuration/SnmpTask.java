//Can be used for testing conf_gen_befo_tp.html
//To create & execute an SNMP task
import com.adventnet.management.config.*;
import com.adventnet.management.config.xml.*;
import com.adventnet.management.config.snmp.*;
import com.adventnet.security.authentication.*;

import java.rmi.Naming;

public class SnmpTask 
{
public static void main(String args[])
{
	TaskGenerator taskGen = new TaskGenerator();

    SnmpDevice[] devices = new SnmpDevice[1];
    devices[0] = new SnmpDevice("192.168.112.212", "161", "public");

    taskGen.setDevices(devices);

	for(int i=1; i<2; i++)
	{

		taskGen.setTaskName("MatrixTask3");
		taskGen.setNewTask(true); 
		taskGen.setSequential(true);
		taskGen.setOverwrite(true);
		taskGen.setRollback(true);
		taskGen.setProtocol("snmp");

		SnmpAttribute snmpAttribute = new SnmpAttribute("1.5.0", "sysName",(byte)4, "test");
		taskGen.addAttribute(snmpAttribute);
		//returns the taskXML
		String taskXML = taskGen.getTask();

	
		try
		{
		
			RMIAccessAPI rmiapi = (RMIAccessAPI)Naming.lookup("//localhost/RMIAccessAPI");
			ConfigAPIFactory configapi =  (ConfigAPIFactory) rmiapi.getAPI("root","public","ConfigurationAPI");
			DeviceConfigurationAPI deviceConfigurationAPI = configapi.getDeviceConfigurationAPI("root");
			deviceConfigurationAPI.executeTask(taskXML);
			Thread.sleep(100);
		}
		catch (Exception ex)
		{
			System.out.println("Exception "+ex.getMessage());
			ex.printStackTrace();
		} 
	}

}

}