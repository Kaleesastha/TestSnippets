/* $Id: IPService.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $   */
package test;

import com.adventnet.nms.topodb.*;

 /** 
  *This is a class that extends ManagedGroupObject. 
  *Objects of this class are created to represent the three groups 
  *namely, FtpService, TelnetService and SnmpService.
  **/
public class IPService extends ManagedGroupObject 
{
    
    public IPService(){
    }

    public IPService(String name) 
    {
	setClassname("IPService");
	setName(name);
	setTester(null);
    }
    
}
