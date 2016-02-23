import java.util.*;
import com.adventnet. nms.util.*;
import com.adventnet.nms.mapui.*;

public class RemoveEventNode implements CustomClassInterface
{
    public void setProperties(Properties[] prop)
    {
        String[] nodeId = {"Events"};
        NmsUiAPI.removeNodeFromTree(nodeId,null,true);
    }
}
