package com.adventnet.nms.store.relational; 
import java.sql.*; 
import java.util.*; 
import com.adventnet.nms.store.*; 
import com.adventnet.nms.topodb.*;  

class RelationalLinkObject extends RelationalManagedObject
{	
	PreparedStatement preparedStatementForGet; 
	PreparedStatement preparedStatementForAdd; 
	PreparedStatement preparedStatementForDelete; 
	PreparedStatement preparedStatementForUpdate; 
	
	public RelationalLinkObject() 
	{		
	}
	public void init(RelationalAPI relapi) 
	{		
		super.init(relapi); 
		String prepareSqlStringForGet =  "select * from LinkObject where ( "
		 +"( " + RelationalUtil.quot + RelationalUtil.getAlias( "name" ) + RelationalUtil.quot + " = ? )"
		 +")"; 
		
		preparedStatementForGet = relapi.getPreparedStatement 
		( prepareSqlStringForGet ); 
		
		String prepareSqlStringForAdd =  "insert into LinkObject values ( "
		 +" ?,"
		 +" ?,"
		 +" ?,"
		 +" ?"
		 +" )"; 
		
		preparedStatementForAdd = relapi.getPreparedStatement 
		( prepareSqlStringForAdd ); 
		
		String prepareSqlStringForUpdate =  "update LinkObject set "
		 +RelationalUtil.quot + RelationalUtil.getAlias( "name" ) + RelationalUtil.quot + " = ?,"
		 +RelationalUtil.quot + RelationalUtil.getAlias( "source" ) + RelationalUtil.quot + " = ?,"
		 +RelationalUtil.quot + RelationalUtil.getAlias( "destination" ) + RelationalUtil.quot + " = ?,"
		 +RelationalUtil.quot + RelationalUtil.getAlias( "ipADDR" ) + RelationalUtil.quot + " = ?"
		 +" where ( "
		 +"( " + RelationalUtil.quot + RelationalUtil.getAlias( "name" ) + RelationalUtil.quot + " = ? )"
		 +")"; 
		
		preparedStatementForUpdate = relapi.getPreparedStatement 
		( prepareSqlStringForUpdate ); 
		
		String prepareSqlStringForDelete =  "delete from LinkObject where ( "
		 +"( " + RelationalUtil.quot + RelationalUtil.getAlias( "name" ) + RelationalUtil.quot + " = ? )"
		 +")"; 
		
		preparedStatementForDelete = relapi.getPreparedStatement 
		( prepareSqlStringForDelete ); 
	}
	
	public boolean fillProperties(DBInterface dbObj, Properties p) 
	{		
		LinkObject obj = (LinkObject)dbObj; 
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
		if (preparedStatementForGet!=null) 
		{			
			try 
			{				
				preparedStatementForGet.setString(1,args[0]);  
				
				v.addElement(preparedStatementForGet); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting prepared statement for " 
				+" LinkObject "+args[0]+":"+sqle); 
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
	public Vector getStatementsForAdd(DBInterface dbObj) 
	{		
		LinkObject obj = (LinkObject)dbObj; 
		Vector v =super.getStatementsForAdd(dbObj); 
		if (preparedStatementForAdd!=null) 
		{			
			Properties p = obj.getProperties(); 
			try 
			{				
				preparedStatementForAdd.setString
				(1,db2str(p.getProperty("name")));

				preparedStatementForAdd.setString
				(2,db2str(p.getProperty("source")));

				preparedStatementForAdd.setString
				(3,db2str(p.getProperty("destination")));

				preparedStatementForAdd.setString
				(4,db2str(p.getProperty("ipADDR")));

				
				v.addElement(preparedStatementForAdd); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting preparedStatement for " 
				+ " LinkObject :"+sqle); 
				sqle.printStackTrace(); 
			}
		}
		return v; 
	}
	public Vector getStatementsForDelete(DBInterface dbObj) 
	{		
		LinkObject obj = (LinkObject)dbObj; 
		Vector v =super.getStatementsForDelete(dbObj); 
		if (preparedStatementForDelete!=null) 
		{			
			try 
			{				
				preparedStatementForDelete.setString
				(1,db2str(obj.getName()));
				
				v.addElement(preparedStatementForDelete); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting preparedStatement for " 
				+" LinkObject :"+sqle); 
			}
		}
		return v; 
	}
	public Vector getStatementsForUpdate(DBInterface dbObj) 
	{		
		LinkObject obj = (LinkObject)dbObj; 
		Vector v =super.getStatementsForUpdate(dbObj); 
		if (preparedStatementForUpdate!=null) 
		{			
			Properties p = obj.getProperties(); 
			try 
			{				
				preparedStatementForUpdate.setString
				(1,db2str(p.getProperty("name")));

				preparedStatementForUpdate.setString
				(2,db2str(p.getProperty("source")));

				preparedStatementForUpdate.setString
				(3,db2str(p.getProperty("destination")));

				preparedStatementForUpdate.setString
				(4,db2str(p.getProperty("ipADDR")));

				preparedStatementForUpdate.setString
				(5,db2str(p.getProperty("name")));

				
				v.addElement(preparedStatementForUpdate); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting preparedStatement for " 
				+" LinkObject :"+sqle); 
				sqle.printStackTrace(); 
			}
		}
		return v; 
	}
}
