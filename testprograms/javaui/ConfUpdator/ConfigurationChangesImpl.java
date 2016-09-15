
/*
This Program is to test the methods of confChanges class of the testplan cf_api_cupfw_tp.html
To test the class Do the Following
(1) Change the directory path in the arguments of the initParameters() call in the getHan() method in TestConfig.java file
(2) Download ConfigurationChangesImpl.java,TestExceptionHandler.java & TestListener.java Files into your Working Directory
(3) Include webnms/classes, xalan.jar,javax.servlet.jar,Adventnetsnmp.jar in the classpath
(4) Compile all the Java Files into Webnms/classes
(5) Check for the confFileChanges.xml file in the Webnms/conf Directory
(6) In the classname tag include classname=com.adventnet.nms.tools.confchanges.ConfigurationChangesImpl so that this
	class gets called on running the TestConfig.class file.
			[OR]
	For Testing Purpose the default ConfFileChanges.xml present in this Folder can be used.
	Copy the file to Webnms/conf folder
(7)If you are using the default xml file then,
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


public class ConfigurationChangesImpl extends AbstractConfChanges
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
	Object temp = null;
        String path = null;
        ConfNode node = null;
    public boolean updateChanges(ConfNode node1,File productConfHome,File productHome,File backupDir ) throws ConfUpgradeException
    {
		System.out.println("*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Implementation of Node @@@@@@@@@@@@@@@@@@@@@@@@@@@*");
		System.out.println(" Class called Test Case *006* & *023*  PASSED");
		System.out.println("Backup directory ::"+ backupDir);
        temp = new Object();
        path = node1.getFilePath();
        node=node1;
        System.out.println("VARIABLE PATH HAS "+ path);
        System.out.println();

		//System.out.println("Exception Related Test case *007*, *008*, *009, *010*, *012*");
		//Checking Exception;
		//int i=1;
		//ConfUpgradeException exobj = new ConfUpgradeException();
		//if (i==1)
		//throw exobj;

		testcase17to22();
		testcase25to28();
        doBackup(path,productHome.toString(),backupDir.toString());
		return true;
    }


public void revertChanges(ConfNode node,File productConfHome,File productHome,File backupDir)
      throws com.adventnet.nms.tools.confchanges.ConfUpgradeException

	        {
				System.out.println("Test Case *014* & *024* revert changes for my impl called ");
	        }


public void testcase17to22()
{
try

{

	 		 Vector getnodes = node.getAllUpdateNodes();
	         System.out.println("Testcase *17*  getPriority() method is --"+ node.getPriority());
	         System.out.println("Testcase  *018*  getName() method is --"+ node.getName());
	         System.out.println("Testcase *019*  node.getAttribute()  method is --"+ node.getAttribute("A"));
	         System.out.println("Testcase *020* & *021* node.getAttributeList()  method is --");
	         System.out.println(node.getAttributeList());
	         System.out.println();
	         System.out.println("%%%%%%%%%%%%%%%Printing the Entire Node%%%%%%%%%%%%%%%");
	         System.out.println("Testcase *022* getNode() method is "+ node.getNode());
	         System.out.println("%%%%%%%%%%%%%%%End of  Node%%%%%%%%%%%%%%%");
			 System.out.println();
			}
			catch(Exception e)
			{
			e.printStackTrace();
			}

}

public void testcase25to28()
{

Vector update_nodesVec =  node.getAllUpdateNodes();

        for(Enumeration enum = update_nodesVec.elements(); enum.hasMoreElements();)
        {

			System.out.println("$$$$$$$$$$$$$$$$$$$$$Parsing the Node$$$$$$$$$$$$$$$$$$$");
            ConfUpdateNode update_node = (ConfUpdateNode)enum.nextElement();
            System.out.println(" Name of the Current Node :: "+update_node.getName());

            Vector propSetVec = update_node.getAllPropertySet();

			System.out.println("Testcase *025*");
            for(Enumeration enum1 = propSetVec.elements(); enum1.hasMoreElements();)
            {
                PropertySet propSet = (PropertySet)enum1.nextElement();

                System.out.println(" propertySet getting all Properties - ");
                System.out.println(propSet.getAllProperties());
                System.out.println();

		 //Codde to check user defined attributes in the main set tag--Starts
               	System.out.println("Test Case *027* getAttributeList() of propertySet - ");
               	System.out.println(propSet.getAttributeList());
               	System.out.println();

               	System.out.println(" Test case *028* getAttribute() method " + propSet.getAttribute("phone"));
                Hashtable getattr = propSet.getAttributeList();
                if(getattr.size()>0)
                {	System.out.println();
					System.out.println(" List of Properties - ");
				for(Enumeration enum3 = getattr.keys(); enum3.hasMoreElements();)
					{
                	temp=enum3.nextElement();
                	System.out.println(" PropertyName - "+ temp +" Value - "+ getattr.get(temp));
					}

				}
			//Codde to check user defined attributes in the main set tag --Ends

			//&&&&&&&&&&&&&&&&&&&&To get Property nodes inside Propertyset-Starts
			Vector propsetvec= propSet.getAllProperties();
			System.out.println(" ------Vector of properties inside Property set-------" );
			System.out.println( propsetvec);
			System.out.println();
			System.out.println();
			System.out.println(" Test Case getName-*029* , getValue-*30* & *032*");
			            for(Enumeration enum2 = propsetvec.elements(); enum2.hasMoreElements();)
			            {
			                Property prop4 = (Property)enum2.nextElement();

			                System.out.println(" property - "+ prop4.getName() + "  Value - " + prop4.getValue() );

			                //Codde to check user defined attributes --Starts
			                System.out.println();
					               System.out.println(" Test Case *031*  getAttributeList() of property - ");
					               System.out.println(prop4.getAttributeList());
					               System.out.println();
						                Hashtable getattr3 = prop4.getAttributeList();
						                if(getattr3.size()>0)
						                {System.out.println(" List of Properties - ");
										for(Enumeration enum3 = getattr3.keys(); enum3.hasMoreElements();)
										{
					                	temp=enum3.nextElement();
					                	System.out.println(" PropertyName - "+ temp +" Value - "+ getattr3.get(temp));
										}

											}
							//Codde to check user defined attributes --Ends
            }

			//&&&&&&&&&&&&&&&&&&&&To get Property nodes inside Propertyset-Ends
			//######################
			//To get Nested Property inside Propertyset --Starts
			System.out.println(" Getting inside Nested PropertySET   *026*");
			Vector nestpropSetVec= propSet.getAllPropertySet();
			System.out.println("vector list "+ nestpropSetVec);
			System.out.println();
			for(Enumeration enum4 = nestpropSetVec.elements(); enum4.hasMoreElements();)
			            {
			                PropertySet propSet1 = (PropertySet)enum4.nextElement();
			                System.out.println(" propertySet getting all Properties - "+propSet1.getAllProperties());
							System.out.println();
					 //Codde to check user defined attributes in the main set tag--Starts
			               	System.out.println(" Test case *027* getAttributeList() of propertySet - ");
			               	System.out.println(propSet1.getAttributeList());
			               	System.out.println();
			                Hashtable getattr2 = propSet1.getAttributeList();
			                if(getattr2.size()>0)
			                {
								System.out.println(" PropertySet - ");
							for(Enumeration enum3 = getattr2.keys(); enum3.hasMoreElements();)
							{
			               		temp=enum3.nextElement();
			                	System.out.println(" PropertyName - "+ temp +" Value - "+ getattr2.get(temp));
							}

							}
						//Codde to check user defined attributes in the main set tag --Ends

					}
			System.out.println(" ****** Getting out of Nested PropertySET*************");

			//#######################
			//To get Nested Property inside Propertyset --Ends

            }


       //Code to get all Properties -Starts
            Vector propVec = update_node.getAllProperties();

            for(Enumeration enum2 = propVec.elements(); enum2.hasMoreElements();)
            {
                Property prop = (Property)enum2.nextElement();
                System.out.println();
                System.out.println(" _________________New Property________________________________________");
                System.out.println(" property - "+ prop.getName() + "  Value - " + prop.getValue() );

                //Codde to check user defined attributes --Starts
		               //System.out.println(" getAttributeList() of property - "+prop.getAttributeList());
			                Hashtable getattr1 = prop.getAttributeList();
			                if(getattr1.size()>0)
			                {System.out.println(" PropertySet - ");
							for(Enumeration enum3 = getattr1.keys(); enum3.hasMoreElements();)
							{
							temp=enum3.nextElement();
		                	System.out.println(" PropertyName - "+ temp +" Value - "+ getattr1.get(temp));
							}

								}
				//Codde to check user defined attributes --Ends
            }
             //Code to get all Properties -Ends
       }

}

 /* This method is useful to check whether the node is already updated or not */

    public boolean isNodeUpdated()
    {
        System.out.println(" node updated ");
        return true;
    }


    private void doBackup(String file_path,String product_home,String backupdir)

    {
		System.out.println();
		System.out.println("backup Dir: "+backupdir);
		System.out.println("File_path: "+file_path);
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


}// ConfigurationChangesImpl
