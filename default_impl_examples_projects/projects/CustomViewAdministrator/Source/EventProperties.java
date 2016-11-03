//$Id: EventProperties.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $

 package com.adventnet.nms.tools.CustomView ; 

import java.util.*;

 public class EventProperties 
 {
	Properties idVsDisplayNames=new Properties();
	Properties checkedColumns=new Properties();
	public Properties getIdVsDisplayNames()
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
	public Properties getCheckedColumns()
	{
		checkedColumns.put("severity","true");
		checkedColumns.put("text","true");
		checkedColumns.put("time","true");
		checkedColumns.put("category","false");
		checkedColumns.put("network","false");
		checkedColumns.put("entity","false");
		checkedColumns.put("source","true");
		checkedColumns.put("helpURL","false");
		checkedColumns.put("id","false");
		checkedColumns.put("domain","false");
		checkedColumns.put("node","false");
		return checkedColumns;
	}	
	
	
 }




