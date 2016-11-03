/*
 * Copyright (c) AdventNet, Inc.,1999. All Rights Reserved
 * file:   NamingServiceClientUtil.java
 * @author: Sreenivas Kanumuru
 *
 * Provides utility methods to interact with CORBA Naming Service. 
 * Can be applied both on client and server side.
 *  
 */


// CORBA imports
import org.omg.CORBA.SystemException;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.NamedValue;
import org.omg.CORBA.ORB;

public class NamingServiceClientUtil
{
  /** Gets the reference to the NamingContext from the ORB using 
    * resolve_initial_references(). 
    *
    * NOTE : using 'resolve_initial_references()' requires that the 
    * NS host and port to be passed as parameters when ORB is 
    * initialized. 
    *
    * The NamingService root context name differs across vendors.
    * Iona's ORBIX: "NS"   (default can be changed in OrbixWeb.properties)
    * JavaSoft's JavaORB: "NamingService"
    *
    * @param orb reference to the initialized ORB. 
    * @param ns_name NmaingService root context name 
    */
  public static NamingContext getRootContext(ORB orb, String ns_name) {
    NamingContext rootContext=null;
    //
    // Find Naming service Context 
    //
    try {
       org.omg.CORBA.Object initNCRef = orb.resolve_initial_references(ns_name);
       rootContext = NamingContextHelper.narrow(initNCRef);
    }
    catch (SystemException se) { 
       System.err.println("NSServerUtil: Exception during resolve initial reference to the Naming Service : ");
    } 
    catch (org.omg.CORBA.ORBPackage.InvalidName in) {
       System.err.println("NSServerUtil: Exception while resolving NS initial reference (i.e., root NamingContext) ");
    }
    return rootContext;
  } 

  /** Gets the reference to the specified NamingContext from the ORB.
    * @param ncArray Array of name components identifying the required NamingContext 
    * @param orb reference to the initialized ORB. 
    * @param rootContextName NmaingService root context name 
    * @param contextNameDebug A string value containing the NamingContext identity; 
    *                         used for debug purposes 
    */
  public static NamingContext getContextByName(NameComponent []ncArray, ORB  orb, String rootContextName, String contextNameDebug) {

     
    NamingContext reqContext=null;
      
    try {
       reqContext= NamingContextHelper.narrow(getRootContext(orb,rootContextName).resolve(ncArray));
    } catch (InvalidName se) {
          System.err.println("NamingServiceClientUtil: Exception in getContextByName() for " + contextNameDebug + 
                          " context - InvalidName : ");
    } catch (CannotProceed se) {
          System.err.println("NamingServiceClientUtil: Exception in getContextByName() for " + contextNameDebug + 
                          " context  - CannotProceed : ");
    } catch (NotFound se) {
          System.err.println("NamingServiceClientUtil: Exception in getContextByName() for " + contextNameDebug + 
                          " context - NotFound : ");
    } 
    return reqContext; 
  } 

  /** Gets the object by the name in the specified context 
    * @param parentContext NamingContext which contains the object being resolved 
    * @param reqObjName Name of the object being resolved.
    */
  public static org.omg.CORBA.Object getObjectByName(NamingContext parentContext, String reqObjName) {

    org.omg.CORBA.Object reqObject=null;

    //NameComponent nc1 = new NameComponent(reqObjName, reqObjName);
    NameComponent nc1 = new NameComponent(reqObjName, "");
    NameComponent [] name = {nc1};
      
    try {
       reqObject= parentContext.resolve(name);
    } catch (InvalidName se) {
          System.err.println("NamingServiceClientUtil: Exception in getObjectByName() for " + reqObjName + 
                          " context - InvalidName : ");
    } catch (CannotProceed se) {
          System.err.println("NamingServiceClientUtil: Exception in getObjectByName() for " + reqObjName + 
                          " context  - CannotProceed : ");
    } catch (NotFound se) {
          System.err.println("NamingServiceClientUtil: Exception in getObjectByName() for " + reqObjName + 
                          " context - NotFound : ");
    } 
    return reqObject; 
  } 
}


