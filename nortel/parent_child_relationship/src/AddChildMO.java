package test;

import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.management.transaction.TransactionAPI;
public class AddChildMO implements RunProcessInterface
{
	public void callMain(String args[])
	{
		String mo1 = args[0];
		TopoAPI api = null;
		ManagedObject obj = null;
		while(api == null)
		{
			api = (TopoAPI) NmsUtil.getAPI("TopoAPI");
			try{Thread.sleep(2000);} catch(Exception exp){exp.printStackTrace();}
		}
		TransactionAPI txnAPI = TransactionAPI.getInstance();
			TestMO md = new TestMO(); //Parent MO
			ChildMO cm = new ChildMO(); //Child MO
			try{obj = api.getByName(mo1);} catch(Exception e){e.printStackTrace();}
			try{
				/****** BEGIN TRANSACTION ********/
				txnAPI.begin();
				String ct = "_1"; 
				String testName = obj.getName()+ct;
				md.setProperties(obj.getProperties());
				md.setName(testName);//setName of the Parent so that it is added as a different object
				//md.setIsContainer(true);//setIsContainer to true
				md.setIsGroup(true);
				md.setSysNode("Test"+ct);
				try{
					System.err.println("################ Result of adding TestMO : "+testName+" is : "+api.addObject (md)); //Add Parent
				}
				catch(Exception e){
					System.err.println("Error in adding TestMO");
					e.printStackTrace();
				}                      
				String child = ct+"_child";
				cm.setIsGroup(true);
				cm.setProperties (obj.getProperties());
				cm.setName(testName+child);
				cm.setSysDesc("Test"+child);
				cm.setGroupNames(new String[] {md.getName()});
				//cm.setParentKey(testName); //set the PARENT as the previously added TestMO
				try{
					System.err.println("################ Result of adding ChildMO : "+child+" is : "+api.addObject (cm));
				}
				catch(Exception e){
					System.err.println("Error in adding ChildMO");
					e.printStackTrace();
				}
				ChildMO cm1 = new ChildMO();
				cm1.setIsGroup(true);
				cm1.setProperties (obj.getProperties());
				cm1.setSysDesc("Test"+child);
				cm1.setGroupNames(new String[] {md.getName()});
				cm1.setName(testName+child+"_2");
				try{
					System.err.println("################ Result of adding ChildMO : "+child+" is : "+api.addObject (cm1));
				}
				catch(Exception e){
					System.err.println("Error in adding ChildMO");
					e.printStackTrace();
				}
				/****** COMMIT TRANSACTION AFTER ALL********/
				txnAPI.commit();
				String[] grpMembers = md.getGroupMembers();
				/*if (grpMembers == null){
					System.err.println("No group members found for " + md.getName());
				}else{
					System.err.println("parent member count: " + grpMembers.length);
					for (int x = 0; x < grpMembers.length; x++){
						System.err.println("parent group member: " + grpMembers[x]);
					}
				}*/
			}
			catch(Exception exp){
				try{
					exp.printStackTrace();
					txnAPI.rollback();
				}catch(Exception ex){
					ex.printStackTrace();
					System.err.println("Exception in Rollback");}
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
