import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import com.adventnet.management.scheduler.Scheduler;
import com.adventnet.nms.util.*;

public class SchedulerTestServer implements RunProcessInterface {
                private static final Logger log = Logger.getLogger(SchedulerTest.class.getName());
                static AtomicInteger completed = new AtomicInteger();
               
                public void callMain(String[] args) {
                                //System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT.%1tL %4$s %2$s %5$s%6$s%n");
                               
                                Scheduler sch = Scheduler.createScheduler("myTestScheduler", 100);
                                sch.start();
                               
                                int qtyTasks = 3000;
                                for (int i = 0; i < qtyTasks; i++) {
                                                final int taskNo = i;
                                                sch.scheduleTask(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                                System.err.println("VOCUS:Task: " + taskNo + " starts in " + Thread.currentThread().getName());
                                                                                try {
                                                                                                // sleep for 20 seconds
                                                                                                Thread.sleep(20000);
                                                                                } catch (InterruptedException e) { e.printStackTrace();}
                                                                                System.err.println("VOCUS:Task: " + taskNo + " completes");
                                                                                completed.incrementAndGet();
                                                                }
                                                }, 100);
                                }
                                System.err.println("VOCUS_DEBUG: scheduleTask submitted with " + qtyTasks );
                               
                                while( completed.get() < qtyTasks ){
                                                try {
                                                                Thread.sleep(10000);
                                                                System.err.println("VOCUS_DEBUG: Tasks Scheduled -> " + sch.getNumTasks() );
                                                                System.err.println("VOCUS_DEBUG: Task names Scheduled -> " + sch.runnables );
                                                                System.err.println("VOCUS_DEBUG: Active Thread Estimate : " + Thread.activeCount() );
                                                                System.err.println("VOCUS_DEBUG: Completed Tasks -> " + completed.get() );
                                                } catch (InterruptedException e) { e.printStackTrace();}
                                }
                                System.err.println("VOCUS_DEBUG: all done !");
                                sch.stopThis();
                                sch.cleanUp();
                }
		public boolean isInitialized() {return true;}
		public void shutDown(){}
}
