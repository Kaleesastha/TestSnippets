
//$Id: ArchivePolicy.java,v 1.1 2002/01/31 04:48:52 nilofer Exp $ 


package com.adventnet.nms.policies;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.adventnet.nms.util.NmsFileUtil;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.management.policydb.PolicyEvent;
import com.adventnet.management.policydb.PeriodicPolicyObject;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil ;
import com.adventnet.nms.store.relational.*;

/**
 * This policy will move the older files that are present under logs directory to Archive.
 * This policy will be executed after the specified interval of time.
 * The default values for logs directory, archive directory are mentioned.
 * The default file pattern is given as nmsout.txt. If the current file is nmsout12.txt, then after
 * the specified time interval , all the files except nmsout12.txt( for example nmsout11.txt, nmsout10.txt
 * etc.) will be moved to archive.If the file  pattern need to be changed, then make corresponding changes
 * in the client side. So only the current file will be present under logs directory.
 **/

public class ArchivePolicy extends PeriodicPolicyObject
{
    //Default values for logs directory, archive directory and file type are given.
    //The Logs directory is stored 
    private String logDir = "logs"; 
    //The file pattern  is stored 
    private String filePattern="nmsout.txt";
    //Archiving directory is stored 
    private String archiveDir="logs/archive";
    //  NMS home.
    private  String home;
    //File rotation is done in NmsFileUtil
    private NmsFileUtil nfu=null;


    /** This policy will run after every 5 hour to move the older files to archive.
        So check every 5 hour **/
    
    public ArchivePolicy() 
    { 
	super();
	period = 1000*60*60*5;  // we'll check whether to execute every 5 hours
	
    }
    
    /**
     * Sets the properties for this policy object
     * @param p the property to be set.
     */

    public void setProperties(Properties p)
    {
		super.setBaseProperties(p);
		String temp;
		temp = (String)p.get("LOG_DIRECTORY");
		if (temp != null)
		{
			logDir = temp;
		}
		temp = (String)p.get("ARCH_DIRECTORY");
		if( temp != null)
		{
			archiveDir = temp;                 ;
		}
		temp = (String)p.get("FILE_PATTERN");
		if(temp != null)
		{
			filePattern = temp;
		}
    }


    /**
     * Returns the properties of this policy object
     * @return "ArchivePolicy properties"
     */

    public Properties getProperties()
    {
	Properties p = super.getBaseProperties();
	p.put("LOG_DIRECTORY",logDir);
	p.put("ARCH_DIRECTORY",archiveDir); 
	p.put("FILE_PATTERN",filePattern); 
	return p;
    }

    /**
     * Returns the help url of this policy object.  The string being relative
     * to the help directory.  
     * @return url as string
     */

    public String getHelpURL()
    {
	return "User_Guide/Java_UI/Policies/policyviewer.html";
    }

    /** 
     * Returns the properties of this policy object 
     * @return "Policy customizer name" 
     */ 
    public String getPolicyObjectCustomizer() {
	return null;
    }


    /**
     * executes action of this policy object.
     * @param policyEvt PolicyEvent
     */

    public  void executeAction(PolicyEvent policyEvt)
    {
        Properties prop=getProperties();
        String logd=prop.getProperty("LOG_DIRECTORY");
        String archd=prop.getProperty("ARCH_DIRECTORY");
        String file=prop.getProperty("FILE_PATTERN");
        String args[]={"LOG_DIRECTORY",logd,"ARCH_DIRECTORY",archd,"FILE_PATTERN",file};
        rotateFilesOfSameType(args);        
    }
    private void rotateFilesOfSameType(String[] args)
    {
        //The older files which has the same type of the given filename are moved to archive and only the current file remains in the logs directory.
        home=PureUtils.rootDir;
        int argc=args.length;
        int argn=0;
        for(argn=0;argn<argc;argn++)
        {
            if((args[argn].equals("LOG_DIRECTORY")) && (argn +1 < argc))
            {
                logDir= args[argn+1];
            }
            if((args[argn].equals("ARCH_DIRECTORY")) && (argn +1 < argc))
            {
                archiveDir=args[argn+1];
            }
            if((args[argn].equals("FILE_PATTERN")) && (argn +1 < argc))
            {
                filePattern=args[argn+1];
            }
        }
        
        File logsDirectory=new File(home+"/"+logDir);
        nfu = new NmsFileUtil(logDir);
        String listOfFiles[]=logsDirectory.list();
        //Get the current file.
        String fileMax=nfu.getFileMax(filePattern);
        String typeOfFile=nfu.getFileType(fileMax);
        int intPart=nfu.getFileIndex(fileMax);
        for(int i=0;i<listOfFiles.length;i++)
        {
            String type=nfu.getFileType(listOfFiles[i]);
            //Get the files of same type of the given file.
            if(typeOfFile.equals(type))
            {
                //The integer part in the file which is less than the current file is moved to Archive
                if((nfu.getFileIndex(listOfFiles[i]))<intPart)
                {
                    File archDirectory=new File(home+"/"+archiveDir);
                    archDirectory.mkdirs();
                    moveFilesToArchive(listOfFiles[i],logDir,archiveDir);
                    //The older files are deleted from the logs directory.
                    File deleteFile=new File(home+"/"+logDir,listOfFiles[i]);
                    if ( deleteFile.exists() )
                    {
                        deleteFile.delete();
                    }
                }
                
            }
        }
        
    }
    //Move the file to Archive.
    private boolean moveFilesToArchive(String filePattern, String logDir, String archiveDir)
    {
        String file_type=nfu.getFileType(filePattern);
        String zipName= file_type+".zip";
        zipName = home+"/"+archiveDir+"/"+zipName;
        // get the file format first
        SimpleDateFormat formatter
            = new SimpleDateFormat ("ddMMMyyyy_hh_mm_ss_SSS");
        File file=new File(home+"/"+logDir, filePattern);
        long modifiedTime=file.lastModified();
        Date currentTime_1=new Date(modifiedTime);
        String timeStamp = formatter.format(currentTime_1);
        try
        {
            ZipEntry zipentry=null;
            // temp zip creation
            OutputStream ops = new FileOutputStream(zipName+".tmp");
            ZipOutputStream zos = new ZipOutputStream(ops);
            copyExistingEntries(zipName , zos);
            zipentry=new ZipEntry(file_type+timeStamp+".txt");
            zos.putNextEntry(zipentry);
            File newFile=new File(home+"/"+logDir, filePattern);
            FileInputStream fis = new FileInputStream(newFile);
            int available = fis.available();
            byte arr[] = new byte[available];
            fis.read(arr);
            if(available > 0)
            {
                zos.write(arr);
            }
            else
            {
                zos.write(new String("no contents").getBytes());
            }
            fis.close();
            zos.flush();
            zos.close();
            ops.close();
            
        }catch(IOException e)
        {
            System.err.println("Problem while moving the files to the Archive");
            e.printStackTrace();
            return false;
        }
        File tempZipFile = new File(zipName);
        tempZipFile.delete();
        
        File orgZipFile = new File(zipName+".tmp");
        orgZipFile.renameTo(new File(zipName));
        return true;
    }


    //The existing entries in the zip file is stored in a temporary file.

    private void copyExistingEntries(String sourceZipFile , ZipOutputStream zos) throws IOException
    {
        // source zip file
        // check existence of an entry
        File sourceFile = new File(sourceZipFile);
        if(!(sourceFile.exists()))
        {
            return;
        }
        ZipInputStream zis = new ZipInputStream(new FileInputStream(sourceZipFile));
        ZipEntry oldZipEntry = null;
        int noOfEntries = 0;
        while((oldZipEntry = zis.getNextEntry()) != null)
        {
            noOfEntries++;
            break;
        }
        zis.close();
        if( noOfEntries <= 0)
        {
            // no entries present in zip file
            return;
        }
        
        ZipFile zf = new ZipFile(new File(sourceZipFile));
        // transfer existing contents to the .tmp file
        for(Enumeration en = zf.entries() ; en.hasMoreElements();)
        {
            ZipEntry zipentry = (ZipEntry)en.nextElement();
            InputStream is = zf.getInputStream(zipentry);
            zos.putNextEntry(new ZipEntry(zipentry.getName()));
            
            while(true)
            {
                byte arr[] = new byte[1024];
                int len = 0;
                if((len = is.read(arr)) < 0)
                {
                    is.close();
                    zos.closeEntry();
                    break;
                }
                zos.write(arr,0,len);
                zos.flush();
            }
        }
        zf.close();

    }

}


