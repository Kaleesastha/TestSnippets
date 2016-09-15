package   com.adventnet.nms.topodb;


//import com.adventnet.nms.topodb.*;
import java.beans.*;
import java.lang.reflect.*;

public class DescCommentedBeanInfo  extends SimpleBeanInfo
{
	private final static  Class   beanClass  = DescCommented.class;

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


			Method getter1 = beanClass.getMethod("getOwnerName",null);
			MethodDescriptor gettermd1 = new MethodDescriptor(getter1);
			gettermd1.setShortDescription("2");
			Class c1[] = { new String().getClass() };
			Method setter1 = beanClass.getMethod("setOwnerName",c1);
			MethodDescriptor settermd1 = new MethodDescriptor(setter1);
			settermd1.setShortDescription("3");

			Method getter2 = beanClass.getMethod("getIntVal",null);
			MethodDescriptor gettermd2 = new MethodDescriptor(getter2);
			gettermd2.setShortDescription("4");
			Class c2[] = { Integer.TYPE   };
			Method setter2 = beanClass.getMethod("setIntVal",c2);
			MethodDescriptor settermd2 = new MethodDescriptor(setter2);
			settermd2.setShortDescription("5");

			Method getter3 = beanClass.getMethod("getStringVal1",null);
			MethodDescriptor gettermd3 = new MethodDescriptor(getter3);
			gettermd3.setShortDescription("6");
			Class c3[] = { new String().getClass() };
			Method setter3 = beanClass.getMethod("setStringVal1",c3);
			MethodDescriptor settermd3 = new MethodDescriptor(setter3);
			settermd3.setShortDescription("7");

			Method getter4 = beanClass.getMethod("getStringVal2",null);
			MethodDescriptor gettermd4 = new MethodDescriptor(getter4);
			gettermd4.setShortDescription("8");
			Class c4[] = { new String().getClass() };
			Method setter4 = beanClass.getMethod("setStringVal2",c4);
			MethodDescriptor settermd4 = new MethodDescriptor(setter4);
			settermd4.setShortDescription("9");

			Method getter5 = beanClass.getMethod("getStringVal3",null);
			MethodDescriptor gettermd5 = new MethodDescriptor(getter5);
			gettermd5.setShortDescription("10");
			Class c5[] = { new String().getClass() };
			Method setter5 = beanClass.getMethod("setStringVal3",c5);
			MethodDescriptor settermd5 = new MethodDescriptor(setter5);
			settermd5.setShortDescription("11");

			MethodDescriptor[]   md = { gettermd, settermd, gettermd1, settermd1,gettermd2, settermd2, gettermd3, settermd3,gettermd4, settermd4, gettermd5, settermd5};
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

			PropertyDescriptor ownerName = new PropertyDescriptor("ownerName",beanClass);
			ownerName.setDisplayName("PRIMARY KEY");
			ownerName.setShortDescription("1");

			PropertyDescriptor stringVal1 = new PropertyDescriptor("stringVal1",beanClass);
			stringVal1.setShortDescription("2");

           	PropertyDescriptor intVal = new PropertyDescriptor("intVal",beanClass);
			intVal.setDisplayName("PRIMARY KEY");
			intVal.setShortDescription("3");

			PropertyDescriptor stringVal2 = new PropertyDescriptor("stringVal2",beanClass);
          	//stringVal2.setShortDescription("4");

          	PropertyDescriptor stringVal3 = new PropertyDescriptor("stringVal3",beanClass);
          	//stringVal3.setShortDescription("5");

			PropertyDescriptor  pd[] = { name, ownerName, stringVal1,intVal, stringVal2, stringVal3 };
			return pd;
		}
		catch (Exception Ex)
		{
			Ex.printStackTrace();
			return null;
		}

	}

}
