/* https://nmskbase.wiki.zoho.com/DiscoverManyNodes.hlp */
package test;

import com.adventnet.nms.topodb.*;
import com.adventnet.management.transaction.*; 
import com.adventnet.nms.util.*;
import java.util.*;
import javax.transaction.*;
import com.adventnet.nms.netwatch.*;

public class PreDiscoveryProcess  implements RunProcessInterface {
	ArrayList<String>  networks= new ArrayList<String>();
	ArrayList<String> netmasks = new ArrayList<String>();
	TopoAPI topo = null;
	boolean allNetworksAdded = false;
        public void callMain(String args[]) {
                try{
			System.err.println("Starting PreDiscoveryProcess");
			if(!shouldNetworksBeAdded()){
				return;
			}
			while(DiscoveryAPI.getInstance()== null) {
				System.err.println("DiscoveryAPI.getInstance(): "+DiscoveryAPI.getInstance()); Thread.sleep(1000);
			}
			System.err.println("Got the DiscoveryAPI.getInstance() instance");
			while(DiscoveryAPI.getInstance().seed== null) {
				System.err.println("DiscoveryAPI.getInstance().seed: "+DiscoveryAPI.getInstance().seed); Thread.sleep(1000);
			}
			while(topo == null){
				topo = (TopoAPI)NmsUtil.getAPI("TopoAPI"); Thread.sleep(1000);
			}
			System.err.println("Got the DiscoveryAPI.getInstance().seed object and TopoAPI");
			Vector nodes = DiscoveryAPI.getInstance().seed.inNodes;
			Vector masks = DiscoveryAPI.getInstance().seed.nodemasks;
			HashMap<String,String> netVsmask = new HashMap<String,String>();

			while(DBServer.getdisc() == null){
				System.err.println("DBServer.getdisc(): "+DBServer.getdisc());	Thread.sleep(1000);
			}
			System.err.println("After loop :DBServer.getdisc(): "+DBServer.getdisc());
			System.err.println(" About to start iteration");
			for(int i=0; i<nodes.size(); i++){
				String node = nodes.get(i).toString();
				String mask = masks.get(i).toString();
				String netw = WatchUtil.getNetAddr(node,mask);
				if(!networks.contains(netw)){
					networks.add(netw);
					netmasks.add(mask);
					netVsmask.put(netw,mask);
				}
			}
			boolean result =  addObjects();
			System.err.println("addObjects result : "+result);
                	System.err.println("networks: "+networks);              	
                	System.err.println("netVsmask: "+netVsmask);              	
                }   
                catch(Exception e) {e.printStackTrace();}
        }   
	public boolean isInitialized() {
			if(!shouldNetworksBeAdded()){
				return true;
			}
		while(!allNetworksAdded){ try{Thread.sleep(3000); System.err.println("waiting in PreDiscoveryProcess.isInitialized");} catch(Exception exo){}}
		return true;}
        public void shutDown() {}

	private boolean addObjects(){
		try{
			TransactionAPI.getInstance().begin(30000);
			for (int i=0;i< networks.size() ;i++){
				Network obj  = new Network(networks.get(i).toString(),netmasks.get(i).toString());
				obj.setDiscover(false);
				obj.setUserProperty("<writeToSeedFile>", "false");
				topo.addObject(obj);
			}
			System.err.println("completed all network addition!!");
			allNetworksAdded = true;
			TransactionAPI.getInstance().commit();
			return true;
		}
		catch(NotSupportedException nse) {nse.printStackTrace(); return false;}
		catch(TransactionException te) {
			te.printStackTrace();
			try{
				TransactionAPI.getInstance().rollback(te.getMessage());
			}
			catch(Exception exp){
				exp.printStackTrace();
			}
			return false;
		}
		catch(RollbackException re){ re.printStackTrace();return false; }
		catch(SystemException se) {
			se.printStackTrace();
			try{	TransactionAPI.getInstance().rollback(se.getMessage()); }
			catch(Exception exp){exp.printStackTrace();}
			return false;
		}
		catch(Exception re) {re.printStackTrace(); return false;}
		finally{return true; }
	}
	private boolean shouldNetworksBeAdded(){
		/*######## This check can be changed to suit your need #########*/
		if(!NmsUtil.isFreshStart()){
			System.err.println("WebNMS is warm starting. Hence no need for PreDiscoveryProcess");
			return false;
		}
		else{
			return true;
		}
	}
}
