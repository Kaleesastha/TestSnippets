package   com.test.provisioning;


//import com.adventnet.nms.topodb.*;
import java.beans.*;
import java.lang.reflect.*;
import java.sql.*;

public class AllDataTypeNetworkInventoryBeanInfo  extends SimpleBeanInfo
{
	private final static  Class   beanClass  = AllDataTypeNetworkInventory.class;

	public BeanDescriptor  getBeanDescriptor()
	{
		return new BeanDescriptor(beanClass);
	}

	public MethodDescriptor[]    getMethodDescriptors()
	{
		try{

			Method  getter = beanClass.getMethod("getName",null);
			MethodDescriptor gettermd = new MethodDescriptor(getter);
			gettermd.setShortDescription("0");
			Class c[] = { new String().getClass() };
			Method setter = beanClass.getMethod("setName",c);
			MethodDescriptor settermd = new MethodDescriptor(setter);
			settermd.setShortDescription("1");


			Method getter1 = beanClass.getMethod("getIntprop",null);
			MethodDescriptor gettermd1 = new MethodDescriptor(getter1);
			gettermd1.setShortDescription("2");
			Class c1[] = { int.class };
			Method setter1 = beanClass.getMethod("setIntprop",c1);
			MethodDescriptor settermd1 = new MethodDescriptor(setter1);
			settermd1.setShortDescription("3");

			Method getter2 = beanClass.getMethod("getBooleanprop",null);
			MethodDescriptor gettermd2 = new MethodDescriptor(getter2);
			gettermd2.setShortDescription("4");
			Class c2[] = { boolean.class };
			Method setter2 = beanClass.getMethod("setBooleanprop",c2);
			MethodDescriptor settermd2 = new MethodDescriptor(setter2);
			settermd2.setShortDescription("5");

			Method getter3 = beanClass.getMethod("getFloatprop",null);
			MethodDescriptor gettermd3 = new MethodDescriptor(getter3);
			gettermd3.setShortDescription("6");
			Class c3[] = { float.class };
			Method setter3 = beanClass.getMethod("setFloatprop",c3);
			MethodDescriptor settermd3 = new MethodDescriptor(setter3);
			settermd3.setShortDescription("7");

			Method getter4 = beanClass.getMethod("getLongprop",null);
			MethodDescriptor gettermd4 = new MethodDescriptor(getter4);
			gettermd4.setShortDescription("8");
			Class c4[] = { long.class };
			Method setter4 = beanClass.getMethod("setLongprop",c4);
			MethodDescriptor settermd4 = new MethodDescriptor(setter4);
			settermd4.setShortDescription("9");

			Method getter5 = beanClass.getMethod("getDoubleprop",null);
			MethodDescriptor gettermd5 = new MethodDescriptor(getter5);
			gettermd5.setShortDescription("10");
			Class c5[] = { double.class };
			Method setter5 = beanClass.getMethod("setDoubleprop",c5);
			MethodDescriptor settermd5 = new MethodDescriptor(setter5);
			settermd5.setShortDescription("11");

			Method getter6 = beanClass.getMethod("getDateprop",null);
			MethodDescriptor gettermd6 = new MethodDescriptor(getter6);
			gettermd6.setShortDescription("12");
			Class c6[] = { Date.class };
			Method setter6 = beanClass.getMethod("setDateprop",c6);
			MethodDescriptor settermd6 = new MethodDescriptor(setter6);
			settermd6.setShortDescription("13");

			Method getter7 = beanClass.getMethod("getTimeprop",null);
			MethodDescriptor gettermd7 = new MethodDescriptor(getter7);
			gettermd7.setShortDescription("14");
			Class c7[] = { Time.class };
			Method setter7 = beanClass.getMethod("setTimeprop",c7);
			MethodDescriptor settermd7 = new MethodDescriptor(setter7);
			settermd7.setShortDescription("15");

			Method getter8 = beanClass.getMethod("getTimestampprop",null);
			MethodDescriptor gettermd8 = new MethodDescriptor(getter8);
			gettermd8.setShortDescription("16");
			Class c8[] = { Timestamp.class };
			Method setter8 = beanClass.getMethod("setTimestampprop",c8);
			MethodDescriptor settermd8 = new MethodDescriptor(setter8);
			settermd8.setShortDescription("17");

			Method getter9 = beanClass.getMethod("getStringpprop",null);
			MethodDescriptor gettermd9 = new MethodDescriptor(getter9);
			gettermd9.setShortDescription("18");
			Class c9[] = { new String().getClass() };
			Method setter9 = beanClass.getMethod("setStringpprop",c9);
			MethodDescriptor settermd9 = new MethodDescriptor(setter9);
			settermd9.setShortDescription("19");

			
			MethodDescriptor[]   md = { gettermd, settermd, gettermd1, settermd1,gettermd2, settermd2, gettermd3, settermd3,gettermd4, settermd4, gettermd5, settermd5, gettermd6, settermd6, gettermd7, settermd7, gettermd8, settermd8, gettermd9, settermd9};
			return md;
		}
		catch(Exception Ex)
		{
			Ex.printStackTrace();
			return null;
		}

	}

	public PropertyDescriptor[] getPropertyDescriptors()
	{
		try{

			PropertyDescriptor name = new PropertyDescriptor("name",beanClass);
			name.setDisplayName("PRIMARY KEY");
			name.setShortDescription("0");

//			PropertyDescriptor ownerName = new PropertyDescriptor("ownerName",beanClass);
//			ownerName.setDisplayName("PRIMARY KEY");
//			ownerName.setShortDescription("1");

			PropertyDescriptor intprop = new PropertyDescriptor("intprop",beanClass);
			intprop.setShortDescription("1");

           	PropertyDescriptor booleanprop = new PropertyDescriptor("booleanprop",beanClass);
			booleanprop.setShortDescription("2");

			PropertyDescriptor floatprop = new PropertyDescriptor("floatprop",beanClass);
          	floatprop.setShortDescription("3");

          	PropertyDescriptor longprop = new PropertyDescriptor("longprop",beanClass);
          	longprop.setShortDescription("4");

          	PropertyDescriptor doubleprop = new PropertyDescriptor("doubleprop",beanClass);
          	doubleprop.setShortDescription("5");

          	PropertyDescriptor dateprop = new PropertyDescriptor("dateprop",beanClass);
          	dateprop.setShortDescription("6");

          	PropertyDescriptor timeprop = new PropertyDescriptor("timeprop",beanClass);
          	timeprop.setShortDescription("7");

          	PropertyDescriptor timestampprop = new PropertyDescriptor("timestampprop",beanClass);
          	timestampprop.setShortDescription("8");

          	PropertyDescriptor stringprop = new PropertyDescriptor("stringpprop",beanClass);
          	stringprop.setShortDescription("9");

			PropertyDescriptor  pd[] = { name, intprop, booleanprop, floatprop, longprop, doubleprop, dateprop, timeprop, timestampprop, stringprop };
			return pd;
		}
		catch (Exception Ex)
		{
			Ex.printStackTrace();
			return null;
		}

	}

}
