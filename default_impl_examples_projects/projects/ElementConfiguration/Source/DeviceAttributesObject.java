//$Id: DeviceAttributesObject.java,v 1.1 2006/08/29 13:56:51 build Exp $

package com.adventnet.nms.config ; 

import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class DeviceAttributesObject extends java.util.Vector
{
	private static Vector attributesVector = null;

	public DeviceAttributesObject(Object obj)
	{
		
		if(attributesVector != null)
		{
			for(int i=0;i<attributesVector.size();i++)
				addElement(attributesVector.elementAt(i));
		}
			
		setElementAt(obj,0);
	}

	public DeviceAttributesObject(Vector attributesVector)
	{
		this.attributesVector = attributesVector;
	}

	public DeviceAttributesObject(Object [][] array)
	{
		int size = array.length;
		
		attributesVector = new Vector();
		
		for(int i = 0; i < size; i++)
		{
			attributesVector.addElement(array[i][1]);
		}
	}
}




