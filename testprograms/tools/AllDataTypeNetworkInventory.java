package com.test.provisioning;

import com.adventnet.nms.topodb.*;

import java.sql.*;


public  class AllDataTypeNetworkInventory extends com.adventnet.nms.provisioning.inventorydb.NetworkInventory
{
	//<Begin_Variable_Declarations>
	public int intprop;
	public boolean booleanprop;
	public float floatprop;
	public long longprop;
	public double doubleprop;
	public Date dateprop;
	public Time timeprop;
	public Timestamp timestampprop;
	public String stringpprop;
	//<End_Variable_Declarations>
	public void setName(String name)
	{
		super.setName(name);
	}

	public String getName()
	{
		return super.getName();
	}

	public  void setIntprop(int intpropArg)
	{
		//<Begin_setIntprop_int> 
		intprop = intpropArg;

		//<End_setIntprop_int>
	} 

	public  int getIntprop()
	{
		//<Begin_getIntprop> 
		return intprop;

		//<End_getIntprop>
	} 

	public  void setBooleanprop(boolean booleanpropArg)
	{
		//<Begin_setBooleanprop_boolean> 
		booleanprop = booleanpropArg;

		//<End_setBooleanprop_boolean>
	} 

	public  boolean getBooleanprop()
	{
		//<Begin_getBooleanprop> 
		return booleanprop;

		//<End_getBooleanprop>
	} 

	public  void setFloatprop(float floatpropArg)
	{
		//<Begin_setFloatprop_float> 
		floatprop = floatpropArg;

		//<End_setFloatprop_float>
	} 

	public  float getFloatprop()
	{
		//<Begin_getFloatprop> 
		return floatprop;

		//<End_getFloatprop>
	} 

	public  void setLongprop(long longpropArg)
	{
		//<Begin_setLongprop_long> 
		longprop = longpropArg;

		//<End_setLongprop_long>
	} 

	public  long getLongprop()
	{
		//<Begin_getLongprop> 
		return longprop;

		//<End_getLongprop>
	} 

	public  void setDoubleprop(double doublepropArg)
	{
		//<Begin_setDoubleprop_double> 
		doubleprop = doublepropArg;

		//<End_setDoubleprop_double>
	} 

	public  double getDoubleprop()
	{
		//<Begin_getDoubleprop> 
		return doubleprop;

		//<End_getDoubleprop>
	} 

	public  void setDateprop(Date datepropArg)
	{
		//<Begin_setDateprop_Date> 
		dateprop = datepropArg;

		//<End_setDateprop_Date>
	} 

	public  Date getDateprop()
	{
		//<Begin_getDateprop> 
		return dateprop;

		//<End_getDateprop>
	} 

	public  void setTimeprop(Time timepropArg)
	{
		//<Begin_setTimeprop_Time> 
		timeprop = timepropArg;

		//<End_setTimeprop_Time>
	} 

	public  Time getTimeprop()
	{
		//<Begin_getTimeprop> 
		return timeprop;

		//<End_getTimeprop>
	} 

	public  void setTimestampprop(Timestamp timestamppropArg)
	{
		//<Begin_setTimestampprop_Timestamp> 
		timestampprop = timestamppropArg;

		//<End_setTimestampprop_Timestamp>
	} 

	public  Timestamp getTimestampprop()
	{
		//<Begin_getTimestampprop> 
		return timestampprop;

		//<End_getTimestampprop>
	} 

	public  void setStringpprop(String stringppropArg)
	{
		//<Begin_setStringpprop_String> 
		if(stringppropArg != null)
		{
			stringpprop = stringppropArg;
		}

		//<End_setStringpprop_String>
	} 

	public  String getStringpprop()
	{
		//<Begin_getStringpprop> 
		return stringpprop;

		//<End_getStringpprop>
	} 

	public java.util.Properties getProperties()
	{
		//<Begin_getProperties> 
		java.util.Properties mosource_prop;
		mosource_prop = super.getProperties();

		mosource_prop.put("intprop",String.valueOf(getIntprop()));
		mosource_prop.put("booleanprop",String.valueOf(getBooleanprop()));
		mosource_prop.put("floatprop",String.valueOf(getFloatprop()));
		mosource_prop.put("longprop",String.valueOf(getLongprop()));
		mosource_prop.put("doubleprop",String.valueOf(getDoubleprop()));
		if(getDateprop()!=null)
		{
			mosource_prop.put("dateprop",String.valueOf(getDateprop()));
		}
		if(getTimeprop()!=null)
		{
			mosource_prop.put("timeprop",String.valueOf(getTimeprop()));
		}
		if(getTimestampprop()!=null)
		{
			mosource_prop.put("timestampprop",String.valueOf(getTimestampprop()));
		}
		if(getStringpprop()!=null)
		{
			mosource_prop.put("stringpprop",getStringpprop());
		}
		return mosource_prop;

		//<End_getProperties>
	} 

	public void setProperties(java.util.Properties p)
	{
		//<Begin_setProperties_java.util.Properties> 

		String intpropvalue=p.getProperty("intprop");
		if(intpropvalue!=null)
		{ 
			intprop=Integer.parseInt(intpropvalue);
			p.remove("intprop");
		} 

		String booleanpropvalue=p.getProperty("booleanprop");
		if(booleanpropvalue!=null)
		{ 
			booleanprop= Boolean.valueOf(booleanpropvalue).booleanValue();
			p.remove("booleanprop");
		} 

		String floatpropvalue=p.getProperty("floatprop");
		if(floatpropvalue!=null)
		{ 
			floatprop=Float.parseFloat(floatpropvalue);
			p.remove("floatprop");
		} 

		String longpropvalue=p.getProperty("longprop");
		if(longpropvalue!=null)
		{ 
			longprop=Long.parseLong(longpropvalue);
			p.remove("longprop");
		} 

		String doublepropvalue=p.getProperty("doubleprop");
		if(doublepropvalue!=null)
		{ 
			doubleprop=Double.parseDouble(doublepropvalue);
			p.remove("doubleprop");
		} 

		String datepropvalue=p.getProperty("dateprop");
		if(datepropvalue!=null)
		{ 
			dateprop=Date.valueOf(datepropvalue);
			p.remove("dateprop");
		} 

		String timepropvalue=p.getProperty("timeprop");
		if(timepropvalue!=null)
		{ 
			timeprop=Time.valueOf(timepropvalue);
			p.remove("timeprop");
		} 

		String timestamppropvalue=p.getProperty("timestampprop");
		if(timestamppropvalue!=null)
		{ 
			timestampprop=Timestamp.valueOf(timestamppropvalue);
			p.remove("timestampprop");
		} 

		String stringppropvalue=p.getProperty("stringpprop");
		if(stringppropvalue!=null)
		{ 
			stringpprop=stringppropvalue;
			p.remove("stringpprop");
		} 
		super.setProperties( p);


		//<End_setProperties_java.util.Properties>
	} 


} 

