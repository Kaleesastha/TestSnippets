/**
$Id: tl1changes.java,v 1.1 2003/06/04 12:45:39 elangovan Exp $

File Name      : tl1changes.java
Description    : This program will automatically make changes in the tl1seed.file and seed.file 		required for the testplan tl1_perf_tp.html
Other Info     : This program can be to validate the first part of the testplan tl1_perf_tp.html 		(ie for 14 testcases)
USAGE          : java tl1changes <ipaddress1> <tl1port> [<ipaddress2> <tl1port>]	

 
Preample:
	Make sure that this program(tl1changes.java) is stored in the <WEBNMS_HOME>/	
	conf directory and compiled and executed only from there.
	

Owner Name     : G.Elangovan	

Change History	: G.Elangovan		03-06-2003		Initial version 

**/

import java.io.*;



public class tl1changes 
{	
public static void main(String args[])
        {              
                FileOutputStream out; 
                PrintStream p; 
	        File file = new File("tl1seed.file");
                File file2 = new File("tl1seed.file_org");
      	 	boolean success = file.renameTo(file2);
		if (!success) {
    			System.out.println("tl1seed.file was not successfully renamed");
		  }

		File file1 = new File("seed.file");
                File file3 = new File("seed.file_org");
                boolean success1 = file1.renameTo(file3);
                if (!success1) {
			System.out.println("seed.file was not successfully renamed");
                 }
		
		try
                {
                out = new FileOutputStream("tl1seed.file");
	        p = new PrintStream( out );
		p.println ("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
		p.println ("<TL1DISCOVERY>");
		p.println("<TO_DISCOVERIP tl1port=\""+args[1]+"\" type=\"TL1Node\" dictionary=\"default.xml\" connectionHandler= \"com.adventnet.nms.topodb.tl1.TL1BlankConnHandler\" >");
		p.println("<GROUP deviceGroupName=\"Acme-MSU\" tl1port=\""+args[1]+"\" type=\"acme_msu_node\">");
		p.println("<loginCommand command= \"ACT-USER::ROOT:::PUBLIC;\"/>");
		p.println("<initCommand command=\"RTRV-IP::ETHER:;\" response=\"IntfCard=IPTYPE,Gateway=GATEWAY,GatewayIP=IP,GatewayMask=MASK,BroadcastIP=BRDCAST,MTU=MTU,MacAddr=MacAddr\"/>");
		p.println("<statpollCommand command=\"RTRV-IP::ETHER:;\"/>");
		p.println("<IP ipaddress=\""+args[0]+"\" tl1port=\""+args[1]+"\">");
		p.println("</IP>");
		p.println("<IP ipaddress=\""+args[2]+"\" tl1port=\""+args[3]+"\">");
                p.println("</IP>");
		p.println("</GROUP>");
		p.println("</TO_DISCOVERIP>");
		p.println("</TL1DISCOVERY>");
                p.close();
                out = new FileOutputStream("seed.file");
                p = new PrintStream(out);
		p.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> \n<!DOCTYPE SEED SYSTEM \"seed.file.dtd\">\n<SEED>");
		p.println("<DISCOVERY DISCOVER=\"true\" DISCOVER_LOCALNET=\"true\" ENABLE_SNMP_DISCOVERY=\"true\" ENABLE_ICMP_DISCOVERY=\"true\" ENABLE_SNMPV3_DISCOVERY=\"false\" DISCOVERY_INTERVAL=\"1\" REDISCOVER_INTERVAL=\"24\" HOUR=\"17,9\" DAY_OF_THE_MONTH=\"-1\" DAY_OF_THE_WEEK=\"MON,TUE\" REDISCOVER_ALREADY_DISCOVERED=\"false\" PING_RETRIES=\"0\" SNMP_TIMEOUT=\"2\" SNMP_RETRIES=\"0\" READ_COMMUNITY=\"public\" WRITE_COMMUNITY=\"public\" ENABLE_LOG=\"true\"/>\n<NATIVE_PING ICMP_RETRIES=\"2\" ICMP_TIMEOUT=\"2\" ICMP_DEBUG_LEVEL=\"1\" ICMP_SWEEP_PACKETS=\"10\" ICMP_SWEEP_SLEEP_INTERVAL=\"2\" PING_SWEEP=\"false\"/>\n<TO_DISCOVERIP>\n<ip NODE_ID=\""+args[0]+"\" NETMASK=\"255.255.255.0\"/>\n<ip NODE_ID=\""+args[2]+"\" NETMASK=\"255.255.255.0\"/>\n</TO_DISCOVERIP>\n</SEED>");
			p.close();
                }
                catch (Exception e)
                {
                        System.err.println ("Error writing to file");
                }
        }
}
