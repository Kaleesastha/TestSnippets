package com.adventnet.nms.topodb;


//import com.adventnet.nms.topodb.*;


public  class ownernamefirst extends com.adventnet.nms.topodb.ManagedObject
{
	//<Begin_Variable_Declarations>
	public String stringVal1 = "aaa";
	public String stringVal2 = "bbb";
	public String stringVal3 = "ccc";
	public int intVal = 10;
	//<End_Variable_Declarations>


  public  void setStringVal1(String stringVal1Arg)
  {
    //<Begin_setStringVal1_String>
    if(stringVal1Arg != null)
    {
	stringVal1 = stringVal1Arg;
    }

    //<End_setStringVal1_String>
  }

  public  String getStringVal1()
  {
        //<Begin_getStringVal1>
	return stringVal1;

        //<End_getStringVal1>
  }

  public  void setStringVal2(String stringVal2Arg)
  {
    //<Begin_setStringVal2_String>
    if(stringVal2Arg != null)
    {
	stringVal2 = stringVal2Arg;
    }

    //<End_setStringVal2_String>
  }

  public  String getStringVal2()
  {
        //<Begin_getStringVal2>
	return stringVal2;

        //<End_getStringVal2>
  }

  public  void setStringVal3(String stringVal3Arg)
  {
    //<Begin_setStringVal3_String>
    if(stringVal3Arg != null)
    {
	stringVal3 = stringVal3Arg;
    }

    //<End_setStringVal3_String>
  }

  public  String getStringVal3()
  {
        //<Begin_getStringVal3>
	return stringVal3;

        //<End_getStringVal3>
}

  public  void setIntVal(int intValArg)
  {
    //<Begin_setIntVal_int>
    intVal = intValArg;

    //<End_setIntVal_int>
  }

  public  int getIntVal()
  {
        //<Begin_getIntVal>
	return intVal;

        //<End_getIntVal>
  }

  public java.util.Properties getProperties()
  {
        //<Begin_getProperties>
	java.util.Properties mosource_prop;
	mosource_prop = super.getProperties();

	mosource_prop.put("stringVal1",getStringVal1());
	mosource_prop.put("stringVal2",getStringVal2());
	mosource_prop.put("stringVal3",getStringVal3());
	mosource_prop.put("intVal",String.valueOf(getIntVal()));
	return mosource_prop;

        //<End_getProperties>
  }

  public void setProperties(java.util.Properties p)
  {
        //<Begin_setProperties_java.util.Properties>

	String stringVal1value=p.getProperty("stringVal1");
	if(stringVal1value!=null)
	{
		stringVal1=stringVal1value;
		p.remove("stringVal1");
	}

	String stringVal2value=p.getProperty("stringVal2");
	if(stringVal2value!=null)
	{
		stringVal2=stringVal2value;
		p.remove("stringVal2");
	}

	String stringVal3value=p.getProperty("stringVal3");
	if(stringVal3value!=null)
	{
		stringVal3=stringVal3value;
		p.remove("stringVal3");
	}

	String intValvalue=p.getProperty("intVal");
	if(intValvalue!=null)
	{
		intVal=(new Integer(intValvalue).intValue());
		p.remove("intVal");
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
