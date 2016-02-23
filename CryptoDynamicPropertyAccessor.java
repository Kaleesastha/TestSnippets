//$Id: CryptoDynamicPropertyAccessor.java $
package com.adventnet.nms.store.relational.hbn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import org.hibernate.property.Getter;
import org.hibernate.property.Setter;
import org.hibernate.util.ReflectHelper;
import org.hibernate.HibernateException;
import org.hibernate.PropertyAccessException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.SessionFactoryImplementor;

import com.adventnet.nms.store.relational.hbn.DynamicPropertyAccessor.DynamicGetter;
import com.adventnet.nms.store.relational.hbn.DynamicPropertyAccessor.DynamicSetter;

import com.adventnet.security.authorization.Coding;

/**
 * For any user property of ManagedObject or its subclasses of type String,
 * specify this accessor to enable encryption and decryption.
 */
public class CryptoDynamicPropertyAccessor extends DynamicPropertyAccessor
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
			  String encryptVal = null;
			  try
			  {
				// here you can use your own encryption algorithm instead of the proprietary algorithm
				// used below.
				encryptVal = Coding.convertToNewBase(value.toString());

			  }catch(Exception e)
			  { e.printStackTrace();
		  	  }
			  return encryptVal;
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

		protected static Method getterMethod(Class theClass, String propertyName)
		{
			Method method = null;
			try
			{
				method = theClass.getMethod("getDynamicUserProperty",new Class[] {String.class});
			}catch(NoSuchMethodException nsme)
			{
				System.err.println("getterMethod: no such method getDynamicUserProperty "+nsme.getMessage());
				return null;
			}catch(NullPointerException npe)
			{
				System.err.println("getterMethod: method name null "+npe.getMessage());
				return null;
			}
			return method;
		}


	   public static final class DynamicSetter implements Setter
	   {
	      private Class clazz;
	      private final transient Method method;
	      private final String propertyName;

	      private DynamicSetter(Class clazz, Method method, String propertyName)
	      {
	         this.clazz=clazz;
		 this.method=method;
		 this.propertyName=propertyName;
	      }

	      public void set(Object target, Object value, SessionFactoryImplementor factory) throws HibernateException
	      {
	         try
		 {
			  // here you can use your own decryption logic instead of the proprietary algorithm
			  // used below.
			String decryptVal = Coding.convertFromBase(val.toString());
		    method.invoke( target, new Object[] { propertyName,decryptVal } );
		 }
		 catch (NullPointerException npe)
		 {
		    if ( value==null && method.getParameterTypes()[0].isPrimitive() )
		    {
		       throw new PropertyAccessException(npe,"DynamicPropertyAccessor : Null value was assigned to a property of primitive type",true,clazz,propertyName);
	            }
		    else
		    {
		       throw new PropertyAccessException(npe,"DynamicPropertyAccessor : NullPointerException occurred while calling",true,clazz,propertyName);
	            }
		 }
		 catch (InvocationTargetException ite)
		 {
		    throw new PropertyAccessException(ite,"DynamicPropertyAccessor : Exception occurred inside",true,clazz,propertyName);
	         }
		 catch (IllegalAccessException iae)
		 {
	            throw new PropertyAccessException(iae,"DynamicPropertyAccessor : IllegalAccessException occurred while calling",true,clazz,propertyName);
	         }
		 catch (IllegalArgumentException iae)
		 {
		    if ( value==null && method.getParameterTypes()[0].isPrimitive() )
		    {
		       throw new PropertyAccessException(iae,"DynamicPropertyAccessor : Null value was assigned to a property of primitive type",true,clazz,propertyName);
	            }
		    else
		    {
		       throw new PropertyAccessException(iae,"DynamicPropertyAccessor : IllegalArgumentException occurred while calling",true,clazz,propertyName);
	            }
		 }
      	}

      	      public Method getMethod()
		      {
		         return method;
		      }

		      public String getMethodName()
		      {
		         return method.getName();
		      }

		      Object readResolve()
		      {
		         return createSetter(clazz, propertyName);
		      }

		      public String toString()
		      {
		         return "DynamicSetter(" + clazz.getName() + '.' + propertyName + ')';
		      }
	}

         public Setter getSetter(Class theClass, String propertyName)	throws PropertyNotFoundException
	     {
	        return createSetter(theClass, propertyName);
	     }

	     private static Setter createSetter(Class theClass, String propertyName) throws PropertyNotFoundException
	     {
	        DynamicSetter result = getSetterOrNull(theClass, propertyName);
	        if (result==null)
	        {
	           throw new PropertyNotFoundException("Could not find a setter for property "+propertyName+" in class " +theClass.getName());
	        }
	        return result;
	     }

	     private static DynamicSetter getSetterOrNull(Class theClass, String propertyName)
	     {
	        if (theClass==Object.class || theClass==null) return null;
	        Method method = setterMethod(theClass, propertyName);
	        if (method!=null)
	        {
	           if ( !ReflectHelper.isPublic(theClass, method) ) method.setAccessible(true);
	           return new DynamicSetter(theClass, method, propertyName);
	        }
	        else
	        {
	           DynamicSetter setter = getSetterOrNull( theClass.getSuperclass(), propertyName );
	           if (setter==null)
	  	 {
	  	    Class[] interfaces = theClass.getInterfaces();
	              for ( int i=0; setter==null && i<interfaces.length; i++ )
	  	    {
	  	       setter=getSetterOrNull( interfaces[i], propertyName );
	              }
	  	 }
	  	 return setter;
	        }
	     }

	     	private static Method setterMethod(Class theClass, String propertyName)
		 	{
		 		DynamicGetter getter = getGetterOrNull(theClass, propertyName);
		 		Class returnType = (getter==null) ? null : getter.getReturnType();
				Method method = null;
				try
				{
					method = theClass.getMethod("setDynamicUserProperty",new Class[] {String.class,Object.class});
				}catch(NoSuchMethodException nsme)
				{
					System.err.println("setterMethod: no such method setDynamicUserProperty "+nsme.getMessage());
					return null;
				}catch(NullPointerException npe)
				{
					System.err.println("setterMethod: method name null "+npe.getMessage());
					return null;
				}
				return method;
		 	}


}
