//$Id: PerformanceProperties.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $

 package com.adventnet.nms.tools.CustomView ; 


 import java.util.*;

 public class PerformanceProperties 
 {
	Properties idVsDisplayNames=new Properties();
	Properties checkedColumns=new Properties();
	public Properties getIdVsDisplayNames()
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
	public Properties getCheckedColumns()
	{
		checkedColumns.put("name","true");
		checkedColumns.put("dnsName","false");
		checkedColumns.put("community","true");
		checkedColumns.put("active","true");
		checkedColumns.put("SnmpVersion","true");
		checkedColumns.put("port","true");
		checkedColumns.put("lastCounterValue","false");
		checkedColumns.put("numericType","true");
		checkedColumns.put("groupName","false");
		checkedColumns.put("saveAbsolutes","false");
		checkedColumns.put("logFile","false");
		checkedColumns.put("thresholdList","false");
		checkedColumns.put("failureCount","false");
		checkedColumns.put("pollerName","false");
		checkedColumns.put("savePollCount","false");
		checkedColumns.put("suffix","false");
		checkedColumns.put("ownerName","false");
		checkedColumns.put("id","false");
		checkedColumns.put("oid","false");
		checkedColumns.put("period","false");
		checkedColumns.put("isMultiplePolledData","false");
		checkedColumns.put("agent","true");
		checkedColumns.put("threshold","false");
		checkedColumns.put("previousSeverity","false");
		checkedColumns.put("policyName","false");
		checkedColumns.put("save","false");
		checkedColumns.put("logDirectly","false");
		checkedColumns.put("parentObj","false");
		checkedColumns.put("currentSaveCount","false");
		checkedColumns.put("protocol","false");
		checkedColumns.put("statsDataTableName","false");
		checkedColumns.put("failureThreshold","false");
		checkedColumns.put("saveOnThreshold","false");
		checkedColumns.put("timeAvg","false");
		return checkedColumns;
	}	
 }



