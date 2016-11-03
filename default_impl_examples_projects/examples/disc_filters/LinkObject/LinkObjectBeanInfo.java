/**
$Id: LinkObjectBeanInfo.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/
package com.adventnet.nms.topodb;
import java.beans.*;
import java.lang.reflect.Method;

public class LinkObjectBeanInfo extends SimpleBeanInfo
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
			
			PropertyDescriptor source = new PropertyDescriptor("source",beanClass);
			source.setShortDescription("1");

			PropertyDescriptor destination = new PropertyDescriptor("destination",beanClass);
			destination.setShortDescription("2");

			PropertyDescriptor ipADDR = new PropertyDescriptor("ipADDR",beanClass);
			ipADDR.setShortDescription("3");

			PropertyDescriptor pd[] = { name, source, destination, ipADDR };
			return pd;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	private final static Class beanClass = LinkObject.class;
}
