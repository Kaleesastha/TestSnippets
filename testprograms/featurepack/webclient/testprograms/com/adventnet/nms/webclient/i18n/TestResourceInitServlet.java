//$Id: TestResourceInitServlet.java,v 1.1 2003/09/24 17:48:04 rameshkumarp Exp $

/**
 * @(#)TestResourceInitServlet.java
 *
 *  Copyright (c) 2003 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET,
 * INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE
 * OR ITS DERIVATIVES.
 */

package com.adventnet.nms.webclient.i18n;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;

import org.apache.struts.Globals;
import com.adventnet.management.i18n.AdventNetResourceBundle;

/**
 @test suite="I18N framework testing"
 owner="rameshkumarp"
 version="1.0"
 date="Sep 24, 2003"

 Introduction=""
 Acronymn=""
 PriorKnowledge=""
 Resource=""
 Links=""
 Preamble=""
 */
public class TestResourceInitServlet extends org.apache.cactus.ServletTestCase
{

    public TestResourceInitServlet( String theName )
    {
        super( theName );
    }

    public void setUp()
    {
        try {
            super.setUp();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }

    /**
     @valid 
     @test test_case_id=""
     description="Checking whether Config.FMT_LOCALIZATION_CONTEXT attribute is an instance of  AdventNetMessageResource"
     */
    public void testJSTLParam()
    {
        ResourceInitializationServlet ris = new ResourceInitializationServlet();
        try {
            ris.init( config );
        } catch ( ServletException e ) {
            e.printStackTrace();
        }
        ServletContext servletContext = config.getServletContext();
        LocalizationContext localizationContext =  ( LocalizationContext ) servletContext.getAttribute(Config.FMT_LOCALIZATION_CONTEXT+".application") ;
        assertNotNull("Config.FMT_LOCALIZATION_CONTEXT attribute is null" , localizationContext);
        ResourceBundle resourceBundle =  localizationContext.getResourceBundle();
        assertEquals("resource bundle set LocalizationConect is not an intance of AdventNetMessageResource" , true, resourceBundle instanceof AdventNetResourceBundle);
    }

    /**
     @valid
     @test test_case_id="Globals.MESSAGES_KEY is an instance of NmsMessageResources"
     description=""
     */

    public void testStrutsParam()
    {
        ResourceInitializationServlet ris = new ResourceInitializationServlet();
        try {
            ris.init( config );
        } catch ( ServletException e ) {
            e.printStackTrace();
        }
        ServletContext servletContext = config.getServletContext();
        Object nmsMessageResources =  servletContext.getAttribute( Globals.MESSAGES_KEY );
        assertNotNull("Globals.MESSAGES_KEY is null" , nmsMessageResources);
        assertEquals("Globals.MESSAGES_KEY is not instance of NmsMessageResources" , true , nmsMessageResources instanceof NmsMessageResources);
    }

    /**
     @valid
     @test test_case_id=""
     description="To check whether ModulePrefix value is added to the key"
     */
    public void testStrutsParamModule()
    {
        config.setInitParameter("TopoModulePrefix" , "/topo");
        config.setInitParameter("FaultModulePrefix" , "/fault");
        ResourceInitializationServlet ris = new ResourceInitializationServlet();
        try {
            ris.init( config );
        } catch ( ServletException e ) {
            e.printStackTrace();
        }
        ServletContext servletContext = config.getServletContext();
        Object nmsMessageResources = servletContext.getAttribute( Globals.MESSAGES_KEY + "/topo" );
        assertNotNull( "Globals.MESSAGES_KEYTopoModulePrefix is null" , nmsMessageResources );
        assertEquals( "Globals.MESSAGES_KEY TopoModulePrefixis not instance of NmsMessageResources" , true , nmsMessageResources instanceof NmsMessageResources );
        nmsMessageResources = servletContext.getAttribute( Globals.MESSAGES_KEY + "/fault" );
        assertNotNull( "Globals.MESSAGES_KEYFaultModulePrefix is null" , nmsMessageResources );
        assertEquals( "Globals.MESSAGES_KEYFaultModulePrefix is not instance of NmsMessageResources" , true , nmsMessageResources instanceof NmsMessageResources );
    }

    public static junit.framework.Test suite()
    {
        return new junit.framework.TestSuite( TestResourceInitServlet.class );
    }

    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( TestResourceInitServlet.class );
    }

}