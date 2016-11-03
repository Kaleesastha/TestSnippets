/* $Id: LabelUpdateFilter.java,v 1.2 2008/07/08 13:02:44 johnpaul Exp $ */

/**
 * LabelUpdateFilter.java
 *
 *
 * Created: Tue Jan 22 11:56:19 2002
 *
 * @version
 */

package test;

import java.util.*;
import java.rmi.RemoteException;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.util.NmsLogMgr;

import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 

public class LabelUpdateFilter implements ExtendedMapFilter
{
     //If any update happens this method will be called.
    public void update (String type, ManagedObject obj, MapAPI api) throws NmsStorageException, UserTransactionException
    {

        
        if( type.indexOf("Property") != -1)
        {
        
            Vector symbolKeys=null;
            try
            {
                //To get all the Map Symbols representing a particular ManagedObject.
                symbolKeys = api.getSymbolNamesAssociatedWithObject("symbol",obj.getName());//No I18N 
            }
            catch(RemoteException e)
            {
                NmsLogMgr.MAPERR.fail("Exception in LabelUpdateFilter while getting the map symbol keys " + obj, e);
            }
            String dispName=null;
            MapSymbol symb=null;
            int vecSize=symbolKeys.size();
             //Size is equal to the no of maps in which the particular symbol existing
         	    if(vecSize!=0)
             {
            for (int i = 0; i <vecSize; i++)
            {

                try
                {
                     //To get the MapSymbol object from the provided key of the object.
                    symb = (MapSymbol)api.getObject((String)symbolKeys.elementAt(i)); 
                }

                catch(RemoteException e)
                {
                    NmsLogMgr.MAPERR.fail("Exception in LabelUpdateFilter while getting the map symbol" + obj, e);
                }
                dispName=obj.getDisplayName();

                if(!dispName.equals(symb.getLabel()))
                {
                    Properties prop = new Properties();
                    prop.put("name", symb.getName()); //The symbol for which you would like to change the property.
                    prop.put("label", dispName); //To change the label with the displayName.
                    
                    try
                    {
                        //To update the symbol with the current properies.
                        api.updateSymbol(symb.getMapName(), prop); 
                    }
                    catch(RemoteException  e)
                    {
                        NmsLogMgr.MAPERR.fail("Exception in LabelUpdateFilter while updating  the map symbol" + obj, e);
                        
                    }
                }
            }
        }
        }
        else
        {
            return;
        }
        
    }
    public Vector filterMapSymbols(java.util.Vector symb, ManagedObject obj, MapAPI api)
    {
        
        return symb;
    }

    
}







