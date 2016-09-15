/*
This Program is to test the methods of confChanges class of the testplan cf_api_cupfw_tp.html
To test the class Do the Following
(1) Change the directory path in the arguments of the initParameters() call in the getHan() method of TestConfig.java
(2) Download ConfigurationChangesImpl.java,TestExceptionHandler.java & TestListener.java Files into your Working Directory
(3) Include webnms/classes, xalan.jar,javax.servlet.jar,Adventnetsnmp.jar in the classpath
(4) Compile all the Java Files into Webnms/classes
(5) Check for the confFileChanges.xml file in the Webnms/conf Directory
(6) In the classname tag include classname=com.adventnet.nms.tools.confchanges.ConfigurationChangesImpl so that this
		class gets called on running the TestConfig.class file.
		(OR)
		For Testing Purpose the default ConfFileChanges.xml present in this Folder can be used
(7)If you are using the default xml file then,
	Put Four files OIDType.data,abc1.data,OIDType1.data & abc1.data in the Webnms/Conf folder for testing.
Note: These can contain anything and they are used only for checking the backup action.
          The actual contents of the file are not our concern





*/


import com.adventnet.nms.tools.confchanges.*;
import com.adventnet.nms.tools.confchanges.ConfExceptionHandler;
//import com.adventnet.nms.tools.confchanges.ConfUpgradeManager;
import java.io.*;
import com.adventnet.nms.util.*;





public class TestExceptionHandler implements ConfExceptionHandler

{

public static void main(String args[])
    {

    TestExceptionHandler exhandler = new TestExceptionHandler();

 	}




public int handleException(java.lang.Exception e, com.adventnet.nms.tools.confchanges.ConfNode nodex)
{
	System.out.println("INSIDE EXCEPTION HANDLER  Test Case *033* Passed" + e);
	//e.printStacktrace();
	System.out.println(" *****Test case 038 Get Priority value for the node " + nodex.getPriority());
	System.out.println(" *****Test case 039 Get Node name value for the node " + nodex.getNodeName() );
	return nodex.getPriority();

}


}//TestExceptionHandler
