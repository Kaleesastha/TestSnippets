// $Id: ExampleDispatcher.java,v 1.1 2004/05/24 14:54:15 sujithr Exp $

/**
 * ExampleDispatcher.java
 *
 *
 * Created: Thu Apr 18 20:47:13 2002
 *
 * @author <a href="mailto: "Chitrapandian N</a>
 * @version
 */

package txn;

import java.util.*;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.store.relational.RelationalAPI;

public class ExampleDispatcher implements Observer
{
    RelationalAPI relapi = NmsUtil.relapi;
    public ExampleDispatcher ()
    {
        System.out.println(" === === === ExampleDispatcher Constructor called === === === ");
    }

    private boolean ncm38 = false;
    private boolean ncm39 = false;
    private boolean ncm40 = false;
    private boolean ncm40_1 = false;
    private boolean ncm43 = false;
    private boolean ncm43_1 = false;
    private boolean ncm44 = false;
    private boolean ncm44_1 = false;

    public void update(Observable o, Object arg) 
    {
        if(relapi.isInTransaction())
        {
            if (arg instanceof Vector) 
            {
                String type = (String) ((Vector) arg).elementAt(0);
                Object name = (Object) ((Vector) arg).elementAt(1);	
                String result = ((NotificationObject)name).getType();
                System.out.println(" ExampleDispatcher(Vec) = NOTIFY_TIP Received : "+type);
                System.out.println(" ExampleDispatcher(Vec) = NOTIFY_TIP Received : "
                                   +name.toString());
                if(type.equals("Added") && result.equals("DB-TXN-NCM-026"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-026 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-027"))
                {
                    System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-027 :: PASSED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-029"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-029 :: PASSED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-030"))
                {
                    System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-030 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-031"))
                {
                    System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-031 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-032"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-032 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-033"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-033 :: PASSED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-034"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-034 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-035"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-035 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-036"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-036 :: PASSED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-037"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-037 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-038"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-038 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-039"))
                {
                    if(ncm39)
                    {
                        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-039 :: PASSED");
                    }
                    else
                    {
                        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-039 :: FAILED");
                    }
                    ncm39 = false;
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-040"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-040 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-043"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-043 :: FAILED");
                }
                else if(type.equals("Updated") && result.equals("DB-TXN-NCM-043"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-043 :: FAILED");
                }
                else if(type.equals("Deleted") && result.equals("DB-TXN-NCM-043"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-043 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-044"))
                {
                    ncm44 = true;
                }
                else if(type.equals("Updated") && result.equals("DB-TXN-NCM-044"))
                {
                    if(ncm44)
                    {
                        ncm44_1 = true;
                    }
                }
                else if(type.equals("Deleted") && result.equals("DB-TXN-NCM-044"))
                {
                    if(ncm44 && ncm44_1)
                    {
                        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-044 :: PASSED");
                    }
                    else
                    {
                        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-044 :: FAILED");
                    }
                    ncm44 = false;
                    ncm44_1 = false;
                }
            }
            else
            {
                String result = ((NotificationObject)arg).getType();
                System.out.println(" ExampleDispatcher(Obj) = NOTIFY_TIP  Received : "
                                   +result);
                if(result.equals("DB-TXN-NCM-014"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-014 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-015"))
                {
                    System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-015 :: PASSED");
                }
                else if(result.equals("DB-TXN-NCM-017"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-017 :: PASSED");
                }
                else if(result.equals("DB-TXN-NCM-018"))
                {
                    System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-018 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-019"))
                {
                    System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-019 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-020"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-020 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-021"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-021 :: PASSED");
                }
                else if(result.equals("DB-TXN-NCM-022"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-022 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-023"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-023 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-024"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-024 :: PASSED");
                }
                else if(result.equals("DB-TXN-NCM-025"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-025 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-038"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-038 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-039"))
                {
                    ncm39 = true;
                }
                else if(result.equals("DB-TXN-NCM-040"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-040 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-040.1"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-040 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-041"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-041 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-041.1"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-041 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-042"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-042 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-042.1"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-042 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-045"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-045 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-046"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-046 :: PASSED");
                }
                else if(result.equals("DB-TXN-NCM-047"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-047 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-048"))
                {
                    System.out.println(" Txn_Notify_048 Testcase : DB-TXN-NCM-048 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-049"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-049 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-050"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-050 :: FAILED");
                }
            }
        }
        else
        {
            if (arg instanceof Vector) 
            {
                String type = (String) ((Vector) arg).elementAt(0);
                Object name = (Object) ((Vector) arg).elementAt(1);	
                String result = ((NotificationObject)name).getType();
                System.out.println(" ExampleDispatcher(Vec) = NOTIFY_TC Received : "+type);
                System.out.println(" ExampleDispatcher(Vec) = NOTIFY_TC Received : "
                                   +name.toString());
                if(type.equals("Added") && result.equals("DB-TXN-NCM-026"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-026 :: PASSED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-027"))
                {
                    System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-027 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-029"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-029 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-030"))
                {
                    System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-030 :: PASSED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-031"))
                {
                    System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-031 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-032"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-032 :: PASSED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-033"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-033 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-034"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-034 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-035"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-035 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-036"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-036 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-037"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-037 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-038"))
                {
                    if(ncm38)
                    {
                        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-038 :: PASSED");
                    }
                    else
                    {
                        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-038 :: FAILED");
                    }
                    ncm38 = false;
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-039"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-039 :: FAILED");
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-040"))
                {
                    if(ncm40)
                    {
                        ncm40_1 = true;
                    }
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-043"))
                {
                    ncm43 = true;
                }
                else if(type.equals("Updated") && result.equals("DB-TXN-NCM-043"))
                {
                    if(ncm43)
                    {
                        ncm43_1 = true;
                    }
                }
                else if(type.equals("Deleted") && result.equals("DB-TXN-NCM-043"))
                {
                    if(ncm43 && ncm43_1)
                    {
                        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-043 :: PASSED");
                    }
                    else
                    {
                        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-043 :: FAILED");
                    }
                    ncm43 = false;
                    ncm43_1 = false;
                }
                else if(type.equals("Added") && result.equals("DB-TXN-NCM-044"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-044 :: FAILED");
                }
                else if(type.equals("Updated") && result.equals("DB-TXN-NCM-044"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-044 :: FAILED");
                }
                else if(type.equals("Deleted") && result.equals("DB-TXN-NCM-044"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-044 :: FAILED");
                }
            }
            else
            {
                String result = ((NotificationObject)arg).getType();
                System.out.println(" ExampleDispatcher(Obj) = NOTIFY_TC  Received : "
                                   +result);
                if(result.equals("DB-TXN-NCM-014"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-014 :: PASSED");
                }
                else if(result.equals("DB-TXN-NCM-015"))
                {
                    System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-015 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-017"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-017 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-018"))
                {
                    System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-018 :: PASSED");
                }
                else if(result.equals("DB-TXN-NCM-019"))
                {
                    System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-019 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-020"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-020 :: PASSED");
                }
                else if(result.equals("DB-TXN-NCM-021"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-021 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-022"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-022 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-023"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-023 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-024"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-024 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-025"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-025 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-038"))
                {
                    ncm38 = true;
                }
                else if(result.equals("DB-TXN-NCM-039"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-039 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-040"))
                {
                    ncm40 = true;
                }
                else if(result.equals("DB-TXN-NCM-040.1"))
                {
                    if(ncm40 && ncm40_1)
                    {
                        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-040 :: PASSED");
                    }
                    else
                    {
                        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-040 :: FAILED");
                    }
                    ncm40 = false;
                    ncm40_1 = false;
                }
                else if(result.equals("DB-TXN-NCM-041"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-041 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-041.1"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-041 :: PASSED");
                }
                else if(result.equals("DB-TXN-NCM-042"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-042 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-042.1"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-042 :: PASSED");
                }
                else if(result.equals("DB-TXN-NCM-045"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-045 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-046"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-046 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-047"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-047 :: FAILED");
                }
                else if(result.equals("DB-TXN-NCM-048"))
                {
                    System.out.println(" Txn_Notify_048 Testcase : DB-TXN-NCM-048 :: PASSED");
                }
                else if(result.equals("DB-TXN-NCM-049"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-049 :: PASSED");
                }
                else if(result.equals("DB-TXN-NCM-050"))
                {
                    System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-050 :: FAILED");
                }
            }
        }
    }


}   


// ExampleDispatcher
