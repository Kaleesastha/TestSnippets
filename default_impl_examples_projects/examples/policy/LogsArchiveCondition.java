package test;


import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.Enumeration;


import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.policydb.PolicyCondition;
import com.adventnet.management.policydb.PolicyEvent;

/**
* This class specifies the policy condition for archiving the log files
* under logs directory. This condition is satisfied only when the total
* number of files under <WebNMSHome>/logs directory reaches 50. Once the
* count is reached, the method provided by this class isConditionSatisfied
* returns true. Once the condition is satisfied, the policy Action is
* is executed.
*/

public class LogsArchiveCondition  implements PolicyCondition
{


/**
* This method checks the total number of files under logs directory
* to reach 50. This method will be invoked after a particular period
* (say 5hrs). After every 5hrs, this method will be invoked . Only when this 
* method returns true, the policy action is executed. 
* @param pe Policy Event
* @return  boolean true when the condition is satisfied else false
*/
    public boolean isConditionSatisfied(PolicyEvent pe)
    {
        boolean isSatisfied=false;        
        Vector logsDirVect=NmsLogMgr.getRelativelogDirectories();
        File[] logsDirs=new File[logsDirVect.size()];
        int count=0;
        for(Enumeration en=logsDirVect.elements();en.hasMoreElements();)
        {
            logsDirs[count++]=new File(PureUtils.rootDir+File.separator+(String)en.nextElement());
        }
        count=0;
        for(int i=0;i<logsDirs.length;i++)
        {
            count+=(logsDirs[i].list()).length;
        }
        if(count < 50)
        {
            isSatisfied=false;
        }
        else
        {
            isSatisfied=true;
        }
        return isSatisfied;
    }

/**
* This method matches the various Policy action and condition with the specified
* key.
* @return String key that matches Policy condition and action is returned.
*/

    public String getKey()
    {
        return "MoveToArchives";
    }
}

