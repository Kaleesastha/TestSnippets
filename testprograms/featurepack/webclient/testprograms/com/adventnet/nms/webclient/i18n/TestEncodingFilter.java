//$Id: TestEncodingFilter.java,v 1.2 2003/12/02 18:41:58 rameshkumarp Exp $

/**
 * @(#)TestEncodingFilter.java
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

import java.io.IOException;

import javax.servlet.ServletException;

import com.meterware.httpunit.WebResponse;
import org.apache.cactus.WebRequest;



/**
 @test suite="Encoding filter testing"
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
public class TestEncodingFilter extends org.apache.cactus.FilterTestCase
{

    public TestEncodingFilter( String theName )
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
     @test test_case_id="WC-18N-FMW-001"
     description="To check whether the encoding filter set the character encoding in the request"
     */
    /*public void testCharacterEncoding()
    {
        EncodingFilter encodingFilter = new EncodingFilter();
        try {
            encodingFilter.doFilter( request , response , filterChain );
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( ServletException e ) {
            e.printStackTrace();
        }
        System.out.println( "character encoding = " + request.getCharacterEncoding() );
    }*/

    public static junit.framework.Test suite()
    {
        return new junit.framework.TestSuite( TestEncodingFilter.class );
    }

    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( TestEncodingFilter.class );
    }
}