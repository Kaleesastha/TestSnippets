/* $Id: DatabaseOperationsImpl.src,v 1.8.2.12.2.1 2005/08/25 05:08:49 kcpravin Exp $ */

/*
 * @(#)DatabaseOperationsImpl.src
 * Copyright (c) 2001 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */



package com.adventnet.snmp.snmp2;

import com.adventnet.utils.*;
import java.sql.*;
import java.util.*;
import java.io.*;


class DatabaseOperationsImpl implements DatabaseOperations
{
	DatabaseTableParams[] svd = new DatabaseTableParams[6];

	private	String[][] engineTable = 
						{{"DBKEY","DBKEY"},{"HOST","HOST"},{"PORT", "PORT"},	{"ENGINENAME", "ENGINENAME"},
						{"ENGINEID", "ENGINEID"}, {"ENGINETIME","ENGINETIME"}, {"ENGINEBOOTS","ENGINEBOOTS"}};

	private	String[][] usmTable = 
					{{"DBKEY","DBKEY"}, {"HOST","HOST"}, {"PORT","PORT"}, {"ENGINENAME","ENGINENAME"},
					{"ENGINEID","ENGINEID"}, {"USERNAME","USERNAME"}, {"SECURITYLEVEL","SECURITYLEVEL"},
					{"SECURITYNAME","SECURITYNAME"}, {"AUTHPROTOCOL","AUTHPROTOCOL"},{"AUTHPASSWORD","AUTHPASSWORD"},
					{"AUTHKEY","AUTHKEY"}, {"PRIVPROTOCOL","PRIVPROTOCOL"},	{"PRIVPASSWORD","PRIVPASSWORD"},
					{"PRIVKEY","PRIVKEY"}, {"ENGINETIME","ENGINETIME"},	{"ENGINEBOOTS","ENGINEBOOTS"},
					{"LATESTRCVDENGTIME","LATESTRCVDENGTIME"},{"LOCALTIME","LOCALTIME"}};
		
	private	String[][] vacmContextTable = { {"CONTEXTNAME","CONTEXTNAME"} };

	private	String[][] vacmSecurityToGroupTable = 
					{ {"SECURITYMODEL","SECURITYMODEL"},	{"SECURITYNAME","SECURITYNAME"},
					{"GROUPNAME","GROUPNAME"},{"STORAGETYPE","STORAGETYPE"},{"GROUPSTATUS","GROUPSTATUS"}};
		
	private	String[][] vacmAccessTable = 
					{{"GROUPNAME","GROUPNAME"},{"CONTEXTPREFIX","CONTEXTPREFIX"},{"SECURITYMODEL","SECURITYMODEL"},
					{"SECURITYLEVEL","SECURITYLEVEL"},{"CONTEXTMATCH","CONTEXTMATCH"},
					{"READVIEWNAME","READVIEWNAME"},{"WRITEVIEWNAME","WRITEVIEWNAME"},
					{"NOTIFYVIEWNAME","NOTIFYVIEWNAME"},{"STORAGETYPE","STORAGETYPE"},{"ROWSTATUS","ROWSTATUS"}};

	private	String[][] vacmViewTreeFamilyTable = 
					{{"VIEWNAME","VIEWNAME"},{"SUBTREE","SUBTREE"},{"MASK","MASK"},{"TYPE","TYPE"},
					{"STORAGETYPE","STORAGETYPE"},{"STATUS","STATUS"}};

	private	String[][] engineTypes = 
					{{"DBKEY","VARCHAR(57)"}, {"HOST","VARCHAR(50)"},{"PORT", "VARCHAR(5)"},
					{"ENGINENAME", "VARCHAR(50)"},{"ENGINEID", "VARCHAR(64)"},{"ENGINETIME","VARCHAR(10)"},
					{"ENGINEBOOTS","VARCHAR(10)"}};

	private	String[][] usmTypes = 
					{{"DBKEY","VARCHAR(116)"},{"HOST","VARCHAR(50)"},{"PORT","VARCHAR(5)"},{"ENGINENAME","VARCHAR(50)"},
					{"ENGINEID","VARCHAR(64)"},{"USERNAME","VARCHAR(50)"},{"SECURITYLEVEL","VARCHAR(5)"},
					{"SECURITYNAME","VARCHAR(50)"},	{"AUTHPROTOCOL","VARCHAR(10)"},	{"AUTHPASSWORD","VARCHAR(255)"},
					{"AUTHKEY","VARCHAR(255)"}, {"PRIVPROTOCOL","VARCHAR(10)"},{"PRIVPASSWORD","VARCHAR(255)"},
					{"PRIVKEY","VARCHAR(255)"},{"ENGINETIME","VARCHAR(10)"},{"ENGINEBOOTS","VARCHAR(10)"},
					{"LATESTRCVDENGTIME","LATESTRCVDENGTIME"},{"LOCALTIME","VARCHAR(30)"}};
		
	private	String[][] vacmContextTypes = {{"CONTEXTNAME","VARCHAR(65)"}};
		
	private	String[][] vacmSecurityToGroupTypes = 
					{ {"SECURITYMODEL","VARCHAR(10)"},{"SECURITYNAME","VARCHAR(65)"},
					{"GROUPNAME","VARCHAR(35)"},{"STORAGETYPE","VARCHAR(5)"},{"GROUPSTATUS","VARCHAR(5)"}};
		
	private	String[][] vacmAccessTypes = 
					{{"GROUPNAME","VARCHAR(255)"},{"CONTEXTPREFIX","VARCHAR(65)"},{"SECURITYMODEL","VARCHAR(10)"},
					{"SECURITYLEVEL","VARCHAR(5)"},{"CONTEXTMATCH","VARCHAR(5)"},{"READVIEWNAME","VARCHAR(65)"},
					{"WRITEVIEWNAME","VARCHAR(65)"},{"NOTIFYVIEWNAME","VARCHAR(65)"},{"STORAGETYPE","VARCHAR(5)"},
					{"ROWSTATUS","VARCHAR(5)"}};
		
	private	String[][] vacmViewTreeFamilyTypes = 
					{{"VIEWNAME","VARCHAR(65)"},{"SUBTREE","VARCHAR(255)"},{"MASK","VARCHAR(35)"},
					{"TYPE","VARCHAR(5)"},{"STORAGETYPE","VARCHAR(5)"},{"STATUS","VARCHAR(5)"}};

	private SASClient sasclient 			= null;

	private java.sql.Statement stmt 		= null;

	private java.sql.Connection connection	= null;

	private boolean passwordEncryptionNeeded = true;

	DatabaseOperationsImpl(SASClient sas) throws SQLException 
	{
		sasclient = sas;
		initV3tables();
		try
		{
			parseDatabaseConf();
		}
		catch(Exception e)
		{
			throw new SQLException(e.getMessage());
		}
	}

#ifdef(VERSION3){
	private void createTables() throws SQLException
	{
		String[] tableNames =
		{
			"ENGINETABLE",
			"USMTABLE",
			"VACMCONTEXTTABLE",
			"VACMSECURITYTOGROUPTABLE",
			"VACMACCESSTABLE",
			"VACMVIEWTREEFAMILYTABLE"
		};

		String[] tables =
		{
			"CREATE TABLE ENGINETABLE (" +
				"DBKEY VARCHAR(57) PRIMARY KEY, " +
				"HOST VARCHAR(50), " +
				"PORT VARCHAR(5), " +
				"ENGINENAME VARCHAR(50), " +
				"ENGINEID VARCHAR(64), " +
				"ENGINETIME VARCHAR(10)," +
				"ENGINEBOOTS VARCHAR(10) )",

			"CREATE TABLE USMTABLE (" +
				"DBKEY VARCHAR(116) PRIMARY KEY," +
				"HOST VARCHAR(50)," +
				"PORT VARCHAR(5)," +
				"ENGINENAME VARCHAR(50), "+
				"ENGINEID VARCHAR(64)," +
				"USERNAME VARCHAR(50), " +
				"SECURITYLEVEL VARCHAR(5)," +
				"SECURITYNAME VARCHAR(50), " +
				"AUTHPROTOCOL VARCHAR(10)," +
				"AUTHPASSWORD TEXT, " +
				"AUTHKEY TEXT," +
				"PRIVPROTOCOL VARCHAR(10), " +
				"PRIVPASSWORD TEXT," +
				"PRIVKEY TEXT, " +
				"ENGINETIME VARCHAR(10)," +
				"ENGINEBOOTS VARCHAR(10), " +
				"LATESTRCVDENGTIME VARCHAR(10)," +
				"LOCALTIME VARCHAR(30))",

			"CREATE TABLE VACMCONTEXTTABLE (" +
				"CONTEXTNAME VARCHAR(65) NOT NULL," +
				"PRIMARY KEY(CONTEXTNAME))",

			"CREATE TABLE VACMSECURITYTOGROUPTABLE (" +
				"SECURITYMODEL varchar(10) NOT NULL," +
				"SECURITYNAME varchar(65) NOT NULL," +
				"GROUPNAME varchar(35)," +
				"STORAGETYPE varchar(5)," +
				"GROUPSTATUS varchar(5)," +
				"PRIMARY KEY (SECURITYMODEL, SECURITYNAME))",

			"CREATE TABLE VACMACCESSTABLE (" +
				"GROUPNAME VARCHAR(255) NOT NULL," +
				"CONTEXTPREFIX VARCHAR(65) NOT NULL," +
				"SECURITYMODEL VARCHAR(10) NOT NULL," +
				"SECURITYLEVEL VARCHAR(5) NOT NULL," +
				"CONTEXTMATCH VARCHAR(5)," +
				"READVIEWNAME VARCHAR(65)," +
				"WRITEVIEWNAME VARCHAR(65)," +
				"NOTIFYVIEWNAME VARCHAR(65)," +
				"STORAGETYPE VARCHAR(5)," +
				"ROWSTATUS VARCHAR(5)," +
				"PRIMARY KEY(GROUPNAME," +
				"CONTEXTPREFIX," +
				"SECURITYMODEL," +
				"SECURITYLEVEL))",

			"CREATE TABLE VACMVIEWTREEFAMILYTABLE (" +
				"VIEWNAME VARCHAR(65)," +
				"SUBTREE TEXT," +
				"MASK VARCHAR(35)," +
				"TYPE VARCHAR(5)," +
				"STORAGETYPE VARCHAR(5)," +
				"STATUS VARCHAR(5))"

		};

		String errorString = null;
		String[] qs = getCreateDatabaseQueryString();
		String queryString = null;
		String selectString = "SELECT * FROM ";

		for(int tableNumber=0;tableNumber<qs.length;tableNumber++)
		{
			try
			{
				ResultSet r = null;
				if(sasclient == null)
				{
					String str = selectString + svd[tableNumber].getTableName();
					try
					{
						r = stmt.executeQuery(str);
					}
					catch(SQLException sql)
					{
						if(connection.isClosed())
						{
							stmt.close();
							connection.close();
							getLostConnection();	
							r = stmt.executeQuery(str);
							r.close();
						}	
						else
						{
							throw sql;
						}
					}

				}
				else
				{
					r = sasclient.queryDB(selectString + tableNames[tableNumber]);
				}
				
				if(tableNumber==1)
				{
					while(r.next())
					{
						String authPassword = r.getString(svd[tableNumber].getColumnName("AUTHPASSWORD"));
						if(authPassword !=null &&
							!(authPassword.equals("null") || authPassword.equals("NULL") || authPassword.equals("")))
						{
							try
							{
								StringEncrypter se = new StringEncrypter();
								byte[] darr = se.decrypt(SnmpUtils.getBytes(authPassword));
							}
							catch(Exception ex)
							{
								passwordEncryptionNeeded = false;
							}
							break;
						}
					}
				}
				r.close();
				continue;
			}
			catch(SQLException sqlexp)
			{
				//throw sqlexp;
			}
//			queryString = tables[tableNumber];
			queryString = qs[tableNumber];
			try
			{
				if(sasclient == null)
				{
					try
					{
						stmt.executeUpdate(queryString);
					}	
					catch(SQLException sql)
					{
						if(connection.isClosed())
						{
							stmt.close();
							connection.close();
							getLostConnection();
							stmt.executeUpdate(queryString);
						}
						else
						{
							throw sql;
						}
					}
				}
				else
				{
					sasclient.updateDB(queryString);
				}
			}
			catch(SQLException sqlexp)
			{
				if(errorString == null)
				{
					errorString = "Could not create table(s) ";
				}
				errorString += tableNames[tableNumber] + " ";
			}
		}
		if(errorString != null)
		{
			throw new SQLException(errorString);
		}
	}
}#endif

	/**
	 * This method should be involked to initialize the Jdbc parameters in case
	 * of database support. Either initJdbcParams method or this one can be
	 * called to make the database connection.
	 * @param driver Name of the DataBase driver.
	 * @param url        URL pointing to the DataBase file name
	 * @param user   userName
	 * @param pass   password
	 */
	synchronized public void connectDB(	String driver,String url,String user,String pass)throws SQLException, ClassNotFoundException
	{

		this.url=url;
		this.user=user;
		this.pass=pass;

		boolean perfFlag = SnmpAPI.isPerformanceLog(LogManager.CRITICAL);
		boolean debugFlag = SnmpAPI.isDebugLog(LogManager.CRITICAL);
		long t1 = 0;
		if(sasclient != null)
		{
			sasclient.connectDB(driver,url,user,pass);
		}
		else
		{
			if(perfFlag)
			{
				t1 = System.currentTimeMillis();	
			}
			String debugStr = null;
			if(debugFlag)
			{
				StringBuffer str = new StringBuffer(SnmpAPI.i18n("Driver name")).
					append(" : ").append(driver).append(" ").append("URL").
					append(" : ").append(url).append(" ").append("User").
					append(" ").append(user);
				SnmpAPI.debugLogging("connectDB","DBOpsImpl",debugStr);
			}

			Class.forName(driver);
			connection=DriverManager.getConnection(url,user,pass);
			debugStr = SnmpUtils.getString(
				"Connection has been established");
			if(debugFlag)
			{
				SnmpAPI.debugLogging("connectDB","DBOpsImpl",debugStr);
			}
			SnmpAPI.debugPrintHigh(debugStr);
			stmt = connection.createStatement();
		}
#ifdef(VERSION3){
		createTables();
}#endif
		if(perfFlag)
		{
			long t2 = System.currentTimeMillis();
			SnmpAPI.performanceLogging("connectDB","DBOpsImpl",t2-t1);
		}

	}


	/**
	 * This method should be invoked in case of closing the database connection.
	 *
	 */
	synchronized public void closeDB() throws SQLException
	{
		boolean perfFlag = SnmpAPI.isPerformanceLog(LogManager.SERIOUS);
		boolean debugFlag = SnmpAPI.isDebugLog(LogManager.CRITICAL);
		long t1 = 0;
		if(sasclient != null)
		{
			sasclient.closeDB();
		}
		else
		{
			if(perfFlag)
			{
				t1 = System.currentTimeMillis();	
			}
			if(debugFlag)
			{
				String debugStr = SnmpAPI.i18n("entering");
				SnmpAPI.debugLogging("closeDB","DBOpsImpl",debugStr);
			}

			if(stmt != null)
			{
				stmt.close();
				connection.close();
				stmt = null;
				connection = null;
			}
			else
			{
				String debugStr = SnmpUtils.getString("Database not connected");
				if(debugFlag)
				{
					SnmpAPI.debugLogging("closeDB","DBOpsImpl",debugStr);
				}
				throw new SQLException(debugStr);
			}
		}
		if(perfFlag)
		{
			long t2 = System.currentTimeMillis();
			SnmpAPI.performanceLogging("closeDB","DBOpsImpl",t2-t1);
		}

	}

	/**
	 * This method is used for making any database queries. The queryString should
	 * be passed as argument to the method. The returned ResultSet is the implementation
	 * of AdvetnNet and not that of the concerned database driver. So only
	 * the methods most needed are implemented. The methods implemeted are: next(),
	 * previous(), first(), last(), afterLast(), beforeFirst(), isLast(), isFirst(),
	 * isBeforeFirst(), isAfterLast(), getRow(), getString(int) and  getString(String).
	 * Rest of the methods will either return "null" or "-1" or "false".
	 * @param s the SQL query string.
	 */

	synchronized public ResultSet queryDB(String s) throws SQLException
	{
		if(sasclient != null)
		{
			return sasclient.queryDB(s);
		}

		boolean perfFlag = SnmpAPI.isPerformanceLog(LogManager.SERIOUS);
		boolean debugFlag = SnmpAPI.isDebugLog(LogManager.CRITICAL);
		long t1 = 0;
		if(perfFlag)
		{
			t1 = System.currentTimeMillis();	
		}
		if(debugFlag)
		{
			StringBuffer str = new StringBuffer("Query").append(" : ").																									append(s);
			String debugStr  = str.toString();	
			SnmpAPI.debugLogging("queryDB","DBOpsImpl",debugStr);
		}

		ResultSetImpl impl = null;
		if(stmt != null)
		{
			ResultSet r = null;
			try
			{
				r = stmt.executeQuery(s);
			}	
			catch(SQLException sql)
			{
				if(connection.isClosed())
				{
					stmt.close();
					connection.close();
					getLostConnection();
					r = stmt.executeQuery(s);
				}
				else
				{
					throw sql;
				}
			}
			ResultSetMetaData m = r.getMetaData();
			int columnCount = m.getColumnCount();
			String[] col_arr = null;
			if(columnCount > 0)
			{
				col_arr = new String[columnCount];
				for(int i=0;i<columnCount;i++)
				{
					col_arr[i] = m.getColumnName(i+1);
				}
			}
			Vector v = new Vector();
			int rowCount = 0;
			while(r.next())
			{
				for(int i=0;i<columnCount;i++)
				{
					v.addElement(r.getString(i+1));
				}
				rowCount++;
			}
			r.close();
			String[][] values = null;
			if(rowCount > 0 && columnCount > 0 &&
				v.size() == rowCount*columnCount)
			{
				values = new String[rowCount][columnCount];
				for(int i=0;i<rowCount;i++)
				{
					for(int j=0;j<columnCount;j++)
					{
						values[i][j] = (String)v.elementAt(i*columnCount + j);
					}
				}
			}
			impl = new ResultSetImpl(col_arr, values);
		}
		else
		{
			String debugStr = SnmpUtils.getString("Database not connected");
			if(SnmpAPI.isDebugLog(LogManager.DEFAULT))
			{
				SnmpAPI.debugLogging("queryDB","DBOpsImpl",debugStr);
			}
        	throw new SQLException(debugStr);
		}
		if(perfFlag)
		{
			long t2 = System.currentTimeMillis();
			SnmpAPI.performanceLogging("queryDB","DBOpsImpl",t2-t1);
		}
			return (ResultSet)impl;
    }


	/**
	 * The method is used for making an update to the database.
	 * @param s the SQL update string.
	 */

	synchronized public int updateDB(String s) throws SQLException
	{
		boolean debugFlag = SnmpAPI.isDebugLog(LogManager.CRITICAL);
		int stat = 0;
		if(sasclient != null)
		{
			return sasclient.updateDB(s);
		}
		else if(stmt != null)
		{
			boolean perfFlag = SnmpAPI.isPerformanceLog(LogManager.SERIOUS);
			long t1 = 0;
			if(perfFlag)
			{
				t1 = System.currentTimeMillis();	
			}
			if(debugFlag)
			{
				StringBuffer str = new StringBuffer(SnmpAPI.i18n("Query")).
					append(" : ").append(s);
				String debugStr		 = str.toString();
				SnmpAPI.debugLogging("updateDB","DBOpsImpl",debugStr);
			}
			try
			{
				stat = stmt.executeUpdate(s);
			}	
			catch(SQLException sql)
			{
				if(connection.isClosed())
				{
					stmt.close();
					connection.close();
					getLostConnection();
					stat = stmt.executeUpdate(s);
				}
				else
				{
					throw sql;
				}
			}
		}
		else
		{
			String debugStr = SnmpUtils.getString("Database not connected");
			if(SnmpAPI.isDebugLog(LogManager.DEFAULT))
			{
				SnmpAPI.debugLogging("updateDB","DBOpsImpl",debugStr);
			}
			throw new SQLException(debugStr);
		}
		if(debugFlag)
		{
			StringBuffer str = new StringBuffer(
			SnmpAPI.i18n("returing")).append(" : ").append(s);
			String debugStr = str.toString();
			SnmpAPI.debugLogging("updateDB","DBOpsImpl",debugStr);
		}
		return stat;

	}
	
	/* 
	 * The following table contains the list of integer value and the
	 * corresponding DatabaseTableParams object	
	 * +-----------+-----------------------------------+
	 * |integer i  | Corresponding DatabaseTableParams |
	 * +-----------+-----------------------------------+
	 * |    0      | ENGINETABLE                       |
	 * |    1      | USMTABLE                          |
	 * |    2      | VACMCONTEXTTABLE                  |
	 * |    3      | VACMSECURITYTOGROUPTABLE          |
	 * |    4      | VACMACCESSTABLE                   |	
	 * |    5      | VACMVIEWTREEFAMILYTABLE           |
	 * +-----------+-----------------------------------+
	 */
	public synchronized DatabaseTableParams getTableParams(int i)
	{
		return svd[i];
	}
	
	public synchronized boolean isPasswordEncryptionNeeded()
	{
		return passwordEncryptionNeeded;
	}

	private void initV3tables()
	{
		svd[0] = new DatabaseTableParams("ENGINETABLE",engineTable,engineTypes);
		svd[1] = new DatabaseTableParams("USMTABLE",usmTable,usmTypes);
		svd[2] = new DatabaseTableParams("VACMCONTEXTTABLE",vacmContextTable,vacmContextTypes);
		svd[3] = new DatabaseTableParams("VACMSECURITYTOGROUPTABLE",vacmSecurityToGroupTable, vacmSecurityToGroupTypes);
		svd[4] = new DatabaseTableParams("VACMACCESSTABLE",vacmAccessTable,vacmAccessTypes);
		svd[5] = new DatabaseTableParams("VACMVIEWTREEFAMILYTABLE",vacmViewTreeFamilyTable,vacmViewTreeFamilyTypes);
	}

	private void parseDatabaseConf() throws Exception
	{
		int inc = 0;
		BufferedReader br = null;
		// 	In case of applets stream cannot be obtained using the below line. Instead stream is
		//	got using Class.getResource().
		//	InputStream istr = ClassLoader.getSystemResourceAsStream("snmpDatabaseSchema.config");
		
		String dbSchemaURL =null;
		java.net.URL url = null;

		try
		{
			dbSchemaURL = System.getProperty("SNMP_DBSCHEMA_URL");
		}
		catch(SecurityException t)
		{
			if(sasclient != null && sasclient.app != null)
			{
				dbSchemaURL = sasclient.app.getParameter("SNMP_DBSCHEMA_URL");
			}
		}

		if(dbSchemaURL != null)
		{
			url = new java.net.URL(dbSchemaURL);
		}

  	    if(url == null)
		{
			url = this.getClass().getResource("/snmpDatabaseSchema.config");
		}
		if(url == null)
		{
			throw new Exception(SnmpUtils.getString("Could not find the file snmpDatabaseSchema.config"));
		}
		
		try
		{
			br = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String s = null;
			
			while((s = br.readLine()) != null)
			{
				s = s.trim();
				if(s.startsWith("BEGIN"))
				{
					parseTableParams(br,inc);
					inc ++;
				}
				continue;
			}
		}
		catch(Exception e)
		{
			throw new Exception(SnmpUtils.getString(
				"Error encountered while reading the file snmpDatabaseSchema.config")+
				e.getMessage());
		}

	}
	
	private void parseTableParams(BufferedReader br, int inc) throws Exception
	{
		String s = null;
		int count = 0;
		while((s = br.readLine()) !=null)
		{
			s = s.trim();
			if(s.startsWith("#"))
			{
				continue;
			}
			else if(s.equals("END"))
			{
				break;
			}
			StringTokenizer st = new StringTokenizer(s,"\t, ");
			while(st.hasMoreTokens())
			{
				String key = st.nextToken();
				key = key.trim();
				
				if(count==0)
				{
					String origTableKey = svd[inc].getTableKey();
					if(origTableKey.equals(key))
					{
						while(st.hasMoreTokens())
						{
							svd[inc].setTableName(st.nextToken());
						}
						count ++;
					}
					else
					{
						throw new Exception(SnmpUtils.getString("The Original TableKey")+" "+origTableKey+" "+SnmpUtils.getString("has been changed as")+" "+key);
					}
				}
				else
				{
					if(svd[inc].getColumnName(key) == null)
					{
						throw new Exception(SnmpUtils.getString("Invalid ColumnKey in Conf file")+" : "+key);
					}
					else
					{
						if(st.hasMoreTokens())
						{
							String elem = st.nextToken();
							svd[inc].setColumnName(key,elem);
						}
						if(st.hasMoreTokens())
						{
							String type = st.nextToken();
							svd[inc].setColumnType(key,type);
						}
					}
				}
			}	
		}
	}
	
	private String[] getCreateDatabaseQueryString()
	{
		String queryString[] = new String[6];
		queryString[0] = createEngineTable();
		queryString[1] = createUsmTable();
		queryString[2] = createVacmContextTable();
		queryString[3] = createVacmSecurityToGroupTable();
		queryString[4] = createVacmAccessTable();
		queryString[5] = createVacmViewTreeFamilyTable();
		return queryString;
	}

	private String createEngineTable()
	{
		String qs = "CREATE TABLE "+svd[0].getTableName()+" (";
		Enumeration en = svd[0].getKeys();
		while(en.hasMoreElements())
		{
			String key = (String)en.nextElement();
			
			qs += svd[0].getColumnName(key)+" "+svd[0].getColumnType(key)+",";
			if(key.equals("DBKEY"))
			{
				qs = qs.substring(0,qs.length()-1);
				qs += " PRIMARY KEY,";
			}
		}
		qs = qs.substring(0,qs.length()-1);
		qs += ")";
		return qs;
	}

	private String createUsmTable()
	{
		String qs = "CREATE TABLE "+svd[1].getTableName()+" (";
		
		Enumeration en = svd[1].getKeys();
		while(en.hasMoreElements())
		{
			String key = (String)en.nextElement();
			
			qs += svd[1].getColumnName(key)+" "+svd[1].getColumnType(key)+",";
			if(key.equals("DBKEY"))
			{
				qs = qs.substring(0,qs.length()-1);
				qs += " PRIMARY KEY,";
			}
		}	
		qs = qs.substring(0,qs.length()-1);
		qs += ")";
		return qs;
	}

	private String createVacmContextTable()
	{
		String qs = "CREATE TABLE "+svd[2].getTableName()+" (";
		Enumeration en = svd[2].getKeys();
		while(en.hasMoreElements())
		{
			String key = (String)en.nextElement();
			qs += svd[2].getColumnName(key)+" "+svd[2].getColumnType(key)+",";
			qs = qs.substring(0,qs.length()-1);
                        qs += " NOT NULL,";
		}
                qs += "PRIMARY KEY(CONTEXTNAME))";
		return qs;
	}

	private String createVacmSecurityToGroupTable()
	{
		String qs = "CREATE TABLE "+svd[3].getTableName()+" (";
		
		Enumeration en = svd[3].getKeys();
		while(en.hasMoreElements())
		{
			String key = (String)en.nextElement();
			
			qs += svd[3].getColumnName(key)+" "+svd[3].getColumnType(key)+",";
			if(key.equals("SECURITYMODEL") || key.equals("SECURITYNAME"))
			{
				qs = qs.substring(0,qs.length()-1);
				qs += "NOT NULL,";	
			}
		}
		qs += "PRIMARY KEY (SECURITYMODEL, SECURITYNAME))";
		return qs;
	}

	private String createVacmAccessTable()
	{
		String qs = "CREATE TABLE "+svd[4].getTableName()+" (";
		
		Enumeration en = svd[4].getKeys();
		while(en.hasMoreElements())
		{
			String key = (String)en.nextElement();

			qs += svd[4].getColumnName(key)+" "+svd[4].getColumnType(key)+",";
			if(key.equals("GROUPNAME") || key.equals("CONTEXTPREFIX") || 
			   key.equals("SECURITYMODEL") || key.equals("SECURITYLEVEL"))
			{
				qs = qs.substring(0,qs.length()-1);
				qs += "NOT NULL,";
			}			   
		}
		qs += "PRIMARY KEY(GROUPNAME, CONTEXTPREFIX, SECURITYMODEL, SECURITYLEVEL))";
		return qs;
	}

	private String createVacmViewTreeFamilyTable()
	{
		String qs = "CREATE TABLE "+svd[5].getTableName()+" (";

		Enumeration en = svd[5].getKeys();
		while(en.hasMoreElements())
		{
			String key = (String)en.nextElement();
			qs += svd[5].getColumnName(key)+" "+svd[5].getColumnType(key)+",";
		}
		qs = qs.substring(0,qs.length()-1);
		qs += ")";
		return qs;
	}

	private String url = "";
	private String user = "";
	private String pass = "";

	private void getLostConnection() throws SQLException
	{
		SQLException sqlEx1 = null;
		for(int i=0;i<3;i++)
		{
			try
			{
				connection=DriverManager.getConnection(url,user,pass);
				stmt = connection.createStatement();
				sqlEx1 = null;
				break;
			}
			catch(SQLException sql)
			{
				sqlEx1 = sql;
				try
				{
					Thread.sleep(3000);
				}
				catch(InterruptedException ie)
				{
//					ie.printStackTrace();
				}
			}
		}
		if(sqlEx1 != null)
		{
			throw sqlEx1;
		}
	}
}
