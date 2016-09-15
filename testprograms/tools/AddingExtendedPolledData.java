import com.adventnet.nms.util.PureServerUtils;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.poll.ExtendedPolledData;
import com.adventnet.nms.poll.*;
import com.adventnet.util.*;
import java.rmi.*;
import java.util.*;
import com.adventnet.nms.util.CommonAPI;

public class AddingExtendedPolledData
{
    public static PollAPI api = null;


public static void main(String args[])
{

    if(args.length < 4)
    {
        System.out.println(" usage : ADD/GET name agent oid {ownername}");
        System.exit(0);
    }
    String name=args[1];
    String agent=args[2];
    String oid=args[3];
    
    String ownername=null;
    if(args.length > 4)
        ownername=args[4];
    
    String key=null;
    try {
        
        api = (PollAPI) Naming.lookup("//localhost/PollAPI");
        System.out.println("api: "+api);
        
    }
    catch (Exception e)
    {
        System.err.println("Remote exception: " + e.getMessage());
        e.printStackTrace();
    }
    
    ExtendedPolledData pd=new ExtendedPolledData();
    if(args[0].equalsIgnoreCase("add"))
    {
        try{
            
            pd.setName(name);
            pd.setAgent(agent);
            pd.setOid(oid);
            if(ownername!=null)
                pd.setOwnerName(ownername);
            pd.setPeriod(30);
		    pd.setCollectorName("Collector_"+name);
            pd.setAttribute("Attribute_"+name);
            pd.setPolPort(20);
            pd.setInterfaceIndex("index_"+name);
			pd.setSonetBucketNo(123);
			pd.setTimeCache(11000000);

            api.addPoll(pd);
            System.out.println(" pd is added");
        }
        
        catch(Exception e1)
        {
            System.out.println(" Exception while adding :"+e1);
			e1.printStackTrace();
        }
    }
    else
    {
        key=name+"\t"+agent+"\t"+oid;
        if(ownername!=null)
            key=key+"\t"+ownername;

        try{
            pd = (ExtendedPolledData) api.getPolledData(key);
            System.out.println(" PD:"+pd);
            System.out.println("prop: "+pd.getProperties());
        }
        catch(Exception e2)
        {
            System.out.println(" Exception :"+e2);
			e2.printStackTrace();
        }
                        
        
    }
        
}
    
}









































































































