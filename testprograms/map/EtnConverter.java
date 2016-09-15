/*

This is a java file to simulate internationalisation of string by appending "$<<" and ">>$" to the beginning and end of every string specified in the *.properties . For every .properties file(say EnglishToNative.properties) it generates a corresponding _en_us.properties file(EnglishToNative_en_us.properties file in this case) where all the English string entries( for eg "Please enter a valid name") will have their corresponding internationalised strings with "$<<" and ">>$" appended (in this case "$<<Please enter a valid name>>$") to the beginning and end correspondingly.

To use this file compile an put it in the <WebNMS-HOME>/html directory and type the foilling line in the command prompt  :
	
	           java EtnConverter *.properties

*/

import java.util.*;
import java.io.*;

public class EtnConverter
{
	public static int counter=0;

	public static void main(String args[])
	{
		for(int i = 0; i < args.length; i++)
		{
			File f = new File(args[i]);
			if(f.isDirectory())
			{
				String[] lists = f.list();
				for(int m=0; m < lists.length; m++)
				{
					if(lists[m].endsWith("_en_us.properties"))
					{
						continue;
					}
					if(lists[m].endsWith(".properties"))
					{
						convertValues(args[i]+File.separator+lists[m]);
					}
				}
			}
			else
			{
				convertValues(args[i]);
			}
		}
	}
	public static void convertValues(String filename)
	{
		Properties p = new Properties();

		String newfilename=filename.substring(0,filename.lastIndexOf("."))+"_en_us.properties";

		try
		{

			p.load(new FileInputStream(filename));
		}
		catch(Exception ex)
		{
			System.out.println("exception while reading filename= " +filename +" "+ ex );
			System.out.println("error message=" + ex.getMessage());

			ex.printStackTrace();
		}

		for(Enumeration e = p.keys(); e.hasMoreElements();)
		{
			String key = (String) e.nextElement();
			if( ! key.equals("<PROMINENT_KEY>Image Type"))
			{
				p.put(key,"$<<"+key+">>$");
				counter++;
			}
		}


		try
		{
			FileOutputStream fos = new FileOutputStream(newfilename);
			p.save(fos,"New");
			System.out.println("Modified " + newfilename);
		}
		catch(Exception ex)
		{
			System.out.println("exception while writing into file " + newfilename + " " + ex);
			ex.printStackTrace();
		}
	}
}



