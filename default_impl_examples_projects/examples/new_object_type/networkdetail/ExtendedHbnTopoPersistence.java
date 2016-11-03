// $Id: ExtendedHbnTopoPersistence.java,v 1.6 2010/10/29 13:46:04 swaminathap Exp $
package com.adventnet.nms.example.newobject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.PreparedStatementWrapper;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.TransactionException;
import com.adventnet.management.transaction.UserTransactionException;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.nms.store.relational.hbn.HbnTopoPersistence;

public class ExtendedHbnTopoPersistence extends HbnTopoPersistence {
	
	
	//PreparedStatement stmtForGet, stmtForAdd, stmtForUpdate, stmtForDel, stmtForFetch;
	static int psIdForGet, psIdForAdd, psIdForFetch, psIdForUpdate, psIdForDelete;
	
	public ExtendedHbnTopoPersistence()
	{
		init();
	}
	
	private void init()
	{
		ConnectionPool cp = ConnectionPool.getInstance();
		String queryForGet = "select * from NetworkSeverityDetail where NAME=?";//no i18n
		String queryForAdd = "insert into NetworkSeverityDetail values( ?,?,?)";//no i18n
		String queryForFetch = "select * from NetworkSeverityDetail where NAME=? and SEVERITY=?";//no i18n
		String queryForUpdate= "update NetworkSeverityDetail set TOTAL=? where NAME=? and SEVERITY=?";//no i18n
		String queryForDelete = "delete from NetworkSeverityDetail where NAME=?";//no i18n
		psIdForGet = cp.getPreparedStatementID(queryForGet);
		psIdForAdd = cp.getPreparedStatementID(queryForAdd);
		psIdForUpdate= cp.getPreparedStatementID(queryForUpdate);
		psIdForDelete= cp.getPreparedStatementID(queryForDelete);
		psIdForFetch= cp.getPreparedStatementID(queryForFetch);
	}
	
	PreparedStatementWrapper preparedStatementWrapper = null;

    public Vector getCustomStatements(Object obj)
    {
		//This will return the custom statements for fetching the severityDetail proeprty from NetworkSeverityDetail table.
    	Vector v = new Vector();
    	if (obj instanceof NetworkDetail)
    	{
    		try {
    			ConnectionPool cp = ConnectionPool.getInstance();
    			preparedStatementWrapper=cp.fetchPreparedStatement(psIdForGet);
    			PreparedStatement stmtForGet=preparedStatementWrapper.getPreparedStatement();
				stmtForGet.setString(1,((NetworkDetail)obj).getName());
				v.add(stmtForGet);
			} catch (SQLException e) {
				System.out.println("Error in getting custom queries for fetching networkseveritydetails table."+e.getMessage());//no i18n
			}
    	}
    	return v;
    }
    
    public void fillCustomProperties(Object obj, ResultSet rs, PreparedStatement stmt)
    {
    	//Called after executing the queries from getCustomStatements. Let us manipulate the resultset and fill 
    	//the custom property severityDetail hashtable here.
    	try{
	    	if (obj instanceof NetworkDetail)
	    	{
	    		ConnectionPool cp = ConnectionPool.getInstance();
	    		NetworkDetail netDetails = (NetworkDetail)obj;
	    		preparedStatementWrapper=cp.fetchPreparedStatement(psIdForGet);
    			PreparedStatement stmtForGet=preparedStatementWrapper.getPreparedStatement();
	    		if (stmt.equals(stmtForGet))
	    		{
					Hashtable<String, Integer> ht = new Hashtable<String, Integer>(10);
					while ( rs.next() )
					{
						ht.put(rs.getString(2),new Integer(rs.getInt(3)));
					}
					netDetails.setSeverityDetail(ht);
	    		}
	    	}
    	}
		catch( SQLException e)
		{
			System.err.println(" Exception in retrieving data from resultset");//no i18n
			e.printStackTrace();
		}
    }
    
    public Vector getCustomStatementsForAdd(Object obj)
	{
		//this method returns the custom statements for adding values into the NetworkSeverityDetail table.
		//each entry in the severitydetail hashtable corresponds to a row in this table.
		Vector v = new Vector();

		if (obj instanceof NetworkDetail)
		{
			Hashtable ht = ((NetworkDetail)obj).getSeverityDetail();
			if ( ht != null)
			{
				Enumeration keyList  = ht.keys();
				while( keyList.hasMoreElements() )
				{
					String key=null;
					key= (String)keyList.nextElement();
					int val=0;
					try
					{
						val = ((Integer)ht.get(key)).intValue();
					}
					catch ( NumberFormatException nfe)
					{
						System.err.println(" Error in converting String to Integer "+ ht.get(key) );//no i18n
						nfe.printStackTrace();
					}

					try{
						ConnectionPool cp = ConnectionPool.getInstance();
						preparedStatementWrapper = cp.fetchPreparedStatement(psIdForAdd);
						PreparedStatement stmtForAdd=preparedStatementWrapper.getPreparedStatement();
					    stmtForAdd.setString(1,((NetworkDetail)obj).getName());//name of the network
					    stmtForAdd.setString(2,key);//string severity
					    stmtForAdd.setInt(3,val);//count of interfaces belonging to this severity.
						v.addElement(stmtForAdd);
					}catch(SQLException se)
					{
						System.err.println(" Error in getting custom queries for add"+se.getMessage());//no i18n
					}
					// Finished
				}
			}
		}
    	return v;
	}
    
	public Vector getCustomStatementsForUpdate(Object obj)
	{
		Vector v = new Vector();

		if (obj instanceof NetworkDetail) {
			// A row inserted, if that severity for a Network is not
			// already present in the database otherwise updated.
			Hashtable ht = ((NetworkDetail)obj).getSeverityDetail();
			
			if ( ht != null)
			{
				Enumeration keyList=ht.keys();
				
				while( keyList.hasMoreElements() )
				{
					String key=(String)keyList.nextElement();
					int val=0;
					try
					{
						val= ((Integer)ht.get(key)).intValue();
					}
					catch ( NumberFormatException nfe)
					{
						System.err.println(" Exception in conveting String to int "+ key);//no i18n
						nfe.printStackTrace();
					}
					ResultSet rs=null;
					try {
						ConnectionPool cp = ConnectionPool.getInstance();
						preparedStatementWrapper = cp.fetchPreparedStatement(psIdForFetch);
						PreparedStatement stmtForFetch=preparedStatementWrapper.getPreparedStatement();
						stmtForFetch.setString(1,((NetworkDetail)obj).getName());
						stmtForFetch.setString(2,key);
						rs = stmtForFetch.executeQuery();
						boolean a = rs.next();
						if ( ! a )
						{	
							// No such severity present for the Network, hence
							// inserted.
						    preparedStatementWrapper = cp.fetchPreparedStatement(psIdForAdd);
						    PreparedStatement stmtForAdd=preparedStatementWrapper.getPreparedStatement();
						    stmtForAdd.setString(1,((NetworkDetail)obj).getName());
						    stmtForAdd.setString(2,key);
						    stmtForAdd.setInt(3,val);
							v.addElement(stmtForAdd);	
						}
						else
						{
							// Updation for that severity
						    
						    preparedStatementWrapper = cp.fetchPreparedStatement(psIdForUpdate);							
						    PreparedStatement stmtForUpdate=preparedStatementWrapper.getPreparedStatement();
							stmtForUpdate.setInt(1,val);
							stmtForUpdate.setString(2,((NetworkDetail)obj).getName());
							stmtForUpdate.setString(3,key);
							v.addElement(stmtForUpdate);
						}
					} catch (TransactionException e) {
						System.out.println("Error in getting custom queries for updating networkseveritydetails table."+e.getMessage());//no i18n
					} catch (SQLException e) {
						System.out.println("Error in getting custom queries for updating networkseveritydetails table ."+e.getMessage());//no i18n
					}
					finally
					{
						if(rs!=null)
						{
							try
							{
								rs.close();
							}
							catch(Exception e){}
						}
					}
					// Finished
				}
				
			}
			
		}
    	return v;
	}
	
	public Vector getCustomStatementsForDelete(Object obj)
	{
		Vector v = new Vector();
		if (obj instanceof NetworkDetail)
		{
			// To delete rows for the corresponding network from NetworkSeverityDetail.
			try {
				ConnectionPool cp = ConnectionPool.getInstance();
				preparedStatementWrapper = cp.fetchPreparedStatement(psIdForDelete);
				PreparedStatement stmtForDel = preparedStatementWrapper.getPreparedStatement();
				stmtForDel.setString(1,((NetworkDetail)obj).getName());
				v.addElement(stmtForDel);
			} catch (TransactionException e) {
				System.out.println("Error in getting custom queries for deleting networkseveritydetails table."+e.getMessage());//no i18n
			} catch (SQLException e) {
				System.out.println("Error in getting custom queries for deleting  networkseveritydetails table."+e.getMessage());//no i18n
			}
			// Finished
		}
		return v;
	}

	public Object getObject(String name)throws NmsStorageException, UserTransactionException
	{
		beginTxn();
		Object obj;
		try {
			//fetch the Network object first then populate the custom property.
			obj = super.getObject(name);
			if (obj instanceof NetworkDetail)
			{
				//method which will get the cutomStatements and then fill the custom properties. method present in HbnTopoPersistence.
				processCustomQueries(obj);
			}
			commitTxn();
		} catch(Exception e) {
			if(!(e instanceof RollbackException) && !(e instanceof UserTransactionException))
            {
                rollbackTxn(e.getMessage());
			}
            throw new NmsStorageException(e.getMessage(),e);
		}
		return obj;
	}
	
	public Object getObject(String name, boolean lazyFetch)
			throws NmsStorageException, UserTransactionException {
		beginTxn();
		Object obj;
		try {
			obj = super.getObject(name, lazyFetch);
			if (obj instanceof NetworkDetail) {
				//method which will get the cutomStatements and then fill the custom properties. method present in HbnTopoPersistence.
				processCustomQueries(obj);
			}
			commitTxn();
		} catch(Exception e) {
			if(!(e instanceof RollbackException) && !(e instanceof UserTransactionException))
            {
                rollbackTxn(e.getMessage());
			}
            throw new NmsStorageException(e.getMessage(),e);
		}
		return obj;
	}

	public void addObject(Object obj) throws NmsStorageException,
			UserTransactionException {
		beginTxn();
		try {
			super.addObject(obj);
			if (obj instanceof NetworkDetail) {
				//method to execute the custom statements to add into NetworkSeverityDetail table. method present in HbnTopoPersistence.
				executeCustomStatements(getCustomStatementsForAdd(obj));
				
			}
			commitTxn();
		} catch (Exception e) {
			if(!(e instanceof RollbackException) && !(e instanceof UserTransactionException))
            {
                rollbackTxn(e.getMessage());
			}
            throw new NmsStorageException(e.getMessage(),e);
		}
	}
	
	public void updateObject(Object obj) throws NmsStorageException, UserTransactionException {
		beginTxn();
		try {
			super.updateObject(obj);
			if (obj instanceof NetworkDetail) {
				//method to execute the custom statements to update into NetworkSeverityDetail table. method present in HbnTopoPersistence.
				executeCustomStatements(getCustomStatementsForUpdate(obj));
			}
			commitTxn();
		} catch(Exception e) {
			if(!(e instanceof RollbackException) && !(e instanceof UserTransactionException))
            {
                rollbackTxn(e.getMessage());
			}
            throw new NmsStorageException(e.getMessage(),e);
		}
	}
	
	public void deleteObject(Object obj) throws NmsStorageException, UserTransactionException {
		beginTxn();
		
		try {
			super.deleteObject(obj);
			if (obj instanceof NetworkDetail) {
				//method to execute the custom statements to delete into NetworkSeverityDetail table. method present in HbnTopoPersistence.
				executeCustomStatements(getCustomStatementsForDelete(obj));
			}
			commitTxn();
		} catch (Exception e) {
			if(!(e instanceof RollbackException) && !(e instanceof UserTransactionException))
            {
                rollbackTxn(e.getMessage());
			}
            throw new NmsStorageException(e.getMessage(),e);
		}
	}	
	
	private void beginTxn() throws UserTransactionException
    {
            try
            {
                    TransactionAPI.getInstance().begin();
            }
            catch(NotSupportedException nse)
            {
                    System.err.println("Transaction Not supported : "+nse);//NO I18N
            }
            catch(SystemException se)
            {
                    throw new UserTransactionException(se.getMessage(),se);
            }
    }
    private void commitTxn() throws UserTransactionException,SystemException
    {
            try
            {
                    TransactionAPI.getInstance().commit();
            }
            catch(RollbackException re)
            {
                    throw new UserTransactionException(re.getMessage(),re);
            }
            catch(HeuristicMixedException e)
            {
                    rollbackTxn(e.getMessage());
                    throw new UserTransactionException(e.getMessage(),e);
            }
            catch(HeuristicRollbackException e)
            {
                    rollbackTxn(e.getMessage());
                    throw new UserTransactionException(e.getMessage(),e);
            }
    }

    private void rollbackTxn(String message) throws UserTransactionException
    {
        TransactionAPI.getInstance().rollback(message);
    }
}
