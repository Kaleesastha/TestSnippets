package com.adventnet.nms.topodb;
import java.sql.*;


public  class UserObject
{
	//<Begin_Variable_Declarations>
	private Timestamp timeStampProp;
	private boolean booleanProp;
	private String stringProp = "";
	private Time timeProp;
	private int intProp;
	private Date dateProp;
	private long longProp;
	//<End_Variable_Declarations>


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

 public void setBooleanProp(boolean booleanPropArg)
  {
 //<Begin_setBooleanProp_boolean>
 booleanProp = booleanPropArg;

 //<End_setBooleanProp_boolean>
  }

 public boolean getBooleanProp()
  {
         //<Begin_getBooleanProp>
	return booleanProp;

         //<End_getBooleanProp>
  }

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


public java.util.Properties getProperties()
  {
         //<Begin_getProperties>
	java.util.Properties mosource_prop = null;

	if(getTimeStampProp()!=null)
	{
		mosource_prop.put("timeStampProp",String.valueOf(getTimeStampProp()));
	}
	mosource_prop.put("booleanProp",String.valueOf(getBooleanProp()));
	if(getStringProp()!=null)
	{
		mosource_prop.put("stringProp",getStringProp());
	}
	if(getTimeProp()!=null)
	{
		mosource_prop.put("timeProp",String.valueOf(getTimeProp()));
	}
	mosource_prop.put("intProp",String.valueOf(getIntProp()));
	if(getDateProp()!=null)
	{
		mosource_prop.put("dateProp",String.valueOf(getDateProp()));
	}
	mosource_prop.put("longProp",String.valueOf(getLongProp()));
	return mosource_prop;

         //<End_getProperties>
  }

  public void setProperties(java.util.Properties p)
  {
         //<Begin_setProperties_java.util.Properties>

	String timeStampPropvalue=p.getProperty("timeStampProp");
	if(timeStampPropvalue!=null)
	{
		timeStampProp=Timestamp.valueOf(timeStampPropvalue);
		p.remove("timeStampProp");
	}

	String booleanPropvalue=p.getProperty("booleanProp");
	if(booleanPropvalue!=null)
	{
		booleanProp= Boolean.valueOf(booleanPropvalue).booleanValue();
		p.remove("booleanProp");
	}

	String stringPropvalue=p.getProperty("stringProp");
	if(stringPropvalue!=null)
	{
		stringProp=stringPropvalue;
		p.remove("stringProp");
	}

	String timePropvalue=p.getProperty("timeProp");
	if(timePropvalue!=null)
	{
		timeProp=Time.valueOf(timePropvalue);
		p.remove("timeProp");
	}

	String intPropvalue=p.getProperty("intProp");
	if(intPropvalue!=null)
	{
		intProp=Integer.parseInt(intPropvalue);
		p.remove("intProp");
	}

	String datePropvalue=p.getProperty("dateProp");
	if(datePropvalue!=null)
	{
		dateProp=Date.valueOf(datePropvalue);
		p.remove("dateProp");
	}

	String longPropvalue=p.getProperty("longProp");
	if(longPropvalue!=null)
	{
		longProp=Long.parseLong(longPropvalue);
		p.remove("longProp");
	}


         //<End_setProperties_java.util.Properties>
  }

}

