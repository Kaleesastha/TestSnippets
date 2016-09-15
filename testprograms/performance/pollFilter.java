package test;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.poll.*;
import java.util.*;
import com.adventnet.nms.util.*;


public class pollFilter implements PollFilter 
{
 String oid="",agent="" ;
 String deloid="1.2.0";
 String win95oid = ".1.3.6.1.4.1.311.1.1.3.2";
 
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
     if (obj instanceof SnmpNode) 
     {
      if((pd.getPolicyName().equals("snmpnode"))&&(pd.getOid().indexOf("1.2.0")>-1)) 
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
