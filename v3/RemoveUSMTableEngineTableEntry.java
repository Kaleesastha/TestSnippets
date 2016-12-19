package test;

import com.adventnet.nms.util.*;
import java.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.db.PopulateUserTable;
import java.rmi.server.RMISocketFactory;
import com.adventnet.nms.netwatch.DiscoveryAPI;

import com.adventnet.management.log.Log;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.snmp2.usm.USMUserTable;
import com.adventnet.nms.util.PureServerUtils;

public class RemoveUSMTableEngineTableEntry implements RunProcessInterface{
        public void callMain(String args[]){
                try{
			SnmpEngineTable engTable=PureServerUtils.getSnmpTarget().getSnmpEngineTable();
			SnmpEngineEntry engEntry=engTable.getEntry("192.168.57.135",8003); //Ipaddress & SNMP Port got by obj.getIpAddress(), obj.getSnmpport()
			byte[] id=null;
			if(engEntry!=null)
			{
				engTable.removeEntry(engEntry);
				id=engEntry.getEngineID();
			}
			else
			{
				System.err.println(NmsUtil.GetString("Exception while getting Engine Entry For 192.168.57.135 "));//NO I18N
			}
			USMUserTable usmTable=null;
			if(id!=null)
			{
				usmTable=PureServerUtils.getSnmpTarget().getUSMTable();
				usmTable.removeEntry("privUser".getBytes(),id); //username got by obj.getUserName()
			}
			NmsLogMgr.TOPOUSER.log("Successfully removed Old Tables: engtable - "  + engTable +" engid - "+ id + " usm -"+ usmTable, Log.SUMMARY);//NO I18N
                }catch(Exception e)  {
                        e.printStackTrace();
                }
        }
        public boolean isInitialized(){return true;}
        public void shutDown() {}

}
