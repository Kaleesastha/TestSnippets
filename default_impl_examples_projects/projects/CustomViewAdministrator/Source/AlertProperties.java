//$Id: AlertProperties.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
 package com.adventnet.nms.tools.CustomView ; 

 import java.util.*;

 public class AlertProperties 
 {
	Properties idVsDisplayNames=new Properties();
	Properties checkedColumns=new Properties();
	public Properties getIdVsDisplayNames()
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
	public Properties getCheckedColumns()
	{
		checkedColumns.put("severity","true");
		checkedColumns.put("who","true");
		checkedColumns.put("message","true");
		checkedColumns.put("previousSeverity","false");
		checkedColumns.put("category","false");
		checkedColumns.put("entity","true");
		checkedColumns.put("modTime","false");
		checkedColumns.put("source","true");
		checkedColumns.put("groupName","false");
		checkedColumns.put("createTime","false");
		return checkedColumns;
	}	
 }



