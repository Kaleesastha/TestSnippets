
/* $Id: StatsTableCleanup.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

/*
 *  StatsTableCleanup.java
 */
package com.adventnet.nms.policies;

import java.util.Properties;
import java.util.Calendar;
import java.util.Date;			 
import java.text.SimpleDateFormat;
import java.text.*;
import com.adventnet.management.policydb.PolicyEvent;
import com.adventnet.management.policydb.PeriodicPolicyObject;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.management.log.Log;
import com.adventnet.nms.util.NmsLogMgr;
/**
 * This policy deletes statistics data tables from the database  
 * on a schedule chosen by the administrator.    When not
 * using dated tables, it cleans up the old data in the table.
 * 
 * The age of the data to delete is specified in days.
 *  
 * An extension of this policy can be done to backup the data
 * to another database table having the same columns.
 * 
 */

public class StatsTableCleanup extends PeriodicPolicyObject
{
	static final long serialVersionUID = 1552666204524928604L;

    /** This policy will be run every hour to check if it's time to run.
      So check every hour **/
    public StatsTableCleanup() 
    { 
	super();
	period = 3600;  // we'll check whether to execute every hour
        setUserProperty("Table Name" , "STATSDATA%");//No Internationalisation
        setUserProperty("Cleanup Hour (0-23)" , "0");//No Internationalisation
        setUserProperty("Delete data after (days)" , "7");//No Internationalisation
    }

    /**
     * Sets the properties for this policy object
     * @param p the property to be set.
     */

    public void setProperties(Properties p)
    {
        String temp;
        temp = (String)p.remove("Table Name");//No Internationalisation
        if (temp != null)
        {
            setUserProperty("Table Name" , temp.trim());//No Internationalisation
        }

        temp = (String)p.remove("Cleanup Hour (0-23)");//No Internationalisation
        if( temp != null)
        {
            setUserProperty("Cleanup Hour (0-23)" , temp.trim());//No Internationalisation
        }

        temp = (String)p.remove("Delete data after (days)");//No Internationalisation
        if(temp != null)
        {
            setUserProperty("Delete data after (days)" , temp.trim());//No Internationalisation
        }
        super.setBaseProperties(p);
    }


    /**
     * Returns the properties of this policy object
     * @return "DBCleanupPolicy properties"//No Internationalisation
     */

    public Properties getProperties()
    {
	Properties p = super.getBaseProperties();
        String temp;
        temp = (String)getUserProperty("Table Name");//No Internationalisation
        if (temp != null)
        {
            p.put("Table Name" , temp);//No Internationalisation
        }

        temp = (String)getUserProperty("Cleanup Hour (0-23)");//No Internationalisation
        if( temp != null)
        {
            p.put("Cleanup Hour (0-23)" , temp);//No Internationalisation
        }

        temp = (String)getUserProperty("Delete data after (days)");//No Internationalisation
        if(temp != null)
        {
            p.put("Delete data after (days)" , temp);//No Internationalisation
        }
		
	return p;
    }
    
    /**
     * Returns the help url of this policy object. As, the default customizer associated
     * with this policy uses the help url directly from the help.conf, this method
     * returns null.
     */
    public String getHelpURL()
    {
        return null;
    }

    /** 
     * Returns the properties of this policy object 
     * @return "Policy customizer name" //No Internationalisation
     */ 
    public String getPolicyObjectCustomizer() {
	return policyObjectCustomizer;
    }


    /**
     * executes action of this policy object.
     * @param policyEvt PolicyEvent
     */

    public  void executeAction(PolicyEvent policyEvt)
    {
        String table = getUserProperty("Table Name"); //No Internationalisation
        int age = Integer.parseInt(getUserProperty("Delete data after (days)"));//No Internationalisation
        int when = Integer.parseInt(getUserProperty("Cleanup Hour (0-23)"));//No Internationalisation

        if (table == null || table.equals(""))//No Internationalisation
        {
            NmsLogMgr.POLICYERR.log(NmsUtil.GetString("Table name not specified in StatsTableCleanup Policy.Can not execute Policy"), Log.SUMMARY);
            return;
        }

        // Check hour of day
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        
        if (hour != when) return;

        Date d = new Date();
        
        System.err.println(MessageFormat.format(NmsUtil.GetString("StatsTableCleanup Policy start for table: {0} at {1}"),new Object[]{table,d}) );

        long deleteAge = d.getTime() - (age * 3600 * 24 * 1000L);
        if (deleteAge < 0) return;

        try 
        {
            RelationalAPI api = NmsUtil.relapi;

            if (api == null) 
            {
                System.err.println(NmsUtil.GetString("StatsTableCleanup Policy: cannot get access to DB API"));
                return;
            }
            // We can have one of two modes 1) dated tables 2) single table

            if(table.endsWith("%")) //No Internationalisation
            { // dated tables

                String tableName = table.substring(0,table.length()-1);

                java.util.Date dt = new java.util.Date(deleteAge);
                java.text.SimpleDateFormat formatter
                    = new java.text.SimpleDateFormat ("M_d_yyyy");//No Internationalisation
                String tdate = formatter.format(dt);

                tableName += tdate;
                String statement = "drop table " + tableName;
                api.execute(statement);
            } else 
            { 
                // single table where old data should be cleaned up
                String statement = "delete from " + table + "where TTIME < " + deleteAge;//No Internationalisation
                api.execute(statement);
            }
            System.err.println(MessageFormat.format(NmsUtil.GetString("StatsTableCleanup Policy done: {0} at {1}"),new Object[]{table,new Date()}) );
        } catch (Exception ex) {
            System.err.println(NmsUtil.GetString("StatsTableCleanup Policy exception")+ex);
            ex.printStackTrace();
        } 
    }
}

