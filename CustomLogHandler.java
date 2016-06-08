package com.ns.ems.server.log;

import com.adventnet.management.log.Log4jHandler;
import org.apache.log4j.xml.DOMConfigurator;
import java.net.URL;

public class CustomLogHandler extends Log4jHandler {

	public void configure(URL url)
	  {
		DOMConfigurator.configureAndWatch(url.getFile(),300000);
	  }

}
