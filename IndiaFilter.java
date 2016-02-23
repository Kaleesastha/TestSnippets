//package test;
import com.adventnet.nms.topodb.*;
import com.italtel.emx.inventoryapi.beans.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Time;
import test.HitachiMO1;

public class IndiaFilter implements FoundFilter
{
private static int count = 0 ;

public ManagedObject filterObject (ManagedObject obj, TopoAPI api) 
 {
boolean flag = true;
//       System.out.println("Inside the IndiaFilter Filter Object");
 	// Users can validate the object before it goes to the database
 	if (obj == null)
	{
	  return null;
 	}
//        obj.setType ("SnmpNode");

/* 	// Users can change any property of the ManagedObject by first unmanaging 
    	// the object, and setting its device type to a user-defined type.
 //	obj.setManaged (false);
 	obj.setType ("India");

 	// Users can do any lower level object specific operations
    	// If the oject is a Network, discovery can be started.
 	if (obj instanceof Network) 
	{
	  ((Network)obj).setDiscover (true);
  	}

 	// Users can have their own criteria to filter objects
 	if ((obj.getType()).startsWith ("Windows")) 
	{
		return null;
 	}*/
	if(flag)
	{
		if(obj.getName.indexOf("venkatramanan")!=-1)
				{
				flag = false;
				return null;
				}
				}

 	// Users can instantiate their own class, which extends ManagedObject
 	if (obj instanceof SnmpNode) 
	{
                System.out.println("YES,SnmpNode");
	//	if ((obj.getSysOID()).equals ("1.3.3.4.5.6")) 
	//	{
	  		HitachiMO1  md = new HitachiMO1();
                        md.setName(obj.getName()+"_"+String.valueOf(count));
                        //md.setName(obj.getName()+"_"+String.valueOf(count));
	  		md.setSysContact ("MSingh");
		//md.setPattern("Delhi");
		//
                	md.setOnlydate(new Date(System.currentTimeMillis()));
                	md.setOnlytime (new Time(System.currentTimeMillis()));
                	md.setDatentime (new Timestamp(System.currentTimeMillis()));
                //        md.setUserProperty("myProp","vpr");
                       java.util.Properties temp =obj.getProperties();
                  //      temp.put("pm","MSingh");
                  //      temp.put("capital","Delhi");
                   //     temp.put("mount","Himalaya"); 
                      
                     
                     //   obj.setProperties(temp);
	  	md.setProperties (obj.getProperties());
count++;
                         

	  		// If an object is returned from this method, an existence check is done. 
// If it is already present in the database, the object will not be added 
// Else, the object is directly added to the database.
                       try{
	  		api.addObject (md);
                        System.out.println("Himalaya"+obj.getProperties());
                        }
                      catch(Exception e){
                              }                      
	//	}

 	}

 	// Users can add their own properties to the object
 //obj.setUserProperty ("myProperty", "prop");

	// If an object is returned from this method, an existence check is done. 
// If it is already present in the database, then the properties of the
// object will be updated
// Else, the object is directly added to the database.
return obj;
//return null;
 }

}
