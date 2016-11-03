/*$Id: DataExportImpl.java,v 1.2 2007/07/04 05:33:38 barathv Exp $*/
package test;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.DataExport;
import com.adventnet.nms.severity.SeverityInfo;
import java.util.TimeZone;

/**
 * This class in an implementation of the DataExport interface. This implementation is used to 
 * generate a report in CSV format with the incoming data and file name.
 */
public class DataExportImpl implements DataExport
{
	/**
	* Constructor for this class.
	*/
	public DataExportImpl()
	{
	}
	
	/**
	* Generates a CSV report file with the given filename using the Vector of incoming data, When
	* this method is used the columns names will not be exported into the CSV files as columns headers.
	* @param data The vector of data for which the report has to be generated. Here the structure
	* of the vector should be only of the following format:<br><br>
	* This vector should contain only java.util.Properties as elements. Here each java.util.Property
	* object should represent a row of data to be exported. In this java.util.Property object, key
	* should contain the name of the property and value should contain the value of that property.
	* For example, if an user want to export the properties snmpport and community of the 
	* ManagedObject table, then the structure of this vector should be as follows:<br><br>
	* Each java.util.Properties in the Vector will represent one row of data in the 
        * ManagedObject table. In this java.util.Property object, the key would contain the
        * property name "snmpport". The value would contain the value of the property "snmpport".
        * The next element of the java.util.Property object will contain the property name "community"
        * and the value would contain the value of the property "community" and so on. Similarly
        * the next java.util.Property object in the vector would contain the next row of data in the 
        * ManagedObject table.<br><br>
	* @param filename The name of the file which which the report has to be generated. If only the
	* file name is provided as say "test.csv", then this file would be generated under WebNMS home
	* directory. If the file name is specified as say "users/test.csv", the file test.csv would be 
	* stored under "WebNMS/users/" directory.
	* @return Returns true if the report has been successfully generated and returns false 
	* if any error occurs while generating the report.
	*/
	public boolean generateReport(Vector data, String filename)
	{
		FileOutputStream fs = null;
		try
		{
			filename = new String(PureUtils.rootDir + filename);
			File f = new File (filename);
			if (f.exists())
			{
				if (!f.canWrite())
				{
					NmsLogMgr.MISCERR.fail(NmsUtil.GetString("dataexport.csv.error.inopening.file")+filename,null); // No i18n
					return false;
				}
			}
			File parent = f.getParentFile();
			if(parent == null || !parent.exists())
			{
				 NmsLogMgr.MISCERR.fail(NmsUtil.GetString("dataexport.csv.error.non.existingdirectory")+filename,null); //No i18n
				return false;
			}
			SeverityInfo info = SeverityInfo.getInstance();
			TimeZone time = TimeZone.getDefault();
			SimpleDateFormat df = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a"); //No i18n
			df.setTimeZone(time);
			fs = new FileOutputStream(f);
			for (Enumeration en1 = data.elements();en1.hasMoreElements();)
			{
				StringBuffer buff = new StringBuffer();
				Properties pn1 = (Properties)en1.nextElement();
				for (Enumeration en2 = pn1.propertyNames();en2.hasMoreElements();)
				{
					String key = (String)en2.nextElement();
					String val = pn1.getProperty(key);
					if (key.equalsIgnoreCase("statusChangeTime") || key.equalsIgnoreCase("statusUpdateTime") || key.equalsIgnoreCase("time") || key.equalsIgnoreCase("modTime") || key.equalsIgnoreCase("createTime"))
					{
						try
						{
							Long l = new Long(val);
							Date d = new Date(l.longValue());
							val = df.format(d);
						}
						catch (NumberFormatException ne)
						{
						}
					}
					if (key.equalsIgnoreCase("severity") || key.equalsIgnoreCase("status") || key.equalsIgnoreCase("previousseverity"))
					{
						try
						{
							Integer i = new Integer(val);
							String severity = info.getName(i.intValue());
							if (severity != null)
							{
								val = severity;
							}
						}
						catch (NumberFormatException nx)
						{
						}
					}
					if (val != null)
					{
						int count = val.indexOf(","); //No i18n
						if ( count != -1)
						{
							val = "\"" + val + "\""; // No i18n 
						}
					}
					if (val == null)
					{
						val = ""; // no i18n
					}
					buff.append(val);
					buff.append(","); // No i18n
				}
				String total = buff.toString();
				total = total.substring(0,total.length() - 1) + "\n"; // No i18n
				fs.write(total.getBytes());
			}
		}
		catch (Exception nf)
		{
			NmsLogMgr.MISCERR.fail(NmsUtil.GetString("dataexport.csv.error.generating.csv")+nf.getMessage(),null); // No i18n
			return false;
		}
		finally
		{
		   try
		   {
			fs.close();
		   }	
		   catch (Exception io)
		   {
		   	System.err.println("Error in closing the IO Streams "+io); // No I18n
		   }
		}

		return true;
	}
	
	/**
	* Generates a CSV report file with the given filename using the Vector of incoming data with the 
	* column names as the column headers of the CSV file.
	* @param data The vector of data for which the report has to be generated. Here the structure
	* of the vector should be only of the following format:<br><br>
	* This vector should contain only java.util.Properties as elements. Here each java.util.Property
	* object should represent a row of data to be exported. In this java.util.Property object, key
	* should contain the name of the property and value should contain the value of that property.
	* For example, if an user want to export the properties snmpport and community of the 
	* ManagedObject table, then the structure of this vector should be as follows:<br><br>
	* Each java.util.Properties in the Vector will represent one row of data in the 
        * ManagedObject table. In this java.util.Property object, the key would contain the
        * property name "snmpport". The value would contain the value of the property "snmpport".
        * The next element of the java.util.Property object will contain the property name "community"
        * and the value would contain the value of the property "community" and so on. Similarly
        * the next java.util.Property object in the vector would contain the next row of data in the 
        * ManagedObject table.<br><br>
        * @param columnNames The string array of columns names, Here the string elements in the 
        * columns names must be equal to the key of the property objects in the vector of data.Also
        * the length of the columnsNames array must be equal to the number of elements in the property
        * object of the vector. <br><br>
	* @param filename The name of the file which which the report has to be generated. If only the
	* file name is provided as say "test.csv", then this file would be generated under WebNMS home
	* directory. If the file name is specified as say "users/test.csv", the file test.csv would be 
	* stored under "WebNMS/users/" directory.
	* @return Returns true if the report has been successfully generated and returns false 
	* if any error occurs while generating the report.
	*/
	public boolean generateReport(Vector data, String[] columnNames, String filename)
	{
		FileOutputStream fs = null;
		try
		{
			filename = new String(PureUtils.rootDir + filename);
			File f = new File (filename);
			if (f.exists())
			{
				if (!f.canWrite())
				{
					NmsLogMgr.MISCERR.fail(NmsUtil.GetString("dataexport.csv.error.inopening.file")+filename,null); // No i18n
					return false;
				}
			}
			File parent = f.getParentFile();
			if(parent == null || !parent.exists())
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("dataexport.csv.error.non.existingdirectory")+filename,null); // No i18n
				return false;
			}
			SeverityInfo info = SeverityInfo.getInstance();
			TimeZone time = TimeZone.getDefault();
			SimpleDateFormat df = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a"); //No i18n
			df.setTimeZone(time);
			fs = new FileOutputStream(f);
			StringBuffer col = new StringBuffer();
			for (int i =0; i < columnNames.length;i++)
			{
				String name = columnNames[i];
				int l1 = name.indexOf(","); //No i18n
				if ( l1 != -1)
				{
					name = "\"" + name + "\""; // No i18n
				}
				col.append(name);
				col.append(","); // No i18n
			}
			String line = col.toString();
			line = line.substring(0,line.length() - 1) + "\n"; //No i18n
			fs.write(line.getBytes());
			
			for (Enumeration en1 = data.elements();en1.hasMoreElements();)
			{
				StringBuffer buff = new StringBuffer();
				Properties pn1 = (Properties)en1.nextElement();
				for (int j =0; j < columnNames.length;j++)
				{
					String key = columnNames[j];
					String val = pn1.getProperty(key);
					if (key.equalsIgnoreCase("statusChangeTime") || key.equalsIgnoreCase("statusUpdateTime") || key.equalsIgnoreCase("time") || key.equalsIgnoreCase("modTime") || key.equalsIgnoreCase("createTime"))
					{
						try
						{
							Long l = new Long(val);
							Date d = new Date(l.longValue());
							val = df.format(d);
						}
						catch (NumberFormatException ne)
						{
						}
					}
					if (key.equalsIgnoreCase("severity") || key.equalsIgnoreCase("status")|| key.equalsIgnoreCase("previousseverity"))
					{
						try
						{
							Integer i = new Integer(val);
							String severity = info.getName(i.intValue());
							if (severity != null)
							{
								val = severity;
							}
						}
						catch (NumberFormatException nx)
						{
						}
					}
					if (val != null)
					{
						int count = val.indexOf(","); // No i18n
						if ( count != -1)
						{
							val = "\"" + val + "\""; // No i18n 
						}
					}
					if (val == null)
					{
						val = ""; // no i18n
					}
					buff.append(val);
					buff.append(","); // No i18n
				}
				String total = buff.toString();
				total = total.substring(0,total.length() - 1) + "\n"; // No i18n
				fs.write(total.getBytes());
			}
		}
		catch (Exception nf)
		{
			NmsLogMgr.MISCERR.fail(NmsUtil.GetString("dataexport.csv.error.generating.csv")+nf.getMessage(),null); // no i18n
			return false;
		}
		finally
		{
		   try
		   {
			fs.close();
		   }	
		   catch (Exception io)
		   {
		   	System.err.println("Error in closing the IO Streams "+io); // No I18n
		   }
		}

		return true;

	}
	
	/**
	* Generates a CSV report file with the given filename using the Vector of incoming data with the 
	* displayed column names as the column headers of the CSV file.
	* @param data The vector of data for which the report has to be generated. Here the structure
	* of the vector should be only of the following format:<br><br>
	* This vector should contain only java.util.Properties as elements. Here each java.util.Property
	* object should represent a row of data to be exported. In this java.util.Property object, key
	* should contain the name of the property and value should contain the value of that property.
	* For example, if an user want to export the properties snmpport and community of the 
	* ManagedObject table, then the structure of this vector should be as follows:<br><br>
	* Each java.util.Properties in the Vector will represent one row of data in the 
        * ManagedObject table. In this java.util.Property object, the key would contain the
        * property name "snmpport". The value would contain the value of the property "snmpport".
        * The next element of the java.util.Property object will contain the property name "community"
        * and the value would contain the value of the property "community" and so on. Similarly
        * the next java.util.Property object in the vector would contain the next row of data in the 
        * ManagedObject table.<br><br>
        * @param DBcolumnNames The string array of DBcolumns names, Here the string elements in the 
        * columns names must be equal to the key of the property objects in the vector of data.Also
        * the length of the columnsNames array must be equal to the number of elements in the property
        * object of the vector. <br><br>
	* @param displayedColumnNames The string array of column names that has to be exported as column
	* headers of the CSV file.
	* @param filename The name of the file which which the report has to be generated. If only the
	* file name is provided as say "test.csv", then this file would be generated under WebNMS home
	* directory. If the file name is specified as say "users/test.csv", the file test.csv would be 
	* stored under "WebNMS/users/" directory.
	* @return Returns true if the report has been successfully generated and returns false 
	* if any error occurs while generating the report.
	*/

	public boolean generateReport(Vector data, String[] DBcolumnNames,String[] displayedColumnNames, String filename)
	{
		FileOutputStream fs = null;
		try
		{
			filename = new String(PureUtils.rootDir + filename);
			File f = new File (filename);
			if (f.exists())
			{
				if (!f.canWrite())
				{
					NmsLogMgr.MISCERR.fail(NmsUtil.GetString("dataexport.csv.error.inopening.file")+filename,null); // No i18n
					return false;
				}
			}
			File parent = f.getParentFile();
			if(parent == null || !parent.exists())
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("dataexport.csv.error.non.existingdirectory")+filename,null); // No i18n
				return false;
			}
			SeverityInfo info = SeverityInfo.getInstance();
			TimeZone time = TimeZone.getDefault();
			SimpleDateFormat df = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a"); // No i18N
			df.setTimeZone(time);
			fs = new FileOutputStream(f);
			StringBuffer col = new StringBuffer();
			for (int i =0; i < displayedColumnNames.length;i++)
			{
				String name = displayedColumnNames[i];
				int l1 = name.indexOf(","); // No i18n
				if ( l1 != -1)
				{
					name = "\"" + name + "\""; // No i18n
				}
				col.append(name);
				col.append(","); // No i18n
			}
			String line = col.toString();
			line = line.substring(0,line.length() - 1) + "\n"; // No i18n
			fs.write(line.getBytes());
			
			for (Enumeration en1 = data.elements();en1.hasMoreElements();)
			{
				StringBuffer buff = new StringBuffer();
				Properties pn1 = (Properties)en1.nextElement();
				for (int j =0; j < DBcolumnNames.length;j++)
				{
					String key = DBcolumnNames[j];
					String val = pn1.getProperty(key);
					if (key.equalsIgnoreCase("statusChangeTime") || key.equalsIgnoreCase("statusUpdateTime") || key.equalsIgnoreCase("time") || key.equalsIgnoreCase("modTime") || key.equalsIgnoreCase("createTime"))
					{
						try
						{
							Long l = new Long(val);
							Date d = new Date(l.longValue());
							val = df.format(d);
						}
						catch (NumberFormatException ne)
						{
						}
					}
					if (key.equalsIgnoreCase("severity") || key.equalsIgnoreCase("status")|| key.equalsIgnoreCase("previousseverity"))
					{
						try
						{
							Integer i = new Integer(val);
							String severity = info.getName(i.intValue());
							if (severity != null)
							{
								val = severity;
							}
						}
						catch (NumberFormatException nx)
						{
						}
					}
					if (val != null)
					{
						int count = val.indexOf(","); //No i18n
						if ( count != -1)
						{
							val = "\"" + val + "\""; //No i18n
						}
					}
					if (val == null)
					{
						val = ""; // no i18n
					}
					buff.append(val);
					buff.append(","); // No i18n
				}
				String total = buff.toString();
				total = total.substring(0,total.length() - 1) + "\n"; //No i18n
				fs.write(total.getBytes());
			}
		}
		catch (Exception nf)
		{
			NmsLogMgr.MISCERR.fail(NmsUtil.GetString("dataexport.csv.error.generating.csv")+nf.getMessage(),null); // No i18n
			return false;
		}
		finally
		{
		   try
		   {
			fs.close();
		   }	
		   catch (Exception io)
		   {
		   	System.err.println("Error in closing the IO Streams "+io); // No I18n
		   }
		}
		return true;

	}


}
