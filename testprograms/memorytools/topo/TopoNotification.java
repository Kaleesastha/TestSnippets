
import com.adventnet.nms.topodb.*;
//import com.adventnet.nms.util.XMLNode ;
import com.adventnet.nms.topodb.NmsTreeNode;

import com.adventnet.nms.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;

public class TopoNotification extends Thread 
{
	TopoAPI tapi = null ;
	
	TopoActionListenerImpl tactlImpl = null;
	TopoListenerImpl tlImpl = null ;
	ExTopoListenerImpl exImpl = null ;
	TopoSubscriberImpl topoSubsImpl = null ;
	static int ins = 0 ;
	
	public TopoNotification(String host) 
	{
		try
		{
			if( host == null ) host = "kprakash" ;
			ins ++;	
			tapi = (TopoAPI) Naming.lookup("rmi://"+host+":1099/TopoAPI");		
			System.out.println("TopoAPI is "+tapi + "  from instance "+ins );	
			registerObs();
			start();
		}catch(Exception exp)
		{
			exp.printStackTrace();	
		}
	}
	
	
	void registerObs()
	{
		try
		{
			tactlImpl = new TopoActionListenerImpl();
			tlImpl = new TopoListenerImpl();
			exImpl = new ExTopoListenerImpl();
			topoSubsImpl = new TopoSubscriberImpl();
			
			
		}catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
		
	public void run()
	{
		try
		{
			for( int j=0; j<1 ; j++ )
			//while( true )
			{
				try
				{
					tapi.register( tactlImpl );
					System.out.println("TopoNotification: registered as TopoActionListener --- "+this.tactlImpl +"  from instance is "+ ins);
		//			Thread.sleep( 25000 );
					
					tapi.addTopoListener( this.tlImpl );
					System.out.println("TopoNotification: registered as TopoListener --- "+this.tlImpl+"  from instance is "+ ins);
					Thread.sleep( 25000 );
					String[] nets ={"192.168.11.0","192.168.12.0","192.168.13.0"};
					for( int i=0 ; i<nets.length ; i++ )
					{	
						tapi.registerTopoSubscriber( this.topoSubsImpl, nets[i] );
						System.out.println("TopoNotification: registered as TopoSubscriber for "+nets[i] + "   "+this.topoSubsImpl  +"  from instance is "+ ins);
						Thread.sleep( 25000 );
					}
					
					tapi.addTopoListener( this.exImpl );
					System.out.println("TopoNotification: registered as TopoListener -- "+this.exImpl  +"  from instance is "+ ins);
					Thread.sleep( 25000 );
					System.out.println("TopoNotification: Registered all Observers sucessfully " +"  from instance is "+ ins);
					//*************************************//
				//	Thread.sleep( 900000 );
					//****************************************//
				/*	tapi.unregister( this.tactlImpl );
					System.out.println("TopoNotification: registered as TopoActionListener --- "+this.tactlImpl +"  from instance is "+ ins);
					Thread.sleep( 25000 );
					tapi.removeTopoListener( this.tlImpl );
					System.out.println("TopoNotification: registered as TopoListener --- "+this.tlImpl  +"  from instance is "+ ins );
					Thread.sleep( 25000 );
					//String[] nets ={"192.168.11.0","192.168.12.0","192.168.13.0"};
					for( int i=0 ; i<nets.length ; i++ )
					{	
						tapi.deregisterTopoSubscriber( this.topoSubsImpl );
						System.out.println("TopoNotification: registered as TopoSubscriber for "+nets[i] + "   "+this.topoSubsImpl  +"  from instance is "+ ins) ;
						Thread.sleep( 25000 );
					}
					
					tapi.removeTopoListener( this.exImpl );
					System.out.println("TopoNotification: registered as TopoListener -- "+this.exImpl +"  from instance is "+ ins);
					Thread.sleep( 25000 );
					System.out.println("TopoNotification: Registered all Observers sucessfully "  +"  from instance is "+ ins);*/

				}catch(Exception exp)
				{
					exp.printStackTrace();
				}	
			}
			
		}catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		try
		{	
			Thread th1  = new TopoNotification(args[0]);
//			Thread th2 = new TopoNotification(args[0]);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	class TopoActionListenerImpl extends UnicastRemoteObject implements TopoActionListener
	{
		Vector modPropKeys = null ;
		ManagedObject mo = null;
		String upType = null;
		
		public TopoActionListenerImpl() throws RemoteException
		{
			super();
		}
		
		public void update(TopoNotificationEvent evt) 
		{
			try
			{
				System.out.println("*****  START  ******");
				System.out.println("TopoActionListenerImpl: update method invoked");
			/*	if( evt == null ) return ;
				modPropKeys = evt.getModifiedPropertyKeys();
				System.out.println("TopoActionListenerImpl: modifiedPropKeys are "+modPropKeys);
				mo = evt.getOldManagedObject();
				System.out.println("Properties of old MO are "+mo.getProperties());
				mo = evt.getNewManagedObject();
				System.out.println("Properties od New MO are "+ mo.getProperties());
				upType = evt.getUpdateType();
				System.out.println("TopoActionListenerImpl: update type is "+upType);*/
				System.out.println("**** END  ****");
			
			}catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
			
	}

	class TopoListenerImpl extends UnicastRemoteObject implements TopoListener
	{
		public TopoListenerImpl() throws RemoteException
		{
			super();
		}
		
		public boolean deleteObject(XMLNode data) 
		{
			try
			{
				System.out.println("****  START  *****");
				System.out.println("TopoActionListenerImpl: deleteObject method invoked "+data.toString());
				System.out.println("**** END  ******");
			}catch(Exception exp)
			{
				exp.printStackTrace();
			}
			return true;
		}
	}
	
	class ExTopoListenerImpl extends UnicastRemoteObject implements ExtendedTopoListener
	{
		
		public ExTopoListenerImpl() throws RemoteException
		{
			super();
		}
		
		public boolean addObjects(Vector mos) 
		{
			try
			{
				System.out.println("****  START  ******");
				if( mos == null )
				{
					System.out.println("Vector od mo's is NULL ");	
					return true;
				}
				for( int i=0; i<mos.size() ; i++)
				{
					ManagedObject mo = (ManagedObject) mos.elementAt(i);
					System.out.println("Display Name of ManagedObject is "+mo.getDisplayName());
				}
				System.out.println("***** END  ****");
			
			}catch(Exception exp)
			{
				exp.printStackTrace();
			}
			return true;
		}
		
		public boolean deleteObject(XMLNode data) 
		{
			try
			{
				System.out.println("*****  START  ******");
				System.out.println("TopoActionListenerImpl: deleteObject method invoked "+data.toString());
				System.out.println("***** END  *********");
			
			}catch(Exception exp)
			{
				exp.printStackTrace();
			}
			return true;
		}
	}
	
	class TopoSubscriberImpl extends UnicastRemoteObject implements TopoSubscriber
	{
		
		public TopoSubscriberImpl() throws RemoteException
		{
			super();
		}
		
		public void update(String type, ManagedObject mo , String[] path) 
		{
			try
			{
				System.out.println("******  START  *****");
				System.out.println("TopoSubscriberImpl: update method invoked");
				if( mo == null ) 
				{
					System.out.println("TopoSubscriberImpl: --> ManagedObject NULL ");	
					//return ;
				}
				else
				{
					System.out.println("Properties of MO are "+mo.getProperties());
				}
				System.out.println("TopoSubscriberImpl: update type is  "+type );
				if ( path == null )
				{
					System.out.println("TopoSubscriberImpl: --> String path[] is NULL ");	
				}
				else 
				{
					for( int i=0; i<path.length ; i++)
						System.out.println("TopoSubscriberImpl: path is "+path[i] );
				}
				System.out.println("****** END  ********");
			
			}catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
		
		public void initialize(NmsTreeNode rootNode) throws RemoteException
		{
			System.out.println("******** START  *******");
			if( rootNode == null )
			{
				System.out.println("TopoSubscriberImpl: rootNode Object is NULL ");
				return;
			}
			System.out.println("TopoSubscriberImpl: rootNode is "+rootNode.toString());
			System.out.println("***** END  *****");
		}
	}
}
