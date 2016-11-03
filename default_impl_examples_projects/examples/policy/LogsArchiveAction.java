package test;


import java.text.SimpleDateFormat;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.Enumeration;
import java.util.Date;
import java.util.Calendar;
import java.util.Vector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

import com.adventnet.management.policydb.PolicyAction;
import com.adventnet.management.policydb.PolicyEvent;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.management.log.NmsFileUtil;
import com.adventnet.nms.util.NmsLogMgr;

/**
* This class executes an action for copying all the log files under log
* directory to a specified directory. This action takes the backup of all
* the log files. The directory where the log files are backed up is
* LogsArchiveDir under <WebNMS Home> directory. This policy will copy all the
* log files and place it as the zip file with the name as 
* Logs_BackUp_month_day_year_hour_minute under LogsArchiveDir.
* For every time the action is executed, the zip file name will be changed
* with respect to the date, month, year etc.
*/

public class LogsArchiveAction  implements PolicyAction
{
	public String getKey()
	{
		return "MoveToArchives";
	}
	public int getPriority()
	{
		return 5;
	}

	/**
	* This method copy all the log files from the logs directory and place
	* it as the zip file under the directory "LogsArchiveDir". This method 
	* will be invoked when the policy condition is satisfied while executing
	* a policy. The zip file name will be as
	* Logs_BackUp_month_day_year_hour_minute
	* While executing this action, the current month, day , hour etc will
	* be taken.
	* @param pe Policy Event
	*/
	public void executeAction(PolicyEvent pe)
	{
            String arcDirName="LogsArchiveDir";
            File arcDir=new File(PureUtils.rootDir+File.separator+arcDirName);
            if(!arcDir.exists())
            {
                arcDir.mkdirs();
            }
            Vector logsDirVect=NmsLogMgr.getRelativelogDirectories();
            for(Enumeration en=logsDirVect.elements();en.hasMoreElements();)
            {
                String logDir=(String)en.nextElement();
                File logFiles=new File(PureUtils.rootDir+File.separator+logDir);
                String[] files=logFiles.list();
                for(int i=0;i<files.length;i++)
                {
                    moveFilesToArchive(files[i],logDir,"LogsArchiveDir");
                }
                
            }
            
	}

	/**
	* This method returns the current month.
	*/	
    
    private String month(int i)
    {
        switch(i)
        {
        case 0: return "JAN";
        case 1: return "FEB";
        case 2: return "MAR";
        case 3: return "APR";
        case 4: return "MAY";
        case 5: return "JUN";
        case 6: return "JUL";
        case 7: return "AUG";
        case 8: return "SEP";
        case 9: return "OCT";
        case 10: return "NOV";
        case 11: return "DEC";
        }
        return "";
    }

   /**
    * This method returns the zip file name . All the files will begin
    * with the name  Logs_BackUp_ and followed by the month, day etc
    * The current date is passed as the argument.
    */
    private String getFileName(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
 
        String minute = String.valueOf(cal.get(Calendar.MINUTE));
        if(cal.get(Calendar.MINUTE)<10) minute="0"+minute;
        String filename ="Logs_BackUp_"+ month(cal.get(Calendar.MONTH)) +
            + cal.get(Calendar.DAY_OF_MONTH) + "_"
            + cal.get(Calendar.YEAR) + "_"
            + cal.get(Calendar.HOUR_OF_DAY) + "_"
            + minute;
        return filename;
 
    }                                

   /**
    * This method archives the files from the logs directorty to the specified
    * directory . If already the zip file is present, then the new file will
    * be appended to the zip else a new zip file will be created and the file
    * is zipped into it. The zip file name will be as
    * Logs_BackUp_month_day_year_hour_minute.zip
    */
    private boolean moveFilesToArchive(String filePattern, String logDir, String archiveDir)
    {
        NmsFileUtil nfu= nfu = new NmsFileUtil(logDir);;
        String file_type=nfu.getFileType(filePattern);
        
        String zipName= getFileName(new Date())+".zip";
        zipName = PureUtils.rootDir+"/"+archiveDir+"/"+zipName;
        // get the file format first
        SimpleDateFormat formatter = new SimpleDateFormat ("ddMMMyyyy_hh_mm_ss_SSS");
        File file=new File(PureUtils.rootDir+"/"+logDir, filePattern);
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
            File newFile=new File(PureUtils.rootDir+"/"+logDir, filePattern);
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
