//$Id: InitialProperties.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.CustomView;
import java.util.*;
class InitialProperties 
{
		Properties idVsDisplayNames=new Properties();
		public Properties getIdVsDisplayNamesForTopo()
		{
		idVsDisplayNames.put("name","Name");
		idVsDisplayNames.put("classname","classname");
		idVsDisplayNames.put("managed","managed");
		idVsDisplayNames.put("type","Type");
		idVsDisplayNames.put("isSNMP","IsSnmp");
		idVsDisplayNames.put("ipAddress","IPAddress");
		idVsDisplayNames.put("netmask","NetMask");
		idVsDisplayNames.put("pollInterval","pollInterval");
		idVsDisplayNames.put("status","Status");
		idVsDisplayNames.put("statusUpdateTime","statusUpdateTime");
		idVsDisplayNames.put("statusChangeTime","statusChangeTime");
		idVsDisplayNames.put("tester","tester");
		idVsDisplayNames.put("uClass","uClass");
		idVsDisplayNames.put("inUse","inUse");
		idVsDisplayNames.put("isRouter","isRouter");
		idVsDisplayNames.put("sysOID","sysOID");
		idVsDisplayNames.put("sysName","sysName");
		idVsDisplayNames.put("sysDescr","sysDescr");
		idVsDisplayNames.put("community","community");
		idVsDisplayNames.put("isInterface","isInterface");
		idVsDisplayNames.put("isNode","isNode");
		idVsDisplayNames.put("isNetwork","isNetwork");
		return idVsDisplayNames;
		}
		public Properties getIdVsDisplayNamesForAlerts()
		{	
			idVsDisplayNames.put("severity","Status");
			idVsDisplayNames.put("who","Owner");
			idVsDisplayNames.put("message","Alarm Message");
			idVsDisplayNames.put("previousSeverity","Previous Severity");
			idVsDisplayNames.put("category","Category");
			idVsDisplayNames.put("entity","Failure Object");
			idVsDisplayNames.put("modTime","Date/Time");
			idVsDisplayNames.put("groupName","Alarm Group");
			idVsDisplayNames.put("source","Source");
			idVsDisplayNames.put("createTime","CreateTime");
			return idVsDisplayNames;
		}
		public Properties getIdVsDisplayNamesForEvents()
	{
		idVsDisplayNames.put("severity","Status");
		idVsDisplayNames.put("text","Message");
		idVsDisplayNames.put("time","Date");
		idVsDisplayNames.put("category","Category");
		idVsDisplayNames.put("network","Network");
		idVsDisplayNames.put("entity","Entity");
		idVsDisplayNames.put("source","Source");
		idVsDisplayNames.put("helpURL","helpURL");
		idVsDisplayNames.put("id","id");
		idVsDisplayNames.put("domain","domain");
		idVsDisplayNames.put("node","node");
		return idVsDisplayNames;
	}
	public Properties getIdVsDisplayNamesForPerformance()
	{
		idVsDisplayNames.put("name","StatisticName");
		idVsDisplayNames.put("dnsName","DNSName");
		idVsDisplayNames.put("community","community");
		idVsDisplayNames.put("active","Active");
		idVsDisplayNames.put("snmpVersion","SnmpVersion");
		idVsDisplayNames.put("port","port");
		idVsDisplayNames.put("lastCounterValue","lastCounterValue");
		idVsDisplayNames.put("numericType","numericType");
		idVsDisplayNames.put("groupName","groupName");
		idVsDisplayNames.put("saveAbsolutes","saveAbsolutes");
		idVsDisplayNames.put("logFile","logFile");
		idVsDisplayNames.put("thresholdList","thresholdList");
		idVsDisplayNames.put("failureCount","failureCount");
		idVsDisplayNames.put("pollerName","pollerName");
		idVsDisplayNames.put("savePollCount","savePollCount");
		idVsDisplayNames.put("suffix","suffix");
		idVsDisplayNames.put("ownerName","ownerName");
		idVsDisplayNames.put("id","pollid");
		idVsDisplayNames.put("oid","objectid");
		idVsDisplayNames.put("period","Interval");
		idVsDisplayNames.put("isMultiplePolledData","Multiple");
		idVsDisplayNames.put("agent","agent");
		idVsDisplayNames.put("threshold","threshold");
		idVsDisplayNames.put("previousSeverity","previousSeverity");
		idVsDisplayNames.put("policyName","PolicyName");
		idVsDisplayNames.put("save","save");
		idVsDisplayNames.put("logDirectly","logDirectly");
		idVsDisplayNames.put("parentObj","ParentObj");
		idVsDisplayNames.put("currentSaveCount","currentSaveCount");
		idVsDisplayNames.put("protocol","protocol");
		idVsDisplayNames.put("statsDataTableName","statsDataTableName");
		idVsDisplayNames.put("failureThreshold","failureThreshold");
		idVsDisplayNames.put("saveOnThreshold","saveonThreshold");
		idVsDisplayNames.put("timeAvg","timeAvg");
		return idVsDisplayNames;
	}


	}
	
