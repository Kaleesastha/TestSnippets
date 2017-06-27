//$Id: CorbaServer.java,v 1.2 2007/04/30 17:21:28 tinku Exp $
//Package Name
package com.adventnet.nms.jmxagent;

// Server will use the naming service.
import org.omg.CosNaming.*;

// The package containing special exceptions thrown by the name service.
import org.omg.CosNaming.NamingContextPackage.*;

// All CORBA applications need these classes.
import org.omg.CORBA.*;

//Other imports
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

/**
 * A Server class to bind the CORBA Objects into the NamingService.
 */
public class CorbaServer extends Thread
{
    String host = "localhost"; //No I18N
    String port = "1050";//No I18N
    String servantName = "";//No I18N
    String keyName = "";//No I18N

    public CorbaServer()
    {
    }

    public void bindObject(String host, String port, String servantName, String keyName) throws Exception
    {
	if(host != null)
	    this.host = host;
	if(port != null)
	    this.port = port;

	if(servantName == null)
	    throw new Exception("Servant Name is null");//No I18N
	else
	    this.servantName = servantName;

	if(keyName == null)
	    this.keyName = this.servantName;
	else
	    this.keyName = keyName;

	this.start();
    }

    public void run()
    {
	try
	{
	    //Set property for OpenORB
	    System.setProperty("org.omg.CORBA.ORBClass", "org.openorb.CORBA.ORB");
	    System.setProperty("org.omg.CORBA.ORBSingletonClass", "org.openorb.CORBA.ORBSingleton");

	    //Set host and port as a Property
            java.util.Properties p =  new java.util.Properties();
            String pathInfo="corbaloc::" + host + ":" + port + "/NameService";//No I18N
            p.put("InitRef.NameService", pathInfo );

            String arg[]={"1"};//No I18N

            //Init ORB
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(arg,p);

	    //Activate POA Manager
	    org.omg.PortableServer.POA rootpoa = (org.omg.PortableServer.POA)orb.resolve_initial_references("RootPOA");//No I18N
	    rootpoa.the_POAManager().activate();

	    //Look-up Naming Service
	    org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");//No I18N
	    NamingContextExt context = NamingContextExtHelper.narrow(obj);
	    NameComponent path[] = null;

	    //Bind the Servant Object
	    Class servantClass = Class.forName(servantName);
            Constructor cc = servantClass.getConstructor(new Class[] {org.omg.CORBA.ORB.class });
            java.lang.Object servantObj = cc.newInstance(new java.lang.Object[] {orb});
	    //java.lang.Object servantObj = servantClass.newInstance();

	    org.omg.CORBA.Object servantRef = rootpoa.servant_to_reference((org.omg.PortableServer.Servant)servantObj);

	    String poaClassName = servantClass.getSuperclass().getName();
	    String interfaceClassName = poaClassName.substring(0,poaClassName.length() - 3);
	    Class helperClass = Class.forName(interfaceClassName + "Helper");//No I18N
	    Method narrowMethod = helperClass.getMethod("narrow", new Class[]{org.omg.CORBA.Object.class});//No I18N
	    org.omg.CORBA.Object servant = (org.omg.CORBA.Object)narrowMethod.invoke(null, new java.lang.Object[]{servantRef});

	    path = context.to_name(keyName);
	    context.rebind(path,servant);

	    System.out.println("CORBAServer: CORBA Object " + servantName + " has been bound"); //No I18N

	    //For a pause
	    orb.run();
	}
	catch(Exception e)
	{
	    System.out.println("Exception while bind the CORBA Object " + servantName + " : " + e);//No I18N
	}
    }
}
