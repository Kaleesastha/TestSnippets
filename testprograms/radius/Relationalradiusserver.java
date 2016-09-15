package test.radius;  

import java.sql.*; 
import java.util.*; 

import com.adventnet.nms.store.*; 
import com.adventnet.management.transaction.PreparedStatementWrapper; 
import com.adventnet.nms.store.relational.*; 

import com.adventnet.management.log.Log; 
//import com.adventnet.nms.util.NmsLogMgr; 
import com.adventnet.wifi.utils.server.WLANLogManager;

public class Relationalradiusserver extends RelationalNode
{
	static boolean initialized = false ;
	static int psIDForGet;
	static int psIDForAdd;
	static int psIDForDelete;
	static int psIDForUpdate;
 
	
	public Relationalradiusserver() 
	{
         }

	public void init(RelationalAPI relapi) 
	{
		System.out.println("INTO RELATIONAL CLASSES ");
		super.init(relapi); 
		if (initialized) return; 
		String prepareSqlStringForGet =  "select * from radiusserver where ( "
		 +"( " + RelationalUtil.getAlias("name" ) + " = ? ) AND "
		 +"( " + RelationalUtil.getAlias("ownerName" ) + " = ? )"
		 +")"; 
		
		psIDForGet = relapi.getPreparedStatementID 
		( prepareSqlStringForGet ); 
		
		String prepareSqlStringForAdd =  "insert into radiusserver values ( "
		 +" ?,"
		 +" ?,"
		 +" ?,"
		 +" ?,"
		 +" ?"
		 +" )"; 
		
		psIDForAdd = relapi.getPreparedStatementID 
		( prepareSqlStringForAdd ); 
		
		String prepareSqlStringForUpdate =  "update radiusserver set " + RelationalUtil.getAlias( "authport" ) + " = ?," + RelationalUtil.getAlias( "acctport" ) + " = ?," + RelationalUtil.getAlias( "sharedsecret" ) + " = ?"
		 +" where ( "
		 +"( " + RelationalUtil.getAlias( "name" ) + " = ? ) AND "
		 +"( " + RelationalUtil.getAlias( "ownerName" ) + " = ? )"
		 +")"; 
		
		psIDForUpdate = relapi.getPreparedStatementID 
		( prepareSqlStringForUpdate ); 
		
		String prepareSqlStringForDelete =  "delete from radiusserver where ( "
		 +"( " + RelationalUtil.getAlias( "name" ) + " = ? ) AND "
		 +"( " + RelationalUtil.getAlias( "ownerName" ) + " = ? )"
		 +")"; 
		
		psIDForDelete = relapi.getPreparedStatementID 
		( prepareSqlStringForDelete ); 
		initialized=true; 
	
         }
	
	public boolean fillProperties(DBInterface dbObj, Properties p) 
	{
		test.radius.RadiusServer obj = (test.radius.RadiusServer)dbObj; 
		if ( (p==null) || (p.getProperty("name")==null) )
		{			
			return false; 
		}
		obj.setName(p.getProperty("name"));  
		obj.setOwnerName(p.getProperty("ownerName"));  
		
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
				preparedStatementForGet.setString(2,args[1]);  
				
				v.addElement(ps); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting prepared statement for " 
				+" Radius Server "+args[0]+":" + sqle); 
			}
		}
		
		return v; 
         }
	public Vector getCustomStatements(DBInterface dbObj,String[] args) 
	{
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
		test.radius.RadiusServer obj = (test.radius.RadiusServer)dbObj; 
		Vector v =super.getStatementsForAdd(dbObj); 
		PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForAdd); 
		PreparedStatement preparedStatementForAdd = ps.getPreparedStatement(); 
		if (preparedStatementForAdd!=null) 
		{			
			Properties p = obj.getProperties(); 
			try 
			{				
				preparedStatementForAdd.setString(1,db2str(p.getProperty("name")));

				preparedStatementForAdd.setString(2,db2str(p.getProperty("ownerName")));

				preparedStatementForAdd.setInt(3,Integer.parseInt(p.getProperty("authport")));

				preparedStatementForAdd.setInt(4,Integer.parseInt(p.getProperty("acctport")));

				preparedStatementForAdd.setString(5,db2str(p.getProperty("sharedsecret")));

				
				v.addElement(ps); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting preparedStatement for " 
				+ " Radius Server :" + sqle); 
			}
		}
		return v; 
	
         }
	
	public Vector getStatementsForDelete(DBInterface dbObj) 
	{
		test.radius.RadiusServer obj = (test.radius.RadiusServer)dbObj; 
		Vector v =super.getStatementsForDelete(dbObj); 
		PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForDelete); 
		PreparedStatement preparedStatementForDelete = ps.getPreparedStatement(); 
		if (preparedStatementForDelete!=null) 
		{			
			try 
			{				
				Properties p = obj.getProperties();
preparedStatementForDelete.setString(1,db2str(obj.getName()));
				preparedStatementForDelete.setString(2,db2str(obj.getOwnerName()));
				
				v.addElement(ps); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting preparedStatement for " 
				+" test.radius.radiusserver :" + sqle); 
			}
		}
		return v; 
	
         }
	
	public Vector getStatementsForUpdate(DBInterface dbObj) 
	{
		test.radius.RadiusServer obj = (test.radius.RadiusServer)dbObj; 
		Vector v =super.getStatementsForUpdate(dbObj); 
		PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForUpdate); 
		PreparedStatement preparedStatementForUpdate = ps.getPreparedStatement(); 
		if (preparedStatementForUpdate!=null) 
		{			
			Properties p = obj.getProperties(); 
			try 
			{				
				preparedStatementForUpdate.setInt(1,Integer.parseInt(p.getProperty("authport")));

				preparedStatementForUpdate.setInt(2,Integer.parseInt(p.getProperty("acctport")));

				preparedStatementForUpdate.setString(3,db2str(p.getProperty("sharedsecret")));

				preparedStatementForUpdate.setString(4,db2str(p.getProperty("name")));

				preparedStatementForUpdate.setString(5,db2str(p.getProperty("ownerName")));

				
				v.addElement(ps); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting preparedStatement for " 
				+" test.radius.radiusserver :" + sqle); 
			}
		}
		return v; 
	
         }
}
