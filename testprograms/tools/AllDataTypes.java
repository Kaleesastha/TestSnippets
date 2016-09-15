package com.adventnet.nms.topodb;

import java.sql.*;


public  class AllDataTypes extends com.adventnet.nms.topodb.ManagedObject
{
	//<Begin_Variable_Declarations>
	private String stringProp = "";
	private int intProp;
	private long longProp;
	private float floatProp;
	private boolean boolProp;
	private Date dateProp;
	private Time timeProp;
	private Timestamp timeStampProp;
	//<End_Variable_Declarations>

  
 public void setStringProp(String stringPropArg)
  {
 //<Begin_setStringProp_String> 
 if(stringPropArg != null)
 {
	stringProp = stringPropArg;
 }
  
 //<End_setStringProp_String>
  } 
  
 public String getStringProp()
  {
         //<Begin_getStringProp> 
	return stringProp;
  
         //<End_getStringProp>
  } 
  
 public void setIntProp(int intPropArg)
  {
 //<Begin_setIntProp_int> 
 intProp = intPropArg;
  
 //<End_setIntProp_int>
  } 
  
 public int getIntProp()
  {
         //<Begin_getIntProp> 
	return intProp;
  
         //<End_getIntProp>
  } 
  
 public void setLongProp(long longPropArg)
  {
 //<Begin_setLongProp_long> 
 longProp = longPropArg;
  
 //<End_setLongProp_long>
  } 
  
 public long getLongProp()
  {
         //<Begin_getLongProp> 
	return longProp;
  
         //<End_getLongProp>
  } 
  
 public void setFloatProp(float floatPropArg)
  {
 //<Begin_setFloatProp_float> 
 floatProp = floatPropArg;
  
 //<End_setFloatProp_float>
  } 
  
 public float getFloatProp()
  {
         //<Begin_getFloatProp> 
	return floatProp;
  
         //<End_getFloatProp>
  } 
  
 public void setBoolProp(boolean boolPropArg)
  {
 //<Begin_setBoolProp_boolean> 
 boolProp = boolPropArg;
  
 //<End_setBoolProp_boolean>
  } 
  
 public boolean getBoolProp()
  {
         //<Begin_getBoolProp> 
	return boolProp;
  
         //<End_getBoolProp>
  } 
  
 public void setDateProp(Date datePropArg)
  {
 //<Begin_setDateProp_Date> 
 dateProp = datePropArg;
  
 //<End_setDateProp_Date>
  } 
  
 public Date getDateProp()
  {
         //<Begin_getDateProp> 
	return dateProp;
  
         //<End_getDateProp>
  } 
  
 public void setTimeProp(Time timePropArg)
  {
 //<Begin_setTimeProp_Time> 
 timeProp = timePropArg;
  
 //<End_setTimeProp_Time>
  } 
  
 public Time getTimeProp()
  {
         //<Begin_getTimeProp> 
	return timeProp;
  
         //<End_getTimeProp>
  } 
  
 public void setTimeStampProp(Timestamp timeStampPropArg)
  {
 //<Begin_setTimeStampProp_Timestamp> 
 timeStampProp = timeStampPropArg;
  
 //<End_setTimeStampProp_Timestamp>
  } 
  
 public Timestamp getTimeStampProp()
  {
         //<Begin_getTimeStampProp> 
	return timeStampProp;
  
         //<End_getTimeStampProp>
  } 
  
  public java.util.Properties getProperties()
  {
         //<Begin_getProperties> 
	java.util.Properties mosource_prop;
	mosource_prop = super.getProperties();

	if(getStringProp()!=null)
	{
		mosource_prop.put("stringProp",getStringProp());
	}
	mosource_prop.put("intProp",String.valueOf(getIntProp()));
	mosource_prop.put("longProp",String.valueOf(getLongProp()));
	mosource_prop.put("floatProp",String.valueOf(getFloatProp()));
	mosource_prop.put("boolProp",String.valueOf(getBoolProp()));
	if(getDateProp()!=null)
	{
		mosource_prop.put("dateProp",String.valueOf(getDateProp()));
	}
	if(getTimeProp()!=null)
	{
		mosource_prop.put("timeProp",String.valueOf(getTimeProp()));
	}
	if(getTimeStampProp()!=null)
	{
		mosource_prop.put("timeStampProp",String.valueOf(getTimeStampProp()));
	}
	return mosource_prop;
  
         //<End_getProperties>
  } 
  
  public void setProperties(java.util.Properties p)
  {
         //<Begin_setProperties_java.util.Properties> 

	String stringPropvalue=p.getProperty("stringProp");
	if(stringPropvalue!=null)
	{ 
		stringProp=stringPropvalue;
		p.remove("stringProp");
	} 

	String intPropvalue=p.getProperty("intProp");
	if(intPropvalue!=null)
	{ 
		intProp=Integer.parseInt(intPropvalue);
		p.remove("intProp");
	} 

	String longPropvalue=p.getProperty("longProp");
	if(longPropvalue!=null)
	{ 
		longProp=Long.parseLong(longPropvalue);
		p.remove("longProp");
	} 

	String floatPropvalue=p.getProperty("floatProp");
	if(floatPropvalue!=null)
	{ 
		floatProp=Float.parseFloat(floatPropvalue);
		p.remove("floatProp");
	} 

	String boolPropvalue=p.getProperty("boolProp");
	if(boolPropvalue!=null)
	{ 
		boolProp= Boolean.valueOf(boolPropvalue).booleanValue();
		p.remove("boolProp");
	} 

	String datePropvalue=p.getProperty("dateProp");
	if(datePropvalue!=null)
	{ 
		dateProp=Date.valueOf(datePropvalue);
		p.remove("dateProp");
	} 

	String timePropvalue=p.getProperty("timeProp");
	if(timePropvalue!=null)
	{ 
		timeProp=Time.valueOf(timePropvalue);
		p.remove("timeProp");
	} 

	String timeStampPropvalue=p.getProperty("timeStampProp");
	if(timeStampPropvalue!=null)
	{ 
		timeStampProp=Timestamp.valueOf(timeStampPropvalue);
		p.remove("timeStampProp");
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
