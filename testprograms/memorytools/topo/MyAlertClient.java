//package com.adventnet.testtools.util;

//import com.adventnet.nms.startnms.*;
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.util.*;
import java.io.*;
import java.util.*;

public class MyAlertClient implements SocketConnection 
{

	final  static int GET_SO_MANY_MAP_PROPERTY = 91;
	final static int SEND_UPDATE_OBJECT = 92;	
	final static int GET_FILTER_OBJECTS = 90;   
	final static int BULK_DELETE = 87; String CUSTOMID= "ALERT_CLIENT";
	final static int CLOSE = 98;
	final static int READY=100;							
	final static int SEND_REMOVE_FILTER=95;							
	public static boolean proceed=false;
	public boolean continuity=false;

	public void register()
	{
		PureClientUtils.commonSocket.registerForResponses(this,CUSTOMID);
		open();
	} 

	private void removeCV(String viewId) throws Exception
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
		DataOutputStream outp = new DataOutputStream (byteStream);
		outp.writeInt (SEND_REMOVE_FILTER);
		outp.writeUTF (viewId);
		byte[] bytes = byteStream.toByteArray();
		byteStream.reset();
		byteStream.close();
		outp.close();
		PureClientUtils.commonSocket.send(CUSTOMID, bytes);
	}

	public synchronized void receive(byte[] data)
	{
		DataInputStream inp = new DataInputStream (new ByteArrayInputStream (data));
		try
		{
			int req = inp.readInt ();

			if (req == GET_SO_MANY_MAP_PROPERTY)
			{
				String dType = inp.readUTF();   // Custom view ID
				int startIndex = inp.readInt(); // Start Index for this current set of Objects
				int vLength = inp.readInt();    // View Length
				int totalLength = inp.readInt ();   // startIndex
				String orderByColumn=inp.readUTF();
				boolean isAscending=inp.readBoolean();
				int vlnth=inp.readInt();
				byte[] b = new byte[vlnth];
				int n =inp.read(b,0,vlnth);
				Vector vec=NmsUtil.deSerializeVector(b);
				System.err.println("The vector: "+vec); 
				System.err.println("The CVID: "+dType); 
				System.err.println("The startIndex "+startIndex); 
				System.err.println("The ORDERBYCOLUMN: "+orderByColumn); 
				System.err.println("The isAscending: "+ isAscending);
				System.err.println("The totalLength "+totalLength); 
				System.exit(0);
			}

		}
		catch(Exception e)
		{
			System.err.println("Exception in receiving data from FE");
			e.printStackTrace();
		}

	}

	public void open()
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
		try
		{
			DataOutputStream outp = new DataOutputStream (byteStream);
			String username = "root";//No Internationalisation
			outp.writeInt (READY);
			outp.writeUTF (username);
			outp.flush ();

			byte[] bytes = byteStream.toByteArray();
			byteStream.reset();
			byteStream.close();
			outp.close();
			PureClientUtils.commonSocket.send(CUSTOMID, bytes);
		}
		catch (IOException ie)
		{
			System.err.println ("Could not open" + " " + CUSTOMID + ie);
			return;
		}
	}

	public void close()
	{

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos=null;
		try
		{
			dos = new DataOutputStream(baos);
			dos.writeInt(CLOSE);
			dos.flush();
			PureClientUtils.commonSocket.send(CUSTOMID,baos.toByteArray() );
		}
		catch ( Exception e)
		{
			System.err.println("Exception in sending CLOSE  to FE SERVER");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				baos.close();
				if ( dos != null )
					dos.close();
			}
			catch(Exception ioe)
			{
			}
		}
	}

	public void createCustomView(String viewId, Properties crit, boolean sort, String col,boolean asc)
	{
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream ();
			DataOutputStream outp = new DataOutputStream (byteStream);
			ByteArrayOutputStream bo = new ByteArrayOutputStream ();
			//    Properties crit = new Properties();
			//  crit.setProperty("entity",entCrit+"*");
			//  crit.setProperty("status","5");
			((Properties) crit).save (bo, "Property Set Request");//No Internationalisation
			String  s = bo.toString ();
			bo.reset();
			Properties treeProperties = new Properties();
			treeProperties.setProperty("TREE-NAME",viewId);
			treeProperties.save(bo,"Tree Properties");//No Internationalisation
			String treeString = bo.toString();
			// first the request type
			outp.writeInt (GET_FILTER_OBJECTS);
			outp.writeUTF ("Add|"+viewId);
			outp.writeUTF(""); //Node Type
			outp.writeInt(20); //View Length
			outp.writeUTF(s); //View Criteria Properties
			outp.writeUTF(treeString); //Tree Node Properties
			//outp.writeUTF("Alerts"); //Parent Node under which the CV to be added
			outp.writeUTF("Events"); //Parent Node under which the CV to be added
			outp.writeBoolean(sort); //is sorting needed
			if(sort)
			{ 
				outp.writeUTF(col);    //currently sorted column 
				outp.writeBoolean(asc); //ascending order or decending orde
			} 
			outp.flush ();
			byte[] bytes = byteStream.toByteArray();
			byteStream.reset();
			byteStream.close();
			outp.close();
			//need to make CUSTOMID generic
			PureClientUtils.commonSocket.send(CUSTOMID, bytes);
		}
		catch (IOException e)
		{
			System.err.println ("IO Error sending request to server. " + e);
		}
	}

	private static void createClient() throws Exception
	{
		Properties props = new Properties();
		props.setProperty("USERNAME","root");
		props.setProperty("PASSWORD", "public");
		props.setProperty("WEB-SERVER-PORT","9090");
		props.setProperty("HOSTNAME","kprakash");
		new ClientFrameWorkAPI(props);
	}

	public static void main(String args[]) throws Exception
	{
		//for(int j =0 ; j<100 ; j++ )
	//	while( true )
	//	{
			try {
			createClient();
			MyAlertClient myAlertClient=new MyAlertClient();
			myAlertClient.register();
			for(int i=0;i<1;i++)
			{
				String id=i+"dad"+Math.random();
				Properties prop=new Properties();
				prop.setProperty("entity","a*");
				myAlertClient.createCustomView(id,prop,false,"entity",true);
				System.out.println("Created "+id);
				myAlertClient.removeCV(id);
				System.out.println("Removed "+id);
			}
			Thread.sleep(60000);
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
	//	}
	}
}
