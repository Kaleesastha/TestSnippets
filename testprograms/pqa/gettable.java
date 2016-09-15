import com.adventnet.snmp.beans.SnmpTarget;
import com.adventnet.snmp.snmp2.SnmpOID;
import java.io.*;
class gettable
{
	public static void main(String args[]) throws IOException
	{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
            SnmpTarget target = new SnmpTarget();
            System.out.println("Specify the HostName...........");
            String name=in.readLine();                   
            //target.setTargetHost("192.168.4.45");  // set host, or other parameters
	    target.setTargetHost(name);  // set host, or other parameters
             System.out.println("Specify the oid :...........");
             String innew=in.readLine();
		
		SnmpOID rootoid = new SnmpOID(innew);
		target.setSnmpOID(rootoid);
		
		SnmpOID curroid = target.getSnmpOID();
		/*while(true)
		{
			String result = target.snmpGetNext();

			if(result==null)
			{	
				 System.out.println("Error is "+target.getErrorString());
				 break;
			}

			curroid = target.getSnmpOID();
			
			if(target.isInSubTree(rootoid,curroid))
			{
				System.out.println("result = " + result);
			}
			else
			{
				break;
			}
			
		}*/
		
		//target.setObjectID(".1.3.6.1.2.1.2.2.1.6");  // or 1.1.0 with standard prefix
		
		/*
		String result=target.snmpGetNext();
		if(result==null)
		{
			 System.out.println("Error is "+target.getErrorString());
		}
		System.out.println("result = " + result);
		
		
		result=target.snmpGetNext();
		if(result==null)
		{
			 System.out.println("Error is "+target.getErrorString());
		}
		System.out.println("result = " + result);
		
		result=target.snmpGetNext();
		if(result==null)
		{
			 System.out.println("Error is "+target.getErrorString());
		}
		System.out.println("result = " + result);
		*/
		
		//BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
//String result=target.snmpGet();
		//if(result==null)
		//{
		//	 System.out.println("Error is "+target.getErrorString());
		//}
		
		
		String[] result=target.snmpGetList();
		if(result==null)
		{
			 System.out.println("Error is "+target.getErrorString());
		}
		for(int i = 0; i < result.length; i++)
		{
			System.out.println("val = " + result[i]);
		}
		
		System.exit(0);
	}
}



































