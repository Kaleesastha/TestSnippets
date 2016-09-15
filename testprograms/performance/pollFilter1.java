package test;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.poll.*;
import java.util.*;
import com.adventnet.nms.util.*;


public class pollFilter1 implements PollFilter 
{
 public Vector applyPollFilter(ManagedObject obj ,Vector pdatasVect) 
 {
  Vector pvector = pdatasVect;
  try 
  {
   if ( !(obj instanceof TopoObject)) return pvector;   
   if (((TopoObject)obj).getIsSNMP()) 
   {
    for ( Enumeration e=pvector.elements();e.hasMoreElements();) 
    {
     PolledData pd = (PolledData) e.nextElement();
     if (obj instanceof SnmpInterface) 
     {
      if((pd.getPolicyName().equals("snmpnode"))&&(pd.getOid().indexOf("2.2.1.10")>-1)) 
      {
       pvector.removeElement(pd);
      }
	 }
    }
   }
  } 
  catch(Exception e )
  {
   System.err.println("Exception in examplePollFilter: "+ e);
   return pvector;
  }
  return pvector;
 }
}
