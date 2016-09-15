/* This is a test WatchUtil */
package test;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import java.io.*;
import java.util.*;

import com.adventnet.nms.util.*;

public class TestWatch extends HttpServlet
{
    HttpServletRequest req = null;
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {
        doGet(req, res);
    }
    private void writeIPList(String list[])
    {
        for(int i=0;i< list.length;i++)
            System.out.println("\t\tIPAddress  :   "+ list[i]);

    }
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
            String temp=null;
            WatchUtil target=new WatchUtil();
            System.out.println(" \t\t\t Test Program : TestWatchUtil");
            System.out.println(" \t\t\t##############################");
            System.out.println(" Method : getDefaultNetMask(netaddress)");
            System.out.println("########################################");
            System.out.println(" Class-A NetAddress : --> getDefaultNetMask(127.23.3.0) = ahi"+ target.getDefaultNetMask("127.23.3.0"));
            System.out.println(" Class-B NetAddress : --> getDefautlNetMask(190.23.3.0) = "+ target.getDefaultNetMask("190.23.3.0"));
            System.out.println(" Class-C NetAddress : --> getDefautlNetMask(198.23.3.0) = "+ target.getDefaultNetMask("198.23.3.0"));
            try
            {
                temp=target.getDefaultNetMask("265.23.3.0");
                if(temp==null)
                    System.out.println(" Wrong NetAddress : (netaddress > 255) --> getDefautlNetMask(265.23.3.0) =  Invalid NetAddress : "+temp);   
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            try
            {
                temp=target.getDefaultNetMask("192.23w.3w.0");
                if(temp==null)
                    System.out.println(" Wrong NetAddress : (netaddress with character --> getDefautlNetMask(192.23w.3w.0) =  Invalid NetAddress : "+temp); 
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            try
            {
                temp=target.getDefaultNetMask("192,23,3,0");
                if(temp==null)
                    System.out.println(" Wrong NetAddress : (netaddress with special character ) --> getDefautlNetMask(192,23,3,0) =  Invalid NetAddress : "+temp);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            try
            {
                temp=target.getDefaultNetMask("0.0.0.0");
                if(temp==null)
                    System.out.println(" Wrong NetAddress : (Netaddress with all zeros) --> getDefaultNetMask(0.0.0.0) = Invalid NetAddress : "+temp);
                else
                    System.out.println(" getDefaultNetMask(0.0.0.0) = "+temp);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+ e.getMessage());
            }
            try
            {
                temp=target.getDefaultNetMask(null);
                if(temp==null)
                    System.out.println(" Wrong NetAddress : (null Netaddress) --> getDefaultNetMask(null) = Invalid NetAddress : "+temp);
                else
                    System.out.println(" getDefaultNetMask(null) = "+temp);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : " + e.getMessage());
            }

            System.out.println(" Method : getNumIPs(netaddress,netmask)");
            System.out.println("########################################");
            
            long numips;
            numips=target.getNumIPs("127.4.5.0","255.0.0.0");
            System.out.println(" class-A : getNumIPs(127.4.5.0 , 255.0.0.0) --> " +numips);
            numips=target.getNumIPs("127.4.5.0","255.255.0.0");
            System.out.println(" class-A : getNumIPs(127.4.5.0 , 255.255.0.0) --> "+ numips);
            numips=target.getNumIPs("127.4.5.0","255.255.255.0");
            System.out.println(" class-A : getNumIPs(127.4.5.0 , 255.0.0.0) --> "+ numips);
            numips=target.getNumIPs("191.4.5.0","255.0.0.0");
            System.out.println("class-B getNumIPs(191.4.5.0 , 255.0.0.0) --> " +numips);
            numips=target.getNumIPs("191.4.5.0","255.255.0.0");
            System.out.println("class-B getNumIPs(191.4.5.0 , 255.255.0.0) --> " +numips);
            numips=target.getNumIPs("191.4.5.0","255.255.255.0");
            System.out.println("class-B getNumIPs(191.4.5.0 , 255.255.255.0) --> "+ numips);
            try
            {
                numips=target.getNumIPs("194.4.5.0","255.0.0.0");
                System.out.println("class-C getNumIPs(194.4.5.0 , 255.0.0.0) --> " +numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            try
            {
                numips=target.getNumIPs("194.4.5.0","255.255.0.0");
                System.out.println("class-C getNumIPs(194.4.5.0 , 255.255.0.0) --> " +numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            try
            {
                numips=target.getNumIPs("194.4.5.0","255.255.255.0");
                System.out.println("class-C getNumIPs(194.4.5.0 , 255.255.255.0) --> "+ numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            try
            {
                numips=target.getNumIPs("192.168.4.0","255.255.0.255");
                System.out.println("getNumIPs(192.168.4.0 , 255.255.0.255) --> "+ numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }                

            try
            {    
                numips=target.getNumIPs("192.168.4.0","255.0.0.255");
                System.out.println("getNumIPs(192.168.4.0 , 255.0.0.255) --> " +numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            try
            {
                numips=target.getNumIPs("192.168.4.0","0.0.0.255");
                System.out.println("getNumIPs(192.168.4.0 , 0.0.0.255) --> " +numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }                
            try
            {
                numips=target.getNumIPs("192.168.4.0","2552552550");
                System.out.println("getNumIPs(192.168.4.0 , 2552552550) --> "+ numips);                
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            try
            {
                numips=target.getNumIPs("192.168.4.0","255r.255y.255u.0");
                System.out.println("getNumIPs(192.168.4.0 , 255r.255y.255r.0) --> "+ numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }

            try
            {
                numips=target.getNumIPs("192.168.4.0","255.255.265.0");
                System.out.println("getNumIPs(192.168.4.0 , 255.255.265.0) --> " +numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }           
            try
            {
                numips=target.getNumIPs("334.34.55.66","255.255.255.0");
                System.out.println("getNumIPs(334.34.55.66 , 255.255.265.0) --> " +numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }           
            try
            {
                numips=target.getNumIPs("192.34e.55.66","255.255.255.0");
                System.out.println("getNumIPs(192.34e.55.66 , 255.255.265.0) --> " +numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }           
            try
            {
                numips=target.getNumIPs("192.45.445.0","255.255.255.0");
                System.out.println("getNumIPs(192.45.445.0 , 255.255.265.0) --> " +numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }           

            try
            {
                numips=target.getNumIPs("192.45.445.0","255.255.255.0");
                System.out.println("getNumIPs(192.45.445.0 , 255.255.265.0) --> " +numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }           
            try
            {
                numips=target.getNumIPs("192.168.4.0",null);
                System.out.println("getNumIPs(192.168.4.0 , null) --> " +numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }           
            try
            {
                numips=target.getNumIPs(null,"255.255.255.0");
                System.out.println("getNumIPs(null , 255.255.265.0) --> " +numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }           
            try
            {
                numips=target.getNumIPs(null,null);
                System.out.println("getNumIPs(null , null) --> " +numips);
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }           
            System.out.println(" Method getIPList(netaddress , netmask)");
            System.out.println("########################################");
//To test the get IPList please uncomment the following lines. 
/*
            String iplist[]=null;
            iplist=target.getIPList("192.168.4.0","255.255.255.0");
            System.out.println(" IPList for NetAddress[192.168.4.0] & NetMask[255.255.255.0]");
            writeIPList(iplist);

            iplist=target.getIPList("192.168.4.0","255.255.0.0");
            System.out.println(" IPList for NetAddress[192.168.4.0] & NetMask[255.255.0.0]");
            writeIPList(iplist);

            iplist=target.getIPList("192.168.4.0","255.0.0.0");
            System.out.println(" IPList for NetAddress[192.168.4.0] & NetMask[255.0.0.0]");
            writeIPList(iplist);

            iplist=target.getIPList("192.168.4.0","255.255.0.255");
            System.out.println(" IPList for NetAddress[192.168.4.0] & NetMask[255.255.0.255]");
            writeIPList(iplist);
            iplist=target.getIPList("192.168.4.0","255.0.255.255");
            System.out.println(" IPList for NetAddress[192.168.4.0] & NetMask[255.0.255.255]");
            writeIPList(iplist);
            iplist=target.getIPList("192.168.4.0","0.255.255.255");
            System.out.println(" IPList for NetAddress[192.168.4.0] & NetMask[0.255.255.255]");
            writeIPList(iplist);
            iplist=target.getIPList("192.168.4.0","255.255.255.255");
            System.out.println(" IPList for NetAddress[192.168.4.0] & NetMask[255.255.255.255]");
            writeIPList(iplist);
            iplist=target.getIPList("192.168.4.0","255e.255.255.255");
            System.out.println(" IPList for NetAddress[192.168.4.0] & NetMask[255e.255.255.255]");
            writeIPList(iplist);
            iplist=target.getIPList("323.45.67.77","255.255.255.0");
            System.out.println(" IPList for NetAddress[323.45.67.77] & NetMask[255.255.255.0]");
            writeIPList(iplist);
            iplist=target.getIPList("192.168e.4.0","255.255.255.0");
            System.out.println(" IPList for NetAddress[192.168e.4.0] & NetMask[255.255.255.0]");
            writeIPList(iplist);
            iplist=target.getIPList(null,"0.255.255.255");
            System.out.println(" IPList for NetAddress[null] & NetMask[0.255.255.255]");
            writeIPList(iplist);
            iplist=target.getIPList("192.168.4.0",null);
            System.out.println(" IPList for NetAddress[192.168.4.0] & NetMask[null]");
            writeIPList(iplist);
            iplist=target.getIPList(null,null);
            System.out.println(" IPList for NetAddress[null] & NetMask[null]");
            writeIPList(iplist);
            iplist=target.getIPList("0","0");
            System.out.println(" IPList for NetAddress[0] & NetMask[0]");
            writeIPList(iplist);
            iplist=target.getIPList("0","0.255.255.255");
            System.out.println(" IPList for NetAddress[0] & NetMask[0.255.255.255]");
            writeIPList(iplist);
            iplist=target.getIPList("192.168.4.0","0");
            System.out.println(" IPList for NetAddress[192.168.4.0] & NetMask[0]");
            writeIPList(iplist);
*/
            System.out.println(" Method getNetAddr(ipaddress,netmask)");
            System.out.println("######################################");
            
            System.out.println(" Class-C netmask(255.255.255.0) & ipaddress[192.168.4.73]");
            System.out.println("NetAddress : "+WatchUtil.getNetAddr("192.168.4.73","255.255.255.0"));

            System.out.println(" Class-B netmask(255.255.0.0) & ipaddress[192.168.4.73]");
            System.out.println("NetAddress : "+WatchUtil.getNetAddr("192.168.4.73","255.255.0.0"));

            System.out.println(" Class-A netmask(255.0.0.0) & ipaddress[192.168.4.73]");
            System.out.println("NetAddress : "+WatchUtil.getNetAddr("192.168.4.73","255.0.0.0"));

            System.out.println(" Netmask(255.255.255.0) & ipaddress[192.168.4.373]");
            System.out.println("NetAddress : "+WatchUtil.getNetAddr("192.168.4.373","255.255.255.0"));

            System.out.println(" Netmask(255.255.255.0) & ipaddress[192,168,4,73]");
            System.out.println("NetAddress : "+WatchUtil.getNetAddr("192,168,4,73","255.255.255.0"));

            System.out.println(" Netmask(255.255.255.0) & ipaddress[192t.168y.4.73]");
            System.out.println("NetAddress : "+WatchUtil.getNetAddr("192t.168y.4.73","255.255.255.0"));

            System.out.println(" Netmask(255.255.255.0) & ipaddress[192.168.4.73]");
            System.out.println("NetAddress : "+WatchUtil.getNetAddr("192.168.4.73","255.255.0.255"));
            System.out.println(" Netmask(255.255.255.0) & ipaddress[192.168.4.73]");
            System.out.println("NetAddress : "+WatchUtil.getNetAddr("192.168.4.73","255.0.255.255"));
            System.out.println(" Netmask(null) & ipaddress[null]");
            System.out.println("NetAddress : "+WatchUtil.getNetAddr(null,null));
            System.out.println(" Netmask(null) & ipaddress[192.168.4.73]");
            System.out.println("NetAddress : "+WatchUtil.getNetAddr("192.168.4.73",null));
            System.out.println(" Netmask(255.255.255.0) & ipaddress[null]");
            System.out.println("NetAddress : "+WatchUtil.getNetAddr(null,"255.255.255.0"));
            System.out.println(" Method convertAddr(long addr) & getAddrLong(string add)");
            System.out.println("#########################################################");
            System.out.println(" IPAddress : 192.168.4.73");
            System.out.println(" Incremented IP Address : "+(WatchUtil.getAddrLong("192.168.4.173")+1));
            try
            {
                System.out.println(" IPAddress : 192.368,4e,73");
                System.out.println(" Incremented IP Address : "+(WatchUtil.getAddrLong("192.368,4e,73")+1));  
            }catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            try
            {
                System.out.println(" IPAddress : 192.168.4.265");
                System.out.println(" Incremented IP Address : "+(WatchUtil.getAddrLong("192.168.4.265")+1));  
            }catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            
            try
            {
                System.out.println(" IPAddress : null");
                System.out.println(" Incremented IP Address : "+(WatchUtil.getAddrLong(null)+1));  
            }catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            try
            {
                System.out.println(" IPAddress : 0");
                System.out.println(" Incremented IP Address : "+(WatchUtil.getAddrLong("0")+1));  
            }catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            System.out.println(" Method getDNSName(ipaddress)");
            System.out.println("##############################");
            System.out.println(" getDNSName(192.168.4.73) : "+WatchUtil.getDNSName("192.168.4.73"));
            try
            {
                System.out.println(" getDNSName(192.r8.4.373) : "+WatchUtil.getDNSName("192.r8.4.373"));
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }
            
              
            System.out.println(" Method getIP(name)");
            System.out.println("##############################");
            System.out.println(" getIP(rameshj) : "+WatchUtil.getIP("rameshj"));
            try
            {
                System.out.println(" getIP(dsfaff) : "+WatchUtil.getIP("dsfaff"));
            }
            catch(Exception e)
            {
                System.out.println(" Exception : "+e.getMessage());
            }

            System.out.println(" Method inNet(ipaddress,netaddress,netmask)");
            System.out.println("############################################");
            System.out.println(" inNet(192.168.4.73 , 192.168.4.0 , 255.255.255.0  ) : "+WatchUtil.inNet("192.168.4.73","192.168.4.0","255.255.255.0"));
            System.out.println(" inNet(192.368.44.73 , 192.168.4.ee , 255.255.255.0  ) : "+WatchUtil.inNet("192.368.44.73","192.168.4.ee","255.255.255.0"));

            System.out.println(" Method getMinMaxAddr(vector ipadlist,boolean min)");
            System.out.println("############################################");
            Vector vec=new Vector();
            vec.addElement((Object)"192.168.4.71");
            vec.addElement((Object)"192.168.4.72");
            vec.addElement((Object)"192.168.4.73");
            vec.addElement((Object)"192.168.4.74");
            System.out.println(" Vector elements : 192.168.4.71 , 192.168.4.72 , 192.168.4.73 , 192.168.4.74 ");
            System.out.println(" Min address is "+WatchUtil.getMinMaxAddr(vec,true));
            System.out.println(" Max address is "+WatchUtil.getMinMaxAddr(vec,false));
            vec.clear();
            vec.addElement((Object)"264.3.4.5");
            vec.addElement((Object)"198e.168.4.73");
            System.out.println(" Vector elements : 264.3.4.5 , 198e.168.4.73");           
            System.out.println(" Min address is "+WatchUtil.getMinMaxAddr(vec,true));
            System.out.println(" Max address is "+WatchUtil.getMinMaxAddr(vec,false));
            System.out.println(" Method isAddressInRange(ipaddress,lowaddr,highadd)");
            System.out.println("####################################################");
            vec.clear();
            vec.addElement((Object)"192.168.4.70");
            vec.addElement((Object)"192.130.4.50");
            Vector high=new Vector();
            high.addElement((Object)"192.168.4.75");
            high.addElement((Object)"192.130.4.60");
            System.out.println(" Low  IP : 192.168.4.70 , 192.130.4.50");
            System.out.println(" High IP : 192.168.4.75 , 192.130.4.60");
            System.out.println(" IP address : 192.168.4.73");
            System.out.println(" Result : "+WatchUtil.isAddressInRange("192.168.4.73",vec,high));
            System.out.println(" Low  IP : 192.168.4.70 , 192.130.4.50");
            System.out.println(" High IP : 192.168.4.75 , 192.130.4.60");
            System.out.println(" IP address : 192.168.4.78");
            System.out.println(" Result : "+WatchUtil.isAddressInRange("192.168.4.78",vec,high));
            System.out.println(" Method setDNSEnabled(boolean) & isDNSEnabled");
            System.out.println("###############################################");
            System.out.println(" call setDNSEnabled(true)");
            WatchUtil.setDNSEnabled(true);
            System.out.println(" DNSEnabled is : "+WatchUtil.isDNSEnabled());
            System.out.println(" SystemUtil class : Method cout.println(string)");
            System.out.println("###############################################");
            SystemUtil.cout.println("This Message will be print on console");
            
                               


	}
    
}
















