public class StartMemoryTesting
{
    String hostName=null;
    int trapsPerSecond =10;
    //2 hours
    int faultInterval = 7200;
    int mapInterval = 7200;
    int topoInterval = 7200;
    int frameInterval = 7200;

    boolean firstTime = true;

    long lastFaultTime,lastPollTime,lastMapTime,lastFrameTime,lastTopoTime=0;
    
    public StartMemoryTesting(String a[])
    {
        try
        {
            this.hostName = a[0];
            trapsPerSecond = Integer.parseInt(a[1]);
            faultInterval = Integer.parseInt(a[2])*1000;
            mapInterval = Integer.parseInt(a[3])*1000;
            topoInterval = Integer.parseInt(a[4])*1000;
            frameInterval = Integer.parseInt(a[5])*1000;
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }

        startTesting();
    }
    
    int i=0;
    private void startTesting()
    {
        while(true)
        {
            //System.out.println(" ivalue =="+(i++));
            startSendingTraps();
            startFaultTesting();
            //startMapTesting();
            startTopoTesting();
            startFrameTesting();
        }
    }
    
    private void startSendingTraps()
    {
        if(!firstTime)
        {
            return;
        }
        firstTime=false;
        String[] array = new String[9];
        
        //servername
        array[0]=hostName;
        //portNumber
        array[1]= String.valueOf(5000);
        //number of traps 20/sec for 20 days
        array[2]=String.valueOf(24*3600*20*20);
        //time interval
        array[3]=String.valueOf(1000/trapsPerSecond);
        //GT
        array[4]=String.valueOf(2);
        //ST
        array[5]=String.valueOf(0);
        //Enterprise
        array[6]="1.3.6.1.2.1.11";
        //printCount
        array[7]=String.valueOf(1000);
        //number of threads
        array[8]=String.valueOf(1);
        new MultiSendTrap(array);
        
    }

    private void startFaultTesting()
    {
        long diff= System.currentTimeMillis()-lastFaultTime;
        if(diff > faultInterval)
        {
            new FaultOperations(hostName);
            lastFaultTime=System.currentTimeMillis();
        }
    }

    private void startMapTesting()
    {
        long diff = System.currentTimeMillis()-lastMapTime;
        if(diff > mapInterval)
        {
            new MapOperations(hostName);
            lastMapTime = System.currentTimeMillis();
        }
    }

    private void startTopoTesting()
    {
        long diff = System.currentTimeMillis()-lastTopoTime;
        if(diff > topoInterval)
        {
            new TopoOperations(hostName);
            lastTopoTime = System.currentTimeMillis();
        }
    }

    private void startFrameTesting()
    {
        long diff = System.currentTimeMillis()-lastFrameTime;

        if(diff > frameInterval)
        {
            new FrameworkOperations(hostName);
            lastFrameTime = System.currentTimeMillis();
        }
    }
    
    public static void main(String a[])
    {
        if(a.length !=6)
        {
            System.out.println(" java StartMemoryTesting hostName TrapsPerSecond FaultPeriod MapPeriod TopoPeriod FrameworkPeriod ");
            return;
        }
        new StartMemoryTesting(a);
    }
}
