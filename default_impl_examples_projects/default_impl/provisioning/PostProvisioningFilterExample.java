/* $Id: PostProvisioningFilterExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/**
 * @(#)PostProvisioningFilterExample.java
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
 * The PostProvisioningFilter is an interface implemented for 
 * processing the provisioning operation results after executing
 * the operation.  This allows special rules to be applied to 
 * provisioning operations after completion or failure.  The 
 * configured filters are applied to the results obtained after
 * configuration.Filtered results are applied to the network elements.
 * <p>
 * The filter implementation must have a no-argument constructor.
 * A single instance of this filter will be used for every use of
 * a named template, so it is the responsibility of the filter to
 * ensure it is thread safe.
 * <p>
 * Except in cases where network configuration did not proceed, this
 * filter is called in every case network configuration is attempted.
 * So it is a way to recover from, or report, particular configuration 
 * failures, for example.
 * <p>
 * Special cases of this kind of filter will read rules from
 * XML configuration files or the database, to make it easier to 
 * add and change rules dynamically.
 **/

public class PostProvisioningFilterExample implements PostProvisioningFilter
{
	/** The no-argument constructor **/
	public PostProvisioningFilterExample()
	{
		System.out.println("Instantiating PostProvisioningFilterExample.");
	}

	/**
     * Process the operation result and return the filtered operation result.
     * @param templateResult Contains operation result
     * @return filtered template result
     * @exception OperationFailedException if an error occurs
     */
    public TemplateResult postProvision(TemplateResult templateResult, Template template) throws OperationFailedException 
	{
        System.out.println("PostProvisioning Operation: \n"+templateResult.toString()); 
		return templateResult;
	}
}
