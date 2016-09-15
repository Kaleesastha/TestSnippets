package com.adventnet.nms.poll;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
//public class ObserverForPoll extends UnicastRemoteObject implements PollObserver
public class ObserverForPoll  implements PollObserver
{
	public ObserverForPoll() //throws java.rmi.RemoteException
	{
		System.out.println("Inside ObserverForPoll");
	}
	public void dataUpdate(String pollkey,long time,long value)
	{
		System.out.println("%%%%%%%%%%%");
		System.out.println(pollkey + "  "+time + "   "+value);
		System.out.println("%%%%%%%%%%%");
	}
	
	public void dataUpdate(String pollkey,long time,String value)
	{
		System.out.println(pollkey + "  "+time + "   "+value);
	}
	
  	public void dataUpdate(Hashtable allDataTogether)
	{
		Date d = new Date();
		String mul = (String)allDataTogether.get("Multiple");
		Long tempTime = (Long)allDataTogether.get("Time");
		long time =0;
		if(tempTime != null)
			time = tempTime.longValue();
		if(time == 0) time = d.getTime();
		if(mul == null)
		{
			for(Enumeration e=allDataTogether.elements();e.hasMoreElements();)
			{	
				String instance = "";
				Object ele = e.nextElement();
				if(ele instanceof Long) continue;
				Vector v = (Vector)ele;
				PolledData pd = (PolledData)v.elementAt(0);
				Object obj = v.elementAt(1);
				System.out.println(pd.getKey() +"   "+ time +"   "+ obj);
			}
		}
		else 
		{
			Vector v = new Vector();
			Vector mpdVector=null;
			Vector valuesVector=null;
			String index="";
			for(Enumeration e = allDataTogether.keys();e.hasMoreElements();)
			{
				String key = (String)e.nextElement();
				if((key.equals("Multiple"))||(key.equals("Time"))) continue;
				v = (Vector)allDataTogether.get(key);
				if(v == null ) break;
				mpdVector = (Vector)v.elementAt(0);
				valuesVector = (Vector)v.elementAt(1);
				index = ((Integer)v.elementAt(2)).toString();
				for(int j=0;j<mpdVector.size();j++)
				{
					MultiplePolledData pd = (MultiplePolledData) mpdVector.elementAt(j);
					Object obj = valuesVector.elementAt(j);
					System.out.println(pd.getKey() +"   "+ time +"   "+ obj+ "   "+ index);
				}
			}

		}

	}
	
	public void dataUpdate(CollectedInfo cinfo)
	{
		Vector keys = cinfo.getKeys();
		for(int i=0;i<keys.size();i++)
		{
			Vector instances = cinfo.getInstances((String)keys.elementAt(i));
			for(int j=0;j<instances.size();j++)
			{
				Object obj = cinfo.getValue((String)keys.elementAt(i),(String)instances.elementAt(j));
				System.out.println(keys.elementAt(i) + "  " + instances.elementAt(j) + "  " + obj);
			}
		}
	
	}
}	
