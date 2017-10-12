package test;

import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.management.transaction.TransactionAPI;
public class BulkAddMO implements RunProcessInterface{
	public void callMain(String args[]){
		/*To add 300 parents & 300 children with the MO properties of an existing MO*/
		String mo1 = args[0];
		TopoAPI api = null;
		ManagedObject obj = null;
		TransactionAPI txnAPI = null; TransactionAPI.getInstance();
		while(api == null){
			api = (TopoAPI) NmsUtil.getAPI("TopoAPI");
			try{Thread.sleep(2000);} catch(Exception exp){exp.printStackTrace();}
		}
		try{obj = api.getByName(mo1); txnAPI = TransactionAPI.getInstance();} catch(Exception e){e.printStackTrace();}
		if(obj != null){
			try{
				//txnAPI.begin(-1);
				for(int count=0; count<=3 ; count++){
					/****** BEGIN TRANSACTION ********/
					String ct = "_"+String.valueOf(count); 
					String testName = "test1"+ct;
					try{ 
						ManagedObject moToBeDeleted = api.getByName(testName);
						//api.deleteObject(testName);
					}catch(Exception exp){exp.printStackTrace();}
					TestMO md = new TestMO(); //Parent MO
					ChildMO cm = new ChildMO(); //Child MO
					SecondChildMO scm = new SecondChildMO();//Second level of child - child for child
					md.setProperties(obj.getProperties());
					md.setName(testName);//setName of the Parent so that it is added as a different object
					md.setIsContainer(true);//setIsContainer to true
					md.setSysNode("Test"+ct);
					try{
						System.err.println("################ Result of adding TestMO : "+testName+" is : "+api.addObject (md)); //Add Parent
					}
					catch(Exception e){
						System.err.println("Error in adding TestMO");
						e.printStackTrace();
					}                      
					String child = ct+"_child1";
					cm.setName(testName+child);
					cm.setProperties (obj.getProperties());
					cm.setSysDesc("Test"+child);
					cm.setIsContainer(true);//setIsContainer to true
					cm.setParentKey(testName); //set the PARENT as the previously added TestMO
					try{
						System.err.println("################ Result of adding ChildMO : "+child+" is : "+api.addObject (cm));
					}
					catch(Exception e){
						System.err.println("Error in adding ChildMO");
						e.printStackTrace();
					}
					String secondChild = ct+"_second_child1";
					scm.setName(testName+child+secondChild);
					scm.setProperties (obj.getProperties());
					scm.setSysTest("Test"+secondChild);
					scm.setParentKey(testName+child); //set the PARENT as the previously added TestMO
					try{
						System.err.println("################ Result of adding SecondChildMO : "+secondChild+" is : "+api.addObject (scm));
					}
					catch(Exception e){
						System.err.println("Error in adding SecondChildMO");
						e.printStackTrace();
					}

				}
				/****** COMMIT TRANSACTION AFTER ALL********/
				//txnAPI.commit();
			}
			catch(Exception exp){
				try{
					txnAPI.rollback();
				}catch(Exception ex){
					ex.printStackTrace();
					System.err.println("Exception in Rollback");}
			}
		}
	}
	public boolean isInitialized(){return true;}
	public void shutDown(){}
}
