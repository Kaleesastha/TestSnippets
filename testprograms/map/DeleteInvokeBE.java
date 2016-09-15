package com.adventnet.nms.topodb;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.UserClassInterface;


public class DeleteInvokeBE
    implements UserClassInterface
{

    public DeleteInvokeBE()
    {
    }

    public String getResultString(String as[])
    {
        if(as == null || as.length == 0)
            return NmsUtil.GetString("Error - Please choose an object to Delete");
			TopoAPI topoApi = null;
			try
			{
				topoApi = (TopoAPI) NmsUtil.getAPI ("TopoAPI");
				System.out.println( "Successfully got the handle for TopoAPI");
				for(int i=0; i< as.length; i++)
				{
				if(!as[i].equals("EOP"))
				{
			    boolean a = topoApi.deleteObjectAndSubElements(as[i]);
				System.out.println( "Successfully deleted the symbol "+as[i]+" and the return type is "+a);
				}
				}
			}
			catch (Exception remoteException)
			{
				System.out.println( "Error in getting the handle for TopoAPI");
			}
			return "";
            
    }
}
