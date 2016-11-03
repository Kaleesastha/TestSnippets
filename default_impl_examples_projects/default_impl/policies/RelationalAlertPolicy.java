//$Id: RelationalAlertPolicy.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.store.relational; 

import java.sql.*; 
import java.util.*; 
import com.adventnet.nms.store.*; 
import com.adventnet.nms.store.relational.RelationalUtil;
import com.adventnet.management.policydb.*;
import com.adventnet.nms.policies.AlertPolicy;
import com.adventnet.nms.alertdb.AlertFilter;
import com.adventnet.nms.eventdb.FilterAction;
import com.adventnet.management.log.Log;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.management.transaction.PreparedStatementWrapper;


import com.adventnet.management.transaction.UserTransactionException;


public class RelationalAlertPolicy extends RelationalPolicyObject
{	
    private static int preparedStatementForGet; 
    private static int preparedStatementForAdd; 
    private static int preparedStatementForDelete; 
    private static int preparedStatementForUpdate; 
    
    private boolean initialized = false;
    public RelationalAlertPolicy() 
    {		
    }
    public void init(RelationalAPI relapi) 
    {		
        super.init(relapi); 
        if(initialized)
        {
            // If already initialized don't do it again
            return;
        }
        String prepareSqlStringForGet =  "select "+ RelationalUtil.getAlias("actionName")+ " , " +RelationalUtil.getAlias("propKey")+ " , " + RelationalUtil.getAlias("propValue") + "  from AlertPolicyObject where ( "
            +"( " + RelationalUtil.getAlias( "name" ) + " = ? )" 
            + " ) ";
        
        preparedStatementForGet = relapi.getPreparedStatementID 
            ( prepareSqlStringForGet ); 
        
        String prepareSqlStringForAdd =  "insert into AlertPolicyObject values ( "
            +" ?,"
            +" ?,"
            +" ?,"
            +" ?"
            +" )"; 
        
        preparedStatementForAdd = relapi.getPreparedStatementID 
            ( prepareSqlStringForAdd ); 
        
        
        String prepareSqlStringForDelete =  "delete from AlertPolicyObject where ( "
            +"( " + RelationalUtil.getAlias( "name" ) + " = ? )"
            +")"; 
        
        preparedStatementForDelete = relapi.getPreparedStatementID 
            ( prepareSqlStringForDelete ); 
    
        // to reduce open cursor issue
        initialized = true;
    }
    
    // In this method we will populate required properties in this case
    // Alert filter and update policy with it
    public boolean fillProperties(DBInterface dbObj, Properties p) 
    {	
	super.fillProperties(dbObj , p);
        // just filling all FilterActions here
        fillFilterActions(dbObj);
        return true; 
    }
    
    private void fillFilterActions(DBInterface dbObj)
    {
        PreparedStatementWrapper pstat = relapi.fetchPreparedStatement(preparedStatementForGet);
        PreparedStatement prepared = pstat.getPreparedStatement();
        ResultSet rs = null;
        try
        {
            AlertPolicy po = (AlertPolicy)dbObj;
            AlertFilter af = new AlertFilter();
            Vector filterActionList = new Vector();
            String policyName = po.getName();
            prepared.setString(1,policyName);
            rs = relapi.executeQuery(prepared);;
            Hashtable filterActions = new Hashtable();
            while(rs.next())
            {
                String filterActionName = rs.getString(1);
                String propKey = rs.getString(2);
                String propvalue = rs.getString(3);
                Properties prop = (Properties)filterActions.get(filterActionName);
                if(prop == null)
                {
                    prop = new Properties();
                    // during initialization itself we have to set the name in the properties
                    // otherwise for filteractions default name will be taken
                    prop.setProperty("name",filterActionName);
                }
                if(propvalue==null)
                    {
                        propvalue="";//No I18n
                    }
                prop.setProperty(propKey , propvalue);
                filterActions.put(filterActionName , prop);
            }
            for(Enumeration en = filterActions.elements();en.hasMoreElements();)
            {
                Properties filterActionProp = (Properties)en.nextElement();
                String classname = (String)filterActionProp.remove("classname");
                try
                {
                    FilterAction filterAction = (FilterAction)Class.forName(classname).newInstance();
                    filterAction.setProperties(filterActionProp);
                    filterActionList.addElement(filterAction);
                
                }catch(Exception e)
                {
                    NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Error while recreating FilterActions :") , e); 
                }
            }
            af.actions = filterActionList;
            po.setAlertFilter(af);        
        }
        catch(Exception e)
        {
            NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Exception in fillFilterActions ") , e); 
        }
        finally
        {
            closeResultSet(rs);
            relapi.returnPreparedStatement(pstat); 
        }
    }

    //    private Vector
    public Vector getStatementsForGet(String[] args) 
    {
        return  super.getStatementsForGet(args);
    }
    public Vector getCustomStatements(DBInterface dbObj,String[] args) 
    {		
        return  super.getCustomStatements(dbObj,args);
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
	storeAlertActionsIntoDB(dbObj);
        return super.getStatementsForAdd(dbObj);
    }
    private void storeAlertActionsIntoDB(DBInterface dbObj)
    {
        
        try
        {
            PreparedStatementWrapper pstat = relapi.fetchPreparedStatement(preparedStatementForAdd);
            PreparedStatement prepared = pstat.getPreparedStatement();

            AlertPolicy po = (AlertPolicy)dbObj;
            String policyObjectName = po.getName();
            AlertFilter af = po.getAlertFilter();
            if(af == null)
            {
                return;
            }
            Vector v = af.getActionProperties();
            for(Enumeration en = v.elements() ; en.hasMoreElements();)
            {
                FilterAction filterAction = (FilterAction)en.nextElement();
                String filterActionClassName = filterAction.getClass().getName();
                Properties prop = filterAction.getProperties();
                String filterActionName = (String)prop.remove("name");
                prepared.setString(1,policyObjectName);
                prepared.setString(2,filterActionName);
                prepared.setString(3,"classname");
                prepared.setString(4,filterActionClassName);
                // This is to store class name first to differentiate different filter actions
                executePreparedStatement(prepared);
                for(Enumeration second  = prop.keys() ; second.hasMoreElements();)
                {
                    String propKey = (String)second.nextElement();
                    String propVal = prop.getProperty(propKey);
                
                    // As previous two values are already set no need to set it again
                    prepared.setString(3,propKey);
                    prepared.setString(4,propVal);
                    executePreparedStatement(prepared);
                }
            }
            relapi.returnPreparedStatement(pstat); 

        }catch(Exception e)
        {
            NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Exception in storeAlertActionsIntoDB method ") , e);
        }
        
    }

    
    // only delete string we will be passing in the vector
    public Vector getStatementsForDelete(DBInterface dbObj) 
    {	
        deleteFilterActions(((PolicyObject)dbObj).getName());
	return  super.getStatementsForDelete(dbObj);
    }
    
    private void deleteFilterActions(String policyObjectName)
    {
        PreparedStatementWrapper pstat = relapi.fetchPreparedStatement(preparedStatementForDelete);
        PreparedStatement prepared = pstat.getPreparedStatement();

        if (prepared!=null) 
        {			
            try 
            {				
                prepared.setString(1,policyObjectName);
                executePreparedStatement(prepared);
            }
            catch (Exception sqle) 
            {				
                System.err.println(NmsUtil.GetString("In RelationalAlertPolicy Exception setting preparedStatement for PolicyObject :")+sqle); 

                // TODO For future use
                //           throws new NmsStorageException("In RelationalAlertPolicy Exception setting preparedStatement for  PolicyObject : "+policyObjectName , sqle);
            }
            relapi.returnPreparedStatement(pstat); 

        }

    }


    // In this method no other go other than deleting existing contents and 
    // filling it up again
    public Vector getStatementsForUpdate(DBInterface dbObj) 
    {
        deleteFilterActions(((PolicyObject)dbObj).getName());
        storeAlertActionsIntoDB(dbObj);
        return super.getStatementsForUpdate(dbObj);
    }
            

    private void executePreparedStatement(PreparedStatement ps) throws NmsStorageException, UserTransactionException



    {
        if(ps != null)
        {
            relapi.executeUpdate(ps);
        }
        else
        {
            NmsLogMgr.POLICYERR.log(NmsUtil.GetString("PreparedStatement is null. Can not execute") , Log.SUMMARY);
        }
    }
    private void closeResultSet(ResultSet rs)
    {
        try
        {
            if(rs != null)
            {
                rs.close();
            }
            else
            {
                NmsLogMgr.POLICYERR.log(NmsUtil.GetString("Result set is null. Can not close it") , Log.SUMMARY); 
            }
        }catch(Exception e)
        {
            NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Error while closing result set ") ,e); 
        }
    }

}
