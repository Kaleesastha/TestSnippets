

package com.adventnet.management.policydb;

import com.adventnet.nms.store.CommonDBStore;
import com.adventnet.nms.store.PersistenceAPI;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.management.policydb.CommonPolicyAPI;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.management.policydb.PolicyObject;
import java.util.Properties;


public class TestCh1 extends CommonDBStore implements RunProcessInterface,PersistenceAPI
{
	CommonDBStore c = null;
	
	public void callMain(String args[])
	{
		new TestCh1();
	}

	public TestCh1()

	{
		try
		{
			c = new CommonPolicyAPI();
		}
		catch(Exception r)
		{
			r.printStackTrace();
		}
		try
		{
			Thread.sleep(30000);
		}
		catch(Exception u)
		{
			u.printStackTrace();
		}
		testcase022();
		testcase023();
		testcase024();
		testcase025();
		testcase026();
//		add();
//		get();
//		update();
//			System.out.println(" 3 over -- delete");
//		delete();
	}

	public boolean isInitialized()
	{
		return true;
		}
	
	public void setPersistenceAPI()
	{
	}
	
	
	public void shutDown()
	{
	}

	
	public void testCase026()

	{
		try
		{
		Thread.sleep(10000);
		c.deleteObject("破不");
		System.out.println("Rajeshd Object deleted");
		System.out.println("TESTCASE 026 OBJECT DELETED!");
		}
		catch(Exception i)
		{
			i.printStackTrace();
		}
	}

	public void testCase25()

	{
		try
		{
			Thread.sleep(5000);
			PolicyObject po = (PolicyObject)c.getObject("不就");
			po.setStatus(2);
			//c.updateObject(po);
			Properties p = new Properties();
            p.setProperty("status",4+"");
			//po.setProperties(p);
			c.updateObject(po);
			System.out.println("Rajeshd Object updated");
			System.out.println("TESTCASE 25 OBJECT UPDATED");
		}
		catch(Exception r)
		{
			r.printStackTrace();
		}
	}

	public void testCase024()
	{
		try
		{
			PolicyObject po = (PolicyObject)c.getObject("撒没破不就");
			System.out.println("TESTCASE 024 DISPLAYING PROPERTIES ");
			System.out.println("RAJESHD PROPRORPRP : "+po.getProperties());
		}
		catch(Exception r)
		{
			r.printStackTrace();
		}
	}

	private void testCase022()

	{
		try
		{
			PolicyObject m = new PolicyObjectImpl();
			m.setName("不就");
			m.setGroupName("是批");
			m.setStatus(3);
			Properties p = new Properties();
            p.setProperty("name",m.getName());
            p.setProperty("groupName",m.getGroupName());
	        p.setProperty("status","3");
			p.setProperty("period" , "300");

//                prepared.setString(5,db2str(p.getProperty("policyObjectCustomizer")));
//                prepared.setString(6,db2str(p.getProperty("helpURL")));
			m.setProperties(p);

			c.addObject(m);
			System.out.println("TESTCASE 022 ADDED OBJECT");
		}
		catch(Exception p)
		{
			p.printStackTrace();
		}
	}

	private void testCase023()
	{
		try
		{

			PolicyObject m1 = new PolicyObjectImpl();
			m1.setName("aa破sdd就不");
			m1.setGroupName("adf批地是批");
			m1.setStatus(1);
			Properties p1 = new Properties();
            p1.setProperty("name",m1.getName());
            p1.setProperty("groupName",m1.getGroupName());
   	        p1.setProperty("status","3");
			p1.setProperty("period" , "300");

//                prepared.setString(5,db2str(p.getProperty("policyObjectCustomizer")));
//                prepared.setString(6,db2str(p.getProperty("helpURL")));
			m1.setProperties(p1);

			c.addObject(m1);

			System.out.println("TESTCASE 23 OBJECT ADDED");
		}
		catch(Exception i)
		{
			i.printStackTrace();
		}
	}
	
/*
			PolicyObject m2 = new PolicyObjectImpl();
			m2.setName("破不");
			m2.setGroupName("f批地是批");
			m2.setStatus(1);
			Properties p2 = new Properties();
                     p2.setProperty("name",m2.getName());
                     p2.setProperty("groupName",m2.getGroupName());
		       p2.setProperty("status","3");
			p2.setProperty("period" , "300");

//                prepared.setString(5,db2str(p.getProperty("policyObjectCustomizer")));
//                prepared.setString(6,db2str(p.getProperty("helpURL")));
			m2.setProperties(p2);

			c.addObject(m2);
*/
//			System.out.println("Rajeshd Obj added");

}


