/*
$ Id $
*/
/**
 *@(#)GetMapFunctions.java
 */

import java.rmi.*;
import java.util.*;
import com.adventnet.nms.mapdb.*;
import java.lang.*;
/**
 * This GetMapFunctins class used  for accessing, updating the Map Objects in the  map database  by using the MapAPI interface. 
 * This API is accessible either through RMI or directly from the same JVM. When RMI is enabled,
 *i.e. by running RMI registry before the NMS server is started, the MapAPI can be accessed 
 * remotely via RMI. It is published with RMI handle /MapAPI on the server. 
 * here "hostname" should be the name of the machine where the nms server is running.
 *
 */
public class GetMapFunctions
{    
    MapAPI api=null;
    static String hostname;
    /**
     *The GetMapFunctions constructor used to get the MapAPI handle via RMI
     *and use it for the following methods.
     */
    GetMapFunctions()
    {
	//hostname = "localhost";
//	hostname = "pitchaimani";
	getMapApi();
    }
    //this method is used  to set the hostname 
    public static  void setHostName(String name)
    {
	if((name!=null)&&!(name.trim().equals("")))
	{
	    hostname = name;
	}
	else
	    hostname ="localhost";
	return;
    }
    //
    
    public void getMapApi()// to get map api
    {     
	
	try
	{
	    api= (MapAPI)Naming.lookup("rmi://"+hostname+"/MapAPI");
	}
	catch(Exception e)
	{
	    System.out.println("error in lookup "+e);
	    // e.printStackTrace();
	}
    }   
	
    //to get the list maps available
      //this is for getMapNamelist 
    /**
     * To get the MapNames from the server.
     * using the getMapNames API call.
     * @see com.adventnet.nms.mapdb.MapAPI#getMapNames()  
     * @return	Returns String array of MapNames.
     */
    
    public String[] getMapNameList()
    {  
	

	String[] s= null;
	int size=0;
	Vector list = null;
	try
	{
	    list = api.getMapNames();
	}
	catch(Exception e)
	{
	    System.out.println("error in getting list of map");
	}
	
	size = list.size();
	//here we are changing the VECTOR into String array//
	try
	{
	    s= new String[size];
	    //to get number map names
	    for(int i=0;i<size;i++)
	    {  
		s[i] = (String)(list.elementAt(i));
	    }
	}
	catch(Exception e)
	{
	    System.out.println("error in source");
	}

	return s;
    }
 // this is for adding default map
    /**To add a defaultmap into the server.
     * using the addMap API call.
     * @see com.adventnet.nms.mapdb.MapAPI#addMap(String mapname,Properties p)  
     * @param  mapname  name of the map .The mapname should be unique and ends with <code> .netmap </code>
     
     * @param  p		properties of the map
     * @return	true if the map is succesfully added
     */

    public boolean addDefaultMap(String mapname,Properties p)
    {
	
	boolean b =false;
	try
	{
	    b = api.addMap(mapname,p);
	}
	catch(Exception e)
	{
	    System.out.println("Error in add map");
	}
	return b;
    }
    //this is for add custom map 
    /**
     * To add a Custom map into the server.
     * using the addCustomMap API call.
     * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @see com.adventnet.nms.mapdb.MapAPI#addCustomMap(String s,Properties p,Properties cp)  
     * @param  p			properties of the map
     * @param customproperties	this is the criteria to be specified 	
     * @return	Returns true ,if the map is succesfully added
     */
    public boolean addSelectedCustomMap(String mapname,Properties p,Properties Customproperties)
    {
	boolean b=false;
	try
	{
	    b = api.addCustomMap(mapname,p,Customproperties);
	}
	catch (Exception e)
	{
	    System.out.println("error in add custom map");
	}
	return b;
    }
    /** To delete a Map from the server. Can be used for deleting both normal and custom maps  .
     *using the deleteMapAPI call.
     * @see com.adventnet.nms.mapdb.MapAPI#deleteMap(String s)  
     * @param  s 	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @return	Returns true if the Map is sucessfully deleted 
     */
    public boolean deleteSelectedMap(String s)
    {


	boolean b= false;
	try
	{
	    b = api.deleteMap(s);
	}
	catch(Exception e)
	{
	    System.out.println("Error in delete map");
	}
	return b;
    }
    /**To get the properties of the selected map from the server.
     * using the getMap API call return MapDB Object.
     *
     * @see com.adventnet.nms.mapdb.MapAPI#getMap(String s)  
     * @param  s 	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @return	Returns MapDB Object.
     * @see com.adventnet.nms.mapdb.MapDB#getProperties()
     * @return Returns Properties of the map
     */
 public Properties getPropertiesofSelectedMap(String s)
    {

	Properties p= new Properties();

	try
	{
	    
	    // for test the update map
	    MapDB map1=null;
	    map1 = api.getMap(s);
	    if (map1 != null)
	     p = map1.getProperties();
	}
	catch(Exception e)
	{
	    System.out.println("Error in update map");
	    //e.printStackTrace();
	}
	return p;
    }
    //update the map
    /**To update the map into the server.
     * using the updateMap API call.
     * @see com.adventnet.nms.mapdb.MapAPI#update(String s,Properties p)  
     * @param  s 	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @param  p			properties of the map
     * @return Returns true if the map is sucessfully updated
     */

    public boolean updateMap(String mapname, Properties p)
    {
	boolean b =false;
	if(p!=null)
	   {
	       try
	       {
		  b= api.updateMap(mapname,p);
	       }
	       catch(Exception e)
	       {
		   System.out.println("ERROR in updating the map");
	       }
	   }
	return b;
     }

    // this is for adding  mapsymbol
/**
 * To add a Symbol to a map.
 * using the addSymbol API call.
 * @see  com.adventnet.nms.mapdb.MapAPI#addSymbol(String mapname,Properties p) 
 * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
 * @return true if the Map Symbol is added to the specified Map.	
 */

    public boolean addMapSymbol(String mapname,Properties p)
    {

	boolean b =false;
	
	try
	{
	b =api.addSymbol(mapname,p);
	}
	catch(Exception e)
	{
	    System.out.println("Error in add map");
	}
	return b;
      }
  
   
    /** To delete a SelectedSymbol on a map. 
     * using the deleteSymbolAPI call.
     * @see com.adventnet.nms.mapdb.MapAPI#deleteSymbol(String mapname,String name)
     * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @param name		MapSymbol name. 
     * @return true if the Mapsymbol is Deleted .	
     */
    public boolean deleteSelectedSymbol(String mapname,String symbolname )
    {
	boolean b =false;
	try
	{
	   b =  api.deleteSymbol(mapname,symbolname);
	}
	catch(Exception e)
	{
	    System.out.println("Error in delete map");
	}
	return b;
    }
    /** To get properties of a symbol. 
     * using the GetSymbolProperties API call.
     * @see com.adventnet.nms.mapdb.MapAPI#getSymbolProperties(String mapname,String name)
     * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @param name		MapSymbol name or name of an object which extends MapSymbol. 
     * @return Properties of the MapSymbol. Returns <b>null</b> if the symbol doestn't exist.	
     */ 
    
    public Properties getPropertiesofSymbol(String mapname,String symbolname)
    {
	
	Properties p= new Properties();

	try
	{
	    p = api.getSymbolProperties(mapname,symbolname);
	}
	catch(Exception e)
	{
	    System.out.println("Error in getting the propertiesof symbol");
	}
	return p;
    }
    //for update map symbol
    	
/**
 * To update a Symbol on a map. 
 * using the updateSymbol API call.
 * @see com.adventnet.nms.mapdb.MapAPI#updateSymbol(String mapname,Properties p)
 * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
 * @param p		MapSymbol properties .
 * @return true if the Map Symbol is updated.
 */
    public boolean updateSymbol(String mapname, Properties p)
    {

	boolean b =false;
	if(p!=null)
	   {
	       try
	       {

		  b= api.updateSymbol(mapname,p);
	       }
	       catch(Exception e)
	       {
		   System.out.println("ERROR in updating the symbol"+e);
	       }
	   }
	return b;
     }
    //for adding the map link
    
  /**
   * To add a link to a map. 
   *using the addLink API call.
   * @see com.adventnet.nms.mapdb.MapAPI#addLink(String mapname,Properties p)
   * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
   *<pre>
   * @param p		MapLink properties .The following are the additional Properties 
   * of MapLink  apart from those it inherits from MapSymbol.
   * <b>PROPERTIES</b> 			<b> DESCRIPTION</B>
   * source			Name of the Source MapSymbol 
   * dest			Name of the Destination MapSymbol
   * nx			x-coordinate of destination 
   * ny			y-coordinate of destination 
   * thickness		Thickness of the link in terms of pixels.
   </pre>
   * @return true if the Map Link is added to the specified Map.	
   */ 

    public boolean addMapLink(String mapname,Properties p)
    {

	boolean b =false;
	try
	{
	b =api.addLink(mapname,p);
	}
	catch(Exception e)
	{
	    System.out.println("Error in add map link");
	}
	return b;
      }
    /** To delete a selected link on a map. 
     * using the deleteLink API call.
     * @see com.adventnet.nms.mapdb.MapAPI#deleteLink(String mapname,String name)
     * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @param name		MapLink  name. 
     * @return true if the Link  is Deleted .	
     */
	
    public boolean deleteSelectedLink(String mapname,String linkname )
    {
	boolean b =false;
	try
	{
	   b =  api.deleteLink(mapname,linkname);
	}
	catch(Exception e)
	{
	    System.out.println("Error in delete map link");
	}
	return b;
    }
    
    /** To get properties of a link. 
     * using the getLinkProperties API call.
     * @see com.adventnet.nms.mapdb.MapAPI#getLinkProperties(String mapname,String name)
     * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @param name		MapLink   name. 
     * @return Properties of the MapLink . Returns <b>null</b> if the link doestn't exist.	
     */
 public Properties getPropertiesOfLink(String mapname,String linkname)
    {

	Properties p= new Properties();
	try
	{
	    p = api.getLinkProperties(mapname,linkname);
	}
	catch(Exception e)
	{
	    System.out.println("Error in getting the propertiesof symbol");
	}
	return p;
    }
    /**
     *  To update a link on a map.
     * using the updateLink API call.
     * @see com.adventnet.nms.mapdb.MapAPI#updateLink(String mapname,Properties p)
     * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @param p		MapLink properties .
     * @return true if the link is updated .	
     */
     public boolean updateLink(String mapname, Properties p)
     {
	boolean b =false;
	if(p!=null)
	   {
	       try
	       {

		  b= api.updateLink(mapname,p);
	       }
	       catch(Exception e)
	       {
		   System.out.println("ERROR in updating the map");
	       }
	   }
	return b;
     }
    //for adding the container
    /**
     * To add a Container into a map.
     * using the addContainer API call.
     * @see com.adventnet.nms.mapdb.MapAPI#addContainer(String mapname,Properties p)
     * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @param p		MapContainer properties .Other than MapSymbol properties ,MapContainer can take an extra property called layout, which says the topology of his children.
     * @return true if the Map Container is added into the specified Map.	
     */
    public boolean addContainer(String mapname,Properties p)
    {
	boolean b =false;
	try
	{
	b =api.addContainer(mapname,p);
	}
	catch(Exception e)
	{
	    System.out.println("Error in add Container");
	}
	return b;
      }
  
    //for deleting the container
    /**
     * To delete a Container on a map.
     * using the deleteContainer API call.
     * @see com.adventnet.nms.mapdb.MapAPI#deleteContainer(String mapname,String name)
     * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @param name		MapContainer name.
     * @return true if the Map Container is Deleted on the specified Map.	     */
    public boolean deleteContainer(String mapname,String container)
    {
	boolean b =false;
	try
	{
	    b =  api.deleteContainer(mapname,container);
	}
	catch(Exception e)
	{
	    System.out.println("Error in delete container"+e);
	}
	return b;
    }

/**
 * To get the container properties.
 * using the getObject API call.
 * @see com.adventnet.nms.mapdb.MapAPI#getObject(String container)
 * @param  container name + "\t" +mapName for MapSymbol,MapContainer and MapLink
 * @return java.lang.Object 	
 */
    
 public Properties getContainerProperties(String mapname,String container)
    {
	Properties p= new Properties();
	try
	{
	     p = api.getSymbolProperties(mapname,container);
	}
	catch(Exception e)
	{
	    System.out.println("Error in getting the propertiesof container"+e);
	}
	return p;
    }
    //for up date the container

    /**
     *To update the selected container on the map.
     * using the updateContainer API call. 
     * The Map container to be updated is identified by the "name" in the properties and mapname.
     * @see com.adventnet.nms.mapdb.MapAPI#updateContainer(String mapname,Properties p)
     * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @param p		MapContainer properties .
     * @return	true if the MapContainer is updated.
     */
    public boolean updateContainer(String mapname, Properties p)
    {
	boolean b =false;
	if(p!=null)
	{
	    try
	    {
		b= api.updateContainer(mapname,p);
	    }
	    catch(Exception e)
	    {
		System.out.println("ERROR in updating the container"+e);
	    }
	}
	return b;
    }
    //for adding the group
    /** To add a group into a map. 
     * using the addGroup API call.
     * @see com.adventnet.nms.mapdb.MapAPI#addGroup(String mapname,Properties p)
     * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @param p		MapGroup properties .There are no additional Properties for MapGroup apart from those it inherits from MapSymbol.
     * @return true if the Map Group is added to the specified Map.	
     */
    public boolean addGroup(String mapname,Properties p)
    {
	boolean b =false;
	try
	{
	b =api.addGroup(mapname,p);
	}
	catch(Exception e)
	{
	    System.out.println("Error in add Group"+e);
	}
	return b;
      }
  
    //for deleting the group
    /** To delete a group on a map. 
     * using the deleteGroup API call.
     * @see com.adventnet.nms.mapdb.MapAPI#deleteGroup(String mapname,String groupname)
     * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @param groupname		MapGroup name. 
     * @return true if the Group is Deleted .	
     */
    public boolean deleteGroup(String mapname,String group)
    {
	boolean b =false;
	try
	{
	   b =  api.deleteGroup(mapname,group);
	}
	catch(Exception e)
	{
	    System.out.println("Error in delete group"+e);
	}
	return b;
    }
    
/**
 * To get the group properties.
 * using the getObject API call.
 * @see com.adventnet.nms.mapdb.MapAPI#getObject(String groupname)
 * @param  groupname name + "\t" +mapName for MapSymbol,MapContainer and MapLink
 * @return java.lang.Object 	
 */
    public Properties getGroupProperties(String mapname,String group)
    {
	Properties p= new Properties();
	try
	{
	     p = api.getSymbolProperties(mapname,group);
	}
	catch(Exception e)
	{
	    System.out.println("Error in getting the propertiesof group");
	}
	return p;
    }
    //for up date the group

    /** To update a group on a map.
     * using the updateGroup API call.
     * @see com.adventnet.nms.mapdb.MapAPI#updateGroup(String mapname,Properties p) 
     * "name" property name in the second argument 
     * @param  mapname  	name of the map .The mapname should be unique and ends with <b> .netmap </b>
     * @param p		Mapgroup properties .
     * @return true if the Group is updated .	
     */
    public boolean updateGroup(String mapname, Properties p)
    {
	boolean b =false;
	if(p!=null)
	   {
	       try
	       {
		  b= api.updateGroup(mapname,p);
	       }
	       catch(Exception e)
	       {
		   System.out.println("ERROR in updating the group");
	       }
	   }
	return b;
    }
    public Vector getCustomMapNames()
    {
	Vector names = new Vector();
	try
	{
	    names = api.getCustomMapNames();
	}
	catch(Exception e)
	{
	    System.out.println("Error in getting custommapnames"+e);
	}
	return names;
    }
    public Vector getDefaultmapNames()
    {
	Vector defaultMapNames = new Vector();
	try
	{
	    defaultMapNames = api.getDefaultMapNames();
	}
	catch(Exception e)
	{
	    System.out.println("Error in getting custommapnames"+e);
	}
	return defaultMapNames;
    }

	public boolean doesTheMapExist(String map)
	{
		boolean b =false;
		try
		{
			b = api.doesTheMapExist(map);
		}
		catch(Exception e)
		{
			System.out.println("Error in getting custommapnames"+e);
		}
		return b;
	}
	//this method will return the symbol names of the map
	public Vector getSymbolNames(String mapName)
	{
		Vector SymbolNames = null;
		try
		{
			SymbolNames = api.getSymbolNames(mapName);
		}
		catch(Exception e)
		{
			System.out.println("Error in geting the symbol names"+e);	
		}
		return SymbolNames;
	}
	//this method will return the mapsymbol names associated with the Object name
	public Vector getSymbolNamesAssociatedWithObject(String objName)
	{
		Vector SymbolNames = null;
		try
		{
			SymbolNames = api.getSymbolNamesAssociatedWithObject("all",objName);
		}
		catch(Exception e)
		{
			System.out.println("Error in geting the symbol names associated with objname"+e);	
		}
		return SymbolNames;
	}
}
