/* $Id: ConnectionCustomizerImpl.java,v 1.2 2008/08/14 05:10:16 sudharshan Exp $ */
/**
 * 
 */
package test;

import java.util.Properties;

import com.adventnet.management.transaction.ConnectionCustomizer;
import com.adventnet.nms.util.NmsUtil;

/**
 * @(#) ConnectionCustomizerImpl.java
 * Copyright (c) 2006 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 * 
 * Created: Tue Aug 12 11:30:30 2008
 * 
 * @author sudharshan
 * @since AdventNet Web NMS 5
 * 
 * @see ConnectionCustomizer.java for details. 
 *
 */
public class ConnectionCustomizerImpl implements ConnectionCustomizer 
{

	/* 
	 * @see com.adventnet.management.transaction.ConnectionCustomizer#readDBParameters(java.lang.String, java.lang.String, java.util.Properties)
	 */
	public void readDBParameters(String key, String value, Properties prop)
	{
		if(key.equals("hibernate.c3p0.max_size"))
		{
			prop.put("hibernate.c3p0.max_size",value);
		}		
		else if(key.equals("hibernate.c3p0.min_size"))
		{
			prop.put("hibernate.c3p0.min_size",value);
		}
		else if(key.equals("nontransaction.connection"))
		{
			prop.put("nontransaction.connection",value);
		}		
	}


	/* 
	 * @see com.adventnet.management.transaction.ConnectionCustomizer#validateDBParameters(java.util.Properties)
	 */
	public void validateDBParameters(Properties prop)
	{
		int max=0,min=0, non_txn=0;
		String max_con=null, min_con=null, ntxn=null;
		try
		{
			max_con = (String)prop.get("hibernate.c3p0.max_size");
			min_con = (String)prop.get("hibernate.c3p0.min_size");
			ntxn = (String)prop.get("nontransaction.connection");
		}
		catch(Exception ex)
		{
			exitWebNMS("Unable to read the database connections configured ",ex.getMessage(),ex);
		}
		
		try
		{
			min = Integer.parseInt(min_con);
		}
		catch(Exception e)
		{
			exitWebNMS("Minimum number of connections, hibernate.c3p0.min_size configured is invalid ",min_con,e);
		}
		try
		{
			max = Integer.parseInt(max_con);
		}
		catch(Exception e)
		{
			exitWebNMS("Maximum number of connections, hibernate.c3p0.max_size configured is invalid ",max_con,e);
		}
		try
		{
			non_txn = Integer.parseInt(ntxn);
		}
		catch(Exception e)
		{
			exitWebNMS("Number of non-transaction connections,nontransaction.connection configured is invalid ",ntxn,e);
		}
		
		if(min<=0)
		{
			exitWebNMS("Minimum database connections, hibernate.c3p0.min_size should be a positive integer ",null,null);
		}
		if(max<min)
		{
			exitWebNMS("Maximum number of connections should be greater than mininum number of connections ",null,null);
		}
		if(non_txn<=0)
		{
			exitWebNMS("Number of non-transaction connection nontransaction.connection should be a positive integer",null,null);
		}
		if(max<=non_txn)
		{
			exitWebNMS("Maximum number of connections, hibernate.c3p0.max_size should be greater than non-transactional connection count",null,null);
		}
		int txn_con = max-non_txn;
		prop.setProperty("transaction.connection",String.valueOf(txn_con));
	}

	private void exitWebNMS(String message, String ex, Exception e)
	{
		String msg = NmsUtil.GetString(message);
		if(ex!=null)
		{
			msg = msg + " : " +ex;
		}
		System.err.println(msg);
		if(e!=null)
		{
			e.printStackTrace();
		}
		System.exit(1);
	}
}
