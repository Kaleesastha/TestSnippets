/* $Id: TestStorageAPI.java,v 1.2 2007/10/17 15:16:02 parimala Exp $ */
package br.com.asga.ems.asga4e1int.inventory;
import java.rmi.Naming;
import java.io.BufferedReader;
import com.adventnet.nms.persistence.StorageAPI;
import java.sql.Date;
import java.util.Vector;
import java.util.Properties;
import com.adventnet.nms.util.*;
import br.com.asga.ems.asga4e1int.inventory.Tabela;
public class TestStorageAPI
{

	static StorageAPI storageapi =null ;
	static String operation ="";
	static BufferedReader consoleInput  = null;
	public static void main(String args[]) throws Exception
	{
		try
		{
			if(args.length <3)
			{
				System.out.println("Usage : java test.TestStorageAPI <host-name> <rmiregistry-port> <operation-name(add || update || delete || get || getobjects)>"); //No I18N

				return;
			}
			String url="//"+args[0] +":"+  args[1] + "/StorageAPI";
			try
			{
				storageapi = (StorageAPI) Naming.lookup(url);
				System.out.println(NmsUtil.GetString("RMI Handle of StorageAPI is obtained"));//No I18N
			}
			catch(Exception e)
			{
				System.out.println ("Error in getting the handle StorageAPI"+e);  //No I18N
				return;
			}
			operation  = args[2];
			if(operation.equals("add") )
			{
				addObject(args[3]);
			}
			else if(operation.equals("get") )
			{
				getObject(args[3]);
			}
			else if(operation.equals("update") )
			{
				updateObject();
			}
			else if(operation.equals("delete") )
			{
				deleteObject();
			}
			else if(operation.equals("getobjects") )
			{
				getObjects();
			}
			else
			{
				System.out.println(NmsUtil.GetString("Invalid operation name "));//No I18N
				System.out.println(NmsUtil.GetString("Operations: add, update, get, delete , getobjects"));//No I18N
			}
		}
		catch (Exception e)
		{

			e.printStackTrace();
			System.exit(-1);
		}

	}
	private static void addObject(String name)throws Exception
	{
		Tabela object = new Tabela();
		object.setHost(name);//No I18N
		object.setInteiro(1000);
		storageapi.addObject("br.com.asga.ems.asga4e1int.inventory.Tabela",object); //No I18N
		System.out.println(NmsUtil.GetString("Object added into the Database")); //No I18N
	}
	private static void getObject(String criteria)throws Exception
	{
		Tabela object = null;
		Properties p = new Properties();
		p.put("id.host",criteria);
		Vector v = storageapi.getObjects("br.com.asga.ems.asga4e1int.inventory.Tabela",p); //No I18N
		System.out.println(NmsUtil.GetString("Object fetched from Database"));//No I18N
		if(v !=null)
		{
			System.out.println("vector size is "+v.size());
		}
		else
			System.out.println("vector is NULL ");
		/*System.out.println("Source  = "+object.getHost());//No I18N
		System.out.println("ID  = "+object.getId());//No I18N
		System.out.println("TimeValue  = "+object.getInteiro());//No I18N*/
	}
	private static void updateObject()throws Exception
	{

		Tabela object = null;
		long c=1;
		object=(Tabela)storageapi.getObject(c,"br.com.asga.ems.asga4e1int.inventory.Tabela"); //No I18N
		// changing the value of dayof event
		storageapi.updateObject(object,object.getId());
		System.out.println(NmsUtil.GetString("Object Updated "));//No I18N
	}
	private static void deleteObject()throws Exception
	{

		Tabela object = null;
		long c=1;
		object=(Tabela)storageapi.getObject(c,"br.com.asga.ems.asga4e1int.inventory.Tabela"); //No I18N
		// changing the value of dayof event
		storageapi.deleteObject(object,object.getId());
		System.out.println(NmsUtil.GetString("Object deleted "));//No I18N
	}
	private static void getObjects()throws Exception
	{
		Tabela object = new Tabela();
		object.setHost("remotemachine");//No I18N
		object.setInteiro(1000);
		storageapi.addObject("br.com.asga.ems.asga4e1int.inventory.Tabela",object); //No I18N
		System.out.println(NmsUtil.GetString("Object added into the Database"));//No I18N
		Properties p=new Properties();
		p.put("source","remotemachine");	//No I18N
		Vector objects = storageapi.getObjects("br.com.asga.ems.asga4e1int.inventory.Tabela",p); //No I18n

	}

}











