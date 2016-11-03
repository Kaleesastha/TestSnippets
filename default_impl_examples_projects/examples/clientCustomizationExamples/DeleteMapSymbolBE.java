//$Id: DeleteMapSymbolBE.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package com.adventnet.nms.example;
/** java imports *
  */
import java.net.*;
import java.rmi.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.util.*;

 /**
  * This class gives an example for invoke classonbe.
  * When this file is invoked from client this class  
  * will delete the selected mapsymbol or link using MapAPI.
  * by the WebNMS.
  */
public class DeleteMapSymbolBE implements com.adventnet.nms.util.UserClassInterface
{
    public String getResultString(String[] s)
    {

        MapAPI api=null; 
        try
            {
                api = com.adventnet.nms.mapdb.MapManager.mapapi; 
            }
        catch( Exception err )
            {
                err.printStackTrace();
                return null;
            }


        try
            {
               
                /** Bydefault this method tries to delete the selected object. 
                  * If the selected object is an symbol it will get deleted and 
                  * return true otherwise it will return false
                  * If the returned boolean is false selected object will be deleted 
                  * using deleteLink method assuming it has a link. 
                  * If it is not an link this method will not  delete the object
                  * and it will return false. 
                  */
                boolean bool =  api.deleteSymbol(s[0],s[1]);//updates the selected map using mapAPI, by changing its label to TEST
                if(bool)
                    return NmsClientUtil.GetString("sucess in removal of selected node ")+s[1];
                else
                    {
                    bool =  api.deleteLink(s[0],s[1]);
                      if(bool)
                          return NmsClientUtil.GetString("sucess in removal of selected Link ")+s[1];
                      else
                          return NmsClientUtil.GetString("Failure in removal of selected link ")+s[1];
                     }
            }
        catch(Exception ex)
            { 
                return NmsClientUtil.GetString("Error in removal Operation ")+s[1]+" "+ex.toString();
            }
    }
}







