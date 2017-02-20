package test;
import java.util.*;
import java.rmi.*;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.authentication.UserConfigAPI;

public class RmiList {
	public static void main(String[] argv) throws Exception {
		System.out.println("main: started");
		String urlString = "//"+argv[0]+":"+argv[1];
		try{
			String[] n = Naming.list(urlString);
			for (int i=0; i<n.length; i++) System.out.println(n[i]);
		}catch (Exception re){
			System.out.println ( "Error in getting the handle: " + re);
			re.printStackTrace();
		} 
		/*UserConfigAPI uc = (UserConfigAPI) Naming.lookup("//localhost/UserConfigAPI");
		Vector v = uc.getAllGroups();
		Vector newVect = new Vector();
		System.err.println("groups : "+v);

                ListIterator iter = v.listIterator();
                while (iter.hasNext()) {
                        String grp = ((String)iter.next()).trim();
			newVect.add(grp);
                }*/
		/*Collection noDup = new LinkedHashSet(newVect);
		newVect.clear();
		newVect.addAll(noDup);*/
		/*Set<String> set = new HashSet<String>();
		set.addAll(newVect);
		newVect.clear();
		newVect.addAll(set);
		System.err.println("groups : "+newVect);*/
	}
}
