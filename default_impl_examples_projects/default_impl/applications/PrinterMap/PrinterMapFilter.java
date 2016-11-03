/* $Id: PrinterMapFilter.java,v 1.3.8.3 2013/07/05 11:11:33 aravinds Exp $ */

/* 
   PrinterMapFilter.java 
*/

package test;

import java.util.Properties;
import java.util.Vector;
import java.util.Enumeration;

import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.Node;

import com.adventnet.nms.mapdb.MapAPI;
import com.adventnet.nms.mapdb.MapFilter;
import com.adventnet.nms.mapdb.MapSymbol;
import com.adventnet.nms.mapdb.ExtendedMapFilter;

import com.adventnet.nms.severity.SeverityInfo;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;


import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 


public class PrinterMapFilter implements ExtendedMapFilter {

    String mapString = "Printers.netmap";
    

    public Vector filterMapSymbols( Vector mapSymbs, ManagedObject obj, MapAPI api ) throws NmsStorageException, UserTransactionException {



        
        if ( !( obj instanceof Printer) )
            return mapSymbs;

        Vector mapNames = createMaps( (Node) obj, api );
        if ( mapNames == null || mapNames.size() == 0 )
            return mapSymbs;

        Printer printerObj = ( Printer ) obj;

        for( int z = 0; z < mapNames.size(); z++ ) {

            String mapName = (String) mapNames.elementAt(z);
            if ( mapName == null )
                continue;
            try {
                MapSymbol mapobject = createMapObject( obj );
                mapobject.setMapName( mapName );
                mapobject.setObjType(1);
//                mapobject.setMenuName("printermenu"); //commented out to enable specifying menus from the mapIcon.data file.
                mapobject.setIconName("printer1" + NmsUtil.getImageType());
                mapobject.setUserProperty("statusInt",String.valueOf( printerObj.getStatusInt()));
                mapobject.setUserProperty("consoleDisplayText", printerObj.getConsoleDispBufferText());
                mapobject.setUserProperty("consoleLightString", printerObj.getConsoleLightString());//No I18N
                
                mapSymbs.addElement( mapobject );
            }
            catch ( Exception ex ) {
                NmsLogMgr.MAPERR.fail("Exception in PrinterMapFilter when handling object : " + printerObj.getKey() , ex);
            }
        }
        return mapSymbs;
    }


    public void update( String type, ManagedObject obj, MapAPI api ) throws NmsStorageException, UserTransactionException {



        if ( !(obj instanceof Printer) )
            return;

        Printer printerObj = ( Printer ) obj;

        try {
            Vector v = api.getSymbolNamesAssociatedWithObject("all", obj.getKey() );
            if (v != null)
            {
              for ( Enumeration e = v.elements(); e.hasMoreElements();) {

                String symbKey = (String) e.nextElement();
                if( symbKey.indexOf( mapString ) == -1 ) 
                    continue;

                int index = symbKey.indexOf("\t");
                String symbName = symbKey.substring( 0, index );
                String mapName = symbKey.substring( index+1 );

                Properties p = new Properties();
                p.put("name",symbName);
                p.put("statusInt",String.valueOf( printerObj.getStatusInt()));
                String text = printerObj.getConsoleDispBufferText();

                if (text != null && ! text.equalsIgnoreCase("null"))
                {
                    p.put("consoleDisplayText", text.trim());
                }
                text = (String)printerObj.getConsoleLightString(); //No I18N

                if(text != null && ! text.equalsIgnoreCase("null"))
                {
                    p.put("consoleLightString",text.trim()); 
                }
                api.updateSymbol(mapName,p);
              }
           }

	} catch(NmsStorageException nmse) {
	    throw nmse;
	} catch(UserTransactionException ute ) {
	    throw ute;

        } catch(Exception exp) 
        {
            NmsLogMgr.MAPERR.fail("Error in PrinterMapFilter update method "+ exp.getMessage() , exp);
            return;
        }
    }


    private Vector createMaps( Node mobj, MapAPI mapapi )  throws NmsStorageException, UserTransactionException  {



        Vector mapNames = new Vector();
        String mapName = null;

        try {

            if ( !mapapi.doesTheMapExist( mapString ) ) {
                int index = 1;
                boolean routermapexists = mapapi.doesTheMapExist("RouterMap.netmap");
                boolean switchmapexists = mapapi.doesTheMapExist("RouterMap.netmap");
                if ( routermapexists && switchmapexists )
                    index = 3;
                else if ( routermapexists || switchmapexists )
                    index = 2;
                if ( addPrinterMap( mapString, null, index, mapapi ) )
                    mapNames.addElement(mapString);
            }
            else
                mapNames.addElement( mapString );
            
            Vector parentnets = mobj.getParentNets();
            for ( int i = 0; i < parentnets.size(); i++ ) {
                String parentname = (String) parentnets.elementAt(i);
                if ( !mapapi.doesTheMapExist( parentname + ".netmap" ) ) 
                    continue;
                mapName = parentname + mapString;
                if ( mapapi.doesTheMapExist( mapName ) ) {
                    mapNames.addElement( mapName );
                    continue;
                }
                if ( addPrinterMap( mapName, parentname + ".netmap", -1, mapapi ) )
                    mapNames.addElement( mapName );
            }

	} catch(NmsStorageException nmse) {
	    throw nmse;
	} catch(UserTransactionException ute ) {
	    throw ute;

        }
        catch ( Exception someexc ) {
            NmsLogMgr.MAPERR.fail("Error in PrinterMapFilter. " + mapName + " could not be created or already exists "+someexc , someexc);
            return null;
        }
        return mapNames;
    }


    private boolean addPrinterMap ( String mapname, String parent, int index, MapAPI mapapi )  throws NmsStorageException, UserTransactionException



    {
        if ( ( mapname == null ) || ( mapapi == null ) )
            return false;

        Properties p = new Properties();
        p.put("name", mapname);
        p.put("label", "Printers");
        if ( parent != null && !parent.equalsIgnoreCase("null") )
            p.put("parentMapKey", parent );
        p.put("autoPlacement", String.valueOf( true ));
        //p.put("imageName", "world1" + NmsUtil.getImageType());
        p.put("treeIconFileName", "images/printer2" + NmsUtil.getImageType());
        p.put("webTreeIcon","printers.png");
        p.put("topology", "$grid(width=300;height=200;gapX=360;gapY=270)");
        p.put("currentTopology", "grid");
        p.put("backgroundColor","255,255,255");
        p.put("mapSymbolRenderer", "{\"html\"=\"symbol_label\",\"java\"=\"com.adventnet.nms.mapui.PrinterMapRenderer\"}");
        p.put("mapLinkRenderer", "{\"html\"=\"no_link\",\"java\"=\"com.adventnet.nms.mapui.MapLinkRendererImpl\"}");
        p.put("helpDoc","default_impl/applications/help/PrinterMap.html"); 
        p.put("HTML-TABLE","Infrastructure View");
//        p.put("helpDoc", "help/Developer_Guide/Customization/Maps/Using_Examples/mapfilter_eg.html");

        boolean added = false;

        try {

            if ( index == -1 )
                added = mapapi.addMap( mapname, p );
            else
                added = mapapi.addMap( mapname, p, index );

            if ( added )
                NmsLogMgr.MAPUSER.log("In PrinterMapFilter ." + mapname + " created successfully ", Log.SUMMARY);
            else
                NmsLogMgr.MAPERR.fail("In PrinterMapFilter ." + mapname + " could not be created. " , null);

	} catch(NmsStorageException nmse) {
	    throw nmse;
	} catch(UserTransactionException ute ) {
	    throw ute;

        }
        catch ( Exception e ) {
            NmsLogMgr.MAPERR.fail("Exception in PrinterMapFilter while trying to add " + mapname, e );
        }
        return added;
    }

    private MapSymbol createMapObject ( ManagedObject obj ) {
        MapSymbol mobj = new MapSymbol();

        String objname = obj.getKey();
        mobj.setName( objname );

        // WebNMS Field added.
        if(obj.getWebNMS()!=null)
        {
            mobj.setWebNMS(obj.getWebNMS());
        }


        mobj.setObjName( objname );
        mobj.setLabel( objname );
        mobj.setObjType( 1 );       
        if ( obj.getManaged() ) {
            mobj.setParameter("status", String.valueOf( obj.getStatus() ));
            mobj.setParameter("managed", "true");
        }
        else {
            int unknown = SeverityInfo.getInstance().getUnknown();
            mobj.setParameter("status", String.valueOf( unknown ));
            mobj.setParameter("managed", "false");
        }
        return mobj;
    }
}
