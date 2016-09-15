package test;

import java.sql.*;


public  class OracleExtendedMO extends com.adventnet.nms.topodb.ManagedObject
{
	//<Begin_Variable_Declarations>
	private long longdata;
	private int numberdata;
	//<End_Variable_Declarations>

  
 public void setLongdata(long longdataArg)
  {
 //<Begin_setLongdata_long> 
 longdata = longdataArg;
  
 //<End_setLongdata_long>
  } 
  
 public long getLongdata()
  {
         //<Begin_getLongdata> 
	return longdata;
  
         //<End_getLongdata>
  } 
  
 public void setNumberdata(int numberdataArg)
  {
 //<Begin_setNumberdata_int> 
 numberdata = numberdataArg;
  
 //<End_setNumberdata_int>
  } 
  
 public int getNumberdata()
  {
         //<Begin_getNumberdata> 
	return numberdata;
  
         //<End_getNumberdata>
  } 
  
  public java.util.Properties getProperties()
  {
         //<Begin_getProperties> 
	java.util.Properties mosource_prop;
	mosource_prop = super.getProperties();

	mosource_prop.put("longdata",String.valueOf(getLongdata()));
	mosource_prop.put("numberdata",String.valueOf(getNumberdata()));
	return mosource_prop;
  
         //<End_getProperties>
  } 
  
  public void setProperties(java.util.Properties p)
  {
         //<Begin_setProperties_java.util.Properties> 

	String longdatavalue=p.getProperty("longdata");
	if(longdatavalue!=null)
	{ 
		longdata=Long.parseLong(longdatavalue);
		p.remove("longdata");
	} 

	String numberdatavalue=p.getProperty("numberdata");
	if(numberdatavalue!=null)
	{ 
		numberdata=Integer.parseInt(numberdatavalue);
		p.remove("numberdata");
	} 
	super.setProperties( p);

  
         //<End_setProperties_java.util.Properties>
  } 
  
  public Object clone()
  {
         //<Begin_clone> 
	return super.clone();
  
         //<End_clone>
  } 
  
  public int checkStatus() throws java.rmi.RemoteException
  {
         //<Begin_checkStatus> 
	return super.checkStatus();
  
         //<End_checkStatus>
  } 

} 
