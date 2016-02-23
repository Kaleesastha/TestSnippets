package com.adventnet.nms.alertui;

import java.util.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.alertui.*;

public class ModifyTitleNmsListView extends AlertApplet
{
	protected void removeShowAllButton() 
	{    
		List_label.setText(NmsClientUtil.GetString(getNodeValue("TREE-NAME")));
		super.removeShowAllButton();
	}
	protected void addShowAllButton() 
	{
		super.addShowAllButton();
	}

	public void setProperties(Properties prop) 
	{
		super.setProperties(prop);


		String arg = prop.getProperty("Arg");
		String search = prop.getProperty("AnyOrAll");
		if( arg != null && arg.equals("temporary"))
		{
			List_label.setText("abc" + prop.getProperty("name") + "xxx" );
		}
		else if( search != null && search.equals("MatchAll"))
		{
			List_label.setText(NmsClientUtil.GetString(getNodeValue("TREE-NAME")) + " yyyy"); //Changes the Temporary CV to <Name>yyyy
		}
	}

	private String getNodeValue(String key)
	{
		if(key == null)
			return null;

		Hashtable xmlNodeProperties = (Hashtable)getCurrentNodeProperties();
		if(xmlNodeProperties != null)
		{
			for(Enumeration enumm = xmlNodeProperties.keys(); enumm.hasMoreElements();)
			{
				String attribute =(String)enumm.nextElement();
				if(attribute != null && attribute.equals(key))
				{
					return (String)xmlNodeProperties.get(key);
				}
			}
		}
		else
		{
			return (String)prop_of_node.get(key);
		}

		return null;
	}
	void setLabel()
	{
		super.setLabel();
	}

}
