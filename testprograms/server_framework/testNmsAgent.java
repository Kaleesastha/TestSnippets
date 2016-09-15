/* $Id: testNmsAgent.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */

/**
 * This is an example program to explain how to write an application to do
 * the basic SNMP operation WALK using com.adventnet.snmp.snmp2 package of
 * AdventNetSNMP2 api.
 * The user could run this application by giving any one of the following usage.
 *  
 * java testNmsAgent [options] hostname oid
 *
 * v1 request: 
 * java testNmsAgent testNmsAgent [-d] [-c community] [-p port] [-t timeout] [-r retries] host OID
 * e.g. 
 * java testNmsAgent -p 161 -c public adventnet .1.3.6.1 
 *
 * v2c request:  
 * java testNmsAgent [-d] [-v version(v1,v2)] [-c community] [-p port] [-t timeout] [-r retries] host OID
 * e.g. 
 * java testNmsAgent -v v2 -p 161 -c public -m rfc1213-mib adventnet .1.3.6.1
 * 
 * v3 request:
 * java testNmsAgent [-d] [-v version(v1,v2,v3)] [-c community] [-p port] [-r retries] [-t timeout] [-u user] [-a auth_protocol] [-w auth_password] [-s priv_password] [-i context_id] host OID
 * e.g.
 * java testNmsAgent -v v3 -u initial2 -w initial2Pass -a MD5 10.3.2.120 .1.3
 * 
 * If the oid is not starting with a dot (.) it will be prefixed by .1.3.6.1.2.1 .
 * So the entire OID of 1.1.0 will become .1.3.6.1.2.1.1.1.0 . You can also
 * give the entire OID .
 * 
 * Options:
 * [-d]                - Debug output. By default off.
 * [-c] <community>    - community String. By default "public".
 * [-p] <port>         - remote port no. By default 161.
 * [-t] <Timeout>      - Timeout. By default 5000ms.
 * [-r] <Retries>      - Retries. By default 0.      
 * [-v] <version>      - version(v1 / v2 / v3). By default v1.
 * [-u] <username>     - The v3 principal/userName
 * [-a] <autProtocol>  - The authProtocol(MD5/SHA). Mandatory if authPassword is specified
 * [-w] <authPassword> - The authentication password.
 * [-s] <privPassword> - The privacy protocol password. Must be accompanied with auth password and authProtocol fields.
 * [-n] <contextName>  - The context to be used for the v3 pdu.
 * [-i] <contextID>    - The context to be used for the v3 pdu.
 * <host> Mandatory    - The RemoteHost (agent).Format (string without double qoutes/IpAddress).
 * <OID>  Mandatory    - Give multiple no. of Object Identifiers.
 */

import java.lang.*;
import java.util.*;
import java.net.*;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.snmp2.usm.*;

public class testNmsAgent {

    public static void main(String args[]) {
        
        // Take care of getting options
        String usage = "testNmsAgent [-d] [-v version(v1,v2,v3)] [-c community] [-p port] [-r retries] [-t timeout] [-u user] [-a auth_protocol] [-w auth_password] [-s priv_password] [-n contextName] [-i contextID] host OID ";
        String options[] = { "-d", "-c",  "-wc", "-p", "-r", "-t", "-m", "-v", "-u", "-a", "-w", "-s", "-n", "-i"};
        String values[] = { "None", null, null, null, null, null, "None", null, null, null, null, null, null, null };

		String hostName = "localhost";
		String oidValue = ".1.3.6.1.4.1.2162.4";

        ParseOptions opt = new ParseOptions(args,options,values, usage);
        if (opt.remArgs.length ==2) 
		{
			hostName = opt.remArgs[0];
			oidValue = opt.remArgs[1];
		}
		else if(opt.remArgs.length == 1)
		{
			oidValue = opt.remArgs[0];
		}
		else if(opt.remArgs.length == 0)
		{
			//Taking default values.
		}
		else
		{
			opt.usage_error();
		}

		if(values[3] == null)
		{
			values[3] = "8001";
		}

        
        // Start SNMP API
        SnmpAPI api;
        api = new SnmpAPI();
        api.start();
        if (values[0].equals("Set")) api.setDebug( true );
    
        // Open session
        SnmpSession session = new SnmpSession(api);
        // set remote Host 
        session.setPeername(hostName);

        //set the values
        SetValues setVal = new SetValues(session, values);
        if(setVal.usage_error)
		{
			opt.usage_error(); 
		}

        // Build GETNEXT request PDU
        SnmpPDU pdu = new SnmpPDU();
        pdu.setCommand( api.GETNEXT_REQ_MSG );
        
        // need to save the root OID to walk sub-tree
        SnmpOID oid = new SnmpOID(oidValue);
        int rootoid[] = (int[]) oid.toValue();  
        if (rootoid == null) { //if don't have a valid OID for first, exit
            System.err.println("Invalid OID argument: " + oidValue);
            System.exit(1);
        }
                else pdu.addNull(oid);
    
        try {
            //open session
            session.open();
        } catch (SnmpException e) {
            System.err.println("Error in open session "+e.getMessage());
            System.exit(1);
        }
        
        if(session.getVersion()==SnmpAPI.SNMP_VERSION_3) {
			pdu.setUserName(setVal.userName.getBytes());
            USMUtils.init_v3_params(setVal.userName, setVal.authProtocol, setVal.authPassword, setVal.privPassword, session.getPeername(),session.getRemotePort(),session);
            pdu.setContextName(setVal.contextName.getBytes());
            pdu.setContextID(setVal.contextID.getBytes());
        }
        // loop for each PDU in the walk
        while (true) { // until received OID isn't in sub-tree
             try {
                // Send PDU and receive response PDU
                pdu = session.syncSend(pdu);
            }
            catch (SnmpException e) {
                System.err.println("Sending PDU"+e.getMessage());
            }
        
            if (pdu == null) {
                // timeout
                System.out.println("Request timed out to: " + hostName );
                System.exit(1);
            }
            
            // stop if outside sub-tree
            if (!isInSubTree(rootoid,pdu)) 
                break; 

            if(pdu.getVersion() == SnmpAPI.SNMP_VERSION_1) {
                // check for error 
                if (pdu.getErrstat() != 0) {
                    System.out.println("Error Indication in response: " +
                        SnmpException.exceptionString((byte)pdu.getErrstat()) + 
                        "\nErrindex: " + pdu.getErrindex()); 
                    System.exit(1);
                }
                // print response pdu variable-bindings                    
                System.out.println(pdu.printVarBinds());
            } else if((pdu.getVersion() == SnmpAPI.SNMP_VERSION_2C)||(pdu.getVersion() == SnmpAPI.SNMP_VERSION_3)) {
                for(Enumeration e = pdu.getVariableBindings().elements(); e.hasMoreElements();) {
                    int error = 0;
                    SnmpVarBind varbind = (SnmpVarBind)e.nextElement();
                    // check for error
                    if ( (error = varbind.getErrindex()) != 0) {
                        System.out.println("Error Indication in response: " +
                                           SnmpException.exceptionString((byte)error));
                        System.exit(1);
                    }
                    // print response pdu variable-bindings
                    System.out.println(pdu.printVarBinds());
                }                
            } else System.out.println("Invalid Version Number"); 

            // set GETNEXT_REQ_MSG to do walk
            pdu.setCommand( api.GETNEXT_REQ_MSG );
            // Don't forget to set request id to 0 otherwise next request will fail
            pdu.setReqid(0);
            
    } // end of while true
        
	// Print the GroupCounters
	String[] localAddr = session.getLocalAddresses();
	int localPort = session.getLocalPort();
	SnmpGroup group = api.getSnmpGroup(localAddr[localAddr.length - 1],localPort);
	if(group != null){
		System.out.println("The SnmpGroup Counter values :");
		System.out.println("snmpInPkts = " + group.getSnmpInPkts());
		System.out.println("snmpOutPkts = " + group.getSnmpOutPkts());
		System.out.println("snmpInGetResponses = " + group.getSnmpInGetResponses());
		System.out.println("snmpOutGetRequests = " + group.getSnmpOutGetRequests());
		System.out.println("snmpOutGetNexts = " + group.getSnmpOutGetNexts());
	}	

    // close session
    session.close();
    // stop api thread
    api.close();

    System.exit(0);

    }

    /** check if first varbind oid has rootoid as an ancestor in MIB tree */
    static boolean isInSubTree(int[] rootoid, SnmpPDU pdu) {
        
        SnmpOID objID = (SnmpOID) pdu.getObjectID(0);
        if (objID == null) return false;

        int oid[] = (int[]) objID.toValue();
        if (oid == null) return false;        
        if (oid.length < rootoid.length) return false;
        
        for (int i=0;i<rootoid.length;i++)
            if (oid[i] != rootoid[i]) return false;
        return true;
    }

}
