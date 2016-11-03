//$Id: RemoveJarsFromPath.java,v 1.2.4.1 2012/01/25 05:13:23 karen.r Exp $
package com.adventnet.nms.tools.confchanges;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ArrayList;

import com.adventnet.tools.update.PrePostProcessInterface;
import com.adventnet.tools.update.UpdateManagerConts;
import com.adventnet.tools.update.installer.UpdateManager;

/* This file is a PreInstallation invokation class to remove the NmsServerClasses.jar from
 * the System classpath by a simple hack.
 * This hack assumes that the SystemClassLoader is a URLClassLoader. No other go to resolve the 5 SP1 UpdateManager crash.
 * This crash is mainly due to the NmsServerClasses.jar in the UpdateManager.bat/sh files.
 */
public class RemoveJarsFromPath implements PrePostProcessInterface
{
	private String  home = null;
	
	private String[] propertiesFileList = {"html/EnglishToNative.properties","html/launcher.properties"};		
	private int length = propertiesFileList.length;
	public String getErrorMsg() 
	{
		return null;
	}

	public Object[] getFilesToModify() 
	{	
		return null;
	}

	private URL[] removeArrayElement(int n, URL[] urls)
	{
		URL[] newarray = new URL[urls.length - 1];
		for (int i = 0;i < urls.length;i++)
		{
			if (i < n)
			{
				newarray[i] = urls[i];
			}
			if (i > n)
			{
				newarray[i-1] = urls[i];
			}
		}
		return newarray;
	}

	public int install(Properties arg0) 
	{
		home = arg0.getProperty("home");
		removeJarsFromPath();
		if(isFirstPPM())
		{
		    removeBaseVersionDirectory();
		    deleteUnnecessaryFolders();			
		    nameChange();
		    return UpdateManagerConts.SUCCESS;
		}

	
		return UpdateManagerConts.SUCCESS;
	}
	public boolean isFilesToBeBackedUp()
	{
		return false;
	}

	public int revert(Properties arg0) 
	{
		home = arg0.getProperty("home");	
		for(int i=0;i < length;i++)
		{
        	    try
		    {
                        File destinationFile=new File(home+ File.separator + propertiesFileList[i]);
                        File sourceFile = new File(home + File.separator + "conf" + File.separator + "backup" + File.separator + "PropertyFiles" + File.separator + propertiesFileList[i]);
			if(destinationFile.exists() && sourceFile.exists())
                        {
				copyFile(sourceFile, destinationFile);
			}
		    }
		    catch(Exception e)
			{
			}

		}
		return UpdateManagerConts.SUCCESS;
	}

	public static void main(String args[])
	{
		RemoveJarsFromPath mv = new RemoveJarsFromPath();
		mv.install(null);
	}
	private void removeJarsFromPath()
	{
		try
		{
			URLClassLoader sysloader = (URLClassLoader)ClassLoader.getSystemClassLoader();
			URL[] urls = sysloader.getURLs();
			URL[] modifiedUrls=null;
			int length=urls.length;
			ArrayList<URL>   arrList = new ArrayList<URL>();
			boolean modify=false;

			for(int i = 0; i < length; i++)
			{
				URL url= urls[i];
				String path= url.getPath();
				if(path.indexOf("NmsServerClasses.jar")==-1 && path.indexOf("crimson.jar")==-1 && path.indexOf("xalan.jar")==-1 && path.indexOf("jaxp.jar")==-1)
				    {
					arrList.add(urls[i]);
				    }
				else
				    {
					modify=true;
					System.err.println(path+" found in classpath");//no i18n
				    }

			}
			if(modify)
			{
			        modifiedUrls= arrList.toArray(new URL[arrList.size()]);
				Field f= URLClassLoader.class.getDeclaredField("ucp");//no i18n
				f.setAccessible(true);

				//URLClassPath newUcp = new URLClassPath(modifiedUrls);
				//f.set(sysloader, newUcp);

				//Commented the above two lines and going with Reflection as below to avoid any compilation dependency during build..
				Class urlc = Class.forName("sun.misc.URLClassPath");//No I18N
				Class constructorType[] ={URL[].class};
				Constructor cons =urlc.getConstructor(constructorType);
				Object constructorValues[] = {modifiedUrls};
				f.set(sysloader, cons.newInstance(constructorValues));
			}	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	private void removeBaseVersionDirectory()
	{
		// Following code added to remove already existing files and directories under <WebNMS_HOME>/conf/backup/BaseVersion. If the files under thisdirectory are already exist , while applying/reverting 5 SP ppm's major issues may occur.
		String file = home + File.separator + "conf" + File.separator + "backup" + File.separator + "BaseVersion"; //No I18N
		deleteFiles(file);
	}

	private void deleteFiles(String fileName)
	{
		File file;
		try
		{
			file = new File(fileName);
			if(file.exists())
			{
				if(file.isDirectory())
				{
					String fileList[] = file.list();
					int fileCount = fileList.length;
					for(int i = 0 ; i < fileCount ; i++)
					{
						deleteFiles(fileName+ File.separator +fileList[i]);
					}
					file.delete(); // deleting the directory
				}
				else
				{
					file.delete();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Exception while deleting the file "+fileName);  //No I18N
		
		}
	}
	private void deleteUnnecessaryFolders()
	{
		//Deleting unnecessary folders present in webnms permanently. 	
		String[] list= {home + File.separator + "conf"+ File.separator + "propagation.filters", home + File.separator + "examples"+ File.separator + "FaultManagement"+  File.separator + "propagation_filters", home + File.separator + "webclient" + File.separator + "common" + File.separator + "i18n"}; //No I18N
		for(int i=0;i<list.length;i++)
		{	
			deleteFiles(list[i]);
		}
	}
	private void nameChange()
	{
		File backupDir = new File(home + File.separator + "conf" + File.separator + "backup" + File.separator + "PropertyFiles"); //No I18N
		if(!backupDir.exists()){
                        backupDir.mkdirs();
                }
		
		Properties sourceProp = new Properties();
		Properties properties = new Properties();  // Stores oldValues which need to be changed to newValues 
		String[] keyList = {"nms-support@adventnet.com","sales@adventnet.com","AdventNet, Inc.","www.adventnet.com","info@adventnet.com"};
		//html/EnglishToNativeComplete.properties - This file contains only keys , not values. So not updating this file. 	
		// html/RuntimeAdministrationResources.properties  - Only one values ("Protocols supported by Web NMS discovery process.") exist which matches below criteria. 
		// {"classes/com/adventnet/nms/startclient/LoginInfoUI.properties"} - directly present in 5 sp1 branch
		//{"html/LoginInfoUI.properties"}
		//{"html/ShutDownNmsServerResources.properties"}; //thee values with "web nms" are present, is this really need to be changed?
		//This  html/UpdateManagerResources.properties file don't exist 
		//String[] confFileList = {"conf/launcher_conf.txt","conf/clientparameters.conf","conf/WebNMS.jnlp","jar_files/AboutDialogProps.xml"}; These files will be updated using Configuration Updater.  
		
		//{"html/ReinitializeNmsResources.properties","html/CLIBrowser.properties","html/MibBrowser.properties","html/tl1craftinterface_i18n.properties","html/UpdateManagerResources_en_US.properties"}; - These files contains Adventnet reference only , don't have website or email id references. 
		properties.put("nms-support@adventnet.com","nms-support@webnms.com");	
		properties.put("sales@adventnet.com","sales@webnms.com");	
		properties.put("AdventNet, Inc.","ZohoCorp.");
		properties.put("www.adventnet.com","www.webnms.com/webnms");	
		properties.put("info@adventnet.com","nms-support@webnms.com");	

		File sourceFile = null;
		for(int i=0;i <length;i++)
		{
		    try
		    {
			
			boolean isCopy = false;
			sourceFile=new File(home+ File.separator + propertiesFileList[i]);
                        sourceProp=new Properties();
			if(sourceFile.exists())
			{
				FileInputStream sourceFileIn=null;
				try
				{
					sourceFileIn=new FileInputStream(sourceFile);  // sourceFile is the file which need to be updated
					sourceProp.load(sourceFileIn);   // sourceProp is the properties variable contains all the lines in sourceFile
				}
				catch(Exception e)
				{
					continue;
				}
				finally
				{
					try
					{
						if(sourceFileIn!=null)	
						{
							sourceFileIn.close();
						}
					}
					catch(Exception e)
					{}
				}

				for(String oldValue : keyList )  
				{
					String newValue =  properties.getProperty(oldValue); // properties contains old values &  new values, old values will get replaced with new values in the file. 
				// Load the properties file.
				Enumeration keysFromSourceFile = sourceProp.keys();	
				
				while(keysFromSourceFile.hasMoreElements())  // Will loop all the keys in file 	
				{
					String keyFromFile = (String) keysFromSourceFile.nextElement();
					String valueFromFile = (String) sourceProp.get(keyFromFile);  

					if(valueFromFile.indexOf(oldValue)!=-1)
					{
						if(valueFromFile==null || valueFromFile.trim().equals(""))
						{
							continue;
						}
						if(valueFromFile.startsWith("<PROMINENT_KEY>"))
						{
							valueFromFile=valueFromFile.substring("<PROMINENT_KEY>".length());			
						}
					
						int index=valueFromFile.indexOf(oldValue);
						int newValueIndex=newValue.indexOf(oldValue);
	
						while(index!=-1)
						{
							valueFromFile=valueFromFile.substring(0,index)+newValue+valueFromFile.substring(index+oldValue.length());
							index=valueFromFile.indexOf(oldValue,newValueIndex+index+1);
						}
					

						sourceProp.setProperty(keyFromFile,valueFromFile);
					        isCopy = true;	   
					} // End if loop

				} //Enumeration loop for file
			} // end for loop 
			if(isCopy)
			{
				File backUpFile  = new File(backupDir + File.separator + propertiesFileList[i]);
				copyFile(sourceFile,backUpFile);
				FileOutputStream fout= null;
				try
				{
					fout =new FileOutputStream(sourceFile);
					sourceProp.store(fout,"");
				}
				catch(Exception e)
				{
					System.err.println("Error while saving "+sourceFile.getPath()+" file in PrePostProcessInterface : "+e.getMessage());
				}
				finally
				{
				   try
				   {
					if(fout!=null)
					{
						fout.close();
					}
				   }
				   catch(Exception e){}
				}

			}
		    } // End of if  condition (for source file exist )
		}	
		catch(Exception e)
		{
			System.out.println("Exception in PrePostProcessInterface while doing name change :"+e.getMessage());
			e.printStackTrace();
		}
            }  // for loop for fileList end

	} //nameChange method end	

	public boolean isFirstPPM()
	{
		String currentVersion = UpdateManager.getCurrentServicePackVersion();
		if(currentVersion!=null && !currentVersion.trim().equals("") && currentVersion.indexOf("5.0-SP-0.")==-1)   
		{
			return false;
		}
		return true;
	}
	private void copyFile(File sourceFile,File destinationFile)
	{

    	    if(!destinationFile.exists())
	    {
		String path = destinationFile.getPath();
		int index = path.lastIndexOf(File.separator);
		String str = path.substring(0,index); 
	        File file = new File(str);
		file.mkdirs();
	    }

	    FileInputStream fis = null;
	    byte[] read = null;
	    try
            {
                fis=new FileInputStream(sourceFile);
                read=new byte[(int)sourceFile.length()];
                fis.read(read);
            }
            catch(Exception e)
            {
                System.err.println("Error while reading file "+sourceFile.getPath()+" in PrePostProcessInterface :"+e.getMessage());
                e.printStackTrace();
            }
	    finally
	    {
		try
		{
			if(fis!=null)
			{	
		                fis.close();
			}
	       }
	       catch(Exception e){}
	   }
           writeByteToFile(read,destinationFile.toString());
	}

	private void  writeByteToFile(byte[] source,String filePath)
	{
		 FileOutputStream fout = null;
		try
		{
			fout=new FileOutputStream(filePath);
			fout.write(source);
			fout.flush();
		}
		catch(IOException ioe)
		{
			System.err.println("Exception while writing to "+filePath+" \n"+ioe.getMessage());
			ioe.printStackTrace();
		}
		finally
		{
			try
			{
				if(fout!=null)
				{
			            fout.close();
				}
			}
			catch(Exception e){}
		}
	}	

}
