package com.adventnet.nms.admin;
import com.adventnet.nms.admin.StartUpAction;
import com.adventnet.nms.util.*;
import com.adventnet.util.PortChecker;
import com.adventnet.nms.store.relational.RelationalAPI;
import java.sql.ResultSet;
import java.sql.Statement;
public class TestStartupActionImpl implements  StartUpAction
{
	public int handleStartUp(java.util.Map map)
	{
		/*CONTINUE = 101;
		  ABORT = 102;*/
		System.err.println("Map is::"+map);
		System.err.println("Map values is::"+map.values());
		if(map.get("primaryrole").equals("false"))
		{
			try{
				ResultSet rs = null;
				Statement stmt = null;
				RelationalAPI relapi = NmsUtil.relapi;
				System.err.println("BEFailOver - Executing Dummy Select");
				String select_dummy = "select HOSTADDRESS,NMSBEPORT,RMIREGISTRYPORT from BEFailOver where SERVERROLE = 'FAILED'";
				stmt = relapi.query(select_dummy,true);
				rs = stmt.getResultSet();
				PortChecker pc = new PortChecker();
				while(rs.next())
				{
					System.err.println("PRIMARY value exists!");
					String primaryIp = rs.getString(1);
					int rmiport = rs.getInt(2);
					int beport = rs.getInt(3);
					boolean rmi = pc.isPortFree(primaryIp,rmiport);
					boolean be = pc.isPortFree(primaryIp,beport);
					System.err.println("PRIMARY values are::\n primaryIp:"+primaryIp+"\nrmiport:"+rmiport+"\nbeport:"+beport);
					System.err.println("\nbe port occupied:"+be+"\nrmiport occupied:"+rmi);
					if(be || rmi)
					{
						System.err.println("Seems BE is still running! BEPORT or RMIPORT is up! So I will not start");
						return 102;
					}
					else
					{
						System.err.println("Seems BE is really shutdown! So I will start");
						return 101;
					}
				}
			}
			catch(Exception e){
				System.err.println("Error in StartUpAction!!!!");
				e.printStackTrace();
			}
		}
		return 101;
	}
}
/*DBParamsParser parse = DBParamsParser.getInstance(PureUtils.rootDir + "conf" + File.separator + "database_params.conf");
  String url = parse.getURL();
  String user = parse.getUserName();
  String driver = parse.getDriverName();
  String passwd = parse.getPassword();
  CONTINUE = 101;
  ABORT = 102;*/
