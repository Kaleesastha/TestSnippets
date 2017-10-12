package test;

import java.util.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.management.transaction.TransactionAPI;
public class DeleteObjects implements RunProcessInterface{
	public void callMain(String args[]){
		/*To add 300 parents & 300 children with the MO properties of an existing MO*/
		TopoAPI api = null;
		TransactionAPI txnAPI = null; 
		while(api == null){
			api = (TopoAPI) NmsUtil.getAPI("TopoAPI");
			try{Thread.sleep(2000);} catch(Exception exp){exp.printStackTrace();}
		}

		while(txnAPI == null){
			txnAPI = TransactionAPI.getInstance();
			try{Thread.sleep(2000);} catch(Exception exp){exp.printStackTrace();}
		}
		try{
			txnAPI.begin(-1);
			for(int j=0;j<args.length;j++){
				ManagedObject mo = api.checkOut(args[j]);
				System.err.println("The result of deleting "+args[j]+ "is : " + api.deleteObject(mo,true,true));
			}
			txnAPI.commit();
		}catch(Exception exp){
			try{
				exp.printStackTrace();
				txnAPI.rollback();
			}catch(Exception ex){
				ex.printStackTrace();
				System.err.println("Exception in Rollback");}
		}
	}
	public boolean isInitialized(){return true;}
	public void shutDown(){}
}
