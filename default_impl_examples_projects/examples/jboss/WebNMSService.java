// $Id: WebNMSService.java,v 1.2 2006/12/28 06:41:47 sunilg Exp $

/**
 * WebNMSService.java
 *
 *
 * Created: Tue Jun 11 10:37:59 2002
 *
 * @author Chitrapandian N
 * @since AdventNet Web NMS 2.3
 */
package com.adventnet.nms.jboss;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.adventnet.nms.admin.ShutDownAPIImpl;
import com.adventnet.nms.startnms.NmsMainFE;
import com.adventnet.nms.fe.common.naming.NameLookup;
import com.adventnet.nms.fe.common.naming.NameLookupImpl;

import org.jboss.system.ServiceMBeanSupport;

/**
 * Initializes the Web NMS FE Server as service in JBoss Application
 * Server, with the specified arguments. Based on the given
 * parameters, this service MBean starts, Web NMS Front-End Server and
 * Apache Webserver with Tomcat web-container.  It registers in JNDI
 * with the name specified in setBindName() method, bind name should
 * be "WebNMSFactory".
 *
 * @see ServiceMBeanSupport
 * @see WebNMSServiceMBean 
 */
public class WebNMSService 
    extends ServiceMBeanSupport
    implements WebNMSServiceMBean
{
    // Attributes ----------------------------------------------------
    /**
     * Bind name for NameLookup in JNDI.
     */
    private String m_name;
    /**
     * Arguments for starting Web NMS FE from JBoss.
     */
    private String m_args;

    /**
     * Constructs a new <code>WebNMSService</code> instance.
     */
    public WebNMSService ()
    {
        System.out.println(" [JBoss-WebNMS] : WebNMSService constructor called !! ");
    }

    // Public --------------------------------------------------------
    /**
     * Returns, name of this Service MBean.
     *
     * @return a <code>String</code> value
     */
    public String getName()
    {
        return "Web NMS Service";
    }

    /**
     * Sets the bind name of NameLookup interface in JNDI.
     *
     * @param name a <code>String</code> bind name.
     */
    public void setBindName(String name)
    {
        System.out.println(" [JBoss-WebNMS] : BINDNAME == > "+name );
        m_name = name;
    }
    
    /**
     * Returns the bind name of NameLookup interface in JNDI.
     *
     * @return a <code>String</code> bind name.
     */
    public String getBindName()
    {
        return m_name;
    }
    
    /**
     * Sets the arguments used to start Web NMS FE Server 
     * from JBoss Application Server.
     *
     * @param args a <code>String</code> value
     */
    public void setArgs(String args)
    {
        System.out.println(" [JBoss-WebNMS] : ARGS == > "+args );
        m_args = args;
    }
    
    /**
     * Returns the arguments used to start Web NMS FE Server 
     * from JBoss Application Server.
     *
     * @return a <code>String</code> value
     */
    public String getArgs()
    {
        return m_args;
    }

    /**
     * Starts the Web NMS FE Server and Apache Web Server, also,
     * NameLookup interface is bound in JNDI.
     *
     * @exception Exception if an error occurs
     */
    public void startService() throws Exception
    {
        System.out.println(" [JBoss-WebNMS] : ENTER : startService() WebNMSService..!!" );
        System.out.println(" [JBoss-WebNMS] : Starting Web NMS FEServer..!!" );
        final String[] args = formArray(m_args);
	
	//Apache Arguments Not Required Hence Starting NmsMainFE Directly
	Thread startFE=new Thread()
	{
			public void run()
			{
				com.adventnet.nms.startnms.NmsMainFE.main(args);
			}
		
	};
	startFE.start();
        String factory = "org.jnp.interfaces.NamingContextFactory"; // jboss factory
        String url = "localhost"; //the jboss url

        Hashtable ht = new Hashtable(2);
        ht.put(Context.INITIAL_CONTEXT_FACTORY, factory);
        ht.put(Context.PROVIDER_URL, url);

        Context ctx = new InitialContext(ht);
        NameLookup lookup = new NameLookupImpl();
        ctx.bind(m_name, lookup);  // binds Web NMS Factory .!

        System.out.println(" [JBoss-WebNMS] : "+m_name+" is registered successfully..!!" );
        System.out.println(" [JBoss-WebNMS] : EXIT : startService() WebNMSService..!!" );
    }

    /**
     * Stops the Web NMS FE Server.
     *
     */
    public void stopService()
    {
        try{
            ShutDownAPIImpl.getInstance().shutDownNMSServer(true);
        }
        catch(Exception e) {}
    }

    /**
     * Forms the key=pair string separated by comma (,) into 
     * array of String.
     *
     * @param arg a <code>String</code> String to be converted to String array.
     * @return a <code>String[]</code> formed from the passed string.
     */
    private String[] formArray(String arg)
    {
        String[] retArr = null;
        if(arg == null)
        {
            retArr = new String[0];
            return retArr;
        }
        int i = 0;
        StringTokenizer tok = new StringTokenizer(arg, ",");
        int count = tok.countTokens();
        retArr = new String[count * 2];
        while(tok.hasMoreTokens())
        {
            String token = tok.nextToken();
            StringTokenizer intok = new StringTokenizer(token, "=");
            retArr[i] = intok.nextToken();
            retArr[i+1] = intok.nextToken();
            i += 2;
        }
        System.out.println(" [JBoss-WebNMS] : formArray() : " +Arrays.asList(retArr));
        return retArr;
    }
}

// WebNMSService.java
