//$Id: UpdateConfWrapper.java,v 1.2.4.3 2013/06/04 08:05:13 wesley Exp $

package com.adventnet.nms.tools.confchanges;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.ArrayList;
import java.util.zip.ZipFile;
import java.net.URL;
import java.net.URLClassLoader;

import com.adventnet.tools.update.PrePostProcessInterface;
import com.adventnet.tools.update.UpdateManagerConts;
import com.adventnet.tools.update.UpdateManagerUtil;
import com.adventnet.tools.update.installer.UpdateManager;

public class UpdateConfWrapper implements PrePostProcessInterface
{

    
    public int install(Properties p)
    {
    	String home = p.getProperty("home");
    	
    	//Added check to copy lib file during post installation process.
    	copyLibFiles(home);
    	
        int j = invokeMethod("install", p);
        return j;
    }
    
    private void copyLibFiles(String home)
    {
    	String osname = System.getProperty("os.name").toLowerCase();
    	String osarch = System.getProperty("os.arch").toLowerCase();
    	
    	File folder64bit = new File(home + File.separator + "lib"+File.separator+"64bit");
    	
    	File linux32bit = new File(home + File.separator + "lib"+File.separator+"librxtxSerial.so");
    	File window32bit = new File(home + File.separator + "lib"+File.separator+"rxtxSerial.dll");
    	
    	File linux64bit = new File(home + File.separator + "lib"+File.separator+"64bit"+File.separator+"librxtxSerial.so");
		File window64bit = new File(home + File.separator + "lib"+File.separator+"64bit"+File.separator+"rxtxSerial.dll");
		
    	if(osname.contains("linux"))
    	{
        	if(osarch.contains("amd64") && linux64bit.exists())
        	{
        		copyFile(linux64bit, linux32bit);
        	}
    	}
    	else if(osname.contains("windows"))
    	{
    		if(osarch.contains("amd64")  && window64bit.exists())
        	{
        		copyFile(window64bit, window32bit);
        	}
    	}
    	linux64bit.delete();
    	window64bit.delete();
    	folder64bit.delete();
    }
    
    private void copyFile(File oldFilePath,File newFilePath)
    {
    	try{
    	FileInputStream fis=new FileInputStream(oldFilePath);
        byte[] read=new byte[(int)oldFilePath.length()];
        fis.read(read);
        fis.close();
        FileOutputStream fout=new FileOutputStream(newFilePath);
        fout.write(read);
        fout.flush();
        fout.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    public int revert(Properties p)
    {
        int j = invokeMethod("revert", p);
        return j;
    }

		
    
    public int invokeMethod(String methodName, Properties p) 
    {
        int res=UpdateManagerConts.FAILURE_REVERT_ALL;
        
        try
        {
            URLClassLoader urlc = new URLClassLoader(getDependentJars(p.getProperty("context")),null);
            
            String fileName="com.adventnet.nms.tools.confchanges.UpdateConfChanges";//no i18n

            
            Class c1 = urlc.loadClass(fileName);
            if(c1 != null)
            {
                System.out.println(" Successfully Loaded " + c1.getName());//no i18n

            }
            else
            {
                System.out.println(" Failed loading the class " +fileName);//no i18n

            }
            Object obj = c1.newInstance();
            Method method = c1.getMethod(methodName, new Class[]{java.util.Properties.class});
            res = (Integer)method.invoke(obj, new Object[]{p});
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return res;
    }
    
    public String error = "Configuration changes Failure";
    
    public String getErrorMsg()
    {
        return error;
    }

    public Object[] getFilesToModify()
    {
        return null;
    }

    public boolean isFilesToBeBackedUp()
    {
        return true;
    }


    private URL[] getDependentJars(String context)
    {

    	String nmsClasses = System.getProperty("user.dir")+ File.separator + "classes";//no i18n
    	ArrayList<String> files = new ArrayList<String>();
    	files.add(nmsClasses);
    	files.add(nmsClasses+File.separator+"NmsServerClasses.jar");//no i18n
    	files.add(nmsClasses+File.separator+"AdventNetUpdateManagerInstaller.jar");//no i18n

    	if(!context.equalsIgnoreCase("POLLER"))//no i18n
    	{
    		if(context.indexOf("CLIENT")!=-1)//no i18n
    		{
    			files.remove(nmsClasses+File.separator+"NmsServerClasses.jar");//no i18n
    		}
    		files.add(nmsClasses+File.separator+"NmsClientClasses.jar");//no i18n
    		files.add(nmsClasses+File.separator+"JimiProClasses.jar");//no i18n
    		files.add(nmsClasses+File.separator+"xalan.jar");//no i18n
    		files.add(nmsClasses+File.separator+"crimson.jar");//no i18n
    		files.add(nmsClasses+File.separator+"jaxp.jar");//no i18n
    	}
    	int length=files.size();
    	URL[] urls = new URL [length];
    	try
    	{
    		for(int i=0;i<length;i++)
    		{
    			String fileName=files.get(i);
    			File file = new File(fileName);
    			if(file.exists())
    			{
    				urls[i]=file.toURL();
    			}
    			else
    			{
    				System.err.println("Could not Reload the file: "+fileName); //no i18n  
    			}
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return urls;
    }
}
