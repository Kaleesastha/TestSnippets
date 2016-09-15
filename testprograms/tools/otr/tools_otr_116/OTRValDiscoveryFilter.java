package com.adventnet.nms.tools.otrval;

import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.SnmpNode;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.management.transaction.*;
import java.sql.Time;
import java.util.*;
import java.sql.Timestamp;
import java.sql.Date;


public class OTRValDiscoveryFilter implements com.adventnet.nms.topodb.FoundFilter
{
	//<Begin_Variable_Declarations>
	private TransactionAPI transactionAPI = null;
	//<End_Variable_Declarations>

        public ManagedObject filterObject(ManagedObject managedObject,TopoAPI topoApi) throws com.adventnet.management.transaction.UserTransactionException, com.adventnet.nms.store.NmsStorageException
        {
                //<Begin_filterObject_ManagedObject_TopoAPI> 


                java.sql.Time  timeVar = new Time(100000);
                java.sql.Date  dateVar = new Date(100000);
                java.sql.Timestamp  timeStampVar = new Timestamp(100000);
                if(transactionAPI == null)
                {
                        initTransactionAPI();
                }
                beginTransaction();
                String name = null;
                boolean checkMO = false;
                if((managedObject instanceof SnmpNode))
                {
                        //<UserCode_Begin_Criteria1_IF_START>






                        //<UserCode_End_Criteria1_IF_START>


                        com.adventnet.nms.tools.otrval.OTRValMO OTRValMO0 = null;
                        name = managedObject.getName();
                        try
                        {
                                checkMO = topoApi.isManagedObjectPresent(name);
                                if(!checkMO)
                                {
                                        OTRValMO0 = new com.adventnet.nms.tools.otrval.OTRValMO();
                                        OTRValMO0.setProperties(managedObject.getProperties());
                                        OTRValMO0.setName(name);
                                }
                                else
                                {
                                        OTRValMO0 = (com.adventnet.nms.tools.otrval.OTRValMO)topoApi.checkOut(name);
                                }
                        }
                        catch( java.rmi.RemoteException re )
                        {
                                System.err.println( "RemoteException while checking for the object in database : " + name );
                        }
                        catch(com.adventnet.nms.util.TimeoutException re )
                        {
                                System.err.println( " Timeout Exception while checking for the object in database : " +  name);
                        }				
                        OTRValMO0.setBooleanProp(true);		
                        OTRValMO0.setDateProp(dateVar);		
                        OTRValMO0.setFloatProp((float)0.5555);		
                        OTRValMO0.setIntProp(1000);		
                        OTRValMO0.setLongProp(100000);		
                        OTRValMO0.setStringProp("rameshkumarp");		
                        OTRValMO0.setTimeProp(timeVar);		
                        OTRValMO0.setTimeStampProp(timeStampVar);
                        //<UserCode_Begin_Criteria1_PROPERTIES_FINISH>









                        //<UserCode_End_Criteria1_PROPERTIES_FINISH>

                        try
                        {
                                if(!checkMO)
                                {
                                        topoApi.addObject(OTRValMO0);
                                }
                                else
                                {
                                        topoApi.updateObject( OTRValMO0, true , true );
                                }
                        }
                        catch(java.rmi.RemoteException ex)
                        {
                                System.err.println("RemoteException while adding/updating the object" + OTRValMO0.getName() );
                                rollbackTransaction(ex);
                        }
                        catch( com.adventnet.nms.util.AccessDeniedException ex )
                        {
                                System.err.println( " AccessDeniedException while updating the object : " + OTRValMO0.getName() );
                                rollbackTransaction(ex);
                        }
                        catch( com.adventnet.nms.store.NmsStorageException  ex)
                        {
                                System.err.println( " NmsStorageException while adding/updating the object : " + OTRValMO0.getName() );
                                rollbackTransaction(ex);
                        }
                        catch( com.adventnet.management.transaction.UserTransactionException  ex)
                        {
                                System.err.println( " UserTransactionException while adding/updating the object : " + OTRValMO0.getName() );
                                rollbackTransaction(ex);
                        }
                        commitTransaction();
                        return null;
                }
                //<UserCode_Begin_Criteria1_IF_END>






                //<UserCode_End_Criteria1_IF_END>

                //<UserCode_Begin_METHOD_FINISH>






                //<UserCode_End_METHOD_FINISH>
                commitTransaction();
                return managedObject;
        
                //<End_filterObject_ManagedObject_TopoAPI>
        } 
        private void initTransactionAPI()
        {
                //<Begin_initTransactionAPI> 
                try
                {
                        transactionAPI = NmsUtil.relapi.getTransactionAPI();
                }
                catch(Exception ex)
                {
                        System.err.println("Exception in getting the handle of TransactionAPI "+ ex);
                }
        
                //<End_initTransactionAPI>
        } 
        private void beginTransaction()
        {
                //<Begin_beginTransaction> 
                try
                {
                        transactionAPI.begin();
                }
                catch(javax.transaction.NotSupportedException nse)
                {
                        System.err.println("Transactions not supported ");
                }
                catch(Exception ex)
                {
                        System.err.println("Exception in beginning the transaction "+ex);
                }
        
                //<End_beginTransaction>
        } 
        private void commitTransaction()
        {
                //<Begin_commitTransaction> 
                try
                {
                        transactionAPI.commit();
                }
                catch(Exception ex)
                {
                        System.err.println("Exception in committing the transaction "+ex);
                }
        
                //<End_commitTransaction>
        } 
        private void rollbackTransaction(Exception exception)
        {
                //<Begin_rollbackTransaction_Exception> 
                try
                {
                        transactionAPI.rollback(exception.getMessage());
                }
                catch(Exception ex)
                {
                        System.err.println("Exception in rolling back the transaction "+ex);
                }
        
                //<End_rollbackTransaction_Exception>
        } 

} 




