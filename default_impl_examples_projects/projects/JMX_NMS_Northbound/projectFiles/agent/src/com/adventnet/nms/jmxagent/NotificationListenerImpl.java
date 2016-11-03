//$Id: NotificationListenerImpl.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
/* Copyright (c)  1996 - 2004  Adventnet, Inc. All Rights Reserved.
 * PLEASE READ THE ASSOCIATED COPYRIGHTS FILE FOR MORE DETAILS.
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.
 * ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE
 * OR ITS DERIVATIVES.
 */

/**
 * @Version :  6.0.0 Fri Mar 09 14:26:10 IST 2007
 * @Author  :  AdventNet Agent Toolkit Java Edition
 */

// Any changes made to this file will be lost, if regenerated.
// User code should be added within user tags for code merging support, if regenerated.

// Package Name (Dont Delete this comment)
package com.adventnet.nms.jmxagent ;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.NotificationFilterSupport;

/**
 *	This class is an implementation of the NotificationListener interface. This
 *	will be passed as the listener object to the  addNotificationListener() of
 *	the various services that are instantiated by the agent.
 */
public class NotificationListenerImpl extends NotificationFilterSupport implements NotificationListener
{
	/**
	 *	Default Constructor
	 */
	public NotificationListenerImpl()
	{
	super.enableType("jmx"); //No I18N
	}

	/**
	 *  @param	notification - The notification.
	 *  @param  handback - An opaque object which helps the listener to
	 *  associate information regarding the MBean emitter. This object  is passed
	 *  to the MBean during the addListener call and resent, without
	 *  modification, to the listener. The MBean object should not use  or modify the object.
	 */
	public void handleNotification(Notification notification, Object handback)
	{
		System.out.println("Notification Type			: "+notification.getType());	//No I18N
		System.out.println("Notification TimeStamp		: "+notification.getType());	//No I18N
		System.out.println("Notification Source		: "+notification.getSource()); //No I18N
		System.out.println("Notification Message		: "+notification.getMessage()); //No I18N
	}
}

