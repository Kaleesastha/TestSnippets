//$Id: CustomClass.java,v 1.2 2007/02/22 15:02:57 srajeswari Exp $
//sample file to be invoked as custom action

package com.adventnet.nms.example;

//util imports
import java.util.Enumeration;
import java.util.Properties;

//WebNMS imports.
import com.adventnet.nms.util.*;

/**
 * This class just displays the properties given to it 
 * by the WebNMS.
 */
public class CustomClass implements com.adventnet.nms.util.CustomClassInterface
{
	
	/**
	 * Implementation of CustomClassInterface.
	 */
   public void setProperties(Properties p[])
   {
	
	  System.out.println("The properties that are given to customclass are:" + p.length);
	  if (p == null)
	  {
	 	return;
	  }
	for(int count =0;count < p.length;count ++)
	{
		System.out.println("Property  "+count);
		System.out.println("------------------------------------");
		for(Enumeration enumerate = p[count].keys();enumerate.hasMoreElements();)
		{
			String propertyName = (String)enumerate.nextElement();
			String propertyValue = (String)p[count].get(propertyName);
			System.out.println("The property name is :"+ propertyName);
			System.out.println("The property value is :"+propertyValue);
		}
		System.out.println("------------------------------------");
	}	
 }

 
	
}

 