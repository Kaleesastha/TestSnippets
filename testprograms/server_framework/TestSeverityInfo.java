import com.adventnet.nms.severity.*;
import com.adventnet.nms.util.*;
import java.util.*;
public class TestSeverity
{
    public static void main(String arg[])
    {
	/* Please to change the file path at the time of testing*/
        String url="file:/home/rameshj/apr18/AdventNet/WebNMS/conf/SeverityInfo.conf";
        SeverityInfo sevInfo= SeverityInfo.getInstance(url);
        Vector vect=sevInfo.getNames(SeverityInfo.EXCLUDE_NO_CRITICALITY);
	System.out.println("\nTestSeverity");
	System.out.println("--------------\n");

	for(Enumeration en=vect.elements();en.hasMoreElements();)
	{   
	   String name=(String)en.nextElement();
	   System.out.println(" Name  : "+name+ "   Value  " + Integer.toString(sevInfo.getValue(name)));
	}
	System.out.println("\n\n Test to getSeverity Values");
	System.out.println(" ------------------------------");
	System.out.println(" Clear Value is : "+sevInfo.getClear());
	System.out.println(" Unknown Value is : "+sevInfo.getUnknown());
	System.out.println(" Info Value is : "+ sevInfo.getInfo());
	boolean sam=sevInfo.isDescending();
	System.out.println(sam);


    }
    

}
