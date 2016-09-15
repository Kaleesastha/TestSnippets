package test;

import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.util.NmsUtil;
import java.sql.*; 


import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.TopoAPI;

public class DBTrans_67_76 implements RunProcessInterface , Runnable
{
	TransactionAPI t = null;
	RelationalAPI r = null;
	TopoAPI topo=null;
	boolean testCase073=false;
	boolean testCase0731=false;
	boolean testCase074=false;
	boolean testCase0741=false;
	boolean testCase075=false;
	boolean testCase0751=false;
	boolean testCase0761=false;	

	public void callMain(String args[])
	{
		r = NmsUtil.relapi;
		t = r.getTransactionAPI();
		while(topo==null)
		{
			try
			{
				topo = (TopoAPI)NmsUtil.getAPI("TopoAPI");
				Thread.sleep(100);
			}
			catch(Exception excp)
			{
			}
		}
		System.out.println("TopoAPI Handle "+topo);
        testCase067();
        testCase067_01();
        testCase068();
        testCase069();
        testCase070();
        testCase071();
        testCase072();
        testCase073();
        testCase074();
        testCase075();
        testCase076();
	}

	public void shutDown()
	{
	}

	public boolean isInitialized()
	{
		return true;
	}

	private void testCase067()
	{
		try
		{
			
			t.begin(50);
			{
                Thread.currentThread().sleep(1000);
                ConnectionPool cp1 = t.getConnectionPool();
                System.out.println("Connection Pool " + cp1);
                Connection conn = cp1.getConnection();
                System.out.println("Connection : "+ conn);
                System.out.println("DB-TRANS-067: FAILED");
			}
			t.commit();
			System.out.println("DB-TRANS-067: FAILED -- after commit");
		}
		catch(Exception excp)
		{
			System.out.println("DB-TRANS-067 PASSED -- inside excp");
			excp.printStackTrace();
		}
	}

    public void testCase067_01()
    {
		try
		{
			t.begin();
			ManagedObject mo = new ManagedObject();
			mo.setName("Muppi67_01");
			System.out.println("Added-067_01 "+topo.addObject(mo));
			Object obj1=topo.getByName("Muppi67_01");
			try
			{
				t.rollback();
			}
			catch(Exception excp)
			{
				System.out.println("TimeOut for 067_01");
			}
			Object obj2=topo.getByName("Muppi67_01");
			if(obj1!=null && obj2==null)
			{
				System.out.println("DB-TRANS-067_01 PASSED");
			}
			else
			{
				System.out.println("DB-TRANS-067_01 FAILED objects are "+obj1+" "+obj2);
			}
		}
		catch(Exception excp)
		{
			System.out.println("DB-TRANS-067_01 FAILED -- inside excp");
			excp.printStackTrace();
		}
    }

	private void testCase068()
	{
		try
		{
			t.begin();
			ManagedObject mo = new ManagedObject();
			mo.setName("Muppi68");
			System.out.println("Added-068 "+topo.addObject(mo));
			Object obj1=topo.getByName("Muppi68");
			Thread.sleep(25000);
			try
			{
				t.commit();
			}
			catch(Exception excp)
			{
				System.out.println("TimeOut for 068");
			}
			Object obj2=topo.getByName("Muppi68");
			if(obj1!=null && obj2==null)
			{
				System.out.println("DB-TRANS-068 PASSED");
			}
			else
			{
				System.out.println("DB-TRANS-068 FAILED objects are "+obj1+" "+obj2);
			}
		}
		catch(Exception excp)
		{
			System.out.println("DB-TRANS-068 FAILED -- inside excp");
			excp.printStackTrace();
		}
	}
	private void testCase069()
	{
		try
		{
			ManagedObject mo = new ManagedObject();
			mo.setName("Muppi69");
			System.out.println("Added-069 "+topo.addObject(mo));
			t.begin();
			topo.deleteObject(mo);
			Object obj1=topo.getByName("Muppi69");
            try
            {
                t.rollback();
            }catch (Exception e){}
			Object obj2=topo.getByName("Muppi69");
			if(obj1==null && obj2!=null)
			{
				System.out.println("DB-TRANS-069 PASSED");
			}
			else
			{
				System.out.println("DB-TRANS-069 FAILED - Objects are "+obj1+" "+obj2);
			}
		}
		catch(Exception excp)
		{
			System.out.println("DB-TRANS-069 FAILED -- inside excp");
			excp.printStackTrace();
		}
	}
	private void testCase070()
	{
		try
		{
			ManagedObject mo = new ManagedObject();
			mo.setName("Muppi70");
			System.out.println("Added-070 "+topo.addObject(mo));
			t.begin();
			topo.deleteObject(mo);
			Object obj1=topo.getByName("Muppi70");
			Thread.sleep(25000);
			try
			{
				t.commit();
			}
			catch(Exception excp)
			{}			
			Object obj2=topo.getByName("Muppi70");
			if(obj1==null && obj2!=null)
			{
				System.out.println("DB-TRANS-070 PASSED");
			}
			else
			{
				System.out.println("DB-TRANS-070 FAILED objects are "+obj1+" "+obj2);
			}
		}
		catch(Exception excp)
		{
			System.out.println("DB-TRANS-070 FAILED -- inside excp");
			excp.printStackTrace();
		}
	}
	private void testCase071()
	{
		try
		{
			ManagedObject mo = new ManagedObject();
			mo.setName("Muppi71");
			System.out.println("Added-071 "+topo.addObject(mo));
			System.out.println("Managed before transaction begin "+mo.getManaged());
			t.begin();
			mo=(ManagedObject)topo.getByName("Muppi71");
			mo.setManaged(false);
			topo.updateObject(mo);
			Object obj1=topo.getByName("Muppi71");
			boolean managed1=mo.getManaged();
            try
            {
                t.rollback();
            }catch (Exception e){}
			mo=(ManagedObject)topo.getByName("Muppi71");
			boolean managed2=mo.getManaged();
			if(!managed1 && managed2)
			{
				System.out.println("DB-TRANS-071 PASSED");
			}
			else
			{
				System.out.println("DB-TRANS-071 FAILED managed1 and managed 2 are "+managed1+" "+managed2);
			}
		}
		catch(Exception excp)
		{
			System.out.println("DB-TRANS-071 FAILED -- inside excp");
			excp.printStackTrace();
		}
	}
	private void testCase072()
	{
        boolean managed1 = false;
		try
		{
			ManagedObject mo = new ManagedObject();
			mo.setName("Muppi72");
			System.out.println("Added-072 "+topo.addObject(mo));
			t.begin();

			mo=(ManagedObject)topo.getByName("Muppi72");
			mo.setManaged(false);
			topo.updateObject(mo);
			mo=(ManagedObject)topo.getByName("Muppi72");
			managed1=mo.getManaged();
			try
			{
				t.rollback();
			}
			catch(Exception excp){}
            mo=(ManagedObject)topo.getByName("Muppi72");
            boolean managed2=mo.getManaged();
			if(!managed1 && managed2)
			{
				System.out.println("DB-TRANS-072 PASSED");
			}
            else
            {
                System.out.println("DB-TRANS-072 FAILED managed1 and managed2 are "+managed1 + " " + managed2);
            }
		}
		catch(Exception excp)
		{
            System.out.println("DB-TRANS-072 FAILED");
            excp.printStackTrace();
		}
	}
	private void testCase073()
	{
		try
		{
			t.begin();
			ManagedObject mo = new ManagedObject();
			mo.setName("Muppi73");
			System.out.println("Added-073 "+topo.addObject(mo));
			Object obj1=topo.getByName("Muppi73");
			new CallGetByName("73").start();
            try
            {
                t.rollback();
            }catch (Exception e){}
			new CallGetByName("731").start();
			Thread.sleep(1500);
			System.out.println("Object for 073 is "+obj1);
			if(obj1!=null && testCase073 && testCase0731)
			{
				System.out.println("DB-TRANS-073 PASSED");
			}
			else
			{
				System.out.println("DB-TRANS-073 PASSED obj and booleans are "+obj1+" "+testCase073+" "+testCase0731); 
			}
		}
		catch(Exception excp)
		{
			System.out.println("DB-TRANS-073 FAILED -- inside excp");
			excp.printStackTrace();
		}
	}
	private void testCase074()
	{
		try
		{
			ManagedObject mo = new ManagedObject();
			mo.setName("Muppi74");
			System.out.println("Added-074 "+topo.addObject(mo));
			t.begin();
			topo.deleteObject(mo);
			Object obj1=topo.getByName("Muppi74");
			new CallGetByName("74").start();
            try
            {
                t.rollback();
            }catch (Exception e){}
			new CallGetByName("741").start(); 
			Thread.sleep(1500);
			if(obj1==null && testCase074 && testCase0741)
			{
				System.out.println("DB-TRANS-074 PASSED");
			}
			else
			{
				System.out.println("DB-TRANS-074 FAILED obj and booleans are "+obj1+" "+testCase074+" "+testCase0741);
			}
		}
		catch(Exception excp)
		{
			System.out.println("DB-TRANS-074 FAILED -- inside excp");
			excp.printStackTrace();
		}
	}
	private void testCase075()
	{
		try
		{
			ManagedObject mo = new ManagedObject();
			mo.setName("Muppi75");
			System.out.println("Added-075 "+topo.addObject(mo));
			System.out.println("Before t.begin -075 added "+topo.getByName("Muppi75"));
			t.begin();
			topo.deleteObject(mo);
			new CallGetByName("75").start();
			Thread.sleep(1500);
			t.commit();
			new CallGetByName("751").start();
			Thread.sleep(1500);

			if(testCase075 && testCase0751)
			{
				System.out.println("DB-TRANS-075 PASSED");
			}
			else
			{
				System.out.println("DB-TRANS-075 FAILED booleans are "+testCase075+" "+testCase0751);
			}
		}
		catch(Exception excp)
		{
			System.out.println("DB-TRANS-075 FAILED -- inside excp");
			excp.printStackTrace();
		}
	}

	private void testCase076()
	{
		try
		{
			ManagedObject mo = new ManagedObject();
			mo.setName("Muppi76");
			System.out.println("Added-076 "+topo.addObject(mo));
			t.begin();
			mo=(ManagedObject)topo.getByName("Muppi76");
			mo.setDisplayName("Muppi761");
			mo.setManaged(false);
			topo.updateObject(mo);
			new CallGetByName("761").start();
			Thread.sleep(500);
			t.commit();
			mo=(ManagedObject)topo.getByName("Muppi76");
			if(testCase0761 && !mo.getManaged() && mo!=null)
			{
				System.out.println("DB-TRANS-076 PASSED");
			}
			else
			{
                System.out.println("testCase0761 : " + testCase0761);
                System.out.println("Managed State : " + mo.getManaged());
                System.out.println("MO is : " + mo);
				System.out.println("DB-TRANS-076 FAILED");
			}
		}
		catch(Exception excp)
		{
			System.out.println("DB-TRANS-076 FAILED -- inside excp");
			excp.printStackTrace();
		}
	}

	public void run()
	{
		try
		{
			String ss = Thread.currentThread().getName();
			t.begin();
			for(int i = 1;1<100;i++)
			{
				System.out.println("PRINT FROM RAJESHD PROCESS BY THREAD : "+ss+" NO. :"+i);
				Thread.sleep(1000);
			}
		}
		catch(Exception r)
		{
			r.printStackTrace();
		}
		finally
		{
			try
			{
				t.commit();
			}
			catch(Exception excp)
			{}
		}
	}

	private class CallGetByName extends Thread
	{
		String id=null;
		String id1=null;
		CallGetByName(String id)
		{
			this.id=id;
			if(id.endsWith("1"))
			{
				id1=id.substring(0,id.length()-1);
			}
			else
			{
				id1=id;
			}
		}
		public void run()
		{
			try
			{
				//t.begin();
				String name = "Muppi"+id1;
				System.out.println("getby name inside thread is "+name+" and id is "+id);
				Object obj = topo.getByName(name);
				//t.commit();
				if(id.equals("73") && obj==null)
				{
					testCase073=true;
				}
				if(id.equals("731") && obj==null)
				{
					testCase0731=true;
				}
				if(id.equals("74") && obj!=null)
				{
					testCase074=true;
				}
				if(id.equals("741") && obj!=null)
				{
					testCase0741=true;
				}
				if(id.equals("75") && obj!=null)
				{
					testCase075=true;
				}
				if(id.equals("751") && obj==null)
				{
					testCase0751=true;
				}
				if(id.equals("761") && obj!=null)
				{
					testCase0761=true;
				}
				System.out.println("Obj for "+id+" "+obj);
			}
			catch(Exception excp)
			{
				excp.printStackTrace();
			}
		}
	}
}
