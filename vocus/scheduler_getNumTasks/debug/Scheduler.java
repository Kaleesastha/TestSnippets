/* 
   $Id: Scheduler.java,v 1.3.4.1 2012/01/25 05:10:30 karen.r Exp $
 */

/**
 *  Scheduler.java
 */

package com.adventnet.management.scheduler;

import java.util.*;
import java.io.*;

//import com.adventnet.nms.util.PureUtils;

/**
 * A scheduler for running tasks in a Java VM.
 * This class can be used to schedule a particular task at a
 * specified time. This is used to control effectively a number 
 * of tasks using a limited number of threads
 */

public class Scheduler extends Thread 
{
	/**
	 * @see #getNextIndex
	 */
	private static int indexCount = 0;		

	/**
	 * @see #addToSchedulerVector
	 */
	private static Vector allSchedulerVector = new Vector();    

	/** 
	 * Gets a list of all the Schedulers .
	 * @return A Vector containing the list of Schedulers. 
	 */
	public static Vector getSchedulerList()
	{
		return allSchedulerVector;
	}

	/**
	 * The maximum numer of threads to be used in executing tasks for this
	 * scheduler
	 */
	private int MAX_THREADS = 4;

	/**
	 * Denotes the index of the scheduler as maintained by the Main Scheduler.
	 */
	private int index = 0;	

	/**
	 * Descriptor for the scheduler
	 */
	private String descriptor = "";	

	/**
	 * Variable representing the number of threads idle at any point of time.
	 */
	 int idleThreads = 4;

	/**
	 * Variable representing the number of active threads at any point of time.
	 */
	 int activeThreads = 0;

	 /**
	  * To get the number of threads that this scheduler controls.
	  *@return the the maximum number of threads to be used in executing 
	  *tasks for this scheduler
	  * @see #setMaxThreads
	  */

	public int getMaxThreads() 
	{
		return MAX_THREADS ;
	}

	private static int DEFAULT_MAX_THREADS = 4;

	/**
	 * Get the default value for Maximum number of threads used.
	 * In case a scheduler is initialized without specifying the max number 
	 * of threads neither in the conf file, nor in the arguments passed
	 * to the method, then it will take the default value which is 4.
	 * @return returns an integer denoting the default value for the maximum
	 * number of threads used 
	 * @see #setDefaultMaxThreads
	 */
	public static int getDefaultMaxThreads() 
	{
		return DEFAULT_MAX_THREADS ;
	}

	/**
	 * Sets the default value for the max threads to be used.
	 * In case a scheduler is initialized without specifying the max number 
	 * of threads neither in the conf file , nor in the arguments passed
	 * to the method , then what should the default number
	 *@param i the default value of maximum threads to be used 
	 * @see #getDefaultMaxThreads
	 */
	public static void setDefaultMaxThreads(int i) 
	{
		if ( (i>=0) && (i<100) ) 
		{
			DEFAULT_MAX_THREADS=i;
		} 
	}

	/**
	 * To set/change the number of threads that this scheduler controls . 
	 * Note this can be done only when the scheduler has been stopped by 
	 * the stopThis or stopAll methods .
	 * The boolean returned will indicate whether the operation was 
	 * successful or not .
	 * @param i  The maximum number of threads to be used in executing tasks
	 * for this scheduler
	 * @return returns true if the operation was successful , false otherwise
	 * @see #getMaxThreads
	 */
	public boolean setMaxThreads(int i) 
	{
		if (isAlive()) return false;
		if ( (i>=0) && (i<100) ) 
		{
			MAX_THREADS=i;
			idleThreads=i;
			return true ;
		} 
		return false ;
	}
	
	/**
	 * This method will be useful in case of dynamic addition of worker threads. 
	 * Note that this method will add worker threads to existing workers and increase 
	 * the Max Threads and total threads according to the number of worker threads added.
	 * If this method called before starting of this scheduler then it will
	 * just increase max threads alone. This method will not add worker threads 
	 * when the scheduler has been stopped by the stopThis or stopAll methods or isAlive returns false.
	 * 
	 * @param count  The number of worker threads to add.
	 * @return returns true if the operation was successful , false otherwise
	 */
	public boolean addWorkerThreads(int count){
		if(count <= 0 || ((count+MAX_THREADS) > 100)){
			System.err.println("Cannot Add worker threads count: " + count + ", Max Threads: " + MAX_THREADS); //No i18n
			return false;
		}
		// If this method called before starting the Scheduler then we can just increase the max_threads.
		if(workers != null && workers.size() == 0){
			MAX_THREADS += count;
		}
		else{
			if((!isAlive()) || (STOP_ALL || STOP_THIS)){
				System.err.println("Cannot Add worker threads due to isAlive: " + isAlive() + " or STOP_ALL: " + STOP_ALL + " or STOP_THIS: " + STOP_THIS); //No i18n
				return false;
			}
			for (int i = MAX_THREADS; i < MAX_THREADS + count; i++)
			{
				WorkerThread worker = new WorkerThread (this, getName()+"-"+(i + 1)); //No i18n
				workers.addElement (worker);
				worker.start ();
			}
			MAX_THREADS += count;
			idleThreads += count;
			TOTAL_THREADS += count;
		}
		System.out.println("Worker Threads Incremented(" + count + ") for the Scheduler "+ getName() +". Max Threads: " + MAX_THREADS); //No i18n
		return true;
	}
	
	/**
	 * This method will be useful in case of dynamic addition of worker threads.
	 * This method will get the scheduler instance from the {@link #getScheduler(String)} method 
	 * and invoke {@link #addWorkerThreads(int)} method to add worker threads.
	 * 
	 * @param schedulerName name of the scheduler to add worker threads.
	 * @param count the number of worker threads to add.
	 * @return returns true if the operation was successful , false otherwise
	 */
	public static boolean addWorkerThreads(String schedulerName, int count){
		Scheduler scheduler = Scheduler.getScheduler(schedulerName);
		if(scheduler == null){
			System.err.println("Cannot add worker threads since no scheduler created with name: " + schedulerName); //No i18n
			return false;
		}
		return scheduler.addWorkerThreads(count);
	}


	/*
	 * The total number of threads which are scheduled via schedulers . that is
	 * the combined total of all active schedulers .
	 */
	 static int TOTAL_THREADS = 0;


	/**
	 * To get the total number of active threads that all the schedulers
	 * put together control. 
	 * @return The total Number of Threads as an integer.
	 */
	public static int getTotalThreads() {
		return TOTAL_THREADS ;
	}

	/**
	 * The boolean for one to be able to stop all schedulers
	 */
	static boolean STOP_ALL = false;

	/**
	 * The boolean for one to be able to stop this instance of scheduler alone
	 */
	boolean STOP_THIS = false;

	/**
	 * The number of threads that have been stopped for this scheduler alone
	 */
	int NUM_THREADS_STOPPED=0;

	// The tasks to be run
	public Vector runnables = new Vector(); 

	// when to run the tasks
	private Vector times = new Vector();

	// The guys who do the work
	private Vector workers = new Vector();

	// The tasks to be run immediately
	Vector ready_tasks = new Vector();

    //To check whether priority is needed for this scheduler alone
    private boolean isPriorityNeeded = false;

    //The normal priority that a task can have.
    public static final int NORMAL_PRIORITY = 1;

    //The high priority that a task can have.
    public static final int HIGH_PRIORITY = 2;

    //Secondary storage of the tasks to be run immediately
    Vector secondary_ready_tasks = new Vector();

    //Storage of tasks to be run immediately in secondary
    private boolean inSecondary = false;

    //rearm time in seconds
    private int rearmTime = -1;

    //start time of the storage of the tasks to be run immediately in ready_tasks
    long startTime = System.currentTimeMillis();


	// We will store internally reference to all the schedulers when they
	// are instantiated . Schedulers can no longer be instantiated publicly

	private static Hashtable schedulers = new Hashtable(15) ;

	private static Vector threadNames = new Vector(15) ;

	private static Hashtable maxThreads = new Hashtable(15);

    private static Hashtable rearmTimes = new Hashtable(15);

    /**
	 * To set/change the rearm time in seconds that this scheduler controls . 
     * @param timeInSeconds The rearm time in seconds for this scheduler
	 */
	public void setRearmTime(int timeInSeconds) 
	{
        if(timeInSeconds<=0)
        {
            rearmTime = -1;
        }
        else
        {
            rearmTime = timeInSeconds;
        }
	}

    /**
	  * To get the rearm time in seconds that this scheduler controls .
	  *@return the rearm time in seconds for this scheduler
	  */

	public int getRearmTime() 
	{
		return rearmTime;
	}

	// The conffile which should be used to read the details of the number
	// of threads etc .
	private static String confFile = null;
 
	static
	{
		try
		{
			confFile = "conf/threads.conf" ;
        }
		catch(Throwable th)
		{
			confFile = "";
        }
	}

	private static boolean readConfFile = false ;

	/** Gets the conf file from which the thread related configuration should be read. 
	*   @return returns the conf file .i.e  threads.conf by default
	*   @see #setConfFile
	*/
	public static String getConfFile() 
	{
		return confFile;
	}

	/** 
	 * Sets the name of the conf file from which the thread related configuration should be read. 
	 *
	 * @param s String representing the name of the configuration file.
	 * @see #getConfFile
	 */
	public static void setConfFile(String s) {
		confFile=s;
	}


    private static InputStream openFile(File path) throws IOException 
    { 
        InputStream ret = null; 
        
        if (System.getProperty("JavaWebStart") != null) 
        { 
            System.out.println("Java Web Start mode in Scheduler: " + path); 
            
            String filename = path.getName(); 
            
            ret = Scheduler.class.getClassLoader().getResourceAsStream(filename); 
        } 
        else 
        { 
            ret = new FileInputStream(path); 
        } 
        
        return ret; 
    } 

	private static synchronized void readTheConfFile() 
	{
		if (readConfFile) return;
		BufferedReader is = null;
		String line = null;    
		try
		{
                    File ff = new File(confFile);
                    if (!ff.exists())
			{
				readConfFile=true;
				return ;
			}
			//is =  new BufferedReader(new FileReader(ff));
			is =  new BufferedReader(new InputStreamReader(openFile(ff)));

			while ( (line = is.readLine()) != null)
			{
				if (line.trim().equals("")) continue;
				else if (line.startsWith("#")) continue;
				else
				{
					StringTokenizer tok = new StringTokenizer(line);
                    //Third token for getting the rearmTime
					if (tok.countTokens()==2 || tok.countTokens()==3) {
						String s = tok.nextToken();
						int numb =-1;
                        int timeInSeconds = -1;
						try 
						{
							numb =Integer.parseInt(tok.nextToken());
                            if(tok.hasMoreTokens())
                            {
                                timeInSeconds = Integer.parseInt(tok.nextToken());
                            }
						} 
						catch ( NumberFormatException nfe)
						{
						}
						if ( (numb<0) || (numb>100) || ((tok.countTokens()==3) && (timeInSeconds<=0))) 
						{
							System.err.println(" Invalid line in the conf file "+
									confFile+
									" :"+line);
							continue;
						}
						maxThreads.put(s,new Integer(numb));
                        if(timeInSeconds > 0)
                        {
                            rearmTimes.put(s,new Integer(timeInSeconds));
                        }
					} 
					else
					{
						System.err.println(" Invalid line in the conf file "+confFile+
								" :"+line);
					}
				}
			}
		} 
		catch (IOException e) 
		{
			System.err.println("Scheduler  File read Error:"+confFile + ": "+e); 
		}
		catch (SecurityException anye)
		{
		}
		readConfFile = true ;
	}

	/**
	 *  Creates a scheduler with the specified name with default rearm time and
	 *  number of worker threads. This method returns a reference to the newly
	 *  created scheduler .If a scheduler with the same name already exists,
	 *  it returns the reference to the existing one.
	 *  @param nam name of the scheduler
	 *  @return A reference of the scheduler, created based on the name passed as an argument.
	 */
	public static  Scheduler createScheduler(String nam) {
		return createScheduler(nam,-1,-1);
	}


	/**
	 * Creates and returns a new instance of the Scheduler.
	 * If there already exists a scheduler with that name then that is 
	 * returned.
	 * @param nam name of the scheduler
	 * @param maxThreadNumber The number of threads this scheduler can handle at any point of time. 
	 * @return A Scheduler reference, created based on the name passed as an argument.
	 */

	public static Scheduler createScheduler(String nam,int maxThreadNumber) {
        return createScheduler(nam,maxThreadNumber,-1);
	}

    /**
	 * Creates and returns a new instance of the Scheduler.
	 * If there already exists a scheduler with that name then that is 
	 * returned.
	 * @param nam name of the scheduler
	 * @param maxThreadNumber The number of threads this scheduler can handle at any point of time.
     * @param rearmTimeInSeconds The rearm time in seconds for this scheduler
	 * @return A Scheduler reference, created based on the name passed as an argument.
	 */

	public static Scheduler createScheduler(String nam,int maxThreadNumber,int rearmTimeInSeconds) {
		synchronized (schedulers) {
			if (nam==null) return null;
			Scheduler sch = getScheduler(nam);
			if (sch!=null) return sch ;
			int threadsSpecifiedInConf = getNoOfThreadsSpecified(nam);
            if(rearmTimeInSeconds <=0)
            {
                rearmTimeInSeconds = getRearmTimeSpecified(nam);
            }
			if ((maxThreadNumber<=0) || (maxThreadNumber>100) ) {
				if (threadsSpecifiedInConf!=-1) 
					sch =new Scheduler(nam,threadsSpecifiedInConf);
				else
					sch =new Scheduler(nam);
			} else sch =new Scheduler(nam,maxThreadNumber);
            sch.setRearmTime(rearmTimeInSeconds);
			System.out.println("Instantiated "+sch.getName()+" scheduler with "+
					sch.MAX_THREADS +" threads ");
			return sch;
		}
	}
	
	/**
	 *  This method gets the maximum number of threads specified for that
	 *  particular scheduler.
	 *  It returns -1 if the specified scheduler is not present 
	 *  @param nam name of the scfheduler
	 *  @return returns the max number of threads for the scheduler
	 */

	public static int getNoOfThreadsSpecified(String nam)
	{
		readTheConfFile();
		Integer integ = (Integer)maxThreads.get(nam);
		if (integ!=null) 
			return integ.intValue();
		else 
			return -1;
	}

    /**
	 *  This method gets the rearmTime specified in minutes for that
	 *  particular scheduler.
	 *  It returns -1 if the specified scheduler is not present 
	 *  @param nam name of the scheduler
	 *  @return returns the rearmTime in minutes for the scheduler
	 */

	public static int getRearmTimeSpecified(String nam)
	{
		readTheConfFile();
		Integer integ = (Integer)rearmTimes.get(nam);
		if (integ!=null) 
			return integ.intValue();
		else 
			return -1;
	}

	/**
	 * One should use this method to get a reference to the scheduler . If
	 * a scheduler with the given name does not exist then it returns null; 
	 * @param nam  The name of the Scheduler.
	 * @return An instance of the scheduler identified by the name passed as argument. Else it returns null.
	 */
	public static Scheduler getScheduler(String nam) {
		if (nam==null) return null;
		return (Scheduler)schedulers.get(nam);
	}

	/**
	 * The constructor initializes the worker threads which invokes the task
	 * to be scheduled at a specific time.	
	 * @param name The Name associated for this scheduler
	 * @param noOfThreads Specifies the maximum number of threads that can be handled by this Scheduler.
	 */
	protected Scheduler(String name,int noOfThreads)
	{
        super(name);
		descriptor = name;
		index = getNextIndex();
		MAX_THREADS = noOfThreads;
		idleThreads = MAX_THREADS;
		schedulers.put(name ,this);
		addToSchedulerVector(name,this);
	}
	/**
	 * The constructor initializes the worker threads which invokes the task
	 * to be scheduled at a specific time.
	 * @param name The Name of this Scheduler.
	 */
	protected Scheduler(String name)
	{
		this(name,DEFAULT_MAX_THREADS);
	}

	/** 
	 * Gets the indexCount incremented by one.
	 * Denotes the index of the scheduler as maintained by the Main Scheduler
	 * @return returns the next index of the scheduler
	 */
	private synchronized static int getNextIndex()
	{
		indexCount = indexCount + 1;
		return indexCount;
	}

	/**
	 * This method schedules a task at the time specified by the Date object passed as argument.
	 * @param task the task to be scheduled 
	 * @param when time at which the task is to be scheduled 
	 */
	public synchronized void scheduleTask(Runnable task, Date when)
	{
		long l=0;

		if (when != null) 
			l = when.getTime();

		scheduleTask(task,l);
	}

    /**
	 * This method schedules a task with the said priority at the time specified by the Date object passed as argument.
	 * @param task the task to be scheduled 
	 * @param when time at which the task is to be scheduled 
     * @param priority priority of the task
	 */
	public synchronized void scheduleTask(Runnable task, Date when, int priority)
	{
		long l=0;

		if (when != null) 
			l = when.getTime();

		scheduleTask(task,l,priority);
	}

    /**
	 * This method schedules a task with the said priority at the time specified by the long value passed as argument.
	 * @param task the task to be scheduled 
	 * @param when time n milliseconds at which task the is to be scheduled
     * @param priority priority of the task
	*/
    public synchronized void scheduleTask(Runnable task,long when,int priority)
    {
        //If priority is normal, then no need to create PriorityTask.
        //If the task is not a PriorityTask, we will consider the priority of the task as normal and proceed.
        if(priority == NORMAL_PRIORITY)
        {
            scheduleTask(task, when);
        }
        else
        {
            isPriorityNeeded = true;
            PriorityTask pt = new PriorityTask();
            pt.setPriority(priority);
            pt.setTask(task);
            scheduleTask(pt,when);
        }
    }
	
	/**
	 * This method schedules a task at the time specified by the long value passed as argument.
	 * @param task the task to be scheduled 
	 * @param when time n milliseconds at which task the is to be scheduled 
	*/

	public synchronized void scheduleTask(Runnable task,long when)
	{
		System.err.println("#### DEBUG: adding task: "+task+" ## time ## "+when);
		if(when <= 0)
			when = System.currentTimeMillis();

		if(timeReverted)		
		{			
			if(System.currentTimeMillis() < scheduleAdjuster)		
			{
				if(when >= oldWrongTime)
					when = when - diffTime;
			}			
			else			
			{
				timeReverted=false;		
			}
		}		


		for (int i=0;i<times.size();i++) 
		{
			long l = ((Long)times.elementAt(i)).longValue();

			if(l > when) 
			{
				System.err.println("===== DEBUG: upating runnables vector: "+task+" ## time ## "+l);
				times.insertElementAt(new Long(when),i);
				runnables.insertElementAt(task,i);
				return;
			}
            //If priority needed and the time of both the tasks i.e., the one coming and the one present are equal with the task already present in the vector has low priority, then the task coming for addition to be inserted before the low priority task
            else if(isPriorityNeeded && l == when)
            {
		    System.err.println("===== DEBUG: isPriorityNeeded upating runnables vector: "+task+" ## time ## "+l);
                Runnable t = (Runnable)runnables.elementAt(i);
                if( ((!(t instanceof PriorityTask)) 
                     || (t instanceof PriorityTask && ((PriorityTask)t).getPriority() == NORMAL_PRIORITY) )
                    && (task instanceof PriorityTask && ((PriorityTask)task).getPriority() == HIGH_PRIORITY) )
                {
                    times.insertElementAt(new Long(when),i);
		    System.err.println("===== DEBUG: NORMAL_PRIORITY upating runnables vector: "+task+" ## time ## "+l);
                    runnables.insertElementAt(task,i);
                    return;
                }
            }
		}
		times.addElement(new Long(when));
		System.err.println("===== DEBUG: adding at last upating runnables vector: "+task+" ## time ## "+new Long(when));
		runnables.addElement(task);
		notifyAll();
	}

	/**
	 * This method is used to remove a task from being scheduled.
	 * @param task The task which is to be removed from the existing list of tasks.
	 */

	public synchronized void removeTask(Runnable task) 
	{
		ready_tasks.removeElement(task);
		if (task == null) return;
		for (int i=0;i<runnables.size();i++) {
			Runnable r = (Runnable) runnables.elementAt(i);
			if (task.equals(r)) {
				runnables.removeElement(r);
				System.err.println("===== DEBUG: removing from runnables "+r);
				times.removeElementAt(i);
				i--;
			}
		}
	}

	static boolean SUSPEND_ALL=false;

	/**
	 * Returns the status of all the Schedulers.
	 * @return a boolean true if all the Scheduler are suspended, else false.
	 */
	public static boolean isSuspended()
	{
		return SUSPEND_ALL;
	}
	

	/**
	 * If you want to suspend all the schedulers, this method can be used .
	 *  The method resumeAll can be used to restart all the schedulers.
	 * @return returns true if all tasks can be suspended. Otherwise false.
	 */	
	public static boolean suspendAll()
	{
		SUSPEND_ALL=true;
		// Before returning the boolean we should ensure whether
		// all the schedulers are suspended or not, atleast give
		// a message as to how many threads has been suspended.
		return true;
	}

	/**
	 * If you have used suspendAll method to suspend all schedulers , then using this
	 * will restart all of them.
	 * @return a boolean true if all the tasks are resumed
	 */

	public static boolean resumeAll() {
		SUSPEND_ALL=false;
		for (Enumeration en=schedulers.elements();en.hasMoreElements();)
		{
                    Scheduler sch = (Scheduler)en.nextElement();
                    wakeUpScheduler(sch);
                }
		return true;
	}

	/**
	 * This method will stop not only this but all schedulers threads . 
	 * so  be very careful in using this .
	 * Will return false if it could not  stop any thread
	 * @return a boolean true if all the threads are stopped.
	 */    

	public static boolean stopAll()
	{
		synchronized (schedulers) {
			if (STOP_ALL) return false;
			int count=0;
			STOP_ALL = true;
                        resumeAll();
			int totalorg =TOTAL_THREADS;
			while (TOTAL_THREADS>0)
			{
				if(count >= STOP_TIME_OUT) {
					System.err.println("Schedulers did not stop properly: "
							+ (totalorg-TOTAL_THREADS) +
							" threads stopped out of " + 
							totalorg);
					System.err.println("The remaining "+
							TOTAL_THREADS+
							" threads did not stop in "+
							STOP_TIME_OUT+ " seconds ");
					return false;
				}
				try {
					Thread.sleep(1000);
					count++;
				}
				catch (Exception e)
				{
					System.out.println("Exception while stopping the Schedulers"+e.getMessage());	
				}
			}
			System.out.println((totalorg-TOTAL_THREADS) +
					" of the "+totalorg+
					" active threads in the control "+
					" of  the schedulers stopped");

			TOTAL_THREADS=0;
			return true;
		}
	}

    /**
     *This method is used to notify all thread in the scheduler. This is introduced because
     * we have specified longer period for wait method which will basically halt thread
     * operation for the specified time. Unless we notify/wakeup all thread it won't come out
     * leading to delay in the shutdown.
     */
    private static void wakeUpScheduler(Scheduler sche)
    {
        sche.wakeUp();

        Vector v = sche.workers;

            for (int i=0;i<v.size();i++) 
            { 
                WorkerThread worker = (WorkerThread) v.elementAt(i);
                worker.wakeUp();
            }    	
        }


	/**
	 * This method will stop this scheduler alone . The scheduler will not
	 * clean up the runnables queue by calling this method . One can restart
	 * by invoking the start method . If you want this scheduler to get
	 * cleaned up, invoke the cleanup method after invoking this method.
	 * Will return false if it could not  stop this thread.
	 *
	 * @return a boolean true if the thread is successfully stopped. 
	 */

	public boolean stopThis()
	{
		int count=0;
		STOP_THIS = true;
                wakeUpScheduler(this);
		while (NUM_THREADS_STOPPED < MAX_THREADS)
		{
			if(count >= STOP_TIME_OUT) {
				System.err.println("Scheduler:"+getName()+" did not stop properly: " + NUM_THREADS_STOPPED + " threads stopped out of " + MAX_THREADS);
				System.err.println("The remaining "+
						(MAX_THREADS-NUM_THREADS_STOPPED)+
						" threads of scheduler:"+getName()+
						"did not stop in "+
						STOP_TIME_OUT+ " seconds ");

				return false;
			}
			try {
				Thread.sleep(1000);
				count++;
			}
			catch (Exception e)
			{
				System.out.println("Exception while stopping the Scheduler " +getName() + "  " +e.getMessage());	
			}
		}
		System.out.println(NUM_THREADS_STOPPED + "out of "+MAX_THREADS+
				" active threads stopped in "+
				" Scheduler:"+getName());

		return true;
	}

	/**
	 * This method should be used if you want to cleanup the scheduler and 
	 * release all resources from it. Remember the external code has to take
	 * care of the references it has to this object .This should be called
	 * when you do a stopThis on a scheduler and you have no intentions
	 * on using it (that is restarting it) again .
     * @return true if the cleaup operation is successuful. Otherwise false.
	 */

	public boolean cleanUp() {
		//if (isAlive()) return false;
	//For MOVAZ: Proper cleanup of threads The custom views were not getting updated properly. Movaz ticket id:12207844
	if (isAlive())
 	{
 	        if(!STOP_THIS && !STOP_ALL)
 	        {                
 	                return false;
 	        }
 	}
	//For MOVAZ: fix ends	
		synchronized (schedulers) {
			times.removeAllElements();
			runnables.removeAllElements();
			ready_tasks.removeAllElements();
			workers.removeAllElements();
			Scheduler s = (Scheduler)schedulers.remove(getName());
			//Fix for memory leak
		        	
			allSchedulerVector.remove(s);	

			threadNames.remove(getDescriptor());


			maxThreads.remove(getDescriptor());


			return true;
		}
	}

	private static int STOP_TIME_OUT = 15;

	/**
	 * The time in seconds that the calling thread should wait before invoking
	 * the stopALl or stopThis methods . That is when the stopALL or stopThis methods are 
	 * invoked the method will block for a period specified as Time Out period before 
	 * printing a message that all the threads could not be stopped . 
	 * If all the relevent threads are stopped prior to invoking the stopALL or stopThis methods ,
	 * then the method will return immediately.
	 * 
	 * @param The timeout value as an integer.
	 */
	public static void setStopTimeout(int timeout)
	{
		STOP_TIME_OUT = timeout;
	}

	/** 
	 *  This method is an internal implementation of Scheduler. When the user schedules a task( a Runnable object) using
	 *  this scheduler it internally stores the task. The Scheduler implementation periodically checks if there is any task
	 *  to be executed at that particular point of time. If there is any, it returns the task( a Runnable).
	 * 
	 * @return A Task(Runnable object) to be executed.
	 */
	protected synchronized Runnable getTheWork() {
		while (times.size() == 0) try { 
			wait(waitTime);
			if ( (STOP_ALL) || (STOP_THIS) )return null;
		} catch (InterruptedException iex) {}

		long first = ((Long)times.firstElement()).longValue();
		long now = System.currentTimeMillis();

		if (first > now) return null;

		while (runnables.size() == 0) 
		{                     
                       try 
                              {  
                           wait(waitTime); 
                           if ( (STOP_ALL) || (STOP_THIS) )return null; 
		       } 
                    catch (InterruptedException iex) {} 
		} 

		Runnable task = (Runnable) runnables.firstElement();
		runnables.removeElement(task);
		System.err.println("===== DEBUG: removing Element "+task);
                //times.removeElement(new Long(first)); commented
                times.removeElementAt(0);//added
		return task;
	}

    private boolean timeReverted=false;
	private long oldWrongTime=0;
	private long diffTime=0;	
	private long scheduleAdjuster=0;

	private boolean adjForReversion=true;
	/**
	  * Method to enable or disable Time Reversion. When Time Reversion is enabled, and during RunTime,
	  * if the System Time is modified then the Scheduler will automatically adjust the Time for all the tasks to
	  * the new time.
	  * @param bool A true value as argument enables the Time Reversion
	  */
	public void setAdjustForTimeReversion(boolean bool)
	{
		adjForReversion=bool;
	}
	/**
	  * Method to get the status of whether the Time Reversion is enables or  not.
	  * @return A boolean true if the Time Reversion is enabled.
	  */
	public boolean getAdjustForTimeReversion()
	{
		return adjForReversion;
	}
	

	/**
	 * The main thread which kicks off the task execution. 
	 * 
	 */
	public void run() 
	{
		long prevTime = 0;
		long temp = 0;
		
		synchronized (schedulers) {
			STOP_ALL=false;
			STOP_THIS=false;
		}
		startWorkers(); // first start the workers
		if (MAX_THREADS==0) return ;

		prevTime=System.currentTimeMillis();

		while ( (!STOP_ALL) && (!STOP_THIS) )
		{
            temp = System.currentTimeMillis(); 

			// We make changes only when the time reverted back is greater
			// than 2 minutes or else we ignore it.
			
			if(((prevTime - temp)  > 120000) && (adjForReversion))          
			{
				diffTime = prevTime - temp; 
				scheduleAdjuster=temp+1000;
				timeReverted = true;
				oldWrongTime=prevTime;
				adjustForTimeReversion(diffTime);
			}	
			prevTime = temp;            

			if (suspendable && SUSPEND_ALL)
			{	
				while(SUSPEND_ALL)
				{
					try
					{
						if(waitAndIntimateStopping(waitTime))
							break;
					}
					catch(InterruptedException ex)
					{
					}
				}
			}
			try {
				while(ready_tasks.size() > runnableControlCount)
				{
					waitAndIntimateStopping(waitTime);
				}
				Runnable task = getTheWork();
				if (task == null) {
					try  { 
						waitAndIntimateStopping(waitTime);
					} catch (InterruptedException iex) {}
				} else { 
					startTask(task);
				}
			} catch (Exception ex) {
				System.err.println("Exception scheduling task in scheduler:"
						+getName()+" "+ ex);
				ex.printStackTrace();
			}
		}
		return;
	}

	/**
	 *  This method should be used only when the time reverted back is 
	 *  greater than 2 minutes .
	 *  @param diff the time to be reverted back
	 */
	protected synchronized void adjustForTimeReversion(long diff)
	{
		synchronized(times)
		{
			for (int i=0;i<times.size();i++) 
			{
				long l = ((Long)times.elementAt(i)).longValue() - diff;
				times.setElementAt(new Long(l),i);
			}
		}
	}
	
	/**
	* This is an internal implementation of Scheduler.
    * This method waits for a period of time before checking the status of 
	* the parameters STOP_ALL and STOP_THIS. 
	*@param milli specifies the time interval for waiting
	*@return returns a boolean , if true indicated that  thread(s) have 
	*stopped 	 
	* @throws java.lang.InterrruptedException
	* 
	*/ 

	protected synchronized boolean waitAndIntimateStopping(long milli) throws InterruptedException 
	{
		try
		{
			wait(milli);
			if((STOP_ALL) || (STOP_THIS))
				return true;
		}catch(InterruptedException iex) {}
		return false;	
	}

	/**
	 *  Wake this one up, if waiting 
	 */
	private synchronized void wakeUp() 
	{
		notifyAll();
	}


	/**
	 *  start the task 
	 */
	private synchronized void startTask(Runnable task) {
        if(inSecondary)
        {
            secondary_ready_tasks.addElement(task);
        }
        else
        {
            ready_tasks.addElement(task);
        }
		for (int i=0;i<workers.size();i++) { // first start the workers
			WorkerThread worker = (WorkerThread) workers.elementAt(i);
			worker.wakeUp();
		}    	
	}


	/**
	 *  get the next task ready to run 
	 */
	synchronized Runnable getNextTask() {
        if (ready_tasks.size() == 0)
        {
            if(inSecondary)
            {
                synchronized(ready_tasks)
                {
                    inSecondary = false;
                    if(secondary_ready_tasks.size() > 0)
                    {
                        ready_tasks = secondary_ready_tasks;
                        secondary_ready_tasks = new Vector();
                    }
                }
                startTime = System.currentTimeMillis();
                if (ready_tasks.size() == 0) return null;
            }
            else
            {
                return null;
            }
        }
        
        if(rearmTime > 0 && ((System.currentTimeMillis() - startTime ) > (rearmTime * 1000)))
        {
            inSecondary = true;
        }
        for(Enumeration enu=ready_tasks.elements();enu.hasMoreElements();)
        {
            Runnable task = (Runnable) enu.nextElement();
            if((task instanceof PriorityTask) && (((PriorityTask)task).getPriority() == HIGH_PRIORITY))
            {
                ready_tasks.removeElement(task);
                return task;
            }
        }
        return (Runnable)ready_tasks.remove(0);
	}

	/** start the workers that have been added in the scheduler list
	*/
	private synchronized void startWorkers() {
		if ( (STOP_ALL) || (STOP_THIS) ) return;
		workers = new Vector();
		for (int i=0;i<MAX_THREADS;i++) {
			TOTAL_THREADS += 1;
			WorkerThread worker = new WorkerThread(this,getName()+"-"+(i+1));
			workers.addElement(worker);
			worker.start();
		}
	}
	/**
	  * Removes the scheduler from the list of scheduler identified by the name passed as argument.
	  * @param The name of the scheduler to be removed.
	  *
      */
	public void deregisterThisScheduler(String nam)// Devesh...because of reload problem in JavaUI
	{
		if(nam == null) 
			return;
		else
		{

			Scheduler s = (Scheduler)schedulers.remove(nam);

			//Fix for memory leak

			threadNames.remove(nam);

			allSchedulerVector.remove(s);	

			maxThreads.remove(nam);

			s.stopThis();
		}

	}

	/** 
	 * This method add the Scheduler to the Scheduler-Store so that it can be 
	 * referencesd  at any point of time in NMS. 
	 * @param nam  A name for this Scheduler. 
	 * @param sch  The Scheduler instance which is to be added to the existing list.
	 */
	private static void addToSchedulerVector(String nam,Scheduler sch)
	{
		threadNames.addElement(nam);
		allSchedulerVector.addElement(sch);
	}

	/**
	  * Gets the name of all the schedulers as a Vector.
	  * @return The list of Names of all the Scheduler's stored as String. 
      *
 	  */
	public static Vector getAllSchedulerNames()
	{
		return threadNames;
	}

	/**
	  * Gets the index of the Scheduler.
	  *  @return returns an int ,Denoting the index of the scheduler as 
	  * maintained by the Main Scheduler.
	  */
	public int getIndex()
	{
		return index;
	}

	/**
	  *  Gets the name of this scheduler.
	  * 
	  * @return A String representing this name of this Scheduler instance.
	  */
	public String getDescriptor()
	{
		return descriptor;
	}

	/** 
	  * Gets the number of tasks.
	  *
	  * @return The Total Number of Tasks handled by this Scheduler as an integer.
	  */
	public int getNumTasks()
	{
		return runnables.size();
	}

	/**
	  * Gets the total number of all the threads.
	  *
	  * @return The Maximum Number of Threads allocated for this Scheduler. 
	  */
	public int getNumThreads()
	{
		return MAX_THREADS;
	}

	/**
	  * Gets the number of active threads.
	  *
	  * @return The Total Number of Active Threads as an integer. 
      */
	public int getActiveThreads()
	{
		// Some racing condition this active threads value may become negative 
		// Hence we are adding new logic here to find the current active threads.
		int active = 0;
		if(workers != null)
		{
			for (Enumeration en=workers.elements();en.hasMoreElements();)
			{
				WorkerThread worker = (WorkerThread) en.nextElement();
				if(worker.getActive())
				{
					active++;
				}
			}
		}
		return active;
	}

	/**
	  * Gets the number of idle threads.
	  *
	  * @return The Total Number for Idle Threads.
      */
	public int getIdleThreads()
	{
		// Some racing condition this idle threads value may become negative
		// Hence we are adding new logic here to find the current idle threads.
		return (MAX_THREADS-getActiveThreads());
	}	 
	
	/**
	 * To maintain the number of instances of runnables getting craeted.
	 */
	private int runnableControlCount = 2000;
	
	/**
	 * Returns the instanceCount value which is used to control the number of 
	 * instances of runnable tasks that has to be instantiiated at a time.
	 * @return instanceCount number of instance to maintain.
	 * @see #setRunnableControlCount
	 */
	public int getRunnableControlCount()
	{
		return runnableControlCount;
	}
	
	/**
	 * Sets the instanceCount to this value which is used to control the number of 
	 * instances of runnable tasks that has to be instanciated at a time.
	 * @param count number of instance to maintain.
	 * @see #getRunnableControlCount
	 */
	public void setRunnableControlCount(int count)
	{
		runnableControlCount = count;
	}
	
	/**
	 * Time up to which the thread has to wait before checking for the number of instances.
	 */
	private long waitTime = 10;

	/**
	 * Retuns the time in millis up to which it has to wait before it creates a new instance of a runnable.
	 * @return waitTime 
	 * @see #setWaitTime
	 */
	public long getWaitTime()
	{
		return waitTime;
	}
	
	/**
	 * Sets the time in millis up to which it has to wait before it creates a new instance of a runnable.
	 * @param time The Time in milli seconds. 
	 * @see #getWaitTime
	 */
	public void setWaitTime(long time)
	{
		waitTime = time;
	}

	//Added for Ticket 48575 by Venkatramanan. In SUSPEND_ALL exclude the HeartBeat thread alone.
	//From NmsUtil, HeartBeat Scheduler's suspendable will be set as false
	boolean suspendable = true;
	public void setSuspendable (boolean suspend)
	{
		this.suspendable = suspend;
	}
}
