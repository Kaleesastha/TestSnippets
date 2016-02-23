package test;

import com.adventnet.nms.util.*;
import java.util.*;
import com.adventnet.nms.topodb.*;
import java.io.*;
import com.adventnet.management.log.SystemUtil;
public class NodeList implements RunProcessInterface
{
	public void callMain(String args[])
	{
		TopoAPI topo = null;
		/*try{
			File logOutFile = new File(PureUtils.rootDir+"logs"+File.separator+"TestOut.txt");
			File logErrFile = new File(PureUtils.rootDir+"logs"+File.separator+"TestErr.txt");
			logOutFile.createNewFile();
			logErrFile.createNewFile();
			PrintStream logout =new PrintStream(new FileOutputStream(logOutFile));
			PrintStream logerr =new PrintStream(new FileOutputStream(logErrFile));
			SystemUtil.cout = logout;
			SystemUtil.cerr = logerr;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}*/
		while (topo == null)
		{
			try{
				topo = (TopoAPI)NmsUtil.getAPI("TopoAPI");
				System.err.println("Got TopoAPI handle"); //Will log them in stderr
				Thread.sleep(2000);
			}
			catch(Exception e){}
		}
		try {
			Vector vect = topo.getCompleteList();
			Enumeration enumm = vect.elements();
			ManagedObject mobj;
			while (enumm.hasMoreElements()) {
				String str = enumm.nextElement().toString();
				mobj = topo.getByName(str);
				if (mobj instanceof Node) {
					Vector vec = ((Node)mobj).getInterfacesList(); //Will provide the list of Interfaces of the Node
					System.err.println("Interfaces List  for : "+mobj+":-->"); //Will log them in stderr
					for(Iterator iter = vec.iterator(); iter.hasNext();)
						System.err.println((String)iter.next() );
				}
			}
		}catch(Exception e) {
			System.err.println("Exception  : " + e);
		}
	}
	public boolean isInitialized()
	{
		return true;
	}
	public void shutDown()
	{

	}
}
