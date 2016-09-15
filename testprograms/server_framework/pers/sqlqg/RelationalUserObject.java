package com.adventnet.nms.store.relational;  
import java.sql.*;  
import java.util.*;  
import com.adventnet.nms.store.*;  
import com.adventnet.management.transaction.PreparedStatementWrapper;  
import test.*;   
 
public class RelationalUserObject extends CommonRelationalObject 
{	 
	static boolean initialized = false ;  
	static int psIDForGet;  
	static int psIDForAdd;  
	static int psIDForDelete;  
	static int psIDForUpdate;  
	 
	public RelationalUserObject()  
	{		 
	} 
	public void init(RelationalAPI relapi)  
	{		 
		super.init(relapi);  
		if (initialized) return;  
		String prepareSqlStringForGet =  "select * from UserObject whe"
		 +")";  
		 
		psIDForGet = relapi.getPreparedStatementID  
		( prepareSqlStringForGet );  
		 
		String prepareSqlStringForAdd =  "insert into UserObject values ( "
		 +" ?,"
		 +" ?,"
		 +" ?"
		 +" )";  
		 
		psIDForAdd = relapi.getPreparedStatementID  
		( prepareSqlStringForAdd );  
		 
		String prepareSqlStringForUpdate =  "update UserObject set " + RelationalUtil.getAlias( "source" ) + " = ?," + RelationalUtil.getAlias( "id" ) + " = ?," + RelationalUtil.getAlias( "timeValue" ) + " = ?"
		 +" whe"
		 +")";  
		 
		psIDForUpdate = relapi.getPreparedStatementID  
		( prepareSqlStringForUpdate );  
		 
		String prepareSqlStringForDelete =  "delete from UserObject whe"
		 +")";  
		 
		psIDForDelete = relapi.getPreparedStatementID  
		( prepareSqlStringForDelete );  
		initialized=true;  
	} 
	 
	public Object fillPropertiesForObject(Properties p)  
	{		 
		UserObject obj = new UserObject();  
		obj.setSource(p.getProperty("source"));  
		obj.setId(Integer.parseInt( p.getProperty("id")));  
		obj.setTimeValue(Long.parseLong( p.getProperty("timeValue")));  
		 
		return obj;  
	} 
	 
	public Vector getStatementsForGet(String args)  
	{		 
		Vector v = new Vector();  
		PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForGet);  
		PreparedStatement preparedStatementForGet = ps.getPreparedStatement();  
		if (preparedStatementForGet!=null)  
		{			 
			try  
			{				 
				StringTokenizer strTok = new StringTokenizer(args,"\t");  
				int index = 1;  
				while(strTok.hasMoreTokens())  
				{					 
					preparedStatementForGet.setString(index,strTok.nextToken()); 
					index++;  
				} 
				v.addElement(ps);  
			} 
			catch (SQLException sqle)  
			{				 
				System.err.println("Exception setting prepared statement for "  
				+" UserObject "+args+":"+sqle);  
			} 
		} 
		return v;  
	} 
	public Vector getStatementsForAdd(Object dbObj)  
	{		 
		UserObject obj = (UserObject)dbObj;  
		Vector v = new Vector();  
		PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForAdd);  
		PreparedStatement preparedStatementForAdd = ps.getPreparedStatement();  
		if (preparedStatementForAdd!=null)  
		{			 
			try  
			{				 
				 
				preparedStatementForAdd.setString(1,db2str(obj.getSource()));

				preparedStatementForAdd.setInt(2,obj.getId());

				preparedStatementForAdd.setLong(3,obj.getTimeValue());

				
				v.addElement(ps);  
			} 
			catch (SQLException sqle)  
			{				 
				System.err.println("Exception setting preparedStatement for "  
				+ " UserObject :"+sqle);  
				sqle.printStackTrace();  
			} 
		} 
		return v;  
	} 
	public Vector getStatementsForDelete(Object dbObj)  
	{		 
		UserObject obj = (UserObject)dbObj;  
		Vector v =new Vector();  
		PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForDelete);  
		PreparedStatement preparedStatementForDelete = ps.getPreparedStatement();  
		if (preparedStatementForDelete!=null)  
		{			 
			try  
			{				 
				 
				v.addElement(ps);  
			} 
			catch (SQLException sqle)  
			{				 
				System.err.println("Exception setting preparedStatement for "  
				+" UserObject :"+sqle);  
			} 
		} 
		return v;  
	} 
	public Vector getStatementsForUpdate(Object dbObj)  
	{		 
		UserObject obj = (UserObject)dbObj;  
		Vector v = new Vector();  
		PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForUpdate);  
		PreparedStatement preparedStatementForUpdate = ps.getPreparedStatement();  
		if (preparedStatementForUpdate!=null)  
		{			 
			try  
			{				 
				preparedStatementForUpdate.setString(1,db2str(obj.getSource()));

				preparedStatementForUpdate.setInt(2,obj.getId());

				preparedStatementForUpdate.setLong(3,obj.getTimeValue());

				 
				v.addElement(ps);  
			} 
			catch (SQLException sqle)  
			{				 
				System.err.println("Exception setting preparedStatement for "  
				+" UserObject :"+sqle);  
				sqle.printStackTrace();  
			} 
		} 
		return v;  
	} 
}