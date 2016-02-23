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
				System.err.println("Intentionally waiting here in handleStartUp just to check whether STANDBY server waits only after we allow it to take over");
				Thread.sleep(45000);
				System.err.println("Wait over! Now it can take over!");
			}
			catch(Exception exp) {exp.printStackTrace();}
		}
		return 101;
	}
}
