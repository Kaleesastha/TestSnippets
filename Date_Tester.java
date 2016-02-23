import java.util.*;
import com.adventnet.management.log.SystemUtil;
import com.adventnet.nms.util.NmsUtil;

public class DateTester
{
	public static void main(String[] args) throws Exception
	{
		SystemUtil.cout.println(NmsUtil.NmsFormatter.format(new Date()));

	}
}
