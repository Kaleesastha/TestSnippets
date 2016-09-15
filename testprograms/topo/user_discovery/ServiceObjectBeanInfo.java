/**
$Id: ServiceObjectBeanInfo.java,v 1.1 2001/08/08 11:45:10 tsgovind Exp $
*/
package com.adventnet.nms.topodb;

import java.beans.*;
import java.lang.reflect.Method;

public class ServiceObjectBeanInfo extends SimpleBeanInfo
{
	public BeanDescriptor getBeanDescriptor()
	{
		BeanDescriptor bd = new BeanDescriptor(beanClass);
		return bd;
	}
	public MethodDescriptor[] getMethodDescriptors()
	{
		try
		{
			Method getter = beanClass.getMethod("getName",null);
			MethodDescriptor gettermd = new MethodDescriptor(getter);
			gettermd.setShortDescription("0");
			
			Class c[] =  { new String().getClass() };
			Method setter = beanClass.getMethod("setName",c);
			MethodDescriptor settermd = new MethodDescriptor(setter);
			settermd.setShortDescription("1");

			MethodDescriptor md[] = { gettermd ,settermd } ;
			return md;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
  
	public PropertyDescriptor[] getPropertyDescriptors()
	{
		try
		{
			PropertyDescriptor name = new PropertyDescriptor("name",beanClass);
			name.setDisplayName("PRIMARY KEY");
			name.setShortDescription("0");
			
			PropertyDescriptor serviceType = new PropertyDescriptor("serviceType",beanClass);
			serviceType.setShortDescription("1");

			PropertyDescriptor port = new PropertyDescriptor("port",beanClass);
			port.setShortDescription("2");
	
			/*PropertyDescriptor byteArrayTest = new PropertyDescriptor("byteArrayTest",beanClass);
			byteArrayTest.setShortDescription("3");*/

			PropertyDescriptor pd[] = { name, serviceType, port };
			return pd;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	private final static Class beanClass = ServiceObject.class;
}

