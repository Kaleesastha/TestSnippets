

/* This sample code is to reproduce the NoRouteToHost Exception while pinging the nodes 
in the network when the router is not configured properly */

import java.io.*;
import java.util.*;
import java.net.*;

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.beans.*;
import com.adventnet.snmp.mibs.*;
import com.adventnet.nms.util.*;

public class SnmpGetTest
{
    public static void main(String args[]) 
    {
        try
            {
                boolean bool = true;
                while(bool)
                    {
                        FileWriter pw = new FileWriter("output.txt",true);
                        for(int i = 0 ; i < args.length ; i++)
                            {
                                Thread.sleep(10000);
                                try
                                    {
                        
                                        SnmpTarget target = new SnmpTarget();
                                        target.setTargetHost(args[i]);
                                        target.setCommunity("public");
                                        target.setSnmpOID(new SnmpOID("1.3.0"));
                                        target.setTargetPort(161);
                                        String sysDescr = target.snmpGet();
                                     
                                        Date d = new Date(System.currentTimeMillis());
                                        int h = d.getHours();
                                        int m = d.getMinutes();
                                        int s = d.getSeconds();
                        
                                        pw.write("\nTIME      --->   "+h +" :"+m+" :"+s);
                                        pw.write("\nHOST       --->   "+args[i]);
                                        pw.write("\nSNMP PING  --->   "+sysDescr);
                                        pw.write("\nPING RESULT -->   "+com.adventnet.nms.util.Ping.ping(args[i]));
                                

                                        SnmpPDU pdu = new SnmpPDU();
                                        pdu.setRemotePort(161);
                                        pdu.setCommand(SnmpAPI.GET_REQ_MSG);
                                        pdu.addNull(new SnmpOID("1.2.0")); 
                                        pdu.setRemoteHost(args[i]);
                                        pdu.setCommunity("public");
                                        pdu.setReqid(0);
                                        int length = pdu.getEncodedLength(null);
                                        DatagramSocket sessionSocket = new DatagramSocket();
                                        DatagramPacket sessionPacket = new DatagramPacket(new byte[length], length);
                                        sessionPacket.setAddress(InetAddress.getByName(args[i]));
                                        sessionPacket.setPort(161);
                                        sessionPacket.setData(pdu.getData());
                                        sessionSocket.send(sessionPacket);
                                    }
                                catch(Exception e)
                                    {
                                        try
                                            {
                                                pw.write("\n");
                                                pw.write(e.toString());
                                                pw.write("\n\n");
                                                continue;
                                            }catch(Exception ee){}
                                    }
                                pw.write("\n\n");
                            }
                        pw.close();
                    }
            }
        catch(Exception e)
            {
            }
    }
}


