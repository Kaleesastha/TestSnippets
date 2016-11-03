/* $Id: PreProvisioningFilterExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/**
 * @(#)PreProvisioningFilterExample.java
 * Copyright (c) 2001 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 **/

package test;

import java.util.*;
import java.rmi.*;
import com.adventnet.nms.provisioning.*;
import com.adventnet.nms.provisioning.xml.*;
import com.adventnet.nms.provisioning.server.*;


/**
 * The PreProvisioningFilter is an interface implemented for 
 * processing the provisioning operation data prior to executing
 * the operation.  This allows special rules to be applied to 
 * provisioning operations on specific templates.  The 
 * configured filters are applied after the populated template
 * has been received from the client and just before configuration
 * is applied to the network elements.
 * <p>
 * The filter implementation must have a no-argument constructor.
 * A single instance of this filter will be used for every use of
 * a named template, so it is the responsibility of the filter to
 * ensure it is thread safe.
 * <p>
 * For example, implement this class to check specific conditions, 
 * or verify whether the operation should be permitted.  The
 * OperationFailedException should be thrown to stop the
 * operation from proceeding.
 * <p>
 * Special cases of this kind of filter will read rules from
 * XML configuration files or the database, to make it easier to 
 * add and change rules dynamically.
 **/

public class PreProvisioningFilterExample implements PreProvisioningFilter
{
	/** The no-argument constructor **/
	public PreProvisioningFilterExample()
	{
		System.out.println("Instantiating PreProvisioningFilterExample.");
	}

	/**
	 *  Process the operation (defined by the populated template)
	 *  and return the processed operation 
	 *  to be executed.   
	 *  @throws OperationFailedException On error or if operation should not continue
	 **/
	public Template preProvision(Template template) throws OperationFailedException
	{
		System.out.println("PreProvisioning Operation: \n"+template);
		return template;
	}
}
