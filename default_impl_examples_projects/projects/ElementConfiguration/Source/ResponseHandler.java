/*
 *$Id: ResponseHandler.java,v 1.1 2006/08/29 13:56:51 build Exp $
 *
 *	Source - ResponseHandler.java
 *	Author - Thiagu.C.
 *
 * Copyright (c) 2002 - 2003 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * This software is the proprietary information of AdventNet, Inc.
 * Use is subject to license terms.
 */
package com.adventnet.nms.config;

import java.util.Properties;

public interface ResponseHandler {

	public void init(ConfigPanel configPanel) throws Exception;

	public	String sendRequest(int workID, Object[] parameters, ConfigResponseListener configResponseListener);

}
