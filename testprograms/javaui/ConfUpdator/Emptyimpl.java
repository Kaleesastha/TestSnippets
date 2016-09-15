/*
This Program is to test the methods of confChanges class of the testplan cf_api_cupui_tp.html
(1) Check for the confFileChanges.xml file in the Webnms/conf Directory
(2) In the classname tag include classname=com.adventnet.nms.tools.confchanges.Emptyimpl
(3)If you are using the default xml file then,
	Put Four files OIDType.data,abc1.data,OIDType1.data & abc1.data in the Webnms/Conf folder for testing.
Note: These can contain anything and they are used only for checking the backup action.
          The actual contents of the file are not our concern

*/


/**
 * ConfigurationChangesImpl.java
 *
 *
 * Created: Sun Mar 23 07:03:07 2003
 *
 * @author <a href="mailto: "</a>
 * @version
 */

package com.adventnet.nms.tools.confchanges;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;
import java.lang.Object;
import org.w3c.dom.*;

import com.adventnet.nms.tools.confchanges.ConfNode;
import com.adventnet.nms.tools.confchanges.ConfChangeEvent;


public class Emptyimpl extends AbstractConfChanges
{


    /**
     * This method updates the configuration informations that have changes
     * When some  bug fixes or features are added as a part of the Service Packs
     * over the base version of Web NMS.This update mechanism does not affect
     * the changes that are made by the users.
     @param node This node contains the details of the changes that are made,
     @param productHome This is the home directroy of the product.
     @param productConfHome  This is the conf directroy of the product , all the config files will be present under this directory.
     @return Returns the status of the operation.
    */

    public boolean updateChanges(ConfNode node,File productConfHome,File productHome,File backupDir ) throws ConfUpgradeException
    {
		System.out.println("$$$$$$$$$$$$$Backup directory inside update changes :::"+ backupDir);
        Object temp = new Object();
        String path = node.getFilePath();
        System.out.println(" getNode() method is "+ node.getNode());
		doBackup(path,productHome.toString(),backupDir.toString());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return true;
    }



    /* This method is useful to check whether the node is already updated or not */

    public boolean isNodeUpdated()
    {
        System.out.println(" node updated ");
        return true;
    }


    private void doBackup(String file_path,String product_home,String backupdir)
    {
		System.out.println("backup Dir: "+backupdir + " file_path: "+file_path);
        String backupDirectoryPath = backupdir+ File.separator + file_path;
        File backup_confFile = new File(backupDirectoryPath);
        System.out.println("backupDirectoryPath :: backup_confFile"+backup_confFile);

        if(backup_confFile.exists())
        {
            System.out.println(" Yes ");
            return;
        }
        else
        {
            File parentdir = backup_confFile.getParentFile();

            if(!parentdir.exists())
            {
                parentdir.mkdirs();
            }
        }

        copyTheFile( new File(product_home + File.separator+ file_path),new File(backupdir+ File.separator+ file_path) );
    }

    private void copyTheFile(File source, File destination)
    {
        try
        {
            FileReader input = new FileReader(source);
            FileWriter output = new FileWriter(destination);

            int c;

            while ((c = input.read()) != -1)
            {
                output.write(c);
            }

            input.close();
            output.close();
        }
        catch(Exception ex)
        {
            System.err.println("DEBUG From "+this.getClass().getName()+" FATAL ERROR: Unable to take back-up");
            ex.printStackTrace();
        }


    }
     public void revertChanges(ConfNode a,File productConfHome,File productHome,File backupDir) throws ConfUpgradeException
	    {

	        System.out.println(" *******************************************");
	        System.out.println(" revert *changes* for my impl called ");
	        System.out.println(a.getNode());
	        System.out.println(" *******************************************");
	    }



}// Emptyimpl
