package test;
import com.adventnet.nms.topodb.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Time;
import test.TestMO1;

public class TestFilter implements FoundFilter
{
	private static int count = 0 ;

	public ManagedObject filterObject (ManagedObject obj, TopoAPI api) 
	{
		if (obj == null)
		{
			return null;
		}
		if (obj instanceof Node) 
		{
			TestMO1  md = new TestMO1();
			md.setName(obj.getName()+"_"+String.valueOf(count));
			md.setSysContact1 ("Test");
			java.util.Properties temp =obj.getProperties();
			temp.put("testprop","Slot");
			temp.put("myProp","port");
			//temp.put("PREFID",new Integer(count).toString());
			md.setPREFID(count);
			obj.setProperties(temp);
			md.setProperties (obj.getProperties());
			count++;

			// If an object is returned from this method, an existence check is done. 
			// If it is already present in the database, the object will not be added 
			// Else, the object is directly added to the database.
			try{
				api.addObject (md);
			}
			catch(Exception e){
				System.err.println("Error in adding TestMO1");
				e.printStackTrace();
			}                      

		}
		return obj;
	}
}
