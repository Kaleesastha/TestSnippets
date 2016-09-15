
import com.adventnet.nms.topodb.*;
import java.rmi.*;

public class TopoTest 
{
	static TopoAPI tapi = null;
	AddUpdateDeleteThread th1 = null;
	UpdateDeleteAddThread th2 = null;
	DeleteAddUpdateThread th3 = null;

	public TopoTest(String host)
	{
		try
		{
			if( tapi == null )
			{
				tapi = (TopoAPI)Naming.lookup("rmi://"+host+"/TopoAPI");
				System.out.println("TopoTest: TopoAPI is "+tapi);
				th1 = new AddUpdateDeleteThread();
				th2 = new UpdateDeleteAddThread();
				th3 = new DeleteAddUpdateThread();
			}
		}catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}

	class AddUpdateDeleteThread extends Thread
	{
		String net = "192.164.1.";
		int ip = 1;	
		ManagedObject obj = null ;
		boolean result = false ;
		String str = null;

		public AddUpdateDeleteThread()
		{
			start();
		}

		public void run()
		{
			while( true )
			{
				try
				{
					if( (ip % 255) == 0 )
					{
						ip = 1;
					}

					obj = new ManagedObject();
					obj.setName( net+ip);
					obj.setDisplayName( net+ip);
					str = tapi.addObject( obj,false,true );					
					System.out.println("TopoTest: addObject from th1 result is "+str + " for "+net+ip);

					obj = null;
					obj = new ManagedObject();
					obj.setName("IF-"+net+ip);
					obj.setDisplayName("IF-"+net+ip);
					str = tapi.addObject( obj,false,true );					
					System.out.println("TopoTest: addObject from th1 result is "+str + " for "+"IF-"+net+ip);
					obj = null ;	
					obj = tapi.getByName( net+ip );
					if( obj != null )
					{
						System.out.println("TopoTest: getByName from th1 successfull for "+obj.getDisplayName());
					//	result = tapi.refreshObject( obj );	
					//	System.out.println("TopoTest: refreshObject from th1 result is "+result);
						System.out.println("----- TopoTest: deleting the object from th1 -----");	
						result = tapi.deleteObject( obj,true,false );
						System.out.println("TopoTest: result for deleteObject from th1 is "+result);
					}
					else
					{
						System.out.println("TopoTest: getByName from th1 returened NULL for "+net+ip);
					}

					obj = null;
					obj = tapi.getByName( "IF-"+net+ip );
					if( obj != null )
					{
					//	System.out.println("TopoTest: getByName from th1 successfull for "+obj.getDisplayName());
					//	result = tapi.updateObject( obj , true, true );
					//	System.out.println("TopoTest: updateObject from th1 result is "+result);
						System.out.println("----- TopoTest: from th1 deleting the object -----");	
						result = tapi.deleteObject( obj,true,false );
						System.out.println("TopoTest:from th1 result for deleteObject is "+result);
					}
					else
					{
						System.out.println("TopoTest: from th1 getByName returened NULL for "+"IF-"+net+ip);
					}

					obj = new ManagedObject();
					obj.setName( net+ip);
					obj.setDisplayName( net+ip);
					str = tapi.addObject( obj,false,true );					
					System.out.println("TopoTest: addObject from th1 result is "+str + " for "+net+ip);

					obj = null;
					obj = new ManagedObject();
					obj.setName("IF-"+net+ip);
					obj.setDisplayName("IF-"+net+ip);
					str = tapi.addObject( obj,false,true );					
					System.out.println("TopoTest: addObject from th1 result is "+str + " for "+"IF-"+net+ip);
					ip++;		
				}catch(Exception exp)
				{
					System.out.println("Exception from AddUpdateDeleteThread ");
					exp.printStackTrace();
				}
			}
		}
	}

	class UpdateDeleteAddThread extends Thread
	{
		String net = "192.185.4.";
		int ip = 1;	
		ManagedObject obj = null ;
		boolean result = false ;
		String str = null;

		public UpdateDeleteAddThread()
		{
			start();
		}

		public void run()
		{
			while( true )
			{
				try
				{
					if( (ip % 255) == 0 )
					{
						ip = 1;
					}

					obj = tapi.getByName( net+ip );
					if( obj != null )
					{
						System.out.println("TopoTest:from th2 getByName successfull for "+obj.getDisplayName());
				//		result = tapi.refreshObject( obj );	
				//		System.out.println("TopoTest:from th2 refreshObject result is "+result);
						System.out.println("----- TopoTest: from th1 deleting the object -----");	
						result = tapi.deleteObject( obj,true,false );
						System.out.println("TopoTest:from th2 result for deleteObject is "+result);
					}
					else
					{
						System.out.println("TopoTest:from th2 getByName returened NULL for "+net+ip);
						obj = new ManagedObject();
						obj.setName( net+ip);
						obj.setDisplayName( net+ip);
						str = tapi.addObject( obj,false,true );					
						System.out.println("TopoTest: from th2 addObject result is "+str + " for "+net+ip);

						obj = null;
						obj = new ManagedObject();
						obj.setName("IF-"+net+ip);
						obj.setDisplayName("IF-"+net+ip);
						str = tapi.addObject( obj,false,true );					
						System.out.println("TopoTest: from th2 addObject result is "+str + " for "+"IF-"+net+ip);	

					}

					obj = null;
					obj = tapi.getByName( "IF-"+net+ip );
					if( obj != null )
					{
					//	System.out.println("TopoTest: from th2 getByName successfull for "+obj.getDisplayName());
					//	result = tapi.updateObject( obj , true, true );
					//	System.out.println("TopoTest: from th2 updateObject result is "+result);
						System.out.println("----- TopoTest: from th2 deleting the object -----");	
						result = tapi.deleteObject( obj,true,false );
						System.out.println("TopoTest: from th2 result for deleteObject is "+result);
					}
					else
					{
						System.out.println("TopoTest: from th2 getByName returened NULL for "+"IF-"+net+ip);
						obj = new ManagedObject();
						obj.setName( net+ip);
						obj.setDisplayName( net+ip);
						str = tapi.addObject( obj,false,true );					
						System.out.println("TopoTest: from th2 addObject result is "+str + " for "+net+ip);

						obj = null;
						obj = new ManagedObject();
						obj.setName("IF-"+net+ip);
						obj.setDisplayName("IF-"+net+ip);
						str = tapi.addObject( obj,false,true );					
						System.out.println("TopoTest: from th2 addObject result is "+str + " for "+"IF-"+net+ip);	

					}

					obj = new ManagedObject();
					obj.setName( net+ip);
					obj.setDisplayName( net+ip);
					str = tapi.addObject( obj,false,true );					
					System.out.println("TopoTest: from th2 addObject result is "+str + " for "+net+ip);

					obj = null;
					obj = new ManagedObject();
					obj.setName("IF-"+net+ip);
					obj.setDisplayName("IF-"+net+ip);
					str = tapi.addObject( obj,false,true );					
					System.out.println("TopoTest: from th2 addObject result is "+str + " for "+"IF-"+net+ip);	
					ip++;		
				}catch(Exception exp)
				{
					System.out.println("Exception from UpdateDeleteAddThread ");
					exp.printStackTrace();
				}
			}
		}

	}

	class DeleteAddUpdateThread extends Thread
	{
		String net = "192.155.9.";
		int ip = 1;	
		ManagedObject obj = null ;
		boolean result = false ;
		String str = null;
		ManagedObject mgClone = null;

		public DeleteAddUpdateThread()
		{
			start();
		}

		public void run()
		{
			while( true )
			{
				try
				{
					if( (ip % 255) == 0 )
					{
						ip = 1;
					}

					obj = tapi.getByName( net+ip );
					if( obj != null )
					{
						mgClone =(ManagedObject) obj.clone();

						System.out.println("----- TopoTest: from th3 deleting the object -----");	
						result = tapi.deleteObject( obj,true,false );
						System.out.println("TopoTest: from th3 result for deleteObject is "+result);

						str = tapi.addObject( mgClone,false,true );					
						System.out.println("TopoTest: from th3  addObject result is "+str + " for "+net+ip);

						System.out.println("TopoTest: from th3 getByName successfull for "+obj.getDisplayName());
					//	result = tapi.refreshObject( obj );	
					//	System.out.println("TopoTest: from th3 refreshObject result is "+result);

					}
					else
					{
						System.out.println("TopoTest: from th3 getByName returened NULL for "+net+ip);
						obj = new ManagedObject();
						obj.setName( net+ip);
						obj.setDisplayName( net+ip);
						str = tapi.addObject( obj,false,true );					
						System.out.println("TopoTest: from th2 addObject result is "+str + " for "+net+ip);

						obj = null;
						obj = new ManagedObject();
						obj.setName("IF-"+net+ip);
						obj.setDisplayName("IF-"+net+ip);
						str = tapi.addObject( obj,false,true );					
						System.out.println("TopoTest: from th2 addObject result is "+str + " for "+"IF-"+net+ip);	

					}

					obj = null;
					mgClone = null ;
					obj = tapi.getByName( "IF-"+net+ip );
					if( obj != null )
					{
						mgClone =(ManagedObject) obj.clone();

						System.out.println("----- TopoTest: from th3 deleting the object ----- "+obj.getName());	
						result = tapi.deleteObject( obj,true,false );
						System.out.println("TopoTest: from th3 result for deleteObject is "+result);

						str = tapi.addObject( mgClone,false,true );					
						System.out.println("TopoTest: from th3 addObject result is "+str + " for "+"IF-"+net+ip);

						System.out.println("TopoTest: from th3 getByName successfull for "+obj.getDisplayName());
					//	result = tapi.updateObject( obj , true, true );
					//	System.out.println("TopoTest: from th3 updateObject result is "+result);

					}
					else
					{
						System.out.println("TopoTest: from th3 getByName returened NULL for "+"IF-"+net+ip);
						obj = new ManagedObject();
						obj.setName( net+ip);
						obj.setDisplayName( net+ip);
						str = tapi.addObject( obj,false,true );					
						System.out.println("TopoTest: from th2 addObject result is "+str + " for "+net+ip);

						obj = null;
						obj = new ManagedObject();
						obj.setName("IF-"+net+ip);
						obj.setDisplayName("IF-"+net+ip);
						str = tapi.addObject( obj,false,true );					
						System.out.println("TopoTest: from th2 addObject result is "+str + " for "+"IF-"+net+ip);	

					}
					ip++;			
				}catch(Exception exp)
				{
					System.out.println("Exception from DeleteAddUpdateThread ");
					exp.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args)
	{
		TopoTest test = new TopoTest(args[0]);
	}
}
