//$Id: TestStatusIconConfigurationPopulator.java,v 1.4 2003/12/16 11:10:38 sasikumar Exp $

package com.adventnet.nms.webclient.common;

import java.util.Properties;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.textui.TestRunner;
import junit.framework.*;

import com.adventnet.nms.util.*;
import  com.adventnet.nms.webclient.common.*;

public class TestStatusIconConfigurationPopulator extends TestCase
{
    
    public TestStatusIconConfigurationPopulator(String name)
    {
        super(name);
    }
    protected void setUp() 
    {
    }
    public void testStatusIconConfigurationPopulator()
    {
        PureUtils.rootDir = "/home/sasi/webclient/Latest/AdventNet/WebNMS/";
        StatusIconConfigurationPopulator SC = new StatusIconConfigurationPopulator();
       // System.out.println("<------->"+SC.getInstance().getStatusImage("3"));
       // System.out.println("<------->"+SC.getStatusImage("3"));
       // System.out.println("<------->"+SC.getStatusVsIconConfiguration());
       
	   //hard coded values has to be read from conf file?
	    assertNotNull(SC.getStatusVsIconConfiguration());
		assertEquals("/webclient/common/images/severity_critical.gif",SC.getStatusImage("1"));        
		assertEquals("/webclient/common/images/severity_major.gif",SC.getStatusImage("2"));
		assertEquals("/webclient/common/images/severity_minor.gif",SC.getStatusImage("3"));
		assertEquals("/webclient/common/images/severity_warning.gif",SC.getStatusImage("4"));
		assertEquals("/webclient/common/images/severity_clear.gif",SC.getStatusImage("5"));
		assertEquals("/webclient/common/images/severity_info.gif",SC.getStatusImage("6"));
		assertEquals("/webclient/common/images/severity_unknown.gif",SC.getStatusImage("7"));
    }

	  public static junit.framework.Test suite()
      {
         return new junit.framework.TestSuite( TestStatusIconConfigurationPopulator.class );
      }

     public static void main(String arg[])
   {
       junit.textui.TestRunner.run(TestStatusIconConfigurationPopulator.class);
   }
}
