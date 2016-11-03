//$Id: IntegerDynamicPropertyAccessor.java,v 1.1 2008/10/24 07:12:59 sudharshan Exp $
package com.adventnet.nms.store.relational.hbn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import org.hibernate.property.Getter;
import org.hibernate.util.ReflectHelper;
import org.hibernate.HibernateException;
import org.hibernate.PropertyAccessException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.SessionImplementor;

import com.adventnet.nms.store.relational.hbn.DynamicPropertyAccessor.DynamicGetter;

public class IntegerDynamicPropertyAccessor extends DynamicPropertyAccessor 
{
	
	public static final class DynamicGetter implements Getter 
	{
		private Class clazz;
		private final transient Method method;
		private final String propertyName;
		private DynamicGetter(Class clazz, Method method, String propertyName) 
		{
			this.clazz=clazz;
			this.method=method;
			this.propertyName=propertyName;
		}

		public Object get(Object target) throws HibernateException 
		{
			try 
			{
				Object val = method.invoke(target, new Object[] { propertyName});
				if(val==null) return val;
				return Integer.parseInt((String)val);
			}
			catch (InvocationTargetException ite) 
			{
				throw new PropertyAccessException(ite,"Exception occurred inside",false,clazz,propertyName);//No I18N
			}
			catch (IllegalAccessException iae) 
			{
				throw new PropertyAccessException(iae,"IllegalAccessException occurred while calling",false,clazz,propertyName);//No I18N
			}
			catch (IllegalArgumentException iae) 
			{
				throw new PropertyAccessException(iae,"IllegalArgumentException occurred calling",false,clazz,propertyName);//No I18N
			}
		}

		public Object getForInsert(Object target, Map mergeMap, SessionImplementor session) 
		{
			return get( target );
		}

		public Class getReturnType() 
		{
			return method.getReturnType();
		}

		public Method getMethod() 
		{
			return method;
		}

		public String getMethodName() 
		{
			return method.getName();
		}

		public String toString() 
		{
			return "DynamicGetter(" + clazz.getName() + '.' + propertyName + ')';//No I18N
		}

		Object readResolve() 
		{
			return createGetter(clazz, propertyName);
		}
	}
	
	public Getter getGetter(Class theClass, String propertyName) throws PropertyNotFoundException 
	{
		return createGetter(theClass, propertyName);
	}

	public static Getter createGetter(Class theClass, String propertyName)throws PropertyNotFoundException 
	{
		DynamicGetter result = getGetterOrNull(theClass, propertyName);
		if (result==null) 
		{
			throw new PropertyNotFoundException("Could not find a getter for "+propertyName+" in class "+theClass.getName());//No I18N
		}
		return result;
	}

	private static DynamicGetter getGetterOrNull(Class theClass, String propertyName) 
	{
		if (theClass==Object.class || theClass==null) return null;
		Method method = getterMethod(theClass, propertyName);
		if (method!=null) 
		{
			if ( !ReflectHelper.isPublic(theClass, method) ) method.setAccessible(true);
			return new DynamicGetter(theClass, method, propertyName);
		}
		else 
		{
			DynamicGetter getter = getGetterOrNull( theClass.getSuperclass(), propertyName );
			if (getter==null) 
			{
				Class[] interfaces = theClass.getInterfaces();
				for ( int i=0; getter==null && i<interfaces.length; i++ ) 
				{
					getter=getGetterOrNull( interfaces[i], propertyName );
				}
			}
			return getter;
		}
	}
}
