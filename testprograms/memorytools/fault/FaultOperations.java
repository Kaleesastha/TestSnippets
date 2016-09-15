import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.eventdb.*;
import java.rmi.Naming;
import java.util.Vector;
import java.util.Date;

public class FaultOperations
{
	AlertAPI api = null;
	EventAPI eapi = null;
	String hostName = null;
	
	FaultOperations(String hostName)
	{
		try
		{
			this.hostName = hostName;
            getAlertAPI();
			new Thread(new EventRunnable()).start();
			new Thread(new ClearRunnable()).start();
			new Thread(new DeleteRunnable()).start();
            new Thread(new UpdateRunnable()).start();
            new Thread(new PickRunnable()).start();
            new Thread(new UnpickRunnable()).start();
            new Thread(new AnnotateRunnable()).start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}	

	private void getAlertAPI()
	{
		try
		{
			api = (AlertAPI)Naming.lookup("//"+hostName+"/AlertAPI");
			eapi = (EventAPI)Naming.lookup("//"+hostName+"/EventAPI");
			System.out.println(" API is =="+api);
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}	
	}	
	
	public static void main(String a[])
	{
		if(a.length !=1)
		{
			System.out.println("HELP java MemoryThreadTest hostName");
			return;
		}
		
		new FaultOperations(a[0]);
	}

	class DeleteRunnable implements Runnable
	{
        public void run()
		{
			System.out.println(" Delete Runnable Started ===");
            String entity=null;
            try
            {
                Vector vect = api.getCompleteList();
                for(int i=0;i<vect.size();i++)
                {
                    entity = (String)vect.elementAt(i);
                    Alert alt = api.checkOut(entity,5);
                    api.deleteAlert(alt,true);
                }
            }
            catch(Exception ee)
            {
                System.out.println(ee + " while deleting =="+entity);
            }
            System.out.println(new Date(System.currentTimeMillis())+" DeleteRunnable completed ==");
            
        }
	}

	class ClearRunnable implements Runnable
	{
        public void run()
		{
			System.out.println(" Clear Runnable Started ===");
            String entity=null;
            try
            {
                Vector vect = api.getCompleteList();
                for(int i=0;i<vect.size();i++)
                {
                    entity = (String)vect.elementAt(i);
                    Alert alt = api.checkOut(entity,5);
                    api.clearAlert(alt,true);
                    api.unlock(alt);
                }
            }
            catch(Exception ee)
            {
                System.out.println(ee + " while clearing =="+entity);
            }
            System.out.println(new Date(System.currentTimeMillis())+" Clearr Runnable completed ==");
        }
        
    }

    class EventRunnable implements Runnable
	{
        public void run()
		{
			System.out.println(" Event Runnable Started ===");
            String entity=null;
            try
            {
                Vector vect = api.getCompleteList();
                for(int i=0;i<vect.size();i++)
                {
                    entity = (String)vect.elementAt(i);
                    Event evt = new Event();
                    evt.setEntity(entity);
                    evt.setSource(entity);
                    evt.setTime(System.currentTimeMillis());
                    evt.setCategory("Test");
                    evt.setText("This is test i");
                    evt.setSeverity((i%4)+1);
                    eapi.addEvent(evt);
                }
            }
            catch(Exception ee)
            {
                System.out.println(ee + " while adding Event for =="+entity);
            }
            System.out.println(new Date(System.currentTimeMillis())+" EventRunnable completed ==");
        }
		
	}

	class PickRunnable implements Runnable
	{
        public void run()
		{
			System.out.println(" Pick Runnable Started ===");
            String entity=null;
            try
            {
                Vector vect = api.getCompleteList();
                for(int i=0;i<vect.size();i++)
                {
                    entity = (String)vect.elementAt(i);
                    Alert alt = api.checkOut(entity,5);
                    api.pickUpAlert(alt,"Test"+i,true);
                    api.unlock(alt);
                }
            }
            catch(Exception ee)
            {
                System.out.println(ee + " while picking =="+entity);
            }
            System.out.println(new Date(System.currentTimeMillis())+" PickUpRunnable completed ==");
        }
        
        
	}

    class UnpickRunnable implements Runnable
	{
        public void run()
		{
			System.out.println(" Unpick Runnable Started ===");
            String entity=null;
            
            try
            {
                Vector vect = api.getCompleteList();
                for(int i=0;i<vect.size();i++)
                {
                    entity = (String)vect.elementAt(i);
                    Alert alt = api.checkOut(entity,5);
                    api.unPickAlert(alt,"Test"+i,true);
                    api.unlock(alt);
                }
            }
            catch(Exception ee)
            {
                System.out.println(ee + " while un picking =="+entity);
            }
            System.out.println(new Date(System.currentTimeMillis())+" UnpickRunnable completed ==");
        }
        
	}

    class UpdateRunnable implements Runnable
	{
        public void run()
		{
			System.out.println(" Update Runnable Started ===");
            String entity=null;
            try
            {
                Vector vect = api.getCompleteList();
                for(int i=0;i<vect.size();i++)
                {
                    entity = (String)vect.elementAt(i);
                    Alert alt = api.checkOut(entity,5);
                    int severity = alt.getSeverity()+1;
                    if(severity ==6)
                    {
                        severity=1;
                    }
                    alt.setSeverity(severity);
                    api.updateAlert(alt,true);
                    api.unlock(alt);
                }
            }
            catch(Exception ee)
            {
                System.out.println(ee + " while updating =="+entity);
            }
            System.out.println(new Date(System.currentTimeMillis())+" UpdateRunnable completed ==");
        }
        
	}

    class AnnotateRunnable implements Runnable
	{
        public void run()
		{
			System.out.println(" Annotate Runnable Started ===");
            String entity=null;
            try
            {
                Vector vect = api.getCompleteList();
                for(int i=0;i<vect.size();i++)
                {
                    entity = (String)vect.elementAt(i);
                    Alert alt = api.checkOut(entity,5);
                    api.updateNotes(alt,"Test"+i,"This is King Notes "+i,true);
                    api.unlock(alt);
                }
            }
            catch(Exception ee)
            {
                System.out.println(ee + " while annotating =="+entity);
            }
            System.out.println(new Date(System.currentTimeMillis())+" AnnotateRunnable completed ==");
        }
        
	}
}	

