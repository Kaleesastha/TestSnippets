package se.netinsight.bugtesting.bug13037;

import java.util.Properties;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.security.audit.AuditAPI;

/**
 * This class starts two threads that adds audit entries. The purpose with this
 * class is to test if concurrent adding is thread safe.
 * 
 * @author janbur
 * 
 */
public class ConcurrentAuditReporting implements RunProcessInterface{

    @Override
    public void callMain(String[] arg) {
        
        try {
            Thread.sleep(30000); // Allow WebNMS to fully start.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Starting the Audit Reporting test.");

        final AuditAPI auditApi = (AuditAPI) NmsUtil.getAPI("NmsAuditAPI");
        
        
        new Thread(new Runnable() {           
            @Override
            public void run() {
                Properties p = new Properties();
                p.setProperty("operation", "operation 1");
                p.setProperty("status", "OK");        

                for (int i = 0; i < 2; i++) {
                    p.setProperty("auditedObj", "one-" + i);
                    try {
                        Thread.sleep(20);
                        auditApi.audit("root", p);
                    } catch (Exception e) {
                        System.err.println("one-" + i);
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        
        new Thread(new Runnable() {            
            @Override
            public void run() {
                Properties p = new Properties();
                p.setProperty("operation", "operation 2");
                p.setProperty("status", "OK");        

                for (int i = 0; i < 2; i++) {
                    p.setProperty("auditedObj", "two-" + i);
                    try {
                        Thread.sleep(20);
                        auditApi.audit("root", p);
                    } catch (Exception e) {
                        System.err.println("two-" + i);
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        
        
    }

    @Override
    public boolean isInitialized() {
        return true;
    }

    @Override
    public void shutDown() {
    }

}
