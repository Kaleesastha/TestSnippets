package com.adventnet.nms.testdb.tools;

import com.adventnet.nms.testdb.util.*;
import com.adventnet.nms.testdb.*;
/* $Id: 
/*
 * @(#)TrapGenerator.java
 * Copyright (c) 1998 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 */

/**
 * This is an example program to explain how to write an application to do
 * the basic SNMP operation GET using com.adventnet.snmp.snmp2 package of
 * AdventNetSNMP2 api.
 * The user could run this application by giving any one of the following
usage.
 *  
 * java TrapGenerator [options] hostname oid ...
 *
 * v1 request:
 * java TrapGenerator [-d] [-c community] [-p port] host OID [OID] ...
 * e.g. 
 * java TrapGenerator -p 161 -c public adventnet 1.1.0 1.2.0
 *
 * v2c request:
 * java TrapGenerator [-d] [-v version(v1,v2)] [-c community] [-p port]
host OID [OID] ...
 * e.g. For v1 request give -v v1 or drop the option -v .
 * java TrapGenerator -p 161 -v v2 -c public adventnet 1.1.0 1.2.0
 * 
 * v3 request:
 * java TrapGenerator [-d] [-v version(v1,v2,v3)] [-c community] [-p
port] [-u user] [-a auth_protocol] [-w auth_password] [-s priv_password]
[-i contextName] host OID [OID] ...
 * e.g.
 * java TrapGenerator -v v3 -u initial2 -w initial2Pass -a MD5
10.3.2.120 1.2.0
 * 
 * If the oid is not starting with a dot (.) it will be prefixed by
.1.3.6.1.2.1 .
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
* [-a] <autProtocol>  - The authProtocol(MD5/SHA). Mandatory if
authPassword is specified
* [-w] <authPassword> - The authentication password.
* [-s] <privPassword> - The privacy protocol password. Must be accompanied
with auth password and authProtocol fields.
* [-n] <contextName>  - The contextName to be used for the v3 pdu.
* [-i] <contextID>    - The contextID to be used for the v3 pdu.
* host Mandatory      - The RemoteHost (agent).Format (string without
double qoutes/IpAddress).

* OID  Mandatory      - Give multiple no. of Object Identifiers.
*/
import java.util.*;
import java.net.*;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.*;
import com.adventnet.snmp.snmp2.usm.*;
import ParseOptions;
import SetValues;



public class TrapGenerator implements SnmpClient 
{      
       int trapCount    =   1000;
       String host      =   "localhost"; 
       String port      =   "3001";           
       int tempCount    =   0;
       
       public void sendTrapsInBursts(String[] optArgs,TrapGenerator tg)
       {            
    
              host=optArgs[0];
              port=optArgs[1];  
              trapCount=Integer.parseInt(optArgs[2]);   
               // Start SNMP API
               SnmpAPI api;
               api = new SnmpAPI();
               api.start();                              
               SnmpSession session = new SnmpSession(api);               
               session.setPeername(host);
               session.setRemotePort(Integer.parseInt(port)); 
               //SetValues setVal = new SetValues( session, values ); 
               //if(setVal.usage_error) opt.usage_error();
               // Build get request PDU
               SnmpPDU pdu = new SnmpPDU();
               pdu.setCommand( SnmpAPI.TRP_REQ_MSG );
               byte[] b = new byte[1];
               b[0] = (byte)1;
               pdu.setData(b);                
               for (int i=3;i<optArgs.length;i++) 
               {
                       SnmpOID oid = new SnmpOID(optArgs[i]);
                       if (oid.toValue() == null) 
                       System.err.println("Invalid OID argument: " + optArgs[i]);
                       else pdu.addNull(oid);
               }
               SnmpVar var = null;
               try {
                       
                       session.addSnmpClient(tg);
                       session.open();
                       var = SnmpVar.createVariable(".1.3.6.1.2.1.11",SnmpAPI.OBJID);
               } catch (SnmpException e) {
                       System.err.println("Open session: "+e.getMessage());
               }

              /* pdu.setUserName(setVal.userName.getBytes());
               if(session.getVersion()==SnmpAPI.SNMP_VERSION_3) {
                       USMUtils.init_v3_params(setVal.userName, setVal.authProtocol,setVal.authPassword, setVal.privPassword,session.getPeername(),session.getRemotePort(),session);   
                       pdu.setContextName(setVal.contextName.getBytes());
                       pdu.setVariable(1,var);
                       pdu.setContextID(setVal.contextID.getBytes());
               }*/
               SnmpOID oid = new SnmpOID(".1.3.6.1.2.1.11");
               addvarbind(pdu, oid, "INTEGER", String.valueOf("5"));
               int count=0;
               int temp=0;
               long lastd = System.currentTimeMillis();               	           
               while (true)
               {
                       try {
                               pdu.setEnterprise(new SnmpOID(".1.3.6.1.2.1.11"));
                               pdu.setTrapType(4);
                               pdu.setSpecificType(0);
                               double rand = Math.random();                               
                               pdu.setUpTime(System.currentTimeMillis());
                               tempCount =0;
                               rand = Math.random();
                               String str = String.valueOf((int)Math.round(rand*4) + 1);
                               SnmpVar var1 = SnmpVar.createVariable(str,SnmpAPI.INTEGER);
                               pdu.setVariable(1, var1);                               
                               while(tempCount < 0)
                               {
                                       tempCount++;
                                       indicateIt(tempCount);
                               }               
                               temp++;
                               session.send(pdu);                                                                                              
                               if ((++count%100)==0) //Outputs the rate once in ___
                               {
                                       long date = System.currentTimeMillis();                              
                                       //System.out.println(temp+ " Requests Sent at: " + (temp*1000/(date-lastd)) + " per second");                                        
                                       if(count==trapCount) 
                                       { 
                                         FileUtil.appendMessagesToFile(" Traps Sent to the Host: " + host+ " Waiting On Port: "+port+"\n",TrapReceiveRateRecorder.file,false);                                                  
                                         FileUtil.appendMessagesToFile(" Traps Sent In Burst Mode: " + count+ " At A Rate Of: "+(count*1000/(date-lastd)) + " per second \n",TrapReceiveRateRecorder.file,false);   
                                         break;               
                                        // System.exit(0);                                        
                                       }
                                       temp = 0;
                                       lastd = date;        
                               }
                             
                            } catch (SnmpException e) {
                               System.err.println("Sending PDU"+e.getMessage());
                            }
                }    
            
        }
       
       public void sendTrapAtSteadyRate(String[] optArgs,TrapGenerator tg)
       {                    
              host=optArgs[0];
              port=optArgs[1];  
              trapCount=Integer.parseInt(optArgs[2]);  
 
               // Start SNMP API
               SnmpAPI api;
               api = new SnmpAPI();
               api.start();                              
               SnmpSession session = new SnmpSession(api);               
               session.setPeername(host);
               session.setRemotePort(Integer.parseInt(port)); 
               //SetValues setVal = new SetValues( session, values ); 
               //if(setVal.usage_error) opt.usage_error();

               // Build get request PDU
               SnmpPDU pdu = new SnmpPDU();
               pdu.setCommand( SnmpAPI.TRP_REQ_MSG );
               byte[] b = new byte[1];
               b[0] = (byte)1;
               pdu.setData(b);                
               for (int i=3;i<optArgs.length;i++) 
               {
                       SnmpOID oid = new SnmpOID(optArgs[i]);
                       if (oid.toValue() == null) 
                       System.err.println("Invalid OID argument: " + optArgs[i]);
                       else pdu.addNull(oid);
               }
               SnmpVar var = null;
               try {
                       
                       session.addSnmpClient(tg);
                       session.open();
                       var = SnmpVar.createVariable(".1.3.6.1.2.1.11",SnmpAPI.OBJID);
               } catch (SnmpException e) {
                       System.err.println("Open session: "+e.getMessage());
               }

              /* pdu.setUserName(setVal.userName.getBytes());
               if(session.getVersion()==SnmpAPI.SNMP_VERSION_3) {
                       USMUtils.init_v3_params(setVal.userName, setVal.authProtocol,setVal.authPassword, setVal.privPassword,session.getPeername(),session.getRemotePort(),session);   
                       pdu.setContextName(setVal.contextName.getBytes());
                       pdu.setVariable(1,var);
                       pdu.setContextID(setVal.contextID.getBytes());
               }*/
               SnmpOID oid = new SnmpOID(".1.3.6.1.2.1.11");
               addvarbind(pdu, oid, "INTEGER", String.valueOf("5"));
               int count=0;
               int temp=0;
               long lastd = System.currentTimeMillis();               	           
               while (true)
               {
                       
                       try {
                               pdu.setEnterprise(new SnmpOID(".1.3.6.1.2.1.11"));
                               pdu.setTrapType(4);
                               pdu.setSpecificType(0);
                               double rand = Math.random();                               
                               pdu.setUpTime(System.currentTimeMillis());
                               tempCount =0;
                               rand = Math.random();
                               String str = String.valueOf((int)Math.round(rand*4) + 1);
                               SnmpVar var1 = SnmpVar.createVariable(str,SnmpAPI.INTEGER);
                               pdu.setVariable(1, var1);                               
                               while(tempCount < 0)
                               {
                                       tempCount++;
                                       indicateIt(tempCount);
                               }               
                               temp++;
                               session.send(pdu);
                               
                               
                               if ((++count%100)==0) //Outputs the rate once in ___
                               {
                                       long date = System.currentTimeMillis();                                                                     
                                       try{Thread.sleep(5000);}
                                       catch(Exception ex){continue;}                                                                
                                       if(count==trapCount) 
                                       {                             
                                         FileUtil.appendMessagesToFile(" Traps Sent to the Host: " + host+ " Waiting On Port: "+port+"\n",TrapReceiveRateRecorder.file,false);                                                                  
                                         FileUtil.appendMessagesToFile(" Traps Sent In Steady Mode: " + count+
                                             " At A Rate Of: "+(count*1000/(date-lastd)) + 
                                             " Per Second \n",TrapReceiveRateRecorder.file,false);                  
                                         break;               
                                        // System.exit(0);                                        
                                       }
                                       temp = 0;
                                       lastd = date;        
                               }
                             
                            } catch (SnmpException e) {
                               System.err.println("Sending PDU"+e.getMessage());
                            }
                }       
        }


       /** We need to implement the other methods in the SnmpClient interface */
       public void debugPrint(String s) {
               System.err.println(s);
       }

       public boolean authenticate(SnmpPDU pdu, String community) {
               return (pdu.getCommunity().equals(community));
       }
       public static void indicateIt(int count)
       {
               int cc = count;
       }


          /** adds the varbind  with specified oid, type and value to the pdu */
    static void addvarbind(SnmpPDU pdu, SnmpOID oid, String type, String value)  
    {        
        byte dataType ;
        if (type.equals("INTEGER")) {
            dataType = SnmpAPI.INTEGER;
        } else if (type.equals("STRING")) {
            dataType = SnmpAPI.STRING;
        } else if (type.equals("GAUGE")) {
            dataType = SnmpAPI.GAUGE;
        } else if (type.equals("TIMETICKS")) {
            dataType = SnmpAPI.TIMETICKS;
        } else if (type.equals("OPAQUE")) {
            dataType = SnmpAPI.OPAQUE;
        } else if (type.equals("IPADDRESS")) {
            dataType = SnmpAPI.IPADDRESS;
        } else if (type.equals("COUNTER")) {
            dataType = SnmpAPI.COUNTER;
        } else if (type.equals("OID")) { 
            dataType = SnmpAPI.OBJID;
        } else if (type.equals("BITS")) { 
                       dataType = SnmpAPI.BITSTRING;
               } else { // unknown type
            System.err.println("Invalid variable type: " + type);
            return;

        }

        SnmpVar var = null;
        try {
            // create SnmpVar instance for the value and the type
            var = SnmpVar.createVariable( value, dataType );
        }
        catch(SnmpException e){
            System.err.println("Cannot create variable: "+oid+" with value: "+value);           
            return;
        }
        //create varbind
        SnmpVarBind varbind = new SnmpVarBind(oid, var);
        // add variable binding
        pdu.addVariableBinding(varbind);

    }
       int rcount=0;
       long rlastd;
        
       /** The callback for incoming PDUs */
       public boolean callback(SnmpSession session, SnmpPDU npdu, int reqid) {

               if (npdu == null) {  // timeout
                       return true;
              }

               if(npdu.getVersion() == SnmpAPI.SNMP_VERSION_1) {
                       if (npdu.getErrstat() == 0) rcount++;
                       else  System.out.println("Error Indication in response: " + SnmpException.exceptionString((byte)npdu.getErrstat()) + "\nErrindex: " + npdu.getErrindex());       
               } else if((npdu.getVersion() == SnmpAPI.SNMP_VERSION_2C) ||(npdu.getVersion() == SnmpAPI.SNMP_VERSION_3)) {  
                       if (npdu.getErrstat() != 0) 
                               System.out.println("Error Indication in response: " +SnmpException.exceptionString((byte)npdu.getErrstat()) + "\nErrindex: " + npdu.getErrindex());  
                       else rcount++;

               } else System.out.println("Invalid Version Number");
               if (rcount >= 1000) {
                       long date = System.currentTimeMillis();
                       System.out.println("1,000 Requests received at: " + (rcount*1000/(date-rlastd)) + " per second");
                       rcount = 0;
                       rlastd = date;
               }
               return true;
       }
       

        public static void main(String args[]) 
       {
            
            String usage = "TrapGenerator [-d] [-v version(v1,v2,v3)] [-ccommunity] [-p port] [-u user] [-a auth_protocol] [-w auth_password] [-s priv_password] [-n contextName] [-i contextID] host numberoftraps OID [OID] ...";   
            String options[] = {"-d","-c","-wc","-p","-r","-t","-m","-v","-u","-a","-w","-s","-n","-i"};
            String values[] = { "None", null, null, null, null, null, "None", null,null, null, null, null, null, null, null };       
            ParseOptions opt = new ParseOptions(args,options,values, usage);
            //if (values[0].equals("Set")) api.setDebug( true );
            if (opt.remArgs.length<3) System.out.println("Usage Error "+usage);//opt.usage_error();
       }


} // End Of Class
