package com.adventnet.nms.poll;
import com.adventnet.snmp.snmp2.*;
public class GetVariableBindings
{
	public static void main(String args[])
	{
		GetVariableBindings gv = new GetVariableBindings();
		SnmpOID[] snmpoid = new SnmpOID[1];
		snmpoid[0] = new SnmpOID(".1.3.6.1.2.1.2.2.1.10");
		
		int[] hole = {0};
		SnmpVarBind[] svar = (SnmpVarBind[])gv.getBindings(snmpoid,3,false);
		//System.out.println(svar.length);
		if(svar[0] != null)
		{
			for(int i=0;i<svar.length;i++)
			{
				
				String oid = ((SnmpOID)svar[i].getObjectID()).toString();
				SnmpVar var = svar[i].getVariable();
				Long value = (Long)(var.toValue());
				System.out.println("value is "+ value + " oid is " + oid);
				
			}
		}
		
	}
	private static long value = 1000;
	public static SnmpVarBind[] getBindings(SnmpOID[] snmpoid,int count,boolean hole)
	{
		SnmpVarBind[] svar = null;
		if(count == 0)
		{
			svar = new SnmpVarBind[snmpoid.length];
			for(int i=0;i<snmpoid.length;i++)
			{
				svar[i] = getVarBind(snmpoid[i],count);
			}
			
		}
		else if(count == 1)
		{
			svar = new SnmpVarBind[snmpoid.length];
			for(int i=0;i<snmpoid.length;i++)
			{
				svar[i] = getVarBind(snmpoid[i],count);
			}
			
		}
		else if(count == 2)
		{
			svar = new SnmpVarBind[snmpoid.length];
			for(int i=0;i<snmpoid.length;i++)
			{
				svar[i] = getVarBind(snmpoid[i],count);
			}
			
		}
		else if(count == 3)
		{
			svar = new SnmpVarBind[snmpoid.length];
			for(int i=0;i<snmpoid.length;i++)
			{
				svar[i] = getVarBind(snmpoid[i],count);
			}
			
		}
		else if(count == 4)
		{
			svar = new SnmpVarBind[snmpoid.length];
			for(int i=0;i<snmpoid.length;i++)
			{
				try{
					String oid = snmpoid[i].toString();
					oid = oid.substring(oid.lastIndexOf(".")+1 ,oid.length());
					int ioid = new Integer(oid).intValue() +1;
					oid = snmpoid[i].toString();
					String oid1 = oid.substring(0,oid.lastIndexOf(".") + 1);
					oid = oid1 + String.valueOf(ioid) + ".1";
					SnmpOID soid = new SnmpOID(oid);

					SnmpVar var = SnmpVar.createVariable(String.valueOf(value),SnmpAPI.COUNTER);
					svar[i] = new SnmpVarBind(soid,var);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		}
		if((hole) && (count == 3 ))
		{
			try{
					String oid = snmpoid[0].toString();
					oid = oid.substring(oid.lastIndexOf(".")+1 ,oid.length());
					int ioid = new Integer(oid).intValue() +1;
					oid = snmpoid[0].toString();
					String oid1 = oid.substring(0,oid.lastIndexOf(".") + 1);
					oid = oid1 + String.valueOf(ioid) + ".1";
					SnmpOID soid = new SnmpOID(oid);


					SnmpVar var = SnmpVar.createVariable(String.valueOf(value),SnmpAPI.COUNTER);
					svar[0] = new SnmpVarBind(soid,var);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			
		}
		else if(hole)
		{
			try {
				
				String oid = snmpoid[0].toString();
				oid = oid + "." + (count+2);
				SnmpOID soid = new SnmpOID(oid);
				SnmpVar var = SnmpVar.createVariable(String.valueOf(value),SnmpAPI.COUNTER);
				svar[0] = new SnmpVarBind(soid,var);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		value = value + 100;
		return svar;
	}
	
	
	
	public static SnmpVarBind[] createDynamicHoles(SnmpOID[] snmpoid,int count,int holeColumn)
	{
		SnmpVarBind[] svar = null;
		if(count == 0)
		{
			svar = new SnmpVarBind[snmpoid.length];
			for(int i=0;i<snmpoid.length;i++)
			{
				svar[i] = getVarBind(snmpoid[i],count);
			}
			
		}
		else if(count == 1)
		{
			svar = new SnmpVarBind[snmpoid.length];
			for(int i=0;i<snmpoid.length;i++)
			{
				svar[i] = getVarBind(snmpoid[i],count);
			}
			
		}
		else if(count == 2)
		{
			svar = new SnmpVarBind[snmpoid.length];
			for(int i=0;i<snmpoid.length;i++)
			{
				svar[i] = getVarBind(snmpoid[i],count);
			}
			
		}
		else if(count == 3)
		{
			svar = new SnmpVarBind[snmpoid.length];
			for(int i=0;i<snmpoid.length;i++)
			{
				svar[i] = getVarBind(snmpoid[i],count);
			}
			
		}
		else if(count == 4)
		{
			svar = new SnmpVarBind[snmpoid.length];
			for(int i=0;i<snmpoid.length;i++)
			{
				try{
					String oid = snmpoid[i].toString();
					oid = oid.substring(oid.lastIndexOf(".")+1 ,oid.length());
					int ioid = new Integer(oid).intValue() +1;
					oid = snmpoid[i].toString();
					String oid1 = oid.substring(0,oid.lastIndexOf(".") + 1);
					oid = oid1 + String.valueOf(ioid) + ".1";
					SnmpOID soid = new SnmpOID(oid);

					SnmpVar var = SnmpVar.createVariable(String.valueOf(value),SnmpAPI.COUNTER);
					svar[i] = new SnmpVarBind(soid,var);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		if((holeColumn == count)  && (count == 3))
		{
			try{
					String oid = snmpoid[0].toString();
					oid = oid.substring(oid.lastIndexOf(".")+1 ,oid.length());
					int ioid = new Integer(oid).intValue() +1;
					oid = snmpoid[0].toString();
					String oid1 = oid.substring(0,oid.lastIndexOf(".") + 1);
					oid = oid1 + String.valueOf(ioid) + ".1";
					SnmpOID soid = new SnmpOID(oid);


					SnmpVar var = SnmpVar.createVariable(String.valueOf(value),SnmpAPI.COUNTER);
					svar[0] = new SnmpVarBind(soid,var);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			
		}
		else if(holeColumn == count)
		{
			try {
				
				String oid = snmpoid[0].toString();
				oid = oid + "." + (count+2);
				SnmpOID soid = new SnmpOID(oid);
				SnmpVar var = SnmpVar.createVariable(String.valueOf(value),SnmpAPI.COUNTER);
				svar[0] = new SnmpVarBind(soid,var);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		value = value + 100;
		return svar;
	}
	
	private static SnmpVarBind getVarBind(SnmpOID snmpoid,int count)
	{
		SnmpVarBind svar = null;
		try{
			String oid = snmpoid.toString();
			
			oid = oid + "." + (count+1);
			
			SnmpOID soid = new SnmpOID(oid);

			SnmpVar var = SnmpVar.createVariable(String.valueOf(value),SnmpAPI.COUNTER);
			svar = new SnmpVarBind(soid,var);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return svar;
	}
}