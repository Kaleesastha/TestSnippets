/* $Id: TemplateFilterExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/**
 * @(#)TemplateFilterExample.java
 * Copyright (c) 2001 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 **/

package test;

import com.adventnet.nms.provisioning.server.TemplateFilter;
import com.adventnet.nms.provisioning.xml.Template;

import com.adventnet.management.config.xml.InvalidTemplateException;

/**
 * The TemplateFilter is an interface implemented for custom template
 * processing, so that special rules can be applied to specific
 * templates.  The configured filters are applied when the template
 * has been initialized and just before it is sent to the client.
 * <p>
 * The filter implementation must have a no-argument constructor.
 * A single instance of this filter will be used for every use of
 * a named template, so it is the responsibility of the filter to
 * ensure it is thread safe.
 * <p>
 * For example, implement this class to check specific conditions, 
 * or verify whether the usage of this template should be permitted.
 * <p>
 * Special cases of this kind of filter will read rules from
 * XML configuration files or the database, to make it easier to 
 * add and change rules dynamically.
 **/

public class TemplateFilterExample implements TemplateFilter
{
	/** The no-argument constructor **/
	public TemplateFilterExample()
	{
		System.out.println("Instantiating TemplateFilterExample.");
	}

	/**
	 *  Process the template and return the processed template
	 *  to be sent to the user.   
	 *  @throws InvalidTemplateException On error or if template should not be sent to the client
	 **/
	public Template filterTemplate(Template template) throws InvalidTemplateException
	{
		System.out.println("Filtering Template: \n"+template);
		return template;
	}
}
