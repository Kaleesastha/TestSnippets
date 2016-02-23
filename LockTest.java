/* $Id: LockTest.java,v 1.3 2003/06/24 10:26:23 priya Exp $
 *
 * File Name      : LockTest.java
 * Description    : To lock and unlock the Managed Object
 * Other Info     : Complie the Program and run it and give enter the input values.
 *
 * USAGE          : Java LockTest
 * Parameter Desc :                 
 *
 * Owner Name     : Priya
 * Change History(Author Date(dd-mm-yyy) and Description of methods added/modifed/deleted):setManagedAll method has been deleted
 */
import java.rmi.Naming;
import java.io.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.*;
import java.util.Vector;



public class LockTest
{
    public static void main(String args[]){
   
        TopoAPI topocs = null;
        TopoAPI topodms = null;
        boolean unlock=true;
        String hostName = "localhost";
	String port = "1099";
		//String moname="oishi-desktop.DMS2";
            ManagedObject mo1 = null;
        try{
		PrintStream out=new PrintStream(new FileOutputStream("1.txt"));
		int pi;
		String conf;
		if(args.length != 0)
		{
		if(args[0]!=null)
		{
			hostName = args[0];
		}
		if(args[1]!=null)
		{
			port = args[1];
		}
		}
		String URL = "//"+hostName+":"+port+"/TopoAPI";
		out.println("URL is::"+URL);
		topocs =(TopoAPI)Naming.lookup(URL);
		int i,j,k,l;

		String NodeName, PortName, BladeName;
		//for(i=1;i<=25;i++)
		for(i=1;i<=2;i++)
		{
			ManagedObject mo_Node=new ManagedObject();
			NodeName = "Node_"+i;
			mo_Node.setName(NodeName);
			mo_Node.setIsContainer(true);
			topocs.addObject(mo_Node);
			//System.err.println(NodeName);
			out.println(mo_Node.getName());
		}

		//for(i=1;i<=25;i++)
		for(i=1;i<=2;i++)
		{
			for(j=1;j<=2;j++)
			{
				ManagedObject mo_Blade=new ManagedObject();
				NodeName = "Node_"+i;
				BladeName = NodeName+"_Blade_"+j;
				mo_Blade.setName(BladeName);
				mo_Blade.setParentKey(NodeName);
				mo_Blade.setIsContainer(true);
				topocs.addObject(mo_Blade);
				//System.err.println(BladeName);
				out.println(mo_Blade.getName()+"Parent::"+mo_Blade.getParentKey());
			}
		}
		//for(i=1;i<=25;i++)
		for(i=1;i<=2;i++)
		{
			for(j=1;j<=2;j++)
			{
				//for(k=1;k<=24;k++)
				for(k=1;k<=3;k++)
				{
					ManagedObject mo_Port=new ManagedObject();
					BladeName = "Node_"+i+"_Blade_"+j;
					PortName = BladeName+"_Port_"+k;
					mo_Port.setName(PortName);
					mo_Port.setParentKey(BladeName);
					mo_Port.setIsContainer(true);
					topocs.addObject(mo_Port);
					//System.err.println(PortName);
					out.println(mo_Port.getName()+"Parent::"+mo_Port.getParentKey());
				}
			}
		}
		//for(i=1;i<=25;i++)
		for(i=1;i<=2;i++)
		{
			for(j=1;j<=2;j++)
			{
				//for(k=1;k<=24;k++)
				for(k=1;k<=3;k++)
				{
					for(l=1;l<=1;l++)
					{
						ManagedObject mo_Tranceiver=new ManagedObject();
						PortName = "Node_"+i+"_Blade_"+j+"_Port_"+k;
						String TranceiverName = PortName+"_Tranceiver";
						mo_Tranceiver.setName(TranceiverName);
						mo_Tranceiver.setParentKey(PortName);
						topocs.addObject(mo_Tranceiver);
						out.println(mo_Tranceiver.getName()+"Parent::"+mo_Tranceiver.getParentKey());
					}
				}
			}
		}

	    }
        catch(Exception e){
            System.out.println("Exception Occured "+e);
	    e.printStackTrace();
        }
	}
    }
