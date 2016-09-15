package test;

import java.sql.*;


public  class ExtendedMO extends com.adventnet.nms.topodb.ManagedObject
{
	//<Begin_Variable_Declarations>
        private int intdata;
	private int integerdata;
	private float floatdata;
	private int smallintd;
	private int bigintd;
	private double doubledata;
	private float decimaldata;
	private int numericdata;
	//<End_Variable_Declarations>

   public void setIntdata(int intdataArg)
  {
 //<Begin_setIntdata_int> 
 intdata = intdataArg;
  
 //<End_setIntdata_int>
  } 
  
 public int getIntdata()
  {
         //<Begin_getIntdata> 
	return intdata;
  
         //<End_getIntdata>
  } 

 public void setIntegerdata(int integerdataArg)
  {
 //<Begin_setIntegerdata_int> 
 integerdata = integerdataArg;
  
 //<End_setIntegerdata_int>
  } 
  
 public int getIntegerdata()
  {
         //<Begin_getIntegerdata> 
	return integerdata;
  
         //<End_getIntegerdata>
  } 
  
 public void setFloatdata(float floatdataArg)
  {
 //<Begin_setFloatdata_float> 
 floatdata = floatdataArg;
  
 //<End_setFloatdata_float>
  } 
  
 public float getFloatdata()
  {
         //<Begin_getFloatdata> 
	return floatdata;
  
         //<End_getFloatdata>
  } 
  
 public void setSmallintd(int smallintdArg)
  {
 //<Begin_setSmallintd_int> 
 smallintd = smallintdArg;
  
 //<End_setSmallintd_int>
  } 
  
 public int getSmallintd()
  {
         //<Begin_getSmallintd> 
	return smallintd;
  
         //<End_getSmallintd>
  } 
  
 public void setBigintd(int bigintdArg)
  {
 //<Begin_setBigintd_int> 
 bigintd = bigintdArg;
  
 //<End_setBigintd_int>
  } 
  
 public int getBigintd()
  {
         //<Begin_getBigintd> 
	return bigintd;
  
         //<End_getBigintd>
  } 
  
 public void setDoubledata(double doubledataArg)
  {
 //<Begin_setDoubledata_float> 
 doubledata = doubledataArg;
  
 //<End_setDoubledata_float>
  } 
  
 public double getDoubledata()
  {
         //<Begin_getDoubledata> 
	return doubledata;
  
         //<End_getDoubledata>
  } 
  
 public void setDecimaldata(float decimaldataArg)
  {
 //<Begin_setDecimaldata_float> 
 decimaldata = decimaldataArg;
  
 //<End_setDecimaldata_float>
  } 
  
 public float getDecimaldata()
  {
         //<Begin_getDecimaldata> 
	return decimaldata;
  
         //<End_getDecimaldata>
  } 
  
 public void setNumericdata(int numericdataArg)
  {
 //<Begin_setNumericdata_int> 
 numericdata = numericdataArg;
  
 //<End_setNumericdata_int>
  } 
  
 public int getNumericdata()
  {
         //<Begin_getNumericdata> 
	return numericdata;
  
         //<End_getNumericdata>
  } 
  
  public java.util.Properties getProperties()
  {
         //<Begin_getProperties> 
	java.util.Properties mosource_prop;
	mosource_prop = super.getProperties();
        mosource_prop.put("intdata",String.valueOf(getIntdata()));
	mosource_prop.put("integerdata",String.valueOf(getIntegerdata()));
	mosource_prop.put("floatdata",String.valueOf(getFloatdata()));
	mosource_prop.put("smallintd",String.valueOf(getSmallintd()));
	mosource_prop.put("bigintd",String.valueOf(getBigintd()));
	mosource_prop.put("doubledata",String.valueOf(getDoubledata()));
	mosource_prop.put("decimaldata",String.valueOf(getDecimaldata()));
	mosource_prop.put("numericdata",String.valueOf(getNumericdata()));
	return mosource_prop;
  
         //<End_getProperties>
  } 
  
  public void setProperties(java.util.Properties p)
  {
         //<Begin_setProperties_java.util.Properties> 

      String intdatavalue=p.getProperty("intdata");
	if(intdatavalue!=null)
	{ 
		intdata=Integer.parseInt(intdatavalue);
		p.remove("intdata");
	} 


	String integerdatavalue=p.getProperty("integerdata");
	if(integerdatavalue!=null)
	{ 
		integerdata=Integer.parseInt(integerdatavalue);
		p.remove("integerdata");
	} 

	String floatdatavalue=p.getProperty("floatdata");
	if(floatdatavalue!=null)
	{ 
		floatdata=Float.parseFloat(floatdatavalue);
		p.remove("floatdata");
	} 

	String smallintdvalue=p.getProperty("smallintd");
	if(smallintdvalue!=null)
	{ 
		smallintd=Integer.parseInt(smallintdvalue);
		p.remove("smallintd");
	} 

	String bigintdvalue=p.getProperty("bigintd");
	if(bigintdvalue!=null)
	{ 
		bigintd=Integer.parseInt(bigintdvalue);
		p.remove("bigintd");
	} 

	String doubledatavalue=p.getProperty("doubledata");
	if(doubledatavalue!=null)
	{ 
		doubledata=Float.parseFloat(doubledatavalue);
		p.remove("doubledata");
	} 

	String decimaldatavalue=p.getProperty("decimaldata");
	if(decimaldatavalue!=null)
	{ 
		decimaldata=Float.parseFloat(decimaldatavalue);
		p.remove("decimaldata");
	} 

	String numericdatavalue=p.getProperty("numericdata");
	if(numericdatavalue!=null)
	{ 
		numericdata=Integer.parseInt(numericdatavalue);
		p.remove("numericdata");
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

