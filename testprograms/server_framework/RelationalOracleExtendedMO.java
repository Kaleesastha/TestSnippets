package test;  
import java.sql.*; 
import java.util.*; 
import com.adventnet.nms.store.*; 
import com.adventnet.management.transaction.PreparedStatementWrapper; 
import com.adventnet.nms.store.relational.*; 

public class RelationalOracleExtendedMO extends RelationalManagedObject
{
	//<Begin_Variable_Declarations>
	static boolean initialized = false ;
	static int psIDForGet;
	static int psIDForAdd;
	static int psIDForDelete;
	static int psIDForUpdate;
	//<End_Variable_Declarations>
 
	
	public RelationalOracleExtendedMO() 
	{
         //<Begin_RelationalOracleExtendedMO>		
	
         //<End_RelationalOracleExtendedMO>
         }
	public void init(RelationalAPI relapi) 
	{
                 //<Begin_init_RelationalAPI>		
		super.init(relapi); 
		if (initialized) return; 
		String prepareSqlStringForGet =  "select * from OracleExtendedMO where ( "
		 +"( " + RelationalUtil.getAlias("name" ) + " = ? ) AND "
		 +"( " + RelationalUtil.getAlias("ownerName" ) + " = ? )"
		 +")"; 
		
		psIDForGet = relapi.getPreparedStatementID 
		( prepareSqlStringForGet ); 
		
		String prepareSqlStringForAdd =  "insert into OracleExtendedMO values ( "
		 +" ?,"
		 +" ?,"
		 +" ?,"
		 +" ?"
		 +" )"; 
		
		psIDForAdd = relapi.getPreparedStatementID 
		( prepareSqlStringForAdd ); 
		
		String prepareSqlStringForUpdate =  "update OracleExtendedMO set " + RelationalUtil.getAlias( "longdata" ) + " = ?," + RelationalUtil.getAlias( "numberdata" ) + " = ?"
		 +" where ( "
		 +"( " + RelationalUtil.getAlias( "name" ) + " = ? ) AND "
		 +"( " + RelationalUtil.getAlias( "ownerName" ) + " = ? )"
		 +")"; 
		
		psIDForUpdate = relapi.getPreparedStatementID 
		( prepareSqlStringForUpdate ); 
		
		String prepareSqlStringForDelete =  "delete from OracleExtendedMO where ( "
		 +"( " + RelationalUtil.getAlias( "name" ) + " = ? ) AND "
		 +"( " + RelationalUtil.getAlias( "ownerName" ) + " = ? )"
		 +")"; 
		
		psIDForDelete = relapi.getPreparedStatementID 
		( prepareSqlStringForDelete ); 
		initialized=true; 
	
                 //<End_init_RelationalAPI>
         }
	
	public boolean fillProperties(DBInterface dbObj, Properties p) 
	{
                 //<Begin_fillProperties_DBInterface_Properties>		
		test.OracleExtendedMO obj = (test.OracleExtendedMO)dbObj; 
		if ( (p==null) || (p.getProperty("name")==null) )
		{			
			return false; 
		}
		obj.setName(p.getProperty("name"));  
		obj.setOwnerName(p.getProperty("ownerName"));  
		
		obj.setProperties(p); 
		//<UserCode_Begin_FILL-PROPPERTIES>
		
		//<UserCode_End_FILL-PROPPERTIES> 
		return true; 
	
                 //<End_fillProperties_DBInterface_Properties>
         }
	
	public Vector getStatementsForGet(String[] args) 
	{
                 //<Begin_getStatementsForGet_String[]>		
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
				+" test.OracleExtendedMO "+args[0]+":"+sqle); 
			}
		}
		//<UserCode_Begin_GET-STATEMENTSFORGET>
		
		//<UserCode_End_GET-STATEMENTSFORGET> 
		return v; 
	
                 //<End_getStatementsForGet_String[]>
         }
	public Vector getCustomStatements(DBInterface dbObj,String[] args) 
	{
                 //<Begin_getCustomStatements_DBInterface_String[]>		
		//User to fill custom sql statements if any required 
		return super.getCustomStatements(dbObj,args); 
	
                 //<End_getCustomStatements_DBInterface_String[]>
         }
	public void fillCustomProperties(DBInterface dbObj,ResultSet rs,String str) 
	{
                 //<Begin_fillCustomProperties_DBInterface_ResultSet_String>		
		super.fillCustomProperties(dbObj,rs,str); 
	
                 //<End_fillCustomProperties_DBInterface_ResultSet_String>
         }
	public void fillCustomProperties(DBInterface dbObj,ResultSet rs,PreparedStatement prstmt) 
	{
                 //<Begin_fillCustomProperties_DBInterface_ResultSet_PreparedStatement>		
		super.fillCustomProperties(dbObj,rs,prstmt); 
	
                 //<End_fillCustomProperties_DBInterface_ResultSet_PreparedStatement>
         }
	public void fillCustomProperties(DBInterface dbObj,ResultSet rs,int id) 
	{
                 //<Begin_fillCustomProperties_DBInterface_ResultSet_int>		
		super.fillCustomProperties(dbObj,rs,id); 
	
                 //<End_fillCustomProperties_DBInterface_ResultSet_int>
         }
	public Vector getStatementsForAdd(DBInterface dbObj) 
	{
                 //<Begin_getStatementsForAdd_DBInterface>		
		test.OracleExtendedMO obj = (test.OracleExtendedMO)dbObj; 
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

				preparedStatementForAdd.setLong(3,Long.parseLong(p.getProperty("longdata")));

				preparedStatementForAdd.setInt(4,Integer.parseInt(p.getProperty("numberdata")));

				
				v.addElement(ps); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting preparedStatement for " 
				+ " test.OracleExtendedMO :"+sqle); 
				sqle.printStackTrace(); 
			}
		}
		//<UserCode_Begin_GET-STATEMENTSFORADD>
		
		//<UserCode_End_GET-STATEMENTSFORADD> 
		return v; 
	
                 //<End_getStatementsForAdd_DBInterface>
         }
	
	public Vector getStatementsForDelete(DBInterface dbObj) 
	{
                 //<Begin_getStatementsForDelete_DBInterface>		
		test.OracleExtendedMO obj = (test.OracleExtendedMO)dbObj; 
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
				+" test.OracleExtendedMO :"+sqle); 
			}
		}
		//<UserCode_Begin_GET-STATEMENTSFORDELETE>
		
		//<UserCode_End_GET-STATEMENTSFORDELETE> 
		return v; 
	
                 //<End_getStatementsForDelete_DBInterface>
         }
	
	public Vector getStatementsForUpdate(DBInterface dbObj) 
	{
                 //<Begin_getStatementsForUpdate_DBInterface>		
		test.OracleExtendedMO obj = (test.OracleExtendedMO)dbObj; 
		Vector v =super.getStatementsForUpdate(dbObj); 
		PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForUpdate); 
		PreparedStatement preparedStatementForUpdate = ps.getPreparedStatement(); 
		if (preparedStatementForUpdate!=null) 
		{			
			Properties p = obj.getProperties(); 
			try 
			{				
				preparedStatementForUpdate.setLong(1,Long.parseLong(p.getProperty("longdata")));

				preparedStatementForUpdate.setInt(2,Integer.parseInt(p.getProperty("numberdata")));

				preparedStatementForUpdate.setString(3,db2str(p.getProperty("name")));

				preparedStatementForUpdate.setString(4,db2str(p.getProperty("ownerName")));

				
				v.addElement(ps); 
			}
			catch (SQLException sqle) 
			{				
				System.err.println("Exception setting preparedStatement for " 
				+" test.OracleExtendedMO :"+sqle); 
				sqle.printStackTrace(); 
			}
		}
		//<UserCode_Begin_GET-STATEMENTSFORUPDATE>
		
		//<UserCode_End_GET-STATEMENTSFORUPDATE> 
		return v; 
	
                 //<End_getStatementsForUpdate_DBInterface>
         }
}
