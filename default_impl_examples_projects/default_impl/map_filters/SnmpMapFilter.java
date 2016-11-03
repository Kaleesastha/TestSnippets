/*
$Id: SnmpMapFilter.java,v 1.6 2010/10/29 13:45:52 swaminathap Exp $
*/

/**
 * SnmpMapFilter.java
 */
package test;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.util.NmsLogMgr;
import java.util.*;

/** 
 * This is the interface for filtering map symbols, and
 * allows for modifying symbols before entering topology database.
 */
public class SnmpMapFilter implements ExtendedMapFilter 
{

	/** 
	 * The Vector of MapSymbols,ManagedObject, and MapAPI are passed as arguments.
	 * The returned Vector of map symbol is added to the map
	 * Note that if the class returns a null or an empty vector then there will
	 * be no symbols added for the ManagedObject
	 * As of version 2.0 Interface objects (that is objects of class IpAddress ) are 
	 * not passed to the filters .
	 * Here in this example if the ManagedObject is SNMP then we set some 
	 * additional parameters and change the menu to snmpmenu
	 * One could have added additional MapSymbols or deleted the ones passed to this
	 * This filter is declared in the configuration file conf/map.filters
	 * and so every ManagedObject this will be invoked 
	 */
	public Vector filterMapSymbols(Vector mapSymbs, ManagedObject obj, MapAPI api) 
	{
		//return if not instance of TopoObject
		if (! (obj instanceof TopoObject) ) return mapSymbs; 

		boolean isSNMP = ((TopoObject)obj).getIsSNMP();

		for (int i=0;i<mapSymbs.size();i++) 
		{
			MapSymbol mobj = (MapSymbol) mapSymbs.elementAt(i);
			//mobj.setParameter("MO_TYPE", ((TopoObject)obj).getType());//Add Object Type

			if ( mobj.getObjName() != null && mobj.getObjName().equals(obj.getName()) && ((TopoObject)obj).getIsRouter() )
		       mobj.setMenuName("routermenu,performancemenu"); //No Internationalization 

			if (isSNMP)
			{
				mobj.setParameter("INITIAL_MIBS", ((TopoObject)obj).getBaseMibs()) ; //No I18N
				mobj.setParameter("community", ((TopoObject)obj).getCommunity()) ; //No I18N
				
			}	
		}
		return mapSymbs;
	}

	/**
	 * This method will be invoked whenever there is a update in topology database. This 
	 * method handles the property updates happening in the topology database for the type, 
	 * community and baseMibs properties of any managed object and updates the same in the 
	 * corresponding property values, set for the map symbols representing that particular  
	 * Managed object, namely the MO_TYPE, COMMUNITY and INITIAL_MIBS prperties.
	 */
	public void update(String type, ManagedObject obj, MapAPI api) 
	{
		//return if not instance of TopoObject
		if (! (obj instanceof TopoObject) ) 
			return; 
		/*
		 * Here the update is done only for props like community and initial_mib.
		 * Both these properties are valid only for SNMP enabled device. 
		 * Hence if the incoming MO has isSnmp false, then we can skip the entire block.
		 * Issue : for non-snmp devices, the initial_mib read as getBaseMib() returns empty space.
		 * This empty space with be trimmed to empty string and store as null in case of oracle.
		 * When the user properties is read, the property key with value as null will never be read.
		 * Hence a duplicate entry exception will be thrown when such property will be inserted 
		 * again during updateObject notification.
		 * FIX : As in above filterMapSymbols method, use the getIsSnmp. However here only SNMP 
		 * properties matters. So can skip the flow if obj.getIsSnmp() is false. In case of any issues,
		 * remove the check here and move it to the if-else block where community and initial_mibs is set to 
		 * userProp Properties object.
		 */
		
		if(!((TopoObject)obj).getIsSNMP()) return;
		//checking for property updation in topodb
		if(!type.equals("Property Update")) 
			return;
		
		Vector symbolKeys = null;
		MapSymbol symb = null;
		try 
		{
			//getting all symbols representing the MO 
			symbolKeys = api.getSymbolNamesAssociatedWithObject("symbol",obj.getName());//No I18N 
			if(symbolKeys == null || symbolKeys.size() == 0)
			{
				return;
			}
			symb = (MapSymbol)api.getObject((String)symbolKeys.elementAt(0));
		} 
		catch ( Exception ex ) 
		{
			NmsLogMgr.MAPERR.fail("Exception in SnmpMapFilter "+ex , ex);
		}
		Properties updateProps = new Properties();
		Properties userProp = symb.getUserProperties();
		if (userProp != null && userProp.size() > 0)
		{
			/*
			String mo_type = (String) userProp.get("MO_TYPE");
			if ((mo_type == null) || (mo_type != null && ! (mo_type.equals(((TopoObject)obj).getType()))))
				updateProps.put("MO_TYPE", ((TopoObject)obj).getType());
			*/	
			String init_mibs = (String) userProp.get("INITIAL_MIBS");
			if ((init_mibs == null) || (init_mibs != null && ! (init_mibs.equals(((TopoObject)obj).getBaseMibs())))) 
			{
				String baseMibs = ((TopoObject)obj).getBaseMibs();
				if(baseMibs != null)
				{
					updateProps.put("INITIAL_MIBS", ((TopoObject)obj).getBaseMibs());
				}
			}

			String community = (String) userProp.get("community");//No I18N
			if((community == null) || (community != null && ! (community.equals(((TopoObject)obj).getCommunity()))))
			{
				updateProps.put("community", ((TopoObject)obj).getCommunity());//No I18N
			}
		}
		else 
		{
			//updateProps.put("MO_TYPE", ((TopoObject)obj).getType());
			String baseMibs = ((TopoObject)obj).getBaseMibs();
			if(baseMibs!=null)
			{
				updateProps.put("INITIAL_MIBS", ((TopoObject)obj).getBaseMibs());
			}
			else
			{
				updateProps.put("INITIAL_MIBS","");
			}
			updateProps.put("community", ((TopoObject)obj).getCommunity());//No I18N
		}

		if (updateProps.size() == 0)
			return;

		try 
		{
			updateProps.put("name", symb.getName());
			api.updateSymbol(symb.getMapName(), updateProps);
		} 
		catch ( Exception ex )
		{
			NmsLogMgr.MAPERR.fail("Exception in SnmpMapFilter "+ex , ex);
		}
			
		for (int i = 1; i < symbolKeys.size(); i++) 
		{ 
			try 
			{
				symb = (MapSymbol) api.getObject((String)symbolKeys.elementAt(i)); 
				Properties props = (Properties) updateProps.clone();
				if(symb!=null)
				{
					props.put("name", symb.getName());
					api.updateSymbol(symb.getMapName(), props);
				}
			}
			catch (Exception ex)
			{
				NmsLogMgr.MAPERR.fail("Exception in SnmpMapFilter "+ex , ex);
			}
		}
	}	
}


