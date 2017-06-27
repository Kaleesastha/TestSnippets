//$Id: RelationalAgentObject.java,v 1.2 2007/04/30 17:21:28 tinku Exp $
package com.adventnet.nms.store.relational;

import java.sql.*;
import java.util.*;

import com.adventnet.nms.store.*;
import com.adventnet.management.transaction.PreparedStatementWrapper;

import com.adventnet.nms.jmxagent.AgentDefValObject;

public class RelationalAgentObject extends CommonRelationalObject
{

    static boolean initialized = false ;
    static int psIDForGet;
    static int psIDForAdd;
    static int psIDForDelete;
    static int psIDForUpdate;

    public RelationalAgentObject()
    {
    }

    public void init(RelationalAPI relapi)
    {
        super.init(relapi);

        String prepareSqlStringForGet =  "select * from AgentDefValObject where ( " + RelationalUtil.getAlias("name" ) + " = ? )"; //No I18N
        psIDForGet = relapi.getPreparedStatementID(prepareSqlStringForGet );

        String prepareSqlStringForAdd =  "insert into AgentDefValObject values ( " + " ?," + " ?" + " )";//No I18N
        psIDForAdd = relapi.getPreparedStatementID(prepareSqlStringForAdd );

        String prepareSqlStringForUpdate = "update AgentDefValObject set " + RelationalUtil.getAlias( "name" ) + " = ?," + RelationalUtil.getAlias( "value" ) + " = ?" + " where ( " + RelationalUtil.getAlias( "name" ) + " = ? )"; //No I18N
        psIDForUpdate = relapi.getPreparedStatementID(prepareSqlStringForUpdate );

        String prepareSqlStringForDelete = "delete from AgentDefValObject where ( " + RelationalUtil.getAlias( "name" ) + " = ? )";//No I18N
        psIDForDelete = relapi.getPreparedStatementID(prepareSqlStringForDelete );

        initialized=true;
    }

    public Vector getStatementsForGet(String args)
    {
        Vector v =  new Vector();
        PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForGet);
        PreparedStatement preparedStatementForGet=ps.getPreparedStatement();
        if (preparedStatementForGet!=null)
        {
            try
            {
                preparedStatementForGet.setString(1,args);
                v.addElement(ps);
            }
            catch (SQLException sqle)
            {
                System.err.println("Exception " + args + ":" + sqle); //No I18N
            }
        }

        return v;
    }

    public Object fillPropertiesForObject(Properties prop)
    {
        AgentDefValObject obj=new AgentDefValObject();

        if(prop==null) return null;

        try
        {
            obj.setName(prop.getProperty("name"));//No I18N
            obj.setValue(prop.getProperty("value"));//No I18N
        }
        catch(Exception e)
        {
            System.out.println("Exception while fillproperties fortest object"+e);//No I18N
            return null;
        }

        return obj;
    }


    public Vector getStatementsForUpdate(Object dbobj)
    {
        Vector v = new Vector();
        AgentDefValObject obj=(AgentDefValObject)dbobj;
        try
        {
            PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForUpdate);
            PreparedStatement preparedStatementForUpdate=ps.getPreparedStatement();

            preparedStatementForUpdate.setString(1,obj.getName());
            preparedStatementForUpdate.setString(2,obj.getValue());
            preparedStatementForUpdate.setString(3,obj.getName());
            v.addElement(ps);
        }
        catch(Exception e)
        {
            // SystemUtil.cout.println("exception in commnrelationaltestobject"+e);//No I18N
        }
        return v;
    }

    public Vector getStatementsForDelete(Object dbobj)
    {
        AgentDefValObject obj = (AgentDefValObject)dbobj;
        Vector v =new Vector();
        try{
            PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForDelete);
            PreparedStatement preparedStatementForDelete=ps.getPreparedStatement();
            preparedStatementForDelete.setString(1,obj.getName());
            v.addElement(ps);
        }
        catch(SQLException e)
        {
            //   SystemUtil.cout.println("in commonRelationalTestObject :"+ e);//No I18N
        }

        return v;

    }
    public Vector getStatementsForAdd(Object dbObj)
    {
        AgentDefValObject obj = (AgentDefValObject)dbObj;
        Vector v = new Vector();
        PreparedStatementWrapper ps = relapi.fetchPreparedStatement(psIDForAdd);
        PreparedStatement preparedStatementForAdd=ps.getPreparedStatement();
	if (preparedStatementForAdd!=null)
	{
	    try
	    {
		preparedStatementForAdd.setString(1,obj.getName());

		preparedStatementForAdd.setString(2,obj.getValue());

		v.addElement(ps);
	    }
	    catch (SQLException sqle)
	    {
		System.err.println("Exception setting preparedStatement for "  + " TestObject :"+sqle); //No I18N
		sqle.printStackTrace();
	    }
	}
        return v;
    }
}










































