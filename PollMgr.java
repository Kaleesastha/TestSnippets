 /*
   $Id: PollMgr.java.src,v 1.3.2.9 2002/05/27 14:24:07 rajagopal Exp $
 */
/**
 * PollMgr.java
 */


package com.adventnet.nms.poll;

import java.util.*;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import java.rmi.RemoteException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import java.net.InetAddress;  
import java.rmi.server.UnicastRemoteObject;
import com.adventnet.management.log.Log;

import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureServerUtils;
import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.util.LockableObject;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.nms.store.relational.RelationalPollingObject;
import com.adventnet.nms.store.relational.RelationalThresholdObject;
import com.adventnet.nms.store.relational.RelationalPollFilters;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.nms.startnms.CvcMaintainer;
import com.adventnet.nms.store.DBVectortable;

import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import javax.transaction.HeuristicMixedException;
import com.adventnet.management.transaction.UserTransactionException;

import com.adventnet.nms.store.Notifier;
import com.adventnet.management.transaction.TransactionHandler;
import com.adventnet.nms.store.NotificationCacheMaintainer;

import com.adventnet.management.log.*;

/**
 * The API implementation of PollAPI for accessing, adding, modifying and
 * deleting 
 * PolledData objects in the polling engine database.
 * The PollAPI Provides methods for :
 * <ul>
 * <li> Adding, Modifying, Deleting PolledData objects.
 * <li> Getting the data collected during data collection.
 * <li> Adding, Modifying, Deleting Threshold Objects.
 * <li> Adding, Modifying, Deleting Polling Objects.
 * <li> Returning the PolledData objects depending on various parameters.
 * <li> Starting and Stopping data collection for all agents or for a
 *		particular agent.
 * <li> To know about the Poller status and to shut down Pollers.
 * </ul>
 * PollAPI is accessible either through RMI or directly from the same 
 * JVM. When RMI is enabled, i.e. by running RMI registry before the NMS 
 * server is started, the PollAPI can be accessed remotely via RMI.
 * It is published with RMI handle /PollAPI on the server.
 * <pre>
 * <br></br>
 * The following code can be used to get the Handle of the PollAPI.
 * <code>
 * import com.adventnet.nms.util.PureServerUtils;
 * import com.adventnet.nms.poll.PollAPI;
 * import java.rmi.Naming;
 * .
 * .
 * .
 * try {
 * 	if (!PureServerUtils.smallNet) {
 *		api = (PollAPI) Naming.lookup("//" +//No Internationalisation
 *				java.net.InetAddress.getLocalHost().getHostName() + "/PollAPI");//No Internationalisation
 *	} else api = com.adventnet.nms.poll.Collector.pollmgr;

 *	} catch (Exception e) {
 *		System.err.println(NmsUtil.GetString("Remote exception: ") + e.getMessage());
 *		e.printStackTrace();
 *	}
 * </code>
 * <br></br>
 * <b>smallNet</b> is a boolean variable which is used to specify whether the  
 * NMS server is started in the same JVM or not.
 * </pre>
 * <br></br>
 * PollAPI can be also obtained from NmsUtil's getPollAPI() method.
 * <p>
 * <b>Note</b> This class should be used only in BE/FE server JVM.
 *
 */
public class PollMgr extends UnicastRemoteObject implements PollAPI, java.io.Serializable {

boolean perf_firstTime=true;
static long counter=0;
static long periodiccounter=0;
static long startTime=0;
static long startPeriodicLogTime=0;
public static LogUser testuser = LogMgr.getLogUser("DATAPOLL");

		
	// This class  is basically responsible for reading the conf
	// files, listening for changes in topo and policy, and for
	// kicking off the polling policies.
	PollInitializer initializer;  
        // This Hash table maps the key of the pds against the PDSpecificNotifiers
	// which is interested in the data collection of that pd.
	transient Hashtable pollObservers;

        AllDataNotifier allDataNotifier = null;

        //used to store the AgentSpecificNotifiers which has registered for PD's in a agent.
	transient Hashtable agentObservers;
                
        PollUnitNotifier pollUnitNotifier=null;

	Hashtable insertHash =new Hashtable();
	// Returns whether the Polling Engine has been initialized or not. 
	// NOTE: Please change the sate of this variable to true as soon 
	// as the poll engine is initialized because DBServer will wait
	// for all the modules to get initialized.

	PDKeyHolder pdatas = null;
	transient DBVectortable snmppolls = null;
	static int POLLID=1;
	static int TIME=2;
	static int VAL=3;
	static int VALUE=5;
	static int OIDINDEX=4;   

	long id=1;

	Date date = new Date(); // the last time reports were run

	private long pdCount = 0;
	private long prevPeriod = 0;
	static transient CommonPollAPI comapi;
	RelationalPollingObject relPollObj = null;
	RelationalThresholdObject relThreshObj = null;
	RelationalPollFilters relPollFlt = null;
	private TransactionAPI transapi = null;
	transient Hashtable lockObjectTable;
	static boolean firstTime = true;
	private long updateTime = 0;
	private long maxinsert = 0; 
        LockHandler lockHandler;
	/**
	 * Constructor for this class.
	 * @exception RemoteException if error occurs during remote call 
	 */
	public PollMgr() throws RemoteException
	{      
		init();
	}

	private void init() 
	{    
		
	}  

	void initializeTransientFields()
	{
		transapi = Collector.porelapi.relapi.getTransactionAPI();
		pdatas = new PDKeyHolder("PDATAS");//No Internationalisation
                relPollObj = new RelationalPollingObject();
                relThreshObj = new RelationalThresholdObject();
                relPollFlt = new RelationalPollFilters();
                pollUnitNotifier = new PollUnitNotifier();
                allDataNotifier = new AllDataNotifier();
                lockHandler = new LockHandler(transapi);

                try
                {
                        pdCount=pdatas.size();
                }
                catch(Exception e)
                {
                   NmsLogMgr.POLLERR.fail("Error in PolledData count:"+e,e);
                }



		if(!Collector.comapi.isDB)
		{
			snmppolls = new DBVectortable("SNMPPOLLS",null);//No Internationalisation
			snmppolls.setLog(NmsLogMgr.POLLUSER, NmsLogMgr.POLLERR);

                try{                        
			snmppolls.restore();
                }
                catch(Exception e1){}       



		}
                lockObjectTable = new Hashtable();
	}

	/**
	 * Sets the SnmpRetries value with the one that is passed as argument
	 * to the Collector when NMS is started.
         * This retries parameter will be used 
	 * as the snmp retries value across all polls.
	 * @param r snmpretries value as int.
	 */
	public void setSnmpRetries(int r)
	{
		if ( r < 0 ) return;
		Collector.DATA_COLLECTION_SNMP_RETRIES=r;
	}
	
	/**
	 * Returns the retries parameter. 
         * This is used as the snmp retries value 
	 * across all polls.
	 * Used by SnmpPoll and MultipleSnmpPoll.
	 * @return snmpretries as int.
	 */
	public int getSnmpRetries()
	{
		return Collector.DATA_COLLECTION_SNMP_RETRIES;
	}
	
	/**
	 * Returns whether the Polling Engine has been initialized or not. 
	 * The Polling Engine can be used  only after it has been initialized.
	 * @return boolean depending on the state of the Polling Engine.
	 */
	public boolean isInitialized() 
	{
		return Collector.isInitialized;
	}

	// called from the collector.
	synchronized void startPoller() throws RemoteException 
	{
		PolledData.setPollApi((PollAPI)this);
		PolledData.setIsPoller(false);
		addAll();
		initializer = new PollInitializer();
		RunPoll.providerHash=initializer.getProviderHash();
                initializer.passThroAllPOs=Collector.PASS_THRO_ALL_POLLING_OBJECTS;
                startScheduler();
	}

	private synchronized void addAll() 
	{
		PollUtil.getInterfaces();
		PollUtil.enableSecurity=Collector.AUTHORIZATION;
		StoreData.decoder=PollUtil.getDataDecoder();
		PollingObject.comapi = comapi;
		StoreData.comapi = (CommonMgrInterface)comapi;
		//set the static variables of RunPoll
		RunPoll.useJDBC = comapi.isDB;
		RunPoll.comifc = (CommonMgrInterface)comapi;
		//RunPoll.initializeScheduler();
		DataCollectionConstants.MAX_OIDS_IN_ONE_POLL = Collector.MAX_OIDS_IN_ONE_POLL;
		DataCollectionConstants.DEBUGGING_MODE = Collector.DEBUGGING_MODE;
		DataCollectionConstants.snmpRetries =Collector.DATA_COLLECTION_SNMP_RETRIES;
		DataCollectionConstants.SNMP_TIMEOUT= Collector.DATA_COLLECTION_SNMP_TIMEOUT;
		DataCollectionConstants.SEND_MULTIPLE_REQUEST=Collector.SEND_MULTIPLE_REQUEST;
		DataCollectionConstants.BULK_INSERT_INTERVAL=Collector.BULK_INSERT_INTERVAL;
		DataCollectionConstants.MAX_ALLOWABLE_INSERT=Collector.MAX_ALLOWABLE_INSERT;     
		ThresholdUtil.SNMP_TIMEOUT= Collector.DATA_COLLECTION_SNMP_TIMEOUT;
		ThresholdUtil.pfIfc = PollUtil.getPollToFaultIfc();

		NameScheduler.setTheMode(comapi.isDB);

		if(!comapi.isDB)
		{
			int delay = 0;
			int noOfOs = 0;

                try{

			int totalNum = snmppolls.size();

			for(Enumeration e=snmppolls.keys();e.hasMoreElements();)
			{
				String key = (String)e.nextElement();
				try
				{
					delay = (300000/totalNum)*noOfOs;
					RunPoll.newSchedule(key,System.currentTimeMillis() + 300000+delay);
					noOfOs++;
				}
				catch (NullPointerException ne)
				{
					NmsLogMgr.POLLERR.fail(NmsUtil.GetString("PollMgr Key : ") + key + " " + ne, ne);//No Internationalisation
				}
			}
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Started the datacollection for ") + noOfOs , Log.VERBOSE);

                }       
                catch(Exception e2){}

	}

		//RunPoll.startScheduler();
	}

    private synchronized void startScheduler()
    {
        RunPoll.initializeScheduler();
        RunPoll.startScheduler();
        if(pdCount > 0)
        {
            prevPeriod = comapi.getMinPeriod();
            updateDataCollectionQueryInterval(prevPeriod,true);
        }
    }
	// a method to add an oid without affecting other polls
	// by default it will kickoff
	private synchronized void addOne(PolledData pd,String con) 
	{
		addOne(pd,true,con);
	}
	
	private synchronized void addOne(PolledData pd,boolean kickoff,String con)
	{
		// This methid seems to be doing costly operations.
		// TODO: provide a method in DBVectortable to add a record 
		// given a keystring and a valuestring as arguments.

		if (pd == null) return;

		intimatePoller(pd,con);
		
		// There is no concept of snmp polls in database mode so we return.
		if(comapi.isDB)	 return;

		if (!pd.getActive()) return;

			String key= new String(pd.getAgent() + "::" + pd.getPeriod() + "::" + pd.getCommunity());//No Internationalisation


                try{

		Vector v = (Vector) snmppolls.get(key);
		if (v == null)
		{
			v = new Vector();
			v.addElement(pd.getKey());
			snmppolls.put(key,v);

			// In  database mode the time when a polled data is to be 
			// scheduled is in the PolledData itself.  SO no concept
			// of scheduing oneself. In Serailized mode we schedule
			// in the normal way , by calling ScheduleTask at the
			// end of the run method	
			RunPoll.newSchedule(key,System.currentTimeMillis() +pd.getPeriod()*1000);
			
		}
		else
		{
			v.addElement(pd.getKey());
			snmppolls.addPut(key,v);
		}

        }
        catch(Exception e2){}

	}

	private synchronized void deleteOne(PolledData pd)
	{
		// There is no concept of snmp polls in db mode so we return.
		if(comapi.isDB)	 return;

		String key = new String(pd.getAgent() + "::" + pd.getPeriod() + "::" + pd.getCommunity());//No Internationalisation
		String value = new String(pd.getName() +"\t"+ pd.getAgent()
+"\t"+ pd.getOid()); //No Internationalisation

                try{
                  snmppolls.remove(key,value);
                }
                catch(Exception e2){}



	}

	/** 
	 * Adds a PolledData object to the Polling Engine. 
         * This method will start 
	 * data collection immediately if the PolledData is active
         * (Active property of PolledData can be set to true using
         * {@link com.adventnet.nms.poll.PolledData#setActive(boolean) setActive(boolean)})
         * else  data collection is not started for this PolledData.
	 * @param  pd  The PolledData object to be added.

         * @exception NmsPollException if ,<ul>
        * <li> PolledData with the given key already exists.
        * <li> PolledData key is not formed properly.
        * <li> if dynamic table is not created properly at run time (this is
applicable only if dynamic table feature is used).
        * if there is any exception while adding PolledData in non-transaction mode.
        * </ul>
        * @exception UserTransactionException if there is any exception while
        * doing transaction operations.
        * @exception RemoteException if an exception occurs during remote call


	 */    


    public void addPoll(PolledData pd) throws NmsPollException, UserTransactionException , RemoteException



	{
		addPoll(pd,false,true);
	}
	
	/**
	 * Adds a PolledData object to the Polling Engine. 
         * This method was useful in 
	 * earlier versions where the second argument specifies whether to start
	 * data collection immediately or not, but now there is no need for this
	 * method because starting the data collection immediately or otherwise 
	 * depends on the active or inactive state of the PolledData. 
	 * @deprecated as of WebNMS2.1  
	 * use {@link #addPoll(PolledData) addPoll(PolledData)} instead.
	 * @param pd     The PolledData to be added to the Polling engine.
	 * @param start  Boolean specifying data collection is to be 
	 * started immediately or not.

         * @exception NmsPollException if ,<ul>
        * <li> PolledData with the given key already exists.
        * <li> PolledData key is not formed properly.
        * <li> if dynamic table is not created properly at run time (this is applicable only if dynamic table feature is used).
        * if there is any exception while adding PolledData in non-transaction mode
        * </ul>
        * @exception UserTransactionException if there is any exception while doing transaction operations.
	 * @exception RemoteException if an exception occurs during remote call

	 */

    public void addPoll(PolledData pd,boolean start) throws NmsPollException, UserTransactionException , RemoteException



	{
            String agent = pd.getAgent();
            String dns = pd.getDnsName();
            ManagedObject mo=null;
            try
            {
                mo= PollUtil.getPollToTopoIfc().getManagedObject(agent);
            }

            catch(UserTransactionException e1)
                {
                    throw e1;
                }
            catch(NmsPollException e2)
                {
                    throw e2;
                }

            catch(Exception e)
            {
                NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception while getting the ManagedObject by agent Name")+e.getMessage(),e);

                throw new NmsPollException("Exception while getting ManagedObject by agent name "+e.getMessage(),e);

            }
            if(mo!=null)
            {
                Properties p = mo.getProperties();
                String tempString = com.adventnet.nms.util.WatchUtil.getDNSName(((String)p.get("ipAddress")).trim());//No Internationalisation
                pd.setDnsName(tempString);
            }
            else
            {
                if((dns==null) || (dns.equals("")))
                    pd.setDnsName(agent); 
            }	
            addPoll(pd, start, false);
	}

	// This new method with package level scope has been added for the follwoing
	// reasons. When an object is disvovered the topo listener of poll
	// gets the Polled Data for that object and adds it to PollMgr . 
	// The polled data are later combined based on agent , key and Poling
	// interval by RunPoll. For the combining to occur properly it is 
	// essential that all PD's with same Polling Interval  for a given
	// Managed Object needs to go into the DB with the same Time. 
	// If it does not go it is not a disaster. Just that things will
	// not be combined efficiently 
	
	/** 
       * Adds a PolledData  to the Polling Engine. This method was useful in 
       * in earlier versions where the second argument specifies whether to start
	 * polling immediately or not, but now there is no need for this because
         * starting the polling immediately or otherwise depends on the active or
	 * inactive state of the PolledData. Third argument specifies whether to 
	 * schedule the PolledData based on the time field in it or not.  Please 
	 * avoid using this method, use addPoll(PolledData) instead.
	 * @param     pd       The PolledData to be added to the Polling engine.
	 * @param     start     Whether polling is to start immediately or not.
	 * @param     useTimeInPD   Whether polledData is to be scheduled based on the
	 *							time field in it or not.
	 */ 

    synchronized void addPoll(PolledData pd,boolean start, boolean useTimeInPD) throws NmsPollException, UserTransactionException, RemoteException



	{
		
            //Wait if back up is going on.
            PureServerUtils.backUpInProcess();     
            if (pd == null) return;
            String dnsName = pd.getDnsName();	
            
            // Beware before removing or moving the above line
            // setAgent also sets the dnsname	
            pd.setAgent(pd.getAgent().toLowerCase());
            pd.setDnsName(dnsName.toLowerCase());
            
            String key = pd.getKey();
            StringTokenizer st = new StringTokenizer(key,"\t");//No Internationalisation
            int count = st.countTokens();
            if((count != 3) && (count !=4))
            {

                throw new NmsPollException("key is not formed correctly", null);



            }


     try{           
        if(pdatas.contains(key))
        {
                NmsLogMgr.POLLUSER.log(NmsUtil.GetString("poll already exists for ") + key , Log.VERBOSE);                
                throw new NmsPollException("poll already exists for "+key,null);
        }
        }
        catch(NmsStorageException e3)
        {
                throw new NmsPollException(e3.getMessage(),e3);
        } 
                







                      
            if (pd.getId() == 0)
            {
                pd.setId(id);
            }
            // Increment the id
            id++;
            pdCount++;
            if (useTimeInPD == false)
            {
                pd.setTimeVal(System.currentTimeMillis() + (Collector.DATA_COLLECTION_STARTUP_DELAY * 1000));
            }
            saveState();

            try 
            {
                if (pd.getIsMultiplePolledData()) 
                {
                    MultiplePolledData mpd = null;
                    try 
                    {
                        mpd =(MultiplePolledData) pd;
                    }
                    catch (ClassCastException cce)
                    {
                        mpd = new MultiplePolledData();
                        mpd.setId(pd.getId());
                        mpd.setOwnerName(pd.getOwnerName());
                        mpd.setProperties(pd.getProperties());
                    }
                    pdatas.addElement(mpd.getKey());

                    try
                    {
                        begin();
                        comapi.addObject(mpd);
                        commit();
                    }
                    catch(NmsStorageException nse)
                    {
                        
                        NmsLogMgr.POLLERR.fail(NmsUtil.GetString("addpoll exception")+nse.getMessage(),nse);
                        rollback(nse.getMessage());
                        throw new NmsPollException("addpoll exception "+nse.getMessage(),nse);
                    }














                    RunPoll.notifySchedulerAboutNewPoll(mpd.getTimeVal());
                    notifyPD(PollConstants.ADD_NEW_OBJECT,mpd);
                    
                }
                else 
                {
                    pdatas.addElement(pd.getKey());

                    try
                    {
                        begin();
                        comapi.addObject(pd);
                        commit();
                    }
                    catch(NmsStorageException nse)
                    {
                        
                        NmsLogMgr.POLLERR.fail(NmsUtil.GetString("addpoll exception")+nse.getMessage(),nse);
                        rollback(nse.getMessage());
                        throw new NmsPollException("addpoll exception "+nse.getMessage(),nse);
                    }













                    RunPoll.notifySchedulerAboutNewPoll(pd.getTimeVal());
                    notifyPD(PollConstants.ADD_NEW_OBJECT,pd);
                    
                }
                
                addOne(pd,"addition");//No Internationalisation       
                updateDataCollectionQueryInterval(pd.getPeriod(),false);
                
            }

           catch(UserTransactionException e)
           {
                throw e;
           }

            catch(Exception ex){
                NmsLogMgr.POLLERR.fail(NmsUtil.GetString("addpoll exception")+ex.getMessage(), ex);

                throw new NmsPollException("add poll exception :"+ex.getMessage(),ex);



            }
            // call the customViewController to update any clients
            // who are currently connected on
            CvcMaintainer.update("poll", "Added", key,pd.getProperties());
            try{
                if(!pd.getStatsDataTableName().equals("STATSDATA%") && !pd.getStatsDataTableName().equals("STRINGDATA%"))//No Internationalisation
                    comapi.createDynamicTable(pd.getStatsDataTableName());
            }

            catch(UserTransactionException e2)
                {
                    throw e2;
                }

            catch(Exception e1)
            {
                NmsLogMgr.POLLERR.fail(e1.getMessage(), e1);

                throw new NmsPollException(e1.getMessage(),e1);

            }
           

	}

    
    /**
     * Adds a PolledData to the Polling Engine. If <code>passthrofilters</code> is true ,
     * the added PolledData is passed through the available poll filters ,
     * else it is added directly, as in
     * {@link #addPoll(PolledData) addPoll(PolledData)}
     * method.This method gets the parent ManagedObject name from the
     * PolledData itself.(Parent ManagedObject name should be set
     * using {@link com.adventnet.nms.poll.PolledData#setParentObj(String) setparentObj(String)}.
     * Using the ManagedObject name, the corresponding ManagedObject is
     * retrieved using {@link PollToTopoIfc#getManagedObject(String) getManagedObject(String)}.
     * If it is obtained,
     * the ManagedObject and the PolledData are passed through the poll filters,
     * else PolledData is added directly without passing it through
     * poll filters.
     * @param pd a <code>PolledData</code> to be added to the system
     * @param passthrofilters a <code>boolean</code> indicating whether to pass
     * through poll filters or not.

     * @exception NmsPollException if ,<ul>
     * <li> there is any exception while passing PolledData through existing PollFilters  
        * <li> there is any problem while retrieving the corresponding ManagedObject 
     * <li> PolledData with the given key already exists.
     * <li> PolledData key is not formed properly.
     * <li> if dynamic table is not created properly at run time (this is applicable only if dynamic table feature is used).
     * if there is any exception while adding PolledData in non-transaction mode
     * </ul>
     * @exception UserTransactionException if there is any exception while
doing transaction operations.

     * @exception RemoteException if an error occurs during remote call.
     */

    public void addPolledData(PolledData pd,boolean passthrofilters) throws RemoteException,UserTransactionException,NmsPollException




    
    {
        ManagedObject mo=null;
        
        try
        {
                mo = getManagedObject(pd);
        }

        catch(UserTransactionException e2)
            {
                throw e2;
            }
        catch(NmsPollException e3)
            {
                throw e3;
            }

        catch(Exception e1)
        {
            NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Error in getting ManagedObject for the PolledData "+e1.getMessage()), e1);

                throw new NmsPollException(e1.getMessage(),e1);

        }
        
        if((passthrofilters) && (mo != null)) 
        {
            Vector pdatavector = new Vector(1);
            pdatavector.addElement(pd);
	    try {
         	   Vector pdVect = initializer.passThroughThePollFilters(pdatavector,mo);
                   initializer.addPolledData(pdVect);
	    }

            catch(NmsPollException e1)
            {
		NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Error in adding PolledData ") + e1.getMessage(),e1);                
                throw e1;
            }
            catch(UserTransactionException e2)
            {
		NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Error in adding PolledData ") + e2.getMessage(),e2);                
                throw e2;
            }
            catch(Exception e3)
            {
		NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Error in adding PolledData ") + e3.getMessage(),e3);                
                throw new NmsPollException(e3.getMessage(),e3);
            }







        }
        
        else
        {
            addPoll(pd,false,false);
        }
    }

    public void addBulkPolledData(Vector pdvect) throws NmsPollException
    {
        for(Enumeration en = pdvect.elements(); en.hasMoreElements();)
        {
            PolledData pd = (PolledData)en.nextElement();
	    
            String key = pd.getKey();
            StringTokenizer st = new StringTokenizer(key,"\t");//No Internationalisation
            int count = st.countTokens();
            if((count != 3) && (count !=4))
            {
                return;
            }


        try{
            if (pdatas.contains(key))
            {
                NmsLogMgr.POLLUSER.log(NmsUtil.GetString("poll already exists for ") + key , Log.VERBOSE);
                return;
            }
        }
        catch(Exception e1)
        {
                throw new NmsPollException(e1.getMessage(),e1);
        }









            if(pd.getId() == 0)
            {
                pd.setId(id);
            }
            id++;
            pd.setTimeVal(System.currentTimeMillis() + (Collector.DATA_COLLECTION_STARTUP_DELAY * 1000));
        }
        pdCount = pdCount + pdvect.size();
	
        try
        {
            String classname = "PolledData";
            comapi.comtorel.addObjects(classname,pdvect);
        }
        catch(Exception e)
        {
            throw new NmsPollException("Problem in adding bulk pd's" ,e);
        }
    }

    private ManagedObject getManagedObject(PolledData pd) throws Exception
    {
        //return ManagedObject of this pd.
        ManagedObject mo = null;
        String parentobj = pd.getParentObj();
              
        if((parentobj!=null) &&(!(parentobj.equals(""))))//No Internationalisation
        {
            try{
                mo= PollUtil.getPollToTopoIfc().getManagedObject(parentobj);
            }
            catch(Exception e)
            {
                NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Error while obtaining ManagedObject of agent ")+pd.getAgent() +e.getMessage(),e);
                throw e;
            }
            return mo;
        }
   
        return mo;
    }    

    
    //deletes the PolledData created for this ManagedObject name passed.
    // this is called internally by the PollingEngine when the ManagedObject
    // is deleted.
    void deletePollForMO(String name)
    {
        try
        {
            Vector deleteVect = comapi.deletePDForMO(name);  
            if(deleteVect == null) 
            {
                return;
            }
            Vector keysVect = new Vector();
            Vector updateVect = new Vector();
            for(Enumeration en=deleteVect.elements();en.hasMoreElements();)
            {
                PolledData pd = (PolledData)en.nextElement();
                updateVect.addElement(pd.getProperties());
                String key = (String)pd.getKey();
                keysVect.addElement(key);
                pdatas.removeElement(key);
                if (checkForPoller(pd) != null)
                {
                    intimatePoller(pd, "deletion");
                }

            }
            pdCount = pdCount - deleteVect.size();
            CvcMaintainer.update("poll","Deleted",keysVect,updateVect);//No Internationalisation
            pollUnitNotifier.notify(deleteVect);

        }
        
        catch(Exception ex)
        {
            NmsLogMgr.POLLERR.fail(NmsUtil.GetString("deletepoll exception")+ex.getMessage(), ex);
        } 
    }


	/** 
	 * Stops data collection for all the PolledData objects whose agent name
	 * is the name passed as argument. It also removes the PolledData objects
	 * from the Polling Engine database. 
	 * @param name The agent name of the PolledData object for which 
	 * data collection is to be stopped and the PolledData objects are to
	 * be deleted.

         * @exception NmsPollException if there is any exception while deleting
PolledData objects for the agent
         * @exception UserTransactionException if there is any exception
         *  while doing transaction operations.

	 */

    public void deletePoll(String agent) throws NmsPollException,UserTransactionException



    {
        try
        {
            Vector deleteVect = comapi.deletePoll(agent);  
            if(deleteVect == null) 
            {
                return;
            }
            Vector keysVect = new Vector();
            Vector updateVect = new Vector();
            for(Enumeration en=deleteVect.elements();en.hasMoreElements();)
            {
                PolledData pd = (PolledData)en.nextElement();
                String key = (String)pd.getKey();
                updateVect.addElement(pd.getProperties());
                keysVect.addElement(key);
                pdatas.removeElement(key);
                if (checkForPoller(pd) != null)
                {
                         intimatePoller(pd, "deletion");
                }
            }
            pdCount = pdCount - deleteVect.size();
            CvcMaintainer.update("poll","Deleted",keysVect,updateVect);//No Internationalisation
            pollUnitNotifier.notify(deleteVect);
        }

        catch(UserTransactionException e1)
        {
                throw e1;
        }                

        catch(Exception ex)
        {
            NmsLogMgr.POLLERR.fail(NmsUtil.GetString("deletepoll exception" )+ex.getMessage(), ex);

            throw new NmsPollException (ex.getMessage(),ex);

        } 
    }


	/**
	 * Changes the status of the ManagedObject given with the given status.
	 * @param obj ManagedObject whose status has to be changed.
         * @param moProp properties of the managedObject.
	 * @param status boolean status.
	 */

	public void changeTheStatusForPollObject(ManagedObject obj,Properties moProp, boolean status)
	{

            //TODO  the code here is sort of repeated in  deletePoll
            // Let us get the obj name and then 

            String agentname ="";//No Internationalisation
            String keyagent="";//No Internationalisation
            String classType = (String)moProp.getProperty("isNode","");//No Internationalisation
            if(classType.equals("true"))//No Internationalisation
            {
                // get the name and search for guys with that name
                // interesting thing is for DHCP nodes agent is the mac address
                // and that is the name of the object too... 

                // for NON dhcp guys the name of the node is  DNS name itlself..
                // and that is what polled data contains.
                agentname = obj.getName();
                agentname = agentname.toLowerCase();
            }
            classType = (String)moProp.getProperty("isInterface","");//No Internationalisation
            String ifType = (String)moProp.get("physMedia");//No Internationalisation
            String isDHCP = (String)moProp.get("isDHCP");//No Internationalisation
            String ipAddress = (String)moProp.get("ipAddress");//No Internationalisation
            if ( classType.equals("true"))//No Internationalisation
            {
                // the name would be the If-Mac if DHCP and
                // name would be IF-(IP or dns name) based
                if (isDHCP.equals("true"))//No Internationalisation
                {
                    agentname = obj.getName();
                    agentname = agentname.toLowerCase();
				// let us eat up the IF-
                    agentname = agentname.substring(3);
                }
                else
                {
                    agentname = 
                        com.adventnet.nms.util.WatchUtil.getDNSName(ipAddress).toString().trim();
                }
            }

            // we will use this only if there has been a change in the 
            // IP address allocation bcos of DNS stuff. By default let us
            // set it to agentname and if the object we are dealing with is
            // DHCP  then we get the Ip address etc..
            if(agentname.equals(""))//No Internationalisation
                agentname=obj.getName();
            String dnsname = agentname;
            if(ipAddress != null)
            {
                if (isDHCP.equals("true"))//No Internationalisation
                {
				// we are dealing with a DHCP object whose Ip address
				// would have been changed etc...

				// So we get the object's ip addresss , then the dns name
				// from it and set it in the polled Data.
                    dnsname = ipAddress;

                    if(!(ipAddress.equals("0.0.0.0")))//No Internationalisation
                    {
                        dnsname = 
                            com.adventnet.nms.util.WatchUtil.getDNSName(dnsname);
                    }
                }
            }

            // this timeToSchedule is used by  the rdbms mode to 
            // scheudle the PolledData.

            long timeToSchedule = new Date().getTime() + 10000;

            String key = "";//No Internationalisation
            try {
                Vector v = getPollsForAgent(agentname);
			
                for(int i=0;i<v.size();i++)
                {
                    
                    key = (String)v.elementAt(i);
                    PolledData pd = null;
                    try {
                        pd = checkOut(key,LOCK_TIMEOUT);
                    }
                    catch(Exception e)
                    {
                        NmsLogMgr.POLLERR.fail(e.getMessage(),null);
                    }
                    if(pd != null)
                    {
                        String pdown = pd.getOwnerName();
                        String moown = obj.getOwnerName();

                        try
                        {
                        
                        if(((pdown == null)&&(moown == null)) || ((pdown != null) && (pdown.equals(moown))))
                        {
                            Properties oldProps = pd.getProperties();
                            comapi.updateStatus(key, status, timeToSchedule, dnsname);
                            pd.setActive(status);
                            RunPoll.notifySchedulerAboutNewPoll(timeToSchedule);
                                notifyPD(PollConstants.MODIFY_OBJ_DETAILS,pd);
                            if(checkForPoller(pd) != null)
                            {
                                intimatePoller(pd,"modification");//No Internationalisation
                            }
                            CvcMaintainer.update("poll", "Changed", key,oldProps,pd.getProperties()); //No Internationalisation
                        }
                        }
                        finally
                        {
                            unlock(pd);
                        }

                    }
                }
            }catch(Exception ne)
            {
                NmsLogMgr.POLLERR.fail(NmsUtil.GetString("PollMgr Key : ") + key + " " + ne, ne);//No Internationalisation
            }
            if (!comapi.isDB) 
            {
                if(status)
                {


        try{

                    int totalNum = snmppolls.size();
                    for(Enumeration e=snmppolls.keys();e.hasMoreElements();)
                    {
                        String acpKey = (String)e.nextElement();
                        StringTokenizer st = new StringTokenizer(acpKey,"::");//No Internationalisation
                        if(agentname.equals(st.nextToken()))
                        {
                            try
                            {
                                //RunPoll.updateSchedule(acpKey, 180000);
                            }
                            catch (NullPointerException ne)
                            {
                                NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception while updating the schedule in PollMgr ") + ne, ne);
                            }
                        }
                    }

        }
        catch(Exception e2){}

         }
      }
		
	}
	
	/**
	 * Stops and restarts all the polls.
	 * This is a costly process and avoid using this method.
	 */                                                           
	public void restart()
	{
                stopDataPoll();
		startDataPoll();     
	}

	/**
	 * Stops all data collection. This is a costly operation and it is
	 * generally not advisable to call this method.
	 */                                                    
	public synchronized void stopAll()
	{
  		stopDataPoll();
	}
	
	/**
	 * Saves the state of the polling engine. It is not required to call
	 * this method as saving  the State is done automatically whenever any
	 * changes are made. This is used during ShutDown  of the Server to
	 * make sure the latest information is saved.
	 */                                                         
	public void savePollState()
	{
                saveState();
	}

	/**
	 * Returns key of all the PolledData objects in the Polling Engine 
	 * which are currently available .
	 * @return vector containing key of all the PolledDatas.
	 * The key for a PolledData pd is <br> 
	 * <b>pd.name + "\t" + pd.agent.toLowerCase().trim() + "\t" + pd.oid</b>.//No Internationalisation
	 * @deprecated as of version WebNMS2.0 
	 * use {@link #getCompleteList() getCompleteList()} instead.

        * @exception UserTransactionException if this method is called within
        * a transaction block and if transaction times out while retrieving the
        * list of PolledData objects.
        * @exception NmsPollException if there is any problem in getting the
        * list of PolledData objects 
        * when the method is called within a non-transaction block.

	 * @exception RemoteException if error occurs during remote call
	 */

        public Vector getList() throws UserTransactionException,
NmsPollException, java.rmi.RemoteException 



	{
   		Vector v = new Vector();
   		long i = 0;

                try{
		for (Enumeration e=pdatas.elements();e.hasMoreElements();) 
		{
			v.addElement(e.nextElement());
			i++;
		}
                }
                catch(NmsStorageException e2)
                {
                        throw new NmsPollException(e2.getMessage(),e2);
                }







		return v;
	}

	/**
	 * To get  a specified PolledData element whose key which uniquely
	 * identifies that PolledData element is passed as a parameter.
	 * The key for a PolledData pd is
	 * pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid//No Internationalisation
	 * @param     key     The unique key for the PolledData object
	 *                    formed by concatinating name, agent, oid
	 *                    fields of the PolledDataobject with "\t"
	 *                    as the separator between the fields.//No Internationalisation
	 * @return    Return the PolledData object corresponding to the key.

         * @exception NmsPollException if any problem while retrieving PolledData
        * @exception UserTransactionException if this method is called
        * within a transaction block and if transaction times out.

	 */                                                                       

    public PolledData getPolledData(String key) throws UserTransactionException,
NmsPollException



	{
		PolledData pd =null;   
		
		try
		{
			pd = (PolledData)comapi.getObject(key);	
		}

                catch(UserTransactionException e1)
                {
                        throw e1;
                }


                catch(Exception ex)
		{
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the poll for key ") + key + "  "+ex.getMessage(), ex);

                        throw new NmsPollException("Could not get PolledData for the key "+key+" "+ex.getMessage(),ex);



		}
		
		if (pd==null)
		{
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("pd is null for ") + key, Log.INTERMEDIATE_DETAIL);
			return null;
		}
		
		if (pd.getIsMultiplePolledData()) 
		{
			MultiplePolledData mpd = null;
			try {
				mpd =(MultiplePolledData) pd;
			} catch (ClassCastException cce)
			{
				mpd = new MultiplePolledData();
				mpd.setId(pd.getId());
				mpd.setProperties(pd.getProperties());
			}
			return mpd;
		}
		return pd;
	}


	/**
	 * Stops the Snmp Poll identified by the key and also deletes the 
         * corresponding PolledData object from the Polling Engine.
	 * @param   keyer   The key for the PolledData object.The key is
	 * pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid//No Internationalisation
	 * @return "true" if PolledData with the given key is removed //No Internationalisation
         * successfully, "false" if PolledData could not be removed or if //No Internationalisation
         * PolledData with the given key could not be found.

        * @exception UserTransactionException if there is any exception while doing transaction operations.
        * @exception NmsPollException 
        * if there is any problem in deleting the PolledData from the database

	 */

    public boolean removePoll(String keyer) throws NmsPollException, UserTransactionException, RemoteException




    {

        PolledData pd=null;
        if(keyer==null) throw new NmsPollException("key is null",null);
        try
        {
           pd=(PolledData)comapi.getObject(keyer);
        }
        catch(NmsStorageException e)
        {
                throw new NmsPollException(e.getMessage(),e);
        }
        return removePoll(pd,false);










    }    

    /**
	 * Stops the Snmp Poll identified by the key and also deletes
	 * the corresponding  PolledData object from the Polling Engine.
         * This checks for lock if the second parameter is true.
	 * @param   pd   The key for the PolledData object.The key is
	 * pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid//No Internationalisation
         * @param dealWithLocks boolean suggesting whether to check for
         * locking or not.
	 * @return "true" if PolledData with the given key is removed //No Internationalisation
         * successfully, "false" if PolledData could not be removed or if //No Internationalisation
         * PolledData with the given key could not be found.

        * @exception UserTransactionException if there is any exception while
doing transaction operations.

         * @exception RemoteException if an exception occurs during remote call.

         * @exception NmsPollException if an exception occurs while removing PolledData         
	 */                                                                   


   public boolean removePoll(PolledData pd , boolean dealWithLocks) throws NmsPollException, UserTransactionException, RemoteException



    {
        PureServerUtils.backUpInProcess();     
        String key = pd.getKey();
        if(dealWithLocks)
        {
            boolean b = checkForAccess(pd);
        }
        
        try {
            pdatas.removeElement(key);
            
            // we have to remove the polleddata from the snmppoll
            if (pd!=null) 
            {

            try
            {
              begin();
              comapi.deleteObject(pd);
              commit();
            }
            catch(NmsStorageException nse)
            {
                NmsLogMgr.POLLERR.fail(NmsUtil.GetString("deletepoll exception")+nse.getMessage(),nse);
                rollback(nse.getMessage());
                throw new NmsPollException("deletepoll exception "+nse.getMessage(),nse);
            }
                       












                pdCount--;

                notifyPD(PollConstants.REMOVE_OBJECT,pd);
                deleteOne(pd);    
                intimatePoller(pd,"deletion");//No Internationalisation
                saveState();
                CvcMaintainer.update("poll", "Deleted",key,pd.getProperties());//No Internationalisation
            } else {
                return false;
            }
            //if(dealWithLocks) unlock(pd);
        }

        catch(UserTransactionException e)
        {
            throw e;
        }

        catch(Exception anye)
        {
            throw new NmsPollException(NmsUtil.GetString("Exception removing poll -")+key+" "+anye.getMessage() , null);//No Internationalisation  
        }
        finally
        {
			if (dealWithLocks)
			{
            	unlock(pd);
			}
        }
        return true;

    }
	/**
	 * Modifies the PolledData whose key matches with the key of 
         * PolledData passed as argument. This also checks whether 
         * the object is locked if the second parameter is true.  If the 
         * key of the PolledData, passed as the argument, matches that of 
         * any other PolledData object already in the database, then that 
         * PolledData's properties will be changed
	 * to the given PolledData's properties. Else the given PolledData will
	 * be added as a new PolledData and the Poll is started for this.
	 * @param   pdata The PolledData object to be modified.
         * @param dealWithLocks boolean to check for locking.

         * @exception UserTransactionException if there is any exception while
doing transaction operations.           
         * @exception NmsPollException if ,<ul>
        * <li> there is any exception while retrieving PolledData from the
database 
        * <li> if there is any exception while updating database.</ul>
        * @exception RemoteException if there is any exception while doing
        * remote call.




	 */                                                  

       	public void modifyPoll (PolledData pdata,boolean dealWithLocks) throws NmsPollException , UserTransactionException, RemoteException




	{
		//Wait if back up is going on.
		PureServerUtils.backUpInProcess();     
		if (pdata == null) return;

		// change the case of dnsName and agent
		// first the dnsName because the setAgent
		// also sets the DNS name
		String dnsName = pdata.getDnsName();	
		pdata.setAgent(pdata.getAgent().toLowerCase());
		pdata.setDnsName(dnsName.toLowerCase());
		String key1 = pdata.getKey();
		PolledData pd = null;
                boolean b1=false;

                try{
                        b1=pdatas.contains(key1);
                }
                catch(UserTransactionException e2)
                {
                        throw e2;
                }
                catch(NmsStorageException e1)
                {
                        throw new NmsPollException(e1.getMessage(),e1);
                }




                  if (!b1)
                  {
                      addPoll(pdata);
                     return;
                  }

        else if(dealWithLocks)
        {
            boolean b = checkForAccess(pdata);            
            try {
                pd = (PolledData)comapi.getObject(key1);
            }


            catch(UserTransactionException e2)
                {
                    throw e2;
                }

            catch(Exception ce)
            {
                throw new NmsPollException(NmsUtil.GetString("Object is locked cannot modify ") + ce.getMessage(),null);
            }
            finally
            {
                unlock(pdata);
            }
        }
        else 
        {
            try
            {
                pd= (PolledData)comapi.getObject(key1);
            }

            catch(UserTransactionException e1)
            {
                throw e1;
            }

            catch(Exception nse)
            {
                throw new NmsPollException(NmsUtil.GetString("Exception in getting the Object with key ") +key1 + " " +nse.getMessage(), nse);//No Internationalisation
            }
        } 

		if (pd == null)
		{
                    throw new NmsPollException(NmsUtil.GetString("Cannot modify poll.  Does not exist ")+key1, null);
		}

                try{               

                    begin();
                    comapi.updateObject(pdata);
                    commit();
                  }
                catch(NmsStorageException ex)   
                {
                    rollback(ex.getMessage());
                    throw new NmsPollException(NmsUtil.GetString("modifypoll exception")+ex.getMessage(),null);
                }












                finally
                {
					if (dealWithLocks)
					{
                    	unlock(pdata);
					}
                }
                if (checkForPoller(pd) != null)
                {
                        intimatePoller(pdata, "modification");
                }
		RunPoll.notifySchedulerAboutNewPoll(pdata.getTimeVal());
                updateDataCollectionQueryInterval(pdata.getPeriod(),false);

                notifyPD(PollConstants.MODIFY_OBJ_DETAILS,pdata);
                
                //if(dealWithLocks) unlock(pdata);
		CvcMaintainer.update("poll", "Changed",
                                     key1,pd.getProperties(),
                                     pdata.getProperties());                
                try {
                    if(!pdata.getStatsDataTableName().equals("STATSDATA%") && !pdata.getStatsDataTableName().equals("STRINGDATA%"))//No Internationalisation
                        comapi.createDynamicTable(pdata.getStatsDataTableName());

		}

            catch(UserTransactionException e2)
                {
                    throw e2;
                }

                catch(Exception ex)
                {
                    throw new NmsPollException(NmsUtil.GetString("Error in modifyPoll:")+ex,ex);
                }

	}

	/**
	 * Modifies the PolledData whose key matches with the key of PolledData
	 * passed as argument. If the key of the PolledData, passed
	 * as the argument, matches that of any other PolledData object already
	 * in the database, then that PolledData's properties will be changed
	 * to the given PolledData's properties. Else the given PolledData will
	 * be added as a new PolledData and the Poll is started for this.
	 * @param   pdata The PolledData object to be modified.

         * @exception UserTransactionException if there is any exception while
doing transaction operations.           
        * @NmsPollException if ,<ul>
        * <li> there is any exception while retrieving PolledData from the
database 
        * <li> if there is any exception while updating database.</ul>
	 * @exception RemoteException if error occurs during remote call
         

	 */

    public void modifyPoll(PolledData pdata) throws NmsPollException, UserTransactionException, RemoteException



        {


            modifyPoll(pdata,false);    









        }
	/**
	 * saves the database when required 
	 */
	transient SavePM savepm = null;

	/**
	 * Periodically saves the state of this object 
	 */
	void saveState() 
	{
		if(comapi.isDB)	 return;
		
		if (savepm == null)
		{
			savepm = new SavePM(this);
		}
		savepm.scheduleSave = true;
		savepm.schedule();
	}

	/**
	 * Registers for the data collection done for the PolledData whose
	 * key corresponds to the key passed as argument. 
         * This PollObserver will be notified whenever data is collected for 
         * that PolledData.
	 * @see com.adventnet.nms.poll.PollObserver
	 * @param    key     The key of the PolledData object whose statistic
	 * will be notified to the PollObservers registered for the key.
	 * @param   pollob  The PollObserver interface implementor class which
	 * is notified of the collected statistics.
	 */
	public void registerForData(String key,PollObserver pollob)
	{

		PolledData pd=null;
		try
		{

		if (!pdatas.contains(key)) 
		{
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString(" No such polleddata for registering pollobserver -")+key , Log.INTERMEDIATE_DETAIL);
			return;
		}

			pd= (PolledData)comapi.getObject(key);             
		}
		catch(Exception nse)
		{
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception in getting the Object with key ") +key + NmsUtil.GetString(" for registering as observer") +nse.getMessage(), nse);
			return;
		}
	
		if (pd!=null)
                {
                    if (pollObservers== null)
                        pollObservers = new Hashtable();
                    if(pollObservers.containsKey(key))
                    {
                            
                        PDSpecificNotifier notifier=(PDSpecificNotifier)pollObservers.get(key);
                        notifier.addObserver(pollob);
                    }
                    else
                    {
                        PDSpecificNotifier notifier=new PDSpecificNotifier();                            
                        notifier.addObserver(pollob);
                        pollObservers.put(key,notifier);
                    }

		} 
		else
		{
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString(" Polled data -")+key+NmsUtil.GetString(" not found ") , Log.INTERMEDIATE_DETAIL);
		}
	}

	/**
	 * Deregisters the specified poll observer from being
         * notified when the data is collected for PolledData whose
	 * key corresponds to the key passed as argument.
	 * @see com.adventnet.nms.poll.PollObserver
	 * @param  key  The key of the PolledData object whose statistic was
	 * notified to the PollObserver.
	 * @param  pollob The PollObserver which has to be notified.
	 */
	public void deregisterForData(String key,PollObserver pollob)
	{

            if (pollObservers!=null)
            {

                PDSpecificNotifier notifier = (PDSpecificNotifier)pollObservers.get(key);
                if(notifier != null)
                    {
                        notifier.deleteObserver(pollob);
                        if(notifier.countObservers() == 0) pollObservers.remove(key);
                    }
                else
                    {
                        NmsLogMgr.POLLUSER.log(NmsUtil.GetString("No PollObserver has been registered for ") +key, Log.INTERMEDIATE_DETAIL);
                    }
                
            }
	}

	/**
	 * Register for all data which is collected by Poll Engine.
         * This PollObserver
	 * will be notified whenever a data is collected.
	 * @see com.adventnet.nms.poll.PollObserver
	 * @param   pollob  The PollObserver interface implementor class which
	 * is notified of the collected statistics.
	 * @exception RemoteException if error occurs during remote call
	 */
	public void registerForAllData(PollObserver pollob) throws java.rmi.RemoteException
	{
		if (pollob != null)
		{
		//***	if (allPollObservers == null) allPollObservers = new Vector();
                //***	allPollObservers.addElement(pollob);
                        allDataNotifier.addObserver(pollob);
		}
		else
		{
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString(" PollObserver is null "),null);
		}
	}

	/**
	 * Deregisters the specified pollobserver from being notified.
	 * @see com.adventnet.nms.poll.PollObserver
	 * @param  pollob  The PollObserver to be deregistered.
	 * @exception RemoteException if error occurs during remote call
	 */
	public void deregisterForAllData(PollObserver pollob) throws java.rmi.RemoteException
	{
            
                allDataNotifier.deleteObserver(pollob);
	}

	/**
	 * Registers for data collection whenever the data is collected for the
         * agent passed as argument. 
         * This PollObserver
	 * will be notified whenever data is collected from that agent.
	 * @see com.adventnet.nms.poll.PollObserver
	 * @param    agent     The  agent name whose statistic will be notified
	 * to the PollObserver.
	 * @param   pollob  The PollObserver interface implementor class which
	 * is notified of the collected statistics.
	 * @exception RemoteException if error occurs during remote call
	 */
	public void registerForDataFromAgent(String agent,PollObserver pollob) throws java.rmi.RemoteException
	{
		if(pollob != null)
		{
                    if(agentObservers == null) agentObservers = new Hashtable();
                    if(agentObservers.containsKey(agent)) 
                    {
                        AgentSpecificNotifier notifier = (AgentSpecificNotifier)agentObservers.get(agent);
                        notifier.addObserver(pollob);
                    }
                    else 
                    {
                        AgentSpecificNotifier notifier=new AgentSpecificNotifier();
                        notifier.addObserver(pollob);
                        agentObservers.put(agent,notifier);
                    }                    
		}
	}

	/**
	 * Deregisters the PollObserver from being notified of the data 
         * collected from the agent.
	 * @see com.adventnet.nms.poll.PollObserver
	 * @param agent agent name which has tobe deregistered from notifying.
	 * @param  pollob  The PollObserver interface implementor class which is
	 * notified of the statistics collected.
	 * @exception RemoteException if error occurs during remote call
	 */
	public void deregisterForDataFromAgent(String agent,PollObserver pollob) throws java.rmi.RemoteException
	{
		if(agentObservers!= null)
		{
                    AgentSpecificNotifier notifier = (AgentSpecificNotifier)agentObservers.get(agent);
                    if(notifier!=null)
                        {
                            notifier.deleteObserver(pollob);
                            if(notifier.countObservers()==0) agentObservers.remove(agent);
                        }
                    else
                        {
                            NmsLogMgr.POLLUSER.log(NmsUtil.GetString("No PollObserver is registered for ")+agent,Log.INTERMEDIATE_DETAIL);
                        }
		}
	}

	/** 
	 * To get all the collected datas of the given polldata for a given date.
	 * @param pd PolledData whose datas has to be returned.
	 * @param date Date as String specified in Month-Day-Year (9-20-1997)format.
	 * If today's data is required then date is specified as "current".//No Internationalisation
	 * @return Vector containing collected statistics.
	 * 
	 */


        static Vector getData(PolledData pd,String date) throws
UserTransactionException, NmsPollException



{
		if (pd==null) return null;
		try
		{
			if (pd instanceof MultiplePolledData) 
				return comapi.getData(pd.getId(),((MultiplePolledData)(pd)).currentIndex,date, pd.getStatsDataTableName());
			return comapi.getData(pd.getId(),null, date, pd.getStatsDataTableName());
		}

                catch(UserTransactionException e1){throw e1;}
                catch(Exception e3)
                {
                         NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data for the poll ")+ pd.getKey()+" " +e3.getMessage(), e3);
                        throw new NmsPollException(e3.getMessage(), e3);
                }







	}

	/**
	 * To get the data of the given PolledData object for a given time period.
	 * This method  requires the start time and end time in long.
	 * As of version 2.0, this method returns null in the case of data storage
	 * being flat files  and returns the data in case of  storage being RDBMS.
	 * @param  pd  The PolledData Object whose collected statistic is required.
	 * @param  timeStart The starting time from when the collected statistic
	 * is required. The time  is given as a long.
	 * @param  timeEnd  The end time until when the collected statistic is
	 * required as long.
	 * @return Vector containing the collected statistics.
	 */


	static Vector getData(PolledData pd,long timeStart,long timeEnd) throws
UserTransactionException , NmsPollException



	{
		if (pd==null) return null;
		
		// If the PolledData is polled through a poller then we get the
		// data from the Poller.  The Method checkForPoller returns the
		// Poller of the PolledData or null, if the Pd does not have any
		// poller.
		RemotePoller up = checkForPoller(pd);
		
		if(up != null) 
		{
			try{
				Vector v =  up.getData(pd,timeStart,timeEnd);
				return v;

			}catch(Exception re)
			{
				NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data from the poller")+re,re);
				return null;
			}
		}   
		
		try
		{
			if (pd instanceof MultiplePolledData) 
				return comapi.getData(pd.getId(),((MultiplePolledData)pd).currentIndex,timeStart,timeEnd, pd.getStatsDataTableName());
			return comapi.getData(pd.getId(),null,timeStart,timeEnd, pd.getStatsDataTableName());
		}

                catch(UserTransactionException e1){throw e1;}
                catch(Exception e3)
                {
                         NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data for the poll ")+ pd.getKey()+" " +e3.getMessage(), e3);
                        throw new NmsPollException(e3.getMessage(), e3);
                }








	}

	/**
	 * To get the data of the given PolledData object for a given time period.
	 * This method  requires the start time and end time in long.
	 * As of version 2.0, this method returns null in the case of data storage
	 * being flat files  and returns the data in case of  storage being RDBMS.
	 * @param pd The PolledData Object whose collected statistic is required.
	 * @param  timeStart The starting time from when the collected statistic
	 * is required. The time  is given as a long.
	 * @return Vector containing the collected statistics. 
	 */


	static Vector getData(PolledData pd,long timeStart) throws
UserTransactionException , NmsPollException



	{
		if (pd==null) return null;

		// If the PolledData is polled through a poller then we get the
		// data from the Poller.  The Method checkForPoller returns the
		// Poller of the PolledData or null, if the Pd does not have any
		// poller.
		RemotePoller up = checkForPoller(pd);

		if(up != null) 
		{
			try{
				Vector v = up.getData(pd,timeStart);
				return v;


			}catch(Exception re)
			{
				NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data from the poller")+re,re);
				return null;
			}
		}   
		try
		{
			if (pd instanceof MultiplePolledData) 
				return comapi.getData(pd.getId(),((MultiplePolledData)pd).currentIndex,timeStart,(new Date()).getTime(), pd.getStatsDataTableName());
			return comapi.getData(pd.getId(),null,timeStart,(new Date()).getTime(), pd.getStatsDataTableName());
		}

                catch(UserTransactionException e1){throw e1;}
                catch(Exception e3)
                {
                         NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data for the poll ")+ pd.getKey()+" " +e3.getMessage(), e3);
                        throw new NmsPollException(e3.getMessage(), e3);
                }







		
	}

    /**
     * Saves both long and string type of data(for both PolledDatas 
     * and MultiplePolledDatas) and notifies PollObservers.
	 * If BULK_INSERT_INTERVAL and MAX_ALLOWABLE_INSERT parameters
	 * are given in WebNMS-Home/conf/NmsProcessBE.conf, then the
	 * collected values are saved as bulk in a single transaction depending
	 * upon the values set to these parameters.
	 * BULK_INSERT_INTERVAL gives the time interval (in seconds) after which collected
	 * values will be stored in the database as a bulk. The second parameter
	 * MAX_ALLOWABLE_INSERT gives the maximum insert strings that can be kept
	 * in memory before saving. 
	 * <p>
	 * For example, if 300
	 * seconds is set as BULK_INSERT_INTERVAL and MAX_ALLOWABLE_INSERT
	 * value is set to 2000, then the collected values will be stored in
	 * the database as a bulk when either of the parameter is satisfied.
	 * <p>	
	 * <b>Note:</b> MAX_ALLOWABLE_INSERT parameter alone should not be set
	 * as it will be considered only if the BULK_INSERT_INTERVAL 
	 * parameter is set. If BULK_INSERT_INTERVAL parameter alone
	 * is set, a default value of 1000 will be set to the
	 * MAX_ALLOWABLE_INSERT parameter.
     * @param colinfo the CollectedInfo instance containing collected data.

     * @exception NmsPollException if there is any exception while saving data
     * in non-transaction mode
     * @exception UserTransactionException if there is any exception while
     * saving data in transaction mode.

     * @see PollObserver
     */

    public void saveData(CollectedInfo colinfo) throws UserTransactionException,
NmsPollException 



    {
        saveData(colinfo , true);
    }
    
	
	/**
  	 * Saves both long and string type of data(for both PolledDatas and MultiplePolledDatas)
  	 * and notifies PollObservers if intimateObservers is true.
	 * If BULK_INSERT_INTERVAL and MAX_ALLOWABLE_INSERT parameters
     * are given in WebNMS-Home/conf/NmsProcessBE.conf, then the
     * collected values are saved as bulk in a single transaction depending
     * upon the values set to these parameters. 
	 * BULK_INSERT_INTERVAL gives the time interval (in seconds) after which collected
     * values will be stored in the database as a bulk. The second parameter
     * MAX_ALLOWABLE_INSERT gives the maximum insert strings that can be kept
     * in memory before saving.
     * <p>
	 * For example if 300
     * seconds is set as BULK_INSERT_INTERVAL and MAX_ALLOWABLE_INSERT
     * value is set to 2000, then the collected values will be stored in
     * the database as a bulk when either of the parameter is satisfied.
	 * <p>
     * <b>Note:</b> MAX_ALLOWABLE_INSERT parameter alone should not be set
     * as it will be considered only if the BULK_INSERT_INTERVAL
     * parameter is set. If BULK_INSERT_INTERVAL parameter alone
     * is set, a default value of 1000 will be set to the
     * MAX_ALLOWABLE_INSERT parameter.
	 *     
	 * @param colinfo the CollectedInfo instance containing collected data.
     * @param intimateObservers a boolean specifying whether to intimate
     * observers or not.
	 *

     * @throws  NmsPollException if there is any exception while saving data
     * in non-transaction mode
     * @throws UserTransactionException if there is any exception while
     * saving data in transaction mode.

	 */

        public void saveData(CollectedInfo colinfo,boolean intimateObservers)
throws UserTransactionException, NmsPollException



	{
                String agentname="";
                String key="";
		try {
                    Vector keys=colinfo.getKeys();
                    
                    if(keys==null) return;
                    long time=colinfo.getTime();

					if((DataCollectionConstants.BULK_INSERT_INTERVAL != 0) && (firstTime))
            		{
               	    	long curtime = (System.currentTimeMillis())/1000;
                  		updateTime = curtime + DataCollectionConstants.BULK_INSERT_INTERVAL;
                   		firstTime = false;
            		}   
					
                    begin(-1);
                    for(Enumeration e=keys.elements();e.hasMoreElements();)
                    {
                        
                        key=(String)e.nextElement();
                        PolledData pd = colinfo.getPolledData(key);
                        agentname = pd.getAgent();
                        Vector instances = colinfo.getInstances(key);
                        if(instances==null) 
                        {
                            if(DataCollectionConstants.DEBUGGING_MODE)
                            {
                                NmsLogMgr.POLLUSER.log(NmsUtil.GetString("updating Pd properties for ")+agentname+": "+pd.getOid(),Log.INTERMEDIATE_DETAIL);//No Internationalisation
                            }
                            //continue;
                        }
                        else
                        {
                        for(Enumeration e1=instances.elements();e1.hasMoreElements();)
                        {

                            String inst=(String)e1.nextElement();
                            Object obj=colinfo.getValue(key,inst);
                            long longVal=-1;
                            String stringVal=null;
                            if(obj instanceof Long)
                                longVal = ((Long)obj).longValue();
                            else if(obj instanceof String)
                                stringVal = obj.toString();
                            applyThresholds(longVal,stringVal,inst,pd);
                            // updateLastValues(time,longVal,stringVal,inst,pd,false);
                            if(DataCollectionConstants.DEBUGGING_MODE)
                            {
                                if(obj instanceof Long)
                                    NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Saving data for ")+agentname+": "+pd.getOid()+NmsUtil.GetString(" for the instance ")+inst+NmsUtil.GetString(" :Value is ")+longVal,Log.INTERMEDIATE_DETAIL);//No Internationalisation
                                else
                                    NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Saving data for ")+agentname+": "+pd.getOid()+NmsUtil.GetString(" for the instance ")+inst+NmsUtil.GetString(" :Value is ")+stringVal,Log.INTERMEDIATE_DETAIL);                                        
                            }                                        
                            saveData(time,longVal,stringVal,inst,pd,colinfo);
                            if((pollObservers != null) && (intimateObservers))
                            {
                                notifyData(time,longVal,stringVal,colinfo.getPolledData(key));
                            }
                        }
                        }
                        if(colinfo.getErrorMessage(key)==null)
                                updateLastValues(time,pd);
                    }
                    commit();
		}

                catch(UserTransactionException e1)
                {
                    throw e1;
                }
                catch(NmsStorageException e4)
                {
                    NmsLogMgr.POLLERR.fail(e4.getMessage(),e4);
                    rollback(e4.getMessage());
                    throw new NmsPollException(e4.getMessage(),e4);
                }

                catch(Exception e)
		{
                    NmsLogMgr.POLLERR.fail("Exception saving the data for the key "+key+" "+e.getMessage(), e);

                    rollback(e.getMessage());   
                    throw new NmsPollException(e.getMessage(),e);



		}
                
                if(DataCollectionConstants.DEBUGGING_MODE)
                {
                    NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Data Collection finished for ")+agentname+NmsUtil.GetString(" at ")+new Date() ,Log.INTERMEDIATE_DETAIL);                    
                }                
		if(intimateObservers)
		{
                        allDataNotifier.notify(colinfo);
                        /*
			if(allPollObservers != null)
			{
				notifyAllData(colinfo);
			}
                        */
			if(agentObservers != null)
			{
                            notifyDataFromAgent(colinfo, colinfo.getAgent());	
			}                    

		}
		colinfo=null;
	}

					 
	/**
	 *Checks if the key given matches with the name,agent,oid passed.
	 */
	private boolean checkIfKeyMatches(String key,String name,String agent,String oid,String ownerName) 
	{
		if (key==null) return false;
		
		StringTokenizer tok = new StringTokenizer(key,"\t");//No Internationalisation
		int numTokens = tok.countTokens();

		if(numTokens == 3)
		{
			if ((oid==null) && (name==null) && (agent==null) )
				return true;
		}
		else if(numTokens == 4)
		{
			if ((oid==null) && (name==null) && (agent==null) && (ownerName == null))
				return true;
		}
		
		String myname = tok.nextToken();
		if (name!=null) if (!checkProp(name,myname)) return false;
		String myagent = tok.nextToken();
		if (agent!=null) if (!checkProp(agent,myagent)) return false;
		String myoid = tok.nextToken();
		if (oid!=null) if (!checkProp(oid,myoid)) return false;
		if(numTokens == 4)
		{
			String myowner = tok.nextToken();
			if(ownerName!=null)if(!checkProp(ownerName,myowner)) return false;
		}	
		return true;
	}



	public Vector getObjectNamesWithProps(Properties p)  throws
UserTransactionException, NmsStorageException,java.rmi.RemoteException 



	{
		Vector v = new Vector();
		if (p == null) return v;
		
		if (p.size()==0)
		{

		for (Enumeration e=pdatas.elements();e.hasMoreElements();) 
                {

				String x = (String)e.nextElement();
				v.addElement(x);
		}
		return v;
		}
		// This will be true in case of db mode or otherwise
		if (comapi.canYouDoThisFilteringFast(p))
		{
			try
			{
				return comapi.getObjectNamesWithProps(p);
			}

                        catch(UserTransactionException e2)
                        {
                                throw e2;
                        }
                        catch(NmsStorageException e3)
                        {
                                throw e3;
                        }







		}	

		String oid = p.getProperty("oid");//No Internationalisation
		String name = p.getProperty("name");//No Internationalisation
		String agent = p.getProperty("agent");//No Internationalisation
		String ownerName = null;
		if(p.getProperty("ownerName") != null)//No Internationalisation
			ownerName = p.getProperty("ownerName");

                try{
		for (Enumeration e = pdatas.elements();e.hasMoreElements();) 
		{
			try {
				String pollkey = (String) e.nextElement();
				
				if (!checkIfKeyMatches(pollkey,name,agent,oid,ownerName))
					continue;

				PolledData ht = (PolledData) comapi.getObject(pollkey);
				if (ht == null)
				{
					NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Not able to get polleddata ")+pollkey , Log.INTERMEDIATE_DETAIL);
					continue;
				}

				String s;
				boolean match = true;
				Properties objproperty =  ht.getProperties();
				
				//comparing the properties of the pd with the given prop
				for (Enumeration en = p.propertyNames();en.hasMoreElements();) 
				{
					String key = (String) en.nextElement();
					String value = (String) p.get(key);

					// if the value is null we don't compare or else we do
					if (value != null)
					{
						if(((s = (String)objproperty.get(key)) == null ) || (!checkProp(value,s))) 
						{
							// mismatch
							match = false;
							break;
						}
					}	
				} // end each h key

				
				if (match)
				{
					// matches so adding to the vector.
					v.addElement(pollkey);
				}	
			}

                        catch(UserTransactionException e1)
                        {
                                throw e1;
                        }
                        catch(NmsStorageException e2)
                        {
                                throw e2;
                        }






		}
                }

                catch(UserTransactionException e1)
                {
                        throw e1;
                }
                catch(NmsStorageException e2)
                {
                        throw e2;
                }







		return v;

	} // end getObjectNamesWithProps()



        public  Properties getPropertiesOfObject(String objectkey) throws UserTransactionException,NmsStorageException



	{

            PolledData pd=null;
            try
            {
                pd=getPolledData(objectkey);
                return pd.getProperties();
            }
            catch(UserTransactionException e1)
            {
                throw e1;
            }    
            catch(NmsPollException e)
            {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }



		if (pd==null) 
		{
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("No such PolledData ")+objectkey , Log.INTERMEDIATE_DETAIL);
			return null;
		}
		return pd.getProperties();
	}



        public Vector getCompleteList() throws UserTransactionException,NmsStorageException



	{
		Vector v = new Vector();
		long i = 0;

		for (Enumeration e=pdatas.elements();e.hasMoreElements();)
		{
			v.addElement(e.nextElement());
			i++;
		}
		return v;
	}

	/**
	 * Checks whether the key of the PolledData is already added .
	 * @param key as <code>String</code> .
	 * @return boolean specifies the status of the operation .
	 */


        boolean alreadyContains(String key) throws UserTransactionException,
NmsPollException
        {
                try{
                return pdatas.contains(key);
                }
                catch(NmsStorageException e1)
                {
                        throw new NmsPollException(e1.getMessage(),e1);
                }
        }








	/**
	 * This method is for taking care of the various cases stars , nots ,
	 * comma-separated  In additon to this in various API's additions should
	 * be made for special cases.
	 */

	private boolean checkProp(String propval,String objval) {
		return NmsUtil.checkString(objval, propval);
	}


	void setDBStore() {
		//dbstore = new DBStore(db);
		//pdatas.setStore(dbstore);
	}

	/** This sets up the object store **/
	void setDBConn(CommonPollAPI dbase) {
		comapi=dbase;
		//comapi.specialTable = specialKeyTable;
		id =  comapi.getMaxId();
	}
	/**
	 * Returns the data collected for the PolledData object whose key matches 
	 * with the key passed, which is collected on a given date.
	 * @param  key  The key of the PolledData Object whose data are required.
	 * The key for a PolledData pd is
	 * <b>pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid</b>//No Internationalisation
	 * @param  date  The date on which data collected  is required. 
	 * The date is given in  <b>Month-Day-Year (9-20-2000)</b> format.
	 * @return  The Vector containing the collected statistics. The first
	 * element is the  time array as long[] and the second element is the 
	 * values array as long[] or string[] depending on the value collected
	 * Vector is null if no data is collected for the given key and on the 
	 * particular date given.
	 * @deprecated as of WebNMS2.2 
	 * use {@link #getCollectedData(String,Date) getColletedData(String, Date)}
         * @exception RemoteException if an error occurs during remote call.
	 */


public Vector getCollectedData(String key,String date) throws
UserTransactionException, NmsPollException, java.rmi.RemoteException




	{
		PolledData pd = getPolledData(key);

		if (pd==null) {
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Cannot get collected data: No polledData matching key ")+key , Log.INTERMEDIATE_DETAIL);
			return null;
		}
		return getData(pd,date);
	}
	/**
	 * Returns the data collected for the PolledData object whose key matches 
	 * with the key passed, which is collected between the specified time.
	 * This method  requires the start time and end time as <b>long</b>.
	 * @param  key  The key of the PolledData Object whose data are required.
	 * The key for a PolledData pd is
	 * <b>pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid</b>//No Internationalisation
	 * @param  timeStart The starting time from when the collected data 
	 * is required. The time  is given as a long.  
	 * @param  timeEnd  The end time until when the collected data is
	 * required as long.
	 * @return The Vector containing the collected statistics. The first 
	 * element is the  time array as long[] and the second element is the values
	 * array as long[] or String[] depending on the value collected.
	 * The return value is null if there is no data collected.
	 * @deprecated as of WebNMS2.2 
	 * use {@link #getCollectedValues(String,long,long) getColletedValues(String,long,long)}
	 */

        public Vector getCollectedData(String key,long timeStart,long timeEnd)
throws UserTransactionException, NmsPollException



	{
		PolledData pd = getPolledData(key);

		if (pd==null) {
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Cannot get collected data: No polledData matching key ")+key , Log.INTERMEDIATE_DETAIL);
			return null;
		}
		return getData(pd,timeStart,timeEnd);
	}
	/** 
	 * Returns the data collected for the given PolledData object whose key 
	 * is given and from the start time specified till the current time .
	 * This method  requires the start time in long.
	 * @param  key  The key of the PolledData Object whose data are required.
	 * The key for a PolledData pd is
	 * <b>pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid</b>//No Internationalisation
	 * @param  timeStart The starting time from when the collected data 
	 * is required. The time  is given as a long.  
	 * @return The Vector containing the collected statistics. The first 
	 * element is the  time array as long[] and the second element is the values
	 * array as long[] or String[] depending on the value collected.
	 * The return value is null if there is no data collected.
	 * @deprecated as of WebNMS2.2 
	 * use {@link #getCollectedValues(String,long) getColletedValues(String,long)}
	 */

        public Vector getCollectedData(String key,long timeStart) throws
UserTransactionException , NmsPollException



	{
		PolledData pd = getPolledData(key);
		if (pd==null) {
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Cannot get collected data: No polledData matching key ")+key , Log.INTERMEDIATE_DETAIL);
			return null;
		}
		return getData(pd,timeStart);
      }	
	/**
	 * Returns the data collected for the PolledData whose key corresponds to 
	 * the key specified as argument and for the specified instance on the
	 * specified date.  This method is used only in case of MultiplePolledDatas.
	 * @param index the instance of the MultiplePolledData for which 
	 * the data is required.
	 * @param  key The key of the PolledData Object whose data are required.
	 * The key for a PolledData pd is
	 * <b>pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid</b>//No Internationalisation
	 * @param date Date on which the data collected are required.
	 * @return CollectedData containing the collected statistics. 
	 */


public CollectedData getCollectedData(String index, String key, Date date)
throws UserTransactionException, NmsPollException



	{

		PolledData pd = getPolledData(key);
                if(PollUtil.getDataHandler() != null)
                {
                    if(PollUtil.getDataHandler().canHandleRequest(pd))
                    {
                        return PollUtil.getDataHandler().getCollectedData(pd,index,GetNextTime.getStartTimeOfDate(date),GetNextTime.getEndTimeOfDate(date));
                    }
                }
		if(pd == null) {
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Cannot get collected data: No PolledData with matching key ") + key, Log.INTERMEDIATE_DETAIL);
			return null;
		}
		return constructCollectedData(getData(index,pd,date));
	}
	
	/**
	 * Returns the data collected for the PolledData object whose key corresponds to the
	 * the key specified as argument and for the specified instance and form 
	 * the given time till current time.  This is used in case of MultiplePolledDatas.
	 * @param index instance of the MultiplePolledData
	 * @param  key The key of the PolledData Object whose data are required.
	 * The key for a PolledData pd is
	 * <b>pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid</b>//No Internationalisation
	 * @param  timeStart The starting time from when the collected statistic
	 * is required. The time  is given as a long.
	 * @return CollectedData containing the collected statistics.
	 */                                                                      

        public CollectedData getCollectedData(String index, String key, long timeStart) throws UserTransactionException , NmsPollException




	{
		PolledData pd = getPolledData(key);

            if(PollUtil.getDataHandler() != null)
            {
                if(PollUtil.getDataHandler().canHandleRequest(pd))
                {
                    return PollUtil.getDataHandler().getCollectedData(pd,index,timeStart,System.currentTimeMillis());
                }
            }
	   if(pd== null){
		   NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Cannot get collected data: No PolledData with matching key ")+ key,Log.INTERMEDIATE_DETAIL);
		   return null;
	   }
	   return constructCollectedData(getData(index,pd, timeStart));
	}
	
	/**
	 * Returns the data collected for the PolledData object whose key matches 
	 * with the key passed, which is collected between the specified time,
	 * and for the specified instance.  This is used only in case of
	 * MultiplePolledDatas.
	 * This method  requires the start time and end time as <b>long</b>.
	 * This method is used to get the Collected Data when 
	 * the PolledData is an instance of MultiplePolledData.
	 * @param index the instance string whose value is needed.
	 * @param  key  The key of the PolledData Object whose data are required.
	 * The key for a PolledData pd is
	 * <b>pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid</b>//No Internationalisation
	 * @param  timeStart The starting time from when the collected data 
	 * is required. The time  is given as a long.  
	 * @param  timeEnd  The end time until when the collected data is
	 * required as long.
	 * @return CollectedData containing the collected statistics.
	 */

        public CollectedData getCollectedData(String index, String key, long
timeStart, long timeEnd) throws UserTransactionException, NmsPollException




	{
        	PolledData pd = getPolledData(key);

           if(PollUtil.getDataHandler() != null)
           {
               if(PollUtil.getDataHandler().canHandleRequest(pd))
               {
                   return PollUtil.getDataHandler().getCollectedData(pd,index,timeStart,timeEnd);
               }
           }
	   if(pd== null){
		   NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Cannot get collected data: No PolledData with matching key ")+ key,Log.INTERMEDIATE_DETAIL);
		   return null;
	   }
	   return constructCollectedData(getData(index,pd, timeStart, timeEnd));
	}
	

	/**
	 * Suspends data deing collected for the specified agent.
         * No data will be collected for the PolledData that are
         * belong to this agent. 
	 * @param  agentname Name of the agent for whom polls has to be suspended.
	 * @return false if there are no polls configured for the agent else true.
	 */        

        public boolean suspendAllPollsForAgent(String agentname) throws
UserTransactionException, NmsPollException, RemoteException



	{
		//Wait if back up is going on.
		PureServerUtils.backUpInProcess();     
   		return changeActiveStateForPollsOfAgent(agentname,false);
	}

	private boolean changeActiveStateForPollsOfAgent(String agentname,boolean active)   throws UserTransactionException, NmsPollException , RemoteException




	{
            Vector v =  getPollsForAgent(agentname);
            if ( (v==null) || (v.size()==0) ) return false;
            for (int i=0;i<v.size();i++) 
            {
              
                PolledData pd = getPolledData((String)v.elementAt(i));
                if (pd!=null) {
                    pd.setActive(active);
				//should avoid this this is very costly

                    modifyPoll(pd);

                }
            }
            return true;
	}
     
       /**
	 * Restarts the data collection for an agent if there are any PolledData 
	 * objects configured for that agent.
	 * @param  agentname   Name of the agent for whom the data collection
	 * are to be resumed.
	 * @return false If there are no PolledData objects configured for the
	 * agent else true.  
	 * @exception RemoteException if an error occurs during remote call
	 */

public boolean resumeAllPollsForAgent(String agentname) throws
UserTransactionException, NmsPollException, RemoteException



	{
		//Wait if back up is going on.
		PureServerUtils.backUpInProcess();     
		return changeActiveStateForPollsOfAgent(agentname,true);
	}
	
 
       /**
	 * Returns the keys of all the PolledData objects configured for an agent as a Vector.
	 * The Key for the PolledData object pd is
	 * <b>pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid</b>//No Internationalisation
	 * @param  agentname  Name of the agent whose keys to the PolledData
	 * has to be returned.
	 * @return Vector of keys to the PolledData objects as <code>String</code>.
	 * Empty Vector if there is no PolledData configured for that agent.
	 * @exception RemoteException if an error occurs during remote call
	 */

        public Vector getPollsForAgent(String agentname) throws
UserTransactionException, NmsPollException



	{
	 	Vector tobereturned = new Vector();
		if(agentname == null)
			return tobereturned;
		agentname = agentname.toLowerCase();
		String keyagent="";//No Internationalisation
		if (!agentname.startsWith("IF-"))//No Internationalisation

                try{
			for (Enumeration en =pdatas.elements();en.hasMoreElements();) {
				String key = (String) en.nextElement();
				if (key.indexOf(agentname)>=0)
				{
					StringTokenizer st = new StringTokenizer(key,"\t");//No Internationalisation
					st.nextToken();
					keyagent=st.nextToken().trim();
					if(keyagent.equals(agentname.trim()))
					{
						tobereturned.addElement(key);
					}
				}
			}
                }
                catch(NmsStorageException e1)
                {
                        throw new NmsPollException(e1.getMessage(),e1);
                }















		return tobereturned;
	}
	/**
	 * Returns a Vector of objects matching the given properties. The classname is 
	 * the name of the class the objects belong to. <code>This call can be used only
	 * if the  object's properties are stored in an RDBMS. It is also assumed
	 * one object is  stored in only one table. If one object is stored in
	 * multiple tables, then only partial object will be returned.<code/>
	 * @param classname name of the class the object belongs.
	 * @param match  Properties which to be matched with the object's properties.
	 * @return Vector of objects whose properties match the properties given.
	 */                                                                  

        public Vector getObjects(String classname, Properties match) throws
UserTransactionException, NmsPollException



	{
 		if(classname.equals("MultiplePolledData"))//No Internationalisation
 		{
 			classname = "PolledData";//No Internationalisation
 			match.put("isMultiplePolledData","true");//No Internationalisation
 		}
		try
		{
			return comapi.getObjects(classname,match);
		}

                catch(UserTransactionException e1)
                {
                        throw e1;
                }
                catch(Exception e2)
                {
                        NmsLogMgr.POLLERR.fail(e2.getMessage(),e2);
                        throw new NmsPollException(e2.getMessage(),e2);
                }








	}
     
       /**
	 * Sets the AutoCommit mode of WebNMS Poll module. 
         * WebNMS PollModule has a connection to the database,
	 * This method sets the connection's auto-commit mode to true or false. 
	 * By default auto-commit mode is true. 	
	 * @param b boolean true/false which sets/resets the AutoCommit mode.
	 * @return boolean false if connection to the database doesnot exist,else
	 * true when connection exists and the autocommit is set.
	 * @exception RemoteException if an error occurs during remote call
	 * @deprecated as of version WebNMS2.2.1 
	 */	                         
	public boolean setAutoCommit(boolean b) 
	{
		return true;
	}
	
	/**
	 * Used as a utility to convert slashes.
	 */
	private String convertSlashes(String oldStr)
	{
		if (oldStr == null) return oldStr;
		else if (oldStr.indexOf('/') != -1) return oldStr.replace('/','$');
		else if (oldStr.indexOf('$') != -1) return oldStr.replace('$','/');
		else return oldStr;
	}
	
	/**
	 * Returns the total number of PolledData objects currently present in
	 * the Poll Engine.
	 * @return int Number of PolledDatas.
	 */       

       

    public int getNumPollObjects() throws NmsPollException,UserTransactionException
        {
            try{
               return pdatas.size();
            }
            catch(NmsStorageException e1)
            {
                  throw new NmsPollException(e1.getMessage(),e1);
            }

        }






	/**
	 * Executes the sqlString given .
	 * @param sqlString as <code>String<code>.
	 * @exception SQLException if an error occurs during database access
	 */

	public void execute(String sqlString) throws SQLException
	{
		try
		{
			comapi.execute(sqlString);
		}	
		catch(Exception nse)
		{
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception executing the query") +nse.getMessage(), nse);
		}

	}
	/**
	 * Registers the Poller with the Poll Engine when the Poller is not 
           already registered.
	 * @param rp Remote Poller which is to be registered with the Poll engine.
	 * @param name name of the poller
	 * @return false when the Poller is not registered since the Poller with
	 * the given name is already registered ,else true.
	 */        
	public boolean registerPoller(RemotePoller rp,String name)
	{
		NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Poller - ") + name + NmsUtil.GetString(" comes for registeration"), Log.VERBOSE);
		if(addPoller(rp,name))
		{
                        try {
                            rp.updateAboutInsertHash(insertHash);
                            rp.updatePollerWithProviderHash(initializer.getProviderHash());
                        }catch(RemoteException e)
                        {
                            e.printStackTrace();
                            return false;
                        }
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Poller - ") + name + NmsUtil.GetString(" registered successfully"), Log.VERBOSE);
			return true;
		}
		return false;
	}
       /**
	 * Returns the Vector of PolledData objects for which the 
         * data collection has to 
	 * be done by the poller with the name passed as argument.
	 * This method is purely for the use of poller which queries for the
	 * PolledData objects for which data collection has to be done by this
         * poller.
	 * @param name Name of the Poller as <b>String</b>.
	 * @return Vector of PolledData objects.
	 * @exception RemoteException if an error occurs during remote call
	 */

	public Vector fetchPollerSpecificPolldata(String name) throws java.rmi.RemoteException
	{
		try
		{
			Vector v = comapi.comtorel.getPollerSpecificPolldata(name);
			return v;
		}
		catch(Exception nse)
		{
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception fetching the polled datas of the poller ") + name +nse.getMessage(), nse);
			return null;
		}

	}

	/**
	 * Adds the given poller if the poller with the name given is not available.
	 * @param poller Poller which is tobe added as one of the Pollers for the NMS.
	 * @param name name of the poller
	 * @return "false" if poller with the same name is already present,"true" if a new poller//No Internationalisation
	 * has been added successfully
	 */
	
	private synchronized boolean addPoller(RemotePoller poller,String name)
	{
		if(poller == null) return false;

		if(name == null || name.length() < 0) return false;

		String s="";//No Internationalisation
		
		for(Enumeration en=Collector.pollers.keys();en.hasMoreElements();)
		{
			s=(String)en.nextElement();

			if(s.equals(name))
			{
				NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Cannot add the Poller") + name +NmsUtil.GetString(", Poller with the same name already exists"), null);
				return false;
			}
		}
		Collector.pollers.put(name,poller);
		return true;
	}
	
	static RemotePoller checkForPoller(PolledData pd)
	{
		String pollerName = pd.getPollerName();
		RemotePoller rp=null;
		String s = "";//No Internationalisation
		
		if((pollerName == null) || (pollerName.length() <= 0)) return null;

		for(Enumeration en=Collector.pollers.keys();en.hasMoreElements();)
		{
			s=(String)en.nextElement();

			if(s.equals(pollerName))
			{
				//check out whether this is needed
				rp = (RemotePoller)Collector.pollers.get(s);
				
				// just to check whether the connection is alive
				try
				{
					String temp =rp.getName();
				}catch(RemoteException re)
				{
					Collector.pollers.remove(s);
					return null;
				}
				return rp;
			}
		}
		return null;
	}	
	
	private void intimatePoller(PolledData pd,String con)
	{
		String pollerName = pd.getPollerName();
		String name = "";//No Internationalisation
		RemotePoller rp = null;

		if((pollerName == null) || (pollerName.length() <= 0)) return;

		for(Enumeration en=Collector.pollers.keys();en.hasMoreElements();)
		{
			name=(String)en.nextElement();

			if(name.equals(pollerName))
			{
				rp = (RemotePoller)Collector.pollers.get(name);
				try{
					if(con.equals("deletion"))//No Internationalisation
						rp.updatePollerAboutDeletion(pd.getKey());
					else	
						rp.updatePollerAboutPoll(pd,con);
				}catch(RemoteException re)
				{
					NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception in intimating the poller - ") + pollerName,re); 
				}
			}
		}
		return;
	}	
	/**
	 * Returns a Vector of tables names in which collected data are stored.
	 * @return return Vector containing table names as String  if RDBMS mode is used,
	 * null otherwise.
	 */               
  	 public Vector getTableNames()
   	{
		//don't get misguided by the name of the method.  
		// anyway the name of the method must be changed.
		if(comapi.comtorel != null)
			return comapi.comtorel.getPollerSpecificStatsTableNames();
		else	
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString("This method can be called only in the RDBMS mode"),null);
		return null;	
	}
	
	/**
	 * Intimates the poller about the deletion of a policy.
	 * @param policyname which is deleted as String.
	 */
   	void intimatePollerAboutPolicyDeletion(String policyname)
   	{
		RemotePoller rp = null;
		String name = "";//No Internationalisation
		for(Enumeration en=Collector.pollers.keys();en.hasMoreElements();)
		{
			name=(String)en.nextElement();
			rp = (RemotePoller)Collector.pollers.get(name);
			try{
				name= rp.getName();
				rp.removePolicyFromHash(policyname);
			}catch(RemoteException e)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Unable to intimate the poller ") + name + NmsUtil.GetString("about the removal of the policy  ") + policyname + "  " + e.getMessage(),e);//No Internationalisation
				Collector.pollers.remove(name);
				continue;
			}
		}	
   	}
	/**
	 * Updates the poller of the ThresholdObjects available.
	 * @param threshVector Vector containing Threshold objects.
	 */
	 
    void updatePollerAboutThresholdObjects(Vector threshVector)
    {
		RemotePoller rp = null;
		String name = "";//No Internationalisation
		
		for(Enumeration en=Collector.pollers.keys();en.hasMoreElements();)
		{
			name=(String)en.nextElement();
			rp = (RemotePoller)Collector.pollers.get(name);

			try{
				name= rp.getName();
				rp.updateAboutThresholdObjects(threshVector);
			}catch(RemoteException e)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Unable to intimate the poller ") + name + NmsUtil.GetString("about the thresholdobjects") + e.getMessage(),e);
				Collector.pollers.remove(name);
				continue;
			}
		}	
    }
	
    void updatePollerAboutPollingPolicies(Hashtable ht)
    {
   		RemotePoller rp = null;
		String name="";//No Internationalisation

		for(Enumeration en=Collector.pollers.keys();en.hasMoreElements();)
		{
			name=(String)en.nextElement();
			rp = (RemotePoller)Collector.pollers.get(name);
			try{
				name= rp.getName();
				rp.updateAboutPollingPolicies(ht);
			}catch(RemoteException e)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Unable to intimate the poller ") + name + NmsUtil.GetString("about the PollingPolicyObjects") + e.getMessage(),e);
				Collector.pollers.remove(name);
				continue;
			}
		}	
	}

       /**
	 * Updates the numericType of the PolledData whose key is same as the
	 * the key given, with the numericType passed.
         * Valid values are,
         * <ul>
         *<li>1- long
         *<li>2- string
         *</ul> 
	 * @param key key of the PolledData whose numericType has tobe changed 
	 * as string.
	 * The Key for the PolledData object pd is
	 * <b>pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid</b>//No Internationalisation
	 * @param numericType to which the numericType of PolledData has to be  changed.
	 * @exception RemoteException if an error occurs during remote call
	 */
                          

    public void updateNumericTypeOfPd(String key,int numericType) throws
UserTransactionException, NmsPollException ,java.rmi.RemoteException




   {
		if((numericType !=1) && (numericType !=2))
			return;
	       
		PolledData pd = getPolledData(key);
		if(pd == null ) 
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString("No Such PolledData with this key   ") + pd.getKey(),null);
		pd.setNumericType(numericType);
		try{
			//must avoid this and update only the numericType
			modifyPoll(pd);
		}

                catch(UserTransactionException e1)
                {
                        throw e1;
                }
                catch(NmsPollException e2)
                {
                        throw e2;
                }







    }
       /**
	 * Returns all the instances of the PolledData as a Vector when the type of the 
	 * PolledData is multiple or else returns one element with the value -1.
	 * @param mpd MultiplePolledData whose instances has to be obtained.
	 * @return Vector containing the instances of the mpd as String.
	 * @exception RemoteException if an error occurs during remote call
	 */

	public  Vector getInstances(MultiplePolledData mpd) throws
UserTransactionException, NmsPollException



	{
		RemotePoller up = checkForPoller(mpd);
		
		if (up != null) 
		{
			try
            {
				Vector v =  up.getInstances(mpd);
				return v;
			}
            catch (Exception re)
			{
				NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting "
                    + "the instance from the poller") +re, re);
				return null;
			}
		} 
        
		if(mpd == null) return null;
                if(PollUtil.getDataHandler() != null)
                {
                    if(PollUtil.getDataHandler().canHandleRequest(mpd))
                    {
                        return PollUtil.getDataHandler().getInstances(mpd);
                    }
                }
		try
		{
			return comapi.getInstances(mpd.getId(),mpd.getStatsDataTableName());
		}

                catch(UserTransactionException e1)
                {
                        throw e1;
                }
                catch(Exception e2)
                {
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception in getting the instances of the multiple polled data ") +  mpd.getKey() +" " +e2.getMessage(), e2);
                        throw new NmsPollException(e2.getMessage(),e2);
                }







	}
	/** 
	 * Returns the data collected for the PolledData object whose key matches 
	 * with the key passed as argument, which is collected on a given date.
	 * @param  key  The key of the PolledData Object whose data are required.
	 * The key for a PolledData pd is
	 * <b>pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid</b>//No Internationalisation
	 * @param  date  The date as on which data collected is required. 
	 * The date is the instance of <b>java.util.Date</b>
	 * @return  CollectedData containing the collected statistics.
         * @exception RemoteException if an error occurs during remote call
	 */


        public CollectedData getCollectedData(String key,Date date) throws
UserTransactionException , NmsPollException ,RemoteException 




	{
		PolledData pd = getPolledData(key);

                if(PollUtil.getDataHandler() != null)
                {
                    if(PollUtil.getDataHandler().canHandleRequest(pd))
                    {
                        return PollUtil.getDataHandler().getCollectedData(pd,"-1",GetNextTime.getStartTimeOfDate(date),GetNextTime.getEndTimeOfDate(date));
                    }
                }
		if (pd==null) {
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Cannot get collected data: No polledData matching key ")+key , Log.INTERMEDIATE_DETAIL);
			return null;
		}
		return constructCollectedData(getData(pd,date));
	}
	/** 
	 *  To get all the data collected for the given polldata for a given date
	 * The return value is the Vector containing long arrays of time and value.
	 * @param  pd  The PolledData Object whose collected statistic is required.
	 * @param  date  The date for which collected statistic is required which
	 * is given as java.util.Date.
	 * @return  The Vector containing the collected statistics. The first
	 * element is the  time array as long[] and the second element is the
	 * values array as long[].Vector is null if no data is collected for the
	 * given key and for the particular date given.
	 */                                                         


	Vector getData(PolledData pd,Date date) throws UserTransactionException,NmsPollException



{
		if (pd==null) return null;

		RemotePoller up = checkForPoller(pd);
		
		if(up != null) 
		{
			try{
				Vector v =  up.getData(pd,date);
				return v;

			}
                        catch(Exception re)
			{
				NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data from the poller")+re,re);
				return null;
			}
		}   

		try
		{
			if (pd instanceof MultiplePolledData) 
				return comapi.getData(pd.getId(),((MultiplePolledData)pd).currentIndex,date, pd.getStatsDataTableName());
			return comapi.getData(pd.getId(),null, date, pd.getStatsDataTableName());
		}

                catch(UserTransactionException e1){throw e1;}
                catch(Exception e3)
                {
                         NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data for the poll ")+ pd.getKey()+" " +e3.getMessage(), e3);
                        throw new NmsPollException(e3.getMessage(), e3);
                }








	}
	

        Vector getData(String index, PolledData pd,Date date) throws UserTransactionException,NmsPollException



        {
		if(pd == null) return null;
		RemotePoller up = checkForPoller(pd);
		if(up != null) 
		{
			try{
				Vector v =  up.getData(index,pd,date);
				return v;

			}catch(Exception re)
			{
				NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data from the poller")+re,re);
				return null;
			}
		}   

		try
		{
			if(pd instanceof MultiplePolledData)
				return comapi.getData(pd.getId(), index, date, pd.getStatsDataTableName());
			return comapi.getData(pd.getId(), null, date, pd.getStatsDataTableName());
		}	

                catch(UserTransactionException e1){throw e1;}
                catch(Exception e3)
                {
                         NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data for the poll ")+ pd.getKey()+" " +e3.getMessage(), e3);
                        throw new NmsPollException(e3.getMessage(), e3);
                }









	}


        Vector getData (String index,PolledData pd, long timeStart) throws
UserTransactionException,NmsPollException



	{
		if (pd==null) return null;
		RemotePoller up = checkForPoller(pd);
		if(up != null) 
		{
			try{
				Vector v =  up.getData(index,pd,timeStart);
				return v;

			}catch(Exception re)
			{
				NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data from the poller")+re,re);
				return null;
			}
		}   

		try
		{
			if (pd instanceof MultiplePolledData) 
				return comapi.getData(pd.getId(),index,timeStart,(new Date()).getTime(), pd.getStatsDataTableName());
			return comapi.getData(pd.getId(),null,timeStart,(new Date()).getTime(), pd.getStatsDataTableName());
		}

                catch(UserTransactionException e1){throw e1;}
                catch(Exception e3)
                {
                         NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data for the poll ")+ pd.getKey()+" " +e3.getMessage(), e3);
                        throw new NmsPollException(e3.getMessage(), e3);
                }








		
	}


  Vector getData(String index, PolledData pd, long timeStart, long timeEnd)
throws UserTransactionException, NmsPollException



{
		if(pd==null) return null;
		RemotePoller up = checkForPoller(pd);
		if(up != null) 
		{
			try{
				Vector v =  up.getData(index,pd,timeStart,timeEnd);
				return v;

			}catch(Exception re)
			{
				NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data from the poller")+re,re);
				return null;
			}
		}   

		try
		{
			if (pd instanceof MultiplePolledData)
				return comapi.getData(pd.getId(),index,timeStart,timeEnd,pd.getStatsDataTableName());
			return comapi.getData(pd.getId(),null,timeStart,timeEnd,pd.getStatsDataTableName());
		}

                catch(UserTransactionException e1){throw e1;}
                catch(Exception e3)
                {
                         NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception getting the data for the poll ")+ pd.getKey()+" " +e3.getMessage(), e3);
                        throw new NmsPollException(e3.getMessage(), e3);
                }







	}
	
	/**
	 * Sets the class implementing the CustomDataCollection Interface which is
	 * passed as a parameter to the collector to the RunPoll.
	 * CustomDataCollection has a method which checks whether the connection with 
	 * the agent is established or not,based on the return value data is
	 * collected for that agent.
	 * @param che  Instance of class implementing CustomDataCollection interface.
	 */
	void setClassNameInRunPoll(CustomDataCollection che)
	{
		RunPoll.checkForConnection = che;
	}

		
		
	/**
	 * To get the PolledData objects for the agent name given.
	 * @param agent Name of the agent as <code>String</code>.
	 * @return Vector containing PolledDatas for that agent.
	 */

        private Vector getPdsForAgent(String agent) throws
UserTransactionException, NmsPollException



	{
		Vector retVect = new Vector();
		String key="";//No Internationalisation
		PolledData pd = null;
		if (agent != null)
		{
			Vector v =  getPollsForAgent(agent);
			for(int k=0;k<v.size();k++)
			{
				key=(String)v.elementAt(k);
				try
				{
					pd = (PolledData)comapi.getObject(key);
				}

                                catch(UserTransactionException e3)
                                {
                                    throw e3;
                                }
                                catch(NmsStorageException e2)
                                {
                                    throw new NmsPollException(e2.getMessage(),e2);
                                }







				if(pd != null)
					retVect.addElement(pd);
			}
		}
		return retVect;
	}
       /**
	 * Returns a Vector of all the PolledData objects configured for Node with the 
	 * given IPAddresss.
	 * @param ipaddr IPAddress of the node as String.
	 * @return Vector containing PolledData objects.
	 * @exception RemoteException if an error occurs during remote call
	 */ 

        public Vector getPolledDataForNode(String ipaddr) throws
UserTransactionException, NmsPollException



	{
		String agent = "";//No Internationalisation
		try
		{
            agent = com.adventnet.nms.util.WatchUtil.getDNSName(ipaddr).toString().trim();
		}catch(Exception he) {
	  		NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Problem in getting the agent name of the ip  : ")+ ipaddr,he);
		}	
		return getPdsForAgent(agent);
	}


       /**
	 * Returns a Vector of PolledData objects for all the nodes whose IPAddress matches
	 * the IPAddress in the given list.
	 * @param ipaddr  IPAddresses as String[] array.
	 * @return  Vector containing PolledData objects of all the nodes whose IP 
	 * matches with the one in the list.
	 * @exception RemoteException if an error occurs during remote call
	 */

    public Vector getPolledDataForNodes(String[] ipaddr) throws
    UserTransactionException, NmsPollException



	{
		Vector retVect = new Vector();
		String ip = "";//No Internationalisation
		if((ipaddr != null) && (ipaddr.length > 0))
		{
			for(int i=0;i<ipaddr.length;i++)
			{
				Vector v = new Vector();
				ip = ipaddr[i];

                        try{
				v = getPolledDataForNode(ip);
                        }
                        catch(NmsPollException e2)
                        {
                                continue;
                        }



				for(int j=0;j<v.size();j++)
				{
					retVect.addElement(v.elementAt(j));
				}
			}
		}
		return retVect;
	}

	private long getNext(long currentIpAddress,long netMask)
	{
		long networkAddress;
		long localAddress;
		long nextAddress;

		networkAddress      = currentIpAddress & netMask;
		localAddress        = currentIpAddress - networkAddress;
		nextAddress         = (networkAddress) | (localAddress+1);

		return nextAddress;
	} // end of getNext


       /**
	 * Returns a Vector of all the PolledData objects of the nodes whose IPAddress falls 
	 * between startip and endip.
	 * <code>The starting and ending IPAddress must belong to same 
	 * network.</code>
	 * @param startip The starting ip address as String.
	 * @param endip   The ending ip address as String.
	 * @param netmask The netmask as String.
	 * @return Vector containing PolledData objects. 
	 * @exception RemoteException if an error occurs during remote call
	 */

    public Vector getPolledDataForNodes(String startip,String endip,String
                                        netmask)  throws
UserTransactionException, NmsPollException




	{
		Vector retVect = new Vector();
		Vector addrVect = new Vector();
		
		String netAddr = "";//No Internationalisation
		
		long str_ip = com.adventnet.nms.util.WatchUtil.getAddrLong(startip);
		long end_ip = com.adventnet.nms.util.WatchUtil.getAddrLong(endip);
		long net_mask = com.adventnet.nms.util.WatchUtil.getAddrLong(netmask);

                if(net_mask == 0) 
                {
                    NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Invalid netmask "+
" given ") + netmask ,Log.VERBOSE);

                    throw new NmsPollException("Invalid netmask given"+
                                               " :"+netmask, null) ;



                }
		if((str_ip <= 0) || (end_ip <= 0) || (end_ip < str_ip))
		{
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Unable to fetch the PolledData for the range of IpAddres :  ")+ startip + "  " + endip
                        +NmsUtil.GetString(" :Invalid range"),Log.VERBOSE);

                        throw new NmsPollException("starting and ending "+
" ipaddresses are not in the same network ",null);



		}


		netAddr = com.adventnet.nms.util.WatchUtil.getNetAddr(startip,netmask);

		if(!com.adventnet.nms.util.WatchUtil.inNet(endip,netAddr,netmask))
		{
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Unable to fetch the PolledData for the range of IpAddres :  ")+ startip + "  " + endip
                      +"Starting and ending IpAddress are not in the same network ",Log.VERBOSE);

                        throw new NmsPollException("starting and ending "+
" ipaddresses are not in the same network ",null);



		}
		
		long temp_ip = str_ip;
		String temp_str = "";//No Internationalisation

		addrVect.addElement(startip);
		
		while(true)
		{
			temp_ip = getNext(temp_ip,net_mask);
			if ((temp_ip <= end_ip) && (temp_ip >= str_ip))
			{
			    temp_str = com.adventnet.nms.util.WatchUtil.convertAddr(temp_ip);
				addrVect.addElement(temp_str);
			}	
			else
				break;
		}
		int len = addrVect.size();
		String[] strArr = new String[len];

		for(int l=0;l<len;l++)
		{
			strArr[l] = (String)addrVect.elementAt(l);
		}	
		return getPolledDataForNodes(strArr);
	}

       /**
	 * Returns a Vector of PolledData objects for nodes that belong to the groupname
	 * given.
	 * <code>This group name is different from the groupname in the PolledData.
	 * Here group refers to the group of the Node in which it belongs.</code>
	 * @param  group group name as String.
	 * @return Vector containing PolledData objects.
	 * @exception RemoteException if an error occurs during remote call
	 */

    public Vector getPolledDataForGroup(String group) throws NmsPollException,UserTransactionException



        
	{
		Vector retVect = new Vector();
		if(group == null)
			return retVect;
		Vector agents = new Vector();
		Vector tempVect = new Vector();
		String agentname = "";//No Internationalisation

		try 
		{
			agents = PollUtil.getPollToTopoIfc().getMembersOfGroup(group);
		}

            catch(UserTransactionException e1)
                {
                    throw e1;
                }
            catch(NmsPollException e2)
                {
                    throw e2;
                }

                catch(Exception te) {
		NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Unable to getthe objects of the group") + group,te);

                    throw new NmsPollException("Unable to get objects of the "+
" group "+group,te);



		}
		if (agents != null)
		{
			for(int l=0;l<agents.size();l++)
			{
				agentname = (String)agents.elementAt(l);
				if(agentname.startsWith("IF-") == true)//No Internationalisation
				{
					agentname = agentname.substring(3);
				}	
				agentname = com.adventnet.nms.util.WatchUtil.getDNSName(agentname).toString().trim();
				if(!tempVect.contains(agentname))
					tempVect.addElement(agentname);
			}		
			for(int h=0;h<tempVect.size();h++)
			{
				Vector v = new Vector();
				agentname = (String)tempVect.elementAt(h);

                        try{
        			v = getPdsForAgent(agentname);
                        }
                        catch(NmsPollException e2)
                        {
                                continue;
                        }



				for(int k=0;k<v.size();k++)
				{
					retVect.addElement(v.elementAt(k));
				}
			}	
		}
		return retVect;
	}


        private boolean setPolledDataForAgent(String agent,PolledData pd) throws
NmsPollException, UserTransactionException



	{
		PolledData pdata = new PolledData();
		pdata.setProperties(pd.getProperties());
		pdata.setAgent(agent);
		try{
                	modifyPoll(pdata);
		}

                catch(UserTransactionException e1)
                {
                        throw e1;
                }
                catch(NmsPollException e3)
                {
                    throw e3;
                }
                catch(Exception e2)
                {
                        throw new NmsPollException(e2.getMessage(),e2);
                }                                                     







		return true;
	}
	
	/**
	 * Modifies the PolledData of the node with the specified IpAddress with the PolledData passed as argument.
	 * When the key of the PolledData passed matches with the PolledData
	 * that PolledData is modified else,a new PolledData is added .
	 * @param ipaddr IPAddress of the node for which the PolledData is
	 *              to be modified.
	 * @param pd The PolledData to be assigned.
	 * @return boolean representing the status of the operation.
	 */

    public boolean setPolledDataForNode(String ipaddr,PolledData pd) throws
    NmsPollException,UserTransactionException



	{
            	if(ipaddr == null) return false;
                String agent = PollUtil.getPollToTopoIfc().getHostName(ipaddr);
                if(agent == null )
                {

                    throw new NmsPollException("Could not retrieve ManagedObject",null);



                }
		return setPolledDataForAgent(agent,pd);
	}
  
       /**
	 * Replaces all the PolledData objects to all the nodes that belongs 
	 * to the group with the pd passed.
	 * @param group The group name.
	 * @param pd the PolledData with which the modification has to be made.
	 * @return boolean representing the status of the operation.Returns false
	 * even if the operation is unable to assign the PolledData to any one of
	 * the node of the group.
	 * @exception RemoteException if an error occurs during remote call
	 */

    public boolean setPolledDataForGroup(String groupname,PolledData pd) throws
    NmsPollException,UserTransactionException




	{
		if(groupname == null)
			return false;
		Vector agents = new Vector();
		String agentname = "";//No Internationalisation
		boolean retval = true;
		Vector tempVect = new Vector();

		try 
		{
			agents = PollUtil.getPollToTopoIfc().getMembersOfGroup(groupname);
		}

            catch(UserTransactionException e1)
                {
                    throw e1;
                }
            catch(NmsPollException e2)
                {
                    throw e2;
                }

                catch(Exception te) {
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Unable to get the objects of the group") + groupname,te);

                        throw new NmsPollException("Unable to get objects of the group "+groupname,te);



		}
		if (agents != null)
		{
			for(int l=0;l<agents.size();l++)
			{
				agentname = (String)agents.elementAt(l);
				if(agentname.startsWith("IF-") == true)//No Internationalisation
				{
					agentname = agentname.toLowerCase().substring(3);
				}
				agentname = com.adventnet.nms.util.WatchUtil.getDNSName(agentname).toString().trim();
				if(!tempVect.contains(agentname))
					tempVect.addElement(agentname);
			}

			for(int h=0;h<tempVect.size();h++)
			{
				agentname = (String)tempVect.elementAt(h);

                        try{
				if(!setPolledDataForAgent(agentname,pd))
				{
                                    NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Unable to set the PolledData  ")+ pd.getKey() +NmsUtil.GetString("  for the agent  :") +agentname,Log.VERBOSE);
					retval = false;
				}
                        }
                        catch(UserTransactionException e1)
                        {
                                throw e1;
                        }
                        catch(NmsPollException e2)
                        {
                                continue;
                        }







			}	
		}
		return retval;
	}

       /**
	 * Replaces all the PolledData objects of all the nodes whose IpAddress 
	 * matches the IpAddress in the given list, with the pd passed.
	 * @param ipaddrlist the list of IpAddress for which the PolledData is to 
	 * be assigned.
	 * @param pd The PolledData with which the modification has to be made.
	 * @return boolean representing the status of the operation.  Returns false
	 * even if the operation is unable to assign the PolledData to any one of
	 * the IpAddress in the give list
	 * @exception RemoteException if an error occurs during remote call
	 */

        public boolean setPolledDataForNodes(String[] ipaddr,PolledData pd)
            throws UserTransactionException, NmsPollException



	{
		String ip = "";//No Internationalisation
		String agent = "";//No Internationalisation
		boolean retval = true;
		

		if((ipaddr != null) && (ipaddr.length > 0))
		{
			for(int i=0;i<ipaddr.length;i++)
			{
				ip = ipaddr[i];

                        try{
				if(!setPolledDataForNode(ip,pd))
				{
					NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Unable to set the PolledData  ")+ pd.getKey() +NmsUtil.GetString("  for the  IpAddress :") + ip,Log.VERBOSE);
					retval = false;
				}
                        }
                        catch(UserTransactionException e2)
                        {
                                throw e2;
                        }
                        catch(NmsPollException e3)
                        {
                                continue;
                        }







			}
		}
                else
                {
                   retval=false;  
                }

		return retval;
	}
        /**
	 * Replaces all the PolledData objects of all the Nodes whose IpAddres 
	 * falls between startip and endip with the pd passed.
	 * <code>The starting and ending IPAddress must belong to same 
	 * network.</code>
	 * @param startip The starting ip address.
	 * @param endip   The ending ip address.
	 * @param netmask The netmask.
	 * @param pd the PolledData with which the modification has to be made.
	 * @return boolean representing the status of the operation.  Returns false
	 * even if the operation is unable to assign the PolledData to any one of
	 * the IpAddress in the give range.
	 * @exception RemoteException if an error occurs during remote call
	 */

	public boolean setPolledDataForNodes(String startip,String endip,String
                                             netmask,PolledData pd) throws
                                             NmsPollException,UserTransactionException





	{
		String netAddr = "";//No Internationalisation
		Vector addrVect = new Vector(); 

		long str_ip = com.adventnet.nms.util.WatchUtil.getAddrLong(startip);
		long end_ip = com.adventnet.nms.util.WatchUtil.getAddrLong(endip);
		long net_mask = com.adventnet.nms.util.WatchUtil.getAddrLong(netmask);
                if(net_mask == 0) 
                {
                    NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Invalid netmask given ") + netmask ,Log.VERBOSE);

                    throw new NmsPollException("Invalid netmask given"+
                                            " :"+netmask, null) ;



                }

		if((str_ip <= 0) || (end_ip <= 0) || (end_ip < str_ip))
		{
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Unable to set the PolledData for the range of IpAddres :  ")+ startip + "  " + endip
                         +NmsUtil.GetString(" :Invalid range"),Log.VERBOSE);

                        throw new NmsPollException("starting and ending"+
 " ipaddresses are not in the same network ",null);



		}

		netAddr = com.adventnet.nms.util.WatchUtil.getNetAddr(startip,netmask);

		if(!com.adventnet.nms.util.WatchUtil.inNet(endip,netAddr,netmask))
		{
		NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Unable to set the PolledData for the range of IpAddres :  ")+ startip + "  " + endip
+NmsUtil.GetString(" : Starting and ending IpAddress are not in the same network "),Log.VERBOSE);

                        throw new NmsPollException("starting and ending "+
" ipaddresses are not in the same network ",null);



		}
		
		long temp_ip = str_ip;
		String temp_str = "";//No Internationalisation

		addrVect.addElement(startip);
		
		while(true)
		{
			temp_ip = getNext(temp_ip,net_mask);
			if ((temp_ip <= end_ip) && (temp_ip >= str_ip))
			{
			    temp_str = com.adventnet.nms.util.WatchUtil.convertAddr(temp_ip);
				addrVect.addElement(temp_str);
			}	
			else
				break;
		}
		int len = addrVect.size();
		String[] strArr = new String[len];

		for(int l=0;l<len;l++)
		{
			strArr[l] = (String)addrVect.elementAt(l);
		}	
		return setPolledDataForNodes(strArr,pd);

	}


        private boolean removePolledDataForAgent(String agent,String key) throws
NmsPollException, UserTransactionException



	{
		boolean retval = true;
		
		if(key == null) return false;
		StringTokenizer st = new StringTokenizer(key,"\t");//No Internationalisation
		
		int numTokens = st.countTokens();
		if((numTokens != 3) && (numTokens != 4))
			return false;
		
		String name  = st.nextToken();
		//for agent name
		st.nextToken();
		String oid   = st.nextToken();
		String newkey = "";//No Internationalisation
		
		if(numTokens == 3)
		  newkey = name+"\t"+agent.trim()+"\t"+oid;//No Internationalisation
		else if(numTokens == 4) 
		{
		  String ownerName = st.nextToken();
		  newkey = name+"\t"+agent.trim()+"\t"+oid+"\t"+ownerName;//No Internationalisation
		}  
		
		try {
			retval = removePoll(newkey);
		}

                catch(UserTransactionException e1)
                {
                        throw e1;
                }
                catch(Exception e2)
                {
                      throw new NmsPollException(e2.getMessage(),e2);  
                }







		return retval;
	}

           
       /**
	 * Removes the PolledData of the node with the given IpAddress whose key 
	 * matches with the key passed.
	 * @param  ipaddr the IpAddress for which the PolledData is to be removed.
	 * @param  key  the key of the PolledData to be removed.
	 * @return boolean representing the status of the operation.
	 * @exception RemoteException if an error occurs during remote call
	 */


        public boolean removePolledDataForNode(String ipaddr,String key) throws
NmsPollException, UserTransactionException



	{
		String agent="";//No Internationalisation
		try
		{
			agent = com.adventnet.nms.util.WatchUtil.getDNSName(ipaddr).toString().trim();
			
		}catch(Exception he) {
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Problem in getting the agent name of the ip  : ")+ ipaddr,he);
		}	
		return removePolledDataForAgent(agent,key);
	}
	
	/**
	 * Removes the PolledData for each and every node that belongs to the group.
	 * @param groupname the group name.
	 * @param key the key of the PolledData that is to be removed.
	 * @return boolean representing the status of the operation.  Returns false
	 * if the operation is unable to remove the PolledData for any one of
	 * the node of the group.
	 */               

	public boolean removePolledDataForGroup(String groupname,String key)
throws NmsPollException , UserTransactionException



	{
		Vector agents = new Vector();
		String agentname = "";//No Internationalisation
		boolean retval = true;
		Vector tempVect = new Vector();
		try 
		{
                    agents = PollUtil.getPollToTopoIfc().getMembersOfGroup(groupname);
		}

            catch(UserTransactionException e1)
                {
                    throw e1;
                }
            catch(NmsPollException e2)
                {
                    throw e2;
                }

                catch(Exception te) {
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Unable to get the objects of the group") + groupname,te);


                        throw new NmsPollException(te.getMessage(),te);
                        



		}	
		if (agents != null)
		{
			for(int l=0;l<agents.size();l++)
			{
				agentname = (String)agents.elementAt(l);
				if(agentname.startsWith("IF-") == true)//No Internationalisation
				{
					agentname = agentname.toLowerCase().substring(3);
				}	
				agentname = com.adventnet.nms.util.WatchUtil.getDNSName(agentname).toString().trim();

				if(!tempVect.contains(agentname))
					tempVect.addElement(agentname);
			}

			for(int h=0;h<tempVect.size();h++)
			{
				agentname=(String)tempVect.elementAt(h);

                        try{
				if(!removePolledDataForAgent(agentname,key))
				{
					NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Unable to remove the PolledData  ")+ key + NmsUtil.GetString("  for the  agent :") + agentname,Log.VERBOSE);
					retval = false;
				}
                        }
                        catch(UserTransactionException e2)
                        {
                                throw e2;
                        }
                        catch(NmsPollException e3)
                        {
                                continue;
                        }







			}	
		}
		return retval;
	}

	/**
	 * Removes the PolledData for all the nodes whose IpAddress matches the
	 * IpAddress in the given list.
	 * @param ipaddr list containig the IpAddress for which the PolledData is to be removed.
	 * @param key the key of the PolledData to be removed.
	 * @return boolean representing the status of the operation.  Returns false
	 * if the operation is unable to remove the PolledData of any one of
	 * the IpAddress in the list.
	 * 
         */

        public boolean removePolledDataForNodes(String[] ipaddr,String key)
            throws UserTransactionException, NmsPollException



	{
		String ip = "";//No Internationalisation
		boolean retval = true;
		if((ipaddr != null) && (ipaddr.length > 0))
		{
			for(int i=0;i<ipaddr.length;i++)
			{
				ip = ipaddr[i];

                        try{
				if(!removePolledDataForNode(ip,key))
				{
					NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Unable to remove the PolledData  ")+ key +NmsUtil.GetString("  for the  IpAddress :") + ip,Log.VERBOSE);
					retval = false;
				}
                        }
                        catch(UserTransactionException e2)
                        {
                                throw e2;
                        }
                        catch(NmsPollException e3)
                        {
                                continue;
                        }
                        







			}
		}
                else
                {
                   retval =false;
                } 
		return retval;
	}

	/**
	 * Removes the PolledData for all the nodes whose IpAddress falls between
	 * startip and end ip.
	 * The starting and ending IPAddress must belong to same network.
	 * @param startip The starting ip address.
	 * @param endip   The ending ip address.
	 * @param netmask The netmask.
	 * @param key the key of the PolledData to be removed.
	 * @return boolean representing the status of the operation.  Returns false
	 * even if the operation is unable to remove the PolledData of any one of
	 * the IpAddress in the range.
	 */

    public boolean removePolledDataForNodes(String startip,String endip,String
                                            netmask,String key) throws
                                            NmsPollException,UserTransactionException




	{
		String netAddr = "";//No Internationalisation
		Vector addrVect = new Vector(); 
		
		long str_ip = com.adventnet.nms.util.WatchUtil.getAddrLong(startip);
		long end_ip = com.adventnet.nms.util.WatchUtil.getAddrLong(endip);
		long net_mask = com.adventnet.nms.util.WatchUtil.getAddrLong(netmask);
                if(net_mask == 0) 
                {
                    NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Invalid netmask given ") + netmask ,Log.VERBOSE);

                    throw new NmsPollException("Invalid netmask given"+
                                            " :"+netmask, null) ;



                }

		if((str_ip <= 0) || (end_ip <= 0) || (end_ip < str_ip))
		{
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Unable to remove the PolledData for the range of IpAddres :  ")+ startip + "  " + endip
              +NmsUtil.GetString(" :Invalid  range"),Log.VERBOSE);

                        throw new NmsPollException("Invalid ipaddress range "
                                                   +startip +" "+endip,null);



		}

		netAddr = com.adventnet.nms.util.WatchUtil.getNetAddr(startip,netmask);

		if(!com.adventnet.nms.util.WatchUtil.inNet(endip,netAddr,netmask))
		{
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Unable to remove the PolledData for the range of IpAddres :  ")+ startip + "  " + endip +NmsUtil.GetString(" : Starting and ending IpAddress are not in the same network "),Log.VERBOSE);

                        throw new NmsPollException("starting and ending "+
" ipaddresses are not in the same network ",null);



		}
		
		long temp_ip = str_ip;
		String temp_str = "";//No Internationalisation

		addrVect.addElement(startip);
		
		while(true)
		{
			temp_ip = getNext(temp_ip,net_mask);
			if ((temp_ip <= end_ip) && (temp_ip >= str_ip))
			{
			    temp_str = com.adventnet.nms.util.WatchUtil.convertAddr(temp_ip);
				addrVect.addElement(temp_str);
			}
			else
				break;
		}
		int len = addrVect.size();
		String[] strArr = new String[len];
		for(int l=0;l<len;l++)
		{
			strArr[l] = (String)addrVect.elementAt(l);
		}	
		return removePolledDataForNodes(strArr,key);
	}
	
	/**
	 * Checks whether all the nodes in the group has PolledData with same
	 * name and oid.
	 * @param groupname the group name.
	 * @param key The key of the PolledData that is to be checked for.
	 * @return boolean representing the status of the operation.
	 */

    public boolean getPolledDataConsistent(String groupname, String key) throws
    NmsPollException, UserTransactionException



	{
		String agentname = "";//No Internationalisation
		Vector agents;
		Vector tempVect = new Vector();

		try 
		{
			agents = PollUtil.getPollToTopoIfc().getMembersOfGroup(groupname);
		}

            catch(UserTransactionException e1)
                {
                    throw e1;
                }
            catch(NmsPollException e2)
                {
                    throw e2;
                }

                catch(Exception te) {
			NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Unable to get the objects of the group") + groupname,te);

                        throw new NmsPollException("Unable to get objects of the group "+groupname,te);



		}	
		if (agents != null)
		{
			for(int l=0;l<agents.size();l++)
			{
				agentname = (String)agents.elementAt(l);
				if(agentname.startsWith("IF-") == true)//No Internationalisation
				{
					agentname = agentname.toLowerCase().substring(3);
				}	
				agentname = com.adventnet.nms.util.WatchUtil.getDNSName(agentname).toString().trim();
				
				if(!tempVect.contains(agentname))
					tempVect.addElement(agentname);
			}

			for(int h=0;h<tempVect.size();h++)
			{
				agentname = (String)tempVect.elementAt(h);

				if(!checkPolledDataForAgent(agentname,key))
				{
					NmsLogMgr.POLLUSER.log(NmsUtil.GetString("PolledData  ")+ key +NmsUtil.GetString("  is not  consistent for the  group :  agent ") + agentname +NmsUtil.GetString(" is an example"),Log.VERBOSE);
					return false;
				}	

			}	
		}
		return true;
	}


        private boolean checkPolledDataForAgent(String agentname,String key)
throws NmsPollException, UserTransactionException



	{
		if(key == null) return false;
		StringTokenizer st = new StringTokenizer(key,"\t");//No Internationalisation
		int numTokens = st.countTokens();
		if((numTokens != 3) && (numTokens != 4))
			return false;
		String name  = st.nextToken();
		st.nextToken();
		String oid   = st.nextToken();
		String newkey = "";//No Internationalisation
		if(numTokens == 3)
		    newkey = name+"\t"+agentname.trim()+"\t"+oid;//No Internationalisation
		else if(numTokens == 4)
		{
			String ownerName = st.nextToken();
		  	newkey = name+"\t"+agentname.trim()+"\t"+oid+"\t"+ownerName;//No Internationalisation
		}

                try{
		if(pdatas.contains(newkey))
			return true;
		else
			return false;
                }
                catch(NmsStorageException e1)
                {
                        throw new NmsPollException(e1.getMessage(),e1);
                }






	}
	
	/**
	 * Starts the data collection.
	 * @return boolean true if the Datacollection is started.
	 */       
	public boolean startDataPoll()
	{
	    try
		{
			addAll();
                        startScheduler();
		}
		catch(Exception e)
		{
			 NmsLogMgr.POLLERR.fail(NmsUtil.GetString("Exception in startDataPoll") ,e ); 
			 return false;
		}	
		return true;
	}
	
	/**
	 * Stops the data collection.
	 * @return boolean true if the Datacollection is stopped.
	 */         
	public boolean stopDataPoll()
	{
	  	return RunPoll.stopScheduler();
	}
	
       /**
         * Returns a Vector of names of all the active pollers at that time.
         * @return Vector containing names of ActivePollers as String.
         * @exception RemoteException if an error occurs during remote call
         */
	public Vector getActivePollers()
	{
		Vector retVect = new Vector();
		
		int len = Collector.pollers.size();
		
		String name[] = new String[len];
		String hostname[] = new String[len];
		String s="";//No Internationalisation

		RemotePoller rp = null;
		int i=0;

		for(Enumeration en=Collector.pollers.keys();en.hasMoreElements();)
		{
			s=(String)en.nextElement();
			rp = (RemotePoller)Collector.pollers.get(s);
			try{
				name[i] = s;
				hostname[i] = rp.getHostName();
			}catch(RemoteException e)
			{
				Collector.pollers.remove(s);
				continue;
			}
			i++;
		}
		retVect.addElement(name);
		retVect.addElement(hostname);
		return retVect;
	}
	
	/**
	 * Returns the status of the poller identified by the name  given as
	 * argument.
	 * @param pollerName as <code>String</code>.
	 * @return boolean true if the poller is active or otherwise.
	 */            
	 public boolean isPollerActive(String pollerName)
	 {
	 	String s="";//No Internationalisation
		if((pollerName == null) || (pollerName.length() <= 0)) return false;
			
		for(Enumeration en=Collector.pollers.keys();en.hasMoreElements();)
		{
			s=(String)en.nextElement();
			
			if(s.equals(pollerName))
				return true;
		}
		return false;
	 }

	 /**
	  * ShutsDown the poller with the name passed as a parameter.
	  * @param pollerName as <code>String</code>.
	  */          
	 public void shutDownPoller(String pollerName)
	 {
	 	String s="";//No Internationalisation
		RemotePoller rp = null;

		if((pollerName == null) || (pollerName.length() <= 0)) return;
			
		for(Enumeration en=Collector.pollers.keys();en.hasMoreElements();)
		{
			s=(String)en.nextElement();
			rp = (RemotePoller)Collector.pollers.get(s);
			try{
				if(s.equals(pollerName))
				{
					rp.shutDown();
					Collector.pollers.remove(s);
					NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Poller  ") + pollerName + NmsUtil.GetString("  Shutdown successfully"),Log.VERBOSE);
					break;
				}
			}catch(RemoteException e)
			{
				Collector.pollers.remove(s);
				continue;
			}
		}
		return;
	 }
	 
	 /**
	  *ShutsDown all the pollers which are active currently.
	  */       
	 public void shutDownAllPollers()
	 {
		RemotePoller rp = null;
		String s="";//No Internationalisation

		for(Enumeration en=Collector.pollers.keys();en.hasMoreElements();)
		{
			s=(String)en.nextElement();
			rp = (RemotePoller)Collector.pollers.get(s);
			try{
				 rp.shutDown();
 				 NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Poller  ") + s + NmsUtil.GetString("  Shutdown successfully"),Log.VERBOSE);
			}catch(RemoteException e){}
			Collector.pollers.remove(s);
		}
	 }
	 
	 /**
	  * Returns all the available ThresholdObjects in the Polling Engine.
	  * @return the Vector containing ThresholdObjects.
	  */
	public Vector getAllThresholdObjects()
	{
		return initializer.getAllThresholdObjects();
	}

	/*
	 * Adds a ThresholdObject in to the system.
	 * This threshold object can be associated with any data collection
	 * variable to check thresholds.
	 * @param obj ThresholdObject which has to be added.
	 * @return boolean representing the status of the operation.
	 */

	public boolean addThresholdObject(ThresholdObject obj) throws
UserTransactionException, NmsPollException



	{
            if(initializer.checkAndAddThresholdObject(obj.getProperties(),true))
		{
 			 NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Threshold Object ") + obj.getName() +NmsUtil.GetString("  added successfully"),Log.VERBOSE);
			 return true;
		}
		else
		{
 			 NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Problem in adding the Threshold Object ") + obj.getName(),Log.VERBOSE);
			 return false;
		}
	}

	/*
	 * Modifies the ThresholdObject which has been added already.
	 * That is changes the properties of the ThresholdObject already
	 * present in the system to that of the one passed as argument.
	 * @param obj ThresholdObject with the properties to be modified.
	 * @return boolean to represent the status of the operation.
	 */

        public boolean modifyThresholdObject(ThresholdObject obj) throws
UserTransactionException , NmsPollException



	{
		return initializer.modifyThresholdObject(obj);
	}
	
	/*
	 * Deletes the ThresholdObject with the name <code> name </code>
	 * from the system.
	 * @param name Name of the thresholdObject to be deleted.
	 * @return boolean representing the status of the operation.
	 */

	 public boolean deleteThresholdObject(String name) throws
UserTransactionException, NmsPollException



	{
		return initializer.deleteThresholdObject(name);
	}
    


    /*
     * Adds a new PollingObject to the system. This method takes a PollingObject and a boolean
     * specifying whether to apply this Polling Object to already existing ManagedObjects or not.
     * If this boolean is false ,then this Polling Object will be applicable only to Managed
     * Objects which get discovered later(after addition of this PollingObject).
     * if it is true ,polling will be configured for an already discovered ManagedObject if ,
     * <ul>
     * <li>polling for that MO has not been configured already under existing Polling Objects and 
     * <li>if it satisfies this PollingObject's match criteria.
     * </ul>
     * Since it is a very costly process
     * it is not advisible to use this option unless it is absolutely necessary.
     * @param po PollingObject to be added.
     * @param updateOldMOs a <code>boolean</code> value specifying whether this Polling Object
     * has to be applied to already discovered ManagedObjects or not.
     * @exception NmsPollException if an error occurs while adding PollingObject.
     */    


    public void addPollingObject(PollingObject po,boolean updateOldMOs) throws UserTransactionException,NmsPollException




	 {
             try{
		 initializer.addPollingObject(po,updateOldMOs,true);
                 notifyPollingObject(PollConstants.ADD_POLLING_OBJECT,po);
             }

        catch(UserTransactionException e1)
        {
                throw e1;
        }

             catch(NmsPollException pollex)
             {
                throw pollex;

             }
             catch(Exception exp)
             {

		 throw new NmsPollException(exp.getMessage(),exp);

             }
	 }
	


    /*
     * Deletes the PollingObject with name <code> name </code> 
     * from NMS
     * @param name  name of the PollingObject
     * @exception NmsPollException if an error occurs while deleting PollingObject.
     */

	 public void deletePollingObject(String name) throws UserTransactionException, NmsPollException



	 {
             try{
                 getAndNotifyPollingObject(PollConstants.DELETE_POLLING_OBJECT,name);
		 initializer.deletePollingObject(name);
                 pdCount = pdatas.size();
                 CvcMaintainer.update("poll","PollingObject",name,null);//No Internationalisation
             }

        catch(UserTransactionException e1)
        {
                throw e1;
        }   

             catch(NmsPollException pollex)
             {
                 throw pollex;
             }
             
             catch(Exception exp)
             {
                 throw new NmsPollException(exp.getMessage(),exp);
             }
	 }
	
    /*
     * Modifies an existing PollingObject.
     * This method takes a PollingObject and a boolean specifying whether to apply the
     * changes(applicable only if new oids are being added)
     * to already existing ManagedObjects or not. Only the data collection details can be
     * modified for a Polling Object. This includes addition of new oids , modification
     * of existing oids(except name , oid string and type) and deletion of existing oids.
     * Modification and deletion of existing oids will have effect on existing PolledDatas.
     * If any new oids are added , if the boolean is false , it will be applicable only
     * to ManagedObjects which get discovered later.On the other hand if it is true, then,
     * old ManagedObjects for which polling has been configured as per this PollingObject
     * will be taken , and the new oids will be added.Since this is a very costly process
     * it is not advisible to use this option unless it is absolutely necessary.
     *
     * @param po Polling Object to be modified.
     * @param updateOldMOs a boolean specifying whether to apply  the changes
     * (if any new oids are added) to already existing ManagedObjects or not.
     * 
     * @exception NmsPollException if an error occurs while modification of PollingObject
     */
    

    public void modifyPollingObject(PollingObject po,boolean updateOldMOs)
throws UserTransactionException,NmsPollException




	 {
             try{
		 initializer.modifyPollingObject(po,updateOldMOs);
                 notifyPollingObject(PollConstants.MODIFY_POLLING_OBJECT,po);
                 pdCount = pdatas.size();
                 CvcMaintainer.update("poll","PollingObject",po.getName(),null);//No Internationalisation
             }
             catch(NmsPollException pollex)
             {
                  throw pollex;
             }

             catch(UserTransactionException e2)
             {
                throw e2;
             }

             catch(Exception exp)
             {
                 throw new NmsPollException(exp.getMessage(),exp);
             }
	 }

    /*
     * modifies properties of a single oid or adds a new oid in the data collection criteria
     * of PollingObject of name <code> name </code>.
     * The key represents the key of the oid ,
     * <b>
     * name of oid + "::" + oid string + "::" + type of oid</b> .//No Internationalisation
     * 
     * <code>prop</code> represents the new properties of the oid and the boolean
     * <code>updateMOs</code> indicates whether to apply changes to existing ManagedObjects
     * or not(applicable only if the oid is being added).
     * <p>
     * If the oid with the oid key is already present in the Polling Object,then all the
     * corresponding PolledDatas  will be modified  as per the new properties.
     * If such an oid key is not present already , if the boolean <code>updateMOs</code>
     * is false,then the new property will be added as a part of data collection criteria
     * and PolledDatas with this new oid will be created for ManagedObjects which get added
     * later .
     * On the other hand , if the the boolean <code>updateMOs</code> is true , new PolledDatas
     * will be created for all the ManagedObjects for which data collection has been configured
     * already under this Polling Object.
     * @param name name of the PollingObject
     * @param prop the new properties of the oid.
     * @param updateMOs a <code>boolean</code> value indicating whether to apply the changes
     * to existing ManagedObjects or not.(applicable only if oid is being added).
     * @exception NmsPollException if an error occurs while modification or additon as the case
     * may be.
     */

    public void modifyPropForPollingObject(String name  ,Properties prop
,boolean updateMOs) throws UserTransactionException,NmsPollException




    {
        try
        {
            initializer.modifyPropForPollingObject(name,prop,updateMOs);
            getAndNotifyPollingObject(PollConstants.MODIFY_POLLING_OBJECT,name);
            CvcMaintainer.update("poll","PollingObject",name,null);//No Internationalisation

        }

        catch(UserTransactionException e1)
        {
                throw e1;
        }

        catch(NmsPollException pollex)
        {
            throw pollex;
        }
        catch(Exception exp)
        {
            throw new NmsPollException(exp.getMessage(),exp);
        }
    }

    /*
     * Deletes a single oid from the PollingObject with name <code>name</code>. The key refers to
     * key of the oid,
     * <b>
     * name of oid + "::" + oid string + "::" + type of oid</b> .//No Internationalisation
     * If such an oid exists , all the corresponding PolledDatas will be deleted.
     * @param name name of the PollingObject
     * @param key key of the oid
     * @exception NmsPollException if an error occurs while deleting the oid.
     */

    public void deletePropForPollingObject(String name,String key) throws
NmsPollException, UserTransactionException




    {
        try
        {
            initializer.deletePropForPollingObject(name,key);
            getAndNotifyPollingObject(PollConstants.MODIFY_POLLING_OBJECT,name);
            pdCount = pdatas.size();
            CvcMaintainer.update("poll","PollingObject",name,null);//No Internationalisation
        }

        catch(UserTransactionException e1)
        {
                throw e1;
        }

        catch(NmsPollException pollex)
        {
            throw pollex;
        }
        
        catch(Exception exp)
        {
            throw new NmsPollException(exp.getMessage(),exp);
        }        
    }
    
   /*
     * Returns the PollingObject with name <code> name </code> 
     * @param name  name of the PollingObject to be retrived.
     * @return The specified PollingObject.
     * @exception NmsPollException if an error occurs while getting
     * PollingObject.
     */
     public PollingObject getPollingObject(String name) throws NmsPollException
     {
         PollingObject po = null;
         try{
             po=initializer.getPollingObject(name);
         }
         catch(NmsPollException pollex)
         {
             throw pollex;
         }
         catch(Exception e)
         {
             throw new NmsPollException(e.getMessage(),e);
         }
         return po;
     } 

   /*
     * Returns all PollingObjects in NMS 
     * @return A Vector contains all the Polling Objects in NMS.
     * @exception NmsPollException if an error occurs while
     * getting PollingObjects.
     */
     public Vector getPollingObjects() throws NmsPollException
     {
         Vector pos= null;
         try{
             
             pos=initializer.getPollingObjects();
         }
         catch(NmsPollException pollex)
         {
             throw pollex;
         }
         catch(Exception e)
         {
             throw new NmsPollException(e.getMessage(),e);
         }
         return pos;
     }


    /*
     * sets status of the PollingObject <code>name</code> to the given value.
     * 
     * @param name name of the PollingObject whose status is to be set.
     * @param status status of PollingObject.
     * @exception NmsPollException if an error occurs while setting status
     */

    public void setPollingObjectStatus(String name , boolean status) throws
UserTransactionException, NmsPollException




    {
        try{
            initializer.setPollingObjectStatus(name , status);
            getAndNotifyPollingObject(PollConstants.SET_STATUS_POLLING_OBJECT,name);
            CvcMaintainer.update("poll","PollingObject",name,null);//No Internationalisation

        }

        catch(UserTransactionException e1)
        {
                throw e1;
        }

        catch(NmsPollException pollex)
        {
            throw pollex;
        }
        catch(Exception e)
        {
            throw new NmsPollException(e.getMessage(),e);
        }
    }
    /**
     * returns the status of the PollingObject <code>name</code>.
     *
     * @param name name of the Polling Object.
     * @return status of the Polling Object.
     * @exception NmsPollException if an error occurs while getting status.
     */
    public boolean getPollingObjectStatus(String name) throws NmsPollException
    {
        boolean status;
        try{
            status = initializer.getPollingObjectStatus(name);
        }
        catch(NmsPollException pollex)
        {
            throw pollex;
        }
        return status;
    }
    
       /**
	 * Sets the debugging to true/false based on which some debugging
	 * messages will be printed during DataCollection.
	 * @param debug true/false.
	 */

           


	public void setDebugMode(boolean debug)
	{
		DataCollectionConstants.DEBUGGING_MODE = debug;
	}

	private void saveData(long time,long longVal, String stringVal,String index, PolledData pd,CollectedInfo cifo) throws Exception
	{

		if(perf_firstTime)
		{
			startTime=System.currentTimeMillis();
			startPeriodicLogTime=startTime;
			perf_firstTime = false;
		}
		counter++;
		periodiccounter++;
		long currenttime=System.currentTimeMillis();
		long elapsedtime=currenttime-startPeriodicLogTime;
		if(elapsedtime>30000)
		{
			startPeriodicLogTime=currenttime;
			
			testuser.log("Data Collection for the last 30 secs :"+(periodiccounter*1000/elapsedtime),3);
			periodiccounter=0;

		}
		if(counter == 1000)
		{
			long difftime=System.currentTimeMillis() - startTime;
			testuser.log("Rate:" + (1000000/difftime),3);
			startTime=System.currentTimeMillis();
			counter=0;
		}
		
        	try
		{
			if ((pd.getSave()) &&((pd.getCurrentSaveCount()+1) == (pd.getSavePollCount())) || ((pd.getSaveOnThreshold()) && (pd.getSavePollCount()== -1)))                 {
	                        String insert=null;
				String nameOfOid=pd.getName();  
				String key=pd.getPolicyName()+nameOfOid;
				insert=(String)insertHash.get(key);
				if ( index == null) index="-1";
				if(insert!=null)
				{
					long pollId=pd.getId();
					String sql=""; 
					int inx=insert.indexOf("values");
					sql=insert.substring(inx);
					sql=sql.substring(sql.indexOf('(')+1,sql.length()-1);
					insert = insert.substring(0,inx);
					StringTokenizer st = new StringTokenizer(sql,","); 
					int i=0;
					int val=0; 
					String checkFor="12345"; 
					String nsql=""; 
					while(st.hasMoreTokens()) 
					{
						i++; 
						String to=st.nextToken();
						if(checkFor.indexOf(to)>-1) 
							val=Integer.parseInt(to);
						else
							val=6;
						switch(val)
						{
							case 1:
								nsql=nsql+pollId;
								break; 
							case 2:
								nsql=nsql+time;
								break; 
							case 3:
								nsql=nsql+longVal;
								break; 
							case 4:
								nsql=nsql+"'"+index+"'";
								break; 
							case 5:
								nsql=nsql+"'"+stringVal+"'";
								break; 
							case 6:
								{
									Object obj=cifo.getValuesForColumn(pd.getKey(),index,to);
									if(obj instanceof Long)
									{ 
										long longValue = ((Long)obj).longValue();
										nsql=nsql+longValue;
									}
									else 
									{
										nsql=nsql+"'"+obj.toString()+"'";
									} 
								}
								break; 
						}
						nsql=nsql+",";

					}
					nsql=nsql.substring(0,nsql.length()-1);
					nsql = "("+nsql +")";
					insert="insert into  <> " +insert + " values "+nsql;
				}
				if(DataCollectionConstants.BULK_INSERT_INTERVAL != 0)
                        {
                    long curtime = System.currentTimeMillis()/1000;
                    if(curtime < updateTime)
                    {
                        if(longVal != -1)
                        {
                            comapi.saveData(time,longVal,pd.getId(),index,pd.getStatsDataTableName(),insert,true);
                        }
                        else if(stringVal != null)
                        {
                            comapi.saveData(time,stringVal,pd.getId(),index,pd.getStatsDataTableName(),insert,true);
                        }
                        ++maxinsert;
                    }
                    if((curtime >= updateTime) || (maxinsert == DataCollectionConstants.MAX_ALLOWABLE_INSERT))                    {
                        comapi.saveBulkData();
                        updateTime = (System.currentTimeMillis()/1000) + DataCollectionConstants.BULK_INSERT_INTERVAL;
                        maxinsert = 0;
                    }
                }
				else
				{
					if(longVal != -1)
					{
						comapi.saveData(time,longVal,pd.getId(),index,pd.getStatsDataTableName(),insert,false);
					}
					else if(stringVal != null)
					{
						comapi.saveData(time,stringVal,pd.getId(),index,pd.getStatsDataTableName(),insert,false);
					}
				}
			}
            else
            {
            	if(DataCollectionConstants.DEBUGGING_MODE)
                {
                	NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Returning without saving data for ")+pd.getAgent()+": "+pd.getOid(),Log.INTERMEDIATE_DETAIL);//No Internationalisation
                }
            }        
		}           
		catch(Exception nse)
		{
                    throw nse;
		}
	}

    	
	private void notifyData(long time,long longVal, String stringVal, PolledData pd)
	{
            PDSpecificNotifier notifier=(PDSpecificNotifier)pollObservers.get(pd.getKey());

            if(notifier !=null)
            {
                if(stringVal!=null)
                    notifier.notify(pd.getKey(),time,stringVal);
                else
                    notifier.notify(pd.getKey(),time,longVal);
            }
	   
	}
	
	private void notifyDataFromAgent(CollectedInfo colinfo,String agent)
	{
            AgentSpecificNotifier notifierForAgent=(AgentSpecificNotifier)agentObservers.get(agent);
            if(notifierForAgent!=null)
                notifierForAgent.notify(colinfo);
	}
	
	
	
	
	long getCount()
	{
		return pdCount;
	}

	/** 
	 * Returns the data collected for the PolledData object whose key matches 
	 * with the key passed, and is collected between the specified time.
	 * This method  requires the start time and end time as <b>long</b>.
	 * @param  key  The key of the PolledData Object whose data are required.
	 * The key for a PolledData pd is
	 * <b>pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid</b>//No Internationalisation
	 * @param  timeStart The starting time from when the collected data 
	 * is required. The time  is given as a long.  
	 * @param  timeEnd  The end time until when the collected data is
	 * required as long.
	 * @return CollectedData containing the collected statistics.
	 * @exception RemoteException if an error occurs during remote call
	 */


public CollectedData getCollectedValues(String key,long timeStart,long
timeEnd) throws UserTransactionException, NmsPollException,RemoteException




	{

		PolledData pd = getPolledData(key);

                if(PollUtil.getDataHandler() != null)
                {
                    if(PollUtil.getDataHandler().canHandleRequest(pd))
                    {
                        return PollUtil.getDataHandler().getCollectedData(pd,"-1",timeStart,timeEnd);
                    }
                }
		if (pd==null) {
		 	NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Cannot get collected data: No polledData matching key ")+key , Log.INTERMEDIATE_DETAIL);
			return null;
		}
		return constructCollectedData(getData(pd,timeStart,timeEnd));
	}

	/** 
	 * Returns the data collected for the given PolledData object whose key 
	 * is given and between the start time specified till the current time .
	 * This method  requires the start time in long.
	 * @param  key  The key of the PolledData Object whose data are required.
	 * The key for a PolledData pd is
	 * <b>pd.name+"\t"+pd.agent.toLowerCase().trim()+"\t"+pd.oid</b>//No Internationalisation
	 * @param  timeStart The starting time from when the collected data 
	 * is required. The time  is given as a long.  
	 * @return CollectedData containing the collected statistics. 
	 * @exception RemoteException if an error occurs during remote call
	 */


	public CollectedData getCollectedValues(String key,long timeStart)
throws UserTransactionException, NmsPollException,RemoteException 




	{
		PolledData pd = getPolledData(key);

                if(PollUtil.getDataHandler() != null)
                {
                    if(PollUtil.getDataHandler().canHandleRequest(pd))
                    {
                        return PollUtil.getDataHandler().getCollectedData(pd,"-1",timeStart,System.currentTimeMillis());
                    }
                }
		if (pd==null) {
			NmsLogMgr.POLLUSER.log(NmsUtil.GetString("Cannot get collected data: No polledData matching key ")+key , Log.INTERMEDIATE_DETAIL);
			return null;
		}
		return constructCollectedData(getData(pd,timeStart));
	}
	
	private CollectedData constructCollectedData(Vector v)
	{
		
		if(v != null)
		{
			try
			{
				return new CollectedData(v);
			}
			catch(Exception e)
			{
				return null;
			}
		}	
		return null;
	}
	
     /**
      * Returns a Hashtable contains ThresholdObjects associated with OIDs.
      * In this Hashtable Threshold Objects are
      * stored against <b>policyName+oidName </b> of the Oid.
      * @return Hashtable containsThresholdObjects
      * @exception RemoteException if an error occurs during remote call
      */

	public Hashtable getThreshHashOfOid()
	{
	  return initializer.getThreshHashOfOid();
	}
   /**
     * Adds a new poll filter to the Polling Engine.
     * PolledDatas which are added will be passed through this filter.
     * @param className name of the Poll Filter
     * @exception RemoteException if an error occurs remote call
     * @exception NmsPollException if an error occurs while adding poll filter
     */
    

    public void addPollFilter(String className) throws UserTransactionException ,NmsPollException



    {
        try{
            initializer.addPollFilter(className);
        }

        catch(UserTransactionException e1)
        {
                throw e1;
        }

        catch(NmsPollException pollex)
        {
            throw pollex;
        }
        catch(Exception e)
        {
            throw new NmsPollException(e.getMessage(),e);
        }
    }

   /**
     * Deletes an existing poll filter from Poll Engine.
     * PolledData that are added will be no more passed through this filter.
     * @param className name of the poll filter
     * @exception RemoteException if an error occurs during remote call
     * @exception NmsPollException if an error occurs while deleting
     */

    public void deletePollFilter(String className) throws
UserTransactionException, NmsPollException



    {
        try{
        initializer.deletePollFilter(className);
        }

        catch(UserTransactionException e1)
        {
                throw e1;
        }

        catch(NmsPollException nmsex)
        {
            throw nmsex;
        }
        catch(Exception e)
        {
            throw new NmsPollException(e.getMessage(),e);
        }        
    }


    /**
     * Adds a new report to the Poll Engine. 
     * The className in the ReportObject must
     * be an implementing call of Reporter interface.If time for report
     * generation is not set in the ReportObject(setTimeVal() method) ,
     * this method takes care of setting up the time depending on the parameters
     * given in ReportObject like whether it is a daily report / weekly
     * report etc.,
     *
     * @param ro The ReportObject to be added.
     * @exception NmsPollException if an error occurs while adding report.
     */    

     public void addReport(ReportObject ro) throws UserTransactionException , NmsPollException



    {
        try{
            Collector.addReport(ro);
        }
        catch(NmsPollException pollex)
        {
            throw pollex;
        }

        catch(UserTransactionException e2)
        {
                throw e2;
        }






    }
    
    /**
     * Deletes an existing report with the class name <code> name </code>.
     *
     * @param name name of the report to be deleted.
     * @exception NmsPollException if an error occurs while deleting report.
     */


    public void deleteReport(String name) throws UserTransactionException, NmsPollException



    {
        try{
            Collector.deleteReport(name);
        }
        catch(NmsPollException pollex)
        {
            throw pollex;
        }

        catch(UserTransactionException e2)
        {
                throw e2;
        }






    }
    
    /**
     * Schedules an existing report to be run at a given time.The
     * <code>timeval</code> given will override the default report
     * generation time.If <code>timeval</code> is < 0 or less than current time,
     * the report will be run immediately.After running the report at the given
     * <code>timeval</code>, the next report generation time
     * will be set depending on the type of the report(daily/weekly etc).
     * 
     *
     * @param name the name of the report to be scheduled.
     * @param timeval the time in milliseconds  at which the report should be run.
     * @exception NmsPollException if an error occurs while scheduling report.
     */    

    public void scheduleReport(String name , long timeval) throws
UserTransactionException, NmsPollException




    {
        try{
            Collector.scheduleReport(name , timeval);
        }

        catch(UserTransactionException e1)
        {
                throw e1;
        }

        catch(NmsPollException pollex)
        {
            throw pollex;
        }
        catch(Exception e)
        {
            throw new NmsPollException(e.getMessage(),e);
        }
        
    }


    void  bulkDelete(String key ,String sqlString,XMLNode propNode) throws
NmsStorageException,NmsPollException , UserTransactionException





    {
        try{
            String retString = comapi.getQuery(key ,sqlString);
            XMLNode node = new XMLNode();
            node.setNodeType(XMLNode.ELEMENT);
            node.setNodeName("BULK_DELETE_INFO");//No Internationalisation
            XMLNode subNode = new XMLNode();
            subNode.setNodeType(XMLNode.ELEMENT);
            subNode.setNodeName("DATA");//No Internationalisation
			subNode.setAttribute("sqlQuery",retString);//No Internationalisation
			if((key != null) || (key.equalsIgnoreCase("NULL")))
			{
				subNode.setAttribute("parentObjKey",key);//No Internationalisation
			}
            node.addChildNode(subNode);
            node.addChildNode(propNode);
            pollUnitNotifier.notify(node);
            comapi.bulkDelete(key,sqlString);
            pdCount = pdatas.size();
            Properties p = new Properties();
            CvcMaintainer.update("poll","BulkDelete",key,p);//No Internationalisation

        }

        catch(UserTransactionException e2)
        {
                throw e2;
        }

        catch(NmsStorageException e)
        {
            throw e;
        }
        catch(Exception e1)
        {
            throw new NmsPollException(NmsUtil.GetString("Error while deleting objects"),e1);
        }
    }

 

    /**
     * Registers for getting the notifications whenever a PolledData is
     * added/modified/deleted.
     * This Observer will be intimated whenever a PolledData is added/modified/deleted
     * @param pdObs a <code>PollUnitObserver</code> Observer which needs
     * to get the notification.
     * @exception NmsPollException if an error occurs during registering.
     */
    public void register(PollUnitObserver pdObs) throws NmsPollException
    {
        if(pdObs == null) 
        {
            throw new NmsPollException(NmsUtil.GetString("Cannot register a null observer"),null);
        }
        pollUnitNotifier.addObserver(pdObs);

    }

    /**
     * Deregisters this observer from getting the notifications whenever 
     * a PolledData is added/modified/deleted.
     * This Observer will be no more intimated when a PolledDat is added/
     * modified/deleted.
     * @param pdObs a <code>PollUnitObserver</code>. Observer that was 
     * getting notifications. 
     * @exception NmsPollException if an error occurs during registering.
     */
    public void deRegister(PollUnitObserver pdObs) throws NmsPollException
    {
        if(pdObs == null)
        {
            throw new NmsPollException(NmsUtil.GetString("Cannot deregister a null observer"),null);
        }
        pollUnitNotifier.deleteObserver(pdObs);

    }

    private void notifyPD(int type, PolledData pd)
    {
        pollUnitNotifier.notify(type,pd);
    }

    /**
     * Adds a new ProtocolProvider class for the protocol provided.
     * This provider  class will be used to poll PolledDatas with the
     * given<code>protocol</code>.
     * @param protocol a <code>String</code> for which provider is added.
     * @param prov a <code>ProtocolProvider</code>. 
     * @exception NmsPollException if an error occurs while adding protocolProvider
     */

    public void addProtocolProvider(String protocol,ProtocolProvider prov)
throws  UserTransactionException, NmsPollException




    {
        initializer.addProvider(protocol,prov);
    }

    /**
     * Deletes the Provider Class defined for this protocol.
     *  This ProtocolProvider will be no more used.
     * @param protocol  <code>String</code> for which provider is deleted.
     * @exception NmsPollException if an error occurs while deleting provider
     */

    public void deleteProtocolProvider(String protocol) throws
UserTransactionException, NmsPollException



    {
        initializer.deleteProvider(protocol);
    }



    private void begin() throws UserTransactionException
    {
        
        try{
            transapi.begin();
        }catch(NotSupportedException e) {}
        catch(Exception e)
        {
            throw new UserTransactionException(e.getMessage(),e);
        }
    }
    
    private void begin(int timeout) throws UserTransactionException
    {
        try{
            transapi.begin(timeout);
        }catch(NotSupportedException e){}
        catch(Exception e)
        {
            throw new UserTransactionException(e.getMessage(),e);
        }
    }

    private void rollback(String str) throws UserTransactionException
    {
                transapi.rollback(str);
    }

    private void commit() throws UserTransactionException
    {
        try {
            transapi.commit();
        }
        catch(HeuristicRollbackException e){
                throw new UserTransactionException(e.getMessage(),e);
        }
        catch(HeuristicMixedException e){
                throw new UserTransactionException(e.getMessage(),e);
        }
        catch(Exception e2)
        {
            throw new UserTransactionException(e2.getMessage(),e2);
        }
    }
    







































    
    static int LOCK_TIMEOUT;
    /**
     * Checks out the PolledData with the given key from the 
     * database for writing, with a write lock. This method blocks for a very
     * long time until the PolledData is ready for writing, if the object is
     * locked by another thread. Returns null if the PolledData is not present
     * in the database.
     * <p>
     * <b>Note:</b> Use the {@link #checkOut(String, int)} method to check out
     * objects from the database with locks, as it might result in the method
     * call getting blocked unacceptably.
     *
     *	@param      key   The unique key of the PolledData to be checked out
     *
     *	@return     PolledData, if it is present in the database
     *				null, if not present in the database
     *
     *  @throws	NmsPollException if the object is not available for writing
     *				even after a very long time (approx 80 mins).
     */    

    public PolledData checkOut(String key) throws NmsPollException,UserTransactionException



    {
        return lockAndGetTheObject(key,LOCK_TIMEOUT); 
    }

    /**
     * Checks out the PolledData with the given key from the 
     * database for writing, with a write lock. If the PolledData to be 
     * checked out is currently locked by some other thread, this method keeps
     * trying to check out the object for the specified time. If the 
     * PolledData becomes available for writing before the specified time, 
     * the object is fetched from the database, locked and returned back. 
     * Returns null if the PolledData is not present in the database.
     *
     *	@param      key   The unique key of the PolledData to be checked out
     *  @param		timeOut	Time in seconds to wait, if the object is 
     *                  locked by some other thread.
     *
     *	@return     PolledData, if it is present in the database
     *				null, if not present in the database
     *
     *  @throws NmsPollException if the object is not available for writing
     *		for the specified amount of time.
     */

    public PolledData checkOut(String key, int timeOut) throws NmsPollException, UserTransactionException



    {
        return lockAndGetTheObject(key, timeOut); 
    }
    /**
     * Checks out the PolledData with the given key from the 
     * database for writing, with a write lock. This method throws an 
     * Exception if the managed object is locked by another thread and 
     * doesn't wait.
     * @param	key   The unique key of the PolledData to be checked out
     *
     *	@return     PolledData, if it is present in the database
     *				null, if not present in the database
     *
     *  @throws		NmsPollException, if the object is locked by some other thread
     *
     */

    public PolledData checkOutIfAvailable(String key) throws NmsPollException, UserTransactionException



    {
        return lockAndGetTheObject(key,0); //return immediately
    }


    private PolledData lockAndGetTheObject(String key,int timeout) throws
NmsPollException, UserTransactionException




    {
        if (key == null) 
        {
            throw new NmsPollException(NmsUtil.GetString("Cannot lock a null key "), null);
        }
        //wait if backup is going on
        PureServerUtils.backUpInProcess();

        PolledData obj = null;
       
        if (timeout==-1)
        {
            obj = getPolledData(key);
            return obj;
        }
        try
        {
                obj = (PolledData)comapi.getObject(key);
        }

        catch(UserTransactionException e1)
        {
                throw e1;
        }                

        catch (NmsStorageException nse)
        {
            throw new NmsPollException(NmsUtil.GetString("No PolledData is found for this key ")+ nse.getMessage() ,null);
        }
        catch (Exception e)
        {
            throw new NmsPollException(NmsUtil.GetString("Problem in locking ") + e.getMessage(),null);
        }

        if (obj == null) 
        {
            throw new NmsPollException(NmsUtil.GetString("No PolledData is found for this key ") ,null);
        }

        if(NmsUtil.relapi.isInTransaction())
        {
            Object lockId  =  lockHandler.getObject(Thread.currentThread(),obj.getKey());
            if(lockId!=null)
            {
                obj.setLockId(lockId);
                return obj;
            }
        }

        LockableObject lockObj = (LockableObject) lockObjectTable.get (key);

        if (lockObj == null)
        {
            lockObj = createLockObject(key);
        }

        if (lockObj != null)
        {
            try {
                Object lock = lockObj.lock(LockableObject.WRITE_LOCK,timeout);
                obj.setLockId(lock);
            }catch(Exception e)
            {
                throw new NmsPollException(e.getMessage(),null);
            }
            return obj;
        }

        return null;
    }

    /**
     * Applies the specified lock to the PolledData. This method will lock the
     * specified PolledData, by applying the given type of lock, which will
     * actually define access levels for the object.  This interface 
     * defines the lock types and various other methods to implement 
     * the locking mechanism as required by the user. The third argument 
     * specifies the time in seconds as to how long to wait to obtain the
     * lock for the PolledData.
     *
     * @param	obj	The PolledData that is to be locked.
     * @param	lock_type	The type of lock to be applied.
     * @param	timeout	Time, in seconds, to wait to acquire the lock.
     *
     * @return The PolledData with the <i>lock</i> set in it's <i>lockId</i>
     *		 property.
     *
     * @see	com.adventnet.nms.util.LockableObject
     * @throws NmsPollException if an error occurs.
     */    
    public PolledData lock (PolledData obj,int lock_type,int timeout) throws NmsPollException
    {
        if (obj == null)
        {
            throw new NmsPollException(NmsUtil.GetString("Cannot lock a null object"),null);
        }

        if (timeout==-1) return obj;

        LockableObject lockObj = (LockableObject)lockObjectTable.get(obj.getKey());
        if(lockObj == null)
        {
            lockObj = createLockObject(obj.getKey());
        }

        if(lockObj != null)
        {
            try {
                Object lock = lockObj.lock(lock_type,timeout);
                obj.setLockId(lock);
            }catch(Exception e)
            {
                throw new NmsPollException(e.getMessage(),null);
            }
            return obj;
        }

        return null;
    }

    /**
     * Unlocks the specified PolledData. This method will unlock the given
     * PolledData, releiving it of all the previously applied locks.
     *
     *
     * @param	obj	The PolledData that is to be unlocked.
     *
     * @see	com.adventnet.nms.util.LockableObject
     * @throws NmsPollException if an occur occurs while unlocking .
     */
    public void unlock(PolledData obj) throws NmsPollException
    {
            LockableObject lockObj = (LockableObject)lockObjectTable.get(obj.getKey());
            unlock(obj,lockObj);

    }
    /**
     * Checks whether this instance of the PolledData can be written to the 
     * database. This method checks whether the specified instance of the 
     * PolledData currently holds a valid write lock, so that it can be 
     * written to the database. 
     *
     *
     * @param	obj	The PolledData instance that is to be checked whether
     *                  it has a write lock.
     *
     * @return	true if the object holds a write lock, false otherwise.
     *
     * @see	com.adventnet.nms.util.LockableObject
     * @throws NmsPollException if an occur occurs while checking permission
     */

    public boolean checkWritePermission (PolledData obj) throws NmsPollException
    {
        LockableObject lockObj = (LockableObject)lockObjectTable.get(obj.getKey());

        if (lockObj == null)
        {
            return false;
        }

        return (lockObj.checkWritePermission (obj.getLockId()));
    }
    /**
     * Gets the lock type currently held by the specified instance of the 
     * PolledData. This method is useful in finding out what type of lock this
     * instance of the PolledData holds, when multiple instances hold different
     * types of locks. 
     *
     * @param	obj	PolledData instance for which the lock type is to be
     *					obtained.
     *
     * @return	The lock type value, as defined in the <b>LockableObject</b> 
     *		interfae, currently held by the specified instance of the 
     *		PolledData. If the object instance is not locked previously,
     *		this method returns UNKNOWN_LOCK.
     * @throws NmsPollException if an occur occurs while getting lock type
     */
    public int getCurrentLockType (PolledData obj) throws NmsPollException
    {
        LockableObject lockObj = (LockableObject)lockObjectTable.get(obj.getKey());

        if (lockObj == null)
        {
            return LockableObject.UNKNOWN_LOCK;
        }

        return (lockObj.getCurrentLockType (obj.getLockId()));
    }
    /**
     * Releases the specified lock type unconditionally for the PolledData with
     * the given name. If under some exceptional condition, a PolledData 
     * instance that was holding a lock, is lost before it has unlocked, then it 
     * will result in all subsequent requests for locks over that PolledData 
     * to fail. In such a case, this method can be used to unconditionally release
     * the specified lock type by giving just the name of the PolledData. But
     * the effect of this method depends entirely on the implementation of the 
     * <b>LockableObject</b> interface. 
     *
     * @param		key	key of the PolledData to be unlocked.
     * @param		lockType	Type of the lock to be unlocked.
     * @throws NmsPollException if an occur occurs while clearing lock.
     *
     */

    public void clearLockForObject (String key, int lockType) throws
UserTransactionException, NmsPollException




    {
        PolledData obj = getPolledData(key);

        if (obj == null)
        {
            return;
        }

        LockableObject lockObj = (LockableObject) lockObjectTable.get (obj.getKey());

        if (lockObj != null)
        {
            lockObj.clearLock (lockType);

            if (!(lockObj.isAnyLockPresent ()))
            {
                removeLockObject(obj.getKey());
            }
        }
    }

    private void unlock(PolledData obj, LockableObject lockObj)
    {
        if (lockObj == null) return;

        if(NmsUtil.relapi.isInTransaction())
        {
            //Object[] lockArray = {lock,name};
            lockHandler.cacheObject(obj.getLockId(),obj.getKey());
        }
        else
        {
            /*
            lockObj.unlock(obj.getLockId());
            
            obj.setLockId(null);
            
            if (!(lockObj.isAnyLockPresent ()))
            {
                removeLockObject(obj.getKey());
            }
            */
            unlockAndRemoveFromTable(obj.getLockId(),obj.getKey());
            obj.setLockId(null);
        }
    }

    private void unlockAndRemoveFromTable(Object lockId,String key)
    {
        LockableObject lockObj = (LockableObject) lockObjectTable.get (key);
        if(lockObj != null)
        {
        lockObj.unlock(lockId);
        if (!(lockObj.isAnyLockPresent ()))
        {
            removeLockObject(key);
        }
        }
    }

    transient Class lockClass;
    transient String lockClassName = NmsUtil.getParameter("LOCK_CLASS");//No Internationalisation

    private LockableObject createLockObject(String key) throws NmsPollException
    {
        if(lockClassName == null) 
        {
            throw new NmsPollException(NmsUtil.GetString("Lock classname is null "),null);
        }

        LockableObject lockObject = null;
        if((lockObject = (LockableObject)lockObjectTable.get(key)) == null)
        {
            try
            {
                if(lockClass == null)
                {
                    lockClass = Class.forName(lockClassName);
                    if (lockClass == null)
                    {
                        throw new NmsPollException(NmsUtil.GetString("In createLockObject, No Lockable class found: ")+ lockClass, null);
                    }
                }
                com.adventnet.nms.util.LockableObject lObj = (com.adventnet.nms.util.LockableObject) lockClass.newInstance();
                lockObjectTable.put(key,lObj);
                return lObj;
            }
            catch(Exception e)
            {
                throw new NmsPollException(e.getMessage(),null);
            }

        }
        else
        {
            return lockObject;
        }

    }

    private void removeLockObject(String key)
    {
        lockObjectTable.remove(key);
    }
/**
     * Adds a new table name and the respective schema to Poll Engine
     *  to create this table.
     * When this table name is given for the
     * PolledData the table will be created and the Collected
     * Data will be stored in this table.
     * @see createTable(String)
     * @param tableName name of the table
     * @param schema String which used to create the table

     * @exception RemoteException if an error occurs during remote call

     * @exception NmsStorageException if an error occurs while adding the schema.
     */

    public void addCreateSchema(String tableName, String schema)throws
NmsStorageException, UserTransactionException




    {
        comapi.addCreateSchema(tableName,schema);
    }

   /**
     * Returns the schema that is used for creating the
     * table that is specified.
     * @return The String which is the schema used to create this table
     * @param  tableName name of the table for which schema is returned.
     * @exception RemoteException if an error occurs during remote call
     */

    public String  getCreateSchema(String tableName)   
    {
        return comapi.getCreateSchema(tableName);
    }

   /**
     * Creates the table that is given as argument.
     * If the schema for creation of this table is already present
     * then table will be created according to this schema else,
     * it will throw NmsStorageException
     * @see addCreateSchema(String,String)
     * @param tabName the name of the table which will be created.
     * @exception RemoteException if an error occurs during remote call
     * @exception NmsStorageException if an error occurs while creating table.
     */


    public void createTable(String tableName)throws NmsStorageException , UserTransactionException



    {
        comapi.createTable(tableName);
    }

    private boolean checkForAccess(PolledData pd) throws NmsPollException
    {
        LockableObject lockObj = null;
        lockObj =  (LockableObject)lockObjectTable.get(pd.getKey());
        
        if(lockObj != null)
        {
            if(!lockObj.checkWritePermission(pd.getLockId()))
                throw new NmsPollException(NmsUtil.GetString("No write permission.")+NmsUtil.GetString("Get the write lock before updating object."),null);
        }
        return true;
    }

    private void notifyPollingObject(int type, PollingObject po) throws NmsPollException
    {
        pollUnitNotifier.notify(type,po);

    }
    
    private void getAndNotifyPollingObject(int type,String name) throws NmsPollException
    {
        PollingObject po = getPollingObject(name);
        notifyPollingObject(type,po);
    }

    /**
     * Adds PolledData to the Polling Engine for the ManagedObject which
     * is passed as a Parameter. This can be used when the discovery 
     * process is not used and the ManagedObjects  are added by using 
     * TopoAPI, and PolledData needs to be configured for these 
     * ManagedObjects. This ManagedObject is passed through all the 
     * PollingObjects and if it satisfies any of the MatchCriteria 
     * PolledData are added, these PolledData are passed through 
     * PollFilters also.
     * @param mo <code>ManagedObject</code> for which the PolledData is to
     * be added.
     * @see addPoll(PolledData)

     * @exception NmsPollException if ,<ul>
        * <li> there is any problem while retrieving the corresponding
ManagedObject 
     * <li> PolledData with the given key already exists.
     * <li> PolledData key is not formed properly.
     * <li> if dynamic table is not created properly at run time (this is
applicable only if dynamic table feature is used).
     * if there is any exception while adding PolledData 
     * </ul>
     * @exception UserTransactionException if there is any exception while
doing transaction operations.




     * @exception RemoteException if an error occurs during remote call.
     
     * @exception NmsPollException if an error occurs while adding PolledData
     */


        public void addPolledDataForMO(ManagedObject mo) throws NmsPollException,UserTransactionException, RemoteException



    {
        try{
        initializer.addThePollForObject(mo);
        }

        catch(UserTransactionException e1)
        {
                throw e1;
        }

        catch(NmsPollException e2)
        {       
                throw e2;
        }
        catch(Exception e3)
        {
                throw new NmsPollException (e3.getMessage(),e3);
        }

    }

    /**
     * Returns the available PollFilter objects. 
     *
     * @return a <code>Vector</code> of PollFilter Objects.
     */
    public Vector getFilterList()
    {
        return initializer.getFilterList();
    }


    public Vector checkForPollerForPd(Vector pdatas)
    {
        if(Collector.pollers.size() == 0)
            return pdatas;
        else
        {
            Vector pdVect = new Vector();

        try{
            for(Enumeration en=pdatas.elements();en.hasMoreElements();)
            {
                PolledData pd = (PolledData)en.nextElement();
                String pollerName = pd.getPollerName();
                RemotePoller rp = (RemotePoller)Collector.pollers.get(pollerName);
	        // just to check whether the connection is alive
                try
                {
                    String temp = rp.getName();
                }catch(Exception re)
                {
                    Collector.pollers.remove(pollerName);
                    pdVect.addElement(pd);
                }
            }
        
        }
        catch(Exception e1)
        {
                e1.printStackTrace();
        }
        

















            return pdVect;
        }
    }

    void updateDataCollectionQueryInterval(long period,boolean firstTime)
    {
        long dcqi = 0;
        //This check is for the warm start.
        if(firstTime)
        {
            if(pdCount < 1000)
            {
                dcqi = (period/2)*1000;
            }
            else
            {
                dcqi = (period/(pdCount/500)) * 1000;
            }
            if(dcqi < 1500) dcqi = 1500;
            RunPoll.setQueryInterval(dcqi);
        }
        else // This is when each PolledData is added/modified.
        {
            if((pdCount == 1) || ((period < prevPeriod) && (pdCount < 1000)))
            {
                dcqi = (period/2)*1000;
                if(dcqi < 1500) dcqi = 1500;
                RunPoll.setQueryInterval(dcqi);
            }
            else if((period < prevPeriod) || ((pdCount%1000) == 0))
            {
                dcqi = (period/(pdCount/500)) * 1000;
                if(dcqi < 1500) dcqi = 1500;
                RunPoll.setQueryInterval(dcqi);
            }
        }
        if((prevPeriod == 0) || (period < prevPeriod))
            prevPeriod = period;
    }

    void saveMaxId()
    {
        if(id > 1)
            comapi.comtorel.saveMaxId((id-1));
    }

    private void applyThresholds(long val, String stringVals,String inst,PolledData pd) throws Exception
    {
        MultiplePolledData mpd=null;
        if(val != -1)
        {
            if(pd.getIsMultiplePolledData())
            {
                try 
                {
                    mpd =(MultiplePolledData) pd;
                }
                catch (ClassCastException cce)
                {
                    mpd = new MultiplePolledData();
                    mpd.setId(pd.getId());
                    mpd.setOwnerName(pd.getOwnerName());
                    mpd.setProperties(pd.getProperties());
                }
                
                ((MultiplePolledData)pd).checkAgainstThresholds(val,inst);
            }
            else
            {
                pd.checkAgainstThresholds(-1 , val);
            }
        }
        else
        {
            if(pd.getStatsDataTableName().equals("STATSDATA%"))//No Internationalisation
            {
                pd.setStatsDataTableName("STRINGDATA%");//No Internationalisation
            }

            if(pd.getIsMultiplePolledData())
            {
                try 
                {
                    mpd =(MultiplePolledData) pd;
                }
                catch (ClassCastException cce)
                {
                    mpd = new MultiplePolledData();
                    mpd.setId(pd.getId());
                    mpd.setOwnerName(pd.getOwnerName());
                    mpd.setProperties(pd.getProperties());
                }
                
                ((MultiplePolledData)pd).checkAgainstThresholds(stringVals,inst);
            }
            else
            {
                pd.checkAgainstThresholds(-1, stringVals);
            }
        }
    }
 
    private void updateLastValues(long time,PolledData pd) throws Exception
    {
        pd.updateCurrentSaveCount();
        pd.setLastTimeValue(time);
        try 
        {
            if(pd.getPeriod() == 0)
            {
                comapi.updateStatus(pd.getKey(),false,-1,pd.getDnsName());
            }
            comapi.updateLastValues(pd);
        }catch(Exception e)
        {
            throw e;

        }
    }

    class LockHandler implements TransactionHandler
    {   
 
        NotificationCacheMaintainer maintainer = new NotificationCacheMaintainer();
        private TransactionAPI txnAPI = null;

        LockHandler( TransactionAPI tapi)
        {
            txnAPI = tapi;
        }

    
        void cacheObject(Object lockId,String key)
        {
            Object [] array= {lockId,key};
            maintainer.addNotificationCache(this, array);
            txnAPI.addTransactionHandler(this, Thread.currentThread());
        }

        Object getObject(Thread context,String key)
        {
            Vector cache = maintainer.getNotificationCache(this,context);
            if(cache == null)
                return null;
            for(Enumeration en = cache.elements();en.hasMoreElements();)
            {

                Object[] obj = (Object[]) en.nextElement();
                if(obj[1].equals(key))
                {
                    return obj[0];
                }

            }
            return null;
        }

        public void notifyCommit (Thread context)
        {
            Vector cache = maintainer.getNotificationCache(this,context);
            if(cache == null)
                return ;
            for(Enumeration en = cache.elements();en.hasMoreElements();)
            {
                Object[] obj = (Object[]) en.nextElement();
                unlockAndRemoveFromTable(obj[0],(String)obj[1]);
            }
            maintainer.removeNotificationCache(this, context);            

        }
        
        public void notifyRollback (Thread context)
        {
            Vector cache = maintainer.getNotificationCache(this,context);
            if(cache == null)
                return ;
            for(Enumeration en = cache.elements();en.hasMoreElements();)
            {
                Object[] obj = (Object[]) en.nextElement();
                unlockAndRemoveFromTable(obj[0],(String)obj[1]);
            }
            maintainer.removeNotificationCache(this, context);            

        }

    }		//	End of class DefaultLockableObject

} 

