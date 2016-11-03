// $Id: NetworkDetail.java,v 1.5 2008/07/30 11:32:55 barathv Exp $
package com.adventnet.nms.example.newobject;

import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import com.adventnet.management.transaction.UserTransactionException;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.nms.topodb.DBServer;
import com.adventnet.nms.util.LockableObject;

public class NetworkDetail extends com.adventnet.nms.topodb.Network {

	private String networkType;
	private Hashtable<String, Integer> severityDetail;

	public NetworkDetail()
	{
		setClassname("NetworkDetail"); //No I18N
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setSeverityDetail(Hashtable<String ,Integer> severityDetail )
	{
		this.severityDetail=severityDetail;
	}
	
	public Hashtable<String, Integer> getSeverityDetail()
	{
		return severityDetail;
	}

	public void setProperties(Properties props) {
		String networkType_value = (String) props.remove("networkType");//No I18N
		if (networkType_value != null) {
			networkType = networkType_value;
		}
		Hashtable<String, Integer> severityDetail = (Hashtable)props.get("severityDetail");//No I18N
		if ( severityDetail != null)
			this.severityDetail= severityDetail;

		super.setProperties(props);
	}

	public Properties getProperties() {
		Properties props = super.getProperties();
		if (getNetworkType() != null) {
			props.put("networkType", getNetworkType());//No I18N
		}
		if ( severityDetail != null){
			props.put("severityDetail",this.severityDetail);//No I18N
		}
		//System.out.println("Inside NetworkDetails::: Returining  props: "+props);
		return props;
	}
	public Object clone()
	{
		NetworkDetail cloneNetworkDetail = ( NetworkDetail)super.clone();
		if ( networkType != null)
			cloneNetworkDetail.networkType=networkType;
		if ( severityDetail != null)
			cloneNetworkDetail.severityDetail=severityDetail;
		return cloneNetworkDetail;
	}
	// To get the severity of each node.
	protected int checkStatus() throws java.rmi.RemoteException
	{
		SeverityInfo sinfo = SeverityInfo.getInstance();
		Hashtable sevTable = new Hashtable(10);
		int maxSeverity=super.checkStatus();
		Vector ipaddress=null;
		try{
			ipaddress=DBServer.topodb.getInterfacesOfNetwork(this.getName());
		}catch(UserTransactionException ute)
		{
			System.err.println("Unable to get the interfaces for this network." +ute.getMessage());//No I18N
		} catch (NmsStorageException e) {
			System.err.println("Unable to get the interfaces for this network." +e.getMessage());//No I18N
		}
		//TODO: TopoAPI.getInterfacesOfNetwork
		String name="";//No I18N
		String status="";//No I18N
		for (int i=0; i< ipaddress.size();++i)
		{
			try
			{
				name=(String)ipaddress.elementAt(i);
				int stat_num=DBServer.topodb.checkOut(name,0,false,true).getStatus();
				
				String stat=sinfo.getName( stat_num) ;
				
				if ( sevTable.get(stat) == null)
					sevTable.put(stat,new Integer(1) );
				else
					try
					{
						int count = ((Integer)sevTable.get(stat)).intValue();
						count++;
						sevTable.put(stat,new Integer(count));
						
					}
					catch(NumberFormatException nfe)
					{
						System.err.println(" Exception in conversion(  NetworkDetail)" );//No I18N
						nfe.printStackTrace();
					}
			}
			catch ( Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			DBServer.topodb.lock(this, LockableObject.WRITE_LOCK, 2);
			setSeverityDetail(sevTable);
			DBServer.topodb.updateObject(this,false,true);
		}
		catch(Exception e)
		{
			System.out.println("Exception in updating the object properties.");//No I18N
			e.printStackTrace();
		}
		return maxSeverity;
	}

}
