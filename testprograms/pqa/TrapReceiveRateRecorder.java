package com.adventnet.nms.testdb;


import com.adventnet.nms.testdb.tools.*;
import com.adventnet.nms.testdb.util.*;
import java.util.*;
import java.io.*;

public class TrapReceiveRateRecorder implements Runnable,CheckerInterface
{

    String[] arg                    =   null;
    String mode                     =   "";
    String receiveWait              =   "";  
    TrapGenerator     tg            =   null;
    TrapReceiver      tr            =   null; 
    Thread t                        =   null; 
    Properties p                    =   null;
    public static String file       =   TestUtil.TEST_LOGS_DIR+File.separator+"TrapReceiveRate.txt";
    public TrapReceiveRateRecorder(){  }

    public boolean checkResult()
    {
        
        mode                = (String)  p.get("mode"); //"BURST_MODE"
        receiveWait         = (String)  p.get("wait"); // "120000"; 
        p.remove("mode");
        p.remove("wait");
        
        arg        =   new String[p.size()];
        for(int i=arg.length;i>0;i--)
        {    
            arg[i-1]    = (String)p.get("prop"+i);  
        
        }
        t               = new Thread(this);
        startReceiver();        
        tg=new TrapGenerator();
        if(mode.equals("BURST_MODE"))  tg.sendTrapsInBursts(arg,tg);
        else tg.sendTrapAtSteadyRate(arg,tg);   
        while(t.isAlive()) try{Thread.sleep(50000);}catch(Exception ex){}
        return true;            
        
    }
    public void setProperties(Properties pr)
    {
        p=pr;
    }

public static void main(String[] args)
{    
    if(args.length < 6) Usage();    
    TrapReceiveRateRecorder rm = new TrapReceiveRateRecorder();
    rm.mode                =   args[0]; // mode   
    rm.receiveWait         =   args[1]; // time_wait
    rm.arg                 =   new String[4];
    rm.arg[0]              =   args[2]; //host
    rm.arg[1]              =   args[3]; //port
    rm.arg[2]              =   args[4]; //count;        
    rm.arg[3]              =   args[5]; //oid ".1.3.6.1.2.1.11";        
    rm.t               = new Thread(rm);
    
    rm.startReceiver();        
    rm.tg=new TrapGenerator();
    if(rm.mode.equals("BURST_MODE"))  rm.tg.sendTrapsInBursts(rm.arg,rm.tg);
    else rm.tg.sendTrapAtSteadyRate(rm.arg,rm.tg);           

}

public static void Usage()
{

    System.out.println("Usage is java com.adventnet.nms.testdb.TrapReceiveRateRecorder waittime_in_millis BURST_MODE/STEADY_MODE hostname port count .1.3.6.1.2.1.11"); 
    System.exit(0);

}

public void startReceiver()
{
    t.start();    
    
}
public void run()
{
    long start= System.currentTimeMillis();
    long end= start+Long.parseLong(receiveWait);  
   
    try{
            tr = new TrapReceiver();    
            tr.trapCount=Integer.parseInt(arg[2]);
            tr.registerClient(tr);
        }catch(Exception ex){
            System.out.println("TrapReceiver Instantiation Exception "+ex);
        }  
    long time=Long.parseLong(receiveWait);
    try{  
        
    t.sleep(time);    
    }catch(InterruptedException ie){}    
    
    if(mode.equals("BURST_MODE"))
    {        
        FileUtil.appendMessagesToFile("NMS can handle a trap burst rate of "+ (tr.count*100)+" In About "+time/60000+" Minutes \n",file,false);
        int receiveRate = getTrapReceiveRate();
        FileUtil.appendMessagesToFile("NMS Trap Receiving Rate "+ receiveRate +" per Second \n",this.file,false);
  	    FileUtil.appendMessagesToFile(tr.trapCount-(tr.count*100)+" Traps were Dropped \n",this.file,false);
    }
    else if(mode.equals("STEADY_MODE"))
    {        
        FileUtil.appendMessagesToFile("NMS can handle a trap steady rate of "+ (tr.count*100)+" In About "+time/60000+" Minutes\n",file,false);
        int receiveRate = getTrapReceiveRate();
        FileUtil.appendMessagesToFile("NMS Trap Receiving Rate "+ receiveRate +" per Second \n",this.file,false);
  	    FileUtil.appendMessagesToFile(tr.trapCount-(tr.count*100)+" Traps were Dropped \n",this.file,false);
    }
    System.exit(0);
    
}

public int getTrapReceiveRate()
{
    Vector ratevec = tr.rateVector;
    int totalrate = 0;
    for(Enumeration e = ratevec.elements();e.hasMoreElements();)
    {
        String ratestr = (String)e.nextElement();
        int rate = Integer.parseInt(ratestr);
        totalrate=totalrate+rate;
        
    }
    int avgrate =totalrate/ratevec.size();
    return avgrate;
    
}
}// End of Class
























