package com.adventnet.nms.provisioning.examples;
import com.adventnet.management.config.InventoryHandler;
import com.adventnet.management.config.xml.InventoryInput;
import com.adventnet.nms.util.PureUtils;
import java.io.FileInputStream;
import java.util.StringTokenizer;
import java.io.*;
/**
	This example class gets the inventory input form a file and provides it
	to the provisionng framework for processing. The file from which data is 
	obtained is &lt;Provisioning Home&gt;/examples/tempaltes/usingInventoryInput/InventoryInputData.txt 
**/
public class InventoryInputProcessor implements InventoryHandler
{
	/**
		The value attribute of InventoryInput tag is assigend with a datum 
		obtained from a file. First the MOName and MOField attribute of the
		InventoryInput tag is fetched. If there is any valid entry corresponding 
		to this MOName and MOField attributes, then that entry is set to the 
		value attribute.If no valid entry is available, default attribute's 
		content is set to the value attribute.
		
	**/
	public InventoryInput getInventory(InventoryInput i)
	{
		FileInputStream fis=null;
		String value=null;
		try
		{
			fis=new FileInputStream(PureUtils.rootDir+"/examples/provisioning/templates/usingInventoryInput/InventoryInputData.txt");
			byte b[] =new byte[fis.available()];
			fis.read(b);
			String s=new String(b);
			StringTokenizer st=new StringTokenizer(s,System.getProperty("line.separator"));
			String name=i.getMOName();
			String field=i.getMOField();
			while (st.hasMoreTokens())
			{
				StringTokenizer innerST=new StringTokenizer(st.nextToken()," ");
				if((innerST.nextToken().equals(name))&&(innerST.nextToken().equals(field)))
				{
					value=innerST.nextToken();
					break;
				}
			}
			
		}
		catch(Exception e)
		{
			System.err.println("Error in processing inventory input through using_InventoryHandler");
		}
		finally
		{
			if (fis!=null)
			{
				try
				{
					fis.close();
				}
				catch(Exception e)
				{
					System.err.println("Cannot close inventory input text file using_InventoryHandler.txt");
				}
			}
		}
		if (value!=null)
		{
			i.setAttribute("value",value);
		}
		else if ((i.getAttribute("default")!=null)&&(i.getAttribute("default").length()>0))
		{
			i.setAttribute("value",i.getAttribute("default"));
		}
		else
		{
			System.err.println("Cannot assign a valid value to the inventory input in usig_InventoryHandler class");
		}
		return i;
	}
}
