/*
 *$Id: StandAloneInterface.java,v 1.1 2006/08/29 13:56:51 build Exp $
 *
 *  Source - StandAloneInterface.java
 *  Author - Thiagu.C.
 *
 * Copyright (c) 2002 - 2003 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * This software is the proprietary information of AdventNet, Inc.
 * Use is subject to license terms.
 */
package com.adventnet.nms.config;

import java.util.*;

public interface StandAloneInterface
{
	String getTask(String taskName);

	Vector getTasks();

	String getLastExecutedTime(String taskName);

	boolean deleteTask(String taskName);

	String getDeviceList(String deviceListName);

	String getDataSource(String dataSourceName);

	boolean saveTask(String taskXML);

	boolean saveDeviceList(String deviceListXML);
	
	boolean saveDataSource(String datasourceXML);

	boolean deleteDeviceListMap(String taskName);
	
	boolean deleteDeviceList(String deviceListName);
	
	boolean saveDeviceListMap(String taskName, Vector deviceListNames);

	Vector getProtocolSpecificDeviceLists(String protocol);	
	
	Vector getTaskSpecificDeviceLists(String taskName);	
	
	String[] getAssociatedDataSource(String taskName);

	boolean deleteDataSource(String dataSourceName);
}
