package com.adventnet.nms.store.relational; 
import java.sql.*; 
import java.util.*; 
import com.adventnet.nms.store.*; 
import com.adventnet.management.transaction.PreparedStatementWrapper; 
import com.adventnet.nms.topodb.*;  

public class RelationalServiceObject extends RelationalTopoObject
{	
	static boolean initialized = false ; 
	static int psIDForGet; 
	static int psIDForAdd; 
	static int psIDForDelete; 
	static int psIDForUpdate; 
	
	public RelationalServiceObject() 
	{		
	}
	public void init(RelationalAPI relapi) 
	{		
		super.init(relapi); 
		if (initialized) return; 
		String prepareSqlStringForGet =  "select * from ServiceObject where ( "
		 +"( " + RelationalUtil.getAlias( "name" ) + " = ? )"
		 +")"; 
		
		psIDForGet = relapi.getPreparedStatementID 
		( prepareSqlStringForGet ); 
		
		String prepareSqlStringForAdd =  "insert into ServiceObject values ( "
		 +" ?,"
		 +" ?,"
		 +" ?"
		 +" )"; 
		
		psIDForAdd = relapi.getPreparedStatementID 
		( prepareSqlStringForAdd ); 
		
		String prepareSqlStringForUpdate =  "update ServiceObject set " + RelationalUtil.getAlias( "name" ) + " = ?," + RelationalUtil.getAlias( "serviceType" ) + " = ?," + RelationalUtil.getAlias( "port" ) + " = ?"
		 +" where ( "
		 +"( " + RelationalUtil.getAlias( "name" ) + " = ? )"
		 +")"; 
		
		psIDForUpdate = relapi.getPreparedStatementID 
		( prepareSqlStringForUpdate ); 
		
		String prepareSqlStringForDelete =  "delete from ServiceObject where ( "
		 +"( " + RelationalUtil.getAlias( "name" ) + " = ? )"
		 +")"; 
		
		psIDForDelete = relapi.getPreparedStatementID 
		( prepareSqlStringForDelete ); 
		initialized=true; 
	}
	
	public boolean fillProperties(DBInterface dbObj, Properties p) 
	{		
		ServiceObject obj = (ServiceObject)dbObj; 
		if ( (p==null) || (p.getProperty("name")==null) )
		{			
			return false; 
		}
		obj.setName(p.getProperty("name"));  
		
		obj.setProperties(p); 
		return true; 
	}
	
	public Vector getStatementsForGet(String[] args) 
	{		
		Vector v =super.getStatementsForGet(args); 
		PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForGet); 
		PreparedStatement preparedStatementForGet = ps.getPreparedStatement(); 
		if (preparedStatementForGet!=null) 
		{			
			try 
			{				
				preparedStatementForGet.setString(1,args[0]);  
				
				v.addElement(ps); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting prepared statement for " 
				+" ServiceObject "+args[0]+":"+sqle); 
			}
		}
		return v; 
	}
	public Vector getCustomStatements(DBInterface dbObj,String[] args) 
	{		
		//User to fill custom sql statements if any required 
		return super.getCustomStatements(dbObj,args); 
	}
	public void fillCustomProperties(DBInterface dbObj,ResultSet rs,String str) 
	{		
		super.fillCustomProperties(dbObj,rs,str); 
	}
	public void fillCustomProperties(DBInterface dbObj,ResultSet rs,PreparedStatement prstmt) 
	{		
		super.fillCustomProperties(dbObj,rs,prstmt); 
	}
	public void fillCustomProperties(DBInterface dbObj,ResultSet rs,int id) 
	{		
		super.fillCustomProperties(dbObj,rs,id); 
	}
	public Vector getStatementsForAdd(DBInterface dbObj) 
	{		
		ServiceObject obj = (ServiceObject)dbObj; 
		Vector v =super.getStatementsForAdd(dbObj); 
		PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForAdd); 
		PreparedStatement preparedStatementForAdd = ps.getPreparedStatement(); 
		if (preparedStatementForAdd!=null) 
		{			
			Properties p = obj.getProperties(); 
			try 
			{				
				preparedStatementForAdd.setString(1,db2str(p.getProperty("name")));

				preparedStatementForAdd.setString(2,db2str(p.getProperty("serviceType")));

				preparedStatementForAdd.setInt(3,Integer.parseInt(p.getProperty("port")));

				
				v.addElement(ps); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting preparedStatement for " 
				+ " ServiceObject :"+sqle); 
				sqle.printStackTrace(); 
			}
		}
		return v; 
	}
	public Vector getStatementsForDelete(DBInterface dbObj) 
	{		
		ServiceObject obj = (ServiceObject)dbObj; 
		Vector v =super.getStatementsForDelete(dbObj); 
		PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForDelete); 
		PreparedStatement preparedStatementForDelete = ps.getPreparedStatement(); 
		if (preparedStatementForDelete!=null) 
		{			
			try 
			{				
				preparedStatementForDelete.setString(1,db2str(obj.getName()));
				
				v.addElement(ps); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting preparedStatement for " 
				+" ServiceObject :"+sqle); 
			}
		}
		return v; 
	}
	public Vector getStatementsForUpdate(DBInterface dbObj) 
	{		
		ServiceObject obj = (ServiceObject)dbObj; 
		Vector v =super.getStatementsForUpdate(dbObj); 
		PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForUpdate); 
		PreparedStatement preparedStatementForUpdate = ps.getPreparedStatement(); 
		if (preparedStatementForUpdate!=null) 
		{			
			Properties p = obj.getProperties(); 
			try 
			{				
				preparedStatementForUpdate.setString(1,db2str(p.getProperty("name")));

				preparedStatementForUpdate.setString(2,db2str(p.getProperty("serviceType")));

				preparedStatementForUpdate.setInt(3,Integer.parseInt(p.getProperty("port")));

				preparedStatementForUpdate.setString(4,db2str(p.getProperty("name")));

				
				v.addElement(ps); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting preparedStatement for " 
				+" ServiceObject :"+sqle); 
				sqle.printStackTrace(); 
			}
		}
		return v; 
	}
}
