//$Id: CommonTestSuite.java,v 1.1 2003/09/26 09:30:27 sasikumar Exp $

package com.adventnet.nms.webclient.common;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class CommonTestSuite extends org.apache.cactus.FilterTestCase
{

  public CommonTestSuite( String theName )
  {
  super( theName );
  }

  public static Test suite()
  {
  TestSuite commonTestSuite = new TestSuite( "Common Test Suite" );
  commonTestSuite.addTest( TestLoginAction.suite() );
  commonTestSuite.addTest( TestTreeAction.suite() );
  commonTestSuite.addTest( TestTabAction.suite() );
  commonTestSuite.addTest( TestWebFrameworkUtil.suite() );
  commonTestSuite.addTest( TestStatusIconConfigurationPopulator.suite() );
  return commonTestSuite;
  }
  public static void main( String[] args )
  {
//  TestRunner.run( suite() );
  TestRunner.run( TestLoginAction.class  );
  TestRunner.run( TestTreeAction.class );
  TestRunner.run( TestTabAction.class );
  TestRunner.run( TestWebFrameworkUtil.class );
  TestRunner.run( TestStatusIconConfigurationPopulator.class );
  }
}
