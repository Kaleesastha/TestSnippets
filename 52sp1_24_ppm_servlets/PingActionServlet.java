/* $Id: PingActionServlet.java,v 1.1.4.3 2013/07/12 12:31:09 pradheep.v Exp $ */
package com.adventnet.nms.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.alertdb.AlertAPI;
import com.adventnet.nms.topodb.Node;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.util.NmsUtil;

public class PingActionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String entity = request.getParameter("entity");//NO I18N
		String command = request.getParameter("command");//NO I18N
		
		String output = processRequest(entity, command);
		
		PrintWriter out = response.getWriter();
		out.println(output);
    }
	
	private String processRequest(String entity, String command) {
		
		String osname = System.getProperty("os.name");//NO I18N
	    String cmd = "";
		String output = "";
        String error = "";
        String result = "";
        String ipaddress = "";
        BufferedReader brError = null, br=null;
        Process process = null;
        
        ipaddress = getIPFromEntity(entity);
        
        try {
			InetAddress.getByName(ipaddress);
		} catch (UnknownHostException e1) {
			return "Invalid node : " + ipaddress;///NO I18N
		}
        
        if(command.equals("ping")) {//NO I18N
			cmd = getPingCommand(osname, ipaddress);
		} else if(command.equals("traceroute")) {//NO I18N
			cmd = getTraceRouteCommand(osname, ipaddress);
		} 
        
        try {
            
        	process = Runtime.getRuntime().exec(cmd);
        	
        	br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        	
        	String str = br.readLine();
        	while (str != null) {
        		output = output + str + "<br>";//NO I18N
        		str = br.readLine();
        	}
           
        	brError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        	str = brError.readLine();
        	while (str != null) {
        		error = error + str + "<br>";//NO I18N
        		str = brError.readLine();
        	}
        	
        	process.waitFor();
        	if (!error.equals("")) {
        		result = error;
        	}

        	if (output.trim().equals("")) {
        		result = "Error in executing command on device.";//NO I18N
        	} else {
        		result = output;
        	}
        } catch(Exception e) {
		String expMessage = e.getMessage();
		if(expMessage.startsWith("Cannot run program")) {
			result = command + " program not installed in the server. <br> Install the command and try again.";
		} else {
			result = "Error ocurred while executing " + command + " command";
		}
        }
	    
		return result;
	}
	
	private String getIPFromEntity(String Entity) {
		String ipaddr = null;
		AlertAPI api=(AlertAPI)NmsUtil.getAPI("AlertAPI");//NO I18N
        Alert a=null;
        try {
                a = api.getAlert(Entity);
        } catch (RemoteException e1) {
        } 
        
        if(a!=null) {
                ipaddr = a.getSource();
        }
        else
		{
		  TopoAPI tapi=(TopoAPI)NmsUtil.getAPI("TopoAPI");//NO I18N
		  Node n=null;
			try 
			{
				n = (Node) tapi.getByName(Entity);
			} 
			catch (Exception e1) {
			} 
			if(n!=null)
			{
				ipaddr = n.getIpAddress();
			}

		}
        return ipaddr;
	}
	
	private String getPingCommand(String osName, String ipAddress) {
		String pingPath=NmsUtil.getParameter("FAULT_PING_PATH");//NO I18N
		String pingCommand = "ping -n 5 " + ipAddress;//NO I18N
		boolean isWindows = osName.toLowerCase().startsWith("windows");//NO I18N
	    boolean isLinux = osName.toLowerCase().startsWith("linux");//NO I18N
	    boolean isSolaris = osName.toLowerCase().startsWith("solaris");//NO I18N
	    
	    if(pingPath == null) {
	    	if(isSolaris) {
	    		pingCommand = "/usr/sbin/ping " + ipAddress + " 64 5";//NO I18N
	    	} else if(isLinux) {
	    		pingCommand = "/bin/ping -c 5 " + ipAddress;//NO I18N
	    	} else if(isWindows) {
	    		pingCommand = "ping -n 5 " + ipAddress;//NO I18N
	    	}
	    } else {
	    	if(isSolaris) {
	    		pingCommand = pingPath.trim() + " " + ipAddress + " 64 5";//NO I18N
	    	} else if(isLinux) {
	    		pingCommand = pingPath.trim() + " -c 5 " + ipAddress;//NO I18N
	    	} else if(isWindows) {
	    		pingCommand = pingPath.trim() + " -n 5 " + ipAddress;//NO I18N
	    	}
	    }
	    
		return pingCommand;
	}
	
	private String getTraceRouteCommand(String osName, String ipAddress) {
		String traceRoutePath = NmsUtil.getParameter("FAULT_TRACEROUTE_PATH");//NO I18N
		
		if(traceRoutePath != null) {
			return traceRoutePath.trim() + " " + ipAddress;//NO I18N
		}
		
		String traceRouteCommand = "traceroute " + ipAddress;//NO I18N
		boolean isWindows = osName.toLowerCase().startsWith("windows");//NO I18N
		
		if(isWindows) {
			traceRouteCommand = "tracert " + ipAddress;//NO I18N
		}
		
		return traceRouteCommand;
	}
	
}
