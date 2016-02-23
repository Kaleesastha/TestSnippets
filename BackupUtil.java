/* $Id: BackupUtil.java,v 1.1.2.1 2004/03/15 07:13:30 rajagopal Exp $ */

/*
 * @(#)BackupUtil.java
 * Copyright (c) 1996-2004 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */

package jdbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import com.adventnet.nms.util.XMLDataReader;
import com.adventnet.nms.util.XMLDataWriter;
import com.adventnet.nms.util.XMLNode;

/**
 * Utility class for doing backup and restore of files present under WebNMS
 * directory.   
 *
 * Methods of this class is used by BackUpImpl and RestoreBackup classes for
 * taking backup of specified files and restoring the same.
 *
 * @author Rajagopal N
 * @version $Revision: 1.1.2.1 $
 * @since WebNMS 4.5.0 + SP1
 */
class BackupUtil
{
    /** 
	 * A method to check whether the given file exists and whether it has read,
	 * write permissions.
     *
	 * @param file File to be checked
	 * @return true only if it is a File and it has read and write permissions.
	 */
	static Vector excludeFiles=new Vector();//For Exclude files
	protected static boolean isEveryThingOk(File file)
	{
		if (!file.exists())
		{
			System.err.println("The File: " + file +" "+ "does not exist");//No Internationalization
			return false;
		}
        
		if (!file.isFile())
		{
			System.err.println(String.valueOf(file) + " : "+"is not a file");//No Internationalization
			return false;
		}
        
		if (!file.canRead())
		{
			System.err.println("The File: " + file + " "+"does not have read permission");//No Internationalization
			return false;
		}
        
		if (!file.canWrite())
		{
			System.err.println("The File: " + file + " "+"does not have write permission");//No Internationalization
			return false;
		}
		else
		{
			return true;
		}
	}

    /** 
	 * A method to copy the source file to the destination file.
	 * It copies only if the source file has a read/write permission.
     *
	 * @param source - the source filename, a File object 
	 * @param dest   - the destination filename, a File object 
	 */
	protected static void copyFile(File source, File dest)
	{
		if ((source == null) || (dest == null))
		{
			return;
		}

        BufferedReader bufferedreader = null;
        PrintWriter printwriter = null;
        
		if (isEveryThingOk(source))	
		{
			try
			{
				bufferedreader = new BufferedReader(new FileReader(source));
				printwriter = new PrintWriter(new BufferedWriter(new FileWriter(dest)));

				String s2;
                
				while ((s2 = bufferedreader.readLine()) != null)
				{ 
					printwriter.println(s2);
				}
			}
			catch(Exception ex)
			{
				System.err.println("Exception while copying file " + source.getAbsolutePath()+" "+"as"+" "+dest.getAbsolutePath());//No Internationalization
				ex.printStackTrace();
			}
            finally
            {
                try
                {
                    bufferedreader.close();
                    printwriter.flush();
                    printwriter.close();
                }
                catch (Exception e){}
            }
		}
	}

    // parses the comma separated file names in the String, and returns the
    // resulting Vector of fileNames
    protected static Vector getFilesFromString(String fileNames)
    {
        Vector filesVector = new Vector();

        StringTokenizer str = new StringTokenizer(fileNames, ",");//No Internationalization

        while (str.hasMoreTokens())
        {
            filesVector.addElement(str.nextToken().trim());
        }
        return filesVector;
    }
    
    //recursively searches for all the files in the specified directory and
    //populate the same in the given Vector
    protected static void recurseAndGetTheFiles(File file, Vector allFilesVector)
    {
        if (file.isFile())
        {
            allFilesVector.addElement(file);
            return;
        }
        else if (file.isDirectory())
        {
            allFilesVector.addElement(file);

            File[] filesList = file.listFiles();

            for (int i = 0; i < filesList.length; i++)
            {
		/*For Exclude files */    
		if(excludeFiles!=null) 
		{		
			if(checkForExcludeFiles(filesList[i].toString()))
			recurseAndGetTheFiles(filesList[i], allFilesVector);	
		}
		else
		    recurseAndGetTheFiles(filesList[i], allFilesVector);	
		/*For Exclude files Ends*/
            }
        }
    }
	/* For Exclude files*/
   private static boolean checkForExcludeFiles(String inputFile)
   {
   for (Enumeration en = excludeFiles.elements(); en.hasMoreElements();)
   {
	String thisvectornode=((String)en.nextElement());
	if(thisvectornode.startsWith("."))
	{
		if(inputFile.endsWith(thisvectornode))
		return false;
	}		
	else if(thisvectornode.indexOf(File.separator+".")!=-1)
	{
	int positionofslash=thisvectornode.indexOf(File.separator);
	String first = thisvectornode.substring(0,positionofslash);
	String last = thisvectornode.substring(positionofslash+1,thisvectornode.length());
	if(inputFile.startsWith(first) && inputFile.endsWith(last))
		return false;
	}
	else 
	{
	if(inputFile.endsWith(thisvectornode))
	return false;
	}
   }
   return true;
   }
	/*For Exclude files ends*/
    //reads the file in the url and returns the specified file names and
    //directory names in the file as Hashtable 
    protected static Hashtable getAllFileNames(String url)
    {
        Hashtable filesHash = new Hashtable();

        XMLDataReader xmlreader = new XMLDataReader(url, false);
        XMLNode node = null;
        Vector childNodes = xmlreader.getRootChildNodes();
        String fileNames = null;
	String filesToExclude = null; //exclude
	String dirsToExclude = null; //exclude
        String dirNames = null;
        //flag for appending the file names in the String if more than one
        //FILES_TO_BACKUP or FILES_TO_RESTORE node has been given
        boolean flag = true;

        for (Enumeration en = childNodes.elements(); en.hasMoreElements();)
        {
            node = (XMLNode) en.nextElement();

            if (node.getNodeType() == XMLNode.COMMENT)
            {
                continue;
            }         

            String nodeName = node.getNodeName();
            
            //if ((nodeName.equals("FILES_TO_BACKUP")) || (nodeName.equals("FILES_TO_RESTORE")))//No Internationalization
	if((nodeName.equals("FILES_TO_EXCLUDE")) || (nodeName.equals("DIR_TO_EXCLUDE")))
	{
		if (flag)
		{
		    filesToExclude = (String) node.getAttribute("FILE_NAMES"); //For Exclude files
		    dirsToExclude = (String) node.getAttribute("DIR_NAMES"); //For Exclude files
		}
		else
		{
		    filesToExclude =filesToExclude+ "," + (String) node.getAttribute("FILE_NAMES"); //For Exclude files
		    dirsToExclude = dirsToExclude + "," + (String) node.getAttribute("DIR_NAMES"); //For Exclude files
		}
		flag = false;
	}
	else if ((nodeName.equals("FILES_TO_BACKUP")) || (nodeName.equals("FILES_TO_RESTORE")))//No Internationalization 
            {
                //first node
                if (flag)
                {
                    fileNames = (String) node.getAttribute("FILE_NAMES");//No Internationalization
                    dirNames = (String) node.getAttribute("DIR_NAMES");//No Internationalization
                }
                //successive nodes
                else
                {
                    fileNames = fileNames + "," + (String) node.getAttribute("FILE_NAMES");//No Internationalization
                    dirNames = dirNames + "," + (String) node.getAttribute("DIR_NAMES");//No Internationalization
                } 
                flag = false;
            }
            else
            {
                continue;
            }
        }
	/*For Exclude files*/
	if(filesToExclude != null || dirsToExclude != null)
	{
		if(dirsToExclude != null)
			filesToExclude=filesToExclude.concat(", "+dirsToExclude);
		filesToExclude=filesToExclude.replaceAll("\\*.",".");
		if(System.getProperty("os.name").startsWith("Windows"))	
		filesToExclude=filesToExclude.replace('/','\\');
		excludeFiles=getFilesFromString(filesToExclude);//For Exclude files
	}
	/* For Exclude files ends */
        if (fileNames != null)
        {
            filesHash.put("file", fileNames);
        }

        if (dirNames != null)
        {
            filesHash.put("dir", dirNames);
        }
        return filesHash;
    }
}

