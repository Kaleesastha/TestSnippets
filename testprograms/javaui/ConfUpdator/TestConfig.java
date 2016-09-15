/*
This Program is to test the methods of confChanges class of the testplan cf_api_cupfw_tp.html
To test the class Do the Following
(1) Change the directory path in the arguments of the initParameters() call in the getHan() method
(2) Download ConfigurationChangesImpl.java,TestExceptionHandler.java & TestListener.java Files into your Working Directory
(3) Include webnms/classes, xalan.jar,javax.servlet.jar,Adventnetsnmp.jar in the classpath
(4) Compile all the Java Files into Webnms/classes
(5) Check for the confFileChanges.xml file in the Webnms/conf Directory
(6) In the classname tag include classname=com.adventnet.nms.tools.confchanges.ConfigurationChangesImpl so that this
	class gets called on running the TestConfig.class file.
			[OR]
	For Testing Purpose the default ConfFileChanges.xml present in this Folder can be used
(7)If you are using the default xml file then,
	Put Four files OIDType.data,abc1.data,OIDType1.data & abc1.data in the Webnms/Conf folder for testing.
Note: These can contain anything and they are used only for checking the backup action.
          The actual contents of the file are not our concern





*/



import com.adventnet.nms.tools.confchanges.*;
import com.adventnet.nms.tools.confchanges.ConfUpgradeManager;
import com.adventnet.nms.tools.confchanges.ConfChangeEvent;
import java.io.*;
import com.adventnet.nms.tools.confchanges.ConfUpgradeEventListener;
import com.adventnet.nms.tools.confchanges.ConfExceptionHandler;
import com.adventnet.nms.util.*;
import java.io.PrintStream;


public class TestConfig

{

ConfUpgradeManager cum = null;
TestListener listen= new TestListener();

	public void getHan()
		{

		cum = new ConfUpgradeManager();
		cum.initParameters("E:\\Test4\\Adventnet\\WebNMS","E:\\Test4\\Adventnet\\WebNMS","E:\\Test4\\Adventnet\\WebNMS\\conf","ConfFileChanges.xml");


		 System.out.println("ConfigUpgradeManager Instance   " + cum);


		 cum.addConfUpgradeEventListener(listen);
		 System.out.println("********* Test Case 017**********");
		 TestExceptionHandler handex = new TestExceptionHandler();
 		 cum.setExceptionHandler(handex);
		 testcase1to3();
		 //testcase4to5();
		 cum.updateConfChanges();
		 //testcase013();
		 //testcase15to16();
		 //testcase014();

		}

	public void testcase1to3()
		{

		System.out.println("Test case *001* get Node Count   "    + cum.getNodeCount());
		 System.out.println("Testcase *002* getProductConfHome()   "    + cum.getProductConfHome());
		 System.out.println("Testcase *003* getProductHome()   "    + cum.getProductHome());

		}

	public void testcase4to5()
			{

			 cum.setProductConfHome("E:\\Test4\\Adventnet\\webnms\\conf");
			 System.out.println("Testcase *004* o/p of setProductConfHome()   "    + cum.getProductConfHome());
			 cum.setProductConfHome("E:\\workdfding\\coup");
			 System.out.println("Testcase 008 o/p of setProductConfHome()   "    + cum.getProductConfHome());
			 cum.setProductHome("E:\\working");
			 System.out.println("case *005* setProductHome()   "    + cum.getProductHome());
			 }

	public void testcase014()
	{
		try
			{	System.out.println(" Test Case *014*");
				cum.revertConfChanges("BaseVersion");
				System.out.println("BaseVersion");
			}
			catch(Exception e)
			{
			System.out.println("Into the revertchanges exception handler");
			}

	}

	public void testcase15to16()
	{
		try
				{

				FileOutputStream fileOutStream1 = new FileOutputStream("E:\\outlogs.txt",true);
				PrintStream ptr = new PrintStream(fileOutStream1);
		    	 cum.setOutputStream(ptr);
			    System.out.println("Output Stream set *015* Passed");
				FileOutputStream fileOutStream2 = new FileOutputStream("E:\\errlogs.txt",true);
				PrintStream ptr1 = new PrintStream(fileOutStream2);
				cum.setErrorStream(ptr1);
				System.out.println("Error Stream set *016* Passed");
				}
				catch(java.io.FileNotFoundException e)
				{
					System.out.println("caught file exception Test case *015 & *016 Failed");
				}

	}

	public void testcase013()
	{
				//Test Case *013*
				 cum.removConfUpgradeEventListener(listen);
				 System.out.println(" Test Case *013* Passed Listener Removed");
				 System.err.println("into the error Stream");

	}


public static void main(String args[])
    {
      TestConfig tc=new TestConfig();
       tc.getHan();

    }


}
